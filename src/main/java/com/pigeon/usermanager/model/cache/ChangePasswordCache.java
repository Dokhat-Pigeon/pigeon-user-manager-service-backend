package com.pigeon.usermanager.model.cache;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RedisHash(value = "change_password")
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordCache {

    @Id
    private UUID recordId;

    private Long userId;

    @TimeToLive(unit = TimeUnit.MINUTES)
    private Long timeToLive;
}
