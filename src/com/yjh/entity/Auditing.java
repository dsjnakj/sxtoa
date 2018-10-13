package com.yjh.entity;

import java.io.Serializable;
import java.util.Date;

public class Auditing implements Serializable{
	private int auditId;//审核表id 自增序列
	private int expId;//报销单id
	private String empId;//审核人id
	private String result;//审核结果
	private String auditDesc;//审核信息
	private Date time;//审核时间
	
	//扩展
	private Expense expense;//报销单对象，方便获取报销单的所有数据
	private Employee auditor;//审核人
	public int getAuditId() {
		return auditId;
	}
	public void setAuditId(int auditId) {
		this.auditId = auditId;
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
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getAuditDesc() {
		return auditDesc;
	}
	public void setAuditDesc(String auditDesc) {
		this.auditDesc = auditDesc;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Expense getExpense() {
		return expense;
	}
	public void setExpense(Expense expense) {
		this.expense = expense;
	}
	public Employee getAuditor() {
		return auditor;
	}
	public void setAuditor(Employee auditor) {
		this.auditor = auditor;
	}
	public Auditing() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Auditing(int expId, String empId, String result, String auditDesc,
			Date time) {
		super();
		this.expId = expId;
		this.empId = empId;
		this.result = result;
		this.auditDesc = auditDesc;
		this.time = time;
	}
	
	
	public Auditing(int expId, String result, String auditDesc, Date time,
			Employee auditor) {
		super();
		this.expId = expId;
		this.result = result;
		this.auditDesc = auditDesc;
		this.time = time;
		this.auditor = auditor;
	}
	public Auditing(String result, String auditDesc, Date time,
			Expense expense, Employee auditor) {
		super();
		this.result = result;
		this.auditDesc = auditDesc;
		this.time = time;
		this.expense = expense;
		this.auditor = auditor;
	}
	public Auditing(int expId2, String result2, String auditDesc2,
			Employee auditor2, Date now) {
		this.expId = expId2;
		this.result = result2;
		this.auditDesc = auditDesc2;
		this.auditor = auditor2;
		this.time = now;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((auditDesc == null) ? 0 : auditDesc.hashCode());
		result = prime * result + auditId;
		result = prime * result + ((auditor == null) ? 0 : auditor.hashCode());
		result = prime * result + ((empId == null) ? 0 : empId.hashCode());
		result = prime * result + expId;
		result = prime * result + ((expense == null) ? 0 : expense.hashCode());
		result = prime * result
				+ ((this.result == null) ? 0 : this.result.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		Auditing other = (Auditing) obj;
		if (auditDesc == null) {
			if (other.auditDesc != null)
				return false;
		} else if (!auditDesc.equals(other.auditDesc))
			return false;
		if (auditId != other.auditId)
			return false;
		if (auditor == null) {
			if (other.auditor != null)
				return false;
		} else if (!auditor.equals(other.auditor))
			return false;
		if (empId == null) {
			if (other.empId != null)
				return false;
		} else if (!empId.equals(other.empId))
			return false;
		if (expId != other.expId)
			return false;
		if (expense == null) {
			if (other.expense != null)
				return false;
		} else if (!expense.equals(other.expense))
			return false;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Auditing [auditId=" + auditId + ", expId=" + expId + ", empId="
				+ empId + ", result=" + result + ", auditDesc=" + auditDesc
				+ ", time=" + time + ", expense=" + expense + ", auditor="
				+ auditor + "]";
	}
	
	
}
