package com.ace.training.usecase;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentCounter {

	private int counter;
	// Fairness to give all threads equals chance to aquire lock
	// Avoid starvation problem
	private Lock lock = new ReentrantLock(true);

	public ConcurrentCounter() {
		this(0);
	}

	public ConcurrentCounter(int value) {
		this.counter = value;
	}

	public int getCounter() {
		return counter;
	}

	public void incrementCounter() {
		lock.lock();
		try {
			counter++;
		} finally {
			lock.unlock();
		}

	}

	public void decrementCounter() {
		lock.lock();
		try {
			counter--;
		} finally {
			lock.unlock();
		}

	}
}
