package com.ace.training.usecase;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

public class LinkedBlockingQueueImplTest {
	static LinkedBlockingQueueImpl<String> queueImpl;

	@BeforeClass
	public static void beforeClassInit() {
	}

	@Test
	public void testElemetPushAndTake() throws InterruptedException {
		queueImpl = new LinkedBlockingQueueImpl<>(10);
		String first = "Hello";
		queueImpl.put(first);
		Assert.assertEquals(1, queueImpl.size());
		Assert.assertEquals(first, queueImpl.take());
		Assert.assertEquals(0, queueImpl.size());
	}

	@Test
	public void testClear() throws InterruptedException {
		queueImpl = new LinkedBlockingQueueImpl<>(10);
		String first = "Hello";
		queueImpl.put(first);
		queueImpl.clear();
		Assert.assertEquals(0, queueImpl.size());
		Assert.assertEquals(null, queueImpl.poll());
	}

	@Test
	public void testCapacity() throws InterruptedException {
		queueImpl = new LinkedBlockingQueueImpl<>(10);
		String first = "Hello";
		for (int i = 0; i < 10; i++) {

			queueImpl.put(first + i);
		}
		Assert.assertFalse(queueImpl.offer(first));
		Assert.assertEquals(10, queueImpl.size());
		Assert.assertEquals(first + 0, queueImpl.poll());
	}

	@Test
	public void testIntrupt() throws InterruptedException {
		final LinkedBlockingQueueImpl<String> queueImpl = new LinkedBlockingQueueImpl<>(
				10);
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				String first = "Hello";
				for (int i = 0; i < 10; i++) {

					try {
						queueImpl.put(first + i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				try {
					System.out.println(queueImpl.size());
					queueImpl.put(first);
					System.out.println(queueImpl.size());
				} catch (InterruptedException e) {
					System.out.println("Intruppted while waiting");
					Assert.assertTrue("InteruptedException Thrown", true);
					e.printStackTrace();
				}

			}
		});
		t1.start();
		Thread.sleep(2000);
		t1.interrupt();

	}
}
