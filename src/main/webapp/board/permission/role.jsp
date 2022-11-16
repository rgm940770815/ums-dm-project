<%--
  Created by D.Yang.
  Date: 2015/1/19 0019
  Time: 16:31
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>角色管理</title>
        <jsp:include page="/basic_import.jsp"></jsp:include>
        <script src="<%=basePath%>js/common/codelist.js" type="text/javascript"></script>
        <script src="<%=basePath%>js/common/strings.js" type="text/javascript"></script>
    </head>
    <body>

        <div class="search-grid-container">
            <div id="grid"></div>
        </div>


        <div id="dialog" class="hide">
            <form id="role" class="form-horizontal" action="<%=basePath%>auth/role/save" method="post">
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>角色ID：</label>

                        <div class="controls">
                            <input id="id" name="role.id" type="text">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>角色名称：</label>

                        <div class="controls">
                            <input id="roleName" name="role.roleName" type="text">
                        </div>
                    </div>
                    <div class="row">
                        <div class="control-group span8">
                            <label class="control-label">角色类型：</label>

                            <div class="controls">
                                <label class="radio" for=""><input type="radio" value="1" name="roleType">超级管理员</label>&nbsp;&nbsp;&nbsp;
                                <label class="radio" for=""><input type="radio" value="0" name="roleType" checked>一般角色</label>
                            </div>
                        </div>
                        <div class="control-group span8">
                            <label class="control-label">序号：</label>

                            <div class="controls">
                                <input id="sortNo" name="role.sortNo" type="text">
                            </div>
                        </div>
                    </div>
                    <div id="auth-field">
                        <div class="control-group span8">
                            <label class="control-label">所属法院：</label>

                            <div class="controls">
                                <select id="courtNo" name="role.courtNo"></select>
                            </div>
                        </div>
                        <%--
                        <div class="control-group span8">
                            <label class="control-label">所属部门：</label>

                <div class="controls">
                    <select id="deptNo" courtNo="" data-rules="{required:true}" name="role.deptNoText">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>
                        --%>
                        <div class="control-group span8">
                            <label class="control-label">所属区域：</label>

                            <div class="controls">
                                <select id="areaNo" name="role.areaNo"></select>
                            </div>
                        </div>
                    </div>
                    <div class="control-group span12">
                        <label class="control-label"><s>*</s>权限：</label>

                        <div id="authSelect" class="controls">
                            <input type="hidden" id="authCode" name="role.authCode" value="">
                        </div>
                    </div>
                </div>

            </form>
            <div id="admin-no" class="hide">
                <input type="hidden" name="role.courtNo" value="9999" disabled form="role">
                <input type="hidden" name="role.courtStdNo" value="9999" disabled form="role">
                <input type="hidden" name="role.areaNo" value="9999" disabled form="role">
                <input type="hidden" name="role.deptNo" value="9999" disabled form="role">
            </div>
        </div>


        <script>

            $("[name='roleType']").change(function () {
                if ($("[name='roleType']:checked").val() == 1) {
                    $("#auth-field").hide();
                    $("#auth-field :input").prop("disabled", true);
                } else {
                    $("#auth-field").show();
                    $("#auth-field :input").prop("disabled", false);
                }
            });

            var viewName = "<s:property value="viewName"></s:property>";

            function showTip() {
                $("#success-tip").fadeIn(500).delay(500).fadeOut(500);
            }



            $.getJSON("<%=basePath%>auth/authList", {_: new Date()}, function (authEnums0) {
                var authEnums = {};
                $(authEnums0).each(function (i, item) {
                    authEnums[item.authCode] = item.authName;
                });
                $.getJSON("<%=basePath%>code/courtAndAreaList", {_: new Date()}, function (enums) {
                    var courtEnums = {};
                    var areaEnums = {};
                    $(enums).each(function (i, item) {
                        if (item.courtStdNo < 7000) {
                            courtEnums[item.courtNo] = item.courtStdName;
                        } else {
                            areaEnums[item.courtStdNo] = item.courtStdName;
                        }
                    });
                    BUI.use(["common/search", 'bui/overlay', 'bui/form', 'bui/calendar', 'bui/select', 'bui/data', 'bui/grid'], function (Search, Overlay, Form, Calendar, Select, Data, Grid) {

                        var formName = '角色管理';
                        //弹出层默认宽度
                        var dialogWidth = 700;
                        //弹出层默认高度
                        var dialogHeight = 280;

                        var editing = new Grid.Plugins.RowEditing({
                            autoSave: true
                        });

                        var columns = [
                            {title: '序号', dataIndex: 'sortNo', width: "10%", sortable: true, align: 'left',
                                editor: {
                                    xtype: "text",
                                    rules: {number: true}
                                }
                            },
                            {title: '角色ID', dataIndex: 'id', width: "10%", sortable: true, align: 'left', },
                            {title: '角色名称', dataIndex: 'roleName', width: "20%", sortable: true, align: 'left',
                                editor: {
                                    xtype: "text",
                                    rules: {required: true, maxlength: 50}
                                }
                            },
                            {title: '法院名称', dataIndex: 'courtNo', width: "30%", sortable: true, align: 'left',
                                editor: {
                                    xtype: "select",
                                    //select: {picker: {}},
                                    items: courtEnums
                                },
                                renderer: function (value, obj, index) {
                                    if (obj.courtNo == 9999 && obj.deptNo == 9999 && obj.areaNo == 9999 && obj.courtStdNo == 9999) {
                                        return "[超级管理员]";
                                    } else {
                                        return Grid.Format.enumRenderer(courtEnums)(value);
                                    }
                                }
                            },
                            //            {title: '部门名称', dataIndex: 'deptNoText', width: "20%", align: 'left'},
                            {title: '所属辖区', dataIndex: 'areaNo', width: "25%", align: 'left',
                                editor: {
                                    xtype: "select",
                                    select: {},
                                    items: areaEnums
                                },
                                renderer: function (value, obj) {
                                    return Grid.Format.enumRenderer(areaEnums)(value) || '无';
                                }
                            },
                            {
                                title: '拥有权限', dataIndex: 'authCodeList', width: "35%", align: 'left',
                                editor: {
                                    xtype: "select",
                                    select: {multipleSelect: true},
                                    items: authEnums
                                },
                                renderer: Grid.Format.multipleItemsRenderer(authEnums)
                            }
                        ];

                        editing.on("editorshow", function (ev) {
                            if (ev.record.id == 1) {
                                ev.editor.cancel();
                                BUI.Message.Alert("超级管理员角色不能修改！", null, "warning");
                            }
                        });
//远程处理
//                        editing.on("accept", function (ev) {
//                            ev.record.authCode = authCodeEncode(ev.record.authCodeList);
//                        });

                        var store = Search.createStore('<%=basePath%>auth/role/all', {
                            sortField: 'sort_no',
                            sortDirection: 'ASC',
                            remoteSort: true,
                            proxy: {
                                save: {//也可以是一个字符串，那么增删改，都会往那么路径提交数据，同时附加参数saveType
                                    addUrl: '<%=basePath%>auth/role/save',
                                    updateUrl: '<%=basePath%>auth/role/save',
                                    removeUrl: '<%=basePath%>auth/role/delete'
                                },
                                method: 'POST'
                            },
                            autoSync: true //保存数据后，自动更新
                        });

                        function authCodeDecode(encoded) {
                            var codeList = [];
                            for (var code in authEnums) {
                                if ((encoded & code) == code) {
                                    codeList.push(code);
                                }
                            }
                            return codeList.join(",");
                        }

                        function authCodeEncode(decoded) {
                            var code = 0;
                            $(decoded.split(",")).each(function (i, item) {
                                code |= item * 1;
                            });
                            return code;
                        }

                        store.on("load", function (e) {
                            var data = store.getResult();
                            $(data).each(function (i, item) {
                                item.authCodeList = authCodeDecode(item.authCode);
                            });
                        });

                        //添加记录
                        function addFunction() {
                            var newData = {};
                            store.addAt(newData, 0);
                            editing.edit(newData, 'roleName'); //添加记录后，直接编辑
                        }
                        //删除选中的记录
                        function delFunction() {
                            var records = grid.getSelection();
                            store.remove(records);
                        }

                        //定义表格样式
                        var gridCfg = Search.createGridCfg(columns, {
                            height: "auto",
                            tbar: {
                                items: [
                                    {
                                        text: '<i class="icon-plus"></i>添加',
                                        btnCls: 'button button-small',
                                        handler: addFunction
                                    },
//                                    {
//                                        text: '<i class="icon-edit"></i>编辑',
//                                        btnCls: 'button button-small',
//                                        handler: showEdit
//                                    },
                                    {
                                        text: '<i class="icon-remove"></i>删除',
                                        btnCls: 'button button-small',
                                        handler: deteleSelected
                                    }
                                ]
                            },
                            plugins: [editing, BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格
                        });
                        var search = new Search({
                            store: store,
                            gridCfg: gridCfg
                        });
                        var form = new Form.HForm({
                            srcNode: "#role",
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
                            $("[name='roleType'][value='0']").click();
                            //$("[name='roleType'][value='0']").prop("checked", true);
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
                                        $("#authCode:input").val(function () {
                                            var value = 0;
                                            $($("#authCode:input").val().split(",")).each(function (i, item) {
                                                value |= (item * 1);
                                            });
                                            return value;
                                        });
                                        if ($("[name='roleType']:checked").val() == 1) {
                                            $("#admin-no :input").prop("disabled", false);
                                        } else {
                                            $("#admin-no :input").prop("disabled", true);
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
                            if (sender.hasClass('btn-role')) {
                                record = ev.record;
                                showDetail();
                            }
                        });

                        /**
                         * 显示详情
                         * @returns {undefined}
                         */
                        function showDetail() {
                            $("#role .field").prop("disabled", true); //禁用编辑

                            loadRecord();

                            $(".edit-fields").addClass("hide");


                            dialog.set('title', formName);
                            dialog.show();
                            $(".edit-btns").addClass("disabled");
                        }

                        /**
                         * 显示新建
                         * @type Arguments
                         */
                        function showNew() {
                            resetFormNew();

                            //获取新的ID
                            $.getJSON("<%=basePath%>code/newUUID", {}, function (data) {
                                $("#new_id").val(data);
                            });

                            resetForm = resetFormNew;

                            $("#role .field").prop("disabled", false);
                            $(".edit-btns").removeClass("disabled");
                            $(".edit-fields").removeClass("hide");

                            dialog.set('title', '新增 - ' + formName);
                            dialog.show();
                        }

                        /**
                         * 显示编辑
                         * @returns {undefined}
                         */
                        function showEdit() {
                            resetForm = resetFormEdit;
                            record = grid.getSelected();
                            if (record) {
                                loadRecord(record);

                                $("#role .field").prop("disabled", false);
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
                            try {
                                editing.get("curEditor").hide();
                            } catch (Exception) {
                            }
                            var record = grid.getSelected();
                            if (record) {
                                BUI.Message.Confirm(
                                        '确定要删除《' + record.roleName + '》吗？',
                                        function () {
                                            store.save('remove', {"id": record.id}, function (data) {
                                                if (!data.success && data.result === -1) {
                                                    BUI.Message.Alert("非法操作，当前用户无此权限！", null, "warning");
                                                } else {
                                                    showTip("删除成功");
                                                }
                                            });
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

                            $("#role [name^='role.']").each(function () {
                                $(this).val(record[$(this).attr("id")]);
                            });

                            var authCode = [];

                            $(authStore.getResult()).each(function (i, item) {
                                if ((record.authCode & item.value) === item.value) {
                                    authCode.push(item.value);
//                            $("#authSelect").append($("<input type='hidden' name='role.authCode' id='authCode'>").val(item.value));
                                }
                            });

                            select.setSelectedValue(authCode.join(","));

                            if (record.courtNo == 9999 && record.deptNo == 9999 && record.areaNo == 9999 && record.courtStdNo == 9999) {
                                $("[name='roleType'][value='1']").click();
                            } else {
                                $("[name='roleType'][value='0']").click();
                            }
                        }

                        //----------初始化日期插件Start-----------
                        var datepicker = new Calendar.DatePicker({
                            trigger: '.calendar',
                            autoRender: true
                        });

                        //bui-gird height bug fix deptree
                        $(".bui-grid-body").height($(document).height() - 120);
                        $(".bui-grid-bbar").height(35);
                        $(".bui-grid-hd-empty.bui-grid-hd").width($(".bui-grid-header").width() - $(".bui-grid-body .bui-grid-table").width())
                        $(".bui-grid-cell.bui-grid-cell-empty").width(0);




                        //-----------------权限下拉多选框--------------

                        var authStore = new Data.Store({
                            url: '<%=basePath%>auth/authList2',
                            autoLoad: true
                        });

                        var select = new Select.Select({
                            render: '#authSelect',
                            valueField: '#authCode',
                            multipleSelect: true,
                            store: authStore
                        });
                        select.render();

                    });

                });
            });
        </script>
    </body>
</html>
