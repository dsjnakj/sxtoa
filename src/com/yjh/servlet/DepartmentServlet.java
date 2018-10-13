package com.yjh.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjh.entity.Department;
import com.yjh.service.DepartmentService;
import com.yjh.service.impl.DepartmentServiceImpl;

public class DepartmentServlet extends BaseServlet {
	//修改部门信息
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		//获取部门信息
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		String deptName = request.getParameter("deptName");
		String location = request.getParameter("location");
		Department dept = new Department(deptno,deptName,location);
		//获取业务层对象
		DepartmentService ds = new DepartmentServiceImpl();
		//调用业务层对象
		int n = ds.update(dept);
		if(n>0){//修改成功
			//使用重定向，避免表单重复提交
			response.sendRedirect(request.getContextPath()+"/department?method=findAll");
		}else{//修改失败
			request.setAttribute("error", "修改失败");
			//此时应该使用转发，因为要进行数据传输
			//这里有点问题，修改失败因为回到修改界面，保留原有数据
			request.setAttribute("dept", dept);
			request.getRequestDispatcher("/system/deptUpdate.jsp").forward(request, response);
		}
	}
	
	
	//修改部门信息
	public void findById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		//获取部门编号
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		//获取业务层对象
		DepartmentService ds = new DepartmentServiceImpl();
		//执行业务操作
		Department dept = ds.findById(deptno);
		//把对象存储到request作用域中
		request.setAttribute("dept", dept);
		//转发
		request.getRequestDispatcher("/system/deptUpdate.jsp").forward(request, response);
		
	}
	//删除部门
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		//获取部门编号
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		//获取业务层对象
		DepartmentService ds = new DepartmentServiceImpl();
		//执行业务操作
		int flag = ds.delete(deptno);
		if(flag>0){//删除成功，跳转到查询页面，跳转到查询页之前，需要跳转到查询的servlet
			request.getRequestDispatcher("department?method=findAll").forward(request, response);
		}else{//删除失败
			
		}
	}

	// 添加
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取请求数据
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		String deptName = request.getParameter("deptName");
		String location = request.getParameter("location");
		// 进行业务处理
		// 创建实体类对象
		Department department = new Department(deptno, deptName, location);
		// 创建业务层对象
		DepartmentService ds = new DepartmentServiceImpl();
		// 调用业务层方法
		int n = ds.add(department);
		// 返回处理结果
		if (n > 0) {// 添加部门成功
			// 添加成功进行重定向，刷新表单数据
			// 动态获取根路径request.getContextPath()
			System.out.println(request.getContextPath());
			// 注意：这里必须使用重定向，如果使用转发的话，每当刷新页面，表单数据会再次提交
			response.sendRedirect(request.getContextPath() + "/department?method=findAll");
		} else {// 添加部门失败
			request.setAttribute("error", "添加失败");
			// 添加失败为了把request中的数据传回前端页面，必须使用转发
			request.getRequestDispatcher("/system/deptAdd.jsp").forward(
					request, response);
		}
	}
	
	//查询所有部门信息
	public void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		//创建业务层对象
		DepartmentService ds = new DepartmentServiceImpl();
		//调用业务层方法
		List<Department> list = ds.findAll();
		//转发，把数据存储到request作用域中
		request.setAttribute("deptList", list);
		//转发路径书写	/system/deptList.jsp	转发是从当前项目路径下开始转发
		request.getRequestDispatcher("/system/deptList.jsp").forward(request, response);
	}
}
