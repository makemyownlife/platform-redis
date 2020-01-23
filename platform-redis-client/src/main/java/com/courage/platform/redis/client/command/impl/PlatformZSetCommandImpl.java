package com.courage.platform.redis.client.command.impl;

import com.courage.platform.redis.client.command.PlatformInvokeCommand;
import com.courage.platform.redis.client.command.PlatformZSetCommand;
import com.courage.platform.redis.client.enums.PlatformRedisCommandType;
import org.redisson.api.RBatch;
import org.redisson.api.RFuture;
import org.redisson.api.RScoredSortedSetAsync;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class PlatformZSetCommandImpl extends PlatformKeyCommandImpl implements PlatformZSetCommand {

    private static JsonJacksonCodec codec = JsonJacksonCodec.INSTANCE;

    public PlatformZSetCommandImpl(RedissonClient redissonClient) {
        super(redissonClient);
    }

    @Override
    public Integer zadd(final String key, final Map<Object, Double> scoreMembers) {
        return invokeCommand(new PlatformInvokeCommand<Integer>(PlatformRedisCommandType.ZADD) {
            @Override
            public Integer exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RFuture<Integer> future = getRedissonClient().getScoredSortedSet(key, codec).addAllAsync(scoreMembers);
                return future.get();
            }
        });
    }

    @Override
    public Integer zaddAndEx(final String key, final Map<Object, Double> scoreMembers, final int aliveSecond) {
        return invokeCommand(new PlatformInvokeCommand<Integer>(PlatformRedisCommandType.ZADD) {
            @Override
            public Integer exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RBatch rBatch = getRedissonClient().createBatch();
                RScoredSortedSetAsync rScoredSortedSetAsync = rBatch.getScoredSortedSet(key, codec);
                RFuture<Integer> rFuture = rScoredSortedSetAsync.addAllAsync(scoreMembers);
                rScoredSortedSetAsync.expireAsync(aliveSecond, TimeUnit.SECONDS);
                rBatch.execute();
                return rFuture.get();
            }
        });
    }

    @Override
    public Integer zadd(final String key, final double score, final Object member) {
        return invokeCommand(new PlatformInvokeCommand<Integer>(PlatformRedisCommandType.ZADD) {
            @Override
            public Integer exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RScoredSortedSetAsync rScoredSortedSetAsync = redissonClient.getScoredSortedSet(key, codec);
                RFuture<Integer> rFuture = rScoredSortedSetAsync.addAsync(score, member);
                return rFuture.get();
            }
        });
    }

    @Override
    public Integer zaddAndEx(final String key, final double score, final String member, final int aliveSecond) {
        return invokeCommand(new PlatformInvokeCommand<Integer>(PlatformRedisCommandType.ZADD) {
            @Override
            public Integer exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RBatch rBatch = getRedissonClient().createBatch();
                RScoredSortedSetAsync rScoredSortedSetAsync = rBatch.getScoredSortedSet(key, codec);
                RFuture<Integer> rFuture = rScoredSortedSetAsync.addAsync(score, member);
                rScoredSortedSetAsync.expireAsync(aliveSecond, TimeUnit.SECONDS);
                return rFuture.get();
            }
        });
    }

    @Override
    public Collection zrange(final String key, final int start, final int end) {
        return invokeCommand(new PlatformInvokeCommand<Collection>(PlatformRedisCommandType.ZRANGE) {
            @Override
            public Collection exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RScoredSortedSetAsync rScoredSortedSetAsync = redissonClient.getScoredSortedSet(key, codec);
                RFuture<Collection> rFuture = rScoredSortedSetAsync.valueRangeAsync(start, end);
                return rFuture.get();
            }
        });
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
