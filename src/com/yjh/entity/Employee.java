package com.yjh.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 员工信息
 * @author Administrator
 *
 */
public class Employee implements Serializable{

	
	//属性
	private String empId;//员工id
	private String password;//员工密码
	private String realName;//真实姓名
	private String sex;//性别
	private Date birthDate;//出生日期
	private Date hireDate;//入职日期
	private Date levelDate;//离职日期
	private int onDuty;//0-离职 1-在职 
	private int empType;//1.普通员工 2.管理人员 含经理、总监、总裁等 3.管理员
	private String phone;//联系方式
	private String qq;//QQ
	private String emerContactPerson;//紧急联系人
	private String idCard;//身份证号
	//外键属性
	private int deptno;
	private int positionId;
	private String mgrId;
	private Department dept;//通过外键deptno查找
	private Position position;//职位
	private Employee mgr;//上级领导
	private List<Employee> empList = new ArrayList<Employee>();//下级员工
	
	
	public Employee(String empId, String password, String realName, String sex,
			Date birthDate, Date hireDate, Date levelDate, int onDuty,
			int empType, String phone, String qq, String emerContactPerson,
			String idCard, Department dept, Position position, Employee mgr) {
		super();
		this.empId = empId;
		this.password = password;
		this.realName = realName;
		this.sex = sex;
		this.birthDate = birthDate;
		this.hireDate = hireDate;
		this.levelDate = levelDate;
		this.onDuty = onDuty;
		this.empType = empType;
		this.phone = phone;
		this.qq = qq;
		this.emerContactPerson = emerContactPerson;
		this.idCard = idCard;
		this.dept = dept;
		this.position = position;
		this.mgr = mgr;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", password=" + password
				+ ", realName=" + realName + ", sex=" + sex + ", birthDate="
				+ birthDate + ", hireDate=" + hireDate + ", levelDate="
				+ levelDate + ", onDuty=" + onDuty + ", empType=" + empType
				+ ", phone=" + phone + ", qq=" + qq + ", emerContactPerson="
				+ emerContactPerson + ", idCard=" + idCard + ", dept=" + dept
				+ ", position=" + position + ", mgr=" + mgr + ", empList="
				+ empList + "]";
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(String empid2,String realName2,Department dept2) {
		this.empId = empid2;
		this.realName = realName2;
		this.dept = dept2;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public Date getLevelDate() {
		return levelDate;
	}
	public void setLevelDate(Date levelDate) {
		this.levelDate = levelDate;
	}
	public int getOnDuty() {
		return onDuty;
	}
	public void setOnDuty(int onDuty) {
		this.onDuty = onDuty;
	}
	public int getEmpType() {
		return empType;
	}
	public void setEmpType(int empType) {
		this.empType = empType;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmerContactPerson() {
		return emerContactPerson;
	}
	public void setEmerContactPerson(String emerContactPerson) {
		this.emerContactPerson = emerContactPerson;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Employee getMgr() {
		return mgr;
	}
	public void setMgr(Employee mgr) {
		this.mgr = mgr;
	}
	public List<Employee> getEmpList() {
		return empList;
	}
	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}
	
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public int getPositionId() {
		return positionId;
	}
	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}
	public String getMgrId() {
		return mgrId;
	}
	public void setMgrId(String mgrId) {
		this.mgrId = mgrId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((dept == null) ? 0 : dept.hashCode());
		result = prime
				* result
				+ ((emerContactPerson == null) ? 0 : emerContactPerson
						.hashCode());
		result = prime * result + ((empId == null) ? 0 : empId.hashCode());
		result = prime * result + ((empList == null) ? 0 : empList.hashCode());
		result = prime * result + empType;
		result = prime * result
				+ ((hireDate == null) ? 0 : hireDate.hashCode());
		result = prime * result + ((idCard == null) ? 0 : idCard.hashCode());
		result = prime * result
				+ ((levelDate == null) ? 0 : levelDate.hashCode());
		result = prime * result + ((mgr == null) ? 0 : mgr.hashCode());
		result = prime * result + onDuty;
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((qq == null) ? 0 : qq.hashCode());
		result = prime * result
				+ ((realName == null) ? 0 : realName.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
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
		Employee other = (Employee) obj;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (dept == null) {
			if (other.dept != null)
				return false;
		} else if (!dept.equals(other.dept))
			return false;
		if (emerContactPerson == null) {
			if (other.emerContactPerson != null)
				return false;
		} else if (!emerContactPerson.equals(other.emerContactPerson))
			return false;
		if (empId == null) {
			if (other.empId != null)
				return false;
		} else if (!empId.equals(other.empId))
			return false;
		if (empList == null) {
			if (other.empList != null)
				return false;
		} else if (!empList.equals(other.empList))
			return false;
		if (empType != other.empType)
			return false;
		if (hireDate == null) {
			if (other.hireDate != null)
				return false;
		} else if (!hireDate.equals(other.hireDate))
			return false;
		if (idCard == null) {
			if (other.idCard != null)
				return false;
		} else if (!idCard.equals(other.idCard))
			return false;
		if (levelDate == null) {
			if (other.levelDate != null)
				return false;
		} else if (!levelDate.equals(other.levelDate))
			return false;
		if (mgr == null) {
			if (other.mgr != null)
				return false;
		} else if (!mgr.equals(other.mgr))
			return false;
		if (onDuty != other.onDuty)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (qq == null) {
			if (other.qq != null)
				return false;
		} else if (!qq.equals(other.qq))
			return false;
		if (realName == null) {
			if (other.realName != null)
				return false;
		} else if (!realName.equals(other.realName))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		return true;
	}
	
	
}
