package com.deloop.user.data.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisConfiguration {
    public static final int REDIS_PORT = 6379;
    public static final String REDIS_HOST = "127.0.0.1";
    public static final String REDIS_USERNAME = "default";
    public static final String REDIS_PASSWORD = "123";

    @Bean
    LettuceConnectionFactory lettuceConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(REDIS_HOST, REDIS_PORT);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(REDIS_PASSWORD));
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(lettuceConnectionFactory());
//        return template;
//    }

    @Bean
    public RedisClient redisAsyncCommands() {
        RedisURI redisURI = RedisURI.Builder
                .redis(REDIS_HOST)
                .withPassword(REDIS_PASSWORD.toCharArray())
//                .withPort(REDIS_PORT)
                .build();
        System.err.println(redisURI);
        RedisClient redisClient = RedisClient.create(redisURI);
//        redisClient.setOptions(ClientOptions.builder().protocolVersion(ProtocolVersion.RESP2).build());
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        return redisClient;
    }
}
