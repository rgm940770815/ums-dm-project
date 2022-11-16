<%--
    Document   : contact
    Created on : 2015-1-8, 17:36:58
    Author     : Diluka
--%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <jsp:include page="common_header.jsp"></jsp:include>
        <body>
            <div class="search-grid-container">
                <div id="grid"></div>
            </div>

            <div id="dialog" class="hide">
                <form id="detail" action="/ums/userinfo/attach/save" method="post">
                    <div class="row">
                        <div class="control-group span8">
                            <label class="control-label">区号：</label>

                            <div class="controls">
                                <input type="text" id="c_area_code" class="field control-text"
                                       data-rules="{maxlength:10}">
                            </div>
                        </div>
                        <div class="control-group span8">
                            <label class="control-label">办公电话：</label>

                            <div class="controls">
                                <input type="text" id="c_office_tel" class="field control-text"
                                       data-rules="{maxlength:20}">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="control-group span8">
                            <label class="control-label">家庭电话：</label>

                            <div class="controls">
                                <input type="text" id="c_family_tel" class="field control-text" data-rules="{maxlength:20}">
                            </div>
                        </div>
                        <div class="control-group span8">
                            <label class="control-label">移动电话：</label>

                            <div class="controls">
                                <input type="text" id="c_mobile" class="field control-text" data-rules="{mobile:true}">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="control-group span8">
                            <label class="control-label"><s>*</s>邮政编码：</label>

                            <div class="controls">
                                <input type="text" id="c_postcode" class="field control-text" data-rules="{required:true, maxlength:20}">
                            </div>
                        </div>
                        <div class="control-group span8">
                            <label class="control-label"><s>*</s>通讯地址：</label>

                            <div class="controls">
                                <input type="text" id="c_address" class="field control-text"
                                       data-rules="{required:true,maxlength:500}">
                            </div>
                        </div>
                    </div>
                    <div class="edit-fields hide row">
                        <div class="control-group span8">
                            <label class="control-label">序号：</label>

                            <div class="controls">
                                <input type="text" id="sort_no" class="field control-text" data-rules="{number:true}">
                            </div>
                        </div>
                    </div>
                    <div class="hide">
                        <input type="hidden" id="new_id" name="id">
                        <input type="hidden" id="id" name="p.id" class="field">
                        <input type="hidden" id="user_id" name="p.userId" class="field">
                        <input type="hidden" id="tableName" name="t">
                        <button type="reset" id="btnReset"></button>
                    </div>
                </form>
            </div>

            <script>
    var viewName = "<s:property value="viewName"></s:property>";
    var userId = "<s:property value="userId"></s:property>";

    var columns = [{
            title: '序号',
//            dataIndex: 'sort_no',
            width: '5%',
        renderer: function (value, obj) {
            return startRow++;
        }
        }, {
            title: '区号',
            dataIndex: 'c_area_code',
            width: '10%'
        }, {
            title: '办公电话',
            dataIndex: 'c_office_tel',
            width: '20%'
        }, {
            title: '家庭电话',
            dataIndex: 'c_family_tel',
            width: '20%'
        }, {
            title: '移动电话',
            dataIndex: 'c_mobile',
            width: '20%'
        }, {
            title: '邮政编码',
            dataIndex: 'c_postcode',
            width: '15%'
        }, {
            title: '操作',
            width: '10%', renderer: function (value, obj) {
                return '<span class="grid-command btn-detail" title="显示通讯录详细信息">查看详情</span>';
            }
        }
    ];
    var formName = '通讯录';
    //弹出层默认宽度
    var dialogWidth = 700;
    //弹出层默认高度
    var dialogHeight = 270;
            </script>
        <jsp:include page="common_js.jsp"></jsp:include>
    </body>
</html>