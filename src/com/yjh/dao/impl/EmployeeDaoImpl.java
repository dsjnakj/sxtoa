package com.yjh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yjh.dao.EmployeeDao;
import com.yjh.entity.Department;
import com.yjh.entity.Employee;
import com.yjh.entity.Position;
import com.yjh.util.DBUtil;

public class EmployeeDaoImpl implements EmployeeDao{

	
	@Override//添加员工
	public int save(Employee employee) {
		
		java.sql.Date levelDate2 = null;
		Date levelDate = employee.getLevelDate();
		if(levelDate!=null){
			levelDate2 = new java.sql.Date(levelDate.getTime());
		}
		String sql = "insert into employee values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {
				employee.getEmpId(),
				employee.getPassword(),
				employee.getDept().getDeptno(),
				employee.getPosition().getPosId(),
				employee.getMgr().getEmpId(),
				employee.getRealName(),
				employee.getSex(),
				//转换成sqlDate，sqlDate只存储年月日
				new java.sql.Date(employee.getBirthDate().getTime()),
				new java.sql.Date(employee.getHireDate().getTime()),
				levelDate2,
				employee.getOnDuty(),
				employee.getEmpType(),
				employee.getPhone(),
				employee.getQq(),
				employee.getEmerContactPerson(),
				employee.getIdCard()
		};
		return DBUtil.executeUpdate(sql, params);
	}

	@Override
	public List<Employee> findByType(int type) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Employee> list = new ArrayList<Employee>();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from employee where emptype=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, type);
			rs = ps.executeQuery();
			while (rs.next()) {
				// 获取部门属性
				String empId = rs.getString("empid");
				String realName = rs.getString("realname");
				String sex = rs.getString("sex");
				String phone = rs.getString("phone");
				// 创建员工对象接收
				Employee emp = new Employee();
				emp.setEmpId(empId);
				emp.setRealName(realName);
				emp.setSex(sex);
				emp.setPhone(phone);
				// 存到集合中
				list.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, ps, conn);
		}
		return list;
	}

	@Override
	public Employee findById(String empId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Employee emp = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select emp.*,d.deptname,d.location,p.pname,p.pdesc,mgr.realname"
					+" from employee emp"
					+" join dept d"
					+" on emp.deptno = d.deptno"
					+" join position p"
					+" on emp.posid = p.posid"
					+" join employee mgr"
					+" on emp.mgrid = mgr.empid where emp.empId=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, empId);
			rs = ps.executeQuery();
			if (rs.next()) {
				// 获取员工信息
				String realName = rs.getString("realname");
				String sex = rs.getString("sex");
				String phone = rs.getString("phone");
				Date birthDate = rs.getDate("birthDate");
				Date hireDate = rs.getDate("hireDate");
				int onDuty = rs.getInt("onDuty");
				int empType = rs.getInt("empType");
				String password = rs.getString("password");
				Date leaveDate = rs.getDate("LEAVEDATE");
				String qq = rs.getString("qq");
				String emerContactPerson = rs.getString("emerContactPerson");
				String idCard = rs.getString("idCard");
				
				
				
				//获取部门信息
				int deptno = rs.getInt("deptno");
				String deptName = rs.getString("deptName");
				String location = rs.getString("location");
				Department dept = new Department(deptno, deptName, location);
				
				//获取岗位信息
				int posId = rs.getInt("posid");
				String pName = rs.getString("pName");
				String pDesc = rs.getString("pDesc");
				Position position = new Position(posId, pName, pDesc);
				
				//获取上级信息
				String mgrId = rs.getString("mgrid");
				String mgrRealName = rs.getString(21);//由于存在同名列，因此用列数代替
				Employee mgr = new Employee();
				mgr.setEmpId(mgrId);
				mgr.setRealName(mgrRealName);
				
				// 创建员工对象接收
				emp = new Employee(empId, password, realName, sex, birthDate, hireDate, leaveDate, onDuty, empType, phone, qq, emerContactPerson, idCard, dept, position, mgr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, ps, conn);
		}
		return emp;
	}

	@Override
	public List<Employee> findAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Employee> list = new ArrayList<Employee>();
		try {
			conn = DBUtil.getConnection();
			//注意：sql语句拆分后，记得在前面加上空格，以免被当成一个单词
			String sql = "select emp.*,d.deptname,d.location,p.pname,p.pdesc,mgr.realname"
				+" from employee emp"
				+" join dept d"
				+" on emp.deptno = d.deptno"
				+" join position p"
				+" on emp.posid = p.posid"
				+" join employee mgr"
				+" on emp.mgrid = mgr.empid";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				// 获取员工信息
				String empId = rs.getString("empid");
				String realName = rs.getString("realname");
				String sex = rs.getString("sex");
				String phone = rs.getString("phone");
				Date birthDate = rs.getDate("birthDate");
				Date hireDate = rs.getDate("hireDate");
				int onDuty = rs.getInt("onDuty");
				int empType = rs.getInt("empType");
				String password = rs.getString("password");
//				Date levelDate = rs.getDate("levelDate");
				String qq = rs.getString("qq");
				String emerContactPerson = rs.getString("emerContactPerson");
				String idCard = rs.getString("idCard");
				
				
				
				//获取部门信息
				int deptno = rs.getInt("deptno");
				String deptName = rs.getString("deptName");
				String location = rs.getString("location");
				Department dept = new Department(deptno, deptName, location);
				
				//获取岗位信息
				int posId = rs.getInt("posid");
				String pName = rs.getString("pName");
				String pDesc = rs.getString("pDesc");
				Position position = new Position(posId, pName, pDesc);
				
				//获取上级信息
				String mgrId = rs.getString("mgrid");
				String mgrRealName = rs.getString(21);//由于存在同名列，因此用列数代替
				Employee mgr = new Employee();
				mgr.setEmpId(mgrId);
				mgr.setRealName(mgrRealName);
				
				// 创建员工对象接收
				Employee emp = new Employee(empId, password, realName, sex, birthDate, hireDate, null, onDuty, empType, phone, qq, emerContactPerson, idCard, dept, position, mgr);
				// 存到集合中
				list.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, ps, conn);
		}
		return list;
	}

	@Override
	public List<Employee> find(String empId2, String deptno2, String onDuty2,
			String hireDate2) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Employee> list = new ArrayList<Employee>();
		try {
			conn = DBUtil.getConnection();
			//注意：sql语句拆分后，记得在前面加上空格，以免被当成一个单词
			StringBuilder sql =new StringBuilder( "select emp.*,d.deptname,d.location,p.pname,p.pdesc,mgr.realname"
				+" from employee emp"
				+" join dept d"
				+" on emp.deptno = d.deptno"
				+" join position p"
				+" on emp.posid = p.posid"
				+" join employee mgr"
				+" on emp.mgrid = mgr.empid where 1=1");
			//判断条件
			if(empId2 != null && !"".equals(empId2)){
				sql.append(" and emp.empId ='"+empId2+"'");
			}
			if(!"0".equals(deptno2)){
				sql.append(" and emp.deptno ="+Integer.parseInt(deptno2));
			}
			sql.append(" and emp.onDuty ="+Integer.parseInt(onDuty2));
			if(hireDate2 != null && !"".equals(hireDate2)){
				sql.append(" and to_char(emp.hireDate,'YYYY-MM-DD') >='"+hireDate2+"'");
			}
			System.out.println(sql.toString());
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				// 获取员工信息
				String empId = rs.getString("empid");
				String realName = rs.getString("realname");
				String sex = rs.getString("sex");
				String phone = rs.getString("phone");
				Date birthDate = rs.getDate("birthDate");
				Date hireDate = rs.getDate("hireDate");
				int onDuty = rs.getInt("onDuty");
				int empType = rs.getInt("empType");
				String password = rs.getString("password");
//				Date levelDate = rs.getDate("levelDate");
				String qq = rs.getString("qq");
				String emerContactPerson = rs.getString("emerContactPerson");
				String idCard = rs.getString("idCard");
				
				
				
				//获取部门信息
				int deptno = rs.getInt("deptno");
				String deptName = rs.getString("deptName");
				String location = rs.getString("location");
				Department dept = new Department(deptno, deptName, location);
				
				//获取岗位信息
				int posId = rs.getInt("posid");
				String pName = rs.getString("pName");
				String pDesc = rs.getString("pDesc");
				Position position = new Position(posId, pName, pDesc);
				
				//获取上级信息
				String mgrId = rs.getString("mgrid");
				String mgrRealName = rs.getString(21);//由于存在同名列，因此用列数代替
				Employee mgr = new Employee();
				mgr.setEmpId(mgrId);
				mgr.setRealName(mgrRealName);
				
				// 创建员工对象接收
				Employee emp = new Employee(empId, password, realName, sex, birthDate, hireDate, null, onDuty, empType, phone, qq, emerContactPerson, idCard, dept, position, mgr);
				// 存到集合中
				list.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, ps, conn);
		}
		return list;
	}

	@Override
	public int delete(String empId) {
		String sql = "delete from employee where empid=?";
		Object[] params = {empId};
		return DBUtil.executeUpdate(sql, params);
	}

	@Override
	public int update(Employee employee) {
		java.sql.Date levelDate2 = null;
		Date levelDate = employee.getLevelDate();
		if(levelDate!=null){
			levelDate2 = new java.sql.Date(levelDate.getTime());
		}
		String sql = "update employee set password=?,deptno=?,"
				+" posid=?,mgrid=?,realname=?,sex=?,"
				+" birthDate=?,hireDate=?,leaveDate=?,"
				+" onduty=?,emptype=?,phone=?,qq=?,"
				+" emercontactperson=?,idcard=?"
				+" where empId=?";
		Object[] params = {
				employee.getPassword(),
				employee.getDept().getDeptno(),
				employee.getPosition().getPosId(),
				employee.getMgr().getEmpId(),
				employee.getRealName(),
				employee.getSex(),
				//转换成sqlDate，sqlDate只存储年月日
				new java.sql.Date(employee.getBirthDate().getTime()),
				new java.sql.Date(employee.getHireDate().getTime()),
				levelDate2,
				employee.getOnDuty(),
				employee.getEmpType(),
				employee.getPhone(),
				employee.getQq(),
				employee.getEmerContactPerson(),
				employee.getIdCard(),
				employee.getEmpId()
		};
		return DBUtil.executeUpdate(sql, params);
	}

	@Override
	public Employee login(String empId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Employee emp = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from employee where empid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, empId);
			rs = ps.executeQuery();
			if (rs.next()) {
				// 获取员工信息
				String realName = rs.getString("realname");
				String sex = rs.getString("sex");
				String phone = rs.getString("phone");
				Date birthDate = rs.getDate("birthDate");
				Date hireDate = rs.getDate("hireDate");
				int onDuty = rs.getInt("onDuty");
				int empType = rs.getInt("empType");
				String password = rs.getString("password");
				Date leaveDate = rs.getDate("LEAVEDATE");
				String qq = rs.getString("qq");
				String emerContactPerson = rs.getString("emerContactPerson");
				String idCard = rs.getString("idCard");
				//外键
				String mgrId = rs.getString("mgrid"); 
				int deptno = rs.getInt("deptno");
				int posid = rs.getInt("posid");
				// 创建员工对象接收
				emp = new Employee();
				emp.setRealName(realName);
				emp.setPhone(phone);
				emp.setPassword(password);
				emp.setOnDuty(onDuty);
				emp.setEmpType(empType);
				emp.setQq(qq);
				emp.setEmerContactPerson(emerContactPerson);
				emp.setIdCard(idCard);
				emp.setMgrId(mgrId);
				emp.setDeptno(deptno);
				emp.setPositionId(posid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, ps, conn);
		}
		return emp;
	}

	@Override
	public Employee findBoss(String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Employee emp = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from employee where empid = 'gaoqi' and password = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				int empType = rs.getInt("empType");
				String realName = rs.getString("realName");
				
				//获取部门信息
				int deptno = rs.getInt("deptno");
				Department dept = new Department();
				dept.setDeptno(deptno);
				
				//获取岗位信息
				int posId = rs.getInt("posid");
				Position position = new Position();		
				position.setPosId(posId);
				//获取上级信息
			
				emp = new Employee();
				emp.setEmpId("gaoqi");
				emp.setPassword(password);
				emp.setEmpType(empType);
				emp.setRealName(realName);
				emp.setDept(dept);
				emp.setPosition(position);
				
				;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, ps, conn);
		}
		return emp;
	}

}
