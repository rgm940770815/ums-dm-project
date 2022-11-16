<%--zxxxsh_nr：子项信息审核_内容--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.8.1.min.js"></script>

    <link rel="stylesheet" href="<%=basePath%>/common/layui/css/layui.css" media="all">

    <!--引入ztree-->
    <link type="text/css" rel="stylesheet" href="<%=basePath%>/common/zTree/zTreeStyle/zTreeStyle.css">
    <script type="text/javascript" src="<%=basePath%>/common/zTree/jquery.ztree.all.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/common/zTree/jquery.ztree.exhide.min.js"></script>

    <style type="text/css">
        fieldset {
            border-bottom-width: 0px;
            border-left-width: 0px;
            margin-right: 0px;
            border-right-width: 0px;
            padding-right: 6px;
            padding-left: 24px;
            width: 60px;
            padding-bottom: 0px;
            padding-top: 5px;
            margin-top: 10px;
            margin-left: 0px;
        }
    </style>
</head>
<body>

<!-- 提示：如果你不想用form，你可以换成div等任何一个普通元素 -->
<form class="layui-form layui-inline" lay-filter="sqnr_lay" id="sqnr" style="margin-top: 20px;"></form>


<script src="<%=basePath%>/common/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->


<script type="text/javascript">

    var showContent;
    var changeContentDescribe;
    var examineTypeText;
    // 定时器：一秒执行一次，满足条件的时候执行下面的
    var clock = setInterval(countDown, 100);

    function countDown() {
        console.log(showContent);
        if (showContent != undefined) {
            clearInterval(clock); //清除js定时器
            ymfs();
        }
    }

    // 等页面加载完执行所有方法
    function ymfs() {
        // zxxxsh：子项信息审核
        console.log("zxxxsh_nr.jsp")
        layui.use(['jquery', 'layer', 'table', 'laydate', 'element', 'form'], function () {


            var table = layui.table,
                laydate = layui.laydate,
                layer = layui.layer,
                element = layui.element, //导航的hover效果、二级菜单等功能，需要依赖element模块
                form = layui.form;


            // 遍历json格式键值对
            var i = 0;
            var html = '';
            showContent = $.parseJSON(showContent);
            if (examineTypeText == "新增") {
                xzxs();
            } else if (examineTypeText == "修改") {
                xgxs();
            }
            $("#sqnr").append(html);
            form.render(null, 'sqnr_lay');

            // 新增显示
            function xzxs() {
                $.each(showContent, function (key, value) {
                    if (i % 2 == 0) {
                        html += '<div class="layui-form-item">';
                    }
                    html += '<div class="layui-inline">' +
                        '            <label class="layui-form-label" style="width: 100px;">' + key + '</label>' +
                        '            <div class="layui-input-inline">' +
                        '                <input type="text" name="username" lay-verify="required" placeholder="" autocomplete="off" class="layui-input" readonly value="' + value + '">' +
                        '            </div>' +
                        '     </div>';
                    if (i % 2 == 1) {
                        html += '</div>';
                    }
                    i++;
                });
            }

            // xgxs：修改显示
            function xgxs() {
                var changeContentDescribeArray = changeContentDescribe.split(';');
                $.each(showContent, function (key, value) {
                    if (i % 2 == 0) {
                        html += '<div class="layui-form-item">';
                    }

                    // sfgb：是否改变
                    var sfgb = 0;
                    $.each(changeContentDescribeArray, function (key_, value_) {
                        var key_1 = value_.substring(0, value_.indexOf("由"));
                        var value_old = value_.substring(value_.indexOf("由") + 1, value_.indexOf("改变为"));
                        var value_new = value_.substring((value_.indexOf("改变为") + 3), value_.length);
                        if (key_1 == key) {
                            html += '<div class="layui-inline">' +
                                '            <label class="layui-form-label" style="width: 100px;">' + key + '</label>' +
                                '            <div class="layui-input-inline">' +
                                '                <input type="text" name="username" lay-verify="required" placeholder="" autocomplete="off" class="layui-input" readonly value="' + value_old + '">' +
                                '            </div>' +
                                '     </div>';
                            html +=
                                '<div class="layui-inline">' +
                                '            <div style="padding-right: 13px; float: left;"><fieldset><legend>更改为</legend></fieldset></div>' +
                                '            <div class="layui-input-inline">' +
                                '                <input type="text" name="username" lay-verify="required" placeholder="" autocomplete="off" class="layui-input" readonly value="' + value_new + '" style="color: #fff;background:#009688;">' +
                                '            </div>' +
                                '</div>';
                            sfgb = 1;
                        }
                    });
                    if (sfgb == 0) {
                        html += '<div class="layui-inline">' +
                            '            <label class="layui-form-label" style="width: 100px;">' + key + '</label>' +
                            '            <div class="layui-input-inline">' +
                            '                <input type="text" name="username" lay-verify="required" placeholder="" autocomplete="off" class="layui-input" readonly value="' + value + '">' +
                            '            </div>' +
                            '     </div>';
                        html +=
                            '<div class="layui-inline">' +
                            '            <div style="padding-right: 13px; float: left;"><fieldset><legend>未更改</legend></fieldset></div>' +
                            '            <div class="layui-input-inline">' +
                            '                <input type="text" name="username" lay-verify="required" placeholder="" autocomplete="off" class="layui-input" readonly value="' + value + '">' +
                            '            </div>' +
                            '</div>';
                    }
                    if (i % 2 == 1) {
                        html += '</div>';
                    }
                    i++;
                });
            }
        });
    }

</script>

</body>


</html>