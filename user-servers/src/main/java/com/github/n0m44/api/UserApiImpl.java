package com.github.n0m44.api;

import com.github.n0m44.service.UserAlreadyExistsException;
import com.github.n0m44.service.UserNotFoundException;
import com.github.n0m44.service.UserService;
import org.openapitools.api.UserApi;
import org.openapitools.model.UserDto;
import org.openapitools.model.UserWithPasswordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserApiImpl implements UserApi {
    private final UserService userService;

    @Autowired
    public UserApiImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserDto> createUser(UserWithPasswordDto userDto) {
        try {
            final UserDto newUser = userService.createUser(userDto);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> getUser(Long id) {
        try {
            final UserDto userDto = userService.getUser(id);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity<>(userService.getListUsers(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> updateUser(UserWithPasswordDto userDto) {
        try {
            final UserDto updatedUser = userService.updateUser(userDto);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
