package cn.javayong.platform.redis.client.test;

import cn.javayong.platform.redis.client.RedisOperation;
import cn.javayong.platform.redis.client.config.SingleConfig;

/**
 * Created by zhangyong on 2020/1/18.
 */
public class PlatformListCommandUnitTest {

    public static void main(String[] args) {
        SingleConfig config = new SingleConfig();
        config.setAddress("redis://127.0.0.1:6379");
        RedisOperation redisOperation = new RedisOperation(config);

        redisOperation.getListCommand().lpush("list", "zhangyong张勇");
        redisOperation.getListCommand().rpush("list", "gaohui");
    }

}
