package com.courage.platform.redis.client.command;

import java.util.HashMap;
import java.util.List;

public interface PlatformListCommand {

    Long lpush(final String key, final Object... o);

    <T> List<T> lrange(final String key, final long startNum, final long endNum);

    String ltrim(final String key, final long startNum, final long endNum);

    Long llen(final String key);

    <T> T rpop(final String key);

    <T> T lpop(final String key);

    Long rpush(final String key, final Object... o);

    <T> HashMap<String, T> blpop(final String... keys);

    <T> HashMap<String, T> blpop(final int timeout, final String... keys);

    <T> HashMap<String, T> brpop(final String... keys);

    <T> HashMap<String, T> brpop(final int timeout, final String... keys);

}
