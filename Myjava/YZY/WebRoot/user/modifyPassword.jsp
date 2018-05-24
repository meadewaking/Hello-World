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
<html>
<head>
<title>EMS企业消息管理</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8" />

<link rel="icon" type="image/ico"
	href="http://tattek.com/minimal/YZY/assets/images/favicon.ico" />
<!-- Bootstrap -->
<link href="/YZY/assets/css/vendor/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link
	href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="/YZY/assets/css/vendor/animate/animate.min.css">
<link type="text/css" rel="stylesheet" media="all"
	href="/YZY/assets/js/vendor/mmenu/css/jquery.mmenu.all.css" />
<link rel="stylesheet"
	href="/YZY/assets/js/vendor/videobackground/css/jquery.videobackground.css">
<link rel="stylesheet"
	href="/YZY/assets/css/vendor/bootstrap-checkbox.css">

<link rel="stylesheet"
	href="/YZY/assets/js/vendor/rickshaw/css/rickshaw.min.css">
<link rel="stylesheet"
	href="/YZY/assets/js/vendor/morris/css/morris.css">
<link rel="stylesheet"
	href="/YZY/assets/js/vendor/tabdrop/css/tabdrop.css">
<link rel="stylesheet"
	href="/YZY/assets/js/vendor/summernote/css/summernote.css">
<link rel="stylesheet"
	href="/YZY/assets/js/vendor/summernote/css/summernote-bs3.css">
<link rel="stylesheet"
	href="/YZY/assets/js/vendor/chosen/css/chosen.min.css">
<link rel="stylesheet"
	href="/YZY/assets/js/vendor/chosen/css/chosen-bootstrap.css">

<link href="/YZY/assets/css/minimal.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body class="bg-1">



	<!-- Preloader -->
	<div class="mask">
		<div id="loader"></div>
	</div>
	<!--/Preloader -->

	<!-- Wrap all page content here -->
	<div id="wrap">




		<!-- Make page fluid -->
		<div class="row">





			<!-- Fixed navbar -->
			<div
				class="navbar navbar-default navbar-fixed-top navbar-transparent-black mm-fixed-top"
				role="navigation" id="navbar">



				<!-- Branding -->
				<div class="navbar-header col-md-2">
					<a class="navbar-brand" href="IndexServlet"> <strong>EMS</strong>
					</a>
					<div class="sidebar-collapse">
						<a href="#"> <i class="fa fa-bars"></i>
						</a>
					</div>
				</div>
				<!-- Branding end -->
				<div class="copyrights">
					Collect from <a href="http://www.cssmoban.com/" title="网站模板">网站模板</a>
				</div>


				<!-- .nav-collapse -->
				<div class="navbar-collapse">



					<jsp:include page="/top.jsp"></jsp:include>

					<jsp:include page="userLeft.jsp"></jsp:include>





				</div>
				<!--/.nav-collapse -->





			</div>
			<!-- Fixed navbar end -->






			<!-- Page content -->
			<div id="content" class="col-md-12">









				<!-- page header -->
				<div class="pageheader">


					<h2>
						<i class="fa fa-tachometer"></i> 企业消息管理平台
					</h2>





				</div>
				<!-- /page header -->



				<form class="form-horizontal" id="frmModifyPassword" 
			  action="ModifyPasswordServlet" method="post">







					<!-- tile -->
					<section class="tile color transparent-black">



						<!-- tile header -->
						<div class="tile-header">


							<h1>
								<strong>密码修改</strong>
							</h1>

							<div class="controls">
								<a href="#" class="refresh"><i class="fa fa-refresh"></i></a> <a
									href="#" class="remove"><i class="fa fa-times"></i></a>
							</div>
						</div>
						<!-- /tile header -->

						<!-- tile body -->
						<div class="tile-body">

							<div class="form-horizontal" role="form">

								<div class="form-group">
									<label for="input01" class="col-sm-4 control-label">登录名</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="txtLoginName"
											name="txtLoginName" placeholder="登录名"
											value="${sessionScope.Login.loginName}" readonly="readonly">
										<span class="label label-danger" id="spanDepartmentName"></span>
									</div>
								</div>

								<div class="form-group">
									<label for="input02" class="col-sm-4 control-label">原密码</label>
									<div class="col-sm-8">
										<input type="password" class="form-control" id="txtOldPassword" name="txtOldPassword"
														placeholder="原密码">
													<span class="label label-danger" id="spanOldPassword"></span>
									</div>
								</div>

								<div class="form-group">
									<label for="input03" class="col-sm-4 control-label">新密码</label>
									<div class="col-sm-8">
										<input type="password" class="form-control" id="txtNewPassword" name="txtNewPassword"
														placeholder="新密码">
												  <span class="label label-danger" id="spanNewPassword"></span>
									</div>
								</div>
								<div class="form-group">
									<label for="input03" class="col-sm-4 control-label">确认密码</label>
									<div class="col-sm-8">
										<input type="password" class="form-control" id="txtVPassword" name="txtVPassword"
														placeholder="确认密码">
													<span class="label label-danger" id="spanVPassword"></span>
									</div>
								</div>
								
								<div class="form-group form-footer">
									<div class="col-sm-offset-4 col-sm-8">

										<button type="button" class="btn btn-primary" id="btnModify">修改</button>
										<button type="reset" class="btn btn-default"><a href="IndexServlet">取消</a></button>

									</div>
								</div>

							</div>


						</div>
						<!-- /tile body -->




					</section>
					<!-- /tile -->











				</form>





				<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
				<script src="/YZY/assets/js/jquery.js"></script>
				<!-- Include all compiled plugins (below), or include individual files as needed -->
				<script src="/YZY/assets/js/vendor/bootstrap/bootstrap.min.js"></script>
				<script type="text/javascript"
					src="/YZY/assets/js/vendor/mmenu/js/jquery.mmenu.min.js"></script>
				<script type="text/javascript"
					src="/YZY/assets/js/vendor/sparkline/jquery.sparkline.min.js"></script>
				<script type="text/javascript"
					src="/YZY/assets/js/vendor/nicescroll/jquery.nicescroll.min.js"></script>
				<script type="text/javascript"
					src="/YZY/assets/js/vendor/animate-numbers/jquery.animateNumbers.js"></script>
				<script type="text/javascript"
					src="/YZY/assets/js/vendor/videobackground/jquery.videobackground.js"></script>
				<script type="text/javascript"
					src="/YZY/assets/js/vendor/blockui/jquery.blockUI.js"></script>

				<script src="/YZY/assets/js/vendor/flot/jquery.flot.min.js"></script>
				<script src="/YZY/assets/js/vendor/flot/jquery.flot.time.min.js"></script>
				<script
					src="/YZY/assets/js/vendor/flot/jquery.flot.selection.min.js"></script>
				<script src="/YZY/assets/js/vendor/flot/jquery.flot.animator.min.js"></script>
				<script src="/YZY/assets/js/vendor/flot/jquery.flot.orderBars.js"></script>
				<script
					src="/YZY/assets/js/vendor/easypiechart/jquery.easypiechart.min.js"></script>

				<script src="/YZY/assets/js/vendor/rickshaw/raphael-min.js"></script>
				<script src="/YZY/assets/js/vendor/rickshaw/d3.v2.js"></script>
				<script src="/YZY/assets/js/vendor/rickshaw/rickshaw.min.js"></script>

				<script src="/YZY/assets/js/vendor/morris/morris.min.js"></script>

				<script src="/YZY/assets/js/vendor/tabdrop/bootstrap-tabdrop.min.js"></script>

				<script src="/YZY/assets/js/vendor/summernote/summernote.min.js"></script>

				<script src="/YZY/assets/js/vendor/chosen/chosen.jquery.min.js"></script>

				<script src="/YZY/assets/js/minimal.min.js"></script>

				<script>
					$(function() {

						// Initialize card flip
						$('.card.hover').hover(function() {
							$(this).addClass('flip');
						}, function() {
							$(this).removeClass('flip');
						});

						// Initialize flot chart
						var d1 = [ [ 1, 715 ], [ 2, 985 ], [ 3, 1525 ],
								[ 4, 1254 ], [ 5, 1861 ], [ 6, 2621 ],
								[ 7, 1987 ], [ 8, 2136 ], [ 9, 2364 ],
								[ 10, 2851 ], [ 11, 1546 ], [ 12, 2541 ] ];
						var d2 = [ [ 1, 463 ], [ 2, 578 ], [ 3, 327 ],
								[ 4, 984 ], [ 5, 1268 ], [ 6, 1684 ],
								[ 7, 1425 ], [ 8, 1233 ], [ 9, 1354 ],
								[ 10, 1200 ], [ 11, 1260 ], [ 12, 1320 ] ];
						var months = [ "January", "February", "March", "April",
								"May", "Juny", "July", "August", "September",
								"October", "November", "December" ];

						// flot chart generate
						var plot = $.plotAnimator($("#statistics-chart"), [ {
							label : 'Sales',
							data : d1,
							lines : {
								lineWidth : 3
							},
							shadowSize : 0,
							color : '#ffffff'
						}, {
							label : "Visits",
							data : d2,
							animator : {
								steps : 99,
								duration : 500,
								start : 200,
								direction : "right"
							},
							lines : {
								fill : .15,
								lineWidth : 0
							},
							color : [ '#ffffff' ]
						}, {
							label : 'Sales',
							data : d1,
							points : {
								show : true,
								fill : true,
								radius : 6,
								fillColor : "rgba(0,0,0,.5)",
								lineWidth : 2
							},
							color : '#fff',
							shadowSize : 0
						}, {
							label : "Visits",
							data : d2,
							points : {
								show : true,
								fill : true,
								radius : 6,
								fillColor : "rgba(255,255,255,.2)",
								lineWidth : 2
							},
							color : '#fff',
							shadowSize : 0
						} ], {

							xaxis : {

								tickLength : 0,
								tickDecimals : 0,
								min : 1,
								ticks : [ [ 1, "JAN" ], [ 2, "FEB" ],
										[ 3, "MAR" ], [ 4, "APR" ],
										[ 5, "MAY" ], [ 6, "JUN" ],
										[ 7, "JUL" ], [ 8, "AUG" ],
										[ 9, "SEP" ], [ 10, "OCT" ],
										[ 11, "NOV" ], [ 12, "DEC" ] ],

								font : {
									lineHeight : 24,
									weight : "300",
									color : "#ffffff",
									size : 14
								}
							},

							yaxis : {
								ticks : 4,
								tickDecimals : 0,
								tickColor : "rgba(255,255,255,.3)",

								font : {
									lineHeight : 13,
									weight : "300",
									color : "#ffffff"
								}
							},

							grid : {
								borderWidth : {
									top : 0,
									right : 0,
									bottom : 1,
									left : 1
								},
								borderColor : 'rgba(255,255,255,.3)',
								margin : 0,
								minBorderMargin : 0,
								labelMargin : 20,
								hoverable : true,
								clickable : true,
								mouseActiveRadius : 6
							},

							legend : {
								show : false
							}
						});

						$(window).resize(function() {
							// redraw the graph in the correctly sized div
							plot.resize();
							plot.setupGrid();
							plot.draw();
						});

						$('#mmenu').on("opened.mm", function() {
							// redraw the graph in the correctly sized div
							plot.resize();
							plot.setupGrid();
							plot.draw();
						});

						$('#mmenu').on("closed.mm", function() {
							// redraw the graph in the correctly sized div
							plot.resize();
							plot.setupGrid();
							plot.draw();
						});

						// tooltips showing
						$("#statistics-chart")
								.bind(
										"plothover",
										function(event, pos, item) {
											if (item) {
												var x = item.datapoint[0], y = item.datapoint[1];

												$("#tooltip")
														.html(
																'<h1 style="color: #418bca">'
																		+ months[x - 1]
																		+ '</h1>'
																		+ '<strong>'
																		+ y
																		+ '</strong>'
																		+ ' '
																		+ item.series.label)
														.css(
																{
																	top : item.pageY - 30,
																	left : item.pageX + 5
																}).fadeIn(200);
											} else {
												$("#tooltip").hide();
											}
										});

						//tooltips options
						$("<div id='tooltip'></div>").css({
							position : "absolute",
							//display: "none",
							padding : "10px 20px",
							"background-color" : "#ffffff",
							"z-index" : "99999"
						}).appendTo("body");

						//generate actual pie charts
						$('.pie-chart').easyPieChart();

						//server load rickshaw chart
						var graph;

						var seriesData = [ [], [] ];
						var random = new Rickshaw.Fixtures.RandomData(50);

						for (var i = 0; i < 50; i++) {
							random.addData(seriesData);
						}

						graph = new Rickshaw.Graph({
							element : document
									.querySelector("#serverload-chart"),
							height : 150,
							renderer : 'area',
							series : [ {
								data : seriesData[0],
								color : '#6e6e6e',
								name : 'File Server'
							}, {
								data : seriesData[1],
								color : '#fff',
								name : 'Mail Server'
							} ]
						});

						var hoverDetail = new Rickshaw.Graph.HoverDetail({
							graph : graph,
						});

						setInterval(function() {
							random.removeData(seriesData);
							random.addData(seriesData);
							graph.update();

						}, 1000);

						// Morris donut chart
						Morris.Donut({
							element : 'browser-usage',
							data : [ {
								label : "Chrome",
								value : 25
							}, {
								label : "Safari",
								value : 20
							}, {
								label : "Firefox",
								value : 15
							}, {
								label : "Opera",
								value : 5
							}, {
								label : "Internet Explorer",
								value : 10
							}, {
								label : "Other",
								value : 25
							} ],
							colors : [ '#00a3d8', '#2fbbe8', '#72cae7',
									'#d9544f', '#ffc100', '#1693A5' ]
						});

						$('#browser-usage').find("path[stroke='#ffffff']")
								.attr('stroke', 'rgba(0,0,0,0)');

						//sparkline charts
						$('#projectbar1').sparkline('html', {
							type : 'bar',
							barColor : '#22beef',
							barWidth : 4,
							height : 20
						});
						$('#projectbar2').sparkline('html', {
							type : 'bar',
							barColor : '#cd97eb',
							barWidth : 4,
							height : 20
						});
						$('#projectbar3').sparkline('html', {
							type : 'bar',
							barColor : '#a2d200',
							barWidth : 4,
							height : 20
						});
						$('#projectbar4').sparkline('html', {
							type : 'bar',
							barColor : '#ffc100',
							barWidth : 4,
							height : 20
						});
						$('#projectbar5').sparkline('html', {
							type : 'bar',
							barColor : '#ff4a43',
							barWidth : 4,
							height : 20
						});
						$('#projectbar6').sparkline('html', {
							type : 'bar',
							barColor : '#a2d200',
							barWidth : 4,
							height : 20
						});

						// sortable table
						$('.table.table-sortable th.sortable')
								.click(
										function() {
											var o = $(this)
													.hasClass('sort-asc') ? 'sort-desc'
													: 'sort-asc';
											$('th.sortable').removeClass(
													'sort-asc').removeClass(
													'sort-desc');
											$(this).addClass(o);
										});

						//todo's
						$('#todolist li label').click(function() {
							$(this).toggleClass('done');
						});

						// Initialize tabDrop
						$('.tabdrop').tabdrop({
							text : '<i class="fa fa-th-list"></i>'
						});

						//load wysiwyg editor
						$('#quick-message-content')
								.summernote(
										{
											toolbar : [
													//['style', ['style']], // no style button
													[
															'style',
															[
																	'bold',
																	'italic',
																	'underline',
																	'clear' ] ],
													[ 'fontsize',
															[ 'fontsize' ] ],
													[ 'color', [ 'color' ] ],
													[
															'para',
															[ 'ul', 'ol',
																	'paragraph' ] ],
													[ 'height', [ 'height' ] ],
											//['insert', ['picture', 'link']], // no insert buttons
											//['table', ['table']], // no table button
											//['help', ['help']] //no help button
											],
											height : 143
										//set editable area's height
										});

						//multiselect input
						$(".chosen-select").chosen({
							disable_search_threshold : 10
						});

					})
				</script>
	<script type="text/javascript">
	${requestScope.Message}//el表达式语言
	
		$("#btnModify").click( function () {
			var flag = true;
			if ($("#txtOldPassword").val().length == 0) {
				$("#spanOldPassword").text("原密码不能为空!");
				flag = false;
			} 
			else
				$("#spanOldPassword").text("");
			
			if ($("#txtNewPassword").val().length == 0) {
				$("#spanNewPassword").text("新密码不能为空!");
				flag = false;
			} 
			else
				$("#spanNewPassword").text("");	
				
			if ($("#txtNewPassword").val() != $("#txtVPassword").val()) {
				$("#spanVPassword").text("验证密码不一致!");
				flag = false;
			} 
			else
				$("#spanVPassword").text("");	
				
			if (flag)
				$("#frmModifyPassword").submit();
		});
	</script>
</body>
</html>