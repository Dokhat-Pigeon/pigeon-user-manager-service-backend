package com.pigeon.usermanager.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Конфигурирование объектов, связанных с базой данных Redis.
 */
@Configuration
@EnableRedisRepositories("com.pigeon.usermanager.repository.cache")
@Slf4j
public class RedisConfig {

    @Value("${spring.redis.timeout}")
    private Long timeout;
    @Value("${spring.redis.database}")
    private Integer database;
    @Value("${spring.redis.sentinel.master}")
    private String master;
    @Value("${spring.redis.sentinel.password}")
    private String password;
    @Value("#{'${spring.redis.sentinel.nodes}'.split(', ')}")
    private String[] nodes;

    /**
     * Бин для соединения с базой Redis через кластер Sentinel.
     */
    @Bean
    public RedisConnectionFactory connectionFactory() {
        List<RedisNode> redisNodes = Stream.of(nodes)
                .map(n -> new RedisNode(n.split(":")[0], Integer.parseInt(n.split(":")[1])))
                .collect(Collectors.toList());

        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration();
        sentinelConfig.setSentinels(redisNodes);
        sentinelConfig.setMaster(master);
        sentinelConfig.setSentinelPassword(password);
        sentinelConfig.setPassword(password);
        sentinelConfig.setDatabase(database);

        LettuceClientConfiguration lettuceConfig = LettuceClientConfiguration.builder()
                .commandTimeout(Duration.ofMillis(timeout))
                .build();

        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(sentinelConfig, lettuceConfig);
        connectionFactory.afterPropertiesSet();
        connectionFactory.getConnection();
        log.info("Redis connection factory is created");
        return connectionFactory;
    }
}
