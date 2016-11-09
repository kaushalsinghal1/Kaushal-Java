package com.ace.training;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public class HashMapPerfomanceJava7Vs8 {

	public static void hashMapPerformance() {
		Map<Employee, String> map = new HashMap<>();
		System.out.println("Start Time put: "+ System.currentTimeMillis());
		for (int i = 1; i < 100000; i++) {
			map.put(new Employee(i, "name" + i), "abc");
		}
		System.out.println("End Time put: "+ System.currentTimeMillis());
		
		System.out.println("Start Time of get: "+ System.currentTimeMillis());
		int ii =90000;
		System.out.println("Get Value"+ map.get(new Employee(ii, "name" + ii)));
		System.out.println("End Time of get: "+ System.currentTimeMillis());

	}
	public static void main(String[] args) {
		hashMapPerformance();
	}

	static class Employee implements Comparable<Employee> {
		int id;
		String name;

		public Employee(int id, String name) {
			this.id = id;
			this.name = name;
		}

		@Override
		public int hashCode() {
			// HashCode collision
			return 20;
		}

		@Override
		public boolean equals(Object obj) {
			Employee e =(Employee) obj;
			return this.id == e.id && this.name.equals(e.name);
		}

		@Override
		public int compareTo(Employee o) {
			return this.id - o.id;
		}

	}

}
