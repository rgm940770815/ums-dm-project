<%--
  Created by IntelliJ IDEA.
  User: baish
  Date: 2018/8/27
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>质量计算配置</title>
    <jsp:include page="/basic_import.jsp"></jsp:include>
    <style type="text/css">
        li.select2-results__option {
            min-height: 25px;
        }

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
            width: 95%;
            box-sizing: border-box;
            height: calc(100vh - 60px);
            display: flex;
            display: -webkit-flex;
            margin-left: auto;
            margin-right: auto;
        }

        .controls input {
            -webkit-box-sizing: content-box;
            -moz-box-sizing: content-box;
            box-sizing: content-box;
            width: 400px;
        }

        .controls select {
            width: 426px;
        }

        .form-horizontal .row-condition {
            border: 1px solid #bcbcbc;
            margin-left: 0;
            padding: 15px 0;
            width: 100%;
            margin-bottom: 15px;
        }

        .form-horizontal .row-condition [class*=span] {
            margin-left: 0;
        }

        .row-condition h4 {
            padding-left: 45px;
            padding-bottom: 10px;
        }

        .bui-stdmod-body {
            overflow: auto;
        }

    </style>
</head>
<body>

<div class="header">
    质量计算配置
</div>
<div class="content">
    <div id="grid">

    </div>

</div>

<div style="display: none" id="edit-form-div">

    <form class="form-horizontal " id="edit-form">

        <div class="row">
            <div class="control-group span14">
                <label class="control-label"><s>*</s>规则名称：</label>

                <div class="controls">
                    <input type="text" placeholder="规则名称" data-rules="{required:true}" class="edit-tag"
                           name="infoSql.ruleTitle">

                </div>

            </div>

            <div class="control-group span14" style="height: 85px">
                <label class="control-label">规则描述：</label>

                <div class="controls">
                    <textarea placeholder="规则描述" style="resize: none;width: 425px;height: 65px;" class="edit-tag"
                              name="infoSql.ruleDescribe"></textarea>

                </div>

            </div>

            <div class="control-group span14" style="height: 115px">
                <label class="control-label"><s>*</s>查询sql：</label>

                <div class="controls">

                    <textarea placeholder="查询sql" style="resize: none;width: 425px;height: 95px;" class="edit-tag" data-rules="{required:true}"
                              name="infoSql.sqlQuery"></textarea>

                </div>
            </div>

            <div class="control-group span14" style="height:115px">
                <label class="control-label"><s>*</s>反查sql：</label>

                <div class="controls">

                    <textarea placeholder="反查sql" style="resize: none;width: 425px;height: 95px;" class="edit-tag" data-rules="{required:true}"
                              name="infoSql.sqlReverse"></textarea>

                </div>
            </div>

        </div>


    </form>

</div>



<script type="text/javascript">

    var dataGrid;
    $(function () {

            initBUI();

    });

    function initBUI() {

        BUI.use(['common/search', 'bui/tree', 'bui/data', 'bui/calendar', 'bui/overlay', 'bui/form', 'bui/uploader', 'bui/mask', 'bui/grid'],
            function (Search, Tree, Data, Calendar, Overlay, Form, Uploader, Mask, Grid) {

                var startRow = 0;
                var columns = [
                    {
                        title: '序号', sortable: false, width: "60", align: 'left', renderer: function (value, obj) {
                            return startRow++;
                        }
                    },
                    {title: '规则名称', dataIndex: 'ruleTitle', width: "", sortable: true, align: 'left'},
                    {title: '创建人', dataIndex: 'creator', width: "", sortable: true, align: 'left'},
                    {title: '创建时间', dataIndex: 'createTime', width: "", sortable: true, align: 'left'},
                    {title: '规则描述', dataIndex: 'ruleDescribe', width: "40%", sortable: true, align: 'left'}


                ];


                var suffer = "<span style='color:red;font-size:12px'>查询人员表请使用ums_user_info_view表 同时加上别名 a</span>";
                //菜单按钮
                var tbarData = {
                    items: [{
                        text: '<i class="icon-edit"></i>新增配置',
                        btnCls: 'button button-small ',
                        handler: function () {

                            editDialog.set("title", "新增配置 " + suffer);
                            editDialog.show();

                        }
                    }, {
                        text: '<i class="icon-edit"></i>编辑配置',
                        btnCls: 'button button-small ',
                        handler: function () {

                            var selected = dataGrid.getSelected();
                            if (selected) {

                                config.id = selected.id;
                                //加载配置
                                var $edit = $("#edit-form");
                                $edit.find(":input.edit-tag[name^='infoSql.']").each(function () {
                                    var $this = $(this);
                                    var name = $this.attr("name");
                                    var split = name.split(".");
                                    $this.val(selected[split[1]]);

                                });
                                editDialog.set("title", "编辑配置 " + suffer);
                                editDialog.show();

                            }else {
                                BUI.Message.Alert('请选择一条有效的数据', "warning");
                            }

                        }
                    }]
                };


                //定义表格样式
                var gridCfg = Search.createGridCfg(columns, {
                    height: "auto",
                    //forceFit: true,
                    emptyDataTpl: '<div class="centered"><h2>查询的数据不存在</h2></div>',
                    tbar: tbarData,
                    plugins: [BUI.Grid.Plugins.RadioSelection, BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格
                });

                //请求数据
                var store = Search.createStore("<%=basePath%>view/selectSql", {
                    remoteSort: false, // 开启异步排序
                    pageSize: 20,
                    params: {}

                });

                //错误信息
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

                dataGrid = search.get('grid');

                //================================= dialog ==================================
                var editDialog = new Overlay.Dialog({
                    title: '新增',
                    width: 600,
                    height: 550,
                    contentId: 'edit-form-div', //配置DOM容器的编号
                    buttons: [
                        {
                            text: '保存', elCls: 'button button-primary', handler: function () {
                                form.valid();
                                if(form.isValid()){
                                    var params = $("#edit-form").serializeArray();
                                    if(config.id){
                                        params.push({
                                            "name": "infoSql.id",
                                            "value" : config.id
                                        })
                                    }

                                    $.post("<%=basePath%>view/insertSql",params,function(datas){

                                        if(datas && datas.success ){

                                            BUI.Message.Alert("保存成功","success");
                                            editDialog.close();

                                        }else{

                                            BUI.Message.Alert("保存失败","warning");

                                        }

                                    });
                                }

                            }
                        },
                        {
                            text: '重置', elCls: 'button', handler: function () {
                                form.clearFields();
                                form.clearErrors();
                            }
                        },
                        {
                            text: '关闭', elCls: 'button', handler: function () {
                                this.close();

                            }
                        }]
                });

                editDialog.on("closing", function () {
                    form.clearFields();
                    form.clearErrors();
                    config.id = "";
                });

                //================================Form=====================================

                var form = new Form.HForm({
                    srcNode: '#edit-form',
                }).render();


            });


    }


    //生成uuid
    function guid() {
        function S4() {
            return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
        }

        return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4());
    }

    var config = {
        id : ""
    }


</script>

</body>
</html>
