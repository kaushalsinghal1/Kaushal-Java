package com.ace.training.design;

public class SingletonClass {
	private static SingletonClass instance;
	public static int count = 0;

	private SingletonClass() {
		if (instance != null) {
			throw new IllegalStateException("Singleton Object Already created");
		}
		count++;
	}

	public static SingletonClass getInstance() {
		if (instance == null) {
			System.out.println("Thread 1"+ Thread.currentThread().getName());
			synchronized (SingletonClass.class) {
				System.out.println("Thread 2"+ Thread.currentThread().getName());
				if (instance == null) {
					instance = new SingletonClass();
					System.out.println("Thread 3"+ Thread.currentThread().getName());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}
		return instance;
	}

	// At the time of De-serialization, return the same object
	protected Object readResolve() {
		return instance;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				SingletonClass.getInstance();

			}
		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				SingletonClass.getInstance();

			}
		});
		t1.start();
		t2.start();
		System.out.println("Count" + count);
	}

}
