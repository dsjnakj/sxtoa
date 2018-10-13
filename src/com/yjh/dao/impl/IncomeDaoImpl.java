package com.yjh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yjh.dao.IncomeDao;
import com.yjh.entity.Employee;
import com.yjh.util.DBUtil;
import com.yjh.util.DateUtil;

public class IncomeDaoImpl implements IncomeDao{

	@Override
	public List<Object[]> findStaticData() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Object[]> list = new ArrayList<Object[]>();
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql =new StringBuilder("select ictype,sum(amount) from income ");
			sql.append("group by ictype");
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				String ictype = rs.getString(1);
				double amount = rs.getDouble(2);
				Object[] obj = {ictype,amount};
				// 存到集合中
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, ps, conn);
		}
		return list;
	}

}
