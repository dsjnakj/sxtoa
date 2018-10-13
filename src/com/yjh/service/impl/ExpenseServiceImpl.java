package com.yjh.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.yjh.dao.AuditingDao;
import com.yjh.dao.ExpenseDao;
import com.yjh.dao.ExpenseItemDao;
import com.yjh.dao.PaymentDao;
import com.yjh.dao.impl.AuditingDaoImpl;
import com.yjh.dao.impl.ExpenseDaoImpl;
import com.yjh.dao.impl.ExpenseItemDaoImpl;
import com.yjh.dao.impl.PaymentDaoImpl;
import com.yjh.entity.Auditing;
import com.yjh.entity.Expense;
import com.yjh.entity.ExpenseItem;
import com.yjh.entity.Payment;
import com.yjh.service.ExpenseService;
import com.yjh.util.Constants;
import com.yjh.util.DBUtil2;
import com.yjh.util.MyException;

public class ExpenseServiceImpl implements ExpenseService {

	/**
	 * 问题1： expense expenseitem 两个数据表中通过expid关联
	 * expid在expense表中使用序列自增，在expenseitem表中充当外键 而expense 对 expenseitem是属于一对多关系
	 * 说明expid在expenseitem表中可以重复 那么：怎样获取expid （报销单编号）使其不会因为序列自增而影响到expenseitem
	 * 解决1：service层处理 先获取expid序列的下一个值，再将值分别存储
	 * 
	 * 问题2： 有可能出现主表提交完成后子表出错导致子表提交不完整，造成数据错乱
	 * 解决：使用手动提交事务方式提交事务，因此需要在service层获取connection连接
	 * 使用ThreadLocal存储connection连接，第一次访问时创建connection，然后将其
	 * 存进ThreadLocal中，利用ThreadLocal可以让一个线程中的不同方法共用的特性
	 * 由于一次请求就是客户端向服务器申请一条线程，所以此时使用ThreadLocal正合适
	 * 
	 * 
	 * 
	 */

	// 获取 Expense的dao层对象
	ExpenseDao ed = new ExpenseDaoImpl();

	// 获取ExpenseItem的dao层对象
	ExpenseItemDao eid = new ExpenseItemDaoImpl();
	
	// 获取支出dao对象
	PaymentDao pd = new PaymentDaoImpl();
	
	//创建审核记录dao层对象
	AuditingDao ad = new AuditingDaoImpl();

	// 添加报销单事务
	@Override
	public void add(Expense expense) {
		// 获取expId
		int expId = ed.nextVal();
		// 获取connection对象,这时候已经创建Connection对象，并存储进ThreadLocal
		Connection conn = DBUtil2.getConnection();
		// 设置手动提交
		try {
			conn.setAutoCommit(false);
			expense.setExpId(expId);
			// 添加报销单，主单
			ed.save(expense);// 事务开始
			// 添加报销单明细
			List<ExpenseItem> itemList = expense.getItemList();
			for (int i = 0; i < itemList.size(); i++) {
				itemList.get(i).setExpId(expId);
				eid.save(itemList.get(i));
			}
			// 当执行完所有事务后，手动进行提交
			conn.commit();
		} catch (Exception e) {// 这里要捕获所有异常，防止执行不到rollback
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 如果捕捉到异常，进行事务回滚
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				throw new MyException(e1.toString());
			}
			// 这里需要抛出异常，好让servlet捕捉
			throw new MyException(e.toString());
		} finally {
			// 最后，要关闭数据库连接
			DBUtil2.closeAll(null, null, conn);
		}
	}

	@Override
	public List<Expense> findAudit(String empId) {
		return ed.findByAuditId(empId);
	}

	/**
	 * 审核业务流程
	 */
	@Override
	public void audit(Auditing auditing) {
		// 获取connection对象,这时候已经创建Connection对象，并存储进ThreadLocal
		Connection conn = DBUtil2.getConnection();
		// 设置手动提交
		try {
			conn.setAutoCommit(false);
			String result = auditing.getResult();
			if("通过".equals(result)){//审核通过
				Expense expense = ed.findById(auditing.getExpId());
				if(auditing.getAuditor().getPosition().getPosId()==8){//是财务
					//添加支出记录
					Payment payment = new Payment(Constants.POSITION_CFO, expense.getTotalAmount(), new Date(), auditing.getExpId(), expense.getEmpId());
					pd.save(payment);
					//修改报销单信息
					Expense exp = new Expense();
					exp.setNextAuditorId(null);//审核结束
					exp.setLastResult(auditing.getResult());
					exp.setExpId(auditing.getExpId());
					exp.setStatus(Constants.EXPENSE_STATUS_CASHED);// 打款
					ed.update(exp);
				}else{//不是财务
					//auditing.getExpense().getTotalAmount()>2500 空指针异常
					if(expense.getTotalAmount()>2500){//金额大于2500
						if("gaoqi".equals(auditing.getAuditId())){//是总裁
							//添加审核记录
							ad.save(auditing);
							//修改报销单状态
							Expense exp = new Expense();
							//金额小于2500，直接提交给财务
							exp.setNextAuditorId(Constants.POSITION_CFO);//财务
							exp.setLastResult(auditing.getResult());
							exp.setExpId(auditing.getExpId());
							exp.setStatus(Constants.EXPENSE_STATUS_PASS);// 2 审核通过结束
							ed.update(exp);
						}else{//不是总裁
							//添加审核记录
							ad.save(auditing);
							//修改报销单状态
							Expense exp = new Expense();
//							exp.setNextAuditorId(auditing.getAuditor().getMgrId());
							exp.setNextAuditorId(Constants.POSITION_CEO);//总裁
							exp.setLastResult(auditing.getResult());
							exp.setExpId(auditing.getExpId());
							exp.setStatus(Constants.EXPENSE_STATUS_AUDITING);// 2 审核通过结束
							ed.update(exp);
						}
					}else{//金额小于等于2500
						//添加审核记录
						ad.save(auditing);
						//修改报销单状态
						Expense exp = new Expense();
						//金额小于2500，直接提交给财务
						exp.setNextAuditorId(Constants.POSITION_CFO);//财务
						exp.setLastResult(auditing.getResult());
						exp.setExpId(auditing.getExpId());
						exp.setStatus(Constants.EXPENSE_STATUS_PASS);//状态设为2
						ed.update(exp);
					}
				}
			}else{//审核不通过，拒绝或打回
				//添加审核记录
				ad.save(auditing);
				//修改报销单状态
				Expense exp = new Expense();
				exp.setExpId(auditing.getExpId());
				if("打回".equals(auditing.getResult())){
					exp.setStatus(Constants.EXPENSE_STATUS_BACK);//打回
				}else{//拒绝
					exp.setStatus(Constants.EXPENSE_STATUS_REJECT);//拒绝
				}
				exp.setLastResult(auditing.getResult());
				//下一审核人	拒绝表示不通过  打回表示可重新提交  上级审核人可设置为null
				exp.setNextAuditorId(null);
				ed.update(exp);
			}
			// 当执行完所有事务后，手动进行提交
			conn.commit();
		} catch (Exception e) {// 这里要捕获所有异常，防止执行不到rollback
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 如果捕捉到异常，进行事务回滚
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				throw new MyException(e1.toString());
			}
			// 这里需要抛出异常，好让servlet捕捉
			throw new MyException(e.toString());
		} finally {
			// 最后，要关闭数据库连接
			DBUtil2.closeAll(null, null, conn);
		}

	}

	@Override
	public List<Payment> findPay(String startTime, String endTime,
			String payName, String type) {
		//暂不使用分页
			//直接调用dao层,参数空指针直接交给dao层处理
		return ed.findPay(startTime,endTime,payName,type);
	}

}
