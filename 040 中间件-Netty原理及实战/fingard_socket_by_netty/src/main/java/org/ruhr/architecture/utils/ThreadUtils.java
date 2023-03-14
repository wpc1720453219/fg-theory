package org.ruhr.architecture.utils;

public class ThreadUtils {

	@SuppressWarnings("static-access")
	public static void sleepMillis(long millis) {

		// 睡眠参数提供的毫秒数
		try {
			Thread.currentThread().sleep(millis);
		} catch (Throwable e) {

		}

	}

}
