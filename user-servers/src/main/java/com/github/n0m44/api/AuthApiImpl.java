package com.github.n0m44.api;

import org.openapitools.api.AuthApi;
import org.openapitools.model.CredentialsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

/**
 * Controller for authentication
 */
@Controller
public class AuthApiImpl implements AuthApi {
    @Override
    public ResponseEntity<Object> login(CredentialsDto credentialsDto) {
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
