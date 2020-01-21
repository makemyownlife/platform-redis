package com.courage.platform.redis.client.springboot.starter.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.courage.platform.redis")
public class PlatformRedisConfiguration {

    private final static Logger logger = LoggerFactory.getLogger(PlatformRedisConfiguration.class);


}
