package com.pigeon.usermanager.model.cache;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.ZonedDateTime;

@RedisHash(value = "user_online")
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOnlineCache {

    @Id
    private Long id;

    private Boolean isOnline;

    private ZonedDateTime lastOnlineDate;
}
