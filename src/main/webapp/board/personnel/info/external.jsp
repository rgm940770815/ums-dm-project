<%--
    Document   : external
    Created on : 2015-3-16, 16:25:10
    Author     : Diluka
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
    <title>人员管理</title>
    <jsp:include page="/basic_import.jsp"></jsp:include>
    <script src="<%=basePath%>js/common/idchecker.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/crypto-js/rollups/md5.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/base64/base64.js" type="text/javascript"></script>
    <style type="text/css">
        /**内容超出 出现滚动条 **/
        .bui-stdmod-body {
            overflow-x: hidden;
            overflow-y: auto;
        }

        .my_icon {
            margin-top: 1px !important;
        }

        .nav-tabs li s {
            color: red;
            padding-right: 5px;
            text-decoration: none;
        }

        .button-own {
            width: 125px;
        }

        .grid-valid {
            color: #3366cc;
            cursor: pointer;
            margin-right: 5px;
        }

        .grid-valid:hover {
            color: #ff6600;
        }

    </style>
</head>
<body>

<div id="contentModifiedLog" class="hide">
    <%--表格--%>
    <div id="contentModifiedLog_grid"></div>
</div>

<div class="row">
    <div>
        <ul class="breadcrumb">
            <li><span>人事管理</span><span class="divider">/</span></li>
            <li><span>信息管理</span><span class="divider">/</span></li>
            <li class="active">管理编外人员</li>
        </ul>
    </div>
</div>

<%--树结构--%>
<div id='deptree' class="panel bui-stdmod-body span6">
    <div id="t3"></div>
</div>
<%--表格--%>
<div class="offset6">
    <div class="search-grid-container">
        <div id="grid"></div>
    </div>
</div>
<!-- 复制人员信息-->

<div id="copy_div" class="hide">
    <form id="copy_form" class="form-horizontal" action="<%=path%>/external/copyInfo">
        <div class="row">
            <div class="row">
                <div class="control-group span7">
                    <label class="control-label" style="width: 90px;">姓名：</label>

                    <div class="controls">
                        <input id="name" name="copyname" type="text" placeholder="请输入复制后的姓名"
                               class="control-text search_field" data-rules="{required:true}">
                    </div>
                    <input type="hidden" id="hideId" value="" name="userId">
                </div>
                <div class="control-group span8">
                    <label class="control-label " style="width:200px;">其他信息和被选中的人员信息相同</label>
                </div>
            </div>
        </div>
    </form>
</div>

<div id="DialogInfo" class="hide">

</div>
<%--申请调职窗口--%>
<div id="dz" class="hide">
    <form id="userinfo_dz_form" class="form-horizontal" action="<%=basePath%>userinfo/insertChangeJob" method="post">
        <div class="row" style="float: left">
            <img id="dzpic" src="{url}" style="width: 150px;height: auto"/>
        </div>
        <div style="float: left;width:440px">
            <div class="row">
                <div class="control-group span8">
                    <lable class="control-label">姓名：</lable>
                    <div class="controls">
                        <label id="fullname_"></label>
                        <input id="changeUUID" type="hidden" name="umsChangeJob.changeUUID">
                        <input id="user_id" type="hidden" name="umsChangeJob.user_id">
                        <input id="user_name" type="hidden" name="umsChangeJob.user_name">
                        <input id="apply_user_id" type="hidden" name="umsChangeJob.apply_user_id">
                        <input id="apply_user_name" type="hidden" name="umsChangeJob.apply_user_name">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">性别：</label>

                    <div class="controls">
                        <label id="sex"></label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <lable class="control-label">原法院：</lable>
                    <div class="controls">
                        <label id="old_court_text" name="umsChangeJob.old_court_text"></label>
                        <input type="hidden" id="old_court_text2" name="umsChangeJob.old_court_text">
                        <input id="old_court_no" type="hidden" name="umsChangeJob.old_court_no">
                        <input id="apply_court_no" type="hidden" name="umsChangeJob.apply_court_no">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">原部门：</label>

                    <div class="controls">
                        <label id="old_dep_text" name="umsChangeJob.old_dep_text"></label>
                        <input id="old_dep_text2" type="hidden" name="umsChangeJob.old_dep_text">
                        <input id="old_dep_no" type="hidden" name="umsChangeJob.old_dep_no">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <lable class="control-label">申请法院：</lable>
                    <div class="controls">
                        <select id="new_court_no" name="umsChangeJob.new_court_no"
                                onchange="loadDeptList(new_dept_no, this.value, '<option value=\'\'>请选择</option>')">
                            <option value="">请选择</option>
                        </select>
                        <input id="new_court_text" type="hidden" name="umsChangeJob.new_court_text">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">申请部门：</label>

                    <div class="controls">
                        <select id="new_dept_no" courtNo="" name="umsChangeJob.new_dept_no">
                            <option value="">请选择</option>
                        </select>
                        <input id="new_dept_text" type="hidden" name="umsChangeJob.new_dept_text">
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<%--查询窗口--%>
<div id="search" class="hide">
    <form id="userinfo_search_form" class="form-horizontal">
        <div class="row">
            <div id="nav" class="row">
                <div class="control-group span8">
                    <label class="control-label">姓名：</label>

                    <div class="controls">
                        <input id="fullname" name="fullname" type="text" placeholder="请输入需要查询的姓名"
                               class="control-text search_field">
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
                        <select id="rank" typeId="17" class="code search_field select2_search" name="rank"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">行政职务：</label>

                    <div class="controls">
                        <select id="administrationPosition" typeId="15" name="administrationPosition" type="text"
                                value="" class="code search_field select2_search"></select>
                    </div>
                </div>
            </div>
            <div class="row">

                <div class="control-group span8">
                    <label class="control-label">法院：</label>

                    <div class="controls">
                        <select class="Courtcode search_field" name="innerCourtNo"
                                onchange="loadDeptList('#department_n', this.value, '<option value=\'\'>请选择</option>')"
                        >
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>

                <div class="control-group span8">
                    <label class="control-label">部门：</label>

                    <div class="controls">
                        <select id="department_n" courtNo="" name="department" class="search_field select2_search">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span16">
                    <label class="control-label">进院日期：</label>

                    <div class="controls bui-form-group" data-rules="{dateRange : true}">
                        <input id="enterDateStart" data-tip="{text:'起始日期'}" name="enterDateStart" type="text"
                               placeholder="起始日期"
                               class="calendar search_field calendar-time">
                        &nbsp;-&nbsp;
                        <input id="enterDateEnd" data-tip="{text:'结束日期'}" name="enterDateEnd" type="text"
                               placeholder="结束日期"
                               class="calendar search_field calendar-time">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">是否为法官：</label>
                    <div class="controls">
                        <select id="isFg" name="isFg" class="search_field">
                            <option value="">请选择</option>
                            <option value="1">是</option>
                            <option value="0">否</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<div class="hide">
    <form action="<%=path%>/view/excelDownload" method="post" id="excel_form">

    </form>
</div>

<%--申请改变编制类型窗口--%>
<div id="gbbzlx" class="hide">
    <form id="userinfo_gbbzlx_form" class="form-horizontal" action="<%=basePath%>gbbzlxAction/insertGbbzlx_sq" method="post">
        <div class="row" style="float: left">
            <img id="gbbzlx_pic" src="{url}" style="width: 150px;height: auto"/>
        </div>
        <div style="float: left;width:440px">
            <div class="row">
                <div class="control-group span8">
                    <lable class="control-label">姓名：</lable>
                    <div class="controls">
                        <label id="gbbzlx_fullname" name="user_name"></label>
                        <input name="user_name" type="hidden"></input>
                        <input id="gbbzlx_changeUUID" name="changeUUID" type="hidden">
                        <input id="gbbzlx_user_id" name="user_id" type="hidden">
                        <input id="gbbzlx_sq_time" name="sq_time" type="hidden">
                        <input id="gbbzlx_state" name="state" type="hidden">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">性别：</label>
                    <div class="controls">
                        <label id="gbbzlx_sex"></label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <lable class="control-label">法院：</lable>
                    <div class="controls">
                        <label id="gbbzlx_court_text" name="court_text"></label>
                        <input name="court_text" type="hidden"></input>
                        <input id="gbbzlx_court_no" name="court_no" type="hidden">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">部门：</label>
                    <div class="controls">
                        <label id="gbbzlx_dep_text" name="dep_text"></label>
                        <input name="dep_text" type="hidden"></input>
                        <input id="gbbzlx_dep_no" name="dep_no" type="hidden">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <lable class="control-label">更改为：</lable>
                    <div class="controls">
                        <select id="gbbzlx_sq_content" name="sq_content">
                            <option value="">请选择</option>
                            <option value="编外转在编">在编人员</option>
                            <option value="编外转人民陪审员">人民陪审员</option>
                        </select>
                    </div>
                </div>
                <div class="control-group span16">
                    <lable class="control-label">申请原因</lable>
                    <div class="controls">
                        <textarea id="gbbzlx_sq_reason" name="sq_reason" style="width: 280px; height: 100px;"></textarea>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<jsp:include page="/fragment/tip.jsp"></jsp:include>
</body>

<%--Script Begin--%>
<script type="text/javascript" src="<%=basePath%>js/common/codelist.js"></script>
<script src="<%=basePath%>js/common/datetools.js" type="text/javascript"></script>
<script>
    //-------------日期规范输出Start---------------
    function dateFormat(value) {
        return value + '年';
    }

    function ageFormat(value) {
        return value + '岁';
    }

    //-------------日期规范输出End--------------

    //-------------计算日期相关函数Start---------------
    function calcAge() {
        $("#userinfo_form #age").text(calcYears($("#userinfo_form #birthday").val()) + '岁');
    }

    function workTotal() {
        $("#userinfo_form #totalSeniority").text(workTotalYears($("#userinfo_form #workDate").val(), $("#userinfo_form #extraSeniority").val(), $("#userinfo_form #deductionSeniority").val()) + '年');
    }

    function courtTotal() {
        $("#userinfo_form #totalCourtYear").text(workTotalYears($("#userinfo_form #enterDate").val(), $("#userinfo_form #beforeCourtWorkYear").val()) + '年');
    }

    //-------------计算日期相关函数End---------------


    //-------------初始化下拉框数据Start---------------
    var firstLine = '<option value="">请选择</option>';
    $(function () {
        $("select.code").each(function () {
            loadCodeList(this, firstLine);
        });

        $.getJSON("/ums/code/codeListByType",
            {
                typeId: 1
            },
            function (data) {
                for (var i = 0; i < data.data.length; i++) {
                    $("select.Courtcode").append($("<option>").attr({"value": data.data[i].id}).text(data.data[i].codeName));
                }
                $("select.Courtcode").select2();
                if (data.auth) {
                    auth = true;
                    CourtNo = data.value;
                } else {
                    CourtNo = data.value;
                }

            })


        //隐藏div
        $(document).click(function () {

            if ($("#leaveReasonDiv").is(":visible") && closeFlag) {
                $("#leaveReasonDiv").hide();
                closeFlag = false;
            }

        })


        //判断是否是超姐管理员 主要是用于限制删除
        var c = '${param.c}';
        if (c == 1) {
            $.post("<%=path%>/IsSuperUser", {}, function (data) {
                if (data.res) {
                    canDelete = true;
                }

            })
        }

    });
    //-------------初始化下拉框数据End---------------

    var CourtNo = '';
    var auth = false;
    var V = $.extend({}, V, parent.V);
    var copyDialog;
    var isLoadBadeDialog = false;
    var isEdit = false;
    var closeFlag = false;
    var DzForm;
    var startRow;
    var current_court = null;
    var query_condition = {
        userType: 2,
        isInfoComplete: 0,
        isValid: 1,
    };
    var canDelete = false;
    //初始化BUI相关组件
    BUI.use(['common/search', 'bui/tree', 'bui/data', 'bui/calendar', 'bui/overlay', 'bui/form', 'bui/uploader'], function (Search, Tree, Data, Calendar, Overlay, Form, Uploader) {

        // 显示用户被操作的日志窗体
        var modifiedLogDialog = new Overlay.Dialog({
            title: '该人员被操作的日志',
            width: 1000,
            height: 500,
            buttons: [{
                text: '关闭', elCls: 'button', handler: function () {
                    this.close();
                }
            }],
            contentId: 'contentModifiedLog' //配置DOM容器的编号
        });
        //-------------初始化表格Start---------------
        var userinfoActionUrl = "<%=basePath%>view/userinfo2";
        var editing = new BUI.Grid.Plugins.DialogEditing({
            contentId: 'content', //设置隐藏的Dialog内容
            autoSave: true, //添加数据或者修改数据时，自动保存
            triggerCls: 'btn-edit'
        });
        var store = Search.createStore(userinfoActionUrl, {
            sortField: 'sortNo',
            sortDirection: 'ASC',
            remoteSort: true,
            pageSize: 20,
            proxy: {
                save: {//也可以是一个字符串，那么增删改，都会往那么路径提交数据，同时附加参数saveType
                    //addUrl: '../data/add.json',
                    //updateUrl: '../data/edit.json',
                    removeUrl: '<%=basePath%>userinfo/delete'
                }/*,
                         method : 'POST'*/
            },
            params: {
                userType: 2,
                isInfoComplete: 0,
                isValid: 1,
            },
            autoSync: true //保存数据后，自动更新
        });
        //定义表格列
        var columns = [
            {
                title: '序号', sortable: false, width: "50", align: 'left', renderer: function (value, obj) {
                    return startRow++;
                }
            },
            {
                title: '姓名', dataIndex: 'fullname', width: "100", sortable: true, align: 'left', renderer: function (value, obj) {
                    return '<span class="grid-command btn-detail" title="显示人员详情信息">' + value + '</span>';
                }
            },
            {title: '性别', dataIndex: 'genderText', width: "50", sortable: true, align: 'left'},
            {
                title: '年龄', width: "80", align: 'center', renderer: function (value, obj) {
                    if (obj.birthday != null) {
                        var calcYears2 = calcYears(obj.birthday);
                        if (!isNaN(calcYears2)) {
                            return calcYears2 + "岁";
                        }
                    }

                }
            },
            {title: '法院', dataIndex: 'courtNoText', width: "200", sortable: true, align: 'left'},
            {title: '部门', dataIndex: 'departmentText', width: "180", sortable: true, align: 'left'},
            {title: '籍贯', dataIndex: 'hometown', width: "150", sortable: true, align: 'left'},
            {
                title: '民族',
                dataIndex: 'nationText',
                width: "120",
                sortable: true,
                align: 'left'
            },
            {
                title: '操作', width: "400", sortable: false, renderer: function (value, obj) {
                    var spanStr = '<span class="grid-command btn-detail" title="显示人员详情信息">查看详情</span>';

                    if (obj.isValid == 1) {
                        if (obj.leaveReason != null && obj.leaveReason == 5) {
                            spanStr += '<span class="grid-valid  btn-enabled  btn-rehire" title="取消返聘">取消返聘</span>';
                        }

                        spanStr += '<span class="grid-valid  hide btn-enabled" title="启用">启用</span>' +
                            '<span class="grid-valid  btn-enabled" title="停用">停用</span>';
                    } else {
                        spanStr += '<span class="grid-valid  btn-enabled" title="启用">启用</span>' +
                            '<span class="grid-valid  btn-enabled  hide" title="停用">停用</span>';
                    }
                    spanStr += '<span class="grid-command btn-dz" title="申请调职">申请调职</span>';
                    spanStr += '<span class="grid-command btn-blxtdl" title="保留系统登录">保留系统登录</span>';
                    spanStr += '<span class="grid-command btn-gbbzlx" title="申请改变编制类型">申请改变编制类型</span>';
                    return spanStr;
                }
            }
        ];
        V.store = store;
        var searchDialog = new Overlay.Dialog({
            title: "查询信息",
            width: 700,
            contentId: "search",
            buttons: [
                {
                    text: '搜索', elCls: 'button button-primary search', handler: function () {
                        var condition = {};

                        $(".search_field").each(function () {
                            condition[$(this).attr("name")] = $(this).val();
                        });

                        condition = $.extend({"start": 0, "pageIndex": 0}, condition);

                        store.load(condition);
                        query_condition = store.get("lastParams");
                        this.close();
                    }
                },
                {
                    text: '重置', elCls: 'button', handler: function () {
                        initSelect2("select2_search");
                        $("select.Courtcode").select2();
                        searchForm.clearFields();
                        searchForm.clearErrors();

                        if (current_court != null && current_court != '') {
                            $("select[name='innerCourtNo']").attr("disabled", false);
                            $("select[name='innerCourtNo']").val(current_court);
                            $("select[name='innerCourtNo']").attr("disabled", true);

                        }
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
        searchDialog.on("closing", function () {
            searchForm.clearFields();
        })
        store.on("load", function () {
            startRow = store.get("start") + 1;
            canEdit();
        })
        var copyForm = new Form.HForm({
            srcNode: '#copy_form',
            submitType: 'ajax',
            callback: function (data) {
                if (data) {
                    showTip("添加成功！");
                    copyDialog.close();
                    store.load();
                } else {
                    showTip("添加失败");
                }
            }
        }).render();
        copyDialog = new Overlay.Dialog({
            title: "复制信息",
            contentId: "copy_div",
            buttons: [
                {
                    text: '复制信息', elCls: 'button button-primary', handler: function () {
                        var selected = grid.getSelected();
                        if (selected) {
                            copyForm.submit();
                        }
                    }
                },
                {
                    text: '关闭', elCls: 'button', handler: function () {
                        this.close();
                    }
                }
            ],
        });
        copyDialog.on("closing", function () {
            $("#name").val('');
            $("#hideId").val('');
        });
        var searchForm = new Form.Form({
            srcNode: "#search"
        }).render();
        var tbarData = {
            items: [
                {
                    text: '<i class="icon-search my_icon"></i>搜索',
                    btnCls: 'button button-small',
                    handler: function () {
                        $("select[name=innerCourtNo]").select2().change();
                        initSelect2("select2_search");
                        if (current_court != null && current_court != '') {
                            $("select[name='innerCourtNo']").attr("disabled", false);
                            $("select[name='innerCourtNo']").val(current_court);
                            $("select[name='innerCourtNo']").attr("disabled", true);

                        }
                        searchDialog.show();
                    }
                },
                {
                    text: '<i class="icon-plus my_icon"></i>添加',
                    btnCls: 'button button-small',
                    handler: function () {
                        V.dialog.destroy();
                        makeDialog();
                        isEdit = false;
                        V.dialog.set("title", "新增");
                        V.dialog.set('headerContent',
                            '<ul class="nav-tabs"> <li><a style="border: 1px solid white;color:inherit;">新增</a></li>' +
                            '<li class="active"><a href="#basic_info" onclick="changeMarkers(this)" class="initClass"><s>*</s>基本信息</a></li> ' +
                            '<li><a href="#degree_info" onclick="changeMarkers(this)">学历学位</a></li>' +
                            '<li><a href="#card_info" onclick="changeMarkers(this)">证件信息</a></li>' +
                            '<li><a href="#ext_info" onclick="changeMarkers(this)"><s>*</s>编外人员信息</a></li><li><a href="#company_info" onclick="changeMarkers(this)"><s>*</s>公司信息</a></li>' +
                            '<li><a href="#photo_info" onclick="changeMarkers(this)">头像上传</a></li>');
                        loadDialogInfo();
                        form.clearErrors();

                    }
                },
                {
                    text: '<i class="icon-edit my_icon"></i>编辑',
                    btnCls: 'button button-small',
                    handler: function () {
                        form.clearErrors();
                        var userinfo = grid.getSelected();
                        if (userinfo) {
                            V.dialog.destroy();
                            makeDialog();
                            isEdit = true;
                            V.dialog.set("title", "编辑");
                            V.dialog.set('headerContent',
                                '<ul class="nav-tabs"> <li><a style="border: 1px solid white;color:inherit;">编辑</a></li>' +
                                '<li class="active"><a href="#basic_info" onclick="changeMarkers(this)" class="initClass"><s>*</s>基本信息</a></li> ' +
                                '<li><a href="#degree_info" onclick="changeMarkers(this)">学历学位</a></li>' +
                                '<li><a href="#card_info" onclick="changeMarkers(this)">证件信息</a></li>' +
                                '<li><a href="#ext_info" onclick="changeMarkers(this)"><s>*</s>编外人员信息</a></li><li><a href="#company_info" onclick="changeMarkers(this)"><s>*</s>公司信息</a></li>' +
                                '<li><a href="#photo_info" onclick="changeMarkers(this)">头像上传</a></li>');
                            loadDialogInfo(userinfo.id);
                        } else {
                            BUI.Message.Alert("请选择一条记录", null, "info");
                        }
                    }
                },
                {
                    text: '<i class="icon-inbox my_icon"></i>复制人员信息',
                    btnCls: 'button button-small',
                    handler: function () {
                        var sel = grid.getSelected();
                        if (sel) {
                            $("#hideId").val(sel.id);
                            copyDialog.show();
                        } else {
                            BUI.Message.Alert("请选择一条记录", null, "info");
                        }

                    }
                }, {
                    text: '<i class="icon-zoom-in"></i>显示补录信息',
                    btnCls: 'button button-small button-one',
                    handler: function () {
                        var condition = {};
                        condition['isInfoComplete'] = 1;
                        condition['isValid'] = '';

                        store.load(condition);
                        query_condition = store.get("lastParams");
                        $(".button-one").parent().hide();
                        $(".button-two").parent().show();
                    }
                },
                {
                    text: '<i class="icon-zoom-out"></i>隐藏补录信息',
                    btnCls: 'button button-small button-two',
                    handler: function () {
                        var condition = {};
                        condition['isInfoComplete'] = 0;
                        condition['isValid'] = $(".selectDiv").attr('codeId') + 1;
                        store.load(condition);
                        query_condition = store.get("lastParams");
                        $(".button-one").parent().show();
                        $(".button-two").parent().hide();
                    }
                },
                {
                    text: '<i class=" icon-info-sign"></i>人员状态 - <span id="isValidShow">启用</span>' +
                        '<div id="leaveReasonDiv" class="hide" style="width:113px; position: absolute;background-color: #F5F5F5;margin-left:-10px;margin-top: 4px;z-index: 1001;padding: 6px ;">' +
                        '</div>',
                    btnCls: 'button button-small button-own',
                    handler: function () {

                        if ($("#leaveReasonDiv").is(":hidden")) {
                            var timer = setTimeout(function () {
                                closeFlag = true;
                            }, 300);
                            $("#leaveReasonDiv").show()
                        } else {
                            closeFlag = false;
                            $("#leaveReasonDiv").hide()
                        }
                    }
                },
                {
                    text: '<i class="icon-download-alt"></i>人员信息excel下载',
                    btnCls: 'button button-small ',
                    handler: function () {

                        BUI.Message.Confirm(
                            'excel会按照当前查询出来的信息进行生成,如果数据量较大请稍等,勿重复点击生成。是否按照此信息生成excel?',
                            function () {
                                //要拼接参数
                                var form = $("form[id='excel_form']");
                                form.html("");
                                form.append("<input type='text' name='excelDataType'  value='2' >")
                                for (var i in query_condition) {
                                    form.append("<input type='text' name='" + i + "'  value='" + query_condition[i] + "'>")

                                }
                                form.submit();
                            },
                            'question');


                    }
                }
                <%--, {--%>
                <%--    text: '<i class="icon-edit"></i>批量添加信息',--%>
                <%--    btnCls: 'button button-small',--%>
                <%--    handler: function () {--%>
                <%--        if (courNo == null || courNo == '' || depNo == null || depNo == '') {--%>
                <%--            BUI.Message.Alert("请先选择部门", null, "warning");--%>
                <%--            return;--%>
                <%--        }--%>
                <%--        window.open("<%=basePath%>view/batchEdit?courtNo=" + courNo + "&deptNo=" + depNo);--%>
                <%--    }--%>
                <%--},--%>
            ]
        };
        if (canDelete) {
            var del = {
                text: '<i class="icon-remove my_icon"></i>删除',
                btnCls: 'button button-small',
                handler: function () {
                    var userinfo = grid.getSelected();
                    if (userinfo) {
                        BUI.Message.Confirm(
                            '确定要删除“' + userinfo.fullname + '”吗？',
                            function () {
                                store.save('remove', {id: userinfo.id}, function (data) {
                                    if (!data.success && data.result === -1) {
                                        BUI.Message.Alert("非法操作，当前用户无此权限！", null, "warning");
                                    }
                                });
                            },
                            'question');
                    } else {
                        BUI.Message.Alert("请选择一条记录", null, "info");
                    }

                }
            };
            tbarData.items.push(del);
        }
        //定义表格样式
        var gridCfg = Search.createGridCfg(columns, {
            //width: 600,
            height: "auto",
            //forceFit: true,
            emptyDataTpl: '<div class="centered"><h2>查询的数据不存在</h2></div>',
            tbar: tbarData,
            plugins: [editing, BUI.Grid.Plugins.RadioSelection, BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格
        });
        //申请调职
        var dzDialog = new Overlay.Dialog({
            title: "申请调职",
            width: 750,
            contentId: "dz",
            buttons: [
                {
                    text: '申请调职', elCls: 'button button-primary', handler: function () {
                        $("#new_dept_text").attr("value", $("#new_dept_no").find("option:selected").text());
                        if ($("#new_dept_no").find("option:selected").text() == "请选择") {
                            BUI.Message.Alert("请选择法院和部门！", null, "warning");
                            return;
                        }
                        if ($("#new_court_no").find("option:selected").text() == $("#old_court_text2").val() &&
                            $("#new_court_no").find("option:selected").text() == $("#old_dep_text2").val()) {
                            BUI.Message.Alert("请不要选择相同的法院和部门！", null, "warning");
                            return;
                        }
                        sqdz();
                    }
                },
                {
                    text: '关闭', elCls: 'button', handler: function () {
                        dform.clearFields();
                        this.close();
                    }
                }
            ],
            header: "",
            success: function (data) {
                this.close();
            }
        });
        var dform = new Form.HForm({
            srcNode: '#userinfo_dz_form',
            submitType: 'ajax',
            callback: function (data) {
                if (data.success) {
                    BUI.Message.Alert("申请成功！", null, "success");
                } else {
                    BUI.Message.Alert("申请失败", null, "warning");
                }
                dform.clearFields();
                dzDialog.close();
            }
        }).render();
        dzForm = dform;

        var gbbzlxDialog = new Overlay.Dialog({
            title: "申请改变编制类型",
            width: 680,
            height: 500,
            contentId: "gbbzlx",
            buttons: [
                {
                    text: '提交申请', elCls: 'button button-primary', handler: function () {
                        sqgbbzlx();
                    }
                },
                {
                    text: '关闭', elCls: 'button', handler: function () {
                        dform.clearFields();
                        this.close();
                    }
                }
            ],
            header: "",
            success: function (data) {
                this.close();
            }
        });
        var gbbzlxform = new Form.HForm({
            srcNode: '#userinfo_gbbzlx_form', submitType: 'ajax', callback: function (data) {
                if (data.results == 1) {
                    BUI.Message.Alert("申请成功！", null, "success");
                } else {
                    BUI.Message.Alert("申请失败", null, "warning");
                }
                gbbzlxform.clearFields();
                gbbzlxDialog.close();
            }
        }).render();

        // 申请改变编制类型 sqgbbzlx：申请改变编制类型
        function sqgbbzlx() {

            if ($("#gbbzlx_sq_content").val() == "" || $("#gbbzlx_sq_reason").val() == "") {
                BUI.Message.Alert("您还有数据未填写", "warning");
                return;
            }

            if ($("#gbbzlx_sq_reason").val().length > 200) {
                BUI.Message.Alert("当前文字数量：" + $("#gbbzlx_sq_reason").val().length + "。不能超过200字。", null, "warning");
                return;
            }

            $.post("<%=path%>/gbbzlxAction/sfysq", {user_id: $("#gbbzlx_user_id").val(), state: "申请中"}, function (data) {
                if (data.results > 0) {
                    BUI.Message.Alert("您还有未处理的改变编制申请，请不要重复提交！", "warning");
                    gbbzlxform.clearFields();
                } else {
                    gbbzlxform.submit();
                }
            });
            gbbzlxDialog.close();
        }

        function sqdz() {

            var url = "<%=path%>/userinfo/selectChangeJob";
            var datas = {
                id: $("#user_id").val()
            }
            $.post(url, datas, function (data) {
                if (data.success) {
                    BUI.Message.Alert("您还有未处理的调职申请，请不要重复提交！", "warning");
                    dform.clearFields();
                    return;
                }
                dzForm.submit();
            });
            dzDialog.close();
        }

        function loadAllCourtList(cbo, firstLine, callback) {
            $.getJSON("/ums/code/codeListByTypeWithNoAspect", {}, function (data) {
                if (firstLine) {
                    $(cbo).html(firstLine);
                } else {
                    $(cbo).empty();
                }
                $.each(data, function (index, values) {   // 解析出data对应的Object数组
                    $.each(values, function (index2, value) {
                        $(cbo).append($("<option>").attr({"value": value.id}).text(value.codeName));
                    });
                });
                if (callback) {
                    callback();
                }
            });
        }

        var search = new Search({
            store: store,
            gridCfg: gridCfg
        });
        var grid = search.get('grid');
        $("#btnSearch2").click(function () {
            var condition = {};

            $(".search_field").each(function () {
                condition[$(this).attr("name")] = $(this).val();
            });

            store.load(condition);
            query_condition = store.get("lastParams");
        });
        //监听事件，删除一条记录
        grid.on('cellclick', function (ev) {
            var sender = $(ev.domTarget); //点击的Dom
            if (sender.hasClass('btn-detail')) {
                var record = ev.record;
                open("<%=basePath%>view/detail?id=" + record.id);
            }
            //是返聘操作

            if (sender.hasClass('btn-enabled')) {
                var record = ev.record;
                var url = "<%=path%>/userinfo/enabled";
                var i = record.isValid == 1 ? 0 : 1;
                var datas = {}
                datas['userInfo.isValid'] = i;
                datas['userInfo.id'] = record.id;
                if (sender.hasClass("btn-rehire")) {
                    datas['enableType'] = 1;
                }
                if (record.isValid != 1) {

                    //先验证用户名
                    var url_u = "<%=path%>/checkUserName";
                    var datas_u = {
                        courtStdNo: record.courtNo,
                        username: record.username,
                        valid: 1,
                        userType: 2
                    }
                    //身份证验证
                    var url_ = "<%=path%>/checkIdCard";
                    var da = {
                        idcard: record.idcard,
                        valid: 1
                    };

                    isEdit = true;
                    datas_u = {
                        courtStdNo: record.courtNo,
                        username: record.username,
                        valid: 1,
                        isEdit: isEdit
                    };

                    $.post(url_u, datas_u, function (data) {

                        if (data.UserNameCheck == "false") {

                            isEdit = true;
                            var da = {
                                idcard: record.idcard,
                                valid: 1,
                                isEdit: isEdit
                            };

                            $.post(url_, da, function (data) {
                                if (data.idCardCheck) {
                                    $.post(url, datas, function () {
                                        store.load();
                                    });
                                } else {
                                    var typeStr = "";
                                    switch (data.userType) {
                                        case 1 :
                                            typeStr += "正式人员"
                                            break;
                                        case 2 :
                                            typeStr += "编外人员"
                                            break;
                                        case 3 :
                                            typeStr += "人民陪审员"
                                            break;
                                    }
                                    var enabledStr = "";
                                    switch (data.enabled) {
                                        case 0 :
                                            enabledStr += "停用"
                                            break;
                                        case 1 :
                                            enabledStr += "启用"
                                            break;
                                    }
                                    var showStr = "<span>该人员已经存在,请对相应人员进行操作  ";
                                    showStr += (typeStr == "" ? "" : " , 人员类型为 : " + typeStr);
                                    showStr += (typeStr == "" ? "" : " , 人员状态为 : " + enabledStr);
                                    showStr += ("<a style='margin-left:10px;' target='_blank' href='<%=basePath%>view/detail?id=" + data.userId + " '>点击查看</a></span>");
                                    BUI.Message.Alert(showStr, "warning");

                                }
                            })
                        } else {
                            BUI.Message.Alert("用户名已经存在,请更改用户名后再启用", "warning");
                        }

                    });
                } else {
                    $.post(url, datas, function () {
                        store.load();
                    });
                }
            }
            if (sender.hasClass('btn-dz')) {
                var record = ev.record;
                $("#fullname_").html(record.fullname);
                $("#user_id").val(record.id);
                $("#user_name").val(record.fullname);
                $("#apply_user_id").val("${loginUser.id}");
                $("#apply_user_name").val("${loginUser.fullname}");
                $("#sex").html(record.genderText);
                $("#old_court_no").val(record.courtNo);
                $("#apply_court_no").val(record.courtNo);
                $("#old_court_text").html(record.courtNoText);
                $("#old_dep_no").val(record.department);
                $("#old_dep_text").html(record.departmentText);
                $("#old_dep_text2").attr("value", record.departmentText);
                $.getJSON("<%=basePath%>photo/getPhotoById", {userId: record.id}, function (data) {
                    $("#dzpic").attr("src", data);
                });
                $.getJSON("<%=basePath%>code/newUUID", {_: new Date()}, function (data) {
                    $("#userinfo_dz_form #changeUUID").val(data);
                });
                $.post("<%=path%>/code/deptDetailInfo",
                    {courtNo: $("#old_court_no").val(), deptNo: $("#old_dep_no").val()}, function (data) {
                        var a = data.courtShortName;
                        $("#old_court_text2").attr("value", data.courtShortName);
                    });
                loadAllCourtList("#new_court_no", '<option value=\'\'>请选择</option>');
                dzDialog.show();
                return;
            }

            if (sender.hasClass("btn-blxtdl")) {
                var record = ev.record;
                var url = "<%=path%>/userinfo/enabled";
                var datas = {};
                datas['userInfo.isValid'] = 3;
                datas['userInfo.id'] = record.id;
                $.post(url, datas, function (res) {
                    if (res && res.success) {
                        BUI.Message.Alert("操作成功！", null, "success");
                    }
                    store.load();
                });

            }

            // 申请改变编制类型
            if (sender.hasClass('btn-gbbzlx')) {
                var record = ev.record;
                // 显示的用html()
                $("#gbbzlx_fullname").html(record.fullname);
                $("input[name='user_name']").val(record.fullname);
                // 隐藏的用val()
                $("#gbbzlx_user_id").val(record.id);
                $("#gbbzlx_court_text").html(record.courtNoText);
                $("input[name='court_text']").val(record.courtNoText);
                $("#gbbzlx_court_no").val(record.courtNo);
                $("#gbbzlx_dep_text").html(record.departmentText);
                $("input[name='dep_text']").val(record.departmentText);
                $("#gbbzlx_dep_no").val(record.department);
                $("#gbbzlx_sex").html(record.genderText);
                $("#gbbzlx_sq_time").val(getNow());
                $("#gbbzlx_state").val("申请中");
                // 照片
                $.getJSON("<%=basePath%>photo/getPhotoById", {userId: record.id}, function (data) {
                    $("#gbbzlx_pic").attr("src", data);
                });
                $.getJSON("<%=basePath%>code/newUUID", {}, function (data) {
                    $("#gbbzlx_changeUUID").val(data);
                });
                gbbzlxDialog.show();
                return;
            }
        });
        $(".button-one").parent().show();
        $(".button-two").parent().hide();
        //-------------初始化表格End---------------
        $("#leaveReasonDiv").html('');
        $("#leaveReasonDiv").append('<div><a  onclick="TypeChange(this)" codeId=0 class="selectDiv">启用</a></div>');
        $("#leaveReasonDiv").append('<div><a  onclick="TypeChange(this)" codeId=-1 >停用</a></div>');
        $("#leaveReasonDiv").append('<div><a  onclick="TypeChange(this)" codeId=-2 >返聘</a></div>');
        $("#leaveReasonDiv").append('<div><a  onclick="TypeChange(this)" codeId=-3 >保留系统登录</a></div>');

        //-------------树结构Start-----------------
        var treestore = new Data.TreeStore({
            root: {
                text: "全部",
                courtNo: "",
                deptNo: ""
            },
            url: '<%=basePath%>code/tree/children3?type=2'
        });
        var tree = new Tree.TreeList({
            render: '#t3',
            store: treestore,
            elStyle: {border: "none"},
            //checkType: 'all',
            multipleCheck: false,
            showRoot: true
        });
        tree.render();
        treestore.load();//加载根节点，也可以让用户点击加载
        var courNo, depNo;
        tree.on('itemclick', function (ev) {
            var item = ev.item;
            courNo = item.courtNo;
            depNo = item.deptNo;
            var condition = {courtNo: item.courtNo, deptNo: item.deptNo};
            searchForm.clearFields();

            $(".search_field").each(function () {
                var $name = $(this).attr("name");
                var $val = $(this).val();
                console.log($name);
                console.log($val);
                condition[$(this).attr("name")] = $(this).val();
            });
            condition = $.extend({"start": 0, "pageIndex": 0}, condition);
            store.load(condition);
            query_condition = store.get("lastParams");
            $('#log').text(item.id + ":" + item.text);

            //查询条件
            current_court = item.courtNo;
            $("select[name='innerCourtNo']").attr("disabled", false);
            if (current_court != null && current_court != '') {

                $("select[name='innerCourtNo']").val(current_court);
                $("select[name='innerCourtNo']").attr("disabled", true);

                loadDeptList($("#department_n"), current_court, firstLine, function () {
                });
            }
        });
        //-------------树结构End-----------------
        //--------------初始化新增按钮弹窗Start-----------------
        var form = new Form.HForm({
            srcNode: '#userinfo_form',
            submitType: 'ajax',
            callback: function (data) {
                if (data.success && photoEdit) {
                    $.getJSON("<%=basePath%>photo/save", {
                        userId: $("#userinfo_form #id").val(),
                        _: new Date()
                    }, function () {
                    });
                } else if (data.result === -1) {
                    BUI.Message.Alert("非法操作，当前用户无此权限！", null, "warning");
                }
                store.load();
                V.dialog.close();
            }
        }).render();
        //----------初始化日期插件Start-----------
        var datepicker = new Calendar.DatePicker({
            trigger: '.calendar',
            autoRender: true
        });
        //----------初始化日期插件End--------------
        //异步窗体
        makeDialog();
        <%--var dialog = new Overlay.Dialog({--%>
        <%--title: '新增',--%>
        <%--width: '90%',--%>
        <%--height: 450,--%>
        <%--&lt;%&ndash;loader: {&ndash;%&gt;--%>
        <%--&lt;%&ndash;url: '<%=basePath%>external/form',&ndash;%&gt;--%>
        <%--&lt;%&ndash;autoLoad: false, //不自动加载&ndash;%&gt;--%>
        <%--&lt;%&ndash;lazyLoad: false&ndash;%&gt;--%>
        <%--&lt;%&ndash;},&ndash;%&gt;--%>
        <%--contentId: 'DialogInfo',--%>
        <%--success: function () {--%>
        <%--V.form && V.form.submit();--%>
        <%--}--%>
        <%--});--%>
        <%--V.dialog = dialog;--%>
        <%--dialog.on("closing", function () {--%>
        <%--V.form.clearFields();--%>
        <%--V.form.clearErrors();--%>
        <%--})--%>
        //--------------图片上传功能Start-----------------
        /**
         * 返回数据的格式
         *
         *  默认是 {url : 'url'},否则认为上传失败
         *  可以通过isSuccess 更改判定成功失败的结构
         */
        var uploader = new Uploader.Uploader({
            //指定使用主题
            theme: 'imageView',
            render: '#photo',
            url: '<%=basePath%>photo/upload',
            name: "photo",
            types: ['iframe'],
            queue: {
                resultTpl: {
                    'success': '<div class="success"><img id="qpic" src="{url}" title="{name}" style="width: 100%"/></div>',
                    'error': '<div class="error"><span class="uploader-error">{msg}</span></div>'
                }
            },
            rules: {
                //文的类型
                ext: ['.png,.jpg,.jpeg,.gif,.bmp', '文件类型只能为{0}'],
                //上传的最大个数
                max: [1, '文件的最大个数不能超过{0}个'],
                //文件大小的最小值,这个单位是kb
                //minSize: [10, '文件的大小不能小于{0}KB'],
                //文件大小的最大值,单位也是kb
                maxSize: [10240, '文件大小不能大于10M']
            }
        }).render();
        var queue = uploader.get("queue");
        queue.on("itemremoved", function (e) {
            photoEdit = true;
        });
        uploader.on("change", function (e) {
            photoEdit = true;
        });
        //--------------图片上传功能End-----------------
        var photoEdit = false;
        var userinfo = {};

        function reloadUserinfo() {
            $("#userinfo_form [name^='userInfo.']").each(function () {
                $(this).val(userinfo[$(this).attr("id")]);
                if ($(this).attr("id") === "courtNo") {
                    loadDeptList($("#userinfo_form #department"), $(this).val(), firstLine, function () {
                        $("#userinfo_form #department").val(userinfo.department);
                    });
                }
            });

            $.getJSON("<%=basePath%>photo/getPhotoById", {userId: userinfo.id, _: new Date()}, function (data) {
                if (data !== null) {
                    queue.setItems([{success: true, ext: '.jpg', name: '原始照片.jpg', url: data}]);
                } else {
                    queue.setItems([]);
                }
            });

            photoEdit = false;
            calcAge();
            workTotal();
            courtTotal();
        }

        function loadUserinfo(id) {
            form.clearFields();
            form.clearErrors();
            $.getJSON("<%=basePath%>userinfo/one", {id: id, _: new Date()}, function (data) {
                userinfo = data;
                V.userinfo = userinfo;
                reloadUserinfo();
            });
        }

        function resetUserinfoForm() {
            if (editDialog.get("title") === '新增') {
                form.clearFields();
                form.clearErrors();
            } else {
                reloadUserinfo();
            }
        }

        Form.Rules.add({
            name: "idcheck",
            msg: "身份证格式有误！",
            validator: function (value, baseValue, formatMsg) {
                //alert(checkId(value));
                //alert(checkId("50010619860414213X"));
                if (!checkId(value)) {
                    return formatMsg;
                }
                $('#birthday').val(getBirthdatByIdNo(value));
                $('#birthday_show').html(getBirthdatByIdNo(value));
                calcAge();
                var birthday = $('#birthday').val().replace(/-/g, '');
                if (birthday && birthday !== '') {

                    switch (value.length) {
                        case 18:
                            if (value.slice(6, 14) !== birthday) {
                                return "出生日期与身份证信息不符！";
                            }
                            if (!isEdit) {
                                //用身份证后六位为密码
//                                        var aStr = value.slice(12, 18);
//                                        $("#password").val(epassword(aStr));
                                $("#password").val('admin');

                            }
                            break;
                        case 15:
                            if ('19' + value.slice(6, 12) !== birthday) {
                                return "出生日期与身份证信息不符！";
                            }
                            if (!isEdit) {
                                //用身份证后六位为密码
//                                        var aStr = value.slice(9, 15);
//                                        $("#password").val(epassword(aStr));
                                $("#password").val('admin');

                            }
                            break;
                    }
                }

            }
        });
        Form.Rules.add({
            name: "datecheck1",
            msg: "日期不能大于今天！",
            validator: function (value, baseValue, formatMsg) {
                if (new Date(value.replace(/-/g, '/')) > new Date()) {
                    return formatMsg;
                }
            }
        });
        Form.Rules.add({
            name: 'phoneNumber',
            msg: '请填写{0}位数字的电话号码。',
            validator: function (value, baseValue, formatMsg) {
                // var regexp = new RegExp('^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$');
                // var regexp = new RegExp('^(0|86|17951)?(13[0-9]|14[56789]|15[012356789]|16[6]|17[12345678]|18[0-9]|19[89])[0-9]{8}$');
                var regexp = new RegExp('^[0-9]{11}$');
                if (value && !regexp.test(value)) {
                    return formatMsg;
                }
            }
        });
        //bui-gird height bug fix deptree
        $(".bui-grid-body").height($(document).height() - 200);
        $(".bui-grid-bbar").height(35);
        $("#t3").height($(".bui-grid").height());
//                    $(".bui-grid-hd-empty.bui-grid-hd").width($(".bui-grid-header").width() - $(".bui-grid-body .bui-grid-table").width())
//                    $(".bui-grid-cell.bui-grid-cell-empty").width(0);
    });

    function canEdit() {
        $.getJSON("<%=basePath%>auth/canIEdit", {}, function (can) {
            if (!can) {
                $(".bui-bar.bui-grid-button-bar button").filter(":gt(0)").addClass("hide");
                $(".btn-enabled").remove();
                $(".btn-dz").remove();
                $(".search").filter(":gt(0)").remove("hide");
            }
        });
    }

    function changeMarkers(obj) {
        $(obj).parent().parent().find(".active").removeClass("active");
        $(obj).parent().addClass("active");
    }

    function loadDialogInfo(obj) {
        var url = '<%=basePath%>external/form';
        var datas = {}
        if (obj != null) {
            datas['userId'] = obj;
        }
        $.post(url, datas, function (data) {
            $("#DialogInfo").empty();
            $("#DialogInfo").html(data);
            V.dialog.show();
            $("#courtNo").val(CourtNo);
            loadDeptList($("#J_Form #department"), CourtNo, firstLine, function () {
            });

            if (auth) {
            } else {
                $("#courtNo").attr("disabled", true);
            }
            if ($(".initClass").length > 0) {
                location.href = $(".initClass").attr("href");
                $(".initClass").click();
            }
            if (obj == null) {
                setTimeout(function () {
                    $("#courtNo").val(CourtNo);
                    $("#courtNo").select2();
                    initSelect2("select22");
                    V.form.clearErrors();
                }, 1000);
            }
            // 设置用户名的提示，编辑时不可以修改用户名
            $("#username").attr("readonly", "readonly");
            $("#username").focus(function () {
                $(".show_info_edit").show();
                $(".show_info_ok").hide();
                $(".show_icon_ok").hide();
            });
            $("#username").blur(function () {
                $(".show_info_edit").hide();
            });
        })
    }

    function makeDialog() {

        BUI.use(['bui/overlay'], function (Overlay) {
            var dialog = new Overlay.Dialog({
                title: '新增',
                width: '90%',
                height: 450,
                <%--loader: {--%>
                <%--url: '<%=basePath%>external/form',--%>
                <%--autoLoad: false, //不自动加载--%>
                <%--lazyLoad: false--%>
                <%--},--%>
                contentId: 'contentForRead',
                success: function () {
                    if ($("#id").val() != '') {
                        userCheck();
                    } else {
                        idCardCheck();
                    }
                }
            });
            V.dialog = dialog;

            dialog.on("closing", function () {
                V.form.clearFields();
                V.form.clearErrors();

            })
        })
    }

    function userCheck() {
        var url = "<%=path%>/checkCompanyAndUserName";
        var datas = {
            courtStdNo: $("#courtNo").val(),
            username: $("#username").val(),
            id: $("input[name='userInfo.id']").val(),
            companyInfoId: $("#companyInfoId").val(),
            companyName: $("#companyName").val(),
            valid: 2,
            isEdit: isEdit
        }

        V.form.valid();
        if (V.form.isValid()) {
//        if(true){
            $.post(url, datas, function (data) {
                var userFlag = false;
                var companyFlag = false;

                if (data.CompanyInfoCheck == "true") {
                    companyFlag = true;
                } else {
                    $(".companyCheck").show();
                    var a = document.getElementById("show_e_company");
                    a.setAttribute("onclick", '');
                    a.click("return false");
                }
                // 返回值为true说明用户名可用，为false说明用户名不可用
                if (data.UserNameCheck == "true") {
                    if (isEdit) {
                        $('#password').val("");
                    }
                    userFlag = true;
                } else {
                    // 如果username返回为""，说明并未修改传过去的usename
                    if (data.username != "") {
                        $(".show_info_ok").show();
                        $(".show_icon_ok").show();
                        newUserName = data.username;
                        $("#suggestUsername").text(newUserName);
                        $("#username").val(newUserName);
                    } else {
                        $(".userNameCheck").show();
                    }
                    var a = document.getElementById("show_e_user");
                    a.setAttribute("onclick", '');
                    a.click("return false");
                }

                if (userFlag && companyFlag) {
                    $("#courtNo").attr("disabled", false);
                    V.form.submit();
                }

            })
        } else {
            V.form.submit();
        }
    }

    function idCardCheck() {
        var url = "<%=path%>/checkIdCard";
        var datas = {
            courtStdNo: $("#courtNo").val(),
            username: $("#username").val(),
            id: $("input[name='userInfo.id']").val(),
            idcard: $("#idcard").val()
        };

        V.form.valid();
        if (V.form.isValid()) {
            $.post(url, datas, function (data) {

                if (data.idCardCheck) {
                    userCheck();
                } else {
                    var typeStr = "";
                    switch (data.userType) {
                        case 1 :
                            typeStr += "正式人员"
                            break;
                        case 2 :
                            typeStr += "编外人员"
                            break;
                        case 3 :
                            typeStr += "人民陪审员"
                            break;
                    }
                    var enabledStr = "";
                    switch (data.enabled) {
                        case 0 :
                            enabledStr += "停用"
                            break;
                        case 1 :
                            enabledStr += "启用"
                            break;
                    }
                    var showStr = "<span>该人员已经存在,请对相应人员进行操作 ";
                    showStr += (typeStr == "" ? "" : " , 人员类型为 : " + typeStr);
                    showStr += (typeStr == "" ? "" : " , 人员状态为 : " + enabledStr);
                    showStr += ("<a style='margin-left:10px;' target='_blank' href='<%=basePath%>view/detail?id=" + data.userId + " '>点击查看</a></span>");
                    BUI.Message.Alert(showStr, "warning");
                    //打开新页面
                    <%--open("<%=basePath%>view/detail?id=" + data.userId);--%>

                }
            })
        } else {
            V.form.submit();
        }
    }

    //验证身份证号并获取出生日期
    function getBirthdatByIdNo(iIdNo) {
        var tmpStr = "";
        var strReturn = "";

        iIdNo = trim(iIdNo);

        if ((iIdNo.length != 15) && (iIdNo.length != 18)) {
            strReturn = "";
            return strReturn;
        }

        if (iIdNo.length == 15) {
            tmpStr = iIdNo.substring(6, 12);
            tmpStr = "19" + tmpStr;
            tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-" + tmpStr.substring(6)

            return tmpStr;
        } else {
            tmpStr = iIdNo.substring(6, 14);
            tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-" + tmpStr.substring(6)

            return tmpStr;
        }
    }

    function TypeChange(obj) {
        $(obj).parent().parent().find("div").each(function () {
            $(this).find("a").removeClass("selectDiv");
        })
        $(obj).addClass("selectDiv");
        $("#isValidShow").html($(".selectDiv").html());
        var condition = {};
        $(".search_field").each(function () {
            condition[$(this).attr("name")] = $(this).val();
        });
        if ($(".selectDiv").attr('codeId') == 0) {
            condition['isInfoComplete'] = 0;
            condition['isValid'] = 1;
            condition['leaveReason'] = '';
        } else if ($(".selectDiv").attr('codeId') == -1) {
            condition['isInfoComplete'] = 0;
            condition['isValid'] = 0;
            condition['leaveReason'] = '';
        } else if ($(".selectDiv").attr('codeId') == -2) {
            //查询返聘人员
            condition['isInfoComplete'] = 0;
            condition['isValid'] = 1;
            condition['leaveReason'] = 5; //返聘人员的代码
        } else if ($(".selectDiv").attr('codeId') == -3) {
            //保留系统登录
            condition['isInfoComplete'] = 0;
            condition['isValid'] = 3;
            condition['leaveReason'] = ''; //返聘人员的代码

        }
        V.store.load(condition);
        query_condition = V.store.get("lastParams");
    }

    function initSelect2(class_) {
        $("select." + class_).each(function () {
            var firstOptionValue = $(this).find("option:eq(0)").val();
            $(this).select2().select2("val", firstOptionValue);
            $(this).change();
        });
    }

    function changeSelect2Value(_this, val) {
        $(_this).select2().select2("val", val);
        $(_this).change();
    }

    // 获取当前时间格式为 2019-07-25 14:10:00
    function getNow() {
        var myDate = new Date;
        var year = myDate.getFullYear(); //获取当前年
        var mon = myDate.getMonth() + 1; //获取当前月
        var date = myDate.getDate(); //获取当前日
        var h = myDate.getHours();//获取当前小时数(0-23)
        var m = myDate.getMinutes();//获取当前分钟数(0-59)
        var s = myDate.getSeconds();//获取当前秒
        var nowTime = sjbl(year) + "-" + sjbl(mon) + "-" + sjbl(date) + " " + sjbl(h) + ":" + sjbl(m) + ":" + sjbl(s);
        return nowTime;
    }

    // 时间补零
    function sjbl(s) {
        return s < 10 ? '0' + s: s;
    }
</script>
<%--Script End--%>
</html>
