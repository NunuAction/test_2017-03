package com.duotin.cache;

import java.lang.annotation.*;

/**
 * Created by apple on 16/4/23.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheParam {

    /**
     * 参数位置
     * @return
     */
    String value();
}
