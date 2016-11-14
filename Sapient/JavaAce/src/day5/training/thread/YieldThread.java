package day5.training.thread;

public class YieldThread {
	public static void main(String[] args) {
		Worker w1 = new Worker();
		Thread t1 = new Thread(w1);
		t1.start();

	}

	static class Worker implements Runnable {

		@Override
		public void run() {
			System.out.println("First Run.....");
			System.out.println("Start Time: " + System.currentTimeMillis());
			int a = 0;
			for (int i = 0; i < 1000000; i++) {
				a = ++a + 10 * 7 + --a + 99 + 99;
				if (i % 1000 == 0){
					
				}
				//	Thread.yield();
			}
			System.out.println("End Time: " + System.currentTimeMillis());
		}
	}

	static class Worker2 implements Runnable {
		@Override
		public void run() {
			System.out.println("First Run.....");
			for (int i = 0; i < 1000000; i++) {
				System.out.println("Running.....");
			}
		}
	}
}
