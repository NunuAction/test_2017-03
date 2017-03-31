package com.duotin.common;

/**
 * @author lrh
 * @create 2015年3月24日上午12:08:12
 */
public enum SuccessCode implements EnumCode {
        success("0");
        
        private final String code;
        
        private SuccessCode(String code) {
                this.code = code;
        }
        @Override
        public String code() {
                return this.code;
        }

}
