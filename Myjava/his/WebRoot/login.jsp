<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <title>欢迎登录Dull医院管理系统</title>
	<meta charset="UTF-8">
   <link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="Css/style.css" />
    <script type="text/javascript" src="<%=path%>/Js/jquery-1.6.4.min.js"></script>
    <script type="text/javascript" src="<%=path%>/Js/form/jquery.form.js"></script>
	<script type="text/javascript" src="<%=path%>/Js/form/jquery.validate.defined.js"></script>
	<script type="text/javascript" src="<%=path%>/Js/form/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=path%>/Js/form/jquery.validation.assist.js"></script>
 	<script type="text/javascript" src="<%=path%>/Js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=path %>/Js/ckeditor/ckeditor.js"></script>
    <style type="text/css">
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }

        .form-signin {
            max-width: 300px;
            padding: 19px 29px 29px;
            margin: 0 auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
        }

        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
        }

        .form-signin input[type="text"],
        .form-signin input[type="password"] {
            font-size: 16px;
            height: auto;
            margin-bottom: 15px;
            padding: 7px 9px;
        }
    </style> 
    <script type="text/javascript">
    function yanz(){
    	var yanzm=$('#yy').val();
    	$.ajax({
			type : "POST",
			url : "<%=path%>/Verify?action=yzma",
 			data : "verify="+yanzm,
			success : function(msg) {
				if (msg==1) {
					$("#yzm").html("<font color='green'>正确</font>");
					$('#subm').attr("disabled", false);
				}else{
					$("#yzm").html("<font color='red'>错误</font>");
					$('#subm').attr("disabled", true);
				}
			}
		});
    }
   
    $('#subm').click(function (event) {
    	event.preventDefault();
    	$('form').fadeOut(500);
    	//$('.wrapper').addClass('form-success');
    });
    </script>
    <script type="text/javascript">
	$(document).ready(init);
	function init() {
		$("#frm1").validate( {
			rules : {
				"username" : {
					required : true,
					maxlength : 10
				},
				"password" : {
					required : true,
					maxlength : 30
				}
			},
			success : function(label) {
				label.html("&nbsp;").addClass("checked");
			}
		});
	}
	</script> 
</head>
<body>
<div class="container">
    <form class="form-signin" method="post" id="frm1" action="<%=path%>/Verify">
        <h2 class="form-signin-heading">&nbsp;&nbsp;&nbsp;登录系统</h2>
        <input type="text" name="username" class="input-block-level" placeholder="账号">
        <input type="password" name="password" class="input-block-level" placeholder="密码">
        <input type="text" name="verify" id="yy" onblur="yanz();" class="input-medium" placeholder="验证码">&nbsp;&nbsp;&nbsp;
       	<img src="<%=path%>/yzm.jsp" alt="验证码" height="20" align="bottom" style="cursor:pointer;" title="看不清可单击图片刷新" onclick="this.src='yzm.jsp?d='+Math.random();"/>&nbsp;&nbsp;&nbsp;&nbsp;
        <font color="red"  value=""><span id="yzm"></span></font>
        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <button class="btn btn-large btn-primary" id="subm" type="submit" disabled>登录</button></p>
    </form>

</div>

</body>
</html>
