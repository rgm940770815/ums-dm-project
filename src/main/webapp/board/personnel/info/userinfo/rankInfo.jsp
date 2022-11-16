<%--
    Document   : rankInfo
    Created on : 2015-1-8, 9:50:22
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
                        <label class="control-label"><s>*</s>任免类别：</label>

                        <div class="controls">
                            <select id="n_assign_type" typeId="18" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>任免原因：</label>

                        <div class="controls">
                            <select id="n_assign_reason" typeId="19" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>任免日期：</label>

                        <div class="controls">
                            <input type="text" id="d_assign_date" class="field calendar" data-rules="{required:true}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>批准文号：</label>

                        <div class="controls">
                            <input type="text" id="c_approval_doc_no" class="field control-text" data-rules="{required:true,maxlength:50}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>批准单位：</label>

                        <div class="controls">
                            <input type="text" id="c_approval_unit" class="field control-text" data-rules="{required:true,maxlength:100}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>批准日期：</label>

                        <div class="controls">
                            <input type="text" id="d_approval_date" class="field calendar" data-rules="{required:true}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>职级：</label>

                        <div class="controls">
                            <select id="n_rank" typeId="17" class="code field" listType="select2" data-rules="{required:true}"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>职级(最高院)：</label>

                        <div class="controls">
                            <select id="n_rank_report" typeId="1017" class="code field" listType="select2" data-rules="{required:true}"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>职级性质：</label>

                        <div class="controls">
                            <select id="n_rank_nature" typeId="59" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>综合管理类：</label>

                        <div class="controls">
                            <select id="integrated_management" typeId="129" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>单位：</label>

                        <div class="controls">
                            <input type="text" id="c_unit" class="field control-text" data-rules="{maxlength:50,required:true}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>部门：</label>

                        <div class="controls">
                            <input type="text" id="c_department" class="field control-text" data-rules="{required:true, maxlength:50}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>是否是当前信息：</label>

                        <div class="controls">
                            <select id="n_present_info" typeId="68" class="code field" data-rules="{required:true}"></select>
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
                    <div class="hide">
                        <input type="hidden" id="new_id" name="id">
                        <input type="hidden" id="id" name="p.id" class="field">
                        <input type="hidden" id="user_id" name="p.userId" class="field">
                        <input type="hidden" id="tableName" name="t">
                        <button type="reset" id="btnReset"></button>
                    </div>
                </div>
            </form>
                <div class="hiddenDom hideInfo">
                    <input type="hidden" value="n_rank">
                    <input type="hidden" value="d_assign_date">
                    <input type="hidden" value="rank">
                    <input type="hidden" value="n_rank_report">
                </div>
                <input type="hidden" name="n_rank" value="userInfo.rank">
                <input type="hidden" name="d_assign_date" value="userInfo.rankDate">
                <input type="hidden" name="n_rank_report" value="userInfo.rankReport">

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
                    title: '任免类别',
                    dataIndex: 'n_assign_type_text',
                    width: '10%'
                }, {
                    title: '职级',
                    dataIndex: 'n_rank_report_text',
                    width: '25%'
                }, {
                    title: '任免日期',
                    dataIndex: 'd_assign_date',
                    width: '20%'
                }, {
                    title: '批准日期',
                    dataIndex: 'd_approval_date',
                    width: '20%'
                }, {
                    title: '当前信息',
                    dataIndex: 'n_present_info_text',
                    width: '10%'
                }, {
                    title: '操作',
                    width: '10%', renderer: function (value, obj) {
                        return '<span class="grid-command btn-detail" title="显示职级详细信息">查看详情</span><span class="grid-command update-info" title="更新到基本信息">更新信息</span>';
                    }
                }
            ];

            var formName = '职级信息';
            //弹出层默认宽度
            var dialogWidth = 1000;
            //弹出层默认高度
            var dialogHeight = 320;

            //dom创建时调用，用于禁止修改等级类别
            var buiDialogShowCallBack = function () {
                //增加时
                if (record == null){
                    authField2($("#n_rank"), true);
                } else {
                    if ($('#n_rank option:selected').text() == '未定') {
                        authField2($("#n_rank"), false);
                        authField($("#n_present_info"), true);
                    } else {
                        authField2($("#n_rank"), true);
                    }
                }
            };
            $(document).undelegate('#n_rank', 'change').delegate('#n_rank', 'change',function(ev){
                if ($(this).find('option:selected').text() == '未定') {
                    authField2($("#n_rank"), false);
                    authField($("#n_present_info"), true);
                } else {
                    authField2($("#n_rank"), true);
                }
            });
            </script>
        <jsp:include page="common_js.jsp"></jsp:include>
    </body>
</html>
