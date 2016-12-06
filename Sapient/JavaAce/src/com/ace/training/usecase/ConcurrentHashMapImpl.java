package com.ace.training.usecase;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentHashMapImpl<K, V> {

	private final int BUCKET_COUNT = 10;
	private Lock[] locks;
	private AtomicInteger size = new AtomicInteger(0);
	private List<List<Entry<K, V>>> tableEnries;

	public ConcurrentHashMapImpl() {
		locks = new Lock[BUCKET_COUNT];
		tableEnries = new ArrayList<>(BUCKET_COUNT);
		for (int i = 0; i < locks.length; i++) {
			locks[i] = new ReentrantLock();
			tableEnries.add(new LinkedList<>());
		}
	}

	public V put(K key, V value) {
		if (key == null) {
			throw new NullPointerException();
		}
		int index = getBucketIndex(key);
		Lock lock = locks[index];
		lock.lock();
		try {
			List<Entry<K, V>> entries = tableEnries.get(index);
			addEntries(entries, key, value);
			System.out.println("Inserted Key: " + key);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} finally {
			lock.unlock();
		}

		return value;
	}

	public V remove(K key) {
		if (key == null) {
			throw new NullPointerException();
		}
		int index = getBucketIndex(key);
		Lock lock = locks[index];
		lock.lock();
		try {
			List<Entry<K, V>> entries = tableEnries.get(index);
			if (entries == null) {
				return null;
			}
			Entry<K, V> entry = findEntry(entries, key);
			if (entry != null) {
				entries.remove(entry);
				return entry.getValue();
			}

		} finally {
			lock.unlock();
		}
		return null;
	}

	public V get(K key) {
		int index = getBucketIndex(key);
		List<Entry<K, V>> entries = tableEnries.get(index);
		if (entries == null) {
			return null;
		}
		Entry<K, V> entry = findEntry(entries, key);
		if (entry != null) {
			return entry.getValue();
		}
		return null;
	}

	private Entry<K, V> findEntry(List<Entry<K, V>> entries, K k) {
		for (Entry<K, V> entry : entries) {
			if (entry.getKey().equals(k)) {
				return entry;
			}
		}
		return null;
	}

	private void addEntries(List<Entry<K, V>> entries, K k, V v) {
		Entry<K, V> entry = findEntry(entries, k);
		if (entry == null) {
			entry = new AbstractMap.SimpleEntry<K, V>(k, v);
			entries.add(entry);
			size.incrementAndGet();
		}
		entry.setValue(v);
	}

	private int getBucketIndex(K key) {
		return key.hashCode() % BUCKET_COUNT;
	}

	public static void main(String[] args) {
		ConcurrentHashMapImpl<Integer, String> mapImpl = new ConcurrentHashMapImpl<>();
		for (int i = 1; i <= 10; i++) {
			new Worker(mapImpl, i).start();

		}

	}

	static class Worker extends Thread {
		private ConcurrentHashMapImpl<Integer, String> mapImpl;
		private int keyMult;

		public Worker(ConcurrentHashMapImpl<Integer, String> mapImpl,
				int keyMult) {
			this.mapImpl = mapImpl;
			this.keyMult = keyMult;
		}

		@Override
		public void run() {
			for (int i = 1; i <= 10; i++) {
				int k = keyMult * i;
				mapImpl.put(k, "test" + k);

			}
		}
	}
}
