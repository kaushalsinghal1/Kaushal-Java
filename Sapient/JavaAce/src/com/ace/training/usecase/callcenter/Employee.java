package com.ace.training.usecase.callcenter;

public class Employee {
	private String name;
	protected String designation;
	private String Address;

	public Employee(String designation, String name ) {
		this.designation = designation;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

}
