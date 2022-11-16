<%--
  Created by IntelliJ IDEA.
  User: huise
  Date: 16/5/17 0017
  Time: 上午 10:05
  To change this template use File | Settings | File Templates.
--%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String type = request.getParameter("type");
    String attachName = request.getParameter("attachName");
    String courtLevel = request.getParameter("courtLevel");
    String preparation = request.getParameter("preparation");
    String preparationName = request.getParameter("preparationName");
    String courtCode = request.getParameter("courtCode");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>法院工作人员情况统计表</title>
    <link href="css/biao.css" rel="stylesheet" type="text/css">
</head>
<style type="text/css">
    .crs_table {
        text-align: center;
    }
    .attachName{
        font-size: 20px;
        color: #334A80;
    }

    /*设置超出了显示滚动条*/
    .bui-stdmod-body{
        overflow-x : hidden;
        overflow-y : auto;
        height: 470px !important;
    }
</style>
<body onload="json();">
<jsp:include page="/basic_import.jsp"></jsp:include>
<script src="js/jquery.table2excel.js" type="text/javascript"></script>
<script type="text/javascript">
    var param = {typeStr:'<%=type%>',courtLevel:'<%=courtLevel%>',preparation:'<%=preparation%>',courtCode:'<%=courtCode%>'};

    function json() {
        var left='';
        var attachName = '';
        //第六张报表是法院为横轴，忽略掉副标题及法院限制
        if (!"<%=type%>"=='fss_01_6' || !"<%=type%>"=='fss_01_10') {
            var attachName_ = "<%=attachName%>";
            attachName = attachName_ == "" ? attachName_ : "--" + attachName_;
        }
        var preparationName_ = "<%=preparationName%>";
        var preparationName = preparationName_ == "" ? "" : "--" + preparationName_;
        if ("<%=type%>"=='fss_01_2') {
            $("#all").prepend("<div align='center' style='padding-top: 30px;font-size: xx-large'><b>法院工作人员情况统计表之二(法律职务)<span class='attachName'>"+attachName+preparationName+"</span></b></div>" +
                    "<div style='position: fixed;padding-top: 10px;padding-left: 95%;cursor: pointer;font-size: 14px'><a onclick='todownLoad()'>下载表格</a></div>"+
                    "<div align='left' style=' padding-left: 30px'>" +
                    "报表编号：01－2 " +
                    "<br/>制表机关：重庆市高级人民法院(汇总) " +
                    "<br/>备案机关：国家统计局 " +
                    "<br/>备案文号：国统办函[2001]130号</div>");
        } else if ("<%=type%>"=='fss_01_4') {
            $("#all").prepend("<div align='center' style='padding-top: 30px;font-size: xx-large'><b>法院工作人员情况统计表之四（行政职务）<span class='attachName'>"+attachName+preparationName+"</span></b></div>" +
                    "<div style='position: fixed;padding-top: 10px;padding-left: 95%;cursor: pointer;font-size: 14px'><a onclick='todownLoad()'>下载表格</a></div>"+
                    "<div align='left' style=' padding-left: 30px'>" +
                    "报表编号：01－4 " +
                    "<br/>制表机关：重庆市高级人民法院(汇总) " +
                    "<br/>备案机关：国家统计局 " +
                    "<br/>备案文号：国统办函[2001]130号</div>");
        } else if ("<%=type%>"=='fss_01_6') {
            $("#all").prepend("<div align='center' style='padding-top: 30px;font-size: xx-large'><b>法院工作人员情况统计表之六<span class='attachName'>" + attachName + preparationName + "</span></b></div>" +
                "<div style='position: fixed;padding-top: 10px;padding-left: 95%;cursor: pointer;font-size: 14px'><a onclick='todownLoad()'>下载表格</a></div>"+
                "<div align='left' style=' padding-left: 30px'>" +
                "报表编号：01－6 " +
                "<br/>制表机关：重庆市高级人民法院(汇总) " +
                "<br/>备案机关：国家统计局 " +
                "<br/>备案文号：国统办函[2001]130号</div>");
        } else if ("<%=type%>"=='fss_01_10') {
            $("#all").prepend("<div align='center' style='padding-top: 30px;font-size: xx-large'><b>法院工作人员统计表之九（法官助理、书记员等级信息）<span class='attachName'>" + attachName + preparationName + "</span></b></div>" +
                "<div style='position: fixed;padding-top: 10px;padding-left: 95%;cursor: pointer;font-size: 14px'><a onclick='todownLoad()'>下载表格</a></div>"+
                "<div align='left' style=' padding-left: 30px'>" +
                "报表编号：01－9 " +
                "<br/>制表机关：重庆市高级人民法院(汇总) " +
                "<br/>备案机关：国家统计局 " +
                "<br/>备案文号：国统办函[2001]130号</div>");
        }

        if ("<%=type%>" == 'fss_01_11') {
            $("#all").prepend("<div align='center' style='padding-top: 30px;font-size: xx-large'><b>法院工作人员统计表之十（刑事、民事、执行、立案、行政 部门下的法官数量统计报表）<span class='attachName'>" + attachName + preparationName + "</span></b></div>" +
                "<div style='position: fixed;padding-top: 10px;padding-left: 95%;cursor: pointer;font-size: 14px'><a onclick='todownLoad()'>下载表格</a></div>"+
                "<div align='left' style=' padding-left: 30px'>" +
                "<br/>" +
                "<br/>" +
                "<br/></div>");
            $.post("<%=basePath%>chart/getNewReportTable", param, function (result) {
                result = JSON.parse(result);
                generTable();

                //生成最终表格
                function generTable() {
                    var left = result.leftArray;
                    var title = result.title;
                    var a, b, data = result.list;
                    var tableWidth, colwidth;
                    if (data[0].length <= 15) {
                        tableWidth = (data[0].length + 2) * 100 + "px";
                        colwidth = "100px";
                    } else if (data[0].length > 15 && data[0].length <= 25) {
                        tableWidth = (data[0].length + 2) * 70 + "px";
                        colwidth = "70px";
                    } else {
                        tableWidth = (data[0].length + 2) * 50 + "px";
                        colwidth = "50px";
                    }
                    $("#table").css("width", tableWidth);
                    a += "<thead><tr><th colspan='" + (data[0].length + 2) + "'>各        部        门        人        数</th></tr>";
                    a += "<tr><th class='myth' colspan='2' rowspan='3' style='width: 200px'><br/>";
                    a += "<br/></th></tr>";
                    $.each(data, function (n, list) {
                        if (n == 0) {
                            a += "<tr>";
                            $.each(list, function (index, obj) {
                                a += "<th width=" + colwidth + ">" + obj + "</th>";
                            })
                            a += "</tr>";
                        } else if (n == 1) {
                            a += "<tr>"
                            $.each(list, function (index, obj) {
                                a += "<th width=" + colwidth + ">" + obj + "</td>";
                            })
                            a += "</tr></thead><tbody>";
                        } else {
                            var n_ = n - 2;
                            a += "<tr><th width='200px'>" + left[n_].name + "</th> " +
                                "<th width='50px' align='center'>" + left[n_].num + "</th>";
                            $.each(list, function (key, value) {
                                a += "<td align='center' loadData ";
                                for (var k in value) {
                                    var v = value[k];
                                    if (k == 'count') {
                                        continue;
                                    }
                                    a += " data-" + k + "='" + v + "' ";
                                }
                                a += ">";
                                a += value.count + "</td><br/>";
                            });
                            a += "</tr>";
                        }

                    });
                    a += "</tbody>";
                    $("#table").append(a);
                    $("#all").show();
                    var screenhight = document.body.clientHeight - 130;
                    var screenWidth
                    if (document.body.clientWidth / 50 > data[0].length + 2 && data[0].length > 10)
                        screenWidth = (data[0].length + 3) * 50;
                    else if (document.body.clientWidth / 50 > data[0].length + 2 && data[0].length <= 10)
                        screenWidth = (data[0].length + 2) * 100;
                    else
                        screenWidth = (document.body.clientWidth / 50 - 2) * 50;
                    FixTable("table", "th", screenWidth, screenhight);
                    //最后给td绑定点击事件 进行反查
                    $("td[loadData]").off("click").on("click", function () {
                        makeBUIGrid($(this));
                    })
                }
            });
        }

        if ("<%=type%>" == 'fss_01_2' || "<%=type%>" == 'fss_01_4' || "<%=type%>" == 'fss_01_6' || "<%=type%>" == 'fss_01_10') {
            $.post("<%=basePath%>chart/getNewReportTable", param, function (result) {
                result = JSON.parse(result);

                generTable();

                //生成最终表格
                function generTable() {

                    var left = result.leftArray;
                    var title = result.title;
                    var a, b, data = result.list;
                    var tableWidth, colwidth;
                    if (data[0].length <= 15) {
                        tableWidth = (data[0].length + 2) * 100 + "px";
                        colwidth = "100px";
                    }
                    else if (data[0].length > 15 && data[0].length <= 25) {
                        tableWidth = (data[0].length + 2) * 70 + "px";
                        colwidth = "70px";
                    }
                    else {
                        tableWidth = (data[0].length + 2) * 50 + "px";
                        colwidth = "50px";
                    }
                    $("#table").css("width", tableWidth);
                    a += "<thead><tr><th colspan='" + (data[0].length + 2) + "'>各        部        门        人        数</th></tr>";
                    a += "<tr><th class='myth' colspan='2' rowspan='3' style='width: 200px'> 计算方法：<br/>列:1=2+至" + data[0].length;
                    a += "<br/>行：1=2+至" + (data.length - 2) + " </th></tr>";
                    $.each(data, function (n, list) {
                        if (n == 0) {
                        a += "<tr>" ;
                            $.each(list, function (index, obj) {
                                a += "<th width=" + colwidth + ">" + obj + "</th>";
                            })
                            a += "</tr>";
                        }
                        else if (n == 1) {
                            a += "<tr>"
                            $.each(list, function (index, obj) {
                                a += "<th width=" + colwidth + ">" + obj + "</td>";
                            })
                            a += "</tr></thead><tbody>";
                        }
                        else {
                            var n_ = n - 2;
                            a += "<tr><th width='200px'>" + left[n_].name + "</th> " +
                                "<th width='50px' align='center'>" + left[n_].num + "</th>";
                            $.each(list, function (key, value) {
                                a += "<td align='center' loadData ";
                                for (var k in value) {
                                    var v = value[k];
                                    if (k == 'count') {
                                        continue;
                                    }
                                    a += " data-" + k + "='" + v + "' ";
                                }
                                a += ">";
                                a += value.count + "</td><br/>";
                            });
                            a += "</tr>";
                        }

                    });
                    a += "</tbody>";
                    $("#table").append(a);
                    $("#all").show();
                    var screenhight = document.body.clientHeight - 130;
                    var screenWidth
                    if (document.body.clientWidth / 50 > data[0].length + 2 && data[0].length > 10)
                        screenWidth = (data[0].length + 3) * 50;
                    else if (document.body.clientWidth / 50 > data[0].length + 2 && data[0].length <= 10)
                        screenWidth = (data[0].length + 2) * 100;
                    else
                        screenWidth = (document.body.clientWidth / 50 - 2) * 50;
                    FixTable("table", "th", screenWidth, screenhight);

                    //最后给td绑定点击事件 进行反查
                    $("td[loadData]").off("click").on("click", function () {
                        makeBUIGrid($(this));
                    })

                }

            });
        }
    }
    function todownLoad(){
        var attachName_ = "<%=attachName%>";
        var attachName = attachName_== ""?attachName_:"--"+attachName_;
        var preparationName_ = "<%=preparationName%>";
        var preparationName = preparationName_== ""?"":"--"+preparationName_;
        var fileName;
        if ("<%=type%>"=='fss_01_2') {
            fileName = "法院工作人员情况统计表之二(法律职务)";
        } else if("<%=type%>"=='fss_01_4') {
            fileName = "法院工作人员情况统计表之四（行政职务）";
        } else if ("<%=type%>"=='fss_01_6') {
            fileName = "法院工作人员情况统计表之六";
        } else if ("<%=type%>"=='fss_01_10') {
            fileName = "法院工作人员统计表之九（法官助理、书记员等级信息）";
        }
        if ("<%=type%>" == 'fss_01_11') {
            fileName = "法院工作人员统计表之十（刑事、民事、执行、立案、行政 部门下的法官数量统计报表）";
        }
        fileName = fileName + attachName + preparationName;

        $("#table").table2excel({
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
    function makeBUIGrid ($obj){

        if(!dataDialog){
            initDataBUI();
        }

        var leftColumn = $obj.data("leftcolumn");
        var leftValue = $obj.data("leftvalue");
        var topColumn = $obj.data("topcolumn");
        var topValue = $obj.data("topvalue");

        var condition = $.extend({
            "queryEntity.leftColumn" : leftColumn,
            "queryEntity.leftValue" : leftValue,
            "queryEntity.topColumn" : topColumn,
            "queryEntity.topValue" : topValue,
            "userType" : 1,
            "start" : 0,
            "pageIndex" : 0
        }, param);
        if ("<%=type%>"=='fss_01_10') {
            condition.typeStr = '<%=type%>';
        }
        if ("<%=type%>" == 'fss_01_11') {
            condition.typeStr = '<%=type%>';
        }
        dataStore.load(condition);
        dataDialog.show();

    }

    //查询编码

    function initDataBUI(){


        BUI.use(['common/search', 'bui/tree', 'bui/data', 'bui/calendar', 'bui/overlay', 'bui/form', 'bui/uploader', 'bui/mask', 'bui/grid'],
            function (Search, Tree, Data, Calendar, Overlay, Form, Uploader, Mask, Grid) {

                //定义表格列
                var columns = [
                    {
                        title: '序号', sortable: false, width: "60", align: 'left', renderer: function (value, obj) {
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
                    plugins: [ BUI.Grid.Plugins.RadioSelection, BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格
                });

                var url = "<%=basePath%>chart/fc";
                dataStore = Search.createStore(url, {
                    remoteSort: true,
                    pageSize: 20,
                    autoLoad: false,
                    params: {

                    },
                });

                var search = new Search({
                    store: dataStore,
                    gridCfg: gridCfg
                });
                var dataGrid = search.get('grid');

                dataGrid.on("beforeitemsshow",function(){
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

<div id="all" style="text-align: center ;width: 100%" hidden >
    <table  id="table" class="biaonr">
    </table>
</div>

<%--反查弹出--%>
<div id="data-dialog" style="display:none;">
    <div id="grid">

    </div>
</div>

<br/><br/>
<script src="js/stat.js" type="text/javascript"></script>
</body>
</html>
