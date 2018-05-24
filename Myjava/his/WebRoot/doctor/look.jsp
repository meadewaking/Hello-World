<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <title>查看---2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=path%>/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/Css/style.css" />
    <script type="text/javascript" src="<%=path%>/Js/jquery.js"></script>
    <script type="text/javascript" src="<%=path%>/Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="<%=path%>/Js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=path%>/Js/ckform.js"></script>
    <script type="text/javascript" src="<%=path%>/Js/common.js"></script>
    <script type="text/javascript" src="<%=path%>/Js/ckeditor/ckeditor.js"></script>
 

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
    $(function () {       
		$('#backid').click(function(){
				window.location.href="<%=path%>/DoctorAction?action=FindDoctorPage";
		 });
    });
    </script>
</head>
<body>
<form action="" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td>${doc.name}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">证件类型</td>
        <td>
        	<c:if test="${doc.identifierType==1}">身份证</c:if>
        	<c:if test="${doc.identifierType==2}">护照</c:if>
        	<c:if test="${doc.identifierType==3}">军人证</c:if>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">证件号</td>
        <td>${doc.identifier}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">手机</td>
        <td>${doc.telphone}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">座机</td>
        <td>${doc.phone}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">性别</td>
        <td>
        <c:if test="${doc.gender==1}">男</c:if>
        <c:if test="${doc.gender==0}">女</c:if>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">年龄</td>
        <td>${doc.age}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">出生年月</td>
        <td>${doc.birthDate}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">电子邮箱</td>
        <td>${doc.email}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">所属科室</td>
        <td>${depName}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">学历</td>
        <td>
        <c:if test="${doc.degree==1}">专科</c:if>
        <c:if test="${doc.degree==2}">本科</c:if>
        <c:if test="${doc.degree==3}">博士</c:if>
        <c:if test="${doc.degree==4}">博士后</c:if>
        </td>
    </tr>
	<tr>
        <td width="10%" class="tableleft">备注</td>
        <td>${doc.remarks}</td>
	</tr>
    <tr>
        <td colspan="2">
			<center>
				<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
			</center
		</td>
    </tr>
</table>
</form>
</body>
</html>