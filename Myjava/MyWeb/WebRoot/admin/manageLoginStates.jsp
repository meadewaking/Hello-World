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
<title>显示登录状态信息</title>
<meta name="keywords" content="企业消息平台，EMS，登录状态信息管理，登录状态信息，信息管理" />
<meta name="description" content="企业消息平台的登录状态信息管理页面" />
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

	<%@ include file="/top.jsp"%>

	<div class="content">

		<%@include file="/left.jsp"%>

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
				<form class="form-horizontal" id="frmLoginStates"
					action="ManageLoginStatesServlet" method="post">
					<input type="hidden" name="hidOperationType" id="hidOperationType" />
					<input type="hidden" name="hidLoginStateId" id="hidLoginStateId"
						value="${requestScope.ModifyLoginState.loginStateId}" /> <input
						type="hidden" name="hidFieldName" id="hidFieldName"
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
										<div class="pull-left">登录状态录入</div>
										<div class="widget-icons pull-right">
											<a href="#" class="wminimize"><i
												class="icon-chevron-down"></i></a> <a href="#" class="wclose"><i
												class="icon-remove"></i></a>
										</div>
										<div class="clearfix"></div>
									</div>

									<div class="widget-content"
										<c:if test="${empty requestScope.ModifyLoginState}">style="display: none;"</c:if >>
										<div class="padd">
											<div class="form-group">
												<label class="control-label col-lg-6"
													style="white-space:nowrap">登录状态名称</label>
												<div class="col-lg-9">
													<input type="text" class="form-control"
														id="txtLoginStateName" name="txtLoginStateName"
														placeholder="登录状态名称"
														value="${requestScope.ModifyLoginState.loginStateName}">
													<span class="label label-danger" id="spanLoginStateName"></span>
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-lg-6"
													style="white-space:nowrap">可登录</label>
												<div class="col-lg-9">
													<label class="checkbox-inline"> <input
														type="checkbox" name="txtCanLogin" placeholder="可登录"
														value="true"
														<c:if test="${requestScope.ModifyLoginState.canLogin}">checked</c:if>>
													</label>
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-lg-6"
													style="white-space:nowrap">描述</label>
												<div class="col-lg-9">
													<textarea class="form-control" name="txtDescription"
														placeholder="描述">${requestScope.ModifyLoginState.description}</textarea>
												</div>
											</div>
											<div class="col-lg-9 col-lg-offset-2">
												<c:if test="${empty ModifyLoginState}">
													<button type="button" class="btn btn-primary" id="btnAdd">添加</button>
													<button type="button" class="btn btn-default"
														id="btnCancel">取消</button>
												</c:if>
												<c:if test="${not empty ModifyLoginState}">
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
										<div class="pull-left">查询登录状态</div>
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
													style="white-space:nowrap">登录状态名称</label>
												<div class="col-lg-9">
													<input type="text" class="form-control"
														id="txtSearchLoginStateName"
														name="txtSearchLoginStateName" placeholder="登录状态名称"
														value="${Searcher.loginStateName}"> <span
														class="label label-danger" id="spanSearchLoginStateName"></span>
												</div>
											</div>

											<div class="form-group">
												<label class="control-label col-lg-6"
													style="white-space:nowrap">描述</label>
												<div class="col-lg-9">
													<textarea class="form-control" id="txtSearchDescription"
														name="txtSearchDescription" placeholder="描述">${Searcher.description}</textarea>
												</div>
											</div>
											<div class="col-lg-9 col-lg-offset-2">
												<button type="button" class="btn btn-primary" id="btnSearch">查询</button>
												<button type="button" class="btn btn-default"
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
										<div class="pull-left">登录状态列表</div>
										<div class="widget-icons pull-right">
											<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
											<a href="#" class="wclose"><i class="icon-remove"></i></a>
										</div>
										<div class="clearfix"></div>
									</div>

									<div class="widget-content">
										<table class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th><a href="javascript: sorting('LoginStateId');">登录状态编号</a></th>
													<th><a href="javascript: sorting('LoginStateName');">登录状态名称</a></th>
													<th><a href="javascript: sorting('CanLogin');">可登录</a></th>
													<th><a href="javascript: sorting('Description');">描述</a></th>
													<th></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${loginStates}" var="loginState"
													begin="${begin - 1}" end="${end - 1}">
													<tr>
														<td>${loginState.loginStateId}</td>
														<td>${loginState.loginStateName}</td>
														<td><c:if test="${loginState.canLogin}">
																<button class="btn btn-xs btn-success"
																	disabled="disabled">
																	<i class="icon-ok"></i>
																</button>
															</c:if> <c:if test="${not loginState.canLogin}">
																<button class="btn btn-xs btn-danger"
																	disabled="disabled">
																	<i class="icon-remove"></i>
																</button>
															</c:if></td>
														<td>${loginState.description}</td>
														<td>
															<button class="btn btn-xs btn-warning"
																onclick="modifyLoginState(${loginState.loginStateId})">
																<i class="icon-pencil"></i>
															</button>
															<button class="btn btn-xs btn-danger"
																onclick="removeLoginState(${loginState.loginStateId},'${loginState.loginStateName}')">
																<i class="icon-remove"></i>
															</button>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>

										<div class="widget-foot">
											<label class="control-label col-lg-6"
												style="white-space:nowrap">共${rowCount}条数据
												第${pageIndex}页 共${pageCount}页&nbsp;&nbsp;&nbsp; <input
												type="radio" name="radPageSize" value="2"
												onclick="resize(this)"
												<c:if test="${pageSize == 2}">checked</c:if>> 2条/页 <input
												type="radio" name="radPageSize" value="3"
												onclick="resize(this)"
												<c:if test="${pageSize == 3}">checked</c:if>> 3条/页 <input
												type="radio" name="radPageSize" value="5"
												onclick="resize(this)"
												<c:if test="${pageSize == 5}">checked</c:if>> 5条/页
											</label>

											<ul class="pagination pull-right">
												<c:if test="${pageIndex <= 1}">
													<li><a href="#">首页</a></li>
													<li><a href="#">上一页</a></li>
												</c:if>
												<c:if test="${pageIndex > 1}">
													<li><a href="javascript: paging(1)">首页</a></li>
													<li><a
														href="javascript: paging(${requestScope.pageIndex - 1})">上一页</a></li>
												</c:if>

												<c:if test="${pageIndex >= pageCount}">
													<li><a href="#">下一页</a></li>
													<li><a href="#">尾页</a></li>
												</c:if>
												<c:if test="${pageIndex < pageCount}">
													<li><a
														href="javascript: paging(${requestScope.pageIndex + 1})">下一页</a></li>
													<li><a
														href="javascript: paging(${requestScope.pageCount})">尾页</a></li>
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
	<!-- Content ends -->

	<jsp:include page="/bottom.jsp"></jsp:include>
	<!-- Scroll to top -->
	<span class="totop"><a href="#"><i class="icon-chevron-up"></i></a></span>

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

	<!-- Script for this page -->
	<script type="text/javascript">
		$(function() {

			/* Bar Chart starts */

			var d1 = [];
			for (var i = 0; i <= 20; i += 1)
				d1.push([ i, parseInt(Math.random() * 30) ]);

			var d2 = [];
			for (var i = 0; i <= 20; i += 1)
				d2.push([ i, parseInt(Math.random() * 30) ]);

			var stack = 0, bars = true, lines = false, steps = false;

			function plotWithOptions() {
				$.plot($("#bar-chart"), [ d1, d2 ], {
					series : {
						stack : stack,
						lines : {
							show : lines,
							fill : true,
							steps : steps
						},
						bars : {
							show : bars,
							barWidth : 0.8
						}
					},
					grid : {
						borderWidth : 0,
						hoverable : true,
						color : "#777"
					},
					colors : [ "#ff6c24", "#ff2424" ],
					bars : {
						show : true,
						lineWidth : 0,
						fill : true,
						fillColor : {
							colors : [ {
								opacity : 0.9
							}, {
								opacity : 0.8
							} ]
						}
					}
				});
			}

			plotWithOptions();

			$(".stackControls input").click(function(e) {
				e.preventDefault();
				stack = $(this).val() == "With stacking" ? true : null;
				plotWithOptions();
			});
			$(".graphControls input").click(function(e) {
				e.preventDefault();
				bars = $(this).val().indexOf("Bars") != -1;
				lines = $(this).val().indexOf("Lines") != -1;
				steps = $(this).val().indexOf("steps") != -1;
				plotWithOptions();
			});

			/* Bar chart ends */

		});

		/* Curve chart starts */

		$(function() {
			var sin = [], cos = [];
			for (var i = 0; i < 14; i += 0.5) {
				sin.push([ i, Math.sin(i) ]);
				cos.push([ i, Math.cos(i) ]);
			}

			var plot = $.plot($("#curve-chart"), [ {
				data : sin,
				label : "sin(x)"
			}, {
				data : cos,
				label : "cos(x)"
			} ], {
				series : {
					lines : {
						show : true,
						fill : true
					},
					points : {
						show : true
					}
				},
				grid : {
					hoverable : true,
					clickable : true,
					borderWidth : 0
				},
				yaxis : {
					min : -1.2,
					max : 1.2
				},
				colors : [ "#1eafed", "#1eafed" ]
			});

			function showTooltip(x, y, contents) {
				$('<div id="tooltip">' + contents + '</div>').css({
					position : 'absolute',
					display : 'none',
					top : y + 5,
					left : x + 5,
					border : '1px solid #000',
					padding : '2px 8px',
					color : '#ccc',
					'background-color' : '#000',
					opacity : 0.9
				}).appendTo("body").fadeIn(200);
			}

			var previousPoint = null;
			$("#curve-chart")
					.bind(
							"plothover",
							function(event, pos, item) {
								$("#x").text(pos.x.toFixed(2));
								$("#y").text(pos.y.toFixed(2));

								if ($("#enableTooltip:checked").length > 0) {
									if (item) {
										if (previousPoint != item.dataIndex) {
											previousPoint = item.dataIndex;

											$("#tooltip").remove();
											var x = item.datapoint[0]
													.toFixed(2), y = item.datapoint[1]
													.toFixed(2);

											showTooltip(item.pageX, item.pageY,
													item.series.label + " of "
															+ x + " = " + y);
										}
									} else {
										$("#tooltip").remove();
										previousPoint = null;
									}
								}
							});

			$("#curve-chart")
					.bind(
							"plotclick",
							function(event, pos, item) {
								if (item) {
									$("#clickdata").text(
											"You clicked point "
													+ item.dataIndex + " in "
													+ item.series.label + ".");
									plot.highlight(item.series, item.datapoint);
								}
							});

		});

		/* Curve chart ends */
	</script>

	<script type="text/javascript">
		${requestScope.Message}

		$("#btnAdd").click(function() {
			var flag = true;
			if ($("#txtLoginStateName").val().length == 0) {
				$("#spanLoginStateName").text("登录状态不能为空！");
				flag = false;
			} else
				$("#spanLoginStateName").text("");

			$("#hidOperationType").val("add"); // 设置操作状态

			if (flag)
				$("#frmLoginStates").submit();
		});

		$("#btnCancel").click(function() {
			$("#hidOperationType").val("cancel");//设置操作状态
			$("#frmLoginStates").submit();
		});

		$("#btnSave").click(function() {
			var flag = true;
			if ($("#txtLoginStateName").val().length == 0) {
				$("#spanLoginStateName").text("登录状态不能为空！");
				flag = false;
			} else
				$("#spanLoginStateName").text("");

			$("#hidOperationType").val("save"); // 设置操作状态

			if (flag)
				$("#frmLoginStates").submit();
		});
		$("#btnCancelSave").click(function() {
			$("#hidOperationType").val("cancelSave");
			$("#frmLoginStates").submit();
		});

		$("#btnSearch").click(
				function() {
					var flag = true;
					if ($("#txtSearchLoginStateName").val().length == 0
							&& $("#txtSearchDescription").val().length == 0) {
						$("#spanSearchLoginStateName").text("查询条件不能为空！");
						flag = false;
					} else
						$("#spanSearchLoginStateName").text("");

					$("#hidOperationType").val("search"); // 设置操作状态

					if (flag)
						$("#frmLoginStates").submit();
				});

		$("#btnCancelSearch").click(function() {
			// 将所有查询条件设置为空
			$("#txtSearchLoginStateName").val("");
			$("#txtSearchCanLogin").val("");
			$("#txtSearchDescription").val("");

			$("#hidOperationType").val("cancelserch"); // 设置操作状态 
			$("#frmLoginStates").submit();
		});

		function removeLoginState(loginStateId, loginStateName) {
			if (confirm("是否删除“" + loginStateName + "”登录状态?")) {
				$("#hidLoginStateId").val(loginStateId); // 传递要删除的主键值
				$("#hidOperationType").val("remove"); // 设置操作状态
				$("#frmLoginStates").submit();
			}
		}

		function modifyLoginState(loginStateId) {
			$("#hidLoginStateId").val(loginStateId); // 传递要修改的主键值
			$("#hidOperationType").val("modify"); // 设置操作状态
			$("#frmLoginStates").submit();
		}

		function sorting(fieldName) {
			$("#hidFieldName").val(fieldName); // 传递要排序的列名
			$("#hidOperationType").val("sorting"); // 设置操作状态
			$("#frmLoginStates").submit();
		}

		function paging(pageIndex) {
			$("#hidPageIndex").val(pageIndex); // 传递目标页页码
			$("#hidOperationType").val("paging"); // 设置操作状态
			$("#frmLoginStates").submit();
		}

		function resize(rad) {
			$("#hidPageSize").val(rad.value); // 传递页尺寸
			$("#hidOperationType").val("resize"); // 设置操作状态
			$("#frmLoginStates").submit();
		}
	</script>

</body>
</html>
