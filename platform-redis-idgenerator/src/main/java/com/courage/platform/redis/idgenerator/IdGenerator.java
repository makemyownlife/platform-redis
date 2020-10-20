package com.courage.platform.redis.idgenerator;

import com.courage.platform.redis.client.PlatformRedisClient;
import com.courage.platform.redis.client.command.PlatformAtomicCommand;
import org.apache.commons.lang3.time.DateFormatUtils;
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

    /**
     * 首先通过redis服务 当前毫秒数获取10个批次的序号
     *
     * @param shardingKey
     * @return
     */
    public Long createUniqueId(String shardingKey) {
        PlatformAtomicCommand platformAtomicCommand = platformRedisClient.getPlatformAtomicCommand();
        //int workerId = Math.abs(crc32（shardingkey）/1024);
        int workerId = 1023;
        Long currentTime = System.currentTimeMillis();
        String formatTime = DateFormatUtils.format(currentTime, "yyyyMMddHHmmssSSS");
        String idGeneratorKey = "10000:" + formatTime;
        Long counter = platformAtomicCommand.incrBy(idGeneratorKey, 20);
        
        return null;
    }

}
