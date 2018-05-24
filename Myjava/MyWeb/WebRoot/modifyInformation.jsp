<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.sycu.meade.entity.*"%>
<%@ page import="cn.sycu.meade.business.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<!-- Title and other stuffs -->
<title>修改个人信息</title>
<meta name="keywords"
	content="Bootstrap模版,Bootstrap模版下载,Bootstrap教程,Bootstrap中文,后台管理系统模版,后台模版下载,后台管理系统,后台管理模版" />
<meta name="description"
	content="代码家园-www.daimajiayuan.com提供Bootstrap模版,后台管理系统模版,后台管理界面,Bootstrap教程,Bootstrap中文翻译等相关Bootstrap插件下载" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="">
<!-- Stylesheets -->
<link href="style/bootstrap.css" rel="stylesheet">
<!-- Font awesome icon -->
<link rel="stylesheet" href="style/font-awesome.css">
<!-- jQuery UI -->
<link rel="stylesheet" href="style/jquery-ui.css">
<!-- Calendar -->
<link rel="stylesheet" href="style/fullcalendar.css">
<!-- prettyPhoto -->
<link rel="stylesheet" href="style/prettyPhoto.css">
<!-- Star rating -->
<link rel="stylesheet" href="style/rateit.css">
<!-- Date picker -->
<link rel="stylesheet" href="style/bootstrap-datetimepicker.min.css">
<!-- CLEditor -->
<link rel="stylesheet" href="style/jquery.cleditor.css">
<!-- Uniform -->
<link rel="stylesheet" href="style/uniform.default.css">
<!-- Bootstrap toggle -->
<link rel="stylesheet" href="style/bootstrap-switch.css">
<!-- Main stylesheet -->
<link href="style/style.css" rel="stylesheet">
<!-- Widgets stylesheet -->
<link href="style/widgets.css" rel="stylesheet">
<!-- HTML5 Support for IE -->
<!--[if lt IE 9]>
  <script src="js/html5shim.js"></script>
  <![endif]-->
<!-- Favicon -->
<link rel="shortcut icon" href="img/favicon/favicon.png">
</head>
<body>
	<jsp:include page="top.jsp"></jsp:include>
	<jsp:include page="left.jsp"></jsp:include>
	<!-- Main content starts -->
	<div class="content">
		<!-- Main bar -->
		<div class="mainbar">
			<!-- Page heading -->
			<div class="page-head">
				<h2 class="pull-left">
					<i class="icon-home"></i> 首页
				</h2>
				<!-- Breadcrumb -->
				<div class="bread-crumb pull-right">
					<a href="IndexServlet"><i class="icon-home"></i> 首页</a>
					<!-- Divider -->
					<span class="divider">/</span> <a href="#" class="bread-current">控制台</a>
				</div>
				<div class="clearfix"></div>
			</div>
			<!-- Page heading ends -->
			
			<!-- Matter -->
			<div class="matter">
			<form class="form-horizontal" id="frmModifyInformation" 
			  action="ModifyInformationServlet" method="post">
									
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<div class="widget">
								<div class="widget-head">
									<div class="pull-left">修改个人信息</div>
								<div class="widget-icons pull-right">
									<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
									</div>
									<div class="clearfix"></div>
								</div>
								
								<div class="widget-content">								
									<div class="padd" >
											<div class="form-group">
												<label class="control-label col-lg-6" style="white-space:nowrap">登录名</label>
												<div class="col-lg-9">
													<input type="text" class="form-control" id="txtLoginName" name="txtLoginName"
														placeholder="登录名" value="${sessionScope.Login.loginName}" readonly="readonly"> 
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-lg-6" style="white-space:nowrap">Email</label>
												<div class="col-lg-9">
													<input type="text" class="form-control" id="txtEmail" name="txtEmail"
														placeholder="Email" value="${sessionScope.Login.email}">
													<span class="label label-danger" id="spanEmail"></span>
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-lg-6" style="white-space:nowrap">昵称</label>
												<div class="col-lg-9">
													<input type="text" class="form-control" id="txtNickname" name="txtNickname"
														placeholder="昵称" value="${sessionScope.Login.nickname}">
												  <span class="label label-danger" id="spanNickname"></span>
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-lg-6" style="white-space:nowrap">姓名</label>
												<div class="col-lg-9">
													<input type="text" class="form-control" id="txtUserName" name="txtUserName"
														placeholder="姓名" value="${requestScope.User.userName}">
													<span class="label label-danger" id="spanUserName"></span>
											   </div>
											</div>
											<div class="form-group">
												<label class="control-label col-lg-6" style="white-space:nowrap">性别</label>
												<div class="col-lg-9">
													<input type="radio" name="radGender" value="女" <c:if test="${requestScope.User.gender == '女' }">checked</c:if>>女
													<input type="radio" name="radGender" value="男" <c:if test="${requestScope.User.gender == '男' }">checked</c:if>>男
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-lg-6" style="white-space:nowrap">出生日期</label>
												<div class="col-lg-9">
													<input type="text" class="form-control" id="txtBirthday" name="txtBirthday"
														placeholder="出生日期" value="${requestScope.User.birthday }">
												  <span class="label label-danger" id="spanBirthday"></span>
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-lg-6" style="white-space:nowrap">证件号</label>
												<div class="col-lg-9">
													<input type="text" class="form-control" id="txtCardId" name="txtCardId"
														placeholder="证件号" value="${requestScope.User.cardId }">
													<span class="label label-danger" id="spanCardId"></span>
											   </div>
											</div>
											<div class="form-group">
												<label class="control-label col-lg-6" style="white-space:nowrap">备注</label>
												<div class="col-lg-9">
													<textarea class="form-control" id="txtRemark" name="txtRemark"
														placeholder="备注">${requestScope.User.remark }</textarea>
													<span class="label label-danger" id="spanRemark"></span>
											   </div>
											</div>
											<div class="col-lg-9 col-lg-offset-2">
												<button type="button" class="btn btn-primary" id="btnModify">修改</button>
												<button type="reset" class="btn btn-default">取消</button>
											</div>
										  <br /> 
										<br /> 
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
			</div>
			<!-- Matter ends -->
		
		</div>
		
		<!-- Mainbar ends -->
		<div class="clearfix"></div>

	</div>
	<jsp:include page="bottom.jsp"></jsp:include>
	<!-- Scroll to top -->
	<span class="totop"><a href="#"><i class="icon-chevron-up"></i>
	</a>
	</span>

	<!-- JS -->
	<script src="js/jquery.js"></script>
	<!-- jQuery -->
	<script src="js/bootstrap.js"></script>
	<!-- Bootstrap -->
	<script src="js/jquery-ui-1.9.2.custom.min.js"></script>
	<!-- jQuery UI -->
	<script src="js/fullcalendar.min.js"></script>
	<!-- Full Google Calendar - Calendar -->
	<script src="js/jquery.rateit.min.js"></script>
	<!-- RateIt - Star rating -->
	<script src="js/jquery.prettyPhoto.js"></script>
	<!-- prettyPhoto -->

	<!-- jQuery Flot -->
	<script src="js/excanvas.min.js"></script>
	<script src="js/jquery.flot.js"></script>
	<script src="js/jquery.flot.resize.js"></script>
	<script src="js/jquery.flot.pie.js"></script>
	<script src="js/jquery.flot.stack.js"></script>

	<!-- jQuery Notification - Noty -->
	<script src="js/jquery.noty.js"></script>
	<!-- jQuery Notify -->
	<script src="js/themes/default.js"></script>
	<!-- jQuery Notify -->
	<script src="js/layouts/bottom.js"></script>
	<!-- jQuery Notify -->
	<script src="js/layouts/topRight.js"></script>
	<!-- jQuery Notify -->
	<script src="js/layouts/top.js"></script>
	<!-- jQuery Notify -->
	<!-- jQuery Notification ends -->

	<script src="js/sparklines.js"></script>
	<!-- Sparklines -->
	<script src="js/jquery.cleditor.min.js"></script>
	<!-- CLEditor -->
	<script src="js/bootstrap-datetimepicker.min.js"></script>
	<!-- Date picker -->
	<script src="js/jquery.uniform.min.js"></script>
	<!-- jQuery Uniform -->
	<script src="js/bootstrap-switch.min.js"></script>
	<!-- Bootstrap Toggle -->
	<script src="js/filter.js"></script>
	<!-- Filter for support page -->
	<script src="js/custom.js"></script>
	<!-- Custom codes -->
	<script src="js/charts.js"></script>
	<!-- Charts & Graphs -->
	
<script type="text/javascript">
	${requestScope.Message}//el表达式语言
	
		$("#btnModify").click( function () {
			var flag = true;
			if ($("#txtEmail").val().length == 0) {
				$("#spanEmail").text("邮箱信息不能为空!");
				flag = false;
			} 
			else
				$("#spanEmail").text("");
			
			if ($("#txtNickname").val().length == 0) {
				$("#spanNickname").text("昵称信息不能为空!");
				flag = false;
			} 
			else
				$("#spanNickname").text("");
				
			if ($("#txtUserName").val().length == 0) {
				$("#spanUserName").text("姓名信息不能为空!");
				flag = false;
			} 
			else
				$("#spanUserName").text("");
				
			if ($("#txtBirthday").val().length == 0) {
				$("#spanBirthday").text("出生日期不能为空!");
				flag = false;
			} 
			else
				$("#spanBirthday").text("");
				
			if ($("#txtCardId").val().length == 0) {
				$("#spanCardId").text("证件号不能为空!");
				flag = false;
			} 
			else
				$("#spanCardId").text("");
				
			if ($("#txtRemark").val().length == 0) {
				$("#spanRemark").text("备注不能为空!");
				flag = false;
			} 
			else
				$("#spanRemark").text("");
				
			if (flag)
				$("#frmModifyInformation").submit();
		});
	</script>
</body>
</html>