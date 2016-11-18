package day9;

public class ThreasSprintingUsingLock {

	public static void main(String[] args) {
		Counter counter = new Counter();
		Thread t1 = new FirstThread(counter);
		Thread t2 = new SecondThread(counter);
		Thread t3 = new ThirdThread(counter);
		t1.start();
		t2.start();
		t3.start();

		try {
			Thread.sleep(10);
			t1.interrupt();
			//t2.interrupt();
			Thread.sleep(100);
			t3.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static class Counter {
		volatile int count = 1;

		void incrementCount() {
			count++;
		}
	}

	static class FirstThread extends Thread {
		private Counter counter;

		public FirstThread(Counter counter) {
			this.counter = counter;
		}

		@Override
		public void run() {
			outer: while (true && !Thread.interrupted()) {
				synchronized (counter) {
					while (counter.count % 3 != 1) {
						try {
							counter.wait();
						} catch (InterruptedException e) {
							break outer;
						}
					}
					System.out.println(Thread.currentThread().getName()
							+ " , counter: " + counter.count);
					counter.incrementCount();
					counter.notifyAll();
				}
			}
		}
	}

	static class SecondThread extends Thread {
		private Counter counter;

		public SecondThread(Counter counter) {
			this.counter = counter;
		}

		@Override
		public void run() {
			outer: while (true && !Thread.interrupted()) {
				synchronized (counter) {
					while (counter.count % 3 != 2) {
						try {
							counter.wait();
						} catch (InterruptedException e) {
							break outer;
						}
					}
					System.out.println(Thread.currentThread().getName()
							+ " , counter: " + counter.count);
					counter.incrementCount();
					counter.notifyAll();
				}
			}

		}
	}

	static class ThirdThread extends Thread {
		private Counter counter;

		public ThirdThread(Counter counter) {
			this.counter = counter;
		}

		@Override
		public void run() {
			outer: while (true && !Thread.interrupted()) {
				synchronized (counter) {
					while (counter.count % 3 != 0) {
						try {
							counter.wait();
						} catch (InterruptedException e) {
							break outer;
						}
					}
					System.out.println(Thread.currentThread().getName()
							+ " , counter: " + counter.count);
					counter.incrementCount();
					counter.notifyAll();
				}
			}
		}
	}

}
