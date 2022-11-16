<%--
    Document   : timeline
    Created on : 2015-1-23, 10:07:46
    Author     : Diluka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>用户足迹</title>
        <link href="<%=basePath%>js/timeline/css/history.css" rel="stylesheet" type="text/css"/>
        <script src="<%=basePath%>js/timeline/js/jquery.js" type="text/javascript"></script>
        <script src="<%=basePath%>js/timeline/js/jquery.easing.js" type="text/javascript"></script>
        <script src="<%=basePath%>js/timeline/js/jquery.mousewheel.js" type="text/javascript"></script>
        <!--<script src="<%=basePath%>js/timeline/js/history.js" type="text/javascript"></script>-->
        <script>

            var vBColorArray = {
                '职级': 'red',
                '行政职务': 'green',
                '法律职务': 'gray'
            };
            var vBImgArray = {
                '职级': '<%=basePath%>/js/timeline/js/b1.jpg',
                '行政职务': '<%=basePath%>/js/timeline/js/b2.jpg',
                '法律职务': '<%=basePath%>/js/timeline/js/b3.jpg',
                '考核': '<%=basePath%>/js/timeline/js/b4.jpg',
                '出国': '<%=basePath%>/js/timeline/js/b5.jpg',
                '培训': '<%=basePath%>/js/timeline/js/b6.jpg',
                '学位': '<%=basePath%>/js/timeline/js/b7.jpg',
                '进院时间': '<%=basePath%>/js/timeline/js/b0.jpg'
            };


            $(function () {

                $.getJSON("<%=basePath%>userinfo/footprint/footprints", {userId: "<s:property value="userId"></s:property>"}, function (data) {

                    if (data) {

                        $("#content>ul.list").empty();

                        //定位到最早的一年
                        var thisyear = new Date(data[0].timePoint.replace("-", "/")).getFullYear().toString();
                        $(".timeblock").attr("thisyear", thisyear);

                        $(".timeblock .numf").css({"background-position": "0px -" + thisyear[0] * 24 + "px"});
                        $(".timeblock .nums").css({"background-position": "0px -" + thisyear[1] * 24 + "px"});
                        $(".timeblock .numt").css({"background-position": "0px -" + thisyear[2] * 24 + "px"});
                        $(".timeblock .numfo").css({"background-position": "0px -" + thisyear[3] * 24 + "px"});

                        for (var i = 0; i < data.length; i++) {
                            var fp = data[i];
                            var tp = new Date(fp.timePoint.replace("-", "/"));

                            $("#back_div").append($("<div>")
                                    .append($("<a>", {
                                        name: "i" + i
                                    }))
                                    .append($("<img>", {
                                        src: vBImgArray[fp.type],
                                        width: "100%",
                                        height: "100%"
                                    })));

                            $("#content>ul.list").append(
                                    $("<li>").append(
                                    $("<div class='liwrap'>")
                                    .append($("<div class='lileft'>")
                                            .append($("<div class='date'>")
                                                    .append($("<span class='year'>").text(tp.getFullYear()))
                                                    .append($("<span class='md'>").text((tp.getMonth() + 1) + "月" + tp.getDate() + "日"))
                                                    ))
                                    .append("<div class='point'><b></b></div>")
                                    .append($("<div class='liright'>")
                                            .append($("<div class='histt'>").append($('<a name="i' + i + '" BColor="' + vBColorArray[fp.type] + '" BImg="' + vBImgArray[fp.type] + '">').text(fp.type).click(function () {
                                                $("#back_div").animate({scrollTop: $("#back_div a[name='" + $(this).attr("name") + "']").offset().top - $("#back_div a:eq(0)").offset().top}, 500);
                                            })))
                                            .append($("<div class='hisct'>").text(fp.content))
                                            )
                                    ));
                        }

                        $.getScript("<%=basePath%>/js/timeline/js/history.js");
                    } else {
                        $("#footprint-content").text("没有数据！");
                    }
                })
            });
        </script>
    </head>
    <body >
        <div id="out" style="position: fixed;top: 50px;right: 50px;background-color: black;color: yellow;z-index: 9999"></div>
        <div id="back_div" style="position:absolute; width:100%; height:100%;z-index: 1;overflow: scroll">
            <!--<img id="back_img" src="" width="100%" height="100%"/>-->
        </div>

        <div id="footprint-content" style="position:relative; border: solid 0px red;z-index: 999;float: left;">
            <!--右侧上下箭头-->
            <div id="arrow">
                <ul>
                    <li class="arrowup"></li>
                    <li class="arrowdown"></li>
                </ul>
            </div>

            <div id="history" style="border: solid 0px red;">

                <div class="title">
                    <h1 style="color: white;">我的足迹</h1>
                    <div id="circle" style="border-color: window;filter:alpha(opacity=80); -moz-opacity:0.8; -khtml-opacity: 0.8; opacity: 0.8; color:#fff; ">
                        <div class="cmsk"></div>
                        <div class="circlecontent">
                            <div thisyear="" class="timeblock" >
                                <span class="numf"></span>
                                <span class="nums"></span>
                                <span class="numt"></span>
                                <span class="numfo"></span>
                                <div class="clear"></div>
                            </div>
                            <div class="timeyear">YEAR</div>
                        </div>
                        <a href="#" class="clock"></a>
                    </div>
                </div>

                <div id="content" style="border: solid 0px red;">
                    <ul class="list" style="color: white;">
                    </ul>
                </div>
            </div>
        </div>




    </body>
</html>
