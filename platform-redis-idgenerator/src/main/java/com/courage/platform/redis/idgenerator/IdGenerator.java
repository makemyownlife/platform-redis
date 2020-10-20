package com.courage.platform.redis.idgenerator;

import com.courage.platform.redis.client.PlatformRedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 编号生成器
 */
public class IdGenerator {

    private final static Logger logger = LoggerFactory.getLogger(IdGenerator.class);

    private PlatformRedisClient platformRedisClient;

    public IdGenerator(PlatformRedisClient platformRedisClient) {
        this.platformRedisClient = platformRedisClient;
    }

}
