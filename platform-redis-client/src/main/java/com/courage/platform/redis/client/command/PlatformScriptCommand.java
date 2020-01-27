package com.courage.platform.redis.client.command;

import java.util.List;

/**
 * 执行 lua 脚本
 * Created by zhangyong on 2020/1/27.
 */
public interface PlatformScriptCommand extends PlatformKeyCommand {

    String scriptLoad(String luaScript);

    Object evalSha(String key, String shaDigest, List<Object> keys, Object... values);

}
