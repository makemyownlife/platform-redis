package com.courage.platform.redis.client.command;

/**
 * Created by zhangyong on 2020/1/19.
 */
public interface PlatformStringCommand extends PlatformKeyCommand {

    String get(String key);

    void set(String key, String value);

    void setEx(String key, String value, int second);

    boolean setNx(String key, String value);

    boolean setNx(String key, String value, int aliveSecond);

}
