package com.yjh.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yjh.entity.Auditing;
import com.yjh.entity.Employee;
import com.yjh.entity.Expense;
import com.yjh.entity.ExpenseItem;
import com.yjh.entity.Payment;
import com.yjh.service.ExpenseService;
import com.yjh.service.impl.ExpenseServiceImpl;

public class ExpenseServlet extends BaseServlet {
	
	//创建业务层对象，创建在外面的原因是减少对象的创建，访问权限设置为private
	private ExpenseService es = new ExpenseServiceImpl();
	//创建Gson对象
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
	//查看支出
	public void findPay(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取查询条件
		String startTime  = request.getParameter("startTime");
		String endTime  = request.getParameter("endTime");
		String payName  = request.getParameter("payName");
		String type  = request.getParameter("type");
		//直接扔给业务层处理
		List<Payment> list = es.findPay(startTime,endTime,payName,type);
		//返回json字符串
		response.getWriter().println(gson.toJson(list));
	}
	
	//审核
	public void audit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取审核信息
		int expId = Integer.parseInt(request.getParameter("expId"));
		String result = request.getParameter("result");
		String auditDesc = request.getParameter("auditDesc");
		//封装成Auditing对象
			//获取审核人对象
		Employee auditor = (Employee) request.getSession().getAttribute("emp");
			//获取当前时间
		Date now = new Date();
		Auditing auditing = new Auditing(expId,result,auditDesc,auditor,now);
		try{
			//交给业务层处理业务逻辑
			es.audit(auditing);
			response.getWriter().write("success");
		}catch(Exception e){
			//失败
			System.out.println("审核出错");
			request.getRequestDispatcher("/expense?method=toAudit").forward(request, response);
		}
	}
	
	//待审报销
	public void toAudit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取当前用户的empId
		Employee emp = (Employee) request.getSession().getAttribute("emp");
		//调用业务层获取待审对象
		List<Expense> expList = es.findAudit(emp.getEmpId());
		request.setAttribute("expList", expList);
		//返回待审信息
		request.getRequestDispatcher("/expense/toAudit.jsp").forward(request, response);
	}
	
	//添加报销单
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取报销单明细
		String[] typeArr = request.getParameterValues("type");
		String[] amoutArr = request.getParameterValues("amout");
		String[] ItemDescArr = request.getParameterValues("ItemDesc");
		//获取当前用户id及上级审批人id
		String empId = request.getParameter("empId");
		String nextAuditorId = request.getParameter("nextAuditorId");
		//获取报销单备注信息
		String expDesc = request.getParameter("expDesc");
		//获取报销单提交时间
		Date expTime = new Date();
		//报销单总金额
		double totalAmount = 0;
		//把所有报销单明细封装在一个集合里
		List<ExpenseItem> itemList = new ArrayList<ExpenseItem>();
		for(int i=0;i<typeArr.length;i++){
			String type = typeArr[i];
			//通过报销单明细金额获取总金额,String转double
			double amout = Double.parseDouble(amoutArr[i]);
			String ItemDesc = ItemDescArr[i];
			ExpenseItem expenseItem = new ExpenseItem(type, amout, ItemDesc);
			itemList.add(expenseItem);
			totalAmount += amout;
		}
		//注意：报销单创建的时候需要指明报销状态，刚开始添加报销的时候为 创建报销
		String lastResult = "创建报销单";
		String status = "0";//0 表示报销单的状态 创建报销单
		//把报销单明细集合及总金额，用户id及上级id，报销单备注信息封装成一个报销单对象
		Expense expense = new Expense(empId, totalAmount, expTime, expDesc, nextAuditorId, lastResult, status);
		//把报销单明细加进去
		expense.setItemList(itemList);
		//调用业务层,这次不使用返回值来判断成功或失败，改为使用异常捕捉的方式
		try{
			es.add(expense);
			//成功重定向到查询页面
			response.sendRedirect(request.getContextPath()+"/expenseList.html");
		}catch(Exception e){
			//捕捉到异常
			//打印异常信息
			e.printStackTrace();
			//转发到添加报销单页面，并提示错误信息
			request.setAttribute("error", "报销单添加失败");
			request.getRequestDispatcher("/expense/expenseAdd.jsp").forward(request, response);
		}
		
		
		
		
	}
}
