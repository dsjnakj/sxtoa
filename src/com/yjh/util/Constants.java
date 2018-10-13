package com.yjh.util;
/**
 * 常量工具类
 * @author Administrator
 *
 */
public class Constants {
	//报销单状态
		//新创建
	public static final String EXPENSE_STATUS_NEW = "0";
		//审核中
	public static final String EXPENSE_STATUS_AUDITING = "1";
		//审核通过
	public static final String EXPENSE_STATUS_PASS = "2";
		//审核拒绝
	public static final String EXPENSE_STATUS_REJECT = "3";
		//审核打回
	public static final String EXPENSE_STATUS_BACK = "4";
		//已打款
	public static final String EXPENSE_STATUS_CASHED = "5";
	
	//特殊岗位员工编号
		//总裁
	public static final String POSITION_CEO = "gaoqi";
		//财务总监
	public static final String POSITION_CFO = "liyuying";
}
