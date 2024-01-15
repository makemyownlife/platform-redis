package cn.javayong.platform.redis.client.test;

import cn.javayong.platform.redis.client.RedisOperation;
import org.redisson.config.Config;

/**
 * Created by zhangyong on 2020/1/18.
 */
public class PlatformSetCommandUnitTest {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedisOperation redisOperation = new RedisOperation(config);
        redisOperation.getSetCommand().addAndEx("myset", 109, "zhangyong");

        redisOperation.getSetCommand().addAndEx("myset", 100, "li林");

        redisOperation.getSetCommand().removes("myset", "zhangyong");
    }

}
