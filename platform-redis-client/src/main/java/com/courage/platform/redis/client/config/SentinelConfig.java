package com.courage.platform.redis.client.config;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 哨兵模式
 * Created by zhangyong on 2020/6/1.
 */
public class SentinelConfig {

    private List<String> nodes;

    private String master;

    private int database = 0;

    private String password = StringUtils.EMPTY;

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

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public List<String> getNodes() {
        return nodes;
    }

    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }

}
