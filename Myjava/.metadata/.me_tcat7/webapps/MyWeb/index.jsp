<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="
Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<!-- Title and other stuffs -->
<title>EMS</title>
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
	<!-- 重用头样式 -->
	<div class="navbar navbar-fixed-top bs-docs-nav" role="banner">

		<div class="conjtainer">
			<!-- Menu button for smallar screens -->
			<div class="navbar-header">
				<button class="navbar-toggle btn-navbar" type="button"
					data-toggle="collapse" data-target=".bs-navbar-collapse">
					<span>菜单</span>
				</button>
				<!-- Site name for smallar screens -->
				<a href="index.html" class="navbar-brand hidden-lg">首页</a>
			</div>

			<!-- Navigation starts -->
			<nav class="collapse navbar-collapse bs-navbar-collapse"
				role="navigation">

				<ul class="nav navbar-nav">

					<!-- Upload to server link. Class "dropdown-big" creates big dropdown -->
					<li class="dropdown dropdown-big"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"><span
							class="label label-success"><i class="icon-cloud-upload"></i></span>
							用户主页</a> <!-- Dropdown -->
						<ul class="dropdown-menu">
							<li>
								<!-- Progress bar -->
								<p>图片上传进度</p> <!-- Bootstrap progress bar -->
								<div class="progress progress-striped active">
									<div class="progress-bar progress-bar-info" role="progressbar"
										aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
										style="width: 40%">
										<span class="sr-only">完成40%</span>
									</div>
								</div>

								<hr /> <!-- Progress bar -->
								<p>视频上传进度</p> <!-- Bootstrap progress bar -->
								<div class="progress progress-striped active">
									<div class="progress-bar progress-bar-success"
										role="progressbar" aria-valuenow="80" aria-valuemin="0"
										aria-valuemax="100" style="width: 80%">
										<span class="sr-only">完成80%</span>
									</div>
								</div>

								<hr /> <!-- Dropdown menu footer -->
								<div class="drop-foot">
									<a href="#">查看所有</a>
								</div>

							</li>
						</ul></li>

					<!-- Sync to server link -->
					<li class="dropdown dropdown-big"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"><span
							class="label label-danger"><i class="icon-refresh"></i></span>
							管理员主页</a> <!-- Dropdown -->
						<ul class="dropdown-menu">
							<li>
								<!-- Using "icon-spin" class to rotate icon. -->
								<p>
									<span class="label label-info"><i class="icon-cloud"></i></span>同步会员到服务器
								</p>
								<hr />
								<p>
									<span class="label label-warning"><i class="icon-cloud"></i></span>同步书签到云端
								</p>

								<hr /> <!-- Dropdown menu footer -->
								<div class="drop-foot">
									<a href="#">查看所有</a>
								</div>

							</li>
						</ul></li>

				</ul>

				<!-- Search form -->
				<form class="navbar-form navbar-left" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="查询">
					</div>
				</form>
				<!-- Links -->
				<ul class="nav navbar-nav pull-right">
					<li class="dropdown pull-right"><a data-toggle="dropdown"
						class="dropdown-toggle" href="#"> <i class="icon-user"></i>
							${sessionScope.Login.loginName}感谢你${sessionScope.Login.loginTime}次登录 <%
 	//out.println(session.getAttribute("Name"));	//从session中读取登录名)
 %><b class="caret"></b>
					</a> <!-- Dropdown menu -->
						<ul class="dropdown-menu">
							<li><a href="ModifyInformationServlet"><i class="icon-user"></i> 修改个人信息</a></li>
							<li><a href="ModifyPasswordServlet"><i class="icon-cogs"></i> 修改密码</a></li>
							<li><a href=LogoutServlet><i class="icon-off"></i> 退出</a></li>
						</ul></li>

				</ul>
			</nav>

		</div>
	</div>

	<!-- Main content starts -->

	<div class="content">
		<!-- Sidebar -->
		<div class="sidebar">
			<div class="sidebar-dropdown">
				<a href="#">导航</a>
			</div>

			<!--- Sidebar navigation -->
			<!-- If the main navigation has sub navigation, then add the class "has_sub" to "li" of main navigation. -->
			<jsp:include page="left.jsp"></jsp:include>
			<!--	导航栏-->
		</div>

		<!-- Sidebar ends -->

		<!-- Main bar -->
		<div class="mainbar">
			<!-- Page heading -->
			<div class="page-head">
				<h2 class="pull-left">
					<i class="icon-home"></i> 首页
				</h2>

				<!-- Breadcrumb -->
				<div class="bread-crumb pull-right">
					<a href=index.jsp><i class="icon-home"></i> 首页</a>
					<!-- Divider -->
					<span class="divider">/</span> <a href="#" class="bread-current">控制台</a>
				</div>

				<div class="clearfix"></div>

			</div>
			<!-- Page heading ends -->

			<!-- Matter -->

			<div class="matter">
				<div class="container">

					<!-- Dashboard Graph starts -->

					<div class="row">

						<div class="col-md-4"></div>
					</div>
					<!-- Dashboard graph ends -->

					<!-- Chats, File upload and Recent Comments -->
					<div class="row">

						<div class="col-md-4">
							<!-- Widget -->
						</div>

						<!-- File Upload widget -->
						<div class="col-md-4"></div>

					</div>

					<!-- Matter ends -->

				</div>

				<!-- Mainbar ends -->
				<div class="clearfix"></div>

			</div>
			<!-- Content ends -->

			<!-- Footer starts -->

			

			<!-- Footer ends -->

			<!-- Scroll to top -->
			<span class="totop"><a href="#"><i class="icon-chevron-up"></i></a></span>
			<jsp:include page="bottom.jsp"></jsp:include>
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
													showTooltip(item.pageX,
															item.pageY,
															item.series.label
																	+ " of "
																	+ x + " = "
																	+ y);
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
															+ item.dataIndex
															+ " in "
															+ item.series.label
															+ ".");
											plot.highlight(item.series,
													item.datapoint);
										}
									});
				});
				/* Curve chart ends */
			</script>
</body>

</html>