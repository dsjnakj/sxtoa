package com.yjh.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 报销单
 * @author Administrator
 *
 */
public class Expense implements Serializable{
	private int expId;//报销编号
	private String empId;//报销人id
	private double totalAmount;//报销单总金额
	private Date expTime;//报销时间  yyyy-MM-dd
	private String expDesc;//报销备注信息
	private String nextAuditorId;//下一审核人编号
	private String lastResult;//最新的审核结果，该数据在审核表中体现，在该表中虽然会造成数据冗余，但是可以增加效率
	private String status;//1：审核中 2 审核结束通过 3 审核结束驳回 4 返现
	
	private Employee emp;//报销人
	private Employee nextAuditor;//下一审核人
	
	private List<ExpenseItem> itemList;//各项明细
	@Override
	public String toString() {
		return "Expense [expId=" + expId + ", empId=" + empId
				+ ", totalAmount=" + totalAmount + ", expTime=" + expTime
				+ ", expDesc=" + expDesc + ", nextAuditorId=" + nextAuditorId
				+ ", lastResult=" + lastResult + ", status=" + status
				+ ", emp=" + emp + ", nextAuditor=" + nextAuditor + "]";
	}
	public Expense() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emp == null) ? 0 : emp.hashCode());
		result = prime * result + ((empId == null) ? 0 : empId.hashCode());
		result = prime * result + ((expDesc == null) ? 0 : expDesc.hashCode());
		result = prime * result + expId;
		result = prime * result + ((expTime == null) ? 0 : expTime.hashCode());
		result = prime * result
				+ ((lastResult == null) ? 0 : lastResult.hashCode());
		result = prime * result
				+ ((nextAuditor == null) ? 0 : nextAuditor.hashCode());
		result = prime * result
				+ ((nextAuditorId == null) ? 0 : nextAuditorId.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Expense other = (Expense) obj;
		if (emp == null) {
			if (other.emp != null)
				return false;
		} else if (!emp.equals(other.emp))
			return false;
		if (empId == null) {
			if (other.empId != null)
				return false;
		} else if (!empId.equals(other.empId))
			return false;
		if (expDesc == null) {
			if (other.expDesc != null)
				return false;
		} else if (!expDesc.equals(other.expDesc))
			return false;
		if (expId != other.expId)
			return false;
		if (expTime == null) {
			if (other.expTime != null)
				return false;
		} else if (!expTime.equals(other.expTime))
			return false;
		if (lastResult == null) {
			if (other.lastResult != null)
				return false;
		} else if (!lastResult.equals(other.lastResult))
			return false;
		if (nextAuditor == null) {
			if (other.nextAuditor != null)
				return false;
		} else if (!nextAuditor.equals(other.nextAuditor))
			return false;
		if (nextAuditorId == null) {
			if (other.nextAuditorId != null)
				return false;
		} else if (!nextAuditorId.equals(other.nextAuditorId))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (Double.doubleToLongBits(totalAmount) != Double
				.doubleToLongBits(other.totalAmount))
			return false;
		return true;
	}
	public Expense(String empId, double totalAmount, Date expTime,
			String expDesc, String nextAuditorId, String lastResult,
			String status) {
		super();
		this.empId = empId;
		this.totalAmount = totalAmount;
		this.expTime = expTime;
		this.expDesc = expDesc;
		this.nextAuditorId = nextAuditorId;
		this.lastResult = lastResult;
		this.status = status;
	}
	public Expense(String empId, double totalAmount, Date expTime,
			String expDesc, String nextAuditorId, String lastResult,
			String status, Employee emp, Employee nextAuditor) {
		super();
		this.empId = empId;
		this.totalAmount = totalAmount;
		this.expTime = expTime;
		this.expDesc = expDesc;
		this.nextAuditorId = nextAuditorId;
		this.lastResult = lastResult;
		this.status = status;
		this.emp = emp;
		this.nextAuditor = nextAuditor;
	}
	
	public Expense(int expId, String empId, double totalAmount, Date expTime,
			String expDesc, String nextAuditorId, String lastResult,
			String status, Employee emp, Employee nextAuditor) {
		super();
		this.expId = expId;
		this.empId = empId;
		this.totalAmount = totalAmount;
		this.expTime = expTime;
		this.expDesc = expDesc;
		this.nextAuditorId = nextAuditorId;
		this.lastResult = lastResult;
		this.status = status;
		this.emp = emp;
		this.nextAuditor = nextAuditor;
	}
	
	public Expense(int expId, String empId, double totalAmount, Date expTime,
			String expDesc, String nextAuditorId, String lastResult,
			String status, Employee emp) {
		super();
		this.expId = expId;
		this.empId = empId;
		this.totalAmount = totalAmount;
		this.expTime = expTime;
		this.expDesc = expDesc;
		this.nextAuditorId = nextAuditorId;
		this.lastResult = lastResult;
		this.status = status;
		this.emp = emp;
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
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Date getExpTime() {
		return expTime;
	}
	public void setExpTime(Date expTime) {
		this.expTime = expTime;
	}
	public String getExpDesc() {
		return expDesc;
	}
	public void setExpDesc(String expDesc) {
		this.expDesc = expDesc;
	}
	public String getNextAuditorId() {
		return nextAuditorId;
	}
	public void setNextAuditorId(String nextAuditorId) {
		this.nextAuditorId = nextAuditorId;
	}
	public String getLastResult() {
		return lastResult;
	}
	public void setLastResult(String lastResult) {
		this.lastResult = lastResult;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	public Employee getNextAuditor() {
		return nextAuditor;
	}
	public void setNextAuditor(Employee nextAuditor) {
		this.nextAuditor = nextAuditor;
	}
	public List<ExpenseItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<ExpenseItem> itemList) {
		this.itemList = itemList;
	}
	
	
	
	
}
