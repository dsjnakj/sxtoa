<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
		<link href="css/select.css" rel="stylesheet" type="text/css" />

		<script type="text/javascript" src="js/jquery.js"></script>
		
		<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
		<script type="text/javascript" src="js/select-ui.min.js"></script>
		<script type="text/javascript" src="editor/kindeditor.js"></script>
		<!-- 时间插件 -->
		<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
		$(document).ready(function(e) {
		    $(".select1").uedSelect({
				width : 200		  
			});
			
		});
		</script>
		<script type="text/javascript">
			$(document).ready(function(){
			  $(".click").click(function(){
			  $(".tip").fadeIn(200);
			  });
			  
			  $(".tiptop a").click(function(){
			  $(".tip").fadeOut(200);
			});
			
			  $(".sure").click(function(){
			  $(".tip").fadeOut(100);
			});
			
			  $(".cancel").click(function(){
			  $(".tip").fadeOut(100);
			});
			
			});
		</script>
		<script type="text/javascript">
			$(function(){
				//发送ajax请求
				$.ajax({//获取所有部门信息
					url:"dutyServlet?method=findAllDept",
					success:function(json){
						//json字符串转换成对象
						eval("var json="+json);
						var str = '<option value="0">--全部--</option>';
						//遍历
						for(var i=0;i<json.length;i++){
							//写入deptno
							str += "<option value='"+json[i].deptno+"'>"+json[i].deptName+"</option>";
						}
						//获取部门id
						$("#deptno").html(str);
					}
				});
				
				//给查询按钮绑定单击事件
				$("#select").click(function(){
					//获取查询信息
					var empid = $("#empid").val();
					var deptno = $("#deptno").val();
					var dtdate = $("#dtdate").val();
					//发送ajax请求,带参数需要写明请求方式，type,data,dataType,
					$.ajax({
						url:"dutyServlet?method=findDuty",
						type:"POST",
						data:{"empid":empid,"deptno":deptno,"dtdate":dtdate},
						dataType:"text",
						success:function(result){
							//转换成对象
							eval("var json="+result);
							//清空原有数据
							$("#tbody").empty;
							//拼接考勤信息
							var str = "";
							for(var i=0;i<json.length;i++){
								str += 
									'<tr>'+
										'<td>'+
											'<input name="" type="checkbox" value="" />'+
										'</td>'+
										'<td>'+json[i].emp.empId+'</td>'+
										'<td>'+json[i].emp.realName+'</td>'+
										'<td>'+json[i].emp.dept.deptName+'</td>'+
										'<td>'+json[i].dtDate+'</td>'+
										'<td>'+json[i].signInTime+'</td>'+
										'<td>'+json[i].signOutTime+'</td>'+
									'</tr>';
							}
							//写入指定位置
							$("#tbody").html(str);
						}
					});
				});
				
				//给导出按钮绑定单击事件
				$("#export").click(function(){
					//获取查询条件
					var empid = $("#empid").val();
					var deptno = $("#deptno").val();
					var dtdate = $("#dtdate").val();
					//访问指定的servlet，这里不使用Ajax，因为Ajax是通过回调函数处理结果，也不使用转发和重定向
					location.href = "dutyServlet?method=exportXls&empid="+empid+"&deptno="+deptno+"&dtdate="+dtdate;
				});
			})
		</script>
	</head>

	<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li><a href="#">考勤管理</a></li>
				<li><a href="#">考勤管理</a></li>
			</ul>
		</div>

		<div class="rightinfo">

			<ul class="prosearch">
				<li>
					<label>查询：</label><i>用户名</i>
					<a>
						<input name="" type="text" id="empid" class="scinput" />
					</a><i>所属部门</i>
					<a>
						<select class="select1" id="deptno">
								
								
						</select>
					</a>
					<i>考勤时间</i>
					<a>
						<input name="" type="text" id="dtdate" class="scinput" onfocus="WdatePicker()" />
					</a>	
					<a>
						<input name="" type="button" id="select" class="sure" value="查询" />
						
					</a>
					<a>
						 <input name="" id="export" type="button" class="scbtn2" value="导出"/>
						
					</a>
					
				</li>
				
					
			</ul>

			<div class="formtitle1"><span>考勤管理</span></div>

			<table class="tablelist">
				<thead>
					<tr>
						<th>
							<input name="" type="checkbox" value="" checked="checked" />
						</th>
						<th>用户名<i class="sort"><img src="images/px.gif" /></i></th>
						<th>真实姓名</th>
						<th>所属部门</th>
						<th>出勤日期</th>
						<th>签到时间</th>
						<th>签退时间</th>
					</tr>
				</thead>
				<tbody id="tbody">

				</tbody>
			</table>

			<div class="pagin">
				<div class="message">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>
				<ul class="paginList">
					<li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
					<li class="paginItem"><a href="javascript:;">1</a></li>
					<li class="paginItem current"><a href="javascript:;">2</a></li>
					<li class="paginItem"><a href="javascript:;">3</a></li>
					<li class="paginItem"><a href="javascript:;">4</a></li>
					<li class="paginItem"><a href="javascript:;">5</a></li>
					<li class="paginItem more"><a href="javascript:;">...</a></li>
					<li class="paginItem"><a href="javascript:;">10</a></li>
					<li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
				</ul>
			</div>

			<div class="tip">
				<div class="tiptop"><span>提示信息</span>
					<a></a>
				</div>

				<div class="tipinfo">
					<span><img src="images/ticon.png" /></span>
					<div class="tipright">
						<p>是否确认对信息的修改 ？</p>
						<cite>如果是请点击确定按钮 ，否则请点取消。</cite>
					</div>
				</div>

				<div class="tipbtn">
					<input name="" type="button" class="sure" value="确定" />&nbsp;
					<input name="" type="button" class="cancel" value="取消" />
				</div>

			</div>

		</div>

		<script type="text/javascript">
			$('.tablelist tbody tr:odd').addClass('odd');
		</script>

	</body>

</html>