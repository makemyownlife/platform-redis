package com.courage.platform.redis.client.springboot.starter.configuration;

import com.courage.platform.redis.client.PlatformRedisClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

}
