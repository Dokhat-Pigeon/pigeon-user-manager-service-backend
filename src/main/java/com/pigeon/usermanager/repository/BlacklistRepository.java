package com.pigeon.usermanager.repository;

import com.pigeon.usermanager.model.entity.BlacklistEntity;
import org.springframework.data.repository.CrudRepository;

public interface BlacklistRepository extends CrudRepository<BlacklistEntity, Long> {
}
