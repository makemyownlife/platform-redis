package com.courage.platform.redis.client.command.impl;

import com.courage.platform.redis.client.command.PlatformStringCommand;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * string command 实现
 * Created by zhangyong on 2020/1/19.
 */
public class PlatformStringCommandImpl extends PlatformKeyCommand implements PlatformStringCommand {

    public PlatformStringCommandImpl(RedissonClient redissonClient) {
        super(redissonClient);
    }

    public String get(String key) {
        RBucket<String> result = getRedissonClient().getBucket(key);
        if (result == null || !result.isExists()) {
            return null;
        }
        return result.get();
    }

    public void set(String key, String value) {
        RBucket<String> result = getRedissonClient().getBucket(key);
        result.set(value);
    }

    public void setEx(String key, String value, int second) {
        RBucket<String> result = getRedissonClient().getBucket(key);
        result.set(value, second, TimeUnit.SECONDS);
    }

    public boolean setNx(String key, String value) {
        RBucket<String> result = getRedissonClient().getBucket(key);
        return result.trySet(value);
    }

    public boolean setNx(String key, String value, int aliveSecond) {
        RBucket<String> result = getRedissonClient().getBucket(key);
        return result.trySet(value, aliveSecond, TimeUnit.SECONDS);
    }

}
