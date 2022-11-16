<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html>
<html>
    <head>
        <base href="<%=basePath%>">
        <title>RunJS</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="js/mmgrid/mmGrid.css" rel="stylesheet" type="text/css"/>
        <link href="js/mmgrid/mmPaginator.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery/jquery.js" type="text/javascript"></script>
        <script src="js/mmgrid/mmGrid.js" type="text/javascript"></script>
        <script src="js/mmgrid/mmPaginator.js" type="text/javascript"></script>
        <style>
            html,body{
                height: 95%;
            }
        </style>
    </head>
    <body>
        <button onclick="">添加</button>
        <table id="table_user_info"></table>
        <div style="text-align:right;">
            <div id="table_user_info_pagingator"></div>
        </div>
    </body>
    <script>
        var user_info_cols = [
            {title: '法院编号', name: 'courtNo', width: 50, sortable: true, align: 'left'},
            {title: '人员编号', name: 'userNo', width: 80, sortable: true, align: 'left'},
            {title: '法院', name: 'courtNoText', width: 150, sortable: true, align: 'left'},
            {title: '姓名', name: 'fullname', width: 50, sortable: true, align: 'left'},
            {title: '曾用名', name: 'formerName', width: 50, sortable: true, align: 'left'},
            {title: '性别', name: 'genderText', width: 50, sortable: true, align: 'left'},
            //
            {title: '部门', name: 'departmentText', width: 150, sortable: true, align: 'left'},
            {title: '岗位性质', name: 'positionNatureText', width: 50, sortable: true, align: 'left'},
            {title: '编制', name: 'preparationText', width: 100, sortable: true, align: 'left'},
            {title: '职务类别', name: 'positionTypeText', width: 100, sortable: true, align: 'left'},
            {title: '职务就职日期', name: 'positionTypeDate', width: 100, sortable: true, align: 'left'},
            {title: '任用方式', name: 'assignText', width: 50, sortable: true, align: 'left'},
            //
            {title: '籍贯', name: 'hometown', width: 100, sortable: true, align: 'left'},
            {title: '出生地', name: 'birthplace', width: 100, sortable: true, align: 'left'},
            {title: '出生日期', name: 'birthday', width: 100, sortable: true, align: 'left'},
            {title: '健康状态', name: 'physicalConditionText', width: 50, sortable: true, align: 'left'},
            {title: '婚姻状态', name: 'maritalStatusText', width: 50, sortable: true, align: 'left'},
            {title: '民族', name: 'nationText', width: 50, sortable: true, align: 'left'},
            {title: '身份证', name: 'idcard', width: 150, sortable: true, align: 'left'},
            //
            {title: '学历', name: 'educationBackgroundText', width: 80, sortable: true, align: 'left'},
            {title: '专业', name: 'majorText', width: 50, sortable: true, align: 'left'},
            {title: '学位', name: 'degreeText', width: 50, sortable: true, align: 'left'},
            {title: '获得学位日期', name: 'degreeDate', width: 100, sortable: true, align: 'left'},
            {title: '工作日期', name: 'workDate', width: 100, sortable: true, align: 'left'},
            {title: '进院日期', name: 'enterDate', width: 100, sortable: true, align: 'left'},
            {title: '专业证书', name: 'proCertText', width: 100, sortable: true, align: 'left'},
            {title: '获得专业证书日期', name: 'proCertDate', width: 100, sortable: true, align: 'left'},
            {title: '政治面貌', name: 'politicalText', width: 50, sortable: true, align: 'left'},
            {title: '政治面貌日期', name: 'politicalDate', width: 100, sortable: true, align: 'left'},
            //
            {title: '政法工作日期', name: 'politicLawWorkDate', width: 100, sortable: true, align: 'left'},
            {title: '行政职务', name: 'administrationPositionText', width: 80, sortable: true, align: 'left'},
            {title: '行政职务日期', name: 'administrationPositionDate', width: 100, sortable: true, align: 'left'},
            {title: '法律职务', name: 'lawPositionText', width: 80, sortable: true, align: 'left'},
            {title: '法律职务日期', name: 'lawPositionDate', width: 100, sortable: true, align: 'left'},
            {title: '兼任庭长', name: 'isParttimePresidingJudgeText', width: 50, sortable: true, align: 'left'},
            {title: '党组任职', name: 'partyOfficeText', width: 50, sortable: true, align: 'left'},
            {title: '党组任职日期', name: 'partyOfficeDate', width: 100, sortable: true, align: 'left'},
            //
            {title: '法官资格日期', name: 'lawyerDate', width: 100, sortable: true, align: 'left'},
            {title: '法官资格证书', name: 'lawyerCertText', width: 100, sortable: true, align: 'left'},
            {title: '法官资格证书日期', name: 'lawyerCertDate', width: 100, sortable: true, align: 'left'},
            //
            {title: '补充工龄', name: 'extraSeniority', width: 50, sortable: true, align: 'left'},
            {title: '扣减工龄', name: 'deductionSeniority', width: 50, sortable: true, align: 'left'},
            {title: '进本院前法院工作年限', name: 'beforeCourtWorkYear', width: 50, sortable: true, align: 'left'},
            //
            {title: '职级', name: 'rankText', width: 50, sortable: true, align: 'left'},
            {title: '职级日期', name: 'rankDate', width: 100, sortable: true, align: 'left'},
            {title: '等级', name: 'levelText', width: 80, sortable: true, align: 'left'},
            {title: '等级日期', name: 'levelDate', width: 100, sortable: true, align: 'left'},
            {title: '公务员级别', name: 'servantLevelText', width: 80, sortable: true, align: 'left'},
            {title: '公务员级别日期', name: 'servantLevelDate', width: 100, sortable: true, align: 'left'},
            //
            {title: '进入途径', name: 'enterWayText', width: 60, sortable: true, align: 'left'},
            {title: '进入来源', name: 'enterSourceText', width: 60, sortable: true, align: 'left'},
            {title: '原职务', name: 'formerPostText', width: 50, sortable: true, align: 'left'},
            {title: '原职级', name: 'formerRankText', width: 50, sortable: true, align: 'left'},
            {title: '原单位', name: 'formerUnit', width: 100, sortable: true, align: 'left'},
            {title: '调离原因', name: 'leaveReasonText', width: 50, sortable: true, align: 'left'},
            {title: '调离日期', name: 'leaveDate', width: 100, sortable: true, align: 'left'},
            {title: '调离去向', name: 'leaveDestinationText', width: 100, sortable: true, align: 'left'},
            {title: '审核日期', name: 'verifyDate', width: 100, sortable: true, align: 'left'},
            //
            {title: '应加学制', name: 'additionalDuration', width: 50, sortable: true, align: 'left'},
            //
            {title: '账号', name: 'username', width: 50, sortable: true, align: 'left'},
            {title: '编号', name: 'userCode', width: 100, sortable: true, align: 'left'},
            {title: '法院代码', name: 'courtCode', width: 50, sortable: true, align: 'left'},
            //
            {title: '是否有效', name: 'isValidText', width: 50, sortable: true, align: 'left'}
        ];

        $("#table_user_info").mmGrid({
            cols: user_info_cols,
            method: "get",
            url: "view/userinfo",
            remoteSort: true,
            root: "items",
            limit: 20,
            height: "100%",
            width: "100%",
            plugins: [
                $("#table_user_info_pagingator").mmPaginator()
            ]
        });
    </script>
</html>
