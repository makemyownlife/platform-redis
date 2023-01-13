package com.courage.platform.redis.client.utils;

import com.courage.platform.redis.client.config.ClusterConfig;
import com.courage.platform.redis.client.config.SentinelConfig;
import com.courage.platform.redis.client.config.SingleConfig;
import org.apache.commons.lang3.StringUtils;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.redisson.config.SingleServerConfig;

import java.util.List;

/**
 * Created by zhangyong on 2020/2/6.
 */
public class ConfigBuilder {

    public static Config buildBySingleServerConfig(SingleConfig singleConfig) {
        Config config = new Config();
        String address = singleConfig.getAddress();
        config.useSingleServer().setAddress(address.startsWith("redis://") ? address : "redis://" + address);
        if (singleConfig.getPassword() != null) {
            config.useSingleServer().setPassword(singleConfig.getPassword());
        }
        config.useSingleServer().setDatabase(singleConfig.getDatabase());
        return config;
    }

    public static Config buildByClusterServerConfig(ClusterConfig clusterServersConfig) {
        Config config = new Config();
        for (String node : clusterServersConfig.getNodes()) {
            config.useClusterServers().addNodeAddress(node.startsWith("redis://") ? node : "redis://" + node);
        }
        return config;
    }

    public static Config buildBySentinelServerConfig(SentinelConfig platformSentinelConfig) {
        Config config = new Config();
        List<String> nodes = platformSentinelConfig.getNodes();
        String[] array = new String[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            String item = nodes.get(i);
            array[i] = item.startsWith("redis://") ? item : "redis://" + item;
        }
        config.useSentinelServers().setReadMode(ReadMode.MASTER);
        config.useSentinelServers().addSentinelAddress(array).setDatabase(platformSentinelConfig.getDatabase()).setMasterName(platformSentinelConfig.getMaster());
        if (StringUtils.isNotBlank(platformSentinelConfig.getPassword())) {
            config.useSentinelServers().setPassword(platformSentinelConfig.getPassword());
        }
        config.useSentinelServers().setDatabase(platformSentinelConfig.getDatabase());
        return config;
    }

}
