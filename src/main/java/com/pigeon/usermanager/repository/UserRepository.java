package com.pigeon.usermanager.repository;

import com.pigeon.usermanager.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByLoginOrEmail(String login, String email);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByLogin(String login);
}
