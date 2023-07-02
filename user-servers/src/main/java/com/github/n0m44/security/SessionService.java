package com.github.n0m44.security;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SessionService {
    private record UserToken(String login, LocalDateTime timeExpiration) {}
    private static final Map<String, UserToken> SESSION_STORAGE = new ConcurrentHashMap<>();

    static {
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            final HashMap<String, UserToken> sessionsForDelete = new HashMap<>(SESSION_STORAGE);
            sessionsForDelete.values().removeIf(userToken -> LocalDateTime.now().isBefore(userToken.timeExpiration));
            sessionsForDelete.keySet().forEach(SESSION_STORAGE::remove);
        }, 10, 10, TimeUnit.MINUTES);
    }

    public static void addUserSession(String sessionId, String login)
    {
        final UserToken userToken = new UserToken(login, LocalDateTime.now().plus(5, ChronoUnit.MINUTES));
        SESSION_STORAGE.put(sessionId, userToken);
    }

    public static Optional<String> getUserLoginBySessionId(String sessionId)
    {
        if (sessionId == null)
        {
            return Optional.empty();
        }
        final UserToken userToken = SESSION_STORAGE.get(sessionId);
        if (userToken == null)
            return Optional.empty();
        final UserToken newUserToken = new UserToken(userToken.login(), LocalDateTime.now().plus(5, ChronoUnit.MINUTES));
        SESSION_STORAGE.put(sessionId, newUserToken);
        return Optional.of(userToken.login());
    }
}
