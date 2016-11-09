package com.ace.training.usecase.objectpool;

public interface ObjectFactory<T> {

	T createNew();
}
