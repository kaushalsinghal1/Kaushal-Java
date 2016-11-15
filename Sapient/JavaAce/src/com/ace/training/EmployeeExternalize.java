package com.ace.training;

import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class EmployeeExternalize implements Externalizable {

	private String name;
	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "EmployeeExternalize [name=" + name + ", age=" + age + "]";
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// ssuper.clone();
		return super.clone();
	}

	public static void main(String[] args) {
		EmployeeExternalize e = new EmployeeExternalize();
		e.setName("kaushal");
		e.setAge(50);
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
			FileOutputStream fileOutputStream = new FileOutputStream(new File(
					"externalize.txt"));
			ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
			out.writeObject(e);
			System.out.println(e.toString());
			ObjectInputStream inputStream = new ObjectInputStream(
					new FileInputStream(new File("externalize.txt")));
			EmployeeExternalize e1 = (EmployeeExternalize) inputStream
					.readObject();
			System.out.println(e1.toString());
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

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(this.name);// Chars(this.name);
		out.writeInt(this.age);

	}

	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		this.name = (String)in.readObject();
		this.age = in.readInt();

	}
}
