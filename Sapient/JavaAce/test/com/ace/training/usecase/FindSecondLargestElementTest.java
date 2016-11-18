package com.ace.training.usecase;

import org.junit.Assert;
import org.junit.Test;

public class FindSecondLargestElementTest {

	@Test
	public void testFindSecondLargestElement() {
		int[] arr = { 5, 9, 77, 5, 6, 44, 25, 49, 22, 56, 77, 22, 8 };
		int[] result = FindSecondLargestElement.findFirstAndSecondLargest(arr);
		Assert.assertEquals("First Largest", 77, result[0]);
		Assert.assertEquals("Second Largest", 56, result[1]);

	}
	

	@Test (expected= IllegalArgumentException.class)
	public void testFindSecondLargestElementBoundary() {
		int[] arr = { 5 };
		int[] result = FindSecondLargestElement.findFirstAndSecondLargest(arr);

	}
}
