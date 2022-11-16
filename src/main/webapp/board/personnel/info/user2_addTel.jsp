<%@ page import="cn.net.withub.ums.entity.UmsUserInfoView" %><%--
    Document   : user2
    Created on : 2014-12-23, 16:53:11
    Author     : Diluka
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    UmsUserInfoView user = (UmsUserInfoView) session.getAttribute("loginUser");
    int userCourt = user.getCourtNo();
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
        .hasNum{
            background-color: #00cc00 !important
        }
        .hasNumF{
            background-color: #cc5e45 !important
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

<%--表格--%>
<div class="offset1" style="max-width: 1150px;">
    <div class="search-grid-container">
        <div id="grid"></div>
    </div>
</div>

<%--查询窗口--%>
<div id="search" class="hide">
    <form id="userinfo_search_form" class="form-horizontal">
        <div class="row">
            <div id="nav" class="row">
                <div class="control-group span8">
                    <label class="control-label">姓名：</label>

                    <div class="controls">
                        <input name="fullname" type="text" placeholder="请输入需要查询的姓名"
                               class="control-text search_field">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">性别：</label>

                    <div class="controls">
                        <select typeId="3" class="code search_field" name="gender"></select>
                    </div>
                </div>
            </div>
            <div class="row">

                <div class="control-group span8">
                    <label class="control-label">部门：</label>

                    <div class="controls">
                        <select id="department_n" courtNo="" name="department" class="search_field">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">职级：</label>

                    <div class="controls">
                        <select typeId="17" class="code search_field" name="rank"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">行政职务：</label>

                    <div class="controls">
                        <select typeId="15" name="administrationPosition" type="text"
                                value="" class="code search_field"></select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">最大年龄：</label>

                    <div class="controls">
                        <input type="text" class="code" data-rules="{number:true}"
                               onchange="calcDay(this,'#birthday_info_max_n')"/>
                        <input type="hidden" class="search_field " name="birthdayN" id="birthday_info_max_n"
                               value=""/>
                    </div>
                </div>

                <div class="control-group span8">
                    <label class="control-label">最小年龄：</label>

                    <div class="controls">
                        <input type="text" class="code" data-rules="{number:true}"
                               onchange="calcDay(this,'#birthday_info_min_n')"/>
                        <input type="hidden" class="search_field " name="birthdayM" id="birthday_info_min_n"
                               value=""/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span17">
                    <label class="control-label">进院日期：</label>

                    <div class="controls bui-form-group" data-rules="{dateRange : true}">
                        <input id="enterDateStart" name="enterDateStart" type="text"
                               placeholder="起始日期"
                               class="calendar search_field">
                        &nbsp;-&nbsp;
                        <input id="enterDateEnd" name="enterDateEnd" type="text"
                               placeholder="结束日期"
                               class="calendar search_field">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">学历：</label>

                    <div class="controls">
                        <select typeId="11" name="educationBackground"
                                value="" class="code search_field"></select>
                    </div>
                </div>
            </div>


        </div>
    </form>
</div>

<input type="hidden" id="hide_court_no">
<input type="hidden" id="hide_dept_no">
<jsp:include page="/fragment/tip.jsp"></jsp:include>
<div class="bui-ext-mask hide user-defined-mask" style="position: fixed;"></div>
<div class="bui-ext-mask-msg x-mask-loading hide user-defined-mask" style="position: fixed; left: 50%; top: 50%;">
    <div>正在进行操作请稍后</div>
</div>
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
        $("#userinfo_form #age").val(calcYears($("#userinfo_form #birthday").val()) + '岁');
    }
    function workTotal() {
        $("#userinfo_form #totalSeniority").text(workTotalYears($("#userinfo_form #workDate").val(), $("#userinfo_form #extraSeniority").val(), $("#userinfo_form #deductionSeniority").val()) + '年');
    }
    function courtTotal() {
        $("#userinfo_form #totalCourtYear").text(workTotalYears($("#userinfo_form #enterDate").val(), $("#userinfo_form #beforeCourtWorkYear").val()) + '年');
    }


    function calcDay(obj, target) {

        var age = $(obj).val();

        if (isNaN(age) || age == null || age == '') {
            $(target).val("");
            return;
        }


        var now_day = new Date();
        var year = now_day.getFullYear();
        var month = now_day.getMonth() + 1;
        var date = now_day.getDate();
        $(target).val((year - age) + "-" + month + "-" + date);
    }


    //-------------计算日期相关函数End---------------

    var Rstore;
    var closeFlag = false;
    var V = $.extend({}, V, parent.V);
    var current_court = null;
    var query_condition = {
        userType: 1,
        isInfoComplete: 0,
        isValid: 1,
    };
    var canDelete = false;
    //初始化BUI相关组件
    BUI.use(['common/search', 'bui/tree', 'bui/data', 'bui/calendar', 'bui/overlay', 'bui/form'],
            function (Search, Tree, Data, Calendar, Overlay, Form) {

                //-------------初始化表格Start---------------
                var userinfoActionUrl = "<%=basePath%>view/userinfo2";

                //定义表格列
                var columns = [
                    {title: '姓名', dataIndex: 'fullname', width: "100", sortable: true, align: 'left'},
                    {title: '性别', dataIndex: 'genderText', width: "50", sortable: false, align: 'left'},
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
                    {title: '法院', dataIndex: 'courtNoText', width: "200", sortable: false, align: 'left'},
                    {title: '部门', dataIndex: 'departmentText', width: "150", sortable: true, align: 'left'},
                    {title: '学历', dataIndex: 'educationBackgroundText', width: "100", sortable: false, align: 'left'},
                    {
                        title: '行政职务',
                        dataIndex: 'administrationPositionText',
                        width: "100",
                        sortable: true,
                        align: 'left'
                    },
                    {title: '职级', dataIndex: 'rankText', width: "100", sortable: true, align: 'left'},
                    {
                        title: '联系方式',dataIndex:'phoneNumber',width: "150", sortable: false
                        ,renderer:function(value,obj){
                            if (value==null){
                                value = "";
                            }
                            var idx = obj.id;
                            return "<input style='width: 100px' pid='"+idx  +"' class='pho' type='text' value='" + value + "' name='phoneNumber' >"
                            }
                    }
                ];

                var store = Search.createStore(userinfoActionUrl, {
                    sortField: 'sortNo',
                    sortDirection: 'ASC',
                    remoteSort: true,
                    pageSize: 99999,       //查询出所有人，不想改后台了
                    proxy: {
                        save: {//也可以是一个字符串，那么增删改，都会往那么路径提交数据，同时附加参数saveType
                            <%--//addUrl: '../data/add.json',--%>
                            <%--updateUrl: '<%=basePath%>userinfo/save',--%>
                            <%--&lt;%&ndash;removeUrl: '<%=basePath%>userinfo/delete'&ndash;%&gt;--%>
                        }
                        /*,
                         method : 'POST'*/
                    },
                    params: {
                        userType: 1,
                        isInfoComplete: 0,
                        isValid: 1,
                        courtNo:'<%=userCourt%>'
                    },
                    autoSync: true //保存数据后，自动更新
                });

                Rstore = store;

                var searchDialog = new Overlay.Dialog({
                    title: "查询信息",
                    width: 770,
//                    height: 225,
                    contentId: "search",
                    buttons: [
                        {
                            text: '搜索', elCls: 'button button-primary search', handler: function () {

                            searchForm.valid();
                            if (searchForm.isValid()) {

                                var condition = {};

                                $("#userinfo_search_form .search_field").each(function () {
                                    condition[$(this).attr("name")] = $(this).val();
                                });


                                condition = $.extend({"start": 0, "pageIndex": 0}, condition);
                                store.load(condition);
                                query_condition = store.get("lastParams");

                                this.close();

                            } else {
                                searchForm.submit();
                            }
                        }
                        },
                        {
                            text: '重置', elCls: 'button', handler: function () {

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
                var searchForm = new Form.HForm({
                    srcNode: "#search"
                }).render();


                searchDialog.on("closing", function () {
                    searchForm.clearFields();
                    searchForm.clearErrors();
                })


                var tbarData = {
                    items: [
                        {
                            text: '<i class="icon-search"></i>搜索',
                            btnCls: 'button button-small',
                            handler: function () {

                                if (current_court != null && current_court != '') {
                                    $("select[name='innerCourtNo']").attr("disabled", false);
                                    $("select[name='innerCourtNo']").val(current_court);
                                    $("select[name='innerCourtNo']").attr("disabled", true);

                                }

                                searchDialog.show();
                            }
                        },
                        {
                            text: '<i class=" icon-info-sign"></i><span id="isValidShow">显示全部</span>' +
                            '<div id="hasTel" class="hide" style="width:113px; position: absolute;background-color: #F5F5F5;margin-left:-10px;margin-top: 4px;z-index: 1001;padding: 6px ;">' +
                            '</div>',
                            btnCls: 'button button-small button-own',
                            handler: function () {

                                if ($("#hasTel").is(":hidden")) {
                                    var timer = setTimeout(function () {
                                        closeFlag = true;
                                    }, 300);
                                    $("#hasTel").show()
                                } else {
                                    closeFlag = false;
                                    $("#hasTel").hide()
                                }
                            }
                        }
                    ]
                };

                //定义表格样式
                var gridCfg = Search.createGridCfg(columns, {
                    //width: 600,
                    height: "auto",
                    //forceFit: true,
                    emptyDataTpl: '<div class="centered"><h2>查询的数据不存在</h2></div>',
                    tbar: tbarData,
                    plugins: [BUI.Grid.Plugins.RowNumber,BUI.Grid.Plugins.RadioSelection, BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格
                });

                var search = new Search({
                    store: store,
                    gridCfg: gridCfg
                });
                var grid = search.get('grid');

                store.on('load',function(){
                    var item = grid.getItems();
                    var reg = /^1\d{10}$/; //定义正则表达式
                    for (var a = 0;a<item.length;a++) {
                        if (item[a].phoneNumber!=null && item[a].phoneNumber!="" && item[a].phoneNumber!=undefined) {
                            var storeRow = $("#grid").children().children(".bui-grid-body").children().children().children().eq(a + 1);
                            if (reg.test(item[a].phoneNumber)){
                                storeRow.addClass('hasNum');
                                storeRow.children().children().children(".bui-grid-cell-text").parent().parent().addClass('hasNum');
                                storeRow.removeClass('hasNumF');
                                storeRow.children().children().children(".bui-grid-cell-text").parent().parent().removeClass('hasNumF');
                            }else {
                                storeRow.addClass('hasNumF');
                                storeRow.children().children().children(".bui-grid-cell-text").parent().parent().addClass('hasNumF');
                            }
                        }
                    }
                });

                $(document).off("blur",".pho").on("blur",".pho",function(){
                    handleUpdateNum();
                });

                $(document).keydown(function(event) {
                    if (event.keyCode == 13) {
                        handleUpdateNum();
                    }
                });

                function handleUpdateNum(){
                    var item = grid.getSelected();
                    var editRow = $("input[pid='"+ item.id  +"']").parent().parent().parent().parent();
                    // var item = grid.getItemAt(editRow[0].rowIndex-1);
                    var phone = $("input[pid='"+ item.id  +"']").val();
                    var reg = /^1\d{10}$/; //定义正则表达式
                    // var reg = new RegExp("^[0-9]{1,}$");
                    if (phone != null && phone != "") {
                        if (reg.test(phone)) {
                            var url = '<%=basePath%>userinfo/updateTel';
                            $.post(url, {
                                'userInfo.id': item.id,
                                'userInfo.phoneNumber': phone
                            }, function (data) {
                                if (data.success) {
                                    editRow.removeClass('hasNumF');
                                    editRow.children().children().children(".bui-grid-cell-text").parent().parent().removeClass('hasNumF');
                                    editRow.addClass('hasNum');
                                    editRow.children().children().children(".bui-grid-cell-text").parent().parent().addClass('hasNum');
                                }
                            })
                        } else {
                            editRow.addClass('hasNumF');
                            editRow.children().children().children(".bui-grid-cell-text").parent().parent().addClass('hasNumF');
                        }
                    } else {
                        var url = '<%=basePath%>userinfo/updateTel';
                        $.post(url, {
                            'userInfo.id': item.id,
                            'userInfo.phoneNumber': ""
                        }, function (data) {
                            if (data.success) {
                                editRow.removeClass('hasNumF');
                                editRow.children().children().children(".bui-grid-cell-text").parent().parent().removeClass('hasNumF');
                                editRow.removeClass('hasNum');
                                editRow.children().children().children(".bui-grid-cell-text").parent().parent().removeClass('hasNum');
                            }
                        })
                    }
                    // var nextInput = $("input[pid='"+ item.id  +"']").parent().parent().parent().parent().next().children().eq(10).children().children().children();
                    // var nextInputVal = nextInput.val();
                    // nextInput.val("");
                    // nextInput[0].focus();
                    // nextInput.val(nextInputVal);
                }

                var hasPhone = function(){
                    $("#hasTel").html('');
                    $("#hasTel").append('<div><a  onclick="TypeChange(this)" codeId=0 class="selectDiv">显示全部</a></div>');
                    $("#hasTel").append('<div><a  onclick="TypeChange(this)" codeId=1 >有联系信息</a></div>');
                    $("#hasTel").append('<div><a  onclick="TypeChange(this)" codeId=2 >无联系信息</a></div>');
                }();

                $("#btnSearch2").click(function () {
                    var condition = {};

                    $("#userinfo_search_form .search_field").each(function () {
                        condition[$(this).attr("name")] = $(this).val();
                    });


                    store.load(condition);
                    query_condition = store.get("lastParams");
                });

                //bui-gird height bug fix deptree
                $(".bui-grid-body").height($(document).height() - 180);
                $(".bui-grid-bbar").height(35);
                $("#t3").height($(".bui-grid").height());
//                    $(".bui-grid-hd-empty.bui-grid-hd").width($(".bui-grid-header").width() - $(".bui-grid-body .bui-grid-table").width())
//                    $(".bui-grid-cell.bui-grid-cell-empty").width(0);

                datepickerFix($, BUI.Calendar);
            });

    function TypeChange(obj) {
        $(obj).parent().parent().find("div").each(function () {
            $(this).find("a").removeClass("selectDiv");
        })
        $(obj).addClass("selectDiv");
        $("#isValidShow").html($(".selectDiv").html());
        var condition = {};
        $("#userinfo_search_form .search_field").each(function () {
            condition[$(this).attr("name")] = $(this).val();
        });
        if ($(".selectDiv").attr('codeId') == 1) {
            condition['isInfoComplete'] = 0;
            condition['userType'] = 1;
            condition['isValid'] = 1;
            condition['phoneNumber'] = "NotNull";
            condition['courtNo'] = '<%=userCourt%>';
        } else if ($(".selectDiv").attr('codeId') == 2) {
            condition['isInfoComplete'] = 0;
            condition['userType'] = 1;
            condition['isValid'] = 1;
            condition['phoneNumber'] = "null";
            condition['courtNo'] = '<%=userCourt%>';
        } else {
            condition['isInfoComplete'] = 0;
            condition['userType'] = 1;
            condition['isValid'] = 1;
            condition['phoneNumber'] = null;
            condition['courtNo'] = '<%=userCourt%>';
        }

        Rstore.load(condition);
    }
</script>
<%--Script End--%>
</html>
