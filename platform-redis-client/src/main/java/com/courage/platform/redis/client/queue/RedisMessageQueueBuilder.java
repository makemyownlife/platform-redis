package com.courage.platform.redis.client.queue;

import org.redisson.api.RedissonClient;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public class RedisMessageQueueBuilder {

    private RedissonClient redissonClient;

    public RedisMessageQueueBuilder(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public Queue createQueue(String name) {
        return redissonClient.getQueue(name);
    }

    public BlockingQueue createBlockingQueue(String name) {
        return redissonClient.getBlockingQueue(name);
    }

}
