<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>人员管理</title>
    <jsp:include page="/basic_import.jsp"></jsp:include>
    <jsp:include page="/fragment/tip.jsp"></jsp:include>
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

        .show_info_edit {
            display: none;
            color: red;
        }
    </style>
</head>
<body>

<div class="row">
    <div>
        <ul class="breadcrumb">
            <li><span>人事管理</span><span class="divider">/</span></li>
            <li><span>信息管理</span><span class="divider">/</span></li>
            <li class="active">管理人员信息</li>
        </ul>
    </div>
</div>

<%--树结构--%>
<div id='deptree' class="panel bui-stdmod-body span6">
    <div id="t3"></div>
</div>

<%--表格--%>
<div class="offset6">
    <div class="search-grid-container">
        <div id="grid"></div>
    </div>
</div>

<%--部门修改窗口 --%>
<div id="deptEditContent" class="hide">
    <form id="dept_edit" class="form-horizontal">
        <div class="row">
            <div class="control-group span8">
                <label class="control-label">部门类型：</label>

                <div class="controls">
                    <select id="org_type" class="" name="org_type" data-rules="{required:true}">
                        <option value=>请选择</option>
                        <option value="1">业务部门</option>
                        <option value="2">综合部门</option>
                        <option value="8">派出法庭</option>
                    </select>
                </div>
            </div>
        </div>
    </form>
</div>

<div id="DialogInfo" class="hide"></div>

<%--申请调职窗口--%>
<div id="dz" class="hide">
    <form id="userinfo_dz_form" class="form-horizontal" action="<%=basePath%>userinfo/insertChangeJob" method="post">
        <div class="row" style="float: left">
            <img id="dzpic" src="{url}" style="width: 150px;height: auto"/>
        </div>
        <div style="float: left;width:440px">
            <div class="row">
                <div class="control-group span8">
                    <lable class="control-label">姓名：</lable>
                    <div class="controls">
                        <label id="fullname_"></label>
                        <input id="changeUUID" type="hidden" name="umsChangeJob.changeUUID">
                        <input id="user_id" type="hidden" name="umsChangeJob.user_id">
                        <input id="user_name" type="hidden" name="umsChangeJob.user_name">
                        <input id="apply_user_id" type="hidden" name="umsChangeJob.apply_user_id">
                        <input id="apply_user_name" type="hidden" name="umsChangeJob.apply_user_name">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">性别：</label>

                    <div class="controls">
                        <label id="sex"></label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <lable class="control-label">原法院：</lable>
                    <div class="controls">
                        <label id="old_court_text" name="umsChangeJob.old_court_text"></label>
                        <input type="hidden" id="old_court_text2" name="umsChangeJob.old_court_text">
                        <input id="old_court_no" type="hidden" name="umsChangeJob.old_court_no">
                        <input id="apply_court_no" type="hidden" name="umsChangeJob.apply_court_no">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">原部门：</label>

                    <div class="controls">
                        <label id="old_dep_text" name="umsChangeJob.old_dep_text"></label>
                        <input id="old_dep_text2" type="hidden" name="umsChangeJob.old_dep_text">
                        <input id="old_dep_no" type="hidden" name="umsChangeJob.old_dep_no">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <lable class="control-label">申请法院：</lable>
                    <div class="controls">
                        <select id="new_court_no" name="umsChangeJob.new_court_no"
                                onchange="loadDeptList(new_dept_no, this.value, '<option value=\'\'>请选择</option>')">
                            <option value="">请选择</option>
                        </select>
                        <input id="new_court_text" type="hidden" name="umsChangeJob.new_court_text">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">申请部门：</label>

                    <div class="controls">
                        <select id="new_dept_no" courtNo="" name="umsChangeJob.new_dept_no">
                            <option value="">请选择</option>
                        </select>
                        <input id="new_dept_text" type="hidden" name="umsChangeJob.new_dept_text">
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<%--查询窗口--%>
<div id="search" class="hide">
    <form id="userinfo_search_form" class="form-horizontal">
        <div class="row">
            <div id="nav" class="row">
                <div class="control-group span8">
                    <label class="control-label">姓名：</label>
                    <div class="controls">
                        <input name="fullname" type="text" placeholder="请输入需要查询的姓名" class="control-text search_field">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">性别：</label>
                    <div class="controls">
                        <select typeId="3" class="code search_field" name="gender"></select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">法院：</label>
                    <div class="controls">
                        <select class="Courtcode search_field" name="innerCourtNo" onchange="loadDeptList('#department_n', this.value, '<option value=\'\'>请选择</option>')">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">部门：</label>
                    <div class="controls">
                        <select id="department_n" courtNo="" name="department" class="search_field select2_search">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">职级：</label>
                    <div class="controls">
                        <select typeId="17" class="code search_field select2_search" name="rank"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">行政职务：</label>
                    <div class="controls">
                        <select typeId="15" name="administrationPosition" type="text" value="" class="code search_field select2_search"></select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">最大年龄：</label>
                    <div class="controls">
                        <input type="text" class="code" data-rules="{number:true}" onchange="calcDay(this,'#birthday_info_max_n')"/>
                        <input type="hidden" class="search_field " name="birthdayN" id="birthday_info_max_n" value=""/>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">最小年龄：</label>
                    <div class="controls">
                        <input type="text" class="code" data-rules="{number:true}" onchange="calcDay(this,'#birthday_info_min_n')"/>
                        <input type="hidden" class="search_field " name="birthdayM" id="birthday_info_min_n"
                               value=""/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span17">
                    <label class="control-label">进院日期：</label>

                    <div class="controls bui-form-group" data-rules="{dateRange : true}">
                        <input id="enterDateStart" name="enterDateStart" type="text" placeholder="起始日期" class="calendar search_field">
                        &nbsp;-&nbsp;
                        <input id="enterDateEnd" name="enterDateEnd" type="text" placeholder="结束日期" class="calendar search_field">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">学历：</label>
                    <div class="controls">
                        <select typeId="11" name="educationBackground" value="" class="code search_field select2_search"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">员额法官：</label>
                    <div class="controls">
                        <select id="yefg" name="yefg" class="search_field">
                            <option value="">请选择</option>
                            <option value="1">员额法官</option>
                            <option value="0">非员额法官</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<div class="hide">
    <form action="<%=path%>/view/excelDownload" method="post" id="excel_form">

    </form>
</div>

<input type="hidden" id="hide_court_no">

<input type="hidden" id="hide_dept_no">

<div class="bui-ext-mask hide user-defined-mask" style="position: fixed;"></div>

<div class="bui-ext-mask-msg x-mask-loading hide user-defined-mask" style="position: fixed; left: 50%; top: 50%;">
    <div>正在进行操作请稍后</div>
</div>

<%--人民陪审员的iframe框--%>
<div id="gbbzlx" hidden>
    <%--    在编人员--%>
    <iframe id="gbbzlx_ifram_zbry" style="width: 100%;height: 98%;overflow: auto;frameborder: no;border: 0"></iframe>
    <%--    编外人员--%>
    <iframe id="gbbzlx_ifram_bwry" style="width: 100%;height: 98%;overflow: auto;frameborder: no;border: 0"></iframe>
    <%--    人民陪审员--%>
    <iframe id="gbbzlx_ifram_rmpsy" style="width: 100%;height: 98%;overflow: auto;frameborder: no;border: 0"></iframe>

</div>


</body>


<script>
    // 判断该提交哪个按钮
    var submit_type;
    var CourtNo = '';
    var auth = false;
    var myForm;
    var TForm;
    var DzForm;
    // 是否是编辑状态
    var isEdit = false;
    var Rstore;
    var closeFlag = false;
    var V = $.extend({}, V, parent.V);
    // 行号
    var startRow;
    // 选择的树的法院
    var current_court = null;
    var query_condition = {userType: 11, isInfoComplete: 0, isValid: 0,};
    // 图片编辑
    var photoEdit = false;
    // 显示编外和人民陪审员的dialog，写成全局变量，为了子页面可以调用父页面
    var dialog_gbbzlx;

    // 成功数量
    var successCount = 0;
    var ajax_num = 3;

    //-------------初始化下拉框数据Start---------------
    var firstLine = '<option value="">请选择</option>';
    $(function () {
        $("select.code").each(function () {
            loadCodeList(this, firstLine);
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
            // 是否是超级管理员 superuser
            $.getJSON("<%=path%>/IsSuperUser", {}, function (data) {
                if (data.res) {
                    CourtNo = "";
                }
                successCount++;
            });
            successCount++;
        });
        // 查询所有法院 与权限 限制无关
        $.getJSON("/ums/code/codeListByTypeWithNoAspect", {}, function (data) {
            for (var i = 0; i < data.data.length; i++) {
                if ($("select.CourtInfocode").length > 0) {
                    $("select.CourtInfocode").append($("<option>").attr({"value": data.data[i].courtCode}).text(data.data[i].codeName));
                }
            }
            successCount++;
        });
        // 隐藏div
        $(document).click(function () {
            if ($("#leaveReasonDiv").is(":visible") && closeFlag) {
                $("#leaveReasonDiv").hide();
                closeFlag = false;
            }
        });
    });
    //-------------初始化下拉框数据End---------------

    // 定时器：一秒执行一次，满足条件的时候执行下面的
    var clock = setInterval(countDown, 1000);

    function countDown() {
        if (ajax_num == successCount) {
            clearInterval(clock); //清除js定时器
            // 初始化BUI相关组件
            BUI.use(['common/search', 'bui/tree', 'bui/data', 'bui/calendar', 'bui/overlay', 'bui/form', 'bui/uploader'], function (Search, Tree, Data, Calendar, Overlay, Form, Uploader) {

                // 渲染include JSP的form表单---start
                var form = new Form.HForm({
                    // 该id是include JSP的id
                    srcNode: '#userinfo_form',
                    submitType: 'ajax',
                    autoRender: true,
                    callback: function (data) {
                        // 点击提交按钮后，会做下面操作
                        if (data.success) {
                            if (data.judgeMsg != null && data.judgeMsg != '') {
                                showTip();
                                BUI.Message.Alert(data.judgeMsg, null, "warning");
                            } else {
                                showTip();
                            }
                        }
                        if (data.success && photoEdit) {
                            $.getJSON("<%=basePath%>photo/save", {userId: $("#userinfo_form #id").val() || $("#userinfo_form #new_id").val(), _: new Date().getTime()}, function () {
                            });
                        } else if (data.result === -1) {
                            BUI.Message.Alert("非法操作，当前用户无此权限！", null, "warning");
                        }
                        store.load();
                        editDialog.close();
                    }
                });

                myForm = form;

                // form表单额外验证规则-start
                Form.Rules.add({
                    name: "idcheck",
                    msg: "身份证格式有误！",
                    validator: function (value, baseValue, formatMsg) {
                        if (!checkId(value)) {
                            return formatMsg;
                        }
                        $('#birthday').val(getBirthdatByIdNo(value));
                        $('#birthday_show').html(getBirthdatByIdNo(value));
                        calcAge();
                        var birthday = $('#birthday').val().replace(/-/g, '');
                        if (birthday && birthday !== '') {
                            switch (value.length) {
                                case 18:
                                    if (value.slice(6, 14) !== birthday) {
                                        return "出生日期与身份证信息不符！";
                                    }
                                    if (!isEdit) {
                                        $("#password").val('admin');
                                    }
                                    break;
                                case 15:
                                    if ('19' + value.slice(6, 12) !== birthday) {
                                        return "出生日期与身份证信息不符！";
                                    }
                                    if (!isEdit) {
                                        $("#password").val('admin');
                                    }
                                    break;
                            }
                        }
                    }
                });
                Form.Rules.add({
                    name: "datecheck1",
                    msg: "日期不能大于今天！",
                    validator: function (value, baseValue, formatMsg) {
                        if (new Date(value.replace(/-/g, '/')) > new Date()) {
                            return formatMsg;
                        }
                    }
                });
                Form.Rules.add({
                    name: 'phoneNumber',
                    msg: '请填写{0}位数字的电话号码。',
                    validator: function (value, baseValue, formatMsg) {
                        var regexp = new RegExp('^[0-9]{11}$');
                        if (value && !regexp.test(value)) {
                            return formatMsg;
                        }
                    }
                });
                // form表单额外验证规则-end
                // 渲染form表单---end


                // store：用来加载数据---start
                var userinfoActionUrl = "<%=basePath%>view/userinfo_dwszl";
                var store = Search.createStore(userinfoActionUrl, {
                    sortField: 'sortNo',
                    sortDirection: 'ASC',
                    remoteSort: true,
                    pageSize: 20,
                    params: {
                        userType: 11, // 11是调职的用户
                        // isInfoComplete: 0,
                        // isValid: 0,
                        courtNo: CourtNo,
                        simpleQuery : true
                    },
                    autoSync: true //保存数据后，自动更新
                });
                Rstore = store;
                V.store = store;
                store.on("load", function () {
                    startRow = store.get("start") + 1;
                    // 跟权限有关
                    canEdit();
                });
                // store：用来加载数据---end




                // 加载数据的表格---start
                var columns = [
                    {
                        title: '序号', sortable: false, width: "60", align: 'left', renderer: function (value, obj) {
                            return startRow++;
                        }
                    },
                    {title: 'changeUUID', dataIndex: 'changeUUID', visible: false},
                    {title: '人员来源', dataIndex: 'whereFrom', width: "100", sortable: true, align: 'left'},
                    {title: '申请内容', dataIndex: 'sq_content', width: "150", sortable: true, align: 'left'},
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
                    {title: '学历', dataIndex: 'educationBackgroundText', width: "100", sortable: true, align: 'left'},
                    {title: '行政职务', dataIndex: 'administrationPositionText', width: "100", sortable: true, align: 'left'},
                    {title: '职级', dataIndex: 'rankText', width: "100", sortable: true, align: 'left'},
                    {
                        title: '返聘人员', width: "100", sortable: false, visible: false, renderer: function (value, obj) {
                            var str = "";
                            if (obj.isValid == 1) {
                                str = "是"
                            } else {
                                str = "否"
                            }
                            return str;

                        }
                    },
                    {
                        title: '操作', width: "200", sortable: false, renderer: function (value, obj) {
                            var spanStr = '<span class="grid-command btn-detail" title="显示人员详情信息">查看详情</span>';
                            return spanStr;
                        }
                    }
                ];
                V.cloumn = columns;
                var tbarData = {
                    items: [
                        {
                            text: '<i class="icon-search"></i>搜索',
                            btnCls: 'button button-small',
                            handler: function () {
                                initSelect2("select2_search");
                                $("select.Courtcode").select2();
                                if (current_court != null && current_court != '') {
                                    $("select[name='innerCourtNo']").attr("disabled", false);
                                    $("select[name='innerCourtNo']").val(current_court);
                                    $("select[name='innerCourtNo']").attr("disabled", true);
                                }
                                searchDialog.show();
                            }
                        },
                        {
                            text: '<i class="icon-edit"></i>编辑',
                            btnCls: 'button button-small',
                            handler: function () {
                                form.clearErrors();
                                var userinfo = grid.getSelected();
                                var userType;
                                if (parseInt(userinfo.userType) > 10 && parseInt(userinfo.userType) < 20) {
                                    // 申请调职人员 减 10
                                    userType = parseInt(userinfo.userType) - 10;
                                } else if (parseInt(userinfo.userType) > 20) {
                                    // 申请改编人员 减 20  获取申请内容
                                    var sq_content = userinfo.sq_content;
                                    // 截取改编后人员类型
                                    var gbhrylx = sq_content.substring(sq_content.indexOf("转") + 1);
                                    if (gbhrylx == "在编") {
                                        userType = 1;
                                    } else if (gbhrylx == "编外") {
                                        userType = 2;
                                    } else if (gbhrylx == "人民陪审员") {
                                        userType = 3;
                                    }
                                }
                                if (userinfo) {
                                    if (userType == 1) {
                                        // 在编人员的编辑页面
                                        var src_url = "user2EditForGbbzlx.jsp?user_id=" + userinfo.id + "&changeUUID=" + userinfo.changeUUID;
                                        gbbzlx_ifram_zbry.src =  src_url;
                                        $("#gbbzlx_ifram_zbry").show();
                                        // 将其他iframe设置为空
                                        $("#gbbzlx_ifram_bwry").hide();
                                        $("#gbbzlx_ifram_rmpsy").hide();
                                        submit_type = 1;
                                        dialog_gbbzlx.show();
                                    } else if (userType == 2) {
                                        // 编外人员的编辑页面
                                        var src_url = "externalIframeForGbbzlx.jsp?user_id=" + userinfo.id + "&changeUUID=" + userinfo.changeUUID;
                                        gbbzlx_ifram_bwry.src =  src_url;
                                        $("#gbbzlx_ifram_bwry").show();
                                        // 将其他iframe设置为空
                                        $("#gbbzlx_ifram_zbry").hide();
                                        $("#gbbzlx_ifram_rmpsy").hide();
                                        submit_type = 2;
                                        dialog_gbbzlx.show();
                                    } else if (userType == 3) {
                                        // 人民陪审员编辑页面
                                        var src_url = "jurorEditForGbbzlx.jsp?user_id=" + userinfo.id + "&changeUUID=" + userinfo.changeUUID;
                                        gbbzlx_ifram_rmpsy.src =  src_url;
                                        $("#gbbzlx_ifram_rmpsy").show();
                                        // 将其他iframe设置为空
                                        $("#gbbzlx_ifram_zbry").hide();
                                        $("#gbbzlx_ifram_bwry").hide();
                                        submit_type = 3;
                                        dialog_gbbzlx.show();
                                    }
                                    // 默认点击基本信息
                                    $(".initClass").click();
                                    if (isEdit) {
                                        // 设置用户名的提示，编辑时不可以修改用户名
                                        $("#username").attr("readonly", "readonly");
                                        $("#username").focus(function () {
                                            $(".show_info_edit").show();
                                        });
                                        $("#username").blur(function () {
                                            $(".show_info_edit").hide();
                                        });
                                    }
                                } else {
                                    BUI.Message.Alert("请选择一条记录", null, "info");
                                }
                            }
                        },
                        {
                            text: '<i class="icon-refresh"></i>刷新',
                            btnCls: 'button button-small',
                            handler: function () {
                                store.load();
                            }
                        }
                    ]
                };
                var editing = new BUI.Grid.Plugins.DialogEditing({
                    contentId: 'content', //设置隐藏的Dialog内容
                    autoSave: true, //添加数据或者修改数据时，自动保存
                    triggerCls: 'btn-edit'
                });
                var gridCfg = Search.createGridCfg(columns, {
                    height: "auto",
                    emptyDataTpl: '<div class="centered"><h2>查询的数据不存在</h2></div>',
                    tbar: tbarData,
                    plugins: [editing, BUI.Grid.Plugins.RadioSelection, BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格
                });
                var search = new Search({
                    store: store,
                    gridCfg: gridCfg
                });
                var grid = search.get('grid');
                // 加载数据的表格---end


                // 搜索dialog--start
                var searchForm = new Form.HForm({
                    srcNode: "#search",
                    autoRender: true
                });
                var searchDialog = new Overlay.Dialog({
                    title: "查询信息",
                    width: 770,
                    contentId: "search",
                    buttons: [
                        {
                            text: '搜索', elCls: 'button button-primary search', handler: function () {
                                if (searchForm.isValid()) {
                                    var condition = {};
                                    $("#userinfo_search_form .search_field").each(function () {
                                        condition[$(this).attr("name")] = $(this).val();
                                    });
                                    condition = $.extend({"start": 0, "pageIndex": 0}, condition);
                                    store.load(condition);
                                    query_condition = store.get("lastParams");
                                    this.close();
                                } else {
                                    searchForm.submit();
                                }
                            }
                        },
                        {
                            text: '重置', elCls: 'button', handler: function () {
                                initSelect2("select2_search");
                                $("select.Courtcode").select2();
                                searchForm.clearFields();
                                searchForm.clearErrors();
                                if (current_court != null && current_court != '') {
                                    $("select[name='innerCourtNo']").attr("disabled", false);
                                    $("select[name='innerCourtNo']").val(current_court);
                                    $("select[name='innerCourtNo']").attr("disabled", true);
                                }
                            }
                        },
                        {
                            text: '关闭', elCls: 'button', handler: function () {
                                this.close();
                            }
                        }
                    ],
                    success: function (data) {
                        this.close();
                    }
                });
                // dialog关闭时，清楚表单
                searchDialog.on("closing", function () {
                    searchForm.clearFields();
                    searchForm.clearErrors();
                });
                // 搜索dialog--end


                //-------------树结构Start-----------------
                var treestore = new Data.TreeStore({
                    root: {
                        text: "全部",
                        courtNo: "",
                        deptNo: ""
                    },
                    url: '<%=basePath%>code/tree/children3'
                });
                var tree = new Tree.TreeList({
                    render: '#t3',
                    store: treestore,
                    elStyle: {border: "none"},
                    //checkType: 'all',
                    multipleCheck: false,
                    showRoot: true
                });
                tree.render();
                // 加载根节点，也可以让用户点击加载
                treestore.load();
                var courNo, depNo;
                // 点击树时，加载相应数据
                tree.on('itemclick', function (ev) {
                    var item = ev.item;
                    courNo = item.courtNo;
                    depNo = item.deptNo;
                    var condition;
                    if (auth) {
                        condition = {courtNo: item.courtNo, deptNo: item.deptNo};
                    } else {
                        condition = {courtNo: CourtNo, deptNo: item.deptNo};
                    }
                    searchForm.clearFields();
                    $(" #userinfo_search_form .search_field").each(function () {
                        condition[$(this).attr("name")] = $(this).val();
                    });
                    condition = $.extend({"start": 0, "pageIndex": 0}, condition);
                    store.load(condition);
                    query_condition = store.get("lastParams");
                    $('#log').text(item.id + ":" + item.text);

                    // 查询条件
                    // 加载选择的树的法院
                    current_court = item.courtNo;
                    $("select[name='innerCourtNo']").attr("disabled", false);
                    if (current_court != null && current_court != '') {
                        $("select[name='innerCourtNo']").val(current_court);
                        $("select[name='innerCourtNo']").attr("disabled", true);
                        loadDeptList($("#department_n"), current_court, firstLine, function () {
                        });
                    }
                    // 部门类型的修改
                    if (item.deptNo != null && item.deptNo != "") {
                        $("#hide_court_no").val(item.courtNo);
                        $("#hide_dept_no").val(item.deptNo);
                        var url = '<%=path%>/code/deptDetailInfo';
                        $.post(url, condition, function (data) {
                            var str = '';
                            if (data.orgType != null && data.orgType != 0) {
                                switch (data.orgType) {
                                    case 1:
                                        str += '业务部门';
                                        break;
                                    case 2:
                                        str += '综合部门';
                                        break;
                                    case 8:
                                        str += '派出法庭';
                                        break;
                                    default:
                                        str += '未确认';
                                }
                            } else {
                                str += '未确认';
                            }
                            $("#deptNoId").html(str);
                            $(".button-dept").parent().show()
                        });
                    } else {
                        $(".button-dept").parent().hide()
                    }
                });
                //-------------树结构End-----------------


                // 申请改变编制类型的完善资料弹出框---start
                dialog_gbbzlx = new Overlay.Dialog({
                    title: '新增',
                    width: '81%',
                    height: 600,
                    headerContent: '',
                    headerStyle: {},
                    footerContent: '',
                    footerSytle: {},
                    buttons: [
                        {
                            text: '保存', elCls: 'button button-primary', handler: function () {
                                // 父页面调用子页面:
                                if (submit_type == 1) {
                                    $("#gbbzlx_ifram_zbry").contents().find("#saveButton").click();
                                }
                                if (submit_type == 2) {
                                    $("#gbbzlx_ifram_bwry").contents().find("#saveButton").click();
                                }
                                if (submit_type == 3) {
                                    $("#gbbzlx_ifram_rmpsy").contents().find("#saveButton").click();
                                }
                            }
                        },
                        {
                            text: '关闭', elCls: 'button', handler: function () {
                                this.close();
                            }
                        }
                    ],
                    contentId: 'gbbzlx'  // 配置DOM容器的编号
                });
                // 申请改变编制类型的完善资料弹出框---end


                //单独要使用 提出来
                $.getJSON("/ums/code/codeListByType", {typeId: 45}, function (data) {
                    $("select.leavecode").html('');
                    $("select.leavecode").append('<option value="">请选择</option>');
                    $("#leaveReasonDiv").html('');
                    $("#leaveReasonDiv").append('<div><a  onclick="TypeChange(this)" codeId=0 class="selectDiv">在职</a></div>');
                    $("#leaveReasonDiv").append('<div><a  onclick="TypeChange(this)" codeId=-1 >停用</a></div>');
                    for (var i = 0; i < data.length; i++) {
                        $("select.leavecode").append($("<option>").attr({"value": data[i].id}).text(data[i].codeName));
                        $("#leaveReasonDiv").append('<div><a  onclick="TypeChange(this)" codeId=' + data[i].id + '>' + data[i].codeName + '</a></div>');
                    }
                    //返聘操作
                    $("#leaveReasonDiv").append('<div><a  onclick="TypeChange(this)" codeId=-2 >返聘</a></div>');
                });
                $("#btnSearch2").click(function () {
                    var condition = {};
                    $("#userinfo_search_form .search_field").each(function () {
                        condition[$(this).attr("name")] = $(this).val();
                    });
                    store.load(condition);
                    query_condition = store.get("lastParams");
                });
                //监听事件，删除一条记录
                grid.on('cellclick', function (ev) {
                    var sender = $(ev.domTarget); //点击的Dom
                    if (sender.hasClass('btn-detail')) {
                        var record = ev.record;
                        open("<%=basePath%>view/detail_new?id=" + record.id);
                        return;
                    }
                    if (sender.hasClass('btn-dz')) {
                        var record = ev.record;
                        $("#fullname_").html(record.fullname);
                        $("#user_id").val(record.id);
                        $("#user_name").val(record.fullname);
                        $("#apply_user_id").val("${loginUser.id}");
                        $("#apply_user_name").val("${loginUser.fullname}");
                        $("#sex").html(record.genderText);
                        $("#old_court_no").val(record.courtNo);
                        $("#apply_court_no").val(record.courtNo);
                        $("#old_court_text").html(record.courtNoText);
                        $("#old_dep_no").val(record.department);
                        $("#old_dep_text").html(record.departmentText);
                        $("#old_dep_text2").attr("value", record.departmentText);
                        $.getJSON("<%=basePath%>photo/getPhotoById", {userId: record.id}, function (data) {
                            $("#dzpic").attr("src", data);
                        });
                        $.getJSON("<%=basePath%>code/newUUID", {_: new Date()}, function (data) {
                            $("#userinfo_dz_form #changeUUID").val(data);
                        });
                        $.post("<%=path%>/code/deptDetailInfo", {
                            courtNo: $("#old_court_no").val(),
                            deptNo: $("#old_dep_no").val()
                        }, function (data) {
                            var a = data.courtShortName;
                            $("#old_court_text2").attr("value", data.courtShortName);
                        });
                        loadAllCourtList("#new_court_no", '<option value=\'\'>请选择</option>');
                        dzDialog.show();
                        return;
                    }
                    if (sender.hasClass('btn-enabled')) {
                        var record = ev.record;
                        var url = "<%=path%>/userinfo/enabled";
                        var i = record.isValid == 1 ? 0 : 1;
                        var datas = {};
                        datas['userInfo.isValid'] = i;
                        datas['userInfo.id'] = record.id;
                        //是返聘操作
                        if (sender.hasClass("btn-rehire")) {
                            datas['enableType'] = 1;
                        }
                        //返聘的启用
                        if (sender.hasClass("btn-enabled-on")) {
                            datas['userInfo.isValid'] = 1;
                        }
                        if (record.isValid != 1) {
                            //先验证用户名
                            var url_u = "<%=path%>/checkUserName";
                            var datas_u = {
                                courtStdNo: record.courtNo,
                                username: record.username,
                                valid: 1
                            };
                            //身份证验证
                            var url_ = "<%=path%>/checkIdCard";
                            var da = {
                                idcard: record.idcard,
                                valid: 1
                            };
                            $.post(url_u, datas_u, function (data) {
                                if (data.UserNameCheck == "false") {
                                    $.post(url_, da, function (data) {
                                        if (data.idCardCheck) {
                                            $.post(url, datas, function (dt) {
                                                if (dt.success) {
                                                    BUI.Message.Alert("操作成功", "success");
                                                } else {
                                                    BUI.Message.Alert("操作失败", "success");
                                                }
                                                store.load();
                                            });
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
                                        }
                                    })
                                } else {
                                    BUI.Message.Alert("用户名已经存在,请更改用户名后再启用", "warning");
                                }
                            });
                        } else {
                            $.post(url, datas, function (data) {
                                if (data.success) {
                                    BUI.Message.Alert("操作成功", "success");
                                } else {
                                    BUI.Message.Alert("操作失败", "success");
                                }
                                store.load();
                            });
                        }
                    }
                });
                $(".button-dept").parent().hide()
                $(".button-one").parent().show();
                $(".button-two").parent().hide();
                //-------------初始化表格End---------------


                //-------------初始化表格Start---------------
                //申请调职
                var dzDialog = new Overlay.Dialog({
                    title: "申请调职",
                    width: 750,
                    contentId: "dz",
                    buttons: [
                        {
                            text: '申请调职', elCls: 'button button-primary', handler: function () {
                                $("#new_dept_text").attr("value", $("#new_dept_no").find("option:selected").text());
                                if ($("#new_dept_no").find("option:selected").text() == "请选择") {
                                    BUI.Message.Alert("请选择法院和部门！", null, "warning");
                                    return;
                                }
                                if ($("#new_court_no").find("option:selected").text() == $("#old_court_text2").val() &&
                                    $("#new_court_no").find("option:selected").text() == $("#old_dep_text2").val()) {
                                    BUI.Message.Alert("请不要选择相同的法院和部门！", null, "warning");
                                    return;
                                }
                                sqdz();
                            }
                        },
                        {
                            text: '关闭', elCls: 'button', handler: function () {
                                dform.clearFields();
                                this.close();
                            }
                        }
                    ],
                    header: "",
                    success: function (data) {
                        this.close();
                    }
                });
                var dform = new Form.HForm({
                    srcNode: '#userinfo_dz_form',
                    submitType: 'ajax',
                    callback: function (data) {
                        if (data.success) {
                            BUI.Message.Alert("申请成功！", null, "success");
                        } else {
                            BUI.Message.Alert("申请失败", null, "warning");
                        }
                        dform.clearFields();
                        dzDialog.close();
                    }
                }).render();
                dzForm = dform;


                //--------------初始化新增按钮弹窗Start-----------------

                // 新增窗体
                var editDialog = new Overlay.Dialog({
                    title: '新增',
                    width: '90%',
                    height: 450,
                    buttons: [
                        {
                            text: '保存', elCls: 'button button-primary', handler: function () {
                                if ($("#id").val() != '') {
                                    userCheck();
                                } else {
                                    idCardCheck();
                                }
                            }
                        },
                        {
                            text: '关闭', elCls: 'button', handler: function () {
                                this.close();
                            }
                        }
                    ],
                    contentId: 'content', // 配置DOM容器的编号
                    success: function () {
                        this.close();
                    }
                });


                //-------------------修改部门---------------
                var deptform = new Form.HForm({
                    srcNode: '#dept_edit',
                    submitType: 'ajax',
                    callback: function (data) {

                    }
                }).render();
                var deptDialog = new Overlay.Dialog({
                    title: '部门修改',
                    buttons: [
                        {
                            text: '保存', elCls: 'button button-primary', handler: function () {
                                deptform.valid();
                                if (deptform.isValid()) {
                                    var url = "<%=path%>/code/updateDeptInfo";
                                    var datas = {
                                        courtNo: $("#hide_court_no").val(),
                                        deptNo: $("#hide_dept_no").val(),
                                        orgType: $("#org_type").val()
                                    };
                                    $.post(url, datas, function (data) {
                                        if (data) {
                                            var str = "";
                                            switch (datas['orgType']) {
                                                case "1":
                                                    str += '业务部门';
                                                    break;
                                                case "2":
                                                    str += '综合部门';
                                                    break;
                                                case "8":
                                                    str += '派出法庭';
                                                    break;
                                                default:
                                                    str += '未确认';
                                            }
                                            $("#deptNoId").html(str);
                                            showTip("修改成功");
                                        } else {
                                            showTip("修改失败");
                                        }
                                        deptDialog.close();
                                    });
                                }
                            }
                        },
                        {
                            text: '关闭', elCls: 'button', handler: function () {
                                this.close();
                            }
                        }],
                    contentId: 'deptEditContent', //配置DOM容器的编号
                    success: function () {
                        this.close();
                    }
                });
                deptDialog.on("closing", function () {
                    deptform.clearFields();
                    deptform.clearErrors();
                });
                //-------------------修改部门结束---------------
                //------------------挂靠部门--------------------
                var affiliatedForm = new Form.HForm({
                    srcNode: '#affiliated_form',
                    submitType: 'ajax',
                    callback: function (data) {
                        var datas = {
                            'umsTemporaryPosition.uuid': $("#hideId").val(),
                        };
                        queryInfo(datas);
                        if (data.success) {
                            BUI.Message.Alert("添加成功", "success");
                        } else {
                            var str = '';
                            switch (data.reason) {
                                case 1:
                                    str += '当前用户在该法院部门下已有挂靠记录';
                                    break;
                                case 2:
                                    str += '系统平台对应用户信息不存在,请联系管理员';
                                    break;
                                case 5:
                                    str += '操作错误,不能在用户本法院本部门下挂靠部门';
                                    break;
                                default:
                                    str += '操作过程出现错误,请联系管理员';
                            }
                            BUI.Message.Alert("添加失败   " + str, "error");
                        }
                    }
                }).render();
                TForm = affiliatedForm;
                var affiliatedDialog = new Overlay.Dialog({
                    title: '挂靠部门',
                    buttons: [
                        {
                            text: '关闭', elCls: 'button', handler: function () {
                                this.close();
                            }
                        }],
                    contentId: 'affiliated_info', //配置DOM容器的编号
                    success: function () {
                        this.close();
                    }
                });
                affiliatedDialog.on("closing", function () {
                    affiliatedForm.clearFields();
                    affiliatedForm.clearErrors();
                });
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
                            'success': '<div class="success"><img id="qpic" src="{url}" title="{name}" style="width: 100%;height: auto;"/></div>',
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

                var userinfo = {};

                $(".bui-grid-body").height($(document).height() - 200);
                $(".bui-grid-bbar").height(35);
                $("#t3").height($(".bui-grid").height());
                datepickerFix($, BUI.Calendar);

                function sqdz() {
                    var url = "<%=path%>/userinfo/selectChangeJob";
                    var datas = {id: $("#user_id").val()}
                    $.post(url, datas, function (data) {
                        if (data.success) {
                            BUI.Message.Alert("您还有未处理的调职申请，请不要重复提交！", "warning");
                            dform.clearFields();
                            return;
                        }
                        dzForm.submit();
                    });
                    dzDialog.close();
                }

                function loadAllCourtList(cbo, firstLine, callback) {
                    $.getJSON("/ums/code/codeListByTypeWithNoAspect", {}, function (data) {
                        if (firstLine) {
                            $(cbo).html(firstLine);
                        } else {
                            $(cbo).empty();
                        }
                        $.each(data, function (index, values) {   // 解析出data对应的Object数组
                            $.each(values, function (index2, value) {
                                $(cbo).append($("<option>").attr({"value": value.id}).text(value.codeName));
                            });
                        });
                        if (callback) {
                            callback();
                        }
                    });
                }

                // 加载在编人员信息
                function loadUserinfo(id) {
                    form.clearFields();
                    form.clearErrors();
                    $("#age").html("0岁");
                    $('#birthday').val("");
                    $('#birthday_show').html("");
                    $('#password').val("");
                    $(".show_info").hide();
                    $(".show_icon").hide();
                    $.getJSON("<%=basePath%>userinfo/one", {id: id, _: new Date()}, function (data) {
                        userinfo = data;
                        reloadUserinfo();
                        console.log("123");
                    });
                }

                function reloadUserinfo() {
                    var userType = parseInt(userinfo["userType"]) - 10;
                    var html = "<option value='" + userType + "'></option>"
                    $("#userinfo_form [name='userInfo.userType']").append(html);
                    $("#userinfo_form [name^='userInfo.']").each(function () {
                        $(this).val(userinfo[$(this).attr("id")]);
                        if ($(this).attr("id") === "courtNo") {
                            changeSelect2Value($(this), userinfo[$(this).attr("id")]);
                            loadDeptList($("#userinfo_form #department"), $(this).val(), firstLine, function () {
                                // $("#userinfo_form #department").val(userinfo.department);
                                changeSelect2Value($("#department"), userinfo.department);
                            });
                        }
                        if ($(this).attr("name").indexOf("birthday") > 0) {
                            $("#birthday_show").html(userinfo[$(this).attr("id")]);
                        }
                        if (auth) {
                        } else {
                            $("#courtNo").attr("disabled", true);
                        }
                        if ($(this).hasClass("select22")) {
                            $(this).select2().select2("val", userinfo[$(this).attr("id")]);
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
                    photoEdit = false;
                    calcAge();
                    workTotal();
                    courtTotal();
                }

                function resetUserinfoForm() {
                    $("#age").html("0岁");
                    $('#birthday').val("");
                    $('#birthday_show').html("");
                    $('#password').val("");
                    $(".show_info").hide();
                    $(".show_icon").hide();
                    if (editDialog.get("title") == '新增') {
                        initSelect2("select22");
                        $("select.Courtcode").select2();
                        form.clearFields();
                        form.clearErrors();
                    } else {
                        reloadUserinfo();
                    }
                }
            });
        }
    }








    // 编辑后的保存
    function canEdit() {
        $.getJSON("<%=basePath%>auth/canIEdit", {}, function (can) {
            if (!can) {
                $(".bui-bar.bui-grid-button-bar button").filter(":gt(0)").addClass("hide");
                $(".btn-enabled").remove();
                $(".btn-dz").remove();
                $(".search").filter(":gt(0)").remove("hide");
            }
        });
    }

    // 切换选项卡
    function changeMarkers(obj) {
        $(obj).parent().parent().find(".active").removeClass("active");
        $(obj).parent().addClass("active");
    }

    //验证身份证号并获取出生日期
    function getBirthdatByIdNo(iIdNo) {
        var tmpStr = "";
        var strReturn = "";
        iIdNo = trim(iIdNo);
        if ((iIdNo.length != 15) && (iIdNo.length != 18)) {
            strReturn = "";
            return strReturn;
        }
        if (iIdNo.length == 15) {
            tmpStr = iIdNo.substring(6, 12);
            tmpStr = "19" + tmpStr;
            tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-" + tmpStr.substring(6)
            return tmpStr;
        } else {
            tmpStr = iIdNo.substring(6, 14);
            tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-" + tmpStr.substring(6)
            return tmpStr;
        }
    }

    function userCheck() {
        var url = "<%=path%>/checkUserName";
        var datas = {
            courtStdNo: $("#courtNo").val(),
            username: $("#username").val(),
            id: $("#id").val(),
            valid: 1,
            isEdit: isEdit
        };
        myForm.valid();
        if (myForm.isValid()) {
            $.post(url, datas, function (data) {
                if (data.UserNameCheck == "false") {
                    if (isEdit) {
                        $('#password').val("");
                    }
                    $("#courtNo").attr("disabled", false);
                    // 将用户状态设为启用
                    $("#isValid").val("1");
                    myForm.submit();
                } else {
                    $(".show_info").show();
                    $(".show_icon").show();
                    var a = document.getElementById("show_e");
                    // 取消<a>标签原先的onclick事件,使<a>标签点击后通过href跳转(因为无法用js跳转)^-^
                    a.setAttribute("onclick", '');
                    // 激发标签点击事件OVER
                    a.click("return false");
                }
            })
        } else {
            $("#isValid").val("1");//将用户状态设为启用
            myForm.submit();
        }
    }

    function userCheck2() {
        var url = "<%=path%>/checkCompanyAndUserName";
        var datas = {
            courtStdNo: $("#J_Form #courtNo").val(),
            username: $("#J_Form #username").val(),
            id: $("#J_Form input[name='userInfo.id']").val(),
            companyInfoId: $("#J_Form #companyInfoId").val(),
            companyName: $("#J_Form #companyName").val(),
            valid: 1
        }

        V.form.valid();
        if (V.form.isValid()) {
            $("#J_Form #courtNo").attr("disabled", false);
            $.post(url, datas, function (data) {
                var userFlag = false;
                var companyFlag = false;
                if (data.CompanyInfoCheck == "true") {
                    companyFlag = true;
                } else {
                    $(".companyCheck").show();
                    var a = document.getElementById("show_e_company");
                    a.setAttribute("onclick", '');
                    a.click("return false");
                }
                if (data.UserNameCheck == "true") {
                    if (isEdit) {
                        $('#password').val("");
                    }
                    userFlag = true;
                } else {
                    $(".userNameCheck").show();
                    var a = document.getElementById("show_e_user");
                    a.setAttribute("onclick", '');
                    a.click("return false");
                }
                if (userFlag && companyFlag) {
                    $("#courtNo").attr("disabled", false);
                    V.form.submit();
                }
            });
        } else {
            V.form.submit();
        }
    }

    function idCardCheck() {
        var url = "<%=path%>/checkIdCard";
        var datas = {
            courtStdNo: $("#J_Form #courtNo").val(),
            username: $("#J_Form #username").val(),
            id: $("#J_Form #id").val(),
            idcard: $("#J_Form #idcard").val()
        };

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
                }
            })
        } else {
            myForm.submit();
        }
    }

    function idCardCheck2() {
        var url = "<%=path%>/checkIdCard";
        var datas = {
            courtStdNo: $("#courtNo").val(),
            username: $("#username").val(),
            id: $("input[name='userInfo.id']").val(),
            idcard: $("#idcard").val()
        };

        V.form.valid();
        if (V.form.isValid()) {
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
                    var showStr = "<span>该人员已经存在,请对相应人员进行操作 ";
                    showStr += (typeStr == "" ? "" : " , 人员类型为 : " + typeStr);
                    showStr += (typeStr == "" ? "" : " , 人员状态为 : " + enabledStr);
                    showStr += ("<a style='margin-left:10px;' target='_blank' href='<%=basePath%>view/detail_new?id=" + data.userId + " '>点击查看</a></span>");
                    BUI.Message.Alert(showStr, "warning");
                    //打开新页面
                    <%--open("<%=basePath%>view/detail_new?id=" + data.userId);--%>

                }
            })
        } else {
            V.form.submit();
        }
    }

    function affiliatedSubmit() {
        if ((V.courtCode == $("#courtInfoNo").val()) && (V.department == $("#departmentInfo").val())) {
            BUI.Message.Alert("操作错误,不能在用户本法院本部门下挂靠部门", "warning");
        } else {
            TForm.submit();
        }
    }

    function deleteInfo(obj) {
        var court = $(obj).attr("court");
        var dept = $(obj).attr("dept");
        var url = "<%=path%>/affiliatedInfo/delete";
        var datas = {
            'umsTemporaryPosition.courtCode': court,
            'umsTemporaryPosition.uuid': $("#hideId").val(),
            'umsTemporaryPosition.department': dept,
        };
        $.post(url, datas, function (data) {
            queryInfo(datas);
            if (data.success) {
                BUI.Message.Alert("删除成功", "success");
            } else {
                BUI.Message.Alert("删除失败", "error");
            }
        });
    }

    function queryInfo(datas) {
        $("#mainTable").html('');
        $.post('<%=path%>/affiliatedInfo/select', datas, function (data) {
            if (data.list.length > 0) {
                $("#mainTable").append(
                    '<TR class="titile_tr tr_1">' +
                    '<TD>姓名</TD>' +
                    '<TD>排序</TD>' +
                    '<TD>挂靠法院</TD>' +
                    '<TD>部门</TD>' +
                    '<TD>添加时间</TD>' +
                    '<TD>操作</TD>' +
                    '</TR>'
                );
                for (var i in data.list) {
                    if (i % 2 == 1) {
                        $("#mainTable").append(
                            '<TR class="show_tr tr_1 ">' +
                            '<TD>' + data.list[i].fullname + '</TD>' +
                            '<TD class="sortNo">' + data.list[i].sortno + '</TD>' +
                            '<TD>' + data.list[i].courtstdname + '</TD>' +
                            '<TD>' + data.list[i].department + '</TD>' +
                            '<TD>' + data.list[i].creatdate + '</TD>' +
                            '<TD><a class="show_a" onclick="deleteInfo(this)" court=' + data.list[i].courtcode + '  dept=' + data.list[i].dept + ' userid=' + data.list[i].userid + '>删除</a></TD>' +
                            '</TR>'
                        );
                    } else {
                        $("#mainTable").append(
                            '<TR class="show_tr ">' +
                            '<TD>' + data.list[i].fullname + '</TD>' +
                            '<TD  class="sortNo">' + data.list[i].sortno + '</TD>' +
                            '<TD>' + data.list[i].courtstdname + '</TD>' +
                            '<TD>' + data.list[i].department + '</TD>' +
                            '<TD>' + data.list[i].creatdate + '</TD>' +
                            '<TD><a class="show_a" onclick="deleteInfo(this)" court=' + data.list[i].courtcode + ' dept=' + data.list[i].dept + ' userid=' + data.list[i].userid + '>删除</a></TD>' +
                            '</TR>'
                        );
                    }
                }
                $("#t_tip").remove();
                $(".containner").prepend("<h4 id='t_tip'>双击排序列可修改序号</h4>");
                initUpdate();
            } else {
                $("#t_tip").remove();
            }
        })
    }

    //在职状态类型变化
    function TypeChange(obj) {
        $(obj).parent().parent().find("div").each(function () {
            $(this).find("a").removeClass("selectDiv");
        });
        $(obj).addClass("selectDiv");
        $("#isValidShow").html($(".selectDiv").html());
        var condition = {};
        $("#userinfo_search_form .search_field").each(function () {
            condition[$(this).attr("name")] = $(this).val();
        });
        V.cloumn[9].set("visible", false);
        if ($(".selectDiv").attr('codeId') == 0) {
            condition['isInfoComplete'] = 0;
            condition['isValid'] = 1;
            condition['leaveReason'] = '';
        } else if ($(".selectDiv").attr('codeId') == -1) {
            condition['isInfoComplete'] = 0;
            condition['isValid'] = 0;
            condition['leaveReason'] = '';
        } else if ($(".selectDiv").attr('codeId') == -2) {
            //查询返聘人员
            condition['isInfoComplete'] = 0;
            condition['isValid'] = 1;
            condition['leaveReason'] = 5; //返聘人员的代码
            V.cloumn[9].set("visible", true);
        } else {
            condition['isInfoComplete'] = 0;
            condition['isValid'] = '';
            condition['leaveReason'] = $(".selectDiv").attr('codeId');
            if ($(".selectDiv").attr('codeId') == 5) {
                V.cloumn[9].set("visible", true);
            }
        }
        Rstore.load(condition);
        query_condition = Rstore.get("lastParams");
    }

    // 初始化select2框
    function initSelect2(class_) {
        $("select." + class_).each(function () {
            var firstOptionValue = $(this).find("option:eq(0)").val();
            $(this).select2().select2("val", firstOptionValue);
            $(this).change();
        });
    }

    function changeSelect2Value(_this, val) {
        $(_this).select2().select2("val", val);
        $(_this).change();
    }

    // 加载编外人员信息
    function loadDialogInfo(obj, department) {
        var url = '<%=basePath%>external/form';
        var datas = {};
        if (obj != null) {
            datas['userId'] = obj;
        }
        $.post(url, datas, function (data) {
            $("#DialogInfo").empty();
            $("#DialogInfo").html(data);
            V.dialog.show();
            loadDeptList($("#J_Form #department"), CourtNo, firstLine, function () {
            });
            if (auth) {
            } else {
                $("#J_Form #courtNo").attr("disabled", true);
            }
            if ($(".initClass").length > 0) {
                location.href = $(".initClass").attr("href");
                $(".initClass").click();
            }
            setTimeout(function () {
                $("#J_Form #department").select2().select2("val", department);
                $("#J_Form #isValid").val(1);
                V.form.clearErrors();
            }, 1000);
        });
    }

    //-------------日期规范输出Start---------------
    function dateFormat(value) {
        return value + '年';
    }

    function ageFormat(value) {
        return value + '岁';
    }

    //-------------日期规范输出End--------------

    // 计算年龄
    function calcAge() {
        $("#userinfo_form #age").val(calcYears($("#userinfo_form #birthday").val()) + '岁');
    }

    function workTotal() {
        $("#userinfo_form #totalSeniority").text(workTotalYears($("#userinfo_form #workDate").val(), $("#userinfo_form #extraSeniority").val(), $("#userinfo_form #deductionSeniority").val()) + '年');
    }

    function courtTotal() {
        $("#userinfo_form #totalCourtYear").text(workTotalYears($("#userinfo_form #enterDate").val(), $("#userinfo_form #beforeCourtWorkYear").val()) + '年');
    }

    function calcDay(obj, target) {
        var age = $(obj).val();
        if (isNaN(age) || age == null || age == '') {
            $(target).val("");
            return;
        }
        var now_day = new Date();
        var year = now_day.getFullYear();
        var month = now_day.getMonth() + 1;
        var date = now_day.getDate();
        $(target).val((year - age) + "-" + month + "-" + date);
    }

    //-------------计算日期相关函数End---------------
</script>

</html>
