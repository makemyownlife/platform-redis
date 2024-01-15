package cn.javayong.platform.redis.client.command;

import cn.javayong.platform.redis.client.enums.RedisCommandType;
import org.redisson.api.RedissonClient;

import java.util.concurrent.ExecutionException;

/**
 * invoke 命令
 * Created by zhangyong on 2020/1/19.
 */
public abstract class InvokeCommand<T> {

    private final RedisCommandType commandType;

    public abstract T exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException;

    protected InvokeCommand(RedisCommandType commandType) {
        this.commandType = commandType;
    }

    public RedisCommandType getCommandType() {
        return commandType;
    }

}
