/*
Author zhubo
一个模拟html5 range控件的类。最大特点是可以定义控件方向和外观。
*/

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
function ScapeRange(setting) {
	var rangeContext = this;
	setting.thick = setting.thick ? setting.thick : "5px";
	setting.lighthick = setting.lighthick ? setting.lighthick : "9px";
	if (!setting || !setting.target || !setting.target.appendChild) {
		throw "wrong setting!";
	}
	this.setting = setting;
	this.direction = this.setting.direction;
	this.range = document.createElement('div');
	this.range.className = 'scape-range';
	this.played = document.createElement('div');
	this.played.className = 'scape-range-played';
	this.dragbar = document.createElement('div');
	this.dragbar.className = 'scape-range-dragbar';
	this.light = document.createElement('div');
	this.light.className = 'scape-range-light';
	this.range.appendChild(this.played);
	this.played.appendChild(this.dragbar);
	this.dragbar.appendChild(this.light);
	this.setting.target.appendChild(this.range);
	this.range.context = this.played.context = this.dragbar.context = this.light.context = this;
	
	var relate = {
		"rangeClass":this.range,
		"playedClass":this.played,
		"dragbarClass":this.dragbar,
		"lightClass":this.light
	};
	for(var i in relate){
		if(setting[i]){
			addClass(relate[i],setting[i]);
		}
	}
	this.dragbar.style.width = this.dragbar.style.height = setting.thick;
	this.light.style.width = this.light.style.height = setting.lighthick;
	this.light.style.margin = (parseInt(setting.thick) - parseInt(setting.lighthick)) / 2 + "px";

	if (this.direction == "rl") {
		this.range.style.height = setting.thick;
		this.range.style.width = setting.length ? (setting.length) : '100%';
		this.played.style.height = setting.thick;
		this.played.style.right = "0px";
		this.played.style.left = "auto";
		this.dragbar.style.left = "0px";
	} else if (this.direction == "tb") {
		this.range.style.width = setting.thick;
		this.range.style.height = setting.length ? (setting.length) : '100%';
		this.played.style.width = setting.thick;
		this.played.style.top = "0px";
		this.played.style.bottom = "auto";
		this.dragbar.style.bottom = "0px";

	} else if (this.direction == "bt") {
		this.range.style.width = setting.thick;
		this.range.style.height = setting.length ? (setting.length) : '100%';
		this.played.style.width = setting.thick;
		this.played.style.bottom = "0px";
		this.played.style.top = "auto";
		this.dragbar.style.top = "0px";
	} else {
		this.range.style.height = setting.thick;
		this.range.style.width = setting.length ? (setting.length) : '100%';
		this.played.style.height = setting.thick;
		this.played.style.left = "0px";
		this.played.style.right = "auto";
		this.dragbar.style.right = "0px";
	}
	this.set = function(percent, doChange) {
		this.scale = percent;
		var direction = this.direction;
		var maxLength, minLength;
		if (!direction || direction == 'lr' || direction == 'rl') {
			maxLength = this.range.clientWidth;
			minLength = this.range.clientHeight;
		} else {
			maxLength = this.range.clientHeight;
			minLength = this.range.clientWidth;
		}
		this.range.maxLength = maxLength;
		this.range.minLength = minLength;
		var pct;
		if(percent>1){
			pct=1;
		}else if(percent<0){
			pct=0;
		}else{
			pct=percent;
		}
		var length = parseInt(pct*(maxLength-minLength)+minLength);
		if (!direction || direction == 'lr' || direction == 'rl') {
			this.played.style.width = length + "px";
		} else {
			this.played.style.height = length + "px";
		}
		if (this._change_ && doChange != false) {
			this._change_(pct);
		}
	}
	this.onChange = function(c) {
		this._change_ = c;
	}
	this.range.onclick = function(e) {
		var context = this.context;
		var range = this;
		var setPct;
		var target = e.target;
		if (context.direction == "rl") {//
			var rOffsetX;
			if(e.target!=range){
				rOffsetX = range.maxLength - target.clientWidth + e.offsetX;
			}else{
				rOffsetX = e.offsetX;
			}
			setPct = 1 - (rOffsetX - range.minLength) / range.maxLength;
		} else if (context.direction == "tb") {//
			setPct = (e.offsetY + range.minLength) / range.maxLength;
		} else if (context.direction == "bt") {
			var rOffsetY;
			if(e.target!=range){
				rOffsetY = range.maxLength - target.clientHeight + e.offsetY;
			}else{
				rOffsetY = e.offsetY;
			}
			setPct = 1 - (rOffsetY - range.minLength) / range.maxLength;
		} else {
			setPct = (e.offsetX + range.minLength) / range.maxLength;
		}
		context.set(setPct);
	}
	this.dragbar.onclick = function(e) {
		e.stopPropagation();
		e.preventDefault();
	}
	this.dragbar.onmousedown = function(e) {
		e.stopPropagation();
		e.preventDefault();
		this.oleft = e.screenX;
		this.ow = this.parentNode.clientWidth;
		this.otop = e.screenY;
		this.oh = this.parentNode.clientHeight;
		window.addEventListener("mousemove", _drag_, false);
		document.body.style.cursor = "pointer";
	}
	var _drag_ = function(e) {
		e.preventDefault();
		var setPct;
		if(rangeContext.direction == "rl"){
			setPct = (rangeContext.dragbar.ow + rangeContext.dragbar.oleft - e.screenX) / rangeContext.range.clientWidth;
		}else if(rangeContext.direction == "tb"){
			setPct = (rangeContext.dragbar.oh - rangeContext.dragbar.otop + e.screenY) / rangeContext.range.clientHeight;
		}else if(rangeContext.direction == "bt"){
			setPct = (rangeContext.dragbar.oh + rangeContext.dragbar.otop - e.screenY) / rangeContext.range.clientHeight;
		}else{
			setPct = (rangeContext.dragbar.ow - rangeContext.dragbar.oleft + e.screenX) / rangeContext.range.clientWidth;
		}
		rangeContext.set(setPct);

	}
	window.addEventListener("mouseup", function(e) {
		window.removeEventListener("mousemove", _drag_, false);
		document.body.style.cursor = "auto";
	}, false);
}
