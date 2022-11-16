<%--
  Created by IntelliJ IDEA.
  User: baish
  Date: 2019/8/22
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>审核信息</title>
<%--    <script src="<%=basePath%>js/jquery/jquery.js" type="text/javascript"></script>--%>
<%--    <link href="<%=basePath%>js/bui/css/bs3/bui.css" rel="stylesheet" type="text/css"/>--%>
<%--    <link href="<%=basePath%>js/bui/css/bs3/dpl.css" rel="stylesheet" type="text/css"/>--%>
<%--    <script src="<%=basePath%>js/bui/bui.js" type="text/javascript"></script>--%>
<%--    <script src="<%=basePath%>js/bui/config.js" type="text/javascript"></script>--%>
<%--    <script src="<%=path%>/js/fix/bui-fix.js"></script>--%>
<%--    <link href="<%=path%>/js/fix/bui-fix.css" rel="stylesheet">--%>
    <script src="<%=basePath%>js/vue.min.js" type="text/javascript"></script>
    <style type="text/css">
        input {
            box-sizing: content-box;
        }

        .search-grid-container {
            /*margin: 20px;*/
        }

        textarea {
            overflow: auto;
            resize: none;
            outline: none;
        }

        #data-info textarea {
            background: #fafafa;
            width: 496px;
            font-size: 14px;
            height: 100px;
            line-height: 24px;
            padding: 10px 20px;
            margin: 10px 5px 10px 80px;
        }

        .bui-stdmod-body {
            overflow-y: auto;
        }

        .icon-info-sign ,.icon-remove{
            margin-top: -1px!important;
        }
    </style>
</head>
<body>

<div class="search-grid-container hide" id="dialog-s-1">
    <div id="grid-subitem"></div>
</div>

<div id="dialog-s-2" class="hide">

    <form id="data-info" class="bui-form-horizontal bui-form bui-form-field-container form-horizontal">
        <div class="row">

            <div v-for="(value, key) in dataInfo" class="control-group span8">
                <label class="control-label">{{key}}：</label>

                <div class="controls">
                    <input type="text" class="field" v-bind:value="value" readonly/>
                </div>
            </div>

        </div>

        <div class="row">
            <div class="control-group span8">
                <label class="control-label">审核意见：</label>

                <div class="controls">
                    <textarea readonly id="shyj">

                    </textarea>
                </div>
            </div>
        </div>
    </form>

</div>


<script type="text/javascript">
    var userId = '${param.userId}';

    //弹出层默认宽度
    var dialogWidth_subitem = 700;
    //弹出层默认高度
    var dialogHeight_subitem = 420;
    var startRow_subitem = 0;

    var auditSrotre ;
    $(function () {

        BUI.use(["common/search", 'bui/overlay', 'bui/form', 'bui/calendar'], function (Search, Overlay, Form, Calendar) {


            var status = {};
            $.ajax({
                url: "<%=basePath%>subitem/auditStatus",
                type: "post",
                dataType: "json",
                async: false,
                success: function (res) {
                    status = res;
                }
            });

            //查询状态
            var params = {
                'info.operatedUserId': userId
            };

            var vueData;
            var columns = [{
                title: '序号',
                width: '5%',
                renderer: function (value, obj) {
                    return startRow_subitem++;
                }
            }, {
                title: '被操作人',
                dataIndex: 'operatedUserName',
                width: '12%'
            }, {
                title: '申请日期',
                dataIndex: 'createTime',
                width: '10%'
            }, {
                title: '操作类型',
                dataIndex: 'examineTypeText',
                width: '10%'
            },{
                title: '审核类型',
                dataIndex: 'shlx',
                width: '10%', renderer: function (key, value) {
                    var shlx = value.shlx;
                    switch (shlx) {
                        case "sqre":
                            return "申请入额";
                            break;
                        case "sqte":
                            return "申请退额";
                            break;
                    }
                }
            }, {
                title: '审核状态',
                dataIndex: 'auditStatus',
                width: '10%',
                renderer: BUI.Grid.Format.enumRenderer(status)
            },
                {
                    title: '审核日期',
                    dataIndex: 'auditTime',
                    width: '10%'
                },
                {
                    title: '审核意见',
                    dataIndex: 'auditOpinions',
                    width: '15%'
                },
                {
                    title: '操作',
                    width: '13%', renderer: function (value, obj) {
                        var res = '<span class="grid-command btn-detail" title="显示审核详细信息">审核信息</span>';
                        if (obj.auditStatus === 0) {
                            res += '<span class="grid-command btn-delete" title="更新到基本信息">撤销申请</span>';
                        }
                        return res;
                    }
                }
            ];

            var store = Search.createStore('<%=basePath%>subitem/selectSubItemInfoForBUI', {
                params: params,
                remoteSort: false,
                pageSize: 5,
            });
            auditSrotre = store;
            //定义表格样式
            var gridCfg = Search.createGridCfg(columns, {
                height: "auto",
                tbar: {
                    items: [
                        {
                            text: '<i class="icon-info-sign"></i>审核信息',
                            btnCls: 'button button-small',
                            handler: function () {
                                var record = grid.getSelected();
                                if (record) {
                                    d1(record);
                                } else {
                                    BUI.Message.Alert("请选择一条记录", null, "info");
                                }

                            }
                        },
                        {
                            text: '<i class="icon-remove"></i>撤销申请',
                            btnCls: 'button button-small',
                            handler: function () {
                                var record = grid.getSelected();
                                if (record) {
                                    d2(record);
                                } else {
                                    BUI.Message.Alert("请选择一条记录", null, "info");
                                }

                            }
                        }
                    ]
                },
                plugins: [BUI.Grid.Plugins.RadioSelection, BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格
            });

            var search = new Search({
                store: store,
                gridCfg: gridCfg,
                gridId: 'grid-subitem'
            });

            var dialog = new Overlay.Dialog({
                width: dialogWidth_subitem,
                height: 'auto',
                contentId: "dialog-s-2",
                buttons: [
                    {
                        text: '关闭', elCls: 'button', handler: function () {
                            this.close();
                        }
                    }],
                success: function () {
                    this.close();
                }
            });

            var grid = search.get('grid');

            store.on("beforeprocessload", function () {
                startRow_subitem = store.get("start") + 1;
            });

            grid.on('cellclick', function (ev) {

                var sender = $(ev.domTarget); //点击的Dom
                var record = ev.record;

                if (sender.hasClass('btn-detail')) {
                    d1(record);
                }
                if (sender.hasClass("btn-delete")) {
                    d2(record);
                }
            });

            function d1(record) {
                var describe = record['showContent'];
                var json = jQuery.parseJSON(describe);
                setVueData(json);
                //审核意见
                $("#shyj").val(record['auditOpinions']);

                dialog.set("title", "审核信息");
                dialog.show();
                $(".bui-stdmod-body")[0].scrollTop = 0;
            }

            function d2(record) {

                if(record.auditStatus !== 0){
                    BUI.Message.Alert("只能撤销未审核的申请记录", null, "warning");
                    return;
                }


                BUI.Message.Confirm('确认要撤销申请吗？', function () {
                    $.ajax({
                        url: "<%=basePath%>subitem/deleteAuditInfo",
                        type: "post",
                        dataType: "json",
                        data: {
                            "info.id": record.id
                        },
                        success: function (res) {
                            if (res) {
                                BUI.Message.Alert("删除成功", null, "info");
                                // 重新加载数据
                                store.load();
                            } else {
                                BUI.Message.Alert("删除失败", null, "warning");
                            }
                        }
                    });

                }, 'question');

            }

            function setVueData(data) {

                if (!vueData) {
                    vueData = new Vue({
                        el: '#data-info',
                        data: {
                            dataInfo: data
                        },
                        methods: {}
                    });
                } else {
                    vueData.dataInfo = data;
                }

            }

        });

    });

    /*
           *审核信息
           */
    function auditInfo(){
        $("#dialog_audit").prop("src","<%=basePath%>board/personnel/info/userinfo/subitem_audit_user.jsp?userId="+userId);
        //要刷新
        auditSrotre.load();
        dialog2.show();
    }

</script>

</body>
</html>
