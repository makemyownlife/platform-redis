package com.courage.platform.redis.client.command.impl;

import com.courage.platform.redis.client.command.PlatformScriptCommand;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;

import java.util.List;

/**
 * 执行 lua 脚本
 * Created by zhangyong on 2020/1/27.
 */
public class PlatformScriptCommandImpl extends PlatformKeyCommandImpl implements PlatformScriptCommand {

    private static JsonJacksonCodec codec = JsonJacksonCodec.INSTANCE;

    public PlatformScriptCommandImpl(RedissonClient redissonClient) {
        super(redissonClient);
    }

    public Object evalSha(String key, String shaDigest, List<Object> keys, Object... values) {
        RScript rScript = getRedissonClient().getScript(codec);
        return rScript.evalSha(key, RScript.Mode.READ_WRITE, shaDigest, null, null, keys, values);
    }

    public String scriptLoad(String luaScript) {
        RScript rScript = getRedissonClient().getScript(codec);
        return rScript.scriptLoad(luaScript);
    }

}
