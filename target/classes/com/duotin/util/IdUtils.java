package com.duotin.util;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author jared
 * 
 * @Description:id生成工具
 * 
 * @date Nov 5, 2014 6:14:29 PM
 * 
 */
public class IdUtils {
	private static final int BASE = 100000;
	private static Random RANDOM = new Random(System.currentTimeMillis());
	private static AtomicInteger serial = new AtomicInteger(BASE);

	/**
	 * 根据当前时间生成随机id
	 * 
	 * @return long类型id
	 */
	public static long make() {
		return timeToId(System.currentTimeMillis());
	}

	/**
	 * 通过时间生成随机id
	 * 
	 * @param timeInMillis
	 * @return
	 */
	public static long timeToId(long timeInMillis) {
		if (serial.intValue() > 9900 * BASE) {
			serial.set(BASE);
		}
		return timeInMillis / 1000 * 1000000000 + serial.getAndAdd(BASE) + RANDOM.nextInt(BASE);
	}
}
