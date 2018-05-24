<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <title>添加用户</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=path%>/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/Css/style.css" />
    <%-- <script type="text/javascript" src="<%=path%>/Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="<%=path%>/Js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=path%>/Js/ckform.js"></script>
    <script type="text/javascript" src="<%=path%>/Js/common.js"></script> --%>
    <script type="text/javascript" src="<%=path%>/Js/jquery-1.6.4.min.js"></script>
    <script type="text/javascript" src="<%=path%>/Js/form/jquery.form.js"></script>
	<script type="text/javascript" src="<%=path%>/Js/form/jquery.validate.defined.js"></script>
	<script type="text/javascript" src="<%=path%>/Js/form/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=path%>/Js/form/jquery.validation.assist.js"></script>
 
    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }
    </style>
    <script>
    $(function () {       
		$('#backid').click(function(){
				window.location.href="<%=path%>/UserAction?action=FindUserPage";
		 });
    });
</script>
<script type="text/javascript">
	$(document).ready(init);
	function init() {
		$("#frm1").validate( {
			rules : {
				"username" : {
					required : true,
					maxlength : 10
				},
				"password" : {
					required : true,
					maxlength : 10
				},
				"realname" : {
					required : true,
					maxlength : 10
				},
				"email" : {
					required : true,
					email : true,
					maxlength : 20
				}
			},
			success : function(label) {
				label.html("&nbsp;").addClass("checked");
			}
		});
	}
</script>
</head>
<body>
<form action="<%=path%>/UserAction?action=AddUser" method="post" class="definewidth m20" id="frm1">
<!-- <input type="hidden" name="id" value="{$user.id}" /> -->
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">登录名</td>
            <td><input type="text" name="username"/></td>
        </tr>
        <tr>
            <td class="tableleft">密码</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td class="tableleft">真实姓名</td>
            <td><input type="text" name="realname"/></td>
        </tr>
        <tr>
            <td class="tableleft">邮箱</td>
            <td><input type="text" name="email"/></td>
        </tr>
        <tr>
            <td class="tableleft">状态</td>
            <td>
                <input type="radio" name="status" value="0" checked/> 启用
              	<input type="radio" name="status" value="1" /> 禁用
            </td>
        </tr>
        <tr>
            <td class="tableleft">角色</td>
            <td>
            	<select name="roleid">
	            	<c:forEach items="${roleList}" var="role">
	        			<option value="${role.roleId}">${role.roleName}
	            	</c:forEach>
       			 </select>
        	</td>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
                <button type="submit" class="btn btn-primary" type="button">保存</button>&nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
