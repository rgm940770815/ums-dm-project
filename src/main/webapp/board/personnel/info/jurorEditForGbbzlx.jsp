<%--jurorEditForGbbzlx：改变编制类型为人民陪审员员编辑的页面--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
//    调用iframe传来的userid
    String user_id = request.getParameter("user_id");
    String changeUUID = request.getParameter("changeUUID");
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>人民陪审员编辑页面</title>
    <jsp:include page="/basic_import.jsp"></jsp:include>
    <jsp:include page="/fragment/tip.jsp"></jsp:include>
    <script src="<%=basePath%>js/common/idchecker.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/vue.min.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/common/codelist.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/common/datetools.js" type="text/javascript"></script>
    <style type="text/css">
        /**内容超出 出现滚动条 **/
        .bui-stdmod-body {
            overflow-x: hidden;
            overflow-y: auto;
        }

        .nav-tabs li s {
            color: red;
            padding-right: 5px;
            text-decoration: none;
        }

        .show_icon {
            display: none;
            font-size: 20px;
            font-weight: bold;
            font-family: "Arial";
            -webkit-border-radius: 2px;
            -moz-border-radius: 2px;
            border-radius: 2px;
            text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
            border: 1px solid transparent;
            cursor: inherit;
            margin: 2px 2px 0px 10px;
        }

        .show_info {
            display: none;
            color: red;
        }

        .show_icon_ok {
            display: none;
            font-size: 20px;
            font-weight: bold;
            font-family: "Arial";
            -webkit-border-radius: 2px;
            -moz-border-radius: 2px;
            border-radius: 2px;
            text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
            border: 1px solid transparent;
            cursor: inherit;
            margin: 2px 2px 0px 10px;
        }

        .show_info_ok {
            display: none;
            color: red;
        }

        .show_info_edit {
            display: none;
            color: red;
        }

        input.calendar {
            width: 140px;
        }

        .button-own {
            width: 125px;
        }

        .grid-valid {
            color: #3366cc;
            cursor: pointer;
            margin-right: 5px;
        }

        .grid-valid:hover {
            color: #ff6600;
        }


        .overlay_show {
            display: none;
            z-index: 2040;
            position: fixed;
            left: 0;
            right: 0;
            top: 0;
            bottom: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(255, 255, 255, 0.2) !important;
            background-color: #FFF;
            filter: Alpha(opacity=20);
        }

        .overlay_show .overlay_show_inner {
            position: absolute;
            height: 420px;
            width: 600px;
            margin-left: -300px;
            margin-top: -210px;
            top: 50vh;
            left: 50vw;
            background-color: #fff;
            border: 1px solid rgba(0, 0, 0, .2);
            -webkit-box-shadow: 0 3px 9px rgba(0, 0, 0, 0.5);
            -moz-box-shadow: 0 3px 9px rgba(0, 0, 0, 0.5);
            box-shadow: 0 3px 9px rgba(0, 0, 0, 0.5);
        }

        .bui-message {
            z-index: 2000;
        }

        .overlay_title {
            padding: 15px 10px;
            border-bottom: 1px solid #d3d3d3;
            font-size: 15px;
        }

        .overlay_show .overlay_title ul:after, .overlay_show .overlay_bottom ul:after {
            display: block;
            clear: both;
            content: "";
            visibility: hidden;
            height: 0;
        }

        .overlay_show .overlay_title ul li {
            list-style: none;
            float: left;
        }

        .overlay_show .overlay_bottom ul li {
            list-style: none;
            float: right;
        }

        .overlay_title ul li span.checked_span {
            border-bottom: 2px solid #00F;
            color: #000;
        }

        .overlay_title ul li:after {
            content: '|';
            font-size: 16px;
        }

        .overlay_title ul li span {
            margin: 0 10px;
            cursor: pointer;
            font-weight: bold;
            color: #416DA3;
        }

        .overlay_show .overlay_content {
            height: 300px;
            overflow: auto;
            border-bottom: 1px solid #d3d3d3;
        }

        .overlay_show .overlay_content .overlay_content_tree {
            text-align: left;
        }

        .overlay_show .overlay_bottom {
            padding: 15px 10px;
        }

        .overlay_show .overlay_bottom .button {
            margin: 0 10px;
        }

        .search_button_div {
            padding: 10px 20px;
            border-bottom: 1px solid #d3d3d3;
        }

        #search_element {
            font-size: 15px;
        }

        #search_element td, #search_element th {
            padding: 5px;
        }

        .userSel-1 {
            width: 120px;
        }

    </style>
</head>

<body>

<%--<ul class="nav-tabs">--%>
<%--    <li><a style="border: 1px solid white;color:inherit;">人民陪审员 - 编辑</a></li>--%>
<%--    <li class="active"><a href="#basic_info" onclick="changeMarkers(this)" class="initClass"><s>*</s>基本信息</a></li>--%>
<%--    <li><a href="#juror_degree" onclick="changeMarkers(this)"><s>*</s>学历学位</a></li>--%>
<%--    <li><a href="#position_info" onclick="changeMarkers(this)"><s>*</s>单位及职务</a></li>--%>
<%--    <li><a href="#juror_info" onclick="changeMarkers(this)"><s>*</s>陪审员信息</a></li>--%>
<%--    <li><a href="#education_info" onclick="changeMarkers(this)">其他</a></li>--%>
<%--    <li><a href="#photo_info" onclick="changeMarkers(this)">头像上传</a></li>--%>
<%--</ul>--%>

<%--新增页面弹出层--%>
<div id="content">
    <form id="userinfo_form" class="form-horizontal" action="<%=basePath%>juror/save" method="post">
        <input type="hidden" id="userType" name="userInfo.userType">
        <input type="hidden" id="changeUUID" name="changeUUID">
        <input type="hidden" name="id" id="new_id">
        <input type="hidden" name="userInfo.id" id="id">
        <%--基本信息--%>
        <div id="basic-info">
            <a name="basic_info"></a>
            <h3>人民陪审员 -基本信息</h3>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>姓名：</label>

                    <div class="controls">
                        <input id="fullname" name="userInfo.fullname" type="text" placeholder="请输入用户姓名" class="control-text" data-rules="{required:true}" onchange="generateUserName_2(this)"/>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">曾用名：</label>

                    <div class="controls">
                        <input id="formerName" name="userInfo.formerName" type="text" placeholder="请输入用户曾用名"
                               class="control-text">
                    </div>
                </div>
                <div class="control-group span18">
                    <a id="show_e" href="#basic_info"></a>
                    <label class="control-label"><s>*</s>用户名：</label>
                    <div class="controls">
                        <input id="username" name="userInfo.username" type="text" placeholder="默认：姓名+身份证后六位" class="control-text" onchange="cleanError()" readonly/>
                        <span class="show_icon">!</span>
                        <span class="show_info">用户名已存在</span>
                        <span class="show_icon_ok">!</span>
                        <span class="show_info_ok">用户名重复，已更改为：“<span id="suggestUsername"></span>”，确认请点击保存！</span>
                        <span class="show_info_edit">编辑时用户名不可更改！</span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>性别：</label>

                    <div class="controls">
                        <select id="gender" typeId="3" class="code select22" data-rules="{required:true}" name="userInfo.gender"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>民族：</label>
                    <div class="controls">
                        <select id="nation" name="userInfo.nation" typeId="5" class="code select22" data-rules="{required:true}"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>民族(最高院)：</label>
                    <div class="controls">
                        <select id="nationReport" name="userInfo.nationReport" typeId="1005" class="code select22" data-rules="{required:true}"></select>
                    </div>
                </div>

            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>政治面貌：</label>
                    <div class="controls">
                        <select id="political" typeId="13" class="code select22" name="userInfo.political" data-rules="{required:true}"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>政治面貌(最高院)：</label>

                    <div class="controls">
                        <select id="politicalReport" typeId="1013" class="code select22" data-rules="{required:true}" name="userInfo.politicalReport"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>婚姻状况：</label>
                    <div class="controls">
                        <select id="maritalStatus" typeId="6" class="code select22" data-rules="{required:true}" name="userInfo.maritalStatus"></select>
                    </div>
                </div>

            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>身份证号：</label>
                    <div class="controls">
                        <input id="idcard" name="userInfo.idcard" type="text" class="span-width spancontrol-text" placeholder="请输入用户身份证号码" data-rules="{required:true,idcheck:null}" onchange="generateUserName(this)"/>
                    </div>
                </div>

                <div class="control-group span8">
                    <label class="control-label">出生日期：</label>

                    <div class="controls">
                        <label class="control-label" style="text-align: left" id="birthday_show"></label>
                        <input id="birthday" name="userInfo.birthday" type="hidden" readonly="readonly"
                               value="">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">年龄：</label>

                    <div class="controls">
                        <label class="control-label" style="text-align: left" id="age"></label>
                    </div>
                </div>
            </div>
            <div class="row">

                <div class="control-group span8">
                    <label class="control-label"><s>*</s>健康状况：</label>

                    <div class="controls">
                        <select id="physicalCondition" typeId="10" class="code select22" data-rules="{required:true}" name="userInfo.physicalCondition"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>法院：</label>

                    <div class="controls">
                        <select id="courtNo" typeId="1" class="Courtcode select22" name="userInfo.courtNo" data-rules="{required:true}"></select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>手机号码：</label>
                    <div class="controls">
                        <input id="phoneNumber" name="userInfo.phoneNumber" type="text" class="control-text" placeholder="请输入用户手机号码" data-rules="{required:true,phoneNumber:11}">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">座机号码：</label>
                    <div class="controls">
                        <input id="machineNumber" name="userInfo.machineNumber" type="text" class="control-text" placeholder="请输入用户座机号码" data-messages="{regexp:'不是有效的电话号码'}" data-rules="{number:true}">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>常住地：</label>

                    <div class="controls">
                        <input id="localAddress" name="userInfo.localAddress" type="text" class="control-text" placeholder="请输入用户常住地" data-rules="{required:true}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>籍贯：</label>

                    <div class="controls">
                        <input id="hometown" name="userInfo.hometown" type="text" placeholder="请输入用户籍贯" class="control-text" data-rules="{required:true}">
                    </div>
                </div>

                <div class="control-group span8">
                    <label class="control-label">排序：</label>

                    <div class="controls">
                        <input id="sortNo" name="userInfo.sortNo" type="text" placeholder="请输入排序号" class="control-text">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span24">
                    <label class="control-label"><s>*</s>家庭住址：</label>
                    <div class="controls">
                        <input id="postalAddress" name="userInfo.postalAddress" type="text" class="span20 span-width spancontrol-text" placeholder="请输入用户家庭住址" data-rules="{required:true}">
                    </div>
                </div>
            </div>
        </div>
        <%--学历学位--%>
        <div id="juror-degree">
            <a name="juror_degree"></a>
            <hr/>
            <h3>学历学位</h3>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>学历学位：</label>
                    <div class="controls">
                        <select id="cPsXlxw" name="userInfo.cPsXlxw" typeId="10001" class="code select22" data-rules="{required:true}"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>毕业院校：</label>
                    <div class="controls">
                        <input id="sPsByyxjzy" name="userInfo.sPsByyxjzy" type="text" class="span-width spancontrol-text"
                               placeholder="填写毕业院校" data-rules="{required:true}">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>专业：</label>
                    <div class="controls">
                        <input id="sPsZy" name="userInfo.sPsZy" type="text" class="span-width spancontrol-text" placeholder="填写专业" data-rules="{required:true}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>学历：</label>
                    <div class="controls">
                        <select id="educationBackground" data-rules="{required:true}" typeId="11" class="code select22" name="userInfo.educationBackground"></select>
                    </div>
                </div>
            </div>
        </div>
        <%--单位及职务--%>
        <div id="position-info">
            <a name="position_info"></a>
            <hr/>
            <h3>单位及职务</h3>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>单位：</label>
                    <div class="controls">
                        <input id="company" name="jurorInfo.company" type="text" class="control-text" placeholder="单位" data-rules="{required:true}">
                    </div>
                </div>
                <div class="control-group span12">
                    <label class="control-label"><s>*</s>单位地址：</label>
                    <div class="controls">
                        <input id="sPsDwdz" name="userInfo.sPsDwdz" type="text" class="span8 span-width spancontrol-text" placeholder="填写单位地址" data-rules="{required:true}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>职业：</label>
                    <div class="controls">
                        <select id="jurorWork" name="jurorInfo.jurorWork" class="code select22" data-rules="{required:true}" placeholder="职业">
                            <option value="">请选择</option>
                            <option value="基层干部">基层干部</option>
                            <option value="人民团体成员">人民团体成员</option>
                            <option value="事业单位职员">事业单位职员</option>
                            <option value="专业技术人员">专业技术人员</option>
                            <option value="工商业人员">工商业人员</option>
                            <option value="社区工作者">社区工作者</option>
                            <option value="普通居民">普通居民</option>
                            <option value="农民">农民</option>
                            <option value="进城务工人员">进城务工人员</option>
                            <option value="其他人员">其他人员</option>
                        </select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>单位职务：</label>
                    <div class="controls">
                        <input id="sPsZw" name="userInfo.sPsZw" type="text" class="control-text" placeholder="单位职务" data-rules="{required:true}">
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>单位职级：</label>

                    <div class="controls">
                        <input id="sPsZj" name="userInfo.sPsZj" type="text" class="control-text" placeholder="单位职级" data-rules="{required:true}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">专业技术职务：</label>
                    <div class="controls">
                        <input id="sPsZyjszw" name="userInfo.sPsZyjszw" type="text" class="control-text" placeholder="专业技术职务">
                    </div>
                </div>
            </div>
        </div>
        <%--陪审员信息--%>
        <div id="juror-info">
            <a name="juror_info"></a>
            <hr/>
            <h3>陪审员信息</h3>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">类型：</label>
                    <div class="controls">
                        <select id="cPsLx" name="userInfo.cPsLx" typeId="10002" class="code select22"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">专业类别：</label>
                    <div class="controls">
                        <select id="cPsZylb" name="userInfo.cPsZylb" typeId="10003" class="code select22"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">任命单位：</label>

                    <div class="controls">
                        <input id="sPsRmdw" name="userInfo.sPsRmdw" type="text" class="control-text"
                               placeholder="任命单位">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group12 span8">
                    <label class="control-label"><S>*</S>任命日期：</label>
                    <div class="controls">
                        <input id="beginTime" name="userInfo.beginTime" type="text" class="calendar " placeholder="任命日期" data-rules="{required:true,datecheck1:null}">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">陪审员编号：</label>

                    <div class="controls">
                        <input id="sPsPsybh" name="userInfo.sPsPsybh" type="text" class="control-text"
                               placeholder="陪审员编号">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">uk编号：</label>

                    <div class="controls">
                        <input id="ukbm" name="userInfo.ukbm" type="text" class="control-text"
                               placeholder="uk编号">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group12 span8">
                    <label class="control-label">免职日期：</label>
                    <div class="controls">
                        <input id="endTime" name="userInfo.endTime" type="text" class="calendar " placeholder="免职日期">
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">免职原因：</label>
                    <div class="controls">
                        <select id="cPsMzyy" name="userInfo.cPsMzyy" typeId="10004" class="code select22"></select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>地区分布：</label>
                    <div class="controls">
                        <select id="regional" name="jurorInfo.regional" typeId="109" class="code select22" data-rules="{required:true}"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>工作区域：</label>
                    <div class="controls">
                        <select class="code" typeId="112" style="display: none;"></select>
                        <input id="gzqy" name="jurorInfo.gzqy" type="text" class="control-text" autocomplete="off" placeholder="工作区域">
                        <input id="workArea" name="jurorInfo.workArea" type="text" style="display: none" data-rules="{required:true}">
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>是否新增：</label>
                    <div class="controls">
                        <select id="isNew" name="jurorInfo.isNew" typeId="108" class="code select22" data-rules="{required:true}"></select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>届数：</label>

                    <div class="controls">
                        <input id="numberOfTimes" name="jurorInfo.numberOfTimes" type="text" class="control-text" data-rules="{required:true,number:true}"
                               placeholder="届数">
                    </div>

                </div>

                <div class="control-group12 span8">
                    <label class="control-label">年度参审次数：</label>
                    <div class="controls">
                        <input id="yearAddCount" name="jurorInfo.yearAddCount" type="text" class="control-text" data-rules="{number:true}" placeholder="年度参审次数">
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>参与案件类型：</label>
                    <div class="controls">
                        <select class="code" typeId="110" style="display: none;"></select>
                        <input id="cyajlx" name="jurorInfo.cyajlx" type="text" class="control-text" data-rules="{required:true}" autocomplete="off" placeholder="参与案件类型">
                        <input id="typeOfCase" name="jurorInfo.typeOfCase" type="text" style="display: none">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group12 span8">
                    <label class="control-label">年度陪审费用：</label>
                    <div class="controls">
                        <input id="yearCost" name="jurorInfo.yearCost" type="text" class="control-text" data-rules="{number:true}"
                               placeholder="年度陪审费用">
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>陪审状态：</label>
                    <div class="controls">
                        <select id="memberState" name="jurorInfo.memberState" typeId="111" class="code select22" data-rules="{required:true}"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">退出方式：</label>
                    <div class="controls">
                        <select id="exitMode" name="jurorInfo.exitMode" class="select22"></select>
                    </div>
                </div>
            </div>
        </div>
        <%--其他--%>
        <div id="education-info">
            <a name="education_info"></a>
            <hr/>
            <h3>其他</h3>
            <div class="row">
                <div class="control-group span24">
                    <label class="control-label">专业特长：</label>

                    <div class="controls">
                        <input id="zytc" name="userInfo.zytc" type="text" class="span20 span-width spancontrol-text"
                               placeholder="专业特长">
                    </div>
                </div>
            </div>
        </div>
        <%--额外信息--%>
        <input id="password" name="userInfo.password" type="hidden" value="">
        <input id="isValid" name="userInfo.isValid" type="hidden" value="">
        <input id="is_info_complete" name="userInfo.is_info_complete" type="hidden" value="">
        <input id="salt" name="userInfo.salt" type="hidden" value="">
        <input id="userNo" name="userInfo.userNo" type="hidden" value="">
        <input id="orderNo" name="userInfo.orderNo" type="hidden" value="">
        <%--上传图片--%>
        <a name="photo_info"></a>
        <hr/>
        <h3>头像上传</h3>
        <div class="row" style="height: 170px">
            <div class="control-group span8">
                <div id="photo"></div>
            </div>
        </div>
        <%--        提交按钮--%>
        <div style="margin-left: 40%" hidden>
            <button id="saveButton" type="button" class="button button-primary" style="visibility: visible">保存</button>
        </div>
    </form>
</div>

<%--工作区域div--%>
<div class="overlay_show">
    <div class="overlay_show_inner">
        <div class="overlay_title">
            <ul>
                <li>
                    <span class="checked_span" targetClass="overlay_content_tree" onclick="changeTarget(this)">代码选择</span>
                </li>
                <li>
                    <span targetClass="overlay_content_search" onclick="changeTarget(this)">关键字检索</span>
                </li>
            </ul>
        </div>
        <div class="overlay_content">
            <div class="overlay_content_tree">

            </div>

            <div class="overlay_content_search hide">
                <div class="search_button_div">
                    关键字 :&nbsp;&nbsp;&nbsp;
                    <input id="search_place" type="text" class="control-text" placeholder="关键字">
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="button button-primary" id="search_button" onclick="queryPalces()">查询</button>
                </div>
                <div class="search_show_div">

                    <table class="first" style="width:90%;margin:10px auto;" border="1px solid black" id="search_element">
                        <thead v-show="isShow">
                        <tr>
                            <th style="width: 70px;">选择</th>
                            <th>从属机构</th>
                            <th>名称</th>

                        </tr>
                        </thead>
                        <tbody v-show="isShow">
                        <tr v-for="item in places">
                            <td onclick="checkItem(this)" style="width: 70px;cursor: pointer;text-align: center" class="bui-tree-td" :data-id="item.id" :data-name="item.codeName" :data-fullname="item.fullName">
                                <div class="bui-tree-list" style="border: none">
                                    <span class="x-tree-icon x-tree-icon-checkbox"></span>
                                </div>

                            </td>
                            <td v-for="(value, key) in item" v-if="handleShow(key)">
                                {{ value }}
                            </td>

                        </tr>
                        <tr></tr>
                        </tbody>
                    </table>


                </div>
            </div>
        </div>
        <div class="overlay_bottom">
            <ul>
                <li>
                    <button class="button button-primary" onclick="saveSearch()">确定</button>
                </li>
                <li>
                    <button class="button button-primary" onclick="clearSearch()">清除</button>
                </li>
                <li>
                    <button class="button button-primary" onclick="quitSearch()">退出</button>
                </li>
            </ul>
        </div>
    </div>
</div>

</body>


<script type="text/javascript">

    var changeUUID = "<%=changeUUID%>";
    // 需要编辑的人员id
    var user_id = "<%=user_id%>";
    // 用户信息
    var userinfo;
    // 超级权限
    var CourtNo = '';
    var auth = false;
    var firstLine = '<option value="">请选择</option>';
    // 初始化选择树
    var picker;
    // 图片编辑
    var photoEdit = false;
    // 工作区域的全局变量
    var vm;
    // 是id为fullname的值
    var fullname = $("#basic-info #fullname").val();
    // 工作区域
    var tree;
    // 当前页面只是用来编辑的
    var isEdit = true;
    var myForm;

    $(function () {
        console.log("123");
        // 加载各个select框
        $("select.code").each(function () {
            var typeId = $(this).attr("typeId");
            if (typeId == undefined) {

            } else {
                //陪审员参与案件类型
                if (typeId == 110) {
                    $.getJSON("/ums/code/codeListByType", {typeId: typeId}, function (data) {
                        initMultiSelect(data);
                    });
                    return;
                } else if (typeId == 112) {
                    // 工作区域
                    loadArea();
                    return;
                } else {
                    loadCodeList(this, firstLine);
                    // 渲染成select2框
                    if ($(this).hasClass("select22")) {
                        $(this).select2();
                    }
                }
            }
        });
        // 额外渲染jurorWork
        $("#jurorWork").select2();
        // 先加载法院的select下拉框
        $.getJSON("/ums/code/codeListByType", {typeId: 1}, function (data) {
            for (var i = 0; i < data.data.length; i++) {
                $("select.Courtcode").append($("<option>").attr({"value": data.data[i].id}).text(data.data[i].codeName));
                $("select.Courtcode").select2();
            }
            if (data.auth) {
                CourtNo = data.value;
                auth = true;
            } else {
                CourtNo = data.value;
            }
        });
        // 先获取用户信息
        $.ajax({
            url: "<%=basePath%>userinfo/one",
            data: {id: user_id},
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                userinfo = data;
            }
        });

    });

    BUI.use(['common/search', 'bui/tree', 'bui/data', 'bui/calendar', 'bui/overlay', 'bui/form', 'bui/uploader', 'bui/mask', 'bui/grid'], function (Search, Tree, Data, Calendar, Overlay, Form, Uploader, Mask, Grid) {

        // 渲染form表单
        var form = new Form.HForm({
            srcNode: '#userinfo_form',
            submitType: 'ajax',
            autoRender: true,
            callback: function (data) {
                if (data.success) {
                    BUI.Message.Alert("保存成功", function () {
                        // 关闭父页面的dialog
                        window.parent.dialog_gbbzlx.close();
                        window.parent.V.store.load();
                    }, "warning");
                } else {
                    BUI.Message.Alert(data.msg, null, "warning");
                }
                if (data.success && photoEdit) {
                    $.getJSON("<%=basePath%>photo/save", {userId: $("#userinfo_form #id").val() || $("#userinfo_form #new_id").val(), _: new Date().getTime()}, function () {
                    });
                } else if (data.result === -1) {
                    BUI.Message.Alert("非法操作，当前用户无此权限！", null, "warning");
                }
            }
        });

        myForm = form;
        //--------------图片上传功能Start-----------------
        /**
         * 返回数据的格式
         *
         *  默认是 {url : 'url'},否则认为上传失败
         *  可以通过isSuccess 更改判定成功失败的结构
         */
        var uploader = new Uploader.Uploader({
            //指定使用主题
            theme: 'imageView',
            render: '#photo',
            url: '<%=basePath%>photo/upload',
            name: "photo",
            types: ['iframe'],
            queue: {
                resultTpl: {
                    'success': '<div class="success"><img id="qpic" src="{url}" title="{name}" style="height: 100%"/></div>',
                    'error': '<div class="error"><span class="uploader-error">{msg}</span></div>'
                }
            },
            rules: {
                //文的类型
                ext: ['.png,.jpg,.jpeg,.gif,.bmp', '文件类型只能为{0}'],
                //上传的最大个数
                max: [1, '文件的最大个数不能超过{0}个'],
                //文件大小的最小值,这个单位是kb
                //minSize: [10, '文件的大小不能小于{0}KB'],
                //文件大小的最大值,单位也是kb
                maxSize: [10240, '文件大小不能大于10M']
            }
        }).render();

        var queue = uploader.get("queue");
        queue.on("itemremoved", function (e) {
            photoEdit = true;
        });
        uploader.on("change", function (e) {
            photoEdit = true;
        });
        //--------------图片上传功能End-----------------

        // 加载用户信息
        loadUserinfo(user_id);

        // 加载用户信息juror表
        function loadUserinfo(id) {
            form.clearFields();
            form.clearErrors();
            $("#age").html("0岁");
            $('#birthday').val("");
            $('#birthday_show').html("");
            $('#password').val("");
            $(".show_info").hide();
            $(".show_icon").hide();
            $(".show_info_ok").hide();
            $(".show_icon_ok").hide();
            // 设置用户名的提示，编辑时不可以修改用户名
            $("#username").attr("readonly", "readonly");
            $("#username").focus(function () {
                $(".show_info_edit").show();
                $(".show_info").hide();
                $(".show_icon_ok").hide();
            });
            $("#username").blur(function () {
                $(".show_info_edit").hide();
            });

            // 加载表oneExtend 和 图片
            reloadUserinfo();
        }

        // 加载表oneExtend
        function reloadUserinfo() {
            // 读取审判员信息oneExtend表
            $.getJSON("<%=basePath%>juror/oneExtend", {"jurorInfo.userId": user_id, _: new Date().getTime()}, function (res) {
                if (res && res.info) {
                    var data = res.info;
                    $("#userinfo_form [name^='jurorInfo.']").each(function () {
                        // 循环加载每条数据
                        if ($(this).hasClass("select22")) {
                            $(this).select2().select2("val", ["" + data[$(this).attr("id")] + ""]);
                        } else {
                            $(this).val(data[$(this).attr("id")]);
                        }
                        // 参与案件类型
                        if ("typeOfCase" == $(this).attr("id")) {
                            if (picker) {
                                picker.setSelectedValue(data[$(this).attr("id")]);
                            }
                        }
                        // 工作区域
                        if ("workArea" == $(this).attr("id")) {
                            if (res.gzqy) {
                                $("#gzqy").val(res.gzqy);
                            }
                        }
                    });
                }
            });
            // 根据userinfo里的数据，填充form表单
            var flag_1 = false;
            var flag_2 = false;
            $("#userinfo_form [name^='userInfo.']").each(function () {
                // 循环加载每条数据
                if ($(this).hasClass("select22")) {
                    $(this).select2().select2("val", ["" + userinfo[$(this).attr("id")] + ""]);
                } else {
                    $(this).val(userinfo[$(this).attr("id")]);
                }
                if ($(this).attr("name").indexOf("birthday") > 0) {
                    $("#birthday_show").html(userinfo[$(this).attr("id")]);
                }
                if (auth) {
                } else {
                    $("#courtNo").attr("disabled", true);
                }
                var city = $("#city").val();
                var area = $("#area").val();
                var province = $("#province").val();
                if (!flag_1 && province != '' && province > 0 && city != '' && city > 0) {
                    flag_1 = true;
                    var url = "<%=basePath%>code/getCity";
                    var datas = {provinceID: province};
                    $.post(url, datas, function (data) {
                        $("#userCity").html("<option value='-1'>请选择</option>");
                        $("#userArea").html("<option value='-1'>请选择城市</option>")
                        if (data.length > 0) {
                            for (var i = 0; i < data.length; i++) {
                                $("#userCity").append($("<option>").attr({"value": data[i].cityID}).text(data[i].city));
                            }
                        }
                        $("#userCity").val(city);
                    });
                }
                if (!flag_2 && area != '' && area > 0 && city != '' && city > 0) {
                    flag_2 = true;
                    var url = "<%=basePath%>code/getArea";
                    var datas = {cityID: city};
                    $.post(url, datas, function (data) {
                        $("#userArea").html("<option value='-1'>请选择</option>")
                        if (data.length > 0) {
                            for (var i = 0; i < data.length; i++) {
                                $("#userArea").append($("<option>").attr({"value": data[i].areaID}).text(data[i].area));
                            }
                        }
                        $("#userArea").val(area);
                    });
                }
            });
            // 读取图片
            $.getJSON("<%=basePath%>photo/getPhotoById", {userId: user_id, _: new Date()}, function (data) {
                if (data !== null) {
                    queue.setItems([{success: true, ext: '.jpg', name: '原始照片.jpg', url: data}]);
                } else {
                    queue.setItems([]);
                }
            });
            photoEdit = false;
            calcAge();
            workTotal();
            courtTotal();

            // 清空错误信息
            if (form) {
                form.clearErrors();
            }
        }

        $("#changeUUID").val(changeUUID);

    });

    // 监听form表单的提交按钮
    $("#saveButton").on("click", function () {
        if ($("#id").val() != '') {
            userCheck();
        } else {
            // 先身份验证，身份验证过了，再用户名验证
            idCardCheck();
        }
    });

    // 监控工作区域
    $("#gzqy").on("click", function () {
        $(".overlay_show").show();
    });

    // 监控工作区域
    $("#gzqy").on("focus", function () {
        $(".overlay_show").show();
    })

    function initMultiSelect(data) {
        var getJsonTree = function (data, parentid) {
            var itemArr = [];
            for (var i = 0; i < data.length; i++) {
                var node = data[i];
                if (node.parentId == parentid) {
                    var newNode = {};
                    newNode.id = node.id;
                    newNode.text = node.codeName;
                    newNode.children = getJsonTree(data, node.courtCode);
                    itemArr.push(newNode);
                }
            }
            return itemArr;
        };
        var dat = getJsonTree(data, null);
        BUI.use(['bui/extensions/treepicker', 'bui/tree'], function (TreePicker, Tree) {
            var tree = new Tree.TreeList({
                nodes: dat,
                checkType: 'all',
                cascadeCheckd: false, //不级联勾选
                showLine: true //显示连接线
            });
            picker = new TreePicker({
                trigger: '#cyajlx',
                valueField: '#typeOfCase', //如果需要列表返回的value，放在隐藏域，那么指定隐藏域
                selectStatus: 'checked',//设置勾选作为状态改变的事件
                width: 230,  //指定宽度
                children: [tree] //配置picker内的列表
            });
            picker.render();
        });
    }

    function loadArea() {
        BUI.use(['bui/extensions/treepicker', 'bui/tree', 'bui/data'], function (TreePicker, Tree, Data) {
            var treestore = new Data.TreeStore({
                root: {text: "全部",},
                url: '<%=basePath%>code/getDetailArea'
            });
            tree = new Tree.TreeList({
                render: ".overlay_content_tree",
                store: treestore,
                cascadeCheckd: false, //不级联勾选
                multipleCheck: false, //是否多选，非多选时使用radio
                showLine: true,//显示连接线
                checkType: 'onlyLeaf', //仅叶子可以勾选
                listeners: {
                    itemselected: function (ev) {
                        var item = ev.item;
                        tree.clearSelected();
                        tree.clearAllChecked();
                        tree.setChecked(item);
                        vm.checkValue = item.id;
                        vm.checkName = item.text;
                        var arr = [];
                        readParentNode(item, arr);
                        var str = "";
                        for (var i = arr.length; i > 0; i--) {
                            if (i == arr.length) {
                                str += arr[i - 1];
                                continue;
                            }
                            str += "-" + arr[i - 1];
                        }
                        vm.checkFullName = str;
                    }
                }
            });
            tree.render();
            treestore.load();//加载根节点，也可以让用户点击加载
        });
        initVue();
    }

    function readParentNode(item, arr) {
        if (item) {
            if ("全部" == item.text) {
                return;
            }
            arr.push(item.text);
            if (item.parent) {
                readParentNode(item.parent, arr);
            }
        }
    }

    function changeTarget(obj) {

        var $this = $(obj);
        if ($this.hasClass("checked_span")) {
            return;
        }
        $(".overlay_title ul li span").removeClass("checked_span");
        $this.addClass("checked_span");
        $(".overlay_content > div").addClass("hide");

        $("." + $this.attr("targetClass")).removeClass("hide");

    }

    // 工作区域的查询
    function queryPalces() {
        var place = $("#search_place").val();
        if (!place) {
            return;
        }

        $.ajax({
            url: "<%=basePath%>juror/searchArea",
            type: "post",
            data: {codeName: place},
            dataType: "json",
            success: function (datas) {
                vm.handleData(datas);
            }
        })

    }

    function checkItem(obj) {
        var $this = $(obj);
        $(".bui-tree-td").removeClass("bui-tree-item-checked");
        $this.addClass("bui-tree-item-checked");
        vm.checkValue = $this.data("id");
        vm.checkName = $this.data("name");
        vm.checkFullName = $this.data("fullname");
    }

    function saveSearch() {
        $("input[name='jurorInfo.workArea']").val(vm.checkValue);
        $("input[name='jurorInfo.gzqy']").val(vm.checkFullName);
        quitSearch();
    }

    function quitSearch() {
        $(".overlay_show").hide();
        vm.checkValue = null;
        vm.checkName = null;
        vm.checkFullName = null;
        vm.places = null;
        $("#search_place").val('');
        tree.clearAllChecked();
        $(".bui-tree-td").removeClass("bui-tree-item-checked");
        $("#workArea").focus().change().blur();

    }

    function clearSearch() {
        vm.checkValue = null;
        vm.checkName = null;
        vm.checkFullName = null;
        tree.clearAllChecked();
        $(".bui-tree-td").removeClass("bui-tree-item-checked");
        $("input[name='jurorInfo.workArea']").val(vm.checkValue);
        $("input[name='jurorInfo.gzqy']").val(vm.checkFullName);
    }

    function initVue() {
        vm = new Vue({
            el: "#search_element",
            data: {
                title: ["id", "parentName", "codeName", "fullName"],
                places: [],
                isShow: true,
                checkValue: null,
                checkName: null,
                checkFullName: null
            },
            methods: {
                handleData: function (datas) {
                    if (!datas) {
                        return;
                    }
                    var rows = datas;
                    var returnData = [];
                    var title = this.title;
                    for (var i in rows) {
                        var m = {};
                        var obj = rows[i];
                        for (var j in title) {
                            var index = title[j];
                            m[index] = obj[index] ? obj[index] : "";
                        }
                        returnData.push(m);
                    }
                    this.places = returnData;
                },
                handleShow: function (index) {
                    if (index && (index == 'id' || index == 'fullName')) {
                        return false;
                    }
                    return true;
                }
            }
        });

    }

    function cleanError() {
        $(".show_info").hide();
        $(".show_icon").hide();
    }

    function generateUserName(obj) {
        // 获得身份证字符串
        var str_idcard = $(obj).val().replace(/\s/g, "");
        // 截取身份证后六位
        var idcard_6 = str_idcard.substring(str_idcard.length - 6, str_idcard.length);
        if ("" == fullname || "undefined" == fullname) {
            fullname = $("#basic-info #fullname").val();
        }
        $("#username").val(fullname + idcard_6);
    }

    // 获取当前id的val，赋值给fullname
    function generateUserName_2(obj) {
        fullname = $(obj).val().replace(/\s/g, "");
        if ("" == fullname || "undefined" == fullname) {
            fullname = $("#basic-info #fullname").val();
        }
    }

    // 定位锚点
    function changeMarkers(obj) {
        $(obj).parent().parent().find(".active").removeClass("active");
        $(obj).parent().addClass("active");
    }

    //-------------计算日期相关函数Start---------------
    function calcAge() {
        $("#userinfo_form #age").text(calcYears($("#userinfo_form #birthday").val()) + '岁');
    }

    function workTotal() {
        $("#userinfo_form #totalSeniority").text(workTotalYears($("#userinfo_form #workDate").val(), $("#userinfo_form #extraSeniority").val(), $("#userinfo_form #deductionSeniority").val()) + '年');
    }

    function courtTotal() {
        $("#userinfo_form #totalCourtYear").text(workTotalYears($("#userinfo_form #enterDate").val(), $("#userinfo_form #beforeCourtWorkYear").val()) + '年');
    }

    //-------------计算日期相关函数End---------------

    // 用户名验证
    function userCheck() {
        var url = "<%=path%>/checkUserName";
        var datas = {courtStdNo: $("#courtNo").val(), username: $("#username").val(), id: $("#id").val(), valid: 1, userType: 1, isEdit: isEdit};
        myForm.valid();
        if (myForm.isValid()) {
            $.post(url, datas, function (data) {
                if (data.UserNameCheck == "false") {
                    if (isEdit) {
                        $('#password').val("");
                    }
                    $("#courtNo").attr("disabled", false);
                    myForm.submit();
                } else {
                    // 如果username返回为""，说明并未修改传过去的usename
                    if (data.username != "") {
                        $(".show_info_ok").show();
                        $(".show_icon_ok").show();
                        newUserName = data.username;
                        $("#suggestUsername").text(newUserName);
                        $("#username").val(newUserName);
                    } else {
                        $(".show_info").show();
                        $(".show_icon").show();
                    }
                    var a = document.getElementById("show_e");
                    // 取消<a>标签原先的onclick事件,使<a>标签点击后通过href跳转(因为无法用js跳转)^-^
                    a.setAttribute("onclick", '');
                    // 激发标签点击事件OVER
                    a.click("return false");
                }
            });
        } else {
            myForm.submit();
        }
    }

    // 身份证号验证
    function idCardCheck() {
        var url = "<%=path%>/checkIdCard";
        var datas = {courtStdNo: $("#courtNo").val(), username: $("#username").val(), id: $("#id").val(), idcard: $("#idcard").val()};
        myForm.valid();
        if (myForm.isValid()) {
            $.post(url, datas, function (data) {
                if (data.idCardCheck) {
                    userCheck();
                } else {
                    var typeStr = "";
                    switch (data.userType) {
                        case 1 :
                            typeStr += "正式人员"
                            break;
                        case 2 :
                            typeStr += "编外人员"
                            break;
                        case 3 :
                            typeStr += "人民陪审员"
                            break;
                    }
                    var enabledStr = "";
                    switch (data.enabled) {
                        case 0 :
                            enabledStr += "停用"
                            break;
                        case 1 :
                            enabledStr += "启用"
                            break;
                    }
                    var showStr = "<span>该人员已经存在,请对相应人员进行操作  ";
                    showStr += (typeStr == "" ? "" : " , 人员类型为 : " + typeStr);
                    showStr += (typeStr == "" ? "" : " , 人员状态为 : " + enabledStr);
                    showStr += ("<a style='margin-left:10px;' target='_blank' href='<%=basePath%>view/detail_new?id=" + data.userId + " '>点击查看</a></span>");
                    BUI.Message.Alert(showStr, "warning");
                    //打开新页面
                }
            });
        } else {
            myForm.submit();
        }
    }
</script>
</html>