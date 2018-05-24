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
 	function test() {
		// alert($("#myname").attr({value : "yyyyyyyy"}));
	//	alert($("#myname").val("asdfasdf"));
		//$("#myname").attr("value",100);
	 	$("select option").each(function() {
	 		 if($(this).val()==5){
	 		 	$(this).attr("selected",true);
	 		 }
	 		//alert($(this).val());
	 	});
	}
	 
	</script>
	
  </head>
  
  <body>
 	<input id="myname" type="text" value="ttttttt">
	<input type="button" value="提交结果" onclick="test()"> 星期
	<select id="myselect">
		<option value="1">星期1</option>
		<option value="2">星期2</option>
		<option value="3" >星期3</option>
		<option value="4">星期4</option>
		<option value="5">星期5</option>
	</select>
    
     
     
  </body>
</html>
