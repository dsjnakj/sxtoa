package com.yjh.dao;

import java.util.List;

import com.yjh.entity.Employee;

public interface EmployeeDao {

	/**
	 * 添加员工
	 * @param employee
	 * @return
	 */
	int save(Employee employee);

	/**
	 * 根据员工类型获取相应员工信息
	 * @param type
	 * @return
	 */
	List<Employee> findByType(int type);

	/**
	 * 根据id查询员工信息
	 * @param empId
	 * @return
	 */
	Employee findById(String empId);

	/**
	 * 查询所有
	 * @return
	 */
	List<Employee> findAll();

	/**
	 * 查询所有
	 * @param empId
	 * @param deptno
	 * @param onDuty
	 * @param hireDate
	 * @return
	 */
	List<Employee> find(String empId, String deptno, String onDuty,
			String hireDate);

	/**
	 * 删除员工
	 * @param empId
	 * @return
	 */
	int delete(String empId);

	/**
	 * 更新员工信息
	 * @param employee
	 * @return
	 */
	int update(Employee employee);

	/**
	 * 登录操作
	 * @param empId
	 * @return
	 */
	Employee login(String empId);

	/**
	 * 查找BOSS
	 * @param password
	 * @return
	 */
	Employee findBoss(String password);

}
