<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <title>角色</title>
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
 	var roleName=document.getElementById("roleName").value;
 		document.forms[0].setAttribute("action","RoleAction?action=FindRolePage&pageNo=1&roleName="+roleName);
 		document.forms[0].submit();
 	}
     //上一页
 	function upPage(pageNo){
 	var roleName=document.getElementById("roleName").value;
 		if(pageNo!=1){//判断当前页数是否不等于1
 		pageNo=parseInt(pageNo)-1;
 		}
 		document.forms[0].setAttribute("action","RoleAction?action=FindRolePage&pageNo="+pageNo+"&roleName="+roleName);
         document.forms[0].submit();
 	}
 	//下一页
 	function nextTotal(pageNo,totalPage){
 	var roleName=document.getElementById("roleName").value;
 		if(pageNo!=totalPage){//判断当前页数是否等于总页数
 		pageNo=parseInt(pageNo)+1;
 		}
 		document.forms[0].setAttribute("action","RoleAction?action=FindRolePage&pageNo="+pageNo+"&roleName="+roleName);
        	document.forms[0].submit();
 	} 	
 	//尾页
 	function totalPage(totalPage){
 	var roleName=document.getElementById("roleName").value;
 		document.forms[0].setAttribute("action","RoleAction?action=FindRolePage&pageNo="+totalPage+"&roleName="+roleName);
         document.forms[0].submit();
 	}	
 	//点击任意一页
 	function anyPage(li){
 	var roleName=document.getElementById("roleName").value;
 	document.forms[0].setAttribute("action","RoleAction?action=FindRolePage&pageNo="+li+"&roleName="+roleName);
     document.forms[0].submit();
 	}	
     //添加
     $(function () {
		$('#newNav').click(function(){
				window.location.href="<%=path%>/RoleAction?action=getRes";
		 });
    });
	//全选
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
	//复选框删除
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
				document.forms[0].setAttribute("action","<%=path%>/RoleAction?action=delChecked&ids="+ids);	
				document.forms[0].submit();
			}
		}else{
			alert("请选中要删除的项");
		}
	}
	//按钮删除
	function del(roleId){
			if (confirm("确认删除？")) {
			document.forms[0].setAttribute("action","<%=path%>/RoleAction?action=delRole&roleId="+roleId);	
			document.forms[0].submit();
			}
	}
	//当前页码高亮
	$(document).ready(function(){$("a[onclick='anyPage("+${pageNo}+")']").addClass("current");});
    </script>   
    
</head>
<body>
<form class="form-inline definewidth m20" action="<%=path%>/RoleAction?action=FindRolePage" method="post">  
    角色名称：
    <input type="text" name="roleName" id="roleName" class="abc input-default" placeholder="" value="">&nbsp;&nbsp;  
    <button type="submit" class="btn btn-primary">查询</button>
</form>
<table class="table table-bordered table-hover definewidth m10" >
    <thead>
    <tr>
    	<th width="5%"><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>角色名称</th>
        <th>状态</th>
        <th width="10%">操作</th>
    </tr>
    </thead>
    <c:forEach items="${roleList}" var="role">
	     <tr>
         	<td style="vertical-align:middle;"><input type="checkbox" name="check" value="${role.roleId}"></td>
            <td>${role.roleName}</td>
           <td>
            	<c:if test="${role.roleStatus==0}">禁用</c:if>
				<c:if test="${role.roleStatus==1}">启用</c:if>
            </td>
            <td>
            <a href="<%=path%>/RoleAction?action=findRoleById&roleId=${role.roleId}">编辑</a>&nbsp;&nbsp;&nbsp;
            <a href="javascript:void(0)" onclick="del(${role.roleId})">删除</a>
            </td>
        </tr>
    </c:forEach>
	</table>
        
   <table class="table table-bordered table-hover definewidth m10" >
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
			<a href='javascript:void(0)' onclick="totalPage('${totalPage}')">尾页</a> <span>共${totalPage}页,${totalCount}条</span>
		</div>
	    <div>
	   		<button type="button" class="btn btn-success" id="newNav">添加角色 </button>&nbsp;&nbsp;&nbsp;
	  	 	<button type="button" class="btn btn-success" id="delPro" onClick="delAll();">删除选中</button>
	   	</div>
	   	</th>
   	</tr>
  </table>
 </body>
</html>
