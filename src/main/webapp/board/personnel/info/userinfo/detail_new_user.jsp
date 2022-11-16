<%--
    Document   : detail_user
    Created on : 2014-12-25, 15:34:57
    Author     : Diluka
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String autoPrint = request.getParameter("autoPrint");
    String id = request.getParameter("id");
%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>人员详细信息</title>
    <jsp:include page="/basic_import.jsp"></jsp:include>
    <script src="<%=basePath%>js/common/codelist.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/common/datetools.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/common/idchecker.js" type="text/javascript"></script>
    <style type="text/css">

        #userinfo_form input {
            background: transparent;
            border: 1px solid #ffffff;
            box-shadow: none;
        }

        #userinfo_form select {
            background: transparent;
            border: 1px solid #ffffff;
            -webkit-appearance: none;
            box-shadow: none;
        }
        .checkbox{
            font-size: 16px;
            margin: 20px 20px 20px 50px;
        }
        .radio{
            font-size: 16px;
            margin: 7px 10px 7px 50px;
        }

    </style>
</head>

<body>
<SCRIPT language=javascript>

    function printpage(isQ) {
        //判断是否自动打印
        if (autoPrint != "null" && autoPrint != "") {
            autoPrintCount++;
            if (autoPrintCount >= 2) {
                $(".nav-tabs").hide();
                $("#button_print").hide();

                //判断是否立即打印
                if (isQ == 1) {
                    window.print();
                    $(".nav-tabs").show();
                    $("#button_print").show();
                } else {
                    setTimeout(function () {
                        window.print();
                        $(".nav-tabs").show();
                        $("#button_print").show();
                    }, 1000);
                }


            }
        }
    }

    $(function () {
        $(window).on("scroll", function (e) {
            if ($(window).scrollTop() > $('.nav-tabs')[0].offsetTop) {
                $('.nav-tabs').css("position", "fixed");
            } else {
                $('.nav-tabs').css('position', 'inherit');
            }
        });
    });

    function isOpenPrint() {
        if (window.location.href.indexOf("autoPrint") != -1) {
            printpage(1);
        } else {
            window.open("<%=basePath%>/view/basicinfo_new?id=<%=id%>&autoPrint=1");
        }
    }

    function printCP() {
        $(".nav-tabs").hide();
        WebBrowser1.ExecWB(7, 1);
        $(".nav-tabs").show();
    }
</SCRIPT>

<DIV align=center>
    <INPUT onclick=javascript:isOpenPrint() type=button value="打印个人信息" id=button_print/>
</DIV>

<ul class="nav-tabs" style="z-index:2000;background-color: white;width:100%;top:0">
    <li class="active"><a href="#basic-info" onclick="changeMarkers(this)" class="initClass">基本信息</a></li>
    <li><a href="#card-info" onclick="changeMarkers(this)">证件信息</a></li>
    <li><a href="#education-info" onclick="changeMarkers(this)">工作信息</a></li>
    <li><a href="#dispatch-info" onclick="changeMarkers(this)">调遣信息</a></li>
    <li><a href="#position-info" onclick="changeMarkers(this)">职务信息</a></li>
    <li><a href="#education-info" onclick="changeMarkers(this)">教育经历</a></li>
    <li><a href="#user-status" onclick="changeMarkers(this)">身份信息</a></li>
</ul>

<form id="userinfo_form" class="form-horizontal" action="<%=basePath%>userinfo/save" method="post">

    <%--基本信息--%>
    <div id="basic-info">

        <hr>
        <h3>基本信息</h3>

        <div class="row" style="height: 48px;">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>姓名：</label>

                <div class="controls">
                    <input id="fullname" name="userInfo.fullname" type="text" value="" class="control-text"
                           data-rules="{required:true}">
                </div>
            </div>

            <div class="control-group span8">
                <label class="control-label"><s>*</s>法院：</label>

                <div class="controls">
                    <select id="courtNo" typeId="1" class="code" name="userInfo.courtNo"
                            onchange="loadDeptList(department, this.value, '<option value=\'\'>请选择</option>')"
                            data-rules="{required:true}"></select>
                </div>
            </div>
            <div style="display: inline-block;margin-left: 60px;">
                <img id="photo" height="200">

            </div>
        </div>
        <div class="row">

            <div class="control-group span8">
                <label class="control-label"><s>*</s>部门：</label>

                <div class="controls">
                    <select id="department" courtNo="" data-rules="{required:true}" name="userInfo.department">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">曾用名：</label>

                <div class="controls">
                    <input id="formerName" name="userInfo.formerName" type="text" value="" class="control-text">
                </div>
            </div>

        </div>
        <div class="row">

            <div class="control-group span8">
                <label class="control-label"><s>*</s>性别：</label>

                <div class="controls">
                    <select id="gender" typeId="3" class="code" data-rules="{required:true}"
                            name="userInfo.gender"></select>
                </div>
            </div>

            <div class="control-group span8">
                <label class="control-label"><s>*</s>婚姻状况：</label>

                <div class="controls">
                    <select id="maritalStatus" typeId="6" class="code" data-rules="{required:true}"
                            name="userInfo.maritalStatus"></select>
                </div>
            </div>

        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>民族：</label>

                <div class="controls">
                    <select id="nation" name="userInfo.nation" typeId="5" class="code"
                            data-rules="{required:true}"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>民族(最高院)：</label>

                <div class="controls">
                    <select id="nationReport" name="userInfo.nationReport" typeId="1005" class="code select22"
                            data-rules="{required:true}">
                    </select>

                </div>
            </div>
        </div>

        <div class="row">

            <div class="control-group span8">
                <label class="control-label"><s>*</s>出生地：</label>

                <div class="controls">
                    <input id="birthplace" name="userInfo.birthplace" type="text" value="" class="control-text">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>籍贯：</label>

                <div class="controls">
                    <input id="hometown" name="userInfo.hometown" type="text" value="" class="control-text"
                           data-rules="{required:true}">
                </div>
            </div>

        </div>

        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>身份证号：</label>

                <div class="controls">
                    <input id="idcard" name="userInfo.idcard" type="text" class="span4 span-width spancontrol-text"
                           data-rules="{required:true,idcheck:null}">
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label"><s>*</s>出生日期：</label>

                <div class="controls">
                    <input id="birthday" name="userInfo.birthday" type="text" class="calendar"
                           data-rules="{required:true}" onchange="calcAge()">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>年龄：</label>

                <div class="controls">
                    <label class="control-label" style="text-align: left" id="age"></label>
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

            <div class="control-group span10">
                <label class="control-label"><s>*</s>用户名：</label>

                <div class="controls">
                    <input id="username" name="userInfo.username" type="text"
                           class="control-text" data-rules="{required:true}">
                </div>
            </div>
        </div>
        <div class="row">

            <div class="control-group span8">
                <label class="control-label"><s>*</s>人员分类：</label>

                <div class="controls">
                    <select id="personnelClassification" typeId="94" class="code"
                            name="userInfo.personnelClassification"></select>
                </div>
            </div>

            <div class="control-group12 span8">
                <label class="control-label"><s>*</s>工作岗位：</label>

                <div class="controls">
                    <select id="job" typeId="93" class="code" name="userInfo.job" disabled></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>岗位性质：</label>

                <div class="controls">
                    <select id="positionNature" typeId="64" class="code select22"
                            name="userInfo.positionNature" disabled></select>
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
                <label class="control-label"><s>*</s>法官资格日期：</label>

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
        <hr/>
        <h3>职务信息</h3>

        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>政治面貌：</label>

                <div class="controls">
                    <select id="political" typeId="13" class="code" name="userInfo.political"></select>
                </div>
            </div>
            <%--<div class="control-group span8">--%>
            <%--<label class="control-label"><s>*</s>政治面貌(最高院)：</label>--%>

            <%--<div class="controls">--%>
            <%--<select id="politicalReport" typeId="1013" class="code" name="userInfo.politicalReport" disabled></select>--%>
            <%--</div>--%>
            <%--</div>--%>
            <div class="control-group12 span8">
                <label class="control-label"><s>*</s>加入日期：</label>

                <div class="controls">
                    <input id="politicalDate" name="userInfo.politicalDate" type="text" class="calendar">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>法律职务：</label>

                <div class="controls">
                    <select id="lawPosition" typeId="16" class="code" name="userInfo.lawPosition"></select>
                </div>
            </div>
            <%--<div class="control-group span8">--%>
            <%--<label class="control-label">法律职务(最高院)：</label>--%>

            <%--<div class="controls">--%>
            <%--<select id="lawPositionReport" typeId="1016" class="code" name="userInfo.lawPositionReport" disabled></select>--%>
            <%--</div>--%>
            <%--</div>--%>
            <div class="control-group12 span8">
                <label class="control-label"><s>*</s>任职日期：</label>

                <div class="controls">
                    <input id="lawPositionDate" name="userInfo.lawPositionDate" type="text" class="calendar">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>行政职务：</label>

                <div class="controls">
                    <select id="administrationPosition" typeId="15" class="code"
                            name="userInfo.administrationPosition"></select>
                </div>
            </div>
            <%--<div class="control-group span8">--%>
            <%--<label class="control-label">行政职务(最高院)：</label>--%>

            <%--<div class="controls">--%>
            <%--<select id="administrationPositionReport" typeId="1015" class="code"--%>
            <%--name="userInfo.administrationPositionReport" disabled></select>--%>
            <%--</div>--%>
            <%--</div>--%>
            <div class="control-group12 span8">
                <label class="control-label"><s>*</s>任职日期：</label>

                <div class="controls">
                    <input id="administrationPositionDate" name="userInfo.administrationPositionDate"
                           type="text" class="calendar">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>职级：</label>

                <div class="controls">
                    <select id="rank" typeId="17" class="code" name="userInfo.rank"></select>
                </div>
            </div>
            <%--<div class="control-group span8">--%>
            <%--<label class="control-label">职级类型(最高院)：</label>--%>

            <%--<div class="controls">--%>
            <%--<select id="rankReport" typeId="1017" class="code" name="userInfo.rankReport" disabled></select>--%>
            <%--</div>--%>
            <%--</div>--%>
            <div class="control-group12 span8">
                <label class="control-label"><s>*</s>职级日期：</label>

                <div class="controls">
                    <input id="rankDate" name="userInfo.rankDate" type="text" class="calendar">
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
                    <select id="servantLevel" typeId="83" class="code" name="userInfo.servantLevel"></select>
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label"><s>*</s>级别起算日期：</label>

                <div class="controls">
                    <input id="servantLevelDate" name="userInfo.servantLevelDate" type="text" class="calendar">
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
                <label class="control-label">辅助人员等级：</label>
                <div class="controls">
                    <select id="helper_level" typeId="105" class="code field" otherfield="levelInfo.helper_level"></select>
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label"><s>*</s>辅助人员等级日期：</label>
                <div class="controls">
                    <input type="text" disabled otherfield="levelInfo.d_start_date" placeholder="辅助人员等级日期">
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
        <hr/>
        <h3>教育经历</h3>

        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>学历：</label>

                <div class="controls">
                    <select id="educationBackground" typeId="11" class="code"
                            name="userInfo.educationBackground"></select>
                </div>
            </div>
            <%--<div class="control-group span8">--%>
            <%--<label class="control-label">学历(最高院)：</label>--%>

            <%--<div class="controls">--%>
            <%--<select id="educationBackgroundReport" typeId="1011" class="code"--%>
            <%--name="userInfo.educationBackgroundReport" disabled></select>--%>
            <%--</div>--%>
            <%--</div>--%>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>学位：</label>

                <div class="controls">
                    <select id="degree" typeId="23" class="code" name="userInfo.degree"></select>
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label"><s>*</s>获得学位日期：</label>

                <div class="controls">
                    <input id="degreeDate" name="userInfo.degreeDate" type="text" class="calendar">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>专业：</label>

                <div class="controls">
                    <select id="major" typeId="12" class="code" name="userInfo.major"></select>
                </div>
            </div>
        </div>
    </div>

    <%--证件信息--%>
    <div id="card-info">

        <hr/>
        <h3>证件信息</h3>

        <select name="userInfo.userType" class="hide">
            <option value="3">人民陪审员</option>
        </select>

        <div class="row">
            <div class="control-group span8">
                <label class="control-label">UK编号：</label>

                <div class="controls">
                    <input id="ukNo" name="userInfo.ukNo" type="text" class="control-text" placeholder="UK编号"
                           data-rules="{required:true}">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">工作证编号：</label>

                <div class="controls">
                    <input id="workNo" name="userInfo.workNo" type="text" class="control-text" placeholder="工作证编号"
                           data-rules="{required:true}">
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label">饭卡编号：</label>

                <div class="controls">
                    <input id="fankaNo" name="userInfo.fankaNo" type="text" class="control-text" placeholder="饭卡编号"
                           data-rules="{required:true}">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label">执行公务证编号：</label>

                <div class="controls">
                    <input id="officialNo" name="userInfo.officialNo" type="text" class="control-text"
                           placeholder="执行公务证编号" data-rules="{required:true}">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>法官资格证书类型：</label>

                <div class="controls">
                    <select id="lawyerCert" typeId="86" class="code" name="userInfo.lawyerCert"></select>
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label"><s>*</s>证书取得时间：</label>

                <div class="controls">
                    <input id="lawyerCertDate" name="userInfo.lawyerCertDate" type="text" class="calendar">
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
                    <input id="workDate" name="userInfo.workDate" type="text" class="calendar "
                           data-rules="{required:true,datecheck1:null}"
                           onchange="workTotal()" placeholder="工作日期">
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label"><s>*</s>政法工作日期：</label>

                <div class="controls">
                    <input id="politicLawWorkDate" name="userInfo.politicLawWorkDate" type="text"
                           class="calendar " placeholder="政法工作日期" data-rules="{datecheck1:null}">
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label"><s>*</s>进本院日期：</label>

                <div class="controls">
                    <input id="enterDate" name="userInfo.enterDate" type="text" class="calendar "
                           data-rules="{required:true,datecheck1:null}" onchange="courtTotal()" placeholder="进院日期">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>补充工龄：</label>

                <div class="controls">
                    <input id="extraSeniority" name="userInfo.extraSeniority" type="text"
                           class="control-text input-small" onchange="workTotal()"
                           data-rules="{required:true,number:true,min:0}" placeholder="补充工龄">年
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>扣减工龄：</label>

                <div class="controls">
                    <input id="deductionSeniority" name="userInfo.deductionSeniority" type="text"
                           class="control-text input-small" onchange="workTotal()"
                           data-rules="{required:true,number:true,min:0}" placeholder="扣减工龄">年
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>应加学制：</label>

                <div class="controls">
                    <input id="additionalDuration" name="userInfo.additionalDuration" type="text"
                           class="control-text input-small" data-rules="{required:true,number:true,min:0}"
                           placeholder="应加学制">年
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>入院前工作年限：</label>

                <div class="controls">
                    <input id="beforeCourtWorkYear" name="userInfo.beforeCourtWorkYear" type="text"
                           class="control-text input-small" onchange="courtTotal()"
                           data-rules="{required:true,number:true,min:0}" placeholder="入院前工作年限">年
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
                    <select id="enterWay" typeId="43" class="code" data-rules="{required:true}"
                            name="userInfo.enterWay"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>进入来源：</label>

                <div class="controls">
                    <select id="enterSource" typeId="114" class="code" data-rules="{required:true}"
                            name="userInfo.enterSource"></select>
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label"><s>*</s>审核日期：</label>

                <div class="controls">
                    <input id="verifyDate" name="userInfo.verifyDate" type="text" class="calendar "
                           placeholder="审核日期" data-rules="{datecheck1:null}">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>原职务：</label>
                <div class="controls">
                    <select id="formerPost" typeId="14" class="code select22" name="userInfo.formerPost"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>原职级：</label>
                <div class="controls">
                    <select id="formerRank" typeId="17" class="code select22" name="userInfo.formerRank"></select>
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label"><s>*</s>原单位：</label>

                <div class="controls">
                    <input id="formerUnit" name="userInfo.formerUnit" type="text" value="" class="control-text"
                           placeholder="原单位">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label">调离原因：</label>

                <div class="controls">
                    <select id="leaveReason" typeId="45" class="code leavecode" name="userInfo.leaveReason"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">调离去向：</label>

                <div class="controls">
                    <select id="leaveDestination" typeId="46" class="code"
                            name="userInfo.leaveDestination"></select>
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label">调离日期：</label>

                <div class="controls">
                    <input id="leaveDate" name="userInfo.leaveDate" type="text" class="calendar "
                           placeholder="调离日期" data-rules="{datecheck1:null}">
                </div>
            </div>
        </div>
    </div>

    <%--身份信息--%>
    <div id="user-status">

        <hr/>
        <h3>身份信息</h3>

        <div class="row">
            <div class="control-group span24">
                <label class="control-label">人员身份：</label>

                <div class="controls" id="userStatus" >
<%--                    <input name="userInfo.userStatus" type="checkbox" class="control-text" placeholder="人员身份"--%>
<%--                           data-rules="{required:true}">--%>
                </div>
            </div>
        </div>
    </div>


    <%--考核类型--%>
    <div id="assess-type">

        <hr/>
        <h3>考核类型</h3>

        <div class="row">
            <div class="control-group span24">
                <label class="control-label">考核类型：</label>

                <div class="controls" id="assessType" >
<%--                    <input name="userInfo.userStatus" type="checkbox" class="control-text" placeholder="人员身份"--%>
<%--                           data-rules="{required:true}">--%>
                </div>
            </div>
        </div>
    </div>


</form>

<div id="fgImg" style="z-index: 3000" class="hide">
    <img src="<%=basePath%>/images/judgeImg.png" style="position:absolute;top:0;right:0;border:0;z-index:3100;width:152px"/>
</div>


<script type="text/javascript">

    // 加载的typeId数量
    var count = 0;

    // 所有input框不可选
    $("#userinfo_form :input").prop("disabled", true);

    // 加载所有select下拉框
    $("select.code").each(function () {
        var id= $(this).attr("id");
        var typeId = $(this).attr("typeid");
        loadOptionByTypeId(id, typeId, '<option value=""></option>');
    });
    //人员身份
    $.getJSON("/ums/code/codeListByType", {typeId: 10007}, function (data) {
        var html = '';
        for (var i = 0; i < data.length; i++) {
            userStatus[data[i].id] = data[i].codeName;
            html += "<input id='checkbox"+data[i].id+"' value='"+data[i].id+"'type='checkbox' name='userStatus' class='checkbox' disabled/><label for='checkbox"+data[i].id+"'>"+data[i].codeName+"</label>"
        }
        $("#userStatus").html(html);
    });
    //考核类型
    $.getJSON("/ums/code/codeListByType", {typeId: 10008}, function (data) {
        var html = '';
        for (var i = 0; i < data.length; i++) {
            html += "<input id='checkbox"+data[i].id+"' value='"+data[i].id+"'type='radio' name='assessType' class='radio' disabled/><label for='checkbox"+data[i].id+"'>"+data[i].codeName+"</label>"
        }
        $("#assessType").html(html);
    });

    // 定时器：一秒执行一次，满足条件的时候执行下面的 在加载完所有select下拉框后再加载个人信息
    var clock = setInterval(countDown, 500);

    function countDown() {
        if (count == $("select.code").length) {
            // 清除js定时器
            clearInterval(clock);
            // 等所有下拉框加载完，再加载后面的数据
            // 查询所有法院 与权限 限制无关
            $.getJSON("/ums/code/codeListByTypeWithNoAspect", {}, function (data) {
                if ($("#userinfo_form select[id='courtNo']").length > 0) {
                    $("#userinfo_form select[id='courtNo']").html("");
                    for (var i = 0; i < data.data.length; i++) {
                        $("#userinfo_form select[id='courtNo']").append($("<option>").attr({"value": data.data[i].courtCode}).text(data.data[i].codeName));
                    }
                }
            });

            // 当前人员的userid
            var obj = {id: "<s:property value="id"></s:property>", _: new Date()};

            // 根据userid获取人员基本信息
            $.ajax({
                url: "<%=basePath%>view/userinfoById",
                data: obj,
                dataType: "json",
                async: false,
                success: function (data) {
                    $("#userinfo_form :input").each(function () {

                        if (data[$(this).attr("id")] != null) {

                            // 部门
                            if ($(this).attr("id") === "department") {
                                return true;
                            }

                            if ($(this).attr("id") === "courtNo") {
                                // 法院
                                loadCourts("#courtNo", '<option>请选择</option>', function () {
                                    $("#courtNo").val(data["courtNo"]);
                                    loadDeptList("#department", data["courtNo"], '<option>请选择</option>', function () {
                                        $("#department").val(data["department"]);
                                    });
                                });
                            } else if ($(this).attr("id") === "yefg") {
                                // 员额法官
                                var yefg = data[$(this).attr("id")];
                                if (yefg == 1) {
                                    $("#fgImg").removeClass("hide");
                                }
                                $(this).val(data[$(this).attr("id")]);
                            }  else {
                                $(this).val(data[$(this).attr("id")]);
                            }
                        }
                    });
                    //人员身份信息
                    var userStatus = data.userStatus;
                    for (var i = 0; i < userStatus.length; i++){
                        $("#userStatus input[id='checkbox"+userStatus[i].statusCode+"']").attr("checked","true");
                    }
                    //考核类型
                    var assessType = data.assessType;
                    $("#assessType input[value='"+assessType+"']").attr("checked","true");


                    // 判断是否为院领导，是则显示班子成员
                    if (data.deptOrgCode == '000') {
                        $('#bzcy').parent().parent().show();
                    }

                    // 辅助人员等级信息
                    $("#userinfo_form [otherfield]").each(function () {
                        var table_field = $(this).attr('otherfield');
                        var field = table_field.split('.')[1];
                        if (data['otherfield'] != null) {
                            $(this).val(data['otherfield'][field]);
                        }
                        $(this).attr("disabled", true);
                        if ($(this).hasClass("select22")) {
                            $(this).select2().select2("val", data['otherfield'][field]);
                            $(this).change();
                        }
                    });

                    // 计算年龄
                    var nowYear = new Date().getFullYear();
                    var age = (nowYear - new Date(data["birthday"]).getFullYear());
                    var totalSeniority = nowYear - new Date(data["workDate"]).getFullYear() + data["extraSeniority"] - data["deductionSeniority"];
                    var totalCourtYear = nowYear - new Date(data["enterDate"]).getFullYear() + data["beforeCourtWorkYear"];
                    $("#age").text(age);
                    $("#totalSeniority").text(totalSeniority);
                    $("#totalCourtYear").text(totalCourtYear);

                    // 获取照片
                    $.getJSON("<%=basePath%>photo/getPhotoById", {userId: data["id"]}, function (photo) {
                        $("#photo").attr({"src": photo});
                        //判断是否自动打印
                        printpage();
                    });

                    // 判断是否自动打印
                    printpage();
                }
            });

        }
    }
    // 点击后页面定位
    function changeMarkers(obj) {
        $(obj).parent().parent().find(".active").removeClass("active");
        $(obj).parent().addClass("active");
        var hr = $(obj).attr("href");
        var anh = $(hr).offset().top - 40;
        $("html,body").stop().animate({scrollTop: anh}, 800);
    }

    var autoPrint = "<%=autoPrint%>";
    var autoPrintCount = 0;
    var userid = '<%=id%>'

    // 加载附加信息
    loadDjxx(userid);

    // 加载附加信息
    function loadDjxx(userid) {
        // 如果都没有信息 其他子项目都显示默认显示
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

        // 法官信息
        $.post('<%=basePath%>view/userinfo/attachment2', {"otherParam.n_level_type": "1", "otherParam.n_present_info": "1", "viewName": "levelInfo", userId: userid}, function (data) {
            if (data && data.success && data.results > 0) {
                f1 = true;
                controlLevelShow();
                var dat = data.rows[0];
                $('#judge_level').val(dat['judge_level']);
                $('#judge_level_date').val(dat['d_start_date']);
            }
        });

        // 法警信息
        $.post('<%=basePath%>view/userinfo/attachment2', {"otherParam.n_level_type": "2", "otherParam.n_present_info": "1", "viewName": "levelInfo", userId: userid}, function (data) {
            if (data && data.success && data.results > 0) {
                f2 = true;
                controlLevelShow();
                var dat = data.rows[0];
                $('#marshal_level').val(dat['marshal_level']);
                $('#marshal_level_date').val(dat['d_start_date']);
            }
        });

        // 法官助理
        $.post('<%=basePath%>view/userinfo/attachment2', {"otherParam.n_level_type": "3", "otherParam.n_present_info": "1", "viewName": "levelInfo", userId: userid}, function (data) {
            if (data && data.success && data.results > 0) {
                f3 = true;
                controlLevelShow();
                var dat = data.rows[0];
                $('#helper_level').val(dat['helper_level']);
                $('#helper_level_date').val(dat['d_start_date']);
            }
        });

        //书记员信息
        $.post('<%=basePath%>view/userinfo/attachment2', {"otherParam.n_level_type": "4", "otherParam.n_present_info": "1", "viewName": "levelInfo", userId: userid}, function (data) {
            if (data && data.success && data.results > 0) {
                f4 = true;
                controlLevelShow();
                var dat = data.rows[0];
                $('#clerk_level').val(dat['clerk_level']);
                $('#clerk_level_date').val(dat['d_start_date']);
            }
        });

        // 控制等级显示
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

    // 根据typeid加载select下拉选项
    function loadOptionByTypeId(id, typeId, firstOption) {
        $.ajax({
            url: "<%=basePath%>code/codeListByType",
            data: {typeId: typeId, getParent: 1},
            dataType: "json",
            async: true,
            success: function (data) {
                var option = firstOption;
                for (var i = 0; i < data.length; i++) {
                    option += '<option value="' + data[i].id + '">' + data[i].codeName + '</option>';
                }
                $("#" + id).append(option);
                count++;
            }
        });
    }

</script>

</body>
</html>
