<%--
    Document   : audio_video
    Created on : 2015-1-8, 17:32:05
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

                <div class="hide edit-fields">
                    <div class="row">
                        <div class="control-group span8 ">
                            <label class="control-label">序号：</label>

                            <div class="controls">
                                <input type="text" id="sort_no" class="field control-text" name="p.sortNo"
                                       data-rules="{number:true}">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="control-group span8">
                            <label class="control-label"><s>*</s>多媒体文件：</label>

                            <div class="controls">
                                <input type="text" id="c_file_name" class="field control-text" name="p.cFileName"
                                       data-rules="{required:true,maxlength:255}">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="control-group span16">
                            <label class="control-label"><s>*</s>多媒体文件路径：</label>

                            <div class="controls">
                                <input type="text" id="c_file_path" class="field control-text input-large" name="p.cFilePath"
                                       data-rules="{required:true,maxlength:255}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span16">
                        <label class="control-label"><s>*</s>描述：</label>

                        <div class="controls control-row4">
                            <textarea id="c_description" class="field input-large"
                                      data-rules="{required:true,maxlength:500}"></textarea>
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
                    title: '多媒体文件',
                    dataIndex: 'c_file_name',
                    width: '35%'
                }, {
                    title: '描述',
                    dataIndex: 'c_description',
                    width: '50%',
                    renderer: function (value) {
                        if (value && value.length > 20) {
                            return value.slice(0, 20) + "...";
                        } else {
                            return value;
                        }
                    }
                }, {
                    title: '操作',
                    width: '10%', renderer: function (value, obj) {
                        return '<span class="grid-command btn-detail" title="显示影音资料详细信息">查看详情</span>';
                    }
                }
            ];
            var formName = '影音资料';
            //弹出层默认宽度
            var dialogWidth = 550;
            //弹出层默认高度
            var dialogHeight = 320;
            </script>
        <jsp:include page="common_js.jsp"></jsp:include>
    </body>
</html>