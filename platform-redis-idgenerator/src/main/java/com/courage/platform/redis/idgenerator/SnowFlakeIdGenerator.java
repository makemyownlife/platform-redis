package com.courage.platform.redis.idgenerator;

public class SnowFlakeIdGenerator {

    //总的分区数
    private final static int SHARDING_NUM = ShardingConstant.SHARDING_LENGTH;

    //最大机器id 1023 也就是 1111111111
    private final static int MAX_WORKER_ID = SHARDING_NUM - 1;

    //最大序号
    private final static int MAX_SEQ = 4095;

    private final static long WORKERID_BITS = 10L;

    private final static long SEQUENCE_BITS = 12L;

    private final static long TIMESTAMP_LEFTSHIFT = SEQUENCE_BITS + WORKERID_BITS;

    private final static long WORKERID_SHIFT = SEQUENCE_BITS;

    public static long getUniqueId(int workerId, int seqId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException("workerId is not Illegal");
        }
        if (seqId > MAX_SEQ || seqId < 0) {
            throw new IllegalArgumentException("seqId is not Illegal ");
        }
        //时间戳
        long timestamp = System.currentTimeMillis();
        //机器编号
        return (timestamp << TIMESTAMP_LEFTSHIFT) | (workerId << WORKERID_SHIFT) | seqId;
    }

    public static long getUniqueId(long timestamp, int workerId, int seqId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException("workerId is not Illegal");
        }
        if (seqId > MAX_SEQ || seqId < 0) {
            throw new IllegalArgumentException("seqId is not Illegal ");
        }
        //机器编号
        return (timestamp << TIMESTAMP_LEFTSHIFT) | (workerId << WORKERID_SHIFT) | seqId;
    }

}
