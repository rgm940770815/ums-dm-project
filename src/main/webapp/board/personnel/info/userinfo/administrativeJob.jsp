<%--
  Created by D.Yang.
  Date: 2014/12/30 0030
  Time: 15:37
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
                        <label class="control-label"><s>*</s>任免日期：</label>

                        <div class="controls">
                            <input type="text" id="d_assign_date" class="field calendar" name="p.dAssignDate"
                                   data-rules="{required:true}">
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
                        <label class="control-label"><s>*</s>任免原因：</label>

                        <div class="controls">
                            <select id="n_assign_reason" typeId="19" class="code field" name="p.nAssignReason" data-rules="{required:true}"></select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>单位：</label>

                        <div class="controls">
                            <input type="text" id="c_unit" class="field control-text" data-rules="{maxlength:50,required:true}">
                        </div>
                    </div>


                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>行政职务(最高院)：</label>

                        <div class="controls">
                            <select id="n_job_report" typeId="1015" class="code field" listType="select2" name="p.nJobReport"
                                    data-rules="{required:true}"></select>
                        </div>
                    </div>

                    <div class="control-group span8">
                        <label class="control-label">副行政职务(最高院)：</label>

                        <div class="controls">
                            <select id="n_extra_job" typeId="1015" class="code field" listType="select2" name="p.nExtraJob"
                                    ></select>
                        </div>
                    </div>

                </div>
                <div class="row">

                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>行政职务：</label>

                        <div class="controls">
                            <select id="n_job" typeId="15" class="code field" listType="select2" name="p.nJob"  data-rules="{required:true}"></select>
                        </div>
                    </div>

                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>批准日期：</label>

                        <div class="controls">
                            <input type="text" id="d_approval_date" class="field calendar" name="p.dApprovalDate" data-rules="{required:true}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>批准单位：</label>

                        <div class="controls">
                            <input type="text" id="c_approval_unit" class="field control-text" data-rules="{maxlength:50,required:true}">
                        </div>
                    </div>

                </div>
                <div class="row">

                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>批准文号：</label>

                        <div class="controls">
                            <input type="text" id="c_approval_doc_no" class="field control-text" data-rules="{maxlength:50,required:true}">
                        </div>
                    </div>

                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>是否是当前信息：</label>

                        <div class="controls">
                            <select id="n_present_info" typeId="68" class="code field" name="p.nPresentInfo" data-rules="{required:true}"></select>
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
                <div class="hiddenDom hideInfo">
                    <input type="hidden" value="n_job">
                    <input type="hidden" value="d_assign_date">
                    <input type="hidden" value="administration">
                    <input type="hidden" value="n_job_report">
                </div>
                <input type="hidden" name="n_job" value="userInfo.administrationPosition">
                <input type="hidden" name="d_assign_date" value="userInfo.administrationPositionDate">
                <input type="hidden" name="n_job_report" value="userInfo.administrationPositionReport">
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
                    title: '行政职务',
                    dataIndex: 'n_job_report_text',
                    width: '15%'
                 }, {
                    title: '单位',
                    dataIndex: 'c_unit',
                    width: '20%'
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
                        return '<span class="grid-command btn-detail" title="显示行政职务详细信息">查看详情</span><span class="grid-command update-info" title="更新到基本信息">更新信息</span>';
                    }
                }
            ];
            var formName = '行政职务';
            //弹出层默认宽度
            var dialogWidth = 1000;
            //弹出层默认高度
            var dialogHeight = 280;
            //初始化排序
            sortField = 'd_assign_date';
            sortDirection = 'DESC';
            </script>
        <jsp:include page="common_js.jsp"></jsp:include>
    </body>
</html>
