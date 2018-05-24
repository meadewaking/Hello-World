<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<title>登录</title>
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
					<h5>登录</h5>

					<form class="form-signin" id="frmLogin" action="./LoginServlet"
						method="post">
						<section>
							<div class="input-group">
								<input type="text" class="form-control" id="txtLoginName"
									name="txtLoginName" placeholder="用户名"
									value="${requestScope.LoginName}"> <span
									class="label label-danger" id="spanLoginName"></span>
								<div class="input-group-addon">
									<i class="fa fa-user"></i>
								</div>
							</div>
							<div class="input-group">
								<input type="password" class="form-control" id="txtPassword"
									name="txtPassword" placeholder="密码"> <span
									class="label label-danger" id="spanPassword"></span>
								<div class="input-group-addon">
									<i class="fa fa-key"></i>
								</div>
							</div>
						</section>
						<section class="controls">
							<div class="checkbox check-transparent">
								<input type="checkbox" id="remember" name="chkSelected" value="true"checked>
								<label for="remember" >记住用户名</label>
							</div>
							<a href="FindPasswordServlet">忘记密码？</a>
						</section>
						<section class="log-in">
							<button type="submit" class="btn btn-greensea">登录</button>
							<span>or</span>
							<button class="btn btn-slategray"><a href=RegisterServlet>注册</a></button>
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
	<script>
		$("#frmLogin").submit(function() {
			var flag = true;
			if ($("#txtLoginName").val().length == 0) {
				$("#spanLoginName").text("登录名不能为空！");
				flag = false;
			}

			else if ($("#txtLoginName").val().length < 5) {
				$("#spanLoginName").text("登录名不能少于5个字符！");
				flag = false;
			} else
				$("#spanLoginName").text("");
			return flag;
		});
	</script>
	<script>
		$("#frmLogin").submit(function() {
			var flag = true;
			if ($("#txtPassword").val().length == 0) {
				$("#spanPassword").text("密码不能为空！");
				flag = false;
			}

			else if ($("#txtPassword").val().length < 5) {
				$("#spanPassword").text("登录名不能少于5个字符！");
				flag = false;
			} else
				$("#spanPassword").text("");
			return flag;
		});
		${requestScope.Message} //表达式语言
		${sessionScope.Message}
	</script>

</body>
</html>


