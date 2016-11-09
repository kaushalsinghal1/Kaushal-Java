package com.ace.training.usecase.objectpool;

public interface ObjectPool<T> {
	T get();

	void release(T obj);

	void shutDown();

}


