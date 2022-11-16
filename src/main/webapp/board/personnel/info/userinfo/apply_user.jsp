<%--
  Created by IntelliJ IDEA.
  User: Cypress
  Date: 2016/11/28
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>停启用管理</title>
    <jsp:include page="/basic_import.jsp"></jsp:include>
</head>
<style type="text/css">
    body {
        padding: 0;
        margin: 0;
    }

    .holder {
        width: 100%;
    }

    .holder > div {
        clear: both;
        padding: 5px;
        width: 96%;
        margin: 0 auto;
    }

    tr td span {
        font-size: 12px;
    }

    input {
        box-sizing: content-box;
    }

    a {
        cursor: pointer;
    }
</style>
<body>

<div class="holder">
    <div class="row">
        <div>
            <ul class="breadcrumb" style="margin: 0">
                <li><span>信息管理</span><span class="divider">/</span></li>
                <li>停启用管理</li>
            </ul>
        </div>
    </div>
    <div class="row">
        <ul class="bui-bar " role="toolbar" id="bar7" aria-disabled="false" aria-pressed="false">
            <li class="bui-bar-item bui-inline-block" aria-disabled="false"  aria-pressed="false">
                <input name="search" id="search_name" placeholder="请输入需要查询的姓名" class="control-text search_field" type="text">
            </li>
            <li class="bui-bar-item-button bui-bar-item bui-inline-block" aria-disabled="false" id="btn-search">
                <button type="button" class="button button-small" onclick="searchByName()"><i class="icon-search"></i>搜索
                </button>
            </li>
        </ul>
    </div>


    <div id="grid">

    </div>


</div>
<script type="text/javascript">
    var path = "<%=path%>";
</script>
<script type="text/javascript" src="js/apply_user.js"></script>
</body>
</html>
