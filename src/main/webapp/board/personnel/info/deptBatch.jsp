<%--
    Document   : user2
    Created on : 2014-12-23, 16:53:11
    Author     : Diluka
--%>
<%@page import="cn.net.withub.ums.common.UmsConstant" %>
<%@page import="cn.net.withub.ums.entity.UmsUserInfoView" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    UmsUserInfoView userInfo = (UmsUserInfoView)request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
    boolean isSuperUser = false;
    if(userInfo.getId().equals("-1")){
        isSuperUser = true;
    }
    String userCourtCode = userInfo.getCourtCode();
%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>批量处理序号</title>
    <jsp:include page="/basic_import.jsp"></jsp:include>
    <script src="userinfo/js/json.js"></script>
    <style>
        .contentDiv{
            border: 1px solid #a0a49a;margin-left:20px;margin-right:20px;overflow: scroll;
        }
        .dept_one{
            background-color:gainsboro;border-radius: 20px;border: 2px dashed #5996f5;margin-top: 5px;margin-right: 10px;margin-left: 10px;
        }
        .style,input{
            cursor: pointer;
        }
        /*有变动*/
        .isChanged{
            background-color: #9280fd;
        }
        /*相似*/
        .isSimilarity{
            background-color: #fd6367;
        }
    </style>
</head>
<body>

<div class="row">
    <div>
        <ul class="breadcrumb">
            <li><span>人事管理</span><span class="divider">/</span></li>
            <li><span>信息管理</span><span class="divider">/</span></li>
            <li class="active">批量处理人员序号</li>
        </ul>
    </div>
</div>

<div>
    <form class="form-horizontal" >
        <div class="row">
            <div class="control-group span8">
                <label class="control-label">法院：</label>
                <div class="controls">
                    <select id="courtSelect" onchange="getContent()"></select>
                </div>
            </div>
            <div class="control-group span12">
                <label class="control-label">序号生成方式：</label>
                <div class="controls" style="line-height: 28px">
                    <label class="style" title="自由添加序号" for="style_1">自由：</label>
                    <input class="style" title="自由添加序号" type="radio"  id="style_1" name="style" value="1" checked>
                    <label class="style" title="当前部门最大序号累加1填入点击的序号框" for="style_2">最大值累加：</label>
                    <input class="style" title="当前部门最大序号累加1填入点击的序号框" type="radio" id="style_2" name="style" value="2">
                    <label class="style" title="初始值0开始累加1之后填入点击的序号框" for="style_3">初始值累加：</label>
                    <input class="style" title="初始值0开始累加1之后填入点击的序号框" type="radio" id="style_3" name="style" value="3">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">部门数：</label>
                <div class="controls">
                    <input type="text" id="countAllDept" value="2" style="width: 60px">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">总人数：</label>
                <div class="controls">
                    <input type="text" id="countAllPerson" style="width: 60px">
                </div>
            </div>
            <div class="control-group span6">
                <div class="controls">
                    <input type="button" class="button" value="保存" onclick="toSave()">
                </div>
            </div>
        </div>
    </form>
</div>

<div id="contentDiv" class="contentDiv">
    <form class="form-horizontal" id="contentForm"></form>
</div>
<div class="row">
    <div class="control-group span12">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="control-label" style="color: #0A64A4">说明：红色表示存在该人员相同序号的其他人员，蓝色表示该人员序号有变动。</label>
    </div>
</div>
<script>
    var screenhight = window.screen.height*0.6;
    $("#contentDiv").css("height",screenhight+"px")
    $.post("<%=basePath%>/code/codeListByType", {typeId: 1}, function (data) {
        for (var i = 0; i < data.data.length; i++) {
            var selectOne = "<option courtNo="+data.data[i].id+" value="+data.data[i].courtCode+">"+data.data[i].codeName+"</option>"
            $("#courtSelect").append(selectOne);
        }
        if(<%=isSuperUser%>) {
            $("#courtSelect").select2().select2("val",'M00');
        }
        else {
            $("#courtSelect").select2().select2("val",'<%=userCourtCode%>');
            $("#courtSelect").attr("disabled","disabled");
        }
    });
    function getContent(fydm_)
    {
        $.addLoading();
        $("#contentForm").empty();
        var fydm =fydm_;
        if(!fydm)
        {
            fydm = $("#courtSelect").val();
        }
        $.post("<%=basePath%>/deptBatch/getAllUserMap", {fydm: fydm}, function (data) {
            var deptList = data.allDept;
            var countAllDept = 0,countAllPerson = 0;
            for(var index in deptList)
            {
                var deptNo = deptList[index].deptNo+"";
                var dept_sort = deptList[index].sortNo;
                if(dept_sort==null || dept_sort == undefined ||dept_sort=="null") dept_sort = "暂无";
                var deptOneId = "dept_one"+index;
                var title_dept = "原序号"+dept_sort;
                var dept_one = " <div class='dept_one' id="+deptOneId+">";
                dept_one +="<input type='hidden' class='recodeIndex' value='0'>"
                dept_one += "<div class='row'>"
                dept_one += "<div class='control-group span6'>"
                dept_one += "<label class='control-label'>"+deptList[index].deptName+"：</label>";
                dept_one += "<div class='controls'><input type='text' class='dept_in' style='width: 50px' title="+title_dept+" sortNo="+dept_sort+" deptNo="+deptNo+" value="+dept_sort+" onchange='judgeChange_(this)'></div>";
                dept_one += "</div>";
                dept_one += "<div class='control-group span4'> <div class='controls'><input type='button' class='button' deptNo="+deptNo+" value='全清' onclick='clearByDept(this)'><input type='button' class='button' deptNo="+deptNo+" value='返回' onclick='rollBackByDept(this)'> </div></div>";
                dept_one += "</div>";
                dept_one += "<hr>";
                dept_one += "<div class='row'>";
                for(var deptNo_ in data)
                {
                    if(deptNo_ == deptNo)
                    {
                        countAllDept ++;
                        var userList = data[deptNo_];
                        for(var i=0;i <userList.length;i++ )
                        {
                            countAllPerson++;
                            var userSort = userList[i].sortNo;
                            if(userSort==null || userSort == undefined ||userSort=="null") userSort = "暂无";
                            var title = "原序号："+userSort;
                            if(userList[i].id=="") continue;
                            dept_one += "<div class='control-group span6'>";
                            dept_one += "<label class='control-label'><a title='点击查看详情' onclick='openDetail(this)' href='#'>"+userList[i].fullname+"</a></label>";
                            dept_one += "<div class='controls'><input type='text' class='user_in' title="+title+" sortNo="+userSort+" style='width: 50px' deptNo="+deptNo+" userid="+userList[i].id+"  value="+userSort+"  onclick='autoChangeNo(this)' onchange='judgeChange(this)'> </div>";
                            dept_one += "</div>";
                        }
                    }
                }
                dept_one += "</div>";
                dept_one += "</div>";
                $("#contentForm").append(dept_one);
                judgeSimilarity(deptOneId);
                judgeSimilarity_();
            }
            $("#countAllDept").val(countAllDept);
            $("#countAllPerson").val(countAllPerson);
            $.hideLoading();
        });

    }
    /*根据传入的部门id,将该部门下所有员工的序号返回初始值*/
    function rollBackByDept(this_){
        var deptOneId =  $(this_).closest(".dept_one").attr("id");
        $(this_).closest(".dept_one").find(".recodeIndex").val(0);
        $(this_).closest(".row").next().next().find(".user_in").each(function(){
            var initValue = $(this).attr("sortNo");
            $(this).val(initValue);
            $(this).removeClass("isChanged");
        });
        judgeSimilarity(deptOneId);
    }
    /*根据传入部门的部门id，清除该部门下所有员工的序号*/
    function clearByDept(this_)
    {
        $(this_).closest(".dept_one").find(".recodeIndex").val(0);
        $(this_).closest(".row").next().next().find(".user_in").each(function(){
            $(this).val("");
            $(this).addClass("isChanged");
            $(this).addClass("isSimilarity");
        });
    }
    /*自动填值*/
    function autoChangeNo(this_){
        var deptOneId = $(this_).closest(".dept_one").attr("id");
        var style;
        $("input[name=style]").each(function () {
            if($(this)[0].checked)style = $(this).val();
        })
        if(style == 1) {

        }else if(style == 2) {
            var maxIndex = getMaxIndex(this_);
            $(this_).val(maxIndex+1);
            judgeChange(this_);
            judgeSimilarity(deptOneId);
        }
        else if(style == 3){
            var nowIndex = parseInt($(this_).closest(".dept_one").find(".recodeIndex").val());
            $(this_).val(nowIndex+1);
            $(this_).closest(".dept_one").find(".recodeIndex").val(nowIndex+1);
            judgeChange(this_);
            judgeSimilarity(deptOneId);
        }
    }
    function getMaxIndex(this_) {
        var maxIndex=1;
        $(this_).closest(".dept_one").find(".user_in").each(function(){
            var this_val =parseInt($(this).val()) ;
            if(maxIndex < this_val){
                maxIndex = this_val;
            }
        })
        return maxIndex;
    }
    /*判断每个员工的序号是否有改变，有变动的改变其背景颜色*/
    function judgeChange(this_){
        var initValue = $(this_).attr("sortNo");
        var nowValue = $(this_).val();
        if(nowValue != initValue)
            $(this_).addClass("isChanged");
        else
            $(this_).removeClass("isChanged");
        var deptId = $(this_).closest(".dept_one").attr("id");
        judgeSimilarity(deptId);
    }
    /*判断部门是否有变动，将有变动的改变背景颜色*/
    function judgeChange_(this_){
        var select_value = $(this_).val();
        var initValue =  $(this_).attr("sortNo");
        if(select_value != initValue)
            $(this_).addClass("isChanged");
        else
            $(this_).removeClass("isChanged");
        judgeSimilarity_();
    }
    /*判断每个部门下人员是否有相同的序号*/
    function judgeSimilarity(dept_id){
        $("#"+dept_id).find(".user_in").each(function(indexOuter,obj){
            var this_value = $(this).val();
            var this_ = $(this);
            var isSimilarity = false;//检查是否有相似的序号
            $("#"+dept_id).find(".user_in").each(function(indexInner,obj){
                if(indexOuter != indexInner)
                {
                    var inner_value = $(this).val();
                    if(this_value == inner_value)
                    {
                        isSimilarity = true;
                        $(this_).addClass("isSimilarity");
                        $(this).addClass("isSimilarity");
                    }
                }
            });
            if(!isSimilarity) {
                $(this_).removeClass("isSimilarity");
            }
        })
    }
    /*判断部门是否有相同的序号*/
    function judgeSimilarity_(){
        $(".dept_in").each(function(indexOuter,obj){
            var this_value = $(this).val();
            var this_ = $(this);
            var isSimilarity = false;//检查是否有相似的序号
            $(".dept_in").each(function(indexInner,obj){
                if(indexOuter != indexInner)
                {
                    var inner_value = $(this).val();
                    if(this_value == inner_value)
                    {
                        isSimilarity = true;
                        $(this_).addClass("isSimilarity");
                        $(this).addClass("isSimilarity");
                    }
                }
            });
            if(!isSimilarity) {
                $(this_).removeClass("isSimilarity");
            }
        })
    }
    /*打开详情页*/
    function openDetail(this_){
        var userid = $(this_).parent().next().find(".user_in").attr("userid");
        window.open("<%=basePath%>view/detail?id=" + userid)
    }

    function addLoading(){
        if($("#FullScreenMask").length>0){
            $("#FullScreenMask").css("display","block");
        }else{
            var loading="<style>@-moz-keyframes spinPulse{0{box-shadow:0 0 1px #2187e7;opacity:0;-moz-transform:rotate(160deg)}50%{opacity:1;-moz-transform:rotate(145deg)}100%{opacity:0;-moz-transform:rotate(-320deg)}}@-moz-keyframes spinoffPulse{0{-moz-transform:rotate(0)}100%{-moz-transform:rotate(360deg)}}@-webkit-keyframes spinPulse{0{box-shadow:0 0 1px #2187e7;opacity:0;-webkit-transform:rotate(160deg)}50%{opacity:1;-webkit-transform:rotate(145deg)}100%{opacity:0;-webkit-transform:rotate(-320deg)}}@-webkit-keyframes spinoffPulse{0{-webkit-transform:rotate(0)}100%{-webkit-transform:rotate(360deg)}}.container{overflow:visible;margin:0 auto;padding:0;width:100vw;position: relative;height: 50vh;}.content{margin:0 auto;width:100vw;position: absolute;bottom: -40px;}.circle{margin:0 auto;width:50px;height:50px;box-sizing: border-box;border:5px solid rgba(0,183,229,0.9);border-right:5px solid rgba(0,0,0,0);border-left:5px solid rgba(0,0,0,0);border-radius:50px;background-color:rgba(0,0,0,0);box-shadow:0 0 35px #2187e7;opacity:.9;-moz-animation:spinPulse 1s infinite ease-in-out;-webkit-animation:spinPulse 1s infinite linear}.circle1{position:relative;top:-40px;margin:0 auto;box-sizing: border-box;width:30px;height:30px;border:5px solid rgba(0,183,229,0.9);border-right:5px solid rgba(0,0,0,0);border-left:5px solid rgba(0,0,0,0);border-radius:50px;background-color:rgba(0,0,0,0);box-shadow:0 0 15px #2187e7;opacity:.9;-moz-animation:spinoffPulse 1s infinite linear;-webkit-animation:spinoffPulse 1s infinite linear}</style><div class=\"container\"><div class=\"content\"><div class=\"circle\"></div><div class=\"circle1\"></div></div></div>";
            $("body").append("<div id=\"FullScreenMask\" style=\"width: 100vw;height: 100vh;position: fixed;z-index:1000000;left:0px;top:0px;background-color: rgba(0,0,0,0);filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#00000000,endColorstr=#00000000);\">"+loading+"</div>");
        }
    }
    $.addLoading=addLoading;
    function hideLoading(){
        $("#FullScreenMask").css("display","none");
    }
    $.hideLoading=hideLoading;

   <%--if(!<%=isSuperUser%>)--%>
   <%--     getContent("M10");--%>

    function toSave()
    {
        var param_user = [],param_dept = [];
        $(".user_in").each(function(){
            var initValue = $(this).attr("sortNo");
            var nowValue = $(this).val();
            if(nowValue != initValue){
                var one = {};
                var userId = $(this).attr("userid");
                one["id"] = userId;
                one["sortNo"] = nowValue;
                param_user.push(one);
            }
        });
        var courtNo = $($("#courtSelect")[0].options[$("#courtSelect")[0].selectedIndex]).attr("courtNo");
        $(".dept_in").each(function(){
            var initValue = $(this).attr("sortNo");
            var nowValue = $(this).val();
            if(nowValue != initValue){
                var one = {};
                var deptNo = $(this).attr("deptNo");
                one["deptNo"] = deptNo;
                one["sortNo"] = nowValue;
                one["courtNo"] = courtNo;
                param_dept.push(one);
            }
        });
        var isSubmit = true,userStr,deptStr;
        if(getLength(param_user) <= 0 && getLength(param_dept) <= 0)  {
            isSubmit = false;
            BUI.Message.Alert("您还未进行任何操作，请修改序号！", "warning");
        }
        else{
            userStr = JSON.stringify(param_user);
            deptStr = JSON.stringify(param_dept);
        }

        if(isSubmit){
            if($(".isSimilarity").length>0) {
                BUI.Message.Confirm(
                        '还有相同的序号没有处理，确认忽略此问题？',
                        function () {
                            submit_(userStr,deptStr);
                        },
                        'question');
            }
            else {
                submit_(userStr,deptStr);
            }
        }

    }
    function submit_(userStr,deptStr){
        var success_user,success_dept,flag1=false,flag2=false;
        var showMsg="";
        if(deptStr != "[]") {
            var url = "<%=basePath%>/deptBatch/updateDeptIndex";
            $.ajax({
                type: "post",
                data: {deptStr: deptStr},
                url: url,
                async:false,//让ajax先执行
                success: function (data) {
                    if(data.success) {
                        flag2 = true;
                        success_dept = "部门状态：成功更新"+data.success+"条信息";
                    }
                    else if(data.error)
                        success_dept = "部门状态：更新失败";
                    showMsg += success_dept;
                }
            });
        }
        if(userStr != "[]"){
            var url = "<%=basePath%>/deptBatch/updateUserIndex";
            $.ajax({
                type: "post",
                data: {userStr: userStr},
                url: url,
                async:false,//让ajax先执行
                success: function (data) {
                    if(data.success){
                        flag1 = true;
                        if(flag2)
                            success_user = "，人员状态：成功更新"+data.success+"条信息";
                        else
                            success_user = "人员状态：成功更新"+data.success+"条信息";
                    }
                    else if(data.error) {
                        if(flag2)
                            success_user = ",人员状态：更新失败";
                        else
                            success_user = "人员状态：更新失败";
                    }
                    showMsg += success_user;
                }
            });
        }

        setTimeout(function(){
            if(flag1 || flag2)
                BUI.Message.Alert(showMsg, "success");
            else
                BUI.Message.Alert("更新失败", "error");
        },2000);


    }
    function getLength(array){
        var index = 0;
        for(var i in array){
            index++;
        }
        return index;
    }
</script>
</body>

</html>
