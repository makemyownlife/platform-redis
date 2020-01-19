package com.courage.platform.redis.client.command;

/**
 * Created by zhangyong on 2020/1/19.
 */
public interface PlatformStringCommand extends PlatformRedisCommand {

    String set(String key, String value);

    String setEx(String key, String value, int second);

    Long setNx(String key, String value);

    Long setNx(String key, String value, int aliveSecond);

    String getAndSet(String key, String value);

    String setNxEx(String key, String value, int second);

}
