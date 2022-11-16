<%--
    Document   : training_info
    Created on : 2015-1-8, 16:38:28
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
                        <label class="control-label"><s>*</s>培训形式：</label>

                        <div class="controls">
                            <select id="n_training_form" typeId="22" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">培训形式补充：</label>

                        <div class="controls">
                            <input type="text" id="c_other_training_form" class="field control-text"
                                   data-rules="{maxlength : 100}">
                        </div>
                    </div>

                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>培训方式：</label>

                        <div class="controls">
                            <select id="n_training_mode" typeId="97" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>培训离岗状态：</label>

                        <div class="controls">
                            <select id="n_training_way" typeId="50" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>

                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>培训内容：</label>

                        <div class="controls">
                            <select id="train_content" typeId="99" listType="tree2" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>开始日期：</label>

                        <div class="controls" data-rules="{dateRange : true}">
                            <input type="text" id="d_start_date" class="field calendar" data-rules="{required:true}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>结束日期：</label>

                        <div class="controls" data-rules="{dateRange : true}">
                            <input type="text" id="d_end_date" class="field calendar" data-rules="{required:true}">
                        </div>

                    </div>

                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>培训种类：</label>

                        <div class="controls">
                            <select id="n_training_type" typeId="95" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                </div>
                <div class="row">

                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>承办培训机构：</label>

                        <div class="controls">
                            <select id="n_agency_type" typeId="52" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">承办培训机构补充：</label>

                        <div class="controls">
                            <input type="text" id="c_other_agency_type" class="field control-text"
                                   data-rules="{maxlength : 100}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">同步到简历信息：</label>

                        <div class="controls">
                            <select id="n_sync" typeId="68" class="code field"></select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>组织培训单位：</label>

                        <div class="controls">
                            <select id="c_agency" typeId="98" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">组织培训单位补充：</label>

                        <div class="controls">
                            <input type="text" id="c_agency_add" class="field control-text" data-rules="{maxlength : 100}">
                        </div>
                    </div>

                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>网络自学学时：</label>

                        <div class="controls">
                            <input type="text" id="n_duration_web" class="field control-text" data-rules="{required:true,maxlength : 10, number:true}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>培训班名称：</label>

                        <div class="controls">
                            <select id="c_class_name_code" typeId="96" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">培训班名称补充：</label>

                        <div class="controls">
                            <input type="text" id="c_class_name" class="field control-text" data-rules="{maxlength : 100}">
                        </div>
                    </div>

                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>培训学时：</label>

                        <div class="controls">
                            <input type="text" id="n_duration" class="field control-text" data-rules="{required:true,maxlength : 10, number:true}">
                        </div>
                    </div>

                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label">序号：</label>

                        <div class="controls">
                            <input type="text" id="sort_no" class="field control-text" name="p.sortNo"
                                   data-rules="{number : true}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">备注：</label>

                        <div class="controls">
                            <textarea type="text" id="bz" class="field control-text" data-rules="{maxlength : 150}"></textarea>
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
                    title: '培训形式',
                    dataIndex: 'n_training_form_text',
                    width: '10%'
                }, {
                    title: '培训班名称',
                    dataIndex: 'c_class_name_text',
                    width: '20%'
                }, {
                    title: '开始日期',
                    dataIndex: 'd_start_date',
                    width: '15%'
                }, {
                    title: '结束日期',
                    dataIndex: 'd_end_date',
                    width: '15%'
                }, {
                    title: '当前信息',
                    dataIndex: 'n_present_info_text',
                    width: '10%'
                }, {
                    title: '操作',
                    width: '10%', renderer: function (value, obj) {
                        return '<span class="grid-command btn-detail" title="显示培训详细信息">查看详情</span>';
                    }
                }
            ];
            var formName = '培训信息';
            //弹出层默认宽度
            var dialogWidth = 1000;
            //弹出层默认高度
            var dialogHeight = 420;
            </script>
        <jsp:include page="common_js.jsp"></jsp:include>
    </body>
</html>