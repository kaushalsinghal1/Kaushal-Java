package day8.training;

public class Runner implements Runnable {
	private Payload payload;

	public Runner(Payload payload) {
		this.payload = payload;
	}

	@Override
	public void run() {
		payload.running();
	}

	static class Payload {
		private boolean stop = false;

		public void running() {
			while (!stop) {
				System.out.println("Running----");
				try {
					Thread.sleep(0,10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		public void stop() {
			stop = true;
			System.out.println("Stopping  ----");
		}

	}

	 static class Stopper implements Runnable {
		private Payload payload;

		public Stopper(Payload payload) {
			this.payload = payload;
		}

		@Override
		public void run() {
			System.out.println("Running----");
			payload.stop();
		}
	}

	public static void main(String[] args) {
		Payload payload = new Payload();
		Thread t1 = new Thread(new Runner(payload));
		Thread t2 = new Thread(new Stopper(payload));
		t1.start();
		try {
			Thread.sleep(100,10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t2.start();

	}
}
