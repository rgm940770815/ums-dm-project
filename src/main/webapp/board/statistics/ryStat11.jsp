<%--
  Created by IntelliJ IDEA.
  User: huise
  Date: 16/5/17 0017
  Time: 上午 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@page import="cn.net.withub.ums.common.UmsConstant" %>
<%@page import="cn.net.withub.ums.entity.UmsUserInfoView" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String type = request.getParameter("type");
    String courtLevel = request.getParameter("courtLevel");
    String leftColumn = request.getParameter("leftColumn");
    String topColumn = request.getParameter("topColumn");
    String typeId_left = request.getParameter("typeId_left");
    String typeId_top = request.getParameter("typeId_top");
    String fydmList = request.getParameter("fydmList");
    String leftCodeNeed = request.getParameter("leftCodeNeed");
    String topCodeNeed = request.getParameter("topCodeNeed");
    String courtCode = request.getParameter("courtCode");
    String tableName = request.getParameter("tableName");
    String titles = request.getParameter("titles");
    UmsUserInfoView userInfo = (UmsUserInfoView)request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
    boolean isSuperUser = false;
    if(userInfo.getId().equals("-1")){
        isSuperUser = true;
    }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>法院工作人员情况统计表</title>
    <link href="css/biao.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=basePath%>/js/encodeParam.js" language="JavaScript" charset="GB2312"></script>
</head>
<style type="text/css">
    .crs_table {
        text-align: center;
    }
    .attachName{
        font-size: 20px;
        color: #334A80;
    }
    .mytalble{
        border-bottom-color: black;
        border-top-color: black;
        width: 1600px;
        color: #000000;
        border-right-color: black;
        font-size: medium;
        border-left-color: black;
        border:1px;
        cellspacing:0px;
        cellpadding:0px;
    }
    a,label,input,select{
        cursor: pointer;
    }
    .addLi,.addLi_top,.addLi_left {
        display: inline;
    }
    .addcolumnDiv{
            position:relative;border:1px solid #AAAAAA;left:10%;width: 80%;margin-bottom: 10px;border-radius:10px;margin: 4px;padding: 10px;
    }
    /*设置超出了显示滚动条*/
    .bui-stdmod-body{
        overflow-x : hidden;
        overflow-y : auto;
    }
    li{list-style-type: none}
    .activeLi{
        background-color: #428bca;
    }
    .activeLi2{
        background-color: #fd606a;
    }
        .bui-tab-item .icon-remove {
            position: absolute;
            right: 3px;
            top: 2px;
            z-index: 20;
            cursor: pointer;
        }
    .mask_progress {
        width: 200px;
        position: fixed;
        left: 45%;
        top: 50%;
        z-index: 1050;
        border: 1px solid #c3c3d6;
    }
    .separate {
        border-left: 2px solid #c3c3d6;
    }

    .biaonr tbody td{
        cursor: pointer;
    }
</style>
<body onload="json();">
<jsp:include page="/basic_import.jsp"></jsp:include>

<script src="js/jquery.table2excel.js" type="text/javascript"></script>
<div id="all" style="text-align: center ;width: 100%" hidden >
    <table  id="mytalble" class="biaonr">
    </table>
</div>
<div class="hide" id="customSearchDiv">
    <form class="form-horizontal" id="custom_form">
        <div class="row">
            <h3 style="width:100px;display: inline-block">统计范围</h3>
        </div>
        <%if(isSuperUser){%>
        <div class="row">
            <div class="control-group span8" style="width: 80%">
                <div class="controls">
                    <label for="courtlevel_c">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="countStyle_c" id="courtlevel_c"  value="1" checked/>
                        <span>法院级别</span>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <select id="countStyle_c">
                            <option value="">不限制</option>
                            <option value="0">高院</option>
                            <option value="1">中院</option>
                            <option value="2">基层法院</option>
                        </select>
                    </label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8" style="width: 80%">
                <div class="controls">
                    <label for="courtName_c">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="countStyle_c" id="courtName_c"  value="0"  />
                        <span>选择法院</span>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </label>
                </div>
                <div id="addCourtDiv" class="hide" style="float:left;position:relative;left:3%;width:60%;height: 50px;border: 1px solid grey" ></div>
                <button class="hide" type="button" style="float:left;position: relative;left:4%;top:20%" id="addCourtBtn">添加法院</button>
            </div>
        </div>
        <hr/>
        <%}%>
        <div class="row">
            <h3 style="width:100px;display: inline-block">统计选项</h3>
        </div>
        <div class="row">
            <div class="control-group span8" style="width: 95%">
                <label class="control-label"><s>*</s>上标题选项：</label>
                <div class="controls">
                        <select id="topColumn" data-rules="{required:true}"></select>
                        <button onclick="addColumn(document.getElementById('topColumn'))" type="button">添加</button>
                </div>
            </div>
            <div class="control-group span20 hide addcolumnDiv" id="topAddDiv"></div>
        </div>
        <div class="row addcolumnDiv">
            <div id="top_tab"></div>
            <div id="top_panel" class="" style="padding:10px;">
            </div>
        </div>
        <div class="row">
            <div class="control-group span8" style="width: 95%">
                <label class="control-label"><s>*</s>下标题选项：</label>
                <div class="controls">
                    <select id="leftColumn" onchange="addColumn(this)" data-rules="{required:true}"></select>
                </div>
            </div>
            <label id="leftcolumn-label" style="display: none;float: none;width: 230px;margin-left: 120px;color: red;" class="control-label">提示：统计范围处的法院条件将会被忽略。</label>
            <div class="control-group span20 hide addcolumnDiv" id="leftAddDiv"></div>
        </div>
    </form>
</div>
<div id="addCourtDiv_c" class="hide">
    <form class="form-horizontal" id="addCourt_form">
        <div id="courtList" style="float:left;position:relative;left:8%;height: 100%;width: 30%;overflow: scroll;border: 1px solid grey">
        </div>
        <div id="btnDiv" style="float:left;position:relative;left:13%;height: 100%;width: 10%;">
            <button style="margin: 10px" id="addCourtLiBtn" type="button">添加</button>
            <button style="margin: 10px" id="removeCourtLiBtn" type="button">删除</button>
            <button style="margin: 10px" id="removeAllCourtBtn" type="button">清空</button>
        </div>
        <div id="addedCourtdiv" style="float:left;position:relative;left:19%;height: 100%;width: 30%;border: 1px solid grey;overflow: scroll">
        </div>
    </form>
</div>
<br/><br/>

<%--反查弹出--%>
<div  id="data-dialog">
    <div id="grid">

    </div>
</div>

<script type="text/javascript">
    var basePath = "<%=basePath%>";
    var courtList;
    var isSuperUser = <%=isSuperUser%>;
    var tableName = '<%=tableName%>';
    var userCourtCode = "<%=userInfo.getCourtCode()%>";
    var top_tab;
    var top_columns = [];
    var result = {leftArray:[], list:[[], []], title: [], cal: '', topCodeNeed: '', leftCodeNeed: '', fydmList: ''};
    var count = 1, currentIndex = 0;
    var loadMask = null;
    function json() {
        var left='';
        if ("<%=type%>"=='fss_01_7') {
            $("#all").prepend("<div align='center' style='padding-top: 30px;font-size: xx-large'><b>自定义报表</b></div>" +
                    "<div style='position: fixed;padding-top: 10px;padding-left: 90%;cursor: pointer;font-size: 14px'><a onclick='todownLoad()'>下载表格</a>&nbsp;&nbsp;&nbsp;<a id='toCostomSearch'>自定义查询</a></div>"+
                    "<div align='left' style=' padding-left: 30px'>" +
                    "报表编号：01－7 " +
                    "<br/>制表机关：重庆市高级人民法院(汇总) " +
                    "<br/>备案机关：国家统计局 " +
                    "<br/>备案文号：国统办函[2001]130号</div>");
        }
        var topColumn = '<%=topColumn%>'.split(',');
        var obj = {
            topcolumn: topColumn,
            typeId_top: '<%=typeId_top%>'.split(','),
            topCodeNeed: '<%=topCodeNeed%>'.split(';')
        };
        count = topColumn.length;
        var param = {
            typeStr: '<%=type%>',
            courtLevel: '<%=courtLevel%>',
            courtCode: '<%=courtCode%>',
            leftColumn: '<%=leftColumn%>',
            typeId_left: '<%=typeId_left%>',
            fydmList: '<%=fydmList%>',
            leftCodeNeed: '<%=leftCodeNeed%>'
        };
        BUI.use(['bui/mask'],function(Mask) {
            Mask.maskElement('body');
            $('.bui-ext-mask').after('<div class="progress progress-striped mask_progress"> <div class="bar bar-success" style="width: 0%;"></div> </div>');
            getData(param, obj);
        });
    }

    function getData(param, obj) {
        param.topColumn = obj.topcolumn[currentIndex];
        param.typeId_top = obj.typeId_top[currentIndex];
        param.topCodeNeed = obj.topCodeNeed[currentIndex];


        var queryParam = $.extend({},param);
        queryParam.topCodeNeed = encodeParamByStr(queryParam.topCodeNeed);
        queryParam.leftCodeNeed = encodeParamByStr(queryParam.leftCodeNeed);
        queryParam.fydmList = encodeParamByStr(queryParam.fydmList);

        var url;
        if(param.topColumn=="birthday" || param.leftColumn=="birthday")
            url = basePath + "chart/customSearchForAgePsy";
        else
            url = basePath + "chart/customSearchForPsy";
        if("<%=type%>"=='fss_01_7'){
            $.post(url, queryParam, function (allData) {

                if (!allData) {
                    BUI.use('bui/mask',function(Mask) {
                        Mask.unmaskElement('body');
                    });
                    BUI.Message.Alert("数据错误", "warning");
                    return;
                }

                allData = jQuery.parseJSON(allData);
                console.log(allData);
                if(allData.error)
                {
                    BUI.use('bui/mask',function(Mask) {
                        Mask.unmaskElement('body');
                    });
                    BUI.Message.Alert(allData.error, "warning");
                    return;
                }

                mergeData(allData);
                currentIndex ++;
                $('.mask_progress>div').width((currentIndex / count) * 100 + '%');
                if (currentIndex == count) {
                    generTable();
                    BUI.use('bui/mask',function(Mask) {
                        Mask.unmaskElement('body');
                    });
                    $('.mask_progress').hide();
                } else {
                    getData(param, obj, currentIndex);
                }
            });

        }
    }
    
    //合并数据
    function mergeData(data) {
        result.leftArray = data.leftArray;
        result.topCodeNeed = data.topCodeNeed;
        result.leftCodeNeed = data.leftCodeNeed;
        result.fydmList = data.fydmList;
        var length = result.list[1].length > 0 ? result.list[1][result.list[1].length - 1] : 0;
        for (var i = 0; i < data.list.length; i++) {
            if (i === 0) {
                if (length !== 0) {
                    data.list[0].shift();
                }
                result.list[0] = result.list[0].concat(data.list[0]);           //表头数据
            } else if (i == 1) {
                var len = data.list[1].length;
                if (length !== 0) {
                    len --;
                }
                var titles = decodeURI('<%=titles%>').split('、');
                result.title.push({
                    colspan: len,
                    text: titles[currentIndex],
                });
                result.cal += "=" + (length === 0 ? (length + 2) : length + 1) + "+至" + (length + len);
                for (var j = 0; j < len; j++) {
                    result.list[1].push(Number(data.list[1][j]) + length);           //表列数
                }
            } else {
                result.list[i] = result.list[i] || {};
                if (length !== 0) {
                    delete data.list[i]['countAll']
                }
                for (var k in data.list[i]) {
                    result.list[i][k + length] = data.list[i][k]           //表数据
                }
            }
        }
    }

    //生成最终表格
    function generTable() {

        var left = result.leftArray;
        var title = result.title;
        var a, b,data = result.list;
        var tableWidth,colwidth;
        if(data[0].length<=15)
        {
            tableWidth = (data[0].length+2) * 100+"px";
            colwidth = "100px";
        }
        else if(data[0].length>15 && data[0].length<=25)
        {
            tableWidth = (data[0].length+2) * 70+"px";
            colwidth = "70px";
        }
        else
        {
            tableWidth = (data[0].length+2) * 50+"px";
            colwidth = "50px";
        }
        $("#mytalble").css("width",tableWidth);
        a +="<thead><tr><th colspan='"+(data[0].length+2)+"'>各        部        门        人        数</th></tr>";
        a += "<tr><th class='myth' colspan='2' rowspan='3' style='width: 200px'> 计算方法：<br/>列:1"+result.cal;
        for (var titleIndex = 0; titleIndex < title.length; titleIndex++) {
            a += '<th colspan="' + title[titleIndex]['colspan'] + '">' + title[titleIndex]['text'] + '</th>';
        }
        a += "<br/>行：1=2+至"+(data.length-2)+" </th></tr>";
        $.each(data, function (n, list) {
            if(n == 0)
            {
                a += "<tr>";
                $.each(list, function (index,obj) {
                    a += "<th width="+colwidth+">"+obj+"</th>";
                })
                a += "</tr>";
            }
            else if(n == 1)
            {
                a += "<tr>"
                $.each(list, function (index,obj) {
                    a +="<th width="+colwidth+">"+obj+"</td>";
                })
                a +="</tr></thead><tbody>";
            }
            else
            {
                var n_ = n-2;
                a += "<tr><th width='200px'>" + left[n_].name + "</th> " +
                    "<th width='50px' align='center'>" + left[n_].num + "</th>";
                $.each(list, function (key, value) {
                    a += "<td align='center' loadData ";
                    for(var k in value){
                        var v = value[k];
                        if(k == 'count'){
                            continue;
                        }
                        a += " data-" + k + "='"+ v +"' ";
                    }
                    a += " data-topcodeneed='" + result.topCodeNeed + "'";
                    a += " data-leftcodeneed='" + result.leftCodeNeed + "'";
                    a += " data-fydmlist='" + result.fydmList + "'";
                    a += ">";
                    a += value.count + "</td><br/>";
                });
                a += "</tr>";
            }

        });
        a += "</tbody>";
        $("#mytalble").append(a);
        $("#all").show();
        var screenhight = document.body.clientHeight-130;
        var screenWidth
        if(document.body.clientWidth/50>data[0].length+2 && data[0].length>15)
            screenWidth = (data[0].length+3)*50;
        else if(document.body.clientWidth/50>data[0].length+2 && data[0].length<=15)
            screenWidth = (data[0].length+2) * 100;
        else
            screenWidth = (document.body.clientWidth/50-2)*50;
        FixTable("mytalble", "th", screenWidth, screenhight);

        //最后给td绑定点击事件 进行反查
        $("td[loadData]").off("click").on("click",function(){
            makeBUIGrid($(this));
        })

    }

    function todownLoad(){
        var fileName ;
        if ("<%=type%>"=='fss_01_7') {
            fileName = "统计报表(人民陪审员) "+"-"+tableName;
        }
        $("#mytalble").table2excel({
            exclude: ".noExl",
            name: "Excel Document Name",
            filename: fileName,
            fileext: ".xls",
            exclude_img: true,
            exclude_links: true,
            exclude_inputs: true
        });
    }

    BUI.use(['common/search', 'bui/tree', 'bui/data', 'bui/calendar', 'bui/overlay', 'bui/form', 'bui/uploader', 'bui/mask', 'bui/tab'],
            function (Search, Tree, Data, Calendar, Overlay, Form, Uploader, Mask, Tab) {
                var myForm = new Form.HForm({
                    srcNode: '#custom_form',
                }).render();
                var dzDialog = new Overlay.Dialog({
                    title: "自定义统计",
                    width: '60%',
                    height: 500,
                    contentId: "customSearchDiv",
                    buttons: [
                        {
                            text: '开始统计', elCls: 'button button-primary', handler: function () {
                            myForm.valid();
                            if(myForm.isValid())
                            {
                                if (top_columns.length <= 0) {
                                    BUI.Message.Alert("请至少添加一个上标题！", "warning");
                                    return;
                                }
                                var leftColumnStr = getcolumnStr("check_left");
                                var topColumnObj = getTopStr();
                                var fydmList = "";
                                if(isSuperUser) {
                                    fydmList = courtArrayStr;
                                }
                                else {
                                    fydmList += userCourtCode;
                                }
                                var leftColumn = $("#leftColumn").find('option:selected').attr("columnName");
                                var typeId_left = $("#leftColumn").val();
                                var courtLevel = $("#countStyle_c").val();
                                var tableName = topColumnObj.texts + "-" + $("#leftColumn").find('option:selected').text();

                                if ($('#statForm').length == 0) {
                                    $('body').append($('<form id="statForm" action="ryStat11.jsp" method="post" ></form>'));
                                }
                                var form = $('#statForm');
                                form.hide();
                                form.empty();
                                form.append('<input type="text" name="type" value="fss_01_7">');
                                form.append('<input type="text" name="courtCode" value="' + userCourtCode + '">');
                                form.append('<input type="text" name="leftColumn" value="' + leftColumn + '">');
                                form.append('<input type="text" name="topColumn" value="' + topColumnObj.columnNames + '">');
                                form.append('<input type="text" name="typeId_left" value="' + typeId_left + '">');
                                form.append('<input type="text" name="typeId_top" value="' + topColumnObj.typeids + '">');
                                if (typeId_left != 1) {
                                form.append('<input type="text" name="courtLevel" value="' + courtLevel + '">');
                                form.append('<input type="text" name="fydmList" value="' + fydmList + '">');
                                } else {
                                    form.append('<input type="text" name="courtLevel" value="">');
                                    form.append('<input type="text" name="fydmList" value="">');
                                }
                                form.append('<input type="text" name="leftCodeNeed" value="' + leftColumnStr + '">');
                                form.append('<input type="text" name="topCodeNeed" value="' + topColumnObj.ids + '">');
                                form.append('<input type="text" name="tableName" value="' + tableName + '">');
                                form.append('<input type="text" name="titles" value="' + topColumnObj.texts + '">');

                                // 对该 form 执行提交
                                form.submit();

                            }
                        }
                        },
                        {
                            text: '关闭', elCls: 'button', handler: function () {
                            $(".addLi").remove();
                            $(".addLi_left").remove();
                            $(".addLi_top").remove();
                            $("#topAddDiv").hide();
                            $("#leftAddDiv").hide();
                            this.close();
                        }
                        }
                    ],
                    header: "",
                    success: function (data) {
                        this.close();
                    }
                });
                var addCourtDialog = new Overlay.Dialog({
                    title: "添加法院",
                    width: '40%',
                    height: 300,
                    contentId: "addCourtDiv_c",
                    buttons:[
                        {
                            text: '确定', elCls: 'button button-primary', handler: function () {
                            var courtList="";
                            var nameList = "选择的法院有：";
                            $("#addedCourtdiv li").each(function(index,obj){
                                courtList += $(this).attr("courtid")+",";
                                nameList+=$(this).text()+"，";
                            })
                            nameList = nameList.substring(0,nameList.length-1);
                            courtList = courtList.substring(0,courtList.length-1);
                            courtArrayStr = courtList;
                            nameList += "。";
                            $("#addCourtDiv").html("<label>"+nameList+"</label>");
                            addCourtDialog.hide();
                        }
                        },
                        {
                            text: '关闭', elCls: 'button', handler: function () {this.close();}
                        }

                    ],
                    header: "",
                    success: function (data) {
                        this.close();
                    }
                });

                $("#addCourtBtn").click(function(){
                    if(!courtList)
                    {
                        $.post("<%=basePath%>/code/codeListByType", {typeId: 1}, function (data) {
                            courtList = data.data;
                            for (var i = 0; i < data.data.length; i++) {
                                $("#courtList").append("<li courtid="+data.data[i].courtCode+"><label>"+data.data[i].codeName+"</label></li>")
                            }
                            $("#courtList").find("li").click(function(){
                                if($(this).hasClass("activeLi"))
                                    $(this).removeClass("activeLi");
                                else
                                    $(this).addClass("activeLi");
                            })
                            //添加法院按钮事件
                            $("#addCourtLiBtn").click(function(){
                                for(var i=0;i<$(".activeLi").length;i++)
                                {
                                    if(!$($(".activeLi")[i]).hasClass("removed"))
                                    {
                                        var courtid = $($(".activeLi")[i]).attr("courtid");
                                        var text = $($(".activeLi")[i]).html();
                                        $($(".activeLi")[i]).addClass("removed")
                                        $("#addedCourtdiv").append("<li courtid="+courtid+"><label>"+text+"</label></li>")
                                    }
                                    else
                                    {
                                        var text = $($(".activeLi")[i]).html();
                                        BUI.Message.Alert(text+"已经添加！", "warning");
                                        continue;
                                    }
                                }
                                $(document).undelegate("#addedCourtdiv li", "click").delegate("#addedCourtdiv li", "click", function () {
                                    if($(this).hasClass("activeLi2"))
                                        $(this).removeClass("activeLi2");
                                    else
                                        $(this).addClass("activeLi2");
                                })
                                $(".activeLi").removeClass("activeLi");
                                return false;
                            });
                            $("#removeCourtLiBtn").click(function(){
                                for(var i=0;i<$(".activeLi2").length;i++)
                                {
                                    var courtCode = $($(".activeLi2")[i]).attr("courtid");
                                    for(var j=0;j<$("#courtList").find("li").length;j++)
                                    {
                                        if($($("#courtList").find("li")[j]).attr("courtid") == courtCode)
                                        {
                                            $($("#courtList").find("li")[j]).removeClass("removed");
                                        }
                                    }
                                }
                                $(".activeLi2").remove();
                                return false;
                            });
                            $("#removeAllCourtBtn").click(function(){
                                $("#addedCourtdiv").empty();
                                $(".removed").removeClass("removed");
                                return false;
                            })

                        });
                    }

                    addCourtDialog.show();

                });
               setTimeout(function(){
                   $("#toCostomSearch").click(function(){
                       myForm.clearErrors();
                       $("#leftColumn").empty();
                       $("#leftColumn").append($("<option>").attr({"value": ""}).text("请选择"));
                       $("#leftColumn").append($("<option>").attr({"value": "-99"}).attr({"columnName": 'birthday'}).text("年龄"));
                       $("#topColumn").empty();
                       $("#topColumn").append($("<option>").attr({"value": ""}).text("请选择"));
                       $("#topColumn").append($("<option>").attr({"value": "-99"}).attr({"columnName": 'birthday'}).text("年龄"));
                       $.post("<%=basePath%>/code/getCodeWithNotNullForPsy", {}, function (data) {
                           for (var i = 0; i < data.length; i++) {
                               $("#leftColumn").append($("<option>").attr({"value": data[i].ID}).attr({"columnName": data[i].column_name}).text(data[i].type_name));
                               $("#topColumn").append($("<option>").attr({"value": data[i].ID}).attr({"columnName": data[i].column_name}).text(data[i].type_name));
                           }
                       });
                       $("#leftColumn").append($("<option>").attr({"value": 1}).attr({"columnName": "a.court_code"}).text("法院"));
                       $("#leftColumn").append($("<option>").attr({"value": 800}).attr({"columnName": "juror_work"}).text("职业"));
                       $("#topColumn").append($("<option>").attr({"value": 800}).attr({"columnName": "juror_work"}).text("职业"));
                       $("#leftColumn").append($("<option>").attr({"value": 801}).attr({"columnName": "member_state"}).text("陪审状态"));
                       $("#topColumn").append($("<option>").attr({"value": 801}).attr({"columnName": "member_state"}).text("陪审状态"));
                       $("#leftColumn").select2();
                       $("#topColumn").select2();
                       top_tab.removeChildren(true);   //移除所有多选上标题
                       dzDialog.show();
                   });
               },1000);

                top_tab = new Tab.TabPanel({
                    render:'#top_tab',
                    elCls : 'nav-tabs',
                    panelContainer : '#top_panel',//如果内部有容器，那么会跟标签项一一对应，如果没有会自动生成
                    defaultChildCfg : {//配置子控件公用的属性
                        closeable : true,
                        closeTpl : '<i class="icon icon-remove"></i>'
                    },
                    children : []
                });

                top_tab.render();
                $('#top_tab').parent().hide();
                top_tab.on('beforeRemoveChild', function (e) {
                    if (top_tab.getItems().length == 1) {
                        $('#top_tab').parent().hide();
                        $('#topColumn').val('').select2();
                    }
                    var typeId = e.child.get('id');
                    //    移除已选上标题数组中的对应元素
                    top_columns.splice(top_columns.indexOf(typeId), 1);
                    //    刷新标题选项 disabled 的字段
                    refreshAttr("topColumn");
                });
                //切换时选中项也跟着切换
                top_tab.on('itemselected', function (e) {
                    var typeId = e.item == null ? "" : e.item.get('id');
                    $('#topColumn').val(typeId).select2();
                });
                $(document).undelegate('input[name=selectAll]', 'click').delegate('input[name=selectAll]', 'click', function () {
                    var typeDivId = $(this).attr('typeDivId');
                    if ($(this)[0].value == 1)
                        checkAll(typeDivId);
                    else if ($(this)[0].value == 2)
                        fanxuan(typeDivId);
                    else
                        uncheckAll(typeDivId);
                })
            });
    $("input[name=countStyle_c]").click(function() {
        var countStyle = $("input[name='countStyle_c']:checked").val();
        if(countStyle == 1)
        {
            courtArrayStr = "";
            $("#addCourtDiv").css("display","none")
            $("#addCourtBtn").css("display","none")
            $("#countStyle_c").show();
            var html = "";
            html += "<option value=''>不限制</option>";
            html += "<option value='0'>高院</option>";
            html += "<option value='1'>中院</option>";
            html += "<option value='2'>基层法院</option>";
            $("#countStyle_c").empty();
            $("#countStyle_c").append(html);
        }
        else
        {
            $("#countStyle_c").css("display","none")
            $("#countStyle_c").val("")
            $("#addCourtDiv").show();
            $("#addCourtBtn").show();
        }
    })
    function getcolumnStr(checkBoxName){
        var checkBox = $("input[name="+checkBoxName+"]");
        var checkBoxLength = $("input[name="+checkBoxName+"]").length
        var leftColumn = "";
        for(var i=0;i<checkBoxLength;i++)
        {
            if(checkBox[i].checked)
            {
                leftColumn += checkBox[i].value + ",";
            }
        }
        leftColumn = leftColumn.substring(0,leftColumn.length-1);
        return leftColumn;
    }

    //获取多选的值
    function getTopStr(){
        var items = top_tab.getItems();
        var obj = {columnNames: '', typeids: '', texts: '', ids: ''};
        for (var i = 0; i < items.length; i++) {
            var id = items[i].get('id');
            var columnName = items[i].get('columnName');
            var text = items[i].get('title');
            obj.columnNames += columnName + ',';
            obj.typeids += id + ',';
            obj.texts += text + '、';

            $('input[name=check_top][typeid=' + id + '][checked=checked]').each(function () {
                obj.ids += $(this).val() + ',';
            });
            obj.ids = obj.ids.substring(0, obj.ids.length - 1);
            obj.ids += ';';
        }
        obj.columnNames = obj.columnNames.substring(0, obj.columnNames.length - 1);
        obj.typeids = obj.typeids.substring(0, obj.typeids.length - 1);
        obj.texts = obj.texts.substring(0, obj.texts.length - 1);
        obj.ids = obj.ids.substring(0, obj.ids.length - 1);
        return obj;
    }

    var courtArrayStr = "";

    function addColumn(this_)
    {
        showMask();
        var this_id = $(this_).attr("id");
        var addDiv = "#leftAddDiv";
        var checkBoxName;
        var typeId = $(this_).val();
        var typeText = $(this_).find('option:selected').text();
        var columnName = $(this_).find('option:selected').attr('columnName');
        if(this_id == "leftColumn")
        {
            checkBoxName = "check_left";
            if (typeId == 1) {
                $('#leftcolumn-label').show();
            } else {
                $('#leftcolumn-label').hide();
            }
        }
        else
        {
            addDiv = "#topAddDiv";
            checkBoxName = "check_top";
            if ($(this_).val() != '') {
                top_columns.push(typeId);
            }
        }
        $(addDiv).empty();
        if ($(this_).val() == '') {
            hideMask();
            return;
        }
        if(typeId == -99)
        {
            renderBuiTab(typeId, nnd_outer, typeText, checkBoxName, addDiv, columnName);
            hideMask();
        }
        else if( typeId == 800){
            //职业
            var arr = {"基层干部": "基层干部", "人民团体成员": "人民团体成员", "事业单位职员": "事业单位职员", "专业技术人员": "专业技术人员", "工商业人员": "工商业人员", "社区工作者": "社区工作者", "普通居民": "普通居民", "农民": "农民", "进城务工人员": "进城务工人员", "其他人员": "其他人员"};
            renderBuiTab(typeId, arr, typeText, checkBoxName, addDiv, columnName);
            hideMask();
        }
        else if( typeId == 801){
            //陪审状态
            var arr = [{id:'01', codeName: '在任'}, {id:'02', codeName: '退出'}];
            renderBuiTab(typeId, arr, typeText, checkBoxName, addDiv, columnName);
            hideMask();
        }
        else
        {
            $.post("<%=basePath%>/code/codeListByType", {typeId:typeId}, function (data) {
                if ($(this_).val() == 1) data = data.data;
                renderBuiTab(typeId, data, typeText, checkBoxName, addDiv, columnName);
                hideMask();
            });
        }

        //处理上下标题不能选同一字段
        refreshAttr(this_id);
    }
    var nnd_outer={1:'1-18',2:'19-25',3:'26-30',4:'31-35',5:'36-40',6:'41-45',7:'46-50',9:'51-55',10:'56-60',11:'61-65',12:'66-70'};
    function addNewNND(start,end,nndOld)
    {
        var recode=new Array;
        for(var index in nndOld)
        {
            var nnd = nndOld[index].split("-");
            if(nnd.length==2)
            {
                var start_ = parseInt(nnd[0]);
                var end_ = parseInt(nnd[1]);
                var flag = false;
                for(var i = start_;i<=end_;i++)
                {
                    if(i==start || i==end)
                    {
                        flag= true;
                    }
                }
                if(flag)
                    recode.push(index);
            }
            else
            {
                var start_ = parseInt(nnd[0]);
                if(start_==start || start_==end)
                {
                    recode.push(index);
                }
            }
        }
        var nndNew = {};
        var j=1;
        for(var index in nndOld)
        {
            if(isRight(index,recode))
            {
                nndNew[j]=nndOld[index];//将添加的年龄段直接放入
                j++;
            }
        }
        nndNew[getLength(nndNew)+1]=start+"-"+end;
        var nnNeed = new Array();//需要添加的年龄
        for(var i=1;i<=70;i++)
        {
            var flag = getI_isRight(i,nndNew);//判断数字i是否在年龄段序列中
            if(!flag) nnNeed.push(i);//如果没有就将
        }
        //将需要添加的年龄放入序列
        nndNew = addNeedToNndNew(nnNeed,nndNew)
        //重新将序列排序
        nndNew = arrayNND(nndNew);
        nnd_outer = nndNew;
        return nndNew;
    }
    //判断一个数字是否在记录的数组中
    function isRight(index,recode)
    {
        var flag = true;
        for(var i in recode)
        {
            if(recode[i]==index)
            {
                flag =  false;
            }
        }
        return flag;
    }
    //判断一个年龄是否在年龄段序列中
    function getI_isRight(i,nndNew)
    {
        var flag = false;
        for(var index in nndNew)
        {
            var nnd = nndNew[index].split("-");
            if(nnd.length==2)
            {
                var start_ = parseInt(nnd[0]);
                var end_ = parseInt(nnd[1]);
                for(var j= start_;j<=end_;j++)
                {
                    if(i==j) flag = true;
                }
            }
            else
            {
                if(i==nnd[0]) flag = true;
            }
        }
        return flag;
    }
    //将年龄段序列排序
    function arrayNND(nndOld){
        var nndNew = {};
        var nndNew_index=1;
        for(var i=1;i<=getLength(nndOld);i++)
        {
            var max_nndNew_index;
            if(getLength(nndNew)>0)
            {
                var nnd_ = nndNew[getLength(nndNew)].split("-");
                if(nnd_.length==2)
                    max_nndNew_index = parseInt(nnd_[1]);
                else
                    max_nndNew_index=  parseInt(nnd_[0])
            }
            else
            {
                max_nndNew_index=0;
            }
            for(var index in nndOld)
            {
                var nnd = nndOld[index].split("-");
                var start_ = parseInt(nnd[0]);
                if(start_== max_nndNew_index+1)
                {
                    nndNew[i]=nndOld[index];
                }
            }
        }
        return nndNew;
    }
    function getLength(array){
        var index = 0;
        for(var i in array){
            index++;
        }
        return index;
    }
    function addNeedToNndNew(nnNeed,nndNew){
        for(var i =0;i<nnNeed.length;i++)
        {
            var start,end;
            start = nnNeed[i];
            var k=1;
            for(var j =i+1;j<nnNeed.length;j++)
            {
                if(j ==nnNeed.length-1)
                {
                    if(nnNeed[j]==start+k)
                    {
                        nndNew[getLength(nndNew)+1]= start+"-"+nnNeed[j];
                    }
                    else
                    {
                        nndNew[getLength(nndNew)+1]= start+"-"+nnNeed[j-1];
                        nndNew[getLength(nndNew)+1]= nnNeed[j]+"";
                    }
                    return nndNew;
                }
                else
                {
                    if(nnNeed[j]==start+k)
                    {
                        k++;
                        continue;
                    }
                    else {
                        end = nnNeed[j-1];
                        i=j-1;
                        break;
                    }
                }
                k++;
            }
            if(end == undefined|| end==null) end=start;
            if(start != end)
            {
                nndNew[getLength(nndNew)+1]= start+"-"+end;
            }
            else
            {
                nndNew[getLength(nndNew)+1]= start+"";
            }
        }
        return nndNew;
    }
    function addSJD(){
        var sj = "<select class='sjd' style='width: 10%'>"
        for(var i=1;i<=70;i++)
        {
            sj+="<option value="+i+">"+i+"</option>"
        }
        sj+="</select>"
        return sj;
    }

    function checkAll(cName) {

        var code_Values = $('li[typeDivId=' + cName + ']>input[type=checkbox]');

        if (code_Values.length) {
            for ( var i = 0; i < code_Values.length; i++) {
                code_Values[i].checked = true;
            }
        } else {
            code_Values.checked = true;
        }
    }

    function uncheckAll(cName) {

        var code_Values = $('li[typeDivId=' + cName + ']>input[type=checkbox]');

        if (code_Values.length) {
            for ( var i = 0; i < code_Values.length; i++) {
                code_Values[i].checked = false;
            }
        } else {
            code_Values.checked = false;
        }
    }
    function fanxuan(cName){
        var cb = $('li[typeDivId=' + cName + ']>input[type=checkbox]');

        for(var i = 0; i < cb.length; i++){
            if(cb[i].checked == true){
                cb[i].checked = false;
            }else{
                cb[i].checked = true;
            }
        }
    }

    function renderBuiTab(typeId, data, typeText, checkBoxName, addDiv, columnName) {

        //判断是上标题还是下标题
        if (addDiv === "#topAddDiv") {
            $('#top_tab').parent().show();    //一条都没有时直接隐藏掉
            //如果已有标签，切换到此标签
            if (top_tab.getChild(typeId)) {
                top_tab.setSelected(top_tab.getChild(typeId));
                return;
            }
        }
        var selectAll = "<li typeId=" + typeId + " typeDivId=" + typeId + addDiv + " >&nbsp;&nbsp;&nbsp;&nbsp;<label>全选<input type='radio' value='1' name='selectAll' typeId=" + typeId + " typeDivId=" + typeId + addDiv + "></label>&nbsp;&nbsp;&nbsp;&nbsp;<label>全不选<input type='radio' value='0' name='selectAll' typeId=" + typeId + " typeDivId=" + typeId + addDiv + "></label>&nbsp;&nbsp;&nbsp;&nbsp;<label>反选<input type='radio' value='2' name='selectAll' typeId=" + typeId + " typeDivId=" + typeId + addDiv + "></label>";
        if (typeId == -99) {
            selectAll +="&nbsp;&nbsp;<span>添加年龄段：</span>"+addSJD()+"&nbsp;&nbsp;-&nbsp;&nbsp;"+addSJD();
            selectAll +="&nbsp;&nbsp;&nbsp;&nbsp;<a id='addAgeBtn'>添加</a>";
        }
        selectAll += "</li><hr/>";
        if (typeId == -99 || typeId == 800) {
            for(var index in data)
            {
                selectAll += "<li typeId=" + typeId + " typeDivId=" + typeId + addDiv + " style='float:left;width:20%'>&nbsp;&nbsp;<label><input type='checkbox' value=" + data[index] + " name="+checkBoxName+" checked typeId=" + typeId + ">" + data[index] + "</label>&nbsp;&nbsp;</li>";
            }
        } else {
            for (var i = 0; i < data.length; i++) {
                var str = data[i].id;
                if (typeId == 1) {
                    str = data[i].courtCode;
                }
                selectAll += "<li typeId=" + typeId + " typeDivId=" + typeId + addDiv + " style='float:left;width:20%'>&nbsp;&nbsp;<label><input type='checkbox' value=" + str + " name="+checkBoxName+" checked typeId=" + typeId + ">" + data[i].codeName + "</label>&nbsp;&nbsp;</li>";
            }
        }

        //判断是上标题还是下标题
        if (addDiv === "#topAddDiv") {
            var item = top_tab.addChild({title: typeText, panelContent: selectAll, id: typeId, columnName: columnName});
            top_tab.setSelected(item);
        } else {
            $(addDiv).append(selectAll);
            $(addDiv).show();
        }
        checkAll(typeId + addDiv);
        $("input[typeDivId='" + typeId + addDiv + "']").eq(0)[0].checked = true;

        if (typeId == -99) {
            $("#addAgeBtn").off('click').on('click', function () {
                var start = parseInt($($(".sjd")[0]).val());
                var end = parseInt($($(".sjd")[1]).val());
                if (start < end) {
                    $('li[typeDivId=' + typeId + addDiv + ']>label>input[type=checkbox]').parent().parent().remove();
                    var sjd = $($(".sjd")[0]).val() + "-" + $($(".sjd")[1]).val();
                    var sjd_start = parseInt($($(".sjd")[0]).val());
                    var sjd_end = parseInt($($(".sjd")[1]).val());
                    addNewNND(sjd_start, sjd_end, nnd_outer);
                    var html = "";
                    for (var index in nnd_outer) {
                        html += "<li typeId=" + typeId + " typeDivId=" + typeId + addDiv + " style='float:left;width:20%'>&nbsp;&nbsp;<label><input type='checkbox' value=" + nnd_outer[index] + " name="+checkBoxName+" checked typeId=" + typeId + ">" + nnd_outer[index] + "</label>&nbsp;&nbsp;</li>";
                    }
                    $('li[typeDivId=' + typeId + addDiv + ']').next().after(html);
                    checkAll(typeId + addDiv);
                }
                else {
                    BUI.Message.Alert("输入有误，请重新输入！", "error");
                }
            });
        }

    }

    //刷新标题选项 disabled 的字段
    function refreshAttr(id) {
        var thatId = id == "leftColumn" ? "topColumn" : "leftColumn";
        var obj = $('#' + thatId);
        if (thatId == 'leftColumn') {
            obj.find('option[disabled]').removeAttr('disabled');
            for (var i in top_columns) {
                obj.find('option[value=' + top_columns[i] + ']').attr('disabled', 'disabled');
            }
        } else {
            obj.find('option[disabled]').removeAttr('disabled');
            obj.find('option[value=' + $('#leftColumn').val() + ']').attr('disabled', 'disabled');
        }
        obj.select2();
    }


    var startRow = 0;
    var dataStore;
    var dataDialog;
    function makeBUIGrid ($obj){

        if(!dataDialog){
            initDataBUI();
        }

        var leftColumn = $obj.data("leftcolumn");
        var leftValue = $obj.data("leftvalue");
        var topColumn = $obj.data("topcolumn");
        var topValue = $obj.data("topvalue");
        var topCodeNeed = $obj.data("topcodeneed");
        var leftCodeNeed = $obj.data("leftcodeneed");
        var fydmList = $obj.data("fydmlist");

        var condition = {
            "queryEntity.leftColumn" : leftColumn,
            "queryEntity.leftValue" : leftValue,
            "queryEntity.topColumn" : topColumn,
            "queryEntity.topValue" : topValue,
            "queryEntity.topCodeNeed" : topCodeNeed,
            "queryEntity.leftCodeNeed" : leftCodeNeed,
            "queryEntity.fydmList" : fydmList,
            "queryEntity.define" : 1,
            "userType" : 3,
            "start" : 0,
            "pageIndex" : 0
        };
        dataStore.load(condition);
        dataDialog.show();

    }

    function initDataBUI(){


        BUI.use(['common/search', 'bui/tree', 'bui/data', 'bui/calendar', 'bui/overlay', 'bui/form', 'bui/uploader', 'bui/mask', 'bui/grid'],
            function (Search, Tree, Data, Calendar, Overlay, Form, Uploader, Mask, Grid) {

                //定义表格列
                var columns = [
                    {
                        title: '序号', sortable: false, width: "60", align: 'left', renderer: function (value, obj) {
                            return startRow++;
                        }
                    },
                    {title: '姓名', dataIndex: 'fullname', width: "100", sortable: true, align: 'left'},
                    {title: '性别', dataIndex: 'genderText', width: "50", sortable: true, align: 'left'},
                    {
                        title: '年龄', width: "80", align: 'center', renderer: function (value, obj) {
                            if (obj.birthday != null) {
                                var calcYears2 = calcYears(obj.birthday);
                                if (!isNaN(calcYears2)) {
                                    return calcYears2 + "岁";
                                }
                            }
                        }
                    },
                    {title: '法院', dataIndex: 'courtNoText', width: "200", sortable: true, align: 'left'},
                    {title: '部门', dataIndex: 'departmentText', width: "150", sortable: true, align: 'left'},

                ];

                //定义表格样式
                var gridCfg = Search.createGridCfg(columns, {
                    width: "100%",
                    height: 390,
                    //forceFit: true,
                    emptyDataTpl: '<div class="centered"><h2>查询的数据不存在</h2></div>',
                    plugins: [ BUI.Grid.Plugins.RadioSelection, BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格
                });

                var url = "<%=basePath%>chart/fc";
                dataStore = Search.createStore(url, {
                    remoteSort: true,
                    pageSize: 20,
                    params: {

                    },
                });

                var search = new Search({
                    store: dataStore,
                    gridCfg: gridCfg
                });
                var dataGrid = search.get('grid');

                dataGrid.on("beforeitemsshow",function(){
                    var start = dataStore.get("start");
                    startRow = start + 1;
                });


                //申请调职
                dataDialog = new Overlay.Dialog({
                    title: '人员信息',
                    width: "90%",
                    height: 500,
                    buttons: [{
                        text: '关闭', elCls: 'button', handler: function () {
                            this.close();
                        }
                    }],
                    contentId: 'data-dialog' //配置DOM容器的编号
                });

            })

    }

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

    function showMask() {
        if (loadMask == null) {
            BUI.use(['bui/mask'], function (Mask) {
                loadMask = new Mask.LoadMask({
                    el: '#custom_form',
                    msg: 'loading ....'
                });
            });
        }
        loadMask.show();
    }
    function hideMask() {
        loadMask.hide();
    }

    function encodeParamByStr(str){
        if(!str){
            return "";
        }
        return encodeParam(str);
    }
</script>
<script src="js/stat.js" type="text/javascript"></script>
</body>
</html>
