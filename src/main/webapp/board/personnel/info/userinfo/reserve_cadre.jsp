<%--
    Document   : reserve_cadre
    Created on : 2015-1-8, 17:35:27
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
                        <label class="control-label"><s>*</s>后备职务：</label>

                        <div class="controls">
                            <select id="n_reserve_pos" typeId="14" listType="select2" class="code field" data-rules="{required : true}"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>锻炼职务：</label>

                        <div class="controls">
                            <select id="n_exercise_pos" typeId="14" listType="select2" class="code field" data-rules="{required : true}"></select>
                        </div>
                    </div>
                </div>
                <div class="row bui-form-group" data-rules="{dateRange : true}">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>开始时间：</label>

                        <div class="controls">
                            <input type="text" id="d_start_date" class="field calendar" data-rules="{required : true}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>结束时间：</label>

                        <div class="controls">
                            <input type="text" id="d_end_date" class="field calendar" data-rules="{required : true}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>锻炼单位：</label>

                        <div class="controls">
                            <input type="text" id="c_exercise_unit" class="field control-text" data-rules="{required : true, maxlength : 200}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">结 论：</label>

                        <div class="controls">
                            <input type="text" id="c_remark" class="field control-text" data-rules="{maxlength : 500}">
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
                    title: '后备职务',
                    dataIndex: 'n_reserve_pos_text',
                    width: '15%'
                }, {
                    title: '锻炼职务',
                    dataIndex: 'n_exercise_pos_text',
                    width: '20%'
                }, {
                    title: '锻炼单位',
                    dataIndex: 'c_exercise_unit',
                    width: '20%'
                }, {
                    title: '开始日期',
                    dataIndex: 'd_start_date',
                    width: '15%'
                }, {
                    title: '结束日期',
                    dataIndex: 'd_end_date',
                    width: '15%'
                }, {
                    title: '操作',
                    width: '10%', renderer: function (value, obj) {
                        return '<span class="grid-command btn-detail" title="显示后备干部详细信息">查看详情</span>';
                    }
                }
            ];

            var formName = '后备干部';
            //弹出层默认宽度
            var dialogWidth = 700;
            //弹出层默认高度
            var dialogHeight = 270;
            </script>
        <jsp:include page="common_js.jsp"></jsp:include>
    </body>
</html>