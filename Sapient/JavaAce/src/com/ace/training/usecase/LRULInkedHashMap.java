package com.ace.training.usecase;

import java.util.LinkedHashMap;

public class LRULInkedHashMap<K, V> extends LinkedHashMap<K, V> {
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	private int capacity;

	public LRULInkedHashMap(int capacity) {
		super(capacity, DEFAULT_LOAD_FACTOR, true);
		this.capacity = capacity;
	}

	@Override
	protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
		return size() > capacity;
	}

}
