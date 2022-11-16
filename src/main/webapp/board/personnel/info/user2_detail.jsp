<%--
  Created by D.Yang.
  Date: 2014/12/30 0030
  Time: 14:49
--%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>人员基本信息</title>
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
                <div class="nav-item-inner nav-supplier">人员信息</div>
            </li>
            <s:if test="%{data.userType == 1}">
                <li class="nav-item">
                    <div class="nav-item-inner nav-supplier">干部任免审批表</div>
                </li>
                <%--<li class="nav-item">--%>
                <%--<div class="nav-item-inner nav-supplier">我的足迹</div>--%>
                <%--</li>--%>
            </s:if>
        </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-content">

    </ul>
</div>

<script type="text/javascript" src="<%=basePath%>js/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bui/bui.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bui/config.js"></script>

<script>
    $.getJSON("<%=basePath%>userinfo/one", {
        id: "<s:property value="id"></s:property>",
        _: new Date()
    }, function (userinfo) {

        BUI.use('common/main', function () {

            var userId = "<s:property value="id"></s:property>";

            var config = [{
                id: 'userinfo',
                homePage: "base",
                menu: [{
                    text: '详情描述',
//                                    collapsed : true,
                    items: [{
                        id: 'base',
                        text: '基本信息',
                        href: '<%=basePath%>view/basicinfo?id=' + userId
                    }, {
                        id: 'changeInfo',
                        text: '人员变化信息',
                        href: '<%=basePath%>view/userinfo/page?viewName=change_info&userId=' + userId
                    }, {
                        id: 'politics',
                        text: '政治面貌',
                        href: '<%=basePath%>view/userinfo/page?viewName=politicalInfo&userId=' + userId
                    }, {
                        id: 'administration',
                        text: '行政职务',
                        href: '<%=basePath%>view/userinfo/page?viewName=administrativeJob&userId=' + userId
                    }, {
                        id: 'legal',
                        text: '法律职务',
                        href: '<%=basePath%>view/userinfo/page?viewName=legalJob&userId=' + userId
                    }, {
                        id: 'rank',
                        text: '职级信息',
                        href: '<%=basePath%>view/userinfo/page?viewName=rankInfo&userId=' + userId
                    }, {
                        id: 'additional',
                        text: '兼任职务',
                        href: '<%=basePath%>view/userinfo/page?viewName=parttimePosition&userId=' + userId
                    }, {
                        id: 'level',
                        text: '等级信息',
                        href: '<%=basePath%>view/userinfo/page?viewName=levelInfo&userId=' + userId
                    }, {
                        id: 'servant',
                        text: '公务员级别',
                        href: '<%=basePath%>view/userinfo/page?viewName=civil_servant_level&userId=' + userId
                    }, {
                        id: 'education',
                        text: '学历信息',
                        href: '<%=basePath%>view/userinfo/page?viewName=education_info&userId=' + userId
                    }, {
                        id: 'degree',
                        text: '学位信息',
                        href: '<%=basePath%>view/userinfo/page?viewName=degree_info&userId=' + userId
                    }, {
                        id: 'study',
                        text: '在读信息',
                        href: '<%=basePath%>view/userinfo/page?viewName=study_info&userId=' + userId
                    }, {
                        id: 'train',
                        text: '培训信息',
                        href: '<%=basePath%>view/userinfo/page?viewName=training_info&userId=' + userId
                    }, {
                        id: 'resume',
                        text: '简历信息',
                        href: '<%=basePath%>view/userinfo/page?viewName=resume_info&userId=' + userId
                    }, {
                        id: 'family',
                        text: '家庭信息',
                        href: '<%=basePath%>view/userinfo/page?viewName=family_info&userId=' + userId
                    }, {
                        id: 'assess',
                        text: '考核信息',
                        href: '<%=basePath%>view/userinfo/page?viewName=assess_info&userId=' + userId
                    }, {
                        id: 'reward',
                        text: '奖励信息',
                        href: '<%=basePath%>view/userinfo/page?viewName=reward_info&userId=' + userId
                    }, {
                        id: 'punish',
                        text: '惩处信息',
                        href: '<%=basePath%>view/userinfo/page?viewName=punish_info&userId=' + userId
                    }, {
                        id: 'abroad',
                        text: '出国信息',
                        href: '<%=basePath%>view/userinfo/page?viewName=abroad_info&userId=' + userId
                    }, {
                        id: 'foreign',
                        text: '外语信息',
                        href: '<%=basePath%>view/userinfo/page?viewName=foreign_lang&userId=' + userId
                    }, {
                        id: 'exchange',
                        text: '交流信息',
                        href: '<%=basePath%>view/userinfo/page?viewName=exchange_info&userId=' + userId
                    }, {
                        id: 'judicial',
                        text: '司法考试',
                        href: '<%=basePath%>view/userinfo/page?viewName=judicial_exam&userId=' + userId
                    }, {
                        id: 'professional',
                        text: '专业技术职务',
                        href: '<%=basePath%>view/userinfo/page?viewName=pro_technical_position&userId=' + userId
                    }, {
                        id: 'casualties',
                        text: '伤亡信息',
                        href: '<%=basePath%>view/userinfo/page?viewName=casualty_info&userId=' + userId
                    }, {
                        id: 'remark',
                        text: '备注信息',
                        href: '<%=basePath%>view/userinfo/page?viewName=remark&userId=' + userId
                    }, {
                        id: 'av',
                        text: '声音与影像',
                        href: '<%=basePath%>view/userinfo/page?viewName=audio_video&userId=' + userId
                    }, {
                        id: 'salary',
                        text: '工资信息',
                        href: '<%=basePath%>view/userinfo/page?viewName=wage_info&userId=' + userId
                    }, {
                        id: 'reserve',
                        text: '后备干部',
                        href: '<%=basePath%>view/userinfo/page?viewName=reserve_cadre&userId=' + userId
                    }, {
                        id: 'contacts',
                        text: '通讯录',
                        href: '<%=basePath%>view/userinfo/page?viewName=contact&userId=' + userId
                    }, {
                        id: 'leader',
                        text: '领导班子',
                        href: '<%=basePath%>view/userinfo/page?viewName=leadership&userId=' + userId
                    }]
                }]
            },
                {
                    id: 'footprint',
                    homePage: "fp",
                    menu: [{
                        text: "干部任免审批表",
                        items: [{
                            id: "fp",
                            text: "干部任免审批表",
                            href: "<%=basePath%>board/personnel/info/formTable.jsp?userId=" + userId,
                            closeable: false
                        }]
                    }]
                }

                <%--{--%>
                <%--id: 'footprint',--%>
                <%--homePage: "fp",--%>
                <%--menu: [{--%>
                <%--text: "我的足迹",--%>
                <%--items: [{--%>
                <%--id: "fp",--%>
                <%--text: userinfo.fullname + "的足迹",--%>
                <%--href: "<%=basePath%>userinfo/footprint/timeline?userId=" + userId,--%>
                <%--closeable: false--%>
                <%--}]--%>
                <%--}]--%>
                <%--}--%>

            ];

            if ("<s:property value="data.userType"></s:property>" == 2) {
                config = [{
                    id: 'userinfo',
                    homePage: "base",
                    menu: [{
                        text: '详情描述',
//                        collapsed: true,
                        items: [{
                            id: 'base',
                            text: '基本信息',
                            href: '<%=basePath%>view/basicinfo2?id=' + userId
                        }, {
                            id: 'politics',
                            text: '政治面貌',
                            href: '<%=basePath%>view/userinfo/page?viewName=politicalInfo&userId=' + userId
                        }, {
                            id: 'education',
                            text: '学历信息',
                            href: '<%=basePath%>view/userinfo/page?viewName=education_info&userId=' + userId
                        }, {
                            id: 'degree',
                            text: '学位信息',
                            href: '<%=basePath%>view/userinfo/page?viewName=degree_info&userId=' + userId
                        }, {
                            id: 'study',
                            text: '在读信息',
                            href: '<%=basePath%>view/userinfo/page?viewName=study_info&userId=' + userId
                        }, {
                            id: 'train',
                            text: '培训信息',
                            href: '<%=basePath%>view/userinfo/page?viewName=training_info&userId=' + userId
                        }, {
                            id: 'resume',
                            text: '简历信息',
                            href: '<%=basePath%>view/userinfo/page?viewName=resume_info&userId=' + userId
                        }, {
                            id: 'family',
                            text: '家庭信息',
                            href: '<%=basePath%>view/userinfo/page?viewName=family_info&userId=' + userId
                        }, {
                            id: 'assess',
                            text: '考核信息',
                            href: '<%=basePath%>view/userinfo/page?viewName=assess_info&userId=' + userId
                        }, {
                            id: 'reward',
                            text: '奖励信息',
                            href: '<%=basePath%>view/userinfo/page?viewName=reward_info&userId=' + userId
                        }, {
                            id: 'punish',
                            text: '惩处信息',
                            href: '<%=basePath%>view/userinfo/page?viewName=punish_info&userId=' + userId
                        }, {
                            id: 'abroad',
                            text: '出国信息',
                            href: '<%=basePath%>view/userinfo/page?viewName=abroad_info&userId=' + userId
                        }, {
                            id: 'foreign',
                            text: '外语信息',
                            href: '<%=basePath%>view/userinfo/page?viewName=foreign_lang&userId=' + userId
                        }, {
                            id: 'exchange',
                            text: '交流信息',
                            href: '<%=basePath%>view/userinfo/page?viewName=exchange_info&userId=' + userId
                        }, {
                            id: 'judicial',
                            text: '司法考试',
                            href: '<%=basePath%>view/userinfo/page?viewName=judicial_exam&userId=' + userId
                        }, {
                            id: 'professional',
                            text: '专业技术职务',
                            href: '<%=basePath%>view/userinfo/page?viewName=pro_technical_position&userId=' + userId
                        }, {
                            id: 'casualties',
                            text: '伤亡信息',
                            href: '<%=basePath%>view/userinfo/page?viewName=casualty_info&userId=' + userId
                        }, {
                            id: 'remark',
                            text: '备注信息',
                            href: '<%=basePath%>view/userinfo/page?viewName=remark&userId=' + userId
                        }, {
                            id: 'av',
                            text: '声音与影像',
                            href: '<%=basePath%>view/userinfo/page?viewName=audio_video&userId=' + userId
                        }, {
                            id: 'salary',
                            text: '工资信息',
                            href: '<%=basePath%>view/userinfo/page?viewName=wage_info&userId=' + userId
                        }, {
                            id: 'contacts',
                            text: '通讯录',
                            href: '<%=basePath%>view/userinfo/page?viewName=contact&userId=' + userId
                        }, {
                            id: 'labour_contract',
                            text: '劳动合同',
                            href: '<%=basePath%>view/userinfo/page?viewName=labour_contract&userId=' + userId
                        }, {
                            id: 'external_job',
                            text: '职务信息',
                            href: '<%=basePath%>view/userinfo/page?viewName=external_user_job&userId=' + userId
                        }]
                    }]
                }];

            }

            if ("<s:property value="data.userType"></s:property>" == 3) {
                config = [{
                    id: 'userinfo',
                    homePage: "base",
                    menu: [{
                        text: '详情描述',
//                        collapsed: true,
                        items: [{
                            id: 'base',
                            text: '基本信息',
                            href: '<%=basePath%>view/basicinfo3?id=' + userId
                        }, {
                            id: 'politics',
                            text: '政治面貌',
                            href: '<%=basePath%>view/userinfo/page?viewName=politicalInfo&userId=' + userId
                        }, {
                            id: 'juror_job_info',
                            text: '陪审员职务',
                            href: '<%=basePath%>view/userinfo/page?viewName=juror_job_info&userId=' + userId
                        }, {
                            id: 'education',
                            text: '学历信息',
                            href: '<%=basePath%>view/userinfo/page?viewName=education_info&userId=' + userId
                        }, {
                            id: 'degree',
                            text: '学位信息',
                            href: '<%=basePath%>view/userinfo/page?viewName=degree_info&userId=' + userId
                        }, {
                            id: 'study',
                            text: '在读信息',
                            href: '<%=basePath%>view/userinfo/page?viewName=study_info&userId=' + userId
                        }, {
                            id: 'train',
                            text: '培训信息',
                            href: '<%=basePath%>view/userinfo/page?viewName=training_info&userId=' + userId
                        }, {
                            id: 'resume',
                            text: '简历信息',
                            href: '<%=basePath%>view/userinfo/page?viewName=resume_info&userId=' + userId
                        }, {
                            id: 'family',
                            text: '家庭信息',
                            href: '<%=basePath%>view/userinfo/page?viewName=family_info&userId=' + userId
                        }, {
                            id: 'assess',
                            text: '考核信息',
                            href: '<%=basePath%>view/userinfo/page?viewName=assess_info&userId=' + userId
                        }, {
                            id: 'reward',
                            text: '奖励信息',
                            href: '<%=basePath%>view/userinfo/page?viewName=reward_info&userId=' + userId
                        }, {
                            id: 'punish',
                            text: '惩处信息',
                            href: '<%=basePath%>view/userinfo/page?viewName=punish_info&userId=' + userId
                        }, {
                            id: 'judicial',
                            text: '司法考试',
                            href: '<%=basePath%>view/userinfo/page?viewName=judicial_exam&userId=' + userId
                        }, {
                            id: 'professional',
                            text: '专业技术职务',
                            href: '<%=basePath%>view/userinfo/page?viewName=pro_technical_position&userId=' + userId
                        }, {
                            id: 'remark',
                            text: '备注信息',
                            href: '<%=basePath%>view/userinfo/page?viewName=remark&userId=' + userId
                        }, {
                            id: 'av',
                            text: '声音与影像',
                            href: '<%=basePath%>view/userinfo/page?viewName=audio_video&userId=' + userId
                        }, {
                            id: 'contacts',
                            text: '通讯录',
                            href: '<%=basePath%>view/userinfo/page?viewName=contact&userId=' + userId
                        }]
                    }]
                }];
            }
            new PageUtil.MainPage({
                modulesConfig: config
            });
        });
    });
</script>
<jsp:include page="/changePassword.jsp"/>
</body>
</html>
