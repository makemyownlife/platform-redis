package com.courage.platform.redis.client.command;

public interface PlatformRedisCommand {

    static final byte[] NX = "NX".getBytes();

    static final byte[] EX = "EX".getBytes();

    boolean del(String key);

    boolean exists(String key);

    boolean expire(String key, int seconds);

}
