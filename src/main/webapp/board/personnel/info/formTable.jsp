<%@ page import="java.util.UUID" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/21
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String useId = request.getParameter("userId");
    UUID uuid = UUID.randomUUID();
    String file = "/word/"+uuid+"/"+useId+".docx";
%>
<html>
<head>
    <title></title>
    <script src="<%=path%>/js/jquery/jquery.js" type="text/javascript"></script>
    <style type="text/css">

        .containner {
            width: 920px;
            height: 1200px;
            margin: 0 auto;
            margin-bottom: 10px;
            border: 1px solid black;
            background-color: #e0e0e0;
            /*box-shadow: 5px 5px 2px #6bc30d;*/
            /*box-shadow: 0px 0px 5px 5px #efefef;*/
            padding: 3px;
            overflow: hidden;
        }

        .wordEd:link {
            color: #35477d;
        }

        .wordEd:visited {
            color: #35477d;
        }

        .wordEd:hover {
            color: #FF6600;
            text-decoration: none;
        }

        .wordEd:active {
            color: #FF6600;
            text-decoration: none;
        }

        .containner_N {
            width: 920px;
            margin: 0 auto;
            margin-bottom: 10px;
            border: 1px solid black;
            background-color: #e0e0e0;
            /*box-shadow: 5px 5px 2px #6bc30d;*/
            /*box-shadow: 0px 0px 5px 5px #efefef;*/
            padding: 3px;
        }

        .bottom_con {
            width: 920px;
            margin: 10px auto;
            text-align: left;
            text-indent: 25px;
        }

        table {
            border-right: 1px solid black;
            border-bottom: 1px solid black;
            font-family: "Tahoma", "SimSun";
            text-align: center;
            border-spacing: 0;
            border-collapse: collapse;
            table-layout: fixed;
            font-weight: 600;
            font-size: 15px;
            word-wrap: break-word;
        }

        table td {
            border-left: 1px solid black;
            border-top: 1px solid black;
        }

        table tr {
            height: 48px;
        }

        h1 {
            letter-spacing: 10px;
            text-align: center;
            margin-top: 30px
        }

        .title {
            letter-spacing: 4px;
        }

        #index_resume div {
            display: inline-block;
        }

        .resume_time {
            width: 20%;
            font-size: 13px;

        }

        .resume_dept {
            width: 20%;
            font-size: 13px;

        }

        .resume_info {
            text-align: justify;
            text-indent: 25px;
            padding-right: 15px;
            font-size: 14px;
            font-family: "arial", "微软雅黑";
        }

        #index_1 .t {
            height: 60px;
        }

        .title_l {
            display: inline-block;
            width: 15px;
            font-size: 15px;
            word-wrap: break-word;
            letter-spacing: 20px;
        }

        .title_r {
            display: inline-block;
            width: 45px;
            font-size: 15px;
            word-wrap: break-word;
        }

        .no_border_l {
            border-left: 0;
        }

        .no_border_lt {
            border-left: 0;
            border-top: 0;
        }

        .no_border_t {
            border-top: 0;
        }
        .downBtn{
            position: fixed;
            bottom: 150px;
            right: 100px;
        }

    </style>
    <style media="print">
        .Noprint {
            DISPLAY: none;
        }

        .PageNext {
            PAGE-BREAK-AFTER: always
        }

    </style>
</head>
<body>
<%--<h1>干部任免审批表(<a class="wordEd" href="<%=path%>/word.jsp?id=<%=useId%>" target="_blank">编辑</a>)</h1>--%>
<h1>干部任免审批表</h1>

<%--下载按钮--%>
<div class="downBtn" title="下载word" onclick="load()" id="i1">
    <img src="../../../images/downBtn.png" width="50px" height="50px">
</div>
<object id='OCX_OBJ' style="display: none"
        classid='clsid:B39F1330-3322-4a1d-9BF0-0BA2BB90E970'
        codebase='ofctnewclsid.cab#version=5,0,2,9'
        width='100%' height='100%'>
    <param name='MakerCaption' value='上海交大慧谷通用技术有限公司'>
    <param name='MakerKey'
           value="3F4BF491D77D1551B40E325A59CC8B99D2942124">
    <param name="ProductCaption" value="重庆法院人事管理系统">
    <param name="ProductKey" value="9BE84EA62EFE58885A369F4BAC3351CC976B6156">
    <param name='BorderStyle' value='1'>
    <param name='Menubar' value='true'>
    <param name='Titlebar' value='false'>
    <param name='FileNew' value='false'>
    <param name='FileOpen' value='true'>
    <param name='FileSave' value='false'>
    <param name='Caption' value=''>
    <param name=”IsUseUTF8URL” value=”-1”>
    <param name="isExitOfficeProcessWhenClose" value="-1">
</object>

<div class="containner PageNext" id="index_1">
    <table style="width: 100%;border-bottom: 0">
        <tr class="t">
            <td id="baseTb" class="title">姓名</td>
            <td name="info.fullname" id="fullname"></td>
            <td class="title">性别</td>
            <td name="info.genderText" id="genderText"></td>
            <td class="title">出生年月<br/>( 岁)</td>
            <td name="info.birthday" id="birthday"></td>
            <td rowspan="4" style="width:150px">
                <img id="Pimg" style="width:inherit;height:200px;"/>
            </td>
        </tr>
        <tr class="t">
            <td class="title">民族</td>
            <td name="info.nationText" id="nationText"></td>
            <td class="title">籍贯</td>
            <td name="info.hometown" id="hometown"></td>
            <td class="title">出生地</td>
            <td name="info.birthplace" id="birthplace"></td>
        </tr>
        <tr class="t">
            <td class="title">入党<br/>时间</td>
            <td name="info.politicalDate" id="politicalDate"></td>
            <td class="title">参加工<br/>作时间</td>
            <td name="info.workDate" id="workDate"></td>
            <td class="title">健康状况</td>
            <td name="info.physicalConditionText" id="physicalConditionText"></td>
        </tr>
        <tr class="t">
            <td class="title">专业技<br/>术职务</td>
            <%--<td colspan="2" name="info.lawPositionText" id="lawPositionText"></td>--%>
            <td colspan="2"></td>
            <td class="title">熟悉专业<br/>
                有何特长
            </td>
            <%--<td colspan="2" name="info.levelText" id="levelText"></td>--%>
            <td colspan="2"></td>
        </tr>
        <tbody id='educationInfo'>
        <tr>
            <td rowspan="2" class="title">学历<br/>学位</td>
            <td class="title">全日制<br/>教&nbsp;&nbsp;&nbsp;育</td>
            <td colspan="2" id="qrzjy"></td>
            <td class="title">毕业院校<br/>
                系及专业
            </td>
            <td colspan="2" id="qrzjybyyx"></td>
        </tr>
        <tr>
            <td class="title">在&nbsp;&nbsp;&nbsp;职<br/>教&nbsp;&nbsp;&nbsp;育</td>
            <td colspan="2" id="zzjy"></td>
            <td class="title">毕业院校<br/>
                系及专业
            </td>
            <td colspan="2" id="zzjybyyx"></td>
        </tr>
        </tbody>
        <tr>
            <td colspan="2" class="title">现任职务</td>
            <td colspan="5" name="info.administrationPositionText" id="administrationPositionText"></td>
        </tr>
        <tr>
            <td colspan="2" class="title">拟任职务</td>
            <td colspan="5"></td>
        </tr>
        <tr>
            <td colspan="2" class="title">拟免职务</td>
            <td colspan="5"></td>
        </tr>
    </table>

    <table style="width: 100%;" id="index_resume">
        <tr>
            <td class="title tb_cell"><span class="title_l">简&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历</span></td>
            <td></td>
        </tr>
    </table>
</div>
<div class="containner_N" id="index_2">
    <table style="width:100%;border-bottom: 0" id="index_2_target">
        <tbody id="index_tr_1">
        <tr>
            <td class="title tb_cell" rowspan="2">奖<br/>惩<br/>信<br/>息</td>
            <td colspan="6"></td>
        </tr>
        <tr>
            <td colspan="6" style="border-top: 0px;"></td>
        </tr>
        </tbody>
        <tbody id="index_tr_2">
        <tr>
            <td class="title" rowspan="2">年度<br/>考核<br/>结论<br/></td>
            <td colspan="6"></td>
        </tr>
        <tr>
            <td colspan="6" style="border-top: 0px;"></td>
        </tr>
        </tbody>
        <tr>
            <td class="title" rowspan="2">任<br/>免<br/>理<br/>由</td>
            <td colspan="6"></td>
        </tr>
        <tr>
            <td colspan="6" style="border-top: 0px;"></td>
        </tr>
    </table>

    <table style="width:100%;border-bottom: 0">
        <tbody id="family">
        <tr id="family_title">
            <td rowspan="8" class="title tb_cell family_title"><span class="title_l">家庭主要成员及重要社会关系</span></td>
            <td class="title">称谓</td>
            <td class="title">姓名</td>
            <td class="title">年龄</td>
            <td class="title">政治<br/>面貌</td>
            <td style="width:43%;" class="title" colspan="2">工作单位及主要职务</td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td colspan="2"></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td colspan="2"></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td colspan="2"></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td colspan="2"></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td colspan="2"></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td colspan="2"></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td colspan="2"></td>
        </tr>
        </tbody>
        <tr>
            <td class="title" rowspan="2"><span class="title_l">呈报单位</span></td>
            <td colspan="6"></td>
        </tr>
        <tr>
            <td colspan="6" style="border-top: 0"></td>
        </tr>
    </table>
    <table style="width:100%;" id="index_2_bottom">
        <tr id="index_2_bottom_tr">
            <td rowspan="9" class="title tb_cell">审意<br/>批&nbsp;&nbsp;<br/>机&nbsp;&nbsp;<br/>关见</td>
            <td rowspan="9" style="position: relative">
                <div style="position: absolute;right: 20px;bottom: 45px;letter-spacing: 5px;">(盖章)</div>
                <div style="position: absolute;right: 0;bottom: 20px;letter-spacing: 25px;">年月日</div>
            </td>
            <td rowspan="9" class="title tb_cell"><span class="title_r">行任政免机意关见</span></td>
            <td rowspan="9" style="position: relative">
                <div style="position: absolute;right: 20px;bottom: 45px;letter-spacing: 5px;">(盖章)</div>
                <div style="position: absolute;right: 0;bottom: 20px;letter-spacing: 25px;">年月日</div>
            </td>
        </tr>
        <tr></tr>
        <tr></tr>
        <tr></tr>
        <tr></tr>
        <tr></tr>
        <tr></tr>
        <tr></tr>
        <tr></tr>
    </table>
</div>
<div class="bottom_con">
    填表人:
</div>

<script type="text/javascript">

    var base = 1200 / 48;
    //    alert(base);
    var max_Row_num_1 = base - 8; //第一页可用的行数

    $(function () {
        $("td.tb_cell").each(function () {
            $(this).width($("#baseTb").width());
        })

        var resume = $("#index_resume").offset().top;
        var top_1 = $("#index_1").offset().top;
        var i = 1200 - (resume - top_1) + 3;
        $("#index_resume").height(i);

    })

    var img_url = '<%=path%>/photo/getPhotoById';
    var pa = {
        userId: "${param.userId}",
    }
    $.post(img_url, pa, function (data) {
        $("#Pimg").attr("src", data);
    })

    //查找返回的信息
    var url = '<%=path%>/view/selectInfo';
    var params = {
        id: "${param.userId}"
    };

    $.post(url, params, function (data) {
        if (data != null) {
            $("td[name^='info']").each(function () {

                var vaule = data[$(this).attr("id")];
                $(this).html((vaule == null || vaule == undefined) ? '' : vaule.replace(/-/g, "."));
                if ($(this).attr("id") == 'birthday') {
                    var birthbayVal = data[$(this).attr("id")] ? data[$(this).attr("id")] : '';
                    $(this).html(birthbayVal.replace(/-/g, ".") + "<br/>(" + calcYears(birthbayVal) + "岁)");
                }
                if ($(this).attr("id") == 'politicalDate' && data['politicalText'] && (data['politicalText'].indexOf('党员') == -1 || !data['politicalDate'])) {
                    $(this).html(data['politicalText']).prev().html('政治<br>面貌');
                }
            });
            // 学历信息 备注:以下逻辑,优先使用学历表里的当前信息,如果无全日制当前信息,使用学历表里非当前信息第一条;如果无在职信息,使用学位表里当前信息;如果没有,就使用学位表里非当前信息
            if (data.eduInfo != null && data.eduInfo.length > 0) {
                // 是否有全日制标记0:没有;1:有;
                var sfyqrzbj = "0";
                // 是否有在职标记0:没有;1:有;
                var sfyzzbj = "0";
                // 循环学历信息
                for (var i in data.eduInfo) {
                    // n_educate_form:1为全日制;2位在职教育;
                    var n_educate_form = data.eduInfo[i].n_educate_form;
                    // 是否是当前信息
                    var n_present_info = data.eduInfo[i].n_present_info;

                    if (1 == n_present_info) {
                        if ("0" == sfyqrzbj && 1 == n_educate_form) {
                            $("#qrzjy").text(data.eduInfo[i].n_education_background_report_text);
                            $("#qrzjybyyx").text(data.eduInfo[i].c_college + '  ' + getval(data.eduInfo[i].n_major_text));
                            sfyqrzbj = "1";
                        }

                        if ("0" == sfyzzbj && 2 == n_educate_form) {
                            $("#zzjy").text(data.eduInfo[i].n_education_background_text);
                            $("#zzjybyyx").text(data.eduInfo[i].c_college + '  ' + getval(data.eduInfo[i].n_major_text));
                            sfyzzbj = "1";
                        }

                    }
                    max_Row_num_1--;
                }

                if ("0" == sfyqrzbj) {
                    for (var i in data.eduInfo) {
                        // n_educate_form:1为全日制;2位在职教育;
                        var n_educate_form = data.eduInfo[i].n_educate_form;
                        // 是否是当前信息
                        var n_present_info = data.eduInfo[i].n_present_info;

                        if ("0" == sfyqrzbj && 1 == n_educate_form) {
                            $("#qrzjy").text(data.eduInfo[i].n_education_background_report_text);
                            $("#qrzjybyyx").text(data.eduInfo[i].c_college + '  ' + getval(data.eduInfo[i].n_major_text));
                            sfyqrzbj = "1";
                        }
                        max_Row_num_1--;
                    }
                }


                if ("0" == sfyzzbj) {
                    for (var i in data.degreeList) {
                        // n_educate_form:1为全日制;2位在职教育;
                        var n_educate_form = data.degreeList[i].n_educate_form;
                        // 是否是当前信息
                        var n_present_info = data.degreeList[i].n_present_info;
                        // 学位
                        var n_degree_text = '';
                        if ("0" == sfyzzbj && 2 == n_educate_form && 1 == n_present_info) {
                            n_degree_text = data.degreeList[i].n_degree_text;
                            sfyzzbj = "1";
                            $("#zzjy").text(getval(n_degree_text));
                            $("#zzjybyyx").text(data.degreeList[i].c_college + '  ' + getval(data.degreeList[i].n_major_text));
                        }
                        max_Row_num_1--;
                    }
                }

               if ("0" == sfyzzbj) {
                   for (var i in data.eduInfo) {
                       // n_educate_form:1为全日制;2位在职教育;
                       var n_educate_form = data.eduInfo[i].n_educate_form;
                       // 是否是当前信息
                       var n_present_info = data.eduInfo[i].n_present_info;
                       if ("0" == sfyzzbj && 2 == n_educate_form) {
                           $("#zzjy").text(data.eduInfo[i].n_education_background_text);
                           $("#zzjybyyx").text(data.eduInfo[i].c_college + '  ' + getval(data.eduInfo[i].n_major_text));
                           sfyzzbj = "1";
                       }
                       max_Row_num_1--;
                   }
               }
            } else {
                max_Row_num_1 = max_Row_num_1 - 2;
            }
            // 简历
            var extra_row = 0;
            if (data.resume != null && data.resume.length > 0) {
                $("#index_resume").empty();
                // 数据条数 > 第一页可容纳最大数量
                if (data.resume.length > max_Row_num_1) {
                    $("#index_2_target").before('<table style="width:100%;border-bottom: 0" id="index_1_continue"></table>');
                    // 添加入额时间次数，默认为0，不等于代表已添加，不再添加
                    var yefg_start_time_count = 0;
                    for (var i = 0; i < data.resume.length; i++) {
                        var resumeInfo = '<tr> ';
                        var rTime = (data.resume[i].d_start_date ? data.resume[i].d_start_date.replace(/-/g, ".") : '') + "——";
                        if (data.resume[i].d_end_date == null) {
                            rTime += '至今';
                        } else {
                            rTime += data.resume[i].d_end_date ? data.resume[i].d_end_date.replace(/-/g, ".") : '';
                        }
                        var rInfo = '';
                        if (data.resume[i].c_unit != null) {
                            rInfo += data.resume[i].c_unit;
                        }
                        if (data.resume[i].c_dept != null && data.resume[i].c_dept != '') {
                            rInfo += data.resume[i].c_dept;
                        }
                        if (data.resume[i].c_position != null) {
                            rInfo += data.resume[i].c_position;
                        }
                        if (data.resume[i].c_rank != null && data.resume[i].c_rank.trim() != '') {
                            rInfo += "(" + data.resume[i].c_rank + ")";
                        }
                        // 根据时间戳大小比较，1987的时间戳大于1982的时间戳
                        if (null != data.yefg_start_time) {
                            if (yefg_start_time_count == 0) {
                                if (null != data.resume[i].d_end_date && Date.parse(data.resume[i].d_start_date) < Date.parse(data.yefg_start_time) && Date.parse(data.resume[i].d_end_date) > Date.parse(data.yefg_start_time)) {
                                    rInfo += '<span style="color: red;">&nbsp;&nbsp;&nbsp;&nbsp;（入额时间 ' + data.yefg_start_time + '）</span>';
                                    yefg_start_time_count++;
                                } else if (null == data.resume[i].d_end_date || Date.parse(data.yefg_start_time) < Date.parse(data.resume[i].d_end_date)) {
                                    rInfo += '<span style="color: red;">&nbsp;&nbsp;&nbsp;&nbsp;（入额时间 ' + data.yefg_start_time + '）</span>';
                                    yefg_start_time_count++;
                                }
                            }
                        }
                        if (i == 0) {
                            resumeInfo += "<td rowspan='" + max_Row_num_1 + "' class='title tb_cell' >" + "<span class='title_l'>简&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历</span></td>";
                        }
                        if (i == max_Row_num_1) {
                            resumeInfo += "<td rowspan='" + (data.resume.length - max_Row_num_1) + "' class='title tb_cell' >" + "<span class='title_l'>简&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历</span></td>";
                        }
                        if (i == 0) {
                            resumeInfo += '<td  class="resume_time">' + rTime + '</td>';
                            resumeInfo += '<td class="resume_info no_border_l">' + rInfo + '</td></tr>';
                        } else if (i == max_Row_num_1) {
                            resumeInfo += '<td  class="resume_time ">' + rTime + '</td>';
                            resumeInfo += '<td class="resume_info no_border_l">' + rInfo + '</td></tr>';
                        } else {
                            resumeInfo += '<td  class="resume_time no_border_t">' + rTime + '</td>';
                            resumeInfo += '<td class="resume_info no_border_lt">' + rInfo + '</td></tr>';
                        }
                        if (i >= max_Row_num_1) {
                            extra_row++;
                            $("#index_1_continue").append(resumeInfo);
                        } else {
                            $("#index_resume").append(resumeInfo);
                        }
                    }
                } else {
                    // 添加入额时间次数，默认为0，不等于代表已添加，不再添加
                    var yefg_start_time_count = 0;
                    for (var i = 0; i < max_Row_num_1; i++) {
                        var resumeInfo = '<tr> ';
                        if (i >= data.resume.length) {
                            resumeInfo += '<td  class="resume_time no_border_t"></td>';
                            resumeInfo += '<td class="resume_info no_border_lt"></td></tr>';
                            $("#index_resume").append(resumeInfo);
                            continue;
                        }
                        var rTime = '';
                        if (data.resume[i].d_start_date != null) {
                            rTime += data.resume[i].d_start_date + "——";
                        }
                        if (data.resume[i].d_end_date == null) {
                            rTime += '至今';
                        } else {
                            rTime += data.resume[i].d_end_date;
                        }
                        var rInfo = '';
                        if (data.resume[i].c_unit != null) {
                            rInfo += data.resume[i].c_unit;
                        }
                        if (data.resume[i].c_dept != null && data.resume[i].c_dept != '') {
                            rInfo += data.resume[i].c_dept;
                        }
                        if (data.resume[i].c_position != null) {
                            rInfo += data.resume[i].c_position;
                        }
                        if (data.resume[i].c_rank != null && data.resume[i].c_rank.trim() != '') {
                            rInfo += "(" + data.resume[i].c_rank + ")";
                        }
                        // 根据时间戳大小比较，1987的时间戳大于1982的时间戳
                        if (null != data.yefg_start_time) {
                            if (yefg_start_time_count == 0) {
                                if (null != data.resume[i].d_end_date && Date.parse(data.resume[i].d_start_date) < Date.parse(data.yefg_start_time) && Date.parse(data.resume[i].d_end_date) > Date.parse(data.yefg_start_time)) {
                                    rInfo += '<span style="color: red;">&nbsp;&nbsp;&nbsp;&nbsp;（入额时间 ' + data.yefg_start_time + '）</span>';
                                    yefg_start_time_count++;
                                } else if (null == data.resume[i].d_end_date || Date.parse(data.yefg_start_time) < Date.parse(data.resume[i].d_end_date)) {
                                    rInfo += '<span style="color: red;">&nbsp;&nbsp;&nbsp;&nbsp;（入额时间 ' + data.yefg_start_time + '）</span>';
                                    yefg_start_time_count++;
                                }
                            }
                        }
                        if (i == 0) {
                            resumeInfo += "<td rowspan='" + max_Row_num_1 + "' class='title tb_cell' >" + "<span class='title_l'>简&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历</span></td>";
                        }
                        if (i == 0) {
                            resumeInfo += '<td  class="resume_time">' + rTime + '</td>';
                            resumeInfo += '<td class="resume_info no_border_l">' + rInfo + '</td></tr>';
                        } else {
                            resumeInfo += '<td  class="resume_time no_border_t">' + rTime + '</td>';
                            resumeInfo += '<td class="resume_info no_border_lt">' + rInfo + '</td></tr>';
                        }
                        $("#index_resume").append(resumeInfo);
                    }
                }
            }
            //奖惩信息
            var rowspan = 2;
            var j = 0;
            var f = 0;
            var flag = true;
            if (data.reward != null && data.reward.length > 0) {
                $("#index_tr_1").empty();
                j += data.reward.length;
            }
            if (data.punish != null && data.punish.length > 0) {
                $("#index_tr_1").empty();
                j += data.punish.length;
            }
            if (j > rowspan) {
                rowspan = j;
            }
            f = rowspan;
            if (data.reward != null && data.reward.length > 0) {


                for (var i in data.reward) {
                    var rewardInfo = '<tr> ';
                    var innerInfo = '';
                    if (i == 0) {
                        rewardInfo += "<td rowspan='" + rowspan + "' class='title tb_cell' >奖<br/>惩<br/>信<br/>息<br/></td>";
                        flag = false;
                    }
                    if (data.reward[i].d_reward_date != null) {
                        innerInfo += data.reward[i].d_reward_date.replace(/-/g, ".") ? data.reward[i].d_reward_date.replace(/-/g, ".") : '' + "&nbsp;&nbsp;&nbsp;";
                    }
                    if (data.reward[i].c_approval_unit_code_text != null) {
                        innerInfo += data.reward[i].c_approval_unit_code_text + "&nbsp;&nbsp;&nbsp;";
                    }
                    if (data.reward[i].approval_name_text != null) {
                        innerInfo += "(" + data.reward[i].approval_name_text + ")";
                    }


                    if (i == 0) {
                        rewardInfo += '<td colspan="6">' + innerInfo + '</td></tr>';
                    } else {
                        rewardInfo += '<td colspan="6" class=no_border_t>' + innerInfo + '</td></tr>';
                    }

                    $("#index_tr_1").append(rewardInfo);
                    f--;
                }

            }
            if (data.punish != null && data.punish.length > 0) {
                for (var i in data.punish) {
                    var rewardInfo = '<tr> ';
                    var innerInfo = '';
                    if (i == 0 && flag) {
                        rewardInfo += "<td rowspan='" + rowspan + "' class='title tb_cell' >奖<br/>惩<br/>信<br/>息<br/></td>";
                        flag = false;
                    }
                    if (data.punish[i].d_start_date != null) {
                        innerInfo += data.punish[i].d_start_date + "——";
                    }
                    if (data.punish[i].d_end_date != null) {
                        innerInfo += data.punish[i].d_end_date + "&nbsp;&nbsp;&nbsp;";
                    }
                    if (data.punish[i].c_unit != null) {
                        innerInfo += data.punish[i].c_unit + "&nbsp;&nbsp;&nbsp;";
                    }
                    if (data.punish[i].n_punish_reason_text != null) {
                        innerInfo += "(" + data.punish[i].n_punish_reason_text + ")";
                    }

                    if (i == 0 && f == rowspan) {
                        rewardInfo += '<td colspan="6">' + innerInfo + '</td></tr>';
                    } else {
                        rewardInfo += '<td colspan="6" class=no_border_t>' + innerInfo + '</td></tr>';
                    }
                    $("#index_tr_1").append(rewardInfo);
                    f--;
                }
            }
            if (!flag) {
                for (var i = 0; i < f; i++) {
                    var Info = '<tr> ';
                    Info += '<td colspan="6"  class="no_border_t"></td></tr>';
                    $("#index_tr_1").append(Info);
                }
            }
            //考核信息
            var row_num = 2;
            if (data.assess != null && data.assess.length > 0) {
                $("#index_tr_2").empty();
                row_num = Math.ceil(data.assess.length / 2);
                var t_falg = false;
                if (row_num < 2) {
                    row_num = 2;
                    t_falg = true;
                }
                for (var i = 0; i < data.assess.length; i++) {
                    var assessInfo = '<tr> ';
                    var innerInfo = '';
                    if (i == 0) {
                        assessInfo += "<td rowspan='" + (row_num + 1) + "' class='title tb_cell' >年度<br/>考核<br/>结论<br/></td>";
                    }

                    if (data.assess[i].n_year != null) {
                        innerInfo += data.assess[i].n_year + "年度考核&nbsp;";
                    }

                    if (data.assess[i].n_result_text != null) {
                        innerInfo += data.assess[i].n_result_text;
                    }

                    if (i == 0) {
                        assessInfo += '<td colspan="6">' + innerInfo + '</td>';
                    } else {
                        assessInfo += '<td colspan="6" class="no_border_t">' + innerInfo + '</td>';
                    }

                    $("#index_tr_2").append(assessInfo);

                }
                if (t_falg) {
                    var Info = '<tr> ';
                    Info += '<td colspan="3" class="no_border_t"></td><td colspan="3" class="no_border_lt"></td></tr>';
                    $("#index_tr_2").append(Info);
                }

            }
            //家庭信息
            var family_row = 8;
            if (data.family != null && data.family.length > 0) {

                $("#family tr:not(#family_title)").each(function () {
                    $(this).remove();
                });
                var f = 7;
                if ((data.family.length + 1) > family_row) {
                    family_row = data.family.length + 1;
                }

                for (var i in data.family) {
                    var rewardInfo = '<tr>';
                    var text_1 = '';
                    var text_2 = '';
                    var text_3 = '';
                    var text_4 = '';
                    var text_5 = '';
                    if (data.family[i].n_relationship_text != null) {
                        text_1 = data.family[i].n_relationship_text;
                    }
                    rewardInfo += "<td>" + text_1 + "</td>";
                    if (data.family[i].c_name != null) {
                        text_2 = data.family[i].c_name;
                    }
                    rewardInfo += "<td>" + text_2 + "</td>";
                    if (data.family[i].is_dead != null && data.family[i].is_dead == 1) {
                        text_3 = "已故";
                    } else if (data.family[i].d_birthday != null) {
                        text_3 = calcYears(data.family[i].d_birthday);
                    }
                    rewardInfo += "<td>" + text_3 + "</td>";
                    if (data.family[i].n_political_text != null) {
                        text_4 = data.family[i].n_political_text;
                    }
                    rewardInfo += "<td>" + text_4 + "</td>";
                    if (data.family[i].c_unit_job != null) {
                        text_5 = data.family[i].c_unit_job;
                    }
                    rewardInfo += "<td colspan='2'>" + text_5 + "</td>";

                    rewardInfo += '</tr>';
                    $("#family").append(rewardInfo);

                }

                if (data.family.length < f) {
                    for (var i = data.family.length; i < f; i++) {
                        $("#family").append(" <tr><td></td> <td></td> <td></td> <td></td> <td colspan='2'></td></tr>");
                    }

                }

                $(".family_title").attr("rowspan", family_row);
            }
            var index_2_row = rowspan + row_num + family_row + extra_row + 2 + 2;
            var bo_row = base - index_2_row;
            if (bo_row < 2) {
                bo_row = 2;
                $("#index_2_bottom tr:not(#index_2_bottom_tr)").each(function () {
                    $(this).remove();
                });
                for (var i = 1; i < bo_row; i++) {
                    $("#index_2_bottom").append("<tr></tr>");
                }

                $("#index_2_bottom_tr td").each(function () {
                    $(this).attr("rowspan", bo_row);
                })
            }
            $("td.tb_cell").each(function () {
                $(this).width($("#baseTb").width());
            })
            var resume = $("#index_resume").offset().top;
            var top_1 = $("#index_1").offset().top;
            var i = 1200 - (resume - top_1) + 3;
            $("#index_resume").height(i);
        }
    });

    function calcYears(start, end) {
        if (start)
            start = start.replace(/-/g, "/");
        if (end)
            end = end.replace(/-/g, "/");
        var s = new Date(start);
        var e = end ? new Date(end) : new Date();

        var result = Math.floor((e.getTime() - s.getTime()) / 3600000 / 24 / 365.25);
        return result || 0;
    }

    function getval(val) {
        if (val == undefined || val == null) return '';
        return val;
    }

    var i = 5;
    //延迟下载，防止未生成完毕导致404
    function wait() {
        var url = '<%=path%>/view/dlWord';
        var params = {
            id: "<%=useId%>",
            uuid: '<%=uuid%>'
        };
        $("#i1").text("生成中，请等待" + i + "秒......").off("click").removeAttr('onclick');
        var interval = setInterval(function () {
            i--;
            $("#i1").text("生成中，请等待" + i + "秒......");
            if (i == 0) {
                window.clearInterval(interval);
                window.open('<%=path%>/view/dlWord?uuid=<%=uuid%>&id=<%=useId%>', '_self');
                // $.post(url,params,function (data) {
                //     console.log("data",data);
                // })

                $("#i1").html('<img src="../../../images/downBtn.png" width="50px" height="50px">').on("click", function () {
                    window.open('<%=path%>/view/dlWord?uuid=<%=uuid%>&id=<%=useId%>', '_self');
                    // $.post(url,params,function (data) {
                    //     console.log("data",data)
                    // })
                });
            }
        }, 1000);
    }
    <%--var TANGER_OCX_OBJ;--%>
    <%--function init()--%>
    <%--{--%>
    <%--    TANGER_OCX_OBJ=document.getElementById("OCX_OBJ");--%>
    <%--    TANGER_OCX_OBJ.activate(true);--%>
    <%--    TANGER_OCX_OBJ.BeginOpenFromURL("<%=file%>");--%>
    <%--    //TANGER_OCX_OBJ.isnocopy = true;--%>

    <%--    wait()--%>
    <%--}--%>
    function load() {
        // wait()
        var url = '<%=path%>/view/wordEdit';
        var params = {
            id: "<%=useId%>",
            uuid: '<%=uuid%>'
        };
        $.post(url, params, function (data) {
            if (data.success) {
                // init();
                // wait()
                window.open('<%=path%>/view/dlWord?uuid=<%=uuid%>&id=<%=useId%>', '_self');
            } else {
                alert("加载失败，请重试！");
            }
        });
    }
</script>
</body>
</html>
