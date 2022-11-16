<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>批量修改信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="<%=basePath%>js/bui/css/dpl-min.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>js/bui/css/bui-min.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>js/bui/css/main-min.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css"/>
    <style type="text/css">

        .nav-item {
            width: 145px;
        }
        
    </style>
</head>
<body>
<!-- header -->
<div class="header">
    <div class="logo">
        <img src="<%=basePath%>images/logo2.png">
    </div>
    <div class="dl-title">
        <span class="lp-title-port">${loginUser.courtNoText}</span><span class="dl-title-text">人事管理系统</span>
    </div>

    <div class="dl-log">欢迎您，<span class="dl-log-user">${loginUser.fullname}</span>
        <a id="btnChangePwd" href="#" title="修改密码" class="dl-log-quit">[修改密码]</a>
        <a href="<%=basePath%>logout" title="退出系统" class="dl-log-quit">[退出]</a>
        <a href="###" title="技术支持" class="dl-log-quit">技术支持</a>
    </div>
</div>

<%--body--%>
<div class="content">
    <div class="dl-main-nav">
        <div class="dl-inform">
            <div class="dl-inform-title">
                贴心小秘书<s class="dl-inform-icon dl-up"></s>
            </div>
        </div>
        <ul id="J_Nav" class="nav-list ks-clear">
            <li class="nav-item dl-selected">
                <div class="nav-item-inner nav-supplier">批量修改信息</div>
            </li>
        </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-content">

    </ul>
</div>

<script type="text/javascript" src="<%=basePath%>js/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bui/bui.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bui/config.js"></script>

<script>

    BUI.use('common/main', function () {

        var courtNo = "<s:property value="courtNo"></s:property>"
        var deptNo = "<s:property value="deptNo"></s:property>"
        console.log(courtNo);
        console.log(deptNo);
        var config = [{
            id: 'userinfo',
            homePage: "legal",
            menu: [{
                text: '详情描述',
                items: [{
                    id: 'legal',
                    text: '法律职务',
                    href: '<%=basePath%>view/userinfo/page?viewName=legalJob_batch&courtNo='+courtNo+'&deptNo='+deptNo
                }, {
                    id: 'rank',
                    text: '职级信息',
                    href: '<%=basePath%>view/userinfo/page?viewName=rankInfo_batch&courtNo='+courtNo+'&deptNo='+deptNo
                },
                <%--    {--%>
                <%--    id: 'level',--%>
                <%--    text: '等级信息',--%>
                <%--    href: '<%=basePath%>view/userinfo/page?viewName=levelInfo_batch&courtNo='+courtNo+'&deptNo='+deptNo--%>
                <%--}, --%>
                    {
                    id: 'assess',
                    text: '考核信息',
                    href: '<%=basePath%>view/userinfo/page?viewName=assess_info_batch&courtNo='+courtNo+'&deptNo='+deptNo
                }, {
                    id: 'reward',
                    text: '奖励信息',
                    href: '<%=basePath%>view/userinfo/page?viewName=reward_info_batch&courtNo='+courtNo+'&deptNo='+deptNo
                }]
            }]
        }];
        for(let i in config){
            console.log(config[i]);
        }
        new PageUtil.MainPage({
            modulesConfig: config
        });
    });
</script>
<jsp:include page="/changePassword.jsp"/>
</body>
</html>
