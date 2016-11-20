package com.ace.training;

public class Person {
	private int age;
	public Person() {
		// TODO Auto-generated constructor stub
	}

	public Person(int age) {
		System.out.println("Person constructor called with age: " + age);
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
