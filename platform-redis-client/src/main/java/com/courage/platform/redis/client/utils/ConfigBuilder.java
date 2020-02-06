package com.courage.platform.redis.client.utils;

import com.courage.platform.redis.client.config.PlatformClusterServerConfig;
import com.courage.platform.redis.client.config.PlatformSingleServerConfig;
import org.redisson.config.Config;

/**
 * Created by zhangyong on 2020/2/6.
 */
public class ConfigBuilder {

    public static Config buildBySingleServerConfig(PlatformSingleServerConfig singleServerConfig) {
        Config config = new Config();
        config.useSingleServer().setAddress(singleServerConfig.getAddress());
        if (singleServerConfig.getPassword() != null) {
            config.useSingleServer().setPassword(singleServerConfig.getPassword());
        }
        return config;
    }

    public static Config buildByClusterServerConfig(PlatformClusterServerConfig clusterServersConfig) {
        Config config = new Config();
        for (String node : clusterServersConfig.getNodes()) {
            config.useClusterServers().addNodeAddress(node);
        }
        return config;
    }

}
