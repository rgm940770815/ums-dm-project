<%--
  Created by IntelliJ IDEA.
  User: Cypress
  Date: 2016/11/30
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>地方事业编制人员</title>
    <jsp:include page="/basic_import.jsp"></jsp:include>
    <script src="<%=path%>/js/common/datetools.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=path%>/js/common/codelist.js"></script>
    <style type="text/css">

        tr td span {
            font-size: 12px;
        }

        input {
            box-sizing: content-box;
        }
    </style>
</head>
<body>

<div class="row">
    <div>
        <ul class="breadcrumb">
            <li><span>人事管理</span><span class="divider">/</span></li>
            <li><span>信息管理</span><span class="divider">/</span></li>
            <li class="active">地方事业编制人员</li>
        </ul>
    </div>
</div>

<%--树结构--%>
<div id='deptree' class="panel bui-stdmod-body span6">
    <div id="t3" style="overflow-y: scroll;">
        <div class="bui-tree-list bui-simple-list" aria-disabled="false" aria-pressed="false" style="border: none;">

            <ul id="court_list_ul">
                <li class="bui-tree-item bui-tree-item-expanded" courtNo=""><span
                        class="x-tree-icon-wraper"><span
                        class="x-tree-icon x-tree-elbow-expander x-tree-elbow-expander-end"></span><span
                        class="x-tree-icon x-tree-elbow-dir"></span></span>全部
                </li>
            </ul>
        </div>
    </div>
</div>
<%--表格--%>
<div class="offset6">
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
<script type="text/javascript">

    //-------------初始化下拉框数据Start---------------
    var firstLine = '<option value="">请选择</option>';
    var current_court;
    var Columns;
    var Rstore;

    $("select.code").each(function () {
        loadCodeList(this, firstLine);
    });




    BUI.use(['common/search', 'bui/tree', 'bui/data', 'bui/calendar', 'bui/overlay', 'bui/form', 'bui/uploader', 'bui/mask', 'bui/grid'],
            function (Search, Tree, Data, Calendar, Overlay, Form, Uploader, Mask, Grid) {

                //查询中央编制类型所有的法院
                $.post("<%=path%>/view/getCourtByOrganization", {orgCode: 2}, function (datas) {

                    var str = "";
                    var strSelect = "";
                    for (var i in datas) {
                        str += generateLi(datas[i]);
                        strSelect += "<option value='" + datas[i].courtNo + "'>" + datas[i].courtStdName + "</option>"
                    }
                    $("select.Courtcode").append(strSelect);
                    var ul = $("#court_list_ul");
                    ul.append(str);
                    ul.find("li").on("click", function () {
                        //增加选择
                        ul.find(".bui-tree-item-hover").removeClass("bui-tree-item-hover");
                        $(this).addClass("bui-tree-item-hover");

                        //搜索条件改变
                        var courtNo = $(this).attr("courtNo");
                        current_court = courtNo;
                        $("select[name='innerCourtNo']").attr("disabled", false);
                        $("select[name='innerCourtNo']").val(current_court);
                        if (current_court != null && current_court != '') {

                            $("select[name='innerCourtNo']").attr("disabled", true);

                            loadDeptList($("#department_n"), current_court, firstLine, function () {
                            });
                        }else{
                            $("#department_n").html("<option value=''>请选择</option>");
                        }


                        //进行查询
                        store.load({courtNo: courtNo});


                    });

                });
                //显示列
                //定义表格列
                var columns = [
                    {
                        title: '序号', sortable: false, width: "60", align: 'left', renderer: function (value, obj) {
                        return startRow++;
                    }
                    },
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
                    {title: '法院', dataIndex: 'courtNoText', width: "200", sortable: true, align: 'left'},
                    {title: '部门', dataIndex: 'departmentText', width: "150", sortable: true, align: 'left'},
                    {
                        title: '学历',
                        dataIndex: 'educationBackgroundText',
                        width: "100",
                        sortable: true,
                        align: 'left'
                    },
                    {
                        title: '行政职务',
                        dataIndex: 'administrationPositionText',
                        width: "100",
                        sortable: true,
                        align: 'left'
                    },
                    {title: '职级', dataIndex: 'rankText', width: "100", sortable: true, align: 'left'},
                    {
                        title: '返聘人员',
                        width: "100",
                        sortable: false,
                        visible: false,
                        renderer: function (value, obj) {
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
                        return spanStr;
                    }
                    }
                ];
                Columns = columns;

                //tbar 用于显示按钮信息
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
                            text: '<i class=" icon-info-sign"></i>人员状态 - <span id="isValidShow">在职</span>' +
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

                    ]
                };

                //定义表格样式
                var gridCfg = Search.createGridCfg(columns, {
                    tbar: tbarData,
                    height: "auto",
                    //forceFit: true,
                    emptyDataTpl: '<div class="centered"><h2>查询的数据不存在</h2></div>',
                    plugins: [BUI.Grid.Plugins.RadioSelection, BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格
                });

                //请求数据
                var store = Search.createStore("<%=path%>/view/userinfo2", {
                    sortField: 'sortNo',
                    sortDirection: 'ASC',
                    remoteSort: true, // 开启异步排序
                    pageSize: 20,
                    params: {
                        userType: 1,
                        isInfoComplete: 0,
                        isValid: 1,
                        orgCode: 2
                    },

                });

                //错误信息
                store.on('exception', function () {
                    BUI.Message.Alert('参数请求错误');
                });

                store.on("load", function () {
                    startRow = store.get("start") + 1;
                });

                Rstore = store;

                var search = new Search({
                    store: store,
                    gridCfg: gridCfg
                });
                var grid = search.get('grid');


                //grid 点击事件
                grid.on('cellclick', function (ev) {
                    var sender = $(ev.domTarget); //点击的Dom
                    if (sender.hasClass('btn-detail')) {
                        var record = ev.record;
                        open("<%=path%>/view/detail?id=" + record.id);
                        return;
                    }
                });

                //------------------------------基本配置结束-------------------------------------------------------------------



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


                        });


                //------------------------------日期信息---------------------------------------------------------------------------

                //var nowDate = new Date();
                //var now = nowDate.getFullYear() + "-" + (nowDate.getMonth() + 1) + "-" + nowDate.getDate();
                //var datepicker = new Calendar.DatePicker({
                //    trigger: '.calendar',
                //    minDate: now,
                //    autoRender: true
                //});

                //--------------------------------Form-----------------------------------------------------------------------------

                //-------------------------------弹出框的配置--------------------------------------------------------------------------
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

                //bui-gird height bug fix deptree
                $(".bui-grid-body").height(document.body.clientHeight - 200);
                $(".bui-grid-bbar").height(35);
                $("#t3").height($(".bui-grid").height());

            });

    function generateLi(obj) {
        return '<li class="bui-tree-item"  courtNo="' + obj.courtNo + '"><span class="x-tree-icon-wraper"><span ' +
                'class="x-tree-icon x-tree-elbow-empty"></span><span ' +
                'class="x-tree-icon x-tree-elbow-expander"></span><span ' +
                'class="x-tree-icon x-tree-elbow-dir"></span></span> ' + obj.courtStdName +
                '</li>'
    }

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

    //在职状态类型变化
    function TypeChange(obj) {
        $(obj).parent().parent().find("div").each(function () {
            $(this).find("a").removeClass("selectDiv");
        });

        $(obj).addClass("selectDiv");
        $("#isValidShow").html($(".selectDiv").html());
        var condition = {};
        $("#userinfo_search_form .search_field").each(function () {
            condition[$(this).attr("name")] = $(this).val();
        });
        Columns[9].set("visible", false);
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
            Columns[9].set("visible", true);
        } else {
            condition['isInfoComplete'] = 0;
            condition['isValid'] = '';
            condition['leaveReason'] = $(".selectDiv").attr('codeId');
            if ($(".selectDiv").attr('codeId') == 5) {
                Columns[9].set("visible", true);
            }
        }

        Rstore.load(condition);

    }



</script>

</body>
</html>
