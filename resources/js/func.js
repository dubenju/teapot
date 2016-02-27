function $id(id) {
	return document.getElementById(id);
}

function Num2Str(number) {
	//格式化数字，用于显示时间，如把12:3:2格式化成12:03:02
	return (number > 9 ? String(number) : ("0" + number));
}

function log(x) {
	console.log(x);
}

function hasClass(ele, cls) {
	return ele.className.match(new RegExp('(\\s|^)' + cls + '(\\s|$)'));
}

function addClass(ele, cls) {
	if (!hasClass(ele, cls)) ele.className += " " + cls;
}

function removeClass(ele, cls) {
	if (hasClass(ele, cls)) {
		var reg = new RegExp('(\\s|^)' + cls + '(\\s|$)');
		ele.className = ele.className.replace(reg, ' ');
	}
}