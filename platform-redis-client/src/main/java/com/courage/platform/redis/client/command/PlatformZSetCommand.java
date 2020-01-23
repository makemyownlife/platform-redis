package com.courage.platform.redis.client.command;

import java.util.Collection;
import java.util.Map;

public interface PlatformZSetCommand extends PlatformKeyCommand {

    Integer zadd(final String key, final Map<Object, Double> scoreMembers);

    Integer zaddAndEx(final String key, final Map<Object, Double> scoreMembers, int aliveSecond);

    Integer zadd(final String key, final double score, final Object member);

    Integer zaddAndEx(final String key, final double score, final String member, int aliveSecond);

    Collection zrange(final String key, final int start, final int end);

    Collection zrangeByScore(final String key, final double min, final double max);

    Boolean zrem(final String key, final Object... members);

    Integer zremrangeByScore(final String key, double min, double max);

    /*
    有序集合中对指定成员的分数加上增量 increment
     */
    Double zincrbyAndEx(final String key, final double score, final Object member, int aliveSecond);

    Double zincrby(final String key, final double score, final Object member);

    Integer zrank(final String key, final Object member);

    Integer zrevrank(final String key, final Object member);

    Collection zrevrange(final String key, final int start, final int end);

}
