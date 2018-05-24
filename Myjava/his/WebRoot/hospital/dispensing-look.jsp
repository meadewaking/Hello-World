<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <title>发药详情-- -2015</title>
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
	 $(function () {
		$('#newNav').click(function(){
				window.location.href="add.html";
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
				window.location.href="<%=path%>/DispAction?action=FindDispPage";
		 });
    });
    </script>
    <script type="text/javascript">
    function getTableContent(node,n){
    	var tg=node.parentNode.parentNode;
    	var nodisp=tg.cells[5].innerText;
    	var aldisp=tg.cells[4].innerText;
    	var drugid=tg.cells[2].id
    	var mno=tg.cells[0].innerText;
    	if (n>nodisp) {
    		alert("要发药品数量大于了未发药品,请确认后重新操作");
		}else{
			$.ajax({
				type : "POST",
				url : "<%=path%>/DispAction?action=DrugAldispCount&n="+n+"&mno="+mno,
	 			data : "drid="+drugid,
				success : function(msg) {
					if (msg==-1) {
						tg.cells[5].innerText=parseInt(nodisp)-n;
						tg.cells[4].innerText=parseInt(aldisp)+n;
					}else{
						alert("药品余量不足!药品剩余:"+msg);
					}
				}
			}); 
		}
    }
    </script>
</head>
<body>
   
<table class="table table-bordered table-hover definewidth m10" >
   <thead>
    <tr>
        <th>病历号</th>
        <th>姓名</th>
        <th>药品名称</th>
        <th>药品数量</th>
        <th>已发数量</th>
        <th>未发数量</th>
        <th>操作</th>
    </tr>
    </thead>
    <c:forEach items="${dispList}" var="disp">
	     <tr>
            <td style="vertical-align:middle;">${medicalNo}</td>
            <td style="vertical-align:middle;">${name}</td>
            <td style="vertical-align:middle;" id="${disp.drugId}">
				<c:forEach items="${drugList}" var="drug">
				<c:if test="${disp.drugId==drug.DID}">${drug.drugName}</c:if>
				</c:forEach>
			</td>
            <td style="vertical-align:middle;">${disp.dispCount}</td>
            <td style="vertical-align:middle;" id="${disp.drugId}">${disp.aldispCount}</td>
            <td style="vertical-align:middle;" id="${disp.drugId}zzz">${disp.nodispCount}</td>
            <td style="vertical-align:middle;">
	            <a href="javascript:void(0)" onclick="getTableContent(this,0)">发全</a>&nbsp;&nbsp;&nbsp;
	            <a href="javascript:void(0)" onclick="getTableContent(this,1)">发1</a>&nbsp;&nbsp;&nbsp;
	            <a href="javascript:void(0)" onclick="getTableContent(this,5)">发5</a>&nbsp;&nbsp;&nbsp;
	            <a href="javascript:void(0)" onclick="getTableContent(this,30)">发30</a>
            </td>
        </tr>
   </c:forEach>
  </table>
  
  <table class="table table-bordered table-hover definewidth m10" >
  	<tr><th colspan="5">  
	  		  <!-- <div class="inline pull-right page">
	          <a href='#' >第一页</a> <a href='#'>上一页</a>     <span class='current'>1</span><a href='#'>2</a><a href='/chinapost/index.php?m=Label&a=index&p=3'>3</a><a href='#'>4</a><a href='#'>5</a>  <a href='#' >下一页</a> <a href='#' >最后一页</a>
			  &nbsp;&nbsp;&nbsp;共<span class='current'>32</span>条记录<span class='current'> 1/33 </span>页
			  </div> -->
		 <div>
				<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
		 </div>
		 
		 </th></tr>
  </table>
  
</body>
</html>

