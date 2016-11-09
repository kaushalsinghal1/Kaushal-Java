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
			synchronized (SingletonClass.class) {
				if (instance == null) {
					instance = new SingletonClass();

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

}
