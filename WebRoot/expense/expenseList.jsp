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
				width : 150		  
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
			
				function selPay(){
					var startTime = $("#startTime").val();
					var endTime = $("#endTime").val();
					var payName = $("#payName").val();
					var type = $("#type").val();
					//页面加载默认发送空请求
					$.ajax({
						url:"expense?method=findPay",
						type:"post",
						data:{startTime:startTime,
								endTime:endTime,
								payName:payName,
								type:type},
						success:function(result){
							//这里要使用append方法添加元素
							eval("var arr="+result);
							//清空原有样式，防止累加
							var tbody = $("#tid");
							tbody.empty();
							//添加
							for(var i=0;i<arr.length;i++){
								tbody.append('<tr>');
								tbody.append('<td><input name="" type="checkbox" value="" /></td>');
								tbody.append('<td>'+arr[i].item.type+'</td>');
								tbody.append('<td>'+arr[i].item.amout+'</td>');
								tbody.append('<td>'+arr[i].item.ItemDesc+'</td>');
								tbody.append('<td>'+arr[i].expEmp.realName+'</td>');
								tbody.append('<td>'+arr[i].payEmp.realName+'</td>');
								tbody.append('<td>'+arr[i].payTime+'</td>');
								tbody.append('<td><a href="#" class="tablelink">查看</a> </td>');
								tbody.append('</tr>');
							}
						}
					});
				}
				
				selPay();
				$("#selPay").click(function(){
					selPay();
				});
			
				
			})
		</script>

	</head>

	<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li><a href="#">收支管理</a></li>
				<li><a href="#">查看支出</a></li>
			</ul>
		</div>

		<div class="rightinfo">

			<ul class="prosearch">
				<li>
					<label>查询：</label>
					<i>起始登记时间</i>
					<a>
						<input name="" id="startTime" type="text" class="scinput" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss' })" />
					</a>
					<i>结束登记时间</i>
					<a>
						<input name="" id="endTime" type="text" class="scinput" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss' })"/>
					</a>
					<i>支出人</i>
					<a>
						<input name="" id="payName" type="text" class="scinput" />
					</a>
					
				</li>
			</ul>
					<ul class="prosearch">
				<li>
					
					<i>收入类型</i>
					<a>
						<select class="select1" id="type">
								<option value="">-所有-</option>
								<option value="1">人员外包</option>
								<option value="2">项目开发</option>
								<option value="3">报名费</option>
								<option value="4">学费</option>
								<option value="5">其他</option>
							</select>
					</a>
					<a>
						<input name="" type="button" id="selPay" class="sure" value="查询" />
					</a>	
				</li>
			</ul>
			<div class="formtitle1"><span>支出列表</span></div>

			<table class="tablelist">
				<thead>
					<tr>
						<th>
							<input name="" type="checkbox" value="" checked="checked" />
						</th>
						<th>类型<i class="sort"><img src="images/px.gif" /></i></th>
						<th>金额</th>
						<th>备注</th>
						<th>报销人</th>
						<th>支出人</th>
						<th>支出时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="tid">
					
				
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