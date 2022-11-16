<%--
    Document   : legalJob
    Created on : 2015-1-8, 9:36:14
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
                        <label class="control-label"><s>*</s>任免日期：</label>

                        <div class="controls">
                            <input type="text" id="d_assign_date" class="field calendar" data-rules="{required:true}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>法律职务：</label>

                        <div class="controls">
                            <select id="n_job" typeId="16" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>法律职务(最高院)：</label>

                        <div class="controls">
                            <select id="n_job_report" typeId="1016" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>单位：</label>

                        <div class="controls">
                            <input type="text" id="c_unit" class="field control-text" data-rules="{required:true,maxlength:50}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>部门：</label>

                        <div class="controls">
                            <input type="text" id="c_department" class="field control-text" data-rules="{required:true, maxlength:50}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>任免原因：</label>

                        <div class="controls">
                            <select id="n_assign_reason" typeId="19" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>批准日期：</label>

                        <div class="controls">
                            <input type="text" id="d_approval_date" class="field calendar" data-rules="{required:true}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>批准单位：</label>

                        <div class="controls">
                            <input type="text" id="c_approval_unit" class="field control-text" data-rules="{required:true,maxlength:50}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>批准文号：</label>

                        <div class="controls">
                            <input type="text" id="c_approval_doc_no" class="field control-text" data-rules="{required:true,maxlength:50}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>当前信息：</label>

                        <div class="controls">
                            <select id="n_present_info" typeId="68" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">是否首次任命法官：</label>

                        <div class="controls">
                            <select id="n_is_first_appoint_judge" typeId="68" class="code field"></select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label">首任法官工作年限：</label>

                        <div class="controls">
                            <input type="text" id="n_first_judge_year" class="field control-text"
                                   data-rules="{number:true,min:0,max:70}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">从事法律工作情况：</label>

                        <div class="controls">
                            <select id="n_job_situation" typeId="65" class="code field"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">具有法律专业知识：</label>

                        <div class="controls">
                            <select id="n_is_pro" typeId="68" class="code field"></select>
                        </div>
                    </div>
                </div>
                <div class="row hide edit-fields">
                    <div class="control-group span8">
                        <label class="control-label">法律专业知识情况：</label>

                        <div class="controls">
                            <select id="n_pro_situation" typeId="66" class="code field"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">序号：</label>

                        <div class="controls">
                            <input type="text" id="sort_no" class="field control-text" name="p.sortNo"
                                   data-rules="{number:true}">
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
                    <input type="hidden" value="law">
                    <input type="hidden" value="n_job_report">
                </div>
                <input type="hidden" name="n_job" value="userInfo.lawPosition">
                <input type="hidden" name="d_assign_date" value="userInfo.lawPositionDate">
                <input type="hidden" name="n_job_report" value="userInfo.lawPositionReport">
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
                },{
                    title: '法律职务',
                    dataIndex: 'n_job_report_text',
                    width: '15%'
                 },  {
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
                        return '<span class="grid-command btn-detail" title="显示法律职务详细信息">查看详情</span><span class="grid-command update-info" title="更新到基本信息">更新信息</span>';
                    }
                }
            ];

            var formName = '法律职务';
            //弹出层默认宽度
            var dialogWidth = 1000;
            //弹出层默认高度
            var dialogHeight = 370;
            //初始化排序
            sortField = 'd_assign_date';
            sortDirection = 'DESC';
            </script>
        <jsp:include page="common_js.jsp"></jsp:include>
    </body>
</html>
