package com.ace.training.algo;

public class SumCalcUseCase {
	public static int sumCalc(int[] intArray) {
		int index = 0;
		int i = 0, j = intArray.length - 1;
		int leftSum = intArray[i], rightSum = intArray[j];
		while (j > i) {
			if (leftSum > rightSum) {
				rightSum += intArray[--j];

			}
			if (rightSum > leftSum) {
				leftSum += intArray[++i];
			}

			if ((leftSum == rightSum)) {
				if (((j - i) == 2)) {
					return i + 1;
				} else {
					leftSum += intArray[++i];
					//rightSum += intArray[--j];
				}

			}

		}
		return index;
	}

	public static void main(String[] args) {
		/* 10,5,4,2,3,7,2,1 */
		int array[] = {10,5,3,5,10};
//		new int[8];
//		array[0] = 10;
//		array[1] = 8;
//		array[2] = 4;
//		array[3] = 2;
//		array[4] = 3;
//		array[5] = 7;
//		array[6] = 5;
//		array[7] = 1;

		int index = sumCalc(array);
		System.out.println(index);

	}

}
