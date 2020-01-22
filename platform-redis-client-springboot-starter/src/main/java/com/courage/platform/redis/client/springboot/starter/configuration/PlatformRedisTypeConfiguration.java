package com.courage.platform.redis.client.springboot.starter.configuration;

import com.courage.platform.redis.client.springboot.starter.configuration.config.PlatformSingleServerConfig;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
    @EnableConfigurationProperties(PlatformSingleServerConfig.class)
    static class StaticBuildSingleServer {

        @Bean
        public Config singleServerConfig(PlatformSingleServerConfig platformSingleServerConfig) {
            Config config = new Config();
            config.useSingleServer().setAddress(platformSingleServerConfig.getAddress());
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
