<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	.a {
		background-color: #d0d0ff;
	}
	.b{
		background-color: #ffd0d0;
	}
</style>
	<script type="text/javascript" src="<%=path%>/Js/jquery-1.6.4.min.js"></script>
	<script type="text/javascript" >
		$(function(){
			 $("#myButton").click(
			 	function(){
			 		$("tr:even").addClass("a");
			 		$("tr:odd").addClass("b");
			 	}
			 );
		});
	 
	</script>
	
  </head>
  
  <body>
        <button id="myButton" >格行变色</button>
    <table border="1"  width="500">
  <tr><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td></tr>
  <tr><td>2</td><td>2</td><td>2</td><td>2</td><td>2</td></tr>
  <tr><td>3</td><td>3</td><td>3</td><td>3</td><td>3</td></tr>
  <tr><td>4</td><td>4</td><td>4</td><td>4</td><td>4</td></tr>
  <tr><td>5</td><td>5</td><td>5</td><td>5</td><td>5</td></tr>
  <tr><td>6</td><td>6</td><td>6</td><td>6</td><td>6</td></tr>
  <tr><td>7</td><td>7</td><td>7</td><td>7</td><td>7</td></tr>
  <tr><td>8</td><td>8</td><td>8</td><td>8</td><td>8</td></tr>
</table>
    
     
     
  </body>
</html>
