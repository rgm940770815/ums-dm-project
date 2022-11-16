<%--
    Document   : common_part
    Created on : 2015-1-13, 16:42:23
    Author     : Diluka
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String courtNo = request.getParameter("courtNo");
    String deptNo = request.getParameter("deptNo");
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

    var startRow;
    var record;

    BUI.use(["common/search", 'bui/overlay', 'bui/form', 'bui/calendar'], function (Search, Overlay, Form, Calendar) {

        var store = Search.createStore('<%=basePath%>view/userinfo/attachmentBatch', {
            params: {
                viewName: viewName
            },
            sortField: 'sort_no',
            sortDirection: 'ASC',
            remoteSort: true,
            proxy: {
                save: {//也可以是一个字符串，那么增删改，都会往那么路径提交数据，同时附加参数saveType
                    //addUrl: '../data/add.json',
                    //updateUrl: '../data/edit.json',
                    removeUrl: '<%=basePath%>userinfo/attach/deleteBatch?t=' + viewName
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
                        btnCls: 'button button-small add',
                        handler: showNew
                    },
                    // {
                    //     text: '<i class="icon-edit"></i>编辑',
                    //     btnCls: 'button button-small',
                    //     handler: showEdit
                    // },
                    // {
                    //     text: '<i class="icon-remove"></i>删除',
                    //     btnCls: 'button button-small',
                    //     handler: deteleSelected
                    // }
                ]
            },
            plugins: [BUI.Grid.Plugins.RadioSelection, BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格
        });
        var search = new Search({
            store: store,
            gridCfg: gridCfg,
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
                } else {
                    BUI.Message.Alert("系统繁忙，请稍后重试！", null, "error");
                }
            }
        }).render();

        var resetForm = function () {
        };
        var resetFormNew = function () {
            form.clearFields();
            form.clearErrors(true, true);
        }
        var resetFormEdit = function () {
            loadRecord();
        }

        var dialog = new Overlay.Dialog({
//            title: formName,
            width: dialogWidth,
            height: dialogHeight,
            contentId: "dialog",
            buttons: [
                {
                    text: '保存', elCls: 'button button-primary edit-btns', handler: function () {
                    if ($("#sae").val()=="1") {
                        var allId = '', no = '';
                        $("#ryList li").each(function () {
                            allId += $(this).attr("data-value") + ",";
                            no += $(this).attr("no") + ",";
                        });
                        $("#allId").val(allId);
                        $("#userNo").val(no);
                    }
                    form.submit();
                    }
                },
                {
                    text: '重置', elCls: 'button edit-btns', handler: function () {
                        resetForm();
                    }
                },
                {
                    text: '关闭', elCls: 'button', handler: function () {
                        bjtitle='';
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
        });

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
                        }if(val == 'servant_level'){
                            datas['type'] = 'servant_level';
                        }if(val == 'juror_job'){
                            datas['type'] = 'juror_job';
                        }

                    });
                    var url = "<%=basePath%>updateInfo/update";

                    $.post(url,datas,function(data){
                       if(data){
                           BUI.Message.Alert('更新成功', 'success');
                           setTimeout("reload()", 1500);
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
            yxg();

            $(".edit-fields").addClass("hide");

            $(".update-info").removeClass("hide");

            dialog.set('title', formName + " - - - "+ bjtitle);

//            dialog.set('title', formName);
//            dialog.set('width', dialogWidth);
//            dialog.set('height', dialogHeight);

            dialog.show();
            $(".ry").addClass("hide");
            $(".edit-btns").addClass("disabled");
        }

        /**
         * 显示新建
         * @type Arguments
         */
        function showNew() {
            $(".update-info").addClass("hide");

            ry();

            resetFormNew();

            resetForm = resetFormNew;

            $("#detail .field").prop("disabled", false);
            $("#tableName").val(viewName);
            $(".edit-btns").removeClass("disabled");
            $(".edit-fields").removeClass("hide");
            $.post('<%=basePath%>code/deptDetailInfo',{courtNo:'<%=courtNo%>',deptNo:'<%=deptNo%>'},function(data){
                dialog.set('title','新增 - '+formName+" - - - "+data.courtShortName+" - "+data.deptName);
            });

//            dialog.set('title', '新增 - ' + formName);
//            dialog.set('width', dialogModWidth);
//            dialog.set('height', dialogModHeight);

            dialog.show();
            $("#sae").val("1");
            $(".ry").removeClass("hide");
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

                yxg();
                $("#tableName").val(viewName);
                $("#detail .field").prop("disabled", false);
                $(".edit-btns").removeClass("disabled");
                $(".edit-fields").removeClass("hide");

                dialog.set('title', '编辑 - ' + formName + " - - - " + bjtitle);

//                dialog.set('title', '编辑 - ' + formName);
//                dialog.set('width', dialogModWidth);
//                dialog.set('height', dialogModHeight);
                dialog.show();
                $("#sae").val("0");
                $(".ry").addClass("hide");
                $("#time").val(record.update_time);
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
            yxg();
            if (record) {
                BUI.Message.Confirm(
                        '确定要删除吗？',
                        function () {
                            store.save('remove', {allUUID: $("#allUUID").val()}, function (data) {
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
            if ((<%=courtNo%>)==null || '<%=courtNo%>'==''){
                $(".add").addClass("hide");
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
    });

    function reload(){
        location.reload();
    }

    function ry() {
        $("#ryList").find("li").remove();
        $.post('<%=basePath%>view/userinfo2', {
            userType: 1,
            isValid: 1,
            start: 0,
            limit: 100,
            field: 'sortNo',
            direction: 'ASC',
            courtNo: '<%=courtNo%>',
            deptNo: '<%=deptNo%>'
        }, function (data) {
            var obj = data.rows;
            for (var a in obj) {
                $("#ryList").append("<li no='"+obj[a].userNo+"' data-value='" + obj[a].id + "' >" + obj[a].fullname + "</li>");
            }
            ryListRender();
        });
    }

    $(".refresh").click(function(){
        ry();
    });

    var  bjtitle='';

    function ryListRender() {
        BUI.use(['bui/list'], function (List) {

            var list = new List.SimpleList({
                elCls: 'bui-select-list',//默认是'bui-simple-list'
                width: 170,
                srcNode: '#ryList',
            });
            list.render();
            $(".remove").click(function () {
                list.removeItems(list.getSelected());
            });

        });
    }

    function yxg() {
        $.post('<%=basePath%>view/userinfo/attachmentAllBatch', {where: record.update_time,viewName:viewName}, function (data) {
            var allId = '';
            var allUUID = '';
            var userNo = '';
            var obj = data.rows;
            for (var a in obj) {
                allId+=obj[a].user_id+",";
                allUUID+=obj[a].id+",";
                userNo +=obj[a].user_no+",";
            };
            $("#allId").val(allId);
            $("#allUUID").val(allUUID);
            $("#userNo").val(userNo);
            yx();
        });
    }
    //查询影响到的人
    function yx(){
        $("#ryList").find("li").remove();
        $.post('<%=basePath%>view/batch', {allId:$("#allId").val()},function(data){
            for (var a in data) {
                $("#ryList").append("<li no='"+data[a].user_no+"' data-value='" + data[a].id + "' >" + data[a].fullname + "</li>");
            }
            ryListRender();
            bjtitle = data[0].court_short_name+" - "+data[0].department_text;
        });
    }

</script>
