package com.ace.training;

public class FX {

	static {

		// int i = 5/0;
//		System.out.println("Test " + 5 / 0);
	}

	public static void getConnection() {

	}
	public static void main(String[] args) {
		Employee e = new Employee();
		try {
			Employee e1  =(Employee) e.clone();
		} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
