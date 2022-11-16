<%--
    Document   : leadership
    Created on : 2015-1-8, 17:38:11
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
                        <label class="control-label"><s>*</s>任职前工作单位：</label>

                        <div class="controls">
                            <input type="text" id="c_former_unit" class="field control-text"
                                   data-rules="{required:true,maxlength:100}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">任职前工作部门：</label>

                        <div class="controls">
                            <input type="text" id="c_former_dept" class="field control-text"
                                   data-rules="{required:true,maxlength:100}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>原职务：</label>

                        <div class="controls">
                            <select id="n_position" typeId="14" listType="select2" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">是否参加考察：</label>

                        <div class="controls">
                            <select id="n_is_study" typeId="68" class="code field"></select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label">是否征求上级意见：</label>

                        <div class="controls">
                            <select id="n_is_party_view" typeId="68" class="code field"></select>
                        </div>
                    </div>
                    <div class="hide edit-fields">
                        <div class="control-group span8">
                            <label class="control-label">序号：</label>

                            <div class="controls">
                                <input type="text" id="sort_no" class="field control-text" data-rules="{number:true}">
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
                    title: '任职前工作单位',
                    dataIndex: 'c_former_unit',
                    width: '30%'
                }, {
                    title: '任职前工作部门',
                    dataIndex: 'c_former_dept',
                    width: '30%'
                }, {
                    title: '职务',
                    dataIndex: 'n_position_text',
                    width: '10%'
                }, {
                    title: '是否参加考察',
                    dataIndex: 'n_is_study_text',
                    width: '15%'
                }, {
                    title: '操作',
                    width: '10%', renderer: function (value, obj) {
                        return '<span class="grid-command btn-detail" title="显示领导班子详细信息">查看详情</span>';
                    }
                }
            ];

            var formName = '领导班子';
            //弹出层默认宽度
            var dialogWidth = 660;
            //弹出层默认高度
            var dialogHeight = 230;
            </script>
        <jsp:include page="common_js.jsp"></jsp:include>
    </body>
</html>
