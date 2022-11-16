<%--
    Document   : levelInfo
    Created on : 2015-1-8, 16:13:21
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
    <div style="float: left;width: 200px;overflow:auto;">
        <div style="float: left;">
            <button type="button" class="button button-small refresh ry"><i class="icon-refresh"></i>恢复默认</button>
        </div>
        <div style="float:right;padding-right: 10px;">
            <button type="button" class="button button-small remove ry"><i class="icon-remove"></i>删除</button>
        </div>
        <div style="padding-top: 30px;height:300px;">
            <ul id="ryList" class="bui-select-list">
            </ul>
        </div>
    </div>
    <div style="float: left">
        <form id="detail" action="<%=basePath%>userinfo/attach/saveBatch" method="post">
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>等级类别：</label>

                    <div class="controls">
                        <select id="n_level_type" typeId="60" class="code field" data-rules="{required:true}"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>等级名称：</label>

                    <div class="controls">
                        <select id="n_level_name" typeId="20" class="code field" data-rules="{required:true}"></select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">证书编号：</label>

                    <div class="controls">
                        <input type="text" id="c_cert_no" class="field control-text" data-rules="{maxlength:50}">
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
                        <input type="text" id="c_alt_basis" class="field control-text" data-rules="{required:true, maxlength:150}">
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
                    <label class="control-label">批准文号：</label>

                    <div class="controls">
                        <input type="text" id="c_approval_doc_no" class="field control-text"
                               data-rules="{maxlength:50}">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>当前信息：</label>

                    <div class="controls">
                        <select id="n_present_info" typeId="68" class="code field" data-rules="{required:true}"></select>
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
                <input type="hidden" id="allId" name="allId">
                <input type="hidden" id="allUUID" name="allUUID">
                <input type="hidden" id="tableName" name="t">
                <button type="reset" id="btnReset"></button>
            </div>
            <input type="hidden" name="sae" id="sae">
            <input type="hidden" name="userNo" id="userNo">
            <input type="hidden" name="update_time" id="update_time" class="field control-text">
        </form>
        <div class="hiddenDom hideInfo">
            <input type="hidden" value="n_level_name">
            <input type="hidden" value="d_start_date">
            <input type="hidden" value="level">
        </div>
        <input type="hidden" name="n_level_name" value="userInfo.level">
        <input type="hidden" name="d_start_date" value="userInfo.levelDate">
    </div>
</div>

<script>
    var viewName = "levelInfo_batch";
    var columns = [{
        title: '序号',
//                    dataIndex: 'sort_no',
        width: '5%',
        renderer: function (value, obj) {
            return startRow++;
        }
    }, {
        title: '等级类别',
        dataIndex: 'n_level_type_text',
        width: '9%'
    }, {
        title: '等级名称',
        dataIndex: 'n_level_name_text',
        width: '29%'
    }, {
        title: '起算日期',
        dataIndex: 'd_start_date',
        width: '14%'
    }, {
        title: '证书编号',
        dataIndex: 'c_cert_no',
        width: '19%'
    }, {
        title: '当前信息',
        dataIndex: 'n_present_info_text',
        width: '9%'
    }, {
        title: '人数',
        dataIndex: 'c',
        width: '5%'
    }, {
        title: '操作',
        width: '9%', renderer: function (value, obj) {
            return '<span class="grid-command btn-detail" title="显示等级详细信息">查看详情</span>';
        }
    }
    ];

    var formName = '等级信息';

    //弹出层默认宽度
    var dialogWidth = 900;
    //弹出层默认高度
    var dialogHeight = 370;
</script>
<jsp:include page="common_js_batch.jsp"></jsp:include>
</body>
</html>
