package com.courage.platform.redis.client.queue;

public interface RedisMessageListener {

    //消费消息
    RedisConsumeAction onMessage(RedisMessage redisMessage);

}
