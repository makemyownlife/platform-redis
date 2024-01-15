package cn.javayong.platform.redis.client.command.impl;

import cn.javayong.platform.redis.client.command.InvokeCommand;
import cn.javayong.platform.redis.client.command.ListCommand;
import cn.javayong.platform.redis.client.enums.RedisCodec;
import cn.javayong.platform.redis.client.enums.RedisCommandType;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;

import java.util.List;

public class ListCommandImpl extends KeyCommandImpl implements ListCommand {

    public ListCommandImpl(RedissonClient redissonClient) {
        super(redissonClient);
    }

    public ListCommandImpl(RedissonClient redissonClient, RedisCodec redisCodec) {
        super(redissonClient, redisCodec);
    }

    public Boolean lpush(final String key, final Object o) {
        return invokeCommand(new InvokeCommand<Boolean>(RedisCommandType.LPUSH) {
            public Boolean exe(RedissonClient redissonClient) {
                RList result = getRedissonClient().getList(key, getCodec());
                return Boolean.valueOf(result.add(o));
            }
        });
    }

    public List lrange(final String key, final int startNum, final int endNum) {
        return invokeCommand(new InvokeCommand<List>(RedisCommandType.LRANGE) {
            public List exe(RedissonClient redissonClient) {
                RList result = getRedissonClient().getList(key, getCodec());
                return result.subList(startNum, endNum);
            }
        });
    }

    public void ltrim(final String key, final int startNum, final int endNum) {
        invokeCommand(new InvokeCommand<String>(RedisCommandType.LTRIM) {
            public String exe(RedissonClient redissonClient) {
                RList result = getRedissonClient().getList(key, getCodec());
                result.trim(startNum, endNum);
                return null;
            }
        });
    }

    public Integer llen(final String key) {
        return invokeCommand(new InvokeCommand<Integer>(RedisCommandType.LLEN) {
            public Integer exe(RedissonClient redissonClient) {
                RList result = getRedissonClient().getList(key, getCodec());
                return result.size();
            }
        });
    }

    public <T> T rpop(final String key) {
        return invokeCommand(new InvokeCommand<T>(RedisCommandType.RPOP) {
            public T exe(RedissonClient redissonClient) {
                RBlockingDeque result = getRedissonClient().getBlockingDeque(key, getCodec());
                return (T) result.poll();
            }
        });
    }

    public <T> T lpop(final String key) {
        return invokeCommand(new InvokeCommand<T>(RedisCommandType.LPOP) {
            public T exe(RedissonClient redissonClient) {
                RBlockingDeque result = getRedissonClient().getBlockingDeque(key, getCodec());
                return (T) result.pollFirst();
            }
        });
    }

    public boolean rpush(final String key, final Object o) {
        return invokeCommand(new InvokeCommand<Boolean>(RedisCommandType.RPUSH) {
            public Boolean exe(RedissonClient redissonClient) {
                RBlockingDeque result = getRedissonClient().getBlockingDeque(key, getCodec());
                return result.offerLast(o);
            }
        });
    }

}
