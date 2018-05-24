<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="sidebar">
	<div class="sidebar-dropdown">
		<a href="#">导航</a>
	</div>
	<ul id="nav">
		<!-- Main menu with font awesome icon -->
		<li><a href="index.jsp" class="open"><i class="icon-home"></i>
				首页</a> <!-- Sub menu markup 
            <ul>
              <li><a href="#">Submenu #1</a></li>
              <li><a href="#">Submenu #2</a></li>
              <li><a href="#">Submenu #3</a></li>
            </ul>--></li>
		<li class="has_sub"><a href="#"><i class="icon-list-alt"></i>
				登录管理 <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
			<ul>
				<li><a href="ApproveLoginServlet">登录审批</a></li>
				<li><a href="ManageLoginsServlet">登录信息管理</a></li>
				<li><a href="ManageUsersServlet">用户信息管理</a></li>
			</ul></li>
		<li class="has_sub"><a href="#"><i class="icon-file-alt"></i>
				消息管理 <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
			<ul>
				<li><a href="ManagePublicMessageServlet">公告信息管理</a></li>
				<li><a href="ManageDepartmentMessageServlet">部门消息管理</a></li>
				<li><a href="ManageNotesServlet">个人消息管理</a></li>
			</ul></li>
		<li class="has_sub"><a href="#"><i class="icon-file-alt"></i>
				日志管理 <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
			<ul>
				<li><a href="/MyWeb/admin/ManageLoginLogsServlet">登录日志管理</a></li>
				<li><a href="ManageModifyPasswordLogServlet">密码修改日志管理</a></li>
			</ul></li>
		<li class="has_sub"><a href="#"><i class="icon-file-alt"></i>
				系统管理 <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
			<ul>
				<li><a href="/MyWeb/admin/ManageDepartmentsServlet">部门信息管理</a></li>
				<li><a href="/MyWeb/admin/ManageLoginTypesServlet">登录类型信息管理</a></li>
				<li><a href="/MyWeb/admin/ManageLoginStatesServlet">登录状态信息管理</a></li>
			</ul></li>
	</ul>

</div>