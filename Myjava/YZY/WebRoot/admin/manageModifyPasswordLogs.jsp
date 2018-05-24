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

					<jsp:include page="adminLeft.jsp"></jsp:include>





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

				<form class="form-horizontal" id="frmModifyPasswordLogs"
					action="ManageModifyPasswordLogsServlet" method="post">
					<input type="hidden" name="hidOperationType" id="hidOperationType" />
					<input type="hidden" name="hidModifyPasswordLogId" id="hidModifyPasswordLogId"
						value="${requestScope.ModifyModifyPasswordLog.modifyPasswordLogId}" /> <input
						type="hidden" name="hidFieldName" id="hidFieldName"
						value="${requestScope.fieldName}" /> <input type="hidden"
						name="hidSortString" id="hidSortString"
						value="${requestScope.sortString}" /> <input type="hidden"
						name="hidPageIndex" id="hidPageIndex"
						value="${requestScope.pageIndex}" /> <input type="hidden"
						name="hidPageSize" id="hidPageSize"
						value="${requestScope.pageSize}" />




					<!-- tile -->
					<section class="tile color transparent-black">



						<!-- tile header -->
						<div class="tile-header">
							<h1>
								<strong>密码修改日志信息查询</strong>
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
									<label for="input01" class="col-sm-4 control-label">修改时间</label>
									<div class="col-sm-4">
										<input type="text" class="form-control"
											id="txtSearchModifyDateTimeBegin"
											name="txtSearchModifyDateTimeBegin" placeholder="开始时间"
											value="${searcher.modifyDateTimeBegin}">
									</div>
									<div class="col-sm-4">
										<input type="text" class="form-control"
											id="txtSearchModifyDateTimeEnd"
											name="txtSearchModifyDateTimeEnd" placeholder="结束时间"
											value="${searcher.modifyDateTimeEnd}">
									</div>
								</div>

								<div class="form-group">
									<label for="input02" class="col-sm-4 control-label">登录IP</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="txtSearchModifyIp"
											name="txtSearchModifyIp" placeholder="登录IP"
											value="${searcher.modifyIp }">
									</div>
								</div>

								<div class="form-group">
									<label for="input03" class="col-sm-4 control-label">登录名</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="txtSearchLoginId"
											name="txtSearchLoginId" placeholder="登录名"
											value="${searcher.login.loginName }">
									</div>
								</div>

								<div class="form-group form-footer">
									<div class="col-sm-offset-4 col-sm-8">
										<button type="submit" class="btn btn-primary" id="btnSearch">查询</button>
										<button type="reset" class="btn btn-default"
											id="btnCancelSearch">取消</button>
									</div>
								</div>

							</div>
							>

						</div>
						<!-- /tile body -->




					</section>
					<!-- /tile -->
				</form>
				<!-- tile -->
				<section class="tile color transparent-black">



					<!-- tile header -->
					<div class="tile-header">
						<h1>
							<strong>密码修改日志信息列表</strong>
						</h1>

						<div class="controls">
							<a href="/YZY/file/departments.csv">导出数据</a> <a
								href="#" class="remove"><i class="fa fa-times"></i></a>
						</div>
					</div>
					<!-- /tile header -->

					<!-- tile widget -->
					<div class="tile-widget bg-transparent-black-2">
						<div class="row"></div>
					</div>
					<!-- /tile widget -->



					<!-- tile body -->
					<div class="tile-body nopadding">

						<table class="table table-bordered table-sortable">
							<thead>
								<tr>

									<th class="sortable sort-alpha"><a
										href="javascript:sorting('ModifyPasswordLogId');">密码修改日志编号</a></th>
									<th class="sortable sort-alpha"><a
										href="javascript:sorting('ModifyDateTime');">修改时间</a></th>
									<th class="sortable sort-alpha"><a
										href="javascript:sorting('ModifyIp');">登录IP</a></th>
									

								</tr>
							</thead>
							<tbody>
								<c:forEach items="${modifyPasswordLogs}" var="modifyPasswordLog"
									begin="${begin - 1 }" end="${end - 1 }">
									<!-- jstl显示密码修改日志表 -->
									<tr>
										<td>${modifyPasswordLog.modifyPasswordLogId}</td>
										<td>${modifyPasswordLog.modifyDateTime}</td>
										<td>${modifyPasswordLog.modifyIp}</td>
										

									</tr>
								</c:forEach>
							</tbody>
						</table>

					</div>
					<!-- /tile body -->


					<!-- tile footer -->
					<div
						class="tile-footer bg-transparent-black-2 rounded-bottom-corners">
						<div class="row">


							<label class="control-label col-lg-6 "
								style="white-space: nowrap; ">共${rowCount }条记录
								第${pageIndex }页 共${pageCount }页 <input type="radio"
								name="radPageSize" value="2" onclick="resize(this)"
								<c:if test="${pageSize == 2}">checked </c:if> /> 2条/页<input
								type="radio" name="radPageSize" value="3" onclick="resize(this)"
								<c:if test="${pageSize == 3}">checked </c:if> /> 3条/页<input
								type="radio" name="radPageSize" value="5" onclick="resize(this)"
								<c:if test="${pageSize == 5}">checked </c:if> /> 5条/页<input
								type="text" id="txtPageSize" placeholder="目标页码"
								value="${requestScope.pageIndex}">
								<button class="btn btn-xs btn-warning" type="button"
									id="btnPaging">翻页</button>
							</label>


							<div class="col-sm-4 text-right sm-center">

								<ul class="pagination pagination-xs nomargin pagination-custom">
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
							</div>

						</div>
					</div>
					<!-- /tile footer -->




				</section>
				<!-- /tile -->

				<!-- content main container -->
				<div class="main">







					<!-- row -->
					<div class="row">


						<!-- col 8 -->
						<div class="col-lg-8 col-md-12"></div>
						<!-- /col 8 -->



						<!-- col 4 -->
						<div class="col-lg-4 col-md-12 col-sm-12 col-xs-12"></div>
						<!-- /col 4 -->


					</div>
					<!-- /row -->


					<!-- row -->
					<div class="row">




						<!-- col 6 -->
						<div class="col-md-6"></div>
						<!-- /col 6 -->




						<!-- col 6 -->
						<div class="col-md-6"></div>
						<!-- /col 6 -->




					</div>
					<!-- /row -->



				</div>
				<!-- /content container -->






			</div>
			<!-- Page content end -->








		</div>
		<!-- Make page fluid-->




	</div>
	<!-- Wrap all page content end -->



	<section class="videocontent" id="video"></section>






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
	<script src="/YZY/assets/js/vendor/flot/jquery.flot.selection.min.js"></script>
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
			var d1 = [ [ 1, 715 ], [ 2, 985 ], [ 3, 1525 ], [ 4, 1254 ],
					[ 5, 1861 ], [ 6, 2621 ], [ 7, 1987 ], [ 8, 2136 ],
					[ 9, 2364 ], [ 10, 2851 ], [ 11, 1546 ], [ 12, 2541 ] ];
			var d2 = [ [ 1, 463 ], [ 2, 578 ], [ 3, 327 ], [ 4, 984 ],
					[ 5, 1268 ], [ 6, 1684 ], [ 7, 1425 ], [ 8, 1233 ],
					[ 9, 1354 ], [ 10, 1200 ], [ 11, 1260 ], [ 12, 1320 ] ];
			var months = [ "January", "February", "March", "April", "May",
					"Juny", "July", "August", "September", "October",
					"November", "December" ];

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
					ticks : [ [ 1, "JAN" ], [ 2, "FEB" ], [ 3, "MAR" ],
							[ 4, "APR" ], [ 5, "MAY" ], [ 6, "JUN" ],
							[ 7, "JUL" ], [ 8, "AUG" ], [ 9, "SEP" ],
							[ 10, "OCT" ], [ 11, "NOV" ], [ 12, "DEC" ] ],

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
			$("#statistics-chart").bind(
					"plothover",
					function(event, pos, item) {
						if (item) {
							var x = item.datapoint[0], y = item.datapoint[1];

							$("#tooltip").html(
									'<h1 style="color: #418bca">'
											+ months[x - 1] + '</h1>'
											+ '<strong>' + y + '</strong>'
											+ ' ' + item.series.label).css({
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
				element : document.querySelector("#serverload-chart"),
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
				colors : [ '#00a3d8', '#2fbbe8', '#72cae7', '#d9544f',
						'#ffc100', '#1693A5' ]
			});

			$('#browser-usage').find("path[stroke='#ffffff']").attr('stroke',
					'rgba(0,0,0,0)');

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
			$('.table.table-sortable th.sortable').click(
					function() {
						var o = $(this).hasClass('sort-asc') ? 'sort-desc'
								: 'sort-asc';
						$('th.sortable').removeClass('sort-asc').removeClass(
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
			$('#quick-message-content').summernote(
					{
						toolbar : [
								//['style', ['style']], // no style button
								[
										'style',
										[ 'bold', 'italic', 'underline',
												'clear' ] ],
								[ 'fontsize', [ 'fontsize' ] ],
								[ 'color', [ 'color' ] ],
								[ 'para', [ 'ul', 'ol', 'paragraph' ] ],
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
		/*	$("#frmModifyPasswordLogs").submit(function() {
				var flag = true;
				if ($("#txtModifyDateTime").val().length == 0) {
					$("#spanModifyDateTime").text("修改时间不能为空!");
					flag = false;
				} else
					$("#spanModifyDateTime").text("");
				return flag;
			}); */

		${requestScope.Message}//el表达式语言

		$("#btnAdd").click(function() {
			var flag = true;
			if ($("#txtModifyDateTime").val().length == 0) {
				$("#spanModifyDateTime").text("修改时间不能为空!");
				flag = false;
			} else
				$("#spanModifyDateTime").text("");

			$("#hidOperationType").val("add");//设置操作类型
			if (flag)
				$("#frmModifyPasswordLogs").submit();
		});

		$("#btnSave").click(function() {
			var flag = true;
			if ($("#txtModifyDateTime").val().length == 0) {
				$("#spanModifyDateTime").text("修改时间不能为空!");
				flag = false;
			} else
				$("#spanModifyDateTime").text("");

			$("#hidOperationType").val("save");//设置操作类型
			if (flag)
				$("#frmModifyPasswordLogs").submit();
		});
		$("#btnCancelSave").click(function() {
			$("#spanModifyDateTime").text("");
			$("#hidOperationType").val("cancelSave");//设置操作类型
			$("#frmModifyPasswordLogs").submit();
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
					if ($("#txtSearchModifyDateTime").val().length == 0
							&& $("#txtSearchModifyIp").val().length == 0
							&& $("#txtSearchLoginId").val().length == 0) {
						$("#spanSearchModifyDateTime").text("查询条件不能为空！");
						flag = false;
					} else
						$("#spanSearchModifyDateTime").text("");
					$("#hidOperationType").val("search");

					if (flag)
						$("#frmModifyPasswordLogs").submit();
				});
		$("#btnCancelSearch").click(function() {
			$("#txtSearchModifyDateTime").val("");
			$("#txtSearchModifyIp").val("");
			$("#txtSearchLoginId").val("");

			$("#hidOperationType").val("cancelSearch");
			$("#frmModifyPasswordLogs").submit();
		});

		function removeModifyPasswordLog(modifyPasswordLogId, modifyDateTime) {
			if (confirm("是否删除？" + modifyDateTime + "密码修改日志信息？")) {
				$("#hidModifyPasswordLogId").val(modifyPasswordLogId);//传递要删除的主键值
				$("#hidOperationType").val("remove");//设置操作类型
				$("#frmModifyPasswordLogs").submit();
			}
		}

		function sorting(fieldName) {
			$("#hidFieldName").val(fieldName);//传递要排序的列名
			$("#hidOperationType").val("sorting");//设置操作类型
			$("#frmModifyPasswordLogs").submit();
		}

		function modifyModifyPasswordLog(modifyPasswordLogId) {
			$("#hidModifyPasswordLogId").val(modifyPasswordLogId);//传递要修改的主键值
			$("#hidOperationType").val("modify");//设置操作类型
			$("#frmModifyPasswordLogs").submit();
		}

		function paging(pageIndex) {
			$("#hidPageIndex").val(pageIndex); //传递目标页码
			$("#hidOperationType").val("paging");//设置操作类型
			$("#frmModifyPasswordLogs").submit();
		}

		function resize(rad) {
			$("#hidPageSize").val(rad.value); //传递尺寸
			$("#hidOperationType").val("paging");//设置操作类型
			$("#frmModifyPasswordLogs").submit();
		}
	</script>
</body>
</html>