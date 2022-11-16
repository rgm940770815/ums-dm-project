<%--
  Created by IntelliJ IDEA.
  User: baish
  Date: 2019/4/18
  Time: 10:41
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
    <title>编制验证配置</title>
    <script src="<%=basePath%>js/jquery/jquery.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/vue.min.js" type="text/javascript"></script>
    <link type="text/css" rel="stylesheet" href="css/config.css">
    <link rel="stylesheet" href="../common/layui/css/layui.css">
    <script type="text/javascript" src="../common/layui/layui.js"></script>
</head>
<body>

<div class="main_div">

    <div class="top_div">

        <div>
            <ul class="breadcrumb" style="margin: 0">
                <li><span>系统配置</span><span class="divider">/</span></li>
                <li>系统参数配置</li>
            </ul>
        </div>

    </div>

    <div class="content_div">
        <div class="fgdj_config base_container">
            <h2>
                法官等级晋升配置方式
            </h2>
            <ul id="level_promote_config" style="" class="hide_div">
                <form id="save_form_1">

                    <li v-for="(value, key ,index ) in dataInfo">

                        <div class="layui-form-item layui-form-item-my">
                            <label class="layui-form-label ">{{value.cName}}</label>
                            <div class="layui-input-block">
                                <span>晋升年限: </span>
                                <input type="hidden" name="cName" :value="value.cName">
                                <input type="hidden" name="cScope" :value="value.cScope">
                                <input type="hidden" name="cKey" :value="value.cKey">
                                <input type="text" name="cValue" class="layui-input layui-input-my" :value="value.cValue"
                                       onkeyup="onkeyupN(this)" onafterpaste="onafterpasteN(this)">
                                <span>年    </span>
                                <a class="a_change_style a_margin_left" @click="handleDelete">删除</a>
                            </div>
                        </div>

                    </li>
                </form>

            </ul>
            <div class="layui-input-block hide_div" >
                <button class="layui-btn " btn-c="1">保存</button>
            </div>
        </div>
        <div class="right-container">
            <div class="base_container" style="margin-bottom: 10px;">
                <h2>
                    退休时间配置
                </h2>
                <ul id="retire_config" class="hide_div">

                    <form id="save_form_2">
                        <li  v-for="(value, key ,index ) in dataInfo">
                            <div class="layui-form-item layui-form-item-my">
                                <label class="layui-form-label ">退休年龄</label>
                                <div class="layui-input-block">
                                    <span>{{value.cName}}: </span>
                                    <input type="hidden" name="cName" :value="value.cName">
                                    <input type="hidden" name="cScope" :value="value.cScope">
                                    <input type="hidden" name="cKey" :value="value.cKey">
                                    <input type="text" name="cValue" class="layui-input layui-input-my"
                                           :value="value.cValue"
                                           onkeyup="onkeyupN(this)" onafterpaste="onafterpasteN(this)">
                                    <span>年    </span>
                                </div>
                            </div>
                        </li>

                    </form>
                </ul>
                <div class="layui-input-block hide_div">
                    <button class="layui-btn" btn-c="2">保存</button>
                </div>

            </div>

            <div class="base_container">
                <h2>
                    人员余量报警值
                </h2>
                <ul id="idle_threshold_config" class="hide_div">

                    <form id="save_form_3">
                        <li  v-for="(value, key ,index ) in dataInfo">
                            <div class="layui-form-item layui-form-item-my">
                                <label class="layui-form-label ">人员余量报警值</label>
                                <div class="layui-input-block">
                                    <span>{{value.cName}}: </span>
                                    <input type="hidden" name="cName" :value="value.cName">
                                    <input type="hidden" name="cScope" :value="value.cScope">
                                    <input type="hidden" name="cKey" :value="value.cKey">
                                    <input type="text" name="cValue" class="layui-input layui-input-my" :value="value.cValue"
                                           onkeyup="onkeyupN(this)" onafterpaste="onafterpasteN(this)">
                                    <span>人    </span>
                                </div>
                            </div>

                        </li>
                    </form>

                </ul>
                <div class="layui-input-block hide_div">
                    <button class="layui-btn" btn-c="3">保存</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var urlPath = "<%=path%>";
</script>
<script type="text/javascript" src="js/config.js"></script>
</body>
</html>
