package com.ace.training;

public class StaticExample {
	private static void print(){
		System.out.println("hi");
	}
	 {
		i = 10;
		print();
		System.out.println(i);
	}
	private static int i = 0;
	static {
		i = 20;
	//	System.out.println(i);
	}

	public static void main(String[] args) {
		new StaticExample();
		System.out.println(i);
	}
}
