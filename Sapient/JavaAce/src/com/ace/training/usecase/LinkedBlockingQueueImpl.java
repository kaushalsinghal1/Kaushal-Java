package com.ace.training.usecase;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LinkedBlockingQueueImpl<T> {

	static class Node<T> {
		T data;
		Node<T> next;

		Node(T data) {
			this.data = data;
		}
	}

	private void signalNotFull() {
		final Lock putlock = this.putLock;
		putlock.lock();
		try {
			notFull.signal();
		} finally {
			putlock.unlock();
		}
	}

	private void signalNotEmpty() {
		final Lock takelock = this.takeLock;
		takelock.lock();
		try {
			notEmpty.signal();
		} finally {
			takelock.unlock();
		}
	}

	private void fullyLock() {
		this.putLock.lock();
		this.takeLock.lock();
	}

	private void fullyUnlock() {
		this.takeLock.unlock();
		this.putLock.unlock();
	}

	private ReentrantLock putLock = new ReentrantLock();
	private ReentrantLock takeLock = new ReentrantLock();

	private Condition notFull = putLock.newCondition();
	private Condition notEmpty = takeLock.newCondition();
	private int capacity;
	private Node<T> head, last;
	private AtomicInteger size = new AtomicInteger(0);

	/**
	 * Add Note at the Last
	 * 
	 * @param node
	 */
	private void addNode(Node<T> node) {
		last = last.next = node;
	}

	public void clear() {
		fullyLock();
		head = last = new Node<>(null);
		size.set(0);
		fullyUnlock();
	}

	public int size() {
		return size.get();
	}

	/**
	 * Remove the Node from head
	 * 
	 * @return
	 */
	private synchronized T removeFirstNode() {
		Node<T> h = head;
		Node<T> first = h.next;
		h.next = h; // GC
		head = first;
		T data = first.data;
		first.data = null;
		return data;
	}

	public LinkedBlockingQueueImpl(int capacity) {
		this.capacity = capacity;
		head = last = new Node<>(null);
	}

	public void put(T data) throws InterruptedException {
		Node<T> node = new Node<>(data);
		final ReentrantLock putlock = this.putLock;
		putlock.lockInterruptibly();
		int c = -1;
		try {
			while (size.get() == capacity) {
				try {
					notFull.await();
				} catch (InterruptedException e) {
					notFull.signal();
					throw e;
				}
			}
			addNode(node);

			c = size.getAndIncrement();
			if (c + 1 < capacity) {
				notFull.signal();
			}

		} finally {
			putlock.unlock();
		}
		// If previously empty then signal the take lock
		if (c == 0)
			signalNotEmpty();
	}

	/**
	 * Offer immediately return without violating the capacity
	 * 
	 * @param data
	 * @return
	 */
	public boolean offer(T data) {
		Node<T> node = new Node<>(data);
		putLock.lock();
		int c = -1;
		try {
			if (size.get() < capacity) {
				addNode(node);

				c = size.getAndIncrement();
				if (c + 1 < capacity) {
					notFull.signal();
				}

			}

		} finally {
			putLock.unlock();
		}
		// If previously empty then signal the take lock
		if (c == 0)
			signalNotEmpty();
		return c >= 0;
	}

	public T take() throws InterruptedException {
		final ReentrantLock takelock = this.takeLock;
		takelock.lockInterruptibly();
		T val = null;
		int c = -1;
		try {
			while (size.get() == 0) {
				try {
					notEmpty.await();
				} catch (InterruptedException e) {
					notEmpty.signal();
					throw e;
				}
			}
			val = removeFirstNode();
			c = size.getAndDecrement();
			if (c > 1) {
				notEmpty.signal();
			}
		} finally {
			takelock.unlock();
		}
		if (c == capacity) {
			signalNotFull();
		}
		return val;
	}

	public T poll() {
		T val = null;
		int c = -1;
		takeLock.lock();
		try {
			if (size.get() > 0) {
				val = removeFirstNode();
				c = size.getAndDecrement();
				if (c > 1) {
					notEmpty.signal();
				}
			}
		} finally {
			takeLock.unlock();
		}
		if (c == capacity) {
			signalNotFull();
		}
		return val;
	}
}
