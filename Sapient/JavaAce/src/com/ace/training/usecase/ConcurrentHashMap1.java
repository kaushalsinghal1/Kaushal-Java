package com.ace.training.usecase;

import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentHashMap1<K, V> {

	private static final int DEFAULT_CONCURRENY_LEVEL = 16;
	private static final int DEFAULT_CAPACITY = 16;
	private int concurrencyLevel = DEFAULT_CONCURRENY_LEVEL;
	private int capacity = DEFAULT_CAPACITY;
	private int segTableCapacity;
	private float loadFactor = 0.75f;
	private Segment<K, V>[] segments;

	public ConcurrentHashMap1() {
		segTableCapacity = capacity / concurrencyLevel + 1;
		segments = new Segment[concurrencyLevel];

	}

	private void init() {
		segments = new Segment[concurrencyLevel];
		for (int i = 0; i < concurrencyLevel; i++) {
			segments[i] = new Segment<>(segTableCapacity);
		}
	}

	public V put(K key, V val) {
		Segment<K, V> segment = getSegmentFor(key);
		return segment.put(key, val);
	}

	public V get(K key) {
		Segment<K, V> segment = getSegmentFor(key);
		return segment.get(key);
	}

	private Segment<K, V> getSegmentFor(K key) {
		return segments[getHash(key.hashCode())];
	}

	class HashEntry<K, V> {
		final K key;
		final int hash;
		volatile V val;
		volatile HashEntry<K, V> next;

		public HashEntry(K key, int hash, V val) {
			this.key = key;
			this.hash = hash;
			this.val = val;
		}
	}

	int getHash(int hashCode) {
		return hashCode % concurrencyLevel;
	}

	<K, V> HashEntry<K, V> getNewEntry(K key, V val) {
		HashEntry<K, V> entry = new HashEntry<>(key, getHash(key.hashCode()), val);
		return entry;

	}

	class Segment<K, V> extends ReentrantLock {
		HashEntry<K, V>[] table;
		volatile int count;
		int capacity;

		public Segment(int capacity) {
			this.capacity = capacity;
			table = new HashEntry[capacity];
		}

		int getTableIndex(int hash) {
			return hash % capacity;
		}

		V put(K key, V val) {
			int hash = getHash(key.hashCode());
			this.lock();
			try {
				count++;
				HashEntry<K, V> tab = table[getTableIndex(hash)];
				HashEntry<K, V> tTab = tab;
				while (tTab != null) {
					if (tTab.key == key && tTab.key.equals(key)) {
						V temp = tTab.val;
						tTab.val = val;
						return temp;
					}
					tTab = tTab.next;
				}
				HashEntry<K, V> entry = getNewEntry(key, val);
				if (tab != null) {
					entry.next = tab;
				}
				table[getTableIndex(hash)] = entry;
				if (count > (loadFactor * capacity)) {
					// rehashhing required
					HashEntry<K, V>[] tTemp = table;
					capacity *= 2;
					table = new HashEntry[capacity];
					for (int i = 0; i < tTemp.length; i++) {
						if (tTemp[i] != null) {
							HashEntry<K, V> entry1 = tTemp[i];
							while (entry1 != null) {
								put(entry1.key, entry1.val);
								entry1 = entry1.next;
							}
						}
					}
				}

			} finally {
				this.unlock();
			}
			return null;
		}

		V get(K key) {
			int hash = getHash(key.hashCode());
			HashEntry<K, V> tab = table[getTableIndex(hash)];
			while (tab != null) {
				if (tab.key == key && tab.key.equals(key)) {
					return tab.val;
				}
				tab = tab.next;
			}
			return null;
		}

	}
}
