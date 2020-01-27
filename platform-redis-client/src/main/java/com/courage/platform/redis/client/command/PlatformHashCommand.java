package com.courage.platform.redis.client.command;

import java.util.Set;

public interface PlatformHashCommand extends PlatformKeyCommand {

    Set<Object> hkeys(String key);

    Long hdel(String key, String... fieldKeys);

    boolean hexists(String key, String fieldKey);

    <T> T hget(String key, String fieldKey);

    Object hset(String key, String fieldKey, Object value);

}
