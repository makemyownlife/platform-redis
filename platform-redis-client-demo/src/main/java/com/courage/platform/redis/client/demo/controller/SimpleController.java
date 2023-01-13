package com.courage.platform.redis.client.demo.controller;

import com.courage.platform.redis.client.RedisOperation;
import com.courage.platform.redis.client.command.StringCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {

    private final static Logger logger = LoggerFactory.getLogger(SimpleController.class);

    @Autowired
    private RedisOperation redisOperation;

    @Autowired
    private StringCommand sss;


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hellodanbiao() {
        String mylife = redisOperation.getStringCommand().get("hello");
        System.out.println(mylife);
        redisOperation.getStringCommand().set("hello", "zhang勇");
        return "hello-service";
    }

    @RequestMapping(value = "/generatorId", method = RequestMethod.GET)
    @ResponseBody
    public String generatorId() {
        String mylife = redisOperation.getStringCommand().get("hello");
        System.out.println(mylife);
        redisOperation.getStringCommand().set("hello", "zhang勇");
        return "hello-service";
    }

}
