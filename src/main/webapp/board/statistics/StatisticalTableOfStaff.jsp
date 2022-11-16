<%--
  Created by IntelliJ IDEA.
  User: 28608
  Date: 2018/7/18
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String type = request.getParameter("type");
    String courtLevel = request.getParameter("courtLevel");
    String attachName = request.getParameter("attachName");
    String preparation = request.getParameter("preparation");
    String preparationName = request.getParameter("preparationName");
    String courtCode = request.getParameter("courtCode");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>法院工作人员职级统计表</title>
    <link href="css/biao.css" rel="stylesheet" type="text/css">
    <jsp:include page="/basic_import.jsp"></jsp:include>
    <script src="js/stat.js" type="text/javascript"></script>
    <script src="js/jquery.table2excel.js" type="text/javascript"></script>
</head>
<style type="text/css">

    .attachName {
        font-size: 20px;
        color: #334A80;
    }

    .mytalble {
        border-bottom-color: black;
        border-top-color: black;
        width: 1600px;
        color: #000000;
        border-right-color: black;
        font-size: medium;
        border-left-color: black;
        border: 1px;
        cellspacing: 0px;
        cellpadding: 0px;
    }

</style>
<body>
<div id="all" style="text-align: center ;width: 100%" hidden>
    <table id="mytalble" class="biaonr"></table>
</div>

<br/>
<br/>


<script type="text/javascript">

    var countOfAll = 0;//实有人数
    var countOfMale = 0;//男
    var countOfFemale = 0;//女
    var countOfHanNation = 0;//汉族
    var countOfMinority = 0;//少数民族
    var countOfzgdy = 0;//中共党员
    var countOfgqty = 0;//共青团员
    var countOfmzdp = 0;//民主党派
    var countOfqt = 0;//qt
    var countOf35 = 0;//35岁及以下
    var countOf36 = 0;//36岁至40岁
    var countOf41 = 0;//41岁至45岁
    var countOf46 = 0;//46岁至50岁
    var countOf51 = 0;//51岁至55岁
    var countOf56 = 0;//56岁至60岁
    var countOf61 = 0;//61岁及以上
    var countOfbs = 0;//博士研究生
    var countOfss = 0;//硕士研究生
    var countOfbk = 0;//本科
    var countOfdz = 0;//大专
    var countOfgzzz = 0;//高中中专
    var countOfcz = 0;//初中
    var countOfxx = 0;//小学以下
    var countOfpjnl = 0;//平均年龄

    //法官合计一行
    var countOfAll_sum_fg = 0;//实有人数
    var countOfMale_sum_fg = 0;//男
    var countOfFemale_sum_fg = 0;//女
    var countOfHanNation_sum_fg = 0;//汉族
    var countOfMinority_sum_fg = 0;//少数民族
    var countOfzgdy_sum_fg = 0;//中共党员
    var countOfgqty_sum_fg = 0;//共青团员
    var countOfmzdp_sum_fg = 0;//民主党派
    var countOfqt_sum_fg = 0;//qt
    var countOf35_sum_fg = 0;//35岁及以下
    var countOf36_sum_fg = 0;//36岁至40岁
    var countOf41_sum_fg = 0;//41岁至45岁
    var countOf46_sum_fg = 0;//46岁至50岁
    var countOf51_sum_fg = 0;//51岁至55岁
    var countOf56_sum_fg = 0;//56岁至60岁
    var countOf61_sum_fg = 0;//61岁及以上
    var countOfbs_sum_fg = 0;//博士研究生
    var countOfss_sum_fg = 0;//硕士研究生
    var countOfbk_sum_fg = 0;//本科
    var countOfdz_sum_fg = 0;//大专
    var countOfgzzz_sum_fg = 0;//高中中专
    var countOfcz_sum_fg = 0;//初中
    var countOfxx_sum_fg = 0;//小学以下
    var countOfpjnl_sum_fg = 0;//平均年龄

    //审判辅助人员合计
    var countOfAll_sum_spfzry = 0;//实有人数
    var countOfMale_sum_spfzry = 0;//男
    var countOfFemale_sum_spfzry = 0;//女
    var countOfHanNation_sum_spfzry = 0;//汉族
    var countOfMinority_sum_spfzry = 0;//少数民族
    var countOfzgdy_sum_spfzry = 0;//中共党员
    var countOfgqty_sum_spfzry = 0;//共青团员
    var countOfmzdp_sum_spfzry = 0;//民主党派
    var countOfqt_sum_spfzry = 0;//qt
    var countOf35_sum_spfzry = 0;//35岁及以下
    var countOf36_sum_spfzry = 0;//36岁至40岁
    var countOf41_sum_spfzry = 0;//41岁至45岁
    var countOf46_sum_spfzry = 0;//46岁至50岁
    var countOf51_sum_spfzry = 0;//51岁至55岁
    var countOf56_sum_spfzry = 0;//56岁至60岁
    var countOf61_sum_spfzry = 0;//61岁及以上
    var countOfbs_sum_spfzry = 0;//博士研究生
    var countOfss_sum_spfzry = 0;//硕士研究生
    var countOfbk_sum_spfzry = 0;//本科
    var countOfdz_sum_spfzry = 0;//大专
    var countOfgzzz_sum_spfzry = 0;//高中中专
    var countOfcz_sum_spfzry = 0;//初中
    var countOfxx_sum_spfzry = 0;//小学以下
    var countOfpjnl_sum_spfzry = 0;//平均年龄

    $(function () {

        var law_position_report_text = "";
        var attachName_ = "<%=attachName%>";
        var attachName = attachName_ == "" ? attachName_ : "--" + attachName_;
        var preparationName_ = "<%=preparationName%>";
        var preparationName = preparationName_ == "" ? "" : "--" + preparationName_;

        $("#all").prepend(
            "<div align='center' style='padding-top: 30px;font-size: xx-large'><b>法院工作人员职级统计表<span class='attachName'>" + attachName + preparationName + "</span></b></div>" +
            "<div style='position: fixed;padding-top: 10px;padding-left: 95%;cursor: pointer;font-size: 14px'><a onclick='todownLoad()'>下载表格</a></div>" +
            "</br></br></br>"
        );

        //table表头
        var a =
            "<tbody class='mytalble'>" +
            "<tr>" +
            "<th colspan='2' rowspan='3'></th>" +
            "<th rowspan='3'><div>实有人数</div></th>" +
            "<th colspan='2'><div>性别</div></th>" +
            "<th colspan='2'><div>民族</div></th>" +
            "<th colspan='4'><div>政治面貌</div></th>" +
            "<th colspan='7'><div>年龄</div></th>" +
            "<th colspan='7'><div>文化程度</div></th>" +
            "<th rowspan='3'><div>平均年龄</div></th>" +
            "</tr>" +
            "<tr>" +
            "<th rowspan='2'><div>男</div></th>" +
            "<th rowspan='2'><div>女</div></th>" +
            "<th rowspan='2'><div>汉族</div></th>" +
            "<th rowspan='2'><div>少数民族</div></th>" +
            "<th rowspan='2'><div>中共党员</div></th>" +
            "<th rowspan='2'><div>共青团员</div></th>" +
            "<th rowspan='2'><div>民主党派</div></th>" +
            "<th rowspan='2'><div>其他</div></th>" +
            "<th rowspan='2'><div>35岁及以下</div></th>" +
            "<th rowspan='2'><div>36岁至40岁</div></th>" +
            "<th rowspan='2'><div>41岁至45岁</div></th>" +
            "<th rowspan='2'><div>46岁至50岁</div></th>" +
            "<th rowspan='2'><div>51岁至55岁</div></th>" +
            "<th rowspan='2'><div>56岁至60岁</div></th>" +
            "<th rowspan='2'><div>61岁及以上</div></th>" +
            "<th colspan='2'><div>研究生</div></th>" +
            "<th rowspan='2'><div>本科</div></th>" +
            "<th rowspan='2'><div>大专</div></th>" +
            "<th rowspan='2'><div>高中中专</div></th>" +
            "<th rowspan='2'><div>初中</div></th>" +
            "<th rowspan='2'><div>小学以下</div></th>" +
            "</tr>" +
            "<tr>" +
            "<th><div>博士</div></th>" +
            "<th><div>硕士</div></th>" +
            "</tr>";

        var column = ["\\", "院长", "副院长", "审判委员会委员", "庭长", "副庭长", "审判员", "助理审判员", "法官合计", "执行员", "法官助理", "书记员", "司法警察", "司法技术人员", "其他审判辅助人员", "审判辅助人员合计", "司法行政人员"];

        for (var i = 0; i < column.length; i++) {
            if ("\\" == column[i]) {
                law_position_report_text = "";
                a += "<tr valign='top'>" +
                    "<td rowspan='2' align='center' valign='middle'><div>合计</div></td>" +
                    "</tr>";
            } else if ("院长" == column[i]) {
                law_position_report_text = "院长";
                a += "<tr valign='top'>" +
                    "<td rowspan='9' align='center' valign='middle'><div>法官</div></td>" +
                    "</tr>";
            } else if ("副院长" == column[i]) {
                law_position_report_text = "副院长";
            } else if ("审判委员会委员" == column[i]) {
                law_position_report_text = "审判委员会委员";
            } else if ("庭长" == column[i]) {
                law_position_report_text = "庭长";
            } else if ("副庭长" == column[i]) {
                law_position_report_text = "副庭长";
            } else if ("审判员" == column[i]) {
                law_position_report_text = "审判员";
            } else if ("助理审判员" == column[i]) {
                law_position_report_text = "助理审判员";
            } else if ("法官合计" == column[i]) {
                law_position_report_text = "法官合计";
            } else if ("执行员" == column[i]) {
                law_position_report_text = "执行员";
                a += "<tr valign='top'>" +
                    "<td rowspan='6' align='center' valign='middle'><div>审判辅助人员</div></td>" +
                    "</tr>";
            } else if ("法官助理" == column[i]) {
                law_position_report_text = "法官助理";
            } else if ("书记员" == column[i]) {
                law_position_report_text = "书记员";
            } else if ("司法警察" == column[i]) {
                law_position_report_text = "司法警察";
            } else if ("司法技术人员" == column[i]) {
                law_position_report_text = "司法技术人员";
            } else if ("其他审判辅助人员" == column[i]) {
                law_position_report_text = "其他审判辅助人员";
            } else if ("审判辅助人员合计" == column[i]) {
                law_position_report_text = "审判辅助人员合计";
            } else if ("司法行政人员" == column[i]) {
                law_position_report_text = "司法行政人员";
            }
            a += addRow(law_position_report_text);
        }

        a += "</tbody>";
        $("#mytalble").append(a);
        $("#all").show();
    });

    function addRow(law_position_report_text) {

        $.ajax({
            url: "<%=basePath%>statisticalTable/statData_new",
            async: false, //改为同步方式
            type: "POST",
            data: {
                courtLevel: '<%=courtLevel%>',
                preparation: '<%=preparation%>',
                courtCode: '<%=courtCode%>',
                law_position_report_text: law_position_report_text
            },
            success: function (returnData) {
                if (returnData != null) {
                    data_all = returnData.list;
                }
            },
            dataType: "json"
        });

        //获取数量
        countOfAll = data_all[0]["实有人数"] == undefined ? 0 : data_all[0]["实有人数"];//实有人数
        countOfMale = data_all[0]["男"] == undefined ? 0 : data_all[0]["男"];//男
        countOfFemale = data_all[0]["女"] == undefined ? 0 : data_all[0]["女"];//女
        countOfHanNation = data_all[0]["汉族"] == undefined ? 0 : data_all[0]["汉族"];//汉族
        countOfMinority = data_all[0]["少数民族"] == undefined ? 0 : data_all[0]["少数民族"];//少数民族
        countOfzgdy = data_all[0]["中共党员"] == undefined ? 0 : data_all[0]["中共党员"];//中共党员
        countOfgqty = data_all[0]["共青团员"] == undefined ? 0 : data_all[0]["共青团员"];//共青团员
        countOfmzdp = data_all[0]["民主党派"] == undefined ? 0 : data_all[0]["民主党派"];//民主党派
        countOfqt = data_all[0]["其他"] == undefined ? 0 : data_all[0]["其他"];//其他
        countOf35 = data_all[0]["35岁及以下"] == undefined ? 0 : data_all[0]["35岁及以下"];//35岁及以下
        countOf36 = data_all[0]["36岁至40岁"] == undefined ? 0 : data_all[0]["36岁至40岁"];//36岁至40岁
        countOf41 = data_all[0]["41岁至45岁"] == undefined ? 0 : data_all[0]["41岁至45岁"];//41岁至45岁
        countOf46 = data_all[0]["46岁至50岁"] == undefined ? 0 : data_all[0]["46岁至50岁"];//46岁至50岁
        countOf51 = data_all[0]["51岁至55岁"] == undefined ? 0 : data_all[0]["51岁至55岁"];//51岁至55岁
        countOf56 = data_all[0]["56岁至60岁"] == undefined ? 0 : data_all[0]["56岁至60岁"];//56岁至60岁
        countOf61 = data_all[0]["61岁及以上"] == undefined ? 0 : data_all[0]["61岁及以上"];//61岁及以上
        countOfbs = data_all[0]["博士研究生"] == undefined ? 0 : data_all[0]["博士研究生"];//博士研究生
        countOfss = data_all[0]["硕士研究生"] == undefined ? 0 : data_all[0]["硕士研究生"];//硕士研究生
        countOfbk = data_all[0]["本科"] == undefined ? 0 : data_all[0]["本科"];//本科
        countOfdz = data_all[0]["大专"] == undefined ? 0 : data_all[0]["大专"];//大专
        countOfgzzz = data_all[0]["高中中专"] == undefined ? 0 : data_all[0]["高中中专"];//高中中专
        countOfcz = data_all[0]["初中"] == undefined ? 0 : data_all[0]["初中"];//初中
        countOfxx = data_all[0]["小学以下"] == undefined ? 0 : data_all[0]["小学以下"];//小学以下
        countOfpjnl = data_all[0]["平均年龄"] == undefined ? 0 : data_all[0]["平均年龄"];//平均年龄


        if ($.inArray(law_position_report_text, ["院长", "副院长", "审判委员会委员", "庭长", "副庭长", "审判员", "助理审判员"]) != -1) {
            countOfAll_sum_fg += countOfAll;
            countOfMale_sum_fg += countOfMale;//男
            countOfFemale_sum_fg += countOfFemale;//女
            countOfHanNation_sum_fg += countOfHanNation;//汉族
            countOfMinority_sum_fg += countOfMinority;//少数民族
            countOfzgdy_sum_fg += countOfzgdy;//中共党员
            countOfgqty_sum_fg += countOfgqty;//共青团员
            countOfmzdp_sum_fg += countOfmzdp;//民主党派
            countOfqt_sum_fg += countOfqt;//qt
            countOf35_sum_fg += countOf35;//35岁及以下
            countOf36_sum_fg += countOf36;//36岁至40岁
            countOf41_sum_fg += countOf41;//41岁至45岁
            countOf46_sum_fg += countOf46;//46岁至50岁
            countOf51_sum_fg += countOf51;//51岁至55岁
            countOf56_sum_fg += countOf56;//56岁至60岁
            countOf61_sum_fg += countOf61;//61岁及以上
            countOfbs_sum_fg += countOfbs;//博士研究生
            countOfss_sum_fg += countOfss;//硕士研究生
            countOfbk_sum_fg += countOfbk;//本科
            countOfdz_sum_fg += countOfdz;//大专
            countOfgzzz_sum_fg += countOfgzzz;//高中中专
            countOfcz_sum_fg += countOfcz;//初中
            countOfxx_sum_fg += countOfxx;//小学以下
            countOfpjnl_sum_fg += countOfpjnl;//平均年龄
        } else if ($.inArray(law_position_report_text, ["执行员", "法官助理", "书记员", "司法警察", "司法技术人员", "其他审判辅助人员"]) != -1) {
            countOfAll_sum_spfzry += countOfAll;
            countOfMale_sum_spfzry += countOfMale;//男
            countOfFemale_sum_spfzry += countOfFemale;//女
            countOfHanNation_sum_spfzry += countOfHanNation;//汉族
            countOfMinority_sum_spfzry += countOfMinority;//少数民族
            countOfzgdy_sum_spfzry += countOfzgdy;//中共党员
            countOfgqty_sum_spfzry += countOfgqty;//共青团员
            countOfmzdp_sum_spfzry += countOfmzdp;//民主党派
            countOfqt_sum_spfzry += countOfqt;//qt
            countOf35_sum_spfzry += countOf35;//35岁及以下
            countOf36_sum_spfzry += countOf36;//36岁至40岁
            countOf41_sum_spfzry += countOf41;//41岁至45岁
            countOf46_sum_spfzry += countOf46;//46岁至50岁
            countOf51_sum_spfzry += countOf51;//51岁至55岁
            countOf56_sum_spfzry += countOf56;//56岁至60岁
            countOf61_sum_spfzry += countOf61;//61岁及以上
            countOfbs_sum_spfzry += countOfbs;//博士研究生
            countOfss_sum_spfzry += countOfss;//硕士研究生
            countOfbk_sum_spfzry += countOfbk;//本科
            countOfdz_sum_spfzry += countOfdz;//大专
            countOfgzzz_sum_spfzry += countOfgzzz;//高中中专
            countOfcz_sum_spfzry += countOfcz;//初中
            countOfxx_sum_spfzry += countOfxx;//小学以下
            countOfpjnl_sum_spfzry += countOfpjnl;//平均年龄
        }

        var b = "";
        if (law_position_report_text == "") {
            law_position_report_text = "\\";
        }
        if (law_position_report_text == "司法行政人员") {
            b = "<tr>" +
                "<td><div>司法行政人员</div></td>" +
                "<td><div>司法行政人员</div></td>" +
                "<td><div>" + countOfAll + "</div></td>" +
                "<td><div>" + countOfMale + "</div></td>" +
                "<td><div>" + countOfFemale + "</div></td>" +
                "<td><div>" + countOfHanNation + "</div></td>" +
                "<td><div>" + countOfMinority + "</div></td>" +
                "<td><div>" + countOfzgdy + "</div></td>" +
                "<td><div>" + countOfgqty + "</div></td>" +
                "<td><div>" + countOfmzdp + "</div></td>" +
                "<td><div>" + countOfqt + "</div></td>" +
                "<td><div>" + countOf35 + "</div></td>" +
                "<td><div>" + countOf36 + "</div></td>" +
                "<td><div>" + countOf41 + "</div></td>" +
                "<td><div>" + countOf46 + "</div></td>" +
                "<td><div>" + countOf51 + "</div></td>" +
                "<td><div>" + countOf56 + "</div></td>" +
                "<td><div>" + countOf61 + "</div></td>" +
                "<td><div>" + countOfbs + "</div></td>" +
                "<td><div>" + countOfss + "</div></td>" +
                "<td><div>" + countOfbk + "</div></td>" +
                "<td><div>" + countOfdz + "</div></td>" +
                "<td><div>" + countOfgzzz + "</div></td>" +
                "<td><div>" + countOfcz + "</div></td>" +
                "<td><div>" + countOfxx + "</div></td>" +
                "<td><div>" + Math.floor(countOfpjnl) + "</div></td>" +
                "</tr>";
        } else if (law_position_report_text == "法官合计") {
            b = "<tr>" +
                "<td><div>法官合计</div></td>" +
                "<td><div>" + countOfAll_sum_fg + "</div></td>" +
                "<td><div>" + countOfMale_sum_fg + "</div></td>" +
                "<td><div>" + countOfFemale_sum_fg + "</div></td>" +
                "<td><div>" + countOfHanNation_sum_fg + "</div></td>" +
                "<td><div>" + countOfMinority_sum_fg + "</div></td>" +
                "<td><div>" + countOfzgdy_sum_fg + "</div></td>" +
                "<td><div>" + countOfgqty_sum_fg + "</div></td>" +
                "<td><div>" + countOfmzdp_sum_fg + "</div></td>" +
                "<td><div>" + countOfqt_sum_fg + "</div></td>" +
                "<td><div>" + countOf35_sum_fg + "</div></td>" +
                "<td><div>" + countOf36_sum_fg + "</div></td>" +
                "<td><div>" + countOf41_sum_fg + "</div></td>" +
                "<td><div>" + countOf46_sum_fg + "</div></td>" +
                "<td><div>" + countOf51_sum_fg + "</div></td>" +
                "<td><div>" + countOf56_sum_fg + "</div></td>" +
                "<td><div>" + countOf61_sum_fg + "</div></td>" +
                "<td><div>" + countOfbs_sum_fg + "</div></td>" +
                "<td><div>" + countOfss_sum_fg + "</div></td>" +
                "<td><div>" + countOfbk_sum_fg + "</div></td>" +
                "<td><div>" + countOfdz_sum_fg + "</div></td>" +
                "<td><div>" + countOfgzzz_sum_fg + "</div></td>" +
                "<td><div>" + countOfcz_sum_fg + "</div></td>" +
                "<td><div>" + countOfxx_sum_fg + "</div></td>" +
                "<td><div>" + Math.floor(countOfpjnl_sum_fg / 8) + "</div></td>" +
                "</tr>";
        } else if (law_position_report_text == "审判辅助人员合计") {
            b = "<tr>" +
                "<td><div>审判辅助人员合计</div></td>" +
                "<td><div>" + countOfAll_sum_spfzry + "</div></td>" +
                "<td><div>" + countOfMale_sum_spfzry + "</div></td>" +
                "<td><div>" + countOfFemale_sum_spfzry + "</div></td>" +
                "<td><div>" + countOfHanNation_sum_spfzry + "</div></td>" +
                "<td><div>" + countOfMinority_sum_spfzry + "</div></td>" +
                "<td><div>" + countOfzgdy_sum_spfzry + "</div></td>" +
                "<td><div>" + countOfgqty_sum_spfzry + "</div></td>" +
                "<td><div>" + countOfmzdp_sum_spfzry + "</div></td>" +
                "<td><div>" + countOfqt_sum_spfzry + "</div></td>" +
                "<td><div>" + countOf35_sum_spfzry + "</div></td>" +
                "<td><div>" + countOf36_sum_spfzry + "</div></td>" +
                "<td><div>" + countOf41_sum_spfzry + "</div></td>" +
                "<td><div>" + countOf46_sum_spfzry + "</div></td>" +
                "<td><div>" + countOf51_sum_spfzry + "</div></td>" +
                "<td><div>" + countOf56_sum_spfzry + "</div></td>" +
                "<td><div>" + countOf61_sum_spfzry + "</div></td>" +
                "<td><div>" + countOfbs_sum_spfzry + "</div></td>" +
                "<td><div>" + countOfss_sum_spfzry + "</div></td>" +
                "<td><div>" + countOfbk_sum_spfzry + "</div></td>" +
                "<td><div>" + countOfdz_sum_spfzry + "</div></td>" +
                "<td><div>" + countOfgzzz_sum_spfzry + "</div></td>" +
                "<td><div>" + countOfcz_sum_spfzry + "</div></td>" +
                "<td><div>" + countOfxx_sum_spfzry + "</div></td>" +
                "<td><div>" + Math.floor(countOfpjnl_sum_spfzry / 8) + "</div></td>" +
                "</tr>";
        } else if (law_position_report_text != "司法警察" && law_position_report_text != "司法技术人员") {//暂时不统计这两个
            b = "<tr>" +
                "<td><div>" + law_position_report_text + "</div></td>" +
                "<td><div>" + countOfAll + "</div></td>" +
                "<td><div>" + countOfMale + "</div></td>" +
                "<td><div>" + countOfFemale + "</div></td>" +
                "<td><div>" + countOfHanNation + "</div></td>" +
                "<td><div>" + countOfMinority + "</div></td>" +
                "<td><div>" + countOfzgdy + "</div></td>" +
                "<td><div>" + countOfgqty + "</div></td>" +
                "<td><div>" + countOfmzdp + "</div></td>" +
                "<td><div>" + countOfqt + "</div></td>" +
                "<td><div>" + countOf35 + "</div></td>" +
                "<td><div>" + countOf36 + "</div></td>" +
                "<td><div>" + countOf41 + "</div></td>" +
                "<td><div>" + countOf46 + "</div></td>" +
                "<td><div>" + countOf51 + "</div></td>" +
                "<td><div>" + countOf56 + "</div></td>" +
                "<td><div>" + countOf61 + "</div></td>" +
                "<td><div>" + countOfbs + "</div></td>" +
                "<td><div>" + countOfss + "</div></td>" +
                "<td><div>" + countOfbk + "</div></td>" +
                "<td><div>" + countOfdz + "</div></td>" +
                "<td><div>" + countOfgzzz + "</div></td>" +
                "<td><div>" + countOfcz + "</div></td>" +
                "<td><div>" + countOfxx + "</div></td>" +
                "<td><div>" + Math.floor(countOfpjnl) + "</div></td>" +
                "</tr>";
        }
        return b;
    }

    //下载功能
    function todownLoad() {
        var attachName_ = "<%=attachName%>";
        var attachName = attachName_ == "" ? attachName_ : "--" + attachName_;
        var preparationName_ = "<%=preparationName%>";
        var preparationName = preparationName_ == "" ? "" : "--" + preparationName_;
        var fileName = "法官等级情况统计表" + attachName + preparationName;
        var tableName = "mytalble"

        $("#" + tableName).table2excel({
            exclude: ".noExl",
            name: "Excel Document Name",
            filename: fileName,
            fileext: ".xls",
            exclude_img: true,
            exclude_links: true,
            exclude_inputs: true
        });
    }

</script>

</body>
</html>
