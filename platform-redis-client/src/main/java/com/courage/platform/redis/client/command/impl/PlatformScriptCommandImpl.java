package com.courage.platform.redis.client.command.impl;

import com.courage.platform.redis.client.command.PlatformScriptCommand;
import org.redisson.api.RedissonClient;

/**
 * 执行 lua 脚本
 * Created by zhangyong on 2020/1/27.
 */
public class PlatformScriptCommandImpl extends PlatformKeyCommandImpl implements PlatformScriptCommand {

    public PlatformScriptCommandImpl(RedissonClient redissonClient) {
        super(redissonClient);
    }


}
