<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.sycu.meade.entity.*"%>
<%@ page import="cn.sycu.meade.business.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<!-- Title and other stuffs -->
<title>密码修改日志信息管理</title>
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
				<form class="form-horizontal" id="frmModifyPasswordLogs"
					action="ManageModifyPasswordLogServlet" method="post">

					<input type="hidden" name="hidOperationType" id="hidOperationType" />
					<input type="hidden" name="hidModifyPasswordLogId"
						id="hidModifyPasswordLogId"
						value="${requestScope.ModifyPasswordLog.modifyPasswordLogId}" />
					<input type="hidden" name="hidFieldName" id="hidFieldName"
						value="${requestScope.fieldName}" /> <input type="hidden"
						name="hidSortString" id="hidSortString"
						value="${requestScope.sortString}" /> <input type="hidden"
						name="hidPageIndex" id="hidPageIndex"
						value="${requestScope.pageIndex}" /> <input type="hidden"
						name="hidPageSize" id="hidPageSize"
						value="${requestScope.pageSize}" />

					<div class="container">
						<div class="row">
							<div class="col-md-12">
								<div class="widget">
									<div class="widget-head">
										<div class="pull-left">查询密码修改日志信息</div>
										<div class="widget-icons pull-right">
											<a href="#" class="wminimize"><i
												class="icon-chevron-down"></i></a> <a href="#" class="wclose"><i
												class="icon-remove"></i></a>
										</div>
										<div class="clearfix"></div>
									</div>

									<div class="widget-content" style="display: none;">
										<div class="padd">
											<div class="form-group">
												<label class="control-label col-lg-6"
													style="white-space:nowrap">登录时间</label>
												<div class="col-lg-4">
													<input type="text" class="form-control"
														id="txtSearchModifyDateTimeBegin"
														name="txtSearchModifyDateTimeBegin" placeholder="开始时间"
														value="${searcher.modifyDateTimeBegin}">
												</div>
												<div class="col-lg-4">
													<input type="text" class="form-control"
														id="txtSearchModifyDateTimeEnd"
														name="txtSearchModifyDateTimeEnd" placeholder="结束时间"
														value="${searcher.modifyDateTimeEnd}">
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-lg-6"
													style="white-space:nowrap">修改IP</label>
												<div class="col-lg-9">
													<input type="text" class="form-control"
														id="txtSearchModifyIp" name="txtSearchModifyIp"
														placeholder="修改IP" value="${searcher.modifyIp}"> <span
														class="label label-danger" id="spanSearchModifyIp"></span>
												</div>
											</div>

											<div class="col-lg-9 col-lg-offset-2">
												<button type="button" class="btn btn-primary" id="btnSearch">查询</button>
												<button type="reset" class="btn btn-default"
													id="btnCancelSearch">取消查询</button>
											</div>
											<br /> <br />
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- Table -->

						<div class="row">
							<div class="col-md-12">
								<div class="widget">
									<div class="widget-head">
										<div class="pull-left">密码修改日志信息列表</div>
										<div class="widget-icons pull-right">
											<a href="#" class="wminimize"><i class="icon-chevron-up"></i>
											</a> <a href="#" class="wclose"><i class="icon-remove"></i> </a>
										</div>
										<div class="clearfix"></div>
									</div>

									<div class="widget-content">
										<table class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th><a
														href="javascript: sorting('ModifyPasswordLogId');">密码修改日志编号</a></th>
													<th><a href="javascript: sorting('ModifyDateTime');">修改时间</a></th>
													<th><a href="javascript: sorting('ModifyIp');">登录Ip</a></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${modifyPasswordLogs}" var="modifyPasswordLog"
												 begin="${begin - 1}" end="${end - 1}">
													<tr>
														<td>${modifyPasswordLog.modifyPasswordLogId}</td>
														<td>${modifyPasswordLog.modifyDateTime}</td>
														<td>${modifyPasswordLog.modifyIp}"></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>

										<div class="widget-foot">
											<label class="control-label col-lg-6"
												style="white-space:nowrap; ">共${rowCount}条数据
												第${pageIndex}页 共${pageCount}页&nbsp;&nbsp;&nbsp; <input
												type="radio" name="radPageSize" value="2"
												onclick="resize(this)"
												<c:if test="${pageSize == 2}">checked</c:if>> 2条/页
												<input type="radio" name="radPageSize" value="3"
												onclick="resize(this)"
												<c:if test="${pageSize == 3}">checked</c:if>> 3条/页
												<input type="radio" name="radPageSize" value="5"
												onclick="resize(this)"
												<c:if test="${pageSize == 5}">checked</c:if>> 5条/页
											</label>

											<ul class="pagination pull-right">
												<c:if test="${pageIndex <= 1}">
													<li><a href="#">首页</a></li>
													<li><a href="#">上一页</a></li>
												</c:if>
												<c:if test="${pageIndex > 1}">
													<li><a href="javascript: paging(1);">首页</a></li>
													<li><a
														href="javascript: paging(${requestScope.pageIndex - 1});">上一页</a></li>
												</c:if>
												<c:if test="${pageIndex >= pageCount}">
													<li><a href="#">下一页</a></li>
													<li><a href="#">尾页</a></li>
												</c:if>
												<c:if test="${pageIndex < pageCount}">
													<li><a
														href="javascript: paging(${requestScope.pageIndex + 1});">下一页</a></li>
													<li><a
														href="javascript: paging(${requestScope.pageCount});">尾页</a></li>
												</c:if>
											</ul>
											<div class="clearfix"></div>
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
	</a> </span>

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
		$("#btnAdd").click(function() {
			var flag = true;
			if ($("#txtModifyIp").val().length == 0) {
				$("#spanModifyIp").text("密码修改日志Ip不能为空!");
				flag = false;
			} else
				$("#spanModifyIp").text("");

			$("#hidOperationType").val("add");//设置操作类型
			if (flag)
				$("#frmModifyPasswordLogs").submit();
		});

		$("#btnSave").click(function() {
			var flag = true;
			if ($("#txtModifyIp").val().length == 0) {
				//alert("登录名不能为空!");
				$("#spanModifyIp").text("密码修改日志Ip不能为空!");
				flag = false;
			} else
				$("#spanModifyIp").text("");

			$("#hidOperationType").val("save");//设置操作类型
			if (flag)
				$("#frmModifyPasswordLogs").submit();
		});

		$("#btnCancelSave").click(function() {
			$("#spanModifyIp").text("");
			$("#hidOperationType").val("cancelSave");//设置操作类型
		});

		$("#btnSearch")
				.click(
						function() {
							var flag = true;
							if ($("#txtSearchModifyIp").val().length == 0
									&& $("#txtSearchModifyDateTimeBegin").val().length == 0
									&& $("#txtSearchModifyDateTimeEnd").val().length == 0) {
								$("#spanSearchModifyIp").text("查询条件不能为空!");
								flag = false;
							} else
								$("#spanSearchModifyIp").text("");

							$("#hidOperationType").val("search");//设置操作类型

							if (flag)
								$("#frmModifyPasswordLogs").submit();
						});

		$("#btnCancelSearch").click(function() {
			//将所有查询条件设置成空
			$("#txtSearchModifyIp").val("");
			$("#txtSearchModifyDateTimeBegin").val("");
			$("#txtSearchModifyDateTimeEnd").val("");

			$("#hidOperationType").val("cancelSearch");//设置操作类型
			$("#frmModifyPasswordLogs").submit();
		});

		function removeModifyPasswordLog(modifyPasswordLogId,
				modifyPasswordLogName) {
			if (confirm("是否删除？“" + modifyPasswordLogName + "”密码修改日志信息？")) {
				$("#hidModifyPasswordLogId").val(modifyPasswordLogId);//传递要删除的主键值
				$("#hidOperationType").val("remove");//设置操作类型
				$("#frmModifyPasswordLogs").submit();
			}
		}

		function modifyModifyPasswordLog(modifyPasswordLogId) {
			$("#hidModifyPasswordLogId").val(modifyPasswordLogId);//传递要修改的主键值
			$("#hidOperationType").val("modify");//设置操作类型
			$("#frmModifyPasswordLogs").submit();
		}

		function sorting(fieldName) {
			$("#hidFieldName").val(fieldName);//传递要排序的列名
			$("#hidOperationType").val("sorting");//设置操作类型
			$("#frmModifyPasswordLogs").submit();
		}

		function paging(pageIndex) {
			$("#hidPageIndex").val(pageIndex);//传递目标页码
			$("#hidOperationType").val("paging");//设置操作类型
			$("#frmModifyPasswordLogs").submit();
		}

		function resize(rad) {
			$("#hidPageSize").val(rad.value);//传递页尺寸
			$("#hidOperationType").val("resize");//设置操作类型
			$("#frmModifyPasswordLogs").submit();
		}
	</script>
</body>
</html>