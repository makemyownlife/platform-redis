package com.courage.platform.redis.client.test;

import com.courage.platform.redis.client.PlatformRedisClient;
import com.courage.platform.redis.client.batch.pipeline.HashMgetCommand;
import com.courage.platform.redis.client.batch.pipeline.PiplelineCommand;
import com.courage.platform.redis.client.batch.pipeline.ZsetRangeCommand;
import com.courage.platform.redis.client.config.PlatformSentinelServersConfig;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PlatformPlatformBatchCommandUnitTest {

    private PlatformRedisClient platformRedisClient;

    @Before
    public void before() {
        PlatformSentinelServersConfig platformSentinelServersConfig = new PlatformSentinelServersConfig();
        platformSentinelServersConfig.setNodes("CloudSentineRedis01Host:26101,CloudSentineRedis02Host:26101,CloudSentineRedis03Host:26101");
        platformSentinelServersConfig.setMaster("master6101");
        platformSentinelServersConfig.setPassword("M0uMBZfCu9FCVv#^");
        this.platformRedisClient = new PlatformRedisClient(platformSentinelServersConfig);
    }

    @Test
    public void batchList() {
        List<PiplelineCommand> piplelineCommandList = new ArrayList<PiplelineCommand>(5);
        //添加hash值
        HashSet<Object> fields = new HashSet<Object>();
        fields.add("base");
        fields.add("counter");
        fields.add("other");
        String key1 = "mytesthash";
        HashMgetCommand hashMgetCommand = new HashMgetCommand(key1, fields);
        //添加zset值
        ZsetRangeCommand zsetRangeCommand = new ZsetRangeCommand("feedList", 0, 2);

        piplelineCommandList.add(hashMgetCommand);
        piplelineCommandList.add(zsetRangeCommand);
        List<?> result = this.platformRedisClient.getPlatformBatchCommand().executePipelineCommands(piplelineCommandList);
        System.out.println(result);
    }

}
