package com.courage.platform.redis.client.springboot.starter.configuration;

import com.courage.platform.redis.client.RedisClient;
import com.courage.platform.redis.client.command.*;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.courage.platform.redis")
@Import(value = {PlatformRedisTypeConfiguration.class})
public class PlatformRedisClientConfiguration {

    private final static Logger logger = LoggerFactory.getLogger(PlatformRedisClientConfiguration.class);

    @Bean
    public RedisClient createPlatformRedisClient(Config config) {
        RedisClient redisClient = new RedisClient(config);
        return redisClient;
    }

    @Bean
    @ConditionalOnBean(RedisClient.class)
    public StringCommand createPlatformStringCommand(RedisClient redisClient) {
        return redisClient.getPlatformStringCommand();
    }

    @Bean
    @ConditionalOnBean(RedisClient.class)
    public ZSetCommand createPlatformZSetCommand(RedisClient redisClient) {
        return redisClient.getPlatformZSetCommand();
    }

    @Bean
    @ConditionalOnBean(RedisClient.class)
    public SetCommand createPlatformSetCommand(RedisClient redisClient) {
        return redisClient.getPlatformSetCommand();
    }

    @Bean
    @ConditionalOnBean(RedisClient.class)
    public AtomicCommand createPlatformAtomicCommand(RedisClient redisClient) {
        return redisClient.getPlatformAtomicCommand();
    }

    @Bean
    @ConditionalOnBean(RedisClient.class)
    public HashCommand createPlatformHashCommand(RedisClient redisClient) {
        return redisClient.getPlatformHashCommand();
    }

    @Bean
    @ConditionalOnBean(RedisClient.class)
    public ScriptCommand createPlatformScriptCommand(RedisClient redisClient) {
        return redisClient.getPlatformScriptCommand();
    }


}
