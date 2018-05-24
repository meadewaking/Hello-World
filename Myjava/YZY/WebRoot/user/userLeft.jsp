<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!-- Sidebar -->
<ul class="nav navbar-nav side-nav" id="sidebar">

	<li class="collapsed-content">
		<ul>
			<li class="search">
				<!-- Collapsed search pasting here at 768px -->
			</li>
		</ul>
	</li>

	<li class="navigation" id="navigation"><a href="#"
		class="sidebar-toggle" data-toggle="#navigation">导航 <i
			class="fa fa-angle-up"></i></a>

		<ul class="menu">

			<li class="active"><a href="UserIndexServlet"> <i
					class="fa fa-tachometer"></i> 首页

			</a></li>

			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> <i class="fa fa-list"></i> 消息功能 <b
					class="fa fa-plus dropdown-plus"></b>
			</a>
				<ul class="dropdown-menu">
					<li><a href="/YZY/user/ShowPublicMessageListServlet"> <i
							class="fa fa-caret-right"></i> 查看公告列表
					</a></li>
					<li><a href="/YZY/user/ShowDepartmentMessageListServlet"> <i
							class="fa fa-caret-right"></i> 查看部门消息列表
					</a></li>
					<li><a href="/YZY/user/PublishDepartmentMessageServlet"> <i
							class="fa fa-caret-right"></i> 发布部门消息
					</a></li>
					<li><a href="/YZY/user/ShowReceiveNoteListServlet"> <i
							class="fa fa-caret-right"></i> 查看接收消息列表
					</a></li>
					<li><a href="/YZY/user/ShowSendNoteListServlet"> <i
							class="fa fa-caret-right"></i> 查看发送消息列表
					</a></li>
					<li><a href="/YZY/user/SendNoteServlet"> <i
							class="fa fa-caret-right"></i> 发送个人消息
					</a></li>
				</ul></li>

			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> <i class="fa fa-list"></i> 日志管理 <b
					class="fa fa-plus dropdown-plus"></b>
			</a>
				<ul class="dropdown-menu">
					<li><a href="/YZY/user/ShowLoginLogsServlet"> <i
							class="fa fa-caret-right"></i> 查看个人登录日志
					</a></li>
					<li><a href="/YZY/user/ShowModifyPasswordLogsServlet"> <i
							class="fa fa-caret-right"></i> 查看密码修改日志
					</a></li>
				</ul></li>



		</ul></li>



</ul>
<!-- Sidebar end -->