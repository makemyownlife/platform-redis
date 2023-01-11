package com.courage.platform.redis.client.command.impl;


import com.courage.platform.redis.client.command.InvokeCommand;
import com.courage.platform.redis.client.command.KeyCommand;
import com.courage.platform.redis.client.enums.RedisCodec;
import com.courage.platform.redis.client.enums.RedisCommandType;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.client.codec.StringCodec;
import org.redisson.codec.JsonJacksonCodec;

import java.util.concurrent.TimeUnit;

public class KeyCommandImpl implements KeyCommand {

    private RedissonClient redissonClient;

    //默认JACKSON 编码
    private RedisCodec platformRedisCodec = RedisCodec.JSON;

    public KeyCommandImpl(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public KeyCommandImpl(RedissonClient redissonClient, RedisCodec platformRedisCodec) {
        this.redissonClient = redissonClient;
        this.platformRedisCodec = platformRedisCodec;
    }

    public RedissonClient getRedissonClient() {
        return redissonClient;
    }

    public boolean del(final String key) {
        return invokeCommand(new InvokeCommand<Boolean>(RedisCommandType.DEL) {
            @Override
            public Boolean exe(RedissonClient redissonClient) {
                RMap map = redissonClient.getMap(key);
                return map.delete();
            }
        });
    }

    public boolean exists(final String key) {
        return invokeCommand(new InvokeCommand<Boolean>(RedisCommandType.EXISTS) {
            @Override
            public Boolean exe(RedissonClient redissonClient) {
                RMap map = redissonClient.getMap(key);
                return map.isExists();
            }
        });
    }

    public boolean expire(final String key, final int seconds) {
        return invokeCommand(new InvokeCommand<Boolean>(RedisCommandType.EXPIRE) {
            @Override
            public Boolean exe(RedissonClient redissonClient) {
                RMap map = redissonClient.getMap(key);
                return map.expire(seconds, TimeUnit.SECONDS);
            }
        });
    }

    protected <T> T invokeCommand(InvokeCommand<T> callable) {
        RedisCommandType redisCommandType = callable.getCommandType();
        try {
            RedissonClient redissonClient = this.getRedissonClient();
            T obj = callable.exe(redissonClient);
            return obj;
        } catch (Exception e) {
            throw new RuntimeException("invoke redis cmd is error!", e);
        }
    }

    protected Codec getCodec() {
        if (platformRedisCodec == RedisCodec.STRING) {
            return StringCodec.INSTANCE;
        }
        if (platformRedisCodec == RedisCodec.JSON) {
            return JsonJacksonCodec.INSTANCE;
        }
        return StringCodec.INSTANCE;
    }

}
