package com.yjh.dao.impl;

import com.yjh.dao.AuditingDao;
import com.yjh.entity.Auditing;
import com.yjh.util.DBUtil2;

public class AuditingDaoImpl implements AuditingDao{

	@Override
	public void save(Auditing auditing) {
		String sql = "insert into auditing values (seq_audit.nextval,?,?,?,?,?)";
		Object [] params = {
				auditing.getExpId(),
				auditing.getAuditor().getEmpId(),
				auditing.getResult(),
				auditing.getAuditDesc(),
				new java.sql.Timestamp(auditing.getTime().getTime())//格式要包含年月日时分秒
		};
		DBUtil2.executeUpdate(sql, params);
		
	}
	
}
