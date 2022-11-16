<%--
  Created by IntelliJ IDEA.
  User: baish
  Date: 2019/4/23
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>通知</title>
    <link rel="stylesheet" href="common/layui/css/layui.css">
    <script src="<%=basePath%>js/jquery/jquery.js" type="text/javascript"></script>
    <script type="text/javascript" src="common/layui/layui.all.js"></script>
    <script src="<%=basePath%>js/vue.min.js" type="text/javascript"></script>
    <style type="text/css">
        .layui-elem-quote.layui-quote-nm {
            margin-bottom: 8px;
            padding: 10px 15px;
        }

        #verify_content_x {
            margin: 15px;
            display: none;
        }

        .fa-notice-verify {
            padding: 0 5px;
            cursor: pointer;
        }

        .a_hover_style:hover {
            cursor: pointer;
            text-decoration: underline;
        }

        .a_green {
            text-decoration: none;
            color: #21b384;
        }

        .a_green:hover {
            text-decoration: underline;
            color: #198764;
        }

        /*.hide_div{*/
        /*display: none;*/
        /*}*/
    </style>
</head>

<body>

<div class="hide_div">
    <div id="verify_content_x">

    </div>
</div>


<%--<script type="text/html" id="verify_content_x">--%>

<%--<blockquote v-for="(value, key ,index ) in dataInfo" class="layui-elem-quote layui-quote-nm">--%>
<%--{{value.prefix}}--%>
<%--</blockquote>--%>

<%--</script>--%>


<script type="text/javascript">


    $(function () {
        readVerifyNotice(true);
        //给铃铛绑定事件
        $(".fa-notice-verify").off("click").on("click", function () {
            readVerifyNotice();
        });
    });

    /*
    onlyNotice 当为true时 只用来通知
     */
    function readVerifyNotice(onlyNotice) {

        var template = " <blockquote v-for=\"(value, key ,index ) in dataInfo\" class=\"layui-elem-quote layui-quote-nm\">\n" +
            "            {{value.prefix}}\n" +
            "            <a style=\"margin: 0 8px;\" class='a_hover_style a_green' @click='query(value.scope,value.param,value.type)'>\n" +
            "            {{value.suffix}}\n" +
            "            </a>\n" +
            "            人\n" +
            "        </blockquote>";

        var container = "#verify_content_x";
        //信息提示
        $.ajax({
            url: "<%=path%>/verify/notice",
            data: {},
            type: "post",
            dataType: "json",
            success: function (data) {


                if (data && data.length > 0) {

                    $(".fa-notice-verify").html("<span style='color:red;margin-left:4px;'>" + data.length + "</span>");
                    if (onlyNotice) {
                        return;
                    }
                    $(container).append(template);
                    var vueE = new Vue({
                        el: container,
                        data: {
                            dataInfo: data
                        },
                        methods: {

                            //反查 type 1 是普通反查 2是给法官等级晋升进行反查 3是退休人员
                            query: function (scope, param, type) {
                                var append = "";
                                if (param) {
                                    append += "&param=" + param;
                                }
                                if (type) {
                                    append += "&type=" + type;
                                }

                                layui.use('layer', function () {
                                    var layer = layui.layer;
                                    layer.open({
                                        title: "人员列表",
                                        type: 2,    //类型2,iframe弹窗
                                        area: ['70vw', '80%'],
                                        fixed: false, //不固定
                                        maxmin: true,  //最大最小化按钮
                                        content: 'verify/fc.jsp?scope=' + scope + append,  //内部页面
                                        scrollbar: false    //屏蔽滚动条
                                    });
                                })


                            }
                        }
                    });

                    layui.use('layer', function () {
                        var layer = layui.layer;

                        // layer.open({
                        //     title: '在线调试'
                        //     ,content: '可以填写任意的layer代码',
                        //     offset: 'rb'
                        // });

                        layer.open({
                            title: '通知提醒',
                            type: 1,
                            area: '350px',
                            id: "alert_div", //唯一id
                            content: $(container),
                            // time: 5000, //自动关闭
                            shade: 0,//不显示遮罩层
                            btn: null, //不显示btn
                            offset: 'rb', // top left
                            cancel: function (index, layero) {
                                $("#verify_content_x").empty();
                            }

                        });

                    })
                }


            }
        })

    }


</script>
</body>


</html>
