<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <title>用户首页</title>
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
  	<h3 align="center">欢迎您${User.username}!<h3>
  <hr>
  		<table border="1px" align="center" width="600px" height="300px">
  			<tr>
  				<td>id</td>
  				<td>name</td>
  				<td>age</td>
  				<td>address</td>
  				<td>
  					操作
  				</td>
  			</tr>
  			<s:iterator value="infolist">
    		<tr>
  				<td>${id}</td>
  				<td>${name}</td>
  				<td>${age}</td>
  				<td>${address}</td>
  				<td>
  					<a href="info_updateUI.action?id=${id}">修改</a>&nbsp;
  					<a href="info_delete.action?id=${id}">删除</a>
  				</td>
  			</tr>
    	</s:iterator>
  		</table>
    	<p align="center">
    		<a href="info_addUI.action">新增</a>
    	</p>
  </body>
</html>
