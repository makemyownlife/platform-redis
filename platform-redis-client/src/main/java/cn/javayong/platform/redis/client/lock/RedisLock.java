package cn.javayong.platform.redis.client.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public interface RedisLock extends Lock {

    /**
     * Returns name of object
     *
     * @return name - name of object
     */
    String getName();

    /**
     * Acquires the lock.
     *
     * <p>If the lock is not available then the current thread becomes
     * disabled for thread scheduling purposes and lies dormant until the
     * lock has been acquired.
     * <p>
     * If the lock is acquired, it is held until <code>unlock</code> is invoked,
     * or until leaseTime have passed
     * since the lock was granted - whichever comes first.
     *
     * @param leaseTime the maximum time to hold the lock after granting it,
     *                  before automatically releasing it if it hasn't already been released by invoking <code>unlock</code>.
     *                  If leaseTime is -1, hold the lock until explicitly unlocked.
     * @param unit      the time unit of the {@code leaseTime} argument
     * @throws InterruptedException - if the thread is interrupted before or during this method.
     */
    void lockInterruptibly(long leaseTime, TimeUnit unit) throws InterruptedException;

    /**
     * Returns <code>true</code> as soon as the lock is acquired.
     * If the lock is currently held by another thread in this or any
     * other process in the distributed system this method keeps trying
     * to acquire the lock for up to <code>waitTime</code> before
     * giving up and returning <code>false</code>. If the lock is acquired,
     * it is held until <code>unlock</code> is invoked, or until <code>leaseTime</code>
     * have passed since the lock was granted - whichever comes first.
     *
     * @param waitTime  the maximum time to aquire the lock
     * @param leaseTime lease time
     * @param unit      time unit
     * @return <code>true</code> if lock has been successfully acquired
     * @throws InterruptedException - if the thread is interrupted before or during this method.
     */
    boolean tryLock(long waitTime, long leaseTime, TimeUnit unit) throws InterruptedException;

    /**
     * Acquires the lock.
     *
     * <p>If the lock is not available then the current thread becomes
     * disabled for thread scheduling purposes and lies dormant until the
     * lock has been acquired.
     * <p>
     * If the lock is acquired, it is held until <code>unlock</code> is invoked,
     * or until leaseTime milliseconds have passed
     * since the lock was granted - whichever comes first.
     *
     * @param leaseTime the maximum time to hold the lock after granting it,
     *                  before automatically releasing it if it hasn't already been released by invoking <code>unlock</code>.
     *                  If leaseTime is -1, hold the lock until explicitly unlocked.
     * @param unit      the time unit of the {@code leaseTime} argument
     */
    void lock(long leaseTime, TimeUnit unit);

    /**
     * Unlocks lock independently of state
     *
     * @return <code>true</code> if lock existed and now unlocked otherwise <code>false</code>
     */
    boolean forceUnlock();

    /**
     * Checks if this lock locked by any thread
     *
     * @return <code>true</code> if locked otherwise <code>false</code>
     */
    boolean isLocked();

    /**
     * Checks if this lock is held by the current thread
     *
     * @param threadId Thread ID of locking thread
     * @return <code>true</code> if held by given thread
     * otherwise <code>false</code>
     */
    boolean isHeldByThread(long threadId);

    /**
     * Checks if this lock is held by the current thread
     *
     * @return <code>true</code> if held by current thread
     * otherwise <code>false</code>
     */
    boolean isHeldByCurrentThread();

    /**
     * Number of holds on this lock by the current thread
     *
     * @return holds or <code>0</code> if this lock is not held by current thread
     */
    int getHoldCount();

    /**
     * Remaining time to live of this lock
     *
     * @return time in milliseconds
     * -2 if the lock does not exist.
     * -1 if the lock exists but has no associated expire.
     */
    long remainTimeToLive();

}
