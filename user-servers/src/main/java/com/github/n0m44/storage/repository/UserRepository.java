package com.github.n0m44.storage.repository;

import com.github.n0m44.storage.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByLogin(String username);
}
