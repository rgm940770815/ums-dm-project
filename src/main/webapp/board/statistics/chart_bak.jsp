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

            //initiating jQuery????????????stickup???
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
                        legend: null, //???????????????
//                        plotCfg: {
//                            margin: [50, 50, 100]
//                        },
                        tooltip: {
                            pointRenderer: function (point) {
                                return point.value + '???';
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
                        legend: null, //???????????????
//                        plotCfg: {
//                            margin: [40, 40, 75]
//                        },
                        tooltip: {
                            pointRenderer: function (point) {
                                return point.value + '???';
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
                        legend: null, //???????????????
//                        plotCfg: {
//                            margin: [40, 40, 75]
//                        },
                        tooltip: {
                            pointRenderer: function (point) {
                                return point.value + '???';
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
                        legend: null, //???????????????
//                        plotCfg: {
//                            margin: [40, 40, 75]
//                        },
                        tooltip: {
                            pointRenderer: function (point) {
                                return point.value + '???';
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
                        legend: null //???????????????
                    });

                    var chart = new Chart(config);

                    chart.render();
                });

                $.getJSON("<%=basePath%>chart/statChartData", {chart: "court_user"}, function (data) {
                    //////////???????????????????????????
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
                        legend: null //???????????????
                    });

                    var chart = new Chart(config);

                    chart.render();
                });

                $.getJSON("<%=basePath%>chart/statChartData", {chart: "user_gender"}, function (data) {
                    var total = data[0].y + data[1].y;
                    var man = data[0].y;
                    var woman = data[1].y;
                    $("#chart-3-title").html("<h3>????????????" + total + "??????" + "??????" + man + "??????" + "??????" + woman + "???</h3>");
                    var config = $.ChartHelper.createPieChartConfig(data, {
                        theme: Chart.Theme.SmoothBase,
                        id: "chart-3",
                        legend: null, //???????????????
                        plotCfg: {
                            margin: [50, 50, 100]
                        },
                        tooltip: {
                            pointRenderer: function (point) {
                                return point.value + '???';
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
                        legend: null, //???????????????
                        //                    title: {
                        //                        text: "?????????????????????"
                        //                    },
                        plotCfg: {
                            margin: [50, 50, 100]
                        },
//                        tooltip: {
//                            pointRenderer: function (point) {
//                                return point.value + '???';
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
                        //                        text: "???????????????"
                        //                    },
                        legend: null, //???????????????
//                        series: [{
//                                size: '60%',
//                                labelHeight: 20
//                            }]
                    });

                    var chart = new Chart(config);

                    chart.render();
                });

                $.getJSON("<%=basePath%>chart/statChartData", {chart: "admin_job"}, function (data) {
                    //????????????????????????????????????
                    $(data).each(function (i, item) {
                        if (item.name === "???") {
                            data.splice(i, 1);
                            return false;
                        }
                    });
                    var config = $.ChartHelper.createColumnChartYConfig(data, {
                        theme: Chart.Theme.SmoothBase,
                        id: "chart-6",
                        legend: null //???????????????
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
                    <li class="menuItem active"><a href='#main'>????????????</a><span></span></li>
                    <li class="menuItem"><a href='#law_position'>????????????</a><span></span></li>
                    <li class="menuItem"><a href='#position'>????????????</a><span></span></li>
                    <li class="menuItem"><a href='#level'>????????????</a><span></span></li>
                    <li class="menuItem"><a href='#type'>????????????</a><span></span></li>
                    <li class="menuItem"><a href='#distribution'>????????????</a><span></span></li>
                    <li class="menuItem"><a href='#admin'>????????????</a><span></span></li>
                </ul>
            </div>
        </div>
        <!-- END NAVBAR -->


        <!-- START THE INSTALLATION -->
        <div id="main" class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading">????????????????????????</h2>

                <p class="lead">

                <div class="detail-section">
                    <div id="chart-3-title"></div>
                </div>
                </p>

                <p class="lead">

                <div id="new-enter">
                    <h3>?????????????????????</h3>

                    <div class="list"></div>
                </div>
                </p>
                <p class="lead">

                <div id="recently-alt">
                    <h3>???????????????????????????</h3>

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
                <h2 class="featurette-heading">??????????????????????????????</h2>
                <div style="padding-top: 20px;float: left;text-align: center;">
                    <div>
                        <label style="font-size: xx-large">??????????????????????????????</label>
                        <div class="detail-section" style="width: 515px;padding-top: 20px">
                            <div id="chart-l"></div>
                        </div>
                    </div>
                    <div>
                        <label style="font-size: xx-large">????????????????????????</label>
                        <div style="width: 515px;padding-top: 20px">
                            <div id="chart-l1"></div>
                        </div>
                    </div>
                </div>
                <div style="padding-top: 20px;float: left;text-align: center">
                    <div>
                        <label style="font-size: xx-large">????????????????????????</label>
                        <div style="width: 515px;padding-top: 20px">
                            <div id="chart-l2"></div>
                        </div>
                    </div>
                    <div>
                        <label style="font-size: xx-large">????????????????????????</label>
                        <div style="width: 515px;padding-top: 20px">
                            <div id="chart-l3"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="position" class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading">?????????????????????</h2>

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
                <h2 class="featurette-heading">?????????????????????</h2>

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
                <h2 class="featurette-heading">?????????????????????</h2>

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
                <h2 class="featurette-heading">?????????????????????</h2>

                <p class="lead">

                <div class="detail-section">
                    <div>
                        ???????????????<span id="most"></span><br>
                        ???????????????<span id="least"></span>
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
                <h2 class="featurette-heading">???????????????</h2>

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

        <%--????????????--%>
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
                            <td class="labal">?????????</td>
                            <td><span id="userCode"></span></td>
                            <td class="labal"><s>*</s>???????????????</td>
                        <td><span id="idcard"></span></td>
                        </tr>
                        <tr>
                            <td class="labal"><s>*</s>?????????</td>
                        <td><span id="departmentText"></span></td>
                        <td class="labal"></td>
                        <td></td>
                        </tr>
                        <tr>
                            <td class="labal"><s>*</s>?????????</td>
                        <td><span id="fullname"></span></td>
                        <td class="labal">????????????</td>
                        <td><span id="formerName"></span></td>
                        </tr>
                        <tr>
                            <td class="labal"><s>*</s>?????????</td>
                        <td><span id="genderText"></span></td>
                        <td class="labal"><s>*</s>?????????</td>
                        <td><span id="nation"></span></td>
                        </tr>
                        <tr>
                            <td class="labal"><s>*</s>???????????????</td>
                        <td><span id="maritalStatusText"></span></td>
                        <td class="labal"><s>*</s>???????????????</td>
                        <td><span id="physicalConditionText"></span></td>
                        </tr>
                        <tr>
                            <td class="labal"><s>*</s>???????????????</td>
                        <td><span id="birthday"></span></td>
                        <td class="labal">?????????</td>
                        <td><span id="age"></span></td>
                        </tr>
                        <tr>
                            <td class="labal"><s>*</s>?????????</td>
                        <td><span id="hometown"></span></td>
                        <td class="labal">????????????</td>
                        <td><span id="birthplace"></span></td>
                        </tr>
                    </table>
                </div>


                <div>
                    <table id="t2" class="table">
                        <tr>
                            <td>???????????????</td>
                            <td><span id="positionTypeText"></span></td>
                            <td>?????????????????????</td>
                            <td><span id="positionTypeDate"></span></td>
                            <td>???????????????</td>
                            <td><span id="assignText"></span></td>
                        </tr>
                        <tr>
                            <td>???????????????</td>
                            <td><span id="politicalText"></span></td>
                            <td>???????????????</td>
                            <td><span id="politicalDate"></span></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><strong>???????????????</strong></td>
                            <td><span id="lawPositionText"></span></td>
                            <td><strong>???????????????</strong></td>
                            <td><span id="lawPositionDate"></span></td>
                            <td>?????????????????????</td>
                            <td><span id="lawyerDate"></span></td>
                        </tr>
                        <tr>
                            <td>???????????????</td>
                            <td><span id="isParttimePresidingJudgeText"></span></td>
                            <td>???????????????</td>
                            <td><span id="partyOfficeText"></span></td>
                            <td>?????????????????????</td>
                            <td><span id="partyOfficeDate"></span></td>
                        </tr>
                        <tr>
                            <td><strong>???????????????</strong></td>
                            <td><span id="administrationPositionText"></span></td>
                            <td>???????????????</td>
                            <td><span id="administrationPositionDate"></span></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><strong>?????????</strong></td>
                            <td><span id="rankText"></span></td>
                            <td><strong>???????????????</strong></td>
                            <td><span id="rankDate"></span></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><strong>?????????</strong></td>
                            <td><span id="levelText"></span></td>
                            <td><strong>???????????????</strong></td>
                            <td><span id="levelDate"></span></td>
                            <td>?????????</td>
                            <td><span id="preparationText"></span></td>
                        </tr>
                        <tr>
                            <td>??????????????????</td>
                            <td><span id="servantLevelText"></span></td>
                            <td>??????????????????????????????</td>
                            <td><span id="servantLevelDate"></span></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </table>
                </div>


                <div>
                    <table id="t3" class="table">
                        <tr>
                            <td><strong>?????????</strong></td>
                            <td><span id="educationBackgroundText"></span></td>
                            <td>?????????</td>
                            <td><span id="degreeText"></span></td>
                            <td>?????????????????????</td>
                            <td><span id="degreeDate"></span></td>
                        </tr>
                        <tr>
                            <td><strong>?????????</strong></td>
                            <td><span id="majorText"></span></td>
                            <td><strong>???????????????</strong></td>
                            <td><strong><span id="proCertText"></span></strong></td>
                            <td>?????????????????????</td>
                            <td><span id="proCertDate"></span></td>
                        </tr>
                        <tr>
                            <td><strong>?????????????????????????????????</strong></td>
                            <td><span id="lawyerCertDate"></span></td>
                            <td>?????????????????????????????????</td>
                            <td><span id="lawyerCertText"></span></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </table>
                </div>

                <div>
                    <table id="t4" class="table">
                        <tr>
                            <td><s>*</s><strong>???????????????</strong></td>
                        <td><span id="workDate"></span></td>
                        <td>?????????????????????</td>
                        <td><span id="politicLawWorkDate"></span></td>
                        <td><s>*</s>???????????????</td>
                        <td><span id="enterDate"></span></td>
                        </tr>
                        <tr>
                            <td><s>*</s>???????????????</td>
                        <td><span id="extraSeniority"></span>???</td>
                        <td><s>*</s>???????????????</td>
                        <td><span id="deductionSeniority"></span>???</td>
                        <td>???????????????</td>
                        <td><span id="totalSeniority"></span>???</td>
                        </tr>
                        <tr>
                            <td><s>*</s>?????????????????????????????????</td>
                        <td><span id="beforeCourtWorkYear"></span>???</td>
                        <td>???????????????????????????</td>
                        <td><span id="totalCourtYear"></span>???</td>
                        <td>???????????????</td>
                        <td><span id="additionalDuration"></span>???</td>
                        </tr>
                    </table>
                </div>

                <div>
                    <table id="t5" class="table">
                        <tr>
                            <td><s>*</s>???????????????</td>
                        <td><span id="enterWayText"></span></td>
                        <td><s>*</s>???????????????</td>
                        <td><span id="enterSourceText"></span></td>
                        <td>???????????????</td>
                        <td><span id="verifyDate"></span></td>
                        </tr>
                        <tr>
                            <td>????????????</td>
                            <td><span id="formerPostText"></span></td>
                            <td>????????????</td>
                            <td><span id="formerRankText"></span></td>
                            <td>????????????</td>
                            <td><span id="formerUnit"></span></td>
                        </tr>
                        <tr>
                            <td>???????????????</td>
                            <td><span id="leaveReasonText"></span></td>
                            <td>???????????????</td>
                            <td><span id="leaveDate"></span></td>
                            <td>???????????????</td>
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
                 * ?????????????????????????????????
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
                    panelContainer: '#user-detail-data', //???????????????????????????????????????????????????????????????????????????????????????
                    selectedEvent: 'mouseenter', //?????????click,??????????????????
                    autoRender: true,
                    children: [
                        {title: '????????????', value: '1', selected: true},
                        {title: '????????????', value: '2'},
                        {title: '????????????', value: '3'},
                        {title: '????????????', value: '4'},
                        {title: '????????????', value: '5'}
                    ]
                });

                var dialog = new Overlay.Dialog({
                    title: '????????????',
                    width: 800,
                    height: 410,
                    contentId: "user-detail",
                    //bodyContent: '<p>????????????????????????,??????????????????????????????</p>',
                    buttons: [
                        {
                            text: '??????',
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