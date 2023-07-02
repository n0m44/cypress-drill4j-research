package com.github.n0m44.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class SessionFilter extends OncePerRequestFilter {
    public static final String SESSION_ID_COOKIE_KEY = "sessionId";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String sessionId = null;
        Cookie[] cookies = Objects.requireNonNullElse(request.getCookies(), new Cookie[0]);
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(SESSION_ID_COOKIE_KEY)) {
                sessionId = cookie.getValue();
                break;
            }
        }

        final Optional<String> userLoginBySessionId = SessionService.getUserLoginBySessionId(sessionId);
        if (userLoginBySessionId.isPresent() && SecurityContextHolder.getContext().getAuthentication() == null) {
            final ApiKeyAuthentication apiKeyAuthentication = new ApiKeyAuthentication(sessionId, AuthorityUtils.NO_AUTHORITIES);
            SecurityContextHolder.getContext().setAuthentication(apiKeyAuthentication);
        }
        filterChain.doFilter(request, response);
    }
}
