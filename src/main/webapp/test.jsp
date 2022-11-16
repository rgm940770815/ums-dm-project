<%--
    Document   : test
    Created on : 2015-1-27, 9:47:58
    Author     : Diluka
--%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="/basic_import.jsp"></jsp:include>
    </head>
    <body>
        <div class="search-grid-container">
            <div id="grid"></div>
        </div>
        <script>
            BUI.use(['common/search', 'bui/tree', 'bui/data', 'bui/calendar', 'bui/overlay', 'bui/form', 'bui/uploader'], function (Search, Tree, Data, Calendar, Overlay, Form, Uploader) {
                var columns = [
                    {title: '序号', dataIndex: 'sortNo', width: "50", sortable: true, align: 'left'},
                    {title: '姓名', dataIndex: 'fullname', width: "100", sortable: true, align: 'left'},
                    {title: '性别', dataIndex: 'genderText', width: "50", sortable: true, align: 'left'},
                    {title: '单位', dataIndex: 'company', width: "200", sortable: true, align: 'left'},
                    {title: '职务', dataIndex: 'companyJob', width: "150", sortable: true, align: 'left'},
                    {title: '学历', dataIndex: 'educationBackgroundText', width: "100", sortable: true, align: 'left'},
                    {title: '职级', dataIndex: 'rankText', width: "100", sortable: true, align: 'left'},
                    {
                        title: '操作', width: "100", sortable: false, renderer: function (value, obj) {
                            return '<span class="grid-command btn-detail" title="显示人员详情信息">查看详情</span>';
                        }
                    }
                ];

                var store = Search.createStore("./juror/search", {
                    sortField: 'sortNo',
                    sortDirection: 'ASC',
                    remoteSort: true,
                    pageSize: 20,
                    proxy: {
                        save: {//也可以是一个字符串，那么增删改，都会往那么路径提交数据，同时附加参数saveType
                            //addUrl: '../data/add.json',
                            //updateUrl: '../data/edit.json',
                            //removeUrl: './userinfo/delete'
                        }/*,
                         method : 'POST'*/
                    },
                    autoSync: true //保存数据后，自动更新
                });

                //定义表格样式
                var gridCfg = Search.createGridCfg(columns, {
                    //width: 600,
                    height: "auto",
                    //forceFit: true,
                    emptyDataTpl: '<div class="centered"><h2>查询的数据不存在</h2></div>',
                    tbar: {
                        items: [
                            {
                                text: '<i class="icon-search"></i>搜索',
                                btnCls: 'button button-small',
                                handler: function () {
                                }
                            },
                            {
                                text: '<i class="icon-plus"></i>添加',
                                btnCls: 'button button-small',
                                handler: function () {
                                }
                            },
                            {
                                text: '<i class="icon-edit"></i>编辑',
                                btnCls: 'button button-small',
                                handler: function () {
                                }
                            },
                            {
                                text: '<i class="icon-remove"></i>删除',
                                btnCls: 'button button-small',
                                handler: function () {
                                    var userinfo = grid.getSelected();
                                    if (userinfo) {
                                        BUI.Message.Confirm(
                                                '确定要删除“' + userinfo.fullname + '”吗？',
                                                function () {
                                                },
                                                'question');
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
                    gridCfg: gridCfg
                });
                var grid = search.get('grid');
            });



        </script>
    </body>
</html>
