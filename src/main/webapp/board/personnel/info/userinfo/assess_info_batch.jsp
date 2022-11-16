<%--
    Document   : assess_info
    Created on : 2015-1-8, 17:10:43
    Author     : Diluka
--%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<jsp:include page="common_header.jsp"></jsp:include>
<body>
<div class="search-grid-container">
    <div id="grid"></div>
</div>

<div id="dialog" class="hide">
    <div style="float: left;width: 200px;overflow:auto;">
        <div style="float: left;">
            <button type="button" class="button button-small refresh ry"><i class="icon-refresh"></i>恢复默认</button>
        </div>
        <div style="float:right;padding-right: 10px;">
            <button type="button" class="button button-small remove ry"><i class="icon-remove"></i>删除</button>
        </div>
        <div style="padding-top: 30px;height:300px;">
            <ul id="ryList" class="bui-select-list">
            </ul>
        </div>
    </div>
    <div style="float: left">
        <form id="detail" action="<%=basePath%>userinfo/attach/saveBatch" method="post">

            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>年度：</label>

                    <div class="controls">
                        <input type="text" id="n_year" class="field control-text" name="p.nYear"
                               data-rules="{required:true,number:true,max:2099,min:1949}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class=" control-group span8">
                    <label class="control-label"><s>*</s>考核结果：</label>

                    <div class="controls">
                        <select id="n_result" typeId="39" class="code field" name="p.nResult" data-rules="{required:true}"></select>
                    </div>
                </div>

            </div>
            <div class="hide edit-fields row">
                <div class="control-group span8">
                    <label class="control-label">序号：</label>

                    <div class="controls">
                        <input type="text" id="sort_no" class="field control-text" name="p.sortNo"
                               data-rules="{number:true}">
                    </div>
                </div>
            </div>
            <div class="hide">
                <input type="hidden" id="allId" name="allId">
                <input type="hidden" id="allUUID" name="allUUID">
                <input type="hidden" id="tableName" name="t">
                <button type="reset" id="btnReset"></button>
            </div>
            <input type="hidden" name="sae" id="sae">
            <input type="hidden" name="userNo" id="userNo">
            <input type="hidden" name="update_time" id="update_time" class="field control-text">
        </form>
    </div>
</div>

<script>
    var viewName = "assess_info_batch";
    var columns = [{
        title: '序号',
//                    dataIndex: 'sort_no',
        width: '4%',
        renderer: function (value, obj) {
            return startRow++;
        }
    }, {
        title: '年度',
        dataIndex: 'n_year',
        width: '14%'
    }, {
        title: '考核结果',
        dataIndex: 'n_result_text',
        width: '69%'
    }, {
        title: '人数',
        dataIndex: 'c',
        width: '5%'
    }, {
        title: '操作',
        width: '9%', renderer: function (value, obj) {
            return '<span class="grid-command btn-detail" title="显示考核详细信息">查看详情</span>';
        }
    }
    ];
    var formName = '考核信息';
    //弹出层默认宽度
    var dialogWidth = 580;
    //弹出层默认高度
    var dialogHeight = 230;

</script>
<jsp:include page="common_js_batch.jsp"></jsp:include>
</body>
</html>