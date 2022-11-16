<%--
    Document   : external_add
    Created on : 2015-3-16, 17:07:20
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
    <form id="userinfo_form" class="form-horizontal" action="<%=basePath%>userinfo/save" method="post">
        <input type="hidden" name="id" id="new_id" >
        <input type="hidden" name="userInfo.id" id="id" >
        <%--基本信息--%>
        <div id="basic-info">
            <h3>基本信息</h3>

            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">编号：</label>

                    <div class="controls">
                        <input id="userCode" name="userInfo.userCode" type="text" placeholder="请输入用户编号" class="control-text">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>法院：</label>

                    <div class="controls">
                        <select id="courtNo" typeId="1" class="code" name="userInfo.courtNo"
                                onchange="loadDeptList(department, this.value, '<option value=\'\'>请选择</option>')" data-rules="{required:true}"></select>
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
                        <input id="fullname" name="userInfo.fullname" type="text" placeholder="请输入用户姓名" class="control-text" data-rules="{required:true}">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">曾用名：</label>

                    <div class="controls">
                        <input id="formerName" name="userInfo.formerName" type="text" placeholder="请输入用户曾用名" class="control-text">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>性别：</label>

                    <div class="controls">
                        <select id="gender" typeId="3" class="code" data-rules="{required:true}" name="userInfo.gender"></select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>民族：</label>

                    <div class="controls">
                        <select id="nation" name="userInfo.nation" typeId="5" class="code" data-rules="{required:true}"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>民族(最高院)：</label>

                    <div class="controls">
                        <select id="nationReport" name="userInfo.nationReport" typeId="1005" class="code select22"
                                data-rules="{required:true}">
                        </select>

                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">出生地：</label>

                    <div class="controls">
                        <input id="birthplace" name="userInfo.birthplace" type="text" placeholder="请输入用户出生地" class="control-text">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>籍贯：</label>

                    <div class="controls">
                        <input id="hometown" name="userInfo.hometown" type="text" placeholder="请输入用户籍贯" class="control-text" data-rules="{required:true}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>出生日期：</label>

                    <div class="controls">
                        <input id="birthday" name="userInfo.birthday" type="text" class="calendar calendar-time" placeholder="出生日期"
                               data-rules="{required:true,datecheck1:null}" onchange="calcAge()">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>婚姻状况：</label>

                    <div class="controls">
                        <select id="maritalStatus" typeId="6" class="code" data-rules="{required:true}" name="userInfo.maritalStatus"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>健康状况：</label>

                    <div class="controls">
                        <select id="physicalCondition" typeId="10" class="code" data-rules="{required:true}" name="userInfo.physicalCondition"></select>
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
                        <input id="idcard" name="userInfo.idcard" type="text" class="span8 span-width spancontrol-text" placeholder="请输入用户身份证号码"
                               data-rules="{required:true,idcheck:null}">
                    </div>
                </div>
            </div>
        </div>

        <%--证件信息--%>
        <div id="card-info">
            <hr/>
            <h3>证件信息</h3>

            <select name="userInfo.userType" class="hide"><option value="2">编外人员</option></select>

            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">UK编号：</label>

                    <div class="controls">
                        <input id="ukNo" name="userInfo.ukNo" type="text" class="control-text" placeholder="UK编号" data-rules="{required:true}">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">工作证编号：</label>

                    <div class="controls">
                        <input id="workNo" name="userInfo.workNo" type="text" class="control-text" placeholder="工作证编号" data-rules="{required:true}">
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">饭卡编号：</label>

                    <div class="controls">
                        <input id="fankaNo" name="userInfo.fankaNo" type="text" class="control-text" placeholder="饭卡编号" data-rules="{required:true}">
                    </div>
                </div>
            </div>
            <!--            <div class="row">
                            <div class="control-group span8">
                                <label class="control-label">执行公务证编号：</label>

                                <div class="controls">
                                    <input id="officialNo" name="userInfo.officialNo" type="text" class="control-text" placeholder="执行公务证编号" data-rules="{required:true}">
                                </div>
                            </div>
                        </div>-->
        </div>

        <%--职务信息--%>
        <div id="position-info">
            <hr/>
            <h3>职务信息</h3>

            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">职务类别*：</label>

                    <div class="controls">
                        <select id="positionType" name="userInfo.positionType" typeId="62"
                                class="code"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">职务类别日期：</label>

                    <div class="controls">
                        <input id="positionTypeDate" name="userInfo.positionTypeDate" type="text" class="calendar calendar-time" placeholder="职务类别日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">进入途径*：</label>

                    <div class="controls">
                        <select id="assign" typeId="63" class="code" name="userInfo.assign"></select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">政治面貌信息#：</label>

                    <div class="controls">
                        自动读取
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
                    <label class="control-label">学历和专业#：</label>

                    <div class="controls">
                        从系统中读取
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">学位信息#：</label>

                    <div class="controls">
                        从系统中读取
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
                        <input id="proCertDate" name="userInfo.proCertDate" type="text" class="calendar calendar-time" placeholder="获得证书日期" data-rules="{datecheck1:null}">
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
                    <label class="control-label">劳动合同期限*：</label>

                    <div class="controls">
                        下拉选择框（第一次签订劳动合同期限；第二次签订劳动合同期限；其它；）
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
                <div class="control-group12 span8">
                    <label class="control-label">劳动合同期限*：</label>

                    <div class="controls">
                        下拉选择框（第一次签订劳动合同期限；第二次签订劳动合同期限；其它；）
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">劳动合同性质*：</label>

                    <div class="controls">
                        下拉选择框（固定期限劳动合同、无固定期限劳动合同、其它；）
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>派遣单位：</label>

                    <div class="controls">
                        <input id="enterDate" name="userInfo.enterDate" type="text" class="calendar calendar-time"
                               data-rules="{required:false}"  placeholder="派遣单位" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">连续工龄 和 法院工作合计年限*：</label>

                    <div class="controls">
                        根据进院日期、工龄信息等自动计算，不需要在基本信息中填写
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">进本院前法院年限*：</label>

                    <div class="controls">
                        <input id="beforeCourtWorkYear" name="userInfo.beforeCourtWorkYear" type="text"
                               class="control-text input-small"  data-rules="{required:true,number:true,min:0}" placeholder="进本院前法院年限">年
                    </div>
                </div>

            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>入院前工作年限：</label>

                    <div class="controls">
                        <input id="beforeCourtWorkYear" name="userInfo.beforeCourtWorkYear" type="text"
                               class="control-text input-small" onchange="courtTotal()" data-rules="{required:true,number:true,min:0}" placeholder="入院前工作年限">年
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
                        <label class="control-label" style="text-align: left" id="totalSeniority">计算得出</label>
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
                        <select id="enterWay" typeId="43" class="code" data-rules="{required:true}" name="userInfo.enterWay"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>进入来源：</label>

                    <div class="controls">
                        <select id="enterSource" typeId="44" class="code" data-rules="{required:true}" name="userInfo.enterSource"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">审核日期：</label>

                    <div class="controls">
                        <input id="verifyDate" name="userInfo.verifyDate" type="text" class="calendar calendar-time" placeholder="审核日期" data-rules="{datecheck1:null}">
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
                        <input id="formerUnit" name="userInfo.formerUnit" type="text" value="" class="control-text" placeholder="原单位">
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
                    <label class="control-label">调离去向*：</label>

                    <div class="controls">
                        (填写本项后，该用户会自动转入历史库)
                        <select id="leaveDestination" typeId="46" class="code" name="userInfo.leaveDestination"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">调离日期：</label>

                    <div class="controls">
                        <input id="leaveDate" name="userInfo.leaveDate" type="text" class="calendar calendar-time" placeholder="调离日期" data-rules="{datecheck1:null}">
                    </div>
                </div>
            </div>
        </div>

        <%--上传图片--%>
        <div id="dispatch-info">
            <hr/>
            <h3>头像上传</h3>

            <div class="row" style="height: 170px">
                <div class="control-group span8">
                    <div id="photo">
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<script>
    $(".calendar").prop("readonly", true);
</script>
