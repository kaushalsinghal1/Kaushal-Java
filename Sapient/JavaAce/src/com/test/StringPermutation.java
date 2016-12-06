package com.test;

public class StringPermutation {

	public static void permutation(String str) {
		permute(str.toCharArray(), 0, str.length()-1);
	}

	private static void permute(char[] arr, int l, int r) {
		if (l == r) {
			System.out.println(arr);
		} else {
			for (int i = l; i <= r; i++) {
				swap(arr, l, i);
				permute(arr, l + 1, r);
				swap(arr, l, i);// back track
			}
		}
	}

	private static void swap(char[] arr, int l, int r) {
		char temp = arr[l];
		arr[l] = arr[r];
		arr[r] = temp;
	}
	public static void main(String[] args) {
		permutation("abc");
	}
}
