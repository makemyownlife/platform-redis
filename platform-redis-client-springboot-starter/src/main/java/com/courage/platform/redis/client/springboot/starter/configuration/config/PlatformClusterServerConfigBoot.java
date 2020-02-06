package com.courage.platform.redis.client.springboot.starter.configuration.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * redis 集群服务
 * Created by zhangyong on 2020/1/22.
 */
@ConfigurationProperties(prefix = "platform.redis.clusterserver")
public class PlatformClusterServerConfigBoot {

    private List<String> nodes;

    public List<String> getNodes() {
        return nodes;
    }

    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }

}
