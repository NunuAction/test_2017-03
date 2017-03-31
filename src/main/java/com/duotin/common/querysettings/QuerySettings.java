package com.duotin.common.querysettings;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Author: wangyongxing(wangyongxing@duotin.com)
 * Date: 15-7-28 上午10:58
 *
 * @Description:
 */
public final class QuerySettings {

    public static final Map<String,String> QUERY_SETTINGS = new LinkedHashMap<String, String>();
    static {
        QUERY_SETTINGS.put("title","find");

    }
}
