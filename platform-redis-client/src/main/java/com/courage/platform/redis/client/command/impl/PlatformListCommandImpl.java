package com.courage.platform.redis.client.command.impl;

import com.courage.platform.redis.client.command.PlatformListCommand;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;

import java.util.List;

public class PlatformListCommandImpl extends PlatformKeyCommand implements PlatformListCommand {

    public PlatformListCommandImpl(RedissonClient redissonClient) {
        super(redissonClient);
    }

    public boolean lpush(String key, Object o) {
        RList result = getRedissonClient().getList(key);
        return result.add(o);
    }

    public <T> List<T> lrange(String key, int startNum, int endNum) {
        RList result = getRedissonClient().getList(key);
        return result.subList(startNum, endNum);
    }

    public void ltrim(String key, int startNum, int endNum) {
        RList result = getRedissonClient().getList(key);
        result.trim(startNum, endNum);
    }

    public Integer llen(String key) {
        RList result = getRedissonClient().getList(key);
        return result.size();
    }

    public <T> T rpop(String key) {
        RBlockingDeque result = getRedissonClient().getBlockingDeque(key);
        return (T) result.pollLast();
    }

    public <T> T lpop(String key) {
        RBlockingDeque result = getRedissonClient().getBlockingDeque(key);
        return (T) result.pollFirst();
    }

    public Long rpush(String key, Object... o) {
        return null;
    }

}
