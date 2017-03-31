package com.duotin.common;

/**
 * @author lrh
 * @create 2014年12月23日下午4:18:29
 */
public enum DelStatus implements EnumCode {
    未删除("0"), 已删除("1");

    private final String code;

    private DelStatus(String code) {
        this.code = code;
    }

    @Override
    public String code() {
        return this.code;
    }


    public static DelStatus getEnum(Boolean bool) {
        return bool ? DelStatus.已删除 : DelStatus.未删除;
    }

}
