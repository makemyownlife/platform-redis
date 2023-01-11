package com.courage.platform.redis.client.utils;

import com.courage.platform.redis.client.config.ClusterServerConfig;
import com.courage.platform.redis.client.config.SentinelServersConfig;
import org.apache.commons.lang3.StringUtils;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.redisson.config.SingleServerConfig;

import java.util.List;

/**
 * Created by zhangyong on 2020/2/6.
 */
public class ConfigBuilder {

    public static Config buildBySingleServerConfig(SingleServerConfig singleServerConfig) {
        Config config = new Config();
        String address = singleServerConfig.getAddress();
        config.useSingleServer().setAddress(address.startsWith("redis://") ? address : "redis://" + address);
        if (singleServerConfig.getPassword() != null) {
            config.useSingleServer().setPassword(singleServerConfig.getPassword());
        }
        config.useSingleServer().setDatabase(singleServerConfig.getDatabase());
        return config;
    }

    public static Config buildByClusterServerConfig(ClusterServerConfig clusterServersConfig) {
        Config config = new Config();
        for (String node : clusterServersConfig.getNodes()) {
            config.useClusterServers().addNodeAddress(node.startsWith("redis://") ? node : "redis://" + node);
        }
        return config;
    }

    public static Config buildBySentinelServerConfig(SentinelServersConfig platformSentinelServersConfig) {
        Config config = new Config();
        List<String> nodes = platformSentinelServersConfig.getNodes();
        String[] array = new String[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            String item = nodes.get(i);
            array[i] = item.startsWith("redis://") ? item : "redis://" + item;
        }
        config.useSentinelServers().setReadMode(ReadMode.MASTER);
        config.useSentinelServers().addSentinelAddress(array).setDatabase(platformSentinelServersConfig.getDatabase()).setMasterName(platformSentinelServersConfig.getMaster());
        if (StringUtils.isNotBlank(platformSentinelServersConfig.getPassword())) {
            config.useSentinelServers().setPassword(platformSentinelServersConfig.getPassword());
        }
        config.useSentinelServers().setDatabase(platformSentinelServersConfig.getDatabase());
        return config;
    }

}
