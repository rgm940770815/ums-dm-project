<%--
  Created by IntelliJ IDEA.
  User: baish
  Date: 2019/4/22
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>反查</title>
    <link rel="stylesheet" href="../common/layui/css/layui.css">
    <script src="<%=basePath%>js/jquery/jquery.js" type="text/javascript"></script>
    <script type="text/javascript" src="../common/layui/layui.js"></script>
    <link type="text/css" rel="stylesheet" href="../common/fontawesome/css/font-awesome.min.css">
    <style type="text/css">

        .layui-table-view .layui-form-radio {
            margin-top: 14px;
        }

        .layui-table-tool .btn {
            border: 1px solid #ccc;
            padding: 5px 10px;
            border-radius: 5px;
        }
    </style>
</head>
<body>


<div class="show-for-layui">
    <%--右侧列表--%>
    <div class="main-table-show">
        <table id="show-for-layui" class="" lay-filter="layuiftest"></table>
    </div>

</div>


<script type="text/html" id="toolbarDemo">
    <button class="layui-btn layui-btn-sm" lay-event="query">
        <i class="fa fa-info-circle"></i>
        <span>人员信息</span>
    </button>
</script>


<script type="text/javascript">
    var scope = '${param.scope}';
    var courtNo = '${param.courtNo}' ? '${param.courtNo}' : null;
    var param = '${param.param}';
    var type = '${param.type}' ? '${param.type}' : "1";

    var url = "<%=path%>/verify/query";

    switch (type) {
        case "1":
            url = "<%=path%>/verify/query";
            break;
        case "2":
            url = "<%=path%>/verify/queryForPromote";
            break;
        case "3":
            url = "<%=path%>/verify/queryForRetire";
            break;
    }

    //执行一个 table 实例
    layui.use('table', function () {
        var table = layui.table;
        table.render({
            elem: '#show-for-layui'
            , height: 'full-50'
            , url: url //数据接口
            , where: {
                scope: scope,
                courtNo: courtNo,
                param: param
            }
            , title: '用户表'
            , page: true //开启分页
            , toolbar: '#toolbarDemo' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            , defaultToolbar: ['filter']  //默认的工具
            , cols: [[ //表头
                {type: 'radio', fixed: 'left'}
                , {field: 'index', title: '序号', width: 100, type: "numbers"}
                , {field: 'fullname', title: '姓名', width: 120}
                , {field: 'gender_text', title: '性别', width: 90}
                , {field: 'court_no_text', title: '法院', width: 200}
                , {field: 'department_text', title: '部门', width: 150}
                , {field: 'administration_position_text', title: '行政职务', width: 200}
                , {field: 'law_position_text', title: '法律职务', width: 200}
            ]]
        });


        //监听事件 工具条
        table.on('toolbar(layuiftest)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);

            var cData = checkStatus['data'];

            switch (obj.event) {
                case 'query':
                    //查询信息
                    if (cData.length == 0) {
                        layer.msg('请选择一条有效的数据');
                        return;
                    }
                    open("<%=basePath%>view/detail_new?id=" + cData[0]['id'] + "#userinfo/base");

                    break;
            }

        });

        var store = {};

        //监听事件 行点击
        table.on('row(layuiftest)', function (obj) {
            var trA = obj.tr;
            console.log("in=====");
            // if(trA.length > 1){
                var t = trA[trA.length - 1];
                var isclick = store[obj.data.id];
                if(isclick ){
                    store[obj.data.id] = false;
                }else{
                    store[obj.data.id] = true;
                    $(t).find('.layui-form-radio').click();
                }

            // }



            //obj.del(); //删除当前行
            //obj.update(fields) //修改当前行数据
        });

    });


</script>
</body>
</html>
