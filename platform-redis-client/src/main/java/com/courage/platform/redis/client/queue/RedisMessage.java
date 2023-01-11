package com.courage.platform.redis.client.queue;

import java.io.Serializable;

public class RedisMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Object data;

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

}
