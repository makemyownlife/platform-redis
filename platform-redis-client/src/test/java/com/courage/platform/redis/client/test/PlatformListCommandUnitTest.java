package com.courage.platform.redis.client.test;

import com.courage.platform.redis.client.RedisClient;
import org.redisson.config.Config;

/**
 * Created by zhangyong on 2020/1/18.
 */
public class PlatformListCommandUnitTest {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedisClient redisClient = new RedisClient(config);

        redisClient.getPlatformListCommand().lpush("list", "zhangyong张勇");
        redisClient.getPlatformListCommand().rpush("list", "gaohui");
    }

}
