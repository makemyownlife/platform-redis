package com.courage.platform.redis.client.command.impl;

import com.courage.platform.redis.client.command.PlatformZSetCommand;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;

import java.util.Map;
import java.util.Set;

public class PlatformZSetCommandImpl extends PlatformKeyCommandImpl implements PlatformZSetCommand {

    private static JsonJacksonCodec codec = JsonJacksonCodec.INSTANCE;

    public PlatformZSetCommandImpl(RedissonClient redissonClient) {
        super(redissonClient);
    }

    @Override
    public Long zadd(String key, Map<String, Double> scoreMembers) {
        return null;
    }

    @Override
    public Long zaddAndEx(String key, Map<String, Double> scoreMembers, int aliveSecond) {
        return null;
    }

    @Override
    public Long zadd(String key, double score, String member) {
        return null;
    }

    @Override
    public Long zaddAndEx(String key, double score, String member, int aliveSecond) {
        return null;
    }

    @Override
    public Set<String> zrange(String key, long start, long end) {
        return null;
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max) {
        return null;
    }

    @Override
    public Long zrem(String key, String... members) {
        return null;
    }

    @Override
    public Long zremrangeByScore(String key, double min, double max) {
        return null;
    }

    @Override
    public Double zincrbyAndEx(String key, double score, String member, int aliveSecond) {
        return null;
    }

    @Override
    public Double zincrby(String key, double score, String member) {
        return null;
    }

    @Override
    public Long zrank(String key, String member) {
        return null;
    }

    @Override
    public Long zrevrank(String key, String member) {
        return null;
    }

    @Override
    public Set<String> zrevrange(String key, long start, long end) {
        return null;
    }
}
