function Menu(menu) {
    this.isMouseDowned = false;
    this.isIE = !!(window.attachEvent &&navigator.userAgent.indexOf('Opera') === -1);//判断是否是IE  

    this.y = function(element) {
        var offset = element.offsetTop;
        if(element.offsetParent != null) {
            offset += this.y(element.offsetParent);
        }
        return offset;
    }

    this.x = function(element) {
        var offset = element.offsetLeft;
        if(element.offsetParent != null) {
            offset += this.x(element.offsetParent);
        }
        return offset;
    }

    this.$ = function(id) {
        return document.getElementById(id);
    }

    this.$$ = function(obj, name) {
        var rs = new Array();
        var childNodes = obj.childNodes;
        for(var i = 0 ; i < childNodes.length; i ++) {
            if(childNodes[i].nodeName == name) {
                rs.push(childNodes[i]);
            }
        }
        return rs;
    }

    this.onmouseover = function () {
        this.isMouseDowned != this.isMouseDowned;
    }

    this.root = this.$(menu);
    this.init();
}

Menu.prototype.init = function(){
    this.bind(this.root);
}

Menu.prototype.bind = function(root){
    var liNodes = this.$$(root, 'LI');
    for(var i = 0; i < liNodes.length; i ++) {
        var liNode = liNodes[i];
        if(liNode.style.display == '' || liNode.style.display == 'none') {
            if(this.isIE){
                liNode.style.display = 'inline';
            } else {
                liNode.style.display = 'inline-block';
            }
        }

        var divs = this.$$(liNode, 'DIV');
        for(var j = 0; j < divs.length; j ++){
            var div = divs[j];
            div.style.display = 'none';
            if(div.style.width == ''){
                div.style.maxWidth = this.root.style.width;
            }
            div.style.position = 'absolute';
            if(this.isIE){
                div.style.left = this.x(liNode);
                div.style.top  = this.y(liNode) + liNode.offsetHeight;
            }
            var uls = this.$$(div,'UL');
            for(var  k = 0; k < uls.length; k ++) {
                this.bind(uls[k]);
            }
        }

        liNode.$$ = function(name) {
            var rs = new Array();
            var childNodes = this.childNodes;
            for(var i = 0;i < childNodes.length; i ++) {
                if(childNodes[i].nodeName == name){
                    rs.push(childNodes[i]);
                }
            }
            return rs;
        }

        liNode.onmouseover = function () {
            var sps = this.$$('SPAN');
            for(var j = 0; j < sps.length; j ++) {
                var sp = sps[j];
                sp.style.border = 'solid thin #666';
                sp.style.outline= '#666 thin outset';
            }

            var ds = this.$$('DIV');
            for(var j = 0; j < ds.length; j ++) {
                var div = ds[j];
                div.style.display = 'block';
            }
        }

        liNode.onmouseout = function () {
            var sps = this.$$('SPAN');
            for(var j = 0; j < sps.length; j ++) {
                var sp = sps[j];
                sp.style.border = 'none';
                sp.style.outline = 'none';
            }

            var ds = this.$$('DIV');
            for(var j = 0; j < ds.length; j ++) {
                var div = ds[j];
                div.style.display = 'none';
            }
        }
    }
}
