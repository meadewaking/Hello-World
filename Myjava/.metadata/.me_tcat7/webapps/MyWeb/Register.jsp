<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	//String message = request.getAttribute("Message").toString();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<!-- Title and other stuffs -->
<title>注册</title>
<meta name="keywords"
	content="Bootstrap模版,Bootstrap模版下载,Bootstrap教程,Bootstrap中文,后台管理系统模版,后台模版下载,后台管理系统,后台管理模版" />
<meta name="description"
	content="代码家园-www.daimajiayuan.com提供Bootstrap模版,后台管理系统模版,后台管理界面,Bootstrap教程,Bootstrap中文翻译等相关Bootstrap插件下载" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="">
<!-- Stylesheets -->
<link href="style/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="style/font-awesome.css">
<link href="style/style.css" rel="stylesheet">
<link href="style/bootstrap-responsive.css" rel="stylesheet">

<!-- HTML5 Support for IE -->
<!--[if lt IE 9]>
  <script src="js/html5shim.js"></script>
  <![endif]-->

<!-- Favicon -->
<link rel="shortcut icon" href="img/favicon/favicon.png">
</head>

<body>

	<div class="admin-form">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<!-- Widget starts -->
					<div class="widget wred">
						<div class="widget-head">
							<i class="icon-lock"></i> 注册
						</div>
						<div class="widget-content">
							<div class="padd">

								<form class="form-horizontal" id="frmname" action = "./RegisterServlet" method="post">
									<!-- Registration form starts -->
									<!-- 登录名 -->
									<div class="form-group">
										<label class="control-label col-lg-3">登录名</label>
										<div class="col-lg-9">
											<input type="text" class="form-control" id="Name" name="Name">
											<span class="label label-danger" id="spanName"></span>
										</div>
									</div>
									<!--密码 -->
									<div class="form-group">
										<label class="control-label col-lg-3">密码</label>
										<div class="col-lg-9">
											<input type="password" class="form-control" id="Password"
												name="Password"> <span class="label label-danger"
												id="spanPassword"></span>
										</div>
									</div>
									<!-- 确认密码 -->
									<div class="form-group">
										<label class="control-label col-lg-3">确认密码</label>
										<div class="col-lg-9">
											<input type="password" class="form-control" id="Vpassword"
												name="Vpassword"> <span class="label label-danger"
												id="spanVpassword"></span>
										</div>
									</div>
									<!-- Email -->
									<div class="form-group">
										<label class="control-label col-lg-3">Email</label>
										<div class="col-lg-9">
											<input type="text" class="form-control" id="Email"
												name="Email"> <span class="label label-danger"
												id="spanEmail"></span>
										</div>
									</div>
									<!-- 昵称 -->
									<div class="form-group">
										<label class="control-label col-lg-3">昵称</label>
										<div class="col-lg-9">
											<input type="text" class="form-control" id="NickName"
												name="NickName"> <span class="label label-danger"
												id="spanNickName"></span>
										</div>
									</div>
									<!-- 证件号 -->
									<div class="form-group">
										<label class="control-label col-lg-3">证件号</label>
										<div class="col-lg-9">
											<input type="text" class="form-control" id="CardId"
												name="CardId"> <span class="label label-danger"
												id="spanCardId"></span>
										</div>
									</div>
									<!-- Accept box and button s-->
									<div class="form-group">
										<div class="col-lg-9 col-lg-offset-3">
											<div class="checkbox">
												<label> <input type="checkbox"> 接受协议 和条件
												</label>
											</div>
											<br />
											<button type="submit" class="btn btn-danger">注册</button>
											<button type="reset" class="btn btn-success">重置</button>
										</div>
									</div>
									<br />
								</form>

							</div>
						</div>
						<div class="widget-foot">
							已经注册 <a href=LoginServlet>登录</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



	<!-- JS -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
	<script>
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
	</script>
</body>
</html>