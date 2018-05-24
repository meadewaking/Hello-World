<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <title>挂号-- -2015</title>
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
    <script type="text/javascript" src="<%=path %>/Js/ckeditor/ckeditor.js"></script>
 
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
				"name" : {
					required : true,
					maxlength : 10
				},
				"identifier" : {
					required : true,
					maxlength : 30
				},
				"insuranceNumber" : {
					required : true,
					maxlength : 30
				},
				"regfee" : {
					required : true,
					number : true,
					maxlength : 15
				},
				"phoneNumber" : {
					required : true,
					maxlength : 20
				},
				"age" : {
					required : true,
					number : true,
					maxlength : 3
				},
				"profession" : {
					required : true,
					maxlength : 20
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
				window.location.href="RegisterAction?action=FindRegPage";
		 });
    });
    </script>
   <script type="text/javascript">
   $(function(){
	 	$.ajax({
			type : "POST",
			url : "RegisterAction?action=findDocList",
			data : "depid=1",
			success : function(msg) {
				$("#ss").html(" ");
				var list=eval(msg);
				$.each(list,function(index, user){
				 	$("#ss").append("<option value="+user.id+">"+user.name+"</option>");
				});
			}
		});  
	 	
	 });
   
	 $(function(){
			$("#depId").change(function(){
	 	  	var depid=$("#depId").val();
	 	 	$.ajax({
				type : "POST",
				url : "RegisterAction?action=findDocList",
	 			data : "depid="+depid,
				success : function(msg) {
					$("#ss").html(" ");
					var list=eval(msg);
					$.each(list,function(index, user){
					 	$("#ss").append("<option value="+user.id+">"+user.name+"</option>");
					});
				}
			});  
	 	 	});
	 	 });
    </script>
</head>
<body>
<form id="frm1" action="RegisterAction?action=AddRegB" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td><input type="text" name="name"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">证件类型</td>
        <td><select name="identifierType"><option value="1">身份证</option><option value="2">护照</option><option value="3">军人证</option></select></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">证件号</td>
        <td><input type="text" name="identifier" value=""/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">社保号</td>
        <td><input type="text" name="insuranceNumber"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">挂号费</td>
        <td><input type="text" name="regfee"/>元</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">联系电话</td>
        <td><input type="text" name="phoneNumber" value=""/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">是否自费</td>
        <td><input type="radio" name="expenseFlag" value="1" checked/>是&nbsp;&nbsp;&nbsp;<input type="radio" name="expenseFlag" value="0"/>否</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">性别</td>
        <td><input type="radio" name="gender" value="1" checked/>男&nbsp;&nbsp;&nbsp;<input type="radio" name="gender" value="0"/>女</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">年龄</td>
        <td><input type="text" name="age" value=""/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">职业</td>
        <td><input type="text" name="profession" value=""/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">初复诊</td>
        <td><input type="radio" name="czflag" value="0" checked/>初诊&nbsp;&nbsp;&nbsp;
        <input type="radio" name="czflag" value="1"/>复诊</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">所挂科室</td>
        <td>
	        <select name="depId" id="depId">
	        <c:forEach items="${depList}" var="dep">
	        <option value="${dep.id}">${dep.depName}</option>
	        </c:forEach>
	        </select>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">指定医生</td>
        <td>
        	<select name="docId" id="ss">
	      
	        
        </td>
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
