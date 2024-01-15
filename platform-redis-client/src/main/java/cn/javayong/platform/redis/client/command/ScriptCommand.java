package cn.javayong.platform.redis.client.command;

import java.util.List;

/**
 * 执行 lua 脚本
 * Created by zhangyong on 2020/1/27.
 */
public interface ScriptCommand extends KeyCommand {

    enum ReturnType {
        BOOLEAN,
        INTEGER,
        MULTI,
        STATUS,
        VALUE,
        MAPVALUE,
        MAPVALUELIST;
    }

    /*
       KEYS[1] 用来表示在redis 中用作键值的参数占位，主要用來传递在redis 中用作keyz值的参数。
       ARGV[1] 用来表示在redis 中用作参数的占位，主要用来传递在redis中用做 value值的参数。
     */
    String scriptLoad(String luaScript);

    Object evalSha(String shardingkey, String shaDigest, List<Object> keys, Object... values);

    Object eval(String shardingkey, String luaScript, List<Object> keys, Object... values);

    Object evalSha(String shardingkey, String shaDigest, ReturnType returnType, List<Object> keys, Object... values);

    Object eval(String shardingkey, String luaScript, ReturnType returnType, List<Object> keys, Object... values);


}
