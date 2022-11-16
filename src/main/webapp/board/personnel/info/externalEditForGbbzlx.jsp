<%--externalEditForGbbzlx：改变编制类型为编外人员编辑的页面--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<script type="text/javascript" src="<%=basePath%>js/piny.js"></script>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<style type="text/css">
    .show_icon {
        display: none;
        font-size: 20px;
        font-weight: bold;
        font-family: "Arial";
        -webkit-border-radius: 2px;
        -moz-border-radius: 2px;
        border-radius: 2px;
        text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
        border: 1px solid transparent;
        cursor: inherit;
        margin: 2px 2px 0px 10px;
    }

    .show_info {
        display: none;
        color: red;
    }

    .bui-list-picker {
        height: 200px;
        overflow: scroll;
    }

    .show_icon_ok {
        display: none;
        font-size: 20px;
        font-weight: bold;
        font-family: "Arial";
        -webkit-border-radius: 2px;
        -moz-border-radius: 2px;
        border-radius: 2px;
        text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
        border: 1px solid transparent;
        cursor: inherit;
        margin: 2px 2px 0px 10px;
    }

    .show_info_ok {
        display: none;
        color: red;
    }

    .show_info_edit {
        display: none;
        color: red;
    }

</style>

<%--<ul class="nav-tabs" style="position:fixed;z-index:2000;background-color: white;width:100%">--%>
<%--    <li class="active"><a href="#basic-info" onclick="changeMarkers(this)" class="initClass">基本信息</a></li>--%>
<%--    <li><a href="#card-info" onclick="changeMarkers(this)">证件信息</a></li>--%>
<%--    <li><a href="#ext-info" onclick="changeMarkers(this)">编外人员信息</a></li>--%>
<%--    <li><a href="#company-info" onclick="changeMarkers(this)">公司信息</a></li>--%>
<%--</ul>--%>

<div style="height: 40px;"></div>

<div id="contentForRead">
    <form id="J_Form" class="form-horizontal" action="<%=basePath%>external/save" method="post">
        <input type="hidden" id="userType" name="userInfo.userType">
        <input type="hidden" id="changeUUID" name="changeUUID">
        <input type="hidden" id="new_id" name="newid" value="<s:property value="newid"/>">
        <input type="hidden" id="id" name="userInfo.id" value="<s:property value="userId"/>">
        <%--基本信息--%>
        <div id="basic-info">
            <a name="basic_info"></a>
            <div class="row">
                <h3 style="width:150px;display: inline-block">编外人员 - 基本信息</h3>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>姓名：</label>
                    <div class="controls">
                        <input id="fullname" name="userInfo.fullname" type="text" placeholder="请输入用户姓名" class="control-text" data-rules="{required:true}" onchange="generateUserName(this)">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>法院：</label>
                    <div class="controls">
                        <select id="courtNo" typeId="1" class="Courtcode" name="userInfo.courtNo" onchange="loadDeptList(department, this.value, '<option value=\'\'>请选择</option>')" data-rules="{required:true}"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>部门：</label>
                    <div class="controls">
                        <select id="department" courtNo="" class="select22" data-rules="{required:true}" name="userInfo.department">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
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
                <div class="control-group span18">
                    <a id="show_e_user" href="#basic_info"></a>
                    <label class="control-label"><s>*</s>用户名：</label>
                    <div class="controls">
                        <input id="username" name="userInfo.username" type="text" placeholder="请输入用户名" class="control-text" data-rules="{required:true}" onchange="cleanUserError()">
                        <span class="show_icon userNameCheck">!</span>
                        <span class="show_info userNameCheck">用户名已存在</span>
                        <span class="show_icon_ok">!</span>
                        <span class="show_info_ok">用户名重复，建议改为：“<span id="suggestUsername"></span>”，确认请点击保存！</span>
                        <span class="show_info_edit">编辑时用户名不可更改！</span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">编号：</label>
                    <div class="controls">
                        <input id="userCode" name="userInfo.userCode" type="text" placeholder="请输入用户编号" class="control-text">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>民族：</label>
                    <div class="controls">
                        <select id="nation" name="userInfo.nation" typeId="5" class="code select22" data-rules="{required:true}"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">出生地：</label>
                    <div class="controls">
                        <input id="birthplace" name="userInfo.birthplace" type="text" placeholder="请输入用户出生地" class="control-text">
                    </div>
                </div>

            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>籍贯：</label>
                    <div class="controls">
                        <input id="hometown" name="userInfo.hometown" type="text" placeholder="请输入用户籍贯" class="control-text" data-rules="{required:true}">
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
                    <label class="control-label"><s>*</s>身份证号：</label>
                    <div class="controls">
                        <input id="idcard" name="userInfo.idcard" type="text" class="span4 span-width spancontrol-text" placeholder="请输入用户身份证号码" data-rules="{required:true,idcheck:null}">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">出生日期：</label>

                    <div class="controls">
                        <label class="control-label" style="text-align: left" id="birthday_show"></label>
                        <input id="birthday" name="userInfo.birthday" type="hidden" readonly="readonly" value="">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">年龄：</label>
                    <div class="controls">
                        <label class="control-label" style="text-align: left" id="age">0岁</label>
                    </div>
                </div>

                <%--额外信息--%>
                <input id="password" name="userInfo.password" type="hidden" value="">
                <input id="isValid" name="userInfo.isValid" type="hidden" value="">

            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">手机号码：</label>
                    <div class="controls">
                        <input id="phoneNumber" name="userInfo.phoneNumber" type="text" class="control-text" placeholder="请输入用户手机号码" data-rules="{phoneNumber:11}">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">排序：</label>
                    <div class="controls">
                        <input id="sortNo" name="userInfo.sortNo" type="text" placeholder="请输入排序号" class="control-text">
                    </div>
                </div>
            </div>
        </div>
        <%--学历学位--%>
        <div id="degree-info">
            <a name="degree_info"></a>
            <hr/>
            <h3>学历学位</h3>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>学历：</label>
                    <div class="controls">
                        <select id="educationBackground" data-rules="{required:true}" typeId="11" class="code select22" name="userInfo.educationBackground"></select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">学位：</label>
                    <div class="controls">
                        <select id="degree" typeId="23" class="code select22" name="userInfo.degree"></select>
                    </div>
                </div>
            </div>
        </div>
        <%--证件信息--%>
        <div id="card-info">
            <a name="card_info"></a>
            <hr/>
            <h3>证件信息</h3>
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
                <div class="control-group span8">
                    <label class="control-label">饭卡编号：</label>
                    <div class="controls">
                        <input id="fankaNo" name="userInfo.fankaNo" type="text" class="control-text" placeholder="饭卡编号">
                    </div>
                </div>
            </div>
        </div>
        <%--编外人员信息--%>
        <div id="ext-info">
            <a name="ext_info"></a>
            <hr/>
            <h3>编外人员信息</h3>
            <div class="row">
                <div class="control-group span18">
                    <label class="control-label"><s>*</s> 编外人员来源：</label>
                    <div class="controls bui-form-group" data-rules="{checkRange:1}">
                        <s:iterator value="srcTypes" id="t" status="st">
                            <label class="radio"><input type="radio"

                            <s:if test="info != null">
                                                        <s:if test="info.enterSrc == #t.id">checked</s:if>
                            </s:if>
                            <s:else>
                                                        <s:if test="#st.index == 0">checked</s:if>
                            </s:else>

                                                        value="<s:property value="#t.id"/>"
                                                        id="c<s:property value="#t.id"/>"
                                                        name="info.enterSrc"><s:property
                                    value="#t.srcTypeName"/></label>
                        </s:iterator>
                    </div>
                </div>
            </div>
            <s:iterator value="srcTypes" id="t" status="st">
                <div id="d<s:property value="#t.id"/>"
                     data-depends="{'#c<s:property value="#t.id"/>:checked':['enable','show','clearErrors'],'#c<s:property value="#t.id"/>:unchecked':['hide','disable','clearErrors']}"
                     class="bui-form-group"
                        <s:if test="info != null">
                            <s:if test="info.enterSrc != #t.id">style="display: none"</s:if>
                        </s:if>
                        <s:else>
                            <s:if test="#st.index != 0">style="display: none"</s:if>
                        </s:else>
                >
                    <div class="control-group">
                        <s:iterator id="el" value="jobTypeInputMap[#t.id]">
                            <s:property value="#el" escape="false"></s:property>
                        </s:iterator>
                        <s:if test="#t.id == 99||jobTypeInputMap[#t.id][0].contains('select')">
                            <div class="row">
                                <div class="control-group span8">
                                    <label class="control-label"> 其他信息：</label>

                                    <div class="controls">
                                        <input type="text" class="input-normal"
                                               id="manual-input-<s:property value="#t.id"/>" name="manual-input"
                                               disabled
                                               data-depends="{
                                           '#d<s:property value="#t.id"/>:show':mi<s:property value="#t.id"/>
                                           <s:if test="#t.id == 99">
                                               ,'#d99:show':['enable']
                                           </s:if>
                                           <s:else>
                                               ,'#s1-<s:property value="#t.id"/>:change':mi<s:property value="#t.id"/>
                                               ,'#s2-<s:property value="#t.id"/>:change':mi<s:property value="#t.id"/>
                                           </s:else>
                                           }">
                                    </div>
                                </div>
                            </div>
                            <script>
                                function mi<s:property value="#t.id"/>(e) {
                                    $('#manual-input-<s:property value="#t.id"/>').prop('disabled', true);
                                    $('#g<s:property value="#t.id"/> select option:selected').each(function () {
                                        if ($(this).text() == '其他') {
                                            $('#manual-input-<s:property value="#t.id"/>').prop('disabled', false);
                                            return false;
                                        }
                                    });
                                }
                            </script>
                        </s:if>
                    </div>
                </div>
            </s:iterator>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s> 资金来源：</label>
                    <div class="controls">
                        <select id="fundSrc" name="info.fundSrc" data-rules="{required:true}">
                            <option value="">请选择</option>
                            <option value="1" <s:if test="info.fundSrc == 1">selected</s:if>>财政统支</option>
                            <option value="2" <s:if test="info.fundSrc == 2">selected</s:if>>单位自有</option>
                        </select>
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label"> 离开情况：</label>
                    <div class="controls">
                        <select id="leaveType" name="info.leaveType">
                            <option>请选择</option>
                            <option value="1" <s:if test="info.leaveType == 1">selected</s:if>>辞职</option>
                            <option value="2" <s:if test="info.leaveType == 2">selected</s:if>>辞退</option>
                            <option value="3" <s:if test="info.leaveType == 3">selected</s:if>>期满</option>
                            <option value="4" <s:if test="info.leaveType == 4">selected</s:if>>解聘</option>
                            <option value="99" <s:if test="info.leaveType == 99">selected</s:if>>其他</option>
                        </select>
                    </div>
                </div>
                <div class="control-group span8" id="leaveTypeOther_div" style="display: none">
                    <label class="control-label"><s>*</s> 其他：</label>
                    <div class="controls">
                        <input type="text" class="control-text input-normal" id="leaveTypeOther" name="info.leaveTypeOther" data-rules="{leaveTypeOther_required:''}" placeholder="请输入其他离开情况" disabled data-depends="{'#leaveType:change':leaveTypeOther}">
                    </div>
                </div>
                <script>
                    function leaveTypeOther() {
                        if (this.getSelectedText() === '其他') {
                            $("#leaveTypeOther_div").show();
                            $("#leaveTypeOther").prop('disabled', false);
                        } else {
                            $("#leaveTypeOther_div").hide();
                            $("#leaveTypeOther").prop('disabled', true);
                            $("#leaveTypeOther").change();
                        }
                    }
                </script>
            </div>
        </div>
        <%--公司信息--%>
        <div id="company-info">
            <a name="company_info"></a>
            <a id="show_e_company" href="#company_info"></a>
            <hr/>
            <h3>公司信息</h3>
            <div class="row">
                <div class="control-group span9">
                    <label class="control-label">选择公司：</label>
                    <div class="controls">
                        <div id="company-select">
                            <input id="companyInfoId" name="info.companyInfoId" type="hidden" value="0" onchange="cleanCompanyError()">
                        </div>
                    </div>
                </div>
                <span class=" show_icon companyCheck">!</span>
                <span class="show_info companyCheck" style="line-height: 30px;">该公司已经存在,请在左侧选择公司</span>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">组织机构代码：</label>
                    <div class="controls">
                        <input id="orgCode" name="company.orgCode" type="text" value="" class="control-text input-normal" placeholder="组织机构代码">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span16">
                    <label class="control-label"><s>*</s>公司名称：</label>
                    <div class="controls">
                        <input id="companyName" name="company.companyName" type="text" value="" class="control-text input-large" placeholder="请输入公司名称" data-rules="{company_required:null}" data-depends="{'#companyInfoId:change':['clearErrors']}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span16">
                    <label class="control-label">公司地址：</label>
                    <div class="controls">
                        <input id="address" name="company.address" type="text" value="" class="control-text input-large" placeholder="请输入公司地址">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label"><s>*</s>联系人名称：</label>
                    <div class="controls">
                        <input id="contactName" name="company.contactName" type="text" class="control-text" placeholder="联系人名称" data-rules="{company_required:null}" data-depends="{'#companyInfoId:change':['clearErrors']}">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">联系人电话：</label>
                    <div class="controls">
                        <input id="contactPhone" name="company.contactPhone" type="text" class="control-text" placeholder="联系人电话">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">联系人手机：</label>
                    <div class="controls">
                        <input id="contactMobile" name="company.contactMobile" type="text" class="control-text" placeholder="联系人手机">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">法人名称：</label>
                    <div class="controls">
                        <input id="legalPersonName" name="company.legalPersonName" type="text" class="control-text" placeholder="法人名称">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">法人电话：</label>
                    <div class="controls">
                        <input id="legalPersonPhone" name="company.legalPersonPhone" type="text" class="control-text" placeholder="法人电话">
                    </div>
                </div>
                <div class="control-group span8">
                    <label class="control-label">法人手机：</label>

                    <div class="controls">
                        <input id="legalPersonMobile" name="company.legalPersonMobile" type="text" class="control-text"
                               placeholder="法人手机">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group span16">
                    <label class="control-label">备注：</label>

                    <div class="controls">
                        <textarea type="text" id="remark" name="company.remark"
                                  class="control-row-auto input-large"></textarea>
                    </div>
                </div>
            </div>
            <p><br></p>
        </div>
        <%--上传图片--%>
        <div id="photo-info">
            <a name="photo_info"></a>
            <hr/>
            <h3>头像上传</h3>

            <div class="row" style="height: 170px">
                <div class="control-group span8">
                    <div id="photo">
                    </div>
                </div>
            </div>
        </div>
        <%--        提交按钮--%>
        <div style="margin-left: 40%" hidden>
            <button id="saveButton" type="button" class="button button-primary" style="visibility: visible">保存</button>
        </div>
    </form>
</div>

<script type="text/javascript">



    // 成功数量
    var successCount = 0;
    var ajax_num = 0;
    var userinfo;
    var myForm;
    // 当前页面只是用来编辑的
    var isEdit = true;
    //-------------初始化下拉框数据Start---------------
    var firstLine = '<option value="">请选择</option>';
    $(function () {
        // 当前页面需要通过ajax获取数据的下拉框
        ajax_num = $("select.code").length;

        $("select.code").each(function () {
            loadCodeListCountNum(this, firstLine);
        });

        $.getJSON("/ums/code/codeListByType", {typeId: 1}, function (data) {
            for (var i = 0; i < data.data.length; i++) {
                $("select.Courtcode").append($("<option>").attr({"value": data.data[i].id}).text(data.data[i].codeName));
            }
            if (data.auth) {
                auth = true;
                CourtNo = data.value;
            } else {
                CourtNo = data.value;
            }
        });
    });
    //-------------初始化下拉框数据End---------------

    BUI.use(['common/search', 'bui/tree', 'bui/data', 'bui/calendar', 'bui/overlay', 'bui/form', 'bui/uploader', 'bui/select'], function (Search, Tree, Data, Calendar, Overlay, Form, Uploader, Select) {

        <s:iterator value="srcTypes" id="t" status="st">
        <s:if test="jobTypeInputMap[#t.id][0].contains('select')">
        Form.Group.Select.addType('type-<s:property value="#t.id"></s:property>', {
            url: "<%=basePath%>external/nodes",
            params: {
                srcId: "<s:property value="#t.id"></s:property>"
            },
            listeners: {
                load: function (ev) {
                    var node = ev.node;
                    var children = node.children.length;
                    if (children > 0) {
                        var i = $("#hometown").prop("disabled");
                        if (i) {
                            return;
                        }
                        $('#s2-<s:property value="#t.id"/>').removeAttr("disabled");

                    } else {
                        $('#s2-<s:property value="#t.id"/>').attr("disabled", "disabled");
                    }
                },
            },
            root: {
                id: '0'
            }
        });
        </s:if>
        $('#s1-<s:property value="#t.id"/>').change(function () {
            if ($(this).val() == "") {
                $('#s2-<s:property value="#t.id"/>').val("");
                //不能清空，该死的BUI，从“”值到任意非“”值的切换不会加载数据
                //$('#s2-<s:property value="#t.id"/>').html("<option>请选择</option>");
                myForm.clearErrors(); //选项复位，清空验证
            } else if ($('#s2-<s:property value="#t.id"/>').length <= 1) { //第二项没有选项时，清空一下验证信息
                myForm.clearErrors();
            }
        });
        </s:iterator>
        var photoEdit = false;
console.log(12313);
        var form = new Form.HForm({
            srcNode: '#J_Form',
            submitType: 'ajax',
            callback: function (data) {
                if (data.success) {
                    BUI.Message.Alert("保存成功", function () {
                        // 关闭父页面的dialog
                        window.parent.dialog_gbbzlx.close();
                        window.parent.V.store.load();
                    }, "warning");
                } else {
                    BUI.Message.Alert(data.msg, null, "warning");
                }
                if (data.success && photoEdit) {
                    $.getJSON("<%=basePath%>photo/save", {userId: $("#id").val() || $("#new_id").val(), _: new Date().getTime()});
                } else if (data.result === -1) {
                    BUI.Message.Alert("非法操作，当前用户无此权限！", null, "warning");
                }
            }
        }).render();

        myForm = form;

        //添加 名字为 sid的校验规则
        Form.Rules.add({
            name: 'select2_required', //规则名称
            msg: '请选择第二级选项', //默认显示的错误信息
            validator: function (value, baseValue, formatMsg) { //验证函数，验证值、基准值、格式化后的错误信息
                //第一项非空时，才验证第二第二项，并且第二项要有选项
                if ($("#s1-" + baseValue).val() != "" && $("#s2-" + baseValue).find("option").length > 1 && value == "") {
                    return formatMsg;
                }
            }
        });

        //leaveTypeOther
        Form.Rules.add({
            name: 'leaveTypeOther_required', //规则名称
            msg: '请输入其他离开情况', //默认显示的错误信息
            validator: function (value, baseValue, formatMsg) { //验证函数，验证值、基准值、格式化后的错误信息
                //leaveType==99时，需要人工输入类型
                if ($("#leaveType").val() == 99 && value == "") {
                    return formatMsg;
                }
            }
        });

        //company
        Form.Rules.add({
            name: 'company_required', //规则名称
            msg: '不能为空！', //默认显示的错误信息
            validator: function (value, baseValue, formatMsg) { //验证函数，验证值、基准值、格式化后的错误信息
                //leaveType==99时，需要人工输入类型
                if (($("#companyInfoId").val() == 0 || $("#companyInfoId").val() == "") && !value) {
                    return formatMsg;
                }
            }
        });


        var companyStore = new Data.Store({
            url: "<%=basePath%>external/company/findAll",
            params: {
                emptyItem: "[新增公司]"
            },
            autoLoad: true
        });

        var companySelect = new Select.Select({
            render: '#company-select',
            valueField: "#companyInfoId",
            multipleSelect: false,
            forceFit: false,
            store: companyStore
        });

        V.companySelect = companySelect;

        companySelect.on("change", function (e) {
            if (e.value !== "0") {
                $(":input[name^='company.']").each(function () {
                    if ($(this).attr("id") !== "companyInfoId") {
                        $(this).val(e.item.item[$(this).attr("id")]);
                    }
                });
                $(":input[name^='company.']").prop("disabled", true);
            } else {
                $(":input[name^='company.']").val("");
                $(":input[name^='company.']").prop("disabled", false);
            }
        });

        companySelect.render();

        //--------------图片上传功能Start-----------------
        /**
         * 返回数据的格式
         *
         *  默认是 {url : 'url'},否则认为上传失败
         *  可以通过isSuccess 更改判定成功失败的结构
         */
        var uploader = new Uploader.Uploader({
            //指定使用主题
            theme: 'imageView',
            render: '#photo',
            url: '<%=basePath%>photo/upload',
            name: "photo",
            types: ['iframe'],
            queue: {
                resultTpl: {
                    'success': '<div class="success"><img id="qpic" src="{url}" title="{name}" style="width: 80%"/></div>',
                    'error': '<div class="error"><span class="uploader-error">{msg}</span></div>'
                }
            },
            rules: {
                //文的类型
                ext: ['.png,.jpg,.jpeg,.gif,.bmp', '文件类型只能为{0}'],
                //上传的最大个数
                max: [1, '文件的最大个数不能超过{0}个'],
                //文件大小的最小值,这个单位是kb
                //minSize: [10, '文件的大小不能小于{0}KB'],
                //文件大小的最大值,单位也是kb
                maxSize: [2048, '文件大小不能大于2M']
            }
        }).render();

        var queue = uploader.get("queue");
        queue.on("itemremoved", function (e) {
            photoEdit = true;
        });
        uploader.on("change", function (e) {
            photoEdit = true;
        });
        //--------------图片上传功能End-----------------

        // 定时器：一秒执行一次，满足条件的时候执行下面的
        var clock = setInterval(countDown, 1000);

        function countDown() {
            if (ajax_num == successCount) {
                clearInterval(clock); //清除js定时器
                <s:if test="userId != null">
                loadUserinfo("<s:property value="userId"/>");
                </s:if>
                $("#changeUUID").val(changeUUID);
            }
        }

        function loadUserinfo(id) {
            $.getJSON("<%=basePath%>userinfo/one", {id: id, _: new Date()}, function (data) {
                userinfo = data;
                reloadUserinfo();
                // 设置用户名的提示，编辑时不可以修改用户名
                $("#username").attr("readonly", "readonly");
                $("#username").focus(function () {
                    $(".show_info_edit").show();
                });
                $("#username").blur(function () {
                    $(".show_info_edit").hide();
                });
            });
        }

        function reloadUserinfo() {
            $(":input[name^='userInfo.']").each(function () {
                if ($(this).hasClass("select22")) {
                    $(this).select2().select2("val", ["" + userinfo[$(this).attr("id")] + ""]);
                } else {
                    $(this).val(userinfo[$(this).attr("id")]);
                }
                if ($(this).attr("id") === "department") {
                    loadDeptList($("#department"), $("#courtNo").val(), firstLine, function () {
                        $("#department").select2().select2("val", ["" + userinfo.department + ""]);
                    });
                }
                if ($(this).attr("id") === "courtNo") {
                    $(this).select2().select2("val", ["" + userinfo[$(this).attr("id")] + ""]);
                    loadDeptList($("#department"), $(this).val(), firstLine, function () {
                        $("#department").select2().select2("val", ["" + userinfo.department + ""]);
                    });
                }
                if ($(this).attr("name").indexOf("birthday") > 0) {
                    $("#birthday_show").html(userinfo[$(this).attr("id")]);
                }
            });
            $.getJSON("<%=basePath%>photo/getPhotoById", {userId: userinfo.id, _: new Date().getTime()}, function (data) {
                if (data !== null) {
                    queue.setItems([{success: true, ext: '.jpg', name: '原始照片.jpg', url: data}]);
                } else {
                    queue.setItems([]);
                }
            });
            photoEdit = false;
            calcAge();
            workTotal();
            courtTotal();
        }
    });

    $("#companyInfoId").val("<s:property value="info.companyInfoId" default="0"/>").change();

    // 监听form表单的提交按钮
    $("#saveButton").on("click", function () {
        if ($(".valid-text").length == 0) {
            if ($("#id").val() != '') {
                userCheck();
            } else {
                // 先身份验证，身份验证过了，再用户名验证
                idCardCheck();
            }
        } else {
            myForm.valid();
            if (($($(".valid-text")[0]).parent().offset().top < ($("html, body")[1].scrollTop + 600)) && ($($(".valid-text")[0]).parent().offset().top > $("html, body")[1].scrollTop)) {

            } else {
                $("html, body").animate({scrollTop: $($(".valid-text")[0]).parent().offset().top} ,500);
            }
        }
        myForm.submit();
    });


    function generateUserName(obj) {

        var i = $(obj).val();

//        $("#username").val(getPY($(obj).val()));
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

    function cleanUserError() {
        $(".userNameCheck").hide();
    }

    function cleanCompanyError() {
        $(".companyCheck").hide();
    }

    function changeMarkers(obj) {
        $(obj).parent().parent().find(".active").removeClass("active");
        $(obj).parent().addClass("active");
    }

    //-------------日期规范输出End--------------

    //-------------计算日期相关函数Start---------------
    function calcAge() {
        $("#J_Form #age").text(calcYears($("#J_Form #birthday").val()) + '岁');
    }

    function workTotal() {
        $("#J_Form #totalSeniority").text(workTotalYears($("#J_Form #workDate").val(), $("#J_Form #extraSeniority").val(), $("#J_Form #deductionSeniority").val()) + '年');
    }

    function courtTotal() {
        $("#J_Form #totalCourtYear").text(workTotalYears($("#J_Form #enterDate").val(), $("#J_Form #beforeCourtWorkYear").val()) + '年');
    }

    //-------------计算日期相关函数End---------------

    function loadCodeListCountNum(cbo, firstLine, callback, copySelector) {
        loadCodeListbyTypeCountNum(cbo, $(cbo).attr("typeId"), $(cbo).attr("listType"), firstLine, callback, copySelector);
    }

    function loadCodeListbyTypeCountNum(cbo, typeId, listType, firstLine, callback, copySelector) {
        $.getJSON("/ums/code/codeListByType", {
            typeId: typeId,
            getParent: 1
        }, function (data) {
            if (firstLine) {
                $(cbo).html(firstLine);
                if (copySelector) {
                    copySelector.html(firstLine);
                }
            } else {
                $(cbo).empty();
                if (copySelector) {
                    copySelector.empty();
                }
            }
            if (data) {
                for (var i = 0; i < data.length; i++) {
                    $(cbo).append($("<option>").attr({"value": data[i].id}).text(data[i].codeName));
                    //把同样的东西赋予另外一个jQuery对象
                    if (copySelector) {
                        copySelector.append($("<option>").attr({"value": data[i].id}).text(data[i].codeName));
                    }
                }
                if (listType) {
                    if (listType.indexOf('tree') != -1) {
                        //组装成tree需要的对象格式
                        var getJsonTree = function (data, parentid, pid) {
                            var itemArr = [];
                            for (var i = 0; i < data.length; i++) {
                                var node = data[i];
                                if (node.parentId == parentid) {
                                    var newNode = {};
                                    newNode.id = node.id;
                                    newNode.typeId = typeId;
                                    newNode.text = node.codeName;
                                    newNode.sortNo = node.sortNo;
                                    newNode.pid = pid + ',' + node.id;
                                    newNode.children = getJsonTree(data, node.id, pid + "," + node.id);
                                    itemArr.push(newNode);
                                }
                            }
                            return itemArr;
                        };
                        var treeData = getJsonTree(data, null, '-1');
                        var cboClickfunction = function (ev) {
                            var cbo = $(ev.target);
                            tree_dialog_id = cbo.attr('tree_dialog');
                            BUI.getControl('tree_dialog_' + typeId + '_' + listType).show();
                        };
                        var cboChangefunction = function (ev) {
                            var cbo = $(ev.target);
                            var txt = cbo.find('option:selected').text();
                            var span = cbo.next().children().find('.select2-selection__rendered');
                            span.text(txt).attr('title', txt);
                        };
                        var cbowidth = $(cbo).width() + 24;
                        var html = "<span class=\"select2 select2-container select2-container--default\" dir=\"ltr\" style=\"width: 166px;\"><span class=\"selection\"><span class=\"select2-selection select2-selection--single\" role=\"combobox\" aria-autocomplete=\"list\" aria-haspopup=\"true\" aria-expanded=\"false\" tabindex=\"0\" aria-labelledby=\"select2-n_reward_type-container\"><span class=\"select2-selection__rendered\" title=\"请选择\">请选择</span><span class=\"select2-selection__arrow\" role=\"presentation\"><b role=\"presentation\"></b></span></span></span><span class=\"dropdown-wrapper\" aria-hidden=\"true\"></span></span>";
                        $(cbo).on('change', cboChangefunction).after(html);
                        $(cbo).next().width(cbowidth).children().find('.select2-selection__rendered').attr('tree_dialog', BUI.guid('tree_dialog')).on('click', cboClickfunction);
                        $(cbo).hide();
                        if (copySelector) {
                            copySelector.each(function (e) {
                                var cbowidth = $(this).width() + 24;
                                $(this).on('change', cboChangefunction).after(html);
                                $(this).next().width(cbowidth).children().find('.select2-selection__rendered').attr('tree_dialog', BUI.guid('tree_dialog')).on('click', cboClickfunction);
                                $(this).hide();
                            });
                        }
                        var dialogId = 'tree_dialog_' + typeId + '_' + listType;
                        //只能选择子节点
                        if (listType.indexOf('tree') != -1) {
                            //赋值，并增加点击事件
                            BUI.use(['bui/tree', 'bui/overlay'], function (Tree, Overlay) {
                                var tree = new Tree.TreeList({
                                    id: dialogId + '_tree_id',
                                    root: {                  //由于要显示根节点，所以需要配置根节点
                                        id: '-1',
                                        text: '全部',
                                        typeId: typeId,
                                        expanded: true,
                                        children: treeData
                                    },
                                    showLine: true, //显示连接线
                                    showRoot: true
                                });
                                tree.on('itemclick', function (ev) {
                                    if ($(ev.domTarget).parent().hasClass('x-tree-icon-wraper')) return true;
                                    var item = ev.item;
                                    if (item.children && item.children.length > 0 && listType == 'tree2') {
                                        tree.toggleExpand(item);
                                        return false;
                                    } else {
                                        $('[tree_dialog=' + tree_dialog_id + ']').text(item.text).attr('title', item.text)
                                            .closest('.select2-container').prev().val(item.id).change();
                                        BUI.getControl(dialogId).close();
                                    }
                                });
                                var dialog = new Overlay.Dialog({
                                    id: dialogId,
                                    width: 350,
                                    height: 400,
                                    zIndex: 1080,
                                    buttons: [{
                                        text: '清除',
                                        elCls: 'button button-primary',
                                        handler: function () {
                                            var span = $('[tree_dialog=' + tree_dialog_id + ']');
                                            var cbo = span.closest('.select2-container').prev();
                                            var firstline = cbo.find("option:eq(0)");
                                            span.text(firstline.text()).attr('title', firstline.text());
                                            cbo.val(firstline.val());
                                            this.close();
                                        }
                                    }],
                                    elCls: 'custom-dialog',
                                    children: [tree]
                                });
                                dialog.on('show', function (ev) {
                                    $('.custom-dialog').find('.bui-stdmod-body').css('overflow', 'auto')
                                        .find('.bui-tree-list').css('border', 'none');

                                    var tree = this.getChild(this.get('id') + '_tree_id');
                                    var span = $('[tree_dialog=' + tree_dialog_id + ']');
                                    var cbo = span.closest('.select2-container').prev();
                                    cbo.parent().find('.valid-text').remove();

                                    var id = cbo.find('option:selected').val();
                                    var text = cbo.find('option:selected').text();
                                    span.text(text).attr('title', text);
                                    if (id) {
                                        var itemCurrent = tree.findNode(id);
                                        tree.expandPath(itemCurrent.pid);
                                        tree.setSelected(itemCurrent);
                                    }
                                });
                            });
                        }
                    } else if (listType == 'select2') {
                        var firstOptionValue = $(cbo).find("option:eq(0)").val();
                        $(cbo).select2().select2("val", ["" + firstOptionValue + ""]);
                        $(cbo).change();
                        if (copySelector) {
                            firstOptionValue = copySelector.find("option:eq(0)").val();
                            copySelector.select2().select2("val", ["" + firstOptionValue + ""]);
                            copySelector.change();
                        }
                    }
                }
            }
            if (callback) {
                callback();
            }
            // ajax成功了，successCount就+1
            successCount++;
        });
    }

    // 用户名验证
    function userCheck() {
        var url = "<%=path%>/checkUserName";
        var datas = {courtStdNo: $("#courtNo").val(), username: $("#username").val(), id: $("#id").val(), valid: 1, userType: 1, isEdit: isEdit};
        myForm.valid();
        if (myForm.isValid()) {
            $.post(url, datas, function (data) {
                if (data.UserNameCheck == "false") {
                    if (isEdit) {
                        $('#password').val("");
                    }
                    $("#courtNo").attr("disabled", false);
                    myForm.submit();
                } else {
                    // 如果username返回为""，说明并未修改传过去的usename
                    if (data.username != "") {
                        $(".show_info_ok").show();
                        $(".show_icon_ok").show();
                        newUserName = data.username;
                        $("#suggestUsername").text(newUserName);
                        $("#username").val(newUserName);
                    } else {
                        $(".show_info").show();
                        $(".show_icon").show();
                    }
                    var a = document.getElementById("show_e");
                    // 取消<a>标签原先的onclick事件,使<a>标签点击后通过href跳转(因为无法用js跳转)^-^
                    a.setAttribute("onclick", '');
                    // 激发标签点击事件OVER
                    a.click("return false");
                }
            });
        } else {
            myForm.submit();
        }
    }



    // 身份证号验证
    function idCardCheck() {
        var url = "<%=path%>/checkIdCard";
        var datas = {courtStdNo: $("#courtNo").val(), username: $("#username").val(), id: $("#id").val(), idcard: $("#idcard").val()};
        myForm.valid();
        if (myForm.isValid()) {
            $.post(url, datas, function (data) {
                if (data.idCardCheck) {
                    userCheck();
                } else {
                    var typeStr = "";
                    switch (data.userType) {
                        case 1 :
                            typeStr += "正式人员"
                            break;
                        case 2 :
                            typeStr += "编外人员"
                            break;
                        case 3 :
                            typeStr += "人民陪审员"
                            break;
                    }
                    var enabledStr = "";
                    switch (data.enabled) {
                        case 0 :
                            enabledStr += "停用"
                            break;
                        case 1 :
                            enabledStr += "启用"
                            break;
                    }
                    var showStr = "<span>该人员已经存在,请对相应人员进行操作  ";
                    showStr += (typeStr == "" ? "" : " , 人员类型为 : " + typeStr);
                    showStr += (typeStr == "" ? "" : " , 人员状态为 : " + enabledStr);
                    showStr += ("<a style='margin-left:10px;' target='_blank' href='<%=basePath%>view/detail_new?id=" + data.userId + " '>点击查看</a></span>");
                    BUI.Message.Alert(showStr, "warning");
                    //打开新页面
                }
            });
        } else {
            myForm.submit();
        }
    }

    function linkageChange(obj) {
        var timer = setTimeout(function () {
            if ($(obj).next().length > 0) {
                var objNext = $(obj).next();
                var i = objNext.find("option").length;
                if (i == 1) {
                } else {
                    objNext.removeAttr("disabled");
                }
            }
        }, 300);
    }
</script>
