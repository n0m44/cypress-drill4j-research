package com.github.n0m44.service;

import java.util.UUID;

import com.github.n0m44.UserMapper;
import com.github.n0m44.security.SessionService;
import org.openapitools.model.CredentialsDto;
import org.openapitools.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.AuthenticationException;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    public String authentication(String login, String password) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        final String sessionId = UUID.randomUUID().toString();
        SessionService.addUserSession(sessionId, login);
        return sessionId;
    }

    public UserDto createNewUser(CredentialsDto credentialsDto) throws UserAlreadyExistsException {
        return userService.createNewUser(credentialsDto);
    }
}
