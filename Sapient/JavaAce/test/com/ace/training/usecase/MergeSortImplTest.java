package com.ace.training.usecase;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class MergeSortImplTest {

	@Test
	public void testMergeSort() {
		int[] arr = { 3, 7, 2, 8, 6, 1, 9, 44 };
		int[] arrsort = { 1, 2, 3, 6, 7, 8, 9, 44};
		System.out.println("Before Sort: " + Arrays.toString(arr));
		MergeSortImpl.mergeSort(arr);
		Assert.assertArrayEquals(arrsort, arr);
	}
}
