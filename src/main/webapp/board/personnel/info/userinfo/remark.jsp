<%--
    Document   : remark
    Created on : 2015-1-8, 17:31:04
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
                <input type="hidden" id="new_id" name="id">
                <input type="hidden" id="id" name="p.id" class="field">
                <input type="hidden" id="user_id" name="p.userId" class="field">
                <input type="hidden" id="tableName" name="t">

                <div class="hide edit-fields">
                    <div class="control-group span8">
                        <label class="control-label">序号：</label>

                        <div class="controls">
                            <input type="text" id="sort_no" class="field control-text" name="p.sortNo"
                                   data-rules="{number:true}">
                        </div>
                    </div>
                </div>
                <div class="control-group span16">
                    <label class="control-label"><s>*</s>备注：</label>

                    <div class="controls control-row4">
                        <textarea type="text" id="c_remark" name="p.cRemark" class="input-large field"
                                  data-rules="{required:true,maxlength:500}"></textarea>
                    </div>
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
                    title: '备注',
                    dataIndex: 'c_remark',
                    width: '85%'
                }, {
                    title: '操作',
                    width: '10%', renderer: function (value, obj) {
                        return '<span class="grid-command btn-detail" title="显示备注详细信息">查看详情</span>';
                    }
                }
            ];

            var formName = '备注信息';
            //弹出层默认宽度
            var dialogWidth = 560;
            //弹出层默认高度
            var dialogHeight = 220;
            </script>
        <jsp:include page="common_js.jsp"></jsp:include>
    </body>
</html>