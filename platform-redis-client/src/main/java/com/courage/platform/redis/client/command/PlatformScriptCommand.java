package com.courage.platform.redis.client.command;

/**
 * 执行 lua 脚本
 * Created by zhangyong on 2020/1/27.
 */
public interface PlatformScriptCommand extends PlatformKeyCommand {

    String scriptLoad(String luaScript);
    
}
