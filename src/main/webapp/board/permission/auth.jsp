<%--
  Created by D.Yang.
  Date: 2015/1/23 0023
  Time: 17:07
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <jsp:include page="/basic_import.jsp"></jsp:include>
    <script src="<%=basePath%>js/common/codelist.js" type="text/javascript"></script>
</head>
<body>

<%--表格--%>

<div class="search-grid-container">
    <div id="grid"></div>
</div>

<%--查询窗口--%>
<div id="search" class="hide">
    <form id="userinfo_search_form" class="form-horizontal">
        <div class="row">
            <div id="nav" class="row">
                <div class="control-group span8">
                    <label class="control-label">姓名：</label>

                    <div class="controls">
                        <input id="fullname" name="fullname" type="text" value="" class="control-text search_field">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">性别：</label>

                    <div class="controls">
                        <select id="gender" typeId="3" class="code search_field" name="gender"></select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">职级：</label>

                    <div class="controls">
                        <select id="rank" typeId="17" class="code search_field" name="rank"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">行政职务：</label>

                    <div class="controls">
                        <select id="administrationPosition" typeId="15" name="administrationPosition" type="text"
                                value="" class="code search_field"></select>
                    </div>
                </div>
            </div>
            <
        </div>
    </form>
</div>


<%--权限管理弹出层--%>
<div id="content" class="hide">
    <form id="role_form" class="form-horizontal" action="<%=basePath%>auth/role/replaceUserRoles" method="post">
        <div class="row">
            <input type="hidden" id="roleId" name="roleId"/>
            <input type="hidden" id="userId" name="userId"/>

            <div class="control-group">
                <label class="control-label">用户权限：</label>


                <label id="userRole" class="checkbox">
                    <!--                            <input type="checkbox" name="checkboxAll" id="checkboxAll"
                                                       onclick="checkAll('checkboxAll', 'roleIds', 'userRole')"/>
                                                <lable>全部</lable>-->
                </label>

                <%--
                <div class="controls">
                    <select id="userRole2" name="roleId" type="text" value=""></select>
                </div>
                --%>
            </div>


        </div>
    </form>
</div>

</body>

<script>

    //-------------初始化下拉框数据Start---------------
    var firstLine = '<option value="">请选择</option>';
    $(function () {
        $("select.code").each(function () {
            loadCodeList(this, firstLine);
        });
    });

    /*初始化权限管理checkbox*/
    $(document).ready(function () {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "<%=basePath%>auth/role/listAll",
            data: {_: new Date()},
            success: function (data) {
                var userRole = $("#userRole");
                $.each(data, function (index, item) {
                    $("<input/>", {
                        type: "checkbox",
                        id: item.id,
                        name: "roleIds",
                        val: item.id,
                        onclick: "checkOne('checkboxAll','authCheck')"
                    }).appendTo(userRole);
                    $("<label/>", {text: item.roleName}).appendTo(userRole);
                });
            }
        });
    })

    /*check全选 反选*/
    function checkAll(checkboxAll, checkboxOne, div) {
        if ($("input[name='" + checkboxAll + "']:checked").length > 0) {
            $("#" + div + " input[name='" + checkboxOne + "']").each(function (i) {
                this.checked = true;
            });
        } else {
            $("#" + div + " input[name='" + checkboxOne + "']").each(function (i) {
                this.checked = false;
            });
        }
    }

    /*check单选 反选*/
    function checkOne(checkboxAll, checkboxOne) {
        if ($("input[name='" + checkboxOne + "']:checked").length > 0) {
            $("#" + checkboxAll).attr("checked", "checked");
        } else {
            $("#" + checkboxAll).attr("checked", null);
        }
    }

    /* 提交表单*/
    function checkedSubmit(form) {
        //        var roleId = "";
        //        if ($("input[name='checkboxAll']:checked").length > 0) {
        //            $("#userRole input[name='authCheck']:checked").each(function (i) {
        //                roleId += this.value + ",";
        //            });
        //        }
        //        roleId = roleId.slice(0, roleId.length - 1);
        //        $("#roleId").attr("value", trim(roleId));
        //妈蛋，struts这个垃圾不认现在的标准roleIds[]，只认roleIds[0]，roleIds[1]...或者旧的roleIds，roleIds，。。。。
        $("[name='roleIds']:input").each(function (i, item) {
            this.name += "[" + i + "]";
        });
        form.submit();
    }

    /* 首尾去空格 */
    function trim(str) {
        return str.replace(/(^\s*)|(\s*$)/g, "");
    }


    $.ajax({
        url: "<%=basePath%>auth/role/allRolesForEnum",
        dataType: "json",
        asnyc: false,
        data: {_: new Date()},
        success: function (rolesEnum) {


            //初始化BUI相关组件
            BUI.use(['common/search', 'bui/data', 'bui/calendar', 'bui/overlay', 'bui/form', 'bui/grid'],
                function (Search, Data, Calendar, Overlay, Form, Grid) {

                    //-------------初始化表格Start---------------
                    var authActionUrl = "<%=basePath%>view/userinfo2";

//                    var editing = new BUI.Grid.Plugins.DialogEditing({
//                        contentId: 'content', //设置隐藏的Dialog内容
//                        autoSave: true, //添加数据或者修改数据时，自动保存
//                        triggerCls: 'btn-edit'
//                    });
                    var editing = new Grid.Plugins.CellEditing({triggerSelected: false});

                    editing.on("accept", function (ev) {

                        var data = {userId: ev.record.id};
                        $(ev.record.roleIds.split(",")).each(function (i, item) {
                            data["roleIds[" + i + "]"] = item;
                        });
                        $.post("<%=basePath%>auth/role/replaceUserRoles", data);
                    });

                    //定义表格列
                    var columns = [
                        {title: '序号', dataIndex: 'sortNo', width: "5%", sortable: true, align: 'left'},
                        {title: '姓名', dataIndex: 'fullname', width: "10%", sortable: true, align: 'left'},
                        {title: '性别', dataIndex: 'genderText', width: "5%", sortable: true, align: 'left'},
                        {title: '法院', dataIndex: 'courtNoText', width: "15%", sortable: true, align: 'left'},
                        {title: '部门', dataIndex: 'departmentText', width: "15%", sortable: true, align: 'left'},
                        {title: '职级', dataIndex: 'rankText', width: "10%", sortable: true, align: 'left'},
                        {
                            title: '行政职务',
                            dataIndex: 'administrationPositionText',
                            width: "15%",
                            sortable: true,
                            align: 'left'
                        },
                        {
                            title: "角色（点击单元格即可分配角色）",
                            dataIndex: "roleIds",
                            width: "25%",
                            renderer: Grid.Format.multipleItemsRenderer(rolesEnum),
                            sortable: false,
                            editor: {
                                //xclass: "select",
                                xtype: "select",
                                select: {multipleSelect: true},
                                items: rolesEnum
                            }
                        }
                    ];

                    var store = Search.createStore(authActionUrl, {
                        sortField: 'sortNo',
                        sortDirection: 'ASC',
                        remoteSort: true,
                        pageSize: 20,
                        proxy: {
                            save: {//也可以是一个字符串，那么增删改，都会往那么路径提交数据，同时附加参数saveType
                                //addUrl: '../data/add.json',
                                //updateUrl: '../data/edit.json',
                                //removeUrl: '<%=basePath%>userinfo/delete'
                            }/*,
                                     method : 'POST'*/
                        },
                        autoSync: true //保存数据后，自动更新
                    });

                    store.on("load", function (e) {
                        var data = store.getResult();
                        $(data).each(function (i, u) {
                            $.ajax({
                                url: "<%=basePath%>auth/role/userRoles",
                                data: {userId: u.id, _: new Date()},
                                dataType: "json",
                                async: false,
                                success: function (roles) {
                                    var roleIds = [];
                                    $(roles).each(function (i, item) {
                                        roleIds.push(item.id);
                                    });
                                    u.roleIds = roleIds.join(",");
                                }
                            });
                        });
                        //store.setInternal("result", data);
                    });

                    //定义表格样式
                    var gridCfg = Search.createGridCfg(columns, {
                        //width: 800,
                        height: "auto",
                        emptyDataTpl: '<div class="centered"><h2>查询的数据不存在</h2></div>',
                        tbar: {
                            items: [
                                {
                                    text: '<i class="icon-search"></i>搜索',
                                    btnCls: 'button button-small',
                                    handler: function () {
                                        searchDialog.show();
                                    }
                                }
                            ]
                        },
                        plugins: [editing, Grid.Plugins.AutoFit] // 插件形式引入多选表格
                    });
                    var search = new Search({
                        store: store,
                        gridCfg: gridCfg
                    });
                    var grid = search.get('grid');

                    //--------------初始化新增按钮弹窗Start-----------------
                    var form = new Form.HForm({
                        srcNode: '#role_form',
                        submitType: 'ajax',
                        callback: function (data) {
                            store.load();
                            editDialog.close();
                        }
                    }).render();

                    var searchDialog = new Overlay.Dialog({
                        title: "查询信息",
                        width: 700,
                        height: 180,
                        contentId: "search",
                        buttons: [
                            {
                                text: '搜索', elCls: 'button button-primary', handler: function () {
                                    var condition = {};

                                    $(".search_field").each(function () {
                                        condition[$(this).attr("name")] = $(this).val();
                                    });

                                    condition = $.extend({"start": 0, "pageIndex": 0}, condition);

                                    store.load(condition);
                                    this.close();
                                }
                            },
                            {
                                text: '重置', elCls: 'button', handler: function () {
                                    searchForm.clearFields();
                                }
                            },
                            {
                                text: '关闭', elCls: 'button', handler: function () {
                                    this.close();
                                }
                            }
                        ],
                        success: function (data) {
                            this.close();
                        }
                    });

                    //权限管理窗体
                    var editDialog = new Overlay.Dialog({
                        title: '管理权限',
                        width: '70%',
                        height: 280,
                        buttons: [
                            {
                                text: '保存', elCls: 'button button-primary', handler: function () {
                                    checkedSubmit(form);
                                }
                            },
                            {
                                text: '关闭', elCls: 'button', handler: function () {
                                    this.close();
                                }
                            }],
                        contentId: 'content', //配置DOM容器的编号
                        success: function () {
                            this.close();
                        }
                    });

                    var searchForm = new Form.Form({
                        srcNode: "#search"
                    }).render();

                    function resetAuthForm() {
                        if (editDialog.get("title") === '新增') {
                            form.clearFields();
                            form.clearErrors();
                        } else {
                            reloadUserinfo();
                        }
                    }


                    //bui-gird height bug fix deptree
                    $(".bui-grid-body").height($(document).height() - 200);
                    $(".bui-grid-bbar").height(35);
                    $("#t3").height($(".bui-grid").height());
                    $(".bui-grid-hd-empty.bui-grid-hd").width($(".bui-grid-header").width() - $(".bui-grid-body .bui-grid-table").width())
                    $(".bui-grid-cell.bui-grid-cell-empty").width(0);

                });
        }
    });
</script>
</html>
