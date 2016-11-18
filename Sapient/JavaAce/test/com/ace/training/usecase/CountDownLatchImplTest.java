package com.ace.training.usecase;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.Test;

public class CountDownLatchImplTest {
	private static AtomicInteger counter = new AtomicInteger(3);

	@Test
	public void testCyclicBarrier() throws InterruptedException {

		CountDownLatchImpl doStart = new CountDownLatchImpl(1);
		CountDownLatchImpl doFinish = new CountDownLatchImpl(3);
		new Worker(doStart, doFinish).start();
		new Worker(doStart, doFinish).start();
		new Worker(doStart, doFinish).start();
		Assert.assertEquals("All Worker Finish", 3, counter.get());
		doStart.countDown();
		System.out.println("Waiting for Worker to Finish");
		doFinish.doWait();
		Assert.assertEquals("All Worker Finish", 0, counter.get());
		System.out.println("All Worker finished the work");
	}

	public static void main(String[] args) throws InterruptedException {
	}

	static class Worker extends Thread {
		private final CountDownLatchImpl doStart;
		private final CountDownLatchImpl doFinish;

		public Worker(CountDownLatchImpl doStart, CountDownLatchImpl doFinish) {
			this.doStart = doStart;
			this.doFinish = doFinish;
		}

		@Override
		public void run() {
			try {
				System.out.println("Wait For Start");
				doStart.doWait();
				dowork();
				System.out.println("Finish the Worker Job.");
				doFinish.countDown();
			} catch (InterruptedException e) {

			}
		}

		private void dowork() throws InterruptedException {
			System.out.println("Doing work.....");
			Thread.sleep(1000);
			counter.decrementAndGet();
		}
	}
}
