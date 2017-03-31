package com.duotin.distributedlock;

import java.util.concurrent.TimeUnit;

/**
 * Created by qiguo on 16/9/28.上午11:04
 * <p/>
 * for:
 */
public class ZookeeperDistributeLock implements DistributeLock {


    @Override
    public void lock(String locKey) {

    }

    @Override
    public boolean tryLock(String locKey) {
        return false;
    }

    @Override
    public boolean tryLock(String locKey, long time, TimeUnit unit) {
        return false;
    }

    @Override
    public void unlock(String locKey) {

    }
}
