package com.ace.training.usecase.thread.communication;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

	
	
	static class MyLock {
		ReentrantLock lock = new ReentrantLock();

		public void lock() {
			lock.lock();
		}

		public void unlock() {
			lock.unlock();
		}
	}
}
