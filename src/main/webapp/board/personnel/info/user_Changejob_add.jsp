<%--
  Created by IntelliJ IDEA.
  User: D.Yang
  Date: 2014/12/27 0027
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<script type="text/javascript" src="<%=basePath%>js/piny.js"></script>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--新增页面弹出层--%>
<div id="content" class="hide">
    <a name="baseInfo"></a>

    <form id="userinfo_form" class="form-horizontal" action="<%=basePath%>userinfo/save" method="post">
        <input type="hidden" name="id" id="new_id">
        <input type="hidden" name="userInfo.id" id="id">
        <%--基本信息--%>
        <div id="basic-info">
            <a name="show_error"></a>

            <div class="row">
                <h3 style="width:100px;display: inline-block">基本信息</h3>
                <%--<h5 style="display: inline-block">用户名密码默认为身份证后六位数字</h5>--%>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>姓名：</label>

                    <div class="controls">
                        <input id="fullname" name="userInfo.fullname" type="text" placeholder="请输入姓名"
                               class="control-text" data-rules="{required:true}" onchange="generateUserName(this)">

                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>法院：</label>

                    <div class="controls">
                        <select id="courtNo" typeId="1" class="Courtcode" name="userInfo.courtNo"
                                onchange="loadDeptList(department, this.value, '<option value=\'\'>请选择</option>')"
                                data-rules="{required:true}"></select>
                    </div>
                </div>

                <div class="control-group span8">
                    <label class="control-label"><s>*</s>部门：</label>

                    <div class="controls">
                        <select id="department" courtNo="" data-rules="{required:true}" name="userInfo.department">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>

            </div>
            <div class="row">

                <div class="control-group span8">
                    <label class="control-label">曾用名：</label>

                    <div class="controls">
                        <input id="formerName" name="userInfo.formerName" type="text" placeholder="请输入曾用名"
                               class="control-text">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>性别：</label>

                    <div class="controls">
                        <select id="gender" typeId="3" class="code" data-rules="{required:true}"
                                name="userInfo.gender"></select>
                    </div>
                </div>


                <div class="control-group span11">

                    <a id="show_e" href="#show_error"></a>
                    <label class="control-label"><s>*</s>用户名：</label>

                    <div class="controls">
                        <input id="username" name="userInfo.username" type="text" placeholder="请输入用户名"
                               class="control-text" data-rules="{required:true}" onchange="cleanError()">
                        <span class="show_icon">!</span>
                        <span class="show_info">用户名已存在</span>


                    </div>
                </div>
            </div>

            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">编号：</label>

                    <div class="controls">
                        <input id="userCode" name="userInfo.userCode" type="text" placeholder="请输入用户编号"
                               class="control-text">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>民族：</label>

                    <div class="controls">
                        <select id="nation" name="userInfo.nation" typeId="5" class="code select22"
                                data-rules="{required:true}"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">出生地：</label>

                    <div class="controls">
                        <input id="birthplace" name="userInfo.birthplace" type="text" placeholder="请输入用户出生地"
                               class="control-text">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>籍贯：</label>

                    <div class="controls">
                        <input id="hometown" name="userInfo.hometown" type="text" placeholder="请输入用户籍贯"
                               class="control-text" data-rules="{required:true}">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>婚姻状况：</label>

                    <div class="controls">
                        <select id="maritalStatus" typeId="6" class="code" data-rules="{required:true}"
                                name="userInfo.maritalStatus"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>健康状况：</label>

                    <div class="controls">
                        <select id="physicalCondition" typeId="10" class="code" data-rules="{required:true}"
                                name="userInfo.physicalCondition"></select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>身份证号：</label>

                    <div class="controls">
                        <input id="idcard" name="userInfo.idcard" type="text" class="span4 span-width spancontrol-text"
                               placeholder="请输入用户身份证号码"
                               data-rules="{required:true,idcheck:null}">
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">出生日期：</label>

                    <div class="controls">
                        <%--<input id="birthday" name="userInfo.birthday" type="text" class="calendar " placeholder="出生日期"--%>
                        <%--data-rules="{required:true,datecheck1:null}" onchange="calcAge()">--%>
                        <label class="control-label" style="text-align: left" id="birthday_show"></label>
                        <input id="birthday" name="userInfo.birthday" type="hidden"
                               value="">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">年龄：</label>

                    <div class="controls">
                        <input class="control-text" type="text" style="text-align: left" id="age">
                    </div>
                </div>

            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">手机号码：</label>

                    <div class="controls">
                        <input id="phoneNumber" name="userInfo.phoneNumber" type="text" class="control-text"
                               placeholder="请输入用户手机号码"
                               data-rules="{phoneNumber:11}">
                    </div>
                </div>

                <div class="control-group span8">
                    <label class="control-label">排序：</label>

                    <div class="controls">
                        <input id="sortNo" name="userInfo.sortNo" type="text" placeholder="请输入排序号"
                               data-rules="{number:true}"
                               class="control-text">
                    </div>
                </div>

            </div>
            <%--额外的信息--%>
            <input id="password" name="userInfo.password" type="hidden"
                   value="">
            <input id="isValid" name="userInfo.isValid" type="hidden"
                   value="">


        </div>

        <%--证件信息--%>
        <div id="card-info">
            <a name="certificateInfo"></a>
            <hr/>

            <h3>证件信息</h3>

            <select name="userInfo.userType" class="hide">
            </select>

            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">UK编号：</label>

                    <div class="controls">
                        <input id="ukNo" name="userInfo.ukNo" type="text" class="control-text" placeholder="UK编号">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">工作证编号：</label>

                    <div class="controls">
                        <input id="workNo" name="userInfo.workNo" type="text" class="control-text" placeholder="工作证编号">
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">饭卡编号：</label>

                    <div class="controls">
                        <input id="fankaNo" name="userInfo.fankaNo" type="text" class="control-text" placeholder="饭卡编号">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">执行公务证编号：</label>

                    <div class="controls">
                        <input id="officialNo" name="userInfo.officialNo" type="text" class="control-text"
                               placeholder="执行公务证编号">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>法官资格证书类型：</label>

                    <div class="controls">
                        <select id="lawyerCert" typeId="86" class="code" data-rules="{required:true}" name="userInfo.lawyerCert"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">证书取得时间：</label>

                    <div class="controls">
                        <input id="lawyerCertDate" name="userInfo.lawyerCertDate" type="text"
                               class="calendar " placeholder="资格证书取得时间" data-rules="{datecheck1:null}">
                    </div>
                </div>
            </div>

        </div>

        <%--职务信息--%>
        <div id="position-info">
            <a name="mainjobInfo"></a>
            <hr/>
            <h3>职务信息</h3>

            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>职务类别：</label>

                    <div class="controls">
                        <select id="positionType" name="userInfo.positionType" data-rules="{required:true}" typeId="62"
                                class="code select22"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">职务类别日期：</label>

                    <div class="controls">
                        <input id="positionTypeDate" name="userInfo.positionTypeDate" type="text"
                               class="calendar " placeholder="职务类别日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">职务任用方式：</label>

                    <div class="controls">
                        <select id="assign" typeId="63" class="code" name="userInfo.assign"></select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>政治面貌：</label>

                    <div class="controls">
                        <select id="political" typeId="13" class="code select22" data-rules="{required:true}" name="userInfo.political"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">加入日期：</label>

                    <div class="controls">
                        <input id="politicalDate" name="userInfo.politicalDate" type="text"
                               class="calendar " placeholder="政治面貌日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>员额法官：</label>

                    <div class="controls">
                        <select id="yefg" name="userInfo.yefg" data-rules="{required:true}">
                            <option value="">请选择</option>
                            <option value="0">否</option>
                            <option value="1">是</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>法律职务：</label>

                    <div class="controls">
                        <select id="lawPosition" typeId="16" class="code select22" data-rules="{required:true}" name="userInfo.lawPosition"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">任职日期：</label>

                    <div class="controls">
                        <input id="lawPositionDate" name="userInfo.lawPositionDate" type="text"
                               class="calendar " placeholder="法律职务任职日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">法官资格日期：</label>

                    <div class="controls">
                        <input id="lawyerDate" name="userInfo.lawyerDate" type="text" class="calendar "
                               placeholder="法官资格日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>兼任庭长：</label>

                    <div class="controls">
                        <select id="isParttimePresidingJudge" data-rules="{required:true}" name="userInfo.isParttimePresidingJudge" typeId="68"
                                class="code"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">党组职务：</label>

                    <div class="controls">
                        <select id="partyOffice" typeId="57" class="code" name="userInfo.partyOffice"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">党组职务日期：</label>

                    <div class="controls">
                        <input id="partyOfficeDate" name="userInfo.partyOfficeDate" type="text"
                               class="calendar " placeholder="党组职务日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>行政职务：</label>

                    <div class="controls">
                        <select id="administrationPosition" data-rules="{required:true}" typeId="15" class="code select22"
                                name="userInfo.administrationPosition"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">任职日期：</label>

                    <div class="controls">
                        <input id="administrationPositionDate" name="userInfo.administrationPositionDate"
                               type="text" class="calendar " placeholder="行政职务任职日期"
                               data-rules="{datecheck1:null}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>职级类型：</label>

                    <div class="controls">
                        <select id="rank" typeId="17" class="code select22" data-rules="{required:true}"name="userInfo.rank"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">职级日期：</label>

                    <div class="controls">
                        <input id="rankDate" name="userInfo.rankDate" type="text" class="calendar "
                               placeholder="职级日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>所属编制：</label>

                    <div class="controls">
                        <select id="preparation" typeId="9" class="code" data-rules="{required:true}" name="userInfo.preparation"></select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>等级：</label>

                    <div class="controls">
                        <select id="level" typeId="20" class="code select22" data-rules="{required:true}" name="userInfo.level"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">等级日期：</label>

                    <div class="controls">
                        <input id="levelDate" name="userInfo.levelDate" type="text" class="calendar "
                               placeholder="等级日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">公务员级别：</label>

                    <div class="controls">
                        <select id="servantLevel" typeId="83" class="code select22" name="userInfo.servantLevel"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">级别起算日期：</label>

                    <div class="controls">
                        <input id="servantLevelDate" name="userInfo.servantLevelDate" type="text"
                               class="calendar " placeholder="级别起算日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
            </div>
        </div>

        <%--教育信息--%>
        <div id="education-info">
            <a name="eduInfo"></a>
            <hr/>
            <h3>教育经历</h3>

            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>学历：</label>

                    <div class="controls">
                        <select id="educationBackground" data-rules="{required:true}" typeId="11" class="code select22"
                                name="userInfo.educationBackground"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">学位：</label>

                    <div class="controls">
                        <select id="degree" typeId="23" class="code select22" name="userInfo.degree"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">获得学位日期：</label>

                    <div class="controls">
                        <input id="degreeDate" name="userInfo.degreeDate" type="text" class="calendar "
                               placeholder="获得学位日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">专业：</label>

                    <div class="controls">
                        <select id="major" typeId="12" class="code select22" name="userInfo.major"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">专业证书：</label>

                    <div class="controls">
                        <select id="proCert" typeId="47" class="code" name="userInfo.proCert"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">获得证书日期：</label>

                    <div class="controls">
                        <input id="proCertDate" name="userInfo.proCertDate" type="text" class="calendar "
                               placeholder="获得证书日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
            </div>

        </div>

        <%--工作信息--%>
        <div id="education-info">
            <a name="jobDetailInfo"></a>
            <hr/>
            <h3>工作信息</h3>

            <div class="row">
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>工作日期：</label>

                    <div class="controls">
                        <input id="workDate" name="userInfo.workDate" type="text" class="calendar "
                               data-rules="{required:true,datecheck1:null}"
                               onchange="workTotal()" placeholder="工作日期">
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">政法工作日期：</label>

                    <div class="controls">
                        <input id="politicLawWorkDate" name="userInfo.politicLawWorkDate" type="text"
                               class="calendar " placeholder="政法工作日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>进院日期：</label>

                    <div class="controls">
                        <input id="enterDate" name="userInfo.enterDate" type="text" class="calendar "
                               data-rules="{required:true,datecheck1:null}" onchange="courtTotal()" placeholder="进院日期">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>补充工龄：</label>

                    <div class="controls">
                        <input id="extraSeniority" name="userInfo.extraSeniority" type="text"
                               class="control-text input-small" onchange="workTotal()"
                               data-rules="{required:true,number:true,min:0}" placeholder="补充工龄">年
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>扣减工龄：</label>

                    <div class="controls">
                        <input id="deductionSeniority" name="userInfo.deductionSeniority" type="text"
                               class="control-text input-small" onchange="workTotal()"
                               data-rules="{required:true,number:true,min:0}" placeholder="扣减工龄">年
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>应加学制：</label>

                    <div class="controls">
                        <input id="additionalDuration" name="userInfo.additionalDuration" type="text"
                               class="control-text input-small" data-rules="{required:true,number:true,min:0}"
                               placeholder="应加学制">年
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>入院前工作年限：</label>

                    <div class="controls">
                        <input id="beforeCourtWorkYear" name="userInfo.beforeCourtWorkYear" type="text"
                               class="control-text input-small" onchange="courtTotal()"
                               data-rules="{required:true,number:true,min:0}" placeholder="入院前工作年限">年
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">法院工作合计年限：</label>

                    <div class="controls">
                        <label class="control-label" style="text-align: left" id="totalCourtYear"></label>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">连续工龄：</label>

                    <div class="controls">
                        <label class="control-label" style="text-align: left" id="totalSeniority"></label>
                    </div>
                </div>
            </div>
        </div>

        <%--调遣信息--%>
        <div id="dispatch-info">
            <a name="DispatchInfo"></a>
            <hr/>
            <h3>调遣信息</h3>

            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>进入途径：</label>

                    <div class="controls">
                        <select id="enterWay" typeId="43" class="code" data-rules="{required:true}"
                                name="userInfo.enterWay"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>进入来源：</label>

                    <div class="controls">
                        <select id="enterSource" typeId="44" class="code" data-rules="{required:true}"
                                name="userInfo.enterSource"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">审核日期：</label>

                    <div class="controls">
                        <input id="verifyDate" name="userInfo.verifyDate" type="text" class="calendar "
                               placeholder="审核日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">原职务：</label>

                    <div class="controls">
                        <select id="formerPost" typeId="14" class="code select22" name="userInfo.formerPost"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">原职级：</label>

                    <div class="controls">
                        <select id="formerRank" typeId="17" class="code select22" name="userInfo.formerRank"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">原单位：</label>

                    <div class="controls">
                        <input id="formerUnit" name="userInfo.formerUnit" type="text" value="" class="control-text"
                               placeholder="原单位">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">调离原因：</label>

                    <div class="controls">
                        <select id="leaveReason" typeId="45" class="leavecode" name="userInfo.leaveReason"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">调离去向：</label>

                    <div class="controls">
                        <select id="leaveDestination" typeId="46" class="code"
                                name="userInfo.leaveDestination"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">调离日期：</label>

                    <div class="controls">
                        <input id="leaveDate" name="userInfo.leaveDate" type="text" class="calendar "
                               placeholder="调离日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
            </div>
        </div>

        <%--上传图片--%>
        <a name="photoInfo"></a>
        <hr/>
        <h3>头像上传</h3>


        <div class="row" style="height: 170px">
            <div class="control-group span8">
                <div id="photo">
                </div>
            </div>
        </div>
    </form>
</div>
<script>
    $(".calendar").prop("readonly", true);

    function generateUserName(obj) {

        var i = $(obj).val();

//        $("#username").val(getPY($(obj).val()));
        //改成中文登录  原方式注释大概以后还要改
        $("#username").val($(obj).val().replace(/\s/g, ""));

    }


    function getPY(Str) {
        var returnStr = "";
        var j = 0;
        var username = '';
        for (var i = 0; i < Str.length; i++) {

            if (j == 0 && Str[i].trim() != '') {
                username += Str[i].trim();
                returnStr += pinyin.getFullChars(Str[i]).toLowerCase();
                j++;
            } else if (Str[i].trim() != '') {
                username += Str[i].trim();
                returnStr += pinyin.getFullChars(Str[i]).toLowerCase().substr(0, 1);
            }
        }
        $("input[name='userInfo.fullname']").val(username);

        return returnStr;
    }
    function cleanError() {
        $(".show_info").hide();
        $(".show_icon").hide();
    }

    function epassword(oStr) {
//        var str_1 = CryptoJS.enc.Utf8.parse(oStr);
//        var hash_1 = CryptoJS.MD5(str_1).toString();
//        var base64_1 = Base64.encode(hash_1);

        return oStr;
    }

</script>
