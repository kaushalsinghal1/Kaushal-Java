package com.test;

import java.util.Arrays;

public class SortingAlgo {

	/*
	 * Time complexity o(n2) Find the minimum element from unsorted array and
	 * place in sorted array
	 */
	public static void selectionSort(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			int minIndx = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[minIndx]) {
					minIndx = j;
				}
			}
			int temp = arr[i];
			arr[i] = arr[minIndx];
			arr[minIndx] = temp;
		}

	}

	/*
	 * O(n2)
	 */
	public static void bubleSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}

			}
		}
	}

	/*
	 * O(n2)
	 */
	public static void insertionSort(int[] arr) {
		int n = arr.length;
		for (int i = 1; i < n; i++) {
			int key = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;

		}
	}

	/*
	 * O(n2)
	 */
	public static void insertionSortWithBinarySearch(int[] arr) {
		int n = arr.length;
		for (int i = 1; i < n; i++) {
			int key = arr[i];
			int j = i - 1;
			int s = 0;
			int mid = j;
			while (s <= j) {
				if (j == s) {
					mid = key > arr[s] ? (s + 1) : s;
					break;
				}
				mid = s + (j - s) / 2;
				if (arr[mid] > key) {
					j = mid - 1;
				} else {
					s = mid + 1;
				}
			}
			int l = i - 1;
			while (l >= mid) {
				arr[l + 1] = arr[l];
				l--;
			}
			arr[l + 1] = key;

		}
	}

	static void printArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	static class MergeSort {
		/*
		 * o(nlogn)
		 */
		static void mergeSort(int arr[]) {

			if (arr.length > 1) {
				int mid = arr.length / 2;
				int[] arr1 = Arrays.copyOfRange(arr, 0, mid);
				int[] arr2 = Arrays.copyOfRange(arr, mid, arr.length);
				mergeSort(arr1);
				mergeSort(arr2);
				merge(arr1, arr2, arr);
			}

		}

		static void merge(int[] arr1, int[] arr2, int[] arr) {
			int i = 0;
			int l = 0, r = 0;
			while (l < arr1.length && r < arr2.length) {
				if (arr1[l] < arr2[r]) {
					arr[i] = arr1[l];
					l++;
				} else {
					arr[i] = arr2[r];
					r++;
				}
				i++;
			}
			while (l < arr1.length) {
				arr[i] = arr1[l];
				l++;
				i++;
			}
			while (r < arr2.length) {
				arr[i] = arr2[r];
				r++;
				i++;
			}
		}
	}

	static class HeapSort {

		static void heapSort(int[] arr) {
			int n = arr.length;
			// build the heap (rearrange array
			for (int i = n / 2 - 1; i >= 0; i--) {
				heapify(arr, n, i);
			}

			// One by one extract an element from heap
			for (int i = n - 1; i >= 0; i--) {
				// Move current root to end
				int temp = arr[0];
				arr[0] = arr[i];
				arr[i] = temp;

				// call max heapify on the reduced heap
				heapify(arr, i, 0);
			}
		}

		/*
		 * Max Heap n size i node to heapify
		 */
		private static void heapify(int[] arr, int n, int i) {

			int largest = i;
			int l = 2 * i + 1;
			int r = 2 * i + 2;
			if (l < n && arr[l] > arr[largest]) {
				largest = l;
			}
			if (r < n && arr[r] > arr[largest]) {
				largest = r;
			}
			if (largest != i) {
				int swap = arr[largest];
				arr[largest] = arr[i];
				arr[i] = swap;
				// Recursively heapify the affected sub-tree
				heapify(arr, n, largest);
			}

		}
	}

	public static void main(String[] args) {

		int[] arr = { 3, 7, 2, 9, 2, 7, 90, 22 };
		printArray(arr);
		HeapSort.heapSort(arr);
		printArray(arr);

	}
}
