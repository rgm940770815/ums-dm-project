<%--
  Created by IntelliJ IDEA.
  User: Cypress
  Date: 2018/6/25
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>组织机构</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <jsp:include page="../basic_import.jsp"></jsp:include>
    <link type="text/css" href="css/base.css" rel="stylesheet"/>
    <link type="text/css" href="css/input.css" rel="stylesheet"/>
    <script type="text/javascript" src="js/uploadFileBase.js"></script>
    <style type="text/css">
        li.select2-results__option {
            min-height: 30px;
        }
    </style>

</head>
<body>
<div class="body_div">
    <div class="header_div">

        <div>
            <ul class="breadcrumb">
                <li><span>人事管理</span><span class="divider">/</span></li>
                <li><span>信息管理</span><span class="divider">/</span></li>
                <li class="active">机构信息管理</li>
            </ul>
        </div>

    </div>

    <div class="main_div">

        <div class="main_div_inner">
            <div class="sidenav">

                <div class="tree">

                </div>
            </div>

            <div class="article">
                <div class="title_1">
                    详细信息
                </div>
                <div class="title_2">
                    <ul>
                        <li>
                            <span class="checked_span" typeCode="1">单位(部门)基本情况</span>
                        </li>
                        <li>
                            <span typeCode="2">单位(部门)受奖惩情况</span>
                        </li>
                        <li>
                            <span typeCode="3">单位(部门)通信信息</span>
                        </li>
                        <li>
                            <span typeCode="4">机构编制</span>
                        </li>
                        <li>
                            <span typeCode="5">党组织情况</span>
                        </li>
                    </ul>
                </div>
                <div class="title_3">
                    <ul class="button-group-1">
                        <li>
                            <button class="button button-primary" id="button-save-1">保存</button>
                        </li>
                        <%--<li>--%>
                        <%--<button class="button button-small">操作</button>--%>
                        <%--</li>--%>
                    </ul>
                    <ul class="button-group-2 hide">
                        <li>
                            <button class="button button-primary" id="button-insert-2">新增</button>
                        </li>
                        <li>
                            <button class="button button-primary" id="button-save-2">保存</button>
                        </li>
                        <li>
                            <button class="button button-primary" id="button-delete-2">删除</button>
                        </li>

                    </ul>
                    <ul class="button-group-3 hide">
                        <li>
                            <button class="button button-primary" id="button-insert-3">新增</button>
                        </li>
                        <li>
                            <button class="button button-primary" id="button-save-3">保存</button>
                        </li>
                        <li>
                            <button class="button button-primary" id="button-delete-3">删除</button>
                        </li>

                    </ul>
                    <ul class="button-group-4 hide">
                        <li>
                            <button class="button button-primary" id="button-save-4">保存</button>
                        </li>
                    </ul>
                    <ul class="button-group-5 hide">
                        <li>
                            <button class="button button-primary" id="button-insert-5">新增</button>
                        </li>
                        <li>
                            <button class="button button-primary" id="button-save-5">保存</button>
                        </li>
                        <li>
                            <button class="button button-primary" id="button-delete-5">删除</button>
                        </li>
                    </ul>

                </div>
                <div class="context_div">

                    <div class="context_top_div">

                        <table class="middle_div middle_div_1 " cellpadding="0" cellspacing="0">
                            <tr class="row_style_1">

                                <td style="width: 21%">单位（部门）名称<s>*</s></td>
                                <td style="width: 28%">
                                    <input type="text" class="initClass paramInfo readOnly" param="courtStdName"/>
                                </td>
                                <td>单位（部门）简称</td>
                                <td style="width: 28%">
                                    <input type="text" class="initClass paramInfo readOnly" param="courtShortName"/>
                                </td>
                            </tr>
                            <tr class="row_style_1">

                                <td>单位（部门）级别<s>*</s></td>
                                <td>
                                    <select name="courtGrade" class="initClass paramInfo readOnly"
                                            param="courtGrade"></select>
                                </td>
                                <td>组织机构代码</td>
                                <td>
                                    <input type="text" class="initClass paramInfo" param="institutionCode"/>
                                </td>
                            </tr>
                            <tr class="row_style_1">

                                <td>单位性质类别<s>*</s></td>
                                <td>
                                    <select name="courtType" class="initClass paramInfo readOnly"
                                            param="courtType"></select>
                                </td>
                                <td></td>
                                <td>

                                </td>
                            </tr>
                            <tr class="row_style_1">

                                <td>上级机构代码</td>
                                <td>
                                    <input type="text" class="initClass " name="parentInstitution"/>
                                </td>
                                <td>单位（部门）负责人</td>
                                <td>
                                    <input type="text" class="initClass paramInfo" param="header"/>
                                </td>
                            </tr>

                            <tr class="row_style_2">

                                <td>单位（部门）说明</td>
                                <td colspan="3">
                                    <textarea class="initClass paramInfo" param="description"></textarea>
                                </td>
                            </tr>
                        </table>

                        <table class="middle_div middle_div_2 hide" cellpadding="0" cellspacing="0">
                            <tr class="row_style_1">

                                <td style="width: 21%">单位（部门）名称<s>*</s></td>
                                <td style="width: 28%">
                                    <input type="text" class="initClass paramInfo " param="deptName"/>
                                </td>
                                <td>单位（部门）简称</td>
                                <td style="width: 28%">
                                    <input type="text" class="initClass paramInfo readOnly" param="deptStName"/>
                                </td>
                            </tr>
                            <tr class="row_style_1">

                                <td>单位（部门）级别<s>*</s></td>
                                <td>
                                    <select name="courtGrade" class="initClass paramInfo " param="courtLevel"></select>
                                </td>
                                <td>组织机构代码</td>
                                <td>
                                    <input type="text" class="initClass paramInfo" param="institutionCode"/>
                                </td>
                            </tr>
                            <tr class="row_style_1">

                                <td>上级机构代码</td>
                                <td>
                                    <input type="text" class="initClass " name="parentInstitution"/>
                                </td>
                                <td>单位（部门）负责人</td>
                                <td>
                                    <input type="text" class="initClass paramInfo" param="header"/>
                                </td>
                            </tr>

                            <tr class="row_style_2">

                                <td>单位（部门）说明</td>
                                <td colspan="3">
                                    <textarea class="initClass paramInfo" param="description"></textarea>
                                </td>
                            </tr>
                        </table>


                        <table class="middle_div middle_div_3 hide" cellpadding="0" cellspacing="0">
                            <tr class="row_style_1">

                                <td style="width: 21%">授予单位<s>*</s></td>
                                <td style="width: 28%">
                                    <select name="grantUnitCode" class="initClass paramInfo required" param="grantUnitCode" ></select>
                                </td>
                                <td>授予单位名称补充</td>
                                <td style="width: 28%">
                                    <input type="text" class="initClass paramInfo " param="grantUnitNameAppend" placeholder='选择"其他机构"时需填写'/>
                                </td>
                            </tr>
                            <tr class="row_style_1">

                                <td>奖励名称</td>
                                <td>
                                    <select name="rewardTypeCode" class="initClass paramInfo " param="rewardTypeCode"></select>
                                </td>
                                <td>其他奖励名称</td>
                                <td>
                                    <input type="text" class="initClass paramInfo" param="rewardNameAppend" placeholder='选择"其他奖励"时需填写'/>
                                </td>
                            </tr>
                            <tr class="row_style_1">

                                <td>惩处名称 </td>
                                <td>
                                    <input type="text" class="initClass paramInfo" name="punishName" param="punishName"/>
                                </td>
                                <td>表彰领域<s>*</s></td>
                                <td>
                                    <select name="recognitionField" class="initClass paramInfo required" param="recognitionField"></select>
                                </td>
                            </tr>
                            <tr class="row_style_1">

                                <td>奖惩日期<s>*</s></td>
                                <td>
                                    <input type="text" class="initClass calendar paramInfo required" name="recordDate" param="recordDate"/>
                                </td>
                                <td>批准奖惩机关级别<s>*</s></td>
                                <td>
                                    <input type="text" class="initClass readOnly " name="approvalAuthorityLevel" readonly="readonly"/>
                                </td>

                            </tr>

                            <tr class="row_style_3">

                                <td>奖惩原因<s>*</s></td>
                                <td colspan="3">
                                    <textarea class="initClass paramInfo required" param="reason"></textarea>
                                </td>
                            </tr>
                            <tr class="row_style_3">

                                <td>纪检监察文件</td>
                                <td colspan="3" >
                                    <div class="create_upload" fileParam="1">
                                        <div class="upload_left">
                                            <ul>
                                            </ul>
                                        </div>
                                        <div class="upload_right">
                                            <button class="button button-primary add_file" >添加文件</button>
                                        </div>
                                    </div>

                                </td>
                            </tr>
                            <tr class="row_style_3">

                                <td>干部管理证明文件</td>
                                <td colspan="3">
                                    <div class="create_upload" fileParam="2">
                                        <div class="upload_left">
                                            <ul>
                                            </ul>
                                        </div>
                                        <div class="upload_right">
                                            <button class="button button-primary add_file" >添加文件</button>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr class="row_style_3">

                                <td>荣誉证明文件</td>
                                <td colspan="3">
                                    <div class="create_upload" fileParam="3">
                                        <div class="upload_left">
                                            <ul>
                                            </ul>
                                        </div>
                                        <div class="upload_right">
                                            <button class="button button-primary add_file" >添加文件</button>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr class="row_style_3">

                                <td>其他文件</td>
                                <td colspan="3">
                                    <div class="create_upload" fileParam="4">
                                        <div class="upload_left">
                                            <ul>
                                            </ul>
                                        </div>
                                        <div class="upload_right">
                                            <button class="button button-primary add_file" >添加文件</button>
                                        </div>
                                    </div>
                                </td>
                            </tr>

                        </table>

                        <table class="middle_div middle_div_4 hide" cellpadding="0" cellspacing="0">

                            <tr class="row_style_1">

                                <td>单位地址</td>
                                <td colspan="3">
                                    <input type="text" class="initClass paramInfo" param="address"/>
                                </td>

                            </tr>
                            <tr class="row_style_1">

                                <td style="width: 21%">单位电话号码</td>
                                <td style="width: 28%">
                                    <input type="text" class="initClass paramInfo " param="phoneNumber"/>
                                </td>
                                <td>单位传真号码</td>
                                <td style="width: 28%">
                                    <input type="text" class="initClass paramInfo " param="faxNumber"/>
                                </td>
                            </tr>
                            <tr class="row_style_1">

                                <td>单位邮政编码</td>
                                <td>
                                    <input type="text" class="initClass paramInfo" param="postalCode"/>
                                </td>
                                <td>电子邮箱</td>
                                <td>
                                    <input type="text" class="initClass paramInfo" param="emailAddress"/>
                                </td>
                            </tr>
                            <tr class="row_style_1">

                                <td>办公室联系人姓名</td>
                                <td>
                                    <input type="text" class="initClass paramInfo" param="officeContactName"/>
                                </td>
                                <td>办公室联系人电话</td>
                                <td>
                                    <input type="text" class="initClass paramInfo" param="officeContactPhoneNumber"/>
                                </td>
                            </tr>
                            <tr class="row_style_1">

                                <td>组织人事部门联系人姓名</td>
                                <td>
                                    <input type="text" class="initClass paramInfo" param="personnelDepartmentContactName"/>
                                </td>
                                <td>组织人事部门联系人电话</td>
                                <td>
                                    <input type="text" class="initClass paramInfo"
                                           param="personnelDepartmentContactPhoneNumber"/>
                                </td>
                            </tr>
                            <tr class="row_style_1">

                                <td>组织人事部门联系人传真</td>
                                <td>
                                    <input type="text" class="initClass paramInfo"
                                           param="personnelDepartmentContactEmailAddress"/>
                                </td>
                                <td>单位网址</td>
                                <td>
                                    <input type="text" class="initClass paramInfo" param="internetAddress"/>
                                </td>
                            </tr>

                        </table>

                        <table class="middle_div middle_div_5 hide" cellpadding="0" cellspacing="0">

                            <tr class="row_style_1">

                                <td style="width: 10%">内设机构数</td>
                                <td style="width: 17%;background:#eeeeee;text-align: right;">审判业务机构数<s>*</s></td>
                                <td style="width: 28%;background: none;">
                                    <input type="text" class="initClass paramInfo readOnly isNumber" param="spywjgs"  readonly="readonly" id="spywjgs"/>
                                </td>
                                <td style="background:#eeeeee;text-align: right;">行政后勤机构数<s>*</s></td>
                                <td style="width: 28%;background: none">
                                    <input type="text" class="initClass paramInfo readOnly isNumber" param="xzhqjgs" readonly="readonly"/>
                                </td>
                            </tr>

                            <tr class="row_style_1">

                                <td  rowspan="3">编制及人员数</td>
                                <td style=";background:#eeeeee;text-align: right;">中央政法编制数<s>*</s></td>
                                <td style="background: none;">
                                    <input type="text" class="initClass paramInfo isNumber required" param="zybzs"  />
                                </td>
                                <td style="background:#eeeeee;text-align: right;">中央政法编制实有人数<s>*</s></td>
                                <td style="background: none">
                                    <input type="text" class="initClass paramInfo readOnly isNumber" param="zybzxys" readonly="readonly"/>
                                </td>
                            </tr>

                            <tr class="row_style_1">

                                <td style="background:#eeeeee;text-align: right;">地方编制数<s>*</s></td>
                                <td style="background: none;">
                                    <input type="text" class="initClass paramInfo isNumber required" param="dfbzs"  />
                                </td>
                                <td style="background:#eeeeee;text-align: right;">地方编制实有人数<s>*</s></td>
                                <td style="background: none">
                                    <input type="text" class="initClass paramInfo readOnly isNumber" param="dfbzxys" readonly="readonly"/>
                                </td>
                            </tr>
                            <tr class="row_style_1">

                                <td style="background:#eeeeee;text-align: right;">聘用人员数<s>*</s></td>
                                <td style="background: none;" colspan="3">
                                    <input type="text" class="initClass paramInfo isNumber required" param="pyrs"  />
                                </td>
                            </tr>
                        </table>

                        <table class="middle_div middle_div_6 hide" cellpadding="0" cellspacing="0">

                            <tr class="row_style_1">

                                <td style="width: 21%">是否建立党组织<s>*</s></td>
                                <td style="width: 28%">
                                    <select name="isBuild" class="initClass paramInfo required" param="isBuild"></select>
                                </td>
                                <td>党组织类型<s class="isHide">*</s></td>
                                <td style="width: 28%">
                                    <select name="orgType" class="initClass paramInfo required" param="orgType"></select>
                                </td>
                            </tr>
                            <tr class="row_style_1">

                                <td>党组织名称<s class="isHide">*</s></td>
                                <td colspan="3">
                                    <input type="text" class="initClass paramInfo required" param="orgName"/>
                                </td>
                            </tr>
                            <tr class="row_style_1">

                                <td>所属党员数<s class="isHide">*</s></td>
                                <td colspan="3">
                                    <input type="text" class="initClass paramInfo isNumber required" param="orgPersonCount"/>
                                </td>
                            </tr>
                            <tr class="row_style_1">

                                <td>专职党务干部数<s class="isHide">*</s></td>
                                <td colspan="3">
                                    <input type="text" class="initClass paramInfo isNumber required" param="orgCadrePersonCount"/>
                                </td>
                            </tr>
                            <tr class="row_style_1">

                                <td>党组织书记任职情况<s class="isHide">*</s></td>
                                <td>
                                    <select name="orgSituation" class="initClass paramInfo required" param="orgSituation"></select>
                                </td>

                                <td></td>
                                <td>

                                </td>
                            </tr>

                        </table>

                    </div>

                    <div class="context_bottom_div">
                        <div class="grid" id="grid">

                        </div>
                    </div>

                </div>

            </div>
        </div>


    </div>
</div>

<div class="bui-ext-mask-user mask-info hide" style="position: fixed;"></div>
<div class="bui-ext-mask-msg-index x-mask-loading-index mask-info hide" style="position: fixed; left: 50%; top: 50%;">
    <div id="info">保存附件中,请稍后....</div>
</div>

<div class="hide extend-file-add"></div>
<div class="hide extend-file-save"></div>
<iframe name="targetIfr" style="display:none"></iframe>

<script type="text/javascript">
    var basePath = '<%=basePath%>';
</script>
<script type="text/javascript" src="js/base.js" charset="utf-8"></script>


</body>
</html>
