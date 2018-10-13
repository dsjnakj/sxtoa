package com.yjh.service;

import java.util.List;

import com.yjh.entity.Employee;

public interface EmployeeService {

	/**
	 * 添加员工
	 * @param employee
	 * @return
	 */
	int save(Employee employee);

	/**
	 * 根据员工类型获取相应员工信系
	 * @param type
	 * @return
	 */
	List<Employee> findEmpByType(int type);

	/**
	 * 根据id获取员工信息
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
	 * 根据条件查询员工	不加分页
	 * @param empId
	 * @param deptno
	 * @param onDuty
	 * @param hireDate
	 * @return
	 */
	List<Employee> findEmp(String empId, String deptno, String onDuty,
			String hireDate);

	/**
	 * 删除员工
	 * @param empId
	 * @return
	 */
	int delete(String empId);

	/**
	 * 修改员工信息
	 * @param employee
	 * @return
	 */
	int update(Employee employee);

	/**
	 * 登录
	 * @param empId
	 * @param password
	 * @return
	 */
	Employee login(String empId, String password);

	/**
	 * Boss登录
	 * @param password
	 * @return
	 */
	Employee loginBoss(String password);

}
