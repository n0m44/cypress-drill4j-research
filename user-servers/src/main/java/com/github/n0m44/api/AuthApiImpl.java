package com.github.n0m44.api;

import org.openapitools.api.AuthApi;
import org.openapitools.model.CredentialsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * Controller for authentication
 */
@Controller
public class AuthApiImpl implements AuthApi {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthApiImpl(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<String> login(CredentialsDto credentialsDto) {
        final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        final String login = credentialsDto.getLogin();
        final UserDetails userDetails = userDetailsService.loadUserByUsername(login);
        return new ResponseEntity<>("sessionId", HttpStatus.OK);
    }
}
