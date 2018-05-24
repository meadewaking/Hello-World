<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <title></title>
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
     <script type="text/javascript">
	$(document).ready(init);
	function init() {
		$("#frm1").validate( {
			rules : {
				"roleName" : {
					required : true,
					maxlength : 10
				}
			},
			success : function(label) {
				label.html("&nbsp;").addClass("checked");
			}
		});
	}
	</script>
    
    <script>
    $(function () {
        $(':checkbox[name="group[]"]').click(function () {
            $(':checkbox', $(this).closest('li')).prop('checked', this.checked);
        });

		$('#backid').click(function(){
				window.location.href="<%=path%>/RoleAction?action=FindRolePage";
		 });

    });
  
    function sel(){
    	$("#status input[value='"+${role.roleStatus}+"']").attr("checked",true);
    	var ary=${resIdList};
    	for(var i=0;i<ary.length;i++){
    		$("#group input[value='"+ary[i]+"']").attr("checked",true);
    	}
    }
	</script>
</head>
<body onload="sel()">
<form id="frm1" action="<%=path%>/RoleAction?action=updateRole&roleId=${role.roleId}" method="post" class="definewidth m20" >
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">角色名称</td>
        <td><input type="text" name="roleName" value="${role.roleName}"/></td>
    </tr>
    <tr>
        <td class="tableleft">状态</td>
        <td id="status">
            <input type="radio" name="roleStatus" value="1" /> 启用
           	<input type="radio" name="roleStatus" value="0"  /> 禁用
        </td>
    </tr>
    <tr>
        <td class="tableleft">权限</td>
        <td id="group">
		<ul><label class='checkbox inline'><input type='checkbox' name='group[]' value='1' />挂号信息管理</label></ul> 
        <ul><label class='checkbox inline'><input type='checkbox' name='group[]' value='2' />门诊医生管理</label></ul> 
        <ul><label class='checkbox inline'><input type='checkbox' name='group[]' value='3' />药品管理</label></ul> 
        <ul><label class='checkbox inline'><input type='checkbox' name='group[]' value='4' />住院办理</label></ul> 
        <ul><label class='checkbox inline'><input type='checkbox' name='group[]' value='5' />收费项目登记</label></ul> 
        <ul><label class='checkbox inline'><input type='checkbox' name='group[]' value='6' />在院发药</label></ul> 
        <ul><label class='checkbox inline'><input type='checkbox' name='group[]' value='7' />住院结算</label></ul> 
        <ul><label class='checkbox inline'><input type='checkbox' name='group[]' value='8' />月营业额统计</label></ul> 
        <ul><label class='checkbox inline'><input type='checkbox' name='group[]' value='9' />年营业额统计</label></ul> 
        <ul><label class='checkbox inline'><input type='checkbox' name='group[]' value='10' />用户管理</label></ul> 
        <ul><label class='checkbox inline'><input type='checkbox' name='group[]' value='11' />角色管理</label></ul> 
        <ul><label class='checkbox inline'><input type='checkbox' name='group[]' value='12' />资源管理</label></ul> 
		</td>
    </tr>
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="submit" class="btn btn-primary" type="button">更新</button> &nbsp;&nbsp;
            <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
</table>
</form>
</body>
</html>

