package com.ace.training.usecase;

import org.junit.Assert;
import org.junit.Test;

import com.ace.training.usecase.objectpool.Employee;
import com.ace.training.usecase.objectpool.EmployeeObjectFactory;
import com.ace.training.usecase.objectpool.EmployeeValidator;
import com.ace.training.usecase.objectpool.ObjectPool;
import com.ace.training.usecase.objectpool.ObjectPoolImpl;

public class ObjectPoolTest {
	@Test
	public void testPool() {
		EmployeeObjectFactory objectFactory = new EmployeeObjectFactory();
		EmployeeValidator validator = new EmployeeValidator();
		ObjectPool<Employee> objectPool = new ObjectPoolImpl<>(validator,
				objectFactory, 5);

		Employee e1 = objectPool.get();
		Assert.assertNotNull(e1);
		System.out.println("Employee E1" + e1);
		Employee e2 = objectPool.get();
		Assert.assertNotNull(e2);
		System.out.println("Employee E2" + e2);
		Employee e3 = objectPool.get();
		Assert.assertNotNull(e3);
		System.out.println("Employee E3" + e3);

		Employee e4 = objectPool.get();
		Assert.assertNotNull(e4);
		System.out.println("Employee E4" + e4);
		Employee e5 = objectPool.get();
		Assert.assertNotNull(e5);
		System.out.println("Employee E5" + e5);
		Employee e6 = objectPool.get();
		Assert.assertNull(e6);
		System.out.println("Employee E6, should be nulll:->" + e6);
		objectPool.release(e5);
		System.out.println("E5 released");
		e6 = objectPool.get();
		Assert.assertNotNull(e6);
		System.out
				.println("Employee E6, should not be null because E5 release:->"
						+ e6);

	}

}
