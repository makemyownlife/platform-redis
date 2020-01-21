package com.courage.platform.redis.client.command;

import com.courage.platform.redis.client.enums.PlatformRedisCommandType;
import org.redisson.api.RedissonClient;

import java.util.concurrent.ExecutionException;

/**
 * invoke 命令
 * Created by zhangyong on 2020/1/19.
 */
public abstract class PlatformInvokeCommand<T> {

    private final PlatformRedisCommandType commandType;

    public abstract T exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException;

    protected PlatformInvokeCommand(PlatformRedisCommandType commandType) {
        this.commandType = commandType;
    }

    public PlatformRedisCommandType getCommandType() {
        return commandType;
    }

}
