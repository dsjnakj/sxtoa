package com.yjh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yjh.dao.PositionDao;
import com.yjh.entity.Position;
import com.yjh.util.DBUtil;

public class PositionDaoImpl implements PositionDao{

	@Override
	public List<Position> findAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Position> list = new ArrayList<Position>();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from position";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				// 获取部门属性
				int posId = rs.getInt("posid");
				String pName = rs.getString("pname");
				String pDesc = rs.getString("pdesc");
				// 创建部门对象接收
				Position position = new Position();
				position.setPosId(posId);
				position.setpName(pName);
				position.setpDesc(pDesc);
				// 存到集合中
				list.add(position);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, ps, conn);
		}
		return list;
	}

	@Override
	public int add(Position position) {
		String sql = "insert into position values (?,?,?)";
		Object[] params = {position.getPosId(),position.getpName(),position.getpDesc()};
		return DBUtil.executeUpdate(sql, params);
	}

	@Override
	public int update(Position position) {
		String sql = "update position set pname=?,pdesc=? where posid=?";
		Object[] params = {position.getpName(),position.getpDesc(),position.getPosId()};
		return DBUtil.executeUpdate(sql, params);
	}

	@Override
	public Position findById(int posId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Position position = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from position where posid=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, posId);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				String pName = rs.getString("pname");
				String pDesc = rs.getString("pdesc");
				// 创建部门对象接收
				position = new Position(posId, pName, pDesc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, ps, conn);
		}
		return position;
	}

	@Override
	public int delete(int posId) {
		String sql = "delete from position where posid=?";
		Object[] params = {posId};
		return DBUtil.executeUpdate(sql, params);
	}

}
