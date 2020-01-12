package com.courage.platform.redis.client.command.impl;

import com.courage.platform.redis.client.command.RedisCommand;

import java.util.Set;

public class CommonCommand implements RedisCommand {

    public Long del(String key) {
        return null;
    }

    public Long ttl(String key) {
        return null;
    }

    public boolean exists(String key) {
        return false;
    }

    public String type(String key) {
        return null;
    }

    public Long expire(String key, int seconds) {
        return null;
    }

    public Long expireAt(String key, long unixTime) {
        return null;
    }

    public Long pexpire(String key, long seconds) {
        return null;
    }

    public Long pexpireAt(String key, long unixTime) {
        return null;
    }

    public Set<String> keys(String pattern) {
        return null;
    }

    public Set<String> keysWithOutNamespace(String pattern) {
        return null;
    }

}
