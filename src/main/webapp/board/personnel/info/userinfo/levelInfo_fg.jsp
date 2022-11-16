<%--
  Created by IntelliJ IDEA.
  User: baish
  Date: 2019/8/22
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>法官等级</title>
    <jsp:include page="common_header.jsp"></jsp:include>
</head>
<body>

<div class="search-grid-container">
    <div id="grid"></div>
</div>

<div id="dialog" class="hide">
    <form id="detail" action="<%=basePath%>userinfo/attach/save" method="post">
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>法官等级：</label>

                <div class="controls">
                    <select id="judge_level" typeId="117" class="code field" data-rules="{required:true}"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>起算日期：</label>

                <div class="controls">
                    <input type="text" id="d_start_date" class="field calendar" data-rules="{required:true}">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>变动类别：</label>

                <div class="controls">
                    <select id="n_alt_type" typeId="21" class="code field" data-rules="{required:true}"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>变动原因：</label>

                <div class="controls">
                    <select id="n_alt_reason" typeId="79" class="code field" data-rules="{required:true}"></select>
                </div>
            </div>

        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>变动依据：</label>

                <div class="controls">
                    <input type="text" id="c_alt_basis" class="field control-text" data-rules="{required:true}"
                    >
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>是否为当前信息：</label>

                <div class="controls">
                    <select id="n_present_info" typeId="68" class="code field" data-rules="{required:true}"></select>
                </div>
            </div>

        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>是否入额：</label>

                <div class="controls">
                    <select id="is_yefg" typeId="68" class="field code" data-rules="{required:true, qcsfre_required: true}"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>入额时间 ：</label>

                <div class="controls">
                    <input type="text" id="yefg_start_time" class="field calendar" data-rules="{resj_required:true}">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s id="tcyesjXh" style="visibility: hidden">*</s>退出员额时间：</label>

                <div class="controls">
                    <input type="text" id="yefg_end_time" class="field calendar" data-rules="{tcyesj_required:true}">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">退出员额原因 ：</label>

                <div class="controls">
                    <select id="yefg_end_reason" typeId="104" class="code field"></select>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="control-group span8">
                <label class="control-label">证书编号：</label>

                <div class="controls">
                    <input type="text" id="c_cert_no" class="field control-text"
                           data-rules="{maxlength:50}">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">批准单位：</label>

                <div class="controls">
                    <input type="text" id="c_approval_unit" class="field control-text"
                    >
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label">批准文号：</label>

                <div class="controls">
                    <input type="text" id="c_approval_doc_no" class="field control-text"
                    >
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
            <input id="n_level_type" name="p.nLevelType" value="1">
            <button type="reset" id="btnReset"></button>
        </div>
    </form>
    <div class="hiddenDom hideInfo">
        <input type="hidden" value="judge_level">
        <input type="hidden" value="d_start_date">
        <input type="hidden" value="level">
    </div>
    <input type="hidden" name="judge_level" value="userInfo.level">
    <input type="hidden" name="d_start_date" value="userInfo.levelDate">
</div>


<script type="text/javascript">
    var viewName = "<s:property value="viewName"></s:property>";
    var userId = "<s:property value="userId"></s:property>";
    viewName = viewName.split("_")[0];
    var otherParam = {"otherParam.n_level_type": "1"};

    var columns = [{
        title: '序号',
        width: '5%',
        renderer: function (value, obj) {
            return startRow++;
        }
    }, {
        title: '等级名称',
        dataIndex: 'judge_level_text',
        width: '30%'
    }, {
        title: '起算日期',
        dataIndex: 'd_start_date',
        width: '15%'
    }, {
        title: '证书编号',
        dataIndex: 'c_cert_no',
        width: '20%'
    }, {
        title: '当前信息',
        dataIndex: 'n_present_info_text',
        width: '10%'
    }, {
        title: '操作',
        width: '20%', renderer: function (value, obj) {
            return '<span class="grid-command btn-detail" title="显示等级详细信息">查看详情</span><span class="grid-command update-info" title="更新到基本信息">更新信息</span>';
        }
    }
    ];

    var formName = '法官等级信息';

    //弹出层默认宽度
    var dialogWidth = 700;
    //弹出层默认高度
    var dialogHeight = 420;

    // 监听是否入额
    $(document).undelegate('#is_yefg', 'change').delegate('#is_yefg', 'change', function (ev) {
        if ($(this).val() == 1) {
            authField('#yefg_start_time', true);
        } else {
            authField('#yefg_start_time', false);
        }
    });

    // 监听退出员额原因
    $(document).undelegate('#yefg_end_reason', 'change').delegate('#yefg_end_reason', 'change', function (ev) {
        if ($(this).val() != "") {
            authField('#yefg_end_time', true);
            $("#tcyesjXh").css("visibility", "visible");
        } else {
            authField('#yefg_end_time', false);
            $("#tcyesjXh").css("visibility", "hidden");
        }
    });

    $(document).undelegate('#judge_level', 'change').delegate('#judge_level', 'change', function (ev) {
        if ($(this).val() == 98) {
            authField('#c_approval_doc_no', false);
            authField('#c_approval_unit', false);
            authField('#is_yefg', false);
            authField('#yefg_start_time', false);
            authField('#d_start_date', false);
            authField('#c_alt_basis', false);
            authField('#n_alt_reason', false);
            authField('#n_alt_type', false);
        } else {
            authField('#c_approval_doc_no', true);
            authField('#c_approval_unit', true);
            authField('#is_yefg', true);
            authField('#yefg_start_time', true);
            authField('#d_start_date', true);
            authField('#c_alt_basis', true);
            authField('#n_alt_reason', true);
            authField('#n_alt_type', true);
        }
    });
    var buiformBeforeSubmit = function (e) {
        $('#n_level_type').val(1);
    };
</script>
<jsp:include page="common_js_audit.jsp"></jsp:include>
<jsp:include page="subitem_audit_user.jsp"></jsp:include>
</body>
</html>
