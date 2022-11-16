<%--
    Document   : user2
    Created on : 2014-12-23, 16:53:11
    Author     : Diluka
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String search = request.getParameter("redi");
    String userType = request.getParameter("userType");
%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
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

        .nav-tabs li s {
            color: red;
            padding-right: 5px;
            text-decoration: none;
        }

        .show_icon {
            display: none;
            font-size: 20px;
            font-weight: bold;
            font-family: "Arial";
            -webkit-border-radius: 2px;
            -moz-border-radius: 2px;
            border-radius: 2px;
            text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
            border: 1px solid transparent;
            cursor: inherit;
            margin: 2px 2px 0px 10px;
        }

        .show_info {
            display: none;
            color: red;
        }

        .bui-grid-button-bar .button-small [class^="icon-"] {
            margin: 0px 2px 0 -5px;
        }

        #deptNoId {
            margin: 0 8px 0 3px;
        }

        .bui-message {
            z-index: 2000;
        }

        .show_a {
            cursor: pointer;
        }

        .button-own {
            width: 125px;
        }

        input.calendar {
            width: 140px;
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

<div class="row">
    <div>
        <ul class="breadcrumb">
            <li><span>人事管理</span><span class="divider">/</span></li>
            <li><span>信息管理</span><span class="divider">/</span></li>
            <li class="active">管理人员信息</li>
        </ul>
    </div>
</div>

<%--树结构--%>

<%--表格--%>

    <div class="search-grid-container">
        <div id="grid"></div>
    </div>

<%--申请调职窗口--%>
<div id="dz" class="hide">
    <form id="userinfo_dz_form" class="form-horizontal" action="<%=basePath%>userinfo/insertChangeJob" method="post">
        <div class="row" style="float: left">
            <%--<img id="dzpic" src="{url}" style="width: 150px;height: auto"/>--%>
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
            <div class="row">
                <div class="control-group span17">
                    <label class="control-label">进院日期：</label>

                    <div class="controls bui-form-group" data-rules="{dateRange : true}">
                        <input id="enterDateStart" data-tip="{text:'起始日期'}" name="enterDateStart" type="text"
                               placeholder="起始日期"
                               class="calendar search_field">
                        &nbsp;-&nbsp;
                        <input id="enterDateEnd" data-tip="{text:'结束日期'}" name="enterDateEnd" type="text"
                               placeholder="结束日期"
                               class="calendar search_field">
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<input type="hidden" id="hide_court_no">
<input type="hidden" id="hide_dept_no">
<%--<jsp:include page="user2_add.jsp"></jsp:include>--%>
<%--<jsp:include page="affiliated_depart.jsp"></jsp:include>--%>
<jsp:include page="/fragment/tip.jsp"></jsp:include>
<jsp:include page="statisticsList.jsp?initGrid=false"></jsp:include>
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


    });
    //-------------初始化下拉框数据End---------------
    var CourtNo = '';
    var auth = false;
    var myForm;
    var TForm;
    var DzForm;
    var isEdit = false;
    var Rstore;
    var closeFlag = false;
    var V = $.extend({}, V, parent.V);
    var startRow;
    //初始化BUI相关组件
    BUI.use(['common/search', 'bui/tree', 'bui/data', 'bui/calendar', 'bui/overlay', 'bui/form', 'bui/uploader'],
            function (Search, Tree, Data, Calendar, Overlay, Form, Uploader) {

                //-------------初始化表格Start---------------
                var userinfoActionUrl = "<%=basePath%>chart/selInfo";

                var editing = new BUI.Grid.Plugins.DialogEditing({
                    contentId: 'content', //设置隐藏的Dialog内容
                    autoSave: true, //添加数据或者修改数据时，自动保存
                    triggerCls: 'btn-edit'
                });

                //定义表格列
                var columns = [
                    {title: '序号',  sortable: true, align: 'left',renderer:function(value,obj){
                        return startRow++;
                    }},
                    {title: '姓名', dataIndex: 'fullname', width: "100", sortable: true, align: 'left'},
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
                    {title: '法院', dataIndex: 'courtNoText', width: "180", sortable: true, align: 'left'},
                    {title: '部门', dataIndex: 'departmentText', width: "150", sortable: true, align: 'left'},
                    {title: '学历', dataIndex: 'educationBackgroundText', width: "100", sortable: true, align: 'left'},
                    {
                        title: '行政职务',
                        dataIndex: 'administrationPositionText',
                        width: "120",
                        sortable: true,
                        align: 'left'
                    },
                    {title: '职级', dataIndex: 'rankText', width: "100", sortable: true, align: 'left'},
                    {
                        title: '返聘人员', width: "100", sortable: false, visible: false, renderer: function (value, obj) {
                        var str = "";
                        if (obj.isValid == 1) {
                            str = "是"
                        } else {
                            str = "否"
                        }
                        return str;

                    }
                    },
                    {
                        title: '操作', width: "200", sortable: false, renderer: function (value, obj) {
                        var spanStr = '<span class="grid-command btn-detail" title="显示人员详情信息">查看详情</span>';
                        if (obj.leaveReason != null && obj.leaveReason == 5) {

                            spanStr += '<span class="grid-valid   btn-enabled  btn-enabled-on" title="启用">启用</span>';
                            if (obj.isValid == 1) {
                                spanStr += '<span class="grid-valid  btn-enabled  btn-rehire" title="取消返聘">取消返聘</span>';
                            } else {
                                spanStr += '<span class="grid-valid btn-enabled btn-rehire" title="返聘人员">返聘人员</span>';
                            }

                        } else {
                            if (obj.isValid == 1) {
                                spanStr += '<span class="grid-valid  hide btn-enabled" title="启用">启用</span>' +
                                        '<span class="grid-valid  btn-enabled" title="停用">停用</span>';
                            } else {
                                spanStr += '<span class="grid-valid  btn-enabled" title="启用">启用</span>' +
                                        '<span class="grid-valid  btn-enabled  hide" title="停用">停用</span>';
                            }
                        }

                        spanStr += '<span class="grid-command btn-dz" title="申请调职">申请调职</span>';
                        return spanStr;
                    }
                    }
                ];
                V.cloumn = columns;

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
                    $.getJSON("/ums/code/codeListByTypeWithNoAspect",{},function (data) {
                        if (firstLine) {
                            $(cbo).html(firstLine);
                        } else {
                            $(cbo).empty();
                        }
                        $.each(data, function(index,values){   // 解析出data对应的Object数组
                            $.each(values,function(index2,value){
                                $(cbo).append($("<option>").attr({"value": value.id}).text(value.codeName));
                            });
                        });
                        if (callback) {
                            callback();
                        }
                    });
                }

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
                        },
                        method: 'post',
                        limit : '20'            //一页多少条记录
                    },
                    params: {
                        selectData:"<%=search%>"
                    },
                    autoSync: true //保存数据后，自动更新
                });

                Rstore = store;


                store.on("load", function () {
                    startRow = store.get("start") + 1;
                    canEdit();
                });

                var searchDialog = new Overlay.Dialog({
                    title: "查询信息",
//                    width: auto,
//                    height: 225,
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
                var searchForm = new Form.Form({
                    srcNode: "#search"
                }).render();

                searchDialog.on("closing", function () {
                    searchForm.clearFields();
                })

                var tbarData = {
                    items: [
                        {
                            text: '<i class="icon-search"></i>在结果中搜索',
                            btnCls: 'button button-small',
                            handler: function () {
                                searchDialog.show();
                            }
                        },
                        {
                            text: '<i class="icon-search"></i>excel下载',
                            btnCls: 'button button-small',
                            handler: function () {
                                if(Rstore && Rstore.hasData()){
                                    downLoadFj();
                                }else{
                                    alert("结果没有数据");
                                }
                            }
                        },
                        {
                            text: '<i class="icon-search"></i>保存该自定义查询',
                            btnCls: 'button button-small',
                            handler: function () {
                                initData();
                                saveEdit_dialog.show();
                            }
                        },
                        {
                            text: '<i class="icon-search"></i>花名册下载',
                            btnCls: 'button button-small',
                            handler: function () {
                                if(Rstore && Rstore.hasData()){
                                    downLoadFj(1);
                                }else{
                                    alert("结果没有数据");
                                }
                            }
                        }
                    ]
                };

                //定义表格样式
                var gridCfg = Search.createGridCfg(columns, {
                    //width: 600,
                    height: "450",
                    //forceFit: true,
                    emptyDataTpl: '<div class="centered"><h2>查询的数据不存在</h2></div>',
                    tbar: tbarData,
                    plugins: [editing, BUI.Grid.Plugins.RadioSelection, BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格
                });


                var search = new Search({
                    store: store,
                    gridCfg: gridCfg
                });
                var grid = search.get('grid');

                //单独要使用 提出来
                $.getJSON("/ums/code/codeListByType",
                        {
                            typeId: 45
                        },
                        function (data) {
                            $("select.leavecode").html('');
                            $("select.leavecode").append('<option value="">请选择</option>');
                            $("#leaveReasonDiv").html('');
                            $("#leaveReasonDiv").append('<div><a  onclick="TypeChange(this)" codeId=0 class="selectDiv">在职</a></div>');
                            $("#leaveReasonDiv").append('<div><a  onclick="TypeChange(this)" codeId=-1 >停用</a></div>');
                            for (var i = 0; i < data.length; i++) {
                                $("select.leavecode").append($("<option>").attr({"value": data[i].id}).text(data[i].codeName));
                                $("#leaveReasonDiv").append('<div><a  onclick="TypeChange(this)" codeId=' + data[i].id + '>' + data[i].codeName + '</a></div>');
                            }
                            //返聘操作
                            $("#leaveReasonDiv").append('<div><a  onclick="TypeChange(this)" codeId=-2 >返聘</a></div>');


                        })

                $("#btnSearch2").click(function () {
                    var condition = {};

                    $(".search_field").each(function () {
                        condition[$(this).attr("name")] = $(this).val();
                    });

                    store.load(condition);
                });


                //监听事件，删除一条记录
                grid.on('cellclick', function (ev) {
                    var sender = $(ev.domTarget); //点击的Dom
                    if (sender.hasClass('btn-detail')) {
                        var record = ev.record;
                        open("<%=basePath%>view/detail_new?id=" + record.id);
                        return;
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
                        loadAllCourtList("#new_court_no",'<option value=\'\'>请选择</option>');
                        dzDialog.show();
                        return;
                    }


                    if (sender.hasClass('btn-enabled')) {
                        var record = ev.record;
                        var url = "<%=path%>/userinfo/enabled";
                        var i = record.isValid == 1 ? 0 : 1;
                        var datas = {};
                        datas['userInfo.isValid'] = i;
                        datas['userInfo.id'] = record.id;
                        //是返聘操作
                        if (sender.hasClass("btn-rehire")) {
                            datas['enableType'] = 1;
                        }
                        //返聘的启用
                        if (sender.hasClass("btn-enabled-on")) {
                            datas['userInfo.isValid'] = 1;
                        }
                        if (record.isValid != 1) {
                            //先验证用户名
                            var url_u = "<%=path%>/checkUserName";
                            var datas_u = {
                                courtStdNo: record.courtNo,
                                username: record.username,
                                valid: 1
                            }
                            //身份证验证
                            var url_ = "<%=path%>/checkIdCard";
                            var da = {
                                idcard: record.idcard,
                                valid: 1
                            };

                            $.post(url_u, datas_u, function (data) {

                                if (data.UserNameCheck == "false") {
                                    $.post(url_, da, function (data) {
                                        if (data.idCardCheck) {
                                            $.post(url, datas, function (dt) {
                                                if (dt.success) {
                                                    BUI.Message.Alert("操作成功", "success");
                                                } else {
                                                    BUI.Message.Alert("操作失败", "success");
                                                }
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
                                            showStr += (typeStr == "" ? "" : " , 人员类型为 : " + typeStr );
                                            showStr += (typeStr == "" ? "" : " , 人员状态为 : " + enabledStr );
                                            showStr += ("<a style='margin-left:10px;' target='_blank' href='<%=basePath%>view/detail_new?id=" + data.userId + " '>点击查看</a></span>");
                                            BUI.Message.Alert(showStr, "warning");

                                        }
                                    })
                                } else {
                                    BUI.Message.Alert("用户名已经存在,请更改用户名后再启用", "warning");
                                }

                            });
                        } else {
                            $.post(url, datas, function (data) {
                                if (data.success) {
                                    BUI.Message.Alert("操作成功", "success");
                                } else {
                                    BUI.Message.Alert("操作失败", "success");
                                }

                                store.load();
                            });
                        }
                    }
                });
            }
    );

    // 根据权限显示按钮
    function canEdit() {
        $.getJSON("<%=basePath%>auth/canIEdit", {}, function (can) {
            if (!can) {
                // $(".bui-bar.bui-grid-button-bar button").filter(":gt(0)").addClass("hide");
                $(".btn-enabled").remove();
                $(".btn-dz").remove();
                $(".search").filter(":gt(0)").remove("hide");
            }
        });
    }

    //在职状态类型变化
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
        V.cloumn[9].set("visible", false);
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
            V.cloumn[9].set("visible", true);
        } else {
            condition['isInfoComplete'] = 0;
            condition['isValid'] = '';
            condition['leaveReason'] = $(".selectDiv").attr('codeId');
            if ($(".selectDiv").attr('codeId') == 5) {
                V.cloumn[9].set("visible", true);
            }
        }
        Rstore.load(condition);
    }


    function downLoadFj(val) {

        var url =  '<%=basePath%>/chart/downLoadExcel';
        if(val){
            url =  '<%=basePath%>/chart/downLoadExcelNew';
        }
        var turnForm = document.getElementById("downloadForm");
        if (turnForm) {
            turnForm.innerHTML = "";
            turnForm.action = url;
        } else {
            turnForm = document.createElement("form");
            //一定要加入到body中！！
            document.body.appendChild(turnForm);
            turnForm.method = 'post';
            turnForm.action = url;
            // turnForm.target = 'targetIfr';
            turnForm.target = '_blank';
            turnForm.id = "downloadForm";
        }

        turnForm.setAttribute("style", "display:none");
        var input_ = document.createElement("input");
        input_.name = "selectData";
        input_.value = "<%=search%>";
        turnForm.appendChild(input_);
        var input__ = document.createElement("input");
        input__.name = "userType";
        input__.value = "<%=userType%>";
        turnForm.appendChild(input__);
        turnForm.submit();


    }
</script>
<%--Script End--%>
</html>
