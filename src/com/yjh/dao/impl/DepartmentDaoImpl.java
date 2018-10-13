package com.yjh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yjh.dao.DepartmentDao;
import com.yjh.entity.Department;
import com.yjh.util.DBUtil;

public class DepartmentDaoImpl implements DepartmentDao {

	@Override
	// 添加
	public int save(Department dept) {
		// sql语句
		String sql = "insert into dept values (?,?,?)";
		// 获取参数数组
		Object[] params = { dept.getDeptno(), dept.getDeptName(),
				dept.getLocation() };
		// 返回DML结果
		return DBUtil.executeUpdate(sql, params);
	}

	@Override
	// 查询所有
	public List<Department> findAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Department> list = new ArrayList<Department>();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from dept";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				// 获取部门属性
				int deptno = rs.getInt("deptno");
				String deptName = rs.getString("deptName");
				String location = rs.getString("location");
				// 创建部门对象接收
				Department dept = new Department(deptno, deptName, location);
				// 存到集合中
				list.add(dept);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, ps, conn);
		}
		return list;
	}

	@Override
	public int delete(int deptno) {
		// sql语句
		String sql = "delete from dept where deptno = ?";
		// 获取参数数组
		Object[] params = { deptno };
		// 返回DML结果
		return DBUtil.executeUpdate(sql, params);
	}

	@Override
	public Department findById(int deptno) {
		// sql语句
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Department dept = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from dept where deptno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, deptno);
			rs = ps.executeQuery();
			while (rs.next()) {
				// 获取部门属性
				String deptName = rs.getString("deptName");
				String location = rs.getString("location");
				// 创建部门对象接收
				dept = new Department(deptno, deptName, location);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, ps, conn);
		}
		return dept;
	}

	@Override
	public int update(Department dept) {
		// sql语句
		String sql = "update dept set deptName = ? ,location =? where deptno = ?";
		// 获取参数数组
		Object[] params = { dept.getDeptName(),
				dept.getLocation(), dept.getDeptno() };
		// 返回DML结果
		return DBUtil.executeUpdate(sql, params);
	}

}
