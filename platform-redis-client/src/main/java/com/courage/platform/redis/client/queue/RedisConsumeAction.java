package com.courage.platform.redis.client.queue;

/**
 * 消费动作
 * Created by zhangyong on 2022/5/9.
 */
public enum RedisConsumeAction {

    /**
     * 消费成功，继续消费下一条消息
     */
    CommitMessage,

    /**
     * 消费失败，告知服务器稍后再投递这条消息，继续消费其他消息
     */
    ReconsumeLater;

}
