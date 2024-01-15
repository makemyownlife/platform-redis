package cn.javayong.platform.redis.client.utils;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 本地缓冲 (每张表预留50个)
 */
public class LocalSequence {

    private static ConcurrentLinkedQueue<SeqEntity> sequenceQueue = new ConcurrentLinkedQueue<SeqEntity>();

    public static SeqEntity getSeqEntity() {
        SeqEntity seqEntity = sequenceQueue.poll();
        if (seqEntity == null) {
            return null;
        }
        return seqEntity;
    }

    public static void addSeqEntity(SeqEntity seqEntity) {
        sequenceQueue.offer(seqEntity);
    }

}
