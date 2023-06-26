package com.github.n0m44.api;

import org.openapitools.api.UserApi;
import org.openapitools.model.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class UserApiImpl implements UserApi {
    @Override
    public ResponseEntity<UserDto> createUser(Object id, UserDto userDto) {
        return UserApi.super.createUser(id, userDto);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Object id) {
        return UserApi.super.deleteUser(id);
    }

    @Override
    public ResponseEntity<UserDto> getUser(Object id) {
        return UserApi.super.getUser(id);
    }

    @Override
    public ResponseEntity<Object> getUsers() {
        return UserApi.super.getUsers();
    }

    @Override
    public ResponseEntity<UserDto> updateUser(Object id, UserDto userDto) {
        return UserApi.super.updateUser(id, userDto);
    }
}
