package com.ace.training;

public class StringSwap {

	public static void swap(String s1, String s2) {
		String temp = s1;
		s1 = s2;
		s2 = temp;
	}

	public static void main(String[] args) {
		//java.nio.channels.AcceptPendingException
		String s1 = new String("Hello");
		String s2 = new String("Bye");
		System.out.println(s1 + " , " + s2);
		swap(s1, s2);
		System.out.println(s1 + " , " + s2);
		String s3 = s2;
		s2 = s2.concat("sas");
		System.out.println(s3 + " , " + s2);

	}
}
