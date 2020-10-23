package com.courage.platform.redis.client.command.impl;

import com.courage.platform.redis.client.command.PlatformInvokeCommand;
import com.courage.platform.redis.client.command.PlatformZSetCommand;
import com.courage.platform.redis.client.enums.PlatformRedisCommandType;
import org.redisson.api.*;
import org.redisson.codec.JsonJacksonCodec;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
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
    public Boolean zadd(final String key, final double score, final Object member) {
        return invokeCommand(new PlatformInvokeCommand<Boolean>(PlatformRedisCommandType.ZADD) {
            @Override
            public Boolean exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RScoredSortedSetAsync rScoredSortedSetAsync = redissonClient.getScoredSortedSet(key, codec);
                RFuture<Boolean> rFuture = rScoredSortedSetAsync.addAsync(score, member);
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
                rBatch.execute();
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
    public Collection zrangeByScore(final String key, final double min, final double max) {
        return invokeCommand(new PlatformInvokeCommand<Collection>(PlatformRedisCommandType.ZRANGE) {
            @Override
            public Collection exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RScoredSortedSetAsync rScoredSortedSetAsync = redissonClient.getScoredSortedSet(key, codec);
                RFuture<Collection> rFuture = rScoredSortedSetAsync.valueRangeAsync(min, true, max, true);
                return rFuture.get();
            }
        });
    }

    @Override
    public Boolean zrem(final String key, final Object... members) {
        return invokeCommand(new PlatformInvokeCommand<Boolean>(PlatformRedisCommandType.ZREM) {
            @Override
            public Boolean exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RScoredSortedSetAsync rScoredSortedSetAsync = redissonClient.getScoredSortedSet(key, codec);
                RFuture<Boolean> rFuture = rScoredSortedSetAsync.removeAllAsync(Arrays.asList(members));
                return rFuture.get();
            }
        });
    }

    @Override
    public Integer zremrangeByScore(final String key, final double min, final double max) {
        return invokeCommand(new PlatformInvokeCommand<Integer>(PlatformRedisCommandType.ZREMRANGEBYSCORE) {
            @Override
            public Integer exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RScoredSortedSetAsync rScoredSortedSetAsync = redissonClient.getScoredSortedSet(key, codec);
                RFuture<Integer> rFuture = rScoredSortedSetAsync.removeRangeByScoreAsync(min, true, max, true);
                return rFuture.get();
            }
        });
    }

    @Override
    public Double zincrbyAndEx(final String key, final double score, final Object member, final int aliveSecond) {
        return invokeCommand(new PlatformInvokeCommand<Double>(PlatformRedisCommandType.ZINCRBY) {
            @Override
            public Double exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RBatch rBatch = getRedissonClient().createBatch();
                RScoredSortedSetAsync rScoredSortedSetAsync = rBatch.getScoredSortedSet(key, codec);
                RFuture<Double> rFuture = rScoredSortedSetAsync.addScoreAsync(member, score);
                rScoredSortedSetAsync.expireAsync(aliveSecond, TimeUnit.SECONDS);
                return rFuture.get();
            }
        });
    }

    @Override
    public Double zincrby(final String key, final double score, final Object member) {
        return invokeCommand(new PlatformInvokeCommand<Double>(PlatformRedisCommandType.ZINCRBY) {
            @Override
            public Double exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RScoredSortedSet rScoredSortedSet = getRedissonClient().getScoredSortedSet(key, codec);
                RFuture<Double> rFuture = rScoredSortedSet.addScoreAsync(member, score);
                return rFuture.get();
            }
        });
    }

    @Override
    public Integer zrank(final String key, final Object member) {
        return invokeCommand(new PlatformInvokeCommand<Integer>(PlatformRedisCommandType.ZRANK) {
            @Override
            public Integer exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RScoredSortedSet rScoredSortedSet = getRedissonClient().getScoredSortedSet(key, codec);
                return rScoredSortedSet.rank(member);
            }
        });
    }

    @Override
    public Integer zrevrank(final String key, final Object member) {
        return invokeCommand(new PlatformInvokeCommand<Integer>(PlatformRedisCommandType.ZREVRANK) {
            @Override
            public Integer exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RScoredSortedSet rScoredSortedSet = getRedissonClient().getScoredSortedSet(key, codec);
                return rScoredSortedSet.revRank(member);
            }
        });
    }

    @Override
    public Collection zrevrange(final String key, final int start, final int end) {
        return invokeCommand(new PlatformInvokeCommand<Collection>(PlatformRedisCommandType.ZREVRANK) {
            @Override
            public Collection exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RScoredSortedSet rScoredSortedSet = getRedissonClient().getScoredSortedSet(key, codec);
                return rScoredSortedSet.valueRangeReversed(start, end);
            }
        });
    }


}
