package com.courage.platform.redis.client.command;

public interface PlatformRedisCommand {

    static final byte[] NX = "NX".getBytes();

    static final byte[] EX = "EX".getBytes();

    Long del(String key);

    Long ttl(String key);

    boolean exists(String key);

    String type(String key);

    Long expire(String key, int seconds);

    Long expireAt(String key, long unixTime);

    Long pexpire(String key, long seconds);

    Long pexpireAt(String key, long unixTime);

}
