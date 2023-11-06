package com.pigeon.usermanager.repository.cache;

import com.pigeon.usermanager.model.cache.UserCache;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

//TODO Удалить или поменять название
public interface UserCacheRepository extends KeyValueRepository<UserCache, Long> {
}
