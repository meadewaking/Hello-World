<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <title>收费项目管理-- -2015</title>
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
  //首页	
 	function toptotal(){
 		document.forms[0].setAttribute("action","FeeAction?action=FindFeePage&pageNo=1");
 		document.forms[0].submit();
 	}
     //上一页
 	function upPage(pageNo){
 		if(pageNo!=1){//判断当前页数是否不等于1
 		pageNo=parseInt(pageNo)-1;
 		}
 		document.forms[0].setAttribute("action","FeeAction?action=FindFeePage&pageNo="+pageNo);
         document.forms[0].submit();
 	}
 	//下一页
 	function nextTotal(pageNo,totalPage){
 		if(pageNo!=totalPage){//判断当前页数是否等于总页数
 		pageNo=parseInt(pageNo)+1;
 		}
 		document.forms[0].setAttribute("action","FeeAction?action=FindFeePage&pageNo="+pageNo);
        	document.forms[0].submit();
 	} 	
 	//尾页
 	function totalPage(totalPage){
 		document.forms[0].setAttribute("action","FeeAction?action=FindFeePage&pageNo="+totalPage);
         document.forms[0].submit();
 	}	
 	//点击任意一页
 	function anyPage(li){
 	document.forms[0].setAttribute("action","FeeAction?action=FindFeePage&pageNo="+li);
     document.forms[0].submit();
 	}
 	//当前页码高亮
	$(document).ready(function(){$("a[onclick='anyPage("+${pageNo}+")']").addClass("current");});
	 
 	$(function () {
		$('#newNav').click(function(){
				window.location.href="<%=path%>/FeeAction?action=FindFeePro";
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
    $(function () {       
		$('#backid').click(function(){
				window.location.href="dispensing.html";
		 });
    });
    </script>
</head>
<body>
   
<form action="<%=path%>/FeeAction?action=FindFeePage" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">病历号：</td>
        <td><input type="text" name="medicalNo" value="${medicalNo}"/></td>
		
        <td width="10%" class="tableleft">姓名：</td>
        <td><input type="text" name="name" value="${name}"/></td>
    </tr>
    <tr>
		  <td colspan="4">
			<center>
				<button type="submit" class="btn btn-primary">查询</button> 
				<button type="reset" class="btn btn-primary">清空</button> 
			</center>
        </td>
    </tr>
</table>
</form>
<table class="table table-bordered table-hover definewidth m10" >
   <thead>
    <tr>
        <th>病历号</th>
        <th>姓名</th>
        <th>收费项目</th>
        <th>收费金额</th>
        <th>收费日期</th>
        <th>操作</th>
    </tr>
    </thead>
    <c:forEach items="${feeList}" var="fee">
	     <tr>
            <td style="vertical-align:middle;">${fee.medicalNo}</td>
            <td style="vertical-align:middle;">
				<c:forEach items="${regList}" var="reg">
				<c:if test="${reg.medicalNo==fee.medicalNo}">${reg.name}</c:if>
				</c:forEach>
			</td>
            <td style="vertical-align:middle;">
				<c:forEach items="${feeProList}" var="feepr">
				<c:if test="${feepr.id==fee.feeid}">${feepr.feeName} </c:if>
				</c:forEach>
			</td>
            <td style="vertical-align:middle;">
            <c:forEach items="${feeProList}" var="feepr">
				<c:if test="${feepr.id==fee.feeid}">${feepr.amount} </c:if>
				</c:forEach>
				</td>
            <td style="vertical-align:middle;">${fee.feeDate}</td>
            <td style="vertical-align:middle;"><a href="<%=path%>/FeeAction?action=AddProA&gid=${fee.gid}">添加收费项目</a></td>
        </tr>
   </c:forEach>
  </table>
  
  <table class="table table-bordered table-hover definewidth m10" >
  	<tr><th colspan="5">  
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
			<a href='javascript:void(0)' onclick="totalPage('${totalPage}')">尾页</a> <span>共${totalPage}页,${totalCount}条</span>
		 </div>
		 <div><button type="button" class="btn btn-success" id="newNav">添加收费项目</button>
		 
		 </div>
		 
		 </th></tr>
  </table>
  
</body>
</html>

