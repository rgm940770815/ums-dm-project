
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%-- 分管部门 --%>
<html>
<head>
    <title></title>
    <style>
        #admin_depart_form {
            display: inline-block;
        }

        .containner {
        }

        .main_show {

        }

        .main_show table {
            border-right: 1px solid #dddddd;
            border-bottom: 1px solid #dddddd;
        }

        .main_show table td {
            border-left: 1px solid #dddddd;
            border-top: 1px solid #dddddd;
            text-align: center;
        }

        .main_show .show_tr td {
            font-size: 12px;
            padding-top: 6px;
            height: 35px;
        }

        .main_show .titile_tr td {
            font-size: 14px;
            font-weight: bold;
            padding-top: 6px;
            height: 35px;
        }

        .tr_1 {
            background-color: #F5F5F5;
        }

        .sortNo {
            width: 75px;
        }
    </style>
</head>
<body>
<div id="admin_depart_info" class="hide" style="margin: 20px">
    <div style="margin:2px 80px">

        <form class="row form-horizontal" id="admin_depart_form" action="<%=path%>/adminDept/insert">
            <div class="control-group span7">
                <label class="control-label" style="width: 60px;">法院：</label>

                <div class="controls">
                    <select id="n_courtInfoNo" typeId="1" class="CourtInfocode" name="umsAssignAdminDept.adminCourtCode"
                            onchange="loadDeptListByFydmAdmin('#n_departmentInfo', this.value, '<option value=\'\'>请选择</option>')"
                            data-rules="{required:true}">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>

            <div class="control-group span7">
                <label class="control-label" style="width: 60px;">部门：</label>

                <div class="controls">
                    <select id="n_departmentInfo" courtNo="" data-rules="{required:true}"
                            name="umsAssignAdminDept.adminDeptId">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>

            <input type="hidden" id="adminDeptHideId" name="umsAssignAdminDept.userUuid">
        </form>
        <button class="button button-primary" onclick="adminDeptSubmit()"
                style="display: inline-block;position: relative;top:-30px;">添加信息
        </button>
        <hr style="margin-top: 0px;"/>
        <div class="nx_containner ">
            <div class="main_show">
                <table cellpadding="0" cellspacing="0" style="width: 750px;margin: 0 auto;margin-bottom: 20px;"
                       id="adminDepartMainTable">
                    <%--<TR class="titile_tr tr_1">--%>
                    <%--<TD>姓名</TD>--%>
                    <%--<TD>挂靠法院</TD>--%>
                    <%--<TD>部门</TD>--%>
                    <%--<TD>添加时间</TD>--%>
                    <%--<TD>操作</TD>--%>
                    <%--</TR>--%>
                </table>
            </div>
        </div>
    </div>
</div>


</body>
<script type="text/javascript">

    // $("#n_courtInfoNo").select2();
    $("#n_departmentInfo").select2();

    function loadDeptListByFydmAdmin(cbo, courtNo, firstLine, callback) {
        $.getJSON("/ums/code/deptByFydm",
                {
                    fydm: courtNo
                },
                function (data) {
                    if (firstLine) {
                        $(cbo).html(firstLine);
                    } else {
                        $(cbo).empty();
                    }

                    if (data) {
                        for (var i = 0; i < data.length; i++) {
                            $(cbo).append($("<option>").attr({"value": data[i].deptNo}).text(data[i].deptName));
                        }
                    }

                    if (callback) {
                        callback();
                    }
                    $(cbo).val('').select2();
                });
    }

</script>
</html>
