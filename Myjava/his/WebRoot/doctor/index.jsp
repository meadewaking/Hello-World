<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <title>门诊医生---2015</title>
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
 		document.forms[0].setAttribute("action","DoctorAction?action=FindDoctorPage&pageNo=1");
 		document.forms[0].submit();
 	}
     //上一页
 	function upPage(pageNo){
 		if(pageNo!=1){//判断当前页数是否不等于1
 		pageNo=parseInt(pageNo)-1;
 		}
 		document.forms[0].setAttribute("action","DoctorAction?action=FindDoctorPage&pageNo="+pageNo);
        document.forms[0].submit();
 	}
 	//下一页
 	function nextTotal(pageNo,totalPage){
 		if(pageNo!=totalPage){//判断当前页数是否等于总页数
 		pageNo=parseInt(pageNo)+1;
 		}
 		document.forms[0].setAttribute("action","DoctorAction?action=FindDoctorPage&pageNo="+pageNo);
        document.forms[0].submit();
 	} 	
 	//尾页
 	function totalPage(totalPage){
 		document.forms[0].setAttribute("action","DoctorAction?action=FindDoctorPage&pageNo="+totalPage);
        document.forms[0].submit();
 	}	
 	//点击任意一页
 	function anyPage(li){
 	document.forms[0].setAttribute("action","DoctorAction?action=FindDoctorPage&pageNo="+li);
     document.forms[0].submit();
 	}	
 	//当前页码高亮
	$(document).ready(function(){$("a[onclick='anyPage("+${pageNo}+")']").addClass("current");});
 	
	 $(function () {
		$('#newNav').click(function(){
				window.location.href="<%=path%>/DoctorAction?action=AddDoctorA";
		 });
		$('#cls').click(function(){
			document.getElementById("docid").value="";
			document.getElementById("docname").value="";
			$("#depid option[value='0']").attr("selected",true);
			window.location.href="DoctorAction?action=FindDoctorPage";
		});
		
		$('#exportExcel').click(function(){
			var file_name = window.prompt("请指定输出文件名称(.xls)", "C:/doctor.xls");
			$.ajax({
				type : "POST",
				url : "<%=path%>/DoctorAction?action=ExportExcel",
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
		
		function sel(){
			$("#depid option[value='"+${did}+"']").attr("selected",true);
		}
    </script>
</head>
<body onload="sel();">

<form action="<%=path%>/DoctorAction?action=FindDoctorPage" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">医生编号：</td>
        <td><input type="text" id="docid" name="docid" value="${docid}"/></td>
		
        <td width="10%" class="tableleft">医生姓名：</td>
        <td><input type="text" id="docname" name="name" value="${name}"/></td>
		
        <td width="10%" class="tableleft">科室：</td>
        <td><select name="depid" id="depid">
        		<option value="0">所有科室</option>
            	<c:forEach items="${depList}" var="dep">
        			<option value="${dep.id}">${dep.depName}</option>
            	</c:forEach>
       		</select>
     	</td>
    </tr>
    <tr>
		  <td colspan="6"><center>
            <button type="submit" class="btn btn-primary" type="button">查询</button> 
            <button id="cls" class="btn btn-primary">清空</button> 
			</center>
        </td>
    </tr>
</table>
</form>
   
<table class="table table-bordered table-hover definewidth m10" >
   <thead>
    <tr>
    	<th><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>医生编号</th>
        <th>医生姓名</th>
        <th>入院时间</th>
        <th>所属科室</th>
        <th>操作</th>
    </tr>
    </thead>
    <c:forEach items="${doctorList}" var="doctor">
	     <tr>
         	<td style="vertical-align:middle;"><input type="checkbox" name="check" value="1"></td>
            <td style="vertical-align:middle;">${doctor.id}</td>
            <td style="vertical-align:middle;">${doctor.name}</td>
            <td style="vertical-align:middle;">${doctor.joinTime}</td>
            <td style="vertical-align:middle;">
            <c:forEach items="${depList}" var="dep">
            <c:if test="${dep.id==doctor.depid}">${dep.depName }</c:if>
            </c:forEach>
            </td>
            <td style="vertical-align:middle;"><a href="<%=path%>/DoctorAction?action=Look&id=${doctor.id}">详情>>></a>&nbsp;&nbsp;&nbsp;
            <a href="<%=path%>/DoctorAction?action=FindDoctorById&id=${doctor.id}">更改</a></td>
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
		 <div><button type="button" class="btn btn-success" id="newNav">添加新医生</button>
		 <button type="button" class="btn btn-success" id="exportExcel">导出Excel</button>
		 </div>
		 
		 </th></tr>
  </table>
  
</body>
</html>
