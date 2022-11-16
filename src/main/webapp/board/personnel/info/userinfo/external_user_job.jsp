<%--
    Document   : external_job
    Created on : 2015-3-18, 14:50:33
    Author     : Diluka
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
                        <label class="control-label"><s>*</s>职务：</label>

                        <div class="controls">
                            <select id="job_id" class="field" name="p.jobId"
                                    data-rules="{required:true}" onchange="jobTypeChange()"></select>
                            <script>
                                function jobTypeChange() {
                                    $('#job').prop('disabled', $("#job_id").val() != 1);
                                }
                                $.getJSON("<%=basePath%>code/allExtJobs", {_: new Date().getTime()}, function (data) {
                                    if (data) {
                                        $("#job_id").empty();
                                        for (var i = 0; i < data.length; i++) {
                                            $("#job_id").append("<option value='" + data[i].id + "'>" + data[i].jobName + "</option>");
                                        }
                                    }
                                });
                            </script>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">其他职务：</label>

                        <div class="controls">
                            <input id="job" type="text"  class="field control-text" name="p.job">
                        </div>
                    </div>
                </div>
                <div class="row">
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
                </div>
            </form>
            <jsp:include page="common_update_info_div.jsp"></jsp:include>
            </div>
            <script>
                var viewName = "<s:property value="viewName"></s:property>";
                var userId = "<s:property value="userId"></s:property>";
                var columns = [{
                        title: '序号',
//                        dataIndex: 'sort_no',
                        width: '5%',
                    renderer: function (value, obj) {
                        return startRow++;
                    }
                    }, {
                        title: '职务',
                        dataIndex: 'job_id_text',
                        width: '75%',
                        renderer: function (v, o) {
                            if (o.job_id == 1) {
                                return o.job;
                            } else {
                                return v;
                            }
                        }
                    }, {
                        title: '操作',
                        width: '20%', renderer: function (value, obj) {
                            return '<span class="grid-command btn-detail" title="显示编外人员职务详细信息">查看详情</span>';
                        }
                    }
                ];
                var formName = '编外人员职务';
                //弹出层默认宽度
                var dialogWidth = 700;
                //弹出层默认高度
                var dialogHeight = 200;
            </script>
        <jsp:include page="common_js.jsp"></jsp:include>
    </body>
</html>
