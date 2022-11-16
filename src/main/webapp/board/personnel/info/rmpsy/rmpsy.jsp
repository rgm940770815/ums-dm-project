<%@ page import="cn.net.withub.ums.common.UmsConstant" %>
<%@ page import="cn.net.withub.ums.entity.UmsUserInfoView" %>
<%@ page import="cn.net.withub.ums.auth.AuthType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    // 当前登录的人员账户信息
    UmsUserInfoView umsUserInfoView = (UmsUserInfoView) session.getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
    String fullname = umsUserInfoView.getFullname();
    Integer courtno = umsUserInfoView.getCourtNo();
    AuthType authType = umsUserInfoView.getAuthType();
%>
<!DOCTYPE html>
<html>
<head>
    <title>人民陪审员信息管理</title>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <jsp:include page="/basic_import.jsp"></jsp:include>

    <!--引入ztree-->
    <link type="text/css" rel="stylesheet" href="<%=basePath%>/common/zTree/zTreeStyle/zTreeStyle.css">
    <script type="text/javascript" src="<%=basePath%>/common/zTree/jquery.ztree.all.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/common/zTree/jquery.ztree.exhide.min.js"></script>

    <%--验证身份证的js--%>
    <script src="<%=basePath%>/js/common/idchecker.js" type="text/javascript"></script>

    <style type="text/css">

        /**内容超出 出现滚动条 **/
        .bui-stdmod-body {
            overflow-x: hidden;
            overflow-y: auto;
        }

        body {
            margin: 0;
            padding: 0;
            font-size: 14px;
            position: fixed;
            width: 100%;
            height: 100%
        }

        .main_div {
            height: 100%;
            width: 100%;
            display: flex;
            display: -webkit-flex;
            flex-direction: column;
        }

        .main_div > div {
            clear: both;
            padding: 5px;
            width: 96%;
            margin: 0 auto;
        }

        .breadcrumb {
            padding: 7px 14px;
            margin: 0 0 20px;
            list-style: none;
            border: 1px solid #ddd;
            -webkit-border-radius: 4px;
            -moz-border-radius: 4px;
            border-radius: 4px;
            -webkit-box-shadow: inset 0 1px 0 #ffffff;
            box-shadow: inset 0 1px 0 #ffffff;
        }

        .breadcrumb li {
            display: inline-block;
            *display: inline;
            *zoom: 1;
            text-shadow: 0 1px 0 #ffffff;
        }

        .content_div {
            flex: 1;
            display: flex;
            display: -webkit-flex;
        }

        .base_container {
            border: 1px solid #d3d3d3;
        }

        .xiaobiaoti {
            line-height: 36px;
            border-left: 5px solid #009E94;
            margin: 0 0 5px;
            padding: 0 10px;
            background-color: #f2f2f2;
            font-size: 14px;
            font-weight: 400;
        }

        .base_container > ul {
            margin: 5px;
            padding: 10px;
        }

        .left-container {
            margin-right: 10px;
            width: 280px;
            height: 98%;
            overflow-x: hidden;
        }

        .right-container {
            display: flex;
            display: -webkit-flex;
            flex-direction: column;
            flex: 1;
            overflow-x: hidden;
        }

        .nrlx:hover {
            color: #009688;
            cursor: pointer;
        }

        #search_form .row {
            margin-left: 0px;
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


<div class="main_div">
    <div class="top_div">
        <span class="layui-breadcrumb">
            <a><cite>人事管理 / </cite></a>
            <a><cite>信息管理 / </cite></a>
            <a><cite>人民陪审员信息管理</cite></a>
        </span>
    </div>

    <div class="content_div">
        <div class="left-container base_container">
            <h2 class="xiaobiaoti">法院</h2>
            <div class="ztree" id="ztree-div"></div>
        </div>
        <div class="right-container">
            <!--普通查询表单-->
            <div id="searchDiv">
                <form id="search_form" class="form-horizontal">
                    <div class="row">
                        <div class="control-group span">
                            <label class="control-label">姓名：</label>
                            <div class="controls">
                                <input type="text" id="name" class="control-text search_field" name="name"/>
                            </div>
                        </div>
                        <div class="control-group span">
                            <label class="control-label">性别：</label>
                            <div class="controls">
                                <select id="gender" typeId="3" name="gender" class="code"></select>
                            </div>
                        </div>
                        <div class="control-group span">
                            <label class="control-label">民族：</label>
                            <div class="controls">
                                <select id="nation" typeId="1005" name="nation" class="code"></select>

                            </div>
                        </div>
                        <div class="control-group span">
                            <label class="control-label">学历：</label>
                            <div class="controls">
                                <select id="education" typeId="119" name="education" class="code"></select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="control-group span">
                            <label class="control-label">政治面貌：</label>
                            <div class="controls">
                                <select id="political" typeId="1013" name="political" class="code"></select>
                            </div>
                        </div>
                        <div class="control-group span">
                            <label class="control-label">职业：</label>
                            <div class="controls">
                                <select id="occupation" typeId="121" name="occupation" class="code"></select>
                            </div>
                        </div>
                        <div class="control-group span">
                            <label class="control-label">地区分布：</label>
                            <div class="controls">
                                <select id="areaDistribution" typeId="122" name="areaDistribution" class="code"></select>
                            </div>
                        </div>
                        <div class="control-group span">
                            <label class="control-label">陪审员状态：</label>
                            <div class="controls">
                                <select id="psyzt" typeId="126" name="psyzt" class="code"></select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="control-group span">
                            <label class="control-label">是否停用</label>
                            <div class="controls">
                                <select id="sfty" name="sfty">
                                    <option value="">请选择</option>
                                    <option value="0">已启用</option>
                                    <option value="1">已停用</option>
                                </select>
                            </div>
                        </div>
                        <div class="control-group span">
                            <button type="button" class="button button-primary" id="searchButton">查询</button>
                            <button type="button" class="button" id="resetButton">重置</button>
                        </div>
                    </div>

                </form>
            </div>
            <%--显示人员列表--%>
            <div id="grid" style="flex: 1"></div>
        </div>
    </div>
</div>

<%--添加编辑模态框--%>
<div id="addDialog" class="bui-hidden">
    <form id="addForm" class="form-horizontal">
        <input type="text" id="id_addForm" name="umsRmpsyJbxx.id" style="display: none;">
        <input type="text" id="isvalid_addForm" name="umsRmpsyJbxx.isvalid" style="display: none;">
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>姓名：</label>
                <div class="controls">
                    <input id="name_addForm" name="umsRmpsyJbxx.name" type="text" class="control-text" data-rules="{required:true}">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>法院：</label>
                <div class="controls">
                    <select id="courtno_addForm" typeId="1" name="umsRmpsyJbxx.courtno" class="code" data-rules="{required:true}"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>身份证类型：</label>
                <div class="controls">
                    <select id="sfzlx_addForm" typeId="125" name="umsRmpsyJbxx.sfzlx" data-rules="{required:true}" class="code"></select>
                </div>
            </div>
            <div style="display: inline-block;margin-left: 70px;position: absolute;">
                <div id="photo"></div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>身份证号：</label>
                <div class="controls">
                    <input id="idcard_addForm" type="text" name="umsRmpsyJbxx.idcard" class="control-text" data-rules="{required: true, idcheck: null}"/>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>性别：</label>
                <div class="controls">
                    <select id="gender_addForm" data-rules="{required:true}" typeId="3" name="umsRmpsyJbxx.gender" class="code"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>出生日期：</label>
                <div class="controls">
                    <input id="birthday_addForm" name="umsRmpsyJbxx.birthday" type="text" class="control-text calendar2" data-rules="{required:true}">
                </div>
            </div>
            <div style="display: inline-block;margin-left: 70px;position: absolute;margin-top: 168px;">
                <p><strong>照片要求:</strong></p>
                <p>1、 选择近期免冠清晰照片</p>
                <p>2、 照片高宽比为5∶4</p>
                <p>3、 不大于300KB</p>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>民族：</label>
                <div class="controls">
                    <select id="nation_addForm" data-rules="{required:true}" typeId="1005" name="umsRmpsyJbxx.nation" class="code"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>学历：</label>
                <div class="controls">
                    <select id="education_addForm" typeId="119" data-rules="{required:true}" name="umsRmpsyJbxx.education" class="code"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>政治面貌：</label>
                <div class="controls">
                    <select id="political_addForm" typeId="1013" name="umsRmpsyJbxx.political" data-rules="{required:true}" class="code"></select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>家庭住址：</label>
                <div class="controls">
                    <input id="homeAddress_addForm" name="umsRmpsyJbxx.homeadress" type="text" data-rules="{required:true}"/>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">固定电话：</label>
                <div class="controls">
                    <input id="fixedTel_addForm" type="text" name="umsRmpsyJbxx.fixedtel" class="control-text"/>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>手机电话：</label>
                <div class="controls">
                    <input id="phoneNum_addForm" type="text" name="umsRmpsyJbxx.phonenum" class="control-text" data-rules="{required:true, phoneNumCheck:11}"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label">电子邮箱：</label>
                <div class="controls">
                    <input id="email_addForm" type="text" name="umsRmpsyJbxx.email" class="control-text" data-rules="{email:true}"/>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>地区分布：</label>
                <div class="controls">
                    <select id="areaDistribution_addForm" typeId="122" name="umsRmpsyJbxx.areadistribution" class="code" data-rules="{required:true}"></select>
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label"><s>*</s>职业：</label>
                <div class="controls">
                    <select id="occupation_addForm" typeId="121" name="umsRmpsyJbxx.occupation" class="code" data-rules="{required:true}"></select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>工作单位：</label>
                <div class="controls">
                    <input id="workCompany_addForm" type="text" name="umsRmpsyJbxx.workcompany" class="control-text" data-rules="{required:true}"/>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>工作区域：</label>
                <div class="controls">
                    <input type="text" id="workArea_addForm" name="umsRmpsyJbxx.workarea" class="control-text" placeholder="--请双击选择--" style="background-color: #E1EFFF;" readonly data-rules="{required:true}"/>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">专业特长：</label>
                <div class="controls">
                    <select id="zytc_addForm" typeId="123" name="umsRmpsyJbxx.zytc" class="code"></select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <a id="show_e" href="#show_error"></a>
                <label class="control-label"><s>*</s>任期开始时间：</label>
                <div class="controls">
                    <input id="rqkssj_addForm" type="text" name="umsRmpsyJbxx.rqkssj" class="calendar2" data-rules="{required:true}"/>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>是否新增：</label>
                <div class="controls">
                    <select id="sfxz_addForm" name="umsRmpsyJbxx.sfxz" typeId="108" class="code" data-rules="{required:true}"></select>
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label"><s>*</s>届数：</label>
                <div class="controls">
                    <input id="js_addForm" type="text" name="umsRmpsyJbxx.js" class="control-text" data-rules="{required:true, number: true}"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>参与案件类型：</label>
                <div class="controls bui-form-group" data-rules="{checkRange:1}">
                    <input type="checkbox" id="cyajxz_addForm_1" name="umsRmpsyJbxx.cyajxz" value="刑事"/><label for="cyajxz_addForm_1">刑事</label>
                    <input type="checkbox" id="cyajxz_addForm_2" name="umsRmpsyJbxx.cyajxz" value="民事"/><label for="cyajxz_addForm_2">民事</label>
                    <input type="checkbox" id="cyajxz_addForm_3" name="umsRmpsyJbxx.cyajxz" value="行政"/><label for="cyajxz_addForm_3">行政</label>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">年度参审次数：</label>
                <div class="controls">
                    <input id="ndcscs_addForm" type="text" name="umsRmpsyJbxx.ndcscs" class="control-text" disabled/>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">年度陪审费用：</label>
                <div class="controls">
                    <input id="ndpsfy_addForm" type="text" name="umsRmpsyJbxx.ndpsfy" class="control-text" disabled/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>陪审员状态：</label>
                <div class="controls">
                    <select id="psyzt_addForm" typeId="126" name="umsRmpsyJbxx.psyzt" class="code" data-rules="{required:true}"></select>
                </div>
            </div>
            <div class="control-group12 span10">
                <label class="control-label">退出方式：</label>
                <div class="controls">
                    <select id="tcfs_addForm" typeId="124" name="umsRmpsyJbxx.tcfs" class="code" data-rules="{tcfs: true}"></select>
                </div>
            </div>
        </div>
    </form>
</div>

<%--工作区域模态框--%>
<div id="workAreaDialog" style="display: none">
    <div class="row" style="margin-bottom: 10px;">
        <div class="control-group span">
            <button type="button" class="button button-primary" id="gjzjs">关键字检索</button>
            <button type="button" class="button" id="dmxz">代码选择</button>
        </div>
    </div>
    <%--    关键字检索div--%>
    <div id="gjzjsDiv">
        <div id="searchWorkArea" class="row" style="margin-bottom: 10px;">
            <div class="control-group span">
                <label class="control-label">关键词检索：</label>
                <input type="text" id="workAreaGjc" class="control-text" name="workAreaGjc"/>
            </div>
            <div class="control-group span">
                <button type="button" class="button button-primary" id="searchButtonForGjc">查询</button>
            </div>
        </div>
        <div id="workAreaGrid"></div>
    </div>
    <%--    代码选择div--%>
    <div id="dmxzDiv" style="display: none;">
        <div>
            <ul id="dmxzTree" class="ztree"></ul>
        </div>
    </div>
</div>

<%--Excel下载--%>
<div class="hide">
    <form action="<%=path%>/rmpsyAction/downloadExcelForRmpsy" method="post" id="excel_form"></form>
</div>

</body>


<script type="text/javascript">

    // 权限，分为PERSON, COURT, ADMIN
    var authType = "<%=authType%>";
    // 装参数的
    var condition = {page: 1, limit: 10};
    // 图片是否上传成功
    var photoEdit = false;
    // 左侧树的法院
    var leftZtreeCourt = "<%=courtno%>";
    // 存code列表的本地数组
    var localCodeArrays = {};

    var totalCount = $("select.code").length;
    var count = 0;
    // DOM加载完毕之后执行。
    $(function () {
        // 循环获取下拉框的数据
        $("select.code").each(function () {
            var id = $(this).attr("id");
            loadOptionByTypeId(id);
        });
    });

    // 根据typeid加载select下拉选项
    function loadOptionByTypeId(id) {
        var firstOption = '<option value="">请选择</option>';
        var typeId = $("#" + id).attr("typeId");
        $.ajax({
            url: "<%=basePath%>code/codeListByType",
            data: {typeId: typeId},
            dataType: "json",
            async: true,
            success: function (data) {
                var option = firstOption;
                if (null == data) {
                    console.log("下拉框返回值为空----id:" + id + "|| typeId:" + typeId);
                } else {
                    if (typeId == 1) {
                        data = data.data;
                    }
                    // 同时将获取的code列表存入本地数组，用来替换grid表中的数字显示
                    localCodeArrays[typeId] = data;
                    for (var i = 0; i < data.length; i++) {
                        option += '<option value="' + data[i].id + '">' + data[i].codeName + '</option>';
                    }
                    $("#" + id).append(option);
                    $("#" + id).select2();
                    count++;
                }
            }
        });
    }

    // 定时器：一秒执行一次，满足条件的时候执行下面的
    var clock = setInterval(countDown, 200);

    // 等加载完所有下拉选后，再加载具体逻辑
    function countDown() {
        if (totalCount == count) {
            clearInterval(clock); //清除js定时器
            BUI.use(['common/search', 'bui/tree', 'bui/data', 'bui/calendar', 'bui/overlay', 'bui/form', 'bui/uploader', 'bui/mask', 'bui/grid', 'common/main'], function (Search, Tree, Data, Calendar, Overlay, Form, Uploader, Mask, Grid) {

                var columns = [
                    {title: '姓名', dataIndex: 'name', width: '100'},
                    {
                        title: '任选法院', dataIndex: 'courtno', width: '200', renderer: function (key, value) {
                            return getNameFromLocalArray($("#courtno_addForm").attr("typeId"), key);
                        }
                    },
                    {title: '身份证号', dataIndex: 'idcard', width: '200'},
                    {
                        title: '性别', dataIndex: 'gender', width: '100', renderer: function (key, value) {
                            return getNameFromLocalArray($("#gender_addForm").attr("typeId"), key);
                        }
                    },
                    {
                        title: '民族', dataIndex: 'nation', width: '100', renderer: function (key, value) {
                            return getNameFromLocalArray($("#nation_addForm").attr("typeId"), key);
                        }
                    },
                    {
                        title: '学历', dataIndex: 'education', width: '100', renderer: function (key, value) {
                            return getNameFromLocalArray($("#education_addForm").attr("typeId"), key);
                        }
                    },
                    {
                        title: '政治面貌', dataIndex: 'political', width: '100', renderer: function (key, value) {
                            return getNameFromLocalArray($("#political_addForm").attr("typeId"), key);
                        }
                    },
                    {
                        title: '地区分布', dataIndex: 'areadistribution', width: '100', renderer: function (key, value) {
                            return getNameFromLocalArray($("#areaDistribution_addForm").attr("typeId"), key);
                        }
                    },
                    {
                        title: '职业', dataIndex: 'occupation', width: '100', renderer: function (key, value) {
                            return getNameFromLocalArray($("#occupation_addForm").attr("typeId"), key);
                        }
                    },
                    {
                        title: '陪审员状态', dataIndex: 'psyzt', width: '100', renderer: function (key, value) {
                            return getNameFromLocalArray($("#psyzt_addForm").attr("typeId"), key);
                        }
                    },
                    {
                        title: '操作', width: "50", sortable: false, renderer: function (value, obj) {
                            var spanStr = '<a href="javascript: void(0);">';
                            if (obj.isvalid == 1) {
                                spanStr += '<span class="grid-valid btn-disabled" title="停用">停用</span>';
                            } else {
                                spanStr += '<span class="grid-valid btn-enabled" title="启用">启用</span>';
                            }
                            spanStr += '</a>';
                            return spanStr;
                        }
                    }
                ];

                var store = new Data.Store({
                    autoLoad: true,
                    pageSize: 10,
                    params: {
                        sfty: '0'
                    },
                    proxy: {
                        url: '<%=basePath%>rmpsyAction/getList',
                        method: 'post',
                        dataType: 'json'
                    }
                });

                var grid = new Grid.Grid({
                    render: '#grid',
                    width: '100%',//这个属性一定要设置
                    // height: $("div[class='right-container']").height() - $("#searchDiv").height() - 31,
                    height: $("#grid").height() -25 ,
                    columns: columns,
                    emptyDataTpl: '<div class="centered"><h2>当前无数据显示</h2></div>',
                    plugins: [Grid.Plugins.RowNumber, Grid.Plugins.RadioSelection],	// 插件形式引入多选表格
                    bbar: {
                        // pagingBar:表明包含分页栏
                        pagingBar: true
                    },
                    tbar: { //添加、删除
                        items: [
                            {
                                btnCls: 'button button-small',
                                text: '新增',
                                listeners: {
                                    'click': function (ev) {
                                        // 清除上传图片的
                                        queue.setItems([]);
                                        // 重置下拉框
                                        $("#addForm select").each(function () {
                                            var id = $(this).attr("id");
                                            var name = $(this).attr("name");
                                            var value = $(this).attr("value");
                                            $(this).select2("val", "");
                                        });
                                        // 清除checkbox
                                        $("#cyajxz_addForm_1").prop("checked", false);
                                        $("#cyajxz_addForm_2").prop("checked", false);
                                        $("#cyajxz_addForm_3").prop("checked", false);
                                        // 清除表单中的值
                                        form.clearFields();
                                        // 清除表单中的错误
                                        form.clearErrors();
                                        $("#ndcscs_addForm").val("0");
                                        $("#ndpsfy_addForm").val("0");
                                        // 给模态框添加标题
                                        addDialog.set("title", "新增人民陪审员");
                                        // 显示模态框
                                        addDialog.show();
                                        // 给id复制
                                        $("#id_addForm").val(guid());
                                    }
                                }
                            },
                            {
                                btnCls: 'button button-small',
                                text: '修改',
                                listeners: {
                                    'click': function (ev) {
                                        // 获取选择的数据
                                        var gridSelection = grid.getSelected();
                                        if (gridSelection) {
                                            // 清除表单中的值
                                            form.clearFields();
                                            // 清除表单中的错误
                                            form.clearErrors();
                                            addDialog.set("title", "修改人民陪审员 - ");
                                            addDialog.show();
                                            $("#addForm input").each(function () {
                                                var name_addForm = $(this).attr("name");
                                                name_addForm = name_addForm.replace("umsRmpsyJbxx.", "");
                                                if ("cyajxz".indexOf(name_addForm) != -1) {
                                                    if (gridSelection["cyajxz"] != null) {
                                                        $("#cyajxz_addForm_1").prop("checked", true);
                                                    }
                                                    if (gridSelection["cyajxza"] != null) {
                                                        $("#cyajxz_addForm_2").prop("checked", true);
                                                    }
                                                    if (gridSelection["cyajxzb"] != null) {
                                                        $("#cyajxz_addForm_3").prop("checked", true);
                                                    }
                                                } else if (name_addForm == "workarea") {
                                                    $(this).val(gridSelection["workareaname"]);
                                                } else {
                                                    $(this).val(gridSelection[name_addForm]);
                                                }
                                            });

                                            $("#addForm select").each(function () {
                                                var name_addForm = $(this).attr("name");
                                                name_addForm = name_addForm.replace("umsRmpsyJbxx.", "");
                                                $(this).select2("val", gridSelection[name_addForm]);
                                            });
                                            // 获取头像
                                            getPhoto();
                                        } else {
                                            BUI.Message.Alert("请选择一条记录", null, "info");
                                        }
                                    }
                                }
                            },
                            {
                                btnCls: 'button button-small',
                                text: '停用',
                                handler: function () {
                                    var userinfo = grid.getSelected();
                                    if (userinfo) {
                                        BUI.Message.Confirm(
                                            '确定要停用“' + userinfo.name + '”吗？',
                                            function () {
                                                $.ajax({
                                                    url: "<%=basePath%>rmpsyAction/tqy",
                                                    data: {id: userinfo["id"], isvalid: 0, scbj: 1},
                                                    dataType: "json",
                                                    async: true,
                                                    success: function (data) {
                                                        if (data == 1) {
                                                            BUI.Message.Alert("成功停用" + userinfo.name, null, "success");
                                                        }
                                                    }
                                                });
                                                store.load();
                                            },
                                            'question');
                                    } else {
                                        BUI.Message.Alert("请选择一条记录", null, "info");
                                    }
                                }
                            },
                            {
                                btnCls: 'button button-small',
                                text: '导出Excel',
                                btnCls: 'button button-small ',
                                handler: function () {
                                    BUI.Message.Confirm('excel会按照当前查询出来的信息进行生成,如果数据量较大请稍等,勿重复点击生成。是否按照此信息生成excel?', function () {
                                            var query_condition = store.get("lastParams");
                                            //要拼接参数
                                            var form = $("form[id='excel_form']");
                                            form.html("");
                                            form.append("<input type='text' name='excelDataType'  value='1' >")
                                            for (var i in query_condition) {
                                                form.append("<input type='text' name='" + i + "'  value='" + query_condition[i] + "'>")
                                            }
                                            form.submit();
                                        },
                                        'question');
                                }
                            }
                        ]
                    },
                    store: store
                });

                grid.render();

                grid.on('cellclick', function (ev) {
                    var sender = $(ev.domTarget); //点击的Dom
                    var record = ev.record;
                    var id = record.id;
                    console.log(id);
                    var url = '<%=basePath%>rmpsyAction/tqy';
                    if (sender.hasClass('btn-disabled')) {
                        $.ajax({
                            type: "post",
                            url: url,
                            data: {id: id, isvalid: 0, scbj: 1},
                            dataType: "json",
                            success: function (re) {
                                if (re == 1) {
                                    BUI.Message.Alert('停用成功',function () {
                                        store.load();
                                    }, 'success');
                                }
                            }
                        });
                    } else if (sender.hasClass('btn-enabled')) {
                        $.ajax({
                            type: "post",
                            url: url,
                            data: {id: id, isvalid: 1, scbj: 0},
                            dataType: "json",
                            success: function (re) {
                                if (re == 1) {
                                    BUI.Message.Alert('启用成功',function () {
                                        store.load();
                                    }, 'success');
                                }
                            }
                        });
                    }

                });


                // 加载法院树
                loadCourtTree();

                // 生成法院树结构
                function loadCourtTree() {
                    var url = "<%=basePath%>/code/tree/children3";
                    $.ajax({
                        type: "post",
                        url: url,
                        data: {type: 1},
                        dataType: "json",
                        success: function (re) {
                            if (!re) {
                                return;
                            }
                            // 在返回的数据中，根据权限加入 全部
                            if (authType == "ADMIN") {
                                var baseNode = {};
                                baseNode['id'] = "0";
                                baseNode['text'] = "全部";
                                baseNode['open'] = true;
                                re.push(baseNode);
                            }
                            handleTreeData(re, "0");
                            var callbackHandler = {
                                dataFilter: function (treeId, parentNode, responseData) {
                                    // 处理数据
                                    if (responseData) {
                                        handleTreeData(responseData);
                                        return responseData;
                                    } else {
                                        return [];
                                    }
                                },
                                onClick: function (event, treeId, treeNode) {
                                    // 处理法院树的点击事件
                                    handleClickCourtTree(treeNode);
                                }
                            };
                            // 这个是配置
                            var setting = {
                                // 回掉
                                callback: {onClick: callbackHandler.onClick},
                                async: {
                                    enable: true,
                                    dataFilter: callbackHandler.dataFilter,
                                    url: url,
                                    autoParam: ["id=id"],
                                    otherParam: {'type': 1,}
                                },
                                data: {simpleData: {enable: true}}
                            };
                            // 初始化法院树
                            $.fn.zTree.init($("#ztree-div"), setting, re);
                            // 触发第一个节点点击事件
                            var zTreeObj = $.fn.zTree.getZTreeObj('ztree-div');
                            var nodeById = zTreeObj.getNodeByTId("ztree-div_1");
                            zTreeObj.selectNode(nodeById);
                            // 触发函数
                            zTreeObj.setting.callback.onClick(null, 'ztree-div', nodeById);
                        }
                    });
                }

                // 处理返回数据
                function handleTreeData(datas, parent) {
                    $.each(datas, function (index, value) {
                        value['name'] = value['text'];
                        if (value['name'] == "全部" || value['name'] == "重庆市高级人民法院" || value['name'] == "重庆市第一中级人民法院" || value['name'] == "重庆市第二中级人民法院" || value['name'] == "重庆市第三中级人民法院" || value['name'] == "重庆市第四中级人民法院" || value['name'] == "重庆市第五中级人民法院") {
                            value['isParent'] = true;
                        } else {
                            value['isParent'] = false;
                        }
                        if (!(typeof parent == "undefined" || parent == null || parent == "")) {
                            value['pId'] = parent;
                        }
                    });
                }

                // 处理点击法院事件 同时生成右侧的数据
                function handleClickCourtTree(treeNode) {
                    if (treeNode['courtNo'] == undefined) {
                        condition['courtno'] = '';
                    } else {
                        condition['courtno'] = treeNode['courtNo'];
                    }
                    store.load(condition);
                }

                // 新增或编辑人民陪审员
                var addDialog = new Overlay.Dialog({
                    title: '',
                    width: '70%',
                    height: 450,
                    buttons: [
                        {
                            text: '保存', elCls: 'button button-primary', handler: function () {
                                form.valid();
                                var flag = form.isValid();
                                if (flag) {
                                    // 如果通过验证
                                    // 用于提交到后台的参数
                                    var condition = {};
                                    $("#addForm input, #addForm select").each(function () {
                                        var id = $(this).attr("id");
                                        var name = $(this).attr("name");
                                        var value = $(this).attr("value");
                                        if (name == "umsRmpsyJbxx.cyajxz") {
                                            // 跳过
                                        } else if (id == "workArea_addForm") {
                                            condition[name] = $(this).attr("workareaid");
                                            condition["umsRmpsyJbxx.workareaname"] = $(this).val();
                                        } else {
                                            condition[name] = value;
                                        }
                                    });
                                    // dialog的title，这里是根据dialog的标题区分是新增还是修改人民陪审员的数据
                                    var title = addDialog.get("title");
                                    if (title.indexOf("新增") != -1) {
                                        condition["saveType"] = "sav1";
                                    } else if (title.indexOf("修改") != -1) {
                                        condition["saveType"] = "sav2";
                                    }
                                    // 先判断身份证是否已经存在
                                    var count = checkIdCard($("#idcard_addForm").val(), condition["saveType"], condition["id"]);
                                    if (count == 1) {
                                        return;
                                    }
                                    // 如果陪审状态是退出了,就停用了
                                    if (condition["umsRmpsyJbxx.psyzt"] == 2) {
                                        condition["umsRmpsyJbxx.isvalid"] = 0;
                                    }
                                    // courtCode的值
                                    for (var i = 0; i < localCodeArrays[1].length; i++) {
                                        if (localCodeArrays[1][i].id == condition["umsRmpsyJbxx.courtno"]) {
                                            condition["umsRmpsyJbxx.courtcode"] = localCodeArrays[1][i].courtCode;
                                            break;
                                        }
                                    }
                                    // 获取CheckBox选中的值
                                    $.each($('input:checkbox:checked'), function (key, value) {
                                        var id = $(this).attr("id");
                                        switch (id) {
                                            case "cyajxz_addForm_1":
                                                condition["umsRmpsyJbxx.cyajxz"] = $(this).val();
                                                break;
                                            case "cyajxz_addForm_2":
                                                condition["umsRmpsyJbxx.cyajxza"] = $(this).val();
                                                break;
                                            case "cyajxz_addForm_3":
                                                condition["umsRmpsyJbxx.cyajxzb"] = $(this).val();
                                                break;
                                            default:
                                                break;
                                        }
                                    });
                                    // 发送到后台保存数据
                                    $.ajax({
                                        url: "<%=basePath%>rmpsyAction/saveRmpsyInfo", // 该方法包含新增或者更新
                                        data: condition,
                                        type: "POST",
                                        dataType: "json",
                                        async: false,
                                        success: function (data) {
                                            if (null != data && data > 0) {
                                                addDialog.close();
                                                BUI.Message.Alert('保存成功', 'success');
                                                store.load();
                                                // 保存成功基本信息之后再保存图片
                                                if (photoEdit) {
                                                    $.post("<%=basePath%>photo/save", {userId: $("#id_addForm").val(), userType: "rmpsyr_3"}, function () { // rmpsyr_3：表示3.0平台的人民陪审员
                                                        BUI.Message.Alert("头像上传成功", null, "success");
                                                    });
                                                } else if (data.result === -1) {
                                                    BUI.Message.Alert("非法操作，当前用户无此权限！", null, "warning");
                                                }
                                            } else {
                                                BUI.Message.Alert('保存失败', 'error');
                                            }
                                        }
                                    });
                                }
                            }
                        },
                        {
                            text: '关闭', elCls: 'button', handler: function () {
                                this.close();
                            }
                        }
                    ],
                    contentId: 'addDialog'
                });

                var form = new Form.HForm({srcNode: '#addForm'}).render();

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
                        // 文的类型
                        ext: ['.png,.jpg,.jpeg,.gif,.bmp', '文件类型只能为{0}'],
                        // 上传的最大个数
                        max: [1, '文件的最大个数不能超过{0}个'],
                        // 文件大小的最小值,这个单位是kb
                        // minSize: [10, '文件的大小不能小于{0}KB'],
                        // 文件大小的最大值,单位也是kb
                        maxSize: [2048, '文件大小不能大于2M']
                    },
                    autoRender: true
                });

                var queue = uploader.get("queue");

                queue.on("itemremoved", function (e) {
                    photoEdit = true;
                });
                uploader.on("change", function (e) {
                    photoEdit = true;
                });
                //--------------图片上传功能End-----------------

                // form表单额外验证规则-身份证验证
                Form.Rules.add({
                    name: "idcheck",
                    msg: "身份证格式有误！",
                    validator: function (value, baseValue, formatMsg) {
                        if (!checkId(value)) {
                            return formatMsg;
                        }
                        var birthday = getBirthdatByIdNo(value);
                        $("#birthday_addForm").val(birthday);
                    }
                });

                // 陪审员状态为退出时，退出方式必填
                Form.Rules.add({
                    name: "tcfs",
                    msg: "陪审员状态为退出时，退出方式必填！",
                    validator: function (value, baseValue, formatMsg) {
                        if ($("#psyzt_addForm").val() == "02" && $("#tcfs_addForm").val() == "") {
                            return formatMsg;
                        }
                    }
                });

                // 验证手机号
                Form.Rules.add({
                    name: 'phoneNumCheck',
                    msg: '请输入有效的手机号',
                    validator: function (value, len, formatedMsg) {
                        if (value != null) {
                            value = $.trim(value.toString());
                            if (len != value.length) {
                                return formatedMsg;
                            }
                        }
                    }
                });

                // 多选框验证
                Form.Rules.add({
                    name: 'checkRange',
                    msg: '至少选中{0}项！',
                    validator: function (record, baseValue, formatedMsg, group) {
                        var name = getFieldName(group),
                            value,
                            range = baseValue;

                        if (name && range) {
                            value = record[name];
                            if (!testCheckRange(value, range)) {
                                return formatedMsg;
                            }
                        }
                        return null;
                    }
                });

                function getFieldName(self) {
                    var firstField = self.getFieldAt(0);
                    if (firstField) {
                        return firstField.get('name');
                    }
                    return '';
                }

                function testCheckRange(value, range) {
                    if (!BUI.isArray(range)) {
                        range = [range];
                    }
                    //不存在值
                    if (!value || !range.length) {
                        return false;
                    }
                    var len = !value ? 0 : !BUI.isArray(value) ? 1 : value.length;
                    //如果只有一个限定值
                    if (range.length == 1) {
                        var number = range [0];
                        if (!number) {//range = [0],则不必选
                            return true;
                        }
                        if (number > len) {
                            return false;
                        }
                    } else {
                        var min = range [0],
                            max = range[1];
                        if (min > len || max < len) {
                            return false;
                        }
                    }
                    return true;
                }

                // 加载日期框
                var datepicker = new Calendar.DatePicker({
                    trigger: '.calendar2',
                    autoRender: true
                });

                // 验证身份证号并获取出生日期
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

                //生成uuid
                function guid() {
                    return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4());
                }

                function S4() {
                    return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
                }

                // 查询
                $("#searchButton").click(function () {
                    var condition = {};
                    $("#search_form input, #search_form select").each(function () {
                        var id = $(this).attr("id");
                        var name = $(this).attr("name");
                        var value = $(this).val();
                        if (id == "sfty" && value == "") {
                            value = "";
                        }
                        condition[name] = value;
                    });
                    // 查询的时候不限制陪审员状态
                    store.load(condition);
                });

                // 重置
                $("#resetButton").click(function () {
                    $("#search_form input").each(function () {
                        var id = $(this).attr("id");
                        var name = $(this).attr("name");
                        var value = $(this).attr("value");
                        $(this).val("");
                    });
                    $("#search_form select").each(function () {
                        var id = $(this).attr("id");
                        if (id == "sfty") {
                            $("#sfty").val(-1);
                        } else {
                            var name = $(this).attr("name");
                            var value = $(this).attr("value");
                            $(this).select2("val", "");
                        }
                    });
                });

                // 工作区域模态框
                var workAreaDialog = new Overlay.Dialog({
                    title: '选择工作区域',
                    width: '25%',
                    height: 450,
                    buttons: [],
                    contentId: 'workAreaDialog'
                });

                var columns_workArea = [
                    {title: '从属机构', dataIndex: 'csjg', width: '60%'},
                    {title: '名称', dataIndex: 'name', width: '40%'}
                ];

                var store_workArea = new Data.Store({
                    pageSize: 10,
                    proxy: {
                        url: '<%=basePath%>workAreaAction/getWorkAreaTree',
                        method: 'post',
                        dataType: 'json'
                    },
                    pageSize: 10
                });

                var grid_workArea = new Grid.Grid({
                    render: '#workAreaGrid',
                    width: '100%',//这个属性一定要设置
                    height: 250,
                    columns: columns_workArea,
                    emptyDataTpl: '<div class="centered"><h2>当前无数据显示</h2></div>',
                    plugins: [Grid.Plugins.RowNumber, Grid.Plugins.RadioSelection],
                    loadMask: true,
                    bbar: {
                        // pagingBar:表明包含分页栏
                        pagingBar: true
                    },
                    store: store_workArea
                });


                // 监听双击事件
                $("#workArea_addForm").dblclick(function () {
                    workAreaDialog.show();
                    grid_workArea.render();
                    jzjd();
                    // 先做其他
                });

                // 点击代码选择隐藏关键词检索
                $("#dmxz").click(function () {
                    $("#gjzjsDiv").hide();
                    $("#dmxzDiv").show();
                    $("#dmxz").addClass("button-primary");
                    $("#gjzjs").removeClass("button-primary");
                });

                // 点击关键词检索隐藏代码选择
                $("#gjzjs").click(function () {
                    $("#dmxzDiv").hide();
                    $("#gjzjsDiv").show();
                    $("#gjzjs").addClass("button-primary");
                    $("#dmxz").removeClass("button-primary");
                });

                var url = '<%=basePath%>workAreaAction/getWorkAreaTree';

                // 加载节点
                function jzjd() {
                    $.ajax({
                        url: url,
                        type: "post",
                        data: {ee: 0, type: "ztree"},
                        dataType: "json",
                        async: true,
                        success: function (data) {
                            if (null != data && data.length > 0) {
                                // 这个是配置
                                var setting = {
                                    // 回掉
                                    async: {enable: true, url: url, autoParam: ["aa", "bb", "cc", "ee"], otherParam: {type: "ztree"}}, // ee 在后台处理+1，+1代表查找下一等级
                                    data: {simpleData: {enable: true}},
                                    callback: {
                                        onDblClick: zTreeOnDblClick
                                    }
                                };
                                // 初始化法院树
                                $.fn.zTree.init($("#dmxzTree"), setting, data);
                                // 触发第一个节点点击事件
                                var zTreeObj = $.fn.zTree.getZTreeObj('dmxzTree');
                                var nodeById = zTreeObj.getNodeByTId("dmxzTree_1");
                                zTreeObj.selectNode(nodeById);
                            } else {
                                console.log("error");
                            }
                        }
                    });
                }

                // zTree 双击事件
                function zTreeOnDblClick(event, treeId, treeNode) {
                    var ee = treeNode.ee;
                    var isParent = treeNode.isParent;
                    switch (ee) {
                        case "1":
                            return false;
                            break;
                        case "2":
                            return false;
                            break;
                        case "3":
                            if (isParent) {
                                return false;
                            } else {
                                form.setFieldValue("umsRmpsyJbxx.workarea", treeNode.name);
                                $("#workArea_addForm").attr("workareaId", treeNode.cc);
                                workAreaDialog.close();
                            }
                            break;
                        case "4":
                            form.setFieldValue("umsRmpsyJbxx.workarea", treeNode.name);
                            $("#workArea_addForm").attr("workareaId", treeNode.dd);
                            workAreaDialog.close();
                            break;
                        default:
                            console.log("error");
                            break;
                    }
                }

                // 关键词检索
                $("#searchButtonForGjc").click(function () {
                    var workAreaGjc = $("#workAreaGjc").val();
                    var condition = {};
                    condition["name"] = workAreaGjc;
                    condition["type"] = "workAreaSearch";
                    store_workArea.load(condition);
                });

                // 监听工作区域grid的双击事件
                grid_workArea.on('dblclick', function (ev) {
                    var selection = grid_workArea.getSelection();
                    form.setFieldValue("umsRmpsyJbxx.workarea", selection[0].name);
                    if (selection[0].dd == null) {
                        $("#workArea_addForm").attr("workareaId", selection[0].cc);
                    } else {
                        $("#workArea_addForm").attr("workareaId", selection[0].dd);
                    }
                    workAreaDialog.close();
                });

                // 获取头像
                function getPhoto() {
                    $.getJSON("<%=basePath%>photo/getPhotoById", {userId: $("#id_addForm").val(), _: new Date()}, function (data) {
                        if (data !== null) {
                            queue.setItems([{success: true, ext: '.jpg', name: '原始照片.jpg', url: data}]);
                        } else {
                            queue.setItems([]);
                        }
                    });
                }

                // 检查身份证是否已存在于人民陪审员表中，如果有则提示。
                function checkIdCard(idcard, saveType, id) {
                    var count = 0;
                    $.ajax({
                        url: '<%=basePath%>rmpsyAction/checkIdCard',
                        type: "post",
                        data: {idcard: idcard, saveType: saveType, id: id},
                        dataType: "json",
                        async: false,
                        success: function (data) {
                            if (null != data && data.length > 0) {
                                BUI.Message.Alert("该身份证号已存在", null, "info");
                                count = 1;
                            } else {

                            }
                        }
                    });
                    return count;
                }

            });
        }
    }

    // 在本地数组中，根据typeid和数字获取汉字的含义
    function getNameFromLocalArray(typeId, id) {
        if (id == "") {
            return "";
        }
        var loacCodeTypeId = localCodeArrays[typeId];
        for (var i = 0; i < loacCodeTypeId.length; i++) {
            if (loacCodeTypeId[i].id == id) {
                var codeName = loacCodeTypeId[i].codeName;
                return codeName;
            }
        }
    }
</script>
</html>
