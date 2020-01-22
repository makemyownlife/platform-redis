package com.courage.platform.redis.client.command.impl;

import com.courage.platform.redis.client.command.PlatformInvokeCommand;
import com.courage.platform.redis.client.enums.PlatformRedisCommandType;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

public class PlatformKeyCommandImpl implements com.courage.platform.redis.client.command.PlatformKeyCommand {

    private RedissonClient redissonClient;

    public PlatformKeyCommandImpl(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public RedissonClient getRedissonClient() {
        return redissonClient;
    }

    public boolean del(final String key) {
        return invokeCommand(new PlatformInvokeCommand<Boolean>(PlatformRedisCommandType.DEL) {
            @Override
            public Boolean exe(RedissonClient redissonClient) {
                RMap map = redissonClient.getMap(key);
                return map.delete();
            }
        });
    }

    public boolean exists(final String key) {
        return invokeCommand(new PlatformInvokeCommand<Boolean>(PlatformRedisCommandType.EXISTS) {
            @Override
            public Boolean exe(RedissonClient redissonClient) {
                RMap map = redissonClient.getMap(key);
                return map.isExists();
            }
        });
    }

    public boolean expire(final String key, final int seconds) {
        return invokeCommand(new PlatformInvokeCommand<Boolean>(PlatformRedisCommandType.EXPIRE) {
            @Override
            public Boolean exe(RedissonClient redissonClient) {
                RMap map = redissonClient.getMap(key);
                return map.expire(seconds, TimeUnit.SECONDS);
            }
        });
    }

    protected <T> T invokeCommand(PlatformInvokeCommand<T> callable) {
        PlatformRedisCommandType redisCommandType = callable.getCommandType();
        try {
            RedissonClient redissonClient = this.getRedissonClient();
            T obj = callable.exe(redissonClient);
            return obj;
        } catch (Exception e) {
            throw new RuntimeException("invoke redis cmd is error!", e);
        }
    }

}
