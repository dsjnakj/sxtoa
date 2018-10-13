package com.yjh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yjh.dao.PaymentDao;
import com.yjh.entity.ExpenseItem;
import com.yjh.entity.Payment;
import com.yjh.util.DBUtil;
import com.yjh.util.DBUtil2;
import com.yjh.util.DateUtil;

public class PaymentDaoImpl implements PaymentDao{

	//使用DBUtil2，因为需要进行多事务处理
	
	@Override
	public void save(Payment pay) {
		String sql = "insert into payment values (seq_payment.nextval,?,?,?,?,?)";
		Object[] params = {
				pay.getPayEmpId(),
				pay.getAmount(),//下面需要进行格式处理，要存储为精确时间
				new java.sql.Timestamp(pay.getPayTime().getTime()),
				pay.getExpId(),
				pay.getEmpId()
		};
		DBUtil2.executeUpdate(sql, params);
	}

	/**
	 * 获取所有支出记录
	 */
	@Override
	public List<Payment> findAll(int type) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Payment> list  = new ArrayList<Payment>();
		try{
			conn = DBUtil.getConnection();
			StringBuilder sql =new StringBuilder("select item.type,sum(item.amount)"
					+ " from payment pay"
					+ " join expenseitem item"
					+ " on pay.expid = item.expid"
					+ " where 1=1");
			if(type==1){//本月
				sql.append(" and to_char(pay.paytime,'YYYY-MM-DD HH:MI:SS') > '"+DateUtil.getNowMonthBeginTime()+"'");
			}else if(type==2){//今年上半年
				sql.append(" and to_char(pay.paytime,'YYYY-MM-DD HH:MI:SS') > '"+DateUtil.getNowYearBeginTime()+"'");
				sql.append(" and to_char(pay.paytime,'YYYY-MM-DD HH:MI:SS') <= '"+DateUtil.getNowYearEndTime()+"'");
			}else if(type==3){//去年全年
				sql.append(" and to_char(pay.paytime,'YYYY-MM-DD HH:MI:SS') > '"+DateUtil.getLastYearBeginTime()+"'");
				sql.append(" and to_char(pay.paytime,'YYYY-MM-DD HH:MI:SS') <= '"+DateUtil.getLastYearEndTime()+"'");
			}
			sql.append(" group by item.type");
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while(rs.next()){
				String type1 = rs.getString(1);
				double amount = rs.getDouble(2);
				ExpenseItem item = new ExpenseItem();
				item.setType(type1);
				item.setAmout(amount);
				
				Payment payment = new Payment();
				payment.setItem(item);
				list.add(payment);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(rs, ps, conn);
		}
		return list;
	}

}
