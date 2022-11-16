<%--
    Document   : foreign_lang
    Created on : 2015-1-8, 17:20:23
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
                        <label class="control-label"><s>*</s>外语语种：</label>

                        <div class="controls">
                            <select id="n_language" typeId="34" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>熟练程度：</label>

                        <div class="controls">
                            <select id="n_proficiency" typeId="35" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>水平级别：</label>

                        <div class="controls">
                            <select id="n_level" typeId="36" class="code field" data-rules="{required:true}"></select>
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
                    title: '外语语种',
                    dataIndex: 'n_language_text',
                    width: '25%'
                }, {
                    title: '熟练程度',
                    dataIndex: 'n_proficiency_text',
                    width: '35%'
                }, {
                    title: '水平级别',
                    dataIndex: 'n_level_text',
                    width: '25%'
                }, {
                    title: '操作',
                    width: '10%', renderer: function (value, obj) {
                        return '<span class="grid-command btn-detail" title="显示外语详细信息">查看详情</span>';
                    }
                }
            ];

            var formName = '外语信息';
            //弹出层默认宽度
            var dialogWidth = 700;
            //弹出层默认高度
            var dialogHeight = 180;
            </script>
        <jsp:include page="common_js.jsp"></jsp:include>
    </body>
</html>