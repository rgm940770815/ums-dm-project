<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>统计法官的反查列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../common/layui/css/layui.css">
    <script type="text/javascript" src="../../common/layui/layui.js"></script>
    <script type="text/javascript" src="../../js/jquery/jquery-1.8.1.min.js"></script>
    <style type="text/css">
        .nrlx:hover {
            color: #009688;
            cursor: pointer;
        }
    </style>
</head>
<body>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getPersonDetail">人员信息</button>
    </div>
</script>

<table id="test" lay-filter="test"></table>


<script type="text/javascript">

    var startDate; // 开始时间
    var endDate; // 结束时间
    var leftValue; // 左侧值
    var topValue; // 顶部值

    // 定时器：一秒执行一次，满足条件的时候执行下面的
    var clock = setInterval(countDown, 100);

    function countDown() {
        if (startDate != undefined) {
            clearInterval(clock); //清除js定时器
            ymfs();
        }
    }

    // 等页面加载完执行所有方法
    function ymfs() {
        console.log(startDate);
        console.log(endDate);
        console.log(leftValue);
        console.log(topValue);

        layui.use(['layer', 'form', 'table'], function () {

            var layer = layui.layer,
                table = layui.table;


            table.render({
                elem: '#test',
                title: '用户数据表',
                url: "<%=basePath%>chart/fc_fgList",
                height: 'full-50',
                page: true,
                autoSort: false,
                limit: 10,
                toolbar: '#toolbarDemo',
                where: {'queryEntity.startDate': startDate, 'queryEntity.endDate': endDate, 'queryEntity.leftValue': leftValue, 'queryEntity.topValue': topValue},
                cols: [[
                    {type: 'numbers'},
                    {type: 'radio'},
                    {field: 'fullname', title: '姓名', width: 120, align: 'center', event: 'getPersonDetail', templet: function (value) {
                            return '<span class="nrlx">' + value.fullname + '</span>'
                        }},
                    {field: 'genderText', title: '性别', width: 80, align: 'center'},
                    {
                        field: '', title: '年龄', width: 80, align: 'center', templet: function (obj) {
                            if (obj.birthday != null) {
                                var calcYears2 = calcYears(obj.birthday);
                                if (!isNaN(calcYears2)) {
                                    return calcYears2 + "岁";
                                }
                            }
                        }
                    },
                    {field: 'courtNoText', title: '法院', width: 200, align: 'center'},
                    {field: 'departmentText', title: '部门', width: 200, align: 'center'},
                    {field: 'educationBackgroundText', title: '学历', width: 150, align: 'center'},
                    {field: 'administrationPositionText', title: '行政职务', width: 150, align: 'center'},
                    {field: 'lawPositionText', title: '法律职务', width: 150, align: 'center'},
                    {field: 'rankText', title: '职级', width: 150, align: 'center'}
                ]]
            });

            //工具栏事件
            table.on('toolbar(test)', function (obj) {
                var checkStatus = table.checkStatus(obj.config.id);
                var cData = checkStatus['data'];
                switch (obj.event) {
                    case 'getPersonDetail':
                        //查询信息
                        if (cData.length == 0) {
                            layer.msg('请选择一条数据');
                            return;
                        }
                        open("<%=basePath%>view/detail_new?id=" + cData[0]['id'] + "#userinfo/base");
                        break;
                }
            });

            //监听单元格事件
            table.on('tool(test)', function (obj) {
                var cData = obj['data'];
                switch (obj.event) {
                    case 'getPersonDetail':
                        //查询信息
                        if (cData == undefined) {
                            layer.msg('请选择一条数据');
                            return;
                        }
                        open("<%=basePath%>view/detail_new?id=" + cData['id'] + "#userinfo/base");
                        break;
                }
            });

            // 点击后选择行
            var store = {};
            table.on('row(test)', function (obj) {
                var trA = obj.tr;
                var t = trA[trA.length - 1];
                var isclick = store[obj.data.id];
                if (isclick) {
                    store[obj.data.id] = false;
                } else {
                    store[obj.data.id] = true;
                    $(t).find('.layui-form-radio').click();
                }
            });

            // 计算年龄
            function calcYears(start, end) {
                if (start) {
                    start = start.replace(/-/g, "/");
                }
                if (end) {
                    end = end.replace(/-/g, "/");
                }
                var s = new Date(start);
                var e = end ? new Date(end) : new Date();
                var result = Math.floor((e.getTime() - s.getTime()) / 3600000 / 24 / 365.25);
                return result || 0;
            }


        });
    }

</script>
</body>
</html>
