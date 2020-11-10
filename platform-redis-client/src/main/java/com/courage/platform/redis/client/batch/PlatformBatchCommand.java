package com.courage.platform.redis.client.batch;

import com.courage.platform.redis.client.batch.pipeline.PiplelineCommand;
import com.courage.platform.redis.client.command.impl.PlatformKeyCommandImpl;
import org.redisson.api.BatchResult;
import org.redisson.api.RBatch;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PlatformBatchCommand extends PlatformKeyCommandImpl {

    private static Logger logger = LoggerFactory.getLogger(PlatformBatchCommand.class);

    public PlatformBatchCommand(RedissonClient redissonClient) {
        super(redissonClient);
    }

    public List<?> executePipelineCommands(List<PiplelineCommand> piplelineCommands) {
        try {
            //设置上下文
            RBatch rBatch = getRedissonClient().createBatch();
            BatchContext.set(rBatch);
            //循环执行命令
            for (PiplelineCommand piplelineCommand : piplelineCommands) {
                piplelineCommand.execute();
            }
            BatchResult<?> batchResult = rBatch.execute();
            List<?> list = batchResult.getResponses();
            return list;
        } finally {
            BatchContext.set(null);
        }
    }

}
