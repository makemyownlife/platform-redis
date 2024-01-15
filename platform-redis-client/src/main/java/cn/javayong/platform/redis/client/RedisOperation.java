package cn.javayong.platform.redis.client;

import cn.javayong.platform.redis.client.command.*;
import cn.javayong.platform.redis.client.command.impl.*;
import cn.javayong.platform.redis.client.config.ClusterConfig;
import cn.javayong.platform.redis.client.config.SentinelConfig;
import cn.javayong.platform.redis.client.config.SingleConfig;
import cn.javayong.platform.redis.client.enums.RedisCodec;
import cn.javayong.platform.redis.client.lock.RedisLock;
import cn.javayong.platform.redis.client.lock.impl.RedisLockImpl;
import cn.javayong.platform.redis.client.queue.RedisMessageQueueBuilder;
import cn.javayong.platform.redis.client.utils.ConfigBuilder;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  平台redis 客户端
 *  Created by zhangyong on 2020/1/18.
 */
public class RedisOperation {

    private final static Logger logger = LoggerFactory.getLogger(RedisOperation.class);

    private RedissonClient redissonClient;

    public RedisOperation(SingleConfig SingleServerConfig) {
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

    public StringCommand getStringCommand(RedisCodec RedisCodec) {
        StringCommand StringCommand = new StringCommandImpl(this.redissonClient , RedisCodec);
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

    public ListCommand getListCommand(RedisCodec redisCodec) {
        ListCommand ListCommand = new ListCommandImpl(this.redissonClient, redisCodec);
        return ListCommand;
    }

    public SetCommand getSetCommand() {
        SetCommand SetCommand = new SetCommandImpl(this.redissonClient);
        return SetCommand;
    }

    public SetCommand getSetCommand(RedisCodec redisCodec) {
        SetCommand SetCommand = new SetCommandImpl(this.redissonClient, redisCodec);
        return SetCommand;
    }

    public ZSetCommand getZSetCommand() {
        ZSetCommand ZSetCommand = new ZSetCommandImpl(this.redissonClient);
        return ZSetCommand;
    }

    public ZSetCommand getZSetCommand(RedisCodec redisCodec) {
        ZSetCommand ZSetCommand = new ZSetCommandImpl(this.redissonClient, redisCodec);
        return ZSetCommand;
    }

    public HashCommand getHashCommand() {
        HashCommand HashCommand = new HashCommandImpl(this.redissonClient);
        return HashCommand;
    }

    public HashCommand getHashCommand(RedisCodec redisCodec) {
        HashCommand HashCommand = new HashCommandImpl(this.redissonClient, redisCodec);
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
