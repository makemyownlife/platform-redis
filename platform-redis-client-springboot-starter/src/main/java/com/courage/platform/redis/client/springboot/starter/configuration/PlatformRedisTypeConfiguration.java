package com.courage.platform.redis.client.springboot.starter.configuration;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;

/**
 * 根据type类型不同 配置不同的
 * Created by zhangyong on 2020/1/21.
 */
@AutoConfigureBefore(PlatformRedisClientConfiguration.class)
public class PlatformRedisTypeConfiguration {



}
