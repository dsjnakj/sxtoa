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
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$(function(){
		//给signin按钮绑定单击事件，实现签到
		$("#signin").click(function(){
			//发送ajax请求
			$.ajax({
				url:"dutyServlet?method=signin",
				type:"post",
				dataType:"text",
				success:function(data){
					//显示签到结果
					//获取服务器传过来的数据
					if(data==0){
						$("#message").html("签到失败");
					}else if(data==1){
						$("#message").html("签到成功");
					}else{//其他情况
						$("#message").html("已经签到");
					}
				}
			})
		});
		
		//给签退按钮添加点击事件
		$("#signout").click(function(){
			$.ajax({
				url:"dutyServlet?method=signout",
				success:function(result){
					//判断交给后台处理，以便前端处理代码被别人看到
					$("#message").html(result);
				}
			})
		});
	})
</script>

</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">考勤管理</a></li>
    <li><a href="#">签到签退</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
    
    <ul class="forminfo">
    	<li><label>&nbsp;</label><input name="" id="signin" type="button" class="btn" value="签到"/> 每天签到一次，不可重复签到</li>
        <li><label>&nbsp;</label></li>
    	<li><label>&nbsp;</label></li>
      	<li><label>&nbsp;</label><input name="" id="signout" type="button" class="btn" value="签退"/>可重复签退，以最后一次签退为准</li>
    </ul>
    
    
    </div>

	<div id="message"></div>
</body>

</html>
