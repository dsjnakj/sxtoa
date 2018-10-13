package com.yjh.service;

import java.util.List;

import com.yjh.entity.Position;

public interface PositionService {

	/**
	 * 查询所有岗位
	 * @return
	 */
	List<Position> findAll();

	/**
	 * 添加岗位
	 * @param position
	 * @return
	 */
	int add(Position position);

	/**
	 * 修改岗位信息
	 * @param position
	 * @return
	 */
	int update(Position position);

	/**
	 * 查询单个对象
	 * @param posId
	 * @return
	 */
	Position findById(int posId);

	/**
	 * 删除岗位
	 * @param posId
	 * @return
	 */
	int delete(int posId);
	
}
