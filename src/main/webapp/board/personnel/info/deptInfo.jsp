<%@ page import="cn.net.withub.ums.entity.UmsUserInfoView" %>
<%--
    Document   : user2
    Created on : 2014-12-23, 16:53:11
    Author     : Diluka
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    UmsUserInfoView user = (UmsUserInfoView) session.getAttribute("loginUser");
    int userCourt = user.getCourtNo();
%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>部门管理</title>
    <jsp:include page="/basic_import.jsp"></jsp:include>
    <link rel="stylesheet" href="<%=basePath%>js/zTree/css/zTreeStyle/zTreeStyle.css"/>
    <script type="text/javascript" src="<%=basePath%>js/common/codelist.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/common/datetools.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/zTree/js/jquery.ztree.all.js"></script>
    <jsp:include page="/fragment/tip.jsp"></jsp:include>
    <style type="text/css">
        /**内容超出 出现滚动条 **/
        .bui-stdmod-body {
            overflow-x: hidden;
            overflow-y: auto;
        }

        #grid .icon-user {
            margin: 0px 4px 0 2px;
        }

        .grid-valid {
            color: #3366cc;
            cursor: pointer;
            margin-right: 5px;
        }

        .grid-valid:hover {
            color: #ff6600;
        }

        .grid-validY {
            color: #6bc30d;
        }

        .grid-validN {
            color: #ac2925;
        }

        .bui-message {
            z-index: 1200;
        }

        /*outlook样式*/
        .ztree * {
            font-size: 10pt;
            font-family: "Microsoft Yahei", Verdana, Simsun, "Segoe UI Web Light", "Segoe UI Light", "Segoe UI Web Regular", "Segoe UI", "Segoe UI Symbol", "Helvetica Neue", Arial
        }

        .ztree li ul {
            margin: 0;
            padding: 0
        }

        .ztree li {
            line-height: 30px;
        }

        .ztree li a {
            width: 95%;
            height: 30px;
            padding-top: 0px;
        }

        .ztree li a:hover {
            text-decoration: none;
            background-color: #E7E7E7;
        }

        .ztree li a span.button.switch {
            visibility: hidden
        }

        .ztree li a.curSelectedNode {
            background-color: #D4D4D4;
            border: 0;
            height: 30px;
        }

        .ztree li span {
            line-height: 30px;
        }

        .ztree li span.button {
            margin-top: -7px;
        }

        .ztree li span.button.switch {
            width: 16px;
            height: 16px;
        }

        .ztree li span.button.switch {
            background-image: url("./../../../js/zTree/img/left_menuForOutLook.png");
            *background-image: url("./../../../js/zTree/img/left_menuForOutLook.gif")
        }

        .ztree li span.button.switch.level0 {
            width: 20px;
            height: 20px
        }

        .ztree li span.button.switch.level1 {
            width: 20px;
            height: 20px
        }

        .ztree li span.button.switch.level2 {
            width: 20px;
            height: 20px
        }

        .ztree li span.button.switch.level3 {
            width: 20px;
            height: 20px
        }

        .ztree li span.button.center_open, .ztree li span.button.roots_open, .ztree li span.button.bottom_open {
            background-position: 0 0;
        }

        .ztree li span.button.center_close, .ztree li span.button.roots_close, .ztree li span.button.bottom_close {
            background-position: -18px 0;
        }

        .ztree li span.button.ico_open, .ztree li span.button.ico_docu, .ztree li span.button.ico_close {
            vertical-align: middle;
        }

        .ztree li ul {
            padding-left: 20px;
        }

        .ztree li div {
            line-height: 30px;
            float: right;
            cursor: pointer;
        }

        .ztree li div.nopoint {
            opacity: 0.3;
            cursor: inherit;
        }

        /*ztree拖拽时的指示箭头，默认无index，ztree在弹窗里时会被覆盖*/
        #zTreeMove_arrow_tmp, .zTreeDragUL {
            z-index: 2000;
        }
    </style>
</head>
<body style="overflow: hidden;">

<div class="row">
    <div>
        <ul class="breadcrumb">
            <li><span>人事管理</span><span class="divider">/</span></li>
            <li><span>信息管理</span><span class="divider">/</span></li>
            <li class="active">部门管理</li>
        </ul>
    </div>
</div>

<div style="padding-left: 10px;padding-bottom: 5px;width: 200px ">
    <button id="sortUp" class="button button-small"><i class="icon-circle-arrow-up"></i> 排序上移</button>
    <button id="sortDown" class="button button-small"><i class="icon-circle-arrow-down"></i> 排序下移</button>
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

<%--部门新增窗口 --%>
<div id="deptinsertContent" class="hide">
    <form id="dept_insert" class="form-horizontal">
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>部门名称：</label>

                <div class="controls">
                    <input type="text" class="control-text" id="deptName" data-rules="{required:true}">
                </div>
            </div>

            <div class="control-group span8">
                <label class="control-label"><s>*</s>标准部门名称：</label>

                <div class="controls">
                    <select data-rules="{required:true}" id="deptStdName" class="select2">

                    </select>

                </div>
            </div>
        </div>
        <div class="row">

            <div class="control-group span8">
                <label class="control-label">组织机构编码：</label>

                <div class="controls">
                    <input type="text" class="control-text" id="institutionCode">

                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>单位级别：</label>

                <div class="controls">
                    <select data-rules="{required:true}" id="courtLevel" class="select2">
                    </select>
                </div>
            </div>
        </div>
        <div class="row">

            <div class="control-group span8">
                <label class="control-label"><s>*</s>部门性质类别：</label>

                <div class="controls">
                    <select data-rules="{required:true}" id="deptType">
                    </select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>是否人民法庭：</label>

                <div class="controls">
                    <select data-rules="{required:true}" id="isPeples">
                    </select>
                </div>
            </div>
        </div>
        <div class="row">

            <div class="control-group span8">
                <label class="control-label"><s>*</s>是否院领导：</label>

                <div class="controls">

                    <select data-rules="{required:true}" id="isLeaders">
                    </select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">考核序列：</label>

                <div class="controls">

                    <select id="assessSerial">
                    </select>
                </div>
            </div>
        </div>
    </form>
</div>

<%--部门排序窗口 --%>
<div id="deptsortContent" class="hide">
    <label><span style="color: red;">提示：</span>先将所有部门的顺序通过拖拽或是按钮点击后调整后，再点击确定，将会一次性排好所有部门的顺序。</label>
    <br>
    <br>
    <div style="height: 50vh;overflow-y: auto">
        <div id="dept_Name" class="ztree">
        </div>
    </div>
</div>

<%--申请调职窗口--%>
<div id="dz" class="hide">
    <div style="float: left;width:440px;margin-left: 100px;">
        <div class="control-group span8">
            <label class="control-label">目标部门：</label>
            <div class="controls">
                <select id="new_dept_no" name="new_dept_no">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
    </div>
</div>


</body>

<%--Script Begin--%>
<script type="text/javascript">

    var parentId;// 代表法院的级别，是ums_code表中parent_id的级别，高院1,高院内部门11,中院2,中院内部门21,基层法院3总共5个类型；
    var V = $.extend({}, V, parent.V);
    var court = {};
    var deptJson = {};
    var courtLevelJson = {};
    var deptTypeJson = {};
    var assessSerialJson = {};
    var count = 3;
    var ztreeObj = {};
    var ztreeSettings = {
        async: {
            enable: true,
            url: '<%=basePath%>code/tree/children3',
            otherParam: {field: 'sortNo', direction: 'ASC', limit: 100},
            dataFilter: function (treeId, parentNode, data) {
                var arr = [];
                for (var i in data) {
                    arr.push({
                        "id": data[i].id,
                        "name": data[i].text,
                        "old": data[i].sortNo,
                        "value": data[i].deptNo,
                        "level": data[i].level,
                        "court": data[i].courtNo,
                        "isParent": !data[i].leaf,
                        "parent": parentNode === null || parentNode === undefined ? '' : parentNode['id']
                    });
                }
                return arr;
            }
        },
        edit: {
            enable: true,
            drag: {
                inner: false
            },
            showRemoveBtn: false,
            showRenameBtn: false
        },
        view: {
            selectedMulti: false,
            addDiyDom: function (treeId, treeNode) {
                var aObj = $("#" + treeNode.tId + "_a");
                var id = treeNode.id.replace(',', '');
                var editStr = "";
                if (!treeNode.isLastNode)
                    editStr += "<div id='diyBtn_down_" + id + "' title='排序下移'><i class='x-icon icon-chevron-down'></i></div>";
                else
                    editStr += "<div class='nopoint' id='diyBtn_down_" + id + "' title='排序下移'><i class='x-icon icon-chevron-down'></i></div>";

                if (!treeNode.isFirstNode)
                    editStr += "<div id='diyBtn_up_" + id + "' title='排序上移'><i class='x-icon icon-chevron-up'></i></div>";
                else
                    editStr += "<div class='nopoint' id='diyBtn_up_" + id + "' title='排序上移'><i class='x-icon icon-chevron-up'></i></div>";
                aObj.append(editStr);
                var btnup = $("#diyBtn_up_" + id);
                var btndown = $("#diyBtn_down_" + id);
                btnup.unbind().bind("click", function (e) {
                    if (treeNode.getIndex() === 0) {
                        showTip("该部门已经排在第一位了！");
                        return;
                    }
                    //移动到前一个节点前
                    var preNode = treeNode.getPreNode();
                    var $preDivs = $('#' + preNode.tId + '_a').children('[id^=diyBtn]');
                    //新排序号为0时调整上移按钮
                    if (preNode.getIndex() === 0) {
                        //    移除样式
                        $preDivs.eq(1).removeClass('nopoint');
                        btnup.addClass('nopoint');
                    }
                    //当前节点是最后节点时，调整上一节点
                    if (btndown.hasClass('nopoint')) {
                        $preDivs.eq(0).addClass('nopoint');
                        //清除按钮样式
                        btndown.removeClass('nopoint');
                    }
                    ztreeObj.moveNode(preNode, treeNode, "prev");
                });
                btndown.unbind().bind("click", function (e) {
                    if ($(e.target).hasClass('nopoint') || $(e.target).parent().hasClass('nopoint')) {
                        showTip("该部门已经排在最后了！");
                        return;
                    }
                    //移动到后一个节点后
                    var nextNode = treeNode.getNextNode();
                    var $nextDivs = $('#' + nextNode.tId + '_a').children('[id^=diyBtn]');
                    //下一个节点是最后节点时，修改当前节点的样式
                    if ($nextDivs.eq(0).hasClass('nopoint')) {
                        $nextDivs.eq(0).removeClass('nopoint');
                        btndown.addClass('nopoint');
                    }
                    //当前节点是第一个节点时，将下一个节点样式修改
                    if (treeNode.getIndex() === 0) {
                        $nextDivs.eq(1).addClass('nopoint');
                        //清除按钮样式
                        btnup.removeClass('nopoint');
                    }
                    ztreeObj.moveNode(nextNode, treeNode, "next");
                });
            }
        },
        callback: {
            beforeAsync: function (treeId, treeNode) {
                if (treeNode) {
                    ztreeObj.setting.async.otherParam.id = treeNode.id;
                }
            },
            beforeDrop: function (treeId, treeNodes, targetNode, moveType) {
                var p1 = treeNodes[0].getParentNode();
                var p2 = targetNode.getParentNode();
                if ((p1 == null && p2 != null) || (p2 == null && p1 != null) || (p1 != null && p2 != null && p1.id !== p2.id) && moveType == 'inner') {
                    alert('不能设置为子节点！');
                    return false;
                }
                return true;
            },
            onDrop: function zTreeOnDrop(event, treeId, treeNodes, targetNode, moveType) {
                var p1 = treeNodes[0].getParentNode();
                //更新nopoints
                if (p1 != null) {
                    $('#' + p1.tId + '_a').next().children().find('[id^=diyBtn].nopoint').removeClass('nopoint');
                    $('#' + p1.children[0].tId + '_a').children('[id^=diyBtn]').eq(1).addClass('nopoint');
                    $('#' + p1.children[p1.children.length - 1].tId + '_a').children('[id^=diyBtn]').eq(0).addClass('nopoint');
                } else {
                    var arr = ztreeObj.getNodes();
                    $('.level0>a').children('[id^=diyBtn].nopoint').removeClass('nopoint');
                    $('#' + arr[0].tId + '_a').children('[id^=diyBtn]').eq(1).addClass('nopoint');
                    $('#' + arr[arr.length - 1].tId + '_a').children('[id^=diyBtn]').eq(0).addClass('nopoint');
                }
            }
        },
        newTools: {
            getNodes: function (node) {
                var nodes = [], nodes1 = [];
                if (!node)
                    nodes = ztreeObj.getNodes();
                else
                    nodes = node.children;
                for (var i = 0; i < nodes.length; i++) {
                    nodes[i]['newSort'] = i + 1;
                    nodes1.push(nodes[i]);
                    if (nodes[i].children) {
                        nodes1 = Array.prototype.concat(nodes1, this.getNodes(nodes[i]));
                    }
                }
                return nodes1;
            },
            getSubmitJson: function () {
                var nodes = this.getNodes();
                var newNodes = [];
                for (var i = 0; i < nodes.length; i++) {
                    var id = nodes[i]['id'];
                    var split = id.split(',');
                    var courtno = split[0];
                    var deptno = split[1];
                    newNodes.push({
                        level: nodes[i]['level'] + '',
                        courtno: courtno,
                        deptno: deptno,
                        parent: nodes[i]['parent'],
                        newSort: nodes[i]['newSort'] + ''
                    });
                }
                return newNodes;
            }
        }
    };
    // 批量修改的当前法院 编码
    var courtcode_current;
    // 批量修改的当前法院 数字
    var courtno_current;
    // 批量修改的当前部门 数字
    var deptno_current
    // 批量转移部门内人员的部门
    var persons_changeDept;

    // 初始化下拉框
    $(function () {
        //查询所有法院 与权限 限制无关
        $.getJSON("/ums/code/codeListByTypeWithNoAspect", {}, function (data) {
            for (var i = 0; i < data.data.length; i++) {
                court[data.data[i].courtCode] = data.data[i].codeName;
            }
            makeBUI();
        });
        //标准部门名称
        $.getJSON("<%=path%>/deptAction/getBmDept", {}, function (data) {
            for (var i = 0; i < data.data.length; i++) {
                if ($("#deptStdName").length > 0) {
                    if (i == 0) {
                        $("#deptStdName").select2().select2("val", data.data[i].orgCode);
                    }
                    $("#deptStdName").append($("<option>").attr({"value": data.data[i].orgCode}).text(data.data[i].deptName));
                }
            }
            $("#deptStdName").change();
        });

        // 查询单位级别
        $.getJSON("/ums/code/codeListByType", {typeId: 127}, function (data) {
            $("#courtLevel").empty();
            $("#courtLevel").append($("<option>").attr({"value": ''}).text('请选择'));
            $("#courtLevel").select2().select2("val", '');
            for (var i = 0; i < data.length; i++) {
                courtLevelJson[data[i].id] = data[i].codeName;
                $("#courtLevel").append($("<option>").attr({"value": data[i].id}).text(data[i].codeName));
            }
            $("#courtLevel").change();
            makeBUI();
        });

        //查询部门性质类别
        $.getJSON("/ums/code/codeListByType", {typeId: 102}, function (data) {
            $("#deptType").append($("<option>").attr({"value": ''}).text('请选择'));
            for (var i = 0; i < data.length; i++) {
                deptTypeJson[data[i].id] = data[i].codeName;
                $("#deptType").append($("<option>").attr({"value": data[i].id}).text(data[i].codeName));
            }
            makeBUI();
        });
        //是否
        $.getJSON("/ums/code/codeListByType", {typeId: 68}, function (data) {
            $("#isLeaders").append($("<option>").attr({"value": ''}).text('请选择'));
            $("#isPeples").append($("<option>").attr({"value": ''}).text('请选择'));
            for (var i = 0; i < data.length; i++) {
                $("#isLeaders").append($("<option>").attr({"value": data[i].id}).text(data[i].codeName));
                $("#isPeples").append($("<option>").attr({"value": data[i].id}).text(data[i].codeName));
            }
        });
        //考核序列
        $.getJSON("/ums/code/codeListByType", {typeId: 10006}, function (data) {
            $("#assessSerial").append($("<option>").attr({"value": null}).text('请选择'));
            for (var i = 0; i < data.length; i++) {
                assessSerialJson[data[i].id] = data[i].codeName;
                $("#assessSerial").append($("<option>").attr({"value": data[i].id}).text(data[i].codeName));
            }
        });
    });

    // 初始化BUI相关组件
    function makeBUI() {
        count--;
        if (count !== 0) return;
        BUI.use(['common/search', 'bui/tree', 'bui/data', 'bui/calendar', 'bui/overlay', 'bui/form', 'bui/uploader', 'bui/grid'], function (Search, Tree, Data, Calendar, Overlay, Form, Uploader, Grid) {
            //-------------初始化表格Start---------------
            var userinfoActionUrl = "<%=basePath%>deptAction/getDeptListInfo";
            //定义表格列
            var columns = [
                {
                    title: '序号', dataIndex: 'sortNo', width: "50", sortable: true, align: 'left'
                },
                {
                    title: '法院', dataIndex: 'courtCode', width: "160", sortable: true, align: 'left',
                    renderer: BUI.Grid.Format.enumRenderer(court)
                },
                {title: '部门名称', dataIndex: 'deptName', width: "160", sortable: true, align: 'left'},
                {title: '标准部门名称', dataIndex: 'deptStName', width: "160", sortable: true, align: 'left'},
                {title: '组织机构编码', dataIndex: 'institutionCode', width: "160", sortable: true, align: 'left'},
                {
                    title: '单位级别', dataIndex: 'courtLevel', width: "160", sortable: true, align: 'left',
                    renderer: BUI.Grid.Format.enumRenderer(courtLevelJson)
                },
                {
                    title: '部门性质类别', dataIndex: 'deptType', width: "120", sortable: true, align: 'left',
                    renderer: BUI.Grid.Format.enumRenderer(deptTypeJson)
                },
                {
                    title: '是否人民法庭',
                    dataIndex: 'isPeples',
                    width: "100",
                    sortable: true,
                    align: 'left',
                    renderer: customRender
                },
                {
                    title: '是否院领导',
                    dataIndex: 'isLeaders',
                    width: "100",
                    sortable: true,
                    align: 'left',
                    renderer: customRender
                },
                {
                    title: '考核序列',
                    dataIndex: 'assessSerial',
                    width: "100",
                    sortable: true,
                    align: 'left',
                    renderer: BUI.Grid.Format.enumRenderer(assessSerialJson)
                },
                {
                    title: '启用状态', dataIndex: 'isValid', width: "100", sortable: true, align: 'left',
                    renderer: function (value, obj) {
                        var spanStr = '';
                        if (obj.isValid == 1) {
                            spanStr += '<span class="grid-validY" title="启用">启用</span>';
                        } else {
                            spanStr += '<span class="grid-validN " title="启用">停用</span>';
                        }
                        return spanStr;
                    }
                },
                {
                    title: '操作', width: "100", sortable: false, renderer: function (value, obj) {
                        var spanStr = '';
                        if (obj.isValid == 1) {
                            spanStr += '<span class="grid-valid  hide btn-enabled" title="启用">启用</span>' +
                                '<span class="grid-valid  btn-enabled" title="停用">停用</span>';
                        } else {
                            spanStr += '<span class="grid-valid  btn-enabled" title="启用">启用</span>' +
                                '<span class="grid-valid  btn-enabled  hide" title="停用">停用</span>';
                        }
                        return spanStr;
                    }
                }
            ];
            var store = Search.createStore(userinfoActionUrl, {
                sortField: 'sortNo',
                sortDirection: 'ASC',
                remoteSort: true,
                pageSize: 10,
                proxy: {
                    save: {//也可以是一个字符串，那么增删改，都会往那么路径提交数据，同时附加参数saveType
                        //addUrl: '../data/add.json',
                        //updateUrl: '../data/edit.json',
                        removeUrl: '<%=basePath%>userinfo/delete'
                    }/*,
                     method : 'POST'*/
                },
                params: {},
                autoSync: true //保存数据后，自动更新
            });
            var tbarData = {
                items: [
                    {
                        text: '<i class="icon-plus icon-user"></i>新增部门',
                        btnCls: 'button button-small',
                        handler: function () {
                            initSelect2();
                            deptInsertForm.clearErrors();
                            if (deptJson['department.level'] >= 3) {
                                BUI.Message.Alert("不支持该级部门下新增部门", "warning")
                            } else if (deptJson['department.courtNo'] == null) {
                                BUI.Message.Alert("请在左侧选择法院进行新增部门", "warning")
                            } else {
                                // 加载单位级别
                                // if (deptJson['department.level'] == 0) {
                                //     // 法院级别
                                //     if (deptJson['department.courtText'].indexOf("高级人民法院") != -1) {
                                //         parentId = "1,11,4,41";
                                //     } else if (deptJson['department.courtText'].indexOf("中级人民法院") != -1) {
                                //         parentId = "2,21,4,41";
                                //     } else {
                                //         parentId = "3,4,41";
                                //     }
                                // } else if (deptJson['department.level'] == 1 || deptJson['department.level'] == 2) {
                                //     // 一级部门
                                //     if (deptJson['department.courtText'].indexOf("高级人民法院") != -1) {
                                //         parentId = "12,42";
                                //     } else if (deptJson['department.courtText'].indexOf("中级人民法院") != -1) {
                                //         parentId = "22,42";
                                //     } else {
                                //         parentId = "32,42";
                                //     }
                                // }
                                //加载单位级别
                                if (deptJson['department.level'] == 0) {
                                    // 法院级别
                                    if (deptJson['department.courtText'].indexOf("高级人民法院") != -1) {
                                        parentId = "11";
                                    } else if (deptJson['department.courtText'].indexOf("中级人民法院") != -1) {
                                        parentId = "21";
                                    } else {
                                        parentId = "31";
                                    }
                                } else  {
                                    // 一级部门
                                    if (deptJson['department.courtText'].indexOf("高级人民法院") != -1) {
                                        parentId = "10";
                                    } else if (deptJson['department.courtText'].indexOf("中级人民法院") != -1) {
                                        parentId = "15";
                                    } else {
                                        parentId = "25";
                                    }
                                }

                                dwjb(parentId);
                                V.deptInsertDialog.set("title", "新增部门");
                                V.isEdit = false;
                                V.deptInsertDialog.show();
                            }
                        }
                    },
                    {
                        text: '<i class="icon-edit icon-user"></i>编辑部门',
                        btnCls: 'button button-small',
                        handler: function () {
                            if (deptJson['department.courtNo'] == null) {
                                BUI.Message.Alert("请在左侧选择法院进行新增部门", "warning");
                                return;
                            }
                            var selections = grid.getSelection();
                            var len = selections.length;
                            if (len == 1) {
                                $("#deptName").val(selections[0].deptName);
                                //将select2的容器值设为准确值
                                $("#deptStdName").select2().select2('val', selections[0].orgCode);
                                $("#deptStdName").change();
                                $("#deptStdName").val(selections[0].orgCode);
                                $("#institutionCode").val(selections[0].institutionCode);
                                $("#courtLevel").select2().select2('val', selections[0].courtLevel);
                                $("#courtLevel").change();
                                $("#courtLevel").val(selections[0].courtLevel);
                                $("#deptType").val(selections[0].deptType);
                                $("#isPeples").val(selections[0].isPeples);
                                $("#isLeaders").val(selections[0].isLeaders);
                                $("#assessSerial").val(selections[0].assessSerial);
                                console.log(selections);
                                // 加载单位级别
                               // if (selections[0]["level"] == 1) {
                               //     // 一级部门
                               //      if (court[selections[0]["courtCode"]].indexOf("高级人民法院") != -1) {
                               //          parentId = "11,41";
                               //      } else if (court[selections[0]["courtCode"]].indexOf("中级人民法院") != -1) {
                               //          parentId = "21,41";
                               //      } else {
                               //          parentId = "31,,41";
                               //      }
                               //  } else if (selections[0]["level"] == 2) {
                               //     // 二级部门
                               //      if (court[selections[0]["courtCode"]].indexOf("高级人民法院") != -1) {
                               //          parentId = "12,42";
                               //      } else if (court[selections[0]["courtCode"]].indexOf("中级人民法院") != -1) {
                               //          parentId = "22,42";
                               //      } else {
                               //          parentId = "32,42";
                               //      }
                               //  }
                                //加载单位级别
                                if (deptJson['department.level'] == 0) {
                                    // 法院级别
                                    if (deptJson['department.courtText'].indexOf("高级人民法院") != -1) {
                                        parentId = "11";
                                    } else if (deptJson['department.courtText'].indexOf("中级人民法院") != -1) {
                                        parentId = "21";
                                    } else {
                                        parentId = "31";
                                    }
                                } else  {
                                    // 一级部门
                                    if (deptJson['department.courtText'].indexOf("高级人民法院") != -1) {
                                        parentId = "10";
                                    } else if (deptJson['department.courtText'].indexOf("中级人民法院") != -1) {
                                        parentId = "15";
                                    } else {
                                        parentId = "25";
                                    }
                                }


                                dwjb(parentId, selections[0].courtLevel);

                                V.deptInsertDialog.set("title", "编辑部门");
                                V.isEdit = true;
                                V.deptInsertDialog.show();
                            } else {
                                BUI.Message.Alert('请选择一条数据', 'warning');
                            }
                        }
                    }, {
                        text: '<i class="icon-edit icon-user"></i>调整部门排序',
                        btnCls: 'button button-small',
                        handler: function () {
                            var selections = grid.getSelection();
                            if (deptJson['department.courtNo'] == '' || deptJson['department.courtNo'] == null) {
                                BUI.Message.Alert("请选择法院进行部门排序", "warning");
                                return;
                            }
                            ztreeSettings.async.otherParam['id'] = deptJson['department.courtNo'] + ",null";
                            ztreeSettings.async.otherParam['type'] = "onlyGetDept";
                            ztreeObj = $.fn.zTree.init($("#dept_Name"), ztreeSettings);
                            V.deptSortDialog.show();
                        }
                    },
                    {
                        text: '<i class="icon-check icon-user"></i>批量转移部门内的人员',
                        btnCls: 'button button-small',
                        handler: function () {
                            var selections = grid.getSelection();
                            if (selections.length > 0) {
                                courtcode_current = selections[0].courtCode;
                                courtno_current = selections[0].courtNo;
                                deptno_current = selections[0].deptNo;
                                var condition = {};
                                $.each(selections[0], function (index, value) {
                                    condition[index] = value;
                                });
                                store_forperson.load(condition);
                                dialog_forperson.show();
                            } else {
                                BUI.Message.Alert('请选择一条数据', 'warning');
                            }
                        }
                    }
                ]
            };
            //定义表格样式
            var gridCfg = Search.createGridCfg(columns, {
                //width: 600,
                height: "auto",
                //forceFit: true,
                emptyDataTpl: '<div class="centered"><h2>该法院或部门下没有子部门</h2></div>',
                tbar: tbarData,
                plugins: [BUI.Grid.Plugins.RadioSelection, BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格
            });
            var search = new Search({
                store: store,
                gridCfg: gridCfg
            });
            var grid = search.get('grid');
            //监听事件，删除一条记录
            grid.on('cellclick', function (ev) {
                var sender = $(ev.domTarget); //点击的Dom
                if (sender.hasClass('btn-detail')) {
                    var record = ev.record;
                    open("<%=basePath%>view/detail?id=" + record.id);
                }

                if (sender.hasClass('btn-enabled')) {
                    var record = ev.record;
                    var url = "<%=path%>/deptAction/enableDeptInfo";
                    var i = record.isValid == 1 ? 0 : 1;
                    var datas = {}
                    datas['department.isValid'] = i;
                    datas['department.courtNo'] = deptJson['department.courtNo'];
                    datas['department.deptNo'] = record.deptNo;
                    if (datas['department.courtNo'] == null || datas['department.courtNo'] == 'undefined') {
                        BUI.Message.Alert("请在左侧选择法院", "warning");
                        return;
                    }
                    $.post(url, datas, function (data) {
                        if (data.success) {
                            store.load();
                            treestore.load();
                        } else {
                            var str = "";
                            switch (data.reason) {
                                case 1 :
                                    str = "该部门下有启用的人员信息(在编和编外人员都移除才能停用),禁止未生效";
                                    break;
                            }
                            if (str != "") {
                                if (str == "该部门下有启用的人员信息(在编和编外人员都移除才能停用),禁止未生效") {
                                    BUI.Message.Confirm("该部门下有启用的人员信息,是否将该部门内的人员转移到其他部门", function () {
                                        var selections = grid.getSelection();
                                        if (selections.length > 0) {
                                            courtcode_current = selections[0].courtCode;
                                            courtno_current = selections[0].courtNo;
                                            var condition = {};
                                            $.each(selections[0], function (index, value) {
                                                condition[index] = value;
                                            });
                                            store_forperson.load(condition);
                                            dialog_forperson.show();
                                        } else {
                                            BUI.Message.Alert('请选择一条数据', 'warning');
                                        }
                                    }, 'question');
                                } else {
                                    BUI.Message.Alert(str, "error");
                                }
                            } else {
                                BUI.Message.Alert("停用失败", "error")
                            }

                        }

                    });


                }

            });
            //-------------初始化表格End---------------
            $(".button-one").parent().hide();
            $(".button-two").parent().show();
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
            treestore.load();//加载根节点，也可以让用户点击加载
            var index, firstSort, endSort;
            tree.on('itemclick', function (ev) {
                var item = ev.item;
                index = tree.indexOfItem(item);

                var condition = {'department.courtNo': item.courtNo, 'department.deptNo': item.deptNo};
                condition = $.extend({"start": 0, "pageIndex": 0}, condition);
                store.load(condition);
                console.log(item);
                deptJson['department.courtNo'] = item.courtNo;
                deptJson['department.deptNo'] = item.deptNo;
                deptJson['department.orgCode'] = item.orgCode;
                deptJson['department.sortNo'] = item.sortNo;
                deptJson['department.courtText'] = getCourtText(item);
                //level修正 因为显示的结构问题 如 渝北法院放在一中院的树结构下 传入的level要相应修正一下
                var lv = item.level - 1;
                var pItem;
                if (item.id && item.id.indexOf("null") != -1) {
                    pItem = item;
                } else if (item.parent && item.level != 0) {
                    pItem = localLevel(item);
                }
                if (pItem) {
                    lv = doLevel(pItem, lv);
                }

                deptJson['department.level'] = lv;
                if (item.parent != null) {
                    firstSort = item.parent.children[0].sortNo;
                    endSort = item.parent.children[item.parent.children.length - 1].sortNo;
                }

            });

            function getCourtText(item){
                var text =  item.text;
                if(text.indexOf("人民法院") === -1 && item.parent){
                    return getCourtText(item.parent);
                }else {
                    return text;
                }
            }

            //----------------树结构结束--------------------
            function localLevel(item) {
                var parent = item.parent;
                if (parent && parent.level != 0) {
                    if (parent.id.indexOf("null") != -1) {
                        return parent;
                    } else {
                        return localLevel(parent);
                    }
                }
                return null;
            }

            function doLevel(item, lv) {
                var parent = item.parent;
                if (parent && item.level != 0) {
                    if (parent.id && parent.id.indexOf("null")) {
                        lv = lv - 1;
                    }
                }
                if (parent.parent) {
                    lv = doLevel(parent, lv);
                }
                return lv;
            }

            //----------------部门新增--------------------
            V.deptInsertDialog = new Overlay.Dialog({
                title: "新增部门",
                width: 750,
                contentId: "deptinsertContent",
                buttons: [
                    {
                        text: '保存', elCls: 'button button-primary', handler: function () {
                            deptInsertForm.valid();
                            if (deptInsertForm.isValid()) {
                                if (!V.isEdit) {
                                    var url = "<%=path%>/deptAction/insertDept";
                                    deptJson['department.orgCode'] = $("#deptStdName").val();
                                    deptJson['department.deptName'] = $("#deptName").val();
                                    deptJson['department.institutionCode'] = $("#institutionCode").val();
                                    deptJson['department.courtLevel'] = $("#courtLevel").val();
                                    deptJson['department.deptType'] = $("#deptType").val();
                                    deptJson['department.isPeples'] = $("#isPeples").val();
                                    deptJson['department.isLeaders'] = $("#isLeaders").val();
                                    deptJson['department.assessSerial'] = $("#assessSerial").val();
                                    $.post(url, deptJson, function (data) {
                                            if (data.success) {
                                                store.load();
                                                treestore.load();
                                                V.deptInsertDialog.close();
                                                showTip("保存成功");
                                                // 刷新左边的树
                                                $.fn.zTree.init();
                                            } else {
                                                var str = '';
                                                switch (data.reason) {
                                                    case 1 :
                                                        str = "部门名称重名,无法添加";
                                                        break;
                                                }
                                                if (str != '') {
                                                    BUI.Message.Alert(str, "warning");
                                                } else {
                                                    V.deptInsertDialog.close();
                                                    showTip("保存失败");
                                                }
                                            }
                                        }
                                    )
                                } else {
                                    var url = "<%=path%>/deptAction/updateDeptInfo";
                                    deptJson['department.deptNo'] = grid.getSelected().deptNo;
                                    deptJson['department.orgCode'] = $("#deptStdName").val();
                                    deptJson['department.deptName'] = $("#deptName").val();
                                    deptJson['department.institutionCode'] = $("#institutionCode").val();
                                    deptJson['department.courtLevel'] = $("#courtLevel").val();
                                    deptJson['department.deptType'] = $("#deptType").val();
                                    deptJson['department.isPeples'] = $("#isPeples").val();
                                    deptJson['department.isLeaders'] = $("#isLeaders").val();
                                    deptJson['department.assessSerial'] = $("#assessSerial").val();
                                    // 在编辑的时候,sortNo会传2过来,但实际并未更改.所以这里将排序的这个字段删除,不传参.
                                    delete deptJson['department.sortNo'];
                                    delete deptJson['department.level'];
                                    $.post(url, deptJson, function (data) {
                                            if (data.success) {
                                                store.load();
                                                treestore.load();
                                                V.deptInsertDialog.close();
                                                showTip("编辑成功");
                                                // 刷新左边的树
                                                $.fn.zTree.init();
                                            } else {
                                                V.deptInsertDialog.close();
                                                showTip("编辑失败");
                                            }
                                        }
                                    )

                                }

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
            var deptInsertForm = new Form.HForm({
                srcNode: "#dept_insert"
            }).render();
            V.deptInsertForm = deptInsertForm;
            V.deptInsertDialog.on("closing", function () {
                deptInsertForm.clearFields();
                deptInsertForm.clearErrors();
            });

            //部门排序
            V.deptSortDialog = new Overlay.Dialog({
                title: "修改部门排序",
                width: 500,
                contentId: "deptsortContent",
                buttons: [
                    {
                        text: '提交', elCls: 'button button-primary', handler: function () {
                            var nodes = ztreeSettings.newTools.getSubmitJson();
                            $.post("<%=path%>/deptAction/updateDeptSortNew", {field: JSON.stringify(nodes)}, function (data) {
                                    if (data.success) {
                                        ztreeObj.destroy();
                                        V.deptSortDialog.close();
                                        store.load();
                                        treestore.load();
                                        showTip("修改成功");
                                    } else {
                                        BUI.Message.Alert("修改失败" + (data.msg || ""), "warning");
                                    }
                                }
                            )
                        }
                    }, {
                        text: '返回', elCls: 'button', handler: function () {
                            this.close();
                        }
                    }]
            });
            $("#sortUp").on("click", function () {
                sortEdit(1);
            });
            $("#sortDown").on("click", function () {
                sortEdit(2);
            });

            // 排序
            function sortEdit(num) {
                if (deptJson['department.sortNo'] == '' || deptJson['department.sortNo'] == null) {
                    showTip("请先在左侧选择要调整排序的部门");
                    return;
                }
                BUI.Message.Confirm('确定要调整排序么？', function () {
                    setTimeout(function () {
                        var url = "<%=path%>/deptAction/updateDeptSort";
                        var datas = {};
                        if (num == 1) {
                            if (deptJson['department.sortNo'] == firstSort) {
                                showTip("该部门已经排在第一位了！");
                                return;
                            }
                            datas['startDeptSort'] = deptJson['department.sortNo'];
                            datas['endDeptSort'] = deptJson['department.sortNo'] - 1;
                            index = index - 1;
                        } else {
                            if (deptJson['department.sortNo'] == endSort) {
                                showTip("该部门已经排在最后了！");
                                return;
                            }
                            datas['startDeptSort'] = deptJson['department.sortNo'];
                            datas['endDeptSort'] = deptJson['department.sortNo'] + 1;
                            index = index + 1;
                        }
                        datas['department.courtNo'] = deptJson['department.courtNo'];
                        datas['department.deptNo'] = deptJson['department.deptNo'];
                        datas['department.level'] = deptJson['department.level'];
                        $.post(url, datas, function (data) {
                            if (data.success) {
                                if (data.success) {
                                    store.load();
                                    treestore.load();
                                    showTip("修改成功");
                                } else {
                                    showTip("修改失败");
                                }
                            }
                        })
                    });
                }, 'question');
            }

            $(".bui-grid-body").height($(document).height() - 200);
            $(".bui-grid-bbar").height(35);
            $("#t3").height($(".bui-grid").height());
            datepickerFix($, BUI.Calendar);
            // 新增,增加部门人员列表显示 20190325
            var columns_forperson = [
                    {
                        title: '姓名',
                        dataIndex: 'fullname',
                        width: "70",
                        sortable: true,
                        align: 'left'
                    },
                    {title: '法院', dataIndex: 'courtNoText', width: "160", sortable: true, align: 'left'},
                    {title: '部门', dataIndex: 'departmentText', width: "150", sortable: true, align: 'left'},
                    {title: '性别', dataIndex: 'genderText', width: "40", sortable: true, align: 'left'},
                    {
                        title: '年龄', width: "50", align: 'center', renderer: function (value, obj) {
                            if (obj.birthday != null) {
                                var calcYears2 = calcYears(obj.birthday);
                                if (!isNaN(calcYears2)) {
                                    return calcYears2 + "岁";
                                }
                            }
                        }
                    },
                    {title: '行政职务', dataIndex: 'administrationPositionText', width: "100", sortable: true, align: 'left'},
                    {title: '法律职务', dataIndex: 'lawPositionText', width: "100", sortable: true, align: 'left'},
                    {title: '职级', dataIndex: 'rankText', width: "100", sortable: true, align: 'left'},
                    {title: '学历', dataIndex: 'educationBackgroundText', width: "100", sortable: true, align: 'left'}
                ],
                store_forperson = new Data.Store({
                    url: '<%=basePath%>personOfDept/getPersonList',
                    proxy: {//设置请求相关的参数
                        method: 'post'
                    },
                    autoLoad: false,
                    pageSize: 10  // 配置分页数目
                }),
                grid_forperson = new Grid.Grid({
                    columns: columns_forperson,
                    store: store_forperson,
                    width: 1000,
                    height: 600,
                    // 底部工具栏
                    bbar: {
                        // pagingBar:表明包含分页栏
                        pagingBar: true
                    },
                    plugins: [Grid.Plugins.RowNumber, Grid.Plugins.CheckSelection],	// 插件形式引入多选表格
                    tbar: {
                        items: [
                            {
                                btnCls: 'button button-small',
                                text: '<i class="icon-adjust"></i>根据选择,批量转移部门(翻页会刷新选中)',
                                handler: function () {
                                    var selections_forpersons = grid_forperson.getSelection();
                                    if (selections_forpersons.length > 0) {
                                        persons_changeDept = "";
                                        $.each(selections_forpersons, function (key, value) {
                                            persons_changeDept = persons_changeDept + "," + value.id;
                                        });
                                        // 查询该法院的下属部门
                                        $.post("<%=basePath%>code/deptByCourtNo", {fydm: courtcode_current}, function (data) {
                                            var text = '<option value="">请选择</option>';
                                            $.each(data, function (key, value) {
                                                text += '<option value=' + value.deptNo + '>' + value.deptName + '</option>';
                                            });
                                            $("#new_dept_no").html(text);
                                            $("#new_dept_no").select2();
                                        });
                                        // 对部门内的人员,批量转移部门
                                        dzDialog.show();
                                    } else {
                                        BUI.Message.Alert('请选择至少一条数据', 'warning');
                                    }
                                }
                            },
                            {
                                btnCls: 'button button-small',
                                text: '<i class="icon-hand-up"></i>一键转移该部门内的所有人员',
                                handler: function () {
                                    persons_changeDept = "";
                                    // 获取该部门内的所有人员的数据
                                    $.post("<%=basePath%>personOfDept/getPersonList_all", {courtNo : courtno_current ,courtcode: courtcode_current, deptNo : deptno_current}, function (data) {
                                        if (data.success) {
                                            $.each(data.rows, function (key, value) {
                                                persons_changeDept = persons_changeDept + "," + value.id;
                                            });
                                        }
                                    });
                                    // 查询该法院的下属部门
                                    $.post("<%=basePath%>code/deptByCourtNo", {fydm: courtcode_current}, function (data) {
                                        var text = '<option value="">请选择</option>';
                                        $.each(data, function (key, value) {
                                            text += '<option value=' + value.deptNo + '>' + value.deptName + '</option>';
                                        });
                                        $("#new_dept_no").html(text);
                                        $("#new_dept_no").select2();
                                    });
                                    // 一键转移该部门内的所有人员
                                    dzDialog.show();
                                }
                            }
                        ]
                    },
                }),
                dialog_forperson = new Overlay.Dialog({
                    title: '该部门内的人员列表',
                    width: 1030,
                    height: 400,
                    top: 30,
                    children: [grid_forperson],
                    childContainer: '.bui-stdmod-body',
                    buttons: []
                }),
                // 保存转移后的部门
                dzDialog = new Overlay.Dialog({
                    title: "转移到本院的其它部门",
                    width: 450,
                    contentId: "dz",
                    buttons: [
                        {
                            text: '确定', elCls: 'button button-primary', handler: function () {
                                $("#new_dept_text").attr("value", $("#new_dept_no").find("option:selected").text());
                                if ($("#new_dept_no").find("option:selected").text() == "请选择") {
                                    BUI.Message.Alert("请选择部门！", null, "warning");
                                    return;
                                }
                                $.post('<%=path%>/personOfDept/saveTargetDepart', {
                                    "ids": persons_changeDept,
                                    "courtcode_current": courtcode_current,
                                    "courtno_current": courtno_current,
                                    "new_dept_no": $("#new_dept_no").find("option:selected").val()
                                }, function (data) {
                                    if (data.success) {
                                        BUI.Message.Alert("转移成功！", "success");
                                        store_forperson.load();
                                    } else {
                                        BUI.Message.Alert("转移失败,请联系管理员！", "warning");
                                    }
                                });
                                dzDialog.close();
                            }
                        },
                        {
                            text: '取消', elCls: 'button', handler: function () {
                                dzDialog.close();
                                this.close();
                            }
                        }
                    ],
                    success: function (data) {
                        this.close();
                    }
                });
        });
    }

    function initSelect2() {
        $("select.select2").each(function () {
            var id = $(this).attr("id");
            var firstOptionValue = $(this).find("option:eq(0)").val();
            $("#" + id).select2().select2("val", firstOptionValue);
            $("#" + id).change();
        });
    }

    function customRender(value, obj) {
        if (value == 1) {
            return "是";
        } else {
            return "否";
        }
    }

    // 单位级别
    function dwjb(parentId, courtLevel) {
        // 查询单位级别
        $.getJSON("/ums/code/codeListByType", {typeId: 127, parentId: parentId}, function (data) {
            $("#courtLevel").empty();
            $("#courtLevel").append($("<option>").attr({"value": ''}).text('请选择'));
            $("#courtLevel").select2().select2("val", '');
            for (var i = 0; i < data.length; i++) {
                courtLevelJson[data[i].id] = data[i].codeName;
                $("#courtLevel").append($("<option>").attr({"value": data[i].id}).text(data[i].codeName));
            }
            if (courtLevel == null || courtLevel == undefined) {
                var firstOptionValue = $("#courtLevel").find("option:eq(0)").val();
                $("#courtLevel").select2().select2("val", firstOptionValue);
                $("#courtLevel").change();
            } else {
                // 将编辑前的值赋给下拉框
                $("#courtLevel").select2().select2("val", courtLevel);
                $("#courtLevel").change();
            }
            makeBUI();
            V.deptInsertForm.clearErrors();
        });
    }
</script>
<%--Script End--%>
</html>
