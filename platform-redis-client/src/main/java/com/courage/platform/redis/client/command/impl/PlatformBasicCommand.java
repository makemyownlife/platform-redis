package com.courage.platform.redis.client.command.impl;

import com.courage.platform.redis.client.command.PlatformRedisCommand;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

public class PlatformBasicCommand implements PlatformRedisCommand {

    private RedissonClient redissonClient;

    public PlatformBasicCommand(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public boolean del(String key) {
        RMap map = redissonClient.getMap(key);
        return map.delete();
    }

    public boolean exists(String key) {
        RMap map = redissonClient.getMap(key);
        return map.isExists();
    }

    public boolean expire(String key, int seconds) {
        RMap map = redissonClient.getMap(key);
        return map.expire(seconds, TimeUnit.SECONDS);
    }

}
