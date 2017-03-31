package com.duotin.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 
 * @author jared
 * 
 * @Description: 线程池工具类
 * 
 * @date Nov 5, 2014 2:39:52 PM
 * 
 */
public final class ConcurrentUtils {

	private static final ExecutorService THREAD_POOL = new ScheduledThreadPoolExecutor(Runtime.getRuntime()
			.availableProcessors() * 4);

	private static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool(10);

	/**
	 * 通过线程池分配线程执行任务
	 * 
	 * @param runnable
	 *            待执行任务
	 */
	public static void execute(Runnable runnable) {
		THREAD_POOL.execute(runnable);
	}

	/**
	 * fork-join线程池执行任务
	 * 
	 * @param task
	 *            待执行任务
	 */
	public static void forkjoinExecute(ForkJoinTask<?> task) {
		FORK_JOIN_POOL.execute(task);
	}
}
