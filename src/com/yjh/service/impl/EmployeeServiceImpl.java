package com.yjh.service.impl;

import java.util.List;

import com.yjh.dao.EmployeeDao;
import com.yjh.dao.impl.EmployeeDaoImpl;
import com.yjh.entity.Employee;
import com.yjh.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService{

	//创建dao层对象
	EmployeeDao ed = new EmployeeDaoImpl();
	@Override
	public int save(Employee employee) {
		// TODO Auto-generated method stub
		return ed.save(employee);
	}
	@Override
	public List<Employee> findEmpByType(int type) {
		// TODO Auto-generated method stub
		return ed.findByType(type);
	}
	@Override
	public Employee findById(String empId) {
		// TODO Auto-generated method stub
		return ed.findById(empId);
	}
	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return ed.findAll();
	}
	@Override
	public List<Employee> findEmp(String empId, String deptno, String onDuty,
			String hireDate) {
		// TODO Auto-generated method stub
		return ed.find(empId,deptno,onDuty,hireDate);
	}
	@Override
	public int delete(String empId) {
		// TODO Auto-generated method stub
		return ed.delete(empId);
	}
	@Override
	public int update(Employee employee) {
		// TODO Auto-generated method stub
		return ed.update(employee);
	}
	@Override
	public Employee login(String empId, String password) {
		//登录实现：
		//方法1：用户名密码同时判断
		//Employee emp = ed.findEmp(empId,password);
		//方法2：先判断用户名，再判断密码
		Employee emp = ed.findById(empId);
		if(emp!=null){//用户名正确
			if(password!=null&&password.equals(emp.getPassword())){
				//密码正确
				return emp;
			}else{//密码错误
				return null;
			}
		}else{//用户名错误
			return null;
		}
	}
	@Override
	public Employee loginBoss(String password) {
		Employee emp = ed.findBoss(password);
		if(emp!=null){//用户名正确
			if(password!=null&&password.equals(emp.getPassword())){
				//密码正确
				return emp;
			}else{//密码错误
				return null;
			}
		}else{//用户名错误
			return null;
		}
	}

}
