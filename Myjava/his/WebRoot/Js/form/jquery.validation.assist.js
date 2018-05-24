function jQueryDate(theStr) {
	if (theStr == "")
		return true;
	if (theStr.length != 8) {
		return (false);
	} else {
		var y = theStr.substring(0, 4);
		var m = theStr.substring(4, 6);
		var d = theStr.substring(6);
		var maxDays = 31;
		if (isInt(m) == false || isInt(d) == false || isInt(y) == false) {
			return (false);
		} else if (y.length < 4) {
			return (false);
		}

		// sql��ݿ������ʱ�����С��1753�꣬�ᱨ��
		// ������Ƶ�ʱ����1900���
		else if (y < 1900) {
			return (false);
		}

		else if (!isBetween(m, 1, 12)) {
			return (false);
		} else if (m == 4 || m == 6 || m == 9 || m == 11)
			maxDays = 30;
		else if (m == 2) {
			if (y % 4 > 0)
				maxDays = 28;
			else if (y % 100 == 0 && y % 400 > 0)
				maxDays = 28;
			else
				maxDays = 29;
		}
		if (isBetween(d, 1, maxDays) == false) {
			return (false);
		} else {
			return (true);
		}

	}
}

// ����һ������
function jQueryDateThan(value, element, param) {
	return value > $(param).val();
}
// ��Ҫͬʱ�������ͬʱ��������
function jQueryTwinInput(value, element, param) {
	var flag = true;
	if (value == "") {
		for (var i = 0; i < param.length; i++) {
			if ($("#" + param[i]).val() != "") {
				flag = false;
				break;
			}
		}
		return flag;
	} else {
		return flag;
	}
}
// ��������������
function jQueryNOZHCN(value, element, param) {
	var reg = /[\u4e00-\u9fa5](\s*[\u4e00-\u9fa5])*/;
	return !(reg.test(value));
}
// ֻ������������
function jQueryZHCN(value, element, param) {
	var reg = /^[\u4e00-\u9fa5](\s*[\u4e00-\u9fa5])*$/;
	return reg.test(value);
}

// С�ڲ�������
function jQueryMinNoEquals(value, element, param) {
	return value > param;
}
// ���ڲ�������
function jQueryMaxNoEquals(value, element, param) {
	return value < param;
}
//С��λ������λУ��
function jQueryDouble(value, element, param) {
	if(value==""){
		return true;
	}
	var flag = /^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(value);
	if (flag) {
		var a = value.split(".");
		if (a[0].length > param[0]) {
			flag = false;
		}
		if (typeof(a[1]) != "undefined" && a[1].length > param[1]) {
			flag = false;
		}
	}
	
	return flag;
}