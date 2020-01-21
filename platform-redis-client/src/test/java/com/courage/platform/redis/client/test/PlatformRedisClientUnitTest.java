package com.courage.platform.redis.client.test;

import com.courage.platform.redis.client.PlatformRedisClient;
import org.redisson.config.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyong on 2020/1/18.
 */
public class PlatformRedisClientUnitTest {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        PlatformRedisClient platformRedisClient = new PlatformRedisClient(config);

        platformRedisClient.getPlatformStringCommand().set("hello", "zhangyogn");

        String value = platformRedisClient.getPlatformStringCommand().get("hello");
        System.out.println(value);

        platformRedisClient.getPlatformStringCommand().set("my", "4");
        Long s1 = platformRedisClient.getPlatformAtomicCommand().decrBy("my", 2);

        Long s2 = platformRedisClient.getPlatformAtomicCommand().incrEx("my", 100);

        System.out.println(s1);
        System.out.println(s2);

        List mylist = new ArrayList();
        mylist.add("mytime");

        platformRedisClient.getPlatformListCommand().lpush("list", mylist);

        platformRedisClient.getPlatformListCommand().lpush("list", new Config());

            Object item = platformRedisClient.getPlatformListCommand().lpop("list");
             System.out.println(item);

        Object item1 = platformRedisClient.getPlatformListCommand().lpop("list");
        System.out.println(item1);
    }

}
