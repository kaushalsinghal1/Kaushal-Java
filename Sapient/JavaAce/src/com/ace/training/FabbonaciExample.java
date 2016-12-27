package com.ace.training;

public class FabbonaciExample {

	public static int fabobacci(int n) {
		if (n <= 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		}
		return fabobacci(n - 1) + fabobacci(n - 2);
	}
}
