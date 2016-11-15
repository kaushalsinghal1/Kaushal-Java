package day8.training;

public class JoinITC {
	

	static class SharedObject {
		int count = 0;

		public SharedObject() {
		}
	}

	static class Worker implements Runnable {
		private SharedObject sharedObject;

		public Worker(SharedObject sharedObject) {
			this.sharedObject = sharedObject;
		}

		@Override
		public void run() {
			while (sharedObject.count == 0) {
				for (int i = 0; i < 100; i++) {
					System.out.println("Shared...." + i);
				}
			}
		}
	}
	public static void main(String[] args) {
		SharedObject sharedObject = new SharedObject();
//		synchronized (this) {
//			
//		}
	}
}
