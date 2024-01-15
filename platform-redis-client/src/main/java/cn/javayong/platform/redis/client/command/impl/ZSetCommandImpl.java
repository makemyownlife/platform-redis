package cn.javayong.platform.redis.client.command.impl;

import cn.javayong.platform.redis.client.command.InvokeCommand;
import cn.javayong.platform.redis.client.command.ZSetCommand;
import cn.javayong.platform.redis.client.enums.RedisCodec;
import cn.javayong.platform.redis.client.enums.RedisCommandType;
import org.redisson.api.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ZSetCommandImpl extends KeyCommandImpl implements ZSetCommand {

    public ZSetCommandImpl(RedissonClient redissonClient) {
        super(redissonClient);
    }

    public ZSetCommandImpl(RedissonClient redissonClient, RedisCodec platformRedisCodec) {
        super(redissonClient, platformRedisCodec);
    }

    @Override
    public Integer zadd(final String key, final Map<Object, Double> scoreMembers) {
        return invokeCommand(new InvokeCommand<Integer>(RedisCommandType.ZADD) {
            @Override
            public Integer exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RFuture<Integer> future = getRedissonClient().getScoredSortedSet(key, getCodec()).addAllAsync(scoreMembers);
                return future.get();
            }
        });
    }

    @Override
    public Integer zaddAndEx(final String key, final Map<Object, Double> scoreMembers, final int aliveSecond) {
        return invokeCommand(new InvokeCommand<Integer>(RedisCommandType.ZADD) {
            @Override
            public Integer exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RBatch rBatch = getRedissonClient().createBatch();
                RScoredSortedSetAsync rScoredSortedSetAsync = rBatch.getScoredSortedSet(key, getCodec());
                RFuture<Integer> rFuture = rScoredSortedSetAsync.addAllAsync(scoreMembers);
                rScoredSortedSetAsync.expireAsync(aliveSecond, TimeUnit.SECONDS);
                rBatch.execute();
                return rFuture.get();
            }
        });
    }

    @Override
    public Boolean zadd(final String key, final double score, final Object member) {
        return invokeCommand(new InvokeCommand<Boolean>(RedisCommandType.ZADD) {
            @Override
            public Boolean exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RScoredSortedSetAsync rScoredSortedSetAsync = redissonClient.getScoredSortedSet(key, getCodec());
                RFuture<Boolean> rFuture = rScoredSortedSetAsync.addAsync(score, member);
                return rFuture.get();
            }
        });
    }

    @Override
    public Integer zaddAndEx(final String key, final double score, final String member, final int aliveSecond) {
        return invokeCommand(new InvokeCommand<Integer>(RedisCommandType.ZADD) {
            @Override
            public Integer exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RBatch rBatch = getRedissonClient().createBatch();
                RScoredSortedSetAsync rScoredSortedSetAsync = rBatch.getScoredSortedSet(key, getCodec());
                RFuture<Integer> rFuture = rScoredSortedSetAsync.addAsync(score, member);
                rScoredSortedSetAsync.expireAsync(aliveSecond, TimeUnit.SECONDS);
                rBatch.execute();
                return rFuture.get();
            }
        });
    }

    @Override
    public Collection zrange(final String key, final int start, final int end) {
        return invokeCommand(new InvokeCommand<Collection>(RedisCommandType.ZRANGE) {
            @Override
            public Collection exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RScoredSortedSetAsync rScoredSortedSetAsync = redissonClient.getScoredSortedSet(key, getCodec());
                RFuture<Collection> rFuture = rScoredSortedSetAsync.valueRangeAsync(start, end);
                return rFuture.get();
            }
        });
    }

    @Override
    public Collection zrangeByScore(final String key, final double min, final double max) {
        return invokeCommand(new InvokeCommand<Collection>(RedisCommandType.ZRANGE) {
            @Override
            public Collection exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RScoredSortedSetAsync rScoredSortedSetAsync = redissonClient.getScoredSortedSet(key, getCodec());
                RFuture<Collection> rFuture = rScoredSortedSetAsync.valueRangeAsync(min, true, max, true);
                return rFuture.get();
            }
        });
    }

    @Override
    public Boolean zrem(final String key, final Object... members) {
        return invokeCommand(new InvokeCommand<Boolean>(RedisCommandType.ZREM) {
            @Override
            public Boolean exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RScoredSortedSetAsync rScoredSortedSetAsync = redissonClient.getScoredSortedSet(key, getCodec());
                RFuture<Boolean> rFuture = rScoredSortedSetAsync.removeAllAsync(Arrays.asList(members));
                return rFuture.get();
            }
        });
    }

    @Override
    public Integer zremrangeByScore(final String key, final double min, final double max) {
        return invokeCommand(new InvokeCommand<Integer>(RedisCommandType.ZREMRANGEBYSCORE) {
            @Override
            public Integer exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RScoredSortedSetAsync rScoredSortedSetAsync = redissonClient.getScoredSortedSet(key, getCodec());
                RFuture<Integer> rFuture = rScoredSortedSetAsync.removeRangeByScoreAsync(min, true, max, true);
                return rFuture.get();
            }
        });
    }

    @Override
    public Double zincrbyAndEx(final String key, final double score, final Object member, final int aliveSecond) {
        return invokeCommand(new InvokeCommand<Double>(RedisCommandType.ZINCRBY) {
            @Override
            public Double exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RBatch rBatch = getRedissonClient().createBatch();
                RScoredSortedSetAsync rScoredSortedSetAsync = rBatch.getScoredSortedSet(key, getCodec());
                RFuture<Double> rFuture = rScoredSortedSetAsync.addScoreAsync(member, score);
                rScoredSortedSetAsync.expireAsync(aliveSecond, TimeUnit.SECONDS);
                return rFuture.get();
            }
        });
    }

    @Override
    public Double zincrby(final String key, final double score, final Object member) {
        return invokeCommand(new InvokeCommand<Double>(RedisCommandType.ZINCRBY) {
            @Override
            public Double exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RScoredSortedSet rScoredSortedSet = getRedissonClient().getScoredSortedSet(key, getCodec());
                RFuture<Double> rFuture = rScoredSortedSet.addScoreAsync(member, score);
                return rFuture.get();
            }
        });
    }

    @Override
    public Integer zrank(final String key, final Object member) {
        return invokeCommand(new InvokeCommand<Integer>(RedisCommandType.ZRANK) {
            @Override
            public Integer exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RScoredSortedSet rScoredSortedSet = getRedissonClient().getScoredSortedSet(key, getCodec());
                return rScoredSortedSet.rank(member);
            }
        });
    }

    @Override
    public Integer zrevrank(final String key, final Object member) {
        return invokeCommand(new InvokeCommand<Integer>(RedisCommandType.ZREVRANK) {
            @Override
            public Integer exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RScoredSortedSet rScoredSortedSet = getRedissonClient().getScoredSortedSet(key, getCodec());
                return rScoredSortedSet.revRank(member);
            }
        });
    }

    @Override
    public Collection zrevrange(final String key, final int start, final int end) {
        return invokeCommand(new InvokeCommand<Collection>(RedisCommandType.ZREVRANK) {
            @Override
            public Collection exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RScoredSortedSet rScoredSortedSet = getRedissonClient().getScoredSortedSet(key, getCodec());
                return rScoredSortedSet.valueRangeReversed(start, end);
            }
        });
    }


}
