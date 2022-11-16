<%--
    Document   : levelInfo
    Created on : 2015-1-8, 16:13:21
    Author     : Diluka
--%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String TYPE = request.getParameter("type") == null ? "FG" : request.getParameter("type");   //FG 法官/FJ 法警/FGZL 法官助理/SJY 书记员
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
                <label class="control-label"><s>*</s>法官助理等级：</label>

                <div class="controls">
                    <select id="helper_level" typeId="118" class="code field" data-rules="{required:true}"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>起算日期：</label>

                <div class="controls">
                    <input type="text" id="d_start_date" class="field calendar"  data-rules="{required:true}" >
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

            <%--批准文号--%>
            <div class="control-group span8">
                <label class="control-label">批准文号：</label>

                <div class="controls">
                    <input type="text" id="c_approval_doc_no" class="field control-text"
                    >
                </div>
            </div>

        </div>
        <div class="row">

            <div class="control-group span8">
                <label class="control-label"><s>*</s>是否为当前信息：</label>

                <div class="controls">
                    <select id="n_present_info" typeId="68" class="code field" data-rules="{required:true}" ></select>
                </div>
            </div>


            <div class="control-group span8">
                <label class="control-label"><s>*</s>是否进入职务序列：</label>

                <div class="controls">
                    <select id="enter_job_sequence" typeId="68" class="code field" data-rules="{required:true}" ></select>
                </div>
            </div>

        </div>
        <div class="row">
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
            <input id="n_level_type" name="p.nLevelType" value="3">
            <button type="reset" id="btnReset"></button>
        </div>
    </form>
</div>

<script>
    var viewName = "<s:property value="viewName"></s:property>";
    var userId = "<s:property value="userId"></s:property>";
    viewName = viewName.split("_")[0];
    var otherParam = {"otherParam.n_level_type": "3"};

    var columns = [{
        title: '序号',
//                    dataIndex: 'sort_no',
        width: '5%',
        renderer: function (value, obj) {
            return startRow++;
        },
    }, {
        title: '等级名称',
        dataIndex: 'helper_level_text',
        width: '30%'
    }, {
        title: '起算日期',
        dataIndex: 'd_start_date',
        width: '15%'
    }, {
        title: '变动类别',
        dataIndex: 'n_alt_reason_text',
        width: '20%'
    }, {
        title: '当前信息',
        dataIndex: 'n_present_info_text',
        width: '10%'
    }, {
        title: '操作',
        width: '20%', renderer: function (value, obj) {
            return '<span class="grid-command btn-detail" title="显示等级详细信息">查看详情</span>';
        }
    }
    ];

    var formName = '法官助理等级信息';

    //弹出层默认宽度
    var dialogWidth = 700;
    //弹出层默认高度
    var dialogHeight = 270;
    $(document).undelegate('#helper_level', 'change').delegate('#helper_level', 'change', function (ev) {
        if ($(this).val() == 79) {
            authField('#d_start_date', false);
        } else {
            authField('#d_start_date', true);
        }
    });
    var buiformBeforeSubmit = function (e) {
        $('#n_level_type').val(3);
    };
</script>
<jsp:include page="common_js.jsp"></jsp:include>
</body>
</html>
