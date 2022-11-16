<%--
    Document   : tip
    Created on : 2015-3-26, 10:02:52
    Author     : Diluka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    .pop-tip{
        background:#658e15;
        filter:alpha(opacity:70);
        opacity:0.6;
        border-radius:5px;
        border:1px solid #476a05;
        width:7em;
        color:#FFF;
        text-shadow:1px 1px #000;
        font-family:"黑体";
        font-size:1.825em;
        letter-spacing:2px;
        text-align:center;
        line-height:2.75em;
        position: absolute;
        top:15%;
        right: 50%;
    }
</style>
<div id="success-tip" class="hide pop-tip">
</div>
<script>
    function showTip(text) {
        if (text) {
            $("#success-tip").text(text);
        } else {
            $("#success-tip").text("添加成功");
        }
        $("#success-tip").fadeIn(500).delay(500).fadeOut(500);
    }
</script>
