package com.courage.platform.redis.client.command.impl;

import com.courage.platform.redis.client.command.PlatformRedisCommand;
import org.redisson.api.RedissonClient;

public class PlatformBasicCommand implements PlatformRedisCommand {

    private RedissonClient redissonClient;

    public PlatformBasicCommand(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public Long del(String key) {
        return null;
    }

    public Long ttl(String key) {
        return null;
    }

    public boolean exists(String key) {
        return false;
    }

    public String type(String key) {
        return null;
    }

    public Long expire(String key, int seconds) {
        return null;
    }

    public Long expireAt(String key, long unixTime) {
        return null;
    }

    public Long pexpire(String key, long seconds) {
        return null;
    }

    public Long pexpireAt(String key, long unixTime) {
        return null;
    }

}
