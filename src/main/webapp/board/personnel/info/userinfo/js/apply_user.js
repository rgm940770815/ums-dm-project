var startRow;
var Vstore;
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
                        return "<a style='margin-right: 15px' class='apply_out'>取消申请</a>"
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
                    type: 1,
                    isUser: 1
                }

            })
            ;

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
            if (sender.hasClass('apply_out')) {

                BUI.Message.Confirm(
                    '确定要取消申请吗？',
                    function () {

                        $.post(path + "/applyForUpdate/update", {
                            'umsApplyForUpdate.id': record.id,
                            'enable': 3
                        }, function (res) {
                            if (res.success) {
                                BUI.Message.Alert('操作成功', 'success');
                                store.load({start: 0, pageIndex: 0});
                            } else {
                                BUI.Message.Alert('操作失败', 'error');
                            }
                        })

                    },
                    'question');

            }

        });


        //------------------------------基本配置结束-------------------------------------------------------------------


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


