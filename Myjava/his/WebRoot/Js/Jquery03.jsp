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
 	
 	//  $("input[name='love']").each(function(){
 	//	  	alert($(this).val());
  	//  });
 		//alert("1111");
	  //$(".mytest").append('<input id="myname" type="text" value="ttttttt">');
	  
	   		 $("input[name='love']").each(function(){
			 if($(this).attr("checked")=="checked"){
				// alert($(this).val());
		 	 	$(this).attr("checked",false);
			 }else{
		 	 	$(this).attr("checked",true);
			 }
		 	//alert($(this).attr("checked")+$(this).val());
		 });
	}
	 
	</script>
	
  </head>
  
  <body>
  
  <div id="hello" style="color: blue;background-color: red;" class="mytest">
	今天天气不错。<br />
	 有点闷
</div>
  
 	爱好：<br />
看书:<input type="checkbox" class="mycss" name="love" value="1"><br />
吃饭:<input type="checkbox"  class="mycss" name="love" value="2"><br />
睡觉:<input type="checkbox" class="mycss" name="love" value="3"><br />
上网:<input type="checkbox" class="mycss" name="love" value="4"><br />
打豆豆:<input type="checkbox" class="mycss" name="love" value="5"><br />
     <button onclick="test()">我是按钮</button>
  </body>
</html>
