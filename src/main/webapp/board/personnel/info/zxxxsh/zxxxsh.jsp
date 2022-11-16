<%--zxxxsh：子项信息审核--%>
<%@ page import="cn.net.withub.ums.common.UmsConstant" %>
<%@ page import="cn.net.withub.ums.entity.UmsUserInfoView" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    // 当前登录的人员账户信息
    UmsUserInfoView umsUserInfoView = (UmsUserInfoView) session.getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
    String fullname = umsUserInfoView.getFullname();
    Integer courtNo = umsUserInfoView.getCourtNo();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.8.1.min.js"></script>

    <link rel="stylesheet" href="<%=basePath%>/common/layui/css/layui.css" media="all">

    <!--引入ztree-->
    <link type="text/css" rel="stylesheet" href="<%=basePath%>/common/zTree/zTreeStyle/zTreeStyle.css">
    <script type="text/javascript" src="<%=basePath%>/common/zTree/jquery.ztree.all.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/common/zTree/jquery.ztree.exhide.min.js"></script>

    <style type="text/css">
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
    </style>
</head>
<body>

<div class="main_div">

    <div class="top_div">
        <span class="layui-breadcrumb">
            <a><cite>信息管理</cite></a>
            <a><cite>子项信息审核</cite></a>
        </span>
    </div>

    <div class="content_div">
        <div class="left-container base_container">
            <h2 class="xiaobiaoti">法院</h2>
            <div class="ztree" id="ztree-div"></div>
        </div>
        <div class="right-container">
            <h2 class="xiaobiaoti">子项信息审核列表</h2>

            <form class="layui-form searchDiv" action="" id="searchDiv_id">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label" style="width: 100px;">被操作人姓名</label>
                        <div class="layui-input-inline">
                            <input type="text" name="info.operatedUserName" placeholder="可输入被操作人姓名" autocomplete="off" class="layui-input searchArea">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">子项类型</label>
                        <div class="layui-input-inline">
                            <select name="info.contentType" lay-verify="" lay-search class="searchArea">
                                <option value="">可选择子项类型</option>
                                <option value="UmsLevelInfo">法官等级信息</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">审核状态</label>
                        <div class="layui-input-inline">
                            <select name="info.auditStatus" lay-verify="" lay-search class="searchArea"></select>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label" style="width: 100px;">操作类型</label>
                        <div class="layui-input-inline">
                            <select name="info.examineTypeText" lay-verify="" lay-search class="searchArea">
                                <option value="">可选择操作类型</option>
                                <option value="新增">新增</option>
                                <option value="修改">修改</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label" style="width: 100px;">审核类型</label>
                        <div class="layui-input-inline">
                            <select name="info.shlx" lay-verify="" lay-search class="searchArea">
                                <option value="">可选择审核类型</option>
                                <option value="申请入额">申请入额</option>
                                <option value="申请退额">申请退额</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label" style="width: 100px;">创建时间范围</label>
                        <div class="layui-input-inline">
                            <input type="text" name="info.createTimeStart" class="layui-input searchArea" id="sjfw_start" placeholder="创建时间开始">
                        </div>
                        <div class="layui-input-inline">
                            <input type="text" name="info.createTimeEnd" class="layui-input searchArea" id="sjfw_end" placeholder="创建时间结束">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <div class="layui-input-block">
                            <%--                            <button class="layui-btn" data-type="reload">搜索</button>--%>
                            <button class="layui-btn" lay-submit="" lay-filter="reload">搜索</button>
                            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                        </div>
                    </div>
                </div>
            </form>

            <%--工具栏模板：--%>
            <script type="text/html" id="toolbarDemo">
                <div class="layui-btn-container">
                    <button class="layui-btn layui-btn-sm" lay-event="plsp">批量审批</button>
                    <button class="layui-btn layui-btn-sm" lay-event="cksqnr">审批</button>
                    <button class="layui-btn layui-btn-sm" lay-event="refreshList">刷新列表</button>
                </div>
            </script>
            <%--            shlb：审核列表--%>
            <table id="shlb" lay-filter="lay_shlb" class="layui-table layui-hide" lay-even lay-skin="line" lay-size="lg"></table>
        </div>
    </div>
</div>


<script src="<%=basePath%>/common/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->

<script type="text/javascript">
    // zxxxsh：子项信息审核
    layui.use(['jquery', 'layer', 'table', 'laydate', 'element', 'form'], function () {

        // 当前选中的table行数
        var current_index;
        var courtInfo = {};
        var deptInfo = {};
        // 审核状态_审批意见：1:"审核通过", 2:"审核未通过"
        var auditStatus_spyj;
        var condition = {};
        // 加上法院
        if ("<%=umsUserInfoView.getAuthType()%>" == "COURT" || "<%=umsUserInfoView.getAuthType()%>" == "ADMIN") {
            condition['info.operatedUserCourtNo'] = courtInfo['courtNo'];
        } else {
            condition['info.operatedUserCourtNo'] = "<%=courtNo%>";
        }

        // 加载法院树
        loadCourtTree();

        // 加载右侧列表
        var table = layui.table,
            laydate = layui.laydate,
            layer = layui.layer,
            element = layui.element, //导航的hover效果、二级菜单等功能，需要依赖element模块
            form = layui.form;

        // 获取审核状态下拉
        $.ajax({
            type: "post",
            data: {},
            url: '<%=basePath%>subitem/auditStatus', // 保存审核意见
            async: false, // 异步
            success: function (data) {
                if (data) {
                    var html = '<option value="">可选择审核状态</option>';
                    $.each(data, function (key, value) {
                        html += '<option value="' + key + '">' + value + '</option>';
                    });

                }
                $("[name='info.auditStatus']").append(html);
                form.render();
            }
        });

        // 表格高度根据页面来
        var tableHeight = $(".right-container").height() - $(".xiaobiaoti").height() - $(".searchDiv").height() - 30;
        var tableIns = table.render({
            // shlb：审核列表
            elem: '#shlb',
            height: tableHeight,
            url: '<%=basePath%>/subitem/selectSubItemInfoForLayUI', // 获取到基本的信息
            page: true, // 开启分页
            where: condition['info.operatedUserCourtNo'],
            id: 'shlb_cz', // shlb_cz：审核列表_重载
            cols: [
                [
                    {type: 'numbers'},
                    {type: 'checkbox'},
                    {field: 'id', title: '主键', hide: true},
                    {
                        field: 'contentTypeText', title: '内容类型', sort: true, event: 'cksqnr', width: 135, templet: function (value) {
                            return '<span class="nrlx">' + value.contentTypeText + '</span>'
                        }
                    },
                    {field: 'examineTypeText', title: '操作类型', width: 135},
                    {
                        field: 'shlx', title: '审核类型', width: 135, templet: function (value) {
                            var shlx = value.shlx;
                            switch (shlx) {
                                case "sqre":
                                    return "申请入额";
                                    break;
                                case "sqte":
                                    return "申请退额";
                                    break;
                                default:
                                    return shlx == null ? "" : shlx;
                                    break;
                            }
                        }
                    },
                    {field: 'serializeContent', title: '序列化内容', width: 177, hide: true},
                    {field: 'showContent', title: '展示内容', hide: true},
                    {field: 'changeContent', title: '改变内容', hide: true},
                    {field: 'changeContentDescribe', title: '改变内容描述', hide: true},
                    {field: 'operatedUserId', title: '被操作人id', hide: true},
                    {field: 'operatedUserName', title: '被操作人姓名', width: 135},
                    {field: 'operatedUserCourtText', title: '被操作人法院', width: 180},
                    {field: 'createTime', title: '创建时间', sort: true, width: 135},
                    {field: 'creatorId', title: '创建人id', hide: true},
                    {field: 'creatorName', title: '创建人名称', sort: true, width: 135},
                    {field: 'auditorId', title: '审核人id', hide: true},
                    {field: 'auditorName', title: '审核人名称', sort: true, width: 135},
                    {field: 'auditTime', title: '审核时间', sort: true, width: 135},
                    {
                        field: 'auditStatus', title: '审核状态', sort: true, width: 150, templet: function (value) {
                            var auditStatusText;
                            switch (value.auditStatus) {
                                case 0:
                                    auditStatusText = "未审核";
                                    break;
                                case 1:
                                    auditStatusText = "审核通过";
                                    break;
                                case 2:
                                    auditStatusText = "审核未通过";
                                    break;
                                case 3:
                                    auditStatusText = "通过更新数据成功";
                                    break;
                                case 4:
                                    auditStatusText = "通过更新数据失败";
                                    break;
                                default:
                                    auditStatusText = "数据出错";
                                    break;

                            }
                            return auditStatusText;
                        }
                    },
                    {field: 'auditOpinions', title: '审核意见', sort: true, width: 135}
                ]
            ],
            toolbar: '#toolbarDemo' // 表头工具栏
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#sjfw_start', //sjfw：时间范围
            type: 'datetime'
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#sjfw_end', //sjfw：时间范围
            type: 'datetime'
        });

        // 监听审核button事件
        table.on('toolbar(lay_shlb)', function (obj) {
            // 监听审核列表的方法jtshlb：监听审核列表
            jtshlb("radioCell", obj);
        });

        //监听单元格事件
        table.on('tool(lay_shlb)', function (obj) {

            // 这里是为了移除已经选中的对号
            var length = $("[data-field='1'] .layui-icon.layui-icon-ok").length;
            for (var i = 0; i < length; i++) {
                $("[data-index='" + i + "'] [data-field='1'] .layui-icon.layui-icon-ok").parent().removeClass("layui-form-checked");
            }

            // 取消上面的全选
            if ($('[lay-filter="layTableAllChoose"]').next().hasClass("layui-form-checked")) {
                $('[lay-filter="layTableAllChoose"]').next().removeClass("layui-form-checked");
            }

            // 选中单选框
            current_index = obj.tr[0].rowIndex;
            $("[data-index='" + current_index + "'] [data-field='1'] .layui-icon.layui-icon-ok").parent().addClass("layui-form-checked");
            // 监听审核列表的方法jtshlb：监听审核列表
            jtshlb("nrlxCell", obj);
        });

        form.on('submit(reload)', function (data) {
            // 获取参数
            $(".searchDiv .searchArea").each(function () {
                condition[$(this).attr("name")] = $(this).val();
            });
            var tempCondition = {};
            $.each(condition, function (key, value) {
                if (value == "" || value == undefined) {

                } else {
                    tempCondition[key] = value;
                }
            });
            condition = tempCondition;
            condition = $.extend(condition, {'info.operatedUserCourtNo': condition['courtNo']});
            //执行重载
            tableIns.reload({
                where: condition,
                page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
            return false;
        });

        // 监听审核列表的方法jtshlb：监听审核列表
        function jtshlb(clickName, obj) {
            var data;
            var checkStatus;
            if (clickName == "radioCell") {
                checkStatus = table.checkStatus(obj.config.id);
                data = checkStatus.data[0];
            } else if (clickName == "nrlxCell") {
                checkStatus = table.checkStatus(obj.data.id);
                data = obj.data;
            }
            switch (obj.event) {
                case 'cksqnr':
                    if (data == undefined) {
                        layer.alert('请选择一条数据');
                    } else if (checkStatus.data.length > 1) {
                        layer.alert('这里只能选择一条数据进行审批');
                    } else {
                        var showContent = data.showContent;
                        var changeContentDescribe = data.changeContentDescribe;
                        var examineTypeText = data.examineTypeText;
                        var id = data.id; // 该条数据的主键
                        var auditStatus = data.auditStatus; // 该条数据的审批状态
                        var btn = [];
                        if (auditStatus != 1 && auditStatus != 2 && auditStatus != 3) {
                            btn = ['同意', '不同意'];
                        } else {
                        }
                        var title;
                        switch (auditStatus) {
                            case 0:
                                title = "申请内容-未审核";
                                break;
                            case 1:
                                title = "申请内容-审核通过";
                                break;
                            case 2:
                                title = "申请内容-审核未通过";
                                break;
                            case 3:
                                title = "申请内容-通过更新数据成功";
                                break;
                            case 4:
                                title = "申请内容-通过更新数据失败";
                                break;
                            default:
                                title = "申请内容-数据出错";
                                break;

                        }
                        var layerheight = '0px';
                        if (examineTypeText == "新增") {
                            layerheight = '730px';
                        } else if (examineTypeText == "修改") {
                            layerheight = '1390px';
                        }
                        layer.open({
                            type: 2,
                            content: 'zxxxsh_nr.jsp',
                            title: title,
                            area: [layerheight, '530px'],
                            btn: btn,
                            success: function (layero, index) {
                                var body = layer.getChildFrame('body', index);
                                var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                                iframeWin.showContent = showContent;
                                iframeWin.changeContentDescribe = changeContentDescribe;
                                iframeWin.examineTypeText = examineTypeText;
                            },
                            yes: function (index, layero) {
                                // 同意按钮
                                auditStatus_spyj = 1;
                                srly(id, auditStatus_spyj);// 输入理由
                                return false;
                            },
                            btn2: function (index, layero) {
                                // 不同意按钮
                                auditStatus_spyj = 2;
                                srly(id, auditStatus_spyj);// 输入理由
                                return false;
                            }
                        });
                        break;
                    }
                case 'refreshList':
                    // 刷新列表
                    tableIns.reload();
                    break;
                case 'plsp':
                    // 批量审批
                    data = checkStatus.data;
                    if (data.length == 0) {
                        layer.alert('请选择至少一条数据');
                    } else {
                        var ids = "";
                        for (var i = 0; i < data.length; i++) {
                            var da = data[i].id;
                            var auditStatus = data[i].auditStatus; // 该条数据的审批状态
                            if (auditStatus != 1 && auditStatus != 2 && auditStatus != 3) {

                            } else {
                                layer.alert('请筛选未审核的信息进行批量审核');
                                return;
                            }
                            if (i != (data.length - 1)) {
                                ids += da + ",";
                            } else {
                                ids += da + "";
                            }
                        }
                        layer.prompt({
                            formType: 2,
                            title: "审批理由",
                            btn: ['同意', '不同意'],
                            value: '',
                            success: function (index, layero) {
                            },
                            yes: function (index, layero) {
                                var value = layero.find('.layui-layer-input').val();
                                // 同意按钮
                                auditStatus_spyj = 1;
                                // 调用后台方法，将理由写入数据库
                                bcly(ids, auditStatus_spyj, value);
                                return false;
                            },
                            btn2: function (index, layero) {
                                // 不同意按钮
                                var value = layero.find('.layui-layer-input').val();
                                auditStatus_spyj = 2;
                                // 调用后台方法，将理由写入数据库
                                bcly(ids, auditStatus_spyj, value);
                                return false;
                            }
                        });

                    }
                    break;
                default:
                    break;
            }
        }

        // 输入理由的方法
        function srly(id, auditStatus) {
            layer.prompt({
                btn: ['输入理由', '不输入理由'],
                formType: 2,
                value: '',
                title: '审批理由',
                yes: function (index, layero) {
                    // 输入理由按钮
                    var value = layero.find('.layui-layer-input').val();
                    if (value === '') {
                        layer.alert("请输入理由");
                    } else if (value.length > 500) {
                        layer.alert("最多输入500个字符");
                    } else {
                        // 调用后台方法，将理由写入数据库
                        bcly(id, auditStatus, value);
                    }
                    return false;
                },
                btn2: function (index, layero) {
                    // 不输入理由按钮
                    // 调用后台方法，将理由写入数据库
                    // var value = layero.find('.layui-layer-input').val();
                    var value = '无';
                    bcly(id, auditStatus, value);
                    return false;
                }
            });
        }

        // 将理由写入数据库的方法bcly：保存理由
        function bcly(id, auditStatus, auditOpinions) {
            $.ajax({
                type: "post",
                data: {
                    'info.id': id,
                    'info.auditStatus': auditStatus,
                    'info.auditOpinions': auditOpinions
                }, // audit_opinions: 审核意见
                url: '<%=basePath%>subitem/updateAuditInfo', // 保存审核意见
                async: false, // 异步
                success: function (data) {
                    layer.closeAll(); //疯狂模式，关闭所有层
                    tableIns.reload();
                    layer.open({
                        title: '提示',
                        content: '已完成审批',
                        offset: 'rb',
                        shade: 0,
                        closeBtn: 0,
                        btn: [],
                        anim: 2,
                        icon: 6,
                        time: 3000
                    });
                }
            });
        }

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
                    // 在返回的数据中加入 全部
                    if ("<%=umsUserInfoView.getAuthType()%>" == "ADMIN") {
                        var baseNode = {};
                        baseNode['id'] = "0";
                        baseNode['text'] = "全部";
                        baseNode['open'] = true;
                        re.push(baseNode);
                        condition['courtNo'] = '';
                    } else {

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

        // 处理点击法院事件 同时生成右侧的数据
        function handleClickCourtTree(treeNode) {
            if ("<%=umsUserInfoView.getAuthType()%>" == "COURT" || "<%=umsUserInfoView.getAuthType()%>" == "ADMIN") {
                condition['courtNo'] = treeNode['courtNo'];
            } else {
                condition['courtNo'] = "<%=courtNo%>";
            }
            tableIns.reload({
                where: {'info.operatedUserCourtNo': condition['courtNo'], 'info.auditStatus': 0}
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
    });
</script>

</body>

</html>