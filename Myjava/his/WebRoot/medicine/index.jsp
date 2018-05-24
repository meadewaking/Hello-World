<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<title>药品查询----2015</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=path%>/Css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/Css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/Css/style.css" />
<script type="text/javascript" src="<%=path%>/Js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/Js/jquery.sorted.js"></script>
<script type="text/javascript" src="<%=path%>/Js/bootstrap.js"></script>
<script type="text/javascript" src="<%=path%>/Js/ckform.js"></script>
<script type="text/javascript" src="<%=path%>/Js/common.js"></script>

<style type="text/css">
body {
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}
.cur{
color:red;
}

@media ( max-width : 980px) { /* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}
</style>
<script type="text/javascript">
    //首页	
	function toptotal(){
		document.forms[0].setAttribute("action","DrugAction?action=FindDrugPage&pageNo=1");
		document.forms[0].submit();
	}
    //上一页
	function upPage(pageNo){
	var drugName=document.getElementById("drugName").value;
	var drugType=document.getElementById("drugType").value;
		if(pageNo!=1){//判断当前页数是否不等于1
		pageNo=parseInt(pageNo)-1;
		}
		document.forms[0].setAttribute("action","DrugAction?action=FindDrugPage&pageNo="+pageNo+"&drugName="+drugName+"&drugType="+drugType);
        document.forms[0].submit();
	}
	//下一页
	function nextTotal(pageNo,totalPage){
	var drugName=document.getElementById("drugName").value;
	var drugType=document.getElementById("drugType").value;
		if(pageNo!=totalPage){//判断当前页数是否等于总页数
		pageNo=parseInt(pageNo)+1;
		}
		document.forms[0].setAttribute("action","DrugAction?action=FindDrugPage&pageNo="+pageNo+"&drugName="+drugName+"&drugType="+drugType);
       	document.forms[0].submit();
	} 	
	//尾页
	function totalPage(totalPage){
	var drugName=document.getElementById("drugName").value;
	var drugType=document.getElementById("drugType").value;
		document.forms[0].setAttribute("action","DrugAction?action=FindDrugPage&pageNo="+totalPage+"&drugName="+drugName+"&drugType="+drugType);
        document.forms[0].submit();
	}	
	//点击任意一页
	function anyPage(li){
	var drugName=document.getElementById("drugName").value;
	var drugType=document.getElementById("drugType").value;
	document.forms[0].setAttribute("action","DrugAction?action=FindDrugPage&pageNo="+li+"&drugName="+drugName+"&drugType="+drugType);
    document.forms[0].submit();
	}	
    
	 $(function () {
		$('#newNav').click(function(){
				window.location.href="medicine/addDrug.jsp";
		 });
		$('#cls').click(function(){
			document.getElementById("drugName").value="";
			document.getElementById("drugType").value="0";
			window.location.href="DrugAction?action=FindDrugPage";
		});
		
		$('#drugToTxt').click(function(){
			var file_name = window.prompt("请指定输出文件名称(.txt)", "C:/drug.txt");
			$.ajax({
				type : "POST",
				url : "<%=path%>/DrugAction?action=DrugToTxt",
	 			data : "fileName="+file_name,
				success : function(msg) {
					if (msg==1) {
						alert("导出成功!已导出至"+file_name);
					}else{
						alert("导出失败!")
					}
				}
			}); 
		 });
		$('#exportExcel').click(function(){
			var file_name = window.prompt("请指定输出文件名称(.xls)", "C:/drug.xls");
			$.ajax({
				type : "POST",
				url : "<%=path%>/DrugAction?action=ExportExcel",
	 			data : "fileName="+file_name,
				success : function(msg) {
					if (msg==1) {
						alert("已导出至"+file_name);
					}else{
						alert("导出失败!")
					}
				}
			}); 
		 });
    });
    	function checkall(){
			var alls=document.getElementsByName("check");
			var ch=document.getElementById("checkall");
			if(ch.checked){
				for(var i=0;i<alls.length;i++){
					alls[i].checked=true;	
				}	
			}else{
				for(var i=0;i<alls.length;i++){
					alls[i].checked=false;	
				}	
			}
		}
		function delAll(){
			var alls=document.getElementsByName("check");
			var ids=new Array();
			for(var i=0;i<alls.length;i++){
				if(alls[i].checked){
					ids.push(alls[i].value);
				}		
			}
			if(ids.length>0){
				if(confirm("确认操作?")){
					alert("成功!");
				}
			}else{
				alert("请选中要操作的项");
			}
		}
		//获取药类型并选中
		function sel(){
		$("#drugType option[value='"+${drugType}+"']").attr("selected",true);
		}
		//当前页码高亮
		$(document).ready(function(){$("a[onclick='anyPage("+${pageNo}+")']").addClass("current");});
		
    </script>
    
</head>
<body onload="sel()">
	<form action="<%=path%>/DrugAction?action=FindDrugPage" method="post"
		class="definewidth m20">
		<table class="table table-bordered table-hover definewidth m10">
			<tr>
				<td width="10%" class="tableleft">药品名称：</td>
				<td>
				<input type="text" id="drugName" name="drugName" value="${drugName}">
				</td>
				<td width="10%" class="tableleft">药品类型：</td>
				<td><select id="drugType" name="drugType">
						<option value="0">所有</option>
						<option value="1">中药</option>
						<option value="2">西药</option>
						<option value="3">处方药</option>
						<option value="4">非处方药</option>
				</select></td>
			</tr>
			<tr>
				<td colspan="4">
					<center>
						<button type="submit" class="btn btn-primary">查询</button>
						<button id="cls" class="btn btn-primary">清空</button>
					</center>
				</td>
			</tr>
		</table>
	</form>

	<table class="table table-bordered table-hover definewidth m10" id="tabl">
		<thead>
			<tr>
				<th><input type="checkbox" id="checkall" onChange="checkall();">
				</th>
				<th>药品编号</th>
				<th>药品名称</th>
				<th>药品类型</th>
				<th>简单描述</th>
				<th>状态</th>
				<th>剩余量</th>
				<th>操作</th>
			</tr>
		</thead>
		<c:forEach items="${drugList}" var="drug">
			<tr>
				<td style="vertical-align:middle;"><input type="checkbox"
					name="check" value="${drug.DID}">
				</td>
				<td style="vertical-align:middle;">${drug.DID}</td>
				<td style="vertical-align:middle;">${drug.drugName}</td>
				<td style="vertical-align:middle;">
				<c:if test="${drug.drugType==1}">中药</c:if> 
				<c:if test="${drug.drugType==2}">西药</c:if> 
				<c:if test="${drug.drugType==3}">处方药</c:if>
				</td>
				<td style="vertical-align:middle;">${drug.description}</td>
				<td style="vertical-align:middle;">
				<c:if test="${drug.drugflag==1}">销售中</c:if> 
			    <c:if test="${drug.drugflag==0}">停售</c:if>
				</td>
				<td style="vertical-align:middle;">${drug.inventory}</td>
				<td style="vertical-align:middle;">
				<a href="<%=path%>/DrugAction?action=UpdateDrugA&DID=${drug.DID}">更改</a>&nbsp;&nbsp;&nbsp;
				<a href="<%=path%>/DrugAction?action=Look&DID=${drug.DID}">详情>>></a>
				</td>
			</tr>
		</c:forEach>
	</table>

	<table class="table table-bordered table-hover definewidth m10">
		<tr>
			<th colspan="5">
				<div class="inline pull-right page">
					<a href="javascript:void(0)" onclick="toptotal()">首页</a> 
					<a href='javascript:void(0)' onclick="upPage('${pageNo}')">上一页</a> 
					<a href="javascript:void(0)" onclick="nextTotal('${pageNo}','${totalPage}')">下一页</a> 
					<c:if test="${totalPage<4}">
						<c:forEach items="${lis}" var="li">
							<a href="javascript:void(0)" onclick="anyPage(${li})">${li}</a>
						</c:forEach>
					</c:if>
					<c:if test="${totalPage>=4}">
						<a href="javascript:void(0)" onclick="anyPage(${pageMid-1})">${pageMid-1}</a>
						<a href="javascript:void(0)" onclick="anyPage(${pageMid})">${pageMid}</a>
	     			  	<a href="javascript:void(0)" onclick="anyPage(${pageMid+1})">${pageMid+1}</a>
					</c:if>
					<a href="javascript:void(0)" onclick="totalPage('${totalPage}')">尾页</a> <span>共${totalPage}页,${totalCount}条</span>
				</div>
				<div>
					<button type="button" class="btn btn-success" id="newNav">添加新药</button>
					<button type="button" class="btn btn-success" id="exportExcel">导出Excel</button>
					<button type="button" class="btn btn-success" id="drugToTxt">导出txt</button>
				</div></th>
		</tr>
	</table>

</body>
</html>
