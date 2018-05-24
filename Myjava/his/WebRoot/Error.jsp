<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Error</title>

<link type="text/css" rel="stylesheet" href="Css/animate.min.css" />
<link type="text/css" rel="stylesheet" href="Css/erstyle.css" />

</head>
<body>

<header class="site__header island">
  <div class="wrap">
   <span id="animationSandbox" style="display: block;"><h1 class="site__title mega">${meesg}</h1></span>
   <span class="beta subhead"><a href="http://localhost:8080/his">${retu}</a></span>
  </div>
</header><!-- /.site__header -->

<main class="site__content island" role="content">
  <div class="wrap">
	
  </div>
</main><!-- /.site__content -->

<script type="text/javascript" src="Js/jquery.js"></script>
<script type="text/javascript">
  function testAnim(x) {
    $('#animationSandbox').removeClass().addClass(x + ' animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
      $(this).removeClass();
    });
  };

  $(document).ready(function(){
    $('.js--triggerAnimation').click(function(e){
      e.preventDefault();
      var anim = "wobble";
      testAnim(anim);
    });

    $('.js--animations').change(function(){
      var anim = $(this).val();
      testAnim(anim);
    });
  });

</script>

<div style="text-align:center;clear:both;">
<script src="/gg_bd_ad_720x90-2.js" type="text/javascript"></script>
<script src="/follow.js" type="text/javascript"></script>
</div>
</body>
</html>

