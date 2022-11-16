<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/12/23
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title></title>
    <style>
        #affiliated_form {
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
<div id="affiliated_info" class="hide">
    <form class="row form-horizontal" id="affiliated_form" action="<%=path%>/affiliatedInfo/insert">
        <div class="control-group span7">
            <label class="control-label" style="width: 60px;">法院：</label>

            <div class="controls">
                <select id="courtInfoNo" typeId="1" class="CourtInfocode" name="umsTemporaryPosition.courtCode"
                        onchange="loadDeptListByFydm(departmentInfo, this.value, '<option value=\'\'>请选择</option>')"
                        data-rules="{required:true}">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>

        <div class="control-group span7">
            <label class="control-label" style="width: 60px;">部门：</label>

            <div class="controls">
                <select id="departmentInfo" courtNo="" data-rules="{required:true}"
                        name="umsTemporaryPosition.department">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="control-group span7">
            <label class="control-label" style="width: 60px;">行政职务：</label>

            <div class="controls">
                <select id="administrationPositionInfo" courtNo=""
                        name="umsTemporaryPosition.administrationPosition">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="control-group span7">
            <label class="control-label" style="width: 60px;">法律职务：</label>

            <div class="controls">
                <select id="lawPositionInfo" courtNo=""
                        name="umsTemporaryPosition.lawPosition">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="control-group span7">
            <label class="control-label" style="width: 60px;">排序：</label>

            <div class="controls">
                <input type="text" id="deptSortNo" name="umsTemporaryPosition.sortNo" data-rules="{number:true}"
                       class="control-text" placeholder="排序">
            </div>
        </div>
        <input type="hidden" id="hideId" name="umsTemporaryPosition.uuid">
    </form>
    <button class="button button-primary" onclick="affiliatedSubmit()"
            style="display: inline-block;position: relative;top:-30px;">添加信息
    </button>
    <hr style="margin-top: 0px;"/>
    <div class="containner ">
        <div class="main_show">
            <table cellpadding="0" cellspacing="0" style="width: 750px;margin: 0 auto;margin-bottom: 20px;"
                   id="mainTable">
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


</body>
<script type="text/javascript">

    $("#courtInfoNo").select2();
    $("#departmentInfo").select2();
    $("#lawPositionInfo").select2();
    $("#administrationPositionInfo").select2();

    function initUpdate() {

        $("#mainTable").find(".sortNo").bind("dblclick", function () {

            var input = "<input type='text' id='temp' style='width:40px;' value=" + $(this).text() + " >";
            var oldValue = $(this).text().trim();
            $(this).text("");
            $(this).append(input);
            $("input#temp").focus();
            $("input#temp").blur(function () {
                var reg = new RegExp("^[0-9]{1,}$");
                if (reg.test($(this).val())) {
                    updateSortNo(this, $(this).val());
                    if ($(this).val() == "") {
                        $(this).remove();
                    } else {
                        $(this).closest("td").text($(this).val());
                    }
                } else {
                    BUI.Message.Alert("请输入一个有效的数字", "warning");
                    $(this).closest("td").text(oldValue);
                }


            });

        })
    }

    function updateSortNo(obj, val) {
        var o = $(obj).parent().parent().find(".show_a");
        var court = o.attr("court");
        var dept = o.attr("dept");
        var userid = o.attr("userid");
        var url = "<%=path%>/affiliatedInfo/update";
        var datas = {
            'umsTemporaryPosition.courtCode': court,
            'umsTemporaryPosition.id': userid,
            'umsTemporaryPosition.department': dept,
            'umsTemporaryPosition.sortNo': val,
        }
        $.post(url, datas, function (data) {
            var data = {
                'umsTemporaryPosition.uuid': $("#hideId").val(),
            }
            queryInfo(data);
        })

    }


    function loadDeptListByFydm(cbo, courtNo, firstLine, callback) {
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
                });
    }
</script>
</html>
