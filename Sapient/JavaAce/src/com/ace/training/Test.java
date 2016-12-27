package com.ace.training;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public class Test {
	// static{
	// FX.getConnection();
	// }
	public static void main(String[] args) {
		// FX.getConnection();
		Stream.iterate(1, n -> n + 1).limit(10).filter(i -> i % 2 == 0).forEach(System.out::println);
		Stream.generate(() -> " test").limit(10).forEach(System.out::print);
		System.out.println();
		Integer[] a = { 2, 1, 2, 5 };
		List<Integer> list = Arrays.asList(a);
		list.set(1, 5);
		System.out.println(Arrays.toString(a));
		Future<Double> future = null;
		// future.
		java.util.Map<String, Person> map = new HashMap<String, Person>();
		map.put("abc", new Person("kaushal", 22));
		map.put("abcd", new Person("kaushal-tets", 27));
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(new File("serialize-map.txt"));
			ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
			out.writeObject(map);
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File("serialize-map.txt")));
			Map<String, Person> map2 = (Map<String, Person>) inputStream.readObject();
			System.out.println(map2.get("abc"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

	}

	static class Person implements Serializable {
		private String name;
		private int age;

		public Person(String name, int age) {
			this.name = name;
			this.age = age;
		}

		public Person() {
			// TODO Auto-generated constructor stub
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

	}
}
