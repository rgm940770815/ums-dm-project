<%--
  Created by IntelliJ IDEA.
  User: huise
  Date: 2018/9/13
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    boolean initGrid = request.getParameter("initGrid") == null || Boolean.parseBoolean(request.getParameter("initGrid"));
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String search = request.getParameter("redi");
    String userType = request.getParameter("userType") == null ? "1" : request.getParameter("userType");
%>
<html>
<head>
    <title>已保存统计列表</title>
    <style>
        .displaynone {
            display: none !important;
        }
    </style>
</head>
<body>

<%--表格--%>
<div id="save_grid" class="hide">
    <form id="save_search_form" class="form-horizontal">
        <div class="row">
            <div class="control-group span16">
                <label class="control-label">统计标题：</label>

                <div class="controls">
                    <input name="title" type="text" placeholder="请输入需要查询的标题" style="box-sizing: content-box;"
                           class="control-text save_search_field span12" data-rules="{maxlength: 150}">
                </div>
            </div>
        </div>
    </form>
    <div style="margin-left: 20px;">
        <div class="search-grid-container">
            <div id="savegrid"></div>
        </div>
    </div>
</div>

<%--弹出窗--%>
<div id="save_edit" class="hide">
    <form id="save_edit_form" class="form-horizontal">
        <div class="row">
            <div class="control-group span16">
                <label class="control-label">统计标题：</label>

                <div class="controls">
                    <input name="title" type="text" placeholder="请输入统计标题"
                           class="control-text save_edit_field span12" data-rules="{required:true, maxlength: 150}" style="box-sizing: content-box;">
                </div>
            </div>
        </div>
        <input name="selectData" type="text" class="displaynone save_edit_field">
        <input name="type" type="text" class="displaynone save_edit_field">
        <input name="userType" type="text" class="displaynone save_edit_field">
        <input name="id" type="text" class="displaynone save_edit_field">
    </form>
</div>
</body>
<script>

    var saveEdit_dialog;
    var saveEdit_grid_dialog;
    var saveEdit_form;
    var saveEdit_store;
    var save_startRow;
    $(function () {
        initForm();
        if (<%=initGrid%>) {
            initGrid();
        }
    });

    function initForm() {
        //初始化BUI相关组件
        BUI.use(['bui/form', 'bui/overlay'],
            function (Form, Overlay) {

                var dform = new Form.HForm({
                    srcNode: '#save_edit_form',
                    submitType: 'ajax',
                    callback: function (data) {
                        if (data.success && data.success != -1) {
                            BUI.Message.Alert("操作成功！", null, "success");
                            if (saveEdit_store) {
                                saveEdit_store.load();
                            }
                        } else if (data.success == -1) {
                            BUI.Message.Alert("已存在相同记录<br>标题：" + data.entity['title'] + "<br>时间：" + data.entity['createTime'], null, "warning");
                        } else {
                            BUI.Message.Alert("操作失败", null, "warning");
                        }
                        dform.clearFields();
                        dzDialog.close();
                    }
                }).render();
                saveEdit_form = dform;
                //要个样式
                new Form.HForm({
                    srcNode: '#save_search_form',
                    submitType: 'ajax'
                }).render();

                //保存自定义统计
                var dzDialog = new Overlay.Dialog({
                    title: "自定义统计",
                    width: 750,
                    contentId: "save_edit",
                    buttons: [
                        {
                            text: '保存', elCls: 'button button-primary', handler: function () {
                                if ($('#save_edit_form input[name=id]').val() === '') {
                                    dform.set('action', '<%=basePath%>/chart/add');
                                } else {
                                    dform.set('action', '<%=basePath%>/chart/update');
                                }
                                dform.submit();
                            }
                        },
                        {
                            text: '关闭', elCls: 'button', handler: function () {
                                dform.clearFields();
                                dform.clearErrors();
                                this.close();
                            }
                        }
                    ],
                    header: "",
                    success: function (data) {
                        this.close();
                    }
                });
                saveEdit_dialog = dzDialog;
            });
    }

    function initGrid() {

        //初始化BUI相关组件
        BUI.use(['common/search', 'bui/overlay'],
            function (Search, Overlay) {

                //-------------初始化表格Start---------------
                var saveListUrl = "<%=basePath%>chart/select";

                //定义表格列
                var columns = [
                    {
                        title: '序号', sortable: false, width: "50", align: 'left', renderer: function (value, obj) {
                            return save_startRow++;
                        }
                    },
                    {title: '统计标题', dataIndex: 'title', width: "450", sortable: false, align: 'left', renderer: function (val, obj) {
                            return '<a href="<%=basePath%>/board/statistics/customInfo.jsp?redi=' + encodeParam(obj['selectsql'].replace(/"/g, '\'')) + '&userType=<%=userType%>">' + val + '</a>';
                        }},
                    // {title: '创建人', dataIndex: 'authorName', width: "100", sortable: false, align: 'left'},
                    {title: '创建时间', dataIndex: 'createTime', width: "100", sortable: false, align: 'left'},
                    // {title: '可见性', dataIndex: 'datatype', width: "180", sortable: false, align: 'left',renderer:function (value, obj) {
                    //         if (value == 1) {
                    //             return '所有人可见';
                    //         } else if (value == 2) {
                    //             return '仅自己可见';
                    //         } else {
                    //             return '-';
                    //         }
                    //     }}
                ];

                var store = Search.createStore(saveListUrl, {
                    sortField: 'sortNo',
                    sortDirection: 'ASC',
                    remoteSort: true,
                    pageSize: 10,
                    proxy: {
                        save: {//也可以是一个字符串，那么增删改，都会往那么路径提交数据，同时附加参数saveType
                            //addUrl: '../data/add.json',
                            //updateUrl: '../data/edit.json',
                            removeUrl: '<%=basePath%>chart/delete'
                        },
                        /*,
                         method : 'POST'*/
                    },
                    params: {
                        userType: '<%=userType%>'
                    },
                    autoSync: true //保存数据后，自动更新
                });
                saveEdit_store = store;

                store.on("load", function () {
                    save_startRow = store.get("start") + 1;
                });

                var tbarData = {
                    items: [
                        {
                            text: '<i class="icon-edit"></i>查询',
                            btnCls: 'button button-small',
                            handler: function () {
                                var condition = $.extend({"start": 0}, store.get("lastParams"));
                                $('.save_search_field').each(function () {
                                    condition[$(this).attr('name')] = $(this).val();
                                });
                                saveEdit_store.load(condition);
                            }
                        },
                        {
                            text: '<i class="icon-edit"></i>修改',
                            btnCls: 'button button-small',
                            handler: function () {
                                var item = grid.getSelected();
                                if (item) {
                                    initData(item);
                                    saveEdit_dialog.show();
                                } else {
                                    BUI.Message.Alert("请选择一条记录", null, "info");
                                }
                            }
                        },
                        {
                            text: '<i class="icon-edit"></i>删除',
                            btnCls: 'button button-small',
                            handler: function () {
                                var item = grid.getSelected();
                                if (item) {
                                    BUI.Message.Confirm(
                                        '确定要删除“' + item.title + '”吗？',
                                        function () {
                                            store.save('remove', {id: item.id}, function (data) {
                                                if (data.success) {
                                                    BUI.Message.Alert("删除成功！", null, "success");
                                                }
                                            });
                                        },
                                        'question');
                                } else {
                                    BUI.Message.Alert("请选择一条记录", null, "info");
                                }
                            }
                        }
                    ]
                };

                //定义表格样式
                var gridCfg = Search.createGridCfg(columns, {
                    //width: 600,
                    height: "260",
                    //forceFit: true,
                    emptyDataTpl: '<div class="centered"><h2>查询的数据不存在</h2></div>',
                    tbar: tbarData,
                    plugins: [BUI.Grid.Plugins.RadioSelection, BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格
                });

                var search = new Search({
                    gridId: 'savegrid',
                    store: store,
                    gridCfg: gridCfg
                });
                var grid = search.get('grid');

                //保存自定义统计
                var dzDialog = new Overlay.Dialog({
                    title: "已保存统计",
                    width: 1000,
                    height: 500,
                    contentId: "save_grid",
                    buttons: [
                        {
                            text: '关闭', elCls: 'button', handler: function () {
                                this.close();
                            }
                        }
                    ],
                    header: "",
                    success: function (data) {
                        this.close();
                    }
                });
                saveEdit_grid_dialog = dzDialog;
            });

    }

    function initData(item) {
        if (item) {
            $('#save_edit_form').find('.save_edit_field').each(function (e) {
                saveEdit_form.setFieldValue($(this).attr('name'), item[$(this).attr('name')]);
            });
        } else {
            saveEdit_form.setFieldValue('selectData', "<%=search%>");
            saveEdit_form.setFieldValue('type', 2);
            saveEdit_form.setFieldValue('userType', '<%=userType%>');
        }
    }
</script>
</html>
