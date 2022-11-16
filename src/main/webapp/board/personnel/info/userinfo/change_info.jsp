<%--
    Document   : levelInfo
    Created on : 2015-1-8, 16:13:21
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
                        <label class="control-label"><s>*</s>进院日期：</label>

                        <div class="controls">
                            <input type="text" id="enter_date" class="field calendar" data-rules="{required:true}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>进入来源：</label>

                        <div class="controls">
                            <select id="enter_source" typeId="114" listType="tree2" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>

                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label">离职时间：</label>

                        <div class="controls">
                            <input type="text" id="leave_date" class="field calendar">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">离职去向：</label>

                        <div class="controls">
                            <select id="leave_destination" typeId="89" listType="tree2" class="code field"></select>
                        </div>
                    </div>

                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label">因工伤残：</label>

                        <div class="controls">
                            <select id="ygsc" typeId="103" class="code field"></select>
                        </div>
                    </div>

                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>是否为当前信息：</label>

                        <div class="controls">
                            <select id="n_present_info" typeId="68" class="code field" data-rules="{required:true}"></select>
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
                    title: '进院日期',
                    dataIndex: 'enter_date',
                    width: '15%'
                }, {
                    title: '进入来源',
                    dataIndex: 'enter_source_text',
                    width: '20%'
                }, {
                    title: '离职时间',
                    dataIndex: 'leave_date',
                    width: '15%'
                }, {
                    title: '离职去向',
                    dataIndex: 'leave_destination_text',
                    width: '20%'
                }, {
                    title: '当前信息',
                    dataIndex: 'n_present_info_text',
                    width: '10%'
                }, {
                    title: '因工伤残',
                    dataIndex: 'ygsc_text',
                    width: '10%'
                }, {
                    title: '操作',
                    width: '10%', renderer: function (value, obj) {
                    return '<span class="grid-command btn-detail" title="显示人员变化详细信息">查看详情</span>';
                    }
                }
            ];

            var formName = '人员变化信息';

            //弹出层默认宽度
            var dialogWidth = 700;
            //弹出层默认高度
            var dialogHeight = 280;
            </script>
        <jsp:include page="common_js.jsp"></jsp:include>
    </body>
</html>
