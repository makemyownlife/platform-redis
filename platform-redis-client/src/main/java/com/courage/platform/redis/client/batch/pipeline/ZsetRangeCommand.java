package com.courage.platform.redis.client.batch.pipeline;

import com.courage.platform.redis.client.batch.BatchContext;
import org.redisson.api.RBatch;
import org.redisson.codec.JsonJacksonCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZsetRangeCommand implements PiplelineCommand {

    private static final Logger logger = LoggerFactory.getLogger(ZsetRangeCommand.class);

    private static JsonJacksonCodec hashCodec = JsonJacksonCodec.INSTANCE;

    private String key;

    private int start;

    private int end;

    public ZsetRangeCommand(String key, int start, int end) {
        this.key = key;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute() {
        RBatch rBatch = BatchContext.get();
        rBatch.getScoredSortedSet(key, hashCodec).valueRangeAsync(start, end);
    }

}
