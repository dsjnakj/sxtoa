package com.yjh.dao;

import java.util.List;

import com.yjh.entity.Department;

public interface DepartmentDao {
	/**
	 * 保存
	 * @param dept
	 * @return
	 */
	public int save(Department dept);

	/**
	 * 查询所有部门
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
	 * 查询单个部门信息
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
