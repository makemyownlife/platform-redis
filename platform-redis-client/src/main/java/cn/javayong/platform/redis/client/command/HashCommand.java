package cn.javayong.platform.redis.client.command;

import java.util.Map;
import java.util.Set;

public interface HashCommand extends KeyCommand {

    Set<Object> hkeys(String key);

    Long hdel(String key, String... fieldKeys);

    boolean hexists(String key, String fieldKey);

    <T> T hget(String key, String fieldKey);

    void hset(String key, String fieldKey, Object value);

    Map<String, Object> hmget(String key, String... field);

    Void hmset(String key, Map<String, Object> map);

    Object hincrby(String key, String fieldKey, int by);

}
