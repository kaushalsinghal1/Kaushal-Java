package com.ace.training.usecase;

public class CountDownLatchImplTest {
public static void main(String[] args) throws InterruptedException {
	CountDownLatchImpl doStart = new CountDownLatchImpl(1);
	CountDownLatchImpl doFinish = new CountDownLatchImpl(3);
	new Worker(doStart, doFinish).start();
	new Worker(doStart, doFinish).start();
	new Worker(doStart, doFinish).start();
	
	doStart.countDown();
	System.out.println("Waiting for Worker to Finish");
	doFinish.doWait();
	System.out.println("All Worker finished the work");
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
		}
	}
}
