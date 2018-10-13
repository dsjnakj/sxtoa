package com.yjh.service;

import java.sql.Date;
import java.util.List;

import com.yjh.entity.Duty;

public interface DutyService {

	/**
	 * 签到
	 * @param empId
	 * @return
	 */
	int signin(String empId);

	/**
	 * 签退
	 * @param empId
	 * @return
	 */
	int signout(String empId);

	/**
	 * 查询符合条件的对象
	 * @param empid	员工id
	 * @param deptno 部门编号
	 * @param dtdate 签到日期
	 * @return
	 */
	List<Duty> findDuty(String empid, int deptno, Date dtdate);

}
