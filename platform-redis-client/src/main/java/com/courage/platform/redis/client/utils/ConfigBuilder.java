package com.courage.platform.redis.client.utils;

import com.courage.platform.redis.client.config.PlatformClusterServerConfig;
import com.courage.platform.redis.client.config.PlatformSentinelServersConfig;
import com.courage.platform.redis.client.config.PlatformSingleServerConfig;
import org.apache.commons.lang3.StringUtils;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;

import java.util.ArrayList;
import java.util.List;

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

    public static Config buildBySentinelServerConfig(PlatformSentinelServersConfig platformSentinelServersConfig) {
        Config config = new Config();
        String[] arr = platformSentinelServersConfig.getNodes().split(",");
        List<String> newNodes = new ArrayList(arr.length);
        for (String item : arr) {
            newNodes.add(item.startsWith("redis://") ? item : "redis://" + item);
        }
        config.useSentinelServers().setReadMode(ReadMode.MASTER);
        config.useSentinelServers().addSentinelAddress(newNodes.toArray(new String[0])).setDatabase(platformSentinelServersConfig.getDatabase()).setMasterName(platformSentinelServersConfig.getMaster());
        if (StringUtils.isNotBlank(platformSentinelServersConfig.getPassword())) {
            config.useSentinelServers().setPassword(platformSentinelServersConfig.getPassword());
        }
        return config;
    }

}
