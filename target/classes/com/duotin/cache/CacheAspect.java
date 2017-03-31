package com.duotin.cache;

import com.duotin.page.beans.Page;
import com.duotin.redis.RedisClient;
import org.apache.commons.beanutils.MethodUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by apple on 16/4/23.
 */

@Component
@Aspect
public class CacheAspect {

    @Autowired
    RedisClient redisClient;

    @Pointcut("@annotation(com.duotin.cache.Cache)")
    public void cacheCheck() {
    }

    @Around(value = "cacheCheck() && @annotation(cache)")
    public Object doAround(ProceedingJoinPoint joinPoint, Cache cache) {
        try {
            //这个获取到的是接口的方法
            Method interfaceMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();
            Object targetClass = joinPoint.getTarget();
            //获取实现的方法，以便注解是写在实现上的
            Method implMethod = MethodUtils.getAccessibleMethod(targetClass.getClass(),
                    interfaceMethod.getName(),interfaceMethod.getParameterTypes());
            Annotation[][] annotations = implMethod.getParameterAnnotations();

            Object[] args = joinPoint.getArgs();
            String cacheKey = cache.cacheKey();
            if (args!=null&&args.length>0&&annotations != null) {
                for (int i = 0; i < args.length; i++) {
                    if(annotations[i].length>0){
                        Annotation annotation = annotations[i][0];
                        if( annotation instanceof CacheParam){
                            CacheParam cacheParam=(CacheParam)annotation;

                            //分页参数单独处理
                            String replacecValue ="";
                            if("page".equals(cacheParam.value())){
                                Page page=(Page)args[i];
                                replacecValue=page.getStartIndex()+":"+page.getEndIndex()+":"+String.valueOf(page.getSortType());
                            }else {
                                replacecValue = String.valueOf(args[i]);
                            }
                            cacheKey=cacheKey.replace("{"+cacheParam.value()+"}",replacecValue +"");
                        }
                    }
                }
            }

            if(cacheKey.contains("{")||cacheKey.contains("}")){
                throw new Exception("缓存参数不合法");
            }

            if(cache.timeout()==0){
                Object o=joinPoint.proceed();
                redisClient.del(cacheKey);
                return o;
            }else {
                Object cacheValue = redisClient.get(cacheKey);
                if (cacheValue == null) {
                    Object o = joinPoint.proceed();
                    redisClient.set(cacheKey, o, cache.timeout());
                    return o;
                }
                return cacheValue;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

}
