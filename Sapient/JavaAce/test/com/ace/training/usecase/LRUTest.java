package com.ace.training.usecase;

import org.junit.Assert;
import org.junit.Test;

public class LRUTest {
	@Test
	public void testLRU() {
		LRUCustomImpl<Integer, Integer> lru = new LRUCustomImpl<>(3);
		lru.put(1, 1);
		lru.put(2, 2);
		lru.put(3, 3);
		lru.get(1);
		lru.put(4, 4);
		int key = 2;
		Assert.assertNull("Key 2 removed", lru.get(key));

	}
}
