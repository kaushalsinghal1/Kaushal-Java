package day5.training.thread;

public class DeadLock {
	public static void main(String[] args) {
		Shared1 shared1 = new Shared1();
		Shared2 shared2 = new Shared2();
		Worker w1 = new Worker(shared1, shared2);
		Worker2 w2 = new Worker2(shared1, shared2);
		Thread t1 = new Thread(w1);
		Thread t2 = new Thread(w2);
		t1.start();
		t2.start();

	}

	static class Shared1 {
		int count;
	}

	static class Shared2 {
		int count;
	}

	static class Worker implements Runnable {
		private Shared1 shared1;
		private Shared2 shared2;

		public Worker(Shared1 shared1, Shared2 shared2) {

			this.shared1 = shared1;
			this.shared2 = shared2;
		}

		@Override
		public void run() {
			System.out.println("First Run.....");
			synchronized (shared1) {
				System.out.println("Acuire shared1 " + Thread.currentThread().getName());
				String st = "";
				for (int i = 0; i < 1000000; i++) {
					st = st + i;	
				}
				synchronized (shared2) {
					System.out.println("Acuire shared2 " + Thread.currentThread().getName());
				}

			}
		}
	}

	static class Worker2 implements Runnable {
		private Shared1 shared1;
		private Shared2 shared2;

		public Worker2(Shared1 shared1, Shared2 shared2) {

			this.shared1 = shared1;
			this.shared2 = shared2;
		}

		@Override
		public void run() {
			System.out.println("Second Run.....");
			synchronized (shared2) {
//				try {
//					Thread.sleep(800);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				System.out.println("Acuire shared2 " + Thread.currentThread().getName());
				synchronized (shared1) {
					System.out.println("Acuire shared1 " + Thread.currentThread().getName());
				}

			}
		}
	}
}
