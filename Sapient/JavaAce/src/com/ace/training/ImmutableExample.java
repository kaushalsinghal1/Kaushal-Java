package com.ace.training;

import java.lang.reflect.Field;

public final class ImmutableExample {
	private final String name;
	private final int age;

	public ImmutableExample(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public static void main(String[] args) {
		ImmutableExample example = new ImmutableExample("kaushal", 28);
		Class c = example.getClass();
		try {
			Field nameField = c.getDeclaredField("name");
			nameField.setAccessible(true);
			try {
				nameField.set(example, "Change name");
				System.out.println(example.getName());
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
}
