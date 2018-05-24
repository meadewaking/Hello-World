<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!
DOCTYPE html>
<html>

<head>
<title>发布部门信息</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8" />

<link rel="icon" type="image/ico"
	href="http://tattek.com/minimal//YZY/assets/images/favicon.ico" />
<!-- Bootstrap -->
<link href="/YZY/assets/css/vendor/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link
	href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"
	rel="stylesheet">
<link rel="stylesheet" href="/YZY/assets/css/vendor/animate/animate.css">
<link type="text/css" rel="stylesheet" media="all"
	href="/YZY/assets/js/vendor/mmenu/css/jquery.mmenu.all.css" />
<link rel="stylesheet"
	href="/YZY/assets/js/vendor/videobackground/css/jquery.videobackground.css">
<link rel="stylesheet"
	href="/YZY/assets/css/vendor/bootstrap-checkbox.css">

<link rel="stylesheet"
	href="/YZY/assets/js/vendor/summernote/css/summernote.css">
<link rel="stylesheet"
	href="/YZY/assets/js/vendor/summernote/css/summernote-bs3.css">
<link rel="stylesheet"
	href="/YZY/assets/js/vendor/chosen/css/chosen.min.css">
<link rel="stylesheet"
	href="/YZY/assets/js/vendor/chosen/css/chosen-bootstrap.css">
<link rel="stylesheet"
	href="/YZY/assets/js/vendor/datepicker/css/bootstrap-datetimepicker.css">
<link rel="stylesheet"
	href="/YZY/assets/js/vendor/colorpicker/css/bootstrap-colorpicker.css">
<link rel="stylesheet"
	href="/YZY/assets/js/vendor/colorpalette/bootstrap-colorpalette.css">

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

			<!-- Preloader -->
			<div class="mask">
				<div id="loader"></div>
			</div>
			<!--/Preloader -->

			<!-- Wrap all page content here -->
			<div id="wrap">

				<!-- Make page fluid -->
				<div class="row">



					<!-- Page content -->
					<div id="content" class="col-md-12">

						<!-- page header -->
						<div class="pageheader">


							<h2>
								<i class="fa fa-tachometer"></i> 企业消息管理平台
							</h2>





						</div>
						<!-- /page header -->

						<!-- content main container -->
						<div class="main">

							<!-- row -->
							<div class="row">

								<!-- col 12 -->
								<div class="col-md-8">

									<form class="form-horizontal" id="frmPublishDepartmentMessage"
										action="PublishDepartmentMessageServlet" method="post">
										<!-- tile -->
										<section class="tile color transparent-black">

											<!-- tile header -->
											<div class="tile-header">
												<h1>
													<strong>发布部门消息</strong>
												</h1>
												<div class="controls">
													<a href="#" class="refresh"><i class="fa fa-refresh"></i></a>
													<a href="#" class="remove"><i class="fa fa-times"></i></a>
												</div>
											</div>
											<!-- /tile header -->

											<!-- tile body -->


											<div class="tile-body">
												<li class="dropdown divided ">
													<ul class="dropdown-toggle">
														<form class="form-horizontal" role="form">

															<div class="form-group">
																<label for="input01" class="col-sm-4 control-label">标题</label>
																<div class="col-sm-8">
																	<input type="text" class="form-control" id="txtTitle"
																		name="txtTitle" placeholder="标题"> <span
																		class="label label-danger" id="spanTitle"></span>
																</div>
															</div>
															<div class="form-group transparent-editor">
																<label class="col-sm-4 control-label">内容</label>
																<div class="col-sm-8">
																	<textarea class="form-control" id="txtContent"
																		name="txtContent" placeholder="内容"></textarea>
																	<ckeditor:replace replace="txtContent"
																		basePath="/YZY/ckeditor"></ckeditor:replace>
																	<span class="label label-danger" id="spanContent"></span>
																</div>
															</div>

															<div class="form-group form-footer">
																<div class="col-sm-offset-4 col-sm-8">
																	<button type="submit" class="btn btn-primary">发送</button>
																	<button type="reset" class="btn btn-default">取消</button>
																</div>
															</div>

														</form>
													</ul>
												</li>
											</div>


											<!-- /tile body -->

										</section>
										<!-- /tile -->
									</form>
								</div>
								<!-- /col 6 -->

								<!-- col 12 -->
								<div class="col-md-6"></div>
								<!-- /col 6 -->

							</div>
							<!-- /row -->

							<!-- row -->
							<div class="row">

								<!-- col 12 -->
								<div class="col-md-12"></div>
								<!-- /col 12 -->

							</div>
							<!-- /row -->

						</div>
						<!-- /content container -->

					</div>
					<!-- Page content end -->

					<div id="mmenu" class="right-panel">
						<!-- Nav tabs -->
						<ul class="nav nav-tabs nav-justified">
							<li class="active"><a href="#mmenu-users" data-toggle="tab"><i
									class="fa fa-users"></i></a></li>
							<li class=""><a href="#mmenu-history" data-toggle="tab"><i
									class="fa fa-clock-o"></i></a></li>
							<li class=""><a href="#mmenu-friends" data-toggle="tab"><i
									class="fa fa-heart"></i></a></li>
							<li class=""><a href="#mmenu-settings" data-toggle="tab"><i
									class="fa fa-gear"></i></a></li>
						</ul>

						<!-- Tab panes -->
						<div class="tab-content">
							<div class="tab-pane active" id="mmenu-users">
								<h5>
									<strong>Online</strong> Users
								</h5>

								<ul class="users-list">

									<li class="online">
										<div class="media">
											<a class="pull-left profile-photo" href="#"> <img
												class="media-object" src="/YZY/assets/images/ici-avatar.jpg"
												alt>
											</a>
											<div class="media-body">
												<h6 class="media-heading">
													Ing. Imrich <strong>Kamarel</strong>
												</h6>
												<small><i class="fa fa-map-marker"></i> Ulaanbaatar,
													Mongolia</small> <span class="badge badge-outline status"></span>
											</div>
										</div>
									</li>

									<li class="online">
										<div class="media">

											<a class="pull-left profile-photo" href="#"> <img
												class="media-object"
												src="/YZY/assets/images/arnold-avatar.jpg" alt>
											</a> <span class="badge badge-red unread">3</span>

											<div class="media-body">
												<h6 class="media-heading">
													Arnold <strong>Karlsberg</strong>
												</h6>
												<small><i class="fa fa-map-marker"></i> Bratislava,
													Slovakia</small> <span class="badge badge-outline status"></span>
											</div>

										</div>
									</li>

									<li class="online">
										<div class="media">
											<a class="pull-left profile-photo" href="#"> <img
												class="media-object"
												src="/YZY/assets/images/peter-avatar.jpg" alt>

											</a>
											<div class="media-body">
												<h6 class="media-heading">
													Peter <strong>Kay</strong>
												</h6>
												<small><i class="fa fa-map-marker"></i> Kosice,
													Slovakia</small> <span class="badge badge-outline status"></span>
											</div>
										</div>
									</li>

									<li class="online">
										<div class="media">
											<a class="pull-left profile-photo" href="#"> <img
												class="media-object"
												src="/YZY/assets/images/george-avatar.jpg" alt>
											</a>
											<div class="media-body">
												<h6 class="media-heading">
													George <strong>McCain</strong>
												</h6>
												<small><i class="fa fa-map-marker"></i> Prague,
													Czech Republic</small> <span class="badge badge-outline status"></span>
											</div>
										</div>
									</li>

									<li class="busy">
										<div class="media">
											<a class="pull-left profile-photo" href="#"> <img
												class="media-object"
												src="/YZY/assets/images/random-avatar1.jpg" alt>
											</a>
											<div class="media-body">
												<h6 class="media-heading">
													Lucius <strong>Cashmere</strong>
												</h6>
												<small><i class="fa fa-map-marker"></i> Wien,
													Austria</small> <span class="badge badge-outline status"></span>
											</div>
										</div>
									</li>

									<li class="busy">
										<div class="media">
											<a class="pull-left profile-photo" href="#"> <img
												class="media-object"
												src="/YZY/assets/images/random-avatar2.jpg" alt>
											</a>
											<div class="media-body">
												<h6 class="media-heading">
													Jesse <strong>Phoenix</strong>
												</h6>
												<small><i class="fa fa-map-marker"></i> Berlin,
													Germany</small> <span class="badge badge-outline status"></span>
											</div>
										</div>
									</li>

								</ul>

								<h5>
									<strong>Offline</strong> Users
								</h5>

								<ul class="users-list">

									<li class="offline">
										<div class="media">
											<a class="pull-left profile-photo" href="#"> <img
												class="media-object"
												src="/YZY/assets/images/random-avatar4.jpg" alt>
											</a>
											<div class="media-body">
												<h6 class="media-heading">
													Dell <strong>MacApple</strong>
												</h6>
												<small><i class="fa fa-map-marker"></i> Paris,
													France</small> <span class="badge badge-outline status"></span>
											</div>
										</div>
									</li>

									<li class="offline">
										<div class="media">

											<a class="pull-left profile-photo" href="#"> <img
												class="media-object"
												src="/YZY/assets/images/random-avatar5.jpg" alt>
											</a>

											<div class="media-body">
												<h6 class="media-heading">
													Roger <strong>Flopple</strong>
												</h6>
												<small><i class="fa fa-map-marker"></i> Rome, Italia</small>
												<span class="badge badge-outline status"></span>
											</div>

										</div>
									</li>

									<li class="offline">
										<div class="media">
											<a class="pull-left profile-photo" href="#"> <img
												class="media-object"
												src="/YZY/assets/images/random-avatar6.jpg" alt>

											</a>
											<div class="media-body">
												<h6 class="media-heading">
													Nico <strong>Vulture</strong>
												</h6>
												<small><i class="fa fa-map-marker"></i> Kyjev,
													Ukraine</small> <span class="badge badge-outline status"></span>
											</div>
										</div>
									</li>

									<li class="offline">
										<div class="media">
											<a class="pull-left profile-photo" href="#"> <img
												class="media-object"
												src="/YZY/assets/images/random-avatar7.jpg" alt>
											</a>
											<div class="media-body">
												<h6 class="media-heading">
													Bobby <strong>Socks</strong>
												</h6>
												<small><i class="fa fa-map-marker"></i> Moscow,
													Russia</small> <span class="badge badge-outline status"></span>
											</div>
										</div>
									</li>

									<li class="offline">
										<div class="media">
											<a class="pull-left profile-photo" href="#"> <img
												class="media-object"
												src="/YZY/assets/images/random-avatar8.jpg" alt>
											</a>
											<div class="media-body">
												<h6 class="media-heading">
													Anna <strong>Opichia</strong>
												</h6>
												<small><i class="fa fa-map-marker"></i> Budapest,
													Hungary</small> <span class="badge badge-outline status"></span>
											</div>
										</div>
									</li>

								</ul>

							</div>

							<div class="tab-pane" id="mmenu-history">
								<h5>
									<strong>Chat</strong> History
								</h5>

								<ul class="history-list">

									<li class="online">
										<div class="media">
											<a class="pull-left profile-photo" href="#"> <img
												class="media-object" src="/YZY/assets/images/ici-avatar.jpg"
												alt>
											</a>
											<div class="media-body">
												<h6 class="media-heading">
													Ing. Imrich <strong>Kamarel</strong>
												</h6>
												<small>Lorem ipsum dolor sit amet, consectetur
													adipisicing elit, sed do eiusmod tempor</small> <span
													class="badge badge-outline status"></span>
											</div>
										</div>
									</li>

									<li class="busy">
										<div class="media">

											<a class="pull-left profile-photo" href="#"> <img
												class="media-object"
												src="/YZY/assets/images/arnold-avatar.jpg" alt>
											</a> <span class="badge badge-red unread">3</span>

											<div class="media-body">
												<h6 class="media-heading">
													Arnold <strong>Karlsberg</strong>
												</h6>
												<small>Duis aute irure dolor in reprehenderit in
													voluptate velit esse cillum dolore eu fugiat nulla pariatur</small>
												<span class="badge badge-outline status"></span>
											</div>

										</div>
									</li>

									<li class="offline">
										<div class="media">
											<a class="pull-left profile-photo" href="#"> <img
												class="media-object"
												src="/YZY/assets/images/peter-avatar.jpg" alt>

											</a>
											<div class="media-body">
												<h6 class="media-heading">
													Peter <strong>Kay</strong>
												</h6>
												<small>Excepteur sint occaecat cupidatat non
													proident, sunt in culpa qui officia deserunt mollit </small> <span
													class="badge badge-outline status"></span>
											</div>
										</div>
									</li>

								</ul>

							</div>

							<div class="tab-pane" id="mmenu-friends">
								<h5>
									<strong>Friends</strong> List
								</h5>
								<ul class="favourite-list">

									<li class="online">
										<div class="media">

											<a class="pull-left profile-photo" href="#"> <img
												class="media-object"
												src="/YZY/assets/images/arnold-avatar.jpg" alt>
											</a> <span class="badge badge-red unread">3</span>

											<div class="media-body">
												<h6 class="media-heading">
													Arnold <strong>Karlsberg</strong>
												</h6>
												<small><i class="fa fa-map-marker"></i> Bratislava,
													Slovakia</small> <span class="badge badge-outline status"></span>
											</div>

										</div>
									</li>

									<li class="offline">
										<div class="media">
											<a class="pull-left profile-photo" href="#"> <img
												class="media-object"
												src="/YZY/assets/images/random-avatar8.jpg" alt>
											</a>
											<div class="media-body">
												<h6 class="media-heading">
													Anna <strong>Opichia</strong>
												</h6>
												<small><i class="fa fa-map-marker"></i> Budapest,
													Hungary</small> <span class="badge badge-outline status"></span>
											</div>
										</div>
									</li>

									<li class="busy">
										<div class="media">
											<a class="pull-left profile-photo" href="#"> <img
												class="media-object"
												src="/YZY/assets/images/random-avatar1.jpg" alt>
											</a>
											<div class="media-body">
												<h6 class="media-heading">
													Lucius <strong>Cashmere</strong>
												</h6>
												<small><i class="fa fa-map-marker"></i> Wien,
													Austria</small> <span class="badge badge-outline status"></span>
											</div>
										</div>
									</li>

									<li class="online">
										<div class="media">
											<a class="pull-left profile-photo" href="#"> <img
												class="media-object" src="/YZY/assets/images/ici-avatar.jpg"
												alt>
											</a>
											<div class="media-body">
												<h6 class="media-heading">
													Ing. Imrich <strong>Kamarel</strong>
												</h6>
												<small><i class="fa fa-map-marker"></i> Ulaanbaatar,
													Mongolia</small> <span class="badge badge-outline status"></span>
											</div>
										</div>
									</li>

								</ul>
							</div>

							<div class="tab-pane pane-settings" id="mmenu-settings">
								<h5>
									<strong>Chat</strong> Settings
								</h5>

								<ul class="settings">

									<li>
										<div class="form-group">
											<label class="col-xs-8 control-label">Show Offline
												Users</label>
											<div class="col-xs-4 control-label">
												<div class="onoffswitch greensea">
													<input type="checkbox" name="onoffswitch"
														class="onoffswitch-checkbox" id="show-offline" checked="">
													<label class="onoffswitch-label" for="show-offline">
														<span class="onoffswitch-inner"></span> <span
														class="onoffswitch-switch"></span>
													</label>
												</div>
											</div>
										</div>
									</li>

									<li>
										<div class="form-group">
											<label class="col-xs-8 control-label">Show Fullname</label>
											<div class="col-xs-4 control-label">
												<div class="onoffswitch greensea">
													<input type="checkbox" name="onoffswitch"
														class="onoffswitch-checkbox" id="show-fullname"> <label
														class="onoffswitch-label" for="show-fullname"> <span
														class="onoffswitch-inner"></span> <span
														class="onoffswitch-switch"></span>
													</label>
												</div>
											</div>
										</div>
									</li>

									<li>
										<div class="form-group">
											<label class="col-xs-8 control-label">History Enable</label>
											<div class="col-xs-4 control-label">
												<div class="onoffswitch greensea">
													<input type="checkbox" name="onoffswitch"
														class="onoffswitch-checkbox" id="show-history" checked="">
													<label class="onoffswitch-label" for="show-history">
														<span class="onoffswitch-inner"></span> <span
														class="onoffswitch-switch"></span>
													</label>
												</div>
											</div>
										</div>
									</li>

									<li>
										<div class="form-group">
											<label class="col-xs-8 control-label">Show Locations</label>
											<div class="col-xs-4 control-label">
												<div class="onoffswitch greensea">
													<input type="checkbox" name="onoffswitch"
														class="onoffswitch-checkbox" id="show-location" checked="">
													<label class="onoffswitch-label" for="show-location">
														<span class="onoffswitch-inner"></span> <span
														class="onoffswitch-switch"></span>
													</label>
												</div>
											</div>
										</div>
									</li>

									<li>
										<div class="form-group">
											<label class="col-xs-8 control-label">Notifications</label>
											<div class="col-xs-4 control-label">
												<div class="onoffswitch greensea">
													<input type="checkbox" name="onoffswitch"
														class="onoffswitch-checkbox" id="show-notifications">
													<label class="onoffswitch-label" for="show-notifications">
														<span class="onoffswitch-inner"></span> <span
														class="onoffswitch-switch"></span>
													</label>
												</div>
											</div>
										</div>
									</li>

									<li>
										<div class="form-group">
											<label class="col-xs-8 control-label">Show Undread
												Count</label>
											<div class="col-xs-4 control-label">
												<div class="onoffswitch greensea">
													<input type="checkbox" name="onoffswitch"
														class="onoffswitch-checkbox" id="show-unread" checked="">
													<label class="onoffswitch-label" for="show-unread">
														<span class="onoffswitch-inner"></span> <span
														class="onoffswitch-switch"></span>
													</label>
												</div>
											</div>
										</div>
									</li>

								</ul>

							</div>
							<!-- tab-pane -->

						</div>
						<!-- tab-content -->
					</div>

				</div>
				<!-- Make page fluid-->

			</div>
			<!-- Wrap all page content end -->

			<section class="videocontent" id="video"></section>

			<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
			<script src="/YZY/assets/js/jquery.js"></script>
			<!-- Include all compiled plugins (below), or include individual files as needed -->
			<script src="/YZY/assets/js/vendor/bootstrap/bootstrap.min.js"></script>
			<script
				src="https://google-code-prettify.googlecode.com/svn/loader/run_prettify.js?lang=css&skin=sons-of-obsidian"></script>
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

			<script src="/YZY/assets/js/vendor/summernote/summernote.min.js"></script>

			<script src="/YZY/assets/js/vendor/chosen/chosen.jquery.min.js"></script>

			<script src="/YZY/assets/js/vendor/momentjs/moment-with-langs.min.js"></script>
			<script
				src="/YZY/assets/js/vendor/datepicker/bootstrap-datetimepicker.min.js"></script>

			<script
				src="/YZY/assets/js/vendor/colorpicker/bootstrap-colorpicker.min.js"></script>

			<script
				src="/YZY/assets/js/vendor/colorpalette/bootstrap-colorpalette.js"></script>

			<script src="/YZY/assets/js/minimal.min.js"></script>

			<script>
				//initialize file upload button function
				$(document)
						.on(
								'change',
								'.btn-file :file',
								function() {
									var input = $(this), numFiles = input
											.get(0).files ? input.get(0).files.length
											: 1, label = input.val().replace(
											/\\/g, '/').replace(/.*\//, '');
									input.trigger('fileselect', [ numFiles,
											label ]);
								});
				$(function() {
					//load wysiwyg editor
					$('#input06')
							.summernote(
									{
										toolbar : [
												//['style', ['style']], // no style button
												[
														'style',
														[ 'bold', 'italic',
																'underline',
																'clear' ] ],
												[ 'fontsize', [ 'fontsize' ] ],
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
										height : 137
									//set editable area's height
									});
					//chosen select input
					$(".chosen-select").chosen({
						disable_search_threshold : 10
					});
					//initialize datepicker
					$('#datepicker').datetimepicker({
						icons : {
							time : "fa fa-clock-o",
							date : "fa fa-calendar",
							up : "fa fa-arrow-up",
							down : "fa fa-arrow-down"
						}
					});
					$("#datepicker").on(
							"dp.show",
							function(e) {
								var newtop = $(
										'.bootstrap-datetimepicker-widget')
										.position().top - 45;
								$('.bootstrap-datetimepicker-widget').css(
										'top', newtop + 'px');
							});
					//initialize colorpicker
					$('#colorpicker').colorpicker();
					$('#colorpicker')
							.colorpicker()
							.on(
									'showPicker',
									function(e) {
										var newtop = $(
												'.dropdown-menu.colorpicker.colorpicker-visible')
												.position().top - 45;
										$(
												'.dropdown-menu.colorpicker.colorpicker-visible')
												.css('top', newtop + 'px');
									});
					//initialize colorpicker RGB
					$('#colorpicker-rgb').colorpicker({
						format : 'rgb'
					});
					$('#colorpicker-rgb')
							.colorpicker()
							.on(
									'showPicker',
									function(e) {
										var newtop = $(
												'.dropdown-menu.colorpicker.colorpicker-visible')
												.position().top - 45;
										$(
												'.dropdown-menu.colorpicker.colorpicker-visible')
												.css('top', newtop + 'px');
									});
					//initialize file upload button
					$('.btn-file :file')
							.on(
									'fileselect',
									function(event, numFiles, label) {
										var input = $(this).parents(
												'.input-group').find(':text'), log = numFiles > 1 ? numFiles
												+ ' files selected'
												: label;
										console.log(log);
										if (input.length) {
											input.val(log);
										} else {
											if (log)
												alert(log);
										}
									});
					// Initialize colorpalette
					$('#event-colorpalette').colorPalette(
							{
								colors : [ [ '#428bca', '#5cb85c', '#5bc0de',
										'#f0ad4e', '#d9534f', '#ff4a43',
										'#22beef', '#a2d200', '#ffc100',
										'#cd97eb', '#16a085', '#FF0066',
										'#A40778', '#1693A5' ] ]
							}).on(
							'selectColor',
							function(e) {
								var data = $(this).data();
								$(data.returnColor).val(e.color);
								$(this).parents(".input-group").css(
										"border-bottom-color", e.color);
							});
				})
			</script>
			<script type="text/javascript">
				${requestScope.Message}//el表达式语言

				$("#btnPublish").click(function() {
					var flag = true;
					if ($("#txtTitle").val().length == 0) {
						$("#spanTitle").text("标题不能为空!");
						flag = false;
					} else
						$("#spanTitle").text("");

					if (flag)
						$("#frmPublishDepartmentMessage").submit();
				});
			</script>
</body>

</html>