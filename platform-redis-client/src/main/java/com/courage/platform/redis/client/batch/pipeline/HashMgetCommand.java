package com.courage.platform.redis.client.batch.pipeline;

import com.courage.platform.redis.client.batch.BatchContext;
import org.redisson.api.RBatch;
import org.redisson.codec.JsonJacksonCodec;

import java.util.Set;

public class HashMgetCommand implements PiplelineCommand {

    private static JsonJacksonCodec hashCodec = JsonJacksonCodec.INSTANCE;

    private String key;

    private Set<Object> fields;

    public HashMgetCommand(String key, Set<Object> fields) {
        this.key = key;
        this.fields = fields;
    }

    @Override
    public void execute() {
        RBatch rBatch = BatchContext.get();
        rBatch.getMap(key, hashCodec).getAllAsync(fields);
    }

}
