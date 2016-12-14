package com.ace.training.algo;

import java.util.Arrays;

public class MinHeap {
	private int[] arr;
	private int heapSize = -1;
	private int capacity;

	public MinHeap(int capacity) {
		this.capacity = capacity;
		arr = new int[capacity];
	}

	private void swap(int l, int r) {
		int temp = arr[l];
		arr[l] = arr[r];
		arr[r] = temp;
	}

	private int getParent(int i) {
		return (i - 1) / 2;
	}

	private int left(int i) {
		return 2 * i + 1;
	}

	private int right(int i) {
		return 2 * i + 2;
	}

	/**
	 * Add Node at the end then balance heap property : O(long n)
	 * 
	 * @param key
	 */
	public void inserNode(int key) {
		if (heapSize == capacity) {
			// Already reach the capacity, Overflow
			return;
		}
		if (heapSize == -1) {
			heapSize++;
		}
		arr[heapSize++] = key;
		balanceHeap(heapSize - 1);
	}

	public int getMinKey() {
		return heapSize >= 0 ? arr[0] : Integer.MAX_VALUE;
	}

	/**
	 * Add Node at the end then balance heap property : O(long n)
	 * 
	 * @param i
	 * @param new_val
	 */
	public void decreaseKey(int i, int new_val) {
		arr[i] = new_val;
		balanceHeap(i);
	}

	/**
	 * Remove root node key Replace last node value root then re-heapify to
	 * maintain heap property
	 * 
	 * @return
	 */
	public int extractMin() {
		if (heapSize == 0) {
			return Integer.MAX_VALUE;
		}
		if (heapSize == 1) {
			return arr[heapSize--];
		}
		int minK = arr[0];
		arr[0] = arr[heapSize - 1];
		heapSize--;
		minHeapify(0);
		return minK;

	}

	private void minHeapify(int i) {
		int l = left(i);
		int r = right(i);
		int smallest = i;
		if (l < heapSize && arr[l] < arr[i]) {
			smallest = l;
		}
		if (r < heapSize && arr[r] < arr[smallest]) {
			smallest = r;
		}
		if (i != smallest) {
			swap(smallest, i);
			minHeapify(smallest);
		}
	}

	private void balanceHeap(int i) {
		while (i != 0 && arr[getParent(i)] > arr[i]) {
			swap(getParent(i), i);
			i = getParent(i);
		}
	}

	private void printString() {
		System.out.println(Arrays.toString(arr));
	}

	public static void main(String[] args) {
		MinHeap heap = new MinHeap(10);
		int a[] = { 5, 9, 10, 98, 36, 45, 80 };
		int a1[] = { 5, 10, 15, 4, 55, 30, 35, 40 };
		for (int i = 0; i < a1.length; i++) {
			heap.inserNode(a1[i]);
		}
		heap.printString();
	}
}
