package com.courage.platform.redis.client.test;

import com.courage.platform.redis.client.RedisOperation;
import com.courage.platform.redis.client.config.SingleConfig;
import org.junit.Before;
import org.junit.Test;
import org.redisson.config.Config;

import java.util.HashMap;
import java.util.Map;

/**
 *  hash 单元测试
 * Created by zhangyong on 2020/1/27.
 */
public class HashCommandUnitTest {

    private RedisOperation redisOperation;

    @Before
    public void start() {
        SingleConfig config = new SingleConfig();
        config.setAddress("redis://127.0.0.1:6379");
        this.redisOperation = new RedisOperation(config);
    }

    @Test
    public void setValue() {
        Map<String, Object> mapping = new HashMap<String, Object>();
        mapping.put("one", "张勇");
        Object my = this.redisOperation.getHashCommand().hset("myhash", "time", mapping);
        System.out.println(my);
        mapping.put("two", "高慧");
        Object my1 = this.redisOperation.getHashCommand().hset("myhash", "time", mapping);
        System.out.println(my1);
    }

    @Test
    public void get() {
        Object my = this.redisOperation.getHashCommand().hget("myhash", "time");
        System.out.println(my);
    }

}
