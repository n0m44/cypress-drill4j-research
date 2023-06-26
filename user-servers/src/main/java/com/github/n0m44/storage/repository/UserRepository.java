package com.github.n0m44.storage.repository;

import com.github.n0m44.storage.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
