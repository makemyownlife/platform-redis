package cn.javayong.platform.redis.client.command.impl;


import cn.javayong.platform.redis.client.command.InvokeCommand;
import cn.javayong.platform.redis.client.command.StringCommand;
import cn.javayong.platform.redis.client.enums.RedisCodec;
import cn.javayong.platform.redis.client.enums.RedisCommandType;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * string command 实现
 * Created by zhangyong on 2020/1/19.
 */
public class StringCommandImpl extends KeyCommandImpl implements StringCommand {

    public StringCommandImpl(RedissonClient redissonClient) {
        super(redissonClient);
    }

    public StringCommandImpl(RedissonClient redissonClient, RedisCodec redisCodec) {
        super(redissonClient, redisCodec);
    }

    public String get(final String key) {
        return invokeCommand(new InvokeCommand<String>(RedisCommandType.GET) {
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
        invokeCommand(new InvokeCommand<String>(RedisCommandType.SET) {
            @Override
            public String exe(RedissonClient redissonClient) {
                RBucket<String> result = getRedissonClient().getBucket(key);
                result.set(value);
                return null;
            }
        });
    }

    public void setEx(final String key, final String value, final int second) {
        invokeCommand(new InvokeCommand<String>(RedisCommandType.SET_EX) {
            @Override
            public String exe(RedissonClient redissonClient) {
                RBucket<String> result = getRedissonClient().getBucket(key);
                result.set(value, second, TimeUnit.SECONDS);
                return null;
            }
        });
    }

    public boolean setNx(final String key, final String value) {
        return invokeCommand(new InvokeCommand<Boolean>(RedisCommandType.SET_NX) {
            @Override
            public Boolean exe(RedissonClient redissonClient) {
                RBucket<String> result = getRedissonClient().getBucket(key);
                return result.trySet(value);
            }
        });
    }

    public boolean setNx(final String key, final String value, final int aliveSecond) {
        return invokeCommand(new InvokeCommand<Boolean>(RedisCommandType.SET_NX) {
            @Override
            public Boolean exe(RedissonClient redissonClient) {
                RBucket<String> result = getRedissonClient().getBucket(key);
                return result.trySet(value, aliveSecond, TimeUnit.SECONDS);
            }
        });
    }

    public Map<String, Object> mget(final String... keys) {
        return invokeCommand(new InvokeCommand<Map<String, Object>>(RedisCommandType.MGET) {
            @Override
            public Map<String, Object> exe(RedissonClient redissonClient) {
                return getRedissonClient().getBuckets().get(keys);
            }
        });
    }

}
