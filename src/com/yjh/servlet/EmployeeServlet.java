package com.yjh.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjh.entity.Department;
import com.yjh.entity.Employee;
import com.yjh.entity.Position;
import com.yjh.service.DepartmentService;
import com.yjh.service.EmployeeService;
import com.yjh.service.PositionService;
import com.yjh.service.impl.DepartmentServiceImpl;
import com.yjh.service.impl.EmployeeServiceImpl;
import com.yjh.service.impl.PositionServiceImpl;

public class EmployeeServlet extends BaseServlet {

	// 创建业务层对象
	EmployeeService es = new EmployeeServiceImpl();
	
	//注销操作
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//注销session
		request.getSession().invalidate();
		//重定向到登录也
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}

	//登录验证
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取登录信息
		String empId = request.getParameter("empId");
		String password = request.getParameter("password");
		//获取验证码，用户输入的验证码
		String verifyCode = request.getParameter("verifyCode");
		//获取真正的验证码
		String randStr = (String) request.getSession().getAttribute("randStr");
		if(!verifyCode.equals(randStr)){//验证失败
			request.setAttribute("error", "验证码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;//结束当前代码，不让其继续执行
		}
		//判断是否是总裁，由于总裁没有上级，比较特殊，登录方式与他人不同
		//调用业务层方法
		Employee emp = null;
		if("gaoqi".equals(empId)){
			emp = es.loginBoss(password);
		}else{
			emp = es.login(empId,password);
		}

		
		//判断
		if(emp!=null){//校验成功，允许登录
			request.getSession().setAttribute("emp", emp);
			//使用转发
			request.getRequestDispatcher("/main.html").forward(request, response);
		}else{//登录失败
			request.setAttribute("error", "用户名或密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	// 修改员工信息
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取员工信息
		String empId = request.getParameter("empId");
		String password = "123";
		String realName = request.getParameter("realName");
		String sex = request.getParameter("sex");
		String qq = request.getParameter("qq");
		String phone = request.getParameter("phone");
		String emerContactPerson = request.getParameter("emerContactPerson");
		String idCard = request.getParameter("idCard");

		String sbirthDate = request.getParameter("birthDate");
		String shireDate = request.getParameter("hireDate");
		String slevelDate = request.getParameter("levelDate");
		// 日期转化
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		// 分别进行异常处理的好处，能够精确判断出那段代码出现问题，方面查找
		Date birthDate = null;
		try {
			birthDate = df.parse(sbirthDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date hireDate = null;
		try {
			hireDate = df.parse(shireDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date levelDate = null;
		try {
			levelDate = df.parse(slevelDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Department dept = new Department();
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		dept.setDeptno(deptno);
		Position position = new Position();
		int posId = Integer.parseInt(request.getParameter("posId"));
		position.setPosId(posId);
		Employee mgr = new Employee();
		String mgrId = request.getParameter("mgrId");
		mgr.setEmpId(mgrId);

		int onDuty = Integer.parseInt(request.getParameter("onDuty"));
		int empType = Integer.parseInt(request.getParameter("empType"));

		Employee employee = new Employee(empId, password, realName, sex,
				birthDate, hireDate, levelDate, onDuty, empType, phone, qq,
				emerContactPerson, idCard, dept, position, mgr);
		int n = es.update(employee);
		if (n > 0) {
			response.sendRedirect(request.getContextPath()
					+ "/employee?method=findAll");
		}
	}

	// 修改员工转发
	public void toUpdate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取员工id
		String empId = request.getParameter("empId");
		// 调用业务层进行查询操作
		Employee emp = es.findById(empId);
		// 存储对象
		request.setAttribute("emp", emp);
		// 获取所有部门信息
		// 获取dept的业务层对象
		DepartmentService ds = new DepartmentServiceImpl();
		List<Department> deptList = ds.findAll();
		request.setAttribute("deptList", deptList);
		// 获取所有岗位信息
		// 创建岗位业务层对象
		PositionService ps = new PositionServiceImpl();
		List<Position> posList = ps.findAll();
		request.setAttribute("posList", posList);
		// 获取上级员工
		List<Employee> empList = es.findEmpByType(2);// 1、基层员工 2、各级管理人员
		request.setAttribute("empList", empList);

		// 转发到修改页面
		request.getRequestDispatcher("/system/empUpdate.jsp").forward(request,
				response);
	}

	// 删除员工
	public void deleteEmp(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取请求信息
		String empId = request.getParameter("empId");
		// 调用业务层方法
		int n = es.delete(empId);
		// 重定向到查询页面
		response.sendRedirect(request.getContextPath()
				+ "/employee?method=findAll");

	}

	// 查询对应emp对象
	public void findEmp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取所有请求数据
		String empId = request.getParameter("empId");
		String deptno = request.getParameter("deptno");
		String onDuty = request.getParameter("onDuty");
		String hireDate = request.getParameter("hireDate");

		// 记忆查询条件
		request.setAttribute("empId", empId);
		request.setAttribute("deptno", deptno);
		request.setAttribute("onDuty", onDuty);
		request.setAttribute("hireDate", hireDate);

		// 调用业务层方法
		List<Employee> list = es.findEmp(empId, deptno, onDuty, hireDate);
		// 存储数据到request
		request.setAttribute("list", list);
		// 获取dept的业务层对象
		DepartmentService ds = new DepartmentServiceImpl();
		List<Department> deptList = ds.findAll();
		request.setAttribute("deptList", deptList);
		// 转发
		request.getRequestDispatcher("/system/empList.jsp").forward(request,
				response);
	}

	public void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 调用业务层方法
		List<Employee> list = es.findAll();
		// 存储数据到request
		request.setAttribute("list", list);
		// 获取dept的业务层对象
		DepartmentService ds = new DepartmentServiceImpl();
		List<Department> deptList = ds.findAll();
		request.setAttribute("deptList", deptList);
		// 转发
		request.getRequestDispatcher("/system/empList.jsp").forward(request,
				response);
	}

	public void findById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取请求id
		String empId = request.getParameter("empid");
		// 调用业务方法
		Employee emp = es.findById(empId);
		boolean flag = false;// 查不到对象
		if (emp != null) {// 查到对象
			flag = true;
		}
		request.setAttribute("idFlag", flag);
		request.getRequestDispatcher("/system/empAdd.jsp").forward(request,
				response);
	}

	public void toAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 测试是否进行toAdd
		System.out.println("toAdd");
		// 获取所有部门信息
		// 获取dept的业务层对象
		DepartmentService ds = new DepartmentServiceImpl();
		List<Department> deptList = ds.findAll();
		request.setAttribute("deptList", deptList);
		// 获取所有岗位信息
		// 创建岗位业务层对象
		PositionService ps = new PositionServiceImpl();
		List<Position> posList = ps.findAll();
		request.setAttribute("posList", posList);
		// 获取上级员工
		List<Employee> empList = es.findEmpByType(2);// 1、基层员工 2、各级管理人员
		request.setAttribute("empList", empList);
		// 跳转到system/empAdd.jsp
		request.getRequestDispatcher("/system/empAdd.jsp").forward(request,
				response);
	}

	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 获取员工信息
		String empId = request.getParameter("empId");
		String password = "123";
		String realName = request.getParameter("realName");
		String sex = request.getParameter("sex");
		String qq = request.getParameter("qq");
		String phone = request.getParameter("phone");
		String emerContactPerson = request.getParameter("emerContactPerson");
		String idCard = request.getParameter("idCard");

		String sbirthDate = request.getParameter("birthDate");
		String shireDate = request.getParameter("hireDate");
		String slevelDate = request.getParameter("levelDate");
		// 日期转化
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		// 分别进行异常处理的好处，能够精确判断出那段代码出现问题，方面查找
		Date birthDate = null;
		try {
			birthDate = df.parse(sbirthDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date hireDate = null;
		try {
			hireDate = df.parse(shireDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date levelDate = null;
		try {
			levelDate = df.parse(slevelDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Department dept = new Department();
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		dept.setDeptno(deptno);
		Position position = new Position();
		int posId = Integer.parseInt(request.getParameter("posId"));
		position.setPosId(posId);
		Employee mgr = new Employee();
		String mgrId = request.getParameter("mgrId");
		mgr.setEmpId(mgrId);

		int onDuty = Integer.parseInt(request.getParameter("onDuty"));
		int empType = Integer.parseInt(request.getParameter("empType"));

		Employee employee = new Employee(empId, password, realName, sex,
				birthDate, hireDate, levelDate, onDuty, empType, phone, qq,
				emerContactPerson, idCard, dept, position, mgr);
		// 获取业务层对象
		int n = es.save(employee);

		// 处理结果
		if (n > 0) {// 添加成功，重定向到查询页面
			response.sendRedirect(request.getContextPath()
					+ "/employee?method=findAll");
		} else {// 添加失败，转发到添加页面
				// 把请求信息与错误信息发送到客户端浏览器
			request.setAttribute("error", "添加失败");
			request.setAttribute("employee", employee);
			request.getRequestDispatcher("/system/.jsp").forward(request,
					response);
		}
	}
}
