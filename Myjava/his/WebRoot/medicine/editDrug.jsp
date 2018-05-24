<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <title>更新药品---2015</title>
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
				"drugURL" : {
					required : true,
					maxlength : 256
				},
				"purchasing_price" : {
					required : true,
					number : true,
					maxlength : 20
				},
				"selling_price" : {
					required : true,
					number : true,
					maxlength : 20
				},
				"drugName" : {
					required : true,
					maxlength : 20
				},
				"description" : {
					required : true,
					maxlength : 100
				},
				"production_date" : {
					required : true,
					date : true,
					maxlength : 10
				},
				"expiration_date" : {
					required : true,
					date : true,
					maxlength : 10
				},
				"shelf_life" : {
					required : true,
					digits : true,
					maxlength : 10
				},
				"detail" : {
					required : true,
					maxlength : 300
				},
				"manufacturer" : {
					required : true,
					maxlength : 30
				},
				"directions" : {
					required : true,
					maxlength : 50
				},
				"countpurchases" : {
					required : true,
					number : true,
					maxlength : 30
				},
				"inventory" : {
					required : true,
					number : true,
					maxlength : 30
				},
				"drugcomment" : {
					required : true,
					maxlength : 300
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
				window.location.href="<%=path%>/DrugAction?action=FindDrugPage";
		 });
    });
    function sel(){
		$("#type input[value='"+${drug.drugType}+"']").attr("checked",true);
		$("#flag input[value='"+${drug.drugflag}+"']").attr("checked",true);
	}
    </script>
</head>
<body onload="sel();">
<form id="frm1" action="<%=path%>/DrugAction?action=UpdateU" method="post" enctype="multipart/form-data" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">药品编号</td>
        <td><input type="hidden" name="DID" value="${drug.DID}"></input>${drug.DID}</td>
        
    </tr>
    <tr>
        <td width="10%" class="tableleft">图片</td>
        <td><input type="file" name="drugURL" value="${drug.drugURL}"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">进价</td>
        <td><input type="text" name="purchasing_price" value="${drug.purchasing_price}"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">售价</td>
        <td><input type="text" name="selling_price" value="${drug.selling_price}"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">药品名称</td>
        <td><input type="text" name="drugName" value="${drug.drugName}"/></td>
    </tr>
    <tr id="type">
        <td width="10%" class="tableleft">药品类型</td>
        <td><input type="radio" name="drugType" value="1" checked/>中药&nbsp;&nbsp;&nbsp;<input type="radio" name="drugType" value="2"/>西药&nbsp;&nbsp;&nbsp;<input type="radio" name="drugType" value="3"/>处方药</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">简单描述</td>
        <td><input type="text" name="description" value="${drug.description}"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">生产日期</td>
        <td><input type="text" id="production_date" name="production_date" class="Wdate" onfocus="WdatePicker({el:$dp.$('production_date')})" value="${drug.production_date}"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">过期日期</td>
        <td><input type="text" id="expiration_date" name="expiration_date" value="${drug.expiration_date}" class="Wdate" onfocus="WdatePicker({el:$dp.$('expiration_date')})"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">保质期</td>
        <td><input type="text" name="shelf_life" value="${drug.shelf_life}"/>天</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">详细描述</td>
        <td><textarea name="detail" value="${drug.detail}"></textarea></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">生产厂商</td>
        <td><textarea name="manufacturer" value="${drug.manufacturer}"></textarea></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">服用说明</td>
        <td><input type="text" name="directions" value="${drug.directions}"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">库存</td>
        <td><input type="text" name="countpurchases" value="${drug.countpurchases}"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">剩余量</td>
        <td><input type="text" name="inventory" value="${drug.inventory}"/></td>
    </tr>
    <tr id="flag">
        <td width="10%" class="tableleft">状态</td>
        <td><input type="radio" name="drugflag" value="1" checked/>销售中&nbsp;&nbsp;&nbsp;<input type="radio" name="drugflag" value="0"/>停售</td>
    </tr>
	<tr>
        <td width="10%" class="tableleft">备注</td>
        <td><textarea name="drugcomment" value="${drug.drugcomment}"></textarea></td>
	</tr>
    <tr>
        <td colspan="2">
			<center>
				<button type="submit" class="btn btn-primary">保存</button> &nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>
