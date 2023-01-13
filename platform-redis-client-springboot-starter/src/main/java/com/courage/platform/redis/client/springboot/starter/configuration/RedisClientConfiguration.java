package com.courage.platform.redis.client.springboot.starter.configuration;

import com.courage.platform.redis.client.RedisOperation;
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
@Import(value = {RedisTypeConfiguration.class})
public class RedisClientConfiguration {

    private final static Logger logger = LoggerFactory.getLogger(RedisClientConfiguration.class);

    @Bean
    public RedisOperation createPlatformRedisClient(Config config) {
        RedisOperation redisOperation = new RedisOperation(config);
        return redisOperation;
    }

    @Bean
    @ConditionalOnBean(RedisOperation.class)
    public StringCommand createPlatformStringCommand(RedisOperation redisOperation) {
        return redisOperation.getStringCommand();
    }

    @Bean
    @ConditionalOnBean(RedisOperation.class)
    public ZSetCommand createPlatformZSetCommand(RedisOperation redisOperation) {
        return redisOperation.getZSetCommand();
    }

    @Bean
    @ConditionalOnBean(RedisOperation.class)
    public SetCommand createPlatformSetCommand(RedisOperation redisOperation) {
        return redisOperation.getSetCommand();
    }

    @Bean
    @ConditionalOnBean(RedisOperation.class)
    public AtomicCommand createPlatformAtomicCommand(RedisOperation redisOperation) {
        return redisOperation.getAtomicCommand();
    }

    @Bean
    @ConditionalOnBean(RedisOperation.class)
    public HashCommand createPlatformHashCommand(RedisOperation redisOperation) {
        return redisOperation.getHashCommand();
    }

    @Bean
    @ConditionalOnBean(RedisOperation.class)
    public ScriptCommand createPlatformScriptCommand(RedisOperation redisOperation) {
        return redisOperation.getScriptCommand();
    }


}
