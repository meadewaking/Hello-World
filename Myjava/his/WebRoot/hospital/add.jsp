<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <title>录入住院信息-- -2015</title>
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
				"medicalNo" : {
					required : true,
					maxlength : 30
				},
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
    <script type="text/javascript">
    $(function(){
		$("#medicalNo").blur(function(){
 	  	var mno=$("#medicalNo").val();
 	 	$.ajax({
			type : "POST",
			url : "../HospitalAction?action=ShowInformation",
 			data : "mno="+mno,
			success : function(msg) {
				if (msg==0) {
					alert("该病历号不存在,请重新输入!");
					$("#medicalNo").attr("value","");
				}else{
				var re=eval("("+msg+")");
				$("#name").text(re.name);
				var it=re.identifierType;
				if (it==1) {
					$("#identifierType").text("身份证");
				}else if(re.identifierType==2){
					$("#identifierType").text("护照");
				}else if(re.identifierType==3){
					$("#identifierType").text("军人证");
				}
				$("#identifier").text(re.identifier);
				$("#insuranceNumber").text(re.insuranceNumber);
				$("#phoneNumber").text(re.phoneNumber);
				if (re.expenseFlag==1) {
					$("#expenseFlag").text("是");
				}else if(re.expenseFlag==0){
					$("#expenseFlag").text("否");
				}
				if (re.gender==1) {
					$("#gender").text("男");
				}else if(re.gender==0){
					$("#gender").text("女");
				}
				$("#age").text(re.age);
				if (re.czflag==1) {
					$("#czflag").text("复诊");
				}else if(re.czflag==0){
					$("#czflag").text("初诊");
				}
				$("#depName").text(re.depName);
				$("#docName").text(re.docName);
				$("#remarks").text(re.remarks);
				}
			}
			});  
 		$.ajax({
			type : "POST",
			url : "../HospitalAction?action=Find",
				data : "mno="+mno,
			success : function(mesg) {
				if (mesg==1) {
					alert("当前病人已办理住院!")
					$("#subm").attr("disabled", true);
				}
			}
 		});  
 	 	
		});
		
		
 	 });
    </script>
</head>
<body>
<form id="frm1" action="<%=path%>/HospitalAction?action=AddHos" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">病历号</td>
        <td><input type="text" id="medicalNo" name="medicalNo" placeholder="输入病历号回车自动带出挂号人信息"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td><span id="name"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">证件类型</td>
        <td><span id="identifierType"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">证件号</td>
        <td><span id="identifier"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">社保号</td>
        <td><span id="insuranceNumber"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">联系电话</td>
        <td><span id="phoneNumber"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">是否自费</td>
        <td><span id="expenseFlag"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">性别</td>
        <td><span id="gender"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">年龄</td>
        <td><span id="age"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">初复诊</td>
        <td><span id="czflag"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">所挂科室</td>
        <td><span id="depName"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">指定医生</td>
        <td><span id="docName"></span></td>
    </tr>
	<tr>
        <td width="10%" class="tableleft">备注</td>
        <td><span id="remarks"></span></td>
	</tr>
    <tr>
        <td width="10%" class="tableleft">护理</td>
        <td><input type="text" name="nurse" value=""/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">床位号</td>
        <td><input type="text" name="bedNo" value=""/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">缴费押金</td>
        <td><input type="text" name="payCase" value=""/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">病情</td>
        <td><textarea name="pcondition"></textarea></td>
    </tr>
    <tr>
        <td colspan="2">
			<center>
				<button type="submit" class="btn btn-primary" id="subm">保存</button> &nbsp;&nbsp;
				<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
			</center
		</td>
    </tr>
</table>
</form>
</body>
</html>
