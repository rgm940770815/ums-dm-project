<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>入额法官统计</title>
    <jsp:include page="/basic_import.jsp"></jsp:include>
    <link href="css/biao.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="../../common/layui/css/layui.css">
    <script src="../../common/layui/layui.js" type="text/javascript"></script>
    <script src="js/jquery.table2excel.js" type="text/javascript"></script>
    <script src="js/stat.js" type="text/javascript"></script>
    <style>
        .searchDiv {
            padding-top: 15%;
        }

        .searchDivAfterCount {
            padding-top: 1%;
            margin-left: -70%;
        }
    </style>
</head>
<body style="height: auto;">

<div>
    <h1 class="centered" style="font-size: xx-large;font-weight: bold;padding-top: 1%;">入额法官统计</h1>

    <div id="searchDiv" class="searchDiv">
        <div class="centered">
            <label style="width: 110px; height: 30px;">入额时间范围：</label>
            <input id="startDate" type="text" class="calendar"> - <input id="endDate" type="text" class="calendar">
            <button id="countButton" type="button" class="button button-primary search">开始统计</button>
        </div>
    </div>
    <%--装在统计数据的表格--%>

    <div id="all" style="text-align: center ;width: 100%;padding-top: 1%;" hidden>
        <table id="table" class="biaonr"></table>
    </div>

</div>


<script type="text/javascript">

    // 渲染时间控件
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        laydate.render({
            elem: '#startDate' //指定元素
        });
        laydate.render({
            elem: '#endDate' //指定元素
        });
    });

    // 切换样式
    $("#countButton").click(function () {

        // 开始时间
        var startDate = $("#startDate").val();
        // 结束时间
        var endDate = $("#endDate").val();
        if (startDate == '' || endDate == '') {
            layui.use('layer', function(){
                var layer = layui.layer;

                layer.msg('开始时间与结束时间都必须填写');
            });
            return;
        }
        // 搜索框的样式
        $("#searchDiv").addClass("searchDivAfterCount");

        $("#table").html("");
        $("#download_div").remove();
        $("#searchDiv").append("<div id='download_div' style='padding-left: 85%;font-size: 14px'><span>注:绿地白字:为了便于查看统计数量大于0的数据</span></br><a style='cursor: pointer; color: deepskyblue;' onclick='todownLoad()'>下载表格</a></div>");
        // 请求表格数据
        $.post("<%=basePath%>chart/getNewReportTable", {startDate: startDate, endDate: endDate, typeStr: "fss_01_12"}, function (result) {

            if (result == "json") {
                layui.use('layer', function(){
                    var layer = layui.layer;

                    layer.msg('未查询到数据');
                });
                return;
            }

            // 解析json格式
            result = JSON.parse(result);
            // 获取左边列
            var left = result.leftArray;
            // 获取数据
            var data = result.list;
            // table 宽
            var tableWidth = (data[0].length + 2) * 100 + "px";
            // 列宽
            var colwidth = "40px";
            $("#table").css("width", tableWidth);

            // 拼接的table html代码
            var a;
            a += "<thead>";
            a += "<tr><th class='myth' colspan='2' rowspan='3' style='width: 100px'><br/>";
            a += "<br/></th></tr>";

            $.each(data, function (n, list) {
                if (n == 0) {
                    a += "<tr>";
                    $.each(list, function (index, obj) {
                        a += "<th width=" + colwidth + ">" + obj + "</th>";
                    });
                    a += "</tr>";
                } else if (n == 1) {
                    a += "<tr>";
                    $.each(list, function (index, obj) {
                        a += "<th width=" + colwidth + ">" + obj + "</td>";
                    });
                    a += "</tr></thead><tbody>";
                } else {
                    var n_ = n - 2;
                    a += "<tr><th width='100px'>" + left[n_].name + "</th> " + "<th width='30px' align='center'>" + left[n_].num + "</th>";
                    $.each(list, function (key, value) {
                        a += "<td align='center' loadData ";
                        for (var k in value) {
                            var v = value[k];
                            if (k == 'count') {
                                if (v != 0) {
                                    a += "style='background-color: green;color: white;'";
                                }
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
            var screenhight = document.documentElement.clientHeight - 200;
            var screenWidth = document.body.clientWidth - 130;
            FixTable("table", "th", screenWidth, screenhight);
            //最后给td绑定点击事件 进行反查
            $("td[loadData]").off("click").on("click", function () {

                var leftValue = $(this).data("leftvalue");
                var topValue = $(this).data("topvalue");
                layui.use(['layer'], function () {

                    var layer = layui.layer;

                    layer.open({
                        type: 2,
                        content: 'ryStat12_fc.jsp',
                        title: '人员列表',
                        // area: ['1000px', '500px'],
                        area: ['80vw', '90%'],
                        fixed: false, //不固定
                        success: function (layero, index) {
                            var body = layer.getChildFrame('body', index);
                            var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                            iframeWin.startDate = startDate;
                            iframeWin.endDate = endDate;
                            iframeWin.leftValue = leftValue;
                            iframeWin.topValue = topValue;
                        }
                    });
                });
            });
        });
    });

    function todownLoad() {
        var fileName = "入额法官统计" + $("#startDate").val() + "--" + $("#endDate").val();
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

</script>
</body>
</html>