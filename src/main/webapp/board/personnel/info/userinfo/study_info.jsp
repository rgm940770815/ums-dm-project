<%--
    Document   : study_info
    Created on : 2015-1-8, 16:32:25
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
                        <label class="control-label"><s>*</s>在读学历</label>

                        <div class="controls">
                            <select id="n_education_background" typeId="11" class="code field"
                                    data-rules="{required:true}"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>在读学历(最高院)</label>

                        <div class="controls">
                            <select id="n_education_background_report" typeId="1011" class="code field"
                                    data-rules="{required:true}"></select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>入学日期</label>

                        <div class="controls">
                            <input type="text" id="d_entry_date" class="field calendar" data-rules="{required:true}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>在读院校</label>

                        <div class="controls">
                            <input type="text" id="c_college" class="field control-text"
                                   data-rules="{required:true,maxlength:100}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label">在读专业</label>

                        <div class="controls">
                            <input type="text" id="c_major" class="field control-text" data-rules="{maxlength:100}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">专业</label>

                        <div class="controls">
                            <select id="n_origin_major" typeId="12" class="code field"></select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label">教育形式</label>

                        <div class="controls">
                            <select id="n_educate_form" typeId="72" class="code field"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">学习形式</label>

                        <div class="controls">
                            <select id="n_study_form" typeId="22" class="code field"></select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label">学制</label>

                        <div class="controls">
                            <input type="text" id="n_duration" class="field control-text"
                                   data-rules="{number:true,min:0,max:9}">年
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">当前信息</label>

                        <div class="controls">
                            <select id="n_present_info" typeId="68" class="code field"></select>
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
                    title: '在读学历',
                    dataIndex: 'n_education_background_text',
                    width: '20%'
                }, {
                    title: '在读专业',
                    dataIndex: 'c_major',
                    width: '20%'
                }, {
                    title: '在读院校',
                    dataIndex: 'c_college',
                    width: '20%'
                }, {
                    title: '入学日期',
                    dataIndex: 'd_entry_date',
                    width: '15%'
                }, {
                    title: '当前信息',
                    dataIndex: 'n_present_info_text',
                    width: '10%'
                }, {
                    title: '操作',
                    width: '10%', renderer: function (value, obj) {
                        return '<span class="grid-command btn-detail" title="显示在读详细信息">查看详情</span>';
                    }
                }
            ];

            var formName = '在读信息';
            //弹出层默认宽度
            var dialogWidth = 700;
            //弹出层默认高度
            var dialogHeight = 370;
            </script>
        <jsp:include page="common_js.jsp"></jsp:include>
    </body>
</html>