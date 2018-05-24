<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <title>添加医生---2015</title>
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
 	<script type="text/javascript" src="<%=path%>/Js/My97DatePicker/WdatePicker.js"></script>

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
				"docname" : {
					required : true,
					maxlength : 30
				},
				"identifier" : {
					required : true,
					maxlength : 30
				},
				"telphone" : {
					required : true,
					maxlength : 20
				},
				"phone" : {
					required : true,
					maxlength : 15
				},
				"birthDate" : {
					required : true,
					date : true,
					maxlength : 15
				},
				"age" : {
					required : true,
					number : true,
					maxlength : 10
				},
				"email" : {
					required : true,
					email : true,
					maxlength : 35
				}
			},
			success : function(label) {
				label.html("&nbsp;").addClass("checked");
			}
		});
	}
	</script>
    <script type="text/javascript">
    $(function () {       
		$('#backid').click(function(){
			window.location.href="<%=path%>/DoctorAction?action=FindDoctorPage";
		 });
    });
    </script>
</head>
<body>
<form id="frm1" action="<%=path%>/DoctorAction?action=AddDoctor" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td><input type="text" name="docname" value=""/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">证件类型</td>
        <td><select name="identifierType">
        <option value="1">身份证</option>
        <option value="2">护照</option>
        <option value="3">军人证</option>
        </select></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">证件号</td>
        <td><input type="text" name="identifier" value=""/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">手机</td>
        <td><input type="text" name="telphone" value=""/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">座机</td>
        <td><input type="text" name="phone" value=""/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">性别</td>
        <td>
        <input type="radio" name="gender" value="1" checked/>男&nbsp;&nbsp;&nbsp;
        <input type="radio" name="gender" value="0"/>女</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">出生年月</td>
        <td><input type="text" id="birthDate" name="birthDate" class="Wdate" onfocus="WdatePicker({el:$dp.$('birthDate')})"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">年龄</td>
        <td><input type="text" name="age"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">电子邮箱</td>
        <td><input type="text" name="email"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">所属科室</td>
        <td><select name="depid">
        <c:forEach items="${depList}" var="de">
        <option value="${de.id}">${de.depName}</option>
        </c:forEach>
        </select></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">学历</td>
        <td><select name="degree">
        <option value="1">专科</option>
        <option value="2">本科</option>
        <option value="3">博士</option>
        <option value="4">博士后</option>
        </select></td>
    </tr>
	<tr>
        <td width="10%" class="tableleft">备注</td>
        <td><textarea name="remarks"></textarea></td>
	</tr>
    <tr>
        <td colspan="2">
			<center>
				<button type="submit" class="btn btn-primary">保存</button> &nbsp;&nbsp;
				<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
			</center
		</td>
    </tr>
</table>
</form>
</body>
</html>
