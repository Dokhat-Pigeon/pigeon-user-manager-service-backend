package com.pigeon.usermanager.repository.cache;

import com.pigeon.usermanager.model.cache.TokenCache;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

import java.util.Optional;

public interface TokenCacheRepository extends KeyValueRepository<TokenCache, String> {

    Optional<TokenCache> findTokenCacheByLogin(String login);
}
