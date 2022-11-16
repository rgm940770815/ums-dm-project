<%--
    Document   : detail_user
    Created on : 2014-12-25, 15:34:57
    Author     : Diluka
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";



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
    <style media=print>
        /* 应用这个样式的在打印时隐藏 */
        .noPrint {
            display: none;;
        }

        .clearMP{
            padding: 0!important;
            margin-bottom: 6px!important;
            margin-top: 6px!important;
        }

        /* 应用这个样式的，从那个标签结束开始另算一页，之后在遇到再起一页，以此类推 */
        /*.page {*/
            /*page-break-after: always;*/
        /*}*/

        @page {
            margin: 10mm 8mm 8mm 5mm;
            size: A4;
        }

        #userinfo_form{
            font-size: 10px;
        }

        #userinfo_form  #photo{
            height: 180px;
        }

        #userinfo_form  .input-small{
            width: 10px;
        }

        #userinfo_form #photo{
            margin: 1em;
        }

        #userinfo_form  h3{
            margin-bottom: 0!important;
        }

        #userinfo_form.form-horizontal .controls{
            height: 25px;
            margin-left: 4px;
        }

        #userinfo_form.form-horizontal .controls input:not(.input-small){
            width: 130px;
            padding: 3px 0 0 0;
        }
        #userinfo_form.form-horizontal .controls select{
            width: 130px;
            padding: 2px 0 1px 0;
        }
        #userinfo_form.form-horizontal .controls input.large-text:not(.input-small),#userinfo_form.form-horizontal .controls select.large-text{
            padding: 0;
            width: 110px;
        }
        #userinfo_form .span8{
            width: 230px;
        }
        #userinfo_form .control-label{
            width: 90px;
            height: 25px;
        }
        #userinfo_form .control-label.large-text{
            width: 110px;
        }
    </style>

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

        #photo{
            margin: 2em;
            height: 240px;
        }



    </style>



</head>
<body>
<SCRIPT language=javascript>
    function printpage(isQ){
        //判断是否自动打印
        if(autoPrint != "null" && autoPrint != ""){
            autoPrintCount++;
            if(autoPrintCount >= 2){
                $(".nav-tabs").hide();
                $("#button_print").hide();

                //判断是否立即打印
                if(isQ == 1){
                    window.print();
                    $(".nav-tabs").show();
                    $("#button_print").show();
                }else{
                    setTimeout(function(){
                        window.print();
                        $(".nav-tabs").show();
                        $("#button_print").show();
                    },1000);
                }


            }
        }


    }


    function isOpenPrint(){
        if(window.location.href.indexOf("autoPrint") != -1){
            printpage(1);
        }else{
            window.open("<%=basePath%>/view/basicinfo?id=<%=id%>&autoPrint=1");
        }
    }

    function printCP(){
        $(".nav-tabs").hide();
        WebBrowser1.ExecWB(7,1);
        $(".nav-tabs").show();
    }
</SCRIPT>
<div style="margin-top: 40px" class="noPrint">

</div>
<div align="center" class="noPrint" >
    <INPUT onclick=javascript:isOpenPrint() type=button value="打印个人信息" id=button_print />
    <%--<INPUT onclick=javascript:printview() type=button value=打印预览 name=button_print />--%>
</div>
<ul class="nav-tabs noPrint" style="position:fixed;z-index:2000;background-color: white;width:100%">
    <li class="active"><a href="#basic-info" onclick="changeMarkers(this)" class="initClass">基本信息</a></li>
    <li><a href="#card-info" onclick="changeMarkers(this)">证件信息</a></li>
    <li><a href="#position-info" onclick="changeMarkers(this)">职务信息</a></li>
    <li><a href="#education-info" onclick="changeMarkers(this)">教育经历</a></li>
    <li><a href="#education-info" onclick="changeMarkers(this)">工作信息</a></li>
    <li><a href="#dispatch-info" onclick="changeMarkers(this)">调遣信息</a></li>
</ul>
<div style="height: 40px;" class="noPrint"></div>
<form id="userinfo_form" class="form-horizontal" action="<%=basePath%>userinfo/save" method="post">
    <div class="row">
        <img id="photo">

    </div>
    <%--基本信息--%>
    <div id="basic-info">

        <hr class="clearMP">
        <h3>基本信息</h3>

        <div class="row">
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
            <div class="control-group span8">
                <label class="control-label"><s>*</s>部门：</label>

                <div class="controls">
                    <select id="department" courtNo="" data-rules="{required:true}" name="userInfo.department">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="row">

            <div class="control-group span8">
                <label class="control-label">曾用名：</label>

                <div class="controls">
                    <input id="formerName" name="userInfo.formerName" type="text" value="" class="control-text">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>性别：</label>

                <div class="controls">
                    <select id="gender" typeId="3" class="code" data-rules="{required:true}"
                            name="userInfo.gender"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>用户名：</label>

                <div class="controls">
                    <input id="username" name="userInfo.username" type="text"
                           class="control-text" data-rules="{required:true}">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label">编号：</label>

                <div class="controls">
                    <input id="userCode" name="userInfo.userCode" type="text" value="" class="control-text">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>民族：</label>

                <div class="controls">
                    <select id="nation" name="userInfo.nation" typeId="5" class="code"
                            data-rules="{required:true}"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">出生地：</label>

                <div class="controls">
                    <input id="birthplace" name="userInfo.birthplace" type="text" value="" class="control-text">
                </div>
            </div>

        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>籍贯：</label>

                <div class="controls">
                    <input id="hometown" name="userInfo.hometown" type="text" value="" class="control-text"
                           data-rules="{required:true}">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>婚姻状况：</label>

                <div class="controls">
                    <select id="maritalStatus" typeId="6" class="code" data-rules="{required:true}"
                            name="userInfo.maritalStatus"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>健康状况：</label>

                <div class="controls">
                    <select id="physicalCondition" typeId="10" class="code" data-rules="{required:true}"
                            name="userInfo.physicalCondition"></select>
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
                <label class="control-label">年龄：</label>

                <div class="controls">
                    <label class="control-label" style="text-align: left" id="age"></label>
                </div>
            </div>

        </div>
    </div>

    <%--证件信息--%>
    <div id="card-info">

        <hr class="clearMP"/>
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
                <label class="control-label large-text">执行公务证编号：</label>

                <div class="controls">
                    <input id="officialNo" name="userInfo.officialNo" type="text" class="control-text large-text"
                           placeholder="公务证编号" data-rules="{required:true}">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">资格证书类型：</label>

                <div class="controls">
                    <select id="lawyerCert" typeId="86" class="code" name="userInfo.lawyerCert"></select>
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label">证书取得时间：</label>

                <div class="controls">
                    <input id="lawyerCertDate" name="userInfo.lawyerCertDate" type="text" class="calendar">
                </div>
            </div>
        </div>
    </div>


    <%--职务信息--%>
    <div id="position-info">
        <hr class="clearMP"/>
        <h3>职务信息</h3>

        <div class="row">
            <div class="control-group span8">
                <label class="control-label">职务类别：</label>

                <div class="controls">
                    <select id="positionType" name="userInfo.positionType" typeId="62"
                            class="code"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">职务类别日期：</label>

                <div class="controls">
                    <input id="positionTypeDate" name="userInfo.positionTypeDate" type="text" class="calendar">
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
            <div class="control-group span8">
                <label class="control-label">政治面貌：</label>

                <div class="controls">
                    <select id="political" typeId="13" class="code" name="userInfo.political"></select>
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label">加入日期：</label>

                <div class="controls">
                    <input id="politicalDate" name="userInfo.politicalDate" type="text" class="calendar">
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label">员额法官：</label>
                <div class="controls">
                    <select id="yefg" name="userInfo.yefg">
                        <option value="0">否</option>
                        <option value="1">是</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label">法律职务：</label>

                <div class="controls">
                    <select id="lawPosition" typeId="16" class="code" name="userInfo.lawPosition"></select>
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label">任职日期：</label>

                <div class="controls">
                    <input id="lawPositionDate" name="userInfo.lawPositionDate" type="text" class="calendar">
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label">法官资格日期：</label>

                <div class="controls">
                    <input id="lawyerDate" name="userInfo.lawyerDate" type="text" class="calendar">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group12 span8">
                <label class="control-label">兼任庭长：</label>

                <div class="controls">
                    <select id="isParttimePresidingJudge" name="userInfo.isParttimePresidingJudge" typeId="68"
                            class="code"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">党组职务：</label>

                <div class="controls">
                    <select id="partyOffice" typeId="57" class="code" name="userInfo.partyOffice"></select>
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label">党组职务日期：</label>

                <div class="controls">
                    <input id="partyOfficeDate" name="userInfo.partyOfficeDate" type="text" class="calendar">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label">行政职务：</label>

                <div class="controls">
                    <select id="administrationPosition" typeId="15" class="code"
                            name="userInfo.administrationPosition"></select>
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label">任职日期：</label>

                <div class="controls">
                    <input id="administrationPositionDate" name="userInfo.administrationPositionDate"
                           type="text" class="calendar">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">辅助人员等级：</label>

                <div class="controls">
                    <select id="helper_level" typeId="105" class="code field" name="otherfield.helper_level"></select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label">职级类型：</label>

                <div class="controls">
                    <select id="rank" typeId="17" class="code" name="userInfo.rank"></select>
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label">职级日期：</label>

                <div class="controls">
                    <input id="rankDate" name="userInfo.rankDate" type="text" class="calendar">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">所属编制：</label>

                <div class="controls">
                    <select id="preparation" typeId="9" class="code" name="userInfo.preparation"></select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label">等级：</label>

                <div class="controls">
                    <select id="level" typeId="20" class="code" name="userInfo.level"></select>
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label">等级日期：</label>

                <div class="controls">
                    <input id="levelDate" name="userInfo.levelDate" type="text" class="calendar">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">人员分类：</label>

                <div class="controls">
                    <select id="personnelClassification" typeId="94" class="code" name="userInfo.personnelClassification"></select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label">公务员级别：</label>

                <div class="controls">
                    <select id="servantLevel" typeId="83" class="code" name="userInfo.servantLevel"></select>
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label">级别起算日期：</label>

                <div class="controls">
                    <input id="servantLevelDate" name="userInfo.servantLevelDate" type="text" class="calendar">
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label">工作岗位：</label>

                <div class="controls">
                    <select id="job" typeId="93" class="code" name="userInfo.job"></select>
                </div>
            </div>
        </div>
    </div>

    <%--教育信息--%>
    <div id="education-info">
        <hr class="clearMP"/>
        <h3>教育经历</h3>

        <div class="row">
            <div class="control-group span8">
                <label class="control-label">学历：</label>

                <div class="controls">
                    <select id="educationBackground" typeId="11" class="code"
                            name="userInfo.educationBackground"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">学位：</label>

                <div class="controls">
                    <select id="degree" typeId="23" class="code" name="userInfo.degree"></select>
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label">获得学位日期：</label>

                <div class="controls">
                    <input id="degreeDate" name="userInfo.degreeDate" type="text" class="calendar">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label">专业：</label>

                <div class="controls">
                    <select id="major" typeId="12" class="code" name="userInfo.major"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">专业证书：</label>

                <div class="controls">
                    <select id="proCert" typeId="47" class="code" name="userInfo.proCert"></select>
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label">获得证书日期：</label>

                <div class="controls">
                    <input id="proCertDate" name="userInfo.proCertDate" type="text" class="calendar">
                </div>
            </div>
        </div>
        <%--<div class="row">--%>
        <%--<div class="control-group span8">--%>
        <%--<label class="control-label">法官资格证书类型：</label>--%>

        <%--<div class="controls">--%>
        <%--<select id="lawyerCert" typeId="86" class="code" name="userInfo.lawyerCert"></select>--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--<div class="control-group12 span8">--%>
        <%--<label class="control-label">取得时间：</label>--%>

        <%--<div class="controls">--%>
        <%--<input id="lawyerCertDate" name="userInfo.lawyerCertDate" type="text" class="calendar">--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--</div>--%>
    </div>

    <%--工作信息--%>
    <div id="education-info">
        <hr class="clearMP"/>
        <h3>工作信息</h3>
        <div class="row">
            <div class="control-group12 span8">
                <label class="control-label"><s>*</s>工作日期：</label>

                <div class="controls">
                    <input id="workDate" name="userInfo.workDate" type="text" class="calendar"
                           data-rules="{required:true}"
                           onchange="workTotal()">
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label">政法工作日期：</label>

                <div class="controls">
                    <input id="politicLawWorkDate" name="userInfo.politicLawWorkDate" type="text"
                           class="calendar">
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label"><s>*</s>进院日期：</label>

                <div class="controls">
                    <input id="enterDate" name="userInfo.enterDate" type="text" class="calendar"
                           data-rules="{required:true}" onchange="courtTotal()">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>补充工龄：</label>

                <div class="controls">
                    <input id="extraSeniority" name="userInfo.extraSeniority" type="text"
                           class="control-text input-small" onchange="workTotal()"
                           data-rules="{required:true,number:true}">年
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>扣减工龄：</label>

                <div class="controls">
                    <input id="deductionSeniority" name="userInfo.deductionSeniority" type="text"
                           class="control-text input-small" onchange="workTotal()"
                           data-rules="{required:true,number:true}">年
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>应加学制：</label>

                <div class="controls">
                    <input id="additionalDuration" name="userInfo.additionalDuration" type="text"
                           class="control-text input-small" data-rules="{required:true,number:true}">年
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8 ">
                <label class="control-label large-text"><s>*</s>入院前工作年限：</label>

                <div class="controls">
                    <input id="beforeCourtWorkYear" name="userInfo.beforeCourtWorkYear" type="text"
                           class="control-text input-small" onchange="courtTotal()"
                           data-rules="{required:true,number:true}">年
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label large-text">法院工作合计年限：</label>

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

        <hr class="clearMP"/>
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
                    <select id="enterSource" typeId="44" class="code" data-rules="{required:true}"
                            name="userInfo.enterSource"></select>
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label">审核日期：</label>

                <div class="controls">
                    <input id="verifyDate" name="userInfo.verifyDate" type="text" class="calendar">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label">原职务：</label>

                <div class="controls">
                    <select id="formerPost" typeId="14" class="code" name="userInfo.formerPost"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">原职级：</label>

                <div class="controls">
                    <select id="formerRank" typeId="17" class="code" name="userInfo.formerRank"></select>
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label">原单位：</label>

                <div class="controls">
                    <input id="formerUnit" name="userInfo.formerUnit" type="text" value="" class="control-text">
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
                    <input id="leaveDate" name="userInfo.leaveDate" type="text" class="calendar">
                </div>
            </div>
        </div>
    </div>

</form>
<div id="fgImg" style="z-index: 3000" class="hide">
    <img src="<%=basePath%>/images/judgeImg.png" style="position:absolute;top:0;right:0;border:0;z-index:3100;width:152px"/>
</div>
<%--
<div class="doc-content span24">


<div id="tab"></div>
<div id="panel">
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
--%>
</body>
<script type="text/javascript">
    $("#userinfo_form :input").prop("disabled", true);
    $("select.code").each(function () {
        loadCodeList(this, '<option value="">请选择</option>');
    });

    //查询所有法院 与权限 限制无关
    $.getJSON("/ums/code/codeListByTypeWithNoAspect",
            {},
            function (data) {
                if ($("#userinfo_form select[id='courtNo']").length > 0) {
                    $("#userinfo_form select[id='courtNo']").html("");
                    for (var i = 0; i < data.data.length; i++) {
                        $("#userinfo_form select[id='courtNo']").append($("<option>").attr({"value": data.data[i].courtCode}).text(data.data[i].codeName));
                    }
                }
            })

    var obj = {
            "fields.levelInfo": "helper_level",
            id: "<s:property value="id"></s:property>",
            _: new Date()
    };
    $.getJSON("<%=basePath%>view/userinfoById", obj, function (data) {
        $("#userinfo_form :input").each(function () {
            if (data[$(this).attr("id")] != null) {
                if ($(this).attr("id") === "department") {
                    return true;
                }

                if ($(this).attr("id") === "courtNo") {
                    loadCourts("#courtNo", '<option>请选择</option>', function () {
                        $("#courtNo").val(data["courtNo"]);
                        loadDeptList("#department", data["courtNo"], '<option>请选择</option>', function () {
                            $("#department").val(data["department"]);
                        });
                    });
                } else if($(this).attr("id") === "yefg") {
                    var yefg = data[$(this).attr("id")];
                    if(yefg == 1)
                    {
                        $("#fgImg").removeClass("hide");
                    }
                    $(this).val(data[$(this).attr("id")]);
                }
                else{
                    $(this).val(data[$(this).attr("id")]);
                }
            } else if (data['otherfield'] != null && data['otherfield'][$(this).attr("id")] != null) {
                $(this).val(data['otherfield'][$(this).attr("id")]);
            }

        });

        var nowYear = new Date().getFullYear();
        var age = (nowYear - new Date(data["birthday"]).getFullYear());
        var totalSeniority = nowYear - new Date(data["workDate"]).getFullYear() + data["extraSeniority"] - data["deductionSeniority"];
        var totalCourtYear = nowYear - new Date(data["enterDate"]).getFullYear() + data["beforeCourtWorkYear"];

        $("#age").text(age);
        $("#totalSeniority").text(totalSeniority);
        $("#totalCourtYear").text(totalCourtYear);


        $.getJSON("<%=basePath%>photo/getPhotoById", {userId: data["id"]}, function (photo) {
            $("#photo").attr({"src": photo});


            //判断是否自动打印
            printpage();
        });


        //判断是否自动打印
        printpage();
    });



    function changeMarkers(obj) {
        $(obj).parent().parent().find(".active").removeClass("active");
        $(obj).parent().addClass("active");

        var hr = $(obj).attr("href");
        var anh = $(hr).offset().top - 40;
        $("html,body").stop().animate({scrollTop: anh}, 800);
    }


    var autoPrint = "<%=autoPrint%>";
    var autoPrintCount = 0;
</script>
</html>
