package org.ruhr.architecture.socket.server.threadpool;

import java.util.concurrent.BlockingQueue;

import java.util.concurrent.LinkedBlockingQueue;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadPoolManager4User {

	public static void start() {
		// 用来启动static部分
	}

	// 每种线程池通用配置
	private static int MULTI = 3;
	private static final int THE_SAME_THREAD_COUNT = Math.max(MULTI * Runtime.getRuntime().availableProcessors(), 32);

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * ***************************************定义用户级别线程池
	 * 
	 */
	private static int USER_BUSINESS_TASK_QUEUE_SIZE = 1000 * 10000;
	private static BlockingQueue<Runnable> USER_BUSINESS_TASK_QUEUE = new LinkedBlockingQueue<Runnable>(
			USER_BUSINESS_TASK_QUEUE_SIZE);
	private static final ThreadPoolExecutor USER_BUSINESS_TASK_EXECUTOR = new ThreadPoolExecutor(//
			THE_SAME_THREAD_COUNT, //
			THE_SAME_THREAD_COUNT, //
			0L, TimeUnit.MILLISECONDS, USER_BUSINESS_TASK_QUEUE //
			, new DefaultThreadFactory("user_common_once_business", true)//
	);

	/**
	 * @apiNote 
	 * @param object
	 * @param method
	 * @param arg
	 * @param outputAware
	 * @param channel
	 * @throws Exception
	 */
	public static void submitOnceTask(Runnable runnable) throws Exception {
		
		USER_BUSINESS_TASK_EXECUTOR.submit(runnable);

	}


}
