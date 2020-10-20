package com.courage.platform.redis.client.config;

/**
 * 哨兵模式
 * Created by zhangyong on 2020/6/1.
 */
public class PlatformSentinelServersConfig {

    private String nodes;

    private String master;

    private int database = 0;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

}
