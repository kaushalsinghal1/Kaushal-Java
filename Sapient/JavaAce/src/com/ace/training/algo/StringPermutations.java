package com.ace.training.algo;

import java.util.ArrayList;
import java.util.List;

public class StringPermutations {

	public List<String> permutation(String str) {
		List<String> list = new ArrayList<>();

		return list;
	}

	private static void strPerm(char[] arr, int l, int r) {
		if (l == r) {
			System.out.println(new String(arr));

		} else {
			for (int i = l; i < r; i++) {
				swap(arr, l, i);
				strPerm(arr, l + 1, r);
				swap(arr, l, i);
			}
		}

	}

	private static void swap(char[] arr, int l, int r) {
		char temp = arr[l];
		arr[l] = arr[r];
		arr[r] = temp;
	}

	public static void main(String[] args) {
		strPerm("abc".toCharArray(), 0, 3);
	}

}
