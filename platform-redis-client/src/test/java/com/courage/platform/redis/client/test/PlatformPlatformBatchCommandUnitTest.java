package com.courage.platform.redis.client.test;

import com.courage.platform.redis.client.RedisClient;
import com.courage.platform.redis.client.batch.pipeline.HashMgetCommand;
import com.courage.platform.redis.client.batch.pipeline.PiplelineCommand;
import com.courage.platform.redis.client.batch.pipeline.ZsetRangeCommand;
import com.courage.platform.redis.client.config.SentinelServersConfig;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PlatformPlatformBatchCommandUnitTest {

    private RedisClient redisClient;

    @Before
    public void before() {
        SentinelServersConfig sentinelServersConfig = new SentinelServersConfig();
        sentinelServersConfig.setNodes("CloudSentineRedis01Host:26101,CloudSentineRedis02Host:26101,CloudSentineRedis03Host:26101");
        sentinelServersConfig.setMaster("master6101");
        sentinelServersConfig.setPassword("M0uMBZfCu9FCVv#^");
        this.redisClient = new RedisClient(sentinelServersConfig);
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
        List<?> result = this.redisClient.getPlatformBatchCommand().executePipelineCommands(piplelineCommandList);
        System.out.println(result);
    }

}
