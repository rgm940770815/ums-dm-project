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
        var attachName_ = "<%=attachName%>";
        var attachName = attachName_== ""?attachName_:"--"+attachName_;
        var preparationName_ = "<%=preparationName%>";
        var preparationName = preparationName_== ""?"":"--"+preparationName_;
        if ("<%=type%>"=='fss_01_1') {
            $("#all").prepend("<div align='center' style='padding-top: 30px;font-size: xx-large'><b>法院工作人员情况统计表之一(法律职务)<span class='attachName'>"+attachName+preparationName+"</span></b></div>" +
                    "<div style='position: fixed;padding-top: 10px;padding-left: 95%;cursor: pointer;font-size: 14px'><a onclick='todownLoad()'>下载表格</a></div>"+
                    "<div align='left' style=' padding-left: 30px'>" +
                    "报表编号：01－1 " +
                    "<br/>制表机关：重庆市高级人民法院(汇总) " +
                    "<br/>备案机关：国家统计局 " +
                    "<br/>备案文号：国统办函[2001]130号</div>");

        }else if ("<%=type%>"=='fss_01_3'){
            $("#all").prepend("<div align='center' style='padding-top: 30px;font-size: xx-large'><b>法院工作人员情况统计表之三(行政职务)<span class='attachName'>"+attachName+preparationName+"</span></b></div>" +
                    "<div style='position: fixed;padding-top: 10px;padding-left: 95%;cursor: pointer;font-size: 14px'><a onclick='todownLoad()'>下载表格</a></div>"+
                    "<div align='left' style=' padding-left: 30px'>" +
                    "报表编号：01－3 " +
                    "<br/>制表机关：重庆市高级人民法院(汇总) " +
                    "<br/>备案机关：国家统计局 " +
                    "<br/>备案文号：国统办函[2001]130号</div>");
        }else if ("<%=type%>"=='fss_01_5'){
            $("#all").prepend("<div align='center' style='padding-top: 30px;font-size: xx-large'><b>法院工作人员情况统计表之五<span class='attachName'>"+attachName+preparationName+"</span></b></div>" +
                    "<div style='position: fixed;padding-top: 10px;padding-left: 95%;cursor: pointer;font-size: 14px'><a onclick='todownLoad()'>下载表格</a></div>"+
                    "<div align='left' style=' padding-left: 30px'>" +
                    "报表编号：01－5 " +
                    "<br/>制表机关：重庆市高级人民法院(汇总) " +
                    "<br/>备案机关：国家统计局 " +
                    "<br/>备案文号：国统办函[2001]130号</div>");
        } else if ("<%=type%>"=='fss_01_9') {
            $("#all").prepend("<div align='center' style='padding-top: 30px;font-size: xx-large'><b>法院工作人员统计表之八（人员分类）<span class='attachName'>" + attachName + preparationName + "</span></b></div>" +
                "<div style='position: fixed;padding-top: 10px;padding-left: 95%;cursor: pointer;font-size: 14px'><a onclick='todownLoad()'>下载表格</a></div>"+
                "<div align='left' style=' padding-left: 30px'>" +
                "报表编号：01－8 " +
                "<br/>制表机关：重庆市高级人民法院(汇总) " +
                "<br/>备案机关：国家统计局 " +
                "<br/>备案文号：国统办函[2001]130号</div>");
        }

        if ("<%=type%>" == 'fss_01_1' || "<%=type%>" == 'fss_01_3' || "<%=type%>" == 'fss_01_5' || "<%=type%>" == 'fss_01_9') {
            $.post("<%=basePath%>chart/getNewReportTable", param, function (result) {
                result = JSON.parse(result);

                generTable();

                //生成最终表格
                function generTable() {

                    var left = result.leftArray;
                    var title = result.title;
                    var nnavg = result.nnavg;
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
                    $.each(data, function (n, list) {
                        if (n != 0 && n != 1) {
                            var n_ = n - 2;
                            if ("<%=type%>" == 'fss_01_9' && (n_ == 1 || n_ == 9)) return true;
                            a += "<tr>";
                            if ("<%=type%>" == 'fss_01_9') {
                                if (n_ == 0) {
                                    a += "<th width=\"50\">/</th>";
                                } else if (n_ == 2) {
                                    a += "<th width=\"50\" rowspan=\"7\">法官</th>";
                                } else if (n_ == 10) {
                                    a += "<th width=\"50\" rowspan=\"5\">审判辅助人员</th>";
                                } else if (n_ == 15) {
                                    a += "<th width=\"50\" rowspan='3'>/</th>";
                                }
                            }
                            var num = n_ + 1;
                            if (n_ > 1) num = n_;
                            if (n_ > 9) num = n_ - 1;
                            a += "<th width='200px'>" + left[n_].name + "</th> " +
                                "<th width='50px' align='center'>" + num + "</th>";
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
                            if ("<%=type%>" == 'fss_01_1' || "<%=type%>" == 'fss_01_3' || "<%=type%>" == 'fss_01_5' || "<%=type%>" == 'fss_01_9') {
                                var leftValue = list["countAll"].leftValue;
                                var b;
                                if (leftValue == 'all') {
                                    var all = 0;
                                    var length = 0;
                                    for (var n in nnavg) {
                                        length++;
                                        all += nnavg[n];
                                    }
                                    b = Math.floor(all / length);
                                } else if (leftValue == 'other') {
                                    b = nnavg[''];
                                } else {
                                    b = nnavg[leftValue] || 0;
                                }
                                a += "<td align='center' loadData2>" + b + "</td><br/>";
                            }
                            a += "</tr>";
                        }

                    });
                    a += "</tbody>";
                    $("#table").append(a);
                    if ("<%=type%>" == 'fss_01_9') {
                        $("#table > thead > tr:nth-child(1) > th.myth").attr('colspan', 3);
                    }
                    $("#all").show();
                    var screenhight = document.body.clientHeight - 130;
                    var screenWidth
                    if (document.body.clientWidth / 50 > data[0].length + 2 && data[0].length > 15)
                        screenWidth = (data[0].length + 3) * 50;
                    else if (document.body.clientWidth / 50 > data[0].length + 2 && data[0].length <= 15)
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
        var fileName ;
        if ("<%=type%>"=='fss_01_1') {
            fileName = "法院工作人员情况统计表之一(法律职务)";
        } else if ("<%=type%>"=='fss_01_3') {
            fileName = "法院工作人员情况统计表之三(行政职务)";
        } else if ("<%=type%>"=='fss_01_5') {
            fileName = "法院工作人员情况统计表之五";
        } else if ("<%=type%>"=='fss_01_9') {
            fileName = "法院工作人员统计表之八（人员分类）";
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
        if ((topValue + "").indexOf(",other") > 0) {
            topValue = topValue.replace(",other", "");
        }

        var condition = $.extend({
            "queryEntity.leftColumn" : leftColumn,
            "queryEntity.leftValue" : leftValue,
            "queryEntity.topColumn" : topColumn,
            "queryEntity.topValue" : topValue,
            "userType" : 1,
            "start" : 0,
            "pageIndex" : 0
        }, param);
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
<div id="all" hidden>

    <table id="table" border="1" align="center" class="biaonr" style="width: 1450px">
        <thead>
            <tr>
                <th class='myth' colspan='2' rowspan='3' style='width: 200px'> 计算方法：<br/>列:1=2+3=4+5=6+至8=9+至16=17+至24<br/>行：1=2+至17 </th>
                <th rowspan="2" align="center" width="50">实有<br/>人数</th>
                <th colspan="2" align="center">性别</th>
                <th colspan="2" align="center">民族</th>
                <th colspan="3" align="center">政治面貌</th>
                <th colspan="7" align="center">年龄</th>
                <th colspan="9" align="center">文化程度</th>
                <th align="center" width="50">&nbsp;</th>
                <th align="center" width="50">&nbsp;</th>
            </tr>
            <tr>
                <th height="90"  width="50"  align="center">男</th>
                <th height="90"  width="50" align="center">女</th>
                <th height="90"  width="50" align="center">汉<br/>族</th>
                <th height="90"  width="50" align="center">少<br/>数<br/>民<br/>族</th>
                <th height="90"  width="50" align="center">共<br/>产<br/>党<br/>员</th>
                <th height="90"  width="50" align="center">共<br/>青<br/>团<br/>员</th>
                <th height="90"  width="50" align="center">其他</th>
                <th height="90"  width="50" align="center">35<br/>岁<br/>以<br/>下</th>
                <th height="90"  width="50" align="center">40
                    岁
                </th>
                <th height="90" width="50" align="center">41<br/>
                    |<br/>
                    45<br/>
                    岁
                </th>
                <th height="90" width="50"  align="center">46<br/>
                    |<br/>
                    50<br/>
                    岁
                </th>
                <th height="90" width="50"  align="center">51<br/>
                    |<br/>
                    55<br/>
                    岁
                </th>
                <th height="90" width="50"  align="center">56<br/>
                    |<br/>
                    60<br/>
                    岁
                </th>
                <th height="90" width="50" align="center">61<br/>
                    岁<br/>
                    以<br/>
                    上
                </th>
                <th height="90" width="50" align="center">博士
                </th>
                <th height="90" width="50" align="center">硕<br/>
                    士
                </th>
                <th height="90" width="50" align="center">研<br/>
                    究<br/>
                    生
                </th>
                <th height="90" width="50" align="center">大<br/>
                    <br/>
                    <br/>
                    学
                </th>
                <th height="90" width="50" align="center">大<br/>
                    <br/>
                    <br/>
                    专
                </th>
                <th height="90" width="50" align="center">高<br/>
                    中<br/>
                    中<br/>
                    专
                </th>
                <th height="90" width="50" align="center">初<br/>
                    <br/>
                    <br/>
                    中
                </th>
                <th height="90" width="50" align="center">小<br/>
                    学<br/>
                    以<br/>
                    下
                </th>
                <th height="90" width="50" align="center">未<br/>
                    知
                </th>
                <th height="90" width="50" align="center">专<br/>
                    业<br/>
                    证<br/>
                    书
                </th>
                <th height="90" width="50" align="center">平<br/>
                    均<br/>
                    年<br/>
                    龄
                </th>
            </tr>
            <tr>
                <th align="center">1</th>
                <th align="center">2</th>
                <th align="center">3</th>
                <th align="center">4</th>
                <th align="center">5</th>
                <th align="center">6</th>
                <th align="center">7</th>
                <th align="center">8</th>
                <th align="center">9</th>
                <th align="center">10</th>
                <th align="center">11</th>
                <th align="center">12</th>
                <th align="center">13</th>
                <th align="center">14</th>
                <th align="center">15</th>
                <th align="center">16</th>
                <th align="center">17</th>
                <th align="center">18</th>
                <th align="center">19</th>
                <th align="center">20</th>
                <th align="center">21</th>
                <th align="center">22</th>
                <th align="center">23</th>
                <th align="center">24</th>
                <th align="center">25</th>
                <th align="center">26</th>
            </tr>
        </thead>
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
