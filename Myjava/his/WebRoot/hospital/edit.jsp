<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <title>修改住院信息-- -2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=path%>/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/Css/style.css" />
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
				"nurse" : {
					required : true,
					maxlength : 30
				},
				"bedNo" : {
					required : true,
					maxlength : 30
				},
				"payCase" : {
					required : true,
					number : true,
					maxlength : 30
				},
				"pcondition" : {
					required : true,
					maxlength : 100
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
				window.location.href="<%=path%>/HospitalAction?action=FindHosPage";
		 });
    });
    </script>
</head>
<body>
<form id="frm1" action="<%=path%>/HospitalAction?action=EditHosB&medicalNo=${reg.medicalNo}" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">病历号</td>
        <td>${reg.medicalNo}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td>${reg.name}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">证件类型</td>
        <td>
		<c:if test="${reg.identifierType==1}">身份证</c:if>
		<c:if test="${reg.identifierType==2}">护照</c:if>
		<c:if test="${reg.identifierType==3}">军人证</c:if>
		</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">证件号</td>
        <td>${reg.identifier}</td>
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
        <td>
		<c:if test="${reg.expenseFlag==1}">是</c:if>
		<c:if test="${reg.expenseFlag==0}">否</c:if>
		</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">性别</td>
        <td>
		<c:if test="${reg.gender==1}">男</c:if>
		<c:if test="${reg.gender==0}">女</c:if>
		</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">年龄</td>
        <td>${reg.age}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">初复诊</td>
        <td>
		<c:if test="${reg.czflag==1}">复诊</c:if>
		<c:if test="${reg.czflag==0}">初诊</c:if>
		</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">所挂科室</td>
        <td><c:forEach items="${depList}" var="dep">
			<c:if test="${reg.depid==dep.id}">${dep.depName}</c:if>
			</c:forEach>
		</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">指定医生</td>
        <td><c:forEach items="${docList}" var="doc">
			<c:if test="${reg.docid==doc.id}">${doc.name}</c:if>
			</c:forEach>
		</td>
    </tr>
	<tr>
        <td width="10%" class="tableleft">备注</td>
        <td>${reg.remarks}</td>
	</tr>
    <tr>
        <td width="10%" class="tableleft">护理</td>
        <td><input type="text" name="nurse" value="${hos.nurse }"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">床位号</td>
        <td><input type="text" name="bedNo" value="${hos.bedNo}"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">缴费押金</td>
        <td><input type="text" name="payCase" value="${hos.payCase}"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">病情</td>
        <td><textarea name="pcondition">${hos.pcondition}</textarea></td>
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
