<%--
    Document   : common_update_info_div
    Created on : 2015-3-18, 10:40:05
    Author     : Diluka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form id="update-info">
    <div class="row update-info hide">
        <div class="control-group span8">
            <label class="control-label">修改人：</label>

            <div class="controls">
                <input type="text" id="updateUser" class="field control-text input-normal" disabled>
            </div>
        </div>
        <div class="control-group span8">
            <label class="control-label">修改时间：</label>

            <div class="controls">
                <input type="text" id="updateTime" class="field control-text input-normal" disabled>
            </div>
        </div>
    </div>
</form>