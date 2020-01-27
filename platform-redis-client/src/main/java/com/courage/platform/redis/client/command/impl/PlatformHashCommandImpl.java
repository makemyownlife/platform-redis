package com.courage.platform.redis.client.command.impl;

import com.courage.platform.redis.client.command.PlatformHashCommand;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;

import java.util.Set;

public class PlatformHashCommandImpl extends PlatformKeyCommandImpl implements PlatformHashCommand {

    private static JsonJacksonCodec hashCodec = JsonJacksonCodec.INSTANCE;

    public PlatformHashCommandImpl(RedissonClient redissonClient) {
        super(redissonClient);
    }

    @Override
    public Set<Object> hkeys(String key) {
        return getRedissonClient().getMap(key, hashCodec).keySet();
    }

    @Override
    public Long hdel(String key, String... fieldKeys) {
        return getRedissonClient().getMap(key, hashCodec).fastRemove(fieldKeys);
    }

    @Override
    public boolean hexists(String key, String fieldKey) {
        return getRedissonClient().getMap(key, hashCodec).containsKey(fieldKey);
    }

    @Override
    public <T> T hget(String key, String fieldKey) {
        return (T) getRedissonClient().getMap(key, hashCodec).get(fieldKey);
    }

    @Override
    public Object hset(String key, String fieldKey, Object value) {
        return  getRedissonClient().getMap(key, hashCodec).put(fieldKey , value);
    }

}
