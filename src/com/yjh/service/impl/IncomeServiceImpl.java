package com.yjh.service.impl;

import java.util.List;

import com.google.gson.Gson;
import com.yjh.dao.IncomeDao;
import com.yjh.dao.PaymentDao;
import com.yjh.dao.impl.IncomeDaoImpl;
import com.yjh.dao.impl.PaymentDaoImpl;
import com.yjh.entity.Payment;
import com.yjh.service.IncomeService;

public class IncomeServiceImpl implements IncomeService{
	
	//创建dao层对象
	private IncomeDao id = new IncomeDaoImpl();
	//创建Gson对象
	private Gson gson = new Gson();
	//创建支出dao层对象
	private PaymentDao pd = new PaymentDaoImpl();

	@Override
	public String getBarData() {
		//调用dao层方法获取数据
		List< Object[] > list = id.findStaticData();
		//创建StringBuilder接收列名和列值
		StringBuilder ictypeArr = new StringBuilder("[");
		StringBuilder amountArr = new StringBuilder("[");
		for(int i=0;i<list.size();i++){
			if(i==list.size()-1){
				ictypeArr.append("\""+list.get(i)[0]+"\"");
				amountArr.append(list.get(i)[1]);
				break;
			}
			ictypeArr.append("\""+list.get(i)[0]+"\",");
			amountArr.append(list.get(i)[1]+",");
		}
		//["1","2"]
		//追加
		amountArr.append("]");
		ictypeArr.append("]");
		//转换成json字符串并返回
//		String jsonStr  = gson.toJson(list);
		String jsonStr = "["+ictypeArr.toString()+","+amountArr.toString()+"]";
		return jsonStr;
	}

	@Override
	public String getPieData(int type) {
		List<Payment> list = pd.findAll(type);
		StringBuilder json = new StringBuilder("[");
		for(int i=0;i<list.size();i++){
			
			if(i==list.size()-1){
				json.append("{value:"+list.get(i).getItem().getAmout()+",name:\""+list.get(i).getItem().getType()+"\"}");
			}else{
				json.append("{value:"+list.get(i).getItem().getAmout()+",name:\""+list.get(i).getItem().getType()+"\"},");
			}
		}
		json.append("]");
		return json.toString();
	}

}
