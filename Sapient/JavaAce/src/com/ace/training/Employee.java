package com.ace.training;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Employee extends Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
//	private int empId;
	private Student student;

	public Employee() {
		super(10);
	}

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

	@Override
	public Object clone() throws CloneNotSupportedException {
		// ssuper.clone();
		return super.clone();
	}

	public static void main(String[] args) {
		Employee e = new Employee();
		e.setName("kaushal");
		Student st = new Student();
		st.setName("kkkk");
		//e.setEmpId(10101);
		e.setStudent(st);
		/*
		 * Reflection
		 */

		// e.setName("kaushal");
		// Employee e2 = null;
		// try {
		// e2 = (Employee) e.clone();
		// } catch (CloneNotSupportedException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// System.out.println(e2.getName());
		// ----------------------------------

		try {
//			FileOutputStream fileOutputStream = new FileOutputStream(new File("serialize.txt"));
//			ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
//			out.writeObject(e);
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File("serialize.txt")));
			Employee e1 = (Employee) inputStream.readObject();
			System.out.println(e1.getName() + " , " );
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

	}

//	public int getEmpId() {
//		return empId;
//	}
//
//	public void setEmpId(int empId) {
//		this.empId = empId;
//	}
}
