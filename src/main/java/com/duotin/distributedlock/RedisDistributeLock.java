package com.duotin.distributedlock;

import com.duotin.redis.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * Created by qiguo on 16/9/28.上午10:39
 * <p/>
 * for:
 */
@Service
public class RedisDistributeLock implements DistributeLock {

    @Autowired
    RedisClient redisClient;

    ExecutorService executor = Executors.newCachedThreadPool();


    private static final Logger LOGGER = LoggerFactory.getLogger("distributedlock");


    @Override
    public void lock(String locKey) {
        redisClient.getDistributedLock(locKey);
    }

    @Override
    public boolean tryLock(String locKey) {
        return redisClient.tryGetDistributedLock(locKey);
    }

    @Override
    public boolean tryLock(String locKey, long time, TimeUnit unit) {
        GetLockTask task=new GetLockTask(redisClient,locKey);
        FutureTask<Boolean> futureTask = new FutureTask<Boolean>(task);
        executor.submit(futureTask);
        try {
            futureTask.get(time, unit);
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(),e);
        } catch (ExecutionException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        } catch (TimeoutException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            //未获取到锁
            Boolean cancelResult = futureTask.cancel(true);
            if (cancelResult) {
                //取消任务成功,表示获取锁失败
                return false;
            }else {
                //取消锁失败，说明任务执行完成，即获取锁成功
                return true;
            }
        }
        return false;
    }

    @Override
    public void unlock(String locKey) {
        redisClient.releaseDistributedLock(locKey);
    }
}

/**
 * 获取锁线程
 */
class GetLockTask implements Callable<Boolean> {

    private RedisClient redisClient;

    private String lockKey;

    public GetLockTask(RedisClient redisClient,String lockKey){
        this.redisClient=redisClient;
        this.lockKey=lockKey;
    }

    @Override
    public Boolean call() throws Exception {
        redisClient.getDistributedLock(lockKey);
        return true;
    }
}

