package com.courage.platform.redis.client;

import com.courage.platform.redis.client.batch.PlatformBatchCommand;
import com.courage.platform.redis.client.command.*;
import com.courage.platform.redis.client.command.impl.*;
import com.courage.platform.redis.client.config.PlatformClusterServerConfig;
import com.courage.platform.redis.client.config.PlatformSentinelServersConfig;
import com.courage.platform.redis.client.config.PlatformSingleServerConfig;
import com.courage.platform.redis.client.utils.ConfigBuilder;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 平台redis 客户端
 * Created by zhangyong on 2020/1/18.
 */
public class PlatformRedisClient {

    private final static Logger logger = LoggerFactory.getLogger(PlatformRedisClient.class);

    private RedissonClient redissonClient;

    private PlatformStringCommand platformStringCommand;

    private PlatformAtomicCommand platformAtomicCommand;

    private PlatformListCommand platformListCommand;

    private PlatformSetCommand platformSetCommand;

    private PlatformZSetCommand platformZSetCommand;

    private PlatformHashCommand platformHashCommand;

    private PlatformScriptCommand platformScriptCommand;

    private PlatformBatchCommand platformBatchCommand;

    public PlatformRedisClient(PlatformSingleServerConfig platformSingleServerConfig) {
        Config config = ConfigBuilder.buildBySingleServerConfig(platformSingleServerConfig);
        //默认string编解码
        config.setCodec(new StringCodec());
        this.redissonClient = Redisson.create(config);
        this.createCommands();
    }

    public PlatformRedisClient(Config config) {
        //默认string编解码
        config.setCodec(new StringCodec());
        this.redissonClient = Redisson.create(config);
        this.createCommands();
    }

    public PlatformRedisClient(PlatformClusterServerConfig platformClusterServerConfig) {
        Config config = ConfigBuilder.buildByClusterServerConfig(platformClusterServerConfig);
        //默认string编解码
        config.setCodec(new StringCodec());
        this.redissonClient = Redisson.create(config);
        this.createCommands();
    }

    public PlatformRedisClient(PlatformSentinelServersConfig platformSentinelServersConfig) {
        Config config = ConfigBuilder.buildBySentinelServerConfig(platformSentinelServersConfig);
        //默认string编解码
        config.setCodec(new StringCodec());
        this.redissonClient = Redisson.create(config);
        this.createCommands();
    }

    private void createCommands() {
        this.platformStringCommand = new PlatformStringCommandImpl(this.redissonClient);
        this.platformAtomicCommand = new PlatformAtomicCommandImpl(this.redissonClient);
        this.platformListCommand = new PlatformListCommandImpl(this.redissonClient);
        this.platformSetCommand = new PlatformSetCommandImpl(this.redissonClient);
        this.platformZSetCommand = new PlatformZSetCommandImpl(this.redissonClient);
        this.platformHashCommand = new PlatformHashCommandImpl(this.redissonClient);
        this.platformScriptCommand = new PlatformScriptCommandImpl(this.redissonClient);
        this.platformBatchCommand = new PlatformBatchCommand(this.redissonClient);
    }

    public PlatformStringCommand getPlatformStringCommand() {
        return platformStringCommand;
    }

    public PlatformAtomicCommand getPlatformAtomicCommand() {
        return platformAtomicCommand;
    }

    public PlatformListCommand getPlatformListCommand() {
        return platformListCommand;
    }

    public PlatformSetCommand getPlatformSetCommand() {
        return platformSetCommand;
    }

    public PlatformZSetCommand getPlatformZSetCommand() {
        return platformZSetCommand;
    }

    public PlatformHashCommand getPlatformHashCommand() {
        return platformHashCommand;
    }

    public PlatformScriptCommand getPlatformScriptCommand() {
        return platformScriptCommand;
    }

    public PlatformBatchCommand getPlatformBatchCommand() {
        return platformBatchCommand;
    }

    public void shutdown() {
        if (this.redissonClient != null) {
            this.redissonClient.shutdown();
        }
    }

}
