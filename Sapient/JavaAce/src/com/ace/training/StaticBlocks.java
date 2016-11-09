package com.ace.training;

public class StaticBlocks {
	int i;
	private static int intStatic;
	
	static{
		intStatic  =20;
		System.out.println("static object");
				
	}
	
	{
		i  =20;
		System.out.println("non static object");
				
	}
	public StaticBlocks() {
		System.out.println("cONSTRUCTOR");
		i = 30;
	}
	
	public static void main(String[] args) {
		new StaticBlocks();
		new StaticBlocks();
		new StaticBlocks();
	}

}
