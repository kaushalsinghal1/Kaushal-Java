package day5.training.thread;

public class StopThread {

	public static void main(String[] args) {
		Worker w = new Worker();
		Thread t = new Thread(w);
		System.out.println("Statrt The Thread");
		t.start();
		w.stop();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Main Intruppt");
			e.printStackTrace();
		}
	}

	static class Worker implements Runnable {
		private volatile boolean isRunning = true;

		public void stop() {
			isRunning = false;
			Thread.currentThread().interrupt();
		}

		@Override
		public void run() {
			System.out.println("First Run.....");
			while (isRunning) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Running.....");
			}

		}
	}
}
