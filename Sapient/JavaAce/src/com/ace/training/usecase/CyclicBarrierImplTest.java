package com.ace.training.usecase;

import java.util.concurrent.BrokenBarrierException;

public class CyclicBarrierImplTest {
	public static void main(String[] args) {
		System.out.println("Start Executing.. and waiting to other get ready for");
		CyclicBarrierImpl barrierImpl =new CyclicBarrierImpl(4, new  Runnable() {
			
			@Override
			public void run() {
				System.out.println("All Player are ready");
			}
		});
		(new Thread(new ThreadWait(1000, barrierImpl))).start();
		(new Thread(new ThreadWait(2000, barrierImpl))).start();
		(new Thread(new ThreadWait(3000, barrierImpl))).start();
		(new Thread(new ThreadWait(4000, barrierImpl))).start();
		
		(new Thread(new ThreadWait(7000, barrierImpl))).start();
		(new Thread(new ThreadWait(8000, barrierImpl))).start();
		(new Thread(new ThreadWait(9000, barrierImpl))).start();
		(new Thread(new ThreadWait(10000, barrierImpl))).start();
	}

	static class ThreadWait implements Runnable {
		private final long waitTime;
		private CyclicBarrierImpl barrierImpl;

		public ThreadWait(long waitTime, CyclicBarrierImpl barrierImpl) {
			this.waitTime = waitTime;
			this.barrierImpl = barrierImpl;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(waitTime);
				System.out.println("Player ready, waiting for other: "+ waitTime);
				barrierImpl.await();
				System.out.println("All Player  ready");
			} catch (InterruptedException | BrokenBarrierException e) {
				e.getStackTrace();
			}
		}
	}

}
