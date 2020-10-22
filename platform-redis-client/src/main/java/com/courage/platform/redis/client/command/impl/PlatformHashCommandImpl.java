package com.courage.platform.redis.client.command.impl;

import com.courage.platform.redis.client.command.PlatformHashCommand;
import com.courage.platform.redis.client.command.PlatformInvokeCommand;
import com.courage.platform.redis.client.enums.PlatformRedisCommandType;
import org.redisson.api.BatchResult;
import org.redisson.api.RBatch;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PlatformHashCommandImpl extends PlatformKeyCommandImpl implements PlatformHashCommand {

    private static JsonJacksonCodec hashCodec = JsonJacksonCodec.INSTANCE;

    public PlatformHashCommandImpl(RedissonClient redissonClient) {
        super(redissonClient);
    }

    @Override
    public Set<Object> hkeys(final String key) {
        return invokeCommand(new PlatformInvokeCommand<Set<Object>>(PlatformRedisCommandType.HKEYS) {
            public Set<Object> exe(RedissonClient redissonClient) {
                return getRedissonClient().getMap(key, hashCodec).keySet();
            }
        });
    }

    @Override
    public Long hdel(final String key, final String... fieldKeys) {
        return invokeCommand(new PlatformInvokeCommand<Long>(PlatformRedisCommandType.HDEL) {
            public Long exe(RedissonClient redissonClient) {
                return getRedissonClient().getMap(key, hashCodec).fastRemove(fieldKeys);
            }
        });
    }

    @Override
    public boolean hexists(final String key, final String fieldKey) {
        return invokeCommand(new PlatformInvokeCommand<Boolean>(PlatformRedisCommandType.HEXISTS) {
            public Boolean exe(RedissonClient redissonClient) {
                return getRedissonClient().getMap(key, hashCodec).containsKey(fieldKey);
            }
        });
    }

    @Override
    public <T> T hget(final String key, final String fieldKey) {
        return invokeCommand(new PlatformInvokeCommand<T>(PlatformRedisCommandType.HGET) {
            public T exe(RedissonClient redissonClient) {
                return (T) getRedissonClient().getMap(key, hashCodec).get(fieldKey);
            }
        });
    }

    @Override
    public Object hset(final String key, final String fieldKey, final Object value) {
        return invokeCommand(new PlatformInvokeCommand<Object>(PlatformRedisCommandType.HSET) {
            public Object exe(RedissonClient redissonClient) {
                return getRedissonClient().getMap(key, hashCodec).put(fieldKey, value);
            }
        });
    }

    @Override
    public Map<String, Object> hmget(final String key, final String... fields) {
        return invokeCommand(new PlatformInvokeCommand<Map>(PlatformRedisCommandType.HMGET) {
            public Map exe(RedissonClient redissonClient) {
                Set<Object> array = new HashSet<Object>(fields.length);
                for (String field : fields) {
                    array.add(field);
                }
                return getRedissonClient().getMap(key, hashCodec).getAll(array);
            }
        });
    }

    @Override
    public Void hmset(final String key, final Map<String, Object> map) {
        return invokeCommand(new PlatformInvokeCommand<Void>(PlatformRedisCommandType.HMSET) {
            public Void exe(RedissonClient redissonClient) {
                getRedissonClient().getMap(key, hashCodec).putAll(map);
                return null;
            }
        });
    }

    @Override
    public Object hincrby(final String key, final String fieldKey, final int by) {
        return invokeCommand(new PlatformInvokeCommand<Object>(PlatformRedisCommandType.HINCRBY) {
            public Object exe(RedissonClient redissonClient) {
                return (Object) getRedissonClient().getMap(key, hashCodec).addAndGet(fieldKey, by);
            }
        });
    }

    @Override
    public List pipeMultGet(final List<String> keyList) {
        return invokeCommand(new PlatformInvokeCommand<List>(PlatformRedisCommandType.PIPE_HMGET) {
            public List exe(RedissonClient redissonClient) {
                RBatch rBatch = redissonClient.createBatch();
                for (String key : keyList) {
                    rBatch.getMap(key, hashCodec).readAllMapAsync();
                }
                BatchResult res = rBatch.execute();
                List<?> response = res.getResponses();
                return response;
            }
        });
    }

}
