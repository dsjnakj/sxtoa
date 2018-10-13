package com.yjh.entity;

import java.io.Serializable;
import java.util.Date;

public class Duty implements Serializable{
	private int dtId;
	private String emprId;
	private Date dtDate;
	private String signInTime;
	private String signOutTime;
	private Employee emp;
	public Duty() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Duty(int dtId, String emprId, Date dtDate, String signInTime,
			String signOutTime) {
		super();
		this.dtId = dtId;
		this.emprId = emprId;
		this.dtDate = dtDate;
		this.signInTime = signInTime;
		this.signOutTime = signOutTime;
	}
	public Duty(int dtId2, java.sql.Date dtdate1, String signinTime2,
			String signoutTime2, Employee emp2) {
		this.dtId = dtId2;
		this.dtDate = dtdate1;
		this.signInTime = signinTime2;
		this.signOutTime = signoutTime2;
		this.emp = emp2;
	}
	@Override
	public String toString() {
		return "Duty [dtId=" + dtId + ", emprId=" + emprId + ", dtDate="
				+ dtDate + ", signInTime=" + signInTime + ", signOutTime="
				+ signOutTime + ", emp=" + emp + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dtDate == null) ? 0 : dtDate.hashCode());
		result = prime * result + dtId;
		result = prime * result + ((emp == null) ? 0 : emp.hashCode());
		result = prime * result + ((emprId == null) ? 0 : emprId.hashCode());
		result = prime * result
				+ ((signInTime == null) ? 0 : signInTime.hashCode());
		result = prime * result
				+ ((signOutTime == null) ? 0 : signOutTime.hashCode());
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
		Duty other = (Duty) obj;
		if (dtDate == null) {
			if (other.dtDate != null)
				return false;
		} else if (!dtDate.equals(other.dtDate))
			return false;
		if (dtId != other.dtId)
			return false;
		if (emp == null) {
			if (other.emp != null)
				return false;
		} else if (!emp.equals(other.emp))
			return false;
		if (emprId == null) {
			if (other.emprId != null)
				return false;
		} else if (!emprId.equals(other.emprId))
			return false;
		if (signInTime == null) {
			if (other.signInTime != null)
				return false;
		} else if (!signInTime.equals(other.signInTime))
			return false;
		if (signOutTime == null) {
			if (other.signOutTime != null)
				return false;
		} else if (!signOutTime.equals(other.signOutTime))
			return false;
		return true;
	}
	public int getDtId() {
		return dtId;
	}
	public void setDtId(int dtId) {
		this.dtId = dtId;
	}
	public String getEmprId() {
		return emprId;
	}
	public void setEmprId(String emprId) {
		this.emprId = emprId;
	}
	public Date getDtDate() {
		return dtDate;
	}
	public void setDtDate(Date dtDate) {
		this.dtDate = dtDate;
	}
	public String getSignInTime() {
		return signInTime;
	}
	public void setSignInTime(String signInTime) {
		this.signInTime = signInTime;
	}
	public String getSignOutTime() {
		return signOutTime;
	}
	public void setSignOutTime(String signOutTime) {
		this.signOutTime = signOutTime;
	}
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	
	
}
