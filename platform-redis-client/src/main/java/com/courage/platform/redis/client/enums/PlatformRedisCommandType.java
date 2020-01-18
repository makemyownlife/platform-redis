package com.courage.platform.redis.client.enums;

public enum PlatformRedisCommandType {

    //common 命令
    DEL,
    TTL,
    KEYS,
    EXISTS,
    TYPE,
    EXPIRE,
    EXPIREAT,
    PEXPIRE,
    PEXPIREAT,

    //str 命令
    INCR, INCRBY, DECR, DECRBY,
    INCR_EX, INCRBY_EX, DECR_EX, DECRBY_EX,
    GET, SET, SET_EX, SET_NX, SET_NX_EX, GET_SET,

    //list 命令
    LRANGE, LPUSH, LLEN, LTRIM, RPOP, LPOP, RPUSH, BLPOP, BRPOP,

    //hash 命令
    HKEYS, HDEL, HEXISTS, HGET, HSET, HSET_EX, HINCRBY, HMGET,


    //set命令
    SADD, SCARD, SMOVE, SPOP, SISMEMBER, SMEMBERS,


    //zset命令
    ZADD, ZRANGE, ZREM, ZINCRBY, ZRANK, ZREVRANK, ZREVRANGE, ZREVRANGEBYSCORE, ZRANGEBYSCORE, ZCARD, ZSCORE,
    ZREMRANGEBYSCORE,;
}
