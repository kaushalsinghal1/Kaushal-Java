package com.ace.training;

public class ExceptionExample {

	public static int exceptionRetunrn() {
		int i = 21;
		try {
			System.out.println("Try Return 1");
			if (i == 1)
				throw new Exception();
			return 1;
		} catch (Exception e) {
			System.out.println("Exception return 2");
			return 2;
		} finally {
			System.out.println("Finally return 3");
			 return 3;
		}
	}

	public static void main(String[] args) {
		System.out.println("Return Value" + exceptionRetunrn());
	}
}
