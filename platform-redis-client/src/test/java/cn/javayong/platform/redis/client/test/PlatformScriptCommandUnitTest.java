package cn.javayong.platform.redis.client.test;

import cn.javayong.platform.redis.client.RedisOperation;
import org.redisson.config.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyong on 2020/1/18.
 */
public class PlatformScriptCommandUnitTest {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedisOperation redisOperation = new RedisOperation(config);

        //09ae8711e1bb916fd7a4a9a701e7160e3330ab12
        String value = redisOperation.getScriptCommand().scriptLoad("return 123");

        List<Object> param = new ArrayList<Object>();
        param.add("1");

        Object t = redisOperation.getScriptCommand().evalSha("hello", "30dc9ef2a8d563dced9a243ecd0cc449c2ea0144", param, "2");
        System.out.println(t);
    }

}
