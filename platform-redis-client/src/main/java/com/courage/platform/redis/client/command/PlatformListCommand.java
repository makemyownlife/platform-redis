package com.courage.platform.redis.client.command;

import java.util.List;

public interface PlatformListCommand {

    boolean lpush(final String key, final Object o);

    <T> List<T> lrange(final String key, final int startNum, final int endNum);

    void ltrim(final String key, final int startNum, final int endNum);

    Integer llen(final String key);

    <T> T rpop(final String key);

    <T> T lpop(final String key);

    Long rpush(final String key, final Object... o);

}
