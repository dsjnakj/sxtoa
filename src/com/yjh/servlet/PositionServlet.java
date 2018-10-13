package com.yjh.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yjh.entity.Position;
import com.yjh.service.PositionService;
import com.yjh.service.impl.PositionServiceImpl;

public class PositionServlet extends BaseServlet {
	// 创建岗位业务层对象
	PositionService ps = new PositionServiceImpl();

	// 查询所有岗位	针对ajax请求
	public void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 执行业务方法
		List<Position> posList = ps.findAll();
		// 创建Gson对象
		Gson gson = new Gson();
		// 转换成json字符串
		String json = gson.toJson(posList);
		// 响应回去
		response.getWriter().write(json);
	}

	// 查询所有岗位	存储在request作用域
	public void findAll2(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 执行业务方法
		List<Position> posList = ps.findAll();
		//存储在request作用域
		request.setAttribute("posList", posList);
		//转发到system/positionList.jsp
		request.getRequestDispatcher("/system/positionList.jsp").forward(request, response);
	}

	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取position对象
		int posId = Integer.parseInt(request.getParameter("posId"));
		String pName = request.getParameter("pName");
		String pDesc = request.getParameter("pDesc");
		Position position = new Position(posId, pName, pDesc);
		// 调用service层
		int n = ps.add(position);
		// 重定向到查询页面
		response.sendRedirect(request.getContextPath()
				+ "/position?method=findAll2");
	}
	
	/**
	 * 跳转到岗位修改页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void toUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取请求id
		int posId = Integer.parseInt(request.getParameter("posId"));
		//调用业务层方法
		Position position = ps.findById(posId);
		request.setAttribute("position", position);
		//转发到system/positionUpdate.jsp
		request.getRequestDispatcher("/system/positionUpdate.jsp").forward(request, response);
	}
	
	/**
	 * 修改岗位信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updatePos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int posId = Integer.parseInt(request.getParameter("posId"));
		String pName = request.getParameter("pName");
		String pDesc = request.getParameter("pDesc");
		Position position = new Position(posId, pName, pDesc);
		//调用service层
		int n = ps.update(position);
		//重定向到查询页面
		response.sendRedirect(request.getContextPath()+"/position?method=findAll2");
	}
	
	/**
	 * 删除岗位
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deletePos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int posId = Integer.parseInt(request.getParameter("posId"));
		int n = ps.delete(posId);
		//重定向到查询页面
		response.sendRedirect("position?method=findAll2");
	}

}
