<%--
  Created by IntelliJ IDEA.
  User: Cypress
  Date: 2016/11/28
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>通知管理</title>
    <jsp:include page="/basic_import.jsp"></jsp:include>
</head>
<style type="text/css">
    body {
        padding: 0;
        margin: 0;
    }

    .holder {
        width: 100%;
    }

    .holder > div {
        clear: both;
        padding: 5px;
        width: 96%;
        margin: 0 auto;
    }

    tr td span {
        font-size: 12px;
    }

    input {
        box-sizing: content-box;
    }

    a{
        cursor: pointer;
    }
</style>
<body>

<div class="holder">
    <div class="row">
        <div>
            <ul class="breadcrumb" style="margin: 0">
                <li><span>信息管理</span><span class="divider">/</span></li>
                <li>通知管理</li>
            </ul>
        </div>
    </div>


    <div id="grid">

    </div>

    <div class="hide" id="publishI">


        <form class="form-horizontal" id="publishInfo" enctype="multipart/form-data">
            <div style="margin-left: 20px">

                <div class="row">
                    <h3 style="width:100px;display: inline-block" id="row_title">发布通知</h3>
                </div>
                <div class="row">
                    <div class="control-group span12">
                        <label class="control-label"><s>*</s>标题：</label>

                        <div class="controls">
                            <input name="umsNotice.title" type="text" placeholder="请输入标题"  field="title"
                                   class="control-text spxx" data-rules="{required:true}">
                        </div>
                    </div>
                    <div class="control-group span12 hide">
                        <label class="control-label">id：</label>

                        <div class="controls">
                            <input name="umsNotice.id" type="text" placeholder="隐藏的id"  field="id"
                                   class="control-text spxx" >
                        </div>
                    </div>
                </div>

                <div  class="row hide" id="form-hide-div">
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>发布者：</label>

                        <div class="controls">
                            <input  type="text" field="publisher"
                                   class="control-text spxx">
                        </div>
                    </div>
                    <div class="control-group span8">
                        <label class="control-label"><s>*</s>发布日期：</label>

                        <div class="controls">
                            <input type="text" field="createTime"
                                   class="control-text spxx">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span15">
                        <label class="control-label"><s>*</s>展示日期：</label>
                        <div class="controls bui-form-group" data-rules="{dateInfoRange : null}">
                            <input id="showStart" name="umsNotice.showStart" placeholder="展示开始日期"
                                   class="input-small calendar spxx" data-rules="{required:true}" field="showStart"
                                   type="text"><label>&nbsp;-&nbsp;</label>
                            <input id="showEnd" name="umsNotice.showEnd" data-rules="{required:true}"  placeholder="展示结束日期" field="showEnd"
                                   class="input-small calendar spxx" type="text">
                        </div>
                    </div>

                    <div class="control-group span20">
                        <label class="control-label"><s>*</s>发布内容：</label>

                        <div class="controls" style="height: 250px;">
                            <textarea name="umsNotice.content" class="span12 spxx" data-rules="{required:true}" field="content" style="height: 230px;"
                            ></textarea>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <form action="<%=path%>/noticeFileUpload/upload" class="form-horizontal" id="fjForm" method="post" enctype="multipart/form-data">
            <input type="hidden" id="noticeId" name="noticeId" value=""/>
            <div class="row">
                <div class="control-group span8">
                    <label class="control-label">附件：</label>
                    <div class="controls">
                        <input type="file" multiple="true" dojoType="dojox.form.Uploader"
                               label="Select Some Files" url="/tests/UploadFile.php"
                               uploadOnSelect="true" name="fjs" class="control-text"/>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div>

    </div>
    <div class="hide" id="queryI">


        <form class="form-horizontal" >
            <div style="margin-left: 20px">
                <div class="row">
                    <h3 style="width:100px;display: inline-block">搜索通知</h3>
                </div>
                <div class="row">
                    <div class="control-group span12">
                        <label class="control-label">标题：</label>

                        <div class="controls">
                            <input  type="text" placeholder="输入标题进行模糊检索"  id="queryText" name="queryText"
                                   class="control-text" >
                        </div>
                    </div>

                </div>

            </div>
        </form>

    </div>
    <div class="hide" id="fjgl">
        <div id="fjgrid"></div>
        <input type="hidden" id="noticeId2" name="noticeId" value=""/>
        <div id="pagingDiv"></div>
    </div>




</div>
<script type="text/javascript">
    var path = "<%=path%>";
</script>
<script type="text/javascript" src="js/notice.js"></script>
</body>
</html>
