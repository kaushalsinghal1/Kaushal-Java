package day8.training;


public class WaitExample {

	static class SharedObject {
		private int count = 0;
	}

	static class SharedObject2 {
		private int count = 0;
	}

	static class WaitThread1 implements Runnable {
		private SharedObject sharedObject;

		WaitThread1(SharedObject sharedObject) {
			this.sharedObject = sharedObject;
		}

		@Override
		public void run() {
			System.out.println("Statement 1");
			System.out.println("Statement 2");
			synchronized (sharedObject) {

				try {
					if (sharedObject.count == 0) {
						sharedObject.wait();
					} else {
						sharedObject.count = 1;
						sharedObject.notify();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Statement 3");
		}
	}

	static class WaitThread2 implements Runnable {
		private SharedObject2 sharedObject;

		WaitThread2(SharedObject2 sharedObject) {
			this.sharedObject = sharedObject;
		}

		@Override
		public void run() {
			System.out.println("Statement 1");
			System.out.println("Statement 2");
			// Thread.currentThread().interrupted():
			synchronized (sharedObject) {

				if (sharedObject.count == 0) {
					sharedObject.count = 1;
					System.out.println("Notifying");
					sharedObject.notify();
				} else {
					sharedObject.count = 1;
					System.out.println("Notifying");
					sharedObject.notify();
				}
			}

			System.out.println("Statement 3");
		}
	}

	public static void main(String[] args) {
		SharedObject object = new SharedObject();
		SharedObject2 object2 = new SharedObject2();
		new Thread(new WaitThread1(object)).start();
		new Thread(new WaitThread2(object2)).start();

	}

}
