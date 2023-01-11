package com.courage.platform.redis.client.command.impl;

import com.courage.platform.redis.client.command.HashCommand;
import com.courage.platform.redis.client.command.InvokeCommand;
import com.courage.platform.redis.client.enums.RedisCodec;
import com.courage.platform.redis.client.enums.RedisCommandType;
import org.redisson.api.RedissonClient;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HashCommandImpl extends KeyCommandImpl implements HashCommand {

    public HashCommandImpl(RedissonClient redissonClient) {
        super(redissonClient);
    }

    public HashCommandImpl(RedissonClient redissonClient, RedisCodec redisCodec) {
        super(redissonClient, redisCodec);
    }

    @Override
    public Set<Object> hkeys(final String key) {
        return invokeCommand(new InvokeCommand<Set<Object>>(RedisCommandType.HKEYS) {
            public Set<Object> exe(RedissonClient redissonClient) {
                return getRedissonClient().getMap(key, getCodec()).keySet();
            }
        });
    }

    @Override
    public Long hdel(final String key, final String... fieldKeys) {
        return invokeCommand(new InvokeCommand<Long>(RedisCommandType.HDEL) {
            public Long exe(RedissonClient redissonClient) {
                return getRedissonClient().getMap(key, getCodec()).fastRemove(fieldKeys);
            }
        });
    }

    @Override
    public boolean hexists(final String key, final String fieldKey) {
        return invokeCommand(new InvokeCommand<Boolean>(RedisCommandType.HEXISTS) {
            public Boolean exe(RedissonClient redissonClient) {
                return getRedissonClient().getMap(key, getCodec()).containsKey(fieldKey);
            }
        });
    }

    @Override
    public <T> T hget(final String key, final String fieldKey) {
        return invokeCommand(new InvokeCommand<T>(RedisCommandType.HGET) {
            public T exe(RedissonClient redissonClient) {
                return (T) getRedissonClient().getMap(key, getCodec()).get(fieldKey);
            }
        });
    }

    @Override
    public Object hset(final String key, final String fieldKey, final Object value) {
        return invokeCommand(new InvokeCommand<Object>(RedisCommandType.HSET) {
            public Object exe(RedissonClient redissonClient) {
                return getRedissonClient().getMap(key, getCodec()).put(fieldKey, value);
            }
        });
    }

    @Override
    public Map<String, Object> hmget(final String key, final String... fields) {
        return invokeCommand(new InvokeCommand<Map>(RedisCommandType.HMGET) {
            public Map exe(RedissonClient redissonClient) {
                Set<Object> array = new HashSet<Object>(fields.length);
                for (String field : fields) {
                    array.add(field);
                }
                return getRedissonClient().getMap(key, getCodec()).getAll(array);
            }
        });
    }

    @Override
    public Void hmset(final String key, final Map<String, Object> map) {
        return invokeCommand(new InvokeCommand<Void>(RedisCommandType.HMSET) {
            public Void exe(RedissonClient redissonClient) {
                getRedissonClient().getMap(key, getCodec()).putAll(map);
                return null;
            }
        });
    }

    @Override
    public Object hincrby(final String key, final String fieldKey, final int by) {
        return invokeCommand(new InvokeCommand<Object>(RedisCommandType.HINCRBY) {
            public Object exe(RedissonClient redissonClient) {
                return (Object) getRedissonClient().getMap(key, getCodec()).addAndGet(fieldKey, by);
            }
        });
    }

}
