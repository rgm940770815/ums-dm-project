<%--
    Document   : yxnqgb
    Created on : 2022/3/14
    Author     : Rangm
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
                <label class="control-label span8" style="width: 45%"><s>*</s>是否为优秀年轻干部：</label>

                <div class="controls" style="width: 50%">
                    <select id="n_sfyxnqgb" typeId="68" class="code field"
                            data-rules="{required:true}" onchange="selectChange(this)"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s id="jrtjs">*</s>进入条件：</label>

                <div class="controls">
                    <select id="n_jrtj" typeId="10005" class="code field"
                            data-rules="{required:false}" disabled="true"></select>
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label"><s>*</s>是否当前信息：</label>

                <div class="controls">
                    <select id="n_dqxx" typeId="68" class="code field" data-rules="{required:true}"></select>
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
        <input type="hidden" value="n_sfyxnqgb">
        <input type="hidden" value="n_jrtj">
        <input type="hidden" value="n_dqxx">
    </div>
    <input type="hidden" name="n_sfyxnqgb" value="userInfo.nSfyxnqgb">
    <input type="hidden" name="n_jrtj" value="userInfo.nJrtj">
    <input type="hidden" name="n_dqxx" value="userInfo.nDqxx">


</div>

<script>
    var viewName = "<s:property value="viewName"></s:property>";
    var userId = "<s:property value="userId"></s:property>";

    var columns = [{
        title: '序号',
//        dataIndex: 'sort_no',
        width: '10%',
        renderer: function (value, obj) {
            return startRow++;
        }
    }, {
        title: '是否为优秀年轻干部',
        dataIndex: 'n_sfyxnqgb_text',
        width: '30%'
    }, {
        title: '进入条件',
        dataIndex: 'n_jrtj_text',
        width: '30%'
    }, {
        title: '是否当前信息',
        dataIndex: 'n_dqxx_text',
        width: '30%'
    }
    // , {
    //     title: '操作',
    //     width: '10%', renderer: function (value, obj) {
    //         return '<span class="grid-command btn-detail" title="显示学历详细信息">查看详情</span><span class="grid-command update-info" title="更新到基本信息">更新信息</span>';
    //     }
    // }
    ];

    var formName = '优秀年轻干部信息集';
    //弹出层默认宽度
    var dialogWidth = 1000;
    //弹出层默认高度
    var dialogHeight = 370;
    //初始化排序
    sortField ="n_dqxx";
    sortDirection = "ASC";

    function selectChange(row){
        var val = row.options[row.selectedIndex].value;
        dom = val;
        if(val == 1){
            // $("#n_jrtj").attr("data-rules","{required:true}");
            $("#jrtjs").show();
            $("#n_jrtj").removeAttr("disabled");
        }else{
            // $("#n_jrtj").attr("data-rules","{required:false}");
            $("#jrtjs").hide();
            $("#n_jrtj").attr("disabled","true");
            $("#n_jrtj").val("");
        }
    }
    $("#n_jrtj").change(function (){
        $("#n_jrtj").siblings("span").hide();
        ind = 0;
    })
</script>
<jsp:include page="common_js1.jsp"></jsp:include>

</body>
</html>
