package com.courage.platform.redis.client.springboot.starter.configuration;

import com.courage.platform.redis.client.springboot.starter.configuration.config.PlatformClusterServerConfigBoot;
import com.courage.platform.redis.client.springboot.starter.configuration.config.PlatformSingleServerConfigBoot;
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
    @EnableConfigurationProperties(PlatformSingleServerConfigBoot.class)
    static class StaticBuildSingleServer {

        @Bean
        public Config singleServerConfig(PlatformSingleServerConfigBoot platformSingleServerConfigBoot) {
            Config config = new Config();
            config.useSingleServer().setAddress(platformSingleServerConfigBoot.getAddress());
            if (platformSingleServerConfigBoot.getPassword() != null) {
                config.useSingleServer().setPassword(platformSingleServerConfigBoot.getPassword());
            }
            return config;
        }

    }

    @Configuration
    @ConditionalOnMissingBean(Config.class)
    @ConditionalOnProperty(name = "platform.redis.type", havingValue = "CLUSTER")
    @EnableConfigurationProperties(PlatformClusterServerConfigBoot.class)
    static class StaticBuildClusterServer {

        @Bean
        public Config clusterServerConfig(PlatformClusterServerConfigBoot platformClusterServerConfigBoot) {
            Config config = new Config();
            for (String node : platformClusterServerConfigBoot.getNodes()) {
                config.useClusterServers().addNodeAddress(node);
            }
            return config;
        }

    }


}
