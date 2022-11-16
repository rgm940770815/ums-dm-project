<%@ page import="cn.net.withub.ums.common.UmsConstant" %>
<%@ page import="cn.net.withub.ums.entity.UmsUserInfoView" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="cn.net.withub.ums.auth.AuthType" %><%--
  Created by D.Yang.
  Date: 2015/2/3 0003
  Time: 10:16
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--<%@ page contentType="text/html" pageEncoding="UTF-8" %>--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
//    特殊处理 （莫运垠moyy/admin）2018年10月16日 该账号需要在自定义信息查询在编人员时，能够选择法院，这里进行了特殊处理
    UmsUserInfoView userInfo = (UmsUserInfoView)request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
    String userId = userInfo.getId();
    String username = userInfo.getUsername();
    String fullname = userInfo.getFullname();
    if (username.equals("moyy") && fullname.equals("莫运垠")) {
        userInfo.setFullname("高院管理员");//该账号在登陆时，会显示为欢迎您，高院管理员
    }
//    特殊处理结束
    //根据权限范围 添加通知
    AuthType type = null;
    try {
        type = (AuthType) ActionContext.getContext().getSession().get(UmsConstant.CURRENT_USER_AUTH.toString());
    } catch (Exception e) {
        e.printStackTrace();
    }
    //通知标记
    boolean noticeFlag = false;
    if((type == AuthType.ADMIN || type == AuthType.COURT)){
        noticeFlag = true;
    }
%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>重庆法院人事管理系统</title>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <jsp:include page="/basic_import.jsp"></jsp:include>

        <script type="text/javascript" src="<%=basePath%>js/bui/common/main.js"></script>
        <link href="<%=basePath%>js/bui/css/main-min.css" rel="stylesheet" type="text/css"/>
        <link href="<%=basePath%>css/main.css" rel="stylesheet" type="text/css"/>
        <link type="text/css" rel="stylesheet" href="common/fontawesome/css/font-awesome.min.css">
        <style type="text/css">
            .content .dl-second-tree{
                width: 165px;
            }
            .content .dl-second-nav{
                width: 165px;
            }
            .content .dl-inner-tab{
                margin-left: 165px
            }

        </style>
    </head>
    <body>
		
        <!-- header -->
        <div class="header">
            <div class="logo">
                <img src="<%=basePath%>images/logo4.png" style="height: 50px;width: 50px;">
            </div>
            <div class="dl-title">
                <span class="lp-title-port">${loginUser.courtNoText}</span><span class="dl-title-text">人事管理系统</span>
            </div>

            <div class="dl-log">欢迎您，<span class="dl-log-user">${loginUser.fullname}

                <%if (noticeFlag) {%>
                <i class="fa fa-bell-o fa-notice-verify"></i>
                    <%}%>
            </span>
                <a id="btnChangePwd" href="#" title="修改密码" class="dl-log-quit">[修改密码]</a>
                <a href="<%=basePath%>logout" title="退出系统" class="dl-log-quit">[退出]</a>
                <a href="###" title="技术支持" class="dl-log-quit">技术支持</a>
            </div>
        </div>

        <%-- body --%>
        <div class="content">
            <div class="dl-main-nav">
                <ul id="J_Nav" class="nav-list ks-clear">
<%--                    <li class="nav-item dl-selected">--%>
<%--                        <div class="nav-item-inner nav-supplier">人事管理</div>--%>
<%--                    </li>--%>
<%--                    <li class="nav-item">--%>
<%--                        <div class="nav-item-inner nav-inventory">权限管理</div>--%>
<%--                    </li>--%>
<%--                    <li class="nav-item">--%>
<%--                        <div class="nav-item-inner nav-marketing">统计图表</div>--%>
<%--                    </li>--%>
                </ul>
            </div>
            <ul id="J_NavContent" class="dl-tab-content">

            </ul>
        </div>

        <script>
            var roleMap = {};
            var leadRole = false;

            $.getJSON("<%=basePath%>auth/role/userRoles", {userId:"<%=userId%>"}, (roles)=> {
                roles.forEach(function (role){
                    roleMap[role.id] = role
                    if(role.id == '47'){
                        leadRole = true;
                    }
                })
                //菜单是从过AJAX在后台查询得出的详细列表
                $.getJSON("<%=basePath%>menu/myMenu", {}, (data)=> {

                    var addPage = "";
                    for (var i = 0; i < data.length; i++) {
                        //alert(data[i].name);
                        addPage += initMenu(data[i].name);
                    }
                    $(J_Nav).html(addPage);
                    //alert(JSON.stringify(data));
                    BUI.use('common/main', function () {
                        new PageUtil.MainPage({
                            modulesConfig: data
                        });
                    });
                });


            })



            function initMenu(name) {
                switch (name) {
                    case "信息管理":
                        if(leadRole){
                            return '<li class="nav-item"><div class="nav-item-inner nav-supplier">人员信息</div></li>';
                        }else{
                            return '<li class="nav-item dl-selected "><div class="nav-item-inner nav-supplier">人员信息</div></li>';
                        }
                    case "权限模块":
                        return '<li class="nav-item"><div class="nav-item-inner nav-inventory">系统管理</div></li>';
                    case "领导查询":
                        if(leadRole){
                            return '<li class="nav-item dl-selected"><div class="nav-item-inner nav-marketing">队伍结构</div></li>';
                        }else{
                            return '<li class="nav-item"><div class="nav-item-inner nav-marketing">队伍结构</div></li>';
                        }
                    case "信息查询":
                        return '<li class="nav-item"><div class="nav-item-inner nav-marketing">信息查询</div></li>';
                    case "统计报表":
                        return '<li class="nav-item"><div class="nav-item-inner nav-marketing">统计报表</div></li>';
                    default :
                        return "";
                }
            }
        </script>
        <jsp:include page="/changePassword.jsp"/>
        <jsp:include page="/verifyNotice.jsp"/>

    </body>
</html>
