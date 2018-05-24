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
				"resName" : {
					required : true,
					maxlength : 20
				},
				"resURL" : {
					required : true,
					maxlength : 256
				}
			},
			success : function(label) {
				label.html("&nbsp;").addClass("checked");
			}
		});
	}
	</script>
    <script>
    //返回列表
    $(function () {       
		$('#backid').click(function(){
				window.location.href="<%=path%>/ResourcesAction?action=FindResPage";
		 });
    });
    //原本状态
    function sel(){
    	$("#status input[value='"+${res.status}+"']").attr("checked",true);
    }
	</script>
</head>
<body onload="sel();">
<form id="frm1" action="<%=path%>/ResourcesAction?action=UpdateRes" method="post" class="definewidth m20">
<input type="hidden" name="resid" value="${res.resId}" />
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">资源名称</td>
            <td><input type="text" name="resName" value="${res.resName}"/></td>
        </tr>
        <tr>
            <td class="tableleft">url</td>
            <td><input type="text" name="resURL" value="${res.resURL}"/></td>
        </tr>
        <tr>
            <td class="tableleft">是否有效</td>
            <td id="status">
              <input type="radio" name="statu" value="1" /> 启用
              <input type="radio" name="statu" value="0" /> 禁用
            </td>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
                <button type="submit" class="btn btn-primary" type="button">更新</button>&nbsp;&nbsp;
                <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>

