package com.yjh.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjh.service.IncomeService;
import com.yjh.service.impl.IncomeServiceImpl;


public class IncomeServlet extends BaseServlet {
	
	//获取业务层对象
	IncomeService is = new IncomeServiceImpl();
	
	//获取柱状图数据
	public void getBarData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//调用业务层方法,直接返回json字符串
		String jsonStr = is.getBarData();
		
		//响应数据回浏览器
		response.getWriter().println(jsonStr);
	}
	
	//获取柱状图数据
	public void getPieData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String stype = request.getParameter("type");
		int type = 0;
		try{//转换过程中可能出现异常
			type = Integer.parseInt(stype);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		//int type = Integer.parseInt(request.getParameter("type"));
		String jsonStr = is.getPieData(type);

		//响应数据回浏览器
		response.getWriter().println(jsonStr);
	}
	
	
	
	
}
