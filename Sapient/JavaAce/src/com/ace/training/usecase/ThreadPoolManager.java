package com.ace.training.usecase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPoolManager {
	private BlockingQueue<Runnable> queues;
	private List<WorkerThread> threads;
	private boolean isStopped = false;

	public ThreadPoolManager(int poolSize, int noMaxTaks) {
		queues = new LinkedBlockingQueue<Runnable>(noMaxTaks);
		threads = new ArrayList<>(poolSize);
		for (int i = 0; i < poolSize; i++) {
			threads.add(new WorkerThread(queues));
		}
		for (WorkerThread thread : threads) {
			thread.start();
		}
	}

	public synchronized void execute(Runnable runnable)
			throws IllegalStateException {
		if (isStopped) {
			throw new IllegalStateException("ThreadPool stopped");
		}
		queues.offer(runnable);
	}

	public synchronized void stop() {
		isStopped = true;
		for (WorkerThread thread : threads) {
			thread.doStop();
		}
	}

	class WorkerThread extends Thread {
		private BlockingQueue<Runnable> queues;
		private boolean isStopped = false;

		public WorkerThread(BlockingQueue<Runnable> queues) {
			this.queues = queues;
		}

		@Override
		public void run() {
			while (!isStopped) {
				Runnable task;
				try {
					task = queues.take();
					task.run();
				} catch (Exception e) {
					System.out.println("Log Exception " + e.getMessage());
				}

			}
		}

		public synchronized void doStop() {
			isStopped = true;
			this.interrupt(); // break pool thread out of dequeue() call.
		}

		public synchronized boolean isStopped() {
			return isStopped;
		}
	}

}
