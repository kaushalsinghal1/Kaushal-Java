package com.ace.training.usecase;

import java.util.Arrays;

public class MergeSortImpl {

	public static void mergeSort(int[] arr, int first, int last) {
		if (first < last) {
			int mid = (first + last) / 2;
			mergeSort(arr, first, mid);
			mergeSort(arr, mid + 1, last);
			merge(arr, first, mid, last);
		}

	}

	private static void merge(int[] arr, int first, int mid, int last) {
		int[] temp = new int[arr.length + 30];
		int f1 = first;
		int fLast = mid;
		int s1 = mid + 1;
		int sLast = last;
		int i = first;
		for (; f1 <= fLast && s1 <= sLast;) {
			if (arr[f1] < arr[s1]) {
				temp[i++] = arr[f1++];
			} else {
				temp[i++] = arr[s1++];
			}
		}
		while (f1 <= fLast) {
			temp[i++] = arr[f1++];
		}
		while (s1 <= sLast) {
			temp[i++] = arr[s1++];
		}
		for (int j = first; j <= last; j++) {
			arr[j] = temp[j];
		}
	}

	public static void main(String[] args) {
		int[] arr = { 3, 7, 2, 8, 6, 1, 9, 44 };
		System.out.println("Before Sort: " + Arrays.toString(arr));
		mergeSort(arr, 0, arr.length - 1);
		System.out.println("After Sort: " + Arrays.toString(arr));
	}
}
