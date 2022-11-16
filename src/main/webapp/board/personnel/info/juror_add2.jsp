<%--
    Document   : juror_add
    Created on : 2015-3-16, 16:40:48
    Author     : Diluka
--%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--新增页面弹出层--%>
<div id="content" class="hide">
    <form id="userinfo_form" class="form-horizontal" action="<%=basePath%>juror/save" method="post">
        <input type="hidden" name="id" id="new_id">
        <input type="hidden" name="userInfo.id" id="id">
        <%--基本信息--%>
        <div id="basic-info">
            <h3>基本信息</h3>

            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">编号：</label>

                    <div class="controls">
                        <input id="userCode" name="userInfo.userCode" type="text" placeholder="请输入用户编号"
                               class="control-text">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>法院：</label>

                    <div class="controls">
                        <select id="courtNo" typeId="1" class="code" name="userInfo.courtNo"
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
                    <label class="control-label"><s>*</s>姓名：</label>

                    <div class="controls">
                        <input id="fullname" name="userInfo.fullname" type="text" placeholder="请输入用户姓名"
                               class="control-text" data-rules="{required:true}">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>曾用名：</label>

                    <div class="controls">
                        <input id="formerName" name="userInfo.formerName" type="text" placeholder="请输入用户曾用名"
                               class="control-text"
                               data-rules="{required:true}">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>性别：</label>

                    <div class="controls">
                        <select id="gender" typeId="3" class="code" data-rules="{required:true}"
                                name="userInfo.gender"></select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>民族：</label>

                    <div class="controls">
                        <select id="nation" name="userInfo.nation" typeId="5" class="code"
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
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>籍贯：</label>

                    <div class="controls">
                        <input id="hometown" name="userInfo.hometown" type="text" placeholder="请输入用户籍贯"
                               class="control-text" data-rules="{required:true}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>出生日期：</label>

                    <div class="controls">
                        <input id="birthday" name="userInfo.birthday" type="text" class="calendar calendar-time"
                               placeholder="出生日期"
                               data-rules="{required:true,datecheck1:null}" onchange="calcAge()">
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
                    <label class="control-label">年龄：</label>

                    <div class="controls">
                        <label class="control-label" style="text-align: left" id="age"></label>
                    </div>
                </div>
                <div class="control-group span12">
                    <label class="control-label"><s>*</s>身份证号：</label>

                    <div class="controls">
                        <input id="idcard" name="userInfo.idcard" type="text" class="span8 span-width spancontrol-text"
                               placeholder="请输入用户身份证号码"
                               data-rules="{required:true,idcheck:null">
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>政治面貌：</label>

                    <div class="controls">
                        <select id="political" typeId="13" class="code" name="userInfo.political"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>手机号码：</label>

                    <div class="controls">
                        <input id="phoneNumber" name="userInfo.phoneNumber" type="text" class="control-text"
                               placeholder="请输入用户手机号码"
                               data-rules="{required:true,phoneNumber:11}">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>座机号码：</label>

                    <div class="controls">
                        <input id="machineNumber" name="userInfo.machineNumber" type="text" class="control-text"
                               placeholder="请输入用户座机号码"
                               data-rules="{required:true}">
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>常住地：</label>

                    <div class="controls">
                        <input id="localAddress" name="userInfo.localAddress" type="text" class="control-text"
                               placeholder="请输入用户常住地"
                               data-rules="{required:true}">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label"><s>*</s>通讯地址：</label>
                    <div class="controls">
                        <select id="province"  class="userSel-1" name="userInfo.province" onchange="provinceChange()" data-rules="{required:true,selectRule:'省份'}" ></select>
                        <select id="userCity" class="userSel-1"  onchange="cityChange()"  data-rules="{required:true,selectRule:'城市'}" >
                            <option value='-1'>请选择省份</option>
                        </select>
                        <select id="userArea" class="userSel-1"  data-rules="{required:true,selectRule:'区县'}">
                            <option value='-1'>请选择城市</option>
                        </select>
                        <input id="postalAddress" name="userInfo.postalAddress" type="text" class="control-text"
                        placeholder="请输入用户通讯地址"
                        data-rules="{required:true}">
                        <input  type="hidden" name="userInfo.city" id="city" value="">
                        <input type="hidden" name="userInfo.area"  id="area" value="">
                    </div>
                </div>
            </div>
        </div>

        <%--人民陪审员信息--%>
        <div id="juror-info">
            <hr/>
            <h3>人民陪审员信息</h3>

            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>单位：</label>

                    <div class="controls">
                        <input id="company" name="jurorInfo.company" type="text" class="control-text" placeholder="单位"
                               data-rules="{required:true}">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>单位性质：</label>

                    <div class="controls">
                        <input id="companyNature" name="jurorInfo.companyNature" type="text" class="control-text"
                               placeholder="单位性质" data-rules="{required:true}">
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>单位职务：</label>

                    <div class="controls">
                        <input id="companyJob" name="jurorInfo.companyJob" type="text" class="control-text"
                               placeholder="单位职务" data-rules="{required:true}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>职业：</label>

                    <div class="controls">
                        <input id="jurorWork" name="jurorInfo.jurorWork" type="text" class="control-text" data-rules="required:true"
                               placeholder="职业">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>任命单位：</label>

                    <div class="controls">
                        <input id="jurorUnit" name="jurorInfo.jurorUnit" type="text" class="control-text"
                               placeholder="任命单位" data-rules="{required:true}">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>任命日期：</label>

                    <div class="controls">
                        <input id="jurorDate" name="jurorInfo.jurorDate" type="text" class="calendar calendar-time"
                               placeholder="任命日期" data-rules="{required:true}">
                    </div>
                </div>
            </div>
        </div>


        <%--证件信息--%>
        <%--<div id="card-info">--%>
            <%--<hr/>--%>
            <%--<h3>证件信息</h3>--%>

            <%--<select name="userInfo.userType" class="hide">--%>
                <%--<option value="3">人民陪审员</option>--%>
            <%--</select>--%>

            <%--<div class="row">--%>
                <%--<div class="control-group span8">--%>
                    <%--<label class="control-label"><s>*</s>UK编号：</label>--%>

                    <%--<div class="controls">--%>
                        <%--<input id="ukNo" name="userInfo.ukNo" type="text" class="control-text" placeholder="UK编号"--%>
                               <%--data-rules="{required:true}">--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="control-group span8">--%>
                    <%--<label class="control-label"><s>*</s>工作证编号：</label>--%>

                    <%--<div class="controls">--%>
                        <%--<input id="workNo" name="userInfo.workNo" type="text" class="control-text" placeholder="工作证编号"--%>
                               <%--data-rules="{required:true}">--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="control-group12 span8">--%>
                    <%--<label class="control-label"><s>*</s>饭卡编号：</label>--%>

                    <%--<div class="controls">--%>
                        <%--<input id="fankaNo" name="userInfo.fankaNo" type="text" class="control-text" placeholder="饭卡编号"--%>
                               <%--data-rules="{required:true}">--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="row">--%>
                <%--<div class="control-group span8">--%>
                    <%--<label class="control-label"><s>*</s>执行公务证编号：</label>--%>

                    <%--<div class="controls">--%>
                        <%--<input id="officialNo" name="userInfo.officialNo" type="text" class="control-text"--%>
                               <%--placeholder="执行公务证编号" data-rules="{required:true}">--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>

        <%--职务信息--%>
        <div id="position-info">
            <hr/>
            <h3>职务信息</h3>

            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">职务类别：</label>

                    <div class="controls">
                        <select id="positionType" name="userInfo.positionType" typeId="62"
                                class="code"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">职务类别日期：</label>

                    <div class="controls">
                        <input id="positionTypeDate" name="userInfo.positionTypeDate" type="text"
                               class="calendar calendar-time" placeholder="职务类别日期" data-rules="{datecheck1:null}">
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
                <%--<div class="control-group span8">--%>
                <%--<label class="control-label">政治面貌：</label>--%>

                <%--<div class="controls">--%>
                <%--<select id="political" typeId="13" class="code" name="userInfo.political"></select>--%>
                <%--</div>--%>
                <%--</div>--%>
                <div class="control-group12 span8">
                    <label class="control-label">加入日期：</label>

                    <div class="controls">
                        <input id="politicalDate" name="userInfo.politicalDate" type="text"
                               class="calendar calendar-time" placeholder="政治面貌日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">法律职务：</label>

                    <div class="controls">
                        <select id="lawPosition" typeId="16" class="code" name="userInfo.lawPosition"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">任职日期：</label>

                    <div class="controls">
                        <input id="lawPositionDate" name="userInfo.lawPositionDate" type="text"
                               class="calendar calendar-time" placeholder="法律职务任职日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">法官资格日期：</label>

                    <div class="controls">
                        <input id="lawyerDate" name="userInfo.lawyerDate" type="text" class="calendar calendar-time"
                               placeholder="法官资格日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group12 span8">
                    <label class="control-label">兼任庭长：</label>

                    <div class="controls">
                        <select id="isParttimePresidingJudge" name="userInfo.isParttimePresidingJudge" typeId="68"
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
                               class="calendar calendar-time" placeholder="党组职务日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">行政职务：</label>

                    <div class="controls">
                        <select id="administrationPosition" typeId="15" class="code"
                                name="userInfo.administrationPosition"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">任职日期：</label>

                    <div class="controls">
                        <input id="administrationPositionDate" name="userInfo.administrationPositionDate"
                               type="text" class="calendar calendar-time" placeholder="行政职务任职日期"
                               data-rules="{datecheck1:null}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">职级类型：</label>

                    <div class="controls">
                        <select id="rank" typeId="17" class="code" name="userInfo.rank"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">职级日期：</label>

                    <div class="controls">
                        <input id="rankDate" name="userInfo.rankDate" type="text" class="calendar calendar-time"
                               placeholder="职级日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">所属编制：</label>

                    <div class="controls">
                        <select id="preparation" typeId="9" class="code" name="userInfo.preparation"></select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">等级：</label>

                    <div class="controls">
                        <select id="level" typeId="20" class="code" name="userInfo.level"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">等级日期：</label>

                    <div class="controls">
                        <input id="levelDate" name="userInfo.levelDate" type="text" class="calendar calendar-time"
                               placeholder="等级日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">公务员级别：</label>

                    <div class="controls">
                        <select id="servantLevel" typeId="83" class="code" name="userInfo.servantLevel"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">级别起算日期：</label>

                    <div class="controls">
                        <input id="servantLevelDate" name="userInfo.servantLevelDate" type="text"
                               class="calendar calendar-time" placeholder="级别起算日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
            </div>
        </div>

        <%--教育信息--%>
        <div id="education-info">
            <hr/>
            <h3>教育经历</h3>

            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">学历：</label>

                    <div class="controls">
                        <select id="educationBackground" typeId="11" class="code"
                                name="userInfo.educationBackground"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">学位：</label>

                    <div class="controls">
                        <select id="degree" typeId="23" class="code" name="userInfo.degree"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">获得学位日期：</label>

                    <div class="controls">
                        <input id="degreeDate" name="userInfo.degreeDate" type="text" class="calendar calendar-time"
                               placeholder="获得学位日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">专业：</label>

                    <div class="controls">
                        <select id="major" typeId="12" class="code" name="userInfo.major"></select>
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
                        <input id="proCertDate" name="userInfo.proCertDate" type="text" class="calendar calendar-time"
                               placeholder="获得证书日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">法官资格证书类型：</label>

                    <div class="controls">
                        <select id="lawyerCert" typeId="86" class="code" name="userInfo.lawyerCert"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">取得时间：</label>

                    <div class="controls">
                        <input id="lawyerCertDate" name="userInfo.lawyerCertDate" type="text"
                               class="calendar calendar-time" placeholder="资格证书取得时间" data-rules="{datecheck1:null}">
                    </div>
                </div>
            </div>
        </div>

        <%--工作信息--%>
        <div id="education-info">
            <hr/>
            <h3>工作信息</h3>

            <div class="row">
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>工作日期：</label>

                    <div class="controls">
                        <input id="workDate" name="userInfo.workDate" type="text" class="calendar calendar-time"
                               data-rules="{required:true,datecheck1:null}"
                               onchange="workTotal()" placeholder="工作日期">
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">政法工作日期：</label>

                    <div class="controls">
                        <input id="politicLawWorkDate" name="userInfo.politicLawWorkDate" type="text"
                               class="calendar calendar-time" placeholder="政法工作日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>进院日期：</label>

                    <div class="controls">
                        <input id="enterDate" name="userInfo.enterDate" type="text" class="calendar calendar-time"
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
                        <input id="verifyDate" name="userInfo.verifyDate" type="text" class="calendar calendar-time"
                               placeholder="审核日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">原职务：</label>

                    <div class="controls">
                        <select id="formerPost" typeId="14" class="code" name="userInfo.formerPost"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">原职级：</label>

                    <div class="controls">
                        <select id="formerRank" typeId="17" class="code" name="userInfo.formerRank"></select>
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
                        <select id="leaveReason" typeId="45" class="code" name="userInfo.leaveReason"></select>
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
                        <input id="leaveDate" name="userInfo.leaveDate" type="text" class="calendar calendar-time"
                               placeholder="调离日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
            </div>
        </div>

        <%--上传图片--%>
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
<style type="text/css">
    .userSel-1{
        width :120px;
    }
    #postalAddress{
        width :200px;
    }
</style>
<script>
    $(".calendar").prop("readonly", true);
    function provinceChange(){
        if($("#province").val() != -1){
            var url = "<%=basePath%>code/getCity";
            var id =  $("#province").val();
            var datas ={
                    provinceID : id
            };
            $.post(url,datas,function(data){
                $("#userCity").html("<option value='-1'>请选择</option>");
                $("#userArea").html("<option value='-1'>请选择城市</option>")
                if(data.length >0){
                    for (var i = 0; i < data.length; i++) {
                        $("#userCity").append($("<option>").attr({"value": data[i].cityID}).text(data[i].city));
                    }
                }
            });
        }else{
            $("#userCity").html("<option value='-1'>请选择省份</option>")
            $("#userArea").html("<option value='-1'>请选择城市</option>")
        }
    }


    function cityChange(){
        if($("#userCity").val() != -1){
            var url = "<%=basePath%>code/getArea";
            var id =  $("#userCity").val();
            var datas ={
                cityID : id
            };
            $.post(url,datas,function(data){
                $("#userArea").html("<option value='-1'>请选择</option>")
                if(data.length >0){
                    for (var i = 0; i < data.length; i++) {
                        $("#userArea").append($("<option>").attr({"value": data[i].areaID}).text(data[i].area));
                    }
                }
            });
        }else{
            $("#userArea").html("<option value='-1'>请选择城市</option>")
        }
    }
</script>
