package com.duotin;

import com.duotin.ComponentException.ErrCode;
import org.apache.commons.lang.StringUtils;

import java.lang.Boolean;import java.lang.Object;import java.lang.String;import java.util.Collection;

/**
 * @author lrh
 * @create 2015年3月23日下午3:35:29
 */
public class Assert {
         
        public static void notEmpty(String val,String message){
                if(val == null || StringUtils.isBlank(val)){
                        throwException(message);
                }
        }
        
        public static void notNull(Object val,String message ){
                if(val == null){
                        throwException(message);
                        throwException(message);
                }
        }
        
        public static void notEmpty(Collection val,String message){
                if(val == null || val.size() == 0){
                        throwException(message);
                }
        }
        
        public static void isTrue(Boolean val,String message){
                if(val == null || !val){
                        throwException(message);
                }
        }
        
        private static void throwException(ErrCode errCode,String message){
                throw new ComponentException(errCode,message);
        }
        
        public static void throwException(String message){
                throwException(ErrCode.参数非法,message);
        }

		public static void isStringLengthBetween(String str, int i, int j) {
			if(str==null||str.length()<i||str.length()>j){
				throwException("参数长度不合法");
			}
		}

		public static void isArrayLengthBetween(String[] arr, int i, int j) {
			if(arr==null||arr.length<i||arr.length>j){
				Assert.throwException("数组长度不合法");
			}
		}
        
        
        
}
