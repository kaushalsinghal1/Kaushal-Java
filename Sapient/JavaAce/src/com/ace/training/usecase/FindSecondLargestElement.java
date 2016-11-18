package com.ace.training.usecase;

public class FindSecondLargestElement {

	public static int[] findFirstAndSecondLargest(int[] arr) {
		if (arr.length < 2) {
			System.out
					.println("Input Array should contains atleast 2 elements");
			throw new IllegalArgumentException("Input Array should contains atleast 2 elements");
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
		return new int[] { first, second };
	}

}
