<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'paymentStatic.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- 引入jq -->
     <script type="text/javascript" src="js/jquery.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/select.css" rel="stylesheet" type="text/css" />
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/select-ui.min.js"></script>
	 
     <!-- 引入echarts -->
     <script type="text/javascript" src="js/echarts.min.js"></script>
     <script type="text/javascript">
     	$(document).ready(function(e) {
			$(".select1").uedSelect({
				width : 150			  
			});	
		});
     
     	$(function(){
     		var val = $("#select1").val();
			changePie(val);
     	});
     	
     	function changePie(val){
     		  $.ajax({
     			url:"income?method=getPieData",
     			type:"post",
     			data:{type:val},
     			success:function(data){
     				eval("var arr="+data);
     				var dom = document.getElementById("container");
					var myChart = echarts.init(dom);
		     		option = {
					    title : {
					        text: '尚学堂支出信息统计',
					        subtext: '报销统计',
					        x:'center'
					    },
					    tooltip : {
					        trigger: 'item',
					        formatter: "{a} <br/>{b} : {c} ({d}%)"
					    },
					    legend: {
					        orient: 'vertical',
					        left: 'left',
					        data: ['1','2','3','4','5']
					    },
					    series : [
					        {
					            name: '访问来源',
					            type: 'pie',
					            radius : '55%',
					            center: ['50%', '60%'],
					            data:arr,
					            itemStyle: {
					                emphasis: {
					                    shadowBlur: 10,
					                    shadowOffsetX: 0,
					                    shadowColor: 'rgba(0, 0, 0, 0.5)'
					                }
					            }
					        }
					    ]
					};
					if (option && typeof option === "object") {
						myChart.setOption(option, true);
					}
     			}
     		});
     	}
     </script>

  </head>
  
  <body style="height: 100%; margin: 0">
  	<div style="height: 10%px; width: 500px;  margin:15px auto;">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			请选择支出时间段：<br/>
			<select class="select1" id="select1" onchange="changePie(this.value)">
				<option value="0">不限</option>
				<option value="1">本月</option>
				<option value="2">今年上半年</option>
				<option value="3">去年全年</option>
			</select>			
		</div>
       <div id="container" style="height: 90%"></div>
   </body>
</html>
