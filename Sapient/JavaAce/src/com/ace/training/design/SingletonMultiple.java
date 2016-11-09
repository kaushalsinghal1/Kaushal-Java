package com.ace.training.design;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SingletonMultiple {

	public static void main(String[] args) throws InterruptedException {
		testSingleton();
	}

	private static void testSingle() throws InterruptedException {
		int threadCount = 0;
		ExecutorService executorService = new ThreadPoolExecutor(threadCount,
				100000, 2, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>());
		final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

		for (int i = 0; i < threadCount; i++) {
			executorService.submit(new Runnable() {

				@Override
				public void run() {
					SingletonClass.getInstance();
					countDownLatch.countDown();
					System.out.println("Hi");

				}
			});
		}
		countDownLatch.await();
		try {
			Class<SingletonClass> class11 = SingletonClass.class;
			for (Constructor<?> ctr : class11.getDeclaredConstructors()) {
				System.out.println("Constructor");
				ctr.setAccessible(true);
				ctr.newInstance();
			}
			// class11.getConstructor().setAccessible(true);
			// SingletonClass class1 = class11.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SingletonClass.getInstance();
		System.out.println("Object Count " + SingletonClass.count);
		executorService.shutdown();
	}

	public static void testSingleton() {
		ExecutorService exc = Executors.newCachedThreadPool();
		final Map<Integer, Integer> map =  Collections
				.synchronizedMap(new HashMap<Integer, Integer>());

		for (int i = 0; i < 1000; i++) {
			exc.submit(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
							Thread.sleep(Math.round(10));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						map.put(EagerInitializedSingleton.getInstance()
								.hashCode(), 1);
						// EagerInitializedSingleton.getInstance());
						if (map.size() != 1) {
							System.out.println("error");
						}
					}
				}
			});

		}

		exc.shutdown();
		try {
			exc.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
