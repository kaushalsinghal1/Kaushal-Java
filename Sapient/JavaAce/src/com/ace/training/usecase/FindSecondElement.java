package com.ace.training.usecase;

public class FindSecondElement {

	public static void findFirstAndSecondLargest(int[] arr) {
		if (arr.length < 2) {
			System.out
					.println("Input Array should contains atleast 2 elements");
			return;
		}
		int first = arr[0], second = Integer.MIN_VALUE;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > first) {
				second = first;
				first = arr[i];
			} else if (arr[i] > second && arr[i] != first) {
				second = arr[i];
			}
		}
		System.out.println("First Largest: " + first + " , second Largest: "
				+ second);
	}

	public static void main(String[] args) {
		int[] arr = { 5, 9, 77, 5, 6, 44, 25, 49, 22, 56, 77, 22, 8 };
		findFirstAndSecondLargest(arr);
	}
}
