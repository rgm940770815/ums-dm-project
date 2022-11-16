<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>改变编制类型的申请</title>
    <link href="<%=basePath%>js/bui/css/bs3/dpl.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>js/bui/css/bs3/bui.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>js/bui/common/main.js" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/bui/bui.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/bui/config.js"></script>
</head>
<body>

<div id="grid"></div>

<div id="content" class="hide">
    <form id="userinfo_gbbzlx_form" class="form-horizontal" action="<%=basePath%>gbbzlxAction/insertGbbzlx_pz" method="post">
        <div class="controls">
            <input id="gbbzlx_changeUUID" name="changeUUID" type="hidden">
            <input id="gbbzlx_state" name="state" type="hidden">
            <input id="gbbzlx_user_id" name="user_id" type="hidden">
            <input id="gbbzlx_clr_time" name="clr_time" type="hidden">
            <textarea id="clr_reason" name="clr_reason" type="text" placeholder="请输入拒绝原因" class="control-text" style="width:420px;height:65px;"></textarea>
        </div>
    </form>
</div>


</body>


<script>
    // 成功数量
    var successCount = 0;
    var ajax_num = 1;
    var dzForm;
    var CourtNo = '';
    var auth = false;
    $(function () {
        // 由于要加法院增修限制 超级用户不受限制  单独提出来
        $.ajax({
            url: "/ums/code/codeListByType",
            dataType: "json",
            asnyc: false,
            data: {typeId: 1},
            success: function (data) {
                if (data.auth) {
                    auth = true;
                } else {
                    CourtNo = data.value;
                }
                successCount++;
            }
        });

    });
    // 定时器：一秒执行一次，满足条件的时候执行下面的
    var clock = setInterval(countDown, 1000);

    function countDown() {
        if (ajax_num == successCount) {
            clearInterval(clock); //清除js定时器
            BUI.use(['common/search', 'bui/overlay', 'bui/form', 'bui/grid', 'bui/data', 'bui/toolbar'], function (Search, Overlay, Form, Grid, Data, Toolbar) {
                var url = "<%=basePath%>gbbzlxAction/getList",
                    columns = [
                        {title: '主键UUID', dataIndex: 'changeuuid', sortable: true, align: 'left', visible: false},
                        {title: '用户id', dataIndex: 'userId', sortable: true, align: 'left', visible: false},
                        {title: '申请内容', dataIndex: 'sqContent', width: '8%', sortable: true, align: 'left'},
                        {title: '姓名', dataIndex: 'userName', width: '5%', sortable: true, align: 'left'},
                        {title: '法院编码', dataIndex: 'courtNo', sortable: true, align: 'left', visible: false},
                        {title: '法院名称', dataIndex: 'courtText', width: '10%', sortable: true, align: 'left'},
                        {title: '部门编码', dataIndex: 'depNo', sortable: true, align: 'left', visible: false},
                        {title: '部门名称', dataIndex: 'depText', width: '10%', sortable: true, align: 'left'},
                        {title: '申请时间', dataIndex: 'sqTime', width: '10%', sortable: true, align: 'left'},
                        {title: '申请原因', dataIndex: 'sq_reason', width: '10%', sortable: true, align: 'left'},
                        {title: '处理人id', dataIndex: 'clrUserId', sortable: true, align: 'left', visible: false},
                        {title: '处理人姓名', dataIndex: 'clrUserName', width: '5%', sortable: true, align: 'left'},
                        {title: '处理人法院编码', dataIndex: 'clrCourtNo', sortable: true, align: 'left', visible: false},
                        {title: '处理人法院', dataIndex: 'clrCourtText', width: '13%', sortable: true, align: 'left'},
                        {title: '处理时间', dataIndex: 'clrTime', width: '10%', sortable: true, align: 'left'},
                        {title: '当前状态', dataIndex: 'state', width: '5%', sortable: true, align: 'left'},
                        {
                            title: '操作', width: '5%', sortable: false, renderer: function (value, obj) {
                                var spanStr = '';
                                if (obj.state != "申请中") {
                                    spanStr += '<span class="grid-valid">已处理</span>';
                                } else {
                                    spanStr += '<span class="grid-command btn-no" id="btn-no">拒绝</span>';
                                    spanStr += '<span class="grid-command btn-yes" id="btn-yes">同意</span>';
                                }
                                return spanStr;
                            },
                        },
                        {title: '处理理由', dataIndex: 'clrReason', width: '10%', sortable: true, align: 'left'}
                    ],
                    store = Search.createStore(url, {
                        params: {
                            courtNo: CourtNo
                        },
                        pageSize: 10,
                        autoLoad: true
                    }),
                    grid = new Grid.Grid({
                        render: '#grid',
                        columns: columns,
                        width: '100%',
                        loadMask: true,
                        store: store,
                        bbar: {
                            // pagingBar:表明包含分页栏
                            pagingBar: true
                        },
                        tbar: {},
                        emptyDataTpl: '<div class="centered"><h2>查询的数据不存在</h2></div>',
                        plugins: [Grid.Plugins.RowNumber]
                    }).render(),
                    tbar = grid.get('tbar'),
                    searchBar = new Toolbar.Bar({
                        elCls: 'pull-right',
                        items: [{
                            content: '<input name="search" id="search"  placeholder="请输入需要查询的姓名" class="control-text search_field" type="text"/>'
                        }, {
                            xclass: 'bar-item-button',
                            btnCls: 'button button-small',
                            id: 'btn-search',
                            text: '<i class="icon-search"></i>搜索',
                            handler: function () {
                                var condition = {"user_name": $("#search").val()};
                                condition = $.extend({"start": 0, "limit": 10}, condition);
                                store.load(condition);
                            }
                        }]
                    });
                tbar.addChild(searchBar);
                var btn = '';
                grid.on('cellclick', function (ev) {
                    var sender = $(ev.domTarget);
                    var record = ev.record;
                    $("#gbbzlx_changeUUID").val(record.changeuuid);
                    $("#gbbzlx_user_id").val(record.userId);
                    $("#gbbzlx_clr_time").val(getNow());
                    if (sender.hasClass('btn-no')) {
                        btn = $("#btn-no").html();
                        $("#gbbzlx_state").val("已拒绝");
                        dzDialog.show();
                    }
                    if (sender.hasClass('btn-yes')) {
                        btn = $("#btn-yes").html();
                        $("#gbbzlx_state").val("已同意");
                        show();
                    }
                });
                var dzDialog = new Overlay.Dialog({
                        title: "请输入拒绝原因",
                        width: 500,
                        height: 180,
                        contentId: 'content',
                        buttons: [
                            {
                                text: '拒绝', elCls: 'button button-primary', handler: function () {
                                    submit();
                                }
                            },
                            {
                                text: '关闭', elCls: 'button', handler: function () {
                                    dform.clearFields();
                                    this.close();
                                }
                            }
                        ],
                        success: function (data) {
                            this.close();
                        }
                    }),
                    dform = new Form.HForm({
                        srcNode: '#userinfo_gbbzlx_form',
                        submitType: 'ajax',
                        callback: function (data) {
                            if (data.results == 1) {
                                BUI.Message.Alert("申请表更新成功，请尽快进入“待处理人员”模块完善该用户信息！", null, "success");
                            } else {
                                BUI.Message.Alert("申请表更新失败!", null, "warning");
                            }
                            store.load();
                            dzDialog.close();
                        }
                    }).render();
                dzForm = dform;

                function show() {
                    BUI.Message.Confirm('确认要' + btn + '么？', function () {
                        submit();
                    });
                }

                function submit() {
                    dzForm.submit();
                }

                // 获取当前时间格式为 2019-07-25 14:10:00
                function getNow() {
                    var myDate = new Date;
                    var year = myDate.getFullYear(); //获取当前年
                    var mon = myDate.getMonth() + 1; //获取当前月
                    var date = myDate.getDate(); //获取当前日
                    var h = myDate.getHours();//获取当前小时数(0-23)
                    var m = myDate.getMinutes();//获取当前分钟数(0-59)
                    var s = myDate.getSeconds();//获取当前秒
                    var nowTime = sjbl(year) + "-" + sjbl(mon) + "-" + sjbl(date) + " " + sjbl(h) + ":" + sjbl(m) + ":" + sjbl(s);
                    return nowTime;
                }

                // 时间补零
                function sjbl(s) {
                    return s < 10 ? '0' + s : s;
                }

            });
        }
    }
</script>
</html>
