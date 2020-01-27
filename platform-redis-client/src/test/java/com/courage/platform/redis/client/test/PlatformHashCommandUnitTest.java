package com.courage.platform.redis.client.test;

import com.courage.platform.redis.client.PlatformRedisClient;
import org.junit.Before;
import org.junit.Test;
import org.redisson.config.Config;

/**
 * hash 单元测试
 * Created by zhangyong on 2020/1/27.
 */
public class PlatformHashCommandUnitTest {

    private PlatformRedisClient platformRedisClient;

    @Before
    public void start() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        this.platformRedisClient = new PlatformRedisClient(config);
    }

    @Test
    public void setValue() {
        Object my = this.platformRedisClient.getPlatformHashCommand().hset("myhash", "time", "李林");
        System.out.println(my);
        Object my1 = this.platformRedisClient.getPlatformHashCommand().hset("myhash", "time", "李林 &zhangyong");
        System.out.println(my1);
    }

    @Test
    public void get() {
        Object my = this.platformRedisClient.getPlatformHashCommand().hget("myhash", "time");
        System.out.println(my);
    }

}
