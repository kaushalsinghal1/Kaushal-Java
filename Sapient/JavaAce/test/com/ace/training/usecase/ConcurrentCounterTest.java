package com.ace.training.usecase;

import org.junit.Assert;
import org.junit.Test;

public class ConcurrentCounterTest {

	@Test
	public void testIncrement() throws InterruptedException {
		final ConcurrentCounter counter = new ConcurrentCounter();
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				counter.incrementCounter();
			}
		});
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				counter.incrementCounter();
			}
		});

		t1.start();
		t2.start();
		Thread.sleep(100);
		Assert.assertEquals(2, counter.getCounter());

	}
	
	@Test
	public void testDecrement() throws InterruptedException {
		final ConcurrentCounter counter = new ConcurrentCounter(5);
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				counter.decrementCounter();
			}
		});
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				counter.decrementCounter();
			}
		});

		t1.start();
		t2.start();
		Thread.sleep(100);
		Assert.assertEquals(3, counter.getCounter());

	}
}
