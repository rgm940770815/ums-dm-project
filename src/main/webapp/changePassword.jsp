<%--
    Document   : changePassword
    Created on : 2014-12-31, 14:25:04
    Author     : Diluka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!--<!DOCTYPE HTML>
<html>
    <head>
        <base href="<%=basePath%>">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="js/bui/css/bs3/bui.css" rel="stylesheet" type="text/css"/>
        <link href="js/bui/css/bs3/dpl.css" rel="stylesheet" type="text/css"/>

        <script src="js/jquery/jquery.js" type="text/javascript"></script>

        <script src="js/bui/bui.js" type="text/javascript"></script>-->
<%--<script src="js/crypto-js/rollups/md5.js" type="text/javascript"></script>--%>
<%--<script src="js/base64/base64.js" type="text/javascript"></script>--%>
<script src="<%=basePath%>js/crypto-js/rollups/md5.js" type="text/javascript"></script>
<script src="<%=basePath%>js/base64/base64.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>js/encodeParam.js"></script>
<!--    </head>
    <body>-->
<!--        <h1>Hello World!</h1>
        <div>
            <button id="btn">修改密码</button>
        </div>-->
<div id="dialog" class="hide">
    <form id="change_form" action="<%=basePath%>userinfo/password/change" method="post">
        <div class="span11">

            <div class="control-group row">
                <h4 style="margin-bottom: 10px;">密码长度应不少于8位不多于30位，且密码中至少包含数字、字母</h4>
                <div class="controls row">
                    <label class="control-label" for="oldPwd">原密码：</label>
                </div>
                <input type="password" id="oldPwd" class="control-text">
                <input type="hidden" id="e_oldPwd" name="oldPassword">
            </div>

            <div class="control-group row">
                <div class="controls row">
                    <label class="control-label" for="newPwd">新密码：</label>
                </div>
                <input type="password" id="newPwd" class="control-text">
                <input type="hidden" id="e_newPwd" name="newPassword">
            </div>

            <div class="control-group row">
                <div class="controls row">
                    <label class="control-label" for="newPwd2">重复新密码：</label>
                </div>
                <input type="password" id="newPwd2" class="control-text" data-rules="{equalTo:'#newPwd'}">
            </div>
        </div>
        <div class="hide">
            <button id="btnReset" type="reset"></button>
        </div>
    </form>
</div>
<!--    </body>-->
<script>
    BUI.use(['bui/overlay', 'bui/form'], function (Overlay, Form) {
        var form = new Form.HForm({
            srcNode: "#change_form",
            submitType: 'ajax',
            callback: function (data) {
                BUI.Message.Alert(data.msg, function () {
                    if (data.success) {
                        dialog.close();
                    }
                }, data.success ? "success" : "warning");
            }
        }).render();

        var dialog = new Overlay.Dialog({
            title: '修改密码',
            width: 480,
            height: 280,
            buttons: [
                {
                    text: '修改', elCls: 'button button-primary', handler: function () {
                        var o_p = $("#change_form #oldPwd").val();
                        var n_p = $("#change_form #newPwd").val();
                        if(o_p){
                            $("#change_form #e_oldPwd").val(encodeParam(o_p));
                        }
                        if(n_p){
                            //检查复杂性
                            var pwdRegex = new RegExp('(?=.*[0-9])(?=.*[a-zA-Z]).{8,30}');
                            if (!pwdRegex.test(n_p)) {
                                BUI.Message.Alert("您的密码复杂度太低（密码长度不少于8位且必须包含字母、数字），请及时修改密码！", null, "warning");
                                return;
                            }
                            $("#change_form #e_newPwd").val(encodeParam(n_p));
                        }

//                        passwordEncrypt($("#change_form #oldPwd"), $("#change_form #e_oldPwd"));
//                        passwordEncrypt($("#change_form #newPwd"), $("#change_form #e_newPwd"));
                        $("#change_form [type='text']").prop("disabled", true);

                        form.submit();
                    }
                }, {
                    text: '取消', elCls: 'button', handler: function () {
                        this.close();
                    }
                }],
            contentId: 'dialog', //配置DOM容器的编号
            success: function () {
                this.close();
            }
        });

        $("#btnChangePwd").click(function () {
            $("#change_form #btnReset").click();
            $("#change_form input").prop("disabled", false);
            dialog.show();
        });

        function passwordEncrypt(pwd, epwd) {
            var str = CryptoJS.enc.Utf8.parse($(pwd).val());
            var hash = CryptoJS.MD5(str).toString();
            var base64 = Base64.encode(hash);
            $(epwd).val(base64);
        }
    });
</script>
<!--</html>-->
