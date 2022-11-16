<%--
    Document   : wage_info
    Created on : 2015-1-8, 17:33:07
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

                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>职务工资档次：</label>

                        <div class="controls">
                            <select id="c_pos_wage_level" typeId="85" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>职务工资级别：</label>

                        <div class="controls">
                            <input type="text" id="c_pos_wage_grade" class="field control-text" data-rules="{required:true}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>工资档次时间：</label>

                        <div class="controls">
                            <input type="text" id="d_pos_wage_grade_time" class="field calendar" data-rules="{required:true}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>现级别：</label>

                        <div class="controls">
                            <input type="text" id="c_current_grade" class="field control-text" data-rules="{required:true, maxlength:20}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>现级别时间：</label>

                        <div class="controls">
                            <input type="text" id="d_current_grade_time" class="field calendar" data-rules="{required:true}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>级别工资额：</label>

                        <div class="controls">
                            <input type="text" id="m_level_wage" class="field control-text" data-rules="{required:true, number:true,min:0}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>职务工资额：</label>

                        <div class="controls">
                            <input type="text" id="m_pos_wage" class="field control-text" data-rules="{required:true,number:true,min:0}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">职务岗位补贴：</label>

                        <div class="controls">
                            <input type="text" id="m_posi_allowance" class="field control-text"
                                   data-rules="{number:true,min:0}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">津贴：</label>

                        <div class="controls">
                            <input type="text" id="m_allowance" class="field control-text" data-rules="{number:true,min:0}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label">教、护龄津贴：</label>

                        <div class="controls">
                            <input type="text" id="m_other_allowance" class="field control-text"
                                   data-rules="{number:true,min:0}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">基础工资：</label>

                        <div class="controls">
                            <input type="text" id="m_base_wage" class="field control-text" data-rules="{number:true,min:0}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label">工龄工资：</label>

                        <div class="controls">
                            <input type="text" id="m_time_wage" class="field control-text" data-rules="{number:true,min:0}">
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
                    title: '职务工资级别',
                    dataIndex: 'c_pos_wage_grade',
                    width: '25%'
                }, {
                    title: '职务工资档次时间',
                    dataIndex: 'd_pos_wage_grade_time',
                    width: '20%'
                }, {
                    title: '职务工资额',
                    dataIndex: 'm_pos_wage',
                    width: '20%'
                }, {
                    title: '级别工资额',
                    dataIndex: 'm_level_wage',
                    width: '20%'
                }, {
                    title: '操作',
                    width: '10%', renderer: function (value, obj) {
                        return '<span class="grid-command btn-detail" title="显示工资详细信息">查看详情</span>';
                    }
                }
            ];
            var formName = '工资信息';
            //弹出层默认宽度
            var dialogWidth = 1000;
            //弹出层默认高度
            var dialogHeight = 320;
            </script>
        <jsp:include page="common_js.jsp"></jsp:include>
    </body>
</html>