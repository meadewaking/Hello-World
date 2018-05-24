<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>找回密码</title>
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
						<strong>找回密码</strong> 
					</h1>
					<h5>企业消息平台</h5>

					<div class="widget-content">
						<div class="padd">
							<form class="form-horizontal" id="frmFindPassword" name="frmFindPassword"
								action="./FindPasswordServlet" method="post">

								<section>
							<div class="input-group">
								<input type="text" class="form-control" id="txtLoginName"
									name="txtLoginName" placeholder="用户名"> <span
									class="label label-danger" id="spanLoginName"></span>
								<div class="input-group-addon">
									<i class="fa fa-user"></i>
								</div>
							</div>
							<div class="input-group">
								<input type="text" class="form-control" id="txtEmail"
									name="txtEmail" placeholder="Email"> <span
									class="label label-danger" id="spanEmail"></span>
								<div class="input-group-addon">
									<i class="fa fa-key"></i>
								</div>
							</div>
						</section>
								
								<section class="controls">
				                <div class="checkbox check-transparent">
				                  <input type="checkbox" value="true" id="remember" name="chkSelected" checked>
				                  <label for="remember">记住用户名</label>
				                </div>
				              </section>
								<section class="log-in">
								
									<button type="submit" class="btn btn-greensea" id="btnsub">找回</button>
									<span>or</span>
									<button type="reset" class="btn btn-slategray">取消</button>
								
								</section>

							</form>
						</div>
					</div>
					<div class="widget-foot">
						是否注册？<a href="RegisterServlet">注册</a><br>
						再试试？<a href="LoginServlet">登录</a>
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
		$("#frmFingPassword").submit(function (){
			var flag = true;
			if ($("#txtLoginName").val().length == 0) {
				//alert("登录名不能为空!");
				$("#spanLoginName").text("登录名不能为空!");
				flag = false;
			} else if ($("#txtLoginName").val().length < 5) {
				$("#spanLoginName").text("登录名不能少于5个字符!");
				flag = false;
			} else
				$("#spanLoginName").text("");
			
			if ($("#txtEmail").val().length == 0) {
				//alert("登录名不能为空!");
				$("#spanEmail").text("Email不能为空!");
				flag = false;
			} else if ($("#txtEmail").val().length < 5) {
				$("#spanPassword").text("Email不能少于6个字符!");
				flag = false;
			} else
				$("#spanEmail").text("");
			return flag;
			
		});
        ${sessionScope.Message}//el表达式语言
		${requestScope.Message}//el表达式语言
	</script>
</body>
</html>

