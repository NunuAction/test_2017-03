package com.duotin.distributedlock;

import java.util.concurrent.TimeUnit;

/**
 * Created by qiguo on 16/9/28.上午10:42
 * <p/>
 * for:分布式锁
 */
public interface DistributeLock {

    /**
     * 同步获取锁
     * @param locKey
     */
    void lock(String locKey);

    /**
     * 异步获取锁，
     * @return
     */
    boolean tryLock(String locKey);

    /**
     * 在指定的时间获取锁
     * @param locKey
     * @param time
     * @param unit
     * @return
     */
    boolean tryLock(String locKey,long time, TimeUnit unit);

    /**
     * 释放锁
     */
    void unlock(String locKey);



}
