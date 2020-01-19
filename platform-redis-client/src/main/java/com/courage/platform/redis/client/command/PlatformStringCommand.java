package com.courage.platform.redis.client.command;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;

/**
 * Created by zhangyong on 2020/1/19.
 */
public class PlatformStringCommand extends PlatformKeyCommand {

    public PlatformStringCommand(RedissonClient redissonClient) {
        super(redissonClient);
    }

    public String get(String key) {
        RedissonClient redissonClient = super.getRedissonClient();
        RBucket<String> result = redissonClient.getBucket(key);
        if (result == null || !result.isExists()) {
            return null;
        }
        return result.get();
    }

    public String set(String key, String value) {
        return null;
    }

    public String setEx(String key, String value, int second) {
        return null;
    }

    public Long setNx(String key, String value) {
        return null;
    }

    public Long setNx(String key, String value, int aliveSecond) {
        return null;
    }

    public String getAndSet(String key, String value) {
        return null;
    }

    public String setNxEx(String key, String value, int second) {
        return null;
    }

}
