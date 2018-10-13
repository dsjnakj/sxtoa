package com.yjh.service.impl;

import java.util.List;

import com.yjh.dao.PositionDao;
import com.yjh.dao.impl.PositionDaoImpl;
import com.yjh.entity.Position;
import com.yjh.service.PositionService;

public class PositionServiceImpl implements PositionService{

	//创建dao层对象
	PositionDao pd = new PositionDaoImpl();
	@Override
	public List<Position> findAll() {
		// TODO Auto-generated method stub
		return pd.findAll();
	}
	@Override
	public int add(Position position) {
		// TODO Auto-generated method stub
		return pd.add(position);
	}
	@Override
	public int update(Position position) {
		// TODO Auto-generated method stub
		return pd.update(position);
	}
	@Override
	public Position findById(int posId) {
		// TODO Auto-generated method stub
		return pd.findById(posId);
	}
	@Override
	public int delete(int posId) {
		// TODO Auto-generated method stub
		return pd.delete(posId);
	}

}
