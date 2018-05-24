$(function(){
	$("#range").change(function(){
		var p = $(this).val();
		p = p/5;
		$(".alpha").css("opacity",p);
	})
})