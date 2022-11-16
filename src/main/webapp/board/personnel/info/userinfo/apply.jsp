<%--
  Created by IntelliJ IDEA.
  User: Cypress
  Date: 2016/11/28
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>停启用管理</title>
    <jsp:include page="/basic_import.jsp"></jsp:include>
    <script type="text/javascript" src="<%=basePath%>js/common/codelist.js"></script>
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

<div id="enable_dialog" class="hide">
    <form id="enable_dialog_form" class="form-horizontal">
        <div class="row row_1">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>调离原因：</label>

                <div class="controls">
                    <select  typeId="45" class="code" name="leaveReason"  data-rules="{required:true}" readonly="readonly"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>调离去向：</label>

                <div class="controls">
                    <select  typeId="46" class="code"
                             name="leaveDestination"  data-rules="{required:true}" readonly="readonly"></select>
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label"><s>*</s>调离日期：</label>

                <div class="controls">
                    <input  name="leaveDate" type="text" class=" "
                            placeholder="调离日期" data-rules="{datecheck1:null,required:true}" readonly="readonly">
                </div>
            </div>
        </div>

        <div class="row">
            <div class="control-group12 span8">
                <label class="control-label"><s>*</s>停启用方式：</label>

                <div class="controls">
                    <select name="newValidCode" data-rules="{required:true}" readonly="readonly">
                        <option value="0">申请停用</option>
                        <option value="1">申请启用</option>
                        <option value="3">仅登陆系统</option>
                    </select>
                </div>
            </div>
            <div class="control-group12 span8">
                <label class="control-label">联系电话：</label>

                <div class="controls">
                    <input  name="contactNumber" type="text" readonly="readonly"
                            placeholder="联系电话" >
                </div>
            </div>

        </div>

        <div class="row">
            <div class="control-group span21">
                <label class="control-label">申请说明：</label>

                <div class="controls">
                    <textarea style="resize: none" class="span15" name="remark" readonly="readonly"> </textarea>
                </div>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
    var path = "<%=path%>";
</script>
<script type="text/javascript" src="js/apply.js"></script>
</body>
</html>
