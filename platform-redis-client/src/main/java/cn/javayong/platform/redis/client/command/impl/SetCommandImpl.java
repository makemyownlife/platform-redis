package cn.javayong.platform.redis.client.command.impl;

import cn.javayong.platform.redis.client.command.InvokeCommand;
import cn.javayong.platform.redis.client.command.SetCommand;
import cn.javayong.platform.redis.client.enums.RedisCodec;
import cn.javayong.platform.redis.client.enums.RedisCommandType;
import org.redisson.api.RBatch;
import org.redisson.api.RFuture;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class SetCommandImpl extends KeyCommandImpl implements SetCommand {

    private final static Logger logger = LoggerFactory.getLogger(SetCommandImpl.class);

    public SetCommandImpl(RedissonClient redissonClient) {
        super(redissonClient);
    }

    public SetCommandImpl(RedissonClient redissonClient, RedisCodec platformRedisCodec) {
        super(redissonClient, platformRedisCodec);
    }

    @Override
    public Boolean addAndEx(final String key, final int second, final Object... value) {
        return invokeCommand(new InvokeCommand<Boolean>(RedisCommandType.SADD) {
            @Override
            public Boolean exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                RBatch batch = getRedissonClient().createBatch();
                RFuture<Boolean> future = batch.getSet(key, getCodec()).addAllAsync(Arrays.asList(value));
                batch.getMap(key).expireAsync(second, TimeUnit.SECONDS);
                batch.execute();
                return future.get();
            }
        });
    }

    @Override
    public <T> Set<T> getMembers(final String key) {
        return invokeCommand(new InvokeCommand<Set<T>>(RedisCommandType.SMEMBERS) {
            @Override
            public Set<T> exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                Object o = redissonClient.getSet(key, getCodec());
                return (Set<T>) redissonClient.getSet(key, getCodec()).readAll();
            }
        });
    }

    @Override
    public Boolean removes(final String key, final Object... value) {
        return invokeCommand(new InvokeCommand<Boolean>(RedisCommandType.SMOVE) {
            @Override
            public Boolean exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                return redissonClient.getSet(key, getCodec()).removeAll(Arrays.asList(value));
            }
        });
    }

    @Override
    public Integer size(final String key) {
        return invokeCommand(new InvokeCommand<Integer>(RedisCommandType.SCARD) {
            @Override
            public Integer exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                return redissonClient.getSet(key, getCodec()).size();
            }
        });
    }

    @Override
    public Boolean isMember(final String key, final Object o) {
        return invokeCommand(new InvokeCommand<Boolean>(RedisCommandType.SISMEMBER) {
            @Override
            public Boolean exe(RedissonClient redissonClient) throws ExecutionException, InterruptedException {
                return redissonClient.getSet(key, getCodec()).contains(o);
            }
        });
    }

}
