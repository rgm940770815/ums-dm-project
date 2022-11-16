<%--
    Document   : juror_job_info
    Created on : 2015-3-18, 9:41:13
    Author     : Diluka
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML>
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
                        <label class="control-label">任免日期：</label>

                        <div class="controls">
                            <input type="text" id="d_assign_date" class="field calendar" name="p.dAssignDate">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>任免类别：</label>

                        <div class="controls">
                            <select id="n_assign_type" typeId="18" class="code field" name="p.nAssignType"
                                    data-rules="{required:true}"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">行政职务：</label>

                        <div class="controls">
                            <%--<select id="n_job" typeId="15" class="code field" name="p.nJob"></select>--%>
                            <input  type="text" id="n_job_str"  class="field control-text" name="p.nJobStr">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label">批准日期：</label>

                        <div class="controls">
                            <input type="text" id="d_approval_date" class="field calendar" name="p.dApprovalDate">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">批准单位：</label>

                        <div class="controls">
                            <input type="text" id="c_approval_unit" class="field control-text" data-rules="{maxlength:50}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">批准文号：</label>

                        <div class="controls">
                            <input type="text" id="c_approval_doc_no" class="field control-text" data-rules="{maxlength:50}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label">当前信息：</label>

                        <div class="controls">
                            <select id="n_present_info" typeId="68" class="code field" name="p.nPresentInfo"></select>
                        </div>
                    </div>
                    <div class="edit-fields hide">
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
            <jsp:include page="common_update_info_div.jsp"></jsp:include>
                <div class="hiddenDom hideInfo">
                    <input type="hidden" value="n_job">
                    <input type="hidden" value="d_assign_date">
                    <input type="hidden" value="juror_job">
                </div>
                <input type="hidden" name="n_job" value="userInfo.administrationPosition">
                <input type="hidden" name="d_assign_date" value="userInfo.administrationPositionDate">
            </div>
            <script>
                var viewName = "<s:property value="viewName"></s:property>";
                var userId = "<s:property value="userId"></s:property>";
                var columns = [{
                        title: '序号',
//                        dataIndex: 'sort_no',
                        width: '5%',
                    renderer: function (value, obj) {
                        return startRow++;
                    }
                    }, {
                        title: '任免类别',
                        dataIndex: 'n_assign_type_text',
                        width: '10%'
                    }, {
                        title: '行政职务',
                        dataIndex: 'n_job_text',
                        width: '15%'
                    }, {
                        title: '任免日期',
                        dataIndex: 'd_assign_date',
                        width: '20%'
                    }, {
                        title: '批准日期',
                        dataIndex: 'd_approval_date',
                        width: '20%'
                    }, {
                        title: '当前信息',
                        dataIndex: 'n_present_info_text',
                        width: '10%'
                    }, {
                        title: '操作',
                        width: '10%', renderer: function (value, obj) {
                            return '<span class="grid-command btn-detail" title="显示陪审员职务详细信息">查看详情</span><span class="grid-command update-info" title="更新到基本信息">更新信息</span>';
                        }
                    }
                ];
                var formName = '陪审员职务';
                //弹出层默认宽度
                var dialogWidth = 1000;
                //弹出层默认高度
                var dialogHeight = 280;
            </script>
        <jsp:include page="common_js.jsp"></jsp:include>
    </body>
</html>
