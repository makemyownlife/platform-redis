package com.courage.platform.redis.client.test;

import com.courage.platform.redis.client.PlatformRedisClient;
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
        PlatformRedisClient platformRedisClient = new PlatformRedisClient(config);

        //09ae8711e1bb916fd7a4a9a701e7160e3330ab12
        String value = platformRedisClient.getPlatformScriptCommand().scriptLoad("if redis.call(\"EXISTS\",KEYS[1]) == 1 then\n" + "     return redis.call(\"INCRBY\",KEYS[1],ARGV[1])\n" + "   else\n" + "     return nil\n" + "   end  ");
        System.out.println(value);

        List<Object> param = new ArrayList<Object>();
        param.add("1");

        List<String> values = new ArrayList<String>();
        values.add("2");

        Object t = platformRedisClient.getPlatformScriptCommand().evalSha("hello", "09ae8711e1bb916fd7a4a9a701e7160e3330ab12", param, "2");
        System.out.println(t);
    }

}
