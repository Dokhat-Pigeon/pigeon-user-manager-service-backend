package com.pigeon.usermanager.model.cache;

import com.pigeon.usermanager.model.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RedisHash(value = "registration")
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationCache {

    @Id
    private UUID recordId;

    private String email;

    private String login;

    private String name;

    private String password;

    private UserStatus status;

    @TimeToLive(unit = TimeUnit.MINUTES)
    private Long timeToLive;
}
