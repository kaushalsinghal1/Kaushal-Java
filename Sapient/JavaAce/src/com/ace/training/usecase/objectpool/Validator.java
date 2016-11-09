package com.ace.training.usecase.objectpool;

public interface Validator<T> {
	
	boolean isValid(T obj);
	void invalidate(T obj);
}
