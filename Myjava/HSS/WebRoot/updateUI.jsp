<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>修改信息</title>
    
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
  <h3 align="center">请修改信息：<h3>
  <hr>
    	<form action="info_update.action" method="post" >
    	<table align="center">
    		<s:hidden name="id"/><br>
    		<tr><td>name:</td><td><s:textfield name="name"/></td></tr><br>
    		<tr><td>age:</td><td><s:textfield name="age"/></td></tr><br>
    		<tr><td>address:</td><td><s:textfield name="address"/></td></tr><br>
    		<tr><td><input type="submit" value="提交"></td></tr>
    	</form>
    	</table>
  </body>
</html>
