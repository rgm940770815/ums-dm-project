<%--
    Document   : assess_info
    Created on : 2015-1-8, 17:10:43
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
                        <label class="control-label"><s>*</s>年度：</label>

                        <div class="controls">
                            <input type="text" id="n_year" class="field control-text" name="p.nYear"
                                   data-rules="{required:true,number:true,max:2099,min:1949}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class=" control-group span8">
                        <label class="control-label"><s>*</s>考核结果：</label>

                        <div class="controls">
                            <select id="n_result" typeId="39" class="code field" name="p.nResult" data-rules="{required:true}"></select>
                        </div>
                    </div>

                </div>
                <div class="hide edit-fields row">
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
                    title: '年度',
                    dataIndex: 'n_year',
                    width: '15%'
                }, {
                    title: '考核结果',
                    dataIndex: 'n_result_text',
                    width: '70%'
                }, {
                    title: '操作',
                    width: '10%', renderer: function (value, obj) {
                        return '<span class="grid-command btn-detail" title="显示考核详细信息">查看详情</span>';
                    }
                }
            ];
            var formName = '考核信息';
            //弹出层默认宽度
            var dialogWidth = 380;
            //弹出层默认高度
            var dialogHeight = 230;
            //初始化排序
            sortField = 'n_year';
            sortDirection = 'DESC';
            </script>
        <jsp:include page="common_js.jsp"></jsp:include>
    </body>
</html>