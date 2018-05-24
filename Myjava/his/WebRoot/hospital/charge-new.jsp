<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <title>添加收费项目-- -2015</title>
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
				"medicalNo" : {
					required : true,
					maxlength : 20
				},
				"name" : {
					required : true,
					maxlength : 20
				},
				"feeName" : {
					required : true,
					maxlength : 30
				},
				"amount" : {
					required : true,
					number : true,
					maxlength : 15
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
				window.location.href="<%=path%>/FeeAction?action=FindFeePage";
		 });
    });
    </script>
    <script type="text/javascript">
    $(function(){
		$("#medicalNo").blur(function(){
 	  	var mno=$("#medicalNo").val();
 	 	$.ajax({
			type : "POST",
			url : "<%=path%>/FeeAction?action=ShowName",
 			data : "mno="+mno,
			success : function(msg) {
				var re=eval("("+msg+")");
				$("#name").text(re.name);
			}
		});  
 	 	});
 	 });
    </script>
</head>
<body>
<form id="frm1" action="<%=path%>/FeeAction?action=AddFee" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
    
        <td width="10%" class="tableleft">病历号</td>
        <td><input type="text" name="medicalNo" id="medicalNo" value=""/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td><span id="name"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">收费项目</td>
        <td>
	        <input type="text" name="feeName" value=""/>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">收费金额</td>
        <td><input type="text" name="amount" value=""/></td>
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
