package com.courage.platform.redis.client;

import com.courage.platform.redis.client.command.AtomicCommand;
import com.courage.platform.redis.client.command.ListCommand;
import com.courage.platform.redis.client.command.SetCommand;
import com.courage.platform.redis.client.command.StringCommand;
import com.courage.platform.redis.client.command.impl.AtomicCommandImpl;
import com.courage.platform.redis.client.command.impl.ListCommandImpl;
import com.courage.platform.redis.client.command.impl.SetCommandImpl;
import com.courage.platform.redis.client.command.impl.StringCommandImpl;
import com.courage.platform.redis.client.config.ClusterServerConfig;
import com.courage.platform.redis.client.config.SentinelServersConfig;
import com.courage.platform.redis.client.enums.RedisCodec;
import com.courage.platform.redis.client.utils.ConfigBuilder;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 平台redis 客户端
 * Created by zhangyong on 2020/1/18.
 */
public class RedisClient {

    private final static Logger logger = LoggerFactory.getLogger(RedisClient.class);

    private RedissonClient redissonClient;

    public RedisClient(SingleServerConfig SingleServerConfig) {
        Config config = ConfigBuilder.buildBySingleServerConfig(SingleServerConfig);
        //默认string编解码
        config.setCodec(new StringCodec());
        this.redissonClient = Redisson.create(config);
    }

    public RedisClient(Config config) {
        //默认string编解码
        config.setCodec(new StringCodec());
        this.redissonClient = Redisson.create(config);
    }

    public RedisClient(ClusterServerConfig ClusterServerConfig) {
        Config config = ConfigBuilder.buildByClusterServerConfig(ClusterServerConfig);
        //默认string编解码
        config.setCodec(new StringCodec());
        this.redissonClient = Redisson.create(config);
    }

    public RedisClient(SentinelServersConfig SentinelServersConfig) {
        Config config = ConfigBuilder.buildBySentinelServerConfig(SentinelServersConfig);
        //默认string编解码
        config.setCodec(new StringCodec());
        this.redissonClient = Redisson.create(config);
    }

    public void shutdown() {
        if (this.redissonClient != null) {
            this.redissonClient.shutdown();
        }
    }

    //============================================================================================= get method start ====================================================================

    public StringCommand getStringCommand() {
        StringCommand StringCommand = new StringCommandImpl(this.redissonClient);
        return StringCommand;
    }

    public AtomicCommand getAtomicCommand() {
        AtomicCommand AtomicCommand = new AtomicCommandImpl(this.redissonClient);
        return AtomicCommand;
    }

    public ListCommand getListCommand() {
        ListCommand ListCommand = new ListCommandImpl(this.redissonClient);
        return ListCommand;
    }

    public ListCommand getListCommand(RedisCodec RedisCodec) {
        ListCommand ListCommand = new ListCommandImpl(this.redissonClient, RedisCodec);
        return ListCommand;
    }

    public SetCommand getSetCommand() {
        SetCommand SetCommand = new SetCommandImpl(this.redissonClient);
        return SetCommand;
    }

    public SetCommand getSetCommand(RedisCodec RedisCodec) {
        SetCommand SetCommand = new SetCommandImpl(this.redissonClient, RedisCodec);
        return SetCommand;
    }

    public ZSetCommand getZSetCommand() {
        ZSetCommand ZSetCommand = new ZSetCommandImpl(this.redissonClient);
        return ZSetCommand;
    }

    public ZSetCommand getZSetCommand(RedisCodec RedisCodec) {
        ZSetCommand ZSetCommand = new ZSetCommandImpl(this.redissonClient, RedisCodec);
        return ZSetCommand;
    }

    public HashCommand getHashCommand() {
        HashCommand HashCommand = new HashCommandImpl(this.redissonClient);
        return HashCommand;
    }

    public HashCommand getHashCommand(RedisCodec RedisCodec) {
        HashCommand HashCommand = new HashCommandImpl(this.redissonClient, RedisCodec);
        return HashCommand;
    }

    public ScriptCommand getScriptCommand() {
        ScriptCommand ScriptCommand = new ScriptCommandImpl(this.redissonClient);
        return ScriptCommand;
    }

    public RedisLock getLock(String name) {
        RedisLock redisLock = new RedisLock(this.redissonClient, name);
        return redisLock;
    }

    public RedissonClient getRedissonClient() {
        return redissonClient;
    }

    public RedisMessageQueueBuilder getRedisMessageQueueBuilder() {
        RedisMessageQueueBuilder redisMessageQueueBuilder = new RedisMessageQueueBuilder(this.redissonClient);
        return redisMessageQueueBuilder;
    }

}
