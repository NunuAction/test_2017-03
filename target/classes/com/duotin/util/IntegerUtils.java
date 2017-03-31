package com.duotin.util;

/**
 * 
 * @author jared
 * 
 * @Description:
 * 
 * @date Jan 22, 2015 6:10:30 PM
 * 
 */
public final class IntegerUtils {

	/**
	 * Integer对象判空
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNotEmpty(Integer value) {
		if (value != null && value > 0) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(Integer value) {
		if (value == null || value == 0) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * 字符转数字
	 * 
	 * @param str
	 * @return
	 */
	public static int toInteger(String str) {
		if(StringUtils.isNotEmpty(str)) {
			if(NumberUtils.isNumber(str)) {
				return Integer.valueOf(str);
			}
		}
		return 0;
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static int valueOf(Integer value) {
		if(value != null) {
			return value;
		}
		return 0;
	}
}
