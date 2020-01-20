package com.courage.platform.redis.client.command.impl;

import com.courage.platform.redis.client.command.PlatformAtomicCommand;
import com.courage.platform.redis.client.command.PlatformInvokeCommand;
import com.courage.platform.redis.client.enums.PlatformRedisCommandType;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlatformAtomicCommandImpl extends PlatformKeyCommand implements PlatformAtomicCommand {

    private static final Logger logger = LoggerFactory.getLogger(PlatformAtomicCommandImpl.class);

    public PlatformAtomicCommandImpl(RedissonClient redissonClient) {
        super(redissonClient);
    }

    public Long incr(final String key) {
        return invokeCommand(new PlatformInvokeCommand<Long>(PlatformRedisCommandType.INCR) {
            @Override
            public Long exe(RedissonClient redissonClient) {
                RAtomicLong result = getRedissonClient().getAtomicLong(key);
                return result.get();
            }
        });
    }

    public Long incrBy(String key, long incValue) {
        return null;
    }

    public Long decr(String key) {
        return null;
    }

    public Long decrBy(String key, long incValue) {
        return null;
    }

    public Long incrEx(String key, int seconds) {
        return null;
    }

    public Long incrByEx(String key, long incValue, int seconds) {
        return null;
    }

    public Long decrEx(String key, int seconds) {
        return null;
    }

    public Long decrByEx(String key, long incValue, int seconds) {
        return null;
    }

}
