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
            <a name="basic_info"></a>
            <h3>基本信息</h3>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>姓名：</label>

                    <div class="controls">
                        <input id="fullname" name="userInfo.fullname" type="text" placeholder="请输入用户姓名" class="control-text" data-rules="{required:true}" onchange="generateUserName_2(this)"/>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">曾用名：</label>

                    <div class="controls">
                        <input id="formerName" name="userInfo.formerName" type="text" placeholder="请输入用户曾用名"
                               class="control-text">
                    </div>
                </div>
                <div class="control-group span18">

                    <a id="show_e" href="#basic_info"></a>
                    <label class="control-label"><s>*</s>用户名：</label>

                    <div class="controls">
                        <input id="username" name="userInfo.username" type="text" placeholder="默认：姓名+身份证后六位" class="control-text" onchange="cleanError()" readonly/>
                        <span class="show_icon">!</span>
                        <span class="show_info">用户名已存在</span>
                        <span class="show_icon_ok">!</span>
                        <span class="show_info_ok">用户名重复，已更改为：“<span id="suggestUsername"></span>”，确认请点击保存！</span>
                        <span class="show_info_edit">编辑时用户名不可更改！</span>
                    </div>
                </div>

            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>性别：</label>

                    <div class="controls">
                        <select id="gender" typeId="3" class="code" data-rules="{required:true}"
                                name="userInfo.gender"></select>
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
                    <label class="control-label"><s>*</s>民族(最高院)：</label>

                    <div class="controls">
                        <select id="nationReport" name="userInfo.nationReport" typeId="1005" class="code select22"
                                data-rules="{required:true}">
                        </select>

                    </div>
                </div>

            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>政治面貌：</label>

                    <div class="controls">
                        <select id="political" typeId="13" class="code select22" name="userInfo.political" data-rules="{required:true}"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>政治面貌(最高院)：</label>

                    <div class="controls">
                        <select id="politicalReport" typeId="1013" class="code select22" data-rules="{required:true}" name="userInfo.politicalReport"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>婚姻状况：</label>
                    <div class="controls">
                        <select id="maritalStatus" typeId="6" class="code" data-rules="{required:true}"
                                name="userInfo.maritalStatus"></select>
                    </div>
                </div>

            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>身份证号：</label>

                    <div class="controls">
                        <input id="idcard" name="userInfo.idcard" type="text" class="span-width spancontrol-text"
                               placeholder="请输入用户身份证号码"
                               data-rules="{required:true,idcheck:null}"  onchange="generateUserName(this)"/>
                    </div>
                </div>

                <div class="control-group span8">
                    <label class="control-label">出生日期：</label>

                    <div class="controls">
                        <%--<input id="birthday" name="userInfo.birthday" type="text" class="calendar "--%>
                        <%--placeholder="出生日期"--%>
                        <%--data-rules="{required:true,datecheck1:null}" onchange="calcAge()">--%>
                        <label class="control-label" style="text-align: left" id="birthday_show"></label>
                        <input id="birthday" name="userInfo.birthday" type="hidden"  readonly="readonly"
                               value="">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">年龄：</label>

                    <div class="controls">
                        <label class="control-label" style="text-align: left" id="age"></label>
                    </div>
                </div>
            </div>

            <div class="row">

                <div class="control-group span8">
                    <label class="control-label"><s>*</s>健康状况：</label>

                    <div class="controls">
                        <select id="physicalCondition" typeId="10" class="code" data-rules="{required:true}"
                                name="userInfo.physicalCondition"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>法院：</label>

                    <div class="controls">
                        <select id="courtNo" typeId="1" class="Courtcode" name="userInfo.courtNo"
                        <%-- onchange="loadDeptList(department, this.value, '<option value=\'\'>请选择</option>')"--%>
                                data-rules="{required:true}"></select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>手机号码：</label>

                    <div class="controls">
                        <input id="phoneNumber" name="userInfo.phoneNumber" type="text" class="control-text"
                               placeholder="请输入用户手机号码"
                               data-rules="{required:true,phoneNumber:11}">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">座机号码：</label>

                    <div class="controls">
                        <input id="machineNumber" name="userInfo.machineNumber" type="text" class="control-text"
                               placeholder="请输入用户座机号码" data-messages="{regexp:'不是有效的电话号码'}"
                        <%--data-rules="{required:true,regexp:/^((\d{3,4}|\d{3,4})-)?\d{7,8}$/}">--%>
                               data-rules="{number:true}" >
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>常住地：</label>

                    <div class="controls">
                        <input id="localAddress" name="userInfo.localAddress" type="text" class="control-text"
                               placeholder="请输入用户常住地"
                               data-rules="{required:true}">
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
                    <label class="control-label">排序：</label>

                    <div class="controls">
                        <input id="sortNo" name="userInfo.sortNo" type="text" placeholder="请输入排序号"
                               class="control-text" >
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span24">
                    <label class="control-label"><s>*</s>家庭住址：</label>
                    <div class="controls">
                        <input id="postalAddress" name="userInfo.postalAddress" type="text" class="span20 span-width spancontrol-text"
                               placeholder="请输入用户家庭住址" data-rules="{required:true}">
                    </div>
                </div>
            </div>
        </div>
        <%--学历学位--%>
        <div id="juror-degree">
            <a name="juror_degree"></a>
            <hr/>
            <h3>学历学位</h3>

            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>学历学位：</label>
                    <div class="controls">
                        <select id="cPsXlxw" name="userInfo.cPsXlxw" typeId="10001" class="code"
                                data-rules="{required:true}"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>毕业院校：</label>
                    <div class="controls">
                        <input id="sPsByyxjzy" name="userInfo.sPsByyxjzy" type="text" class="span-width spancontrol-text"
                               placeholder="填写毕业院校" data-rules="{required:true}">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>专业：</label>
                    <div class="controls">
                        <input id="sPsZy" name="userInfo.sPsZy" type="text" class="span-width spancontrol-text"
                               placeholder="填写专业" data-rules="{required:true}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>学历：</label>

                    <div class="controls">
                        <select id="educationBackground" data-rules="{required:true}" typeId="11" class="code select22"
                                name="userInfo.educationBackground"></select>
                    </div>
                </div>
            </div>
        </div>
        <%--单位及职务--%>
        <div id="position-info">
            <a name="position_info"></a>
            <hr/>
            <h3>单位及职务</h3>

            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>单位：</label>

                    <div class="controls">
                        <input id="company" name="jurorInfo.company" type="text" class="control-text" placeholder="单位"
                               data-rules="{required:true}">
                    </div>
                </div>
                <div class="control-group span12">
                    <label class="control-label"><s>*</s>单位地址：</label>

                    <div class="controls">
                        <input id="sPsDwdz" name="userInfo.sPsDwdz" type="text" class="span8 span-width spancontrol-text"
                               placeholder="填写单位地址" data-rules="{required:true}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>职业：</label>
                    <div class="controls">
                        <select id="jurorWork" name="jurorInfo.jurorWork" class="code" data-rules="{required:true}"
                                placeholder="职业">
                        </select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>单位职务：</label>

                    <div class="controls">
                        <input id="sPsZw" name="userInfo.sPsZw" type="text" class="control-text"
                               placeholder="单位职务" data-rules="{required:true}">
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>单位职级：</label>

                    <div class="controls">
                        <input id="sPsZj" name="userInfo.sPsZj" type="text" class="control-text"
                               placeholder="单位职级" data-rules="{required:true}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">专业技术职务：</label>
                    <div class="controls">
                        <input id="sPsZyjszw" name="userInfo.sPsZyjszw" type="text" class="control-text"
                               placeholder="专业技术职务">
                    </div>
                </div>
            </div>
        </div>
        <%--陪审员信息--%>
        <div id="juror-info">
            <a name="juror_info"></a>
            <hr/>
            <h3>陪审员信息</h3>

            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">类型：</label>
                    <div class="controls">
                        <select id="cPsLx" name="userInfo.cPsLx" typeId="10002" class="code" ></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">专业类别：</label>
                    <div class="controls">
                        <select id="cPsZylb" name="userInfo.cPsZylb" typeId="10003" class="code" ></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">任命单位：</label>

                    <div class="controls">
                        <input id="sPsRmdw" name="userInfo.sPsRmdw" type="text" class="control-text"
                               placeholder="任命单位">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group12 span8">
                    <label class="control-label"><S>*</S>任命日期：</label>
                    <div class="controls">
                        <input id="beginTime" name="userInfo.beginTime" type="text"
                               class="calendar " placeholder="任命日期" data-rules="{required:true,datecheck1:null}">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">陪审员编号：</label>

                    <div class="controls">
                        <input id="sPsPsybh" name="userInfo.sPsPsybh" type="text" class="control-text"
                               placeholder="陪审员编号">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">uk编号：</label>

                    <div class="controls">
                        <input id="ukbm" name="userInfo.ukbm" type="text" class="control-text"
                               placeholder="uk编号">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group12 span8">
                    <label class="control-label">免职日期：</label>

                    <div class="controls">
                        <input id="endTime" name="userInfo.endTime" type="text"
                               class="calendar " placeholder="免职日期" >
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">免职原因：</label>
                    <div class="controls">
                        <select id="cPsMzyy" name="userInfo.cPsMzyy" typeId="10004" class="code" ></select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>地区分布：</label>

                    <div class="controls">
                        <select id="regional" name="jurorInfo.regional" typeId="109" class="code" data-rules="{required:true}"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>工作区域：</label>
                    <div class="controls">
                        <select class="code" typeId="112" style="display: none;"></select>
                        <input id="gzqy" name="jurorInfo.gzqy" type="text" class="control-text"   autocomplete="off"
                               placeholder="工作区域">
                        <input id="workArea" name="jurorInfo.workArea" type="text" style="display: none" data-rules="{required:true}">
                        <%--<select id="workArea" name="jurorInfo.workArea"  class="code" ></select>--%>
                    </div>
                </div>

                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>是否新增：</label>
                    <div class="controls">
                        <select id="isNew" name="jurorInfo.isNew" typeId="108" class="code" data-rules="{required:true}"></select>
                    </div>
                </div>

            </div>

            <div class="row">
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>届数：</label>

                    <div class="controls">
                        <input id="numberOfTimes" name="jurorInfo.numberOfTimes" type="text" class="control-text" data-rules="{required:true,number:true}"
                               placeholder="届数">
                    </div>

                </div>

                <div class="control-group12 span8">
                    <label class="control-label">年度参审次数：</label>

                    <div class="controls">
                        <input id="yearAddCount" name="jurorInfo.yearAddCount" type="text" class="control-text"  data-rules="{number:true}"
                               placeholder="年度参审次数">
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>参与案件类型：</label>
                    <div class="controls">
                        <select class="code" typeId="110" style="display: none;"></select>
                        <input id="cyajlx" name="jurorInfo.cyajlx" type="text" class="control-text" data-rules="{required:true}"  autocomplete="off"
                               placeholder="参与案件类型">
                        <input id="typeOfCase" name="jurorInfo.typeOfCase" type="text" style="display: none">
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="control-group12 span8">
                    <label class="control-label">年度陪审费用：</label>

                    <div class="controls">
                        <input id="yearCost" name="jurorInfo.yearCost" type="text" class="control-text"  data-rules="{number:true}"
                               placeholder="年度陪审费用">
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label"><s>*</s>陪审状态：</label>
                    <div class="controls">
                        <select id="memberState" name="jurorInfo.memberState" typeId="111" class="code" data-rules="{required:true}"></select>
                    </div>
                </div>
                <div class="control-group12 span8">
                    <label class="control-label">退出方式：</label>

                    <div class="controls">
                        <select id="exitMode" name="jurorInfo.exitMode" ></select>
                    </div>
                </div>
            </div>

        </div>

        <%--其他--%>
        <div id="education-info">
            <a name="education_info"></a>
            <hr/>
            <h3>其他</h3>
            <div class="row">
                <div class="control-group span24">
                    <label class="control-label">专业特长：</label>

                    <div class="controls">
                        <input id="zytc" name="userInfo.zytc" type="text" class="span20 span-width spancontrol-text"
                               placeholder="专业特长">
                    </div>
                </div>
            </div>
        </div>
        <%--额外信息--%>
        <input id="password" name="userInfo.password" type="hidden"
               value="">
        <input id="isValid" name="userInfo.isValid" type="hidden"
               value="">
        <input id="is_info_complete" name="userInfo.is_info_complete" type="hidden"
               value="">
        <input id="salt" name="userInfo.salt" type="hidden"
               value="">
        <input id="userNo" name="userInfo.userNo" type="hidden"
               value="">
        <input id="orderNo" name="userInfo.orderNo" type="hidden"
               value="">


        <%--上传图片--%>
        <a name="photo_info"></a>
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
</style>
<script>
    // 是id为fullname的值
    var fullname = $("#basic-info #fullname").val();
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

    function cleanError(){
        $(".show_info").hide();
        $(".show_icon").hide();
    }

    function generateUserName(obj) {
        // 获得身份证字符串
        var str_idcard = $(obj).val().replace(/\s/g,"");
        // 截取身份证后六位
        var idcard_6 = str_idcard.substring(str_idcard.length-6,str_idcard.length);
        if ("" == fullname || "undefined" == fullname) {
            fullname = $("#basic-info #fullname").val();
        }
        $("#username").val(fullname + idcard_6);
    }

    // 获取当前id的val，赋值给fullname
    function generateUserName_2(obj) {
        fullname = $(obj).val().replace(/\s/g,"");
        if ("" == fullname || "undefined" == fullname) {
            fullname = $("#basic-info #fullname").val();
        }
    }

    function selWork(work){
        var s = ["基层干部","人民团体成员","事业单位职员","专业技术人员","工商业人员","社区工作者","普通居民","农民","进城务工人员","其他人员"];
        var html = "<option>请选择</option>";
        for (var a in s){
            html += "<option value='" + s[a] + "'>" + s[a] + "</option>";
        }
        $("#jurorWork").html(html);
        if (s.indexOf(work) != -1){
            $("#jurorWork").val(work);
        }
    }






</script>
