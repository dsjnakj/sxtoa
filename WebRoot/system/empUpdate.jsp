<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    
    
   <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
	<base href="<%=basePath%>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link href="css/select.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
		<script type="text/javascript" src="js/select-ui.min.js"></script>
		<!-- 引入富文本插件 -->
		<script type="text/javascript" src="editor/kindeditor.js"></script>
		<!-- 引入时间插件 -->
		<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
		$(document).ready(function(e) {
		    $(".select1").uedSelect({
				width : 345			  
			});
			
			
		});
		
		//引入富文本
		KE.show({id:"ecp",width:"800px",height:"350px"});
		</script>
	</head>

	<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li><a href="#">人事管理</a></li>
				<li><a href="#">修改员工信息</a></li>
			</ul>
		</div>

		<div class="formbody">

			<div class="formtitle"><span>基本信息</span></div>
			<form action="employee?method=update" method="post">
			<ul class="forminfo">
				<li>
					<label>用户名</label>
					<input name="empId" type="text" class="dfinput" value="${emp.empId }" /><i>必须唯一，也可以根据真实姓名自动生成</i></li>
				<li>
					<li>
						<label>真实姓名</label>
						<input name="realName" type="text" class="dfinput" value="${emp.realName }"/><i></i></li>
					<li>
						<label>性别</label><cite>
						<c:if test="${emp.sex == '男' }">
							<input name="sex" type="radio" value="男" checked="checked" />男&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="sex" type="radio" value="女" />女<i>也可以根据身份证号自动获取</i></cite>
						</c:if>
						<c:if test="${emp.sex == '女' }">
							<input name="sex" type="radio" value="男" />男&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="sex" type="radio" value="女" checked="checked" />女<i>也可以根据身份证号自动获取</i></cite>
						</c:if>
						
					</li>
					<li>
						<label>出生日期</label>
						<input name="birthDate" value="${emp.birthDate }" onfocus="WdatePicker()" type="text" class="dfinput" /><i>也可以根据身份证号自动获取</i></li>
					<li>
					<li>
						<label>入职时间</label>
						<input name="hireDate" value="${emp.hireDate }" onfocus="WdatePicker()" type="text" class="dfinput" /><i></i></li>
					
					<li>
						<label>离职时间</label>
						<input name="levelDate" value="${emp.levelDate }" onfocus="WdatePicker()" type="text" class="dfinput" /><i></i></li>
					<li>
						<label>是否在职</label><cite>
						<c:if test="${emp.onDuty==1 }">
							<input name="onDuty" type="radio" value="1" checked="checked" />是&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="onDuty" type="radio" value="0" />否</cite>
						</c:if>
						<c:if test="${emp.onDuty==0 }">
							<input name="onDuty" type="radio" value="1"  />是&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="onDuty" type="radio" value="0" checked="checked"/>否</cite>
						</c:if>
					</li>
					<li><label>员工类型</label><cite> <input name="empType"
								type="radio" value="1" checked="checked" />基层员工&nbsp;&nbsp;&nbsp;&nbsp;
								<input name="empType" type="radio" value="2" />各级管理人员
						</cite></li>
					<li>
						<label>所属部门<b>*</b></label>
						<div class="vocation">
							<select class="select1" name="deptno">
								<c:forEach items="${deptList }" var="dept">
									<c:if test="${emp.dept.deptno == dept.deptno }">
										<option value="${dept.deptno }" selected="selected">${dept.deptName }</option>
									</c:if>
									<c:if test="${emp.dept.deptno != dept.deptno }">
										<option value="${dept.deptno }" >${dept.deptName }</option>
									</c:if>
								</c:forEach>
							</select>
						</div>

					</li>
					<li><label>从事岗位<b>*</b></label>
							<div class="vocation">
								<select class="select1" name="posId" id="pos">
									<c:forEach items="${posList }" var="position">
										<c:if test="${position.posId == emp.position.posId }">
											<option value="${position.posId }" selected="selected">${position.pName }</option>
										</c:if>
										<c:if test="${position.posId != emp.position.posId }">
											<option value="${position.posId }">${position.pName }</option>
										</c:if>
									</c:forEach>
								</select>
							</div></li>
					<li>
						<label>直接上级<b>*</b></label>						
						<div class="vocation">
							<select class="select1" name="mgrId">
								<c:forEach items="${empList }" var="mgr">
									<c:if test="${emp.mgr.empId == mgr.empId }">
										<option value="${mgr.empId }" selected="selected">${mgr.realName }</option>
									</c:if>
									<c:if test="${emp.mgr.empId != mgr.empId }">
										<option value="${mgr.empId }" >${mgr.realName }</option>
									</c:if>
								</c:forEach>
							</select>							
						</div>
					&nbsp;&nbsp;<input name="" type="text" class="dfinput"  placeholder="也可以在此输入首字母帮助显示"/></li>
					</li>
					<li>
						<label>联系方式</label>
						<input name="phone" value="${emp.phone }" type="text" class="dfinput" />
					</li>
					<li>
						<label>QQ号</label>
						<input name="qq" value="${emp.qq }" type="text" class="dfinput" />
					</li>
					<li>
						<label>紧急联系人信息</label>
						<textarea id="ecp" name="emerContactPerson" cols="" rows="" class="textinput">${emp.emerContactPerson }</textarea>
					</li>
					<li>
						<label>身份证号</label>
						<input name="idCard" value="${emp.idCard }" type="text" class="dfinput" />
					</li>
					<li>
						<label>&nbsp;</label>
						<input name="" type="submit" class="btn" value="确认修改" />
					</li>
			</ul>
			</form>

		</div>

	</body>

</html>