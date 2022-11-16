var startRow;
BUI.use(['common/search','bui/toolbar' ,'bui/tree', 'bui/data', 'bui/calendar', 'bui/overlay', 'bui/form', 'bui/uploader', 'bui/mask', 'bui/grid'],
    function (Search, Toolbar,Tree, Data, Calendar, Overlay, Form, Uploader, Mask, Grid) {


        //显示列
        var columns = [
            {
                title: '序号', sortable: false, width: "60", align: 'left', renderer: function (value, obj) {
                return startRow++;
            }
            },
            {title: '标题', dataIndex: 'title', width: "250", align: 'left'},
            {
                title: '展示开始日期',
                dataIndex: 'showStart',
                width: "200",
                sortable: true,
                align: 'left',
                renderer: function (value, obj) {
                    var str = "";
                    if (obj.showStart != null) {
                        var InfoDate = new Date(obj.showStart);
                        str += InfoDate.getFullYear() + "-" + (InfoDate.getMonth() + 1) + "-" + InfoDate.getDate();
                        return '<span>' + str + '</span>';
                    }
                    return '<span ></span>';

                }
            },
            {
                title: '展示结束日期',
                dataIndex: 'showEnd',
                width: "200",
                sortable: true,
                align: 'left',
                renderer: function (value, obj) {
                    var str = "";
                    var col = "";
                    if (obj.showEnd != null) {
                        var InfoDate = new Date(obj.showEnd);
                        var nowDate = new Date();
                        if (InfoDate.getTime() + 86400000 > nowDate.getTime()) {
                            col = "#57c000"
                        } else {
                            col = "#FF3F00"
                        }
                        str += InfoDate.getFullYear() + "-" + (InfoDate.getMonth() + 1) + "-" + InfoDate.getDate();
                        return '<span style="color: ' + col + ' ">' + str + '</span>';
                    }
                    return '<span ></span>';

                }
            },
            {
                title: '发布时间',
                dataIndex: 'createTime',
                sortable: true,
                width: "200",
                align: 'left',
                renderer: BUI.Grid.Format.dateRenderer
            },
            {title: '发布者', dataIndex: 'publisher', width: "200", align: 'left'},
            {
                title: '操作', width: "200", align: 'left', sortable: false,
                renderer: function (value, obj) {

                    return '<a class="publisher_btn">查看详情</a>&nbsp;&nbsp;<a class="fjgl_btn">附件管理</a>';

                }
            }
        ];
//显示列
        var fjcolumns = [
            {
                title: '序号', sortable: false, width: "60", align: 'left', renderer: function (value, obj) {
                return startRow++;
            }
            },
            {title: '附件名称', dataIndex: 'fileName', width: "250", align: 'left'},
            {
                title: '上传日期',
                dataIndex: 'recodeTime',
                width: "200",
                sortable: true,
                align: 'left',
                renderer: function (value, obj) {
                    var str = "";
                    if (obj.recodeTime != null) {
                        var InfoDate = new Date(obj.recodeTime);
                        str += InfoDate.getFullYear() + "-" + (InfoDate.getMonth() + 1) + "-" + InfoDate.getDate();
                        return '<span>' + str + '</span>';
                    }
                    return '<span ></span>';

                }
            },

            {title: '上传人', dataIndex: 'recodeUserFullname', width: "200", align: 'left'},
            {
                title: '操作', width: "200", align: 'left', sortable: false,
                renderer: function (value, obj) {

                    return '<a class="download_btn">下载</a>&nbsp;&nbsp;<a class="delete_btn">删除</a>';

                }
            }
        ];

        //tbar 用于显示按钮信息
        var tbarData = {
            items: [
                {
                    text: '<i class="icon-tag"></i>发布通知',
                    btnCls: 'button button-small',
                    handler: function () {
                        applyDialog.set("title", "发布通知");
                        $("#row_title").html("发布通知");
                        applyDialog.show();
                    }
                },
                {
                    text: '<i class="icon-search"></i>搜索通知',
                    btnCls: 'button button-small',
                    handler: function () {
                        queryDialog.show();
                    }
                },
                {
                    text: '<i class="icon-list-alt"></i>编辑',
                    btnCls: 'button button-small',
                    handler: function () {

                        var selected = grid.getSelected();
                        if (selected) {
                            //不用再查询一次 根据信息读取
                            readInfo(selected);
                            applyDialog.set("title", "编辑");
                            $("#row_title").html("编辑");
                            $("#fjForm>.row").addClass("hide");
                            applyDialog.show();
                            $("#form-hide-div").show();
                        } else {
                            BUI.Message.Alert('请选择一条数据', 'warning');
                        }
                    }
                },
                {
                    text: '<i class="icon-remove"></i>删除信息',
                    btnCls: 'button button-small',
                    handler: function () {
                        var selected = grid.getSelected();
                        if (selected) {
                            BUI.Message.Confirm(
                                '确定要删除这条信息吗？',
                                function () {
                                    var url = path + "/notice/delete";
                                    $.post(url, {'umsNotice.id': selected.id}, function (datas) {
                                        if (datas && datas.success) {

                                            BUI.Message.Alert('删除成功', 'success');
                                            store.load();

                                        } else {
                                            BUI.Message.Alert('删除失败', 'warning');
                                        }

                                    })

                                },
                                'question');


                        } else {
                            BUI.Message.Alert('请选择一条数据', 'warning');
                        }
                    }
                }

            ]
        };

        function readInfo(selected) {
            if (selected) {
                $("#publishInfo").find(".spxx").each(function () {
                    var id = $(this).attr("field");
                    var str = "";
                    // if (id == 'createTime' || id == 'showStart' || id == 'showEnd') {
                    //     if (selected[id]) {
                    //         var myDate = new Date(selected[id]);
                    //         str = myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate();
                    //     }
                    //
                    // } else {
                        str = selected[id];
                    // }
                    $(this).val(str);
                });

            } else {
                BUI.Message.Alert('请选择一条数据', 'warning');
            }
        }

        //定义表格样式
        var gridCfg = Search.createGridCfg(columns, {
            tbar: tbarData,
            height: "auto",
            //forceFit: true,
            emptyDataTpl: '<div class="centered"><h2>查询的数据不存在</h2></div>',
            plugins: [BUI.Grid.Plugins.RadioSelection, BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格
        });

        //请求数据
        var store = Search.createStore(path + "/notice/showAllInfo", {
            //sortField: 'SQRQ',
            //sortDirection: 'DESC',
            remoteSort: true, // 开启异步排序
            pageSize: 20,
            params: {}

        });

        //错误信息
        store.on('exception', function () {
            BUI.Message.Alert('参数请求错误');
        });

        store.on("load", function () {
            startRow = store.get("start") + 1;
        });

        var search = new Search({
            store: store,
            gridCfg: gridCfg
        });
        var grid = search.get('grid');


        //grid 点击事件
        grid.on('cellclick', function (ev) {
            var sender = $(ev.domTarget); //点击的Dom
            var record = ev.record;

            if (sender.hasClass('publisher_btn')) {
                //不用再查询一次 根据信息读取
                readInfo(record);
                applyDialog.set("title", "查看详情");
                $("#row_title").html("查看详情");
                $("#fjForm>.row").addClass("hide");
                applyDialog.show();
                $(".btn-isShow").addClass("hide");
                $("#form-hide-div").show();
            }
            else if(sender.hasClass('fjgl_btn'))
            {
                fjDialog.show();
                $("#noticeId2").val(ev.record.id);
                renderFjgl(ev.record.id)
            }
        });

        var fjstore = Search.createStore(path + "/noticeFileUpload/selectList?", {
            remoteSort: true,
            sortInfo: {
                field: 'recode_time',
                direction: 'desc' //升序ASC，降序DESC
            },// 开启异步排序
            remoteSort: true,	// 开启异步排序
            pageSize: 8,
            showTableToggleBtn: true,
            params: {},
            autoLoad : false
        });

        //错误信息
        fjstore.on('exception', function () {
            BUI.Message.Alert('参数请求错误');
        });

        fjstore.on("load", function () {
            startRow = fjstore.get("start") + 1;
        });
        var PBar = Toolbar.PagingBar;
         bar = new PBar({ //未应用任何样式
         render : '#pagingDiv',
         elCls : 'image-pbar', //应用图标分页栏的样式
         autoRender: true,
         store : fjstore
         });
        var fjgrid = new Grid.Grid({
            render:'#fjgrid',
            width:'100%',//这个属性一定要设置
            columns : fjcolumns,
            idField : 'a',
            store : fjstore,
            height: "400",
            forceFit: false,
             emptyDataTpl: '<div class="centered"><h2>查询的数据不存在</h2></div>',
             plugins: [BUI.Grid.Plugins.RadioSelection, BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格
        });
        fjgrid.on('cellclick', function (ev) {
            var sender = $(ev.domTarget); //点击的Dom
            var record = ev.record;

            if (sender.hasClass('download_btn')) {
                var fjId = ev.record.fjId;
                var turnForm = document.createElement("form");
                document.body.appendChild(turnForm);
                turnForm.method = 'post';
                turnForm.action = path + '/noticeFileUpload/downLoadFj?fjId='+fjId;
                turnForm.submit();
            }
            else if(sender.hasClass('delete_btn'))
            {
                var fjId = ev.record.fjId;
                BUI.Message.Confirm(
                    '确定要删除这条信息吗？',
                    function () {
                        var url = path + "/noticeFileUpload/fjDelete";
                        $.post(url, {'fjId': fjId}, function (datas)
                        {
                            if (datas && datas.success) {

                                BUI.Message.Alert('删除成功', 'success');
                                fjstore.load();

                            } else {
                                BUI.Message.Alert('删除失败', 'warning');
                            }

                        })

                    },
                    'question');
            }
        });
        function renderFjgl(_noticeId){
            var condition = {};
            condition['noticeId'] = _noticeId;
            fjstore.load(condition);
            fjgrid.render();
        }


        //------------------------------基本配置结束-------------------------------------------------------------------


        //------------------------------日期信息---------------------------------------------------------------------------

        //var nowDate = new Date();
        //var now = nowDate.getFullYear() + "-" + (nowDate.getMonth() + 1) + "-" + nowDate.getDate();
        //var datepicker = new Calendar.DatePicker({
        //    trigger: '.calendar',
        //    minDate: now,
        //    autoRender: true
        //});

        //--------------------------------Form-----------------------------------------------------------------------------
        var nowDate = new Date();
        var now = nowDate.getFullYear() + "-" + (nowDate.getMonth() + 1) + "-" + nowDate.getDate();
        $("#showStart").attr("data-cfg", "{datePicker : {minDate : '" + now + "'}}");
        $("#showEnd").attr("data-cfg", "{datePicker : {minDate : '" + now + "'}}");
        var aForm = new Form.Form({
            srcNode: '#publishInfo',
            submitType: 'ajax'
        }).render();
        //var bForm = new Form.HForm({
        //    srcNode: '#spInfo',
        //    submitType: 'ajax'
        //}).render();
        //var cForm = new Form.HForm({
        //    srcNode: '#applyInfo',
        //    submitType: 'ajax'
        //}).render();

        Form.Rules.add({
            name: 'dateInfoRange',
            msg: '请正确填写日期',
            validator: function (value, baseValue, formatMsg) {
                var regexp = new RegExp('^((?!0000)[0-9]{4}-((0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])-(29|30)|(0[13578]|1[02])-31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)-02-29)$');
                var ksrq = $("#showStart").val();
                var jsrq = $("#showEnd").val();
                if (!ksrq || !jsrq) {
                    return formatMsg;
                }
                //正常的日期验证
                if (ksrq && !regexp.test(ksrq)) {
                    return formatMsg;
                }
                if (jsrq && !regexp.test(jsrq)) {
                    return formatMsg;
                }
                if (ksrq && jsrq && new Date(ksrq).getTime() > new Date(jsrq).getTime()) {
                    return formatMsg;
                }

            }
        });

        //-------------------------------弹出框的配置--------------------------------------------------------------------------


        var applyDialog = new Overlay.Dialog({
            title: '发布通知',
            width: 800,
            //配置DOM容器的编号
            contentId: 'publishI',
            mask: true, // 显示mask
            zIndex: 1000, // 自定义zindex，比message的更小
            maskShared: false,
            //closeAction: 'destroy',
            buttons: [
                {
                    text: '重置', elCls: 'button-info button btn-isShow', handler: function () {
                    aForm.clearFields();
                    aForm.clearErrors();
                }
                },
                {
                    text: '发布', elCls: 'button-info button btn-isShow', handler: function () {
                    aForm.valid();
                    if (aForm.isValid()) {
                        var url = path + "/notice/publish";
                        var data = $("#publishInfo").serializeArray();
                        $.post(url, data, function (datas) {
                            if (datas && datas.success) {

                                BUI.Message.Alert('发布成功', 'success');
                                if(!$("#fjForm>div").hasClass("hide"))
                                {
                                    if($("input[name=fjs]").val() != null && $("input[name=fjs]").val() != '')
                                        doUpload(datas.noticeId)
                                }
                            } else {
                                BUI.Message.Alert('发布失败', 'warning');
                            }
                            applyDialog.close();
                            store.load();
                        });

                    }
                }
                },
                {
                    text: '关闭', elCls: 'button', handler: function () {
                    applyDialog.close();
                }
                }
            ]
        });

        applyDialog.on('closing', function () {
            aForm.clearFields();
            aForm.clearErrors();
            $("input[name=fjs]").val("");
            $("#fjForm>div").removeClass("hide");
            $(".btn-isShow").removeClass("hide");
            $("#form-hide-div").hide();
        });

        var queryDialog = new Overlay.Dialog({
            title: '搜索通知',
            width: 800,
            //配置DOM容器的编号
            contentId: 'queryI',
            mask: true, // 显示mask
            zIndex: 1000, // 自定义zindex，比message的更小
            maskShared: false,
            //closeAction: 'destroy',
            buttons: [
                {
                    text: '搜索', elCls: 'button-info button btn-isShow', handler: function () {
                    var info = $("#queryText").val();
                    var condition = $.extend({titleText: info}, {"start": 0, "pageIndex": 0});
                    store.load(condition);
                    queryDialog.close();
                }
                },
                {
                    text: '关闭', elCls: 'button', handler: function () {
                    queryDialog.close();
                }
                }
            ]
        });

        queryDialog.on('closing', function () {
            $("#queryText").val('');
        });

        var fjDialog = new Overlay.Dialog({
            title: '附件管理',
            width: 1100,
            height:500,
            //配置DOM容器的编号
            contentId: 'fjgl',
            mask: true, // 显示mask
            zIndex: 1000, // 自定义zindex，比message的更小
            maskShared: false,
            //closeAction: 'destroy',
            buttons: [
                {
                    text: '选择附件', elCls: 'button-info button ', handler: function () {
                    $("input[name=fjs]").click();
                }
                },
                {
                    text: '上传附件', elCls: 'button-info button ', handler: function () {
                    if($("input[name=fjs]").val() != null && $("input[name=fjs]").val() != '')
                        doUpload($("#noticeId2").val());
                    else
                        BUI.Message.Alert('请选择附件后再上传！', 'warning');
                    fjstore.load();
                    }
                },
                {
                    text: '关闭', elCls: 'button', handler: function () {
                    fjDialog.close();
                    }
                }
            ]
        });

        //展示表单高度修正
        var grid_div = $("#grid");
        grid_div.find(".bui-grid-body").height($(document).height() - grid_div.find(".bui-grid-body").offset().top - grid_div.find(".bui-grid-bbar").height() - 50);

        function doUpload(_id)
        {
            $("#noticeId").val(_id);
            var formData = new FormData($( "#fjForm" )[0]);
            $.ajax({
                url: path + "/noticeFileUpload/upload" ,
                type: 'POST',
                data: formData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (returndata) {
                    $("input[type=file]").val("");
                },
                error: function (returndata) {
                    alert(returndata);
                }
            });
        }
        function showFjs(noticeId)
        {
            var url = path + "/noticeFileUpload/selectList";
            $.post(url, {'noticeId': noticeId}, function (datas)
            {
                if (datas && datas.results>0) {
                    $(".fj_detail_out").removeClass("hide");
                    var html = "";
                    $.each(datas.rows, function (index,obj) {
                        var _index = index+1;
                        html +="<br/><a fjId='"+_index+"' class='fjone'>"+index+"."+obj.fileName+"</a>";
                    });
                    $(".fj_detail").html(html);
                } else {

                }

            })
        }

    });


