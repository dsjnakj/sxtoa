package com.yjh.service;

import java.util.List;

import com.yjh.entity.Auditing;
import com.yjh.entity.Expense;
import com.yjh.entity.Payment;

public interface ExpenseService {
	

	//添加报销单
	void add(Expense expense);

	//获取审核对象
	List<Expense> findAudit(String empId);

	//审核业务
	void audit(Auditing auditing);

	//查看支出
	List<Payment> findPay(String startTime, String endTime, String payName,
			String type);

}
