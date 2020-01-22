package com.courage.platform.redis.client.springboot.starter.configuration.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by zhangyong on 2020/1/22.
 */
@ConfigurationProperties(prefix = "platform.redis.singleserver")
public class PlatformSingleServerConfig {

    /**
     * Redis server address
     */
    private String address;

    /**
     * Minimum idle subscription connection amount
     */
    private int subscriptionConnectionMinimumIdleSize = 1;

    /**
     * Redis subscription connection maximum pool size
     */
    private int subscriptionConnectionPoolSize = 50;

    /**
     * Minimum idle Redis connection amount
     */
    private int connectionMinimumIdleSize = 24;

    /**
     * Redis connection maximum pool size
     */
    private int connectionPoolSize = 64;

    /**
     * Database index used for Redis connection
     */
    private int database = 0;

    /**
     * Interval in milliseconds to check DNS
     */
    private long dnsMonitoringInterval = 5000;

    public int getConnectionPoolSize() {
        return connectionPoolSize;
    }

    public int getSubscriptionConnectionPoolSize() {
        return subscriptionConnectionPoolSize;
    }

    public String getAddress() {
        return address;
    }


    public long getDnsMonitoringInterval() {
        return dnsMonitoringInterval;
    }


    public int getSubscriptionConnectionMinimumIdleSize() {
        return subscriptionConnectionMinimumIdleSize;
    }


    public int getConnectionMinimumIdleSize() {
        return connectionMinimumIdleSize;
    }

    public int getDatabase() {
        return database;
    }


}
