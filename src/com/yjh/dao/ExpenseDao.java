package com.yjh.dao;

import java.util.List;

import com.yjh.entity.Expense;
import com.yjh.entity.Payment;

public interface ExpenseDao {

	/**
	 * 添加报销单（主表）
	 * @param expense
	 */
	void save(Expense expense);
	
	//获取序列号
	int nextVal();

	/**
	 * 获取待审信息
	 * @param empId
	 * @return
	 */
	List<Expense> findByAuditId(String empId);
	
	/**
	 * 修改报销单信息
	 * @param nextAuditorId	下一审核人
	 * @param lastResult	最终结果
	 * @param status		审核状态
	 * @param empId			报销人
	 */
	void update(Expense exp);

	/**
	 * 获取报销单
	 * @param expId	报销单编号
	 */
	Expense findById(int expId);

	/**
	 * 查看支出
	 * @param startTime	时间范围 开始
	 * @param endTime	时间范围 结束
	 * @param payName	支出人
	 * @param type		报销类型
	 * @return
	 */
	List<Payment> findPay(String startTime, String endTime, String payName,
			String type);

}
