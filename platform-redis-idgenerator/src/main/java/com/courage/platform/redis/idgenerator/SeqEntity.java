package com.courage.platform.redis.idgenerator;

/**
 * 序号实体
 */
public class SeqEntity {


    private Long currentTime;

    private Integer seq;

    public SeqEntity(Long currentTime, Integer seq) {
        this.currentTime = currentTime;
        this.seq = seq;
    }

    public Long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Long currentTime) {
        this.currentTime = currentTime;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

}
