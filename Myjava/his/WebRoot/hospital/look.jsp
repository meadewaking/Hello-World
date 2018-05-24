<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <title>查看-- -2015</title>
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
				window.location.href="HospitalAction?action=FindHosPage";
		 });
    });
    </script>
</head>
<body>
<form action="index.html" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td>${reg.name}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">证件类型</td>
        <td><c:if test="${reg.identifierType==1}">身份证</c:if>
        <c:if test="${reg.identifierType==2}">护照</c:if>
        <c:if test="${reg.identifierType==3}">军人证</c:if>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">证件号</td>
        <td>${reg.identifier}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">挂号费</td>
        <td>${reg.regfee}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">社保号</td>
        <td>${reg.insuranceNumber}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">联系电话</td>
        <td>${reg.phoneNumber}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">是否自费</td>
        <td><c:if test="${reg.expenseFlag==1}">是</c:if>
       		<c:if test="${reg.expenseFlag==0}">否</c:if>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">性别</td>
        <td><c:if test="${reg.gender==1}">男</c:if>
        	<c:if test="${reg.gender==0}">女</c:if>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">年龄</td>
        <td>${reg.age}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">职业</td>
        <td>${reg.profession}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">初复诊</td>
        <td><c:if test="${reg.czflag==0}">初诊</c:if>
       <c:if test="${reg.czflag==1}">复诊</c:if>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">所挂科室</td>
        <td>
        <c:forEach items="${depList}" var="dep">
        <c:if test="${dep.id==reg.depid}">${dep.depName}</c:if>
        </c:forEach>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">指定医生</td>
        <td>
			<c:forEach items="${docList}" var="doc">
			<c:if test="${doc.id==reg.docid}">${doc.name}</c:if>
			</c:forEach>
		</td>
    </tr>
	<tr>
        <td width="10%" class="tableleft">备注</td>
        <td>${reg.remarks}</td>
	</tr>
    <tr>
        <td colspan="2">
			<center>
				<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>
