package com.courage.platform.redis.client.springboot.starter.configuration;

import com.courage.platform.redis.client.RedisOperation;
import com.courage.platform.redis.client.command.*;
import com.courage.platform.redis.client.config.SentinelConfig;
import com.courage.platform.redis.client.config.SingleConfig;
import com.courage.platform.redis.client.utils.ConfigBuilder;
import com.courage.platform.redis.client.utils.IdGenerator;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 根据type类型不同 配置不同的
 * Created by zhangyong on 2020/1/21.
 */
@Configuration
public class RedisClientAutoConfiguration {

    @Configuration
    @ConditionalOnMissingBean(Config.class)
    @ConditionalOnProperty(name = "spring.redis.type", havingValue = "single")
    static class StaticBuildSingleServer {

        @Bean(value = "platformSingleServerConfig")
        @ConfigurationProperties(prefix = "spring.redis.single")
        public SingleConfig getSingleConfig() {
            SingleConfig config = new SingleConfig();
            return config;
        }

        @Bean
        public Config singleServerConfig(SingleConfig singleConfig) {
            return ConfigBuilder.buildBySingleServerConfig(singleConfig);
        }

        @Bean(destroyMethod = "shutdown")
        public RedisOperation redisOperation(Config config) {
            RedisOperation redisOperation = new RedisOperation(config);
            return redisOperation;
        }

    }

    @Configuration
    @ConditionalOnMissingBean(Config.class)
    @ConditionalOnProperty(name = "spring.redis.type", havingValue = "sentinel")
    static class StaticBuildSentinelServer {

        @Bean(value = "platformSentinelServerConfig")
        @ConfigurationProperties(prefix = "spring.redis.sentinel")
        public SentinelConfig getPlatformSentinelServersConfig() {
            SentinelConfig sentinelConfig = new SentinelConfig();
            return sentinelConfig;
        }

        @Bean
        public Config sentinelServerConfig(SentinelConfig sentinelConfig) {
            return ConfigBuilder.buildBySentinelServerConfig(sentinelConfig);
        }

        @Bean(destroyMethod = "shutdown")
        public RedisOperation redisOperation(Config config) {
            RedisOperation redisOperation = new RedisOperation(config);
            return redisOperation;
        }

    }

    @Bean("stringCommand")
    @ConditionalOnBean(RedisOperation.class)
    public StringCommand createStringCommand(RedisOperation redisOperation) {
        return redisOperation.getStringCommand();
    }

    @Bean
    @ConditionalOnBean(RedisOperation.class)
    public ZSetCommand createZSetCommand(RedisOperation redisOperation) {
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
    public HashCommand createHashCommand(RedisOperation redisOperation) {
        return redisOperation.getHashCommand();
    }

    @Bean
    @ConditionalOnBean(RedisOperation.class)
    public ScriptCommand createPlatformScriptCommand(RedisOperation redisOperation) {
        return redisOperation.getScriptCommand();
    }

    @Bean
    @Primary
    @ConditionalOnBean(RedisOperation.class)
    public IdGenerator createIdGenerator(RedisOperation redisOperation) {
        return new IdGenerator(redisOperation);
    }

}
