package com.pigeon.usermanager.repository.cache;

import com.pigeon.usermanager.model.cache.ChangePasswordCache;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

import java.util.UUID;

public interface ChangePasswordCacheRepository extends KeyValueRepository<ChangePasswordCache, UUID> {
}
