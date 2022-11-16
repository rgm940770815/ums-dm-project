<%--
    Document   : abroad_info
    Created on : 2015-1-8, 17:16:20
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
                        <label class="control-label"><s>*</s>国别：</label>

                        <div class="controls">
                            <select id="n_country" typeId="24" listType="tree" data-rules="{required:true}" class="code field"
                                    name="p.nCountry"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>性质：</label>

                        <div class="controls">
                            <select id="n_nature" typeId="37" data-rules="{required:true}" class="code field"
                                    name="p.nNature"></select>
                        </div>
                    </div>
                </div>
                <div class="row bui-form-group" data-rules="{dateRange : true}">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>开始日期：</label>

                        <div class="controls">
                            <input type="text" id="d_start_date" data-rules="{required:true}" class="field calendar"
                                   name="p.dStartDate">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">结束日期：</label>

                        <div class="controls">
                            <input type="text" id="d_end_date" class="field calendar"
                                   name="p.dEndDate">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label">出国身份：</label>

                        <div class="controls">
                            <select id="n_identity" typeId="38" class="code field" name="p.nIdentity"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">经费来源：</label>

                        <div class="controls">
                            <input type="text" id="c_fund_source" class="field control-text" data-rules="{maxlength:50}">
                        </div>
                    </div>
                </div>
                <div class="row hide edit-fields">
                    <div class="control-group span8">
                        <label class="control-label">序号：</label>

                        <div class="controls">
                            <input type="text" id="sort_no" class="field control-text" name="p.sortNo" data-rules="{number:true}">
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
                    title: '国别',
                    dataIndex: 'n_country_text',
                    width: '10%'
                }, {
                    title: '性质',
                    dataIndex: 'n_nature_text',
                    width: '35%'
                }, {
                    title: '开始日期',
                    dataIndex: 'd_start_date',
                    width: '20%'
                }, {
                    title: '结束日期',
                    dataIndex: 'd_end_date',
                    width: '20%'
                }, {
                    title: '操作',
                    width: '10%', renderer: function (value, obj) {
                        return '<span class="grid-command btn-detail" title="显示出国详细信息">查看详情</span>';
                    }
                }
            ];
            var formName = '出国信息';
            //弹出层默认宽度
            var dialogWidth = 700;
            //弹出层默认高度
            var dialogHeight = 280;
            </script>
        <jsp:include page="common_js.jsp"></jsp:include>

    </body>
</html>