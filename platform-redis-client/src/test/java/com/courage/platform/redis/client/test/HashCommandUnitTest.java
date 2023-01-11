package com.courage.platform.redis.client.test;

import com.courage.platform.redis.client.RedisClient;
import org.junit.Before;
import org.junit.Test;
import org.redisson.config.Config;

import java.util.HashMap;
import java.util.Map;

/**
 * hash 单元测试
 * Created by zhangyong on 2020/1/27.
 */
public class HashCommandUnitTest {

    private RedisClient redisClient;

    @Before
    public void start() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        this.redisClient = new RedisClient(config);
    }

    @Test
    public void setValue() {
        Map<String, Object> mapping = new HashMap<String, Object>();
        mapping.put("one", "张勇");
        Object my = this.redisClient.getHashCommand().hset("myhash", "time", mapping);
        System.out.println(my);
        mapping.put("two", "高慧");
        Object my1 = this.redisClient.getPlatformHashCommand().hset("myhash", "time", mapping);
        System.out.println(my1);
    }

    @Test
    public void get() {
        Object my = this.redisClient.getPlatformHashCommand().hget("myhash", "time");
        System.out.println(my);
    }

}
