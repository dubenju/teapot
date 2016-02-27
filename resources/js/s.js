var body = document.body;
var player = new AudioPlayer();
var playRange = new ScapeRange({
	target: document.getElementById("range"),
	direction: "lr",
	rangeClass:""
});
playRange.onChange(function(pct) {
	player.time(player.length() * pct);
})
player.onProgress(function() {
	var time = player.time();
	var alltime = player.length();
	var pct = alltime > 0 ? time / alltime : 0;
	playRange.set(pct, false);
	var timeStr = parseInt(time / 60) + ":" + Num2Str(parseInt(time) % 60);
	$id("time").innerHTML = timeStr;
}, 1000);
player.onPlay(function() {
	$id("pause").style.display = "block";
	$id("play").style.display = "none";
	$id("load").style.display = "none";
});
player.onPause(function() {
	$id("pause").style.display = "none";
	$id("play").style.display = "block";
	if (player.ended()) {
		player.next();
	}
});
player.onSet(function() {
	var listId = listIndex(player.currentSource);
	for (i = 0; i < list.length; i++) {
		removeClass(list[i], "active");
	}
	addClass(list[listId], "active");
	$id("load").style.display = "block";
});

$id("play").addEventListener("click", function() {
	player.play();
}, false);
$id("pause").addEventListener("click", function() {
	player.pause();
}, false);
$id("last").addEventListener("click", function() {
	player.last();
}, false);
$id("next").addEventListener("click", function() {
	player.next();
}, false);
var listWrap = $id("list");
var del = $id("delete");
var list = listWrap.children;
function listIndex(source) {
	for (i = 0; i < list.length; i++) {
		if (list[i].source == source) {
			return i;
		}
	}
}
function clearChoosed(item){
	removeClass(item, "choose");
	item.draggable = false;
	item.choosed = false;
}
function setChoosed(item){
	addClass(item, "choose");
	item.draggable = true;
	item.choosed = true;
}
function listDblclick(e){
	player.setSource(e.currentTarget.source);
}
function listClick(e){
	if (!e.ctrlKey) {
		for (i = 0; i < list.length; i++) {
			removeClass(list[i], "choose");
			list[i].draggable = false;
		}
	}
	var thisItem = e.currentTarget;
	if (thisItem.choosed&&e.ctrlKey) {
		clearChoosed(thisItem);
	} else {
		setChoosed(thisItem);
	}
}
function listInsertBefore(arr, item) {
	for (i = 0; i < arr.length; i++) {//重排节点
		if (item) {
			listWrap.insertBefore(arr[i], item);
		} else {
			listWrap.appendChild(arr[i]);
		}
	}
	var sources = new Array();
	for(i=0;i<list.length;i++){//重设player.sources
		sources.push(list[i].source);
	}
	player.sources = sources;
	listEmptyCheck();
}
function listDelete(arr){
	var currentSource = player.currentSource;
	var sourceTarget = list[listIndex(currentSource)];

	for(i=0;i<arr.length;i++){
		if(arr[i] == sourceTarget){
			sourceTarget = sourceTarget.nextSibling;
		}
		listWrap.removeChild(arr[i]);
	}
	if(!sourceTarget){
		if(list.length>0){
			sourceTarget = list[0];
		}else{
			//empty
		}
	}
	if(sourceTarget&&sourceTarget.source!=currentSource){
		player.setSource(sourceTarget.source);
	}
	//重排
	var sources = new Array();
	for(i=0;i<list.length;i++){//重设player.sources
		sources.push(list[i].source);
	}
	player.sources = sources;
	listEmptyCheck();
	arr=[];
}
function deleteChoosed(){
	var choosedArray = new Array();
	for(i=0;i<list.length;i++){
		if(list[i].choosed){
			choosedArray.push(list[i]);
		}
		clearChoosed(list[i]);
	}
	listDelete(choosedArray);
}
function createElemsFromFiles(files){
	var elemArr = new Array();
	for (var i = 0; i < files.length; i++) {
		if(player.canPlay(files[i].type)){
			var _li = document.createElement("li");
			_li.innerHTML = files[i].name;
			_li.source = player.add(files[i]);
			_li.addEventListener("click",listClick,false);
			_li.addEventListener("dblclick",listDblclick,false);
			_li.addEventListener("dragstart",listDragStart,false);
			_li.addEventListener("dragenter",listDragEnter,false);
			_li.addEventListener("dragleave",listDragLeave,false);
			_li.addEventListener("drop",listDrop,false);
			elemArr.push(_li);
		}
	}
	return elemArr;
}
var listEndArea = $id("list-end");
listEndArea.addEventListener("dragenter",listDragEnter,false);
listEndArea.addEventListener("dragleave",listDragLeave,false);
listEndArea.addEventListener("drop",listDrop,false);
function listEmptyCheck(){
	var emptyText = $id('empty-info');
	if(list.length>0){
		emptyText.style.display = "none";
	}else{
		emptyText.style.display = "block";
	}
}
function fileDrop(e) {
	e.stopPropagation();
	e.preventDefault();
	var files = e.dataTransfer.files;
	listInsertBefore(createElemsFromFiles(files));
	if(!player.currentSource){player.play()};
}

function listDragEnter(e) {
	var ct = e.currentTarget;
	addClass(ct, "dragenter");
}

function listDragLeave(e) {
	var ct = e.currentTarget;
	removeClass(ct, "dragenter");
}
window.addEventListener("keydown",function(e){
	if(e.keyCode==46){
		deleteChoosed();
	}
},false);

function listDrop(e) {
	var ct = e.currentTarget;
	removeClass(ct, "dragenter");
	if(ct == listEndArea){
		ct=null;
	}
	if (e.dataTransfer.getData("Text") == "list") {
		var choosedArray = new Array();
		for(i=0;i<list.length;i++){
			if(list[i].choosed){
				choosedArray.push(list[i]);
			}
			clearChoosed(list[i]);
		}
		listInsertBefore(choosedArray, ct);
	}else{
		var files = e.dataTransfer.files;
		listInsertBefore(createElemsFromFiles(files),ct);
	}
}

function handleDragOver(e) {
	e.stopPropagation();
	e.preventDefault();
	e.dataTransfer.dropEffect = 'copy to';
}

function listDragStart(e) {
	e.dataTransfer.setData("Text", "list");
}

function itemdrop(e) {}
body.addEventListener("drop", fileDrop, false);
body.addEventListener("dragover", handleDragOver, false);

//volume
var volumeRange = new ScapeRange({
	target:$id("volume"),
	direction:"bt",
	rangeClass:"vol-range",
	playedClass:"vol-played",
	dragbarClass:"vol-drag",
	lightClass:"vol-light",
	thick:"4px",
	lighthick:"6px"
});
volumeRange.onChange(function(pct){
	player.volume(pct);
});
volumeRange.set(0.5);
$id("volume-btn").onclick = function(){
	volumeRange.set(0);
}
//body.addEventListener("dragstart",itemdragstart,false);
//currentTime 当前播放时间，秒
//duration总时间
//volume音量，1