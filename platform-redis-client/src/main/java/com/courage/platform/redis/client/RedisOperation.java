package com.courage.platform.redis.client;

import com.courage.platform.redis.client.command.*;
import com.courage.platform.redis.client.command.impl.*;
import com.courage.platform.redis.client.config.ClusterConfig;
import com.courage.platform.redis.client.config.SentinelConfig;
import com.courage.platform.redis.client.enums.RedisCodec;
import com.courage.platform.redis.client.lock.RedisLock;
import com.courage.platform.redis.client.lock.impl.RedisLockImpl;
import com.courage.platform.redis.client.queue.RedisMessageQueueBuilder;
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
public class RedisOperation {

    private final static Logger logger = LoggerFactory.getLogger(RedisOperation.class);

    private RedissonClient redissonClient;

    public RedisOperation(SingleServerConfig SingleServerConfig) {
        Config config = ConfigBuilder.buildBySingleServerConfig(SingleServerConfig);
        //默认string编解码
        config.setCodec(new StringCodec());
        this.redissonClient = Redisson.create(config);
    }

    public RedisOperation(Config config) {
        //默认string编解码
        config.setCodec(new StringCodec());
        this.redissonClient = Redisson.create(config);
    }

    public RedisOperation(ClusterConfig ClusterConfig) {
        Config config = ConfigBuilder.buildByClusterServerConfig(ClusterConfig);
        //默认string编解码
        config.setCodec(new StringCodec());
        this.redissonClient = Redisson.create(config);
    }

    public RedisOperation(SentinelConfig SentinelConfig) {
        Config config = ConfigBuilder.buildBySentinelServerConfig(SentinelConfig);
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
        RedisLock redisLock = new RedisLockImpl(this.redissonClient, name);
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
