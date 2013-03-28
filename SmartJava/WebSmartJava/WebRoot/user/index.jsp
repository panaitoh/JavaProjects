<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>客户-物流管理系统</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<style media="all" type="text/css">
@import "../style/all.css";
</style>
<script type="text/javascript" src="../script/jquery-1.3.2.min.js"></script>
<script type="text/javascript">
<!--
	function reinitIframe() {
		var iframe = document.getElementById("frame_content");
		try {
			var bHeight = iframe.contentWindow.document.body.scrollHeight;
			var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
			var height = Math.max(bHeight, dHeight);
			iframe.style.height = height;

		} catch (ex) {
		}
	}
	$(function() {
		window.setInterval("reinitIframe()", 200);
		$("#left-column h3").bind("click", function() {
			var t = $(this);
			t.toggleClass('link');
			t.next().toggle();
		});
	});
//-->
</script>
</head>
<body>
	<div id="main">
		<div id="header">
			<a href="index.jsp" class="logo"><img
				src="../style/img/logo.png" width="200" height="29" alt="" />
			</a>
			<ul id="top-navigation">
				<li class="active"><span><span><a
							href="index.jsp">首页</a>
					</span>
				</span>
				</li>
				<li><span><span><a href="actionUser.jsp?action=orderManager">订单管理</a>
					</span>
				</span>
				</li>
			</ul>
			<div id="operator" class="active">
				<span>客户姓名&nbsp;</span>&nbsp;<a href="#">退出</a>
			</div>
		</div>
		<div id="middle">
			<div id="center-column">
				<span
					style="text-align:center;font-family:Georgia, 'Times New Roman', Times, serif;font-size:36px;padding-top:200px;">Welcome
					to Smart Java 物流管理系统 ！</span> <img src="../images/login/login_tp.jpg"
					alt="" />
			</div>
		</div>
		<div id="footer"></div>
	</div>	
	<div id="copyright">Copyright &copy;2012 中软实训第六小组</div>
</body>
</html>