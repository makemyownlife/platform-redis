package com.courage.platform.redis.client.queue;

import java.io.Serializable;

public class RedisMessage<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private T data;

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

}
