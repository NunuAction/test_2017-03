package com.duotin.cache;

import java.lang.annotation.*;

/**
 * Created by apple on 16/4/23.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {

    String cacheKey() default "default";

    /**
     * 过期时间,单位 秒
     * @return
     */
    int timeout() default 10*60;

}
