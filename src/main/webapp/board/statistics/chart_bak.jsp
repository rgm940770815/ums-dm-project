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

        <script src="<%=basePath%>js/acharts/1.0.16/acharts.js" type="text/javascript"></script>
        <script src="<%=basePath%>js/common/chart_helper.js" type="text/javascript"></script>
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

            a{
                cursor: pointer;
            }

            h3{
                padding: 0;
            }

        </style>

        <script>

            //initiating jQuery（初始化stickup）
            jQuery(function ($) {
                $(document).ready(function () {
                    //enabling stickUp on the '.navbar-wrapper' class
                    $('.navbar-wrapper').stickUp({
                        parts: {
                            0: 'main',
                            1: 'law_position',
                            2: 'position',
                            3: 'level',
                            4: 'type',
                            5: 'distribution',
                            6: 'admin'
                        },
                        itemClass: 'menuItem',
                        itemHover: 'active'
                    });
                });
            });

            $(function () {

                $.getJSON("<%=basePath%>chart/statChartData", {chart: "law_position"}, function (data) {
                    var config = $.ChartHelper.createPieChartConfig(data, {
                        theme: Chart.Theme.SmoothBase,
                        id: "chart-l",
                        legend: null, //不显示图例
//                        plotCfg: {
//                            margin: [50, 50, 100]
//                        },
                        tooltip: {
                            pointRenderer: function (point) {
                                return point.value + '人';
                            }
                        },
                        series: [{
                                size: '100%',
//                                labelHeight: 20
                            }]
                    });

                    var chart = new Chart(config);

                    chart.render();
                });

                $.getJSON("<%=basePath%>chart/statChartData", {chart: "law_position1"}, function (data) {
                    var config = $.ChartHelper.createPieChartConfig(data, {
                        theme: Chart.Theme.SmoothBase,
                        id: "chart-l1",
                        legend: null, //不显示图例
//                        plotCfg: {
//                            margin: [40, 40, 75]
//                        },
                        tooltip: {
                            pointRenderer: function (point) {
                                return point.value + '人';
                            }
                        },
                        series: [{
                                size: '100%',
//                                labelHeight: 20
                            }]
                    });

                    var chart = new Chart(config);

                    chart.render();
                });
                $.getJSON("<%=basePath%>chart/statChartData", {chart: "law_position2"}, function (data) {
                    var config = $.ChartHelper.createPieChartConfig(data, {
                        theme: Chart.Theme.SmoothBase,
                        id: "chart-l2",
                        legend: null, //不显示图例
//                        plotCfg: {
//                            margin: [40, 40, 75]
//                        },
                        tooltip: {
                            pointRenderer: function (point) {
                                return point.value + '人';
                            }
                        },
                        series: [{
                                size: '100%',
//                                labelHeight: 20
                            }]
                    });

                    var chart = new Chart(config);

                    chart.render();
                });
                $.getJSON("<%=basePath%>chart/statChartData", {chart: "law_position3"}, function (data) {
                    var config = $.ChartHelper.createPieChartConfig(data, {
                        theme: Chart.Theme.SmoothBase,
                        id: "chart-l3",
                        legend: null, //不显示图例
//                        plotCfg: {
//                            margin: [40, 40, 75]
//                        },
                        tooltip: {
                            pointRenderer: function (point) {
                                return point.value + '人';
                            }
                        },
                        series: [{
                                size: '100%',
//                                labelHeight: 20
                            }]
                    });

                    var chart = new Chart(config);

                    chart.render();
                });


                $.getJSON("<%=basePath%>chart/statChartData", {chart: "position"}, function (data) {
                    var config = $.ChartHelper.createColumnChartYConfig(data, {
                        theme: Chart.Theme.SmoothBase,
                        id: "chart-1",
                        legend: null //不显示图例
                    });

                    var chart = new Chart(config);

                    chart.render();
                });

                $.getJSON("<%=basePath%>chart/statChartData", {chart: "court_user"}, function (data) {
                    //////////计算人员最多和最少
                    var most = 0;
                    var least = 0;
                    $(data).each(function (i, item) {
                        if (data[most].y < item.y) {
                            most = i;
                        } else if (data[least].y > item.y) {
                            least = i;
                        }
                    });
                    $("#most").text(data[most].name);
                    $("#least").text(data[least].name);

                    var config = $.ChartHelper.createColumnChartYConfig(data, {
                        theme: Chart.Theme.SmoothBase,
                        id: "chart-2",
                        legend: null //不显示图例
                    });

                    var chart = new Chart(config);

                    chart.render();
                });

                $.getJSON("<%=basePath%>chart/statChartData", {chart: "user_gender"}, function (data) {
                    var total = data[0].y + data[1].y;
                    var man = data[0].y;
                    var woman = data[1].y;
                    $("#chart-3-title").html("<h3>总人数：" + total + "人，" + "男：" + man + "人，" + "女：" + woman + "人</h3>");
                    var config = $.ChartHelper.createPieChartConfig(data, {
                        theme: Chart.Theme.SmoothBase,
                        id: "chart-3",
                        legend: null, //不显示图例
                        plotCfg: {
                            margin: [50, 50, 100]
                        },
                        tooltip: {
                            pointRenderer: function (point) {
                                return point.value + '人';
                            }
                        }
                    });

                    var chart = new Chart(config);

                    chart.render();
                });

                $.getJSON("<%=basePath%>chart/statChartData", {chart: "legal_job"}, function (data) {
//                    var config = $.ChartHelper.createPieChartConfig(data, {
                    var config = $.ChartHelper.createColumnChartYConfig(data, {
                        theme: Chart.Theme.SmoothBase,
                        id: "chart-4",
                        legend: null, //不显示图例
                        //                    title: {
                        //                        text: "法律职务比例图"
                        //                    },
                        plotCfg: {
                            margin: [50, 50, 100]
                        },
//                        tooltip: {
//                            pointRenderer: function (point) {
//                                return point.value + '人';
//                            }
//                        },
//                        series: [{
//                                size: '60%',
//                                labelHeight: 20
//                            }]
                    });

                    var chart = new Chart(config);

                    chart.render();
                });

                $.getJSON("<%=basePath%>chart/statChartData", {chart: "lawyer_level"}, function (data) {
                    var config = $.ChartHelper.createColumnChartYConfig(data, {
                        theme: Chart.Theme.SmoothBase,
                        id: "chart-5",
                        //                    title: {
                        //                        text: "法官等级图"
                        //                    },
                        legend: null, //不显示图例
//                        series: [{
//                                size: '60%',
//                                labelHeight: 20
//                            }]
                    });

                    var chart = new Chart(config);

                    chart.render();
                });

                $.getJSON("<%=basePath%>chart/statChartData", {chart: "admin_job"}, function (data) {
                    //临时移除一个数据像这样写
                    $(data).each(function (i, item) {
                        if (item.name === "无") {
                            data.splice(i, 1);
                            return false;
                        }
                    });
                    var config = $.ChartHelper.createColumnChartYConfig(data, {
                        theme: Chart.Theme.SmoothBase,
                        id: "chart-6",
                        legend: null //不显示图例
                    });

                    var chart = new Chart(config);

                    chart.render();
                });


            })
                    ;


        </script>
    </head>
    <body>

        <!-- START THE NAVBAR -->
        <div class="header">
            <div class="nav">
                <ul>
                    <li class="menuItem active"><a href='#main'>信息通览</a><span></span></li>
                    <li class="menuItem"><a href='#law_position'>人员分类</a><span></span></li>
                    <li class="menuItem"><a href='#position'>法律职务</a><span></span></li>
                    <li class="menuItem"><a href='#level'>法官等级</a><span></span></li>
                    <li class="menuItem"><a href='#type'>职务类别</a><span></span></li>
                    <li class="menuItem"><a href='#distribution'>人员分布</a><span></span></li>
                    <li class="menuItem"><a href='#admin'>行政职务</a><span></span></li>
                </ul>
            </div>
        </div>
        <!-- END NAVBAR -->


        <!-- START THE INSTALLATION -->
        <div id="main" class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading">全市法院信息通览</h2>

                <p class="lead">

                <div class="detail-section">
                    <div id="chart-3-title"></div>
                </div>
                </p>

                <p class="lead">

                <div id="new-enter">
                    <h3>最近新进人员：</h3>

                    <div class="list"></div>
                </div>
                </p>
                <p class="lead">

                <div id="recently-alt">
                    <h3>最近职位调整人员：</h3>

                    <div class="list"></div>
                </div>
                </p>
            </div>
            <div class="col-md-5">
            </div>
        </div>
        <!-- END INSTALLATION -->

        <!-- START THE INSTALLATION -->
        <div id="law_position" class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading">全市法院人员分类情况</h2>
                <div style="padding-top: 20px;float: left;text-align: center;">
                    <div>
                        <label style="font-size: xx-large">全市法院人员分类情况</label>
                        <div class="detail-section" style="width: 515px;padding-top: 20px">
                            <div id="chart-l"></div>
                        </div>
                    </div>
                    <div>
                        <label style="font-size: xx-large">重庆高院人员统计</label>
                        <div style="width: 515px;padding-top: 20px">
                            <div id="chart-l1"></div>
                        </div>
                    </div>
                </div>
                <div style="padding-top: 20px;float: left;text-align: center">
                    <div>
                        <label style="font-size: xx-large">中级法院人员统计</label>
                        <div style="width: 515px;padding-top: 20px">
                            <div id="chart-l2"></div>
                        </div>
                    </div>
                    <div>
                        <label style="font-size: xx-large">基层法院人员统计</label>
                        <div style="width: 515px;padding-top: 20px">
                            <div id="chart-l3"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="position" class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading">法律职务比例图</h2>

                <p class="lead">

                <div class="detail-section">
                    <div id="chart-4"></div>
                </div>
                </p>
            </div>
            <div class="col-md-5">
            </div>
        </div>
        <!-- END INSTALLATION -->

        <!-- START THE INSTALLATION -->
        <div id="level" class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading">法官等级比例图</h2>

                <p class="lead">

                <div class="detail-section">
                    <div id="chart-5"></div>
                </div>
                </p>
            </div>
            <div class="col-md-5">
            </div>
        </div>
        <!-- END INSTALLATION -->

        <!-- START THE INSTALLATION -->
        <div id="type" class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading">职务类别比例图</h2>

                <p class="lead">

                <div class="detail-section">
                    <div id="chart-1"></div>
                </div>
                </p>
            </div>
            <div class="col-md-5">
            </div>
        </div>
        <!-- END INSTALLATION -->

        <!-- START THE INSTALLATION -->
        <div id="distribution" class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading">法院人员分布图</h2>

                <p class="lead">

                <div class="detail-section">
                    <div>
                        人员最多：<span id="most"></span><br>
                        人员最少：<span id="least"></span>
                    </div>
                    <div id="chart-2"></div>
                </div>
                </p>
            </div>
            <div class="col-md-5">
            </div>
        </div>
        <!-- END INSTALLATION -->

        <!-- START THE INSTALLATION -->
        <div id="admin" class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading">行政职务图</h2>

                <p class="lead">

                <div class="detail-section">
                    <div id="chart-6"></div>
                </div>
                </p>
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

            BUI.use(['bui/data', 'bui/overlay', 'bui/tab'], function (Data, Overlay, Tab) {

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
            });
        </script>
    </body>
</html>