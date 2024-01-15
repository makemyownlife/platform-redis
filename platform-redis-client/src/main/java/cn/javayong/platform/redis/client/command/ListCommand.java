package cn.javayong.platform.redis.client.command;

import java.util.List;

public interface ListCommand {

    Boolean lpush(final String key, final Object o);

    <T> List<T> lrange(final String key, final int startNum, final int endNum);

    void ltrim(final String key, final int startNum, final int endNum);

    Integer llen(final String key);

    <T> T rpop(final String key);

    <T> T lpop(final String key);

    boolean rpush(final String key, final Object o);

}
