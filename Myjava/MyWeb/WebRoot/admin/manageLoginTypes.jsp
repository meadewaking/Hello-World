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
<title>显示登录类型信息</title>
<meta name="keywords"
	content="Bootstrap模版,Bootstrap模版下载,Bootstrap教程,Bootstrap中文,后台管理系统模版,后台模版下载,后台管理系统,后台管理模版" />
<meta name="description"
	content="代码家园-www.daimajiayuan.com提供Bootstrap模版,后台管理系统模版,后台管理界面,Bootstrap教程,Bootstrap中文翻译等相关Bootstrap插件下载" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="">
<!-- Stylesheets -->
<link href="/MyWeb/style/bootstrap.css" rel="stylesheet">
<!-- Font awesome icon -->
<link rel="stylesheet" href="/MyWeb/style/font-awesome.css">
<!-- jQuery UI -->
<link rel="stylesheet" href="/MyWeb/style/jquery-ui.css">
<!-- Calendar -->
<link rel="stylesheet" href="/MyWeb/style/fullcalendar.css">
<!-- prettyPhoto -->
<link rel="stylesheet" href="/MyWeb/style/prettyPhoto.css">
<!-- Star rating -->
<link rel="stylesheet" href="/MyWeb/style/rateit.css">
<!-- Date picker -->
<link rel="stylesheet" href="/MyWeb/style/bootstrap-datetimepicker.min.css">
<!-- CLEditor -->
<link rel="stylesheet" href="/MyWeb/style/jquery.cleditor.css">
<!-- Uniform -->
<link rel="stylesheet" href="/MyWeb/style/uniform.default.css">
<!-- Bootstrap toggle -->
<link rel="stylesheet" href="/MyWeb/style/bootstrap-switch.css">
<!-- Main stylesheet -->
<link href="/MyWeb/style/style.css" rel="stylesheet">
<!-- Widgets stylesheet -->
<link href="/MyWeb/style/widgets.css" rel="stylesheet">

<!-- HTML5 Support for IE -->
<!--[if lt IE 9]>
  <script src="/MyWeb/js/html5shim.js"></script>
  <![endif]-->

<!-- Favicon -->
<link rel="shortcut icon" href="img/favicon/favicon.png">
</head>

<body>

	<jsp:include page="/top.jsp"></jsp:include>
	<jsp:include page="/left.jsp"></jsp:include>
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
				<form class="form-horizontal" id="frmLoginTypes"
					action="ManageLoginTypesServlet" method="post">
					<input type="hidden" name="hidOperationType" id="hidOperationType" />
					<input type="hidden" name="hidLoginTypeId" id="hidLoginTypeId"
						value="${requestScope.ModifyLoginType.loginTypeId}" /> <input
						type="hidden" name="hidFieldName" id="hidFieldName"
						value="${requestScope.fieldName}" /> <input type="hidden"
						name="hidSortString" id="hidSortString"
						value="${requestScope.sortString}" /> <input type="hidden"
						name="hidPageIndex" id="hidPageIndex"
						value="${requestScope.pageIndex}" /> <input type="hidden"
						name="hidPageSize" id="hidPageSize"
						value="${requestScope.pageSize}" />
					<!-- 隐藏的输入项用以保存传输值，并被调用 -->
					<div class="container">

						<!-- Table -->

						<div class="row">

							<div class="col-md-12">
								<div class="widget">

									<div class="widget-head">
										<div class="pull-left">登录类型信息添加</div>
										<div class="widget-icons pull-right">
											<a href="#" class="wminimize"><i class="icon-chevron-up"></i>
											</a> <a href="#" class="wclose"><i class="icon-remove"></i> </a>
										</div>
										<div class="clearfix"></div>
									</div>
									<c:if test="${empty requestScope.ModifyLoginType}">
										<div class="widget-content" style="display: none">
									</c:if>
									<c:if test="${not empty requestScope.ModifyLoginType}">
										<div class="widget-content">
									</c:if>
									<div class="padd">


										<div class="form-group">
											<label class="control-label col-lg-3">登录类型名称</label>
											<div class="col-lg-9">
												<input type="text" class="form-control"
													id="txtLoginTypeName" name="txtLoginTypeName"
													placeholder="登录类型名称"> <span
													class="label label-danger" id="spanLoginTypeName"></span>
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-lg-3">所在地址</label>
											<div class="col-lg-9">
												<input type="text" class="form-control"
													name="txtdefaultPage" placeholder="所在地址">
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-lg-3">描述</label>
											<div class="col-lg-9">
												<textarea class="form-control" name="txtDescription"
													placeholder="描述"></textarea>
											</div>
										</div>
										<div class="col-lg-9 col-lg-offset-2">
											<c:if test="${empty ModifyLoginType}">
												<button type="button" class="btn btn-primary" id="btnAdd">添加</button>

												<button type="reset" class="btn btn-default">取消</button>
											</c:if>
											<c:if test="${not empty ModifyLoginType}">
												<!-- jstl的判断引用不同按钮样式 -->
												<button type="button" class="btn btn-primary" id="btnSave">保存</button>
												<button type="button" class="btn btn-default"
													id="btnCancelSave">取消保存</button>
											</c:if>
										</div>
										<br /> <br />


									</div>




								</div>
							</div>
						</div>
					</div>

					<div class="row">

						<div class="col-md-12">
							<div class="widget">

								<div class="widget-head">
									<div class="pull-left">登录类型信息查询</div>
									<div class="widget-icons pull-right">
										<a href="#" class="wminimize"><i class="icon-chevron-up"></i>
										</a> <a href="#" class="wclose"><i class="icon-remove"></i> </a>
									</div>
									<div class="clearfix"></div>
								</div>

								<div class="widget-content" style="display: none">


									<div class="padd">


										<div class="form-group">
											<label class="control-label col-lg-3">登录类型名称</label>
											<div class="col-lg-9">
												<input type="text" class="form-control"
													id="txtSearchLoginTypeName" name="txtSearchLoginTypeName"
													placeholder="登录类型名称" value="${searcher.loginTypeName }">
												<span class="label label-danger"
													id="spanSearchLoginTypeName"></span>
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-lg-3">所在地址</label>
											<div class="col-lg-9">
												<input type="text" class="form-control"
													id="txtSearchdefaultPage" name="txtSearchdefaultPage"
													placeholder="所在地址" value="${searcher.defaultPage }">
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-lg-3">描述</label>
											<div class="col-lg-9">
												<textarea class="form-control" id="txtSearchDescription"
													name="txtSearchDescription" placeholder="描述">${searcher.description }</textarea>
											</div>
										</div>
										<div class="col-lg-9 col-lg-offset-2">

											<button type="button" class="btn btn-primary" id="btnSearch">查询</button>

											<button type="reset" class="btn btn-default"
												id="btnCancelSearch">取消</button>

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
									<div class="pull-left">登录类型信息列表</div>
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
												<th><a href="javascript:sorting('LoginTypeId');">登录类型编号</a></th>
												<th><a href="javascript:sorting('LoginTypeName');">登录类型名称</a></th>
												<th><a href="javascript:sorting('defaultPage');">登录类型地址</a></th>
												<th><a href="javascript:sorting('Description');">描述</a></th>
												<th></th>
											</tr>
										</thead>
										<tbody>
											<%-- <%
						List<LoginTypeBean> LoginTypes = (List<LoginTypeBean>)request.getAttribute("LoginTypes");
						 for (int i = 0; i<LoginTypes.size(); i++) { %>
                        <tr>
                          <td><%=LoginTypes.get(i).getLoginTypeId() %></td>
                          <td><%=LoginTypes.get(i).getLoginTypeName() %></td>
                          <td><%=LoginTypes.get(i).getdefaultPage() %></td>
                          <td><%=LoginTypes.get(i).getDescription() %></td>
                          <td>
                              <button class="btn btn-xs btn-warning"><i class="icon-pencil"></i> </button>
                              <button class="btn btn-xs btn-danger"><i class="icon-remove"></i> </button>                         
                          </td>
                        </tr>                                                        
						<%} %> --%>
											<c:forEach items="${loginTypes}" var="loginType"
												begin="${begin - 1 }" end="${end - 1 }">
												<!-- jstl显示登录类型表 -->
												<tr>
													<td>${loginType.loginTypeId}</td>
													<td>${loginType.loginTypeName}</td>
													<td>${loginType.defaultPage}</td>
													<td>${loginType.description}</td>
													<td>
														<button class="btn btn-xs btn-warning"
															onclick="modifyLoginType(${loginType.loginTypeId})">
															<i class="icon-pencil"></i>
														</button>
														<button class="btn btn-xs btn-danger"
															onclick="removeLoginType(${loginType.loginTypeId},${loginType.loginTypeName})">
															<i class="icon-remove"></i>
														</button>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>

									<div class="widget-foot">
										<label class="control-label col-lg-6 "
											style="white-space: nowrap; ">共${rowCount }条记录
											第${pageIndex }页 共${pageCount }页 <input type="radio"
											name="radPageSize" value="2" onclick="resize(this)"
											<c:if test="${pageSize == 2}">checked </c:if> /> 2条/页<input
											type="radio" name="radPageSize" value="3"
											onclick="resize(this)"
											<c:if test="${pageSize == 3}">checked </c:if> /> 3条/页<input
											type="radio" name="radPageSize" value="5"
											onclick="resize(this)"
											<c:if test="${pageSize == 5}">checked </c:if> /> 5条/页<input
											type="text" id="txtPageSize" placeholder="目标页码"
											value="${requestScope.pageIndex}">
											<button type="button" id="btnPaging">翻页</button>
										</label>

										<ul class="pagination pull-right">
											<c:if test="${pageIndex <= 1 }">
												<li><a href="#">首页</a></li>
												<li><a href="#">上一页</a></li>
											</c:if>
											<c:if test="${pageIndex > 1 }">
												<li><a href="javascript:paging(1);">首页</a></li>
												<li><a
													href="javascript:paging(${requestScope.pageIndex - 1 });">上一页</a></li>
											</c:if>
											<c:if test="${pageIndex >= pageCount }">
												<li><a href="#">下一页</a></li>
												<li><a href="#">尾页</a></li>
											</c:if>
											<c:if test="${pageIndex < pageCount }">
												<li><a
													href="javascript:paging(${requestScope.pageIndex + 1});">下一页</a></li>
												<li><a
													href="javascript:paging(${requestScope.pageCount});">尾页</a></li>
											</c:if>
										</ul>
										<div class="clearfix"></div>

									</div>

								</div>

							</div>


						</div>

					</div>
				</form>
			</div>

		</div>

		<!-- Matter ends -->


	</div>

	<!-- Mainbar ends -->
	<div class="clearfix"></div>


	<jsp:include page="/bottom.jsp"></jsp:include>

	<!-- Scroll to top -->
	<span class="totop"><a href="#"><i class="icon-chevron-up"></i>
	</a> </span>

	<!-- JS -->
	<script src="/MyWeb/js/jquery.js"></script>
	<!-- jQuery -->
	<script src="/MyWeb/js/bootstrap.js"></script>
	<!-- Bootstrap -->
	<script src="/MyWeb/js/jquery-ui-1.9.2.custom.min.js"></script>
	<!-- jQuery UI -->
	<script src="/MyWeb/js/fullcalendar.min.js"></script>
	<!-- Full Google Calendar - Calendar -->
	<script src="/MyWeb/js/jquery.rateit.min.js"></script>
	<!-- RateIt - Star rating -->
	<script src="/MyWeb/js/jquery.prettyPhoto.js"></script>
	<!-- prettyPhoto -->

	<!-- jQuery Flot -->
	<script src="/MyWeb/js/excanvas.min.js"></script>
	<script src="/MyWeb/js/jquery.flot.js"></script>
	<script src="/MyWeb/js/jquery.flot.resize.js"></script>
	<script src="/MyWeb/js/jquery.flot.pie.js"></script>
	<script src="/MyWeb/js/jquery.flot.stack.js"></script>

	<!-- jQuery Notification - Noty -->
	<script src="/MyWeb/js/jquery.noty.js"></script>
	<!-- jQuery Notify -->
	<script src="/MyWeb/js/themes/default.js"></script>
	<!-- jQuery Notify -->
	<script src="/MyWeb/js/layouts/bottom.js"></script>
	<!-- jQuery Notify -->
	<script src="/MyWeb/js/layouts/topRight.js"></script>
	<!-- jQuery Notify -->
	<script src="/MyWeb/js/layouts/top.js"></script>
	<!-- jQuery Notify -->
	<!-- jQuery Notification ends -->

	<script src="/MyWeb/js/sparklines.js"></script>
	<!-- Sparklines -->
	<script src="/MyWeb/js/jquery.cleditor.min.js"></script>
	<!-- CLEditor -->
	<script src="/MyWeb/js/bootstrap-datetimepicker.min.js"></script>
	<!-- Date picker -->
	<script src="/MyWeb/js/jquery.uniform.min.js"></script>
	<!-- jQuery Uniform -->
	<script src="/MyWeb/js/bootstrap-switch.min.js"></script>
	<!-- Bootstrap Toggle -->
	<script src="/MyWeb/js/filter.js"></script>
	<!-- Filter for support page -->
	<script src="/MyWeb/js/custom.js"></script>
	<!-- Custom codes -->
	<script src="/MyWeb/js/charts.js"></script>
	<!-- Charts & Graphs -->
	<script type="text/javascript">
		/*	$("#frmLoginTypes").submit(function() {
				var flag = true;
				if ($("#txtLoginTypeName").val().length == 0) {
					$("#spanLoginTypeName").text("登录类型名称不能为空!");
					flag = false;
				} else
					$("#spanLoginTypeName").text("");
				return flag;
			}); */

		${requestScope.Message}//el表达式语言
		$("#btnAdd").click(function() {
			var flag = true;
			if ($("#txtLoginTypeName").val().length == 0) {
				$("#spanLoginTypeName").text("登录类型名称不能为空!");
				flag = false;
			} else
				$("#spanLoginTypeName").text("");

			$("#hidOperationType").val("add");//设置操作类型
			if (flag)
				$("#frmLoginTypes").submit();
		});

		$("#btnSave").click(function() {
			var flag = true;
			if ($("#txtLoginTypeName").val().length == 0) {
				$("#spanLoginTypeName").text("登录类型名称不能为空!");
				flag = false;
			} else
				$("#spanLoginTypeName").text("");

			$("#hidOperationType").val("save");//设置操作类型
			if (flag)
				$("#frmLoginTypes").submit();
		});
		$("#btnCancelSave").click(function() {
			$("#spanLoginTypeName").text("");
			$("#hidOperationType").val("cancelSave");//设置操作类型
			$("#frmLoginTypes").submit();
		});
		$("#btnPaging").click(function() {
			if (isNaN($("txtPageSize").val())) {
				var pageIndex = parseInt($("#txtPageSize").val());
				if ((pageIndex < 1) || (pageIndex > ${pageCount}))
					alert("");
				else {
					paging(pageIndex);
				}
			}
		});
		$("#btnSearch").click(
				function() {
					var flag = true;
					if ($("#txtSearchLoginTypeName").val().length == 0
							&& $("#txtSearchdefaultPage").val().length == 0
							&& $("#txtSearchDescription").val().length == 0) {
						$("#spanSearchLoginTypeName").text("查询条件不能为空！");
						flag = false;
					} else
						$("#spanSearchLoginTypeName").text("");
					$("#hidOperationType").val("search");

					if (flag)
						$("#frmLoginTypes").submit();
				});
		$("#btnCancelSearch").click(function() {
			$("#txtSearchLoginTypeName").val("");
			$("#txtSearchdefaultPage").val("");
			$("#txtSearchDescription").val("");

			$("#hidOperationType").val("cancelSearch");
			$("#frmLoginTypes").submit();
		});

		function removeLoginType(LoginTypeId, LoginTypeName) {
			if (confirm("是否删除？" + LoginTypeName + "登录类型信息？")) {
				$("#hidLoginTypeId").val(LoginTypeId);//传递要删除的主键值
				$("#hidOperationType").val("remove");//设置操作类型
				$("#frmLoginTypes").submit();
			}
		}

		function sorting(fieldName) {
			$("#hidFieldName").val(fieldName);//传递要排序的列名
			$("#hidOperationType").val("sorting");//设置操作类型
			$("#frmLoginTypes").submit();
		}

		function modifyLoginType(LoginTypeId) {
			$("#hidLoginTypeId").val(LoginTypeId);//传递要修改的主键值
			$("#hidOperationType").val("modify");//设置操作类型
			$("#frmLoginTypes").submit();
		}

		function paging(pageIndex) {
			$("#hidPageIndex").val(pageIndex); //传递目标页码
			$("#hidOperationType").val("paging");//设置操作类型
			$("#frmLoginTypes").submit();
		}

		function resize(rad) {
			$("#hidPageSize").val(rad.value); //传递尺寸
			$("#hidOperationType").val("paging");//设置操作类型
			$("#frmLoginTypes").submit();
		}
	</script>
</body>
</html>