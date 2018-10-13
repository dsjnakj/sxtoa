package com.yjh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yjh.dao.ExpenseDao;
import com.yjh.entity.Employee;
import com.yjh.entity.Expense;
import com.yjh.entity.ExpenseItem;
import com.yjh.entity.Payment;
import com.yjh.util.DBUtil;
import com.yjh.util.DBUtil2;

public class ExpenseDaoImpl implements ExpenseDao{

	@Override
	public void save(Expense expense) {
		String sql = "insert into expense values (?,?,?,?,?,?,?,?)";
		Object[] params = {
				expense.getExpId(),
				expense.getEmpId(),
				expense.getTotalAmount(),
				new java.sql.Date(expense.getExpTime().getTime()),
				expense.getExpDesc(),
				expense.getNextAuditorId(),
				expense.getLastResult(),
				expense.getStatus()
		};
		//dao层也要使用DBUtil2，保证connection对象不会被关闭
		DBUtil2.executeUpdate(sql, params);
	}

	@Override
	public int nextVal() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int nextVal = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "select seq_expense.nextval from dual";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				nextVal = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, ps, conn);
		}
		return nextVal;
	}

	@Override
	public List<Expense> findByAuditId(String nextAuditorId) {
		// sql语句
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				//获取签到对象的集合
				List<Expense> list  = new ArrayList<Expense>();
				try {
					conn = DBUtil.getConnection();
					//获取的信息	员工信息  部门信息  签到信息
					String sql = "select exp.*, emp.realname from expense exp join employee emp on exp.empid = emp.empid where exp.nextauditor = ?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, nextAuditorId);
					rs = ps.executeQuery();
					while (rs.next()) {// 查到信息
						int expId = rs.getInt("expid");
						String empId = rs.getString("empid");
						double totalAmount = rs.getDouble("totalAmount");
						Date expTime = rs.getDate("expTime");
						String expDesc = rs.getString("expDesc");
						String lastResult = rs.getString("lastResult");
						String status = rs.getString("status");
						Employee employee = new Employee();
						employee.setRealName(rs.getString("realName"));
						Expense expense = new Expense(expId,empId, totalAmount, expTime, expDesc, nextAuditorId, lastResult, status,employee);
						list.add(expense);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					DBUtil.closeAll(rs, ps, conn);
				}
				return list;
	}

	@Override
	public void update(Expense exp) {
		String sql = "update expense set nextAuditor =?,lastResult=?,status=? where expId = ?";
		Object[] params = {
				exp.getNextAuditorId(),//获取不到
				exp.getLastResult(),
				exp.getStatus(),
				exp.getExpId()
		};
		DBUtil2.executeUpdate(sql, params);
	}

	@Override
	public Expense findById(int expId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Expense exp = null;
		try {
			conn = DBUtil.getConnection();
			//获取的信息	员工信息  部门信息  签到信息
			String sql = "select * from expense where expId=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, expId);
			rs = ps.executeQuery();
			if (rs.next()) {// 查到信息
				String empId = rs.getString("empid");
				double totalAmount = rs.getDouble("totalAmount");
				Date expTime = rs.getDate("expTime");
				String expDesc = rs.getString("expDesc");
				String lastResult = rs.getString("lastResult");
				String status = rs.getString("status");
				String nextAuditorId = rs.getString("nextAuditor");
				exp = new Expense(expId,empId, totalAmount, expTime, expDesc, nextAuditorId, lastResult, status,null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, ps, conn);
		}
		return exp;
		
		
	}

	@Override
	public List<Payment> findPay(String startTime, String endTime,
			String payName, String type) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//获取签到对象的集合
		List<Payment> list  = new ArrayList<Payment>();
		try {
			conn = DBUtil.getConnection();
			//获取的信息	员工信息  部门信息  签到信息
			StringBuilder sql =new StringBuilder( "select item.type,item.amount,emp1.realname,item.itemdesc,emp2.realname,pay.paytime from "
					+"payment pay "
					+"join expenseitem item "
					+"on pay.expid = item.expid "
					+"join employee emp1 "
					+"on pay.payempid = emp1.empid "
					+"join employee emp2 "
					+"on pay.empid = emp2.empid "
					+"where 1=1 ");
			//接下来要判断参数是否为空，合理设置条件,这里可以写个方法，这样传入参数判断更方便
			if(startTime!=null&&(!"".equals(startTime))){
				sql.append(" and to_char(pay.paytime,'YYYY-MM-DD HH:MI:SS') >= '"+startTime+"' ");
			}
			if(endTime!=null&&(!"".equals(endTime))){
				sql.append(" and to_char(pay.paytime,'YYYY-MM-DD HH:MI:SS') < '"+endTime+"' ");
			}
			if(payName!=null&&(!"".equals(payName))){
				sql.append(" and emp1.realname like '%"+payName+"%'");
			}
			if(type!=null&&(!"".equals(type))){
				sql.append(" and item.type = '"+type+"'");
			}
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {// 查到信息
				String type1 = rs.getString(1);
				double amount = rs.getDouble(2);
				String payName1 = rs.getString(3);
				String itemDesc = rs.getString(4);
				String expName = rs.getString(5);
				Date payTime = rs.getTimestamp(6);//日期转换交给Gson处理
				
				
				//报销详情
				ExpenseItem item = new ExpenseItem();
				item.setType(type1);
				item.setAmout(amount);
				item.setItemDesc(itemDesc);
				
				//报销人
				Employee expEmp = new Employee();
				expEmp.setRealName(expName);
				
				//支出人
				Employee payEmp2 = new Employee();
				payEmp2.setRealName(payName1);
				
				//填入支出对象
				Payment pay = new Payment();
				pay.setItem(item);
				pay.setExpEmp(expEmp);
				pay.setPayEmp(payEmp2);
				pay.setPayTime(payTime);
				
				//载入集合
				list.add(pay);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, ps, conn);
		}
		return list;
	}

}
