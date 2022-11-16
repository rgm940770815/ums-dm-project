<%--
    Document   : log2
    Created on : 2015-5-19, 10:35:52
    Author     : Diluka
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <script>
            $(function () {
                $("select.code").each(function () {
                    loadOptions(this, $(this).attr("typeid"), true);
                });
            });
            function loadOptions(select, typeId, flag) {
                $.getJSON("<%=basePath%>code/codeListByType", {typeId: typeId}, function (data) {
                    $(select).empty();
                    if (flag) {
                        $(select).html('<option value="">全部</option>');
                    }
                    for (var i = 0; i < data.length; i++) {
                        $(select).append('<option value="' + data[i].id + '">' + data[i].codeName + '</option>');
                    }
                });
            }
        </script>
    </head>
    <body>

        <div class="search-grid-container">
            <div id="grid"></div>
        </div>

    </body>

    <script>

        BUI.use('common/search', function (Search) {

            var url = "<%=basePath%>log/search";

            var editing = new BUI.Grid.Plugins.DialogEditing({
                contentId: 'content', //设置隐藏的Dialog内容
                autoSave: true, //添加数据或者修改数据时，自动保存
                triggerCls: 'btn-edit'
            });
            var columns = [
                //{title: '序号', dataIndex: 'sortNo', width: 50, sortable: true, align: 'left'},
                {title: '操作时间', dataIndex: 'opTime', width: 150, sortable: true, align: 'left'},
                {title: '用户ID', dataIndex: 'userId', width: 300, sortable: true, align: 'left'},
                {title: '操作用户', dataIndex: 'opUser', width: 70, sortable: true, align: 'left'},
                {title: '操作类型', dataIndex: 'opTypeText', width: 70, sortable: true, align: 'left'},
                {title: '操作内容', dataIndex: 'opContent', width: "80%", align: 'left'},
                {title: '操作IP', dataIndex: 'opIp', sortable: true, align: 'left'}
            ];
            var store = Search.createStore(url, {
                sortField: 'opTime',
                sortDirection: 'DESC',
                remoteSort: true,
                proxy: {
                    save: {//也可以是一个字符串，那么增删改，都会往那么路径提交数据，同时附加参数saveType
                        addUrl: '../data/add.json',
                        updateUrl: '../data/edit.json',
                        removeUrl: '../data/del.php'
                    }/*,
                     method : 'POST'*/
                },
                params: {
                    isLoginLog: true
                },
                autoSync: true //保存数据后，自动更新
            });
            var gridCfg = Search.createGridCfg(columns, {
                width: "100%",
                height: 500,
                plugins: [editing, /*BUI.Grid.Plugins.RadioSelection,*/ BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格
            });
            var search = new Search({
                store: store,
                gridCfg: gridCfg
            });
            var grid = search.get('grid');


            $("#btnSearch").click(function () {
                var store = search.get('store');

                var condition = {};

                $(".search_field").each(function () {
                    if ($(this).val().length > 0) {
                        condition[$(this).attr("name")] = $(this).val();
                    }
                });

                store.load(condition);
            });
        });
    </script>
</html>
