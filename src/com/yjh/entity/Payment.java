package com.yjh.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 支出类
 * @author Administrator
 *
 */
public class Payment implements Serializable{
	
	private int pid;//支出编号
	private String payEmpId;//支出人员工编号  liyuying
	private double amount;//支出金额
	private Date payTime;//支出时间
	private int expId;//报销单id
	private String empId;//报销人员工编号
	
	private Employee payEmp;//支付人
	private Employee expEmp;//报销人
	private Expense exp;//报销单
	private ExpenseItem item;//报销详情
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPayEmpId() {
		return payEmpId;
	}
	public void setPayEmpId(String payEmpId) {
		this.payEmpId = payEmpId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public int getExpId() {
		return expId;
	}
	public void setExpId(int expId) {
		this.expId = expId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public Employee getPayEmp() {
		return payEmp;
	}
	public void setPayEmp(Employee payEmp) {
		this.payEmp = payEmp;
	}
	public Employee getExpEmp() {
		return expEmp;
	}
	public void setExpEmp(Employee expEmp) {
		this.expEmp = expEmp;
	}
	public Expense getExp() {
		return exp;
	}
	public void setExp(Expense exp) {
		this.exp = exp;
	}
	public ExpenseItem getItem() {
		return item;
	}
	public void setItem(ExpenseItem item) {
		this.item = item;
	}
	@Override
	public String toString() {
		return "Payment [pid=" + pid + ", payEmpId=" + payEmpId + ", amount="
				+ amount + ", payTime=" + payTime + ", expId=" + expId
				+ ", empId=" + empId + "]";
	}
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Payment(int pid, String payEmpId, double amount, Date payTime,
			int expId, String empId, Employee payEmp, Employee expEmp,
			Expense exp) {
		super();
		this.pid = pid;
		this.payEmpId = payEmpId;
		this.amount = amount;
		this.payTime = payTime;
		this.expId = expId;
		this.empId = empId;
		this.payEmp = payEmp;
		this.expEmp = expEmp;
		this.exp = exp;
	}
	public Payment(String payEmpId, double amount, Date payTime, int expId,
			String empId) {
		super();
		this.payEmpId = payEmpId;
		this.amount = amount;
		this.payTime = payTime;
		this.expId = expId;
		this.empId = empId;
	}
	
	
	
}
