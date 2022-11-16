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
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    UmsUserInfoView userInfo = (UmsUserInfoView) request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
    boolean isSuperUser = false;
    if (userInfo.getId().equals("-1")) {
        isSuperUser = true;
    }
    //    特殊处理 （莫运垠moyy/admin）2018年10月16日 该账号需要在自定义信息查询在编人员时，能够选择法院，这里进行了特殊处理
    String username = userInfo.getUsername();
    String fullname = userInfo.getFullname();
    if (username.equals("moyy") && fullname.equals("莫运垠")) {
        userInfo.setFullname("高院管理员");//该账号在登陆时，会显示为欢迎您，高院管理员
    }
//    特殊处理结束
%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>人员信息自定义查询</title>
    <meta http-equiv="cache-control" content="no-cache">
    <script type="text/javascript" src="<%=basePath%>/js/encodeParam.js"></script>
    <jsp:include page="/basic_import.jsp"></jsp:include>
    <jsp:include page="statisticsList.jsp"></jsp:include>
    <style>
        [type3=selected] {
            background-color: #428BCA;
            color: #FFFFFF
        }

        label {
            cursor: pointer;
        }

        input[type=radio] {
            cursor: pointer;
        }

        select {
            cursor: pointer;
        }

        .x-align-bl-tl {
            width: 250px !important;
            height: 290px;
            overflow: auto;
        }

        #so input {
            height: 30px;
            width: 170px !important;
        }

        #so i {
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="panel">
    <div class="panel-header">
        <h3>人员信息自定义查询 --
            <button href="javascript:void(0)" onclick="saveEdit_grid_dialog.show()"
                    class="button button-primary search">点击查询已保存搜索
            </button>
        </h3>
    </div>
    <div class="panel-body" style="height: 80%">
        <div style="width: 100%;height: 40px">
            <div style="float: right;padding-right: 80px">
                <% if (isSuperUser) {%>
                <span>搜索范围：&nbsp;&nbsp;</span>
                <label for="courtlevel">
                    <input type="radio" name="countStyle" id="courtlevel" value="1" checked/>
                    <span>法院级别</span>
                </label>
                &nbsp;&nbsp;
                <label for="courtName">
                    <input type="radio" name="countStyle" id="courtName" value="0"/>
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
                <span>所属编制：&nbsp;&nbsp;</span>
                <select id="preparation">
                    <option value="">无限制</option>
                    <option value="1">中央行政编制</option>
                    <option value="2">地方事业编制</option>
                    <option value="3">纪委派驻</option>
                </select>
            </div>
        </div>
        <div id="list1" style="float: left">
        </div>
        <div style="width:230px;height:290px;overflow:auto;float: left;padding-left: 10px">
            <select id="list2"></select>
        </div>
        <div style="float: left;padding-left: 10px">
            <button id="addshow" class="button">添加</button>
        </div>
        <div id="content" hidden>
            <div id="form" class="control-group">
            </div>
        </div>
        <div style="overflow: auto;float: left;padding-left: 10px;width: 300px;height:290px;background-color: #f5f5f5">
            <ul id="list3"></ul>
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
            </form>
        </div>
        <input id="courtInput" style="display: none">
    </div>
</div>
</body>


<script type="text/javascript" src="js/pinyin.js"></script>
<script type="text/javascript">
    var soSelect = null;
    var suggest = null;
    var soList = [];
    var tree;
    // 用于select2下拉框的数据
    var select2_data = [];
    // 是否有权限
    var sfyqx;

    $(function () {
        // 获取角色的权限
        $.post("<%=basePath%>auth/hasRole", {rolename: "超级管理员"}, function (data) {
            sfyqx = data;
        });

        var arr = new Array("政治面貌", "行政职务", "法律职务", "职级信息", "兼任职务", "等级信息", "学历信息", "学位信息", "简历信息", "标准部门");
        var i = 0;
        $("#list1").append("<li data-value='-2' class='bui-list-item-selected'>全部分类</li>");
        $("#list1").append("<li data-value='-1'>人员基本信息</li>");
        $.each(arr, function () {
            i++;
            $("#list1").append("<li data-value='" + i + "'>" + this + "</li>");
        });

        BUI.use(['bui/overlay', 'bui/form', 'bui/calendar', 'bui/extensions/treepicker', 'bui/tree', 'bui/list', 'bui/grid'], function (Overlay, Form, Calendar, TreePicker, Tree, List, Grid) {
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
                }),
                error2 = new Overlay.Dialog({
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
                }),
                list = new List.SimpleList({
                    elCls: 'bui-select-list',
                    width: 180,
                    height: 316,
                    srcNode: '#list1',
                }),
                form = new Form.HForm({
                    srcNode: '#form'
                }).render(),
                dialog = new Overlay.Dialog({
                    title: '添加条件',
                    width: 400,
                    height: 280,
                    contentId: 'content',
                    buttons: [{
                        text: '添加',
                        elCls: 'button button-primary',
                        handler: function () {
                            // select2下拉框的id和text
                            var select2_data_id_value;
                            // select2下拉框的value
                            var ids = [];
                            // select2下拉框的text
                            var texts = [];
                            // 如果是部门的下拉框有值
                            if ($('#so_select2').length > 0) {
                                // select2选择框的值
                                select2_data_id_value = $('#so_select2').select2('data');
                                // 获取text 和 id
                                $.each(select2_data_id_value, function (a, b) {
                                    ids.push(b.id);
                                    texts.push(b.text);
                                });
                            }
                            // 获取最终选项的节点类型，如果是input则获取input['#so']的值,否则获取input['soval']的值
                            var soInput = $("#so")[0].tagName == "INPUT";
                            if (null != select2_data_id_value || "" != select2_data_id_value) {

                            } else if ((soInput && $("#so").val() == "") || $("#soVal").val() == "") {
                                // 判断首选选择 为空 的条件
                                if ($("#tj").find('option:selected').val() != "is null") {
                                    error2.show();
                                    return;
                                }
                            }
                            $("#list3").append("<li style='list-style-type:none;' >并且</li>");
                            var aa = soInput ? $("#so").val() : $('#so div input').val();
                            if (aa == null || aa == "") {
                                // 如果文本框没有值，看select2的文本框
                                aa = texts;
                            }
                            // 将选择后的条件，显示在页面
                            $("#list3").append("<li style='list-style-type:none;padding-left: 20px;' class='sub'>" + $('#list2 option:selected').text() + " " + $("#tj option:selected").text() + ' "' + aa + '" [' + $('#list1 .bui-list-item-selected').text() + "] </li>");
                            // 隐藏域，用来赋值组成sql
                            var term4 = soInput ? $("#so").val() : $("#soVal").val();
                            if (term4 == "" || term4 == null) {
                                // 如果value没有值，看select2的value
                                term4 = ids;
                            }
                            $("#list3").append("<input sub='sub' term1='and' term2='" + $('#list2 option:selected').attr("value") + "'" + " term3='" + $("#tj option:selected").val() + "' term4='" + term4 + "' term5='" + $('#list2 option:selected').attr("child_sql") + "' hidden>");
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
            list.render();
            // 初始化，根据list1 获取list2
            getList2(-2);
            // 监听list的点击事件
            list.on('itemclick', function (ev) {
                // 根据list1 获取list2
                getList2(ev.item.value);
            });

            // 点击添加时的弹出框
            $('#addshow').on('click', function () {
                var a = $("#list2 option:selected").attr("value");
                if (a == null) {
                    error.show();
                    return;
                }
                // 获取判断条件
                $.post("<%=basePath%>chart/getAddList", {
                    list2Id: a,
                    userFullName: userFullName,
                    courtNoText: courtNoText
                }, function (data) {//将登陆的账号是什么管理员，哪个法院的当做参数传到后端
                    $("#form").empty();
                    $("#form").append("<select id='tj' onchange='tjChange();'></select><br><br>");
                    // 生成条件下拉框
                    $.each(data[0], function (key, value) {
                        var selected = "";
                        //默认选中'等于'
                        if (value == "等于") {
                            selected = "selected='selected'";
                        }
                        $("#form select").append(" <option value='" + key + "'" + selected + ">" + value + "</option>");
                    });
                    var type_id = $("#list2 option:selected").attr("type_id");
                    if (type_id != null || a == "dept_org_code") {
                        $("#form").append("<div id='so'></div><input id='soVal' class='hide'>");
                        soList = [];
                        if (a == "dept_org_code" && null == type_id) {
                            // 这个是针对标准部门的（查询的是xtpt_bm_departmernt, 对应ums_user_info表的department字段）
                            $.post("<%=basePath%>deptAction/getBmDept", {}, function (data) {
                                $.each(data.data, function (key, value) {
                                    // 这里用orgCode，是针对的标准部门
                                    soList.push({text: value.deptName, id: value.orgCode});
                                });
                                // 初始化一次，清除上一次加载的数据
                                select2_data = [];
                                select2_data.push({"children": soList, "text": ""});
                                //默认渲染单选框
                                renderSoOptionForMulti(select2_data, false);
                            });
                        } else {
                            if (type_id == '601') {
                                // 针对部门，获取下拉框数据
                                var val = $("#courtInput").val();
                                if (!val) {
                                    alert("请先选择一家法院");
                                    return;
                                }
                                var courtArr = val.split(',');
                                if (courtArr.length != 1) {
                                    alert("请先选择一家法院,不支持多家法院");
                                    return;
                                }
                                $.post("<%=basePath%>code/deptByCourtNo", {fydm: courtArr[0]}, function (data) {
                                    $.each(data, function (key, value) {
                                        // 这里用deptNo，是针对的法院内部的部门
                                        soList.push({text: value.deptName, id: value.deptNo});
                                    });
                                    // 初始化一次，清除上一次加载的数据
                                    select2_data = [];
                                    // 赋值给select2_data，用于组装成select2需要的数据格式
                                    select2_data.push({"children": soList, "text": ""});
                                    // 默认渲染单选框
                                    renderSoOptionForMulti(select2_data, false);
                                });
                            } else if (type_id == '00001') {
                                soList.push({text: "是", id: "1"});
                                soList.push({text: "否", id: "0"});
                                select2_data.push({"children": soList, "text": ""});
                                // 初始化一次，清除上一次加载的数据
                                select2_data = [];
                                // 默认渲染单选框
                                // renderSoOptionForMulti(select2_data, false);
                                renderSoOptionForMulti(soList, false);
                            } else {
                                $.post("<%=basePath%>code/codeListByType", {typeId: type_id}, function (data) {
                                    $.each(data, function (key, value) {
                                        soList.push({text: value.codeName, id: value.id});
                                    });
                                    // 初始化一次，清除上一次加载的数据
                                    select2_data = [];
                                    // 赋值给select2_data，用于组装成select2需要的数据格式
                                    select2_data.push({"children": soList, "text": ""});
                                    // 默认渲染单选框
                                    renderSoOptionForMulti(select2_data, false);
                                });
                            }
                        }
                        $("#tj option[value='like']").remove();
                    } else {
                        $("#form").append("<input id='so' type='text' style='height: auto;width: 300px'>");
                    }
                    // 如果是日期，返回值会有data[1]的值且为date
                    for (var key in data[1]) {
                        if ('date' == data[1][key]) {
                            $("#form input").attr("class", "calendar");
                            $("#form input").attr("readonly", "readonly");
                            var datepicker = new Calendar.DatePicker({
                                trigger: '.calendar'
                            }).render();
                        }
                    }
                    // 类型为法院时显示法院树
                    if (a == "court_no_text") {
                        $("#form input").attr("class", "tree");
                        $("#form input").attr("readonly", "readonly");
                        //异步树选择数据
                        var dat = data[1][0];
                        tree = new Tree.TreeList({
                            nodes: dat,
                            checkType: 'all',
                            cascadeCheckd : false, // 是否级联选
                            showLine: true //显示连接线
                        });
                        var picker = new TreePicker({
                            trigger: '.tree',
                            valueField: '#courtInput', // 如果需要列表返回的value，放在隐藏域，那么指定隐藏域
                            selectStatus: 'checked',// 设置勾选作为状态改变的事件
                            tpl: '<input type="checkbox" onclick="choseAll(this)">全选',
                            width: 300,  //指定宽度
                            children: [tree] //配置picker内的列表bui-tree-item-checked bui-tree-item-selected
                        });
                        picker.render();
                    }
                    dialog.show();
                });
            });
            // 组装sql
            $("[type='submit']").on('click', function () {
                var submit = "";
                var fjtj = getFjtj();//附加条件
                // 带子查询的字段
                var child_sql_fields = [
                    "judge_level", "marshal_level", "helper_level", "clerk_level", "education_background_report",
                    "major", "degree", "degree_date", "political_report", "law_position_report", "law_position_date",
                    "rank_report", "rank_date", "servant_level", "servant_level_date", "political_date",
                    "administration_position", "former_unit", "leave_reason", "verify_date", "position_type",
                    "c_dept", "d_start_date", "c_position", "c_rank"
                ];
                // 只有子表有,主表没有的
                var child_sql_without_parent_table = [
                    "judge_level", "marshal_level", "helper_level", "clerk_level",
                    "c_dept", "d_start_date", "c_position", "c_rank"
                ]
                $("[sub='sub']").each(function () {
                    var c1 = $(this).attr("term1");
                    var c2 = " a." + $(this).attr("term2");
                    var c2_0 = $(this).attr("term2");
                    var c3 = $(this).attr("term3");
                    var c4 = $(this).attr("term4");
                    var c5 = $(this).attr("term5");
                    if (submit == "") {
                        c1 = " and ";
                    }
                    if (c1 == "or") {
                        c1 = " and ( 1=1 or ";
                    }
                    if (c3 == 'is null') {
                        var sfbh_child_sql_fields = false;
                        $.each(child_sql_fields, function (a, b) {
                            if (b == c2_0) {
                                sfbh_child_sql_fields = true;
                            }
                        });
                        if (sfbh_child_sql_fields) {
                            var sfbh_child_sql_without_parent_table = false;
                            $.each(child_sql_without_parent_table, function (a, b) {
                                if (b == c2_0) {
                                    sfbh_child_sql_without_parent_table = true;
                                }
                            });
                            if (sfbh_child_sql_without_parent_table) {
                                submit += " " + c1 + " " + c5 + " " + c3 + ")";
                            } else {
                                submit += " " + c1 + " (" + c2 + " " + c3 + " or " + c5 + " " + c3 + "))";
                            }
                        } else {
                            submit += " " + c1 + " " + c2 + " " + c3 + " ";
                        }
                    } else if (c3 == 'in') {
                        if (c2 == ' a.court_no_text') {
                            arr = $("#courtInput").val().split(',');
                            submit += " " + c1 + " a.court_code in ( ";
                            for (var a = 0; a < arr.length; a++) {
                                submit += "'" + arr[a] + "',";
                            }
                            submit = submit.substring(0, submit.length - 1) + " ) ";
                        } else {
                            var sfbh_child_sql_fields = false;
                            $.each(child_sql_fields, function (a, b) {
                                if (b == c2_0) {
                                    sfbh_child_sql_fields = true;
                                }
                            });
                            if (sfbh_child_sql_fields) {
                                var sfbh_child_sql_without_parent_table = false;
                                $.each(child_sql_without_parent_table, function (a, b) {
                                    if (b == c2_0) {
                                        sfbh_child_sql_without_parent_table = true;
                                    }
                                });
                                if (sfbh_child_sql_without_parent_table) {
                                    arr = c4.split(',');
                                    var submit_2 = " " + c1 + " " + c5 + " in (";
                                    for (var a = 0; a < arr.length; a++) {
                                        submit_2 += "'" + arr[a] + "',";
                                    }
                                    submit_2 = submit_2.substring(0, submit_2.length - 1) + " ) ";
                                    submit += submit_2 + ")";
                                } else {
                                    arr = c4.split(',');
                                    submit += " " + c1 + " (" + c2 + " in ( ";
                                    for (var a = 0; a < arr.length; a++) {
                                        submit += "'" + arr[a] + "',";
                                    }
                                    submit = submit.substring(0, submit.length - 1) + " ) ";
                                    var submit_2 = " or " + c5 + " in (";
                                    for (var a = 0; a < arr.length; a++) {
                                        submit_2 += "'" + arr[a] + "',";
                                    }
                                    submit_2 = submit_2.substring(0, submit_2.length - 1) + " ) ";
                                    submit = submit + submit_2 + "))";
                                }
                            } else {
                                arr = c4.split(',');
                                submit += " " + c1 + " " + c2 + " in ( ";
                                for (var a = 0; a < arr.length; a++) {
                                    submit += "'" + arr[a] + "',";
                                }
                                submit = submit.substring(0, submit.length - 1) + " ) ";
                            }
                        }
                    } else if (c3 == "like") {
                        if (c2 == ' a.court_no_text') {
                            arr = $("#courtInput").val().split(',');
                            submit += " " + c1 + " (" + c3 + " '%" + arr[0] + "%' ";
                            for (var a = 1; a < arr.length; a++) {
                                submit += "  or a.court_code " + c3 + " '%" + arr[a] + "%' ";
                            }
                            submit += " ) ";
                        } else {
                            var sfbh_child_sql_fields = false;
                            $.each(child_sql_fields, function (a, b) {
                                if (b == c2_0) {
                                    sfbh_child_sql_fields = true;
                                }
                            });
                            if (sfbh_child_sql_fields) {
                                var sfbh_child_sql_without_parent_table = false;
                                $.each(child_sql_without_parent_table, function (a, b) {
                                    if (b == c2_0) {
                                        sfbh_child_sql_without_parent_table = true;
                                    }
                                });
                                if (sfbh_child_sql_without_parent_table) {
                                    submit += " " + c1 + " " + c5 + " " + c3 + " '%" + c4 + "%')";
                                } else {
                                    submit += " " + c1 + " (" + c2 + " " + c3 + " '%" + c4 + "%' or " + c5 + " " + c3 + " '%" + c4 + "%'))";
                                }
                            } else {
                                submit += " " + c1 + " " + c2 + " " + c3 + " '%" + c4 + "%' ";
                            }
                        }
                    } else {
                        if (c2 == ' a.court_no_text') {
                            arr = $("#courtInput").val().split(',');
                            submit += " " + c1 + " ( a.court_code " + c3 + " '" + arr[0] + "' ";
                            for (var a = 1; a < arr.length; a++) {
                                submit += " or a.court_code " + c3 + " '" + arr[a] + "' ";
                            }
                            submit += " ) ";
                        } else {
                            var sfbh_child_sql_fields = false;
                            $.each(child_sql_fields, function (a, b) {
                                if (b == c2_0) {
                                    sfbh_child_sql_fields = true;
                                }
                            });
                            if (sfbh_child_sql_fields) {
                                var sfbh_child_sql_without_parent_table = false;
                                $.each(child_sql_without_parent_table, function (a, b) {
                                    if (b == c2_0) {
                                        sfbh_child_sql_without_parent_table = true;
                                    }
                                });
                                if (sfbh_child_sql_without_parent_table) {
                                    submit += " " + c1 + " " + c5 + " " + c3 + " '" + c4 + "')";
                                } else {
                                    submit += " " + c1 + " (" + c2 + " " + c3 + " '" + c4 + "' or " + c5 + " " + c3 + " '" + c4 + "'))";
                                }
                            } else {
                                submit += " " + c1 + " " + c2 + " " + c3 + " '" + c4 + "' ";
                            }
                        }
                    }
                    if (c1 == " and ( 1=1 or ") {
                        submit += " ) ";
                    }
                });
                var sfbh_yefg = false;
                $.each(submit, function (a, b) {
                    if (b == "a.yefg = '0'") {
                        sfbh_yefg = true;
                    }
                });
                if (sfbh_yefg) {
                    submit = submit.replace("a.yefg = '0'", " ifnull(a.yefg,'0') != '1' ")
                }
                if (submit == "") {
                    error2.show();
                    return;

                }
                var pasxx = fjtj + submit;
                var encodeStr = encodeParam(pasxx);

                $("#redirect input").val(encodeStr);
                $("#redirect").submit();
            });
        });
    });


    $("#list2 li").live('click', function () {
        $("#list2 option").removeAttr("selected");
        $(this).attr("class", "selected");
    });

    $(".sub").live('click', function () {
        $("#list3 li").attr("type3", "");
        $(this).attr("type3", "selected");
    });

    // 监听操作，选择部门时，多选
    $("#choseAllDepart").live("click", function () {
        var allDeprat = [];
        $.each(soList, function (a, b) {
            allDeprat.push([b.id]);
        });
        $("#so_select2").val(allDeprat).trigger("change");
    });

    // 监听操作，选择部门时，清除多选
    $("#clearAllDepart").live("click", function () {
        $("#so_select2").val(null).trigger("change");
    });

    // 针对多选的，渲染select2下拉框
    function renderSoOptionForMulti(list, multi) {
        $('#so').empty();
        if ($("#so_select2").length > 0) {
            // 销毁select2，避免加载缓存数据
            $("#so_select2").select2("destroy");
        }
        if (true == multi) {
            // 多选
            $('#so').html('<p><button id="choseAllDepart">全选</button><button id="clearAllDepart">清空</button></p><select id="so_select2" style="width: 300px"></select>');
        } else {
            // 单选
            $('#so').html('<select id="so_select2" style="width: 300px"></select>');
        }
        $('#soVal').val('');
        // if (soSelect != null) soSelect.destroy();
        $("#so_select2").select2({
            data: list,
            multiple: multi,
            matcher: function (params, data) {
                if ($.trim(params.term) === '') {
                    return data;
                }
                if (typeof data.children === 'undefined') {
                    return null;
                }
                var filteredChildren = [];
                $.each(data.children, function (idx, child) {
                    console.log(params.term);
                    if (child.text === params.term || child.text.toPinYin().toUpperCase().indexOf(params.term.toUpperCase()) != -1 || child.text.indexOf(params.term) != -1) {
                        filteredChildren.push(child);
                    }
                });
                if (filteredChildren.length) {
                    var modifiedData = $.extend({}, data, true);
                    modifiedData.children = filteredChildren;
                    return modifiedData;
                }
                return null;
            }
        });
        // 有多项选择的时候
        $(".select2-selection.select2-selection--multiple").css("height", "50px");
        $(".select2-selection.select2-selection--multiple").css("overflow", "auto");
    }

    // 选择法院点击全选的时候
    function choseAll(checkbox) {
        if (checkbox.checked == true) {
            var items = tree.getItems();
            $.each(items, function (index, node) {
                tree.setNodeChecked(node, true); //勾选
            });
        } else {
            var items = tree.getItems();
            $.each(items, function (index, node) {
                tree.setNodeChecked(node, false); //取消勾选
            });
        }
    }

    // 列表1选中后出列表2的选项
    function getList2(a) {
        // 清空list2的option
        $("#list2").empty();
        // 获取list2列表
        $.post("<%=basePath%>chart/getList2", {list1Id: a}, function (data) {
            $("#list2").select2({
                data: data,
                closeOnSelect: false,
                matcher: function (params, data) {
                    if ($.trim(params.term) === '') {
                        return data;
                    }
                    if (typeof data.children === 'undefined') {
                        return null;
                    }
                    var filteredChildren = [];
                    $.each(data.children, function (idx, child) {
                        if (child.text === params.term || child.text.toPinYin().toUpperCase().indexOf(params.term.toUpperCase()) != -1 || child.text.indexOf(params.term) != -1) {
                            filteredChildren.push(child);
                        }
                    });
                    if (filteredChildren.length) {
                        var modifiedData = $.extend({}, data, true);
                        modifiedData.children = filteredChildren;
                        return modifiedData;
                    }
                    return null;
                }
            });
            // 附加type_id
            $.each(data[0].children, function (key, data_option) {
                var type_id = data_option.type_id;
                var child_sql = data_option.child_sql;
                if (type_id != undefined) {
                    $("[value='" + data_option.id + "']").attr("type_id", data_option.type_id);
                }
                if (child_sql != undefined) {
                    $("[value='" + data_option.id + "']").attr("child_sql", data_option.child_sql);
                }
            });
            $("#list2").select2("open");
        });
    }

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

    // 监控条件选择框，为空 等于 包含 类似
    function tjChange() {
        if ($("#tj").find("option:selected").text() == '为空') {
            $("#so").hide();
        } else if ($("#tj").find("option:selected").text() == '等于') {
            $("#so").show();
            // 重新渲染单选框
            renderSoOptionForMulti(soList, false);
        } else if ($("#tj").find("option:selected").text() == '包含') {
            $("#so").show();
            // 重新渲染多选框
            renderSoOptionForMulti(select2_data, true);
        } else {
            $("#so").show();
        }
    }

    var userFullName = "<%=userInfo.getFullname()%>";//账户全称
    var courtNoText = "<%=userInfo.getCourtNoText()%>";//法院全称
    var courtList;
    var isSuperUser = <%=isSuperUser%>;
    var userCourtCode = "<%=userInfo.getCourtCode()%>"

    $("input[name=countStyle]").click(function () {
        var countStyle = $("input[name='countStyle']:checked").val();
        if (countStyle == '1') {
            var html = "";
            html += "<option value=''>无限制</option>";
            html += "<option value='0'>高院</option>";
            html += "<option value='1'>中院</option>";
            html += "<option value='2'>基层法院</option>";
            $("#countStyle").empty();
            $("#countStyle").append(html);
        } else {
            $("#countStyle").empty();
            $("#countStyle").append($("<option>").attr({"value": ""}).text("请选择"));
            if (!courtList) {
                $.post("<%=basePath%>/code/codeListByType", {typeId: 1}, function (data) {
                    courtList = data.data;
                    for (var i = 0; i < data.data.length; i++) {
                        $("#countStyle").append($("<option>").attr({"value": data.data[i].courtCode}).text(data.data[i].codeName));
                    }

                });
            } else {
                for (var i = 0; i < courtList.length; i++) {
                    $("#countStyle").append($("<option>").attr({"value": courtList[i].courtCode}).text(courtList[i].codeName));
                }
            }
        }
    });

    function getFjtj() {
        var countStyle = $("input[name=countStyle]:checked").val();
        var courtLevel = "";
        var courtCode = "";
        var preparation = $("#preparation").val();
            //    特殊处理 （莫运垠moyy/admin）2018年10月16日 该账号需要在自定义信息查询在编人员时，能够选择法院，这里进行了特殊处理
        // if(isSuperUser)
        if (isSuperUser || '<%=userInfo.getUsername()%>' == "moyy" || sfyqx) {
            if (countStyle == 1)
                courtLevel = $("#countStyle").val();
            else if (countStyle == 0)
                courtCode = $("#countStyle").val();
        } else {
            courtCode = userCourtCode;
        }
        var returnStr = "";
        if (courtLevel != "") {
            returnStr += " and b.court_gradation = " + courtLevel;
        }
        if (courtCode != "") {
            returnStr += " and a.court_code = '" + courtCode + "' ";
        }
        if (preparation != "") {
            if (preparation == "1")
                returnStr += " and a.preparation = 1 ";
            if (preparation == "2")
                returnStr += " and ( a.preparation in (2,4,5,8) or a.preparation is null) ";
            if (preparation == "3")
                returnStr += " and a.preparation = 3 ";
        }
        return returnStr;
    }

</script>
</html>
