<%--
    Document   : family_info
    Created on : 2015-1-8, 17:08:35
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
                        <label class="control-label"><s>*</s>姓名：</label>

                        <div class="controls">
                            <input type="text" id="c_name" class="field control-text" data-rules="{maxlength:50,required:true}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>与本人关系：</label>

                        <div class="controls">
                            <select id="n_relationship" typeId="40" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                </div>
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
                        <label class="control-label"><s >*</s>出生日期：</label>

                        <div class="controls">
                            <input type="text" id="d_birthday" class="field calendar" data-rules="{required:true}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s >*</s>人员联系电话：</label>

                        <div class="controls">
                            <input type="text" id="c_telephone" class="field control-text"
                                   data-rules="{maxlength:20,number:true,required:true}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label">住房面积：</label>

                        <div class="controls">
                            <input type="text" id="n_house_area" class="field control-text" data-rules="{maxlength:10,number:true}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s >*</s>家庭住址：</label>

                        <div class="controls">
                            <input type="text" id="c_address" class="field control-text" data-rules="{maxlength:100,required:true}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span8">
                        <label class="control-label"><s >*</s>单位及职务：</label>

                        <div class="controls">
                            <input type="text" id="c_unit_job" class="field control-text" data-rules="{maxlength:100,required:true}">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>已故标识：</label>

                        <div class="controls">
                            <select id="is_dead" typeId="68" class="code field" data-rules="{required:true}"></select>
                        </div>
                    </div>
                </div>
                    <div class="row">

                        <div class="control-group span8">
                            <label class="control-label">邮政编码：</label>

                            <div class="controls">
                                <input type="text" id="c_postcode" class="field control-text"
                                       data-rules="{maxlength:6,number:true}">
                            </div>
                        </div>
                        <div class="control-group span8">
                            <label class="control-label">序号：</label>

                            <div class="controls">
                                <input type="text" id="sort_no" class="field control-text" name="p.sortNo"
                                       data-rules="{number:true}">
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
                    title: '与本人关系',
                    dataIndex: 'n_relationship_text',
                    width: '20%'
                }, {
                    title: '姓名',
                    dataIndex: 'c_name',
                    width: '20%'
                }, {
                    title: '出生日期',
                    dataIndex: 'd_birthday',
                    width: '15%'
                }, {
                    title: '政治面貌',
                    dataIndex: 'n_political_text',
                    width: '20%'
                }, {
                    title: '当前信息',
                    dataIndex: 'n_present_info_text',
                    width: '10%'
                }, {
                    title: '操作',
                    width: '10%', renderer: function (value, obj) {
                        return '<span class="grid-command btn-detail" title="显示家庭详细信息">查看详情</span>';
                    }
                }
            ];

            var formName = '家庭信息';
            //弹出层默认宽度
            var dialogWidth = 700;
            //弹出层默认高度
            var dialogHeight = 370;

            $('#is_dead').on('change', function () {
                if ($(this).find('option:selected').text() == '是') {
                    //如果已故，除了姓名和与本人关系必填，其他全部非必填
                    $('[data-rules]:not(#c_name,#n_relationship,#n_political,#n_political_report,#is_dead,#c_unit_job)').each(function () {
                        if (eval('(' + $(this).data('rules') + ')').required) {
                            // pauseValid(this, true);
                            authField(this, false);
                        }
                    });
                } else {
                    $('[data-rules]:not(#c_name,#n_relationship,#n_political,#n_political_report,#is_dead,#c_unit_job)').each(function () {
                        if (eval('(' + $(this).data('rules') + ')').required) {
                            // pauseValid(this, false);
                            authField(this, true);
                        }
                    });
                }
            });

            //开始或停止验证规则---单个
            function pauseValid(o, bool) {
                var fields = buiform.getFields();
                for (var i = 0; i < fields.length; i++) {
                    var field = fields[i];
                    var obj = field.get('el');
                    if ($(o).is(obj)) {
                        field.set('pauseValid', bool);
                        if (bool) field.clearErrors();
                        break;
                    }
                }
            }

        </script>
        <jsp:include page="common_js.jsp"></jsp:include>
    </body>
</html>