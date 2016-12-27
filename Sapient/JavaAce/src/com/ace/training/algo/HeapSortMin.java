package com.ace.training.algo;

import java.util.Arrays;

public class HeapSortMin {
	/*
	 * Main function to do heap sort. This function uses buildHeap() and
	 * heapify()
	 */
	public static void main(String[] args) {
		int[] a = { 3, 5, 2, 7, 5, 12, 10, 45 };
		HeapSortMin h = new HeapSortMin();
		h.heapSort(a, a.length);
		System.out.println(Arrays.toString(a));
	}

	void heapSort(int arr[], int n) {
		buildHeap(arr, n);
		System.out.println("Heap \n"+Arrays.toString(arr));
		for (int i = n - 1; i >= 0; i--) {
			swap(arr, 0, i);
			heapify(arr, i, 0);
		}
	}

	// The functions should be written in a way that array become sorted
	// in increasing order when above heapSort() is called.

	// To heapify a subtree rooted with node i which is an index in arr[].
	// n is size of heap. This function is used in above heapSor()
	void heapify(int arr[], int n, int i) {
		// Your Code Here
		int l = 2 * i + 1;
		int r = 2 * i + 2;
		int smallest = i;
		if (l < n && arr[l] < arr[i]) {
			smallest = l;
		}
		if (r < n && arr[r] < arr[smallest]) {
			smallest = r;
		}
		if (smallest != i) {
			swap(arr, i, smallest);
			heapify(arr, n, smallest);
		}
	}

	int getParent(int i) {
		return i - 1 / 2;
	}

	void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	int insert(int[] arr, int key, int heapSize) {
		int i = heapSize;
		arr[heapSize++] = key;

		while (i != 0 && arr[getParent(i)] > arr[i]) {
			swap(arr, getParent(i), i);
			i = getParent(i);
		}
		return heapSize;

	}

	// Rearranges input array so that it becomes a min heap
	void buildHeap(int arr[], int n) {
		// Your Code Here
		 for(int i=n/2;i>=0;i--)
	        {
	            heapify(arr,n,i);
	        }
		System.out.println("Temp Heap \n"+Arrays.toString(arr));
//		int[] temp = new int[n];
//		int heapSize = 0;
//		for (int i = 0; i < n; i++) {
//			heapSize = insert(temp, arr[i], heapSize);
//		}
//		for (int i = 0; i < temp.length; i++) {
//			arr[i]= temp[i];
//		}
	}

}
