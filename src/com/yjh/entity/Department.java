package com.yjh.entity;

import java.io.Serializable;

/**
 * 部门类
 * @author Administrator
 *
 */
public class Department implements Serializable,Comparable<Department>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7731832594788567911L;
	private int deptno;	//部门编号
	private String deptName;//部门名称
	private String location;//所在地点
	
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deptName == null) ? 0 : deptName.hashCode());
		result = prime * result + deptno;
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
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
		Department other = (Department) obj;
		if (deptName == null) {
			if (other.deptName != null)
				return false;
		} else if (!deptName.equals(other.deptName))
			return false;
		if (deptno != other.deptno)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Department [deptno=" + deptno + ", deptName=" + deptName
				+ ", location=" + location + "]";
	}
	@Override
	public int compareTo(Department o) {
		return this.deptno-o.deptno;
	}
	public Department(int deptno, String deptName, String location) {
		super();
		this.deptno = deptno;
		this.deptName = deptName;
		this.location = location;
	}
	
}
