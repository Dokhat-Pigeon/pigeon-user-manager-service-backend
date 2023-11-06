package com.pigeon.usermanager.model.cache;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.io.Serializable;

//TODO Удалить или поменять название и поля
@RedisHash("User")
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCache implements Serializable {

    @Id
    private Long id;

    private String value;
}
