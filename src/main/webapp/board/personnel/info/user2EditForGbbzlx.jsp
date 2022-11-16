<%@ page import="cn.net.withub.ums.common.UmsConstant" %>
<%@ page import="cn.net.withub.ums.entity.UmsUserInfoView" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    UmsUserInfoView u = (UmsUserInfoView) session.getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
    boolean equals = u.getId().equals("-1");
    //    调用iframe传来的userid
    String user_id = request.getParameter("user_id");
    String changeUUID = request.getParameter("changeUUID");
%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>在编人员 - 编辑</title>
    <jsp:include page="/basic_import.jsp"></jsp:include>
    <jsp:include page="/fragment/tip.jsp"></jsp:include>
    <script type="text/javascript" src="<%=basePath%>js/piny.js"></script>
    <script src="<%=basePath%>js/common/idchecker.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/crypto-js/rollups/md5.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/base64/base64.js" type="text/javascript"></script>
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

        .bui-grid-button-bar .button-small [class^="icon-"] {
            margin: 0px 2px 0 -5px;
        }

        #deptNoId {
            margin: 0 8px 0 3px;
        }

        .bui-message {
            z-index: 2000;
        }

        .show_a {
            cursor: pointer;
        }

        .button-own {
            width: 125px;
        }

        input.calendar {
            width: 140px;
        }

        .grid-valid {
            color: #3366cc;
            cursor: pointer;
            margin-right: 5px;
        }

        .grid-valid:hover {
            color: #ff6600;
        }

        select.select22 {
            top: 0px;
        }

        .scourtTree .bui-tree-list {
            height: 300px;
            overflow: auto;
        }

        #photo .bui-queue-item {
            height: 160px !important;
        }

        #photo ul {
            width: 122px;
            height: 162px;
            border: 1px solid #eee;
        }

        #photo .bui-uploader-htmlButton {
            margin-left: 33px;
        }
    </style>
</head>

<body>

<%--新增页面弹出层--%>
<div id="content">
    <a name="baseInfo"></a>
    <form id="userinfo_form" class="form-horizontal" action="<%=basePath%>userinfo/save" method="post">
        <input type="hidden" id="userType" name="userInfo.userType">
        <input type="hidden" id="changeUUID" name="changeUUID">
        <input type="hidden" name="id" id="new_id">
        <input type="hidden" name="userInfo.id" id="id">
        <%--基本信息--%>
        <div id="basic-info">
            <a name="show_error"></a>
            <div class="row">
                <h3 style="width:150px;display: inline-block">在编人员 - 基本信息</h3>
                <%--<h5 style="display: inline-block">用户名密码默认为身份证后六位数字</h5>--%>
            </div>
            <div class="row" style="height: 48px;">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>姓名：</label>
                    <div class="controls">
                        <input id="fullname" name="userInfo.fullname" type="text" placeholder="请输入姓名" class="control-text" data-rules="{required:true}" onchange="generateUserName(this)">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>法院：</label>
                    <div class="controls">
                        <select id="courtNo" typeId="1" class="Courtcode" name="userInfo.courtNo" onchange="loadDeptList(department, this.value, '<option value=\'\'>请选择</option>')" data-rules="{required:true}"></select>
                    </div>
                </div>
                <div style="display: inline-block;margin-left: 150px;">
                    <div id="photo"></div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>部门：</label>
                    <div class="controls">
                        <select id="department" courtNo="" class="select22" data-rules="{required:true}" name="userInfo.department">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">曾用名：</label>
                    <div class="controls">
                        <input id="formerName" name="userInfo.formerName" type="text" placeholder="请输入曾用名" class="control-text">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>性别：</label>
                    <div class="controls">
                        <select id="gender" typeId="3" class="code" data-rules="{required:true}" name="userInfo.gender"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>婚姻状况：</label>
                    <div class="controls">
                        <select id="maritalStatus" typeId="6" class="code" data-rules="{required:true}" name="userInfo.maritalStatus"></select>
                    </div>
                </div>

            </div>
            <div class="row">
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
                    <label class="control-label"><s>*</s>出生地：</label>
                    <div class="controls">
                        <input id="birthplace" name="userInfo.birthplace" type="text" placeholder="请输入用户出生地" data-rules="{required:true}" class="control-text">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>籍贯：</label>

                    <div class="controls">
                        <input id="hometown" name="userInfo.hometown" type="text" placeholder="请输入用户籍贯"
                               class="control-text" data-rules="{required:true}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>身份证号：</label>

                    <div class="controls">
                        <input id="idcard" name="userInfo.idcard" type="text" class="span4 span-width spancontrol-text"
                               placeholder="请输入用户身份证号码"
                               data-rules="{required:true,idcheck:null}">
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>出生日期：</label>

                    <div class="controls">
                        <input id="birthday" name="userInfo.birthday" type="text" class="calendar " placeholder="出生日期"
                               data-rules="{datecheck1:null,required:true}" onchange="calcAge()">
                        <%-- <label class="control-label" style="text-align: left" id="birthday_show"></label>
                         <input id="birthday" name="userInfo.birthday" type="hidden"
                                value="">--%>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>年龄：</label>

                    <div class="controls">
                        <input class="control-text" type="text" style="text-align: left" id="age" data-rules="{required:true}">
                    </div>
                </div>

            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>健康状况：</label>

                    <div class="controls">
                        <select id="physicalCondition" typeId="10" class="code" data-rules="{required:true}"
                                name="userInfo.physicalCondition"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>所属编制：</label>

                    <div class="controls">
                        <select id="preparation" typeId="9" class="code" data-rules="{required:true}"
                                name="userInfo.preparation"></select>
                    </div>
                </div>
                <div class="control-group span8">

                    <a id="show_e" href="#show_error"></a>
                    <label class="control-label"><s>*</s>用户名：</label>

                    <div class="controls">
                        <input id="username" name="userInfo.username" type="text" placeholder="请输入用户名"
                               class="control-text" data-rules="{required:true}" onchange="cleanError()">
                    </div>
                    <div class="" style="float: left;margin-top: -15px;color: red;margin-left: 120px;">
                        <span class="show_icon">!</span>
                        <span class="show_info">用户名已存在</span>
                        <span class="show_icon_ok">!</span>
                        <span class="show_info_ok">用户名重复，建议改为：“<span id="suggestUsername"></span>”，确认请点击保存！</span>
                        <span class="show_info_edit">编辑时用户名不可更改！</span>
                    </div>
                </div>

            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>人员分类：</label>
                    <div class="controls">
                        <select id="personnelClassification" typeId="94" class="code" listType="tree2" data-rules="{required:true}" name="userInfo.personnelClassification"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>工作岗位：</label>
                    <div class="controls">
                        <select id="job" typeId="93" class="code select22" data-rules="{required:true}" name="userInfo.job"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>岗位性质：</label>
                    <div class="controls">
                        <select id="positionNature" data-rules="{required:true}" typeId="64" class="code select22" name="userInfo.positionNature"></select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">职务类别：</label>

                    <div class="controls">
                        <select id="positionType" name="userInfo.positionType" typeId="62" class="code"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">职务类别日期：</label>

                    <div class="controls">
                        <input id="positionTypeDate" name="userInfo.positionTypeDate" type="text"
                               class="calendar " placeholder="职务类别日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">职务任用方式：</label>

                    <div class="controls">
                        <select id="assign" typeId="63" class="code" name="userInfo.assign"></select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group12 span8">
                    <label class="control-label">法官资格日期：</label>

                    <div class="controls">
                        <input id="lawyerDate" name="userInfo.lawyerDate" type="text" class="calendar "
                               placeholder="法官资格日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>兼任庭长：</label>

                    <div class="controls">
                        <select id="isParttimePresidingJudge" data-rules="{required:true}" name="userInfo.isParttimePresidingJudge" typeId="68"
                                class="code"></select>
                    </div>
                </div>
            </div>
            <div class="row">

                <div class="control-group span8">
                    <label class="control-label">手机号码：</label>

                    <div class="controls">
                        <input id="phoneNumber" name="userInfo.phoneNumber" type="text" class="control-text"
                               placeholder="请输入用户手机号码"
                               data-rules="{phoneNumber:11}">
                    </div>
                </div>

                <div class="control-group span8">
                    <label class="control-label">办公电话：</label>

                    <div class="controls">
                        <input id="extOfficePhone" name="userInfo.extOfficePhone" type="text" class="control-text" placeholder="请输入用户办公电话">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">党组职务：</label>

                    <div class="controls">
                        <select id="partyOffice" typeId="57" class="code" name="userInfo.partyOffice"></select>
                    </div>
                </div>
                <div class="control-group span8" style="display: none">
                    <label class="control-label">党组职务(最高院)：</label>
                    <div class="controls">
                        <select id="partyOfficeReport" typeId="1057" class="code" name="userInfo.partyOfficeReport"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">党组职务日期：</label>

                    <div class="controls">
                        <input id="partyOfficeDate" name="userInfo.partyOfficeDate" type="text"
                               class="calendar " placeholder="党组职务日期" data-rules="{datecheck1:null}">
                    </div>
                </div>

                <div class="control-group span8" style="display: none;">
                    <label class="control-label">班子成员：</label>
                    <div class="controls">
                        <select id="bzcy" name="userInfo.bzcy">
                            <option value="0"></option>
                            <option value="1">班子成员</option>
                            <option value="2">非班子成员</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">专业证书：</label>

                    <div class="controls">
                        <select id="proCert" typeId="47" class="code" name="userInfo.proCert"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">获得证书日期：</label>

                    <div class="controls">
                        <input id="proCertDate" name="userInfo.proCertDate" type="text" class="calendar "
                               placeholder="获得证书日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">排序：</label>

                    <div class="controls">
                        <input id="sortNo" name="userInfo.sortNo" type="text" placeholder="请输入排序号"
                               data-rules="{number:true}"
                               class="control-text">
                    </div>
                </div>
            </div>
            <%--额外的信息--%>
            <input id="password" name="userInfo.password" type="hidden"
                   value="">
            <input id="isValid" name="userInfo.isValid" type="hidden"
                   value="">
        </div>
        <%--职务信息--%>
        <div id="position-info">
            <a name="mainjobInfo"></a>
            <hr/>
            <h3>职务信息</h3>

            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>政治面貌：</label>

                    <div class="controls">
                        <select id="political" typeId="13" class="code" name="userInfo.political" disabled></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>加入日期：</label>

                    <div class="controls">
                        <input id="politicalDate" name="userInfo.politicalDate" type="text"
                               placeholder="政治面貌日期" disabled>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>法律职务：</label>

                    <div class="controls">
                        <select id="lawPosition" typeId="16" class="code" name="userInfo.lawPosition" disabled></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>任职日期：</label>

                    <div class="controls">
                        <input id="lawPositionDate" name="userInfo.lawPositionDate" type="text"
                               placeholder="法律职务任职日期" disabled>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>行政职务：</label>

                    <div class="controls">
                        <select id="administrationPosition" typeId="15" class="code"
                                name="userInfo.administrationPosition" disabled></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>任职日期：</label>

                    <div class="controls">
                        <input id="administrationPositionDate" name="userInfo.administrationPositionDate"
                               type="text" placeholder="行政职务任职日期" disabled>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>职级：</label>

                    <div class="controls">
                        <select id="rank" typeId="17" class="code" name="userInfo.rank" disabled></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>职级日期：</label>

                    <div class="controls">
                        <input id="rankDate" name="userInfo.rankDate" type="text"
                               placeholder="职级日期" disabled>
                    </div>
                </div>
            </div>
            <div class="row">

                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>员额法官：</label>

                    <div class="controls">
                        <select id="yefg" name="userInfo.yefg" disabled typeId="68" class="code">
                        </select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>公务员级别：</label>

                    <div class="controls">
                        <select id="servantLevel" typeId="83" class="code" name="userInfo.servantLevel" disabled></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>级别起算日期：</label>

                    <div class="controls">
                        <input id="servantLevelDate" name="userInfo.servantLevelDate" type="text"
                               placeholder="级别起算日期" disabled>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>法官等级：</label>

                    <div class="controls">
                        <select id="judge_level" typeId="117" class="code" disabled></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>法官等级日期：</label>

                    <div class="controls">
                        <input type="text" id="judge_level_date" disabled placeholder="法官等级日期">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>法警等级：</label>

                    <div class="controls">
                        <select id="marshal_level" typeId="115" class="code" disabled></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>法警等级日期：</label>

                    <div class="controls">
                        <input type="text" id="marshal_level_date" disabled placeholder="法警等级日期">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>辅助人员等级：</label>

                    <div class="controls">
                        <select id="helper_level" typeId="118" class="code field"
                                otherfield="levelInfo.helper_level" disabled></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>辅助人员等级日期：</label>

                    <div class="controls">
                        <input type="text" disabled otherfield="levelInfo.d_start_date"
                               id="helper_level_date" placeholder="辅助人员等级日期">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>书记员等级：</label>

                    <div class="controls">
                        <select id="clerk_level" typeId="116" class="code field" disabled></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>书记员等级日期：</label>

                    <div class="controls">
                        <input type="text" disabled id="clerk_level_date" placeholder="书记员等级日期">
                    </div>
                </div>
            </div>
        </div>
        <%--教育信息--%>
        <div id="education-info">
            <a name="eduInfo"></a>
            <hr/>
            <h3>教育经历</h3>

            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>学历：</label>

                    <div class="controls">
                        <select id="educationBackground" typeId="11" class="code"
                                name="userInfo.educationBackground" disabled></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>学位：</label>

                    <div class="controls">
                        <select id="degree" typeId="23" class="code" name="userInfo.degree" disabled></select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>获得学位日期：</label>
                    <div class="controls">
                        <input id="degreeDate" name="userInfo.degreeDate" type="text" placeholder="获得学位日期" disabled>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>专业：</label>
                    <div class="controls">
                        <select id="major" typeId="12" class="code" name="userInfo.major" disabled></select>
                    </div>
                </div>
            </div>

        </div>
        <%--证件信息--%>
        <div id="card-info">
            <a name="certificateInfo"></a>
            <hr/>
            <h3>证件信息</h3>
            <%--            <input name="userInfo.userType" class="hide">--%>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">UK编号：</label>
                    <div class="controls">
                        <input id="ukNo" name="userInfo.ukNo" type="text" class="control-text" placeholder="UK编号">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">工作证编号：</label>
                    <div class="controls">
                        <input id="workNo" name="userInfo.workNo" type="text" class="control-text" placeholder="工作证编号">
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">饭卡编号：</label>
                    <div class="controls">
                        <input id="fankaNo" name="userInfo.fankaNo" type="text" class="control-text" placeholder="饭卡编号">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">执行公务证编号：</label>
                    <div class="controls">
                        <input id="officialNo" name="userInfo.officialNo" type="text" class="control-text" placeholder="执行公务证编号">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>法官资格证书类型：</label>
                    <div class="controls">
                        <select id="lawyerCert" typeId="86" class="code" data-rules="{required:true}" name="userInfo.lawyerCert"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>证书取得时间：</label>
                    <div class="controls">
                        <input id="lawyerCertDate" name="userInfo.lawyerCertDate" type="text" class="calendar " placeholder="资格证书取得时间" data-rules="{required:true,datecheck1:null}">
                    </div>
                </div>
            </div>
        </div>
        <%--工作信息--%>
        <div id="education-info">
            <a name="jobDetailInfo"></a>
            <hr/>
            <h3>工作信息</h3>
            <div class="row">
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>工作日期：</label>
                    <div class="controls">
                        <input id="workDate" name="userInfo.workDate" type="text" class="calendar " data-rules="{required:true,datecheck1:null}" onchange="workTotal()" placeholder="工作日期">
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>政法工作日期：</label>
                    <div class="controls">
                        <input id="politicLawWorkDate" name="userInfo.politicLawWorkDate" type="text" class="calendar " placeholder="政法工作日期" data-rules="{required:true,datecheck1:null}">
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>进入本院日期：</label>
                    <div class="controls">
                        <input id="enterDate" name="userInfo.enterDate" type="text" class="calendar " data-rules="{required:true,datecheck1:null}" onchange="courtTotal()" placeholder="进入本院日期">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>补充工龄：</label>
                    <div class="controls">
                        <input id="extraSeniority" name="userInfo.extraSeniority" type="text" class="control-text input-small" onchange="workTotal()" data-rules="{required:true,number:true,min:0}" placeholder="补充工龄">年
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>扣减工龄：</label>
                    <div class="controls">
                        <input id="deductionSeniority" name="userInfo.deductionSeniority" type="text" class="control-text input-small" onchange="workTotal()" data-rules="{required:true,number:true,min:0}" placeholder="扣减工龄">年
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>应加学制：</label>
                    <div class="controls">
                        <input id="additionalDuration" name="userInfo.additionalDuration" type="text" class="control-text input-small" data-rules="{required:true,number:true,min:0}" placeholder="应加学制">年
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>入院前工作年限：</label>
                    <div class="controls">
                        <input id="beforeCourtWorkYear" name="userInfo.beforeCourtWorkYear" type="text" class="control-text input-small" onchange="courtTotal()" data-rules="{required:true,number:true,min:0}" placeholder="入院前工作年限">年
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">法院工作合计年限：</label>
                    <div class="controls">
                        <label class="control-label" style="text-align: left" id="totalCourtYear"></label>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">连续工龄：</label>
                    <div class="controls">
                        <label class="control-label" style="text-align: left" id="totalSeniority"></label>
                    </div>
                </div>
            </div>
        </div>
        <%--调遣信息--%>
        <div id="dispatch-info">
            <a name="DispatchInfo"></a>
            <hr/>
            <h3>调遣信息</h3>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>进入途径：</label>
                    <div class="controls">
                        <select id="enterWay" typeId="43" class="code" data-rules="{required:true}" name="userInfo.enterWay"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>进入来源：</label>
                    <div class="controls">
                        <select id="enterSource" typeId="114" class="code" data-rules="{required:true}" listType="tree2" name="userInfo.enterSource"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>审核日期：</label>
                    <div class="controls">
                        <input id="verifyDate" name="userInfo.verifyDate" type="text" class="calendar " placeholder="审核日期" data-rules="{required:true,datecheck1:null}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>原职务：</label>
                    <div class="controls">
                        <select id="formerPost" typeId="14" class="code select22" name="userInfo.formerPost" data-rules="{required:true}"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>原职级：</label>
                    <div class="controls">
                        <select id="formerRank" typeId="17" class="code select22" name="userInfo.formerRank" data-rules="{required:true}"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>原单位：</label>
                    <div class="controls">
                        <input id="formerUnit" name="userInfo.formerUnit" type="text" value="" class="control-text" placeholder="原单位" data-rules="{required:true}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">调离原因：</label>
                    <div class="controls">
                        <select id="leaveReason" typeId="45" class="code" name="userInfo.leaveReason"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">调离去向：</label>
                    <div class="controls">
                        <select id="leaveDestination" typeId="46" class="code" name="userInfo.leaveDestination"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">调离日期：</label>
                    <div class="controls">
                        <input id="leaveDate" name="userInfo.leaveDate" type="text" class="calendar " placeholder="调离日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
            </div>
        </div>
        <%--        提交按钮--%>
        <div style="margin-left: 40%" hidden>
<%--            <button type="submit" class="button button-primary" style="visibility: visible">保存</button>--%>
            <button id="saveButton" type="button" class="button button-primary" style="visibility: visible">保存</button>
        </div>
    </form>
</div>


</body>


<script>
    // 用于改变编制类型的主键id
    var changeUUID = "<%=changeUUID%>";
    // 成功数量
    var successCount = 0;
    // 需请求ajax的总数
    var ajax_num = 0;
    // 需要编辑的人员id
    var user_id = "<%=user_id%>";
    var CourtNo = '';
    var auth = false;
    var myForm;
    var photoEdit = false;
    var TForm;
    var DzForm;
    // 当前页面只是用来编辑的
    var isEdit = true;
    var Rstore;
    var closeFlag = false;
    var V = $.extend({}, V, parent.V);
    var startRow;
    var current_court = null;
    var query_condition = {userType: 1, isInfoComplete: 0, isValid: 1,};
    var userinfo;
    // -------------初始化下拉框数据Start---------------
    var firstLine = "<option value=''>请选择</option>";

    // 当前页面需要通过ajax获取数据的下拉框
    ajax_num = $("select.code").length;
    $("select.code").each(function () {
        var typeId = $(this).attr("typeId");
        //行政职务和法律职务其他地方要用到
        if (typeId && typeId == 15) {
            loadCodeListCountNum(this, firstLine, null, $("#administrationPositionInfo"));
        } else if (typeId && typeId == 16) {
            loadCodeListCountNum(this, firstLine, null, $("#lawPositionInfo"));
        } else {
            loadCodeListCountNum(this, firstLine);
        }
    });
    // 由于要加法院增修限制 超级用户不受限制  单独提出来
    $.getJSON("/ums/code/codeListByType", {typeId: 1}, function (data) {
        for (var i = 0; i < data.data.length; i++) {
            $("select.Courtcode").append($("<option>").attr({"value": data.data[i].id}).text(data.data[i].codeName));
        }
        $("select.Courtcode").select2();
        if (data.auth) {
            CourtNo = data.value;
            auth = true;
        } else {
            CourtNo = data.value;
        }
    });
    // 同时修改上报字段
    $('[id$=Report]').each(function (e) {
        var reportId = $(this).attr('id');
        // 本地字段修改时
        $('#' + reportId.substring(0, reportId.indexOf('Report'))).on('change', function (e1) {
            // 上报字段
            var reportObj = $('#' + reportId);
            var text = $(this).find('option:selected').text();
            var option = reportObj.find('option');
            //获取上报字段的对应value值
            var getValue = function (text) {
                for (var i = 0; i < option.length; i++) {
                    var obj = $(option[i]);
                    if (obj.text() == text) {
                        return obj.val();
                    }
                }
                return '';
            };
            var val = getValue(text);
            if (val == '') {
                val = getValue('其他');
                if (val == '') {
                    val = getValue('无');
                }
            }
            reportObj.val(val).change();
            if (reportObj.hasClass('select22')) {
                reportObj.select2().select2('val', val);
            }
        });
    });

    // 定时器：一秒执行一次，满足条件的时候执行下面的
    var clock = setInterval(countDown, 500);

    function countDown() {
        if (ajax_num == successCount) {
            clearInterval(clock); //清除js定时器
            // 初始化BUI相关组件
            BUI.use(['common/search', 'bui/tree', 'bui/data', 'bui/calendar', 'bui/overlay', 'bui/form', 'bui/uploader', 'bui/mask', 'bui/grid'], function (Search, Tree, Data, Calendar, Overlay, Form, Uploader, Mask, Grid) {
                form = new Form.HForm({
                    srcNode: '#userinfo_form',
                    submitType: 'ajax',
                    callback: function (data) {
                        if (data.success) {
                            BUI.Message.Alert("保存成功，请尽快完善简历信息、家庭信息！", function () {
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
                }).render();
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
                            'success': '<div class="success"><img id="qpic" src="{url}" title="{name}" style="width: 100%"/></div>',
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
                        maxSize: [2048, '文件大小不能大于2M']
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

                loadUserinfo(user_id);
                $("#changeUUID").val(changeUUID);

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
                    var obj = {id: id, _: new Date()};
                    //找到所有配置其他数据集的字段
                    $("#userinfo_form [otherfield]").each(function () {
                        var table_field = $(this).attr('otherfield');
                        var table = table_field.split('.')[0];
                        var field = table_field.split('.')[1];
                        if (obj['fields.' + table] !== undefined) {
                            obj['fields.' + table] = obj['fields.' + table] + "," + field;
                        } else {
                            obj['fields.' + table] = field;
                        }
                    });
                    $.getJSON("<%=basePath%>userinfo/one", obj, function (data) {
                        userinfo = data;
                        reloadUserinfo();
                        // 设置用户名的提示，编辑时不可以修改用户名
                        $("#username").attr("readonly", "readonly");
                        $("#username").focus(function () {
                            $(".show_info_edit").show();
                        });
                        $("#username").blur(function () {
                            $(".show_info_edit").hide();
                        });
                    });
                }

                function reloadUserinfo() {
                    console.log("123");
                    $("#userinfo_form [name^='userInfo.']").each(function () {
                        if ($(this).hasClass("select22")) {
                            $(this).select2().select2("val", ["" + userinfo[$(this).attr("id")] + ""]);
                        } else {
                            $(this).val(userinfo[$(this).attr("id")]);
                        }
                        if ($(this).attr("id") === "department") {
                            // 根据法院加载部门
                            loadDeptList($("#department"), $("#courtNo").val(), firstLine, function () {
                                $("#department").select2().select2("val", ["" + userinfo.department + ""]);
                            });
                        }
                        if ($(this).attr("id") === "courtNo") {
                            $(this).select2("val", ["" + userinfo[$(this).attr("id")] + ""]);
                            // 根据法院加载部门
                            loadDeptList($("#department"), $(this).val(), firstLine, function () {
                                $("#department").select2().select2("val", ["" + userinfo.department + ""]);
                            });
                        }
                        if ($(this).attr("name").indexOf("birthday") > 0) {
                            $("#birthday").html(userinfo[$(this).attr("id")]);
                        }
                        if (auth) {

                        } else {
                            $("#courtNo").attr("disabled", true);
                        }
                        if ($(this).attr('listtype') == 'tree2') {
                            $(this).change();
                        }
                    });
                    //法官资格证书为 “无” 时，去掉日期验证
                    $('#lawyerCert').change();
                    $('#partyOffice').change();
                    // 判断是否为院领导，是则显示班子成员
                    if (userinfo.deptOrgCode == '000') {
                        $('#bzcy').parent().parent().show();
                    } else {
                        $('#bzcy').parent().parent().hide();
                    }
                    $("#userinfo_form [otherfield]").each(function () {
                        var table_field = $(this).attr('otherfield');
                        var field = table_field.split('.')[1];
                        if (userinfo['otherfield'] != null) {
                            $(this).val(userinfo['otherfield'][field]);
                        }
                        $(this).attr("disabled", true);
                        if ($(this).hasClass("select22")) {
                            $(this).select2().select2("val", userinfo['otherfield'][field]);
                            $(this).change();
                        }
                    });
                    $.getJSON("<%=basePath%>photo/getPhotoById", {userId: userinfo.id, _: new Date()}, function (data) {
                        if (data !== null) {
                            queue.setItems([{success: true, ext: '.jpg', name: '原始照片.jpg', url: data}]);
                        } else {
                            queue.setItems([]);
                        }
                    });
                    loadDjxx(userinfo.id);

                    photoEdit = false;
                    calcAge();
                    workTotal();
                    courtTotal();
                }
            });
        }
    }

    // 监听form表单的提交按钮
    $("#saveButton").on("click", function () {
        if ($(".valid-text").length == 0) {
            if ($("#id").val() != '') {
                userCheck();
            } else {
                // 先身份验证，身份验证过了，再用户名验证
                idCardCheck();
            }
        } else {
            myForm.valid();
            if (($($(".valid-text")[0]).parent().offset().top < ($("html, body")[1].scrollTop + 600)) && ($($(".valid-text")[0]).parent().offset().top > $("html, body")[1].scrollTop)) {

            } else {
                $("html, body").animate({scrollTop: $($(".valid-text")[0]).parent().offset().top} ,500);
            }
        }
    });

    $('#lawyerCert').on('change', function () {
        if ($(this).val() == 4 || $(this).find('option:selected').text() == '无') {
            myForm.getField('userInfo.lawyerCertDate').set('pauseValid', true).clearErrors();
        } else {
            myForm.getField('userInfo.lawyerCertDate').set('pauseValid', false);
        }
    });

    $('#partyOffice').on('change', function () {
        var options = $('#partyOfficeReport').find('option:selected').removeAttr('selected').parent().find('option');
        for (var i in options) {
            if ($(this).find('option:selected').text() == $(options[i]).text()) {
                $(options[i]).attr('selected', 'selected');
                break;
            }
        }
    });

    $(".calendar").prop("readonly", true);

    function generateUserName(obj) {
        var i = $(obj).val();
        // $("#username").val(getPY($(obj).val()));
        // 改成中文登录  原方式注释大概以后还要改
        $("#username").val($(obj).val().replace(/\s/g, ""));

    }

    function getPY(Str) {
        var returnStr = "";
        var j = 0;
        var username = '';
        for (var i = 0; i < Str.length; i++) {
            if (j == 0 && Str[i].trim() != '') {
                username += Str[i].trim();
                returnStr += pinyin.getFullChars(Str[i]).toLowerCase();
                j++;
            } else if (Str[i].trim() != '') {
                username += Str[i].trim();
                returnStr += pinyin.getFullChars(Str[i]).toLowerCase().substr(0, 1);
            }
        }
        $("input[name='userInfo.fullname']").val(username);
        return returnStr;
    }

    function cleanError() {
        $(".show_info").hide();
        $(".show_icon").hide();
    }

    function epassword(oStr) {
        return oStr;
    }

    function changeSelect2Value(_this, val) {
        $(_this).select2().select2("val", val);
        $(_this).change();
    }

    function loadDjxx(userid) {
        //如果都没有信息 其他子项目都显示默认显示
        var r1 = $('#judge_level').closest(".row");
        var r2 = $('#marshal_level').closest(".row");
        var r3 = $('#helper_level').closest(".row");
        var r4 = $('#clerk_level').closest(".row");
        r1.show();
        r2.show();
        r3.show();
        r4.show();
        var f1 = false;
        var f2 = false;
        var f3 = false;
        var f4 = false;

        //法官信息
        $.post('<%=basePath%>view/userinfo/attachment2', {"otherParam.n_level_type": "1", "otherParam.n_present_info": "1", "viewName": "levelInfo", userId: userid}, function (data) {
            if (data && data.success && data.results > 0) {
                f1 = true;
                controlLevelShow();
                var dat = data.rows[0];
                $('#judge_level').val(dat['judge_level']);
                $('#judge_level_date').val(dat['d_start_date']);
            }
        });
        //法警信息
        $.post('<%=basePath%>view/userinfo/attachment2', {
            "otherParam.n_level_type": "2",
            "otherParam.n_present_info": "1",
            "viewName": "levelInfo",
            userId: userid
        }, function (data) {
            if (data && data.success && data.results > 0) {
                f2 = true;
                controlLevelShow();
                var dat = data.rows[0];
                $('#marshal_level').val(dat['marshal_level']);
                $('#marshal_level_date').val(dat['d_start_date']);
            }
        });
        //法官助理
        $.post('<%=basePath%>view/userinfo/attachment2', {
            "otherParam.n_level_type": "3",
            "otherParam.n_present_info": "1",
            "viewName": "levelInfo",
            userId: userid
        }, function (data) {
            if (data && data.success && data.results > 0) {
                f3 = true;
                controlLevelShow();
                var dat = data.rows[0];
                $('#helper_level').val(dat['helper_level']);
                $('#helper_level_date').val(dat['d_start_date']);
            }
        });
        //书记员信息
        $.post('<%=basePath%>view/userinfo/attachment2', {
            "otherParam.n_level_type": "4",
            "otherParam.n_present_info": "1",
            "viewName": "levelInfo",
            userId: userid
        }, function (data) {
            if (data && data.success && data.results > 0) {
                f4 = true;
                controlLevelShow();
                var dat = data.rows[0];
                $('#clerk_level').val(dat['clerk_level']);
                $('#clerk_level_date').val(dat['d_start_date']);
            }
        });

        //控制等级显示
        function controlLevelShow() {
            if (!f1 && !f2 && !f3 && !f4) {
                return;
            }
            if (f1) {
                r1.show();
            } else {
                r1.hide();
            }
            if (f2) {
                r2.show();
            } else {
                r2.hide();
            }
            if (f3) {
                r3.show();
            } else {
                r3.hide();
            }
            if (f4) {
                r4.show();
            } else {
                r4.hide();
            }
        }
    }

    function loadCodeListCountNum(cbo, firstLine, callback, copySelector) {
        loadCodeListbyTypeCountNum(cbo, $(cbo).attr("typeId"), $(cbo).attr("listType"), firstLine, callback, copySelector);
    }

    function loadCodeListbyTypeCountNum(cbo, typeId, listType, firstLine, callback, copySelector) {
        $.getJSON("/ums/code/codeListByType", {typeId: typeId, getParent: 1}, function (data) {
            if (firstLine) {
                $(cbo).html(firstLine);
                if (copySelector) {
                    copySelector.html(firstLine);
                }
            } else {
                $(cbo).empty();
                if (copySelector) {
                    copySelector.empty();
                }
            }
            if (data) {
                for (var i = 0; i < data.length; i++) {
                    $(cbo).append($("<option>").attr({"value": data[i].id}).text(data[i].codeName));
                    //把同样的东西赋予另外一个jQuery对象
                    if (copySelector) {
                        copySelector.append($("<option>").attr({"value": data[i].id}).text(data[i].codeName));
                    }
                }
                if (listType) {
                    if (listType.indexOf('tree') != -1) {
                        //组装成tree需要的对象格式
                        var getJsonTree = function (data, parentid, pid) {
                            var itemArr = [];
                            for (var i = 0; i < data.length; i++) {
                                var node = data[i];
                                if (node.parentId == parentid) {
                                    var newNode = {};
                                    newNode.id = node.id;
                                    newNode.typeId = typeId;
                                    newNode.text = node.codeName;
                                    newNode.sortNo = node.sortNo;
                                    newNode.pid = pid + ',' + node.id;
                                    newNode.children = getJsonTree(data, node.id, pid + "," + node.id);
                                    itemArr.push(newNode);
                                }
                            }
                            return itemArr;
                        };
                        var treeData = getJsonTree(data, null, '-1');
                        var cboClickfunction = function (ev) {
                            var cbo = $(ev.target);
                            tree_dialog_id = cbo.attr('tree_dialog');
                            BUI.getControl('tree_dialog_' + typeId + '_' + listType).show();
                        };
                        var cboChangefunction = function (ev) {
                            var cbo = $(ev.target);
                            var txt = cbo.find('option:selected').text();
                            var span = cbo.next().children().find('.select2-selection__rendered');
                            span.text(txt).attr('title', txt);
                        };
                        var cbowidth = $(cbo).width() + 24;
                        var html = "<span class=\"select2 select2-container select2-container--default\" dir=\"ltr\" style=\"width: 166px;\"><span class=\"selection\"><span class=\"select2-selection select2-selection--single\" role=\"combobox\" aria-autocomplete=\"list\" aria-haspopup=\"true\" aria-expanded=\"false\" tabindex=\"0\" aria-labelledby=\"select2-n_reward_type-container\"><span class=\"select2-selection__rendered\" title=\"请选择\">请选择</span><span class=\"select2-selection__arrow\" role=\"presentation\"><b role=\"presentation\"></b></span></span></span><span class=\"dropdown-wrapper\" aria-hidden=\"true\"></span></span>";
                        $(cbo).on('change', cboChangefunction).after(html);
                        $(cbo).next().width(cbowidth).children().find('.select2-selection__rendered').attr('tree_dialog', BUI.guid('tree_dialog')).on('click', cboClickfunction);
                        $(cbo).hide();
                        if (copySelector) {
                            copySelector.each(function (e) {
                                var cbowidth = $(this).width() + 24;
                                $(this).on('change', cboChangefunction).after(html);
                                $(this).next().width(cbowidth).children().find('.select2-selection__rendered').attr('tree_dialog', BUI.guid('tree_dialog')).on('click', cboClickfunction);
                                $(this).hide();
                            });
                        }
                        var dialogId = 'tree_dialog_' + typeId + '_' + listType;
                        //只能选择子节点
                        if (listType.indexOf('tree') != -1) {
                            //赋值，并增加点击事件
                            BUI.use(['bui/tree', 'bui/overlay'], function (Tree, Overlay) {
                                var tree = new Tree.TreeList({
                                    id: dialogId + '_tree_id',
                                    root: {                  //由于要显示根节点，所以需要配置根节点
                                        id: '-1',
                                        text: '全部',
                                        typeId: typeId,
                                        expanded: true,
                                        children: treeData
                                    },
                                    showLine: true, //显示连接线
                                    showRoot: true
                                });
                                tree.on('itemclick', function (ev) {
                                    if ($(ev.domTarget).parent().hasClass('x-tree-icon-wraper')) return true;
                                    var item = ev.item;
                                    if (item.children && item.children.length > 0 && listType == 'tree2') {
                                        tree.toggleExpand(item);
                                        return false;
                                    } else {
                                        $('[tree_dialog=' + tree_dialog_id + ']').text(item.text).attr('title', item.text)
                                            .closest('.select2-container').prev().val(item.id).change();
                                        BUI.getControl(dialogId).close();
                                    }
                                });
                                var dialog = new Overlay.Dialog({
                                    id: dialogId,
                                    width: 350,
                                    height: 400,
                                    zIndex: 1080,
                                    buttons: [{
                                        text: '清除',
                                        elCls: 'button button-primary',
                                        handler: function () {
                                            var span = $('[tree_dialog=' + tree_dialog_id + ']');
                                            var cbo = span.closest('.select2-container').prev();
                                            var firstline = cbo.find("option:eq(0)");
                                            span.text(firstline.text()).attr('title', firstline.text());
                                            cbo.val(firstline.val());
                                            this.close();
                                        }
                                    }],
                                    elCls: 'custom-dialog',
                                    children: [tree]
                                });
                                dialog.on('show', function (ev) {
                                    $('.custom-dialog').find('.bui-stdmod-body').css('overflow', 'auto')
                                        .find('.bui-tree-list').css('border', 'none');

                                    var tree = this.getChild(this.get('id') + '_tree_id');
                                    var span = $('[tree_dialog=' + tree_dialog_id + ']');
                                    var cbo = span.closest('.select2-container').prev();
                                    cbo.parent().find('.valid-text').remove();

                                    var id = cbo.find('option:selected').val();
                                    var text = cbo.find('option:selected').text();
                                    span.text(text).attr('title', text);
                                    if (id) {
                                        var itemCurrent = tree.findNode(id);
                                        tree.expandPath(itemCurrent.pid);
                                        tree.setSelected(itemCurrent);
                                    }
                                });
                            });
                        }
                    } else if (listType == 'select2') {
                        var firstOptionValue = $(cbo).find("option:eq(0)").val();
                        $(cbo).select2().select2("val", firstOptionValue);
                        $(cbo).change();
                        if (copySelector) {
                            firstOptionValue = copySelector.find("option:eq(0)").val();
                            copySelector.select2().select2("val", firstOptionValue);
                            copySelector.change();
                        }
                    }
                }
            }
            if (callback) {
                callback();
            }
            // ajax成功了，successCount就+1
            successCount++;
        });
    }

    //-------------计算日期相关函数Start---------------
    function calcAge() {
        $("#userinfo_form #age").val(calcYears($("#userinfo_form #birthday").val()) + '岁');
    }

    function workTotal() {
        $("#userinfo_form #totalSeniority").text(workTotalYears($("#userinfo_form #workDate").val(), $("#userinfo_form #extraSeniority").val(), $("#userinfo_form #deductionSeniority").val()) + '年');
    }

    function courtTotal() {
        $("#userinfo_form #totalCourtYear").text(workTotalYears($("#userinfo_form #enterDate").val(), $("#userinfo_form #beforeCourtWorkYear").val()) + '年');
    }

    // 用户名验证
    function userCheck() {
        var url = "<%=path%>/checkUserName";
        var datas = {courtStdNo: $("#courtNo").val(), username: $("#username").val(), id: $("#id").val(), valid: 1, userType: 1, isEdit: isEdit};
        myForm.valid();
        if (myForm.isValid()) {

            //如果状态是启用 那么不能够填写 调离原因 、调离去向、调离日期
            if( $("#leaveReason").val()){

                var showStr = "调离原因、调离去向、调离日期请在停用人员后进行填写   ";

                BUI.Message.Alert(showStr, "warning");
                return;
            }
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