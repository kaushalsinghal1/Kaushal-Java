package com.ace.training;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Employee implements Cloneable, Serializable{

	private String name;
	private Student student;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
//	@Override
//	protected Object clone() throws CloneNotSupportedException {
//		// TODO Auto-generated method stub
//		return new Employee();
//	}
	
	public static void main(String[] args) {
		Employee e =new Employee();
		e.setName("kaushal");
		Student st = new Student();
		st.setName("kkkk");
		e.setStudent(st);
/*
 * Reflection
 */

//		e.setName("kaushal");
//		Employee e2 = null;
//		try {
//			e2 = (Employee) e.clone();
//		} catch (CloneNotSupportedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		System.out.println(e2.getName());
		//----------------------------------
		
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(new File("serialize.txt"));
			ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
			out.writeObject(e);
			ObjectInputStream  inputStream = new ObjectInputStream(new FileInputStream(new File("serialize.txt")));
			Employee e1 = (Employee) inputStream.readObject();
			System.out.println(e1.getName());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
