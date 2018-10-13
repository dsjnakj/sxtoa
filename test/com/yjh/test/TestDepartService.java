package com.yjh.test;

import org.junit.Ignore;
import org.junit.Test;

import com.yjh.entity.Department;
import com.yjh.service.DepartmentService;
import com.yjh.service.impl.DepartmentServiceImpl;

/**
 * 测试部门业务
 * @author Administrator
 *	@Ignore 忽略测试
 *	@Test	进行测试
 */
public class TestDepartService {
	
	@Test//测试查询单个对象
	public void testFindById(){
		
		//创建业务层对象
		DepartmentService ds = new DepartmentServiceImpl();
		//调用业务层方法
		System.out.println(ds.findById(1));
	}
	
	@Test//测试删除
	public void testDelete(){
		
		//创建业务层对象
		DepartmentService ds = new DepartmentServiceImpl();
		//调用业务层方法
		System.out.println(ds.delete(4));
	}
	
	
	@Ignore//忽略测试
	@Test//测试添加
	public void testAdd(){
		//创建实体类对象
		Department department = new Department(1,"总裁办","502");
		//创建业务层对象
		DepartmentService ds = new DepartmentServiceImpl();
		//调用业务层方法
		System.out.println(ds.add(department));
	}
	
	@Test//测试查询
	public void testfindAll(){
		
		//创建业务层对象
		DepartmentService ds = new DepartmentServiceImpl();
		//调用业务层方法
		System.out.println(ds.findAll());
	}
}
