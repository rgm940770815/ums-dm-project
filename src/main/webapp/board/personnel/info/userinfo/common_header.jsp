<%--
    Document   : common_header
    Created on : 2015-1-14, 11:30:38
    Author     : Diluka
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <jsp:include page="/basic_import.jsp"></jsp:include>
    <script src="<%=basePath%>js/common/codelist.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/common/strings.js" type="text/javascript"></script>
    <script>
        var sortField="";
        var sortDirection = "";
    </script>
</head>
