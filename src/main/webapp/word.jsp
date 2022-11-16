<%@ page import="java.util.UUID" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String useid = request.getParameter("id");
    UUID uuid = UUID.randomUUID();
    String file = "/word/"+uuid+"/"+useid+".docx";
%>
<html>
<head>
    <title>干部任免审批表</title>
    <script src="<%=path%>/js/jquery/jquery.js" type="text/javascript"></script>
    <script type="text/javascript">
        function load() {

            var url = '<%=path%>/view/wordEdit';
            var params = {
                id: "<%=useid%>",
                uuid: '<%=uuid%>'
            };
            $.post(url, params, function (data) {
                if (data.success) {
                    init();
                } else {
                    alert("加载失败，请重试！");
                }
            });
        }
    </script>
</head>
<body scroll='none' bgColor=#808080 leftMargin=0 topMargin=0
      marginheight=0 marginwidth=0 onload="load();" onbeforeunload="clickclose();">

<div style="display: none;"><p>word文件路径--"<%=file%>"</p></div>
<p><div style="color: red">如果在线打开失败，请下载后浏览--> <label id="i1" onclick="wait();" style="color: #0F0;">下载word</label></div></p>

<object id='OCX_OBJ'
        classid='clsid:B39F1330-3322-4a1d-9BF0-0BA2BB90E970'
        codebase='ofctnewclsid.cab#version=5,0,2,9'
        width='100%' height='100%'>
    <param name='MakerCaption' value='上海交大慧谷通用技术有限公司'>
    <param name='MakerKey'
           value="3F4BF491D77D1551B40E325A59CC8B99D2942124">
    <param name="ProductCaption" value="重庆法院人事管理系统">
    <param name="ProductKey" value="9BE84EA62EFE58885A369F4BAC3351CC976B6156">
    <param name='BorderStyle' value='1'>
    <param name='Menubar' value='true'>
    <param name='Titlebar' value='false'>
    <param name='FileNew' value='false'>
    <param name='FileOpen' value='true'>
    <param name='FileSave' value='false'>
    <param name='Caption' value=''>
    <param name=”IsUseUTF8URL” value=”-1”>
    <param name="isExitOfficeProcessWhenClose" value="-1">
</object>

</body>
<script type="text/javascript">
    var TANGER_OCX_OBJ;
    function init()
    {
        TANGER_OCX_OBJ=document.getElementById("OCX_OBJ");
        TANGER_OCX_OBJ.activate(true);
        TANGER_OCX_OBJ.BeginOpenFromURL("<%=file%>");
        //TANGER_OCX_OBJ.isnocopy = true;


    }
    //保存文档
    //    function savetourl(){
    //       var filename=document.getElementById("filename").value;
    //    	var a=TANGER_OCX_OBJ.saveToURL("http://192.168.0.221:1986/abc/SaveOffice",//提交到的url地址
    //			"fileUpload",//文件域的id，类似<input type=file id=upLoadFile 中的id
    //			"",         //与控件一起提交的参数如："p1=a&p2=b&p3=c"
    //			filename,    //上传文件的名称，类似<input type=file 的value
    //			0    //与控件一起提交的表单id，也可以是form的序列号，这里应该是0.
    //			);
    //    }
    function clickclose()
    {
        TANGER_OCX_OBJ.Close();
    }
</script>
<script type="text/javascript">
    var i = 5;
    //延迟下载，防止未生成完毕导致404
    function wait() {
        $("#i1").text("生成中，请等待" + i + "秒......").off("click").removeAttr('onclick');
        var interval = setInterval(function () {
            i--;
            $("#i1").text("生成中，请等待" + i + "秒......");
            if (i == 0) {
                window.clearInterval(interval);
                window.open('<%=path%>/view/dlWord?uuid=<%=uuid%>&id=<%=useid%>', '_self');
                $("#i1").text("下载word").on("click", function () {
                    window.open('<%=path%>/view/dlWord?uuid=<%=uuid%>&id=<%=useid%>', '_self');
                });
            }
        }, 1000);
    }
</script>
</html>
