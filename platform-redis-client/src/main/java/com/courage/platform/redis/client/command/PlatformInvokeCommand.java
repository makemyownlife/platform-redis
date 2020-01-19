package com.courage.platform.redis.client.command;

import com.courage.platform.redis.client.enums.PlatformRedisCommandType;
import org.redisson.api.RedissonClient;

/**
 * invoke 命令
 * Created by zhangyong on 2020/1/19.
 */
public abstract class PlatformInvokeCommand<T> {

    private final PlatformRedisCommandType type;

    public abstract T exe(RedissonClient redissonClient);

    protected PlatformInvokeCommand(PlatformRedisCommandType type) {
        this.type = type;
    }

    public PlatformRedisCommandType getType() {
        return type;
    }

}
