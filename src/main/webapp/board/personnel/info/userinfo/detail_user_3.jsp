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
%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
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

    </style>
</head>
<body>
<ul class="nav-tabs" style="position:fixed;z-index:2000;background-color: white;width:100%">
    <li class="active"><a href="#basic-info" onclick="changeMarkers(this)" class="initClass">基本信息</a></li>
    <li><a href="#juror-degree" onclick="changeMarkers(this)">学历学位</a></li>
    <li><a href="#position-info" onclick="changeMarkers(this)">单位及职务</a></li>
    <li><a href="#juror-info" onclick="changeMarkers(this)">陪审员信息</a></li>
    <li><a href="#education-info" onclick="changeMarkers(this)">其他</a></li>
</ul>
<div style="height: 40px;"></div>
<form id="userinfo_form" class="form-horizontal" action="<%=basePath%>userinfo/save" method="post">
    <div class="row">
        <img id="photo" height="240" style="margin: 2em">
    </div>

    <%--基本信息--%>
    <div id="basic-info">
        <hr>
        <h3>基本信息</h3>

        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>姓名：</label>

                <div class="controls">
                    <input id="fullname" name="userInfo.fullname" type="text" value="" class="control-text" data-rules="{required:true}">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">曾用名：</label>

                <div class="controls">
                    <input id="formerName" name="userInfo.formerName" type="text" value="" class="control-text">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>性别：</label>

                <div class="controls">
                    <select id="gender" typeId="3" class="code" data-rules="{required:true}" name="userInfo.gender"></select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>民族：</label>

                <div class="controls">
                    <select id="nation" name="userInfo.nation" typeId="5" class="code" data-rules="{required:true}"></select>
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label">出生日期：</label>

                <div class="controls">
                    <input id="birthday" name="userInfo.birthday" type="text" class="calendar"
                           data-rules="{required:true}" onchange="calcAge()">
                </div>
            </div>

            <div class="control-group span8">
                <label class="control-label"><s>*</s>籍贯：</label>

                <div class="controls">
                    <input id="hometown" name="userInfo.hometown" type="text" value="" class="control-text" data-rules="{required:true}">
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

            <div class="control-group span8">
                <label class="control-label"><s>*</s>婚姻状况：</label>

                <div class="controls">
                    <select id="maritalStatus" typeId="6" class="code" data-rules="{required:true}" name="userInfo.maritalStatus"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>健康状况：</label>

                <div class="controls">
                    <select id="physicalCondition" typeId="10" class="code" data-rules="{required:true}" name="userInfo.physicalCondition"></select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label">年龄：</label>

                <div class="controls">
                    <label class="control-label" style="text-align: left" id="age"></label>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>身份证号：</label>

                <div class="controls">
                    <input id="idcard" name="userInfo.idcard" type="text" class=" span-width spancontrol-text"
                           data-rules="{required:true,idcheck:null}">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>法院：</label>

                <div class="controls">
                    <select id="courtNo" typeId="1" class="code" name="userInfo.courtNo"
                            onchange="loadDeptList(department, this.value, '<option value=\'\'>请选择</option>')" data-rules="{required:true}"></select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>手机号码：</label>

                <div class="controls">
                    <input id="phoneNumber" name="userInfo.phoneNumber" type="text" class="control-text"
                           data-rules="{required:true,phoneNumber:11}">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">座机号码：</label>

                <div class="controls">
                    <input id="machineNumber" name="userInfo.machineNumber" type="text" class="control-text"
                           data-messages="{regexp:'不是有效的电话号码'}"
                           data-rules="{required:true,regexp:/^((\d{3,4}|\d{3,4})-)?\d{7,8}$/}">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>常住地：</label>

                <div class="controls">
                    <input id="localAddress" name="userInfo.localAddress" type="text" class="control-text"
                           data-rules="{required:true}">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span24">
                <label class="control-label"><s>*</s>通讯地址：</label>
                <div class="controls">
                    <input id="postalAddress" name="userInfo.postalAddress" type="text" class="span20 span-width spancontrol-text"
                           data-rules="{required:true}">
                </div>
            </div>
        </div>
    </div>

    <div id="juror-degree">

        <hr/>
        <h3>学历学位</h3>

        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>学历学位：</label>
                <div class="controls">
                    <select id="cPsXlxw" name="userInfo.cPsXlxw" typeId="10001" class="code"
                            data-rules="{required:true}"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>毕业院校：</label>
                <div class="controls">
                    <input id="sPsByyxjzy" name="userInfo.sPsByyxjzy" type="text" class="span-width spancontrol-text"
                           placeholder="填写毕业院校" data-rules="{required:true}">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>专业：</label>
                <div class="controls">
                    <input id="sPsZy" name="userInfo.sPsZy" type="text" class="span-width spancontrol-text"
                           placeholder="填写专业" data-rules="{required:true}">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>学历：</label>

                <div class="controls">
                    <select id="educationBackground" data-rules="{required:true}" typeId="11" class="code"
                            name="userInfo.educationBackground"></select>
                </div>
            </div>
        </div>
    </div>
    <%--单位及职务--%>
    <div id="position-info">
        <hr/>
        <h3>单位及职务</h3>

        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>单位：</label>

                <div class="controls">
                    <input id="company" name="jurorInfo.company" type="text" class="control-text" placeholder="单位"
                           data-rules="{required:true}">
                </div>
            </div>
            <div class="control-group span12">
                <label class="control-label"><s>*</s>单位地址：</label>

                <div class="controls">
                    <input id="sPsDwdz" name="userInfo.sPsDwdz" type="text" class="span8 span-width spancontrol-text"
                           placeholder="填写单位地址" data-rules="{required:true}">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>职业：</label>
                <div class="controls">
                    <input id="jurorWork" name="jurorInfo.jurorWork" type="text" class="control-text" data-rules="{required:true}"
                           placeholder="职业">
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label"><s>*</s>单位职务：</label>

                <div class="controls">
                    <input id="sPsZw" name="userInfo.sPsZw" type="text" class="control-text"
                           placeholder="单位职务" data-rules="{required:true}">
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label"><s>*</s>单位职级：</label>

                <div class="controls">
                    <input id="sPsZj" name="userInfo.sPsZj" type="text" class="control-text"
                           placeholder="单位职级" data-rules="{required:true}">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label">专业技术职务：</label>
                <div class="controls">
                    <input id="sPsZyjszw" name="userInfo.sPsZyjszw" type="text" class="control-text"
                           placeholder="专业技术职务">
                </div>
            </div>
        </div>
    </div>
    <%--陪审员信息--%>
    <div id="juror-info">
        <hr/>
        <h3>陪审员信息</h3>

        <div class="row">
            <div class="control-group span8">
                <label class="control-label">类型：</label>
                <div class="controls">
                    <select id="cPsLx" name="userInfo.cPsLx" typeId="10002" class="code" data-rules="{required:true}"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">专业类别：</label>
                <div class="controls">
                    <select id="cPsZylb" name="userInfo.cPsZylb" typeId="10003" class="code" data-rules="{required:true}"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">任命单位：</label>

                <div class="controls">
                    <input id="sPsRmdw" name="userInfo.sPsRmdw" type="text" class="control-text"
                           placeholder="任命单位">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group12 span8">
                <label class="control-label"><S>*</S>任命日期：</label>
                <div class="controls">
                    <input id="beginTime" name="userInfo.beginTime" type="text"
                           class="calendar calendar-time" placeholder="任命日期" data-rules="{required:true,datecheck1:null}">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">陪审员编号：</label>

                <div class="controls">
                    <input id="sPsPsybh" name="userInfo.sPsPsybh" type="text" class="control-text"
                           placeholder="陪审员编号">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">uk编号：</label>

                <div class="controls">
                    <input id="ukbm" name="userInfo.ukbm" type="text" class="control-text"
                           placeholder="uk编号">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group12 span8">
                <label class="control-label">免职日期：</label>

                <div class="controls">
                    <input id="endTime" name="userInfo.endTime" type="text"
                           class="calendar calendar-time" placeholder="免职日期">
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label">免职原因：</label>
                <div class="controls">
                    <select id="cPsMzyy" name="userInfo.cPsMzyy" typeId="10004" class="code"></select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group12 span8">
                <label class="control-label"><s>*</s>地区分布：</label>

                <div class="controls">
                    <select id="regional" name="jurorInfo.regional" typeId="109" class="code" data-rules="{required:true}"></select>
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label"><s>*</s>工作区域：</label>
                <div class="controls">
                    <select class="code" typeId="112" style="display: none;"></select>
                    <input id="gzqy" name="jurorInfo.gzqy" type="text" class="control-text" data-rules="{required:true}" autocomplete="off"
                           placeholder="工作区域">
                    <input id="workArea" name="jurorInfo.workArea" type="text" style="display: none">
                </div>
            </div>

            <div class="control-group12 span8">
                <label class="control-label"><s>*</s>是否新增：</label>
                <div class="controls">
                    <select id="isNew" name="jurorInfo.isNew" typeId="108" class="code" data-rules="{required:true}"></select>
                </div>
            </div>

        </div>

        <div class="row">
            <div class="control-group12 span8">
                <label class="control-label"><s>*</s>届数：</label>

                <div class="controls">
                    <input id="numberOfTimes" name="jurorInfo.numberOfTimes" type="text" class="control-text" data-rules="{required:true,number:true}"
                           placeholder="届数">
                </div>

            </div>

            <div class="control-group12 span8">
                <label class="control-label">年度参审次数：</label>

                <div class="controls">
                    <input id="yearAddCount" name="jurorInfo.yearAddCount" type="text" class="control-text" data-rules="{number:true}"
                           placeholder="年度参审次数">
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label"><s>*</s>参与案件类型：</label>
                <div class="controls">
                    <select class="code" typeId="110" style="display: none;"></select>
                    <input id="cyajlx" name="jurorInfo.cyajlx" type="text" class="control-text" data-rules="{required:true}" autocomplete="off"
                           placeholder="参与案件类型">
                    <input id="typeOfCase" name="jurorInfo.typeOfCase" type="text" style="display: none">
                </div>
            </div>
        </div>

        <div class="row">
            <div class="control-group12 span8">
                <label class="control-label">年度陪审费用：</label>

                <div class="controls">
                    <input id="yearCost" name="jurorInfo.yearCost" type="text" class="control-text" data-rules="{number:true}"
                           placeholder="年度陪审费用">
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label"><s>*</s>陪审状态：</label>
                <div class="controls">
                    <select id="memberState" name="jurorInfo.memberState" typeId="111" class="code" data-rules="{required:true}"></select>
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label">退出方式：</label>

                <div class="controls">
                    <select id="exitMode" name="jurorInfo.exitMode"></select>
                </div>
            </div>
        </div>
    </div>

    <%--其他--%>
    <div id="education-info">
        <hr/>
        <h3>其他</h3>
        <div class="row">
            <div class="control-group span24">
                <label class="control-label">专业特长：</label>

                <div class="controls">
                    <input id="zytc" name="userInfo.zytc" type="text" class="span20 span-width spancontrol-text"
                           placeholder="专业特长">
                </div>
            </div>
        </div>
    </div>


</form>

</body>
<script type="text/javascript">
    $("#userinfo_form :input").prop("disabled", true);
    $("select.code").each(function () {

        var typeId = $(this).attr("typeId");
        if (typeId == 110) {

            $.getJSON("/ums/code/codeListByType",
                {
                    typeId: typeId
                },
                function (data) {

                    initMultiSelect(data);

                })
            return;

        }
        //工作区域
        if (typeId == 112) {
            loadArea();
            return;

        }
        loadCodeList(this, '<option value=""></option>');
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

    $.getJSON("<%=basePath%>view/userinfoById", {id: "<s:property value="id"></s:property>"}, function (data) {
        $("#userinfo_form :input").each(function () {
            if (data[$(this).attr("id")] != null) {
                if ($(this).attr("id") === "department") {
                    return true;
                }

                if ($(this).attr("id") === "courtNo") {
                    loadCourts("#courtNo", '<option></option>', function () {
                        $("#courtNo").val(data["courtNo"]);
                        loadDeptList("#department", data["courtNo"], '<option></option>', function () {
                            $("#department").val(data["department"]);
                        });
                    });
                } else {
                    $(this).val(data[$(this).attr("id")]);
                }
            }
        });

        //读取审判员信息
        $.getJSON("<%=basePath%>juror/oneExtend", {"jurorInfo.userId": "<s:property value="id"></s:property>", _: new Date().getTime()}, function (res) {
            if (res && res.info) {
                var data = res.info;
                if (data) {
                    $("#userinfo_form [name^='jurorInfo.']").each(function () {
                        $(this).val(data[$(this).attr("id")]);
                        //参与案件类型是多个
                        if ("typeOfCase" == $(this).attr("id")) {
                            if (picker) {
                                picker.setSelectedValue(data[$(this).attr("id")]);
                            }
                            // $("#cyajlx").val(data[$(this).attr("id")]);
                        }

                        //工作区域
                        if ("workArea" == $(this).attr("id")) {
                            if (res.gzqy) {
                                $("#gzqy").val(res.gzqy);
                            }
                        }
                    });
                }

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
        });
    });


    function changeMarkers(obj) {
        $(obj).parent().parent().find(".active").removeClass("active");
        $(obj).parent().addClass("active");

        var hr = $(obj).attr("href");
        var anh = $(hr).offset().top - 40;
        $("html,body").stop().animate({scrollTop: anh}, 800);
    }

    //初始化选择树
    var picker;

    function initMultiSelect(data) {

        var getJsonTree = function (data, parentid) {
            var itemArr = [];
            for (var i = 0; i < data.length; i++) {
                var node = data[i];
                if (node.parentId == parentid) {
                    var newNode = {};
                    newNode.id = node.id;
                    newNode.text = node.codeName;
                    newNode.children = getJsonTree(data, node.courtCode);
                    itemArr.push(newNode);
                }
            }
            return itemArr;
        };
        var dat = getJsonTree(data, null);
        BUI.use(['bui/extensions/treepicker', 'bui/tree'], function (TreePicker, Tree) {
            var tree = new Tree.TreeList({
                nodes: dat,
                checkType: 'all',
                cascadeCheckd: false, //不级联勾选
                showLine: true //显示连接线
            });

            picker = new TreePicker({
                trigger: '#cyajlx',
                valueField: '#typeOfCase', //如果需要列表返回的value，放在隐藏域，那么指定隐藏域
                selectStatus: 'checked',//设置勾选作为状态改变的事件
                width: 230,  //指定宽度
                children: [tree] //配置picker内的列表
            });

            picker.render();

        });
    }

    var areaPicker;

    function loadArea() {

        BUI.use(['bui/extensions/treepicker', 'bui/tree', 'bui/data'], function (TreePicker, Tree, Data) {


            var treestore = new Data.TreeStore({
                root: {
                    text: "全部",
                },
                url: '<%=basePath%>code/getDetailArea'
            });

            var tree = new Tree.TreeList({
                store: treestore,
                cascadeCheckd: false, //不级联勾选
                multipleCheck: false, //是否多选，非多选时使用radio
                showLine: true,//显示连接线
                checkType: 'onlyLeaf', //仅叶子可以勾选
                listeners: {
                    itemselected: function (ev) {

                        var item = ev.item;
                        var nodes = tree.getCheckedNodes();
                        tree.clearSelected();
                        tree.clearAllChecked();
                        tree.setChecked(item);

                    }
                }
            });

            areaPicker = new TreePicker({
                trigger: '#gzqy',
                valueField: '#workArea', //如果需要列表返回的value，放在隐藏域，那么指定隐藏域
                selectStatus: 'checked',//设置勾选作为状态改变的事件
                width: 230,  //指定宽度
                children: [tree] //配置picker内的列表
            });

            areaPicker.render();
            treestore.load();//加载根节点，也可以让用户点击加载

        });


    }
</script>
</html>
