﻿<%@page import="entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
	<title>省公司-物流管理系统</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<style media="all" type="text/css">
@import "style/all.css";
</style>
	<script type="text/javascript" src="script/jquery-1.3.2.min.js"></script>
	<script type="text/javascript">
	<!--
		function reinitIframe(){
			var iframe = document.getElementById("frame_content");
			try{
				var bHeight = iframe.contentWindow.document.body.scrollHeight;
				var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
				var height = Math.max(bHeight, dHeight);
				iframe.style.height = height;
			}catch (ex){}
		}
		$(function(){
			window.setInterval("reinitIframe()", 200);
			$("#left-column h3").bind("click",function(){
				var t = $(this);
				t.toggleClass('link');
				t.next().toggle();
			});
		});
	//-->
	</script>
	</head>
	
	<% User loginUser = (User)session.getAttribute("loginuser"); %>
	
	<body>
<div id="main">
      <div id="header"> <a href="<%=path %>/indexcompany.jsp" class="logo"><img src="style/img/logo.png" width="200" height="29" alt="" /></a>
    <ul id="top-navigation">
          <li class="active"><span><span><a href="<%=path %>/indexcompany.jsp">首页</a></span></span></li>
          <li><span><span><a href="<%=path %>/company/distributionManager.jsp">配送点管理</a></span></span></li>
          <li><span><span><a href="<%=path %>/company/routeManager.jsp">路线管理</a></span></span></li>
          <li><span><span><a href="<%=path %>/company/carManager.jsp">车辆管理</a></span></span></li>
          <li><span><span><a href="<%=path %>/company/priceManager.jsp">价格管理</a></span></span></li>
<<<<<<< .mine
          <c:if test="${ loginUser.getPriority() == '0' }">
=======
          <%
          	if(loginUser.getPriority() == 0){
           %>
>>>>>>> .r466
	          <li><span><span><a href="<%=path %>/company/userManager.jsp">人员管理</a></span></span></li>
	          <li><span><span><a href="<%=path %>/company/excelManager.jsp">报表管理</a></span></span></li>
          <%
          	}
           %>
        </ul>
    <div id="operator"class="active"><span>省公司&nbsp;&nbsp;<%=loginUser.getName() %></span><a href="<%=path %>/login.jsp">&nbsp;&nbsp;退出</a></div>
  </div>
      <div id="middle">
    <div id="center-column"> <span style="text-align:center;font-family:Georgia, 'Times New Roman', Times, serif;font-size:36px;padding-top:200px;">Welcome to Smart Java 物流管理系统 ！</span> 
    <img src="<%=path%>/images/login/login_tp.jpg"/></div>
  </div>
      <div id="footer"></div>
    </div>
<div id="copyright">Copyright &copy;2012 中软实训第六小组</div>
</body>
</html>