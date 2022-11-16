<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<c:if test="${currUserAuth == 'ERROR'}">
    <c:redirect url="/ums"></c:redirect>
</c:if>
<c:if test="${currUserAuth == 'PERSON'}">
    <c:redirect url="/ums/view/detail?id=${loginUser.id}"></c:redirect>
</c:if>
<!DOCTYPE HTML>
<html>
    <head>
        <title>重庆法院人事管理系统</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link href="<%=basePath%>js/bui/css/bs3/dpl-min.css" rel="stylesheet" type="text/css"/>
        <link href="<%=basePath%>js/bui/css/bs3/bui-min.css" rel="stylesheet" type="text/css"/>
        <link href="<%=basePath%>js/bui/css/main-min.css" rel="stylesheet" type="text/css"/>
        <link href="<%=basePath%>css/main.css" rel="stylesheet" type="text/css"/>
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
                <a href="/ums/logout" title="退出系统" class="dl-log-quit">[退出]</a>
                <a href="###" title="技术支持" class="dl-log-quit">技术支持</a>
            </div>
        </div>

        <div class="content">
            <div class="dl-main-nav">
                <div class="dl-inform">
                    <div class="dl-inform-title">
                        贴心小秘书<s class="dl-inform-icon dl-up"></s>
                    </div>
                </div>
                <ul id="J_Nav" class="nav-list ks-clear">
                    <li class="nav-item dl-selected">
                        <div class="nav-item-inner nav-supplier">人事管理</div>
                    </li>
                    <%--<li class="nav-item">--%>
                    <%--<div class="nav-item-inner nav-order">编码格式</div>--%>
                    <%--</li>--%>
                    <c:if test="${currUserAuth == 'ADMIN'}">
                        <li class="nav-item">
                            <div class="nav-item-inner nav-inventory">权限管理</div>
                        </li>
                    </c:if>
                    <li class="nav-item">
                        <div class="nav-item-inner nav-marketing">统计图表</div>
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
                    var config = [{
                    // 人事管理大模块 personnel
                    id: 'personnel',
                            homePage: 'user',
                            menu: [{
                            // 信息管理模块 info
                            text: '信息管理',
                                    items: [{
                                    id: 'user',
                                            closeable: false,
                                            text: '管理人员信息',
                                            href: '<%=basePath%>board/personnel/info/user2.jsp'
                                    }, {
                                    id: 'log',
                                            text: '信息修改日志',
                                            href: '<%=basePath%>board/personnel/info/log.jsp'
                                    }/*, {
                                     id: 'dept',
                                     text: '部门检查',
                                     href: '<%=basePath%>board/personnel/info/dept.jsp'
                                     }, {
                                     id: 'operator',
                                     text: '导入人员',
                                     href: '<%=basePath%>board/personnel/info/operator.jsp'
                                     }, {
                                     id: 'message',
                                     text: '查看留言',
                                     href: '<%=basePath%>board/personnel/info/message.jsp'
                                     }, {
                                     id: 'custom',
                                     text: '自定义模板',
                                     href: '<%=basePath%>board/personnel/info/custom.jsp'
                                     }, {
                                     id: 'interface',
                                     text: '对外接口',
                                     href: '<%=basePath%>board/personnel/info/interface.jsp'
                                     }, {
                                     id: 'assessment',
                                     text: '批量考核',
                                     href: '<%=basePath%>board/personnel/info/assessment.jsp'
                                     }*/]
                            }/*, {
                             // 信息查询模块 search
                             text: '信息查询',
                             collapsed: true,
                             items: [{
                             id: 'position',
                             text: '人员定位',
                             href: '<%=basePath%>board/personnel/search/position.jsp'
                             }, {
                             id: 'query',
                             text: '组合查询',
                             href: '<%=basePath%>board/personnel/search/query.jsp'
                             }, {
                             id: 'custom_query',
                             text: '自定义查询',
                             href: '<%=basePath%>board/personnel/search/custom.jsp'
                             }]
                             }, {
                             // 领导查询模块 leader
                             text: '领导查询',
                             collapsed: true,
                             items: [{
                             id: 'leader_query',
                             text: '领导查询',
                             href: '<%=basePath%>board/personnel/leader/query.jsp'
                             }]
                             }, {
                             // 统计报表模块 statistics
                             text: '统计报表',
                             collapsed: true,
                             items: [{
                             id: 'regular',
                             text: '固定统计',
                             href: '<%=basePath%>board/personnel/statistics/regular.jsp'
                             }, {
                             id: 'organization',
                             text: '组织部统计',
                             href: '<%=basePath%>board/personnel/statistics/organization.jsp'
                             }, {
                             id: 'custom_count',
                             text: '自定义统计',
                             href: '<%=basePath%>board/personnel/statistics/custom.jsp'
                             }]
                             }, {
                             // 数据报送模块 submission
                             text: '数据报送',
                             collapsed: true,
                             items: [{
                             id: 'submit',
                             text: '数据报送',
                             href: '<%=basePath%>board/personnel/submission/submit.jsp'
                             }]
                             }, {
                             // 文档管理模块 document
                             text: '文档管理',
                             collapsed: true,
                             items: [{
                             id: 'doc',
                             text: '文档管理',
                             href: '<%=basePath%>board/personnel/document/doc.jsp'
                             }]
                             }, {
                             // 单位管理模块 department
                             text: '单位信息',
                             collapsed: true,
                             items: [{
                             id: 'depart',
                             text: '维护单位信息',
                             href: '<%=basePath%>board/personnel/department/depart.jsp'
                             }, {
                             id: 'institution',
                             text: '维护内部机构',
                             href: '<%=basePath%>board/personnel/department/institution.jsp'
                             }, {
                             id: 'reward',
                             text: '维护奖励信息',
                             href: '<%=basePath%>board/personnel/department/reward.jsp'
                             }]
                             }, {
                             // 其他管理模块 other
                             text: '其他管理',
                             collapsed: true,
                             items: [{
                             id: 'roster',
                             text: '人员花名册',
                             href: '<%=basePath%>board/personnel/other/roster.jsp'
                             }]
                             }*/]
                    }, /* {
                     // 业绩考核大模块（performance）
                     id: 'performance',
                     menu: [{
                     text: '搜索页面',
                     items: [{
                     id: 'code',
                     text: '搜索页面代码',
                     href: '<%=basePath%>search/code.html'
                     }]
                     }]
                     },*/
            <c:if test="${currUserAuth == 'ADMIN'}">
                    {
                    // 权限模块（meeting）
                    id: 'permission',
                            menu: [{
                            text: '权限管理',
                                    items: [{
                                    id: 'role',
                                            text: '角色管理',
                                            href: '/ums/board/permission/role.jsp'
                                    }, {
                                    id: 'auth',
                                            text: '权限管理',
                                            href: '/ums/board/permission/auth.jsp'
                                    }]
                            }]
                    },
            </c:if>
                    {
                    // 统计模块（work）
                    id: 'statistics',
                            homePage: 'chart',
                            menu: [{
                            text: '统计图表',
                                    items: [{
                                    id: 'chart',
                                            text: '统计图表',
                                            href: '/ums/board/statistics/chart.jsp'
                                    }]
                            }]
                    }];
                            new PageUtil.MainPage({
                            modulesConfig: config
                            });
                    });
        </script>
        <jsp:include page="changePassword.jsp"></jsp:include>
    </body>
</html>
