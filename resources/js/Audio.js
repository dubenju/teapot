/*
Author zhubo
为播放本地html5写的一个类，但是后来我发现它没什么用。
*/
// JavaScript Document
function AudioPlayer() {
	this.element = new Audio();
	this.sources = new Array();
	//this.currentSource
	//this.currentURL = "";
	this.type = 0;
	//_interval_

}
/*
type = 0;//循环播放
type = 1;//单曲
type = 2;//顺序
type = 3;//随机
*/
AudioPlayer.prototype.onProgress = function(progress) {
	clearInterval(this._interval_);
	this._interval_ = setInterval(progress, 30);
}
AudioPlayer.prototype.canPlay = function(type){
	return this.element.canPlayType(type);
}
AudioPlayer.prototype.play = function(time) {
	time = Number(time);
	if (this.currentSource) {
		this.element.play();
	} else if (this.sources.length) {
		this.setSource(this.sources[0]);
	}
	if (!isNaN(time)) {
		this.time(time);
	}
}
AudioPlayer.prototype.time = function() {
	if (arguments.length == 0) {
		return this.element.currentTime;
	} else {
		time = Number(arguments[0]);
		if (!isNaN(time)) {
			this.element.currentTime = time;
		} else {
			throw "time is not a number";
		}
	}
}
AudioPlayer.prototype.pause = function() {
	this.element.pause();
}
AudioPlayer.prototype.length = function() {
	return this.element.duration;
}
AudioPlayer.prototype.volume = function(vol) {
	this.element.volume = vol;
}
AudioPlayer.prototype.setSource = function(source) {
	try {
		this.time(0);
	} catch (e) {}
	if (source) {
		var _this = this;
		this.currentSource = source;
		source.toDataURL(function(data) {
			_this.element.src = data;
			_this.play();
		});
		if (this._set_) {
			this._set_();
		}
	}
}
AudioPlayer.prototype.index = function() {
	for (i = 0; i < this.sources.length; i++) {
		if (this.currentSource == this.sources[i]) {
			return i;
		}
	}
}

AudioPlayer.prototype.ended = function() {
	return this.element.ended;
}
AudioPlayer.prototype.paused = function() {
	return this.element.paused;
}
AudioPlayer.prototype.next = function(byType) {
	//如果byType == true或者不赋值，则按照this.type的值所规定的方式播放
	//如果byType===false,则无视this.type,循环顺序播放
	var c = this.index();
	var scs = this.sources;
	if (byType === false) {
		this.setSource(scs[(c + 1) % scs.length]);
	} else {
		if (this.type == 0) { //循环
			this.setSource(scs[(c + 1) % scs.length]);
		} else if (this.type == 1) { //单曲
			this.play(0);
		} else if (this.type == 2) { //顺序

		} else if (this.type == 3) { //随机

		}
	}
}
AudioPlayer.prototype.last = function() {
	var c = this.index();
	var scs = this.sources;
	this.setSource(scs[(c + scs.length - 1) % scs.length]);
}
AudioPlayer.prototype.setUrl = function(url) {
	this.element.src = url;
	this.init = 1;
}
AudioPlayer.prototype.getUrl = function(url) {
	return this.element.src;
}
AudioPlayer.prototype.add = function(file) {
	var thisFile = file;
	for (i = 0; i < this.sources.length; i++) {
		if (thisFile.lastModifiedDate == file.lastModifiedDate && thisFile.size == file.size) {
			return false;
		}
	}
	var newSource = new MediaSource(file);
	this.sources.push(newSource);
	return newSource;
}
AudioPlayer.prototype.remove = function(source) {

}
AudioPlayer.prototype.onPause = function(c) {
	this.element.addEventListener("pause", this._pause_, false);
	this._pause_ = c;
	this.element.addEventListener("pause", this._pause_, false);
}
AudioPlayer.prototype.onPlay = function(c) {
	this.element.addEventListener("play", this._play_, false);
	this._play_ = c;
	this.element.addEventListener("play", this._play_, false);
}
AudioPlayer.prototype.onSet = function(c) {
	this._set_ = c;
}
//Class MediaSource

function MediaSource(file) {
	this.file = file;
	this.title = "";
	this.author = "";
}
MediaSource.prototype.reader = new FileReader();
MediaSource.prototype.toDataURL = function(callback) {
	this.reader.readAsDataURL(this.file);
	this.reader.onload = function(e) {
		callback(e.target.result);
	}
}
