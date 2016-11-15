package com.ace.training.usecase.thread.communication;

import java.util.concurrent.Exchanger;

public class ProducerConsumerUsinExchanger {
	public static void main(String[] args) {
		Exchanger<StringBuffer> exchanger = new Exchanger<>();
		Thread producer = new Thread(new Producer(exchanger));
		Thread consumer = new Thread(new Consumer(exchanger));
		producer.start();
		consumer.start();
	}

	static class Producer implements Runnable {
		private Exchanger<StringBuffer> exchanger;

		public Producer(Exchanger<StringBuffer> exchanger) {
			this.exchanger = exchanger;
		}

		@Override
		public void run() {
			String msg = "Hello";
			int count = 0;
			StringBuffer sb = new StringBuffer();
			while (true) {
				try {
					Thread.sleep(500);
					sb.append(msg + count++);
					System.out.println("Produce ->" + sb.toString());
					sb = exchanger.exchange(sb);
					System.out.println("SB After exchange Produce ->" + sb.toString());
				} catch (InterruptedException e) {
					System.out.println("Intruppted.. Come out to the loop");
					break;
				}

			}
		}
	}

	static class Consumer implements Runnable {
		private Exchanger<StringBuffer> exchanger;

		public Consumer(Exchanger<StringBuffer> exchanger) {
			this.exchanger = exchanger;
		}

		@Override
		public void run() {
			StringBuffer sb = new StringBuffer();
			while (true) {
				try {
					Thread.sleep(700);
					sb = new StringBuffer();
					System.out.println("Consumer put ->" + sb.toString());
					sb = exchanger.exchange(sb);
					System.out.println("SB After exchange Consume ->" + sb.toString());
				} catch (InterruptedException e) {
					System.out.println("Intruppted.. Come out to the loop");
					break;
				}

			}
		}
	}

}
