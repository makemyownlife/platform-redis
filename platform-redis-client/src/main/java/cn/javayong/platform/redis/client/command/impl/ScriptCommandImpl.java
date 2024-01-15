package cn.javayong.platform.redis.client.command.impl;

import cn.javayong.platform.redis.client.command.ScriptCommand;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;

import java.util.List;

/**
 * 执行 lua 脚本
 * Created by zhangyong on 2020/1/27.
 */
public class ScriptCommandImpl extends KeyCommandImpl implements ScriptCommand {

    private static StringCodec codec = StringCodec.INSTANCE;

    public ScriptCommandImpl(RedissonClient redissonClient) {
        super(redissonClient);
    }

    public Object evalSha(String shardingkey, String shaDigest, List<Object> keys, Object... values) {
        return evalSha(shardingkey, shaDigest, ReturnType.VALUE, keys, values);
    }

    public Object eval(String shardingkey, String luaScript, List<Object> keys, Object... values) {
        return eval(shardingkey, luaScript, ReturnType.VALUE, keys, values);
    }

    public Object evalSha(String shardingkey, String shaDigest, ReturnType returnType, List<Object> keys, Object... values) {
        RScript rScript = getRedissonClient().getScript(codec);
        return rScript.evalSha(shardingkey, RScript.Mode.READ_WRITE, shaDigest, transfer(returnType), keys, values);
    }

    public Object eval(String shardingkey, String luaScript, ReturnType returnType, List<Object> keys, Object... values) {
        RScript rScript = getRedissonClient().getScript(codec);
        return rScript.eval(shardingkey, RScript.Mode.READ_WRITE, luaScript, transfer(returnType), keys, values);
    }

    public String scriptLoad(String luaScript) {
        RScript rScript = getRedissonClient().getScript(codec);
        return rScript.scriptLoad(luaScript);
    }

    public void flush() {
        RScript rScript = getRedissonClient().getScript(codec);
        rScript.scriptFlush();
    }

    //========================================================== private method start ===========================================================

    private RScript.ReturnType transfer(ReturnType returnType) {
        if (returnType == null) {
            return null;
        }
        if (returnType == ReturnType.BOOLEAN) {
            return RScript.ReturnType.BOOLEAN;
        }
        if (returnType == ReturnType.INTEGER) {
            return RScript.ReturnType.INTEGER;
        }
        if (returnType == ReturnType.VALUE) {
            return RScript.ReturnType.VALUE;
        }
        if (returnType == ReturnType.MAPVALUE) {
            return RScript.ReturnType.MAPVALUE;
        }
        if (returnType == ReturnType.MAPVALUELIST) {
            return RScript.ReturnType.MAPVALUELIST;
        }
        if (returnType == ReturnType.MULTI) {
            return RScript.ReturnType.MULTI;
        }
        if (returnType == ReturnType.STATUS) {
            return RScript.ReturnType.STATUS;
        }
        return null;
    }

    //========================================================== private method end ===========================================================

}
