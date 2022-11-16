<%--
  Created by IntelliJ IDEA.
  User: huise
  Date: 16/5/19 0019
  Time: 上午 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@page import="cn.net.withub.ums.common.UmsConstant" %>
<%@page import="cn.net.withub.ums.entity.UmsUserInfoView" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    UmsUserInfoView userInfo = (UmsUserInfoView)request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
    boolean isSuperUser = false;
    if(userInfo.getId().equals("-1")){
        isSuperUser = true;
    }
%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编外人员自定义查询</title>
</head>
<style>
    [type3=selected] {
        background-color: #428BCA;
        color: #FFFFFF
    }
    label{
        cursor: pointer;
    }
    input[type=radio]{
        cursor: pointer;
    }
    select{
        cursor: pointer;
    }
    .x-align-bl-tl{width:250px !important;height:290px;overflow:auto;}
    #so input {height:30px; width: 170px !important;}
    #so i {margin-top:10px;}
</style>
<jsp:include page="/basic_import.jsp"></jsp:include>
<script type="text/javascript" src="<%=basePath%>/js/encodeParam.js"></script>
<script type="text/javascript">
    var soSelect = null;
    var soList = [];

    $(document).ready(function () {
        var arr = new Array("学历学位", "证件信息", "编外人员信息",
                "公司信息");
//                "家庭信息", "考核信息", "奖励信息", "惩处信息",
//                "出国信息", "外语信息", "交流信息", "司法考试",
//                "专业技术职务", "伤亡信息", "备注信息",
//                "声音与影像", "工资信息", "后备干部", "通讯录",
//                "领导班子", "公务员");
        var i = 0;
        $("#list1").append("<li data-value='-1' class='bui-list-item-selected'>人员基本信息</li>");
        $.each(arr, function () {
            i++;
            $("#list1").append("<li data-value='" + i + "'>" + this + "</li>");
        });
        getList2(-1);

        BUI.use(['bui/overlay', 'bui/form', 'bui/calendar', 'bui/extensions/treepicker', 'bui/tree', 'bui/list', 'bui/select'], function (Overlay, Form, Calendar, TreePicker, Tree, List, Select) {

            var list = new List.SimpleList({
                elCls: 'bui-select-list',//默认是'bui-simple-list'
                width: 180,
                height: 290,
                srcNode: '#list1',
            });
            list.render();
            list.on('itemclick', function (ev) {
                getList2(ev.item.value);
                list2.render();
            });

            var list2 = new List.SimpleList({
                elCls: 'bui-select-list',//默认是'bui-simple-list'
                width: 200,
                srcNode: '#list2'
            });
            list2.render();

            var form = new Form.HForm({
                srcNode: '#form'
            }).render();

            var dialog = new Overlay.Dialog({
                title: '添加条件',
                width: 400,
                height: 280,
                contentId: 'content',
                buttons: [{
                    text: '添加',
                    elCls: 'button button-primary',
                    handler: function () {
                        //获取最终选项的节点类型，如果是input则获取input['#so']的值,否则获取input['soval']的值
                        var soInput = $("#so")[0].tagName == "INPUT";
                        if ((soInput && $("#so").val() == "") || $("#soVal").val() == "") {
                            error2.show();
                            return;
                        }
                        $("#list3").append("<li style='list-style-type:none;' >并且</li>");
                        var aa = soInput ? $("#so").val() : $('#so div input').val();
                        //显示
                        $("#list3").append("<li style='list-style-type:none;padding-left: 20px;' class='sub'>"
                                + $('#list2 .bui-list-item-selected').text() + " " + $("#tj option:selected").text() + ' "' + aa + '" [' + $('#list1 .bui-list-item-selected').text() + "] </li>");
                        //隐藏域
                        var term4 = soInput ? $("#so").val() : $("#soVal").val();
                        $("#list3").append("<input sub='sub' term1='and' term2='" + $('#list2 .bui-list-item-selected').attr("data-value") + "'" +
                        " term3='" + $("#tj option:selected").val() + "' term4='" + term4 + "' hidden>");
                        this.close();
                    }
                }, {
                    text: '关闭',
                    elCls: 'button',
                    handler: function () {
                        this.close();
                    }
                }]
            });

            //点击添加时的弹出框
            $('#addshow').on('click', function () {
                var a = $("#list2 .bui-list-item-selected").attr("data-value");
                if (a == null) {
                    error.show();
                    return;
                }

                //获取判断条件
                $.post("<%=basePath%>chart/getAddList", {list2Id: a,userFullName:userFullName, courtNoText:courtNoText}, function (data) {//将登陆的账号是什么管理员，哪个法院的当做参数传到后端
                <%--$.post("<%=basePath%>chart/getAddList", {list2Id: a,gradation:$("#fyjb").val()}, function (data) {--%>
                    $("#form").empty();
                    $("#form").append("<select id='tj' onchange='tjChange();'></select><br><br>");
                    //生成条件下拉框
                    $.each(data[0], function (key, value) {
                        var selected = "";
                        //默认选中'等于'
                        if (value == "等于") {
                            selected = "selected='selected'";
                        }
                        $("#form select").append(" <option value='" + key + "'" + selected + ">" + value + "</option>");
                    });

                    var type_id = $("#list2 .bui-list-item-selected").attr("type_id");
                    if (type_id != null) {
                        $("#form").append("<div id='so'></div><input id='soVal' class='hide'>");
                        soList = [];

                        if (type_id =='601'){
                            var val = $("#courtInput").val();
                            if(!val){
                                alert("请先选择一家法院");
                                return;
                            }

                            var courtArr = val.split(',');
                            if(courtArr.length != 1){
                                alert("请先选择一家法院,不支持多家法院");
                                return ;
                            }


                            $.post("<%=basePath%>code/deptByCourtNo", {fydm: courtArr[0]}, function (data) {
                                $.each(data, function (key, value) {
//                                    $("#so").append("<option value=" + value.deptNo + ">" + value.deptName + "</option>");
//                                     soList.push({text: value.deptName, value: value.deptNo}) //修改成下面orgCode20180423 department 改成 orgCode
                                    soList.push({text: value.deptName, value: value.orgCode})
                                });
                                //默认渲染单选框
                                renderSoOption(soList, false);
                            });
                        }else if (type_id =='00001'){

                            soList.push({text: "是", value: "1"});
                            soList.push({text: "否", value: "0"});
                            //默认渲染单选框
                            renderSoOption(soList, false);
                        }else if (type_id =='company_info_id'){

                            $.post("<%=basePath%>external/company/findAll",{},function(data){
                                if(data){
                                    $.each(data,function(key,value){
                                        soList.push({text: value.text, value: value.value})
                                    });
                                    //默认渲染单选框
                                    renderSoOption(soList, false);
                                }

                            });

                        }else if (type_id =='enter_src'){
                            //编外人员进入来源
                            $.post("<%=basePath%>external/getEnterSrc",{},function(data){
                                if(data){
                                    $.each(data,function(key,value){
                                        soList.push({text: value.srcTypeName, value: value.id})
                                    })
                                    //默认渲染单选框
                                    renderSoOption(soList, false);
                                }

                            });

                        } else {
                            $.post("<%=basePath%>code/codeListByType", {typeId: type_id}, function (data) {
                                $.each(data, function (key, value) {
//                                    $("#so").append("<option value=" + value.id + ">" + value.codeName + "</option>");
                                    soList.push({text: value.codeName, value: value.id})
                                });
                                //默认渲染单选框
                                renderSoOption(soList, false);
                            });
                        }
//                        20170626 每个选项都能多选，不移除'包含'选项
//                        $("#tj option[value='in']").remove();
                        $("#tj option[value='like']").remove();
                    } else {
                        $("#form").append("<input id='so' type='text' style='height: auto;width: 300px'>");
                    }

                    //如果是日期，返回值会有data[1]的值且为date
                    for (var key in data[1]) {
                        if ('date' == data[1][key]) {
                            $("#form input").attr("class", "calendar");
                            $("#form input").attr("readonly", "readonly");
                            var datepicker = new Calendar.DatePicker({
                                trigger: '.calendar'
                            }).render();
                        }
                    }

                    //类型为法院时显示法院树
                    if (a == "court_no_text") {
                        $("#form input").attr("class", "tree");
                        $("#form input").attr("readonly", "readonly");
                        //异步树选择数据
                        var dat = data[1][0];
                        var tree = new Tree.TreeList({
                            nodes: dat,
                            checkType: 'all',
                            cascadeCheckd : false, //不级联勾选
                            showLine: true //显示连接线
                        });

                        var picker = new TreePicker({
                            trigger: '.tree',
                            valueField: '#courtInput', //如果需要列表返回的value，放在隐藏域，那么指定隐藏域
                            selectStatus: 'checked',//设置勾选作为状态改变的事件
                            // filter: function (node) { //过滤非叶子节点
                            //     return node.leaf;
                            // },
                            width: 300,  //指定宽度
                            children: [tree] //配置picker内的列表
                        });
                        picker.render();
                    }
                    dialog.show();
                });
            });

            var error = new Overlay.Dialog({
                title: '提示',
                width: 250,
                height: 120,
                buttons: [{
                    text: '关闭',
                    elCls: 'button',
                    handler: function () {
                        this.close();
                    }
                }],
                bodyContent: '请选择条件字段'
            });
            var error2 = new Overlay.Dialog({
                title: '提示',
                width: 250,
                height: 120,
                buttons: [{
                    text: '关闭',
                    elCls: 'button',
                    handler: function () {
                        this.close();
                    }
                }],
                bodyContent: '请输入条件！'
            });

            //组装sql
            $("[type='submit']").on('click', function () {
                var submit = "";
                var fjtj = getFjtj();//附加条件

                $("[sub='sub']").each(function () {
                    var c1 = $(this).attr("term1");
                    var term2q = $(this).attr("term2");
                    var c2;
                    if(term2q == "enter_src" || term2q == "company_info_id"){
                        c2 = " c."+term2q;
                    }else{
                        c2 = " a."+term2q;
                    }
                    // c2 = " a."+term2q;
                    var c3 = $(this).attr("term3");
                    var c4 = $(this).attr("term4");

                    if (submit == ""){
                        c1 =" and ";
                    }

                    if (c1 == "or"){
                        c1 = " and ( 1=1 or ";
                    }

                    if (c3 == 'is null') {
                        submit += " " + c1 + " " + c2 + " " + c3 + " ";
                    } else if (c3 == 'in') {
                        if (c2 == ' a.court_no_text') {
                            arr = $("#courtInput").val().split(',');
                            submit += " " + c1 + " a.court_code in ( ";
                            for (var a = 0;a<arr.length;a++) {
                                submit += "'" + arr[a] + "',";
                            }
                            submit = submit.substring(0, submit.length - 1) + " ) ";
                        } else {
                            arr = c4.split(',');
                            submit += " " + c1 + " " + c2 + " in ( ";
                            for (var a = 0;a<arr.length;a++) {
                                submit += "'" + arr[a] + "',";
                            }
                            submit = submit.substring(0, submit.length - 1) + " ) ";
                        }
                    } else if (c3 == "like") {
                        if (c2 == ' a.court_no_text') {
                            arr = $("#courtInput").val().split(',');
                            submit += " " + c1 + " (" + c3 + " '%" + arr[0] + "%' ";
                            for (var a = 1;a<arr.length;a++) {
                                submit += "  or a.court_code " + c3 + " '%" + arr[a] + "%' ";
                            }
                            submit += " ) ";
                        } else {
                            submit += " " + c1 + " " + c2 + " " + c3 + " '%" + c4 + "%' ";
                        }
                    } else {
                        if (c2 == ' a.court_no_text') {
                            arr = $("#courtInput").val().split(',');
                            submit += " " + c1 + " ( a.court_code " + c3 + " '" + arr[0] + "' ";
                            for (var a = 1;a<arr.length;a++) {
                                submit += " or a.court_code " + c3 + " '" + arr[a] + "' ";
                            }
                            submit += " ) ";
                        } else {
                            submit += " " + c1 + " " + c2 + " " + c3 + " '" + c4 + "' ";
                        }
                    }

                    if (c1 == " and ( 1=1 or "){
                        submit += " ) ";
                    }

                });
                if (submit == "") {
                    error2.show();
                    return;
                }
                console.log(fjtj+submit);
                var pasxx = fjtj + submit;
                var encodeStr = encodeParam(pasxx);
                $("#redirect input[name='redi']").val(encodeStr);
                $("#redirect input[name='userType']").val(2);
                $("#redirect").submit();
            });

        });

    });



    //渲染最终条件选择框，切换条件'等于','包含'时析构并重新渲染
    function renderSoOption(list, multi) {
        $('#so').empty();
        $('#soVal').val('');
        if (soSelect != null) soSelect.destroy();
        BUI.use(['bui/select'], function (Select) {
            soSelect = new Select.Select({
                width: 200,
                render: '#so',
                valueField:'#soVal',
                multipleSelect: multi,
                items: list
            });
            soSelect.render();
        });
    }

    //列表1选中后出列表2的选项
    function getList2(a) {
        $.post("<%=basePath%>chart/bwGetList2", {list1Id: a}, function (data) {
            $("#list2").empty();
            $.each(data[0], function (key1, value1) {
                $("#list2").append("<li class='bui-list-item' data-value='" + key1 + "' >" + value1 + "</li>");
            });
            $("#list2 .bui-list-item").each(function () {
                var key = $(this).attr("data-value");
                $.each(data[1], function (key1, value1) {
                    if (key1 == key) {
                        $("[data-value=" + key + "]").attr("type_id", value1);
                    }
                });
            });
        });
    }
    $("#list2 li").live('click', function () {
        $("#list2 li").attr("class", "bui-list-item");
        $(this).attr("class", "bui-list-item bui-list-item-selected");
    });

    $(".sub").live('click', function () {
        $("#list3 li").attr("type3", "");
        $(this).attr("type3", "selected");
    })


    function changee() {
        var a = $("[type3=selected]").prev();
        if (a.text() == '并且') {
            a.text("或者");
            $("[type3=selected]").next().attr("term1", "or");
        } else {
            a.text("并且");
            $("[type3=selected]").next().attr("term1", "and");
        }
    }
    function delet() {
        $("[type3=selected]").prev().remove();
        $("[type3=selected]").next().remove();
        $("[type3=selected]").remove();
    }

    function tjChange(){
        if ($("#tj").find("option:selected").text() == '为空'){
            $("#so").hide();
        } else if ($("#tj").find("option:selected").text() == '等于'){
            $("#so").show();
            //重新渲染单选框
            renderSoOption(soList, false);
        } else if ($("#tj").find("option:selected").text() == '包含'){
            $("#so").show();
            //重新渲染多选框
            renderSoOption(soList, true);
        } else {
            $("#so").show();
        }
    }
</script>
<body>

<div class="panel">
    <div class="panel-header">
        <h3>编外人员自定义查询 -- <button href="javascript:void(0)" onclick="saveEdit_grid_dialog.show()" class="button button-primary search">点击查询已保存搜索</button></h3>
    </div>
    <div class="panel-body" style="height: 80%">

        <div style="width: 100%;height: 40px">
            <div style="float: right;padding-right: 80px">
                <%--法院级别 ：--%>
                <%--<select id="fyjb">--%>
                    <%--&lt;%&ndash;<option value="-1">不限制</option>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<option value='0'>高级法院</option>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<option value='1'>中级法院</option>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<option value='2'>基层法院</option>&ndash;%&gt;--%>
                <%--</select>--%>
                <% if(isSuperUser){%>
                <span>搜索范围：&nbsp;&nbsp;</span>
                <label for="courtlevel">
                    <input type="radio" name="countStyle" id="courtlevel"  value="1" checked />
                    <span>法院级别</span>
                </label>
                &nbsp;&nbsp;
                <label for="courtName">
                    <input type="radio" name="countStyle" id="courtName"  value="0"  />
                    <span>法院名称</span>
                </label>
                &nbsp;&nbsp;
                <select id="countStyle">
                    <option value="">无限制</option>
                    <option value="0">高院</option>
                    <option value="1">中院</option>
                    <option value="2">基层法院</option>
                </select>
                <br/>
                <br/>
                <%}%>
                <%--<span>所属编制：&nbsp;&nbsp;</span>--%>
                <%--<select id="preparation">--%>
                    <%--<option value="">无限制</option>--%>
                    <%--<option value="1">中央行政编制</option>--%>
                    <%--<option value="2">地方事业编制</option>--%>
                    <%--<option value="3">纪委派驻</option>--%>
                <%--</select>--%>
            </div>
        </div>
        <div id="list1" style="float: left">
        </div>
        <div style="width:230px;height:290px;overflow:auto;float: left;padding-left: 10px">
            <ul id="list2" class="bui-simple-list bui-select-list">
            </ul>
        </div>
        <div style="float: left;padding-left: 10px">
            <button id="addshow" class="button">添加</button>
        </div>
        <div id="content" hidden>
            <div id="form" class="control-group">
            </div>
        </div>
        <div style="float: left;padding-left: 10px;width: 300px;height:290px;background-color: #f5f5f5">
            <ul id="list3">
            </ul>
        </div>
        <div style="float: left;padding-left: 10px">
            <div>
                <input type="submit" class="button" value="查询">
            </div>
            <br>
            <div>
                <input type="button" class="button" onclick="changee()" value="并且/或者">
            </div>
            <br>
            <div>
                <input type="button" class="button" onclick="delet()" value="删除">
            </div>
        </div>
        <div hidden>
            <form id="redirect" action="customInfo.jsp">
                <input name="redi">
                <input name="userType">
            </form>
        </div>

        <input id="courtInput" style="display: none">

    </div>
</div>
<jsp:include page="statisticsList.jsp?userType=2"></jsp:include>
<script>
    var userFullName = "<%=userInfo.getFullname()%>";//账户全称
    var courtNoText = "<%=userInfo.getCourtNoText()%>";//法院全称
    var courtList;
    var isSuperUser = <%=isSuperUser%>;
    var userCourtCode = "<%=userInfo.getCourtCode()%>"
    $("input[name=countStyle]").click(function() {
        var countStyle = $("input[name='countStyle']:checked").val();
        if(countStyle == '1')
        {
            var html = "";
            html += "<option value=''>无限制</option>";
            html += "<option value='0'>高院</option>";
            html += "<option value='1'>中院</option>";
            html += "<option value='2'>基层法院</option>";
            $("#countStyle").empty();
            $("#countStyle").append(html);
        }
        else
        {
            $("#countStyle").empty();
            $("#countStyle").append($("<option>").attr({"value": ""}).text("请选择"));
            if(!courtList)
            {
                $.post("<%=basePath%>/code/codeListByType", {typeId: 1}, function (data) {
                    courtList = data.data;
                    for (var i = 0; i < data.data.length; i++) {
                        $("#countStyle").append($("<option>").attr({"value": data.data[i].courtCode}).text(data.data[i].codeName));
                    }

                });
            }
            else
            {
                for (var i = 0; i < courtList.length; i++) {
                    $("#countStyle").append($("<option>").attr({"value": courtList[i].courtCode}).text(courtList[i].codeName));
                }
            }

        }
    });
    function getFjtj()
    {
        var countStyle = $("input[name=countStyle]:checked").val();
        var courtLevel = "";
        var courtCode = "";
        // var preparation = $("#preparation").val();
        if(isSuperUser)
        {
            if(countStyle == 1)
                courtLevel = $("#countStyle").val();
            else if(countStyle == 0)
                courtCode = $("#countStyle").val();
        }
        else
        {
            courtCode = userCourtCode;
        }
        var returnStr = "";
        if(courtLevel != "")
            returnStr +=  " and b.court_gradation = " + courtLevel;
        if(courtCode != "")
            returnStr += " and a.court_code = '" + courtCode + "' ";
        // if(preparation != "")
        // {
        //     if(preparation == "1")
        //         returnStr += " and a.preparation = 1 ";
        //     if(preparation == "2")
        //         returnStr += " and ( a.preparation in (2,4,5,8) or a.preparation is null) ";
        //     if(preparation == "3")
        //         returnStr += " and a.preparation = 3 ";
        // }
        returnStr += " and a.user_type = 2";
        return returnStr;
    }
</script>
</body>
</html>
