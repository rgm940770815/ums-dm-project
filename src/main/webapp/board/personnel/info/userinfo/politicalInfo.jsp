<%--
    Document   : politicalInfo
    Created on : 2014-12-31, 15:47:31
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
                        <label class="control-label"><s>*</s>政治面貌：</label>

                        <div class="controls">
                            <select id="n_political" typeId="13" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>政治面貌(最高院)：</label>

                        <div class="controls">
                            <select id="n_political_report" typeId="1013" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>加入日期：</label>

                        <div class="controls">
                            <input type="text" id="d_entry_date" class="field calendar" data-rules="{required:true}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>转正日期：</label>

                        <div class="controls">
                            <input type="text" id="d_positive_date" class="field calendar" data-rules="{required:true}">
                        </div>
                    </div>
                </div>
                <div class="row hide edit-fields">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>是否是当前信息：</label>

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
                <div class="hiddenDom hideInfo">
                    <input type="hidden" value="n_political">
                    <input type="hidden" value="d_entry_date">
                    <input type="hidden" value="political">
                    <input type="hidden" value="n_political_report">
                </div>
                <input type="hidden" name="n_political" value="userInfo.political">
                <input type="hidden" name="d_entry_date" value="userInfo.politicalDate">
                <input type="hidden" name="n_political_report" value="userInfo.politicalReport">
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
                    title: '政治面貌',
                    dataIndex: 'n_political_report_text',
                    width: '35%'
                }, {
                    title: '加入日期',
                    dataIndex: 'd_entry_date',
                    width: '20%'
                }, {
                    title: '转正日期',
                    dataIndex: 'd_positive_date',
                    width: '20%'
                }, {
                    title: '当前信息',
                    dataIndex: 'n_present_info_text',
                    width: '10%'
                }, {
                    title: '操作',
                    width: '10%', renderer: function (value, obj) {
                        return '<span class="grid-command btn-detail" title="显示政治面貌详细信息">查看详情</span><span class="grid-command update-info" title="更新到基本信息">更新信息</span>';
                    }
                }
            ];


            var formName = '政治面貌';
            //弹出层默认宽度
            var dialogWidth = 700;
            //弹出层默认高度
            var dialogHeight = 230;
            //初始化排序
            sortField = 'd_positive_date';
            sortDirection = 'DESC';

            $('#n_political').on('change', function (e) {
                var text = $(this).find('option:selected').text();
                if (text == '群众') {
                    buiform.getField('p.dEntryDate').set('pauseValid',true).clearErrors();
                    buiform.getField('p.dPositiveDate').set('pauseValid',true).clearErrors();
                } else {
                    buiform.getField('p.dEntryDate').set('pauseValid',false);
                    buiform.getField('p.dPositiveDate').set('pauseValid',false);
                }
            });
            </script>
        <jsp:include page="common_js.jsp"></jsp:include>
    </body>
</html>
