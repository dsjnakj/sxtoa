package com.yjh.service;

import java.util.List;

import com.yjh.entity.Department;

public interface DepartmentService {
	/**
	 * 添加部门
	 * @param dept
	 * @return
	 */
	public int add(Department dept);

	/**
	 * 查询所有部门信息
	 * @return
	 */
	public List<Department> findAll();

	/**
	 * 删除部门
	 * @param deptno
	 * @return
	 */
	public int delete(int deptno);
	
	/**
	 * 查询部门信息
	 * @param deptno
	 * @return
	 */
	public Department findById(int deptno);

	/**
	 * 修改部门信息
	 * @param dept
	 * @return
	 */
	public int update(Department dept);

	
}
