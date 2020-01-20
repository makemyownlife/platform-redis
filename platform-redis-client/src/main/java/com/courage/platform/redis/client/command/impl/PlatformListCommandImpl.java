package com.courage.platform.redis.client.command.impl;

import com.courage.platform.redis.client.command.PlatformInvokeCommand;
import com.courage.platform.redis.client.command.PlatformListCommand;
import com.courage.platform.redis.client.enums.PlatformRedisCommandType;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;

import java.util.List;

public class PlatformListCommandImpl extends PlatformKeyCommand implements PlatformListCommand {

    public PlatformListCommandImpl(RedissonClient redissonClient) {
        super(redissonClient);
    }

    public Boolean lpush(final String key, final Object o) {
        return invokeCommand(new PlatformInvokeCommand<Boolean>(PlatformRedisCommandType.LPUSH) {
            public Boolean exe(RedissonClient redissonClient) {
                RList result = getRedissonClient().getList(key);
                return Boolean.valueOf(result.add(o));
            }
        });
    }

    public List lrange(final String key, final int startNum, final int endNum) {
        return invokeCommand(new PlatformInvokeCommand<List>(PlatformRedisCommandType.LRANGE) {
            public List exe(RedissonClient redissonClient) {
                RList result = getRedissonClient().getList(key);
                return result.subList(startNum, endNum);
            }
        });
    }

    public void ltrim(final String key, final int startNum, final int endNum) {
        invokeCommand(new PlatformInvokeCommand<String>(PlatformRedisCommandType.LTRIM) {
            public String exe(RedissonClient redissonClient) {
                RList result = getRedissonClient().getList(key);
                result.trim(startNum, endNum);
                return null;
            }
        });
    }

    public Integer llen(final String key) {
        return invokeCommand(new PlatformInvokeCommand<Integer>(PlatformRedisCommandType.LLEN) {
            public Integer exe(RedissonClient redissonClient) {
                RList result = getRedissonClient().getList(key);
                return result.size();
            }
        });
    }

    public <T> T rpop(final String key) {
        return invokeCommand(new PlatformInvokeCommand<T>(PlatformRedisCommandType.RPOP) {
            public T exe(RedissonClient redissonClient) {
                RBlockingDeque result = getRedissonClient().getBlockingDeque(key);
                return (T) result.poll();
            }
        });
    }

    public <T> T lpop(final String key) {
        return invokeCommand(new PlatformInvokeCommand<T>(PlatformRedisCommandType.LPOP) {
            public T exe(RedissonClient redissonClient) {
                RBlockingDeque result = getRedissonClient().getBlockingDeque(key);
                return (T) result.pollFirst();
            }
        });

    }

    public boolean rpush(final String key, final Object o) {
        return invokeCommand(new PlatformInvokeCommand<Boolean>(PlatformRedisCommandType.RPUSH) {
            public Boolean exe(RedissonClient redissonClient) {
                RBlockingDeque result = getRedissonClient().getBlockingDeque(key);
                return result.offerLast(o);
            }
        });
    }

}
