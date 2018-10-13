package com.yjh.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.yjh.dao.DutyDao;
import com.yjh.dao.impl.DutyDaoImpl;
import com.yjh.entity.Duty;
import com.yjh.service.DutyService;

public class DutyServiceImpl implements DutyService{
	
	//创建dao层对象
	DutyDao dd = new DutyDaoImpl();
	//创建HH:mm:ss时间转换
	DateFormat df = new SimpleDateFormat("HH:mm:ss");

	@Override
	public int signin(String empId) {
		//获取当前日期
		Date now = new Date();
		//获取当前	年 月 日	yyyy-mm-dd
		java.sql.Date today = new java.sql.Date(now.getTime());
		//判断是否已经签到
		boolean flag = dd.find(empId,today);
		//0 签到失败	1 签到成功	 2 已经签到
		if(flag){//已经签到
			return 2;
		}else{//未签到
			//获取当前签到时 分 秒	HH:mm:ss
			String signInTime = df.format(now);
			//创建Duty对象
			Duty duty = new Duty();
			duty.setEmprId(empId);
			duty.setDtDate(today);
			duty.setSignInTime(signInTime);
			//返回0证明数据添加失败，返回1证明数据添加成功，即签到成功
			return dd.save(duty);
		}
	}

	//签退
	@Override
	public int signout(String empId) {
		//获取当前日期
				Date now = new Date();
				//获取当前	年 月 日	yyyy-mm-dd
				java.sql.Date today = new java.sql.Date(now.getTime());
				//判断是否已经签到
				boolean flag = dd.find(empId,today);
				//0 签退失败	1 签退成功	 2 尚未签到
				if(!flag){//尚未签到
					return 2;
				}else{//已签到
					//获取当前签到时 分 秒	HH:mm:ss
					String signOutTime = df.format(now);
					//创建Duty对象
					Duty duty = new Duty();
					duty.setEmprId(empId);
					duty.setDtDate(today);
					duty.setSignOutTime(signOutTime);
					//返回0证明数据添加失败，返回1证明数据添加成功，即签到成功
					return dd.update(duty);
				}
	}

	//查询相应的签到信息
	@Override
	public List<Duty> findDuty(String empid, int deptno, java.sql.Date dtdate) {
		// TODO Auto-generated method stub
		return dd.findDuty(empid,deptno,dtdate);
	}

}
