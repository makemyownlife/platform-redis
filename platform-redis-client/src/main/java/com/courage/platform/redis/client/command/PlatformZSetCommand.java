package com.courage.platform.redis.client.command;

import java.util.Map;
import java.util.Set;

public interface PlatformZSetCommand extends PlatformKeyCommand {

    Integer zadd(final String key, final Map<Object, Double> scoreMembers);

    Integer zaddAndEx(final String key, final Map<Object, Double> scoreMembers, int aliveSecond);

    Long zadd(final String key, final double score, final String member);

    Long zaddAndEx(final String key, final double score, final String member, int aliveSecond);

    Set<String> zrange(final String key, final long start, final long end);

    Set<String> zrangeByScore(final String key, final double min, final double max);

    Long zrem(final String key, final String... members);

    Long zremrangeByScore(final String key, double min, double max);

    Double zincrbyAndEx(final String key, final double score, final String member, int aliveSecond);

    Double zincrby(final String key, final double score, final String member);

    Long zrank(final String key, final String member);

    Long zrevrank(final String key, final String member);

    Set<String> zrevrange(final String key, final long start, final long end);

}
