<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
   <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"  style="height: 100%">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>

 	<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
       <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>
       
       <!-- 引入jq -->
       <script type="text/javascript" src="js/jquery.js"></script>
       <!-- 引入echarts -->
       <script type="text/javascript" src="js/echarts.min.js"></script>
       <script type="text/javascript">
       	$(function(){//页面加载
       		$.ajax({//发送ajax请求
       			url:"income?method=getBarData",
       			success:function(data){
       				//json转换为对象
       				eval("var arr="+data);
       				//data里面封装两个数组，一个作为x轴，一个作为y轴
       				var dom = document.getElementById("container");
					var myChart = echarts.init(dom);
					var app = {};
					option = null;
					option = {
						title:{
							text:'尚学堂收入统计图表'//表名
						},
					    xAxis: {//x轴
					        type: 'category',
					        data: arr[0]
					    },
					    yAxis: {//y轴
					        type: 'value'
					    },
					    series: [{//数据
					        data: arr[1],
					        type: 'bar'
					    }]
					};
					if (option && typeof option === "object") {
					    myChart.setOption(option, true);
					}
       			}
       		});
       	    
       	});
		</script>

</head>
	<body style="height: 100%; margin: 0">
	   <div >
	   	<h1 align="center">公司收入统计柱状图</h1>
	   </div>	
       <div id="container" style="height: 90%"></div>
   </body>
</html>
