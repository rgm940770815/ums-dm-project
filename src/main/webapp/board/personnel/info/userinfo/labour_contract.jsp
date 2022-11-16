<%--
    Document   : labour_contract
    Created on : 2015-3-17, 9:31:45
    Author     : Diluka
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <jsp:include page="common_header.jsp"></jsp:include>
        <body>
            <div class="search-grid-container">
                <div id="grid"></div>
            </div>

            <div id="dialog" class="hide">
                <form id="detail" action="<%=basePath%>external/contract/save" method="post">

                    <div class="hide edit-fields">
                        <div class="row">
                            <div class="control-group span8 ">
                                <label class="control-label">序号：</label>

                                <div class="controls">
                                    <input type="text" id="sortNo" class="field control-text" name="contract.sortNo"
                                           data-rules="{number:true}">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="control-group span8">
                                <label class="control-label"><s>*</s>合同开始日期：</label>

                                <div class="controls">
                                    <input type="text" id="contractStart" class="field calendar" name="contract.contractStart" data-rules="{required:true}">
                                </div>
                            </div>
                            <div class="control-group span8">
                                <label class="control-label"><s>*</s>合同结束日期：</label>

                                <div class="controls">
                                    <input type="text" id="contractEnd" class="field calendar" name="contract.contractEnd" data-rules="{required:true}">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="control-group span16">
                                <label class="control-label"><s>*</s>合同性质：</label>

                                <div class="controls">
                                    <input type="text" id="contractNature" class="field control-text input-large" name="contract.contractNature"
                                           data-rules="{required:true}">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="control-group span16">
                                <label class="control-label"><s>*</s>公司：</label>

                                <div class="controls">
                                    <input type="text" id="contractCompany" class="field control-text input-large" name="contract.contractCompany"
                                           data-rules="{required:true}">
                                </div>
                            </div>
                        </div>
                    </div>
                <div class="hide"><!---->
                    <!---接着这里写-->
                    <input type="hidden" id="new_id" name="id"> <!--type="hidden"-->
                    <input type="hidden" id="id" name="contract.id" class="field"> <!--ums_external_labour_contract表id-->
                    <input type="hidden" id="userId" name="contract.userId" class="field">
                </div>
            </form>
            <form id="update-info">
                <div class="row update-info hide">
                    <div class="control-group span8">
                        <label class="control-label">修改人：</label>

                        <div class="controls">
                            <input type="text" id="updateUser" class="field control-text input-normal" disabled name="contract.updateUser">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">修改时间：</label>

                        <div class="controls">
                            <input type="text" id="updateTime" class="field control-text input-normal" disabled name="contract.updateTime">
                        </div>
                    </div>
                </div>
            </form>
        </div>


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
        </style>
        <div id="success-tip" class="hide pop-tip">
        </div>
        <script>
            var startRow;
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

            BUI.use(["common/search", 'bui/overlay', 'bui/form', 'bui/calendar'], function (Search, Overlay, Form, Calendar) {

                var userId = "<s:property value="userId"></s:property>";
                //构建表格表头
                var columns = [{
                        title: '序号',
                        dataIndex: 'sortNo',
                        width: '50',
                        renderer: function (value, obj) {
                            return startRow++;
                        }
                    }, {
                        title: '合同开始',
                        dataIndex: 'contractStart',
                        width: '200'
                    }, {
                        title: '合同结束',
                        dataIndex: 'contractEnd',
                        width: '200'
                    },
                    {
                        title: '合同性质',
                        dataIndex: 'contractNature',
                        width: '100'
                    },
                    {
                        title: '公司',
                        dataIndex: 'contractCompany',
                        width: '300'
                    },
                    {
                        title: '操作',
                        width: '100',
                        renderer: function (value, obj) {
                            return '<span class="grid-command btn-detail" title="显示影音资料详细信息">查看详情</span>';
                        }
                    }
                ];
                var formName = '劳动合同';
                //弹出层默认宽度
                var dialogWidth = 700;
                //弹出层默认高度
                var dialogHeight = 350;

                var store = Search.createStore('<%=basePath%>external/contract/search', {
                    params: {
                        userId: userId
                    },
                    sortField: 'sortNo',
                    sortDirection: 'ASC',
                    remoteSort: true,
                    proxy: {
                        save: {//也可以是一个字符串，那么增删改，都会往那么路径提交数据，同时附加参数saveType
                            //addUrl: '../data/add.json',
                            //updateUrl: '../data/edit.json',
                            removeUrl: '<%=basePath%>external/contract/delete'
                        },
                        method: 'POST'
                    },
                    autoSync: true //保存数据后，自动更新

                });
                store.on("load", function () {
                    startRow = store.get("start") + 1;
                });
                //定义表格样式
                var gridCfg = Search.createGridCfg(columns, {
                    height: "auto",
                    //forceFit: true,
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
                        } else {
                            BUI.Message.Alert("系统繁忙，请稍后重试！", null, "error");
                        }
                    }
                }).render();

                var record;
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

                                this.close();
                            }
                        }],
                    success: function () {
                        this.close();
                    }
                });
                var grid = search.get('grid');
                grid.on('cellclick', function (ev) {
                    var sender = $(ev.domTarget); //点击的Dom
                    if (sender.hasClass('btn-detail')) {
                        record = ev.record;
                        showDetail();
                    }
                });

                /**
                 * 显示详情
                 * @returns {undefined}
                 */
                function showDetail() {
                    $("#detail .field").prop("disabled", true); //禁用编辑

                    loadRecord();

                    $(".edit-fields").removeClass("hide");

                    $(".update-info").removeClass("hide");
                    $("#updateUser").val(record.updateUser);
                    $("#updateTime").val(record.updateTime);


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
                    $(".update-info").addClass("hide");
                    resetFormNew();

                    //获取新的ID
                    $.getJSON("<%=basePath%>code/newUUID", {_: new Date()}, function (data) {
                        console.log("获取新的ID： ",data)
                        $("#new_id").val(data);
                        $("#id").val(data);
                        $("#userId").val(userId);
                    });

                    resetForm = resetFormNew;

                    $("#detail .field").prop("disabled", false);
                    $("#user_id").val(userId);
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

                        $("#detail .field").prop("disabled", false);
                        $(".edit-btns").removeClass("disabled");
                        $(".edit-fields").removeClass("hide");


                        dialog.set('title', '编辑 - ' + formName);
                        //TODO 下面两行原本是注释的
                        dialog.set('width', dialogModWidth);
                        dialog.set('height', dialogModHeight);

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
                                    store.save('remove', {"id": record.id}, function (data) {
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
                        $(this).val(record[$(this).attr("id")]);
                    });

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
//                $(".bui-grid-body").height($(document).height() - 120);
//                $(".bui-grid-bbar").height(35);
//                $(".bui-grid-hd-empty.bui-grid-hd").width($(".bui-grid-header").width() - $(".bui-grid-body .bui-grid-table").width())
//                $(".bui-grid-cell.bui-grid-cell-empty").width(0);
            });
        </script>
    </body>
</html>

