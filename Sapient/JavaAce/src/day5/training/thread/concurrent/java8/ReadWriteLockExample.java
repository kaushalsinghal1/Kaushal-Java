package day5.training.thread.concurrent.java8;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

public class ReadWriteLockExample {
	public static void main(String[] args) {
		StampedLock stampedLock = new StampedLock();

		long stamp = stampedLock.tryOptimisticRead();
		if (stampedLock.validate(stamp)) {
			// Use local copy of that otherwise take read lovk
		} else {
			stamp = stampedLock.readLock(); // Pesimistic Lock
			/**
			 * Pessimistic Lock --> Allow multiple read, block write Pessimistic
			 * write --> Block all read and write
			 */

		}
		ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		// lock.readLock().
	}
}
