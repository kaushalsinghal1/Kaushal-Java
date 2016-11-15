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
		putLock.lock();
		try {
			notFull.signal();
		} finally {
			putLock.unlock();
		}
	}

	private void signalNotEmpty() {
		takeLock.lock();
		try {
			notEmpty.signal();
		} finally {
			takeLock.unlock();
		}
	}

	private Lock putLock = new ReentrantLock();
	private Lock takeLock = new ReentrantLock();

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
		head = last = new Node<>(null);
		size.set(0);
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
		// Node<E> h = head;
		// Node<E> first = h.next;
		// h.next = h; // help GC
		// head = first;
		// E x = first.item;
		// first.item = null;

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
		putLock.lockInterruptibly();
		int c = -1;
		try {
			while (size.get() == capacity) {
				notFull.await();
			}
			addNode(node);

			c = size.incrementAndGet();
			if (c + 1 < capacity) {
				notFull.signal();
			}

		} finally {
			putLock.unlock();
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

				c = size.incrementAndGet();
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
		takeLock.lockInterruptibly();
		T val = null;
		int c = -1;
		try {
			if (size.get() == 0) {
				notEmpty.await();
			}
			val = removeFirstNode();
			c = size.decrementAndGet();
			if (c > 1) {
				notEmpty.signal();
			}
		} finally {
			takeLock.unlock();
		}
		if (c == capacity) {
			signalNotFull();
		}
		return val;
	}

	public T poll()  {
		T val = null;
		int c = -1;
		takeLock.lock();
		try {
			if (size.get() > 0) {
				val = removeFirstNode();
				c = size.decrementAndGet();
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
