package com.courage.platform.redis.client;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 平台redis 客户端
 * Created by zhangyong on 2020/1/18.
 */
public class PlatformRedisClient {

    private final static Logger logger = LoggerFactory.getLogger(PlatformRedisClient.class);

    private RedissonClient redissonClient;

    public PlatformRedisClient(Config config) {
        this.redissonClient = Redisson.create(config);
    }

}
