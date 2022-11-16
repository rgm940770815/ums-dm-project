<%@ page import="cn.net.withub.ums.common.UmsConstant" %>

<%@ page import="cn.net.withub.ums.entity.UmsUserInfoView" %><%--
  Created by D.Yang.
  Date: 2015/2/3 0003
  Time: 10:16
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

    UmsUserInfoView userinfo = (UmsUserInfoView)session.getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>重庆法院人事管理系统</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <jsp:include page="/basic_import.jsp"></jsp:include>
        <style type="text/css">
            /**内容超出 出现滚动条 **/
            .bui-stdmod-body {
                overflow-x: hidden;
                overflow-y: auto;
            }
        </style>
    </head>
    <body>

    <br/><br/>
<div style="text-align: center;font-size: 2em;">您正在修改【<%=userinfo.getCourtNoText()%>】的人员信息</div>
<br/><br/>

    <div style="margin-left: 50px;">

    <div style="width: 500px;height:600px;;overflow: auto;float:left;">
        <div style="text-align: center;font-size: 1.5em;font-weight: bold;margin-bottom: 15px;">新用户列表</div>
        本院姓名查询：<input type="text" onkeyup="filterTable(2,this.value,1);" style="width: 50px;" />
        身份证：<input type="text" onkeyup="filterTable(2,this.value,2);" />
        <br/>
        全市姓名查询：<input id="a_query" type="text"  style="width: 50px;" /><button onclick="getAllUser(a_query.value)">查询</button>
        <table border="1" cellspacing="1" cellpadding="1" width="100%" >
            <thead>
            <tr align='center' style="font-weight: bold;color: black;">
                <td>序号</td>
                <td>编号</td>
                <td>姓名</td>
                <td>部门</td>
                <td>登录名</td>
                <td>身份证</td>
            </tr>
            </thead>
            <tbody id="newUserList">

            </tbody>
        </table>
    </div>


    <div style="width: 600px;height:600px;;overflow: auto;float:left;margin-left: 40px;">
        <div style="text-align: center;font-size: 1.5em;font-weight: bold;margin-bottom: 15px;">旧用户列表</div>
        姓名查询：<input type="text" onkeyup="filterTable(1,this.value,1);"  style="width: 50px;"/>
        身份证：<input type="text" onkeyup="filterTable(1,this.value,2);" />
        <br/>
        <input type="radio" name="r1" value="1" onclick="changeList(1)" checked>全部<input type="radio" name="r1" value="2" onclick="changeList(2)">未录入<input type="radio" name="r1" value="3" onclick="changeList(3)">已录入
        <table border="1" cellspacing="1" cellpadding="1" width="100%" style="margin-top: 10px;">
            <thead>
            <tr align='center' style="font-weight: bold;color: black;">
                <td>序号</td>
                <td>编号</td>
                <td>姓名</td>
                <td>部门</td>
                <td>登录名</td>
                <td>身份证</td>
                <td>是否排除</td>
            </tr>
            </thead>
            <tbody id="oldUserList">

            </tbody>
        </table>
    </div>
    </div>




    <div id="search" class="hide" style="height: 300px;overflow:auto;">

        <table border="1" cellspacing="1" cellpadding="1" width="100%" >
            <thead>
            <tr align='center' style="font-weight: bold;color: black;">
                <td>序号</td>
                <td>编号</td>
                <td>法院</td>
                <td>姓名</td>
                <td>部门</td>
                <td>登录名</td>
                <td>身份证</td>
            </tr>
            </thead>
            <tbody id="allUserList">

            </tbody>
        </table>
    </div>


    </body>
</html>

<script>

//    alert([{ab:'a',bc:'b'},{ab2:'a2',bc2:'b2'},{ab3:'a3',bc3:'b3'}].filter(function(value){return value.ab=='a' && value.bc == 'b'})[0].ab);


    var currentFydm = "<%=userinfo.getCourtCode()%>";

    if(currentFydm == ""){
        alert("法院信息未能正确获取。");
    }


    //优先获取部门
    var departmentList;
    $.getJSON("<%=basePath%>/view/departmentList", {}, function (data) {
        departmentList = data;
        initList();
    });

    //根据法院信息获取部门
    function getDepartmentName(fydm,deptNo,orgCode){
        try{
//            alert(fydm);
//            alert(deptNo);
            if(orgCode){
                return departmentList.filter(function(value){return value.courtCode==fydm && value.orgCode == orgCode})[0].deptName;
            }else{
                return departmentList.filter(function(value){return value.courtCode==fydm && value.deptNo == deptNo})[0].deptName;
            }
        }catch(e){return "";}
    }

    //根据法院信息获取部门
    function getFyName(fydm,deptNo,orgCode){
        try{
//            alert(fydm);
//            alert(deptNo);
            if(orgCode){
                alert(departmentList[0].courtShortName);
                return departmentList.filter(function(value){return value.courtCode==fydm && value.orgCode == orgCode})[0].courtShortName;
            }else{
                return departmentList.filter(function(value){return value.courtCode==fydm && value.deptNo == deptNo})[0].courtShortName;
            }
        }catch(e){return "";}
    }

    /**
     * 初始化列表
     * */
    function initList(){
        //菜单是从过AJAX在后台查询得出的详细列表
        $.getJSON("<%=basePath%>/view/userinfo_old", {fydm:currentFydm}, function (data) {
    //            alert(data.length);

            var htmlStr = "";
            var plusStr = "";
            var bmStr = "";
            var index = 1;
            for(var i=0;i<data.length;i++){
                if(data[i].isIgnore == 1){
                    plusStr = "style='background-color:#d0d0d0;color: #4F4F4F;'";
                }else if(data[i].idcard != null && data[i].idcard !="") {
                    plusStr = "style='background-color:#CEFFCE;color: #4F4F4F;'";
                }else{
                    plusStr = "style='color: #4F4F4F;'";
                }

                if(data[i].idcard == null){
                    data[i].idcard = "";
                }

                bmStr = getDepartmentName(data[i].fydm,"",data[i].orgCode);

                //过滤院领导
                if(bmStr.indexOf("领导")!=-1){
                    continue;
                }

                htmlStr = "<tr id='tr_"+data[i].id+"' idc='"+data[i].idcard+"' uname='"+data[i].userName+"' oldRow='1' align='center' "+plusStr+">";
                htmlStr += "<td>"+(index++)+"</td>";
                htmlStr += "<td>"+data[i].userId+"</td>";
                htmlStr += "<td>"+data[i].userName+"</td>";
                htmlStr += "<td>"+bmStr+"</td>";
                htmlStr += "<td>"+data[i].loginName+"</td>";
                htmlStr += "<td><input type='text' userID='"+data[i].id+"'  value='"+data[i].idcard+"' onblur='changeIDCard(this)'/></td>";
                htmlStr += "<td><input type='checkbox' userID='"+data[i].id+"'  "+(data[i].isIgnore == 1 ? "checked" : "")+" onclick='changeIgnore(this)'/></td>";
                htmlStr += "</tr>";

                $("#oldUserList").append(htmlStr);
            }
        });

        //菜单是从过AJAX在后台查询得出的详细列表
        $.getJSON("<%=basePath%>/view/userinfo_new", {fydm:currentFydm}, function (data) {
    //            alert(data.length);
             var htmlStr = createNewTable(data);
            $("#newUserList").append(htmlStr);
        });
    }


    function createNewTable(data){
        var htmlStr = "";
        var bmStr = "";
        var index = 1;
        var plusStr = "";
        for(var i=0;i<data.length;i++){

            bmStr = getDepartmentName(data[i].courtCode,data[i].deptNo);

            if(data[i].userType == 3){
                plusStr = "style='color: #4F4F4F;'";
                bmStr = "人民陪审员";
            }else{
                plusStr = "style='color: #4F4F4F;'";
            }



            //过滤院领导
            if(bmStr.indexOf("领导")!=-1){
                continue;
            }

            htmlStr += "<tr uname='"+data[i].fullname+"' idc='"+data[i].idcard+"' newRow='1' align='center' "+plusStr+">";
            htmlStr += "<td height='25'>"+(index++)+"</td>";
            htmlStr += "<td>"+data[i].id+"</td>";
            htmlStr += "<td>"+data[i].fullname+"</td>";
            htmlStr += "<td>"+bmStr+"</td>";
            htmlStr += "<td>"+data[i].username+"</td>";
            htmlStr += "<td>"+data[i].idcard+"</td>";
            htmlStr += "</tr>";
        }
        return htmlStr;
    }

    function createNewTable2(data){
        var htmlStr = "";
        var bmStr = "";
        var fyStr = "";
        var index = 1;
        for(var i=0;i<data.length;i++){

            bmStr = getDepartmentName(data[i].courtCode,data[i].deptNo);
            fyStr = getFyName(data[i].courtCode,data[i].deptNo);

            //过滤院领导
            if(bmStr.indexOf("领导")!=-1){
                continue;
            }

            htmlStr += "<tr uname='"+data[i].fullname+"' idc='"+data[i].idcard+"' newRow='1' align='center' style='color: #4F4F4F;'>";
            htmlStr += "<td height='25'>"+(index++)+"</td>";
            htmlStr += "<td>"+data[i].id+"</td>";
            htmlStr += "<td>"+fyStr+"</td>";
            htmlStr += "<td>"+data[i].fullname+"</td>";
            htmlStr += "<td>"+bmStr+"</td>";
            htmlStr += "<td>"+data[i].username+"</td>";
            htmlStr += "<td>"+data[i].idcard+"</td>";
            htmlStr += "</tr>";
        }
        return htmlStr;
    }

    var searchDialog;
    //初始化BUI相关组件
    BUI.use(['common/search', 'bui/tree', 'bui/data', 'bui/calendar', 'bui/overlay', 'bui/form', 'bui/uploader'],
        function (Search, Tree, Data, Calendar, Overlay, Form, Uploader) {
            searchDialog = new Overlay.Dialog({
                title: "查询信息",
                width: 750,
                height: 405,
                contentId: "search",
                buttons: [

                    {
                        text: '关闭', elCls: 'button', handler: function () {
                        this.close();
                    }
                    }
                ]
            });

        });

    /**
     * 获取全市的用户信息查询
     **/
    function getAllUser(vUsername){


        if(vUsername == ""){
            return;
        }

        $.getJSON("<%=basePath%>/view/userinfo_new", {username:vUsername}, function (data) {
        <%--$.getJSON("<%=basePath%>/view/userinfo_new", {username:'薛海明'}, function (data) {--%>

            var htmlStr = createNewTable2(data);

            $("#allUserList").html("");
            $("#allUserList").append(htmlStr);
            searchDialog.show();//显示

        });
    }


    function filterTable(type,val,stype){
        if(type == 1){

            if(val == ""){
                $("tr[oldRow=1]").show();
                return;
            }

            $("tr[oldRow=1]").hide();

            if(stype == 1){
                $("tr[oldRow=1][uname*="+val+"]").show();
            }else{
                $("tr[oldRow=1][idc*="+val+"]").show();
            }

        }else{
            if(val == ""){
                $("tr[newRow=1]").show();
                return;
            }

            $("tr[newRow=1]").hide();
            if(stype == 1){
                $("tr[newRow=1][uname*="+val+"]").show();
            }else{
                $("tr[newRow=1][idc*="+val+"]").show();
            }
        }

    }


    function changeIDCard(obj){
        obj = $(obj);


        var vUserID = obj.attr("userID");
        var vIdCard = obj.val();

        if(vIdCard == ''){
            return;
        }

        if(!IdCardValidate(vIdCard))
        {
            alert("身份证格式不正确。");
            return;
        }

        var uData = {userid:vUserID,idcard:vIdCard};


        $.post("<%=path%>/view/updateXtptUser",uData,function(result){
            if(result != 1){
                alert("身份证信息已被占用，请检查后录入。");
            }else{
                var parentTr =obj.parent().parent();
                parentTr.css("background-color","#CEFFCE");
                parentTr.css("color","#4F4F4F");
            }
        });
    }

    function changeIgnore(obj){
        obj = $(obj);

        var vUserID = obj.attr("userID");
        var vIgnore = obj.attr("checked") == "checked" ? 1 : 0;



        var uData = {userid:vUserID,ignore:vIgnore};


        $.post("<%=path%>/view/updateXtptUser",uData,function(result){
            if(result == 1){
                if(vIgnore == 1){
                    $("#tr_"+vUserID).css("background-color","#d0d0d0");
                    $("#tr_"+vUserID).css("color","gray");

                }else{
                    $("#tr_"+vUserID).css("background-color","white");
                    $("#tr_"+vUserID).css("color","#4F4F4F");
                }
            }
        });
    }


    function changeList(type){
        $("tr[oldRow=1]").hide();
        if(type == 1){
            $("tr[oldRow=1]").show();
        }
        if(type == 2){
            $("tr[oldRow=1][idc='']").show();
        }
        if(type == 3){
            $("tr[oldRow=1][idc!='']").show();
        }
    }



    var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];    // 加权因子
    var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];            // 身份证验证位值.10代表X
    function IdCardValidate(idCard) {
//        idCard = trim(idCard.replace(/ /g, ""));               //去掉字符串头尾空格
        if (idCard.length == 15) {
            return isValidityBrithBy15IdCard(idCard);       //进行15位身份证的验证
        } else if (idCard.length == 18) {
            var a_idCard = idCard.split("");                // 得到身份证数组
            if(isValidityBrithBy18IdCard(idCard)&&isTrueValidateCodeBy18IdCard(a_idCard)){   //进行18位身份证的基本验证和第18位的验证
                return true;
            }else {
                return false;
            }
        } else {
            return false;
        }
    }
    /**
     * 判断身份证号码为18位时最后的验证位是否正确
     * @param a_idCard 身份证号码数组
     * @return
     */
    function isTrueValidateCodeBy18IdCard(a_idCard) {
        var sum = 0;                             // 声明加权求和变量
        if (a_idCard[17].toLowerCase() == 'x') {
            a_idCard[17] = 10;                    // 将最后位为x的验证码替换为10方便后续操作
        }
        for ( var i = 0; i < 17; i++) {
            sum += Wi[i] * a_idCard[i];            // 加权求和
        }
        valCodePosition = sum % 11;                // 得到验证码所位置
        if (a_idCard[17] == ValideCode[valCodePosition]) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 验证18位数身份证号码中的生日是否是有效生日
     * @param idCard 18位书身份证字符串
     * @return
     */
    function isValidityBrithBy18IdCard(idCard18){
        var year =  idCard18.substring(6,10);
        var month = idCard18.substring(10,12);
        var day = idCard18.substring(12,14);
        var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));
        // 这里用getFullYear()获取年份，避免千年虫问题
        if(temp_date.getFullYear()!=parseFloat(year)
                ||temp_date.getMonth()!=parseFloat(month)-1
                ||temp_date.getDate()!=parseFloat(day)){
            return false;
        }else{
            return true;
        }
    }
    /**
     * 验证15位数身份证号码中的生日是否是有效生日
     * @param idCard15 15位书身份证字符串
     * @return
     */
    function isValidityBrithBy15IdCard(idCard15){
        var year =  idCard15.substring(6,8);
        var month = idCard15.substring(8,10);
        var day = idCard15.substring(10,12);
        var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));
        // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法
        if(temp_date.getYear()!=parseFloat(year)
                ||temp_date.getMonth()!=parseFloat(month)-1
                ||temp_date.getDate()!=parseFloat(day)){
            return false;
        }else{
            return true;
        }
    }
    //去掉字符串头尾空格
    function trim(str) {
        return str.replace(/(^\s*)|(\s*$)/g, "");
    }

</script>
