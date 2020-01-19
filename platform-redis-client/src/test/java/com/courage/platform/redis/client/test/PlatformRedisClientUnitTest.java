package com.courage.platform.redis.client.test;

import com.courage.platform.redis.client.PlatformRedisClient;
import org.redisson.config.Config;

/**
 * Created by zhangyong on 2020/1/18.
 */
public class PlatformRedisClientUnitTest {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        PlatformRedisClient platformRedisClient = new PlatformRedisClient(config);
        platformRedisClient.getPlatformStringCommand().set("hello", "李林");
        String value = platformRedisClient.getPlatformStringCommand().get("hello");
        System.out.println(value);

    }

}
