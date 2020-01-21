package com.courage.platform.redis.client.springboot.starter.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.courage.platform.redis")
@Import(value = {PlatformRedisTypeConfiguration.class})
public class PlatformRedisClientConfiguration {

    private final static Logger logger = LoggerFactory.getLogger(PlatformRedisClientConfiguration.class);


}
