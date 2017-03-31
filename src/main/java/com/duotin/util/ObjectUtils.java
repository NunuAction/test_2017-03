package com.duotin.util;

/**
 * 
 * @author jared
 * 
 * @Description:对象基本操作类
 * 
 * @date Nov 7, 2014 6:43:49 PM
 * 
 */
public final class ObjectUtils {

	/**
	 * 对象为空时赋默认值
	 * 
	 * @param value
	 *            目标对象
	 * @param def
	 *            默认值
	 * @return
	 */
	public static <T> T nullToDefault(T value, T def) {
		return value == null ? def : value;
	}
}
