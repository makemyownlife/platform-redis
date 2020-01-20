package com.courage.platform.redis.client.command.impl;

import com.courage.platform.redis.client.command.PlatformListCommand;
import org.redisson.api.RedissonClient;

import java.util.HashMap;
import java.util.List;

public class PlatformListCommandImpl extends PlatformKeyCommand implements PlatformListCommand {

    public PlatformListCommandImpl(RedissonClient redissonClient) {
        super(redissonClient);
    }

    public Long lpush(String key, Object... o) {
        return null;
    }

    public <T> List<T> lrange(String key, long startNum, long endNum) {
        return null;
    }

    public String ltrim(String key, long startNum, long endNum) {
        return null;
    }

    public Long llen(String key) {
        return null;
    }

    public <T> T rpop(String key) {
        return null;
    }

    public <T> T lpop(String key) {
        return null;
    }

    public Long rpush(String key, Object... o) {
        return null;
    }

    public <T> HashMap<String, T> blpop(String... keys) {
        return null;
    }

    public <T> HashMap<String, T> blpop(int timeout, String... keys) {
        return null;
    }

    public <T> HashMap<String, T> brpop(String... keys) {
        return null;
    }

    public <T> HashMap<String, T> brpop(int timeout, String... keys) {
        return null;
    }

}
