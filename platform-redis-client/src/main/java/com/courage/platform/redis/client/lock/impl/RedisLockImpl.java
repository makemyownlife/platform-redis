package com.courage.platform.redis.client.lock.impl;

import com.courage.platform.redis.client.lock.RedisLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public class RedisLockImpl extends AbstractLock implements RedisLock {

    private final static Logger logger = LoggerFactory.getLogger(RedisLockImpl.class);

    private RLock rLock;

    public RedisLockImpl(RedissonClient redissonClient, String name) {
        super(redissonClient, name);
        this.rLock = redissonClient.getLock(name);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void lockInterruptibly(long leaseTime, TimeUnit unit) throws InterruptedException {
        rLock.lockInterruptibly(leaseTime, unit);
    }

    @Override
    public boolean tryLock(long waitTime, long leaseTime, TimeUnit unit) throws InterruptedException {
        return rLock.tryLock(waitTime, leaseTime, unit);
    }

    @Override
    public void lock(long leaseTime, TimeUnit unit) {
        rLock.lock(leaseTime, unit);
    }

    @Override
    public boolean forceUnlock() {
        return rLock.forceUnlock();
    }

    @Override
    public boolean isLocked() {
        return rLock.isLocked();
    }

    @Override
    public boolean isHeldByThread(long threadId) {
        return rLock.isHeldByThread(threadId);
    }

    @Override
    public boolean isHeldByCurrentThread() {
        return rLock.isHeldByCurrentThread();
    }

    @Override
    public int getHoldCount() {
        return rLock.getHoldCount();
    }

    @Override
    public long remainTimeToLive() {
        return rLock.remainTimeToLive();
    }

    @Override
    public void lock() {
        rLock.lock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        rLock.lockInterruptibly();
    }

    @Override
    public boolean tryLock() {
        return rLock.tryLock();
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return rLock.tryLock(time, unit);
    }

    @Override
    public void unlock() {
        rLock.unlock();
    }

    @Override
    public Condition newCondition() {
        return rLock.newCondition();
    }

}
