<%--
    Document   : education_info
    Created on : 2015-1-8, 16:27:24
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
                <label class="control-label"><s>*</s>学历：</label>

                <div class="controls">
                    <select id="n_education_background" typeId="11" class="code field"
                            data-rules="{required:true}"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>学历(最高院)：</label>

                <div class="controls">
                    <select id="n_education_background_report" typeId="1011" class="code field"
                            data-rules="{required:true}"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>所学专业：</label>

                <div class="controls">
                    <select id="n_major" typeId="12" class="code field" name="p.nMajor" data-rules="{required:true}"></select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label">所学专业名称补充：</label>

                <div class="controls">
                    <input type="text" id="c_major" class="field control-text" data-rules="{maxlength:100}">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>学校名称：</label>

                <div class="controls">
                    <input type="text" id="c_college" class="field control-text" data-rules="{required:true,maxlength:100}">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>学校类别：</label>

                <div class="controls">
                    <select id="n_school_type" typeId="113" class="code field" name="p.nSchoolType" data-rules="{required:true}"></select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>教育形式：</label>

                <div class="controls">
                    <select id="n_educate_form" typeId="72" class="code field" name="p.nEducateForm" data-rules="{required:true}"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>学习形式：</label>

                <div class="controls">
                    <select id="n_study_form" typeId="22" class="code field" name="p.nStudyForm" data-rules="{required:true}"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>入学日期：</label>

                <div class="controls">
                    <input type="text" id="d_entry_date" class="field calendar" name="p.dEntryDate" data-rules="{required:true}">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>毕业日期：</label>

                <div class="controls">
                    <input type="text" id="d_graduate_date" class="field calendar" name="p.dGraduateDate" data-rules="{required:true}">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>学制：</label>

                <div class="controls">
                    <input type="text" id="n_duration" class="field control-text" name="p.nDuration" data-rules="{required:true,number:true}">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>授予单位：</label>

                <div class="controls">
                    <input type="text" id="c_award_unit" class="field control-text" data-rules="{required:true,maxlength:100}">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span8">
                <label class="control-label"><s>*</s>学校所在国家：</label>

                <div class="controls">
                    <select id="n_school_country" typeId="24" listType="tree" class="code field" name="p.nSchoolCountry" data-rules="{required:true}"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>进院学历：</label>

                <div class="controls">
                    <select id="n_enter_court_edu_bg" typeId="68" class="code field" name="p.nEnterCourtEduBg" data-rules="{required:true}"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>是否是当前信息：</label>

                <div class="controls">
                    <select id="n_present_info" typeId="68" class="code field" name="p.nPresentInfo" data-rules="{required:true}"></select>
                </div>
            </div>
            <%--<div class="control-group span8">--%>
                <%--<label class="control-label">同步到简历信息：</label>--%>

                <%--<div class="controls">--%>
                    <%--<select id="n_sync" typeId="68" class="code field" name="p.nSync"></select>--%>
                <%--</div>--%>
            <%--</div>--%>
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
        <%--<div class="hide edit-fields row">--%>
            <%--<div class="control-group span8">--%>
                <%--<label class="control-label">序号：</label>--%>

                <%--<div class="controls">--%>
                    <%--<input type="text" id="sort_no" class="field control-text" name="p.sortNo"--%>
                           <%--data-rules="{number:true}">--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <div class="hide">
            <input type="hidden" id="new_id" name="id">
            <input type="hidden" id="id" name="p.id" class="field">
            <input type="hidden" id="user_id" name="p.userId" class="field">
            <input type="hidden" id="tableName" name="t">
            <button type="reset" id="btnReset"></button>
        </div>
    </form>
    <div class="hiddenDom hideInfo">
        <input type="hidden" value="n_education_background">
        <input type="hidden" value="n_education_background_report">
        <input type="hidden" value="n_major">
        <input type="hidden" value="education">
    </div>
    <input type="hidden" name="n_education_background" value="userInfo.educationBackground">
    <input type="hidden" name="n_major" value="userInfo.major">
    <input type="hidden" name="n_education_background_report" value="userInfo.educationBackgroundReport">


</div>

<script>
    var viewName = "<s:property value="viewName"></s:property>";
    var userId = "<s:property value="userId"></s:property>";

    var columns = [{
        title: '序号',
//        dataIndex: 'sort_no',
        width: '5%',
        renderer: function (value, obj) {
            return startRow++;
        }
    }, {
        title: '学历',
        dataIndex: 'n_education_background_report_text',
        width: '10%'
    }, {
        title: '所学专业',
        dataIndex: 'n_major_text',
        width: '25%'
    }, {
        title: '学校名称',
        dataIndex: 'c_college',
        width: '25%'
    }, {
        title: '毕业日期',
        dataIndex: 'd_graduate_date',
        width: '15%'
    }, {
        title: '当前信息',
        dataIndex: 'n_present_info_text',
        width: '10%'
    }, {
        title: '操作',
        width: '10%', renderer: function (value, obj) {
            return '<span class="grid-command btn-detail" title="显示学历详细信息">查看详情</span><span class="grid-command update-info" title="更新到基本信息">更新信息</span>';
        }
    }
    ];

    var formName = '学历信息';
    //弹出层默认宽度
    var dialogWidth = 1000;
    //弹出层默认高度
    var dialogHeight = 370;
    //初始化排序
    sortField ="d_graduate_date";
    sortDirection = "DESC";
</script>
<jsp:include page="common_js.jsp"></jsp:include>

</body>
</html>
