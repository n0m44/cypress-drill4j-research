package com.github.n0m44.api;

import org.openapitools.api.UserApi;
import org.openapitools.model.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserApiImpl implements UserApi {
    @Override
    public ResponseEntity<UserDto> createUser(UserDto userDto) {
        return UserApi.super.createUser(userDto);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Integer id) {
        return UserApi.super.deleteUser(id);
    }

    @Override
    public ResponseEntity<UserDto> getUser(Integer id) {
        return UserApi.super.getUser(id);
    }

    @Override
    public ResponseEntity<List<UserDto>> getUsers() {
        return UserApi.super.getUsers();
    }

    @Override
    public ResponseEntity<UserDto> updateUser(Integer id, UserDto userDto) {
        return UserApi.super.updateUser(id, userDto);
    }
}
