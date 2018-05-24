/*jQuery扩展验证*/
	jQuery.validator.addMethod("telephone", function(valphone, element) {
		var tel = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
		return this.optional(element) || (tel.test(valphone));
		}, "格式错误");	
	jQuery.validator.addMethod("zipCode", function(valcode, element) {
		var tel = /^[0-9]{6}$/;
		return this.optional(element) || (tel.test(valcode));
		}, "格式错误");
	jQuery.validator.addMethod("mobilePhone", function(valmobile,element) {
		var tel0 = /^13\d{9}$/;
	   var tel1 = /^15\d{9}$/;
	   var tel2 = /^18\d{9}$/;
	   return this.optional(element) || (tel0.test(valmobile)) || (tel1.test(valmobile)) || (tel2.test(valmobile));
		}, "格式错误");
	jQuery.validator.addMethod("fax", function(valfax, element) {
		var tel = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
		return this.optional(element) || (tel.test(valfax));
		}, "格式错误");	