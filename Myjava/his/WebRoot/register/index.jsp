<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <title>门诊查询-- -2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=path%>/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/Css/style.css" />
    <script type="text/javascript" src="<%=path%>/Js/jquery.js"></script>
    <script type="text/javascript" src="<%=path%>/Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="<%=path%>/Js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=path%>/Js/ckform.js"></script>
    <script type="text/javascript" src="<%=path%>/Js/common.js"></script>
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
    //首页	
	function toptotal(){
		document.forms[0].setAttribute("action","RegisterAction?action=FindRegPage&pageNo=1");
		document.forms[0].submit();
	}
    //上一页
	function upPage(pageNo){
		if(pageNo!=1){//判断当前页数是否不等于1
		pageNo=parseInt(pageNo)-1;
		}
		document.forms[0].setAttribute("action","RegisterAction?action=FindRegPage&pageNo="+pageNo);
        document.forms[0].submit();
	}
	//下一页
	function nextTotal(pageNo,totalPage){
		if(pageNo!=totalPage){//判断当前页数是否等于总页数
		pageNo=parseInt(pageNo)+1;
		}
		document.forms[0].setAttribute("action","RegisterAction?action=FindRegPage&pageNo="+pageNo);
       	document.forms[0].submit();
	} 	
	//尾页
	function totalPage(totalPage){
		document.forms[0].setAttribute("action","RegisterAction?action=FindRegPage&pageNo="+totalPage);
        document.forms[0].submit();
	}	
	//点击任意一页
	function anyPage(li){
	document.forms[0].setAttribute("action","RegisterAction?action=FindRegPage&pageNo="+li);
    document.forms[0].submit();
	}	

	 $(function () {
		$('#newNav').click(function(){
				window.location.href="<%=path%>/RegisterAction?action=AddRegA";
		 });
		
		$('#exportExcel').click(function(){
			var file_name = window.prompt("请指定输出文件名称(.xls)", "C:/register.xls");
			$.ajax({
				type : "POST",
				url : "<%=path%>/RegisterAction?action=ExportExcel",
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
	$('#cls').click(function(){
			window.location.href="<%=path%>/RegisterAction?action=FindRegPage";
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
					$.ajax({
						type : "POST",
						url : "<%=path%>/RegisterAction?action=exiChecked",
			 			data : "ids="+ids,
						success : function(msg) {
							if (msg==0) {
								alert("退号成功!")
								window.location.href="<%=path%>/RegisterAction?action=FindRegPage";
							}else{
								alert("退号失败!");
							}
						}
					});  
				}
			}else{
				alert("请选中要操作的项");
			}
		}
		function exi(medicalNo,regtime){
			var str=regtime;
			str = str.replace(/-/g,"/");
			var date = new Date(str);
			var rtime=date.getTime();
			var ctime=new Date;
			var curtime=ctime.getTime();
			if ((curtime-rtime)>3600000) {
				alert("退号成功!")
				document.forms[0].setAttribute("action","RegisterAction?action=UpdateC&medi="+medicalNo);
				document.forms[0].submit();
			}else{
				alert("挂号一小时内不能退号!");
			}
		}
		
		function sel(){
			$("#depid option[value="+${depId}+"]").attr("selected",true);
		}
		function judge(){
			var st=$("#stime").val();
			var ft=$("#ftime").val();
			var date1 = new Date(st.replace(/-/g,"/"));
			var date2 = new Date(ft.replace(/-/g,"/"));
			var d1=date1.getTime();
			var d2=date2.getTime();
			if (d2>d1||(isNaN(d1)&&isNaN(d2))) {
				document.forms[0].setAttribute("action","RegisterAction?action=FindRegPage");
				document.forms[0].submit();
			}else{
				alert("终止时间应大于起始时间!")
			}
		}
		
		//当前页码高亮
		$(document).ready(function(){$("a[onclick='anyPage("+${pageNo}+")']").addClass("current");});
    </script>
    
    
</head>
<body onload="sel();">

<form action="<%=path%>/RegisterAction?action=FindRegPage" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">病历号：</td>
        <td><input type="text" id="medicalno" name="medicalNo" value="${medicalNo}"/></td>
		
        <td width="10%" class="tableleft">主治医生：</td>
        <td><input type="text" id="docname" name="docName" value="${docName}"/></td>
		
        <td width="10%" class="tableleft">科室：</td>
        <td><select name="depId" id="depid">
        <option value="0">所有科室</option>
        <c:forEach items="${depList}" var="dep">
        <option value="${dep.id}">${dep.depName}</option>
        </c:forEach>
        </select></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">挂号时间：</td>
		  <td colspan="5">
		  <input name="stime" id="stime" type="text" value="${stime}" class="Wdate" onfocus="WdatePicker({el:$dp.$('stime')})"/>
			&nbsp;至&nbsp;
			<input name="ftime" id="ftime" type="text" value="${ftime}" class="Wdate" onfocus="WdatePicker({el:$dp.$('ftime')})"/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <button type="button" class="btn btn-primary" onclick="judge();">查询</button> 
          	<button type="reset" class="btn btn-primary" id="cls">清空</button>
        </td>
    </tr>
</table>
</form>
   
<table class="table table-bordered table-hover definewidth m10" >
   <thead>
    <tr>
    	<th><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>病历号</th>
        <th>主治医生</th>
        <th>挂号时间</th>
        <th>挂号科室</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <c:forEach items="${regList}" var="reg">
	     <tr>
         	<td style="vertical-align:middle;"><input type="checkbox" name="check" value="${reg.medicalNo}"></td>
            <td style="vertical-align:middle;">${reg.medicalNo}</td>
            <td style="vertical-align:middle;">
	            <c:forEach items="${docList}" var="doc">
	            <c:if test="${doc.id==reg.docid}">${doc.name}</c:if>
	            </c:forEach>
            </td>
            <td style="vertical-align:middle;">${reg.regtime}</td>
            <td style="vertical-align:middle;">
            	<c:forEach items="${depList}" var="dep">
	            <c:if test="${dep.id==reg.depid}">${dep.depName}</c:if>
	            </c:forEach>
            </td>
            <td style="vertical-align:middle;">
	            <c:if test="${reg.flag==1}">已挂号</c:if>
	            <c:if test="${reg.flag==2}">已询医</c:if>
	            <c:if test="${reg.flag==3}">已出院</c:if>
	            <c:if test="${reg.flag==4}">已退号</c:if>
	            <c:if test="${reg.flag==5}">已住院</c:if>
	            <c:if test="${reg.flag==6}">已退院</c:if>
	            <c:if test="${reg.flag==7}">已结算</c:if>
            </td>
            <td style="vertical-align:middle;">
            <a href="<%=path%>/RegisterAction?action=Look&mNo=${reg.medicalNo}">详情>>></a>&nbsp;&nbsp;&nbsp;
            <a href="<%=path%>/RegisterAction?action=UpdateA&medicalNo=${reg.medicalNo}">更改</a>&nbsp;&nbsp;&nbsp;
            <a href="javascript:void(0)" onclick="exi(${reg.medicalNo},'${reg.regtime}');">退号</a></td>
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
			<a href="javascript:void(0)" onclick="totalPage('${totalPage}')">尾页</a> <span>共${totalPage}页,${totalCount}条</span>
	</div>
		 <div><button type="button" class="btn btn-success" id="newNav">门诊挂号</button>&nbsp;&nbsp;&nbsp;
		 <button type="button" class="btn btn-success" id="delPro" onClick="delAll();">退号</button>&nbsp;&nbsp;&nbsp;
		 <button type="button" class="btn btn-success" id="exportExcel">导出Excel</button>
		 <button type="button" class="btn btn-success" id="delP" onclick="javascript:window.print()">打印</button>
		 </div>
		 </th></tr>
  </table>
  
</body>
</html>

