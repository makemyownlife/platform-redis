package com.courage.platform.redis.client.springboot.starter.configuration;

import com.courage.platform.redis.client.config.ClusterConfig;
import com.courage.platform.redis.client.config.SingleConfig;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
    static class StaticBuildSingleServer {

        @Bean(value = "platformSingleServerConfig")
        @ConfigurationProperties(prefix = "platform.redis.singleserver")
        public SingleConfig getPlatformSingleServerConfig() {
            SingleConfig config = new SingleConfig();
            return config;
        }

        @Bean
        public Config singleServerConfig(SingleConfig singleConfig) {
            Config config = new Config();
            config.useSingleServer().setAddress(singleConfig.getAddress());
            if (singleConfig.getPassword() != null) {
                config.useSingleServer().setPassword(singleConfig.getPassword());
            }
            return config;
        }

    }

    @Configuration
    @ConditionalOnMissingBean(Config.class)
    @ConditionalOnProperty(name = "platform.redis.type", havingValue = "CLUSTER")
    static class StaticBuildClusterServer {

        @Bean(value = "platformClusterServerConfig")
        @ConfigurationProperties(prefix = "platform.redis.clusterserver")
        public ClusterConfig getPlatformClusterServerConfig() {
            ClusterConfig config = new ClusterConfig();
            return config;
        }

        @Bean
        public Config clusterServerConfig(ClusterConfig clusterConfig) {
            Config config = new Config();
            for (String node : clusterConfig.getNodes()) {
                config.useClusterServers().addNodeAddress(node);
            }
            return config;
        }

    }


}
