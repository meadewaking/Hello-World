<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	

  </head>
  
  <body>
  <br>
  <br>
  <br>
  <h3 align="center">请输入信息：<h3>
  <hr>
    	<form action="info_add.action" method="post">
    		<table align="center">
    		<tr><td>name:</td><td><input type="text" name="name"></td></tr><br>
    		<tr><td>age:</td><td><input type="text" name="age"></td></tr><br>
    		<tr><td>address:</td><td><input type="text" name="address"></td></tr><br>
    		<tr><td><input type="submit" value="提交"></td></tr>
    		</table>
    	</form>
  </body>
</html>
