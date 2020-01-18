package com.courage.platform.redis.client.builder;

import com.courage.platform.redis.client.enums.PlatformRedisConfigType;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhangyong on 2020/1/12.
 */
public class PlatformSingleRedisClientBuilder implements PlatformRedisClientBuilder {

    private final static Logger logger = LoggerFactory.getLogger(PlatformSingleRedisClientBuilder.class);

    public PlatformRedisConfigType getConfigType() {
        return PlatformRedisConfigType.SINGLE;
    }

    public RedissonClient buildClient() {
        return null;
    }

}
