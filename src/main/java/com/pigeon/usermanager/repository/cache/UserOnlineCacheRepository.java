package com.pigeon.usermanager.repository.cache;

import com.pigeon.usermanager.model.cache.UserOnlineCache;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

public interface UserOnlineCacheRepository extends KeyValueRepository<UserOnlineCache, Long> {
}
