package cn.javayong.platform.redis.client.test;

import cn.javayong.platform.redis.client.RedisOperation;
import cn.javayong.platform.redis.client.command.HashCommand;
import cn.javayong.platform.redis.client.config.SingleConfig;
import org.junit.Before;
import org.junit.Test;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * hash 单元测试
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
        HashCommand hashCommand = this.redisOperation.getHashCommand();
        hashCommand.hset("myhash", "time", "mybatis");
        // 存储字符串
        hashCommand.hset("myhash", "isstr", "李林");
    }

    @Test
    public void setPutAll() throws Exception {
        HashCommand hashCommand = this.redisOperation.getHashCommand();
        Map<String, Object> putAll = new HashMap<>(4);
        putAll.put("a", "你好");
        putAll.put("b", 1);
        hashCommand.hmset("testPutAll", putAll);
        Thread.sleep(2000L);
        Map<String, Object> putAllMap = hashCommand.hmget("testPutAll", "a", "b", "c");
        System.out.println(putAllMap);
    }

    @Test
    public void send() {
        RedissonClient redissonClient = redisOperation.getRedissonClient();
        RMapCache<String, String> verificationCodeMap = redissonClient.getMapCache(String.format("%s:MapCache[%s]", "myapp", "sms_verification_code_map") , StringCodec.INSTANCE);
        String loginKey = "15011319235" + "-login";
        Object loginCount = verificationCodeMap.get(loginKey);
        if (loginCount == null) {
            verificationCodeMap.put(loginKey, "1", 10 * 60, TimeUnit.SECONDS);
        } else {
            //如果不是10分钟内第一次登录,登录次数递增
            Object tempLoginCount = verificationCodeMap.addAndGet(loginKey, 1);
            System.out.println((Integer) tempLoginCount);
            if((Integer) tempLoginCount > 5) {
                System.out.println("发送次数已经超限");
            }
        }
    }

}
