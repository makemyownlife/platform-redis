package com.courage.platform.redis.client.lock.impl;

import org.redisson.api.RedissonClient;

import java.util.concurrent.locks.Lock;

public abstract class AbstractLock implements Lock {

    private RedissonClient redissonClient;

    private String name;

    public AbstractLock(RedissonClient redissonClient, String name) {
        this.redissonClient = redissonClient;
        this.name = name;
    }

    public RedissonClient getRedissonClient() {
        return redissonClient;
    }

    public String getName() {
        return name;
    }

}
