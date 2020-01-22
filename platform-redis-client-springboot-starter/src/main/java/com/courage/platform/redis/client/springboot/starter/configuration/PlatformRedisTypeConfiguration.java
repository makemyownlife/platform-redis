package com.courage.platform.redis.client.springboot.starter.configuration;

import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 根据type类型不同 配置不同的
 * Created by zhangyong on 2020/1/21.
 */
@AutoConfigureBefore(PlatformRedisClientConfiguration.class)
public class PlatformRedisTypeConfiguration {

    @Configuration
    @ConditionalOnMissingBean(Config.class)
    @ConditionalOnProperty(name = "platform.redis.type", havingValue = "SINGLE")
    static class SingleServerConfig {

        @Bean
        public Config singleServerConfig() {
            Config config = new Config();
            return config;
        }

    }

    @Configuration
    @ConditionalOnMissingBean(Config.class)
    @ConditionalOnProperty(name = "platform.redis.type", havingValue = "MASTER_SLAVE")
    static class MasterSlaveServerConfig {

        @Bean
        public Config masterSlaveServerConfig() {
            Config config = new Config();
            return config;
        }

    }


}
