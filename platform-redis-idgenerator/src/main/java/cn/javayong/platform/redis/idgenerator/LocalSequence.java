package cn.javayong.platform.redis.idgenerator;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 本地缓冲 (每张表预留50个)
 */
public class LocalSequence {

    private static ConcurrentHashMap<String, ConcurrentLinkedQueue<SeqEntity>> sequenceMap = new ConcurrentHashMap<String, ConcurrentLinkedQueue<SeqEntity>>();

    private static Object lock = new Object();

    public static SeqEntity getSeqEntity(String tableName) {
        ConcurrentLinkedQueue<SeqEntity> seqEntities = sequenceMap.get(tableName);
        if (seqEntities == null) {
            //没有实体对象 则直接返回
            synchronized (lock) {
                seqEntities = new ConcurrentLinkedQueue<SeqEntity>();
                sequenceMap.put(tableName, seqEntities);
            }
            return null;
        }
        SeqEntity seqEntity = seqEntities.poll();
        if (seqEntity == null) {
            return null;
        }
        return seqEntity;
    }

    public static void addSeqEntity(String tableName, SeqEntity seqEntity) {
        ConcurrentLinkedQueue<SeqEntity> seqEntities = sequenceMap.get(tableName);
        if (seqEntities == null) {
            //没有实体对象 则直接返回
            synchronized (lock) {
                seqEntities = new ConcurrentLinkedQueue<SeqEntity>();
                sequenceMap.put(tableName, seqEntities);
            }
        } else {
            seqEntities.offer(seqEntity);
        }
    }
}
