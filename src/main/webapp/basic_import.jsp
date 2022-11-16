<%--
    Document   : basic_import
    Created on : 2015-1-15, 15:55:33
    Author     : Diluka
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>

<script src="<%=basePath%>js/jquery/jquery.js" type="text/javascript"></script>
<link href="<%=basePath%>/js/select2/css/select2.css" rel="stylesheet">
<script src="<%=basePath%>/js/select2/js/select2.js"></script>

<link href="<%=basePath%>js/bui/css/bs3/bui.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath%>js/bui/css/bs3/dpl.css" rel="stylesheet" type="text/css"/>
<script src="<%=basePath%>js/bui/bui.js" type="text/javascript"></script>
<script src="<%=basePath%>js/bui/config.js" type="text/javascript"></script>
<script src="<%=path%>/js/fix/bui-fix.js"></script>
<link href="<%=path%>/js/fix/bui-fix.css" rel="stylesheet">

<script src="<%=basePath%>/js/echarts3/echarts.js"></script>
<script src="<%=basePath%>/js/common/echrtsCommon.js"></script>

<style>
    h3 {
        padding-left: 1em;
    }
</style>
