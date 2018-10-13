package com.yjh.dao;

import java.util.List;

import com.yjh.entity.Payment;

public interface PaymentDao {

	//生成支出记录
	void save(Payment pay);

	//获取所有支出记录
	List<Payment> findAll(int type);
}
