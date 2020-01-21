package com.courage.platform.redis.client.command;

import java.util.Set;

public interface PlatformSetCommand extends PlatformRedisCommand {

    Boolean addAndEx(final String key, int second, final Object... value);

    <T> Set<T> getMembers(final String key);

    Boolean removes(final String key, final Object... value);

    Integer size(final String key);

    Boolean isMember(final String key, final Object o);

}
