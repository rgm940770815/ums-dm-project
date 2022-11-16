(function () {

    var uploadF = function (cfg) {
        cfg = cfg || {};
        cfg.fileFilters = fIsArray(cfg.extFilters) ? cfg.extFilters : [];
        this.config = $.extend(this.config, {}, cfg);
        this.init();
    };
    uploadF.prototype = {
        XHR: null,
        fileInputField: null,
        buttonBinding: null,
        allSize: 0,
        fileCount : 0 ,
        bytesSpeeds : [],
        retryTimes : 0 ,
        preTime : 0 ,
        bytesSpeed : 0,
        bytesPrevLoaded : 0,
        uploadSize : 0,
        uploadingSize : 0,
        uploading : !1,
        config: {
            fileInput: null,				//html file控件
            fileCancel : null,              //上传停止
            dragDrop: null,					//拖拽敏感区域
            upButton: null,					//提交按钮
            resetUploadFileBtn : null ,        //重置文件
            url: "",						//ajax地址
            parameters: {},
            multipleFiles: true,          //上传多个文件
            fileFilters: [],     //文件拦截
            isAutoUpload : !1,
            onSelect: function () {
            },		//文件选择后
            onDelete: function () {
            },		//文件删除后
            onDragOver: function () {
            },		//文件拖拽到敏感区域时
            onDragLeave: function () {
            },	//文件离开到敏感区域时
            onProgress: function () {
            },		//文件上传进度
            onSuccess: function () {
            },		//文件上传成功时
            onFailure: function () {
            },		//文件上传失败时,
            onComplete: function () {
            },		//文件全部上传完毕时
            filter: function (files) {		//选择文件组的过滤方法
                return files;
            },
            callResetUploadFile : function(flag){

            }
        },
        fileFilter: [],					//过滤后的文件数组
        uploadEventHandlerFunction: null, //事件包装类
        getKey: function (key) {
            return this.config[key];
        },
        setKey: function (key, val) {
            key && "undefined" !== typeof val && key in this.config && ( this.config[key] = val );
        },
        setParam: function (key, val) {
            key && "undefined" !== typeof val && ( this.config['parameters'][key] = val );
        },
        getParam: function(key){
            if(key && this.config['parameters'][key]){
                return this.config['parameters'][key];
            }
            return null;
        },
        //文件拖放
        funDragHover: function (e) {
            e.stopPropagation();
            e.preventDefault();
            this.getKey(e.type === "dragover" ? "onDragOver" : "onDragLeave").call(e.target);
            return this;
        },

        /**
         * 获取文件扩展名
         * @param {String} filename 文件名
         * @return {String}
         * @private
         * @ignore
         */
        getFileExtName: function (filename) {
            var result = /\.[^\.]+$/.exec(filename) || [];
            return result.join('').toLowerCase();
        },
        //获取选择文件，file控件或拖放
        funGetFiles: function (e) {
            // 取消鼠标经过样式
            this.funDragHover(e);

            // 获取文件列表对象
            var files = e.target.files || e.dataTransfer.files;
            //继续添加文件
            var fExtendFunction = fExtend(this.getKey("filter"), this);
            var extFile = fExtendFunction(files);
            for (var i = 0, file; file = extFile[i]; i++) {
                ////增加唯一索引值
                file.index = guid();
                this.allSize += file.size;
                this.fileCount ++;
            }
            this.fileFilter = this.fileFilter.concat(extFile);
            // this.fileFilter = this.fileFilter.sort(compare("name"));
            this.rebindFileField();
            this.funDealFiles(extFile,e);
            return this;
        },
        //选中文件的处理与回调
        funDealFiles: function (extFile,e) {
            //执行选择回调
            var fExtendFunction = fExtend(this.getKey("onSelect"), this);
            fExtendFunction(extFile,e);
            if(this.config.isAutoUpload){
                this.funUploadFileHandler();
            }
            return this;
        },

        //删除对应的文件 flag 改变总大小
        funDeleteFile: function (index , flag) {
            var i = 0;
            if(!index){
                return;
            }
            var deleteFile = null;
            for( ; i < this.fileFilter.length ; i++){
                if(this.fileFilter[i].index == index){
                    deleteFile = this.fileFilter[i];
                    break;
                }
            }
            if(!deleteFile){
                return;
            }
            this.allSize -= deleteFile.size;
            this.fileCount --;
            this.getKey("onDelete")(index , flag);
            RemoveValByIndex(this.fileFilter, i);
            return this;
        },
        uploadEventHandler: function (event, index) {

            switch (event.type) {
                case "load":
                    var xhr = this.XHR;
                    try {
                        if (xhr.readyState == 4 && (xhr.status == 200 || xhr.status == 308)) {
                            fExtend(this.getKey("onSuccess") , this)(index ,xhr.responseText);
                            this.funDeleteFile(index ,false);
                            if(this.fileFilter.length > 0){
                                this.funUploadFile();
                            }else{
                                var onCompleteF = fExtend(this.getKey("onComplete") , this) ;
                                onCompleteF(true);
                                this.resetSpeed();
                            }
                        } else if (xhr.status < 500 && xhr.status >= 400) {
                            this.resetSpeed();
                            fExtend(this.getKey("onFailure") , this)(index);
                        } else if (xhr.status < 200) {return;}
                    } catch(e) {
                        throw e;
                    }
                    break;
                case "error":
                    console.log(event);
                    this.resetSpeed();
                    fExtend(this.getKey("onFailure") , this)(index);
                    //继续上传
                    this.funDeleteFile(index ,false);
                    if(this.fileFilter.length > 0){
                        this.funUploadFile();
                    }else{
                        var onCompleteF = fExtend(this.getKey("onComplete") , this) ;
                        onCompleteF(true);
                        this.resetSpeed();
                    }
                    break;
                case "abort":
                    this.resetSpeed();
                    //this.fire("uploadcancel", {originEvent : event});
                    break;
                case "progress":
                    var total = event.total, loaded =  event.loaded,
                        now = (new Date).getTime(), cost = (now - this.preTime) / 1E3, totalSpeeds = 0;
                    if (0.68 <= cost || 0 === this.bytesSpeeds.length) {
                        this.bytesPrevLoaded = Math.max( 0, this.bytesPrevLoaded);
                        this.bytesSpeed = Math.round((loaded - this.bytesPrevLoaded) / cost);
                        this.bytesPrevLoaded = loaded;
                        this.preTime = now;
                        5 < this.bytesSpeeds.length && this.bytesSpeeds.shift();
                        5 > this.bytesSpeeds.length && (this.bytesSpeed = this.bytesSpeed / 2);
                        this.bytesSpeeds.push(this.bytesSpeed);
                        for (var i = 0; i < this.bytesSpeeds.length; i++)
                            totalSpeeds += this.bytesSpeeds[i];
                        this.bytesSpeed = Math.round(totalSpeeds / this.bytesSpeeds.length);
                    }
                    var $uploadF = this.getKey("onProgress");
                    var percentLoaded = Math.min(100, Math.floor(1E4 * loaded / total) / 100 ) ;
                    var totalPercentLoaded = Math.min(100, Math.floor(1E4 *  (this.uploadSize + event.loaded ) / this.uploadingSize) / 100 ) ;
                    if(total == loaded){
                        this.uploadSize += total;
                    }
                    $uploadF(index,formatSpeed (this.bytesSpeed) , percentLoaded , total , loaded , totalPercentLoaded);
                    break;
                case "readystatechange":
                    break;
            }
        },
        resetSpeed : function(){
            this.uploading = !1;
            this.bytesSpeeds = [];
            this.preTime = 0;
        },
        resetXhr: function () {
            var self = this;
            if (this.XHR) {
                try {
                    this.XHR.upload.removeEventListener("progress", self.uploadEventHandlerFunction),
                        this.XHR.upload.removeEventListener("error", self.uploadEventHandlerFunction),
                        this.XHR.upload.removeEventListener("abort", self.uploadEventHandlerFunction),
                        this.XHR.removeEventListener("loadend", self.uploadEventHandlerFunction),
                        this.XHR.removeEventListener("error", self.uploadEventHandlerFunction),
                        this.XHR.removeEventListener("abort", self.uploadEventHandlerFunction),
                        this.XHR.removeEventListener("readystatechange", self.uploadEventHandlerFunction);
                    this.XHR.removeEventListener("loadstart", self.uploadEventHandlerFunction);
                    this.XHR.removeEventListener("load", self.uploadEventHandlerFunction);
                } catch (e) {
                    throw e;
                }
                this.XHR = null;
            }
        },
        resetUploadFile :function(){
            if(!this.uploading){
                this.fileFilter = [];
                this.allSize = 0;
                this.fileCount = 0;
                this.getKey("callResetUploadFile")(true);
                return;
            }
            this.getKey("callResetUploadFile")(false);
        },
        funUploadFileHandler : function(){
            this.uploadSize = 0 ;
            if(!this.uploading){
                if(this.getKey("beforeUpload")){
                    var  beforeUploadF = fExtend(this.getKey("beforeUpload") , this) ;
                    var flag  = beforeUploadF();
                    if( !flag) return;
                }
                this.uploadingSize = this.allSize;
                this.resetSpeed();
                this.funUploadFile();
            }

        },
        //文件上传
        funUploadFile: function () {
            var self = this;
            //非站点服务器上运行
            if (location.host.indexOf("sitepointstatic") >= 0) {
                return;
            }

            var file = this.fileFilter[0];
            if(!file){
                var onCompleteF = fExtend(this.getKey("onComplete") , this) ;
                onCompleteF(false);
                return;
            }
            this.uploading = !0;

            self.resetXhr();
            self.XHR = new XMLHttpRequest;
            var fd = new FormData, fileFileName = "uploadImage",
                url = self.getKey("url"), _xhr = self.XHR, _upload = _xhr.upload,index_ = file.index;
            for (var I in self.getKey('parameters')) I && fd.append(I, self.getKey('parameters')[I]);
            //获取到类型
            var closest = $("[spanId='" + index_ + "']").closest("div[fileParam]");
            if(closest && closest.attr("fileParam")){
                fd.append("information.relationType", closest.attr("fileParam"));
            }

            fd.append(fileFileName, file);
            //卷宗需要改名
            if(!this.preTime){
                this.preTime = (new Date).getTime();
            }
            this.bytesPrevLoaded = 0 ;
            this.uploadEventHandlerFunction = fExtend(function (e) {
                self.uploadEventHandler(e, index_);
            }, this);
            _xhr.addEventListener("loadstart", this.uploadEventHandlerFunction, !1);
            _upload.addEventListener("progress", this.uploadEventHandlerFunction, !1);
            _xhr.addEventListener("load", this.uploadEventHandlerFunction, !1);
            _xhr.addEventListener("error", this.uploadEventHandlerFunction, !1);
            _upload.addEventListener("error", this.uploadEventHandlerFunction, !1);
            _upload.addEventListener("abort", this.uploadEventHandlerFunction, !1);
            _xhr.addEventListener("abort", this.uploadEventHandlerFunction, !1);
            _xhr.addEventListener("loadend", this.uploadEventHandlerFunction, !1);
            _xhr.addEventListener("readystatechange", this.uploadEventHandlerFunction, !1);
            _xhr.open("POST", url, !0);
            _xhr.send(fd);

        },

        init: function () {
            var self = this;

            if (this.getKey("dragDrop")) {
                fAddEventListener(this.getKey("dragDrop"), "dragover", fExtend(this.funDragHover, this));
                fAddEventListener(this.getKey("dragDrop"), "dragleave", fExtend(this.funDragHover, this));
                fAddEventListener(this.getKey("dragDrop"), "drop", fExtend(this.funGetFiles, this));
            }

            //文件选择控件选择
            this.fileInputField = fCreateContentEle("<input type='file' style='visibility:hidden;width:0px;height:0px;opacity:0;position:absolute;left:-1000px;'>");
            this.setMultipleFiles();
            this.setFileFilters();
            this.bindButton();


        },
        rebindFileField : function() {
            if (this.getKey("dragDrop")) {
                fAddEventListener(this.getKey("dragDrop"), "dragover", fExtend(this.funDragHover, this));
                fAddEventListener(this.getKey("dragDrop"), "dragleave", fExtend(this.funDragHover, this));
                fAddEventListener(this.getKey("dragDrop"), "drop", fExtend(this.funGetFiles, this));
            }

            this.fileInputField = fCreateContentEle("<input type='file' style='visibility:hidden;width:0px;height:0px;opacity:0;position:absolute;left:-1000px;'>");
            this.setMultipleFiles();
            this.setFileFilters();
            fAddEventListener(this.fileInputField, "change", fExtend(this.funGetFiles, this));
        },
        openFileSelectDialog: function (a) {
            this.fileInputField && this.fileInputField.click && a.target != this.fileInputField && this.fileInputField.click();
        },
        bindButton: function () {
            //绑定点击按钮
            fAddEventListener(this.getKey("fileInput"), "click", fExtend(this.openFileSelectDialog, this));
            //绑定文件改变
            fAddEventListener(this.fileInputField, "change", fExtend(this.funGetFiles, this));
            //绑定停止按钮
            if(this.getKey("fileCancel")){
                fAddEventListener( this.getKey("fileCancel"), "click",  fExtend(this.cancelUploadHandler, this) );
            }
            //上传按钮提交
            fAddEventListener(this.getKey("upButton"), "click", fExtend(this.funUploadFileHandler, this));
            if(this.getKey("resetUploadFileBtn")){
                fAddEventListener(this.getKey("resetUploadFileBtn"), "click", fExtend(this.resetUploadFile, this));
            }

        },
        cancelUploadHandler : function(event, b) {
            var c = event || window.event;
            c.preventDefault();
            c.stopPropagation();
            this.XHR && (this.XHR.abort(), this.resetXhr());
            if(this.getKey("callCancelUpload")){
                fExtend(this.getKey("callCancelUpload"),this)();

            }

        },
        setMultipleFiles: function () {
            !0 === this.getKey("multipleFiles")
            && this.fileInputField.setAttribute("multiple", "multiple")
        },
        setFileFilters: function () {
            var a = this.getKey("fileFilters");
            0 < a.length ? this.fileInputField.setAttribute("accept", a
                .join(",")) : this.fileInputField.setAttribute(
                "accept", "")
        }

    };
    function fCreateContentEle(content) {
        var b = document.createElement("div");
        b.innerHTML = content;
        content = b.childNodes;
        return content[0].parentNode.removeChild(content[0]);
    }

    function fAddEventListener(a, b, c) {
        a.addEventListener ? a.addEventListener(b, c, !1) : a.attachEvent ? a
            .attachEvent("on" + b, c) : a["on" + b] = c;
    }

    function fExtend(a, b) {
        var c = 2 < arguments.length ? [arguments[2]] : null;
        return function () {
            var d = "string" === typeof a ? b[a] : a, e = c ? [arguments[0]].concat(c) : arguments;
            return d.apply(b || d, e);
        };
    }

    function fIsArray(val) {
        return "array" === fToString(val);
    }
    function formatSpeed(a) {
        var b = 0;
        1024 <= Math.round(a / 1024)
            ? (b = Math.round(100 * (a / 1048576)) / 100, b = Math.max(0, b), b = isNaN(b) ? 0 : parseFloat(b).toFixed(2), a = b + "MB/s")
            : (b = Math.round(100 * (a / 1024))	/ 100, b = Math.max(0, b), b = isNaN(b) ? 0 : parseFloat(b).toFixed(2), a = b + "KB/s");
        return a;
    }
    //对象类型
    function fToString(a) {
        var b = {
            "undefined": "undefined",
            number: "number",
            "boolean": "boolean",
            string: "string",
            "[object Function]": "function",
            "[object RegExp]": "regexp",
            "[object Array]": "array",
            "[object Date]": "date",
            "[object Error]": "error"
        };
        return b[typeof a] || b[Object.prototype.toString.call(a)] || (a ? "object" : "null");
    }

    function RemoveValByIndex(arr, index) {
        arr.splice(index, 1);
    }

    //文件排序
    function compare(propertyName) {
        return function (object1, object2) {
            var value1 = object1[propertyName];
            var value2 = object2[propertyName];

            if (!value1 || !value2)return true;

            var reg = /[0-9][0-9]*/g;
            var s1 = value1.match(reg);
            var s2 = value2.match(reg);


            //alert(s1+"_"+s2+"_"+object1.name);
            try {
                if (s1.length > 0) {
                    s1 = parseInt(s1.join(''));
                }
            } catch (e) {
            }

            try {
                if (s2.length > 0) {
                    s2 = parseInt(s2.join(''));
                }
            } catch (e) {
            }

            if (s1 == "" && s2 == "") {
                s1 = value1;
                s2 = value2;
            }

            if (s1 < s2) {
                return -1;
            } else if (s1 > s2) {
                return 1;
            }

            return 0;
        }
    }

    function guid() {
        /**
         * @return {string}
         */
        function S4() {
            return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
        }

        return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4());
    }

    window.uploadFileTemp = uploadF;


})();

