<%@ page import="java.util.UUID" %><%--
  Created by D.Yang.
  Date: 2015/1/28 0028
  Time: 15:14
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    UUID uuid = UUID.randomUUID();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <jsp:include page="/basic_import.jsp"></jsp:include>
    <link href="<%=basePath%>js/stickup/stickup.css" rel="stylesheet"/>
    <%--    <script src="<%=basePath%>js/stickup/stickUp.js" type="text/javascript"></script>--%>

    <style>
        * {
            margin: 0;
            padding: 0;
            list-style: none;
            font-family: 'Microsoft Yahei';
        }

        img {
            border: 0;
        }

        .header {
            width: 100%;
            background: #F5F5F5;
            position: fixed;
            z-index: 9999;
            opacity: 0.8;
            top: 0;
        }

        .nav {
            text-align: center;
            overflow: hidden;
        }

        .nav ul {
            display: inline-block;
        }

        .nav ul li {
            height: 50px;
            line-height: 40px;
            float: left;
            padding: 5px 5px;
            margin: 0px 5px;
            position: relative;
        }

        .nav ul li a {
            color: #666;
            font-family: 'Microsoft Yahei';
            font-size: 14px;
            text-decoration: none;
        }

        .nav ul li a:hover {
            color: #000;
            text-decoration: none;
        }

        .nav ul li span {
            display: block;
            position: absolute;
            width: 0px;
            height: 0px;
            background: #1FAEFF;
            top: 58px;
            left: 50%;
        }

        a {
            cursor: pointer;
        }

        h3 {
            padding: 0;
        }

        .hide {
            display: none;
        }

        .show {
            display: block;
        }

        .active a {
            background-color: #eeeeee;
            color: #000 !important;
        }

        .active span {
            left: 0 !important;
            right: 0 !important;
            width: 100% !important;
            height: 2px !important;
        }

        .row {
            height: 100%;
            padding: 0 80px;
            margin-right: 0px;
            margin-left: 0px;
        }

        .chart {
            width: 77vw;
            height: 500px;
        }

        .chart2, .chart3 {
            width: 77vw;
            height: 500px;
        }

        .chart3 {
            margin-top: 120px;
        }

        /**内容超出 出现滚动条 **/
        .bui-stdmod-body {
            overflow-x: hidden;
            overflow-y: auto;
        }
        #loadCount{
            font-size: 15px;
        }
        #loadCount span{
            color: #0e90d2;
        }
        #rightBode{
            width: 100%;
        }
        #list{
            width: 100%;
            display: flex;
            flex-wrap: wrap;
        }
        .item{
            display: flex;
            width: 270px;
            padding: 9px;
            border: 1px solid #DDE4EE;
            margin: 5px;
            background: #F3F7FA;
        }
        .item .photo{
            width: 88px;
        }
        .item_left{
            width: 90px;
        }
        .item_right{
            position: relative;
            width: 182px;
        }
        .fullname{
            text-align: center;
            font-size: 14px;
        }
        .wordIcon {
            width: 23px;
            position: absolute;
            right: 8px;
            top: -5px;
        }
        .content{
            font-size: 14px;
            padding: 10px;
        }
        #load{
            display: none;
            z-index: 999999;
            width: 100%;
            height: 100%;
            background: #000000aa;
            position: absolute;
            justify-content: center;
            align-items: center;
            color: #fff;
            font-size: 20px;
        }

    </style>

    <script>

    </script>
</head>
<body style="display: flex">

<div id="load">加载中···</div>
<%--树结构--%>
<div id='deptree' class="panel bui-stdmod-body span6">
    <div id="treet3"></div>
</div>

<div id="rightBode" onscroll="showScroll(this)">
    <div id="list">

    </div>
</div>

    <script src="<%=basePath%>js/common/datetools.js" type="text/javascript"></script>
    <script>
        var isLoad = false;
        var courtNo='';
        var depNo='';
        var fymc = '';
        var start = 0;
        var limit = 25;
        var pageIndex = 0;
        var nation={};//民族 map
        var political={};//政治面貌
        var level={};//法官等级

        $(function () {
            $('.nav li').hover(function () {
                $('span', this).stop().css('height', '2px');
                $('span', this).animate({
                    left: '0',
                    width: '100%',
                    right: '0'
                }, 200);
            }, function () {
                $('span', this).stop().animate({
                    left: '50%',
                    width: '0'
                }, 200);
            });
            getCode(5);
            getCode(13);
            getCode(115);
            getCode(117);


        });
        function getCode(typeId){
            $.getJSON("/ums/code/codeListByType", {typeId: typeId}, function (data) {
                data.forEach(function(item,index){
                    switch (typeId) {
                        case 5:
                            nation[item.id] = item;
                            break;
                        case 13:
                            political[item.id] = item;
                            break;
                        case 115:
                            level[item.id] = item;
                            break;
                        case 117:
                            level[item.id] = item;
                            break;
                    }

                })
            })
        }
        $("#rightBode").height(window.innerHeight-30);
        $("#rightBode").css("overflow","scroll");
        $("#treet3").height(window.innerHeight-30);
        var dialogForData;
        var baseStore;
        BUI.use(['common/search', 'bui/tree',  'bui/data', 'bui/overlay', 'bui/tab'], function (Search, Tree, Data, Overlay, Tab) {
            // $("#rightBode").height($(document).height() - 200);
            // $("#treet3").height($(document).height() - 200);
            // $(".bui-grid-bbar").height(35);
            // $("#t3").height($(".bui-grid").height());
            getUserList();
            //-------------树结构Start-----------------
            var treestore = new Data.TreeStore({
                root: {text: "全部", courtNo: "", deptNo: ""},
                url: '<%=basePath%>code/tree/children3'
            });
            var tree = new Tree.TreeList({
                render: '#treet3',
                store: treestore,
                elStyle: {border: "none"},
                multipleCheck: false,
                showRoot: true
            });
            tree.render();
            // 加载根节点，也可以让用户点击加载
            treestore.load();
            tree.on('itemclick', function (ev) {
                var item = ev.item;
                courtNo = item.courtNo;
                depNo = item.deptNo;
                var condition = {courtNo: item.courtNo, deptNo: item.deptNo};
                $(" #userinfo_search_form .search_field").each(function () {
                    condition[$(this).attr("name")] = $(this).val();
                });
                condition = $.extend({"start": 0, "pageIndex": 0}, condition);
                $("#list").html("")
                pageIndex = 0;
                start = 0;
                getUserList()
                // 查询条件
                // current_court = item.courtNo;
                // $("select[name='innerCourtNo']").attr("disabled", false);
                // if (current_court != null && current_court != '') {
                //     $("select[name='innerCourtNo']").val(current_court);
                //     $("select[name='innerCourtNo']").attr("disabled", true);
                //     loadDeptList($("#department_n"), current_court, firstLine, function () {
                //     });
                // }
                // 部门类型的修改
                if (item.deptNo != null && item.deptNo != "") {
                    $("#hide_court_no").val(item.courtNo);
                    $("#hide_dept_no").val(item.deptNo);
                    var url = '<%=path%>/code/deptDetailInfo';
                    $.post(url, condition, function (data) {
                        var str = '';
                        if (data.orgType != null && data.orgType != 0) {
                            switch (data.orgType) {
                                case 1:
                                    str += '业务部门';
                                    break;
                                case 2:
                                    str += '综合部门';
                                    break;
                                case 8:
                                    str += '派出法庭';
                                    break;
                                default:
                                    str += '未确认';
                            }
                        } else {
                            str += '未确认';
                        }
                        $("#deptNoId").html(str);
                        $(".button-dept").parent().show()
                    });
                } else {
                    $(".button-dept").parent().hide()
                }
                if (item.deptNo != null && item.deptNo != "") {
                    $(".up_move_btn").parent().show();
                    $(".down_move_btn").parent().show();
                } else {
                    $(".up_move_btn").parent().hide();
                    $(".down_move_btn").parent().hide();
                }
            });
            //-------------树结构End-----------------


        });

        function getUserList() {
            isLoad = true;
            $("#load").css("display","flex")
            $("#load").html("加载中···");
            var url = "<%=basePath%>view/userinfo2";
            var imgUrl = "<%=basePath%>photo/getPhotoById";
            var param = {
                userType:1,
                isInfoComplete:0,
                isValid: 1,
                simpleQuery: true,
                start: start,
                limit: limit,
                pageIndex: pageIndex,
                field: 'sortNo',
                direction: 'ASC',
                courtNo: courtNo,
                deptNo:depNo
            };
            $.post(url,param,function (data) {
                var list = '';
                var rows = data.rows;
                var src = '';

                console.log("rows",rows);
                rows.forEach(function (obj,index) {

                    var calcYears2 = calcYears(obj.birthday)
                    var age = '';
                    var nationText = ''
                    var politicalText = '';
                    var administrationPositionText = '';
                    var levelText = '';
                    if(obj.nation){
                        nationText = nation[obj.nation].codeName+"，";
                    }
                    if(obj.political){
                        politicalText = political[obj.political].codeName+"，";
                    }
                    if(obj.administrationPositionText){
                        administrationPositionText = '，'+obj.administrationPositionText;
                    }
                    if(obj.level){
                        levelText = "，"+level[obj.level].codeName;
                    }
                    if (!isNaN(calcYears2)) {
                        age = calcYears2+"岁"
                    }
                    var item = "<div class='item'>" +
                        "<div class='item_left' id='"+obj.id+"'></div>" +
                        "<div class='item_right'>" +
                        "<div class='fullname'>"+obj.fullname+"</div>" +
                        "<div onclick='load(\""+obj.id+"\")' ><img class='wordIcon' src='../../images/word_icon.png' ></div>" +
                        "<div class='content'>" +
                        obj.genderText+"，" +
                        age+"，(" +
                        (new Date(obj.birthday)).Format('yyyy年MM月')+"生)，" +
                        nationText+
                        obj.hometown+"人，" +
                        politicalText +
                        obj.educationBackgroundText+"，" +
                        (new Date(obj.workDate)).Format('yyyy年MM月')+"参加工作，" +
                        obj.courtNoText+
                        administrationPositionText+
                        levelText +
                        "</div>" +
                        "</div>" +
                        "</div>"
                    // list += item;
                    $("#list").append(item);
                    $.ajax({
                        type:"post",
                        url:imgUrl,
                        async:true,
                        dataType:"json",
                        data:{
                            userId:obj.id
                        },
                        success:function(photoSrc) {
                            $("#"+obj.id).html("<img class='photo'  src='"+photoSrc+"'>")
                            // src = photoSrc;
                        }

                    })
                })
                $("#load").css("display","none")
                isLoad = false;
                // $("#list").append(list);


            })
        }

        //页面触底事件
        function showScroll(e){
            const windowH = e.clientHeight // 元素高度
            const documentH= e.scrollHeight // 可滑动高度
            const scrollH= e.scrollTop // 滑动高度，滑块到顶部的距离
            if(windowH +scrollH >= documentH){
                console.log("页面触底")
                if(!isLoad){
                    pageIndex += 1;
                    start = pageIndex*limit;
                    getUserList();
                }
            }

        }
        function load(id) {
            $("#load").css("display","flex")
            var url = '<%=path%>/view/wordEdit';
            var params = {
                id: id,
                uuid: '<%=uuid%>'
            };
            $.post(url, params, function (data) {
                if (data.success) {
                    var params = {
                        id: id,
                        uuid: '<%=uuid%>'
                    };
                    // init();
                    // wait(id)
                    $("#load").css("display","none")
                    window.open('<%=path%>/view/dlWord?uuid=<%=uuid%>&id='+id, '_self');
                    <%--window.open('<%=path%>/view/downWord?uuid=<%=uuid%>&id='+id);--%>
                } else {
                    alert("加载失败，请重试！");
                }
            });
        }


        //延迟下载，防止未生成完毕导致404
        <%--function wait(id) {--%>
        <%--    var i = 5;--%>
        <%--    var that = $("#load");--%>
        <%--    var url = '<%=path%>/view/dlWord';--%>
        <%--    var params = {--%>
        <%--        id: id,--%>
        <%--        uuid: '<%=uuid%>'--%>
        <%--    };--%>
        <%--    that.html("文档生成中，请等待" + i + "秒......");--%>
        <%--    var interval = setInterval(function () {--%>
        <%--        i--;--%>
        <%--        that.html("文档生成中，请等待" + i + "秒......");--%>
        <%--        if (i == 0) {--%>
        <%--            $("#load").css("display","none")--%>
        <%--            window.clearInterval(interval);--%>
        <%--            window.open('<%=path%>/view/dlWord?uuid=<%=uuid%>&id='+id, '_self');--%>
        <%--        }--%>
        <%--    }, 1000);--%>
        <%--}--%>


        Date.prototype.Format = function (fmt) {
            var o = {
                "M+": this.getMonth() + 1, //月份
                "d+": this.getDate(), //日
                "h+": this.getHours(), //小时
                "m+": this.getMinutes(), //分
                "s+": this.getSeconds(), //秒
                "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                "S": this.getMilliseconds() //毫秒
            };
            if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            for (var k in o)
                if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            return fmt;
        }
    </script>
</body>
</html>