<%--
    Document   : resume_info
    Created on : 2015-1-8, 16:40:29
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
                <div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>所在单位：</label>

                        <div class="controls">
                            <input type="text" id="c_unit" class="field control-text" data-rules="{required:true, maxlength : 200}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>所在部门：</label>

                        <div class="controls">
                            <input type="text" id="c_dept" class="field control-text" data-rules="{maxlength : 100,required:true}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>起始时间：</label>

                        <div class="controls" data-rules="{dateRange : true}">
                            <input type="text" id="d_start_date" class="field calendar" data-rules="{required:true}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">截止时间：</label>
                        <div class="controls" data-rules="{dateRange : true}">
                            <input type="text" id="d_end_date" class="field calendar">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>职务：</label>

                        <div class="controls">
                            <input type="text" id="c_position" class="field control-text"
                                   data-rules="{required:true,maxlength:100}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>职级：</label>

                        <div class="controls">
                            <input type="text" id="c_rank" class="field control-text" data-rules="{maxlength : 50,required:true}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">证明人：</label>

                        <div class="controls">
                            <input type="text" id="c_reference" class="field control-text" data-rules="{maxlength : 50}">
                        </div>
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
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>上任免表：</label>
                        <%--参数1上任免表,参数2不上任免表--%>
                        <div class="controls">
                            <select id="c_srmb"  typeId="68" class="field code" data-rules="{required:true}"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">补充内容：</label>

                        <div class="controls">
                            <textarea type="text" id="add_content" name="p.addContent" class="field control-row2" data-rules="{maxlength : 150}"></textarea>
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
                <%--<div class="hiddenDom hideInfo">--%>
                    <%--<input type="hidden" value="c_unit">--%>
                    <%--<input type="hidden" value="c_position">--%>
                    <%--<input type="hidden" value="jurorInfo">--%>
                <%--</div>--%>
                <%--<input type="hidden" name="c_unit" value="jurorInfo.company">--%>
                <%--<input type="hidden" name="c_position" value="jurorInfo.companyJob">--%>
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
                    title: '起始时间',
                    dataIndex: 'd_start_date',
                    width: '15%'
                }, {
                    title: '截止时间',
                    dataIndex: 'd_end_date',
                    width: '15%'
                }, {
                    title: '单位',
                    dataIndex: 'c_unit',
                    width: '15%'
                }, {
                    title: '部门',
                    dataIndex: 'c_dept',
                    width: '10%'
                }, {
                    title: '职务',
                    dataIndex: 'c_position',
                    width: '10%'
                }, {
                    title: '职级',
                    dataIndex: 'c_rank',
                    width: '10%'
                }, {
                    title: '当前信息',
                    dataIndex: 'n_present_info_text',
                    width: '10%'
                }, {
                    title: '操作',
                    width: '10%', renderer: function (value, obj) {
                        return '<span class="grid-command btn-detail" title="显示简历详细信息">查看详情</span>';
                    }
                }
            ];

            var formName = '简历信息';
            //弹出层默认宽度
            var dialogWidth = 700;
            //弹出层默认高度
            var dialogHeight = 350;
            //初始化排序
            sortField = 'd_start_date';
            sortDirection = 'DESC';
            </script>
        <jsp:include page="common_js.jsp"></jsp:include>
    </body>
</html>
