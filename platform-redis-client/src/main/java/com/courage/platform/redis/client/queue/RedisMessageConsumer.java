package com.courage.platform.redis.client.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class RedisMessageConsumer {

    private final Logger logger = LoggerFactory.getLogger(RedisMessageConsumer.class);

    private Map<String, RedisMessageListener> subscribeTable = new HashMap<String, RedisMessageListener>();

    private AtomicBoolean started = new AtomicBoolean(false);

    private String consumerGroup;

    private RedisMessageQueueBuilder redisMessageQueueBuilder;

    public RedisMessageConsumer(String consumerGroup, RedisMessageQueueBuilder redisMessageQueueBuilder) {
        this.consumerGroup = consumerGroup;
        this.redisMessageQueueBuilder = redisMessageQueueBuilder;
    }

    public void start() {
        if (this.started.compareAndSet(false, true)) {
            long start = System.currentTimeMillis();
            logger.info("开始启动 redis 消息者, consumerGroup:" + consumerGroup);
            Set<String> set = this.subscribeTable.keySet();
            for (String queueName : set) {
                consumeQueue(queueName);
            }
            logger.info("结束启动 redis 消息者, consumerGroup:" + consumerGroup + " 耗时:" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    public void subscribe(String queueName, RedisMessageListener redisMessageListener) {
        subscribeTable.put(queueName, redisMessageListener);
    }

    private void consumeQueue(String queueName) {
        //多线程消费消息
        Thread consumeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (started.get()) {
                    try {
                        BlockingQueue queue = redisMessageQueueBuilder.createBlockingQueue(queueName);
                        Object element = queue.poll(10, TimeUnit.SECONDS);
                        if (element != null) {
                            RedisMessageListener redisMessageListener = subscribeTable.get(queueName);
                            RedisMessage redisMessage = new RedisMessage();
                            redisMessage.setData(element);
                            redisMessageListener.onMessage(redisMessage);
                        }
                    } catch (Throwable e) {
                        logger.error("consumeQueue poll error:", e);
                    }
                }
            }
        });
        String threadName = this.consumerGroup + ":" + queueName;
        consumeThread.setName(threadName);
        consumeThread.start();
        logger.info("redis 消费线程：" + threadName + " 启动成功");
    }

    public void shutdown() {
        started.compareAndSet(true, false);
    }

}
