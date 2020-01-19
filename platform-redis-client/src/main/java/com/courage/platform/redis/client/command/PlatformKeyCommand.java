package com.courage.platform.redis.client.command;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

public class PlatformKeyCommand implements PlatformRedisCommand {

    private RedissonClient redissonClient;

    public PlatformKeyCommand(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public RedissonClient getRedissonClient() {
        return redissonClient;
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
