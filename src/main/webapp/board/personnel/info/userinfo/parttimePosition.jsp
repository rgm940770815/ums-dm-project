<%--
    Document   : parttimePosition
    Created on : 2015-1-8, 9:57:34
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
                        <label class="control-label"><s>*</s>任免类别：</label>

                        <div class="controls">
                            <select id="n_assign_type" typeId="18" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>兼任职务：</label>

                        <div class="controls">
                            <input type="text" id="c_parttime_job" class="field control-text"
                                   data-rules="{required:true,maxlength:100}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>任免日期：</label>

                        <div class="controls">
                            <input type="text" id="d_assign_date" class="field calendar" data-rules="{required:true}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>任免原因：</label>

                        <div class="controls">
                            <select id="n_assign_reason" typeId="19" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label">单位：</label>

                        <div class="controls">
                            <input type="text" id="c_unit" class="field control-text" data-rules="{maxlength:50}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>部门：</label>

                        <div class="controls">
                            <input type="text" id="c_department" class="code field control-text" data-rules="{required:true, maxlength:50}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label">批准日期：</label>

                        <div class="controls">
                            <input type="text" id="d_approval_date" class="field calendar">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">批准单位：</label>

                        <div class="controls">
                            <input type="text" id="c_approval_unit" class="field control-text" data-rules="{maxlength:50}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label">批准文号：</label>

                        <div class="controls">
                            <input type="text" id="c_approval_doc_no" class="field control-text" data-rules="{maxlength:50}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>是否是当前信息：</label>

                        <div class="controls">
                            <select id="n_present_info" typeId="68" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>同步到简历信息：</label>

                        <div class="controls">
                            <select id="n_is_sync" typeId="68" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                    <div class="hide edit-fields">
                        <div class="control-group span8">
                            <label class="control-label">序号：</label>

                            <div class="controls">
                                <input type="text" id="sort_no" class="field control-text" name="p.sortNo"
                                       data-rules="{number:true}">
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
                    title: '任免类别',
                    dataIndex: 'n_assign_type_text',
                    width: '10%'
                }, {
                    title: '兼任职务',
                    dataIndex: 'c_parttime_job',
                    width: '35%'
                }, {
                    title: '任免日期',
                    dataIndex: 'd_assign_date',
                    width: '15%'
                }, {
                    title: '批准日期',
                    dataIndex: 'd_approval_date',
                    width: '15%'
                }, {
                    title: '当前信息',
                    dataIndex: 'n_present_info_text',
                    width: '10%'
                }, {
                    title: '操作',
                    width: '10%', renderer: function (value, obj) {
                        return '<span class="grid-command btn-detail" title="显示兼任职务详细信息">查看详情</span>';
                    }
                }
            ];

            var formName = '兼任职务';
            //弹出层默认宽度
            var dialogWidth = 700;
            //弹出层默认高度
            var dialogHeight = 370;
            </script>
        <jsp:include page="common_js.jsp"></jsp:include>
    </body>
</html>