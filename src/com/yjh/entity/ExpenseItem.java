package com.yjh.entity;

import java.io.Serializable;

/**
 * 报销单明细
 * @author Administrator
 *
 */
public class ExpenseItem implements Serializable{
	private int itemId;//报销单明细id	序列自增
	private int expId;//报销单id
	private String type;//报销类型  1、通信费用  2、办公耗材 3、水费电费
	private double amout;//明细金额
	private String ItemDesc;//明细备注说明
	
	
	private Expense expense;//报销单

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getExpId() {
		return expId;
	}

	public void setExpId(int expId) {
		this.expId = expId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmout() {
		return amout;
	}

	public void setAmout(double amout) {
		this.amout = amout;
	}

	public String getItemDesc() {
		return ItemDesc;
	}

	public void setItemDesc(String itemDesc) {
		ItemDesc = itemDesc;
	}

	public Expense getExpense() {
		return expense;
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
	}


	public ExpenseItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExpenseItem(int expId, String type, double amout, String itemDesc) {
		super();
		this.expId = expId;
		this.type = type;
		this.amout = amout;
		ItemDesc = itemDesc;
	}

	public ExpenseItem(int expId, String type, double amout, String itemDesc,
			Expense expense) {
		super();
		this.expId = expId;
		this.type = type;
		this.amout = amout;
		ItemDesc = itemDesc;
		this.expense = expense;
	}

	public ExpenseItem(String type2, double amout2, String itemDesc2) {
		// TODO Auto-generated constructor stub
		this.type = type2;
		this.amout = amout2;
		ItemDesc = itemDesc2;
	}

	@Override
	public String toString() {
		return "ExpenseItem [itemId=" + itemId + ", expId=" + expId + ", type="
				+ type + ", amout=" + amout + ", ItemDesc=" + ItemDesc
				+ ", expense=" + expense + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ItemDesc == null) ? 0 : ItemDesc.hashCode());
		long temp;
		temp = Double.doubleToLongBits(amout);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + expId;
		result = prime * result + ((expense == null) ? 0 : expense.hashCode());
		result = prime * result + itemId;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		ExpenseItem other = (ExpenseItem) obj;
		if (ItemDesc == null) {
			if (other.ItemDesc != null)
				return false;
		} else if (!ItemDesc.equals(other.ItemDesc))
			return false;
		if (Double.doubleToLongBits(amout) != Double
				.doubleToLongBits(other.amout))
			return false;
		if (expId != other.expId)
			return false;
		if (expense == null) {
			if (other.expense != null)
				return false;
		} else if (!expense.equals(other.expense))
			return false;
		if (itemId != other.itemId)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	
}
