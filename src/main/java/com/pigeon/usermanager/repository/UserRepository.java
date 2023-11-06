package com.pigeon.usermanager.repository;

import com.pigeon.usermanager.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
