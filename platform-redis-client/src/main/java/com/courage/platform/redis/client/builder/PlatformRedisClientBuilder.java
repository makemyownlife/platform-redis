package com.courage.platform.redis.client.builder;

import com.courage.platform.redis.client.enums.PlatformRedisConfigType;
import org.redisson.api.RedissonClient;

/**
 * Created by zhangyong on 2020/1/12.
 */
public interface PlatformRedisClientBuilder {

    PlatformRedisConfigType getConfigType();

    RedissonClient buildClient();

}
