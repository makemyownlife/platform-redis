package com.courage.platform.redis.client.config;

import java.util.List;

/**
 * redis 集群服务
 * Created by zhangyong on 2020/1/22.
 */
public class PlatformClusterServerConfig {

    private List<String> nodes;

    public List<String> getNodes() {
        return nodes;
    }

    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }
    
}
