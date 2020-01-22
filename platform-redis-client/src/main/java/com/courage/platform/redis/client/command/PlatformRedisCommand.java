package com.courage.platform.redis.client.command;

public interface PlatformRedisCommand {

    boolean del(String key);

    boolean exists(String key);

    boolean expire(String key, int seconds);

}
