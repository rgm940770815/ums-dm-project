var startRow;
var Vstore;


$(function(){

    var firstLine = "<option value=''>请选择</option>";
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

});

BUI.use(['common/search', 'bui/toolbar', 'bui/tree', 'bui/data', 'bui/calendar', 'bui/overlay', 'bui/form', 'bui/uploader', 'bui/mask', 'bui/grid'],
    function (Search, Toolbar, Tree, Data, Calendar, Overlay, Form, Uploader, Mask, Grid) {

        var enumObj_court = {};
        $.post(path + "/code/codeListByType", {typeId: 1}, function (data) {
            for (var i in data.data) {
                enumObj_court[data.data[i].id] = data.data[i].codeName
            }
        });

        //显示列
        var columns = [
            {
                title: '序号', sortable: false, width: "60", align: 'left', renderer: function (value, obj) {
                return startRow++;
            }
            },
            {title: '姓名', dataIndex: 'userName', width: "100", sortable: true, align: 'left'},
            {
                title: '所属法院', dataIndex: 'userCourtNo', width: "200", sortable: true, align: 'left',
                renderer: BUI.Grid.Format.enumRenderer(enumObj_court)
            },
            {title: '申请人', dataIndex: 'applyUserName', width: "100", sortable: true, align: 'left'},
            {
                title: '申请时间',
                dataIndex: 'createTime',
                width: "120",
                sortable: true,
                align: 'left',
                renderer: BUI.Grid.Format.dateRenderer
            },
            {
                title: '申请操作', dataIndex: 'newValidCode', width: "100", sortable: true, align: 'left',
                renderer: function (value, obj) {
                    var str = "";

                    if (obj.newValidCode != null) {
                        switch (obj.newValidCode) {
                            case 0:
                                str = "<span style='color:orangered'>进行停用</span>";
                                break;
                            case 1:
                                str = "<span style='color:#00FF00'>进行启用</span>";
                                break;
                            case 3:
                                str = "<span style='color:orangered'>仅登陆系统</span>";
                                break;
                        }
                    }

                    return str;
                }
            },
            {
                title: '申请状态', dataIndex: 'handle', width: "100", sortable: true, align: 'left',
                renderer: function (value, obj) {
                    var str = "";
                    if (obj.handle != null) {
                        switch (obj.handle) {
                            case 0:
                                str = "未处理";
                                break;
                            case 1:
                                str = "申请通过";
                                break;
                            case 2:
                                str = "申请未通过";
                                break;
                            case 3:
                                str = "申请取消";
                                break;
                        }
                    }
                    return str;
                }
            },
            {title: '处理人', dataIndex: 'updateUserName', width: "120", sortable: true, align: 'left'},
            {
                title: '操作', width: "200", align: 'left', sortable: false,
                renderer: function (value, obj) {
                    if (obj.handle != null && obj.handle == 0) {
                        return "<a style='margin-right: 15px' class='check_apply'>查看申请</a>" +
                            "<a style='margin-right: 15px' class='apply_on'>同意申请</a>" +
                            "<a class='apply_off'>拒绝申请</a>"
                    } else {
                        return "已处理";
                    }
                }
            }
        ];


        //定义表格样式
        var gridCfg = Search.createGridCfg(columns, {
            height: "auto",
            //forceFit: true,
            emptyDataTpl: '<div class="centered"><h2>查询的数据不存在</h2></div>',
            plugins: [BUI.Grid.Plugins.RadioSelection, BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格
        });

        //请求数据
        var store = Search.createStore(path + "/applyForUpdate/getInfo", {
            //sortField: 'SQRQ',
            //sortDirection: 'DESC',
            remoteSort: true, // 开启异步排序
            pageSize: 20,
            params: {
                type: 1
            }

        });

        Vstore = store;

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
        var grid = search.get('grid');


        //grid 点击事件
        grid.on('cellclick', function (ev) {
            var sender = $(ev.domTarget); //点击的Dom
            var record = ev.record;
            if (sender.hasClass('apply_on')) {

                BUI.Message.Confirm(
                    '确定要同意申请吗？',
                    function () {
                        doApply(record.id , 1 );

                    },
                    'question');

            }

            if (sender.hasClass('apply_off')) {

                BUI.Message.Confirm(
                    '确定要拒绝申请吗？',
                    function () {

                        doApply(record.id , 0 );

                    },
                    'question');

            }


            if (sender.hasClass('check_apply')) {

                $("#enable_dialog_form :input").each(function(){

                    var $this = $(this);
                    var name = $this.attr("name");
                    if(record[name] || record[name] == 0){
                        if($this.is("select")){
                            $this.attr("disabled",false);
                        }
                        $this.val(record[name]);
                        if($this.is("select")){
                            $this.attr("disabled",true);
                        }
                    }

                });


                if(record.newValidCode == 1){
                    $("#enable_dialog_form .row_1").hide();
                }else{
                    $("#enable_dialog_form .row_1").show();
                }

                enableDialog.set("v-id",record.id);
                enableDialog.show();
            }
        });

        function doApply(id , value , flag) {

            $.post(path + "/applyForUpdate/update", {
                'umsApplyForUpdate.id': id,
                'enable': value
            }, function (res) {
                if (res.success) {
                    BUI.Message.Alert('操作成功', 'success');
                    store.load({start: 0, pageIndex: 0});
                    if(flag){
                        enableDialog.close();
                    }
                } else {
                    BUI.Message.Alert('操作失败', 'error');
                }
            })

        }

        //------------------------------基本配置结束-------------------------------------------------------------------

        //--------------------------------配置弹出窗--------------------------------------------------------

        var enableForm =  new Form.HForm({
            srcNode: '#enable_dialog_form',
            submitType: 'ajax'
        }).render();

        var enableDialog = new Overlay.Dialog({
            title: '停启用申请',
            width: '90%',
            height: 280,
            buttons: [
                {
                    text: '同意申请', elCls: 'button button-primary', handler: function () {
                        var id = enableDialog.get("v-id");
                        if(id){
                            doApply( id, 1 ,true);
                        }

                    }
                },
                {
                    text: '拒绝申请', elCls: 'button button-primary', handler: function () {
                        var id = enableDialog.get("v-id");
                        if(id){
                            doApply( id, 0 ,true);
                        }
                    }
                },
                {
                    text: '关闭', elCls: 'button', handler: function () {
                        this.close();
                    }
                }],
            contentId: 'enable_dialog', //配置DOM容器的编号
            success: function () {
                this.close();
            }
        });

        enableDialog.on("closing", function () {
            enableForm.clearFields();
            enableForm.clearErrors();
            enableDialog.set("v-id","");
        });

        //-----------------------------------------------------------------------------------------------------------


        //------------------------------日期信息---------------------------------------------------------------------------

        //var nowDate = new Date();
        //var now = nowDate.getFullYear() + "-" + (nowDate.getMonth() + 1) + "-" + nowDate.getDate();
        //var datepicker = new Calendar.DatePicker({
        //    trigger: '.calendar',
        //    minDate: now,
        //    autoRender: true
        //});

        //--------------------------------Form-----------------------------------------------------------------------------
        //var nowDate = new Date();
        //var now = nowDate.getFullYear() + "-" + (nowDate.getMonth() + 1) + "-" + nowDate.getDate();
        //$("#showStart").attr("data-cfg", "{datePicker : {minDate : '" + now + "'}}");
        //$("#showEnd").attr("data-cfg", "{datePicker : {minDate : '" + now + "'}}");
        //var bForm = new Form.HForm({
        //    srcNode: '#spInfo',
        //    submitType: 'ajax'
        //}).render();
        //var cForm = new Form.HForm({
        //    srcNode: '#applyInfo',
        //    submitType: 'ajax'
        //}).render();

        //-------------------------------弹出框的配置--------------------------------------------------------------------------


        //展示表单高度修正
        var grid_div = $("#grid");
        grid_div.find(".bui-grid-body").height($(document).height() - grid_div.find(".bui-grid-body").offset().top - grid_div.find(".bui-grid-bbar").height() - 50);

    });

function searchByName() {
    Vstore.load({'userNameF': $("#search_name").val(), 'start': 0, 'pageIndex': 0});
}


