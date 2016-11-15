package day8.training;

public class ThreadLocking {
	public static synchronized void m1() {
		System.out.println("m1 ->" + Thread.currentThread().getName());
	}

	public synchronized void m2() {
		System.out.println("m2 ->" + Thread.currentThread().getName());
	}

	public static synchronized void m3() {
		System.out.println("m3 ->" + Thread.currentThread().getName());
	}

	public synchronized void m4() {
		System.out.println("m4 ->" + Thread.currentThread().getName());
	}

	public static void main(String[] args) {
		final ThreadLocking obj = new ThreadLocking();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (ThreadLocking.class) {
					m1();
					obj.m2();
					m3();
					obj.m4();

				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (ThreadLocking.class) {
					m1();
					obj.m2();

				}
			}
		});

		Thread t3 = new Thread(new Runnable() {

			@Override
			public void run() {
				/*try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				synchronized (obj) {
					m3();
					obj.m4();

				}
			}
		});
		t1.setName("t1");
		t2.setName("t2");
		t3.setName("t3");

		t1.start();
		t3.start();
		t2.start();
	}

}
