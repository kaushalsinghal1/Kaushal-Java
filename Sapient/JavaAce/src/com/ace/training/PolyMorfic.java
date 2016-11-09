package com.ace.training;

public class PolyMorfic {

	public static void main(String[] args) {
		A a = new B();
		System.out.println(a.i);
	}

}

class A {
	protected int i;
	public A() {
		i = 10;
	}
}

class B extends A {
	//protected int i;
	public B() {
		i = 20;
	}
	public void setI(int i){
		this.i = i;
	}
}
