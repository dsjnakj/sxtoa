package com.yjh.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yjh.dao.DutyDao;
import com.yjh.entity.Department;
import com.yjh.entity.Duty;
import com.yjh.entity.Employee;
import com.yjh.util.DBUtil;

public class DutyDaoImpl implements DutyDao {

	@Override
	public boolean find(String empId, Date today) {
		// sql语句
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flag = false;// 未签到
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from duty where emprId = ? and dtdate = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, empId);
			ps.setDate(2, today);
			rs = ps.executeQuery();
			if (rs.next()) {// 已签到
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, ps, conn);
		}
		return flag;
	}

	@Override
	public int save(Duty duty) {
		String sql = "insert into duty values(seq_duty.nextval,?,?,?,null)";
		Object[] params = { duty.getEmprId(), duty.getDtDate(),
				duty.getSignInTime() };
		return DBUtil.executeUpdate(sql, params);
	}

	@Override
	public int update(Duty duty) {
		String sql = "update duty set signouttime = ? where emprid=? and dtdate=?";
		Object[] params = { duty.getSignOutTime(), duty.getEmprId(),
				duty.getDtDate(), };
		return DBUtil.executeUpdate(sql, params);
	}

	@Override
	public List<Duty> findDuty(String empid, int deptno, Date dtdate) {
		// sql语句
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//获取签到对象的集合
		List<Duty> list  = new ArrayList<Duty>();
		try {
			conn = DBUtil.getConnection();
			//获取的信息	员工信息  部门信息  签到信息
			StringBuilder sql = new StringBuilder("select du.*,emp.realname,de.deptname"+
							" from duty du"+
							" join employee emp"+
							" on du.emprid = emp.empid"+
							" join dept de"+
							" on emp.deptno = de.deptno"+
							" where 1=1 ");
			if(empid!=null&&!"".equals(empid)){
				sql.append("and du.emprid = '"+empid+"'");
			}
			if(deptno!=0){
				sql.append(" and emp.deptno="+deptno);
			}
			if(dtdate!=null){
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String sdtdate = df.format(dtdate);
				sql.append(" and to_char(du.dtdate,'YYYY-MM-DD')>='"+sdtdate+"'");
			}
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {// 查到信息
				int dtId = rs.getInt("dtid");
				java.sql.Date dtdate1 = rs.getDate("dtdate");
				String signinTime = rs.getString("signinTime");
				String signoutTime = rs.getString("signoutTime");
				
				String deptName = rs.getString("deptName");
				Department dept = new Department();
				dept.setDeptName(deptName);
				
				String empId = rs.getString("emprid");
				String realName = rs.getString("realName");
				Employee emp = new Employee(empId,realName,dept);
				
				Duty duty = new Duty(dtId,dtdate1,signinTime,signoutTime,emp);
				list.add(duty);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, ps, conn);
		}
		return list;
	}

}
