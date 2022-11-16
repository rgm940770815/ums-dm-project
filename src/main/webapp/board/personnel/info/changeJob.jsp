<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 16/4/21
  Time: 下午 08:05
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
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
    <form id="userinfo_dz_form" class="form-horizontal" action="<%=basePath%>userinfo/updateChangeJob" method="post" >
        <div class="controls">
                <textarea id="update_reason" name="umsChangeJob.update_reason" type="text" placeholder="请输入拒绝原因"
                       class="control-text" style="width:420px;height:65px;" ></textarea>
            <input id="update_user_id" type="hidden" name="umsChangeJob.update_user_id">
            <input id="changeUUID" type="hidden" name="umsChangeJob.changeUUID">
            <input id="user_id" type="hidden" name="umsChangeJob.user_id">
            <input id="update_user_name" type="hidden" name="umsChangeJob.update_user_name">
            <input id="state" type="hidden" name="umsChangeJob.state">
        </div>
    </form>
</div>
</body>

<script>
    var dzForm;
    BUI.use(['common/search','bui/overlay','bui/form','bui/grid','bui/data','bui/toolbar'],function(Search,Overlay,Form,Grid,Data,Toolbar) {

        var url = "<%=basePath%>userinfo/selectChangeJob";
        var columns = [
//            {title: '用户id', dataIndex: 'user_id', width: 85, sortable: true, align: 'left'},
            {title: '姓名', dataIndex: 'user_name', width: 85, sortable: true, align: 'left'},
            {title: '申请用户', dataIndex: 'apply_user_name', width: 100, sortable: true, align: 'left'},
            {title: '原法院', dataIndex: 'old_court_text', width: 100, sortable: true, align: 'left'},
            {title: '原部门', dataIndex: 'old_dep_text', width: 110, sortable: true, align: 'left'},
            {title: '新法院', dataIndex: 'new_court_text', width: 100, sortable: true, align: 'left'},
            {title: '新部门', dataIndex: 'new_dept_text', width: 110, sortable: true, align: 'left'},
            {title: '申请时间', dataIndex: 'apply_time', width: 150, sortable: true, align: 'left'},
            {title: '处理用户', dataIndex: 'update_user_name', width: 80, sortable: true, align: 'left'},
            {title: '处理理由', dataIndex: 'update_reason', width: 150, sortable: true, align: 'left'},
//            {title: '更新时间', dataIndex: 'update_time', width: 80, sortable: true, align: 'left'},
            {title: '申请状态', dataIndex: 'state', width: 70, sortable: true, align: 'left'},
            {
                title: '操作', width: 120, sortable: false, renderer: function (value, obj) {
                var spanStr = '';
                if (obj.state !="申请中") {
                    spanStr += '<span class="grid-valid">已处理</span>';
                } else {
                    spanStr += '<span class="grid-command btn-no" id="btn-no">拒绝</span>';
                    spanStr += '<span class="grid-command btn-yes" id="btn-yes">同意</span>';
                    spanStr += '<span class="grid-command btn-re"id="btn-re">取消</span>';
                }
                return spanStr;
            },}
        ];

        var store = Search.createStore(url, {
            sortField: 'apply_time',
            sortDirection: 'DESC',
            proxy : {//设置请求相关的参数
                method : 'post',
                //dataType : 'jsonp',    //返回数据的类型
                limit : '20',            //一页多少条记录
                start : '1',             //起始记录
            },
            autoLoad:true,
            pageSize:20
        }),
            grid = new Grid.Grid({
            render:'#grid',
            columns : columns,
            loadMask: true,
            store: store,
                width:"auto",
                height:500,
                bbar:{
                    // pagingBar:表明包含分页栏
                    pagingBar:true},
                tbar:{},
                emptyDataTpl: '<div class="centered"><h2>查询的数据不存在</h2></div>',
                plugins : [Grid.Plugins.RowNumber]
        }).render();

        var tbar = grid.get('tbar'),
                searchBar = new Toolbar.Bar({
                    elCls : 'pull-right',
                    items:[{
                        content : '<input name="search" id="search"  placeholder="请输入需要查询的姓名" class="control-text search_field" type="text"/>'
                    }, {
                        xclass:'bar-item-button',
                        btnCls : 'button button-small',
                        id:'btn-search',
                        text: '<i class="icon-search"></i>搜索',
                        handler:function (){
                            var condition = {"fullname":$("#search").val()};
                            condition = $.extend({"start": 0,"limit":20,pageSize:20}, condition);
                            store.load(condition);
                        }
                    }]
                });

        tbar.addChild(searchBar);

        var btn = '';
        grid.on('cellclick', function (ev) {
            var sender = $(ev.domTarget);
            var record = ev.record;
            $("#changeUUID").val(record.changeUUID);
            $("#user_id").val(record.user_id);
            $("#update_user_id").val("${loginUser.id}");
            $("#update_user_name").val("${loginUser.fullname}");
            if (sender.hasClass('btn-no')) {
                btn = $("#btn-no").html();
                $("#state").val("已拒绝");
                dzDialog.show();
            }
            if (sender.hasClass('btn-yes')) {
                btn = $("#btn-yes").html();
                $("#state").val("已同意");
                show();
            }
            if (sender.hasClass('btn-re')) {
                btn = $("#btn-re").html();
                $("#state").val("已取消");
                show();
            }
        });

        function show () {
            BUI.Message.Confirm('确认要'+btn+'么？',function(){
                submit();
            });
        }

        var dzDialog = new Overlay.Dialog({
            title: "请输入拒绝原因",
            width: 500,
            height:180,
            contentId: 'content',
            buttons: [
                {
                    text: '拒绝', elCls: 'button button-primary', handler: function () {
                    if($("#update_reason").val()==null||$("#update_reason").val()==""){
                        BUI.Message.Alert("请输入拒绝原因！", null, "warning");
                        return;
                    }
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
        });
        var dform = new Form.HForm({
            srcNode: '#userinfo_dz_form',
            submitType: 'ajax',
            callback: function (data) {
                if (data.job) {
                    BUI.Message.Alert("申请表更新成功，该用户暂时停用，请尽快进入待处理调职人员信息模块完善该用户信息！", null, "success");
                } else {
                    BUI.Message.Alert("申请表更新失败!", null, "warning");
                }
                store.load();
                dzDialog.close();
            }
        }).render();

        dzForm = dform;

        function submit(){
            dzForm.submit();
        }
    });
</script>
</html>
