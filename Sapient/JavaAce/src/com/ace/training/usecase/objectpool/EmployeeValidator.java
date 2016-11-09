package com.ace.training.usecase.objectpool;

public class EmployeeValidator implements Validator<Employee> {

	@Override
	public boolean isValid(Employee obj) {
		return false;
	}

	@Override
	public void invalidate(Employee obj) {
		
	}

}
