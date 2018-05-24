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

			<li class="active"><a href="AdminIndexServlet"> <i
					class="fa fa-tachometer"></i> 首页

			</a></li>

			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> <i class="fa fa-list"></i> 登录管理 <b
					class="fa fa-plus dropdown-plus"></b>
			</a>
				<ul class="dropdown-menu">
					<li><a href="/YZY/ApproveLoginServlet"> <i
							class="fa fa-caret-right"></i> 登录审批
					</a></li>
					<li><a href="/YZY/ManageLoginsServlet"> <i
							class="fa fa-caret-right"></i> 登录信息管理
					</a></li>
					<li><a href="/YZY/ManageUsersServlet"> <i
							class="fa fa-caret-right"></i> 用户信息管理
					</a></li>
				</ul></li>

			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> <i class="fa fa-list"></i> 消息管理 <b
					class="fa fa-plus dropdown-plus"></b>
			</a>
				<ul class="dropdown-menu">
					<li><a href="/YZY/ManagePublicMessageServlet"> <i
							class="fa fa-caret-right"></i> 公告信息管理
					</a></li>
					<li><a href="/YZY/ManageDepartmentMessageServlet"> <i
							class="fa fa-caret-right"></i> 部门消息管理
					</a></li>
					<li><a href="/YZY/ManageNotesServlet"> <i
							class="fa fa-caret-right"></i> 个人消息管理
					</a></li>
				</ul></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> <i class="fa fa-list"></i> 日志管理 <b
					class="fa fa-plus dropdown-plus"></b>
			</a>
				<ul class="dropdown-menu">
					<li><a href="/YZY/admin/ManageLoginLogsServlet"> <i
							class="fa fa-caret-right"></i> 登录日志管理
					</a></li>
					<li><a href="/YZY/admin/ManageModifyPasswordLogServlet"> <i
							class="fa fa-caret-right"></i> 密码修改日志管理
					</a></li>

				</ul></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> <i class="fa fa-list"></i> 系统管理 <b
					class="fa fa-plus dropdown-plus"></b>
			</a>
				<ul class="dropdown-menu">
					<li><a href="/YZY/admin/ManageDepartmentsServlet"> <i
							class="fa fa-caret-right"></i> 部门信息管理
					</a></li>
					<li><a href="/YZY/admin/ManageLoginTypesServlet"> <i
							class="fa fa-caret-right"></i> 登录类型信息管理
					</a></li>
					<li><a href="/YZY/admin/ManageLoginStatesServlet"> <i
							class="fa fa-caret-right"></i> 登录状态信息管理
					</a></li>
				</ul></li></li>


</ul>

</li>



</ul>
<!-- Sidebar end -->