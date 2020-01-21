package com.courage.platform.redis.client.command.impl;

import com.courage.platform.redis.client.command.PlatformInvokeCommand;
import com.courage.platform.redis.client.command.PlatformSetCommand;
import com.courage.platform.redis.client.enums.PlatformRedisCommandType;
import org.redisson.api.RBatch;
import org.redisson.api.RFuture;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class PlatformSetCommandImpl extends PlatformKeyCommand implements PlatformSetCommand {

    private final static Logger logger = LoggerFactory.getLogger(PlatformSetCommandImpl.class);

    private static JsonJacksonCodec codec = JsonJacksonCodec.INSTANCE;

    public PlatformSetCommandImpl(RedissonClient redissonClient) {
        super(redissonClient);
    }

    @Override
    public Boolean addAndEx(final String key, final int second, final Object... value) {
        return invokeCommand(new PlatformInvokeCommand<Boolean>(PlatformRedisCommandType.SADD) {
            @Override
            public Boolean exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RBatch batch = getRedissonClient().createBatch();
                RFuture<Boolean> future = batch.getSet(key, codec).addAllAsync(Arrays.asList(value));
                batch.getMap(key).expireAsync(second, TimeUnit.SECONDS);
                batch.execute();
                return future.get();
            }
        });
    }

    @Override
    public <T> Set<T> getMembers(final String key) {
        return invokeCommand(new PlatformInvokeCommand<Set<T>>(PlatformRedisCommandType.SMEMBERS) {
            @Override
            public Set<T> exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                return (Set<T>) redissonClient.getSet(key, codec).readAll();
            }
        });
    }

    @Override
    public Boolean removes(final String key, final Object... value) {
        return invokeCommand(new PlatformInvokeCommand<Boolean>(PlatformRedisCommandType.SMOVE) {
            @Override
            public Boolean exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                return redissonClient.getSet(key, codec).removeAll(Arrays.asList(value));
            }
        });
    }

    @Override
    public Integer size(final String key) {
        return invokeCommand(new PlatformInvokeCommand<Integer>(PlatformRedisCommandType.SCARD) {
            @Override
            public Integer exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                return redissonClient.getSet(key, codec).size();
            }
        });
    }

    @Override
    public Boolean isMember(final String key, final Object o) {
        return invokeCommand(new PlatformInvokeCommand<Boolean>(PlatformRedisCommandType.SISMEMBER) {
            @Override
            public Boolean exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                return redissonClient.getSet(key, codec).contains(o);
            }
        });
    }

}
