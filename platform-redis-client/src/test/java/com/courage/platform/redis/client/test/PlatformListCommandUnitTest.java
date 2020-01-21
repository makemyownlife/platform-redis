package com.courage.platform.redis.client.test;

import com.courage.platform.redis.client.PlatformRedisClient;
import org.redisson.config.Config;

/**
 * Created by zhangyong on 2020/1/18.
 */
public class PlatformListCommandUnitTest {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        PlatformRedisClient platformRedisClient = new PlatformRedisClient(config);

        platformRedisClient.getPlatformListCommand().lpush("list", "zhangyong张勇");
        platformRedisClient.getPlatformListCommand().rpush("list", "gaohui");
    }

}