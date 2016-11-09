package com.ace.training.usecase;

import java.util.HashMap;
import java.util.Map;

public class LRUCustomImpl<K, V> {
	class Node {
		K key;
		V value;
		Node prev;
		Node next;
	}

	private Map<K, Node> map;
	private int capacity;
	private Node head;
	private Node tail;

	public LRUCustomImpl(int capacity) {
		this.capacity = capacity;
		map = new HashMap<>(capacity);
	}

	public synchronized void put(K key, V value) {
		setNode(key, value);
	}

	public synchronized V get(K key) {
		Node node = map.get(key);
		if (node != null) {
			remove(node);
			addAtHead(node);
			return node.value;
		}
		return null;
	}

	private void remove(Node node) {
		if (node.next != null) {
			node.next.prev = node.prev;
		} else {
			tail = node.prev;
		}
		if (node.prev != null) {
			node.prev.next = node.next;
		} else {
			head = node.next;
		}
	}

	private void addAtHead(Node node) {
		node.next = head;

		node.prev = null;
		if (head != null) {
			head.prev = node;
		}
		head = node;
		if (tail == null) {
			tail = head;
		}
	}

	private void setNode(K key, V value) {
		Node node = map.get(key);
		if (node == null) {
			node = new Node();
			node.key = key;
			node.value = value;
			if (capacity <= map.size()) {
				map.remove(tail.key);
				remove(tail);
			}
		} else {
			node.value = value;
			remove(node);
		}
		addAtHead(node);
		map.put(key, node);

	}

	public void print() {
		Node head = this.head;
		while(head !=null){
			System.out.println("key: "+ head.key+" , value: "+head.value);
			head = head.next;
		}

	}

}
