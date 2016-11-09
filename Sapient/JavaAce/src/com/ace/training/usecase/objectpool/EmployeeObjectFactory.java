package com.ace.training.usecase.objectpool;

public class EmployeeObjectFactory implements ObjectFactory<Employee> {

	@Override
	public Employee createNew() {
		return new Employee();
	}

}
