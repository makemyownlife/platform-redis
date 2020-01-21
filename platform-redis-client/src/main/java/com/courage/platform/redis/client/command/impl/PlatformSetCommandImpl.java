package com.courage.platform.redis.client.command.impl;

import com.courage.platform.redis.client.command.PlatformSetCommand;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class PlatformSetCommandImpl extends PlatformKeyCommand implements PlatformSetCommand {

    private final static Logger logger = LoggerFactory.getLogger(PlatformSetCommandImpl.class);

    public PlatformSetCommandImpl(RedissonClient redissonClient) {
        super(redissonClient);
    }

    @Override
    public Long addAndEx(String key, int second, Object... value) {
        return null;
    }

    @Override
    public <T> Set<T> getMembers(String key) {
        return null;
    }

    @Override
    public Long removes(String key, Object... value) {
        return null;
    }

    @Override
    public Long size(String key) {
        return null;
    }

    @Override
    public Boolean isMember(String key, Object o) {
        return null;
    }

}
