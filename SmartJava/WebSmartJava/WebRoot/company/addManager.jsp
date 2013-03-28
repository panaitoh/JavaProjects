﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ page import="entity.Distribution"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>人员管理-省公司-物流管理系统</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<style media="all" type="text/css">
@import "../style/all.css";
</style>
<script type="text/javascript">
	//验证表单
	function checkForm() {
		if (document.getElementById("name").value == "") {
			document.getElementById("message").innerHTML = "管理员账号不能为空！";
			return;
		}
		if (document.getElementById("password").value == "") {
			document.getElementById("message").innerHTML = "管理员密码不能为空！";
			return;
		}
		if (document.getElementById("realname").value == "") {
			document.getElementById("message").innerHTML = "真实姓名不能为空！";
			return;
		}
		if (document.getElementById("phone").value == "") {
			document.getElementById("message").innerHTML = "联系电话不能为空！";
			return;
		}
		document.form1.submit();
	}
</script>
</head>
<body style="background: none">
	<div class="top-bar">
		<h1>新增配送点管理员</h1>
		<div class="breadcrumbs">
			<a href="index.jsp" target="_top">首页</a> / <a href="userManager.jsp"
				target="_top">人员管理</a>
		</div>
	</div>
	<div class="select-bar" />
	<form name="form1" method="post"
		action="actionUser.jsp?action=addManager">
		<div class="select-bar">
			<table class="select-table">
				<tr>
					<th>请选择配送点</th>
					<td><select style="width:130px" name="distribution">
							<%
								List<Distribution> distributionList = (List<Distribution>) request
										.getAttribute("distributionList");
								for (int i = 0; i < distributionList.size(); i++) {
									Distribution distribution = distributionList.get(i);
									out.print("<option value='" + distribution.getId() + "'>"
											+ distribution.getName() + "</option>");
								}
							%>
					</select>
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<th class="first">管理员账号(*)</th>
					<td><input type="text" name="name" id="name" class="text" /></td>
					<th>管理员密码(*)</th>
					<td class="last"><input name="password" id="password" type="text"
						class="text" /></td>
				</tr>
				<tr>
					<th class="realname">真实姓名(*)</th>
					<td><input name="realname" id="realname" type="text" class="text" /></td>
					<th>联系电话(*)</th>
					<td class="last"><input name="phone" id="phone" type="text" class="text" />
					</td>
				</tr>
				<tr>
					<th><input type="button" onclick="checkForm()" value="保 存"
						class="button" /><input type="button" class="button"
						onclick="window.location.href='actionUser.jsp?action=forwardListManager&pageIndex=1'"
						value="查  看" style="cursor:pointer;" /></th>
					<th><label id="message" style="color: red; float: left;">
							<%=request.getAttribute("message")%> </label></th>
					<th></th>
					<th></th>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
