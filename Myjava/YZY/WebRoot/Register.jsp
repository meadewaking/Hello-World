<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	//String message = request.getAttribute("Message").toString();
%>
<!DOCTYPE html>
<html>
<head>
<title>注册</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8" />

<link rel="icon" type="image/ico"
	href="http://tattek.com/minimal/assets/images/favicon.ico" />
<!-- Bootstrap -->
<link href="assets/css/vendor/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link
	href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"
	rel="stylesheet">
<link rel="stylesheet" href="assets/css/vendor/bootstrap-checkbox.css">

<link href="assets/css/minimal.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body class="bg-1">


	<!-- Wrap all page content here -->
	<div id="wrap">
		<!-- Make page fluid -->
		<div class="row">
			<!-- Page content -->
			<div id="content" class="col-md-12 full-page login">


				<div class="inside-block">
					<img src="assets/images/logo-big.png" alt class="logo">
					<h1>
						<strong>欢迎</strong> 访问
					</h1>
					<h5>注册</h5>

					<form class="form-horizontal" id="frmname" action = "./RegisterServlet" method="post">
						<section>
							<div class="input-group">
								<input type="text" class="form-control" id="Name" name="Name" placeholder="用户名"
									> <span class="label label-danger" id="spanName"></span>
								<div class="input-group-addon">
									<i class="fa fa-user"></i>
								</div>
							</div>
							<div class="input-group">
								<input type="password" class="form-control" id="Password"
												name="Password" placeholder="密码"> <span class="label label-danger"
												id="spanPassword"></span>
								<div class="input-group-addon">
									<i class="fa fa-key"></i>
								</div>
							</div>
							<div class="input-group">
								<input type="password" class="form-control" id="Vpassword"
												name="Vpassword" placeholder="确认密码"> <span class="label label-danger"
												id="spanVpassword"></span>
								<div class="input-group-addon">
									<i class="fa fa-key"></i>
								</div>
							</div>
							<div class="input-group">
								<input type="text" class="form-control" id="Email"
												name="Email" placeholder="Email"> <span class="label label-danger"
												id="spanEmail"></span>
								<div class="input-group-addon">
									<i class="fa fa-key"></i>
								</div>
							</div>
							<div class="input-group">
								<input type="text" class="form-control" id="NickName"
												name="NickName" placeholder="昵称"> <span class="label label-danger"
												id="spanNickName"></span>
								<div class="input-group-addon">
									<i class="fa fa-key"></i>
								</div>
							</div>
							<div class="input-group">
								<input type="text" class="form-control" id="CardId"
												name="CardId" placeholder="证件号"> <span class="label label-danger"
												id="spanCardId"></span>
								<div class="input-group-addon">
									<i class="fa fa-key"></i>
								</div>
							</div>
						</section>
						<section class="controls">
							<div class="checkbox check-transparent">
								<input type="checkbox" value="1" id="remember" checked>
								<label for="remember" >接受协议 和条件</label>
							</div>

						</section>
						<section class="log-in">
							<button type="submit" class="btn btn-greensea">注册</button>
							<span>or</span>
							<button type="reset" class="btn btn-slategray" >重置</button>
						</section>
					</form>
				</div>


			</div>
			<!-- /Page content -->
		</div>
	</div>
	<!-- Wrap all page content end -->

	<!-- JS -->
	<script src="assets/js/jquery.js"></script>
	<!--<script>
		$("#frmname").submit(function() {
			var flag = true;
			if ($("#Name").val().length == 0) {
				$("#spanName").text("登录名不能为空!");
				flag = false;
			} else if ($("#Name").val().length < 5) {
				$("#Name").text("登录名不能少于5个字符!");
				flag = false;
			} else
				$("#spanName").text("");

			if ($("#Password").val().length == 0) {
				$("#spanPassword").text("密码不能为空!");
				flag = false;
			} else if ($("#Password").val().length < 5) {
				$("#spanPassword").text("密码不能少于5个字符!");
				flag = false;
			} else
				$("#spanPassword").text("");

			if ($("#Vpassword").val() != $("#Password").val()) {
				$("#spanVpassword").text("确认密码与密码不一致！");
				flag = false;
			} else
				$("#spanVPassword").text("");

			var patten = /^\w+([\.-]?\\w+)*@\w+([\.-]?\\w+)*(\.\w{2,3})+$/;
			if ($("#Email").val().length == 0) {
				$("#spanEmail").text("Email不能为空!");
				flag = false;
			} else if ($("#Email").val().length < 6) {
				$("#spanEmail").text("Email不能少于6个字符!");
				flag = false;
			} else if (!patten.test($("#Email").val())) {
				$("#spanEmail").text("Email格式不正确!");
				flag = false;
			} else
				$("#spanEmail").text("");

			var patten = /^\d{15}$|^\d{17}[0-9xX]$/;
			if ($("#CardId").val().length == 0) {
				$("#spanCardId").text("证件号不能为空!");
				flag = false;
			} else if (!patten.test($("#CardId").val())) {
				$("#spanCardId").text("证件号格式不正确!");
				flag = false;
			} else
				$("#spanCardId").text("");
			return flag;
		});

		${requestScope.Message}
	</script>-->
	<script>
	var flagLoginName = true;
	$("#Name").blur(
	  function () {
	    flagLoginName = true;
	    if ($("#Name").val().length > 0 && $("#Name").val().length < 5) {
	      $("#spanName").text("登录名至少5个字符");
	      flagLoginName = false;
	      return ;
	    } 
	    else if ($("#Name").val().length > 0) {
	      $("#spanName").text("");
	      
	      $.ajax( {
	          type : "POST",
	          url : "/YZY/ValidateLoginNameServlet", 
	          data : {
	            txtLoginName : $("#Name").val()
	          },
	          cache : false,
	          dataType : "xml",
	          success : function(data){
	            var passed = data.getElementsByTagName("passed")[0].firstChild.nodeValue;
	            if (passed == "true") {
	              $("#spanName").text("登录名已存在，请选择其它登录名。");
	              flagLoginName = false;
	            }
	            else {
	              $("#spanName").text("");
	            }
	          },
	          error : function() {
	            $("#spanName").text("验证功能失效，请尝试注册。");
	            flagLoginName = true;
	          }
	        });
	    }
	  }
	);
	
	var flagEmail = true;
	$("#Email").blur(
	  function () {
	    flagEmail = true;
	    var patten = /^\w+([\.-]?\\w+)*@\w+([\.-]?\\w+)*(\.\w{2,3})+$/;
	    if ($("#Email").val().length > 0 && $("#Email").val().length < 6) {
	      $("#spanEmail").text("Email不能少于6个字符");
	      flagEmail = false;
	      return ;
	    } 
	    else if (!patten.test($("#Email").val())) {
				$("#spanEmail").text("Email格式不正确!");
				flagEmail = false;
				return ;
				}
	    else if ($("#Email").val().length > 0) {
	      $("#spanEmail").text("");
	      
	      $.ajax( {
	          type : "POST",
	          url : "/YZY/ValidateEmailServlet", 
	          data : {
	            txtEmail : $("#Email").val()
	          },
	          cache : false,
	          dataType : "xml",
	          success : function(data){
	            var passed = data.getElementsByTagName("passed")[0].firstChild.nodeValue;
	            if (passed == "true") {
	              $("#spanEmail").text("邮箱已使用，请选择其它邮箱。");
	              flagEmail = false;
	            }
	            else {
	              $("#spanEmail").text("");
	            }
	          },
	          error : function() {
	            $("#spanEmail").text("验证功能失效，请尝试注册。");
	            flagEmail = true;
	          }
	        });
	    }
	  }
	);
	
	var flagCardId = true;
	$("#CardId").blur(
	  function () {
	    flagCardId = true;
	    var patten = /^\d{15}$|^\d{17}[0-9xX]$/;
	    if ($("#CardId").val().length > 0 && $("#CardId").val().length < 6) {
	      $("#spanCardId").text("证件号不能少于6个字符");
	      flagCardId = false;
	      return ;
	    } 
	    else if (!patten.test($("#CardId").val())) {
				$("#spanCardId").text("证件号格式不正确!");
				flagEmail = false;
				return ;
				}
	    else if ($("#CardId").val().length > 0) {
	      $("#spanCardId").text("");
	      
	      $.ajax( {
	          type : "POST",
	          url : "/YZY/ValidateCardIdServlet", 
	          data : {
	            txtCardId : $("#CardId").val()
	          },
	          cache : false,
	          dataType : "xml",
	          success : function(data){
	            var passed = data.getElementsByTagName("passed")[0].firstChild.nodeValue;
	            if (passed == "true") {
	              $("#spanCardId").text("证件号已使用，请重新输入。");
	              flagCardId = false;
	            }
	            else {
	              $("#spanCardId").text("");
	            }
	          },
	          error : function() {
	            $("#spanCardId").text("验证功能失效，请尝试注册。");
	            flagCardId = true;
	          }
	        });
	    }
	  }
	);
	
	${requestScope.Message}
	</script>	
</body>
</html>