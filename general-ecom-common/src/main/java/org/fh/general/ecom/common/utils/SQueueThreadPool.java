package org.fh.general.ecom.common.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SQueueThreadPool {

	private static int size = 10;
	private static ScheduledExecutorService scheduledPool;

	static {
		synchronized (SQueueThreadPool.class) {
			if (scheduledPool == null) {
				scheduledPool = Executors.newScheduledThreadPool(size, Executors.privilegedThreadFactory());
			}
		}
	}

	/**
	 * 提交一次性任务
	 * 
	 * @param task
	 *            任务对象
	 * @param time
	 *            延迟时间
	 * @param unit
	 *            时间单位
	 * @return
	 */
	public static boolean addOneTimeTask(Runnable task, long time, TimeUnit unit) {
		try {
			scheduledPool.schedule(task, time, unit);
		} catch (RejectedExecutionException e) {

			return false;
		}
		return true;
	}

	/**
	 * 立刻让线程池执行
	 * 
	 * @param runnable
	 * @return null
	 */
	public static void execute(Runnable runnable) {
		scheduledPool.execute(runnable);
	}

	/**
	 * 提交定期执行任务
	 * 
	 * @param task
	 *            执行任务对象
	 * @param delay
	 *            首次执行的延迟
	 * @param period
	 *            定期执行的间隔
	 * @param unit
	 *            时间单位
	 * @return
	 */
	public static boolean addScheduledTask(Runnable task, long delay, long period, TimeUnit unit) {
		try {
			scheduledPool.scheduleWithFixedDelay(task, delay, period, unit);
		} catch (RejectedExecutionException e) {
			return false;
		}
		return true;
	}

}
