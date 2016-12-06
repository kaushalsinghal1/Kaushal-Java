package com.test;

public class SearchingAlgo {
	private static int wCount = 0;

	/*
	 * Time Complexity o(n)
	 */
	public static int linearSearch(int[] arr, int ele) {
		if (arr == null || arr.length == 0) {
			throw new IllegalArgumentException("Input Array Empty");
		}
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == ele) {
				return i;
			}
		}
		return -1;
	}

	/*
	 * Input array sorted Time Complexity o(log n)
	 */
	public static int binarySearch(int[] arr, int ele) {
		if (arr == null || arr.length == 0) {
			throw new IllegalArgumentException("Input Array Empty");
		}
		return binarySearch(arr, 0, arr.length - 1, ele);
	}

	/*
	 * Input array sorted Time Complexity o(n)
	 */
	public static int binarySearchWithoutRecurrence(int[] arr, int ele) {
		if (arr == null || arr.length == 0) {
			throw new IllegalArgumentException("Input Array Empty");
		}
		int l = 0, r = arr.length - 1;
		int m = 0;
		wCount = 0;
		while (l <= r) {
			wCount++;
			m = l + (r - l) / 2;
			if (arr[m] == ele) {
				return m;
			} else if (arr[m] > ele) {
				r = m - 1;
			} else {
				l = m + 1;
			}

		}
		return -1;
	}

	/*
	 * Input array sorted Time Complexity o(log n)
	 */
	public static int interpolationSearch(int[] arr, int ele) {
		if (arr == null || arr.length == 0) {
			throw new IllegalArgumentException("Input Array Empty");
		}
		int lo = 0, hi = arr.length - 1;
		wCount = 0;
		while (lo <= hi && ele >= arr[lo] && ele <= arr[hi]) {
			wCount++;
			int pos = (int) (lo + (((double) (hi - lo) / (arr[hi] - arr[lo])) * (ele - arr[lo])));
			if (arr[pos] == ele)
				return pos;

			// If x is larger, x is in upper part
			if (arr[pos] < ele)
				lo = pos + 1;

			// If x is smaller, x is in lower part
			else
				hi = pos - 1;
		}
		return -1;
	}

	private static int binarySearch(int[] arr, int start, int end, int ele) {
		if (start > end) {
			return -1;
		}
		int mid = (start + end) / 2;
		if (arr[mid] == ele) {
			return mid;
		} else if (arr[mid] < ele) {
			return binarySearch(arr, mid + 1, end, ele);
		} else {
			return binarySearch(arr, 0, mid - 1, ele);
		}
	}

	public static void main(String[] args) {
		int[] arr = { 2, 8, 12, 9, 36, 48, 50, 90 };
		System.out.println(binarySearchWithoutRecurrence(arr, 36)+ " wCount"+ wCount);
		System.out.println(interpolationSearch(arr, 36)+ " wCount"+ wCount);
	}
}
