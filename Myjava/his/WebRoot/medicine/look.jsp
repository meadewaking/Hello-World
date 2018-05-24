<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <title>查看-- -2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=path%>/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/Css/style.css" />
    <script type="text/javascript" src="<%=path%>/Js/jquery.js"></script>
    <script type="text/javascript" src="<%=path%>/Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="<%=path%>/Js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=path%>/Js/ckform.js"></script>
    <script type="text/javascript" src="<%=path%>/Js/common.js"></script>
    <script type="text/javascript" src="<%=path%>/Js/ckeditor/ckeditor.js"></script>
 

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
		$('#backid').click(function(){
				window.location.href="<%=path%>/DrugAction?action=FindDrugPage";
		 });
    });
    </script>
</head>
<body>
<form action="" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">药品编号</td>
        <td>${drug.DID}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">图片</td>
        <td><img src="${drug.drugURL}" width="185px" height="130px"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">进价</td>
        <td>${drug.purchasing_price}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">售价</td>
        <td>${drug.selling_price}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">药品名称</td>
        <td>${drug.drugName}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">药品类型</td>
        <td>${drug.drugType}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">简单描述</td>
        <td>${drug.description}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">生产日期</td>
        <td>${drug.production_date}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">过期日期</td>
        <td>${drug.expiration_date}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">保质期</td>
        <td>${drug.shelf_life}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">详细描述</td>
        <td>${drug.detail}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">生产厂商</td>
        <td>${drug.manufacturer}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">服用说明</td>
        <td>${drug.directions}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">库存</td>
        <td>${drug.countpurchases}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">剩余量</td>
        <td>${drug.inventory}</td>
    </tr>
     <tr>
        <td width="10%" class="tableleft">状态</td>
        <c:if test="${drug.drugflag==1}"><td>销售中</td></c:if>
        <c:if test="${drug.drugflag==0}"><td>停售</td></c:if>
    </tr>
	<tr>
        <td width="10%" class="tableleft">备注</td>
        <td>${drug.drugcomment}</td>
	</tr>
    <tr>
        <td colspan="2">
			<center>
				<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
			</center
		</td>
    </tr>
</table>
</form>
</body>
</html>
