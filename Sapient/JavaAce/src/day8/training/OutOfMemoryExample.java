package day8.training;

import java.util.HashMap;
import java.util.Map;


public class OutOfMemoryExample {

	public static void main(String[] args) {
		Map<String, Employee> map = new HashMap<>();
		for(int i=0;i <10000000; i++){
			Employee e = new Employee();
			map.put(e.name+i, e);
		}
		System.out.println(map.size());
		
	}
	static class Employee{
		String name="bdghfiasd fodifjdopjf opdsjfpdo pdfk[fpk df[";
		int age = 50;
	}
}
