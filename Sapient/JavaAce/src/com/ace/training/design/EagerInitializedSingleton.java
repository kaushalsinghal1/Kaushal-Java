package com.ace.training.design;

public class EagerInitializedSingleton {
	private static EagerInitializedSingleton instance = null;
	static {
		instance = new EagerInitializedSingleton();
	}

	private EagerInitializedSingleton() {
	}

	public static EagerInitializedSingleton getInstance() {
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
