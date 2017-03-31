package com.duotin.util;

import java.io.Closeable;

/**
 * 
 * @author jared
 * 
 * @Description: 对象终结工具类
 * 
 * @date Nov 5, 2014 2:38:16 PM
 * 
 */
public final class CloseUtils {

	/**
	 * 继承Closeable的对象执行关闭操作
	 * 
	 * @param closeable
	 */
	public static void closeQuiet(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (Throwable e) {
			}
		}
	}
}
