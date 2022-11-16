<%--
  Created by D.Yang.
  Date: 2015/1/28 0028
  Time: 15:14
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <jsp:include page="/basic_import.jsp"></jsp:include>
    <link href="<%=basePath%>js/stickup/stickup.css" rel="stylesheet"/>
    <script src="<%=basePath%>js/stickup/stickUp.js" type="text/javascript"></script>

    <style>
        * {
            margin: 0;
            padding: 0;
            list-style: none;
            font-family: 'Microsoft Yahei';
        }

        img {
            border: 0;
        }

        .header {
            width: 100%;
            background: #F5F5F5;
            position: fixed;
            z-index: 9999;
            opacity: 0.8;
            top: 0;
        }

        .nav {
            text-align: center;
            overflow: hidden;
        }

        .nav ul {
            display: inline-block;
        }

        .nav ul li {
            height: 50px;
            line-height: 40px;
            float: left;
            padding: 5px 5px;
            margin: 0px 5px;
            position: relative;
        }

        .nav ul li a {
            color: #666;
            font-family: 'Microsoft Yahei';
            font-size: 14px;
            text-decoration: none;
        }

        .nav ul li a:hover {
            color: #000;
            text-decoration: none;
        }

        .nav ul li span {
            display: block;
            position: absolute;
            width: 0px;
            height: 0px;
            background: #1FAEFF;
            top: 58px;
            left: 50%;
        }

        a {
            cursor: pointer;
        }

        h3 {
            padding: 0;
        }

        .hide {
            display: none;
        }

        .show {
            display: block;
        }

        .active a {
            background-color: #eeeeee;
            color: #000 !important;
        }

        .active span {
            left: 0 !important;
            right: 0 !important;
            width: 100% !important;
            height: 2px !important;
        }

        .row {
            height: 100%;
            padding: 0 80px;
            margin-right: 0px;
            margin-left: 0px;
        }

        .chart {
            width: 80vw;
            height: 500px;
        }

        .chart2, .chart3 {
            width: 40vw;
            height: 400px;
        }

        .chart3 {
            margin-top: 50px;
        }

        /**内容超出 出现滚动条 **/
        .bui-stdmod-body {
            overflow-x: hidden;
            overflow-y: auto;
        }

        .row2 .featurette-heading{
            font-size: 22px;
            margin-bottom: 15px;
            margin-top: 18px;
            font-weight: 500;
        }
    </style>

    <script>

        function getData(index) {
            var chart = '', title = '';
            switch (index) {

                case 0:
                    getusergender();
                    return;
                    break;
                case 1:
                    chart = 'age';
                    title = '年龄';
                    break;
                case 2:
                    chart = 'educationBackground';
                    title = '学历';
                    break;
                case 3:
                    chart = 'gender';
                    title = '性别';
                    break;
                case 9:
                    chart = 'law_position1';
                    title = '重庆高院人员统计';
                    break;
                case 10:
                    chart = 'law_position2';
                    title = '中级法院人员统计';
                    break;
                case 11:
                    chart = 'law_position3';
                    title = '基层法院人员统计';
                    break;
                case 12:
                    chart = 'courtInstitution';
                    title = '基层法院人员统计';
                    break;
                case 13:
                    chart = 'law_position4';
                    title = '法院人员编制统计';
                    break;
                default:
                    return;
                    break;
            }
            console.log(chart);
            $.getJSON("<%=basePath%>chartData/statChartData", {chart: chart , queryAll : false ,queryFy : true}, function (data) {
                console.log(data);
                var $chart = eval(chart + 'Chart');

                //法院机构用bar
                if(chart == 'age'){

                    getBar($chart, data, 'bar', null, title);
                }else{
                    if(chart === 'law_position4'){
                        console.log(data);
                    }

                    getBar($chart, data, 'pie', null, title);

                    // if(!$chart.isSilent("click")){
                    //     $chart.on("click",function(ev1){
                    //         console.log(ev1);
                    //         var kv = ev1.data.keyValue;
                    //         var condition = {
                    //             chart : chart,
                    //             keyValue : kv
                    //         };
                    //         condition = $.extend({"start": 0, "pageIndex": 0}, condition);
                    //         baseStore.load(condition);
                    //         dialogForData.show();
                    //     });
                    // }
                }






            });
        }
        var lawyer_levelChart, rankChart, genderChart, ageChart, politicalChart, educationBackgroundChart, degreeChart;
        var law_positionChart4;

        $(function () {


            $.post("<%=basePath%>chartData/getUserCourtText",{},function(datas){
                $(".court_text").html(datas);
            });

            getusergender();

            lawyer_levelChart = commonJs.initCharts('lawyer_levelChart');
            lawyer_levelChart.showLoading({text: '数据分析中...'});

            rankChart = commonJs.initCharts('rankChart');
            rankChart.showLoading({text: '数据分析中...'});

            genderChart = commonJs.initCharts('genderChart');
            genderChart.showLoading({text: '数据分析中...'});

            ageChart = commonJs.initCharts('ageChart');
            ageChart.showLoading({text: '数据分析中...'});

            politicalChart = commonJs.initCharts('politicalChart');
            politicalChart.showLoading({text: '数据分析中...'});

            educationBackgroundChart = commonJs.initCharts('educationBackgroundChart');
            educationBackgroundChart.showLoading({text: '数据分析中...'});

            degreeChart = commonJs.initCharts('degreeChart');
            degreeChart.showLoading({text: '数据分析中...'});

            // law_positionChart = commonJs.initCharts('law_positionChart');
            // law_positionChart.showLoading({text: '数据分析中...'});
            //
            // law_position1Chart = commonJs.initCharts('law_position1Chart');
            // law_position1Chart.showLoading({text: '数据分析中...'});
            //
            // law_position2Chart = commonJs.initCharts('law_position2Chart');
            // law_position2Chart.showLoading({text: '数据分析中...'});
            //
            law_position4Chart = commonJs.initCharts('law_position4Chart');
            law_position4Chart.showLoading({text: '数据分析中...'});
            //
            // courtInstitutionChart = commonJs.initCharts('courtInstitutionChart');
            // courtInstitutionChart.showLoading({text: '数据分析中...'});
        })

        function getusergender() {
            // getData(9);
            // getData(10);
            // getData(11);
            getData(13);
            <%--$.getJSON("<%=basePath%>chart/statChartData", {chart: "user_gender"}, function (data) {--%>
            <%--var total = data[0].y + data[1].y;--%>
            <%--var man = data[0].y;--%>
            <%--var woman = data[1].y;--%>
            <%--$("#chart-3-title").html("<h3>总人数：" + total + "人，" + "男：" + man + "人，" + "女：" + woman + "人</h3>");--%>
            <%--});--%>
            <%--getRyfl('law_position', '全市法院人员分类情况');--%>
            <%--getRyfl('law_position1', '中级法院人员统计');--%>
            <%--getRyfl('law_position2', '重庆高院人员统计');--%>
            <%--getRyfl('law_position3', '基层法院人员统计');--%>
        }

        function getRyfl(chart, title) {
            $.getJSON("<%=basePath%>chart/statChartData", {chart: chart}, function (data) {
                var nameList = [];
                var valueList = [];
                for (var d in data) {
                    nameList.push(data[d]['name']);
                    valueList.push(data[d]['y']);
                }
                var dat = {
                    name: nameList,
                    value: valueList
                }
                getBar(eval(chart + 'Chart'), dat, 'pie', null, title);
            });
        }
    </script>
</head>
<body>

<!-- START THE NAVBAR -->
<div class="header">
    <div class="nav">
        <ul>
            <%--<li class="menuItem active"><a href='#'>信息通览</a><span></span></li>--%>
            <%--<li class="menuItem"><a href='#'>法官等级</a><span></span></li>--%>
            <%--<li class="menuItem"><a href='#'>职级</a><span></span></li>--%>
            <%--<li class="menuItem"><a href='#'>性别</a><span></span></li>--%>
            <%--<li class="menuItem"><a href='#'>年龄</a><span></span></li>--%>
            <%--<li class="menuItem"><a href='#'>政治面貌</a><span></span></li>--%>
            <%--<li class="menuItem"><a href='#'>学历</a><span></span></li>--%>
            <%--<li class="menuItem"><a href='#'>学位</a><span></span></li>--%>
                <li class="menuItem active"><a href='#'>人员分类</a><span></span></li>
                <li class="menuItem"><a href='#'>年龄</a><span></span></li>
                <li class="menuItem"><a href='#'>学历</a><span></span></li>
                <li class="menuItem"><a href='#'>性别</a><span></span></li>
        </ul>
    </div>
</div>


<!-- END NAVBAR -->


<!-- START THE INSTALLATION -->
<div id="main" class="row row2 show">
    <%--<div class="col-md-7">--%>
        <%--<h2 class="featurette-heading">全市法院信息通览</h2>--%>

        <%--<p class="lead">--%>

        <%--<div class="detail-section">--%>
            <%--<div id="chart-3-title"></div>--%>
        <%--</div>--%>
        <%--</p>--%>

        <%--<p class="lead">--%>

        <%--<div id="new-enter">--%>
            <%--<h3>最近新进人员：</h3>--%>

            <%--<div class="list"></div>--%>
        <%--</div>--%>
        <%--</p>--%>
        <%--<p class="lead">--%>

        <%--<div id="recently-alt">--%>
            <%--<h3>最近职位调整人员：</h3>--%>

            <%--<div class="list"></div>--%>
        <%--</div>--%>
        <%--</p>--%>
    <%--</div>--%>

    <div class="col-md-7">
        <div style="margin-top: 78px;
        font-size: 27px" class="court_text">
            <%--重庆市高级人民法院--%>
        </div>
        <h2 class="featurette-heading" >法院编制</h2>
        <div  id="law_position4Chart" class="chart">

        </div>

    </div>
    <%--<div class="col-md-7">--%>
        <%--<h2 class="featurette-heading">全市法院人员分类情况</h2>--%>
        <%--<div style="padding-top: 20px;float: left;text-align: center;">--%>
            <%--<div id="law_positionChart" class="chart2"></div>--%>
            <%--<div id="law_position1Chart" class="chart3"></div>--%>
        <%--</div>--%>
        <%--<div style="padding-top: 20px;float: left;text-align: center;margin-left: 100px;">--%>
            <%--<div id="law_position2Chart" class="chart2"></div>--%>
            <%--<div id="law_position3Chart" class="chart3"></div>--%>
        <%--</div>--%>
    <%--</div>--%>
</div>

<!-- START THE INSTALLATION -->
<div id="type" class="row row2 hide">
    <div class="col-md-7">
        <div style="margin-top: 78px;
        font-size: 27px" class="court_text">
            <%--重庆市高级人民法院--%>
        </div>
        <h2 class="featurette-heading">年龄</h2>
        <div id="ageChart" class="chart"></div>
    </div>
    <div class="col-md-5">
    </div>
</div>
<!-- END INSTALLATION -->

<!-- START THE INSTALLATION -->
<div id="type" class="row row2 hide">
    <div class="col-md-7">
        <div style="margin-top: 78px;
        font-size: 27px" class="court_text">
            <%--重庆市高级人民法院--%>
        </div>
        <h2 class="featurette-heading">学历</h2>
        <div id="educationBackgroundChart" class="chart"></div>
    </div>
    <div class="col-md-5">
    </div>
</div>
<!-- END INSTALLATION -->

<!-- START THE INSTALLATION -->
<div id="level" class=" row row2 hide">
    <div class="col-md-7">
        <div style="margin-top: 78px;
        font-size: 27px" class="court_text">
            <%--重庆市高级人民法院--%>
        </div>
        <h2 class="featurette-heading">性别</h2>
        <div id="genderChart" class="chart"></div>
    </div>
    <div class="col-md-5">
    </div>
</div>
<!-- END INSTALLATION -->

<!-- START THE INSTALLATION -->
<div id="law_position" class="row hide">
    <div class="col-md-7">
        <h2 class="featurette-heading">法官等级</h2>
        <div id="lawyer_levelChart" class="chart"></div>
    </div>
</div>
</div>

<div id="position" class="row hide">
    <div class="col-md-7">
        <h2 class="featurette-heading">职级</h2>
        <div id="rankChart" class="chart"></div>
    </div>
    <div class="col-md-5">
    </div>
</div>
<!-- END INSTALLATION -->






<!-- START THE INSTALLATION -->
<div id="type" class="row hide">
    <div class="col-md-7">
        <h2 class="featurette-heading">政治面貌</h2>
        <div id="politicalChart" class="chart"></div>
    </div>
    <div class="col-md-5">
    </div>
</div>
<!-- END INSTALLATION -->



<!-- START THE INSTALLATION -->
<div id="type" class="row hide">
    <div class="col-md-7">
        <h2 class="featurette-heading">学位</h2>
        <div id="degreeChart" class="chart"></div>
    </div>
    <div class="col-md-5">
    </div>
</div>
<!-- END INSTALLATION -->

<%--人员信息--%>
<div id="user-detail" class="doc-content span24 hide">
    <div id="user-detail-tab"></div>
    <div id="user-detail-data">
        <div>
            <table id="t1" class="table">
                <tr>
                    <td rowspan="7" style="text-align: center;vertical-align:  central;padding: 0"><img width="150"
                                                                                                        height="200"
                                                                                                        id="photo"
                                                                                                        style="margin: 0">
                    </td>
                    <td class="labal">编号：</td>
                    <td><span id="userCode"></span></td>
                    <td class="labal"><s>*</s>身份证号：</td>
                    <td><span id="idcard"></span></td>
                </tr>
                <tr>
                    <td class="labal"><s>*</s>部门：</td>
                    <td><span id="departmentText"></span></td>
                    <td class="labal"></td>
                    <td></td>
                </tr>
                <tr>
                    <td class="labal"><s>*</s>姓名：</td>
                    <td><span id="fullname"></span></td>
                    <td class="labal">曾用名：</td>
                    <td><span id="formerName"></span></td>
                </tr>
                <tr>
                    <td class="labal"><s>*</s>性别：</td>
                    <td><span id="genderText"></span></td>
                    <td class="labal"><s>*</s>民族：</td>
                    <td><span id="nation"></span></td>
                </tr>
                <tr>
                    <td class="labal"><s>*</s>婚姻状况：</td>
                    <td><span id="maritalStatusText"></span></td>
                    <td class="labal"><s>*</s>健康状况：</td>
                    <td><span id="physicalConditionText"></span></td>
                </tr>
                <tr>
                    <td class="labal"><s>*</s>出生日期：</td>
                    <td><span id="birthday"></span></td>
                    <td class="labal">年龄：</td>
                    <td><span id="age"></span></td>
                </tr>
                <tr>
                    <td class="labal"><s>*</s>籍贯：</td>
                    <td><span id="hometown"></span></td>
                    <td class="labal">出生地：</td>
                    <td><span id="birthplace"></span></td>
                </tr>
            </table>
        </div>


        <div>
            <table id="t2" class="table">
                <tr>
                    <td>职务类别：</td>
                    <td><span id="positionTypeText"></span></td>
                    <td>职务类别日期：</td>
                    <td><span id="positionTypeDate"></span></td>
                    <td>任用方式：</td>
                    <td><span id="assignText"></span></td>
                </tr>
                <tr>
                    <td>政治面貌：</td>
                    <td><span id="politicalText"></span></td>
                    <td>加入日期：</td>
                    <td><span id="politicalDate"></span></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td><strong>法律职务：</strong></td>
                    <td><span id="lawPositionText"></span></td>
                    <td><strong>任职日期：</strong></td>
                    <td><span id="lawPositionDate"></span></td>
                    <td>法官资格日期：</td>
                    <td><span id="lawyerDate"></span></td>
                </tr>
                <tr>
                    <td>兼任庭长：</td>
                    <td><span id="isParttimePresidingJudgeText"></span></td>
                    <td>党组任职：</td>
                    <td><span id="partyOfficeText"></span></td>
                    <td>党组职务日期：</td>
                    <td><span id="partyOfficeDate"></span></td>
                </tr>
                <tr>
                    <td><strong>行政职务：</strong></td>
                    <td><span id="administrationPositionText"></span></td>
                    <td>任职日期：</td>
                    <td><span id="administrationPositionDate"></span></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td><strong>职级：</strong></td>
                    <td><span id="rankText"></span></td>
                    <td><strong>职级日期：</strong></td>
                    <td><span id="rankDate"></span></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td><strong>等级：</strong></td>
                    <td><span id="levelText"></span></td>
                    <td><strong>等级日期：</strong></td>
                    <td><span id="levelDate"></span></td>
                    <td>编制：</td>
                    <td><span id="preparationText"></span></td>
                </tr>
                <tr>
                    <td>公务员级别：</td>
                    <td><span id="servantLevelText"></span></td>
                    <td>公务员级别起算日期：</td>
                    <td><span id="servantLevelDate"></span></td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
        </div>


        <div>
            <table id="t3" class="table">
                <tr>
                    <td><strong>学历：</strong></td>
                    <td><span id="educationBackgroundText"></span></td>
                    <td>学位：</td>
                    <td><span id="degreeText"></span></td>
                    <td>获得学位日期：</td>
                    <td><span id="degreeDate"></span></td>
                </tr>
                <tr>
                    <td><strong>专业：</strong></td>
                    <td><span id="majorText"></span></td>
                    <td><strong>专业证书：</strong></td>
                    <td><strong><span id="proCertText"></span></strong></td>
                    <td>获得证书日期：</td>
                    <td><span id="proCertDate"></span></td>
                </tr>
                <tr>
                    <td><strong>取得法官资格证书时间：</strong></td>
                    <td><span id="lawyerCertDate"></span></td>
                    <td>取得法官资格证书类型：</td>
                    <td><span id="lawyerCertText"></span></td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
        </div>

        <div>
            <table id="t4" class="table">
                <tr>
                    <td><s>*</s><strong>工作日期：</strong></td>
                    <td><span id="workDate"></span></td>
                    <td>政法工作日期：</td>
                    <td><span id="politicLawWorkDate"></span></td>
                    <td><s>*</s>进院日期：</td>
                    <td><span id="enterDate"></span></td>
                </tr>
                <tr>
                    <td><s>*</s>补充工龄：</td>
                    <td><span id="extraSeniority"></span>年</td>
                    <td><s>*</s>扣减工龄：</td>
                    <td><span id="deductionSeniority"></span>年</td>
                    <td>连续工龄：</td>
                    <td><span id="totalSeniority"></span>年</td>
                </tr>
                <tr>
                    <td><s>*</s>进本院前法院工作年限：</td>
                    <td><span id="beforeCourtWorkYear"></span>年</td>
                    <td>法院工作合计年限：</td>
                    <td><span id="totalCourtYear"></span>年</td>
                    <td>应加学制：</td>
                    <td><span id="additionalDuration"></span>年</td>
                </tr>
            </table>
        </div>

        <div>
            <table id="t5" class="table">
                <tr>
                    <td><s>*</s>进入途径：</td>
                    <td><span id="enterWayText"></span></td>
                    <td><s>*</s>进入来源：</td>
                    <td><span id="enterSourceText"></span></td>
                    <td>审核日期：</td>
                    <td><span id="verifyDate"></span></td>
                </tr>
                <tr>
                    <td>原职务：</td>
                    <td><span id="formerPostText"></span></td>
                    <td>原职级：</td>
                    <td><span id="formerRankText"></span></td>
                    <td>原单位：</td>
                    <td><span id="formerUnit"></span></td>
                </tr>
                <tr>
                    <td>调离原因：</td>
                    <td><span id="leaveReasonText"></span></td>
                    <td>调离日期：</td>
                    <td><span id="leaveDate"></span></td>
                    <td>调离去向：</td>
                    <td><span id="leaveDestinationText"></span></td>
                </tr>
            </table>
        </div>
    </div>
</div>

<div  id="dataGridContainer" class="hide">
    <div id="dataGrid" style="margin-left: 20px">
    </div>
</div>


<script src="<%=basePath%>js/common/datetools.js" type="text/javascript"></script>
<script>

    $(function () {
        $('.nav li').hover(function () {
            $('span', this).stop().css('height', '2px');
            $('span', this).animate({
                left: '0',
                width: '100%',
                right: '0'
            }, 200);
        }, function () {
            $('span', this).stop().animate({
                left: '50%',
                width: '0'
            }, 200);
        });
    });

    var dialogForData;
    var baseStore;
    BUI.use(['common/search', 'bui/data', 'bui/overlay', 'bui/tab'], function (Search, Data, Overlay, Tab) {

        /**
         * 把用户对象填充到窗体中
         * @param {type} item
         */
        function loadUserDataIntoPanel(item) {
            $("#user-detail-data td>span").empty();
            $("#user-detail-data #photo").removeAttr("src");
            $.getJSON("<%=basePath%>photo/getPhotoById", {userId: item["id"]}, function (photo) {
                $("#user-detail-data #photo").attr({"src": photo});
            });

            $("#user-detail-data td>span").each(function () {
                if (item[$(this).attr("id")]) {
                    $(this).text(item[$(this).attr("id")]);
                }
            });
            $("#user-detail-data #age").text(calcYears($("#user-detail-data #birthday").text()));
            $("#user-detail-data #totalSeniority").text(workTotalYears($("#user-detail-data #workDate").text(), $("#user-detail-data #extraSeniority").text(), $("#user-detail-data #deductionSeniority").text()));
            $("#user-detail-data #totalCourtYear").text(workTotalYears($("#user-detail-data #enterDate").text(), $("#user-detail-data #beforeCourtWorkYear").text()));
        }

        var store_new_enter = new Data.Store({
            url: "<%=basePath%>stat/newEnter",
            autoLoad: true
        }).on("load", function (e) {
            var rows = e.target.getResult();
            $("#new-enter .list").empty();
            $(rows).each(function (index, item) {
                $("#new-enter .list").append($('<a>').append(item.fullname).click(function () {
                    loadUserDataIntoPanel(item);
                    dialog.show();
                })).append("&nbsp;&nbsp;&nbsp;&nbsp;");
            });
        });

        var store_recent_alt = new Data.Store({
            url: "<%=basePath%>stat/recentAlt",
            autoLoad: true
        }).on("load", function (e) {
            var rows = e.target.getResult();
            $("#recently-alt .list").empty();
            $(rows).each(function (index, item) {
                $("#recently-alt .list").append($("<a>").append(item.fullname).click(function () {
                    loadUserDataIntoPanel(item);
                    dialog.show();
                })).append("&nbsp;&nbsp;&nbsp;&nbsp;");
            });
        });

        var tab = new Tab.TabPanel({
            render: '#user-detail-tab',
            elCls: 'nav-tabs',
            panelContainer: '#user-detail-data', //如果内部有容器，那么会跟标签项一一对应，如果没有会自动生成
            selectedEvent: 'mouseenter', //默认为click,可以更改事件
            autoRender: true,
            children: [
                {title: '基本信息', value: '1', selected: true},
                {title: '职务信息', value: '2'},
                {title: '教育信息', value: '3'},
                {title: '工作信息', value: '4'},
                {title: '调遣信息', value: '5'}
            ]
        });

        var dialog = new Overlay.Dialog({
            title: '模态窗口',
            width: 800,
            height: 410,
            contentId: "user-detail",
            //bodyContent: '<p>这是一个模态窗口,默认带有确认取消按钮</p>',
            buttons: [
                {
                    text: '确定',
                    elCls: 'button button-primary',
                    handler: function () {
                        this.close();
                    }
                }
            ]
        });

        //=============================================反查相关================================================================

        var userinfoActionUrl = "<%=basePath%>chartData/getUserData";
        var columns = [
            {title: '姓名', dataIndex: 'fullname', width: "100", sortable: true, align: 'left', renderer: function (value, obj) {

                    var spanStr = '<a class="grid-command btn-detail" title="显示人员详情信息">'+obj.fullname+'</a>';
                    return spanStr;

                }
            },
            {title: '性别', dataIndex: 'genderText', width: "50", sortable: true, align: 'left'},
            {title: '法院', dataIndex: 'courtNoText', width: "200", sortable: true, align: 'left'},
            {title: '部门', dataIndex: 'departmentText', width: "150", sortable: true, align: 'left'},
            {title: '学历', dataIndex: 'educationBackgroundText', width: "100", sortable: true, align: 'left'},
            {
                title: '行政职务',
                dataIndex: 'administrationPositionText',
                width: "100",
                sortable: true,
                align: 'left'
            }

        ];

        var store = Search.createStore(userinfoActionUrl, {
            sortField: 'sortNo',
            sortDirection: 'ASC',
            remoteSort: true,
            pageSize: 10,
            params: {
//                userType: 2,
//                isInfoComplete: 0,
//                isValid: 1
            },
            autoSync: true //保存数据后，自动更新
        });

        baseStore = store;

        var gridCfg = Search.createGridCfg(columns, {
            width: 1100,
            height: 440,
            //forceFit: true,
            emptyDataTpl: '<div class="centered"><h2>查询的数据不存在</h2></div>',
            plugins: [BUI.Grid.Plugins.RadioSelection, BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格
        });


        var search = new Search({
            store: store,
            gridCfg: gridCfg,
            gridId: "dataGrid"
        });
        var grid = search.get('grid');

        grid.on('cellclick',function(ev){
            var sender = $(ev.domTarget); //点击的Dom
            if (sender.hasClass('btn-detail')) {
                var record = ev.record;
                open("<%=basePath%>view/detail?id=" + record.id);
                return;
            }
        });
        dialogForData = new Overlay.Dialog({
            title: '人员信息',
            width: 1200,
//            height: 700,
            contentId: "dataGridContainer",
            //bodyContent: '<p>这是一个模态窗口,默认带有确认取消按钮</p>',
            buttons: [
                {
                    text: '确定',
                    elCls: 'button button-primary',
                    handler: function () {
                        this.close();
                    }
                }
            ]
        });
    });

    var contents = $('.row2');
    var tabs = $('.menuItem');

    tabs.each(function () {
        var _this = this;
        $(_this).children('a').unbind('click').on('click', function () {
            showTab(_this);
        })
    });

    function showTab(obj) {
        for (var i = 0, len = tabs.length; i < len; i++) {
            if (tabs[i] === obj) {
                $(tabs[i]).addClass('active');
                $(contents[i]).show();
                getData(i);
            } else {
                $(tabs[i]).removeClass('active');
                $(contents[i]).hide();
            }
        }
    }

</script>
</body>
</html>