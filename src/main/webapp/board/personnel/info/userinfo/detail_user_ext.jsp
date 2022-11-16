<%--
    Document   : detail_user_ext
    Created on : 2015-4-21, 10:28:47
    Author     : Diluka
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="/basic_import.jsp"></jsp:include>
        <script src="<%=basePath%>js/common/codelist.js" type="text/javascript"></script>
        <script src="<%=basePath%>js/common/datetools.js" type="text/javascript"></script>
        <script src="<%=basePath%>js/common/idchecker.js" type="text/javascript"></script>
    </head>
    <body>
        <script>
            $.get("<%=basePath%>external/form", {userId: "<s:property value="id"></s:property>", _: new Date().getTime(),isQuery:10}, function (html) {
                $("body").html(html);
                $(":input").prop("disabled", true);
                $("#photo-info").hide();

                $('<div class="row"><img id="photo" height="240" style="margin: 2em"></div>').insertBefore($("#basic-info"));
                $.getJSON("<%=basePath%>photo/getPhotoById", {userId: "<s:property value="id"></s:property>"}, function (photo) {
                    $("#photo").attr({"src": photo});
                });

                //清除错误error

                (function f() {
                    if (V.companySelect) {
                        V.companySelect.disable();
                    } else {
                        setTimeout(f, 200);
                    }
                })();
                (function g(){

                    if(V.form){
                        V.form.clearErrors();
                    }else{
                        setTimeout(g ,200);
                    }

                })();

//                加样式
                $("#J_Form input").each(function(){
                    $(this).css("background","transparent");
                    $(this).css("box-shadow","none");
                    $(this).css("border","1px solid #ffffff");
                });

                $("#J_Form select").each(function(){
                    $(this).css("background","transparent");
                    $(this).css("box-shadow","none");
                    $(this).css("border","1px solid #ffffff");
                    $(this).css("-webkit-appearance","none");
                })


            });

            function changeMarkers(obj){
                $(obj).parent().parent().find(".active").removeClass("active");
                $(obj).parent().addClass("active");

                var hr = $(obj).attr("href");
                var anh = $(hr).offset().top - 40;
                $("html,body").stop().animate({scrollTop:anh},800);
            };
        </script>
    </body>
</html>
