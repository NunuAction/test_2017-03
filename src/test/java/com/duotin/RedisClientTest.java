package com.duotin;

import com.duotin.redis.RedisClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class User implements Serializable{

    private String username;
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return username+password;
    }
}

/**
 * Created by ganlv on 4/3/16.
 */
@ContextConfiguration({"classpath:applicationContext-test.xml"})
public class RedisClientTest extends AbstractJUnit4SpringContextTests{


    @Autowired
    public  RedisClient redisClient;

    @Test
    public void testRedis() {

        User user = new User();
        user.setPassword("a");
        user.setUsername("v");

        redisClient.set("GANLV_REDIS_TEST",user);
        User getUser  = redisClient.get("GANLV_REDIS_TEST");

        System.out.println(getUser.toString());
    }


    private static final Integer MAX_COUNT = 1;

    private static final CountDownLatch countDownLatch = new CountDownLatch(MAX_COUNT);


    @Test
    public void testRedisInteger(){
        Integer count = 0;
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        final long start = System.currentTimeMillis();
        final ConcurrentHashMap<Long,Integer> set = new ConcurrentHashMap();
        final String KEY = "user_listen_history86";
        Long lastValue = redisClient.getNumber(KEY);
        System.out.println(lastValue);

        redisClient.setNumber(KEY, 100L);

        while (count < MAX_COUNT) {
            count++;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Long nextValue = redisClient.incrBy(KEY,1L);
                        set.put(nextValue,1);
                        System.out.println(nextValue);
                    } catch (Exception e) {
                        System.out.println("ERROR ON ID GENERATE:");
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        try{
            countDownLatch.await();
        }catch (Exception e){}

        lastValue = redisClient.getNumber(KEY);
        System.out.println(lastValue);
        System.out.println("Cost Time:" + (System.currentTimeMillis() - start));
    }

}
