package com.pigeon.usermanager.repository.cache;

import com.pigeon.usermanager.model.cache.RegistrationCache;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

import java.util.Optional;
import java.util.UUID;

public interface RegistrationCacheRepository extends KeyValueRepository<RegistrationCache, UUID> {

    Optional<RegistrationCache> findByLogin(String login);

    Optional<RegistrationCache> findByEmail(String email);
}
