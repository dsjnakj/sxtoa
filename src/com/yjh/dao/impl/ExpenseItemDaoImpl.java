package com.yjh.dao.impl;

import com.yjh.dao.ExpenseItemDao;
import com.yjh.entity.ExpenseItem;
import com.yjh.util.DBUtil2;

public class ExpenseItemDaoImpl implements ExpenseItemDao{

	@Override
	public void save(ExpenseItem expenseItem) {
		String sql = "insert into expenseitem values (seq_item.nextval,?,?,?,?)";
		Object[] params = {
				expenseItem.getExpId(),
				expenseItem.getType(),
				expenseItem.getAmout(),
				expenseItem.getItemDesc(),
		};
		DBUtil2.executeUpdate(sql, params);
	}

}
