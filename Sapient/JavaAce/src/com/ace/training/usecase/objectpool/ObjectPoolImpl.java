package com.ace.training.usecase.objectpool;

import java.util.LinkedList;
import java.util.Queue;

public class ObjectPoolImpl<T> implements ObjectPool<T> {
	private final Validator<T> validator;
	private final Queue<T> queue;
	private final ObjectFactory<T> objectFactory;
	private final int poolSize;
	private int usedCount = 0;

	public ObjectPoolImpl(Validator<T> validator, ObjectFactory<T> objectFactory, int poolSize) {
		this.validator = validator;
		this.queue = new LinkedList<T>();
		this.objectFactory = objectFactory;
		this.poolSize = poolSize;
	}

	@Override
	public synchronized T get() {
		if (usedCount >= poolSize) {
			return null;
		}
		usedCount++;
		while (!queue.isEmpty()) {
			T obj = queue.poll();
			if (validator.isValid(obj)) {
				return obj;
			} else {
				validator.invalidate(obj);
			}
		}
		return objectFactory.createNew();
	}

	@Override
	public synchronized void release(T obj) {
		usedCount--;
		if (validator.isValid(obj)) {
			queue.add(obj);
		} else {
			validator.invalidate(obj);
		}
	}

	@Override
	public void shutDown() {
		for (T obj : queue) {
			validator.invalidate(obj);
		}
		usedCount = 0;
		queue.clear();

	}

}
