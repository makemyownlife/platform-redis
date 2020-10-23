package com.courage.platform.redis.idgenerator;

import com.courage.platform.redis.client.PlatformRedisClient;
import com.courage.platform.redis.client.command.PlatformAtomicCommand;
import com.courage.platform.redis.client.config.PlatformSentinelServersConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.zip.CRC32;

/**
 * 编号生成器
 */
public class IdGenerator {

    private final static Logger logger = LoggerFactory.getLogger(IdGenerator.class);

    private PlatformAtomicCommand platformAtomicCommand;

    public IdGenerator(PlatformRedisClient platformRedisClient) {
        this.platformAtomicCommand = platformRedisClient.getPlatformAtomicCommand();
    }

    /**
     * 生成唯一的序号值
     *
     * @param shardingKey
     * @return
     */
    public Long createUniqueId(String shardingKey, String tableName) {
        //首先将shardingkey转换成 crc32的编码
        Long value = null;
        try {
            CRC32 crc32 = new CRC32();
            crc32.update(shardingKey.getBytes("UTF-8"));
            value = crc32.getValue();
        } catch (Exception e) {
            logger.error("update crc32 error:", e);
        }

        Long currentTime = System.currentTimeMillis();

        //转换成中间10位编码
        Long workerId = Math.abs(value % IdConstants.SHARDING_LENGTH);

        //从本地缓冲中获取
        SeqEntity seqEntity = LocalSequence.getSeqEntity(tableName);
        if (seqEntity != null) {
            return SnowFlakeIdGenerator.getUniqueId(
                    seqEntity.getCurrentTime(),
                    workerId.intValue(),
                    seqEntity.getSeq());
        }

        //从redis自增一个步长,放入本地内存中待用
        String idGeneratorKey = IdConstants.ID_REDIS_PFEFIX + currentTime + ":" + tableName;
        Long counter = platformAtomicCommand.incrByEx(idGeneratorKey, IdConstants.STEP_LENGTH, IdConstants.SEQ_EXPIRE_TIME);
        logger.warn("redisKey:{} 序号值:{} ", idGeneratorKey, counter);

        //判断是否有极限情况 ,1ms产生的数据超过了最大序号，那么最有可能原因是 当前机器的时间钟不一样
        if (counter - IdConstants.STEP_LENGTH >= IdConstants.MAX_SEQ) {
            logger.error("redisKey:{} 序号值:{} 超过了最大阈值{}", idGeneratorKey, counter, IdConstants.MAX_SEQ);
            return null;
        }

        //当前自增的最小id
        long cursor = counter - IdConstants.STEP_LENGTH + 1;
        while (cursor <= IdConstants.MAX_SEQ && cursor < counter) {
            SeqEntity newSeqEntity = new SeqEntity(currentTime, new Long(cursor).intValue());
            LocalSequence.addSeqEntity(tableName, newSeqEntity);
            cursor++;
        }

        seqEntity = LocalSequence.getSeqEntity(tableName);
        if (seqEntity == null) {
            return null;
        }

        Long uniqueId = SnowFlakeIdGenerator.getUniqueId(seqEntity.getCurrentTime(), workerId.intValue(), seqEntity.getSeq());
        return uniqueId;
    }

    public static void main(String[] args) {
        PlatformSentinelServersConfig platformSentinelServersConfig = new PlatformSentinelServersConfig();
        platformSentinelServersConfig.setNodes("CloudSentineRedis01Host:26101,CloudSentineRedis02Host:26101,CloudSentineRedis03Host:26101");
        platformSentinelServersConfig.setMaster("master6101");
        platformSentinelServersConfig.setPassword("M0uMBZfCu9FCVv#^");
        PlatformRedisClient platformRedisClient = new PlatformRedisClient(platformSentinelServersConfig);

        platformRedisClient.getPlatformSetCommand().addAndEx("mylife", 10022, "hni");

        long cur = System.currentTimeMillis();
        System.out.println(cur);

        platformRedisClient.getPlatformZSetCommand().zadd("feedList", cur, "399");
//        IdGenerator idGenerator = new IdGenerator(platformRedisClient);
//        for (int i = 0; i < 1000; i++) {
//            long start = System.currentTimeMillis();
//            Long orderId = idGenerator.createUniqueId("zhangyong", "t_order_base");
//            System.out.println(orderId + " " + Long.toBinaryString(orderId) + " costtime:" + (System.currentTimeMillis() - start) + "ms");
//        }
    }

}
