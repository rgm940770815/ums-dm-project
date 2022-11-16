<%--
  Created by IntelliJ IDEA.
  User: 28608
  Date: 2018/7/18
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String type = request.getParameter("type");
    String courtLevel = request.getParameter("courtLevel");
    String attachName = request.getParameter("attachName");
    String preparation = request.getParameter("preparation");
    String preparationName = request.getParameter("preparationName");
    String courtCode = request.getParameter("courtCode");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>法官等级情况统计表之七</title>
    <link href="css/biao.css" rel="stylesheet" type="text/css">
    <jsp:include page="/basic_import.jsp"></jsp:include>
    <script src="js/stat.js" type="text/javascript"></script>
    <script src="js/jquery.table2excel.js" type="text/javascript"></script>
</head>
<style type="text/css">

    .attachName {
        font-size: 20px;
        color: #334A80;
    }

    .mytalble {
        border-bottom-color: black;
        border-top-color: black;
        width: 1600px;
        color: #000000;
        border-right-color: black;
        font-size: medium;
        border-left-color: black;
        border: 1px;
        cellspacing: 0px;
        cellpadding: 0px;
    }

    /*设置超出了显示滚动条*/
    .bui-stdmod-body {
        overflow-x: hidden;
        overflow-y: auto;
        height: 470px !important;
    }
</style>
<body>
<div id="all" style="text-align: center ;width: 100%" hidden>
    <table id="mytalble" class="biaonr"></table>
</div>

<br/>
<br/>

<%--反查弹出--%>
<div id="data-dialog" style="display:none;">
    <div id="grid">

    </div>
</div>


<script type="text/javascript">
    var param = {
        typeStr: '<%=type%>',
        courtLevel: '<%=courtLevel%>',
        preparation: '<%=preparation%>',
        courtCode: '<%=courtCode%>'
    };

    var countOfAll = 0;//合计
    var countOf0 = 0;//首席大法官
    var countOf1 = 0;//一级大法官
    var countOf2 = 0;//二级大法官
    var countOf10 = 0;//一级高级法官
    var countOf11 = 0;//二级高级法官
    var countOf12 = 0;//三级高级法官
    var countOf13 = 0;//四级高级法官
    var countOfsum_1;//1小计
    var countOf20 = 0;//一级法官
    var countOf21 = 0;//二级法官
    var countOf22 = 0;//三级法官
    var countOf23 = 0;//四级法官
    var countOf24 = 0;//五级法官
    var countOf98 = 0;//等级未定
    var countOfsum_2;//2小计

    <%--$(function () {--%>

    <%--    var law_position_report_text = "";--%>
    <%--    var attachName_ = "<%=attachName%>";--%>
    <%--    var attachName = attachName_ == "" ? attachName_ : "--" + attachName_;--%>
    <%--    var preparationName_ = "<%=preparationName%>";--%>
    <%--    var preparationName = preparationName_ == "" ? "" : "--" + preparationName_;--%>

    <%--    $("#all").prepend(--%>
    <%--        "<div align='center' style='padding-top: 30px;font-size: xx-large'><b>法官等级情况统计表之七<span class='attachName'>" + attachName + preparationName + "</span></b></div>" +--%>
    <%--        "<div style='position: fixed;padding-top: 10px;padding-left: 95%;cursor: pointer;font-size: 14px'><a onclick='todownLoad()'>下载表格</a></div>" +--%>
    <%--        "</br></br></br>"--%>
    <%--    );--%>

    <%--    //table表头--%>
    <%--    var a =--%>
    <%--        "<tbody class='mytalble'>" +--%>
    <%--        "<tr style='height: 40px'>" +--%>
    <%--        "<th rowspan='2'></th><th  rowspan='2'><div>总计</div></th>" +--%>
    <%--        "<th rowspan='2'><div>首席大法官</div></th>" +--%>
    <%--        "<th rowspan='2'><div>一级大法官</div></th>" +--%>
    <%--        "<th rowspan='2'><div>二级大法官</div></th>" +--%>
    <%--        "<th colspan='5'><div>高级法官</div></th>" +--%>
    <%--        "<th colspan='7'><div>法官</div></th>" +--%>
    <%--        "</tr>" +--%>
    <%--        "<tr style='height: 40px'>" +--%>
    <%--        "<th ><div>一级</div></th><th ><div>二级</div></th>" +--%>
    <%--        "<th ><div>三级</div></th><th ><div>四级</div></th>" +--%>
    <%--        "<th ><div>小计</div></th><th ><div>一级</div></th>" +--%>
    <%--        "<th ><div>二级</div></th><th ><div>三级</div></th>" +--%>
    <%--        "<th ><div>四级</div></th><th ><div>五级</div></th>" +--%>
    <%--        "<th ><div>等级未定</div></th><th ><div>小计</div></th>" +--%>
    <%--        "</tr>";--%>

    <%--    var column = ["合计", "院长", "副院长", "审判委员会委员", "庭长", "副庭长", "审判员", "助理审判员"];--%>

    <%--    for (var i = 0; i < column.length; i++) {--%>
    <%--        if ("合计" == column[i]) {--%>
    <%--            law_position_report_text = "";--%>
    <%--            // a += "<tr valign='top'>" +--%>
    <%--            //     "<td rowspan='2' align='center' valign='middle'><div>合计</div></td>" +--%>
    <%--            //     "</tr>";--%>
    <%--        } else {--%>
    <%--            law_position_report_text = column[i];--%>
    <%--        }--%>
    <%--        a += addRow(law_position_report_text);--%>
    <%--    }--%>

    <%--    a += "</tbody>";--%>
    <%--    $("#mytalble").append(a);--%>
    <%--    $("#all").show();--%>

    <%--    //最后给td绑定点击事件 进行反查--%>
    <%--    $("td[loadData]").off("click").on("click", function () {--%>
    <%--        makeBUIGrid($(this));--%>
    <%--    })--%>

    <%--});--%>

    $(function () {

        var law_position_report_text = "";
        var attachName_ = "<%=attachName%>";
        var attachName = attachName_ == "" ? attachName_ : "--" + attachName_;
        var preparationName_ = "<%=preparationName%>";
        var preparationName = preparationName_ == "" ? "" : "--" + preparationName_;

        $("#all").prepend(
            "<div align='center' style='padding-top: 30px;font-size: xx-large'><b>法官等级情况统计表之七<span class='attachName'>" + attachName + preparationName + "</span></b></div>" +
            "<div style='position: fixed;padding-top: 10px;padding-left: 95%;cursor: pointer;font-size: 14px'><a onclick='todownLoad()'>下载表格</a></div>" +
            "</br></br></br>"
        );


        $.ajax({
            url: "<%=basePath%>statisticalTable/countRankOfJudgesNew",
            type: "POST",
            data: param,
            dataType: "json",
            success: function (returnData) {

                if (returnData && returnData.length > 0) {

                    //table表头
                    var a =
                        "<tbody class='mytalble'>" +
                        "<tr style='height: 40px'>" +
                        "<th rowspan='2'></th><th  rowspan='2'><div>总计</div></th>" +
                        "<th rowspan='2'><div>首席大法官</div></th>" +
                        "<th rowspan='2'><div>一级大法官</div></th>" +
                        "<th rowspan='2'><div>二级大法官</div></th>" +
                        "<th colspan='5'><div>高级法官</div></th>" +
                        "<th colspan='7'><div>法官</div></th>" +
                        "</tr>" +
                        "<tr style='height: 40px'>" +
                        "<th ><div>一级</div></th><th ><div>二级</div></th>" +
                        "<th ><div>三级</div></th><th ><div>四级</div></th>" +
                        "<th ><div>小计</div></th><th ><div>一级</div></th>" +
                        "<th ><div>二级</div></th><th ><div>三级</div></th>" +
                        "<th ><div>四级</div></th><th ><div>五级</div></th>" +
                        "<th ><div>等级未定</div></th><th ><div>小计</div></th>" +
                        "</tr>";

                    $.each(returnData, function (index, val) {
                        a += addRowNew(val);
                    });
                    a += "</tbody>";

                    $("#mytalble").append(a);
                    $("#all").show();

                    //最后给td绑定点击事件 进行反查
                    $("td[loadData]").off("click").on("click", function () {
                        makeBUIGrid($(this));
                    })
                }
            }

        });

    });


    function getLevelInfoData() {

        $.ajax({
            url: "<%=basePath%>statisticalTable/countRankOfJudgesNew",
            type: "POST",
            data: param,
            dataType: "json",
            success: function (returnData) {
                data_all = returnData.list;
            }

        });

    }

    function addRowNew(obj) {

        countOfAll = 0;//合计
        countOf0 = 0;//首席大法官
        countOf1 = 0;//一级大法官
        countOf2 = 0;//二级大法官
        countOf10 = 0;//一级高级法官
        countOf11 = 0;//二级高级法官
        countOf12 = 0;//三级高级法官
        countOf13 = 0;//四级高级法官
        countOfsum_1;//1小计
        countOf20 = 0;//一级法官
        countOf21 = 0;//二级法官
        countOf22 = 0;//三级法官
        countOf23 = 0;//四级法官
        countOf24 = 0;//五级法官
        countOf98 = 0;//等级未定
        countOfsum_2;//2小计

        var rowData = obj.data;
        var rowlaw_position = obj.law_position;
        var rowtext = obj.text;
        //获取数量
        $.each(rowData,function(index,val){

            countOfAll += val["c"];
            switch (val["judge_level"]) {
                case "0":
                    countOf0 = val["c"];
                    break;
                case "1":
                    countOf1 = val["c"];
                    break;
                case "2":
                    countOf2 = val["c"];
                    break;
                case "10":
                    countOf10 = val["c"];
                    break;
                case "11":
                    countOf11 = val["c"];
                    break;
                case "12":
                    countOf12 = val["c"];
                    break;
                case "13":
                    countOf13 = val["c"];
                    break;
                case "20":
                    countOf20 = val["c"];
                    break;
                case "21":
                    countOf21 = val["c"];
                    break;
                case "22":
                    countOf22 = val["c"];
                    break;
                case "23":
                    countOf23 = val["c"];
                    break;
                case "24":
                    countOf24 = val["c"];
                    break;
                case "98":
                    countOf98 = val["c"];
                    break;
            }

        });

        countOfsum_1 = countOf10 + countOf11 + countOf12 + countOf13;//1小计
        countOfsum_2 = countOf20 + countOf21 + countOf22 + countOf23 + countOf24 + countOf98;//2小计
        if(countOfAll === 0){
            return "";
        }
        var leftValue = rowlaw_position;
        var b = "<tr>" +
            "<td loaddata2>" + rowtext + "</td>" +
            "<td loaddata data-leftcolumn='law_position' data-leftvalue='" + leftValue + "' data-topcolumn='judge_level' data-topvalue=''>" + countOfAll + "</td>" +
            "<td loaddata data-leftcolumn='law_position' data-leftvalue='" + leftValue + "' data-topcolumn='judge_level' data-topvalue='0'>" + countOf0 + "</td>" +
            "<td loaddata data-leftcolumn='law_position' data-leftvalue='" + leftValue + "' data-topcolumn='judge_level' data-topvalue='1'>" + countOf1 + "</td>" +
            "<td loaddata data-leftcolumn='law_position' data-leftvalue='" + leftValue + "' data-topcolumn='judge_level' data-topvalue='2'>" + countOf2 + "</td>" +
            "<td loaddata data-leftcolumn='law_position' data-leftvalue='" + leftValue + "' data-topcolumn='judge_level' data-topvalue='10'>" + countOf10 + "</td>" +
            "<td loaddata data-leftcolumn='law_position' data-leftvalue='" + leftValue + "' data-topcolumn='judge_level' data-topvalue='11'>" + countOf11 + "</td>" +
            "<td loaddata data-leftcolumn='law_position' data-leftvalue='" + leftValue + "' data-topcolumn='judge_level' data-topvalue='12'>" + countOf12 + "</td>" +
            "<td loaddata data-leftcolumn='law_position' data-leftvalue='" + leftValue + "' data-topcolumn='judge_level' data-topvalue='13'>" + countOf13 + "</td>" +
            "<td loaddata data-leftcolumn='law_position' data-leftvalue='" + leftValue + "' data-topcolumn='judge_level_range' data-topvalue='in (10,11,12,13)'>" + countOfsum_1 + "</td>" +
            "<td loaddata data-leftcolumn='law_position' data-leftvalue='" + leftValue + "' data-topcolumn='judge_level' data-topvalue='20'>" + countOf20 + "</td>" +
            "<td loaddata data-leftcolumn='law_position' data-leftvalue='" + leftValue + "' data-topcolumn='judge_level' data-topvalue='21'>" + countOf21 + "</td>" +
            "<td loaddata data-leftcolumn='law_position' data-leftvalue='" + leftValue + "' data-topcolumn='judge_level' data-topvalue='22'>" + countOf22 + "</td>" +
            "<td loaddata data-leftcolumn='law_position' data-leftvalue='" + leftValue + "' data-topcolumn='judge_level' data-topvalue='23'>" + countOf23 + "</td>" +
            "<td loaddata data-leftcolumn='law_position' data-leftvalue='" + leftValue + "' data-topcolumn='judge_level' data-topvalue='24'>" + countOf24 + "</td>" +
            "<td loaddata data-leftcolumn='law_position' data-leftvalue='" + leftValue + "' data-topcolumn='judge_level' data-topvalue='98'>" + countOf98 + "</td>" +
            "<td loaddata data-leftcolumn='law_position' data-leftvalue='" + leftValue + "' data-topcolumn='judge_level_range' data-topvalue='in (20,21,22,23,24,98)'>" + countOfsum_2 + "</td>" +
            "</tr>";

        return b;
    }


    function addRow(law_position_report_text) {

        countOfAll = 0;//合计
        countOf0 = 0;//首席大法官
        countOf1 = 0;//一级大法官
        countOf2 = 0;//二级大法官
        countOf10 = 0;//一级高级法官
        countOf11 = 0;//二级高级法官
        countOf12 = 0;//三级高级法官
        countOf13 = 0;//四级高级法官
        countOfsum_1;//1小计
        countOf20 = 0;//一级法官
        countOf21 = 0;//二级法官
        countOf22 = 0;//三级法官
        countOf23 = 0;//四级法官
        countOf24 = 0;//五级法官
        countOf98 = 0;//等级未定
        countOfsum_2;//2小计

        param.law_position_report_text = law_position_report_text;
        $.ajax({
            url: "<%=basePath%>statisticalTable/countRankOfJudges",
            async: false, //改为同步方式
            type: "POST",
            data: param,
            success: function (returnData) {
                data_all = returnData.list;
            },
            dataType: "json"
        });
        //获取数量
        for (var i = 0; i < data_all.length; i++) {
            countOfAll += data_all[i]["count(1)"];
            switch (data_all[i]["level"]) {
                case 0:
                    countOf0 = data_all[i]["count(1)"]
                    break;
                case 1:
                    countOf1 = data_all[i]["count(1)"]
                    break;
                case 2:
                    countOf2 = data_all[i]["count(1)"]
                    break;
                case 10:
                    countOf10 = data_all[i]["count(1)"]
                    break;
                case 11:
                    countOf11 = data_all[i]["count(1)"]
                    break;
                case 12:
                    countOf12 = data_all[i]["count(1)"]
                    break;
                case 13:
                    countOf13 = data_all[i]["count(1)"]
                    break;
                case 20:
                    countOf20 = data_all[i]["count(1)"]
                    break;
                case 21:
                    countOf21 = data_all[i]["count(1)"]
                    break;
                case 22:
                    countOf22 = data_all[i]["count(1)"]
                    break;
                case 23:
                    countOf23 = data_all[i]["count(1)"]
                    break;
                case 24:
                    countOf24 = data_all[i]["count(1)"]
                    break;
                case 98:
                    countOf98 = data_all[i]["count(1)"]
                    break;
            }
        }

        countOfsum_1 = countOf10 + countOf11 + countOf12 + countOf13;//1小计
        countOfsum_2 = countOf20 + countOf21 + countOf22 + countOf23 + countOf24 + countOf98;//2小计

        if (law_position_report_text == "") {
            law_position_report_text = "合计";
        }

        var leftValue = law_position_report_text == '合计' ? "in (院长, 副院长, 审判委员会委员, 庭长, 副庭长, 审判员, 助理审判员)" : law_position_report_text;
        var b = "<tr>" +
            "<td loaddata2>" + law_position_report_text + "</td>" +
            "<td loaddata data-leftcolumn='law_position_report_text' data-leftvalue='" + leftValue + "' data-topcolumn='level' data-topvalue='in (0,1,2,10,11,12,13,20,21,22,23,24,98)'>" + countOfAll + "</td>" +
            "<td loaddata data-leftcolumn='law_position_report_text' data-leftvalue='" + leftValue + "' data-topcolumn='level' data-topvalue='0'>" + countOf0 + "</td>" +
            "<td loaddata data-leftcolumn='law_position_report_text' data-leftvalue='" + leftValue + "' data-topcolumn='level' data-topvalue='1'>" + countOf1 + "</td>" +
            "<td loaddata data-leftcolumn='law_position_report_text' data-leftvalue='" + leftValue + "' data-topcolumn='level' data-topvalue='2'>" + countOf2 + "</td>" +
            "<td loaddata data-leftcolumn='law_position_report_text' data-leftvalue='" + leftValue + "' data-topcolumn='level' data-topvalue='10'>" + countOf10 + "</td>" +
            "<td loaddata data-leftcolumn='law_position_report_text' data-leftvalue='" + leftValue + "' data-topcolumn='level' data-topvalue='11'>" + countOf11 + "</td>" +
            "<td loaddata data-leftcolumn='law_position_report_text' data-leftvalue='" + leftValue + "' data-topcolumn='level' data-topvalue='12'>" + countOf12 + "</td>" +
            "<td loaddata data-leftcolumn='law_position_report_text' data-leftvalue='" + leftValue + "' data-topcolumn='level' data-topvalue='13'>" + countOf13 + "</td>" +
            "<td loaddata data-leftcolumn='law_position_report_text' data-leftvalue='" + leftValue + "' data-topcolumn='level' data-topvalue='in (10,11,12,13)'>" + countOfsum_1 + "</td>" +
            "<td loaddata data-leftcolumn='law_position_report_text' data-leftvalue='" + leftValue + "' data-topcolumn='level' data-topvalue='20'>" + countOf20 + "</td>" +
            "<td loaddata data-leftcolumn='law_position_report_text' data-leftvalue='" + leftValue + "' data-topcolumn='level' data-topvalue='21'>" + countOf21 + "</td>" +
            "<td loaddata data-leftcolumn='law_position_report_text' data-leftvalue='" + leftValue + "' data-topcolumn='level' data-topvalue='22'>" + countOf22 + "</td>" +
            "<td loaddata data-leftcolumn='law_position_report_text' data-leftvalue='" + leftValue + "' data-topcolumn='level' data-topvalue='23'>" + countOf23 + "</td>" +
            "<td loaddata data-leftcolumn='law_position_report_text' data-leftvalue='" + leftValue + "' data-topcolumn='level' data-topvalue='24'>" + countOf24 + "</td>" +
            "<td loaddata data-leftcolumn='law_position_report_text' data-leftvalue='" + leftValue + "' data-topcolumn='level' data-topvalue='98'>" + countOf98 + "</td>" +
            "<td loaddata data-leftcolumn='law_position_report_text' data-leftvalue='" + leftValue + "' data-topcolumn='level' data-topvalue='in (20,21,22,23,24,98)'>" + countOfsum_2 + "</td>" +
            "</tr>";

        return b;
    }

    //下载功能
    function todownLoad() {
        var attachName_ = "<%=attachName%>";
        var attachName = attachName_ == "" ? attachName_ : "--" + attachName_;
        var preparationName_ = "<%=preparationName%>";
        var preparationName = preparationName_ == "" ? "" : "--" + preparationName_;
        var fileName = "法官等级情况统计表之七" + attachName + preparationName;
        var tableName = "mytalble"

        $("#" + tableName).table2excel({
            exclude: ".noExl",
            name: "Excel Document Name",
            filename: fileName,
            fileext: ".xls",
            exclude_img: true,
            exclude_links: true,
            exclude_inputs: true
        });
    }


    var startRow = 0;
    var dataStore;
    var dataDialog;
    initDataBUI();

    function makeBUIGrid($obj) {

        if (!dataDialog) {
            initDataBUI();
        }

        var leftColumn = $obj.data("leftcolumn");
        var leftValue = $obj.data("leftvalue");
        var topColumn = $obj.data("topcolumn");
        var topValue = $obj.data("topvalue");

        var condition = $.extend({
            "queryEntity.leftColumn": leftColumn,
            "queryEntity.leftValue": leftValue,
            "queryEntity.topColumn": topColumn,
            "queryEntity.topValue": topValue,
            "userType": 1,
            "start": 0,
            "pageIndex": 0
        }, param);
        dataStore.load(condition);
        dataDialog.show();

    }

    //查询编码

    function initDataBUI() {


        BUI.use(['common/search', 'bui/tree', 'bui/data', 'bui/calendar', 'bui/overlay', 'bui/form', 'bui/uploader', 'bui/mask', 'bui/grid'],
            function (Search, Tree, Data, Calendar, Overlay, Form, Uploader, Mask, Grid) {

                //定义表格列
                var columns = [
                    {
                        title: '序号', sortable: false, width: "60", align: 'left', renderer: function (value, obj) {
                            return startRow++;
                        }
                    },
                    {
                        title: '姓名',
                        dataIndex: 'fullname',
                        width: "100",
                        sortable: true,
                        align: 'left',
                        renderer: function (value, obj) {
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
                    {title: '部门', dataIndex: 'departmentText', width: "150", sortable: true, align: 'left'},
                    {title: '学历', dataIndex: 'educationBackgroundText', width: "100", sortable: true, align: 'left'},
                    {
                        title: '行政职务',
                        dataIndex: 'administrationPositionText',
                        width: "150",
                        sortable: true,
                        align: 'left'
                    },
                    {
                        title: '法律职务',
                        dataIndex: 'lawPositionText',
                        width: "150",
                        sortable: true,
                        align: 'left'
                    }, {
                        title: '职级',
                        dataIndex: 'rankText',
                        width: "100",
                        sortable: true,
                        align: 'left'
                    }

                ];

                //定义表格样式
                var gridCfg = Search.createGridCfg(columns, {
                    width: "100%",
                    height: 390,
                    //forceFit: true,
                    emptyDataTpl: '<div class="centered"><h2>查询的数据不存在</h2></div>',
                    plugins: [BUI.Grid.Plugins.RadioSelection, BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格
                });

                var url = "<%=basePath%>chart/judgeLevelFc";
                dataStore = Search.createStore(url, {
                    remoteSort: true,
                    pageSize: 20,
                    params: {},
                });

                var search = new Search({
                    store: dataStore,
                    gridCfg: gridCfg
                });
                var dataGrid = search.get('grid');

                dataGrid.on("beforeitemsshow", function () {
                    var start = dataStore.get("start");
                    startRow = start + 1;
                });

                //监听事件，删除一条记录
                dataGrid.on('cellclick', function (ev) {
                    var sender = $(ev.domTarget); //点击的Dom
                    if (sender.hasClass('btn-detail')) {
                        var record = ev.record;
                        open("<%=basePath%>view/detail_new?id=" + record.id);
                    }
                });

                dataDialog = new Overlay.Dialog({
                    title: '人员信息',
                    width: "90%",
                    height: 500,
                    buttons: [{
                        text: '关闭', elCls: 'button', handler: function () {
                            this.close();
                        }
                    }],
                    contentId: 'data-dialog' //配置DOM容器的编号
                });

            })

    }

    function calcYears(start, end) {
        if (start)
            start = start.replace(/-/g, "/");
        if (end)
            end = end.replace(/-/g, "/");
        var s = new Date(start);
        var e = end ? new Date(end) : new Date();

        var result = Math.floor((e.getTime() - s.getTime()) / 3600000 / 24 / 365.25);
        return result || 0;
    }

</script>

</body>
</html>