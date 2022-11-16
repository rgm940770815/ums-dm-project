<%--
    Document   : common_part
    Created on : 2015-1-13, 16:42:23
    Author     : Diluka
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<style>
    .pop-tip{
        background:#658e15;
        filter:alpha(opacity:70);
        opacity:0.6;
        border-radius:5px;
        border:1px solid #476a05;
        width:7em;
        color:#FFF;
        text-shadow:1px 1px #000;
        font-family:"黑体";
        font-size:1.825em;
        letter-spacing:2px;
        text-align:center;
        line-height:2.75em;
        position: absolute;
        top:15%;
        right: 50%;
    }
    .hiddenDom{
        display: none;
    }
</style>
<div id="success-tip" class="hide pop-tip">
</div>
<script>
    function showTip(text) {
        if (text) {
            $("#success-tip").text(text);
        } else {
            $("#success-tip").text("添加成功");
        }
        $("#success-tip").fadeIn(500).delay(500).fadeOut(500);
    }
    $("#detail select.code").each(function () {
        loadCodeList(this, '<option value="">请选择</option>');
    });

    $("#detail .field[id]").not("[name]").each(function () {
        var id = $(this).attr("id");
        var name = "p." + underlineToCamel(id);
        $(this).attr("name", name);
    });
    var otherParam;
    otherParam = otherParam || {};
    var startRow;
    var dom;
    var ind = 0;
    BUI.use(["common/search", 'bui/overlay', 'bui/form', 'bui/calendar'], function (Search, Overlay, Form, Calendar) {

        var store = Search.createStore('<%=basePath%>view/userinfo/attachment2', {
            params: $.extend({viewName: viewName, userId: userId}, otherParam),
            sortField: sortField==""?'sort_no':sortField,
            sortDirection: sortDirection==""?'ASC':sortDirection,
            remoteSort: true,
            proxy: {
                save: {//也可以是一个字符串，那么增删改，都会往那么路径提交数据，同时附加参数saveType
                    //addUrl: '../data/add.json',
                    //updateUrl: '../data/edit.json',
                    removeUrl: '<%=basePath%>userinfo/attach/delete?t=' + viewName
                },
                method: 'POST'
            },
            autoSync: true //保存数据后，自动更新
        });
        //定义表格样式
        var gridCfg = Search.createGridCfg(columns, {
            height: "auto",
            tbar: {
                items: [
                    {
                        text: '<i class="icon-plus"></i>添加',
                        btnCls: 'button button-small',
                        handler: showNew
                    },
                    {
                        text: '<i class="icon-edit"></i>编辑',
                        btnCls: 'button button-small',
                        handler: showEdit
                    },
                    {
                        text: '<i class="icon-remove"></i>删除',
                        btnCls: 'button button-small',
                        handler: deteleSelected
                    }
                ]
            },
            plugins: [BUI.Grid.Plugins.RadioSelection, BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格
        });
        var search = new Search({
            store: store,
            gridCfg: gridCfg
        });
        var form = new Form.HForm({
            srcNode: "#detail",
            submitType: 'ajax',
            callback: function (data) {
                dialog.close();

                if (data.success) {
                    store.load();
                    showTip();
//                    BUI.Message.Show({
//                        msg: '保存成功！',
//                        icon: 'success',
//                        buttons: [],
//                        autoHide: true,
//                        autoHideDelay: 618
//                    });
                } else if (data.result === -1) {
                    BUI.Message.Alert("非法操作，当前用户无此权限！", null, "warning");
                }else if(data.msg){
                    BUI.Message.Alert(data.msg, null, "warning");
                } else {
                    BUI.Message.Alert("系统繁忙，请稍后重试！", null, "error");
                }
            }
        }).render();

        var resetForm = function () {
        };
        var resetFormNew = function () {
            $('span[tree_dialog]').text('请选择').attr('title', '请选择');
            $('[listType="select2"]').each(function () {
                var firstOptionValue = $(this).find("option:eq(0)").val();
                $(this).select2().select2("val", firstOptionValue);
                $(this).change();
            });
            form.clearFields();
            form.clearErrors(true, true);
        };
        var resetFormEdit = function () {
            loadRecord();
        };

        var dialog = new Overlay.Dialog({
//            title: formName,
            width: dialogWidth,
            height: dialogHeight,
            contentId: "dialog",
            buttons: [
                {
                    text: '保存', elCls: 'button button-primary edit-btns', handler: function () {
                        var sfyxnqgb = $("#n_sfyxnqgb").val();
                        var jrtj = $("#n_jrtj").val();
                        if(sfyxnqgb == 1 && jrtj == ''){
                            var str = "<span class='valid-text'>" +
                                "<span class='estate error'>" +
                                "<span class='x-icon x-icon-mini x-icon-error'>!</span>" +
                                "<em>不能为空!</em>" +
                                "</span>" +
                                "</span>"
                            if(ind == 0){
                                $("#n_jrtj").parent().append(str)
                                ind++;
                            }
                        }else{
                            form.submit();
                        }
                    }
                },
                {
                    text: '重置', elCls: 'button edit-btns btn-reset', handler: function () {
                        resetForm();
                    }
                },
                {
                    text: '关闭', elCls: 'button', handler: function () {
                        this.close();
                    }
                }],
            success: function () {
                this.close();
            }
        });
        var grid = search.get('grid');

        store.on("beforeprocessload",function(){
            startRow = store.get("start") + 1;
        })

        grid.on('cellclick', function (ev) {
            var sender = $(ev.domTarget); //点击的Dom
            if (sender.hasClass('btn-detail')) {
                record = ev.record;
                showDetail();
            }
            if(sender.hasClass("update-info")){
                BUI.Message.Confirm('确认要更新此条消息么？', function () {
                    record = ev.record;
                    var datas = {
                    };
                    datas['tableId'] = record['id'];
                    datas['userInfo.id'] = record['user_id'];
                    $(".hideInfo input").each(function () {

                        console.log(1);

                        var val = $(this).val();
                        var Tval = $("input[name="+val+"]").val();
                        datas[Tval] = record[val];
                        if(val == 'jurorInfo'){
                            datas['type'] = 'juror';
                            datas['jurorInfo.userId'] = record['user_id'];
                        }
                        if(val == 'political'){
                            datas['type'] = 'political';
                        }
                        if(val == 'administration'){
                            datas['type'] = 'administration';
                        }if(val == 'law'){
                            datas['type'] = 'law';
                        }if(val == 'rank'){
                            datas['type'] = 'rank';
                        }if(val == 'level'){
                            datas['type'] = 'level';
                            if (record.helper_level) {
                                datas['helper_level'] = 1;
                            }
                        }if(val == 'servant_level'){
                            datas['type'] = 'servant_level';
                        }if(val == 'juror_job'){
                            datas['type'] = 'juror_job';
                        }if(val == 'education'){
                            datas['type'] = 'education';
                        }if(val == "degree"){
                            datas['type'] = 'degree';
                        }

                    });
                    var url = "<%=basePath%>updateInfo/update";

                    console.log(datas);

                    $.post(url,datas,function(data){
                       if(data){
                           BUI.Message.Alert('更新成功', 'success');
                           store.load();
                       }
                    });
                }, 'question');
            }
        });



        /**
         * 显示详情
         * @returns {undefined}
         */
        function showDetail() {
            $("#detail .field").prop("disabled", true); //禁用编辑

            loadRecord();

            $(".edit-fields").addClass("hide");

            $(".update-info").removeClass("hide");

            dialog.set('title', formName);
//            dialog.set('width', dialogWidth);
//            dialog.set('height', dialogHeight);

            dialog.show();
            $(".edit-btns").addClass("disabled");
        }

        /**
         * 显示新建
         * @type Arguments
         */
        function showNew() {

            record = null;
            $(".update-info").addClass("hide");

            resetFormNew();

            //获取新的ID
            $.getJSON("<%=basePath%>code/newUUID", {_: new Date()}, function (data) {
                $("#new_id").val(data);
            });

            resetForm = resetFormNew;

            $("#detail .field").prop("disabled", false);
            $("#user_id").val(userId);
            $("#tableName").val(viewName);
            $(".edit-btns").removeClass("disabled");
            $(".edit-fields").removeClass("hide");

            dialog.set('title', '新增 - ' + formName);
//            dialog.set('width', dialogModWidth);
//            dialog.set('height', dialogModHeight);

            dialog.show();
        }

        /**
         * 显示编辑
         * @returns {undefined}
         */
        function showEdit() {
            $(".update-info").addClass("hide");
            resetForm = resetFormEdit;
            record = grid.getSelected();
            if (record) {
                loadRecord(record);
                $("#tableName").val(viewName);
                $("#detail .field").prop("disabled", false);
                $(".edit-btns").removeClass("disabled");
                $(".edit-fields").removeClass("hide");
                dialog.set('title', '编辑 - ' + formName);
                dialog.show();
            } else {
                BUI.Message.Alert("请选择一条记录", null, "info");
            }
        }

        /**
         * 删除选中
         * @returns {undefined}
         */
        function deteleSelected() {
            record = grid.getSelected();
            if (record) {
                BUI.Message.Confirm(
                        '确定要删除吗？',
                        function () {
                            store.save('remove', {"p.id": record.id,user_id:userId,"p.userId":userId,"p.courtNo":record.court_no}, function (data) {
                                if (!data.success && data.result === -1) {
                                    BUI.Message.Alert("非法操作，当前用户无此权限！", null, "warning");
                                } else {
                                    showTip("删除成功");
                                }
                            });
                            record = null;
                        },
                        'question');
            } else {
                BUI.Message.Alert("请选择一条记录", null, "info");
            }
        }

        /**
         * 加载record
         * @returns {undefined}
         */
        function loadRecord() {
            resetFormNew();

            $("#detail .field").each(function () {
                $(this).val(record[$(this).attr("id")]).change();
            });

            $("#updateUser").val(record.update_user);
            $("#updateTime").val(record.update_time);
        }

        //----------初始化日期插件Start-----------
        var datepicker = new Calendar.DatePicker({
            trigger: '.calendar',
            autoRender: true
        });

        $.getJSON("<%=basePath%>auth/canIEdit", {_: new Date()}, function (can) {
            if (!can) {
                $(".bui-bar.bui-grid-button-bar button").addClass("hide");
            }
        });

        //只需要一个样式而已
        new Form.HForm({
            srcNode: "#update-info"
        }).render();
        //bui-gird height bug fix deptree
        $(".bui-grid-body").height($(document).height() - 120);
        $(".bui-grid-bbar").height(35);
        $(".bui-grid-hd-empty.bui-grid-hd").width($(".bui-grid-header").width() - $(".bui-grid-body .bui-grid-table").width())
        $(".bui-grid-cell.bui-grid-cell-empty").width(0);

        buiform = form;
        if (buiDialogShowCallBack) {
            dialog.on('show',buiDialogShowCallBack);
        }
        if (buiformBeforeSubmit) {
            buiform.on('beforesubmit',buiformBeforeSubmit);
        }

        //添加 名字为 tcyesj_required的校验规则
        Form.Rules.add({
            name: 'tcyesj_required', // 规则名称 tcyesj：退出员额时间
            msg: '请填写退出员额时间', // 默认显示的错误信息
            //value：yefg_end_time的val值 baseValue：true  formatMsg：提示信息
            validator: function (value, baseValue, formatMsg) {
                // 如果退出员额原因不为空,那么退出员额时间必填
                if ($("#yefg_end_reason").val() != "" && baseValue && value.trim() == "") {
                    return formatMsg;
                }
            }
        });

    });

    function reload(){
        location.reload();
    }
    var buiform;
    var buiformBeforeSubmit;
    var buiDialogShowCallBack;
    var record;

    $(function () {
        //对于所有上报字段，与本地字段做切换关联
        $('[id$=_report]').each(function (e) {
            var reportId = $(this).attr('id');
            //本地字段修改时
            // $('#' + reportId.substring(0, reportId.indexOf('_report'))).on('change', function (e1) {
            //     //上报字段
            //     var reportObj = $('#' + reportId);
            //     var text = $(this).find('option:selected').text();
            //     var option = reportObj.find('option');
            //     //获取上报字段的对应value值
            //     var getValue = function (text) {
            //         for (var i = 0; i < option.length; i++) {
            //             var obj = $(option[i]);
            //             if (obj.text() == text) {
            //                 return obj.val();
            //             }
            //         }
            //         return '';
            //     };
            //     var val = getValue(text);
            //     if (val == '') {
            //         val = getValue('其他');
            //         if (val == '') {
            //             val = getValue('无');
            //         }
            //     }
            //     reportObj.val(val).change();
            //     if (reportObj.attr('listType') == 'select2') {
            //         reportObj.select2().select2('val', val);
            //     }
            // });
        });
    });

    /**
     * 开始或停止验证规则---单个
     * @param o dom对象
     * @param bool  true验证，false不验证
     */
    function authField(o, bool) {
        var fields = buiform.getFields();
        for (var i = 0; i < fields.length; i++) {
            var field = fields[i];
            var obj = field.get('el');
            if ($(o).is(obj)) {
                field.set('pauseValid', !bool);
                if (!bool) field.clearErrors();
                break;
            }
        }
        var s = $(o).parent().parent().find('s');
        if (s.length > 0) {
            if (!bool) {
                s.addClass('hide');
            } else {
                s.removeClass('hide');
            }
        }
    }

    /**
     * 开始或停止验证规则---单个（除了它都修改验证）
     * @param o dom对象
     * @param bool  true验证，false不验证
     */
    function authField2(o, bool) {
        var fields = buiform.getFields();
        for (var i = 0; i < fields.length; i++) {
            var field = fields[i];
            var obj = field.get('el');
            if (!$(o).is(obj)) {
                field.set('pauseValid', !bool);
                if (!bool) field.clearErrors();
            }
        }
    }
</script>
