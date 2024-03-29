package cn.javayong.platform.redis.client.command;

public interface AtomicCommand extends KeyCommand {

    Long incr(String key);

    Long incrBy(String key, long incValue);

    Long decr(String key);

    Long decrBy(String key, long incValue);

    Long incrEx(String key, int seconds);

    Long incrByEx(String key, long incValue, int seconds);

    Long decrEx(String key, int seconds);

    Long decrByEx(String key, long incValue, int seconds);

}
