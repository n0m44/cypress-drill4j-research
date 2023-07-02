package com.github.n0m44.api;

import com.github.n0m44.service.AuthService;
import com.github.n0m44.service.UserAlreadyExistsException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.openapitools.api.AuthApi;
import org.openapitools.model.CredentialsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;

import static com.github.n0m44.security.SessionFilter.SESSION_ID_COOKIE_KEY;

/**
 * Controller for authentication
 */
@Controller
public class AuthApiImpl implements AuthApi {

    private final AuthService authService;
    private final HttpServletResponse httpServletResponse;

    @Autowired
    public AuthApiImpl(AuthService authService, HttpServletResponse httpServletResponse) {
        this.authService = authService;
        this.httpServletResponse = httpServletResponse;
    }

    @Override
    public ResponseEntity<String> login(CredentialsDto credentialsDto) {
        try {
            final String token = authService.authentication(credentialsDto.getLogin(), credentialsDto.getPassword());
            final Cookie cookie = new Cookie(SESSION_ID_COOKIE_KEY, token);
            cookie.setPath("/");
            httpServletResponse.addCookie(cookie);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<Void> registration(CredentialsDto credentialsDto)
    {
        try {
            authService.createNewUser(credentialsDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
