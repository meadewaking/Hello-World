<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <br>
  <br>
  <br>
  <br>
  <br>
  <h1 align="center">请输入用户名与密码：</h1>
  <hr>
   <form action="user_login.action" method="post">
   	<table align="center">
   		<tr>
   			<td>用户名：</td>
   			<td><input type="text" name="username"></td>
   		</tr>
   		<br>
   		<tr>
   			<td>密&nbsp;码：</td>
   			<td><input type="password" name="password"></td>
   		</tr>
   		<tr>
   			<td><input type="submit" value="登录"></td>
   			<td><input type="reset" value="重置"></td>
   		</tr>
   		<br>
   	</table>
   </form>
   <hr>
  </body>
</html>
