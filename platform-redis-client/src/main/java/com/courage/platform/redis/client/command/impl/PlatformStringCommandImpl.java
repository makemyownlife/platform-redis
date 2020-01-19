package com.courage.platform.redis.client.command.impl;

import com.courage.platform.redis.client.command.PlatformInvokeCommand;
import com.courage.platform.redis.client.command.PlatformStringCommand;
import com.courage.platform.redis.client.enums.PlatformRedisCommandType;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * string command 实现
 * Created by zhangyong on 2020/1/19.
 */
public class PlatformStringCommandImpl extends PlatformKeyCommand implements PlatformStringCommand {

    public PlatformStringCommandImpl(RedissonClient redissonClient) {
        super(redissonClient);
    }

    public String get(final String key) {
        return invokeCommand(new PlatformInvokeCommand<String>(PlatformRedisCommandType.GET) {
            @Override
            public String exe(RedissonClient redissonClient) {
                RBucket<String> result = getRedissonClient().getBucket(key);
                if (result == null || !result.isExists()) {
                    return null;
                }
                return result.get();
            }
        });
    }

    public void set(final String key, final String value) {
        invokeCommand(new PlatformInvokeCommand<String>(PlatformRedisCommandType.SET) {
            @Override
            public String exe(RedissonClient redissonClient) {
                RBucket<String> result = getRedissonClient().getBucket(key);
                result.set(value);
                return null;
            }
        });
    }

    public void setEx(final String key, final String value, final int second) {
        invokeCommand(new PlatformInvokeCommand<String>(PlatformRedisCommandType.SET_EX) {
            @Override
            public String exe(RedissonClient redissonClient) {
                RBucket<String> result = getRedissonClient().getBucket(key);
                result.set(value, second, TimeUnit.SECONDS);
                return null;
            }
        });
    }

    public boolean setNx(final String key, final String value) {
        return invokeCommand(new PlatformInvokeCommand<Boolean>(PlatformRedisCommandType.SET_NX) {
            @Override
            public Boolean exe(RedissonClient redissonClient) {
                RBucket<String> result = getRedissonClient().getBucket(key);
                return result.trySet(value);
            }
        });
    }

    public boolean setNx(final String key, final String value, final int aliveSecond) {
        return invokeCommand(new PlatformInvokeCommand<Boolean>(PlatformRedisCommandType.SET_NX) {
            @Override
            public Boolean exe(RedissonClient redissonClient) {
                RBucket<String> result = getRedissonClient().getBucket(key);
                return result.trySet(value, aliveSecond, TimeUnit.SECONDS);
            }
        });
    }

}
