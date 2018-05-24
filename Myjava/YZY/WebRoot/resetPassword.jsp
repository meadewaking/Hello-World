<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>重置密码</title>
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
					<img src="assets/images/logo-big.png" class="logo">
					<h1>
						<strong>重置密码</strong> 
					</h1>
					<h5>企业消息平台</h5>

					<div class="widget-content">
						<div class="padd">
							<form class="form-horizontal" id="frmResetPassword" name="frmResetPassword"
								action="./ResetPasswordServlet" method="post">

								<div class="form-group">
									<label for="txtLoginName" class="col-sm-2 control-label"
										style="white-space:nowrap">登录名</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="txtLoginName"
											name="txtLoginName" placeholder="登录名" value="${loginName}" readonly="readonly"> 
											<span class="label label-danger" id="spanLoginName"></span>
									</div>
								</div>

								<div class="form-group">
									<label for="txtPassword" class="col-sm-2 control-label"
										style="white-space:nowrap">密码</label>
									<div class="col-sm-10">
										<input type="password" class="form-control" id="txtPassword"
											name="txtPassword" placeholder="密码"> 
											<span class="label label-danger" id="spanPassword"></span>
									</div>
								</div>
								<div class="form-group">
									<label for="txtPassword" class="col-sm-2 control-label"
										style="white-space:nowrap">确认密码</label>
									<div class="col-sm-10">
										<input type="password" class="form-control" id="txtVPassword"
											name="txtVPassword" placeholder="确认密码"> 
											<span class="label label-danger" id="spanVPassword"></span>
									</div>
								</div>

								
								<section class="controls">
				                <div class="checkbox check-transparent">
				                  <input type="checkbox" value="true" id="remember" name="chkSelected" checked>
				                  <label for="remember">记住用户名</label>
				                </div>
				              </section>
								<section class="log-in">
								
									<button type="submit" class="btn btn-greensea" id="btnsub">重置</button>
									<span>or</span>
									<button type="reset" class="btn btn-slategray">取消</button>
								
								</section>

							</form>
						</div>
					</div>
					<div class="widget-foot">
						是否注册？<a href="RegisterServlet">注册</a>
					</div>
				</div>
			</div>

			<!-- /Page content -->
		</div>
	</div>
	<!-- Wrap all page content end -->
	<!-- JS -->
	<script src="js/jquery.js"></script>
	<script src="assets/js/vendor/bootstrap/bootstrap.min.js"></script>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="assets/js/jquery.js"></script>


	<script>
		$("#frmResetPassword").submit(function (){
			var flag = true;
		
			if ($("#txtPassword").val().length == 0) {
				//alert("登录名不能为空!");
				$("#spanPassword").text("密码不能为空!");
				flag = false;
			} else if ($("#txtPassword").val().length < 5) {
				$("#spanPassword").text("密码不能少于6个字符!");
				flag = false;
			} else
				$("#spanPassword").text("");
				
			if ($("#txtVPassword").val().length == 0) {
				//alert("登录名不能为空!");
				$("#spanVPassword").text("确认密码不能为空!");
				flag = false;
			} else if ($("#txtVPassword").val() != $("#txtPassword").val()) {
				$("#spanVPassword").text("确认密码不能与原密码一致!");
				flag = false;
			} else
				$("#spanVPassword").text("");
			return flag;
			
		});
		${requestScope.Message}//el表达式语言
	</script>
</body>
</html>

