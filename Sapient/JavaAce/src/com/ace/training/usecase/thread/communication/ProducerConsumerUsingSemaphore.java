package com.ace.training.usecase.thread.communication;

import java.util.concurrent.Semaphore;

public class ProducerConsumerUsingSemaphore {
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(1);
		Thread producer = new Thread(new Even(semaphore));
		Thread consumer = new Thread(new Odd(semaphore));
		producer.start();
		consumer.start();
	}

	static class Even implements Runnable {
		private Semaphore semaphore;

		public Even(Semaphore semaphore) {
			this.semaphore = semaphore;
		}

		@Override
		public void run() {
			for (int i = 0; i < 10; i = i + 2) {
				try {
					semaphore.acquire();
					System.out.println(i);
					semaphore.release();
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}

			}
		}
	}

	static class Odd implements Runnable {
		private Semaphore semaphore;

		public Odd(Semaphore semaphore) {
			this.semaphore = semaphore;
		}

		@Override
		public void run() {
			for (int i = 1; i < 10; i = i + 2) {
				try {
					semaphore.acquire();
					System.out.println(i);
					semaphore.release();
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}

			}
		}
	}

}
