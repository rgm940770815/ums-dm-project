<%--改变编制类型的编外人员编辑--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    //    调用iframe传来的userid
    String user_id = request.getParameter("user_id");
    String changeUUID = request.getParameter("changeUUID");
%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>编外人员编辑页面</title>
    <jsp:include page="/basic_import.jsp"></jsp:include>
    <jsp:include page="/fragment/tip.jsp"></jsp:include>
    <script src="<%=basePath%>js/common/idchecker.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/crypto-js/rollups/md5.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/base64/base64.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=basePath%>js/common/codelist.js"></script>
    <script src="<%=basePath%>js/common/datetools.js" type="text/javascript"></script>
    <style type="text/css">
        /**内容超出 出现滚动条 **/
        .bui-stdmod-body {
            overflow-x: hidden;
            overflow-y: auto;
        }

        .my_icon {
            margin-top: 1px !important;
        }

        .nav-tabs li s {
            color: red;
            padding-right: 5px;
            text-decoration: none;
        }

        .button-own {
            width: 125px;
        }

        .grid-valid {
            color: #3366cc;
            cursor: pointer;
            margin-right: 5px;
        }

        .grid-valid:hover {
            color: #ff6600;
        }

    </style>
</head>
<body>

<div id="DialogInfo">

</div>


</body>


<script type="text/javascript">
    var changeUUID = "<%=changeUUID%>";
    var CourtNo = '';
    var auth = false;
    var V = $.extend({}, V, parent.V);
    loadDialogInfo("<%=user_id%>");

    function loadDialogInfo(obj) {
        var url = '<%=basePath%>external/form3';
        var datas = {}
        if (obj != null) {
            datas['userId'] = obj;
        }
        $.post(url, datas, function (data) {
            $("#DialogInfo").empty();
            $("#DialogInfo").html(data);
        })
    }
</script>
</html>
