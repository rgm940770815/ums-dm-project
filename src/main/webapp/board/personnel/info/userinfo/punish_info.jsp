<%--
    Document   : punish_info
    Created on : 2015-1-8, 17:14:50
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
                <form id="detail" action="<%=basePath%>userinfo/attach/save" method="post">
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>惩处类别：</label>

                        <div class="controls">
                            <select id="n_punish_type" typeId="27" listType="tree2" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>惩处原因：</label>

                        <div class="controls">
                            <select id="n_punish_reason" typeId="28" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                </div>
                <div class="row bui-form-group" data-rules="{dateRange : true}">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>惩处日期：</label>

                        <div class="controls">
                            <input type="text" id="d_start_date" class="field calendar" data-rules="{required:true}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">解除日期：</label>

                        <div class="controls">
                            <input type="text" id="d_end_date" class="field calendar">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label">批准单位：</label>

                        <div class="controls">
                            <input type="text" id="c_unit" class="field control-text" data-rules="{maxlength : 100}">
                        </div>
                    </div>
                    <div class="hide edit-fields">
                        <div class="control-group span8">
                            <label class="control-label">序号：</label>

                            <div class="controls">
                                <input type="text" id="sort_no" class="field control-text" name="p.sortNo"
                                       data-rules="{number : true}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label">惩处原因说明：</label>

                        <div class="controls">
                            <input type="text" id="n_punish_reason_add" class="field control-text" data-rules="{maxlength : 120}">
                        </div>
                    </div>
                </div>
                <div class="hide">
                    <input type="hidden" id="new_id" name="id">
                    <input type="hidden" id="id" name="p.id" class="field">
                    <input type="hidden" id="user_id" name="p.userId" class="field">
                    <input type="hidden" id="tableName" name="t">
                    <button type="reset" id="btnReset"></button>
                </div>
            </form>
        </div>

        <script>
            var viewName = "<s:property value="viewName"></s:property>";
            var userId = "<s:property value="userId"></s:property>";
            var columns = [{
                    title: '序号',
//                    dataIndex: 'sort_no',
                    width: '5%',
                renderer: function (value, obj) {
                    return startRow++;
                }
                }, {
                    title: '惩处类别',
                    dataIndex: 'n_punish_type_text',
                    width: '15%'
                }, {
                    title: '惩处原因',
                    dataIndex: 'n_punish_reason_text',
                    width: '30%'
                }, {
                    title: '惩处日期',
                    dataIndex: 'd_start_date',
                    width: '20%'
                }, {
                    title: '解除日期',
                    dataIndex: 'd_end_date',
                    width: '20%'
                }, {
                    title: '操作',
                    width: '10%', renderer: function (value, obj) {
                        return '<span class="grid-command btn-detail" title="显示惩处务详细信息">查看详情</span>';
                    }
                }
            ];

            var formName = '惩处信息';
            //弹出层默认宽度
            var dialogWidth = 700;
            //弹出层默认高度
            var dialogHeight = 270;
            </script>
        <jsp:include page="common_js.jsp"></jsp:include>
    </body>
</html>