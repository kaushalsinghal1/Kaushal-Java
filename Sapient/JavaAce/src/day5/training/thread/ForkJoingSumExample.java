package day5.training.thread;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoingSumExample {
	public static void main(String[] args) {
		int LIMIT = 100;
		int[] arr = new int[LIMIT];
		Random random = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = random.nextInt(100);
		}
		CalculateSum calculateSum = new CalculateSum(arr, 0, arr.length - 1);
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		System.out.println(forkJoinPool.invoke(calculateSum));
	}

	static class CalculateSum extends RecursiveTask<Integer> {
		private int[] arr;
		private int startIndex;
		private int endIndex;
		private static final int THRESHOLD = 10;

		public CalculateSum(int[] arr, int startIndex, int endIndex) {
			this.arr = arr;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
		}

		@Override
		protected Integer compute() {
			int length = endIndex - startIndex;
			if (length <= THRESHOLD) {
				System.out.println(startIndex + " , leng" + length);
				return computeDirectly();
			}
			int split = length / 2;
			CalculateSum left = new CalculateSum(arr, startIndex, startIndex + split);
			CalculateSum right = new CalculateSum(arr, startIndex + split + 1, endIndex);
			// left.fork() ;
			// right.fork();
			invokeAll(left, right);
			return left.join()+right.join();

		}

		private int computeDirectly() {
			int sum = arr[startIndex];// 0;
			for (int i = startIndex + 1; i < endIndex; i++) {
					sum += arr[i];
			}
			return sum;
		}

	}
}
