package com.duotin.api.beans;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: yaojiafeng
 * Date: 15/10/30
 * Time: 下午2:55
 */
public class RecommendType {
    public static final Set<String> URL_TYPE = new HashSet<>();
    public static final String URL_TYPE_S = "URL_TYPE";

    public static final Set<String> VALUE_TYPE = new HashSet<>();
    public static final String VALUE_TYPE_S = "VALUE_TYPE";

    public static final Set<String> NONE_TYPE = new HashSet<>();
    public static final String NONE_TYPE_S = "NONE_TYPE";

    static {
        URL_TYPE.addAll(Arrays.asList("4"));
        VALUE_TYPE.addAll(Arrays.asList("1", "2", "3", "5", "10", "12", "13", "15", "17", "20", "21"));
        NONE_TYPE.addAll(Arrays.asList("6", "7", "8", "9", "11", "16", "19"));
    }

    public static String getCheckType(String type) {
        if (URL_TYPE.contains(type)) {
            return URL_TYPE_S;
        } else if (VALUE_TYPE.contains(type)) {
            return VALUE_TYPE_S;
        } else if (NONE_TYPE.contains(type)) {
            return NONE_TYPE_S;
        }
        return null;
    }

}
