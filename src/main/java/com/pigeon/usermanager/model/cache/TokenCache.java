package com.pigeon.usermanager.model.cache;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "token")
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenCache {

    @Id
    private String login;

    private String authorization;

    private String refresh;
}
