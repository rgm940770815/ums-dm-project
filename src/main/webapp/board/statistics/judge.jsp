<%--
  Created by IntelliJ IDEA.
  User: baish
  Date: 2019/8/14
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>法官专项统计</title>
    <link rel="stylesheet" href="../../common/layui/css/layui.css">
    <link rel="stylesheet" href="css/judge.css">
    <script type="text/javascript" src="<%=basePath%>js/encodeParam.js"></script>
    <script src="../../js/jquery/jquery.js" type="text/javascript"></script>
    <script src="../../common/layui/layui.js" type="text/javascript"></script>
    <script src="<%=basePath%>/js/echarts3/echarts.js" type="text/javascript"></script>
    <script src="<%=basePath%>/js/common/echrtsCommon.js" type="text/javascript"></script>
</head>
<body>

<div class="header">

    <form class="layui-form" action="">

        <h2 class="court_txt">

        </h2>

        <div class="layui-inline select-fydm" style="display: none">
            <label class="layui-form-label">切换法院</label>
            <div class="layui-input-inline">
                <select name="fydms" lay-verify="required" lay-search="" lay-filter="fydm">
                </select>
            </div>
        </div>
    </form>
</div>

<div class="base-info">
    <h3></h3>
</div>

<div class="chart-info" style="margin-top: 5px;">

    <div class="site-tree">

        <ul class="layui-tree ">
            <li chart="lawyer_level" class="layui-this"><h2>法官等级</h2></li>
            <li chart="educationBackground"><h2>学历</h2></li>
            <li chart="age"><h2>年龄</h2></li>
            <li chart="department"><h2>部门人员数</h2></li>
        </ul>
    </div>

    <div class="chart-div">
        <div id="lawyer_level_div" class="chart-parent">
            <h2 class="featurette-heading">法官等级</h2>
            <div id="lawyer_levelChart" class="chart"></div>
        </div>
        <div id="educationBackground_div" class="chart-parent">
            <h2 class="featurette-heading">学历</h2>
            <div id="educationBackgroundChart" class="chart"></div>
        </div>
        <div id="age_div" class="chart-parent">
            <h2 class="featurette-heading">年龄</h2>
            <div id="ageChart" class="chart"></div>
        </div>
        <div id="department_div" class="chart-parent">
            <h2 class="featurette-heading">部门人员数</h2>
            <div id="departmentChart" class="chart" ></div>
        </div>
    </div>

</div>


<script type="text/javascript">


    var fydm = "";
    var fyname = "";
    //触发统计的图形
    var chartL = "";
    var lawyer_levelChart;
    var educationBackgroundChart;
    var ageChart;
    var departmentChart;
    $(function () {

        layui.use(['form'], function () {
            var form = layui.form;
            //获取权限
            $.ajax({
                url: '<%=basePath%>chartData/chartAuth',
                type: "post",
                data: {},
                dataType: "json",
                success: function (res) {
                    if (res) {
                        fydm = res.courtCode;
                        fyname = res.courtName;
                        if (res.auth && res.court) {
                            var $select = $("select[name='fydms']");
                            $select.append($("<option></option>").attr("value", "all").html("全部"));
                            $.each(res.court, function (key, val) {
                                $select.append($("<option></option>").attr("value", key).html(val))
                            });
                            fydm = "all";
                            fyname = "全部";
                            $select.val(fydm);
                            $(".select-fydm").show();
                            form.render('select');
                            //监听事件
                            form.on('select(fydm)', function (data) {
                                var $selected = $(data.elem).find("option:selected");
                                fydm = $selected.val();
                                fyname = $selected.text();
                                $(".court_txt").html(fyname + " 法官专项统计");
                                triggerCount();
                            });
                        }
                        $(".court_txt").html(fyname + " 法官专项统计");
                        //获取到基本信息
                        chartL = "lawyer_level";
                        triggerCount();
                    }
                }
            })
        });

        //切换统计项
        $(".site-tree .layui-tree li").off("click").on("click", function () {
            $(".layui-this").removeClass("layui-this");
            $(this).addClass("layui-this");
            //触发统计
            var $li = $(this);
            if (!$li.is("li")) {
                $li = $li.closest("li");
            }
            var type = $li.attr("chart");
            if (chartL !== type) {
                if (type) {
                    chartL = type;
                    getChartData(chartL);
                }
            }
        });
    });

    function triggerCount() {
        // 第一次加载getChartData
        getChartData("base");
        if (chartL) {
            // 第二次加载getChartData
            getChartData(chartL);
        }
    }

    // 获取图标数据
    function getChartData(type) {
        if (!type) {
            return;
        }
        // 如果是全部法院，就查询该人权限下的所有法院
        $.ajax({
            url: '<%=basePath%>chartData/judgeChartData',
            type: "post",
            data: {chart: encodeParam(type), fydm: encodeParam(fydm)},
            dataType: "json",
            success: function (res) {
                if (res) {
                    console.log(res);
                    if (type === 'base') {
                        //拼接基本信息的说明
                        var txt = "<span class='sp1' >" + fyname + "</span>" +
                            "总共<span class='sp2' onclick='fc(\"base\",\"all\",\"" + fydm + "\")' >" + res.all + "</span>人，" +
                            "其中男性<span class='sp2' onclick='fc(\"base\",\"male\",\"" + fydm + "\")'>" + res.male + "</span>名，女性<span class='sp2' onclick='fc(\"base\",\"female\",\"" + fydm + "\")'>" + res.female + "</span>名；" +
                            // "<br><span class='sp1' style='visibility: hidden'>" + fyname + "</span>" +
                            "<span style='font-weight: 600'>员额法官</span>总人员<span class='sp2' onclick='fc(\"base\",\"yefg\",\"" + fydm + "\")'>" + res.yefg + "</span>名，" +
                            "其中男性<span class='sp2' onclick='fc(\"base\",\"male_yefg\",\"" + fydm + "\")'>" + res.male_yefg + "</span>名，女性<span class='sp2' onclick='fc(\"base\",\"female_yefg\",\"" + fydm + "\")'>" + res.female_yefg + "</span>名；";
                        $(".base-info h3").html(txt);
                    } else {
                        var chartDiv = $("#" + type + "_div");
                        var chartX = type + 'Chart';
                        var $chart = eval(type + 'Chart');
                        var chartType = "pie";
                        if (type === 'department') {
                            chartType = "barWithScroll";
                            //如果部门过多造成显示很密集 使用dataXoom 过滤显示
                            if(res.name && res.name.length > 0){
                                //最多显示 10 条数据
                                var end = Math.ceil(100 * 10 / res.name.length );
                                res['end'] =  end;
                            }
                        }
                        $(".chart-parent").hide();
                        chartDiv.css("display", "-webkit-flex");

                        if (!$chart) {
                            //初始化
                            $chart = commonJs.initCharts(chartX);
                            $chart.showLoading({text: '数据分析中...'});
                            switch (type) {
                                case "lawyer_level":
                                    lawyer_levelChart = $chart;
                                    break;
                                case "educationBackground":
                                    educationBackgroundChart = $chart;
                                    break;
                                case "age":
                                    ageChart = $chart;
                                    break;
                                case "department":
                                    departmentChart = $chart;
                                    break;
                            }
                            //绑定事件
                            $chart.on('click', function (params) {
                                console.log(params);
                                if (params.componentType === 'series' && (params.componentSubType === 'bar'
                                    || params.componentSubType === 'pie' || params.componentSubType === "line")) {

                                    var chart = params.data['key'];
                                    var key = params.data['keyValue'];

                                    fc(chart, key, fydm);

                                }
                            });
                        }
                        getBar($chart, res, chartType, null, null);

                    }


                }
            }
        });
    }

    //反查
    function fc(type, key, fydm) {
        if (!type || !key || !fydm) {
            return;
        }
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.open({
                title: "人员列表",
                type: 2,    //类型2,iframe弹窗
                area: ['80vw', '90%'],
                fixed: false, //不固定
                maxmin: true,  //最大最小化按钮
                content: 'judge_fc.jsp?type=' + type + "&key=" + key + "&fydm=" + fydm,  //内部页面
                scrollbar: false    //屏蔽滚动条
            });
        })


    }
</script>
</body>
</html>
