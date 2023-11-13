package com.pigeon.usermanager.repository.cache;

import com.pigeon.usermanager.model.cache.RegistrationCache;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

import java.util.UUID;

public interface RegistrationCacheRepository extends KeyValueRepository<RegistrationCache, UUID> {
}
