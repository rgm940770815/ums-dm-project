<%--
    Document   : pro_technical_position
    Created on : 2015-1-8, 17:26:31
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
                        <label class="control-label"><s>*</s>取得名称：</label>

                        <div class="controls">
                            <select id="n_pt_name" typeId="42" listType="select2" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>取得途径：</label>

                        <div class="controls">
                            <select id="n_pt_way" typeId="41" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>取得日期：</label>

                        <div class="controls">
                            <input type="text" id="d_pt_date" class="field calendar" data-rules="{required:true}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>聘任名称：</label>

                        <div class="controls">
                            <select id="n_engage_name" typeId="42" listType="select2" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>聘任日期：</label>

                        <div class="controls">
                            <input type="text" id="d_engage_date" class="field calendar" data-rules="{required:true}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>职称等级：</label>

                        <div class="controls">
                            <select id="n_title_rank" typeId="58" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                </div>
                <div class="row hide edit-fields">
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
                    title: '取得名称',
                    dataIndex: 'n_pt_name_text',
                    width: '10%'
                }, {
                    title: '取得途径',
                    dataIndex: 'n_pt_way_text',
                    width: '15%'
                }, {
                    title: '取得日期',
                    dataIndex: 'd_pt_date',
                    width: '15%'
                }, {
                    title: '聘任名称',
                    dataIndex: 'n_engage_name_text',
                    width: '20%'
                }, {
                    title: '聘任日期',
                    dataIndex: 'd_engage_date',
                    width: '15%'
                }, {
                    title: '职称等级',
                    dataIndex: 'n_title_rank_text',
                    width: '10%'
                }, {
                    title: '操作',
                    width: '10%', renderer: function (value, obj) {
                        return '<span class="grid-command btn-detail" title="显示专业技术职务详细信息">查看详情</span>';
                    }
                }
            ];

            var formName = '专业技能职务';
            //弹出层默认宽度
            var dialogWidth = 700;
            //弹出层默认高度
            var dialogHeight = 270;
            </script>
        <jsp:include page="common_js.jsp"></jsp:include>
    </body>
</html>