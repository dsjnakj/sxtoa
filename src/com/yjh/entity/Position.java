package com.yjh.entity;

import java.io.Serializable;

/**
 * 职位信息
 * @author Administrator
 *
 */
public class Position implements Serializable{


	private int posId;//职位id
	private String pName;//职位名称
	private String pDesc;//职位降序
	@Override
	public String toString() {
		return "Position [posId=" + posId + ", pName=" + pName + ", pDesc="
				+ pDesc + "]";
	}
	public Position() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Position(int posId, String pName, String pDesc) {
		super();
		this.posId = posId;
		this.pName = pName;
		this.pDesc = pDesc;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pDesc == null) ? 0 : pDesc.hashCode());
		result = prime * result + ((pName == null) ? 0 : pName.hashCode());
		result = prime * result + posId;
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
		Position other = (Position) obj;
		if (pDesc == null) {
			if (other.pDesc != null)
				return false;
		} else if (!pDesc.equals(other.pDesc))
			return false;
		if (pName == null) {
			if (other.pName != null)
				return false;
		} else if (!pName.equals(other.pName))
			return false;
		if (posId != other.posId)
			return false;
		return true;
	}
	public int getPosId() {
		return posId;
	}
	public void setPosId(int posId) {
		this.posId = posId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpDesc() {
		return pDesc;
	}
	public void setpDesc(String pDesc) {
		this.pDesc = pDesc;
	}
	
	
}
