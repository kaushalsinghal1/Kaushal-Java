package com.ace.training.usecase.thread.communication;

import com.ace.training.usecase.LinkedBlockingQueueImpl;

public class ProducerConsumerWIthBlockingQueue {

	public static void main(String[] args) {
		LinkedBlockingQueueImpl<String> queueImpl = new LinkedBlockingQueueImpl<>(3);
		Thread producer = new Thread(new Producer(queueImpl));
		Thread consumer = new Thread(new Consumer(queueImpl));
		producer.start();
		consumer.start();
	}

	static class Producer implements Runnable {
		private LinkedBlockingQueueImpl<String> queue;

		public Producer(LinkedBlockingQueueImpl<String> queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			String msg = "Hello";
			int count = 0;
			while (true) {
				try {
					Thread.sleep(500);
					queue.put(msg + ++count);
					System.out.println("producer: " + msg + count);
				} catch (InterruptedException e) {
					System.out.println("Intruppted.. Come out to the loop");
					break;
				}

			}
		}
	}

	static class Consumer implements Runnable {
		private LinkedBlockingQueueImpl<String> queue;

		public Consumer(LinkedBlockingQueueImpl<String> queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(700);
					System.out.println(queue.take());
				} catch (InterruptedException e) {
					System.out.println("Intruppted.. Come out to the loop");
					break;
				}

			}
		}
	}

}
