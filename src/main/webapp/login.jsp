<%@page import="cn.net.withub.ums.common.UmsConstant" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
    if (request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString()) != null) {
        response.sendRedirect("index.jsp");
        return;
    }
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>重庆法院人事管理系统</title>
    <script src="<%=basePath%>js/jquery/jquery.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/common/codelist.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/crypto-js/rollups/md5.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/base64/base64.js" type="text/javascript"></script>
    <link href="<%=basePath%>js/select2/css/select2.min.css" rel="stylesheet" type="text/css"/>
    <script src="<%=basePath%>js/select2/js/select2.full.min.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/select2/js/i18n/zh-CN.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/rsa/jsbn.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/rsa/jsbn2.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/rsa/prng4.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/rsa/rng.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/rsa/rsa.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/rsa/rsa2.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=basePath%>js/encodeParam.js"></script>
    <!--[if lte IE 6]>
    <script src="<%=basePath%>Js/DD_belatedPNG_0.0.8a.js" type="text/javascript"></script>
    <script type="text/javascript">
        DD_belatedPNG.fix('div, ul, img, li, input , a, .clear');
        /* 将 .png_bg 改成你应用了透明PNG的CSS选择器*/
    </script>
    <![endif]-->
    <style>
        body {
            margin: 0px;
            color: #000;
            background: #1266ae;
            font-size: 12px;
        }

        ul, li, dl, dt, dd {
            list-style: none outside;
            margin: 0;
            padding: 0;
            border: 0;
        }

        input, button, select {
            vertical-align: middle;
        }

        img {
            border: 0px;
            margin: 0 auto;
            vertical-align: middle;
        }

        form {
            margin: 0px;
            border: 0px;
            padding: 0;
        }

        .waiceng {
            padding-top: 100px;
        }

        .bg {
            background: url(images/bg.png) no-repeat top;
            width: 957px;
            height: 460px;
            margin: 0px auto 0px auto;
        }

        a {
            cursor: pointer;
        }

        .gundong {
            width: 740px;
            height: 50px;
            margin: 0 0 0 140px;
        }

        .gundong a {
            color: #9cd845;
        }

        .gundong a:hover {
            color: #FF0;
        }

        .bottom {
            color: #8cc5f6;
            font-size: 12px;
            text-align: center;
            position: fixed;
            bottom: 10px;
            width: 957px;
        }

        .login {
            padding: 210px 0px 35px 580px;
            width: 210px;
            height: 180px;
            color: #FFF;
            filter: Dropshadow(offx=1, offy=0, color=#267bc3) Dropshadow(offx=0, offy=1, color=#267bc3) Dropshadow(offx=-1, offy=0, color=#267bc3) Dropshadow(offx=0, offy=-1, color=#267bc3);
            font-size: 12px;
        }

        .kuang {
            width: 185px;
            height: 20px;
            border: 1px solid #7abef9;
            background: #dae9f3;
        }

        .alert {
            color: red;
            font-style: italic;
            font-size: large;
            text-align: center;
        }

        .tanchu {
            position: fixed;
            z-index: 99;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.8);
        }

        .tanchu_nr {
            width: 800px;
            height: 400px;
            border-radius: 10px;
            border: 3px solid #009aea;
            background: #f1f1f1;
            margin: 100px auto 0 auto;
        }

        .tan_left {
            float: left;
            width: 200px;
            height: 360px;
            overflow-y: auto;
        }

        .tan_left_a {
            padding: 10px 10px;
            font-size: 14px;
            color: #06F;
            display: block;
            text-decoration: none;
            border-bottom: 1px solid #ccc;
            border-right: 1px solid #ccc;
        }

        .tan_left_a:hover {
            color: #f00;
            background: #fff;
        }
        .fjone{
            color: #06F;
            text-decoration: underline;
        }
        .fjone:link{
            color: #06F;
        }
        .fjone:visited{
            color: grey;
        }
        .fjone:hover{
            color:red;
         }
        .fjone:active{
            color: #fff;
        }

        .tan_left_b {
            background: #fff;
        }

        .tan_right {
            float: right;
            background: #fff;
            width: 580px;
            height: 340px;
            padding: 10px;
        }

        .tan_right dt {
            text-align: center;
            font-size: 18px;
            line-height: 30px;
            height: 30px;
            color: #000;
        }

        .tan_right span {
            text-align: center;
            font-size: 14px;
            line-height: 20px;
            color: #666;
            height: 20px;
            display: block;
        }

        .tan_right dd {
            border-top: 1px solid #ccc;
            height: 290px;
            width: 580px;
            overflow-y: auto;
            color: #333;
            line-height: 24px;
            font-size: 14px;
            text-indent: 2em;
        }

        .tan_bottom {
            height: 39px;
            padding: 10px;
            border-top: 1px solid #ccc;
            text-align: center;
        }

        .tan_bottom a {
            background: #d94656;
            text-align: center;
            display: inline-block;
            margin: 0 5px;
            border-radius: 5px;
            font-size: 14px;
            padding: 5px 20px;
            color: #fff;
            text-decoration: none;
        }

        .tan_bottom a:hover {
            color: #FF0;
        }
        .listForTitleDiv{
            position: relative;
            top: -20px;
            left: 50px;
        }
    </style>
    <script>
        var titleInfo = {};
        var contentInfo = {};
        timeInfo = {};
        publisherInfo = {};
        var noticeFjInfo ={};
        var RSADo = {
            "modulus":"",
            "publicExponent":""
        }
        $(function () {

            //加载公钥
            $.getJSON("<%=basePath%>rsa/getRsaModule", {}, (data)=> {
                RSADo.modulus = data.module;
                RSADo.publicExponent = data.key;
            });

            loadCourts2($("#courtStdNo"), null, function () {

                $("#courtStdNo").append("<optgroup id='og0' label='高院'></optgroup>");
                $("#courtStdNo").append("<optgroup id='og1' label='一中院'></optgroup>");
                $("#courtStdNo").append("<optgroup id='og2' label='二中院'></optgroup>");
                $("#courtStdNo").append("<optgroup id='og3' label='三中院'></optgroup>");
                $("#courtStdNo").append("<optgroup id='og4' label='四中院'></optgroup>");
                $("#courtStdNo").append("<optgroup id='og5' label='五中院'></optgroup>");
                $("#courtStdNo").append("<optgroup id='og6' label='铁路法院'></optgroup>");

                var i = -1;
                $("#courtStdNo option").each(function () {
                    if (
                            $(this).html().indexOf("高级") != -1 ||
                            $(this).html().indexOf("中级") != -1 ||
                            $(this).html().indexOf("铁路") != -1) {
                        i++;
                    }
                    $(this).appendTo($("#og" + i));
                });

                loadInfo();
            });

            $("#courtStdNo").select2();
        });
        function passwordEncrypt() {
//                var str = CryptoJS.enc.Utf8.parse($('#password').val());
//                var hash = CryptoJS.MD5(str).toString();
//                var base64 = Base64.encode(hash);

//                $('#epassword').val(base64);
            var password = $('#password').val();
            if(password){
                password = encryption(password);
            }
            var username = $("#username").val();
            console.log(username);
            if(username){
                username = encryption(username);
            }
            $('#epassword').val(password);
            $('#eusername').val(username);
            $('#password').prop('disabled', true);
            return true;
        }


        function encryption(str){
            // 实例化js的RSA对象生成
            var rsa = new RSAKey();
            rsa.setPublic(RSADo.modulus, RSADo.publicExponent);
            var encryptStr = rsa.encrypt(str);
            return encryptStr;
        }



        function saveInfo() {
            if (localStorage) {
                localStorage.lastInfo_courtCode = $("#courtStdNo").val();
                localStorage.lastInfo_username = $("#username").val();
            }
            return true;
        }
        function loadInfo() {
            //取缓存
            var court = null;
            if (localStorage) {
                court = localStorage.lastInfo_courtCode;
                $("#courtStdNo").val(court).change();
                $("#username").val(localStorage.lastInfo_username).change();
                /*var item = localStorage.getItem('noticeInfo_save_time');
                var now = new Date().getTime();
                if (item && (now - item) <  2 * 1000) {
                    //4 * 60 * 60 * 1000
                    titleInfo = localStorage.getItem("titleInfo").split(',');
                    contentInfo = localStorage.getItem("contentInfo").split(',');
                    timeInfo = localStorage.getItem("timeInfo").split(',');
                    publisherInfo = localStorage.getItem("publisherInfo").split(',');
                    noticeFjInfo = localStorage.getItem("noticeFjInfo").split(',');
                    initNotice();
                    return;
                }*/
            }
            //读取通知信息
            var url = "<%=path%>/notice/showInfoForCourt";
            var param = {
                court: court
            };
            $.post(url, param, function (datas) {

                if (datas) {
                    titleInfo = datas.title;
                    contentInfo = datas.content;
                    timeInfo = datas.time;
                    publisherInfo = datas.publisher;
                    noticeFjInfo = datas.noticeFjList;

                    //放入本地存储
                    if (localStorage) {
                        var nowTime = new Date();
                        localStorage.setItem("noticeInfo_save_time", nowTime.getTime());
                        localStorage.setItem("titleInfo", titleInfo);
                        localStorage.setItem("contentInfo", contentInfo);
                        localStorage.setItem("timeInfo", timeInfo);
                        localStorage.setItem("publisherInfo", publisherInfo);
                        localStorage.setItem("noticeFjInfo",noticeFjInfo);
                    }
                    initNotice();
                }

            });

        }

        function initNotice() {
            var htmlInfo = "";
            var htmlInfo_ = "";
            for (var i in titleInfo) {
                var time = "<";
                var month = new Date(Number(timeInfo[i])).getMonth()+1;
                var day = new Date(Number(timeInfo[i])).getDate();
                time += month + "-" + day+">";
                var shortTitle ;
                if(titleInfo[i].length>20)
                    shortTitle = titleInfo[i].substring(0,20) + "....";
                else
                    shortTitle = titleInfo[i];
                if(i<2 && titleInfo[i] && titleInfo[i] != null)
                    htmlInfo += " <a  onclick='showDialog(" + i + ")'>" + time + shortTitle + "</a>&nbsp;&nbsp;&nbsp;&nbsp;";
                if(titleInfo[i] && titleInfo[i] != null)
                    htmlInfo_ += " <a  onclick='showInfo(" + i + ")' class='tan_left_a'>"+ time + titleInfo[i] + "</a>";
            }
            $("#listForTitle").html(htmlInfo);
            $(".tan_left").html(htmlInfo_);

        }

        function showDialog(obj) {
            $(".tanchu").show();
            showInfo(obj);
        }

        function showInfo(obj) {
            $(".tan_left_b").removeClass("tan_left_b");
            $(".tan_right_title").html(titleInfo[obj]);
            $(".tan_right_content").html(contentInfo[obj]);
            showFjList(obj)
            var InfoDate = new Date(Number(timeInfo[obj]));
            var str = publisherInfo[obj] + "&nbsp;&nbsp;&nbsp;&nbsp;" + InfoDate.getFullYear() + "-" + (InfoDate.getMonth() + 1) + "-" + InfoDate.getDate();
            $(".tan_right_span").html(str);
            $(".tan_left a").eq(obj).addClass("tan_left_b");
        }
        function showFjList(index)
        {
            $(".fjone").remove();
            if(noticeFjInfo[index].length>0)
            {
                var noticeFjList = noticeFjInfo[index];
                var html = '<br/><br/><br/>&nbsp;&nbsp;附件：<br/>';
                if(noticeFjList.length>0)
                {
                    for(var i in noticeFjList)
                    {
                        if(noticeFjList[i] != '')
                        {
                            var fj = noticeFjList[i].split(',');
                            html += "&nbsp;&nbsp;<a fjId="+fj[1]+" onclick='downLoadFj(this)' class='fjone'>"+fj[2]+"</a><br/>"
                        }
                    }
                    $(".tan_right_content").append(html);
                }
            }

        }
        function changeShowInfo(obj) {
            var _b = $(".tan_left_b");
            var left = $(".tan_left");
            var len = left.find("a").length;
            var i = left.find("a").index(_b) + obj;
            if (i < 0) {
                return;
            }
            if (i >= len) {
                return;
            }
            showInfo(i);
        }
        function downLoadFj(_this)
        {
            var fjId = $(_this).attr("fjId");
            var turnForm = document.createElement("form");
            document.body.appendChild(turnForm);
            turnForm.method = 'post';
            turnForm.action = '<%=path%>/noticeFileUpload/downLoadFj?fjId='+fjId;
            turnForm.submit();
        }
    </script>
</head>

<body>
<!--弹出层开始-->
<div class="tanchu" style="display: none;">
    <div class="tanchu_nr">
        <ul class="tan_left">
        </ul>
        <dl class="tan_right">
            <dt class="tan_right_title"></dt>
            <span class="tan_right_span"></span>
            <dd class="tan_right_content"></dd>
        </dl>
        <div style="clear:both;"></div>
        <div class="tan_bottom"><a onclick="changeShowInfo(-1)">上翻</a><a onclick="$('.tanchu').hide()">关闭</a><a
                onclick="changeShowInfo(1)">下翻</a></div>
    </div>
</div>
<!--弹出层结束-->
<div class="waiceng">
    <div class="bg">
        <div class="login">
            <form action="<%=basePath%>login3" method="post" id="login_form" onsubmit="return saveInfo()">
                <table width="100%" border="0" cellspacing="0" cellpadding="5">
                    <tr>
                        <td width="50" align="right">法&nbsp;&nbsp;院：</td>
                        <td>
                            <select  name="courtStdNo" class="kuang" id="courtStdNo"></select>
                        </td>
                    </tr>
                    <tr>
                        <td width="50" align="right">帐&nbsp;&nbsp;号：</td>
                        <td>
                            <input  type="text" class="kuang" id="username">
                            <input name="username" type="hidden" id="eusername">
                        </td>
                    </tr>
                    <tr>
                        <td align="right">密&nbsp;&nbsp;码：</td>
                        <td>
                            <input type="password" class="kuang" id="password">
                            <input name="password" type="hidden" id="epassword">
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>
                            <input type="image" src="<%=basePath%>images/btn1.png" name="button" id="button" value="登录"
                                   onclick="return passwordEncrypt()">
                            <input style="padding-left:8px;" type="image" src="<%=basePath%>images/btn2.png"
                                   name="button" value="重置"
                                   onclick="$('#password').val('');$('#epassword').val('');$('#username').val('');
                                               return false;">

                        </td>
                    </tr>
                </table>
            </form>

        </div>
        <div class="gundong"><img src="images/laba.png"/>
            <%--<marquee onMouseOver="this.stop()" onMouseOut="this.start()" scrollamount="3" scrolldelay="7"
                     id="listForTitle"
                     direction="left" width="680">
            </marquee>--%>
            <div id="listForTitle" class="listForTitleDiv"></div>
        </div>
        <div class="bottom">技术支持：上海交大慧谷通用技术有限公司 <a style="margin-left: 10px;text-decoration: none;color: #8CC5F6;"
                                                   href="soft/sogou_explorer_6.0_1124.zip">浏览器下载</a></div>
    </div>
    <%--<div class="bottom"><br>技术支持：上海交大慧谷通用技术有限公司 <a style="margin-left: 10px;text-decoration: none;color: #8CC5F6;" href="soft/sogou_explorer_6.0_1124.zip">浏览器下载</a> </div>--%>
</div>
<!--[if IE]>
<script>

    $(function () {
        var timer = setTimeout(function () {
            alert("当前浏览器为IE浏览器内核会影响系统功能的使用\n请用右下角的链接下载搜狗浏览器使用");
//                    BUI.Message.Alert(,"warning");
        }, 1000);

    })

</script>
<![endif]-->
<script>


    var msg = '<s:property value="msg"></s:property>';
    if (msg !== '') {
        alert(msg);
    }

</script>
</body>
</html>
