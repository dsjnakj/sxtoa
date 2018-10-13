package com.yjh.service.impl;

import java.util.List;

import com.yjh.dao.DepartmentDao;
import com.yjh.dao.impl.DepartmentDaoImpl;
import com.yjh.entity.Department;
import com.yjh.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService{

	//调用持久层对象
	DepartmentDao dd = new DepartmentDaoImpl();
	
	@Override//添加部门信息
	public int add(Department dept) {
		
		return dd.save(dept);
	}
	
	@Override//查询所有部门信息
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return dd.findAll();
	}

	@Override
	public int delete(int deptno) {
		// TODO Auto-generated method stub
		return dd.delete(deptno);
	}
	
	@Override
	public Department findById(int deptno) {
		// TODO Auto-generated method stub
		return dd.findById(deptno);
	}

	@Override
	public int update(Department dept) {
		// TODO Auto-generated method stub
		return dd.update(dept);
	}

	

	

}
