<%--
    Document   : juror
    Created on : 2015-3-16, 16:25:18
    Author     : Diluka
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>人员管理</title>
    <jsp:include page="/basic_import.jsp"></jsp:include>
    <script src="<%=basePath%>js/common/idchecker.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/vue.min.js" type="text/javascript"></script>
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

        .show_icon_ok {
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

        .show_info_ok {
            display: none;
            color: red;
        }

        .show_info_edit {
            display: none;
            color: red;
        }

        input.calendar {
            width: 140px;
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


        .overlay_show{
            display: none;
            z-index: 2040;
            position: fixed;
            left: 0;
            right : 0;
            top: 0;
            bottom: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(255, 255, 255, 0.2) !important;
            background-color: #FFF;
            filter: Alpha(opacity=20);
        }

        .overlay_show .overlay_show_inner{
            position: absolute;
            height: 420px;
            width: 600px;
            margin-left: -300px;
            margin-top: -210px;
            top: 50vh;
            left: 50vw;
            background-color: #fff;
            border: 1px solid rgba(0,0,0,.2);
            -webkit-box-shadow: 0 3px 9px rgba(0, 0, 0, 0.5);
            -moz-box-shadow:  0 3px 9px rgba(0, 0, 0, 0.5);
            box-shadow:  0 3px 9px rgba(0, 0, 0, 0.5);
        }


        .overlay_title {
            padding: 15px 10px;
            border-bottom: 1px solid #d3d3d3;
            font-size: 15px;
        }

        .overlay_show .overlay_title ul:after,.overlay_show .overlay_bottom  ul:after{
            display: block;
            clear: both;
            content: "";
            visibility: hidden;
            height: 0;
        }

        .overlay_show .overlay_title ul li {
            list-style: none;
            float: left;
        }

        .overlay_show .overlay_bottom ul li{
            list-style: none;
            float: right;
        }

        .overlay_title ul li span.checked_span {
            border-bottom: 2px solid #00F;
            color: #000;
        }

        .overlay_title ul li:after {
            content: '|';
            font-size: 16px;
        }

        .overlay_title ul li span {
            margin: 0 10px;
            cursor: pointer;
            font-weight: bold;
            color: #416DA3;
        }

        .overlay_show .overlay_content{
            height: 300px;
            overflow: auto;
            border-bottom: 1px solid #d3d3d3;
        }

        .overlay_show .overlay_content .overlay_content_tree{
            text-align: left;
        }

        .overlay_show .overlay_bottom{
            padding: 15px 10px;
        }

        .overlay_show .overlay_bottom .button {
            margin: 0 10px;
        }

        .search_button_div{
            padding: 10px 20px;
            border-bottom: 1px solid #d3d3d3;
        }

        #search_element{
            font-size :15px;
        }
        #search_element td,#search_element th{
            padding: 5px;
        }
    </style>
</head>
<body>

<div class="overlay_show">
    <div class="overlay_show_inner">
        <div class="overlay_title">
            <ul>
                <li>
                    <span class="checked_span" targetClass="overlay_content_tree" onclick="changeTarget(this)">代码选择</span>
                </li>
                <li>
                    <span  targetClass="overlay_content_search" onclick="changeTarget(this)">关键字检索</span>
                </li>
            </ul>
        </div>
        <div class="overlay_content">
            <div class="overlay_content_tree">

            </div>

            <div class="overlay_content_search hide">
                <div class="search_button_div">
                    关键字 :&nbsp;&nbsp;&nbsp;
                    <input id="search_place"   type="text" class="control-text" placeholder="关键字">
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="button button-primary" id="search_button" onclick="queryPalces()">查询</button>
                </div>
                <div class="search_show_div">

                    <table class="first" style="width:90%;margin:10px auto;" border="1px solid black" id="search_element" >
                        <thead v-show="isShow">
                        <tr>
                            <th style="width: 70px;">选择</th>
                            <th>从属机构</th>
                            <th>名称</th>

                        </tr>
                        </thead>
                        <tbody v-show="isShow">
                        <tr v-for="item in places">
                            <td onclick="checkItem(this)" style="width: 70px;cursor: pointer;text-align: center" class="bui-tree-td" :data-id="item.id" :data-name="item.codeName" :data-fullname="item.fullName">
                                <div class="bui-tree-list" style="border: none">
                                    <span class="x-tree-icon x-tree-icon-checkbox"></span>
                                </div>

                            </td>
                            <td v-for="(value, key) in item" v-if="handleShow(key)">
                                {{ value }}
                            </td>

                        </tr>
                        <tr></tr>
                        </tbody>
                    </table>


                </div>
            </div>
        </div>
        <div class="overlay_bottom">
            <ul>
                <li>
                    <button class="button button-primary"  onclick="saveSearch()">确定</button>
                </li>
                <li>
                    <button class="button button-primary" onclick="clearSearch()">清除</button>
                </li>
                <li>
                    <button class="button button-primary" onclick="quitSearch()">退出</button>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="row">
    <div>
        <ul class="breadcrumb">
            <li><span>人事管理</span><span class="divider">/</span></li>
            <li><span>信息管理</span><span class="divider">/</span></li>
            <li class="active">管理人民陪审员</li>
        </ul>
    </div>
</div>
<div id="contentModifiedLog" class="hide">
    <%--表格--%>
    <div id="contentModifiedLog_grid"></div>
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
                        <input id="id" type="hidden" name="umsChangeJob.id">
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
                        <select id="new_court_no" typeId="1" class="Courtcode" name="umsChangeJob.new_court_no"
                                onchange="loadDeptList(new_dept_no, this.value, '<option value=\'\'>请选择</option>')"
                                data-rules="{required:true}">
                            <option value="">请选择</option>
                        </select>
                        <input id="new_court_text" type="hidden" name="umsChangeJob.new_court_text">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">申请部门：</label>
                    <div class="controls">
                        <select id="new_dept_no" courtNo="" data-rules="{required:true}"
                                name="umsChangeJob.new_dept_no">
                            <option value="">请选择</option>
                        </select>
                        <input id="new_dept_text" type="hidden" name="umsChangeJob.new_dept_text">
                        <input id="state" type="hidden" value="申请中" name="umsChangeJob.state">
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
            <%--<div class="row">--%>
            <%--<div class="control-group span8">--%>
            <%--<label class="control-label">职级：</label>--%>

            <%--<div class="controls">--%>
            <%--<select id="rank" typeId="17" class="code search_field" name="rank"></select>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--<div class="control-group span8">--%>
            <%--<label class="control-label">行政职务：</label>--%>

            <%--<div class="controls">--%>
            <%--<select id="administrationPosition" typeId="15" name="administrationPosition" type="text"--%>
            <%--value="" class="code search_field"></select>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--<div class="row">--%>
            <%--<div class="control-group span16">--%>
            <%--<label class="control-label">进院日期：</label>--%>

            <%--<div class="controls bui-form-group" data-rules="{dateRange : true}">--%>
            <%--<input id="enterDateStart" data-tip="{text:'起始日期'}" name="enterDateStart" type="text"--%>
            <%--placeholder="起始日期"--%>
            <%--class="calendar search_field calendar-time">--%>
            <%--&nbsp;-&nbsp;--%>
            <%--<input id="enterDateEnd" data-tip="{text:'结束日期'}" name="enterDateEnd" type="text"--%>
            <%--placeholder="结束日期"--%>
            <%--class="calendar search_field calendar-time">--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</div>--%>
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
                            <option value="人民陪审员转在编">在编</option>
                            <option value="人民陪审员转编外">编外</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<jsp:include page="juror_add.jsp"></jsp:include>

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
            var typeId = $(this).attr("typeId");
            //陪审员参与案件类型
            if(typeId == 110){

                $.getJSON("/ums/code/codeListByType",
                    {
                        typeId: typeId
                    },
                    function (data) {

                        initMultiSelect(data);

                    })

                return;
            }
            //工作区域
            if(typeId == 112){
                loadArea();
                return;

            }
            loadCodeList(this, firstLine);
        });

        $.getJSON("/ums/code/codeListByType",
                {
                    typeId: 1
                },
                function (data) {
                    for (var i = 0; i < data.data.length; i++) {
                        $("select.Courtcode").append($("<option>").attr({"value": data.data[i].id}).text(data.data[i].codeName));
                        $("select.Courtcode").select2();
                    }

                    if (data.auth) {
                        CourtNo = data.value;
                        auth = true;
                    } else {
                        CourtNo = data.value;
                    }

                })

        //隐藏div
        var closeFlag = false;
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

        $("#gzqy").on("click",function(){
            console.log("in==================================1");
            $(".overlay_show").show();
        });

        $("#gzqy").on("focus",function(){
            console.log("in==================================2");
            $(".overlay_show").show();
        })

    });

    var CourtNo = '';
    var auth = false;
    var myForm;
    var isEdit = false;
    var V = $.extend({}, V, parent.V);
    var DzForm;
    var startRow;
    var query_condition = {
        isValid: 1,
        isInfoComplete: 0,
        userType: 3
    };
    var canDelete = false;
    //-------------初始化下拉框数据End---------------
    //初始化BUI相关组件
    BUI.use(['common/search', 'bui/tree', 'bui/data', 'bui/calendar', 'bui/overlay', 'bui/form', 'bui/uploader', 'bui/grid'], function (Search, Tree, Data, Calendar, Overlay, Form, Uploader,Grid) {

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

                //定义表格列
                var columns = [
                    {
                        title: '序号', sortable: false, width: "50", align: 'left', renderer: function (value, obj) {

                        return startRow++;

                    }
                    },
                    {title: '姓名', dataIndex: 'fullname', width: "100", sortable: true, align: 'left',renderer:function (value, obj) {
                            return '<span class="grid-command btn-detail" title="显示人员详情信息">' + value + '</span>';
                        }},
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
                    {title: '籍贯', dataIndex: 'hometown', width: "150", sortable: true, align: 'left'},
                    {title: '政治面貌', dataIndex: 'politicalText', width: "130", sortable: true, align: 'left'},
                    {
                        title: '民族',
                        dataIndex: 'nationText',
                        width: "120",
                        sortable: true,
                        align: 'left'
                    },
                    {
                        title: '操作', width: "300", sortable: false, renderer: function (value, obj) {
                        var spanStr = '<span class="grid-command btn-detail" title="显示人员详情信息">查看详情</span>';
                        if (obj.isValid == 1) {
                            spanStr += '<span class="grid-valid  hide btn-enabled" title="启用">启用</span>' + '<span class="grid-valid  btn-enabled" title="停用">停用</span>';
                        } else {
                            spanStr += '<span class="grid-valid  btn-enabled" title="启用">启用</span>' + '<span class="grid-valid  btn-enabled  hide" title="停用">停用</span>';
                        }
                            spanStr += '<span class="grid-command btn-blxtdl" title="保留系统登录">保留系统登录</span>';
                            spanStr += '<span class="grid-command btn-gbbzlx" title="申请改变编制类型">申请改变编制类型</span>';
                        return spanStr;

                    }
                    }
                ];

                var store = Search.createStore(userinfoActionUrl, {
                    sortField: 'sortNo',
                    sortDirection: 'ASC',
                    remoteSort: true,
                    pageSize: 15,
                    proxy: {
                        save: {//也可以是一个字符串，那么增删改，都会往那么路径提交数据，同时附加参数saveType
                            //addUrl: '../data/add.json',
                            //updateUrl: '../data/edit.json',
                            removeUrl: '<%=basePath%>userinfo/delete'
                        }/*,
                         method : 'POST'*/
                    },
                    params: {
                        isValid: 1,
                        isInfoComplete: 0,
                        userType: 3
                    },
                    autoSync: true //保存数据后，自动更新
                });
                V.store = store;


                store.on("load", function () {
                    startRow = store.get("start") + 1;
                    canEdit();
                })

                var searchDialog = new Overlay.Dialog({
                    title: "查询信息",
                    width: 700,
                    height: 200,
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
                            text: '<i class="icon-search"></i>搜索',
                            btnCls: 'button button-small',
                            handler: function () {
                                searchDialog.show();
                            }
                        },
                        {
                            text: '<i class="icon-plus"></i>添加',
                            btnCls: 'button button-small',
                            handler: function () {
                                initSelect2("select22")
                                selWork();
                                form.clearFields();
                                form.clearErrors();
                                isEdit = false;
                                $("#age").html("0岁");
                                $('#birthday').val("");
                                $('#birthday_show').html("");
                                $('#password').val("");
                                $(".show_info").hide();
                                $(".show_icon").hide();
                                $(".show_info_ok").hide();
                                $(".show_icon_ok").hide();
                                $("#age,#totalCourtYear,#totalSeniority").text("0岁");
                                $("#userinfo_form #id").val("");
                                queue.setItems([]);
                                $.getJSON("<%=basePath%>code/newUUID", {_: new Date()}, function (data) {
                                    $("#userinfo_form #new_id").val(data);
                                });
                                editDialog.set('title', '新增');
                                editDialog.set('headerContent',
                                        '<ul class="nav-tabs"> <li><a style="border: 1px solid white;color:inherit;">新增</a></li>' +
                                        '<li class="active"><a href="#basic_info" onclick="changeMarkers(this)" class="initClass"><s>*</s>基本信息</a></li> ' +
                                        '<li><a href="#juror_degree" onclick="changeMarkers(this)"><s>*</s>学历学位</a></li>' +
                                        '<li><a href="#position_info" onclick="changeMarkers(this)"><s>*</s>单位及职务</a></li><li><a href="#juror_info" onclick="changeMarkers(this)"><s>*</s>陪审员信息</a></li>' +
                                        '<li><a href="#education_info" onclick="changeMarkers(this)">其他</a></li><li><a href="#photo_info" onclick="changeMarkers(this)">头像上传</a></li>');

                                editDialog.show();
                                $("#courtNo").val(CourtNo);
                                $("#courtNo").select2();
                                //暂时没用 用户权限
                                if (auth) {
                                } else {
                                    $("#courtNo").attr("disabled", true);
                                }
                                location.href = $(".initClass").attr("href");
                                ;
                                $(".initClass").click();
                            }
                        },
                        {
                            text: '<i class="icon-edit"></i>编辑',
                            btnCls: 'button button-small',
                            handler: function () {
                                form.clearErrors();
                                var userinfo = grid.getSelected();
                                if (userinfo) {
                                    $("#userinfo_form #new_id").val("");
                                    loadUserinfo(userinfo.id);
                                    isEdit = true;
                                    editDialog.set('title', '编辑 - ' + userinfo.fullname);
                                    editDialog.set('headerContent',
                                            '<ul class="nav-tabs"> <li><a style="border: 1px solid white;color:inherit;">编辑 - ' + userinfo.fullname + '</a></li>' +
                                            '<li class="active"><a href="#basic_info" onclick="changeMarkers(this)" class="initClass"><s>*</s>基本信息</a></li> ' +
                                            '<li><a href="#juror_degree" onclick="changeMarkers(this)"><s>*</s>学历学位</a></li>' +
                                            '<li><a href="#position_info" onclick="changeMarkers(this)"><s>*</s>单位及职务</a></li><li><a href="#juror_info" onclick="changeMarkers(this)"><s>*</s>陪审员信息</a></li>' +
                                            '<li><a href="#education_info" onclick="changeMarkers(this)">其他</a></li><li><a href="#photo_info" onclick="changeMarkers(this)">头像上传</a></li>');

                                    editDialog.show();

                                    location.href = $(".initClass").attr("href");
                                    ;
                                    $(".initClass").click();
                                } else {
                                    BUI.Message.Alert("请选择一条记录", null, "info");
                                }
                            }
                        },
//                            {
//                                text: '<i class="icon-remove"></i>删除',
//                                btnCls: 'button button-small',
//                                handler: function () {
//                                    var userinfo = grid.getSelected();
//                                    if (userinfo) {
//                                        BUI.Message.Confirm(
//                                                '确定要删除“' + userinfo.fullname + '”吗？',
//                                                function () {
//                                                    store.save('remove', {id: userinfo.id}, function (data) {
//                                                        if (!data.success && data.result === -1) {
//                                                            BUI.Message.Alert("非法操作，当前用户无此权限！", null, "warning");
//                                                        }
//                                                    });
//                                                },
//                                                'question');
//                                    } else {
//                                        BUI.Message.Alert("请选择一条记录", null, "info");
//                                    }
//
//                                }
//                            },
                        {
                            text: '生成电子简历',
                            btnCls: 'button button-small',
                            handler: function () {
                                var userinfo = grid.getSelected();
                                if (userinfo) {
                                    window.open("<%=basePath%>board/personnel/info/juror_grjl.jsp?id=" + userinfo.id);
                                } else {
                                    BUI.Message.Alert("请选择一条记录", null, "info");
                                }
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
                                            form.append("<input type='text' name='excelDataType'  value='3' >")
                                            for (var i in query_condition) {
                                                form.append("<input type='text' name='" + i + "'  value='" + query_condition[i] + "'>")

                                            }
                                            form.submit();
                                        },
                                        'question');


                            }
                        },{
                            text: '<i class="icon-download-alt"></i>最高院上传人员信息excel下载',
                            btnCls: 'button button-small ',
                            handler: function () {

                                BUI.Message.Confirm(
                                        'excel会按照当前查询出来的信息进行生成,如果数据量较大请稍等,勿重复点击生成。是否按照此信息生成excel?',
                                        function () {
                                            //要拼接参数
                                            var form = $("form[id='excel_form']");
                                            form.html("");
                                            form.append("<input type='text' name='excelDataType'  value='31' >")
                                            for (var i in query_condition) {
                                                form.append("<input type='text' name='" + i + "'  value='" + query_condition[i] + "'>")

                                            }
                                            form.submit();
                                        },
                                        'question');


                            }
                        },{
                            text: '<i class="icon-edit"></i>批量添加信息',
                            btnCls: 'button button-small',
                            handler: function () {
                                if (courNo==null || courNo==''||depNo==null || depNo==''){
                                    BUI.Message.Alert("请先选择部门",null,"warning");
                                    return;
                                }
                                window.open("<%=basePath%>view/batchEdit?courtNo="+courNo+"&deptNo="+depNo);
                            }
                        },
                        <%--{--%>
                            <%--text: '<i class="icon-file"></i>显示该人员被操作日志',--%>
                            <%--btnCls: 'button button-small',--%>
                            <%--handler: function () {--%>
                                <%--form.clearErrors();--%>
                                <%--var userinfo = grid.getSelected();--%>
                                <%--var html = "<table border='1' width=100%>";--%>
                                <%--html += "<tr align='center' height='30'>" +--%>
                                    <%--"<td style='width:200px'>操作人</td>" +--%>
                                    <%--"<td style='width:80px;'>操作的类型</td>" +--%>
                                    <%--"<td style='width:120px;'>操作的类型描述</td>" +--%>
                                    <%--"<td style='width:120px;'>操作时间</td>" +--%>
                                    <%--"<td style='width:100px;'>操作人的IP地址</td>" +--%>
                                    <%--"<td style='width:300px;'>操作的内容</td>" +--%>
                                    <%--"</tr>";--%>
                                <%--if (userinfo) {--%>
                                    <%--$.post("<%=basePath%>umsUserOperationLog/selectById", {id : userinfo.id}, function (data) {--%>
                                        <%--if (data != null) {--%>
                                            <%--for (var i = 0; i < data.length; i++) {--%>
                                                <%--html += "<tr align='center' height='30'><td>" + data[i].operationUsername + "</td><td>" + data[i].operationTypecode + "</td><td>" +--%>
                                                    <%--data[i].operationTypedetail + "</td><td>" + data[i].operationTime + "</td><td>" + data[i].operationIp + "</td><td align='left'>" + data[i].operationContent + "</td></tr>";--%>
                                            <%--}--%>
                                            <%--html += "</table>";--%>
                                        <%--}--%>
                                        <%--modifiedLogDialog.set("bodyContent", html);--%>
                                    <%--});--%>
                                    <%--modifiedLogDialog.set(--%>
                                        <%--'headerContent',--%>
                                        <%--'<ul>' +--%>
                                        <%--'<li style="border: 1px solid white;color:inherit;">' + userinfo.fullname + ' - ' + userinfo.courtNoText + ' - 被操作的日志记录</li>' +--%>
                                        <%--'</ul>'--%>
                                    <%--);--%>
                                    <%--modifiedLogDialog.show();--%>
                                <%--} else {--%>
                                    <%--BUI.Message.Alert("请选择一条记录", null, "info");--%>
                                <%--}--%>
                            <%--}--%>
                        <%--}--%>

                    ]
                };

                if (canDelete) {
                    var del = {
                        text: '<i class="icon-remove"></i>删除',
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

                // 申请改变编制类型
                var gbbzlxDialog = new Overlay.Dialog({
                    title: "申请改变编制类型",
                    width: 610,
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
                // 申请改变编制类型
                function sqgbbzlx() {
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

                //监听事件，删除一条记录
                grid.on('cellclick', function (ev) {
                    var sender = $(ev.domTarget); //点击的Dom
                    if (sender.hasClass('btn-detail')) {
                        var record = ev.record;
                        open("<%=basePath%>view/detail?id=" + record.id);
                    }

                    if (sender.hasClass('btn-enabled')) {
                        var record = ev.record;
                        var url = "<%=path%>/userinfo/enabled";
                        var i = record.isValid == 1 ? 0 : 1;
                        var datas = {}
                        datas['userInfo.isValid'] = i;
                        datas['userInfo.id'] = record.id;
                        if (record.isValid != 1) {

                            //先验证用户名
                            isEdit = true;// true 为编辑和启用状态,java端进入if判断
                            var url_u = "<%=path%>/checkUserName";
                            var datas_u = {
                                courtStdNo: record.courtNo,
                                username: record.username,
                                valid: 1,
                                isEdit: isEdit
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
                                isEdit:isEdit
                            };

                            $.post(url_u, datas_u, function (data) {

                                if (data.UserNameCheck == "false") {

                                    isEdit = true;
                                    var da = {
                                        idcard: record.idcard,
                                        valid: 1,
                                        isEdit:isEdit
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
                                            showStr += (typeStr == "" ? "" : " , 人员类型为 : " + typeStr );
                                            showStr += (typeStr == "" ? "" : " , 人员状态为 : " + enabledStr );
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

                    if (sender.hasClass("btn-blxtdl")){
                        var record = ev.record;
                        var url = "<%=path%>/userinfo/enabled";
                        var datas = {};
                        datas['userInfo.isValid'] = 3;
                        datas['userInfo.id'] = record.id;
                        $.post(url, datas, function (res) {
                            console.log(res);
                            if(res && res.success){
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
                //-------------初始化表格End---------------
                $("#leaveReasonDiv").html('');
                $("#leaveReasonDiv").append('<div><a  onclick="TypeChange(this)" codeId=0 class="selectDiv">启用</a></div>');
                $("#leaveReasonDiv").append('<div><a  onclick="TypeChange(this)" codeId=-1 >停用</a></div>');
                $("#leaveReasonDiv").append('<div><a  onclick="TypeChange(this)" codeId=-3 >保留系统登录</a></div>');

                //-------------树结构Start-----------------
                var treestore = new Data.TreeStore({
                    root: {
                        text: "全部",
                        courtNo: "",
                        deptNo: ""
                    },
                    url: '<%=basePath%>code/tree/children3?type=1'
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

                var courNo,depNo;
                tree.on('itemclick', function (ev) {
                    var item = ev.item;
                    courNo = item.courtNo;
                    depNo=item.deptNo;
                    var condition = {courtNo: item.courtNo, deptNo: item.deptNo};
                    searchForm.clearFields();
                    $(".search_field").each(function () {
                        condition[$(this).attr("name")] = $(this).val();
                    });
                    condition = $.extend({"start": 0, "pageIndex": 0}, condition);
                    store.load(condition);
                    query_condition = store.get("lastParams");

                    $('#log').text(item.id + ":" + item.text);
                });
                //-------------树结构End-----------------


                //--------------初始化新增按钮弹窗Start-----------------
                var form = new Form.HForm({
                    srcNode: '#userinfo_form',
                    submitType: 'ajax',
                    callback: function (data) {
                        if (data.success) {
                            showTip("添加成功！");
                        } else {
                            showTip("添加失败");
                        }
                        if (data.success && photoEdit) {
                            $.getJSON("<%=basePath%>photo/save", {
                                userId: $("#userinfo_form #id").val() || $("#userinfo_form #new_id").val(),
                                _: new Date().getTime()
                            }, function () {
                            });
                        } else if (data.result === -1) {
                            BUI.Message.Alert("非法操作，当前用户无此权限！", null, "warning");
                        }
                        store.load();
                        editDialog.close();
                    }
                }).render();

                myForm = form;

                //----------初始化日期插件Start-----------
//                var datepicker = new Calendar.DatePicker({
//                    trigger: '.calendar',
//                    autoRender: true
//                });
                //----------初始化日期插件End--------------

                //新增窗体
                var editDialog = new Overlay.Dialog({
                    title: '新增',
                    width: '90%',
                    height: 450,
                    buttons: [
                        {
                            text: '保存', elCls: 'button button-primary', handler: function () {
                            if ($("#id").val() != '') {
                                userCheck();
                            } else {
                                idCardCheck();
                            }

                        }
                        },
                        {
                            text: '重置', elCls: 'button', handler: function () {
                            resetUserinfoForm();
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
                    //读取审判员信息
                    $.getJSON("<%=basePath%>juror/oneExtend", {
                        "jurorInfo.userId": userinfo.id,
                        _: new Date().getTime()
                    }, function (res) {
                        if(res && res.info){
                            var data = res.info;

                            $("#userinfo_form [name^='jurorInfo.']").each(function () {
                                $(this).val(data[$(this).attr("id")]);
                                //参与案件类型
                                if("typeOfCase" == $(this).attr("id") ){
                                    if(picker){
                                        picker.setSelectedValue(data[$(this).attr("id")]);
                                    }
                                }

                                //工作区域
                                if("workArea" == $(this).attr("id")){
                                   if(res.gzqy){
                                       $("#gzqy").val(res.gzqy);
                                   }
                                }

                            });



                            if (data.jurorWork){
                                selWork(data.jurorWork);
                            }

                        }

                    });

                    var flag_1 = false;
                    var flag_2 = false;
                    $("#userinfo_form [name^='userInfo.']").each(function () {
                        $(this).val(userinfo[$(this).attr("id")]);
                        if ($(this).attr("id") === "courtNo") {
                            changeSelect2Value($(this),userinfo[$(this).attr("id")]);
                            loadDeptList($("#userinfo_form #department"), $(this).val(), firstLine, function () {
                                $("#userinfo_form #department").val(userinfo.department);
                                changeSelect2Value($("#department"),userinfo.department);
                            });
                        }
                        if ($(this).attr("name").indexOf("birthday") > 0) {
                            $("#birthday_show").html(userinfo[$(this).attr("id")]);
                        }

                        if (auth) {
                        } else {
                            $("#courtNo").attr("disabled", true);
                        }
                        if($(this).hasClass("select22"))
                        {
                            $(this).select2().select2("val",userinfo[$(this).attr("id")]);
                            $(this).change();
                        }
                        var city = $("#city").val();
                        var area = $("#area").val();
                        var province = $("#province").val();
                        if (!flag_1 && province != '' && province > 0 && city != '' && city > 0) {
                            flag_1 = true;
                            var url = "<%=basePath%>code/getCity";
                            var datas = {
                                provinceID: province
                            };
                            $.post(url, datas, function (data) {
                                $("#userCity").html("<option value='-1'>请选择</option>");
                                $("#userArea").html("<option value='-1'>请选择城市</option>")
                                if (data.length > 0) {
                                    for (var i = 0; i < data.length; i++) {
                                        $("#userCity").append($("<option>").attr({"value": data[i].cityID}).text(data[i].city));
                                    }
                                }
                                $("#userCity").val(city);
                            });
                        }
                        if (!flag_2 && area != '' && area > 0 && city != '' && city > 0) {
                            flag_2 = true;
                            var url = "<%=basePath%>code/getArea";
                            var datas = {
                                cityID: city
                            };
                            $.post(url, datas, function (data) {
                                $("#userArea").html("<option value='-1'>请选择</option>")
                                if (data.length > 0) {
                                    for (var i = 0; i < data.length; i++) {
                                        $("#userArea").append($("<option>").attr({"value": data[i].areaID}).text(data[i].area));
                                    }
                                }
                                $("#userArea").val(area);
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

                    //清空错误信息
                    if(form){
                        form.clearErrors();
                    }
                }

                function loadUserinfo(id) {
                    form.clearFields();
                    form.clearErrors();
                    $("#age").html("0岁");
                    $('#birthday').val("");
                    $('#birthday_show').html("");
                    $('#password').val("");
                    $(".show_info").hide();
                    $(".show_icon").hide();
                    $(".show_info_ok").hide();
                    $(".show_icon_ok").hide();
                    //读取审判员信息
                    $.getJSON("<%=basePath%>juror/one", {
                        "jurorInfo.userId": userinfo.id,
                        _: new Date().getTime()
                    }, function (data) {
                        if (data) {
                            $("#userinfo_form [name^='jurorInfo.']").each(function () {
                                $(this).val(data[$(this).attr("id")]);
                                if($(this).hasClass("select22"))
                                {
                                    $(this).select2().select2("val",userinfo[$(this).attr("id")]);
                                    $(this).change();
                                }
                            });
                            if (data.jurorWork){
                                selWork(data.jurorWork);
                            }
                        }
                    });
                    $.getJSON("<%=basePath%>userinfo/one", {id: id, _: new Date()}, function (data) {
                        userinfo = data;
                        reloadUserinfo();
                        // 设置用户名的提示，编辑时不可以修改用户名
                        $("#username").attr("readonly","readonly");
                        $("#username").focus(function(){
                            $(".show_info_edit").show();
                            $(".show_info").hide();
                            $(".show_icon_ok").hide();
                        });
                        $("#username").blur(function(){
                            $(".show_info_edit").hide();
                        });
                    });
                }

                function resetUserinfoForm() {
                    $("#age").html("0岁");
                    $('#birthday').val("");
                    $('#birthday_show').html("");
                    $('#password').val("");
                    $(".show_info").hide();
                    $(".show_icon").hide();
                    $(".show_info_ok").hide();
                    $(".show_icon_ok").hide();
                    if (editDialog.get("title") === '新增') {
                        initSelect2("select22");
                        $("select.Courtcode").select2().change();
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
                                        $("#password").val('admin');
                                    }
                                    break;
                                case 15:
                                    if ('19' + value.slice(6, 12) !== birthday) {
                                        return "出生日期与身份证信息不符！";
                                    }
                                    if (!isEdit) {
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

                Form.Rules.add({
                    name: 'selectRule',
                    msg: '请填写{0}',
                    validator: function (value, baseValue, formatMsg) {
                        var regexp = new RegExp("^[0-9]{1,}$");
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
                datepickerFix($, BUI.Calendar);


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
        }
        else {
            tmpStr = iIdNo.substring(6, 14);
            tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-" + tmpStr.substring(6)

            return tmpStr;
        }
    }

    function userCheck() {
        // 返回的新的username
        var newUserName;
        var url = "<%=path%>/checkUserName";
        var datas = {
            courtStdNo: $("#courtNo").val(),
            username: $("#username").val(),
            id: $("input[name='userInfo.id']").val(),
            valid: 1,
            userType: 3,
            isEdit: isEdit
        }
        myForm.valid();
        if (myForm.isValid()) {
            $.post(url, datas, function (data) {
                if (data.UserNameCheck == "false") {
                    if (isEdit) {
                        $('#password').val("");
                    }
                    $("#courtNo").attr("disabled", false);
                    myForm.submit();
                } else {
                    // 如果username返回为""，说明并未修改传过去的usename
                    if (data.username != "") {
                        $(".show_info_ok").show();
                        $(".show_icon_ok").show();
                        newUserName = data.username;
                        $("#suggestUsername").text(newUserName);
                        $("#username").val(newUserName);
                    } else {
                        $(".show_info").show();
                        $(".show_icon").show();
                    }
                    var a = document.getElementById("show_e");
                    //取消<a>标签原先的onclick事件,使<a>标签点击后通过href跳转(因为无法用js跳转)^-^
                    a.setAttribute("onclick", '');
                    //激发标签点击事件OVER
                    a.click("return false");
                }
            })
        } else {
            myForm.submit();
        }
    }

    function idCardCheck() {
        var url = "<%=path%>/checkIdCard";
        var datas = {
            courtStdNo: $("#courtNo").val(),
            id: $("input[name='userInfo.id']").val(),
            idcard: $("#idcard").val()
        };

        myForm.valid();
        if (myForm.isValid()) {
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
                    var showStr = "<span>该人员已经存在,请对相应人员进行操作  ";
                    showStr += (typeStr == "" ? "" : " , 人员类型为 : " + typeStr );
                    showStr += (typeStr == "" ? "" : " , 人员状态为 : " + enabledStr );

                    showStr += ("<a style='margin-left:10px;' target='_blank' href='<%=basePath%>view/detail?id=" + data.userId + " '>点击查看</a></span>");
                    BUI.Message.Alert(showStr, "warning");
                    //打开新页面
                    <%--open("<%=basePath%>view/detail?id=" + data.userId);--%>

                }
            })
        } else {
            myForm.submit();
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
        }else if($(".selectDiv").attr('codeId') == -3){
            //保留系统登录
            condition['isInfoComplete'] = 0;
            condition['isValid'] = 3;
            condition['leaveReason'] = ''; //返聘人员的代码

        }
        V.store.load(condition);
        query_condition = V.store.get("lastParams");
    }
    function initSelect2(class_)
    {
        $("select."+class_).each(function(){
            var firstOptionValue=$(this).find("option:eq(0)").val();
            $(this).select2().select2("val",firstOptionValue);
            $(this).change();
        });
    }
    function changeSelect2Value(_this,val)
    {
        $(_this).select2().select2("val",val);
        $(_this).change();
    }

    //初始化选择树
    var picker;
    function initMultiSelect(data) {

        var getJsonTree = function (data, parentid) {
            var itemArr = [];
            for (var i = 0; i < data.length; i++) {
                var node = data[i];
                if (node.parentId == parentid ) {
                    var newNode = {};
                    newNode.id = node.id;
                    newNode.text = node.codeName;
                    newNode.children = getJsonTree(data, node.courtCode);
                    itemArr.push(newNode);
                }
            }
            return itemArr;
        };
        var dat = getJsonTree(data, null);
        BUI.use(['bui/extensions/treepicker', 'bui/tree'], function (TreePicker, Tree) {
            var tree = new Tree.TreeList({
                nodes: dat,
                checkType: 'all',
                cascadeCheckd : false, //不级联勾选
                showLine: true //显示连接线
            });

            picker = new TreePicker({
                trigger: '#cyajlx',
                valueField: '#typeOfCase', //如果需要列表返回的value，放在隐藏域，那么指定隐藏域
                selectStatus: 'checked',//设置勾选作为状态改变的事件
                width: 230,  //指定宽度
                children: [tree] //配置picker内的列表
            });

            picker.render();

        });
    }


    var tree;
    function loadArea() {

        BUI.use(['bui/extensions/treepicker', 'bui/tree' ,'bui/data' ], function (TreePicker, Tree , Data) {


            var treestore = new Data.TreeStore({
                root: {
                    text: "全部",
                },
                url: '<%=basePath%>code/getDetailArea'
            });

            tree = new Tree.TreeList({
                render : ".overlay_content_tree",
                store: treestore,
                cascadeCheckd : false, //不级联勾选
                multipleCheck : false, //是否多选，非多选时使用radio
                showLine: true ,//显示连接线
                checkType: 'onlyLeaf', //仅叶子可以勾选
                listeners : {
                    itemselected : function(ev){

                        var item = ev.item;

                        tree.clearSelected();
                        tree.clearAllChecked();
                        tree.setChecked(item);
                        vm.checkValue = item.id;
                        vm.checkName = item.text;
                        var arr = [];
                        readParentNode(item , arr);
                        var str = "";
                        for(var i = arr.length ; i > 0 ; i --){
                            if(i == arr.length){
                                str += arr[i - 1];
                                continue;
                            }
                            str += "-" +arr[i - 1];
                        }

                        vm.checkFullName = str;

                    }
                }
            });

            // areaPicker = new TreePicker({
            //     trigger: '#gzqy',
            //     valueField: '#workArea', //如果需要列表返回的value，放在隐藏域，那么指定隐藏域
            //     selectStatus: 'checked',//设置勾选作为状态改变的事件
            //     width: 230,  //指定宽度
            //     children: [tree] //配置picker内的列表
            // });
            //
            // areaPicker.render();
            tree.render();
            treestore.load();//加载根节点，也可以让用户点击加载

        });


        initVue();


    }


    function readParentNode(item , arr) {
        if(item){
            if("全部" == item.text){
                return;
            }
            arr.push(item.text);
            if(item.parent){
                readParentNode(item.parent,arr);
            }
        }
    }

    function queryPalces(){
        var place = $("#search_place").val();
        if(!place){
            return;
        }

        $.ajax({
            url : "<%=basePath%>juror/searchArea",
            type : "post",
            data : { codeName : place},
            dataType :"json",
            success : function(datas){
                vm.handleData(datas);
            }
        })

    }

    function checkItem(obj){
        var $this = $(obj);
        $(".bui-tree-td").removeClass("bui-tree-item-checked");
        $this.addClass("bui-tree-item-checked");
        vm.checkValue = $this.data("id");
        vm.checkName = $this.data("name");
        vm.checkFullName =  $this.data("fullname");
    }


    var vm;
    function initVue(){

        vm = new Vue({
            el: "#search_element",
            data: {
                title: [
                    "id",
                    "parentName",
                    "codeName",
                    "fullName"
                ],
                places: [],
                isShow: true,
                checkValue : null,
                checkName : null,
                checkFullName : null
            },
            methods: {
                handleData: function (datas) {
                    if(!datas){
                        return;
                    }
                    var rows = datas;
                    var returnData = [];
                    var title = this.title;

                    for (var i in rows) {
                        var m = {};
                        var obj = rows[i];
                        for (var j in title) {
                            var index = title[j];

                            m[index] = obj[index] ? obj[index] : "";

                        }
                        returnData.push(m);
                    }
                    this.places = returnData;

                },
                handleShow: function (index) {
                    if (index &&  ( index == 'id'|| index == 'fullName' )) {
                        return false;
                    }
                    return true;
                }
            }

        });

    }

    function saveSearch(){
        $("input[name='jurorInfo.workArea']").val(vm.checkValue);
        $("input[name='jurorInfo.gzqy']").val(vm.checkFullName);
        quitSearch();
    }

    function quitSearch(){

        $(".overlay_show").hide();
        vm.checkValue = null;
        vm.checkName = null;
        vm.checkFullName = null;
        vm.places = null;
        $("#search_place").val('');
        tree.clearAllChecked();
        $(".bui-tree-td").removeClass("bui-tree-item-checked");
        $("#workArea").focus().change().blur();

    }

    function clearSearch(){
        vm.checkValue = null;
        vm.checkName = null;
        vm.checkFullName = null;
        tree.clearAllChecked();
        $(".bui-tree-td").removeClass("bui-tree-item-checked");
        $("input[name='jurorInfo.workArea']").val(vm.checkValue);
        $("input[name='jurorInfo.gzqy']").val(vm.checkFullName);
    }

    function changeTarget(obj){

        var $this = $(obj);
        if($this.hasClass("checked_span")){
            return;
        }
        $(".overlay_title ul li span").removeClass("checked_span");
        $this.addClass("checked_span");
        $(".overlay_content > div").addClass("hide");

        $("." + $this.attr("targetClass")).removeClass("hide");

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
