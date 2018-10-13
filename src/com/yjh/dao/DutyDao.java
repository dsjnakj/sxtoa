package com.yjh.dao;

import java.util.List;

import com.yjh.entity.Duty;

public interface DutyDao {

	/**
	 * 查询数据是否存在
	 * @param empId
	 * @param today
	 * @return
	 */
	boolean find(String empId, java.sql.Date today);

	/**
	 * 添加数据
	 * @param duty
	 * @return
	 */
	int save(Duty duty);

	/**
	 * 更新数据
	 * @param duty
	 * @return
	 */
	int update(Duty duty);

	/**
	 * 查询相应的签到信息
	 * @param empid
	 * @param deptno
	 * @param dtdate
	 * @return
	 */
	List<Duty> findDuty(String empid, int deptno, java.sql.Date dtdate);

}
