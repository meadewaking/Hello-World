<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
 	var resName=document.getElementById("resName").value;
 		document.forms[0].setAttribute("action","ResourcesAction?action=FindResPage&pageNo=1&resName="+resName);
 		document.forms[0].submit();
 	}
     //上一页
 	function upPage(pageNo){
 	var resName=document.getElementById("resName").value;
 		if(pageNo!=1){//判断当前页数是否不等于1
 		pageNo=parseInt(pageNo)-1;
 		}
 		document.forms[0].setAttribute("action","ResourcesAction?action=FindResPage&pageNo="+pageNo+"&resName="+resName);
        document.forms[0].submit();
 	}
 	//下一页
 	function nextTotal(pageNo,totalPage){
 	var resName=document.getElementById("resName").value;
 		if(pageNo!=totalPage){//判断当前页数是否等于总页数
 		pageNo=parseInt(pageNo)+1;
 		}
 		document.forms[0].setAttribute("action","ResourcesAction?action=FindResPage&pageNo="+pageNo+"&resName="+resName);
        document.forms[0].submit();
 	} 	
 	//尾页
 	function totalPage(totalPage){
 	var resName=document.getElementById("resName").value;
 		document.forms[0].setAttribute("action","ResourcesAction?action=FindResPage&pageNo="+totalPage+"&resName="+resName);
        document.forms[0].submit();
 	}	
 	//点击任意一页
 	function anyPage(li){
 	var resName=document.getElementById("resName").value;
 		document.forms[0].setAttribute("action","ResourcesAction?action=FindResPage&pageNo="+li+"&resName="+resName);
   		document.forms[0].submit();
 	}	
 	
     $(function () {
		$('#newNav').click(function(){
				window.location.href="Resource/add.jsp";
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
			if(confirm("确认删除?")){
				alert("删除成功!");
				document.forms[0].setAttribute("action",'ResourcesAction?action=DelChecked&ids='+ids);	
				document.forms[0].submit();
			}
		}else{
			alert("请选中要删除的项");
		}
	}
	//按钮删除
	function del(resId){
		if (confirm("确认删除？")) {
		document.forms[0].setAttribute("action","<%=path%>/ResourcesAction?action=DelRes&resId="+resId);	
		document.forms[0].submit();
		}
	}
	//当前页码高亮
	$(document).ready(function(){$("a[onclick='anyPage("+${pageNo}+")']").addClass("current");});
    </script>
</head>
<body>
<form class="form-inline definewidth m20" action="<%=path%>/ResourcesAction?action=FindResPage" method="post">    
    资源(菜单)名称：
    <input type="text" name="resName" id="resName" class="abc input-default" placeholder="" value="${resName}">&nbsp;&nbsp;  
    <button type="submit" class="btn btn-primary">查询</button>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
    	<th width="5%"><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>资源名称</th>
        <th>路径Url</th>
        <th>是否有效</th>
        <th width="10%">操作</th>
    </tr>
    </thead>
    <c:forEach items="${resList}" var="res">
	     <tr>
         	<td style="vertical-align:middle;"><input type="checkbox" name="check" value="${res.resId}"></td>
            <td>${res.resName}</td>
            <td><a href="${res.resURL}" target="_blank">${res.resURL}</a></td>
            <td>
			<c:if test="${res.status==1}">启用</c:if>
			<c:if test="${res.status==0}">禁用</c:if>
			</td>
            <td>
                <a href="<%=path%>/ResourcesAction?action=FindResById&resId=${res.resId}">编辑</a>&nbsp;&nbsp;&nbsp;
                <a href="javascript:void(0)" onclick="del(${res.resId})">删除</a>            
            </td>
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
		<a href='#' onclick="totalPage('${totalPage}')">尾页</a> <span>共${totalPage}页,${totalCount}条</span>
    </div><div>
    <button type="button" class="btn btn-success" id="newNav">添加资源</button>&nbsp;&nbsp;&nbsp;
    <button type="button" class="btn btn-success" id="delPro" onClick="delAll();">删除选中</button>
    </div>
    </th></tr>
  </table>
</body>
</html>