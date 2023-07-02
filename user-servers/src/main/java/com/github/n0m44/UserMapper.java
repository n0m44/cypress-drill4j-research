package com.github.n0m44;

import com.github.n0m44.storage.entity.UserEntity;
import org.openapitools.model.CredentialsDto;
import org.openapitools.model.UserDto;
import org.openapitools.model.UserWithPasswordDto;

public class UserMapper {
    public static UserEntity toUserEntity(final CredentialsDto credentialsDto) {
        return new UserEntity(credentialsDto.getLogin(), null, null, credentialsDto.getPassword());
    }

    public static UserEntity toUserEntity(final UserWithPasswordDto userWithPasswordDto) {
        return new UserEntity(userWithPasswordDto.getLogin(), userWithPasswordDto.getName(),
                userWithPasswordDto.getSurname(), userWithPasswordDto.getPassword())
                .setId(userWithPasswordDto.getId());
    }

    public static UserDto toUserDto(final UserEntity userEntity) {
        return new UserDto()
                .id(userEntity.getId())
                .login(userEntity.getLogin())
                .name(userEntity.getName())
                .surname(userEntity.getSurname());
    }
}
