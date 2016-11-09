package com.ace.training.usecase.objectpool;

public class ObjectPoolTestUtil {
	public static void main(String[] args) {
		EmployeeObjectFactory objectFactory = new EmployeeObjectFactory();
		EmployeeValidator validator = new EmployeeValidator();
		ObjectPool<Employee> objectPool = new ObjectPoolImpl<>(validator,
				objectFactory, 5);
		
		Employee e1 = objectPool.get();
		System.out.println("Employee E1"+ e1 );
		Employee e2 = objectPool.get();
		System.out.println("Employee E2"+ e2 );
		Employee e3 = objectPool.get();
		System.out.println("Employee E3"+ e3 );
		
		Employee e4 = objectPool.get();
		System.out.println("Employee E4"+ e4 );
		Employee e5= objectPool.get();
		System.out.println("Employee E5"+ e5 );
		Employee e6= objectPool.get();
		System.out.println("Employee E6, should be nulll:->"+ e6 );
		objectPool.release(e5);
		System.out.println("E5 released");
		 e6= objectPool.get();
		System.out.println("Employee E6, should not be null because E5 release:->"+ e6 );
		
	}

}
