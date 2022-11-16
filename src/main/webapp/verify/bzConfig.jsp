<%--
  Created by IntelliJ IDEA.
  User: baish
  Date: 2019/4/19
  Time: 14:32
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
    <title>编制数录入</title>
    <script src="<%=basePath%>js/jquery/jquery.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/vue.min.js" type="text/javascript"></script>
    <link type="text/css" rel="stylesheet" href="css/bzConfig.css">
    <link rel="stylesheet" href="../common/layui/css/layui.css">
    <script type="text/javascript" src="../common/layui/layui.js"></script>
    <!--引入ztree-->
    <script type="text/javascript" src="../common/zTree/jquery.ztree.all.min.js"></script>
    <script type="text/javascript" src="../common/zTree/jquery.ztree.exhide.min.js"></script>
    <link type="text/css" rel="stylesheet" href="../common/zTree/zTreeStyle/zTreeStyle.css">
</head>
<body>

<div class="main_div">

    <div class="top_div">

        <div>
            <ul class="breadcrumb" style="margin: 0">
                <li><span>系统配置</span><span class="divider">/</span></li>
                <li>人员编制录入</li>
            </ul>
        </div>

    </div>

    <div class="content_div">
        <div class="left-container base_container">
            <h2>
                法院
            </h2>
            <div class="ztree" id="ztree-div">

            </div>

        </div>
        <div class="right-container">
            <div class="base_container" style="margin-bottom: 10px;">
                <h2>
                    编制数
                </h2>
                <ul>
                    <div id="zzzx_config">
                        <li v-for="(value, key ,index ) in dataInfo" class="hide_div">
                            <div class="layui-form-item layui-form-item-my">
                                <label class="layui-form-label ">{{value.cName}}: </label>
                                <div class="layui-input-block">
                                    <input type="hidden" name="cName" :value="value.cName">
                                    <input type="hidden" name="cScope" :value="value.cScope">
                                    <input type="hidden" name="cKey" :value="value.cKey">
                                    <input type="text" name="cValue" class="layui-input layui-input-my"
                                           :value="value.cValue"
                                           onkeyup="onkeyupN(this)" onafterpaste="onafterpasteN(this)">
                                    <span>人    </span>
                                </div>
                            </div>
                        </li>
                    </div>

                    <div id="yefg_config">
                        <li v-for="(value, key ,index ) in dataInfo" class="hide_div">
                            <div class="layui-form-item layui-form-item-my">
                                <label class="layui-form-label ">{{value.cName}}: </label>
                                <div class="layui-input-block">
                                    <input type="hidden" name="cName" :value="value.cName">
                                    <input type="hidden" name="cScope" :value="value.cScope">
                                    <input type="hidden" name="cKey" :value="value.cKey">
                                    <input type="text" name="cValue" class="layui-input layui-input-my"
                                           :value="value.cValue"
                                           onkeyup="onkeyupN(this)" onafterpaste="onafterpasteN(this)">
                                    <span>人    </span>
                                </div>
                            </div>
                        </li>
                    </div>

                </ul>
                <div class="layui-input-block hide_div">
                    <button class="layui-btn " btn-c="1">保存</button>
                </div>

            </div>

            <div class="base_container r_b_container">
                <h2>
                    法官等级编制数
                </h2>
                <div class="r_b_i_container">

                    <div class="r_b_i_o_container">

                        <ul id="level_config" style="" class="hide_div">

                            <li v-for="(value, key ,index ) in dataInfo">

                                <div class="layui-form-item layui-form-item-my">
                                    <label class="layui-form-label ">{{value.cName}}</label>
                                    <div class="layui-input-block">
                                        <input type="hidden" name="cName" :value="value.cName">
                                        <input type="hidden" name="cScope" :value="value.cScope">
                                        <input type="hidden" name="cKey" :value="value.cKey">
                                        <input type="text" name="cValue" class="layui-input layui-input-my"
                                               :value="value.cValue"
                                               onkeyup="onkeyupN(this)" onafterpaste="onafterpasteN(this)">
                                        <span>人    </span>
                                    </div>
                                </div>

                            </li>

                        </ul>

                    </div>

                </div>

                <div class="layui-input-block hide_div">
                    <button class="layui-btn " btn-c="2">保存</button>
                </div>

            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var urlPath = "<%=path%>";
</script>
<script type="text/javascript" src="js/bzConfig.js"></script>

</body>
</html>
