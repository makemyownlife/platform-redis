package com.courage.platform.redis.client.springboot.starter.configuration;

import com.courage.platform.redis.client.PlatformRedisClient;
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
    public PlatformRedisClient createPlatformRedisClient(Config config) {
        PlatformRedisClient platformRedisClient = new PlatformRedisClient(config);
        return platformRedisClient;
    }

    @Bean
    @ConditionalOnBean(PlatformRedisClient.class)
    public PlatformStringCommand createPlatformStringCommand(PlatformRedisClient platformRedisClient) {
        return platformRedisClient.getPlatformStringCommand();
    }

    @Bean
    @ConditionalOnBean(PlatformRedisClient.class)
    public PlatformZSetCommand createPlatformZSetCommand(PlatformRedisClient platformRedisClient) {
        return platformRedisClient.getPlatformZSetCommand();
    }

    @Bean
    @ConditionalOnBean(PlatformRedisClient.class)
    public PlatformSetCommand createPlatformSetCommand(PlatformRedisClient platformRedisClient) {
        return platformRedisClient.getPlatformSetCommand();
    }

    @Bean
    @ConditionalOnBean(PlatformRedisClient.class)
    public PlatformAtomicCommand createPlatformAtomicCommand(PlatformRedisClient platformRedisClient) {
        return platformRedisClient.getPlatformAtomicCommand();
    }

    @Bean
    @ConditionalOnBean(PlatformRedisClient.class)
    public PlatformHashCommand createPlatformHashCommand(PlatformRedisClient platformRedisClient) {
        return platformRedisClient.getPlatformHashCommand();
    }

    @Bean
    @ConditionalOnBean(PlatformRedisClient.class)
    public PlatformScriptCommand createPlatformScriptCommand(PlatformRedisClient platformRedisClient) {
        return platformRedisClient.getPlatformScriptCommand();
    }


}
