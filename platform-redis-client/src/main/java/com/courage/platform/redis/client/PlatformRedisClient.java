package com.courage.platform.redis.client;

import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 平台redis 客户端
 * Created by zhangyong on 2020/1/18.
 */
public class PlatformRedisClient {

    private final static Logger logger = LoggerFactory.getLogger(PlatformRedisClient.class);

    private RedissonClient redissonClient;

    public PlatformRedisClient(PlatformRedisClientBuilder platformRedisClientBuilder) {
        this.redissonClient = platformRedisClientBuilder.buildClient();
    }

}
