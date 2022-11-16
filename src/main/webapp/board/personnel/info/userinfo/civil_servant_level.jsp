<%--
    Document   : civil_servant_level
    Created on : 2015-1-8, 16:19:00
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
                        <label class="control-label"><s>*</s>公务员级别：</label>

                        <div class="controls">
                            <select id="n_servant_level" typeId="83" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>起算日期：</label>

                        <div class="controls">
                            <input type="text" id="d_start_date" class="field calendar" name="p.dStartDate" data-rules="{required:true}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>单位：</label>

                        <div class="controls">
                            <input type="text" id="c_unit" class="field control-text" data-rules="{required:true,maxlength:50}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>工资档次：</label>

                        <div class="controls">
                            <select id="n_wage_grade" typeId="85" class="code field" name="p.nWageGrade" data-rules="{required:true}"></select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>是否是当前信息：</label>

                        <div class="controls">
                            <select id="n_present_info" typeId="68" class="code field" name="p.nPresentInfo" data-rules="{required:true}"></select>
                        </div>
                    </div>
                    <div class="edit-fields hide">
                        <div class="control-group span8">
                            <label class="control-label">序号：</label>

                            <div class="controls">
                                <input type="text" id="sort_no" class="field control-text" data-rules="{number:true}">
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
                <div class="hiddenDom hideInfo">
                    <input type="hidden" value="n_servant_level">
                    <input type="hidden" value="d_start_date">
                    <input type="hidden" value="servant_level">
                </div>
                <input type="hidden" name="n_servant_level" value="userInfo.servantLevel">
                <input type="hidden" name="d_start_date" value="userInfo.servantLevelDate">
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
                    title: '公务员级别',
                    dataIndex: 'n_servant_level_text',
                    width: '10%'
                }, {
                    title: '起算日期',
                    dataIndex: 'd_start_date',
                    width: '15%'
                }, {
                    title: '单位',
                    dataIndex: 'c_unit',
                    width: '30%'
                }, {
                    title: '工资档次',
                    dataIndex: 'n_wage_grade_text',
                    width: '20%'
                }, {
                    title: '当前信息',
                    dataIndex: 'n_present_info_text',
                    width: '10%'
                }, {
                    title: '操作',
                    width: '10%', renderer: function (value, obj) {
                        return '<span class="grid-command btn-detail" title="显示公务员详细信息">查看详情</span><span class="grid-command update-info" title="更新到基本信息">更新信息</span>';
                    }
                }
            ];
            var formName = '公务员等级';

            //弹出层默认宽度
            var dialogWidth = 700;
            //弹出层默认高度
            var dialogHeight = 230;
            //dom创建时调用，用于禁止修改等级类别
            var buiDialogShowCallBack = function () {
                //增加时
                if (record == null){
                    authField2($("#n_servant_level"), true);
                } else {
                    if ($('#n_servant_level option:selected').text() == '级别未定') {
                        authField2($("#n_servant_level"), false);
                        authField($("#n_present_info"), true);
                    } else {
                        authField2($("#n_servant_level"), true);
                    }
                }
            };
            $(document).undelegate('#n_servant_level', 'change').delegate('#n_servant_level', 'change',function(ev){
                if ($(this).find('option:selected').text() == '级别未定') {
                    authField2($("#n_servant_level"), false);
                    authField($("#n_present_info"), true);
                } else {
                    authField2($("#n_servant_level"), true);
                }
            });
            </script>
        <jsp:include page="common_js.jsp"></jsp:include>
    </body>
</html>
