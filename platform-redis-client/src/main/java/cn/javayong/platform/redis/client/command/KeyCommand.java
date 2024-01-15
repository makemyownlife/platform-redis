package cn.javayong.platform.redis.client.command;

public interface KeyCommand {

    boolean del(String key);

    boolean exists(String key);

    boolean expire(String key, int seconds);

}
