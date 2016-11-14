package day5.training.thread;

public class WaitAndSleep {

	public static void main(String[] args) {
		SharedObject sharedObject = new SharedObject();
		Worker w1 = new Worker(sharedObject);
		Worker2 w2 = new Worker2(sharedObject);
		Thread t1 = new Thread(w1);
		t1.start();
		Thread t2 = new Thread(w2);
		t2.start();

	}

	static class SharedObject {
		private int count = 0;
	}

	static class Worker implements Runnable {
		private volatile boolean isRunning = true;
		private SharedObject sharedObject;

		public Worker(SharedObject sharedObject) {
			this.sharedObject = sharedObject;
		}

		public void stop() {
			isRunning = false;
			Thread.currentThread().interrupt();
		}

		@Override
		public void run() {
			System.out.println("First Run.....");
			while (isRunning) {
				synchronized (sharedObject) {
					System.out.println("Lock Accuired " + Thread.currentThread().getName());
					try {
						sharedObject.wait(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Wakeup...."+System.currentTimeMillis());
					System.out.println("Sleep complete " + Thread.currentThread().getName());
				}
				System.out.println("Running.....");
			}

		}
	}
	
	static class Worker2 implements Runnable {
		private volatile boolean isRunning = true;
		private SharedObject sharedObject;

		public Worker2(SharedObject sharedObject) {
			this.sharedObject = sharedObject;
		}

		public void stop() {
			isRunning = false;
			Thread.currentThread().interrupt();
		}

		@Override
		public void run() {
			System.out.println("First Run.....");
			while (isRunning) {
				synchronized (sharedObject) {
					System.out.println("Lock Accuired " + Thread.currentThread().getName());
					try {
						//Thread.sleep(2500);
						sharedObject.wait(2500);
						System.out.println("Notifying...."+System.currentTimeMillis());
						sharedObject.notify();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Sleep complete " + Thread.currentThread().getName());
				}
				System.out.println("Running.....");
			}

		}
	}
}
