package com.yjh.test;

import java.util.List;

import org.junit.Test;

import com.yjh.entity.Employee;
import com.yjh.service.EmployeeService;
import com.yjh.service.impl.EmployeeServiceImpl;

public class TestEmployeeService {
	
	@Test
	public void testFindAll(){
		EmployeeService es = new EmployeeServiceImpl();
		List<Employee> empList = es.findAll();
		for (Employee employee : empList) {
			System.out.println(employee);
		}
	}
	
}
