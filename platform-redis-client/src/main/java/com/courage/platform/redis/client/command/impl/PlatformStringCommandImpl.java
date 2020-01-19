package com.courage.platform.redis.client.command.impl;

import com.courage.platform.redis.client.command.PlatformStringCommand;
import org.redisson.api.RedissonClient;

/**
 * string command 实现
 * Created by zhangyong on 2020/1/19.
 */
public class PlatformStringCommandImpl extends PlatformKeyCommand implements PlatformStringCommand {

    public PlatformStringCommandImpl(RedissonClient redissonClient) {
        super(redissonClient);
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
