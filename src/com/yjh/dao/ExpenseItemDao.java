package com.yjh.dao;

import com.yjh.entity.ExpenseItem;

public interface ExpenseItemDao {

	/**
	 * 添加报销单明细（从表）
	 * @param expenseItem
	 */
	void save(ExpenseItem expenseItem);

}
