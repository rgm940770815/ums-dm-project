<%--
  Created by IntelliJ IDEA.
  User: Cypress
  Date: 2018/5/4
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>质量计算</title>
    <jsp:include page="/basic_import.jsp"></jsp:include>
    <jsp:include page="user2_new_add.jsp"></jsp:include>
    <script type="text/javascript" src="<%=basePath%>js/common/codelist.js"></script>
    <script src="<%=basePath%>js/common/datetools.js" type="text/javascript"></script>
    <script src="../../../js/vue.min.js" type="text/javascript"></script>
    <style type="text/css">
        .header {
            width: 100%;
            height: 40px;
            padding: 8px 15px;
            text-align: left;
            border-bottom: 1px solid #bcbcbc;
            margin-bottom: 20px;
            box-sizing: border-box;
        }

        .content {
            width: 100%;
            box-sizing: border-box;
            height: calc(100vh - 60px);
            display: flex;
            display: -webkit-flex;
        }

        #deptree {
            width: 230px;
            height: 100%;
        }

        #t3 {
            height: calc(100% - 25px);
        }

        #count_result {
            flex: 1;
            height: 100%;
            padding: 0 20px;
            display: flex;
            display: -webkit-flex;
        }

        .count_result_left {
            max-height: calc(100% - 25px);
            overflow-y: auto;
            /*width: 500px;*/
            flex-basis: 40%;
            flex-shrink: 0;

        }

        .count_result_right {
            flex: 1;
            padding: 10px;
            margin-left: 20px;
            margin-bottom: 20px;
            border: 1px solid #bcbcbc;
        }

        .result_table {
            width: 100%;
            font-size: 14px;
        }

        .result_table td, .result_table th {
            border: 1px solid #bcbcbc;
            box-sizing: content-box;
        }

        .result_table tr, .result_table td {
            height: 32px;
            line-height: 32px;
        }

        .result_table thead {
            background-color: #FBFCFF;
        }

        .result_table tbody td {
            padding: 0 14px;
        }

        .key_explain {
            font-size: 16px;
        }

        #grid .bui-grid-button-bar .button-small [class^="icon-"] {
            margin: 0 3px 0 0;
        }

        .bui-stdmod-body {
            overflow: auto;
        }

        #userinfo_form {
            position: relative;
        }

        #userinfo_form input {
            box-sizing: content-box;
        }

        #photo .bui-uploader-htmlButton {
            display: none;
        }

        .nav-tabs li s {
            color: red;
            padding-right: 5px;
            text-decoration: none;
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

        .show_info_edit {
            display: none;
        }
    </style>
</head>
<body>

<div class="header">
    质量计算
</div>

<div class="content">
    <%--树结构--%>
    <div id='deptree'>
        <div id="t3" class="panel"></div>
    </div>
    <%--内容--%>
    <div id='count_result'>
        <div class="count_result_left">
            <table class="first result_table" border="1px solid black" id="count_result_table">
                <thead v-show="isShow">
                <tr>
                    <th style="width: 50px;"></th>
                    <th style="width: 70px;">序号</th>
                    <th>名称</th>
                    <th>数量</th>
                </tr>
                </thead>
                <tbody v-show="isShow">
                <tr v-for="(value, key ,index ) in result" @click="checkedItem(value.key,$event)">
                    <td>
                        <div class="bui-grid " v-show="ebehind(value.key)" style="cursor: pointer;">
                            <span class="x-grid-checkbox"></span>
                        </div>
                    </td>
                    <td>
                        {{ showRow(value.key, key) }}
                    </td>
                    <td>
                        {{ changeName(value.key) }}
                    </td>
                    <td>
                        {{ value.value }}
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="count_result_right">
            <div style="margin-bottom: 20px">
                <h2 style="margin-bottom: 5px">统计说明</h2>
                <div class="key_explain" style="min-height: 20px"></div>
            </div>
            <div id="grid"></div>
        </div>
    </div>
</div>

<div class="bui-ext-mask hide user-defined-mask"
     style=" width: 100%;left: 0;top: 0;height: 100%;position: fixed;"></div>

<div class="bui-ext-mask-msg x-mask-loading hide user-defined-mask" style="position: fixed; left: 45%; top: 45%;">
    <div>正在进行统计请稍后</div>
</div>


<script type="text/javascript">
    var vm;
    // 计算table tr td 最大数量
    var tdNumber = Math.floor(($(window).height() - 100) / 33) - 1;
    var isRe = false;
    var court = {};
    var columns = [];
    var firstLine = "<option value=''>请选择</option>";
    $(function () {
        $("select.code").each(function () {
            var typeId = $(this).attr("typeId");
            //行政职务和法律职务其他地方要用到
            if (typeId && typeId == 15) {
                loadCodeList(this, firstLine, null, $("#administrationPositionInfo"));
            } else if (typeId && typeId == 16) {
                loadCodeList(this, firstLine, null, $("#lawPositionInfo"));
            } else {
                loadCodeList(this, firstLine);
            }
        });
        //查询所有法院 与权限 限制无关
        $.getJSON(
            "/ums/code/codeListByTypeWithNoAspect",
            {},
            function (data) {
                for (var i = 0; i < data.data.length; i++) {
                    court[data.data[i].courtCode] = data.data[i].codeName;
                    $("select.Courtcode").append($("<option>").attr({"value": data.data[i].id}).text(data.data[i].codeName));
                }
                $("select.Courtcode").select2();
                makeBUI();
            })
    });
    var nullResult = [];
    for (var i = 0; i <= tdNumber; i++) {
        nullResult.push({
            key: i,
            value: ""
        });
    }
    var target_1 = ["preparation", "nation", "birthday", "gender", "idcard", "enterDate", "personnelClassification", "positionNature", "job"];
    var target_2 = ["political", "level", "lawPosition", "incompleteLawPosition", "educationBackground", "degree", "major", "rank", "incompletePolitical", "incompleteFamily", "incompleteYefg"];
    var target_3 = [];
    var target_2_v = {
        "political": "#userinfo/politics",
        "level": "#userinfo/level",
        "lawPosition": "#userinfo/legal",
        "incompleteLawPosition": "#userinfo/legal",
        "educationBackground": "#userinfo/education",
        "degree": "#userinfo/degree",
        "major": "#userinfo/degree",
        "rank": "#userinfo/rank",
        "incompletePolitical": "#userinfo/politics",
        "incompleteFamily": "#userinfo/family",
        "incompleteYefg": "#userinfo/level"
    };
    vm = new Vue({
        el: '#count_result_table',
        data: {
            result: nullResult,
            isShow: true,
            keyExplain: {
                // preparation: {val1: "编制为空", val2: "未删除且在职的人员中，人员基本信息编制为空的"},
                // nation: {val1: "民族为空", val2: "未删除且在职的人员中，人员基本信息民族为空的"},
                // birthday: {val1: "生日为空", val2: "未删除且在职的人员中，人员基本信息生日为空的"},
                // political: {val1: "政治面貌为空", val2: "未删除且在职的人员中，人员基本信息政治面貌为空的"},
                // level: {val1: "法官等级为空", val2: "未删除且在职的人员中，法官等级子集有当前信息为是的记录，但人员基本信息法官等级为空的且辅助人员等级为空的"},
                // lawPosition: {val1: "法官法律职务为空", val2: "未删除且在职的人员中，法律职务子集有当前信息为是的记录，但人员基本信息法律职务为空的"},
                // incompleteLawPosition: {val1: "法律职务不完整", val2: "未删除且在职的人员中，人员基本信息人员分类为“法官”及相关子类，但法官职务信息集中不存在对应的相关子集信息"},
                // gender: {val1: "性别为空", val2: "未删除且在职的人员中，人员基本信息性别没有选择为男或者女的"},
                // educationBackground: {val1: "学历为空", val2: "未删除且在职的人员中，人员基本信息学历为空的"},
                // degree: {val1: "学位为空", val2: "未删除且在职的人员中，人员基本信息学位为空的"},
                // major: {val1: "专业为空", val2: "未删除且在职的人员中，人员基本信息专业为空的"},
                // rank: {val1: "职级为空", val2: "未删除且在职的人员中，人员基本信息人员分类选择“审判辅助人员或司法行政人员”及相关子类，但人员职级为空的"},
                // idcard: {val1: "身份证号码不完整", val2: "未删除且在职的人员中，人员基本信息身份证号缺失或者不是15位或18位的"},
                // enterDate: {val1: "进入日期为空", val2: "未删除且在职的人员中，人员基本信息身份进入日期为空"},
                // incompletePolitical: {val1: "政治面貌子集不完整", val2: "政治面貌子集信息中有如下要求:1、政治面貌，必填" + "2、加入日期，政治面貌选择共青团员、无党派民主人士、群众、其他之外的项目必填" + "3、转正日期，政治面貌选择中共党员时必填" + "4、是否当前信息，必填"},
                // incompleteFamily: {val1: "是否有家庭成员", val2: "未删除且在职的人员中，未维护家庭成员的"},
                // incompleteYefg: {val1: "入额信息不完整", val2: "未删除且在职的人员中，人员基本信息人员分类选择“法官”及相关子类，" + "但法官等级信息集中不存在“当前信息为是”且“是否入额为是”、“入额时间不为空”的"},
                // personnelClassification: {val1: "人员分类为空", val2: "未删除且在职的人员中，人员基本信息人员分类为空的"},
                // positionNature: {val1: "岗位性质为空", val2: "未删除且在职的人员中，人员基本信息岗位性质为空的"},
                // job: {val1: "工作岗位为空", val2: "未删除且在职的人员中，人员基本信息工作岗位为空的"},
                // institution1: {val1: "单位性质类别", val2: "未撤销、未删除且机构名称中包含“法院”，但机构基本信息中“单位性质”未选择的"},
                // institution2: {val1: "单位（部门）级别（是否法院）", val2: "未撤销、未删除且机构名称中不包含“法院”，但机构基本信息中“单位（部门）级别”选择为法院的"},
                // institution3: {val1: "单位（部门）级别（高中级法院）", val2: "未撤销、未删除且机构名称中包含“高级”，" + "但机构基本信息中“单位（部门）级别”未选择“高级法院”的；机构名称中包含“中级”，但机构基本信息中“单位（部门）级别”未选择“中级法院”的"},
                // institution4: {val1: "是否院领导（院领导标识）", val2: "未撤销、未删除且机构名称中包含“班子成员”且不包含“非”，但机构基本信息中“是否院领导”未选择是的"},
                // institution5: {val1: "是否存在院领导", val2: "每家法院必须存在一个院领导部门"},
                // institution6: {val1: "是否人民法庭", val2: "未撤销、未删除且机构名称中包含“人民法庭”，但机构基本信息中“是否人民法庭”未选择是的"}
            }
        },
        methods: {
            showRow: function (key, index) {
                var t = this.keyExplain;
                return t[key] ? index + 1 : "";
            },
            changeName: function (key) {
                var t = this.keyExplain;
                return t[key] ? t[key]["val1"] : "";
            },
            ebehind: function (key) {
                var t = this.keyExplain;
                return t[key] ? true : false;
            },
            checkedItem: function (key, ev) {
                var target = ev.target || ev.srcElement;
                var $target = $(target).closest("tr");
                if ($target.hasClass("checked")) {
                    return;
                }
                $("tr.checked").removeClass("checked");
                $target.addClass("checked");
                // 改变右侧数据
                $(".key_explain").html(this.keyExplain[key]["val2"]);
                var loadData = {"start": 0, "pageIndex": 0, "query": key};
                console.log(key);
                if (courtNo) {
                    loadData = $.extend({}, loadData, {courtNo: courtNo});
                } else {
                    loadData = $.extend({}, loadData, {courtNo: null});
                }
                store.load(loadData);
                //显示右侧的按钮
                // if (key != "user-defined_1" && ($.inArray(key, target_1) > -1 || $.inArray(key, target_2) > -1 || $.inArray(key, target_3) > -1)) {
                //     $(".button-group-1").show();
                //     $(".button-group-2").show();
                // } else {
                //     $(".button-group-1").hide();
                //     $(".button-group-2").hide();
                // }
                var deptArr = ['user-defined_21','user-defined_23','user-defined_24','user-defined_67','user-defined_68','user-defined_69','user-defined_72','user-defined_73'];
                var courtArr = [ 'user-defined_19' ,'user-defined_20' ,'user-defined_59'];
                if ( $.inArray(key, courtArr) > -1  || key == "user-defined_21" || key == "user-defined_22" || key == "user-defined_23" || key == "user-defined_24" || key == "user-defined_25" || key == "user-defined_43" || $.inArray(key,deptArr)  > -1  ) {
                    $(".button-group-1").hide();
                    $(".button-group-2").hide();
                } else {
                    $(".button-group-1").show();
                    $(".button-group-2").show();
                }
                if ( $.inArray(key, courtArr) > -1  ) {
                    columns[1].set("visible", true);
                    columns[2].set("visible", false);
                    columns[3].set("visible", false);
                    columns[4].set("visible", false);
                    columns[5].set("visible", false);
                    columns[6].set("visible", true);
                    columns[7].set("visible", false);
                    columns[8].set("visible", false);
                    columns[9].set("visible", true);
                    columns[10].set("visible", true);
                }else if( key == "user-defined_22" ){
                    columns[1].set("visible", true);
                    columns[2].set("visible", false);
                    columns[3].set("visible", false);
                    columns[4].set("visible", false);
                    columns[5].set("visible", false);
                    columns[6].set("visible", true);
                    columns[7].set("visible", false);
                    columns[8].set("visible", false);
                    columns[9].set("visible", false);
                    columns[10].set("visible", true);

                } else if ( $.inArray(key,deptArr)  > -1 ) {
                    columns[1].set("visible", true);
                    columns[2].set("visible", false);
                    columns[3].set("visible", false);
                    columns[4].set("visible", false);
                    columns[5].set("visible", false);
                    columns[6].set("visible", true);
                    columns[7].set("visible", true);
                    columns[8].set("visible", true);
                    columns[9].set("visible", false);
                    columns[10].set("visible", true);
                } else if (key == "user-defined_25" || key == "user-defined_43") {
                    columns[1].set("visible", true);
                    columns[2].set("visible", false);
                    columns[3].set("visible", false);
                    columns[4].set("visible", false);
                    columns[5].set("visible", false);
                    columns[6].set("visible", true);
                    columns[7].set("visible", false);
                    columns[8].set("visible", false);
                    columns[9].set("visible", true);
                    columns[10].set("visible", true);
                } else {
                    columns[1].set("visible", true);
                    columns[2].set("visible", true);
                    columns[3].set("visible", true);
                    columns[4].set("visible", true);
                    columns[5].set("visible", true);
                    columns[6].set("visible", true);
                    columns[7].set("visible", false);
                    columns[8].set("visible", false);
                    columns[9].set("visible", false);
                    columns[10].set("visible", false);
                }
                // var institution1 = ["institution1", "institution3", "institution5"];
                // var institution2 = ["institution2", "institution4", "institution6"];
                // if ($.inArray(key, institution1) > -1) {
                //     columns[1].set("visible", false);
                //     columns[2].set("visible", false);
                //     columns[3].set("visible", false);
                //     columns[4].set("visible", false);
                //     columns[5].set("visible", false);
                //     columns[6].set("visible", false);
                //     columns[7].set("visible", false);
                //     columns[8].set("visible", false);
                //     columns[9].set("visible", true);
                //     columns[10].set("visible", true);
                // } else if ($.inArray(key, institution2) > -1) {
                //     columns[1].set("visible", false);
                //     columns[2].set("visible", false);
                //     columns[3].set("visible", false);
                //     columns[4].set("visible", false);
                //     columns[5].set("visible", false);
                //     columns[6].set("visible", true);
                //     columns[7].set("visible", true);
                //     columns[8].set("visible", true);
                //     columns[9].set("visible", false);
                //     columns[10].set("visible", false);
                // } else if (key == "user-defined_1") {
                //     columns[1].set("visible", false);
                //     columns[2].set("visible", false);
                //     columns[3].set("visible", false);
                //     columns[4].set("visible", false);
                //     columns[5].set("visible", false);
                //     columns[6].set("visible", true);
                //     columns[7].set("visible", true);
                //     columns[8].set("visible", true);
                //     columns[9].set("visible", false);
                //     columns[10].set("visible", false);
                // } else {
                //     columns[1].set("visible", true);
                //     columns[2].set("visible", true);
                //     columns[3].set("visible", true);
                //     columns[4].set("visible", true);
                //     columns[5].set("visible", true);
                //     columns[6].set("visible", false);
                //     columns[7].set("visible", false);
                //     columns[8].set("visible", false);
                //     columns[9].set("visible", false);
                //     columns[10].set("visible", false);
                // }
            }
        },
        computed: {}
    });
    vm.$watch("result", function (nv, ov) {
        var tdCount = 0;
        for (var i in vm.result) {
            if (vm.result.hasOwnProperty(i)) {
                tdCount++;
            }
        }
    });
    getStorage();
    // 额外的验证
    $.post("<%=basePath%>view/getZljsConfig", {}, function (datas) {
        console.log(datas);
        if (datas) {
            vm.keyExplain = $.extend({}, vm.keyExplain, datas);
            //为了显示按钮 还要加入到target_3中
            $.each(datas, function (key, value) {
                target_3.push(key);
            });
        }
    });
    var startRow;
    var store;
    var courtNo = "";
    var userinfo = {};

    function makeBUI() {
        BUI.use(['common/search', 'bui/tree', 'bui/data', 'bui/calendar', 'bui/overlay', 'bui/form', 'bui/uploader', 'bui/mask', 'bui/grid'], function (Search, Tree, Data, Calendar, Overlay, Form, Uploader, Mask, Grid) {
            //-------------树结构Start-----------------
            var treestore = new Data.TreeStore({
                root: {
                    text: "全部",
                    courtNo: "",
                    deptNo: ""
                },
                url: '<%=basePath%>code/tree/children3?type=1'
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
            treestore.load();//加载根节点，也可以让用户点击加载
            tree.on('itemclick', function (ev) {
                var item = ev.item;
                courtNo = item.courtNo;
                console.log(courtNo);
                depNo = item.deptNo;
                getStorage(courtNo);
            });
            columns = [
                {
                    title: '序号', sortable: false, width: "60", align: 'left', renderer: function (value, obj) {
                        return startRow++;
                    }
                },
                {title: '姓名', dataIndex: 'fullname', width: "100", sortable: true, align: 'left'},
                {title: '性别', dataIndex: 'gender_text', width: "60", sortable: true, align: 'left'},
                {title: '法院', dataIndex: 'court_no_text', width: "200", sortable: true, align: 'left'},
                {title: '部门', dataIndex: 'department_text', width: "150", sortable: true, align: 'left'},
                {
                    title: '法院',
                    dataIndex: 'court_code',
                    width: "200",
                    sortable: true,
                    align: 'left',
                    renderer: BUI.Grid.Format.enumRenderer(court)
                },
                {title: '部门名称', dataIndex: 'dept_name', width: "150", sortable: true, align: 'left'},
                {title: '标准部门名称', dataIndex: 'dept_st_name', width: "150", sortable: true, align: 'left'},
                {title: '法院', dataIndex: 'court_std_name', width: "200", sortable: true, align: 'left'},
                {title: '法院简称', dataIndex: 'court_short_name', width: "150", sortable: true, align: 'left'}
            ];
            //菜单按钮
            var tbarData = {
                items: [{
                    text: '<i class="icon-edit"></i>基本信息编辑',
                    btnCls: 'button button-small button-group-1',
                    handler: function () {
                        form.clearErrors();
                        var userinfo = grid.getSelected();
                        if (userinfo) {
                            $("#userinfo_form #new_id").val("");
                            loadUserinfo(userinfo.id);
                            editDialog.set('title', '编辑 - ' + userinfo.fullname + ' - ' + userinfo.courtNoText);
                            editDialog.set('headerContent',
                                '<ul class="nav-tabs"> <li><a style="border: 1px solid white;color:inherit;">编辑 - ' + userinfo.fullname + ' - ' + userinfo.courtNoText + '</a></li>' +
                                '<li class="active"><a href="#baseInfo" onclick="changeMarkers(this)" class="initClass"><s>*</s>基本信息</a></li> ' +
                                '<li><a href="#mainjobInfo" onclick="changeMarkers(this)">职务信息</a></li><li><a href="#eduInfo" onclick="changeMarkers(this)">教育经历</a></li> ' +
                                '<li><a href="#certificateInfo" onclick="changeMarkers(this)">证件信息</a></li>' +
                                '<li><a href="#jobDetailInfo" onclick="changeMarkers(this)"><s>*</s>工作信息</a></li><li><a href="#DispatchInfo" onclick="changeMarkers(this)"><s>*</s>调遣信息</a></li>' +
                                '</ul>');
                            editDialog.show();
                            location.href = $(".initClass").attr("href");
                            $(".initClass").click();
                        } else {
                            BUI.Message.Alert("请选择一条记录", null, "info");
                        }
                    }
                }, {
                    text: '<i class="icon-edit"></i>子集信息编辑',
                    btnCls: 'button button-small button-group-2',
                    handler: function () {
                        form.clearErrors();
                        var userinfo = grid.getSelected();
                        if (userinfo) {
                            var params = store.get("lastParams");
                            if ($.inArray(params['query'], target_2) > -1) {
                                var target_url = target_2_v[params['query']];
                                open("<%=basePath%>view/detail_new?id=" + userinfo.id + target_url);
                            } else {
                                open("<%=basePath%>view/detail_new?id=" + userinfo.id);
                            }
                        } else {
                            BUI.Message.Alert("请选择一条记录", null, "info");
                        }
                    }
                }]
            };
            // 定义表格样式
            var gridCfg = Search.createGridCfg(columns, {
                height: "auto",
                emptyDataTpl: '<div class="centered"><h2>查询的数据不存在</h2></div>',
                tbar: tbarData,
                plugins: [BUI.Grid.Plugins.RadioSelection, BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格
            });
            // 请求数据
            store = Search.createStore("<%=basePath%>/view/getZljsResult", {
                sortField: 'sort',
                sortDirection: 'ASC',
                remoteSort: true, // 开启异步排序
                pageSize: 20,
                params: {}
            });
            // 错误信息
            store.on('exception', function () {
                BUI.Message.Alert('参数请求错误');
            });
            store.on("load", function () {
                startRow = store.get("start") + 1;
            });
            var search = new Search({
                store: store,
                gridCfg: gridCfg
            });
            var grid = search.get('grid');
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
            });
            uploader.on("change", function (e) {
            });
            //--------------图片上传功能End-----------------
            //新增窗体
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
                                BUI.Message.Alert("保存过程中出现错误！", null, "warning");
                            }
                        }
                    },
                    {
                        text: '重置', elCls: 'button', handler: function () {
                            resetUserinfoForm();
                        }
                    },
                    {
                        text: '关闭', elCls: 'button', handler: function () {
                            this.close();
                        }
                    }],
                contentId: 'content', //配置DOM容器的编号
                success: function () {
                    this.close();
                }
            });
            editDialog.on("closing", function () {
                initSelect2("select22");
                $("select.Courtcode").select2();
                form.clearFields();
                form.clearErrors();
            });
            var form = new Form.HForm({
                srcNode: '#userinfo_form',
                submitType: 'ajax',
                callback: function (data) {
                    if (data.success) {
                        BUI.Message.Alert("保存成功！", null, "warning");
                        store.load();
                        editDialog.close();
                    } else {
                        BUI.Message.Alert(data.msg, null, "warning");
                    }
                }
            }).render();

            function loadUserinfo(id) {
                form.clearFields();
                form.clearErrors();
                $("#age").html("0岁");
                $('#birthday').val("");
                $('#birthday_show').html("");
                $('#password').val("");
                $(".show_info").hide();
                $(".show_icon").hide();
                $(".show_info_ok").hide();
                $(".show_icon_ok").hide();
                var obj = {
                    id: id,
                    _: new Date()
                };
                // 找到所有配置其他数据集的字段
                $("#userinfo_form [otherfield]").each(function () {
                    var table_field = $(this).attr('otherfield');
                    var table = table_field.split('.')[0];
                    var field = table_field.split('.')[1];
                    if (obj['fields.' + table] !== undefined) {
                        obj['fields.' + table] = obj['fields.' + table] + "," + field;
                    } else {
                        obj['fields.' + table] = field;
                    }
                });
                $.getJSON("<%=basePath%>userinfo/one", obj, function (data) {
                    userinfo = data;
                    reloadUserinfo();
                    // 设置用户名的提示，编辑时不可以修改用户名
                    $("#username").attr("readonly", "readonly");
                });
                loadDjxx(id);
            }

            function resetUserinfoForm() {
                $("#age").html("0岁");
                $('#birthday').val("");
                $('#birthday_show').html("");
                $('#password').val("");
                $(".show_info").hide();
                $(".show_icon").hide();
                $(".show_info_ok").hide();
                $(".show_icon_ok").hide();
                if (editDialog.get("title") == '新增') {
                    initSelect2("select22");
                    $("select.Courtcode").select2();
                    form.clearFields();
                    form.clearErrors();
                } else {
                    reloadUserinfo();
                }
            }

            function reloadUserinfo() {
                $("#userinfo_form [name^='userInfo.']").each(function () {
                    $(this).val(userinfo[$(this).attr("id")]);
                    if ($(this).attr("id") === "courtNo") {
                        //不触发change事件
                        var onchangeF = $(this)[0].onchange;
                        $(this)[0].onchange = null;
                        changeSelect2Value($(this), userinfo[$(this).attr("id")]);
                        loadDeptList($("#userinfo_form #department"), $(this).val(), firstLine, function () {
                            $("#userinfo_form #department").val(userinfo.department);
                            changeSelect2Value($("#department"), userinfo.department);
                        });
                        $(this)[0].onchange = onchangeF;
                        //禁止编辑
                        $(this).attr("disabled", true);
                        $("#userinfo_form #department").attr("disabled", true);
                    }
                    if ($(this).attr("name").indexOf("birthday") > 0) {
                        /*$("#birthday_show").html(userinfo[$(this).attr("id")]);*/
                        $("#birthday").html(userinfo[$(this).attr("id")]);
                    }
                    if ($(this).hasClass("select22")) {
                        $(this).select2().select2("val", userinfo[$(this).attr("id")]);
                        $(this).change();
                    }
                    if ($(this).attr('listtype') == 'tree2') {
                        $(this).change();
                    }
                });
                $('#lawyerCert').change();  //法官资格证书为 “无” 时，去掉日期验证
                $('#partyOffice').change();
                //    判断是否为院领导，是则显示班子成员
                if (userinfo.deptOrgCode == '000') {
                    $('#bzcy').parent().parent().show();
                } else {
                    $('#bzcy').parent().parent().hide();
                }
                $("#userinfo_form [otherfield]").each(function () {
                    var table_field = $(this).attr('otherfield');
                    var field = table_field.split('.')[1];
                    if (userinfo['otherfield'] != null) {
                        $(this).val(userinfo['otherfield'][field]);
                    }
                    $(this).attr("disabled", true);
                    if ($(this).hasClass("select22")) {
                        $(this).select2().select2("val", userinfo['otherfield'][field]);
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
                loadDjxx(userinfo.id);
                calcAge();
                workTotal();
                courtTotal();
                form.clearErrors();
            }

            function changeSelect2Value(_this, val) {
                $(_this).select2().select2("val", val);
                $(_this).change();
            }

            function userCheck() {
                var isEdit = true;
                var url = "<%=path%>/checkUserName";
                var datas = {
                    courtStdNo: $("#courtNo").val(),
                    username: $("#username").val(),
                    id: $("#id").val(),
                    valid: 1,
                    userType: 1,
                    isEdit: isEdit
                }
                form.valid();
                if (form.isValid()) {
                    $.post(url, datas, function (data) {
                        if (data.UserNameCheck == "false") {
                            if (isEdit) {
                                $('#password').val("");
                            }
                            $("#courtNo").attr("disabled", false);
                            $("#userinfo_form #department").attr("disabled", false);
                            var objs = $('[disabled=disabled]');
                            objs.removeAttr('disabled');
                            form.submit();
                            objs.attr('disabled', 'disabled');
                            $("#courtNo").attr("disabled", true);
                            $("#userinfo_form #department").attr("disabled", true);
                        } else {
                            // 如果username返回为""，说明并未修改传过去的usename
                            if (data.username != "") {
                                $(".show_info_ok").show();
                                $(".show_icon_ok").show();
                                newUserName = data.username;
                                $("#suggestUsername").text(newUserName);
                                $("#username").val(newUserName);
                            } else {
                                $(".show_info").show();
                                $(".show_icon").show();
                            }
                            var a = document.getElementById("show_e");
                            //取消<a>标签原先的onclick事件,使<a>标签点击后通过href跳转(因为无法用js跳转)^-^
                            a.setAttribute("onclick", '');
                            //激发标签点击事件OVER
                            a.click("return false");
                        }
                    })
                } else {
                    form.submit();
                }
            }

            $('#lawyerCert').on('change', function () {
                if ($(this).val() == 4 || $(this).find('option:selected').text() == '无') {
                    form.getField('userInfo.lawyerCertDate').set('pauseValid', true).clearErrors();
                } else {
                    form.getField('userInfo.lawyerCertDate').set('pauseValid', false);
                }
            });
            $('#partyOffice').on('change', function () {
                var options = $('#partyOfficeReport').find('option:selected').removeAttr('selected').parent().find('option');
                for (var i in options) {
                    if ($(this).find('option:selected').text() == $(options[i]).text()) {
                        $(options[i]).attr('selected', 'selected');
                        break;
                    }
                }
            });
            $(".bui-grid-body").height($(window).height() - 280);

        });
    }

    function getStorage(courtNo) {
        var url = '<%=basePath%>view/zljs';
        var data = {};
        if (courtNo) {
            data['courtNo'] = courtNo;
        }
        $(".user-defined-mask").removeClass("hide");
        $("tr.checked").removeClass("checked");
        $.post(url, data, function (datas) {
            console.log(datas);
            $(".user-defined-mask").addClass("hide");
            var tdCount = 0;
            var list = [];
            var list2 = [];
            for (var i in datas) {

                if (datas.hasOwnProperty(i)) {
                    if (i.indexOf('user') != -1) {
                        list2.push({
                            key: i,
                            value: datas[i]
                        });
                    } else {
                        list.push({
                            key: i,
                            value: datas[i]
                        });
                    }
                    tdCount++;
                }
            }
            $.merge(list, list2);
            for (var j = tdCount; j <= tdNumber - 1; j++) {
                list.push({
                    key: j,
                    value: ""
                });
            }
            vm.result = list;
        });
    }

    function calcAge() {
        $("#userinfo_form #age").val(calcYears($("#userinfo_form #birthday").val()) + '岁');
    }

    function workTotal() {
        $("#userinfo_form #totalSeniority").text(workTotalYears($("#userinfo_form #workDate").val(), $("#userinfo_form #extraSeniority").val(), $("#userinfo_form #deductionSeniority").val()) + '年');
    }

    function courtTotal() {
        $("#userinfo_form #totalCourtYear").text(workTotalYears($("#userinfo_form #enterDate").val(), $("#userinfo_form #beforeCourtWorkYear").val()) + '年');
    }

    function initSelect2(class_) {
        $("select." + class_).each(function () {
            var firstOptionValue = $(this).find("option:eq(0)").val();
            $(this).select2().select2("val", firstOptionValue);
            $(this).change();
        });
    }

    function changeMarkers(obj) {
        $(obj).parent().parent().find(".active").removeClass("active");
        $(obj).parent().addClass("active");
    }

    function loadDjxx(userid) {
        // 法官信息
        $.post('<%=basePath%>view/userinfo/attachment2', {
            "otherParam.n_level_type": "1",
            "otherParam.n_present_info": "1",
            "viewName": "levelInfo",
            userId: userid
        }, function (data) {
            if (data && data.success && data.results > 0) {
                var dat = data.rows[0];
                $('#judge_level').val(dat['judge_level']);
                $('#judge_level_date').val(dat['d_start_date']);
            }
        });
        // 法警信息
        $.post('<%=basePath%>view/userinfo/attachment2', {
            "otherParam.n_level_type": "2",
            "otherParam.n_present_info": "1",
            "viewName": "levelInfo",
            userId: userid
        }, function (data) {
            if (data && data.success && data.results > 0) {
                var dat = data.rows[0];
                $('#marshal_level').val(dat['marshal_level']);
                $('#marshal_level_date').val(dat['d_start_date']);
            }
        });
        // 书记员信息
        $.post('<%=basePath%>view/userinfo/attachment2', {
            "otherParam.n_level_type": "4",
            "otherParam.n_present_info": "1",
            "viewName": "levelInfo",
            userId: userid
        }, function (data) {
            if (data && data.success && data.results > 0) {
                var dat = data.rows[0];
                $('#clerk_level').val(dat['clerk_level']);
                $('#clerk_level_date').val(dat['d_start_date']);
            }
        });
    }
</script>
</body>
</html>
