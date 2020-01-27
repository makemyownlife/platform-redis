package com.courage.platform.redis.client.command.impl;

import com.courage.platform.redis.client.command.PlatformAtomicCommand;
import com.courage.platform.redis.client.command.PlatformInvokeCommand;
import com.courage.platform.redis.client.enums.PlatformRedisCommandType;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBatch;
import org.redisson.api.RFuture;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class PlatformAtomicCommandImpl extends PlatformKeyCommandImpl implements PlatformAtomicCommand {

    private static final Logger logger = LoggerFactory.getLogger(PlatformAtomicCommandImpl.class);

    public PlatformAtomicCommandImpl(RedissonClient redissonClient) {
        super(redissonClient);
    }

    public Long incr(final String key) {
        return invokeCommand(new PlatformInvokeCommand<Long>(PlatformRedisCommandType.INCR) {
            @Override
            public Long exe(RedissonClient redissonClient) {
                RAtomicLong result = getRedissonClient().getAtomicLong(key);
                return result.incrementAndGet();
            }
        });
    }

    public Long incrBy(final String key, final long incValue) {
        return invokeCommand(new PlatformInvokeCommand<Long>(PlatformRedisCommandType.INCRBY) {
            @Override
            public Long exe(RedissonClient redissonClient) {
                RAtomicLong result = getRedissonClient().getAtomicLong(key);
                return result.addAndGet(incValue);
            }
        });
    }

    public Long decr(final String key) {
        return invokeCommand(new PlatformInvokeCommand<Long>(PlatformRedisCommandType.DECR) {
            @Override
            public Long exe(RedissonClient redissonClient) {
                RAtomicLong result = getRedissonClient().getAtomicLong(key);
                return result.decrementAndGet();
            }
        });
    }

    public Long decrBy(final String key, final long incValue) {
        return invokeCommand(new PlatformInvokeCommand<Long>(PlatformRedisCommandType.DECRBY) {
            @Override
            public Long exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RAtomicLong result = getRedissonClient().getAtomicLong(key);
                RFuture<Long> future = result.addAndGetAsync(-1 * incValue);
                return future.get();
            }
        });
    }

    public Long incrEx(final String key, final int seconds) {
        return invokeCommand(new PlatformInvokeCommand<Long>(PlatformRedisCommandType.INCR_EX) {
            @Override
            public Long exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RBatch batch = getRedissonClient().createBatch();
                RFuture<Long> rFuture = batch.getAtomicLong(key).incrementAndGetAsync();
                batch.getBucket(key).expireAsync(seconds, TimeUnit.SECONDS);
                batch.execute();
                return rFuture.get();
            }
        });
    }

    public Long incrByEx(final String key, final long incValue, final int seconds) {
        return invokeCommand(new PlatformInvokeCommand<Long>(PlatformRedisCommandType.INCRBY_EX) {
            @Override
            public Long exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RBatch batch = getRedissonClient().createBatch();
                RFuture<Long> rFuture = batch.getAtomicLong(key).addAndGetAsync(incValue);
                batch.getBucket(key).expireAsync(seconds, TimeUnit.SECONDS);
                batch.execute();
                return rFuture.get();
            }
        });
    }

    public Long decrEx(final String key, final int seconds) {
        return invokeCommand(new PlatformInvokeCommand<Long>(PlatformRedisCommandType.DECR_EX) {
            @Override
            public Long exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RBatch batch = getRedissonClient().createBatch();
                RFuture<Long> rFuture = batch.getAtomicLong(key).decrementAndGetAsync();
                batch.getAtomicLong(key).expireAsync(seconds, TimeUnit.SECONDS);
                batch.execute();
                return rFuture.get();
            }
        });
    }

    public Long decrByEx(final String key, final long incValue, final int seconds) {
        return invokeCommand(new PlatformInvokeCommand<Long>(PlatformRedisCommandType.DECRBY_EX) {
            @Override
            public Long exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RBatch batch = getRedissonClient().createBatch();
                RFuture<Long> rFuture = batch.getAtomicLong(key).addAndGetAsync(-1 * incValue);
                batch.getAtomicLong(key).expireAsync(seconds, TimeUnit.SECONDS);
                batch.execute();
                return rFuture.get();
            }
        });
    }

}
