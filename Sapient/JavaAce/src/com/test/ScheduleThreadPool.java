package com.test;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduleThreadPool {

	public static void main(String[] args) {
		ScheduledThreadPoolExecutor service = new ScheduledThreadPoolExecutor(2);
		service.scheduleAtFixedRate(new ScheduleThread("name1"), 1, 2, TimeUnit.SECONDS);
		service.scheduleAtFixedRate(new ScheduleThread("name2"), 1, 2, TimeUnit.SECONDS);
		service.scheduleAtFixedRate(new ScheduleThread("name3"), 1, 2, TimeUnit.SECONDS);
//		Executors.n

	}

	static class ScheduleThread implements Runnable {
		String name;

		public ScheduleThread(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()+" Runnnint Thread.." + name);
		}
	}

}
