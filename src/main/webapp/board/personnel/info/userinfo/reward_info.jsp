<%--
    Document   : reward_info
    Created on : 2015-1-8, 17:11:59
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
                <label class="control-label"><s>*</s>奖励类别：</label>

                <div class="controls">
                    <select id="n_reward_type" typeId="25" listType="select2" class="code field" data-rules="{required:true}"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">奖励类别说明：</label>

                <div class="controls">
                    <input type="text" id="c_reward_type_info" class="field control-text" data-rules="{maxlength:50}">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>奖励原因：</label>

                <div class="controls">
                    <select id="n_reward_reason" typeId="26" class="code field" data-rules="{required:true}"></select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label">奖励原因说明：</label>

                <div class="controls">
                    <input type="text" id="c_reward_reason_info" class="field control-text"
                           data-rules="{maxlength:100}">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">奖励级别：</label>

                <div class="controls">
                    <select id="n_reward_level" typeId="54" class="code field"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>奖励时间：</label>

                <div class="controls">
                    <input type="text" id="d_reward_date" class="field calendar" data-rules="{required:true}">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label">批准文号：</label>

                <div class="controls">
                    <input type="text" id="c_approval_doc_no" class="field control-text" data-rules="{maxlength:50}">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">批准单位：</label>

                <div class="controls">
                    <input type="text" id="c_approval_unit" class="field control-text" data-rules="{maxlength:100}">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>授予单位：</label>

                <div class="controls">
                    <select id="c_approval_unit_code" typeId="90" listType="tree2" class="code field" data-rules="{required:true}"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">授予单位名称补充：</label>

                <div class="controls">
                    <input type="text" id="c_approval_unit_add" class="field control-text" data-rules="{dwgz:true, maxlength:50}" placeholder="名称为'其他机关'时必填">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>奖励名称：</label>

                <div class="controls">
                    <select id="approval_name" typeId="92" class="code field" data-rules="{required:true}"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">奖励名称补充：</label>

                <div class="controls">
                    <input type="text" id="approval_name_info" class="field control-text" data-rules="{mcgz:true, maxlength:50}" placeholder="名称为'其他奖励'时必填">
                </div>
            </div>
        </div>
        <div class="row hide edit-fields">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>表彰领域：</label>

                <div class="controls">
                    <select id="approval_type" typeId="91" class="code field" data-rules="{required:true}"></select>
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
        title: '奖励类别',
        dataIndex: 'n_reward_type_text',
        width: '20%'
    }, {
        title: '奖励原因',
        dataIndex: 'n_reward_reason_text',
        width: '25%'
    }, {
        title: '授予单位',
        dataIndex: 'c_approval_unit_code_text',
        width: '20%'
    }, {
        title: '奖励级别',
        dataIndex: 'n_reward_level_text',
        width: '20%'
    }, {
        title: '奖励时间',
        dataIndex: 'd_reward_date',
        width: '20%'
    }, {
        title: '操作',
        width: '10%', renderer: function (value, obj) {
            return '<span class="grid-command btn-detail" title="显示奖励详细信息">查看详情</span>';
        }
    }
    ];

    var formName = '奖励信息';
    //弹出层默认宽度
    var dialogWidth = 1000;
    //弹出层默认高度
    var dialogHeight = 370;

    BUI.use('bui/form', function (Form) {
        Form.Rules.add({
            name: 'dwgz',  //规则名称
            msg: '请填写机构信息。',//默认显示的错误信息
            validator: function (value, baseValue, formatMsg) { //验证函数，验证值、基准值、格式化后的错误信息
                var dw = $('#c_approval_unit option:selected').text();
                if (dw == '其他机关' && !value) {
                    return formatMsg;
                }
            }
        });
        Form.Rules.add({
            name: 'mcgz',  //规则名称
            msg: '请填写名称信息。',//默认显示的错误信息
            validator: function (value, baseValue, formatMsg) { //验证函数，验证值、基准值、格式化后的错误信息
                var dw = $('#approval_name option:selected').text();
                if (dw == '其他奖励' && !value) {
                    return formatMsg;
                }
            }
        });
    });
</script>
<jsp:include page="common_js.jsp"></jsp:include>
</body>
</html>