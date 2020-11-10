package com.courage.platform.redis.client.batch;

import org.redisson.api.RBatch;

public class BatchContext {

    private static ThreadLocal<RBatch> threadLocal = new ThreadLocal<RBatch>();

    public static RBatch get() {
        return threadLocal.get();
    }

    public static void set(RBatch rBatch) {
        threadLocal.set(rBatch);
    }

}
