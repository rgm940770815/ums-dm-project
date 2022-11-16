<%--
  Created by IntelliJ IDEA.
  User: 2015
  Date: 2015/9/23
  Time: 14:25
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
    <title>陪审员个人简历</title>
    <jsp:include page="/basic_import.jsp"></jsp:include>
    <style type="text/css">
        table{
            padding:0px;
            margin:0 auto;
        }
        table caption{
            font-size:28px;
            font-family:'黑体';
            font-weight:bolder;
        }
    </style>
    <script type="text/javascript" src="<%=basePath%>js/common/codelist.js"></script>
</head>
<body>
    <table border="1" width="900px">
        <caption>人民陪审员登记表</caption>
        <tr>
            <td width="150px" align="center" height="40px" >姓 名</td>
            <td width="150px" align="center" height="40px" name="userInfo.fullname" id="fullname"></td>
            <td width="150px" align="center" height="40px">曾用名</td>
            <td width="150px" align="center" height="40px" name="userInfo.formerName" id="formerName"></td>
            <td width="150px" align="center" height="40px">性 别</td>
            <td width="150px" align="center" height="40px" typeId="3" id="gender" class="code"></td>
            <td rowspan="4" align="center" height="40px" width="150px" >
                <div>
                    <img src="" alt="一寸照片" width="130px" height="150px" id="photo"/>
                </div>
            </td>
        </tr>
        <tr>
            <td width="150px" align="center" height="40px">出生年月</td>
            <td width="150px" align="center" height="40px" name="userInfo.birthday" id="birthday"></td>
            <td width="150px" align="center" height="40px">民 族</td>
            <td width="150px" align="center" height="40px" typeId="5" id="nation" class="code"></td>
            <td width="150px" align="center" height="40px" >籍 贯</td>
            <td width="150px" align="center" height="40px" id="hometown" name="userInfo.hometown"></td>
        </tr>
        <tr>
            <td width="150px" align="center" height="40px">常住地</td>
            <td width="150px" align="center" height="40px" id="localAddress" name="userInfo.localAddress"></td>
            <td width="150px" align="center" height="40px">身份证号码</td>
            <td align="center" height="40px" colspan="3" id="idcard" name="userInfo.idcard"></td>
        </tr>
        <tr>
            <td width="150px" align="center" height="40px">政治面貌</td>
            <td width="150px" align="center" height="40px" id="political" typeId="13" class="code"></td>
            <td width="150px" align="center" height="40px">婚姻状况</td>
            <td width="150px" align="center" height="40px" id="maritalStatus" typeId="6" class="code"></td>
            <td width="150px" align="center" height="40px">健康状况</td>
            <td width="150px" align="center" height="40px" id="physicalCondition" typeId="10" class="code"></td>
        </tr>
        <tr>
            <td width="150px" rowspan="2" align="center" height="40px" valign="center">学历学位</td>
            <td width="150px" align="center" height="40px">全日制教育</td>
            <td width="150px" align="center" height="40px" id="xlxw1"></td>
            <td width="150px" align="center" height="40px">毕业院校系<br/>及专业</td>
            <td width="150px" colspan="3" align="center" height="40px" id="school1"></td>
        </tr>
        <tr>
            <td width="150px" align="center" height="40px">在职教育</td>
            <td width="150px" align="center" height="40px" id="xlxw2"></td>
            <td width="150px" align="center" height="40px">毕业院校系<br/>及专业</td>
            <td width="150px" colspan="3" align="center" height="40px" id="school2"></td>
        </tr>
        <tr>
            <td width="150px" align="center" height="40px">职 业</td>
            <td width="150px" align="center" height="40px" id="jurorWork" name="jurorInfo.jurorWork"></td>
            <td width="150px" align="center" height="40px">专业技术职务</td>
            <td width="150px" align="center" height="40px" id="s_ps_zyjszw" name="userInfo.s_ps_zyjszw"></td>
            <td width="150px" align="center" height="40px">熟悉专业<br/>有何特长</td>
            <td width="150px" align="center" height="40px" colspan="2" id="zytc" name="userInfo.zytc"></td>
        </tr>
        <tr>
            <td width="150px" align="center" height="40px">工作单位</td>
            <td width="150px" align="center" height="40px" colspan="4" id="company" name="jurorInfo.company"></td>
            <td width="150px" align="center" height="40px">职务</td>
            <td width="150px" align="center" height="40px" id="s_ps_zw" name="userInfo.s_ps_zw"></td>
        </tr>
        <tr>
            <td width="150px" align="center" height="40px">通讯地址</td>
            <td width="150px" align="center" height="40px" colspan="4" id="postalAddress" name="userInfo.postalAddress"></td>
            <td width="150px" align="center" height="40px">邮编</td>
            <td width="150px" align="center" height="40px"></td>
        </tr>
        <tr>
            <td width="150px" align="center" height="40px">座机号码</td>
            <td width="150px" align="center" height="40px" colspan="2" id="machineNumber" name="userInfo.machineNumber"></td>
            <td width="150px" align="center" height="40px">手机号码</td>
            <td width="150px" align="center" height="40px" colspan="3" id="phoneNumber" name="userInfo.phoneNumber"></td>
        </tr>
        <tr>
            <td width="150px" align="center" height="40px">简历</td>
            <td width="150px" align="center" colspan="7" height="250px"></td>
        </tr>
    </table>
    <script type="text/javascript">
        $(function (){
            var id='${param.id}';
            //读取审判员信息
            $.getJSON("<%=basePath%>juror/one", {
                "jurorInfo.userId": id,
                _: new Date().getTime()
            }, function (data) {
                $("table [name^='jurorInfo.']").each(function () {
                    $(this).text(data[$(this).attr("id")]);
                });
            });

            $.getJSON("<%=basePath%>userinfo/one", {id:id , _: new Date()}, function (data) {
               var userinfo = data;
                loadInfo(userinfo);
                $("table .code").each(function () {
                    var v=userinfo[$(this).attr("id")];
                    var obj=$(this);
                    $.getJSON("/ums/code/codeListByType",{typeId:$(this).attr("typeId")},function (data) {
                        for (var i = 0; i < data.length; i++) {
                            if(data[i].id==v){
                                var val=data[i].codeName;
                                obj.text(val);
                            }
                        }
                    });
                });
                //给学历学位赋值
               var v=userinfo["c_ps_xlxw"];
                $('#xlxw'+v).text("是");
                $('#school'+v).text(userinfo["s_ps_byyxjzy"]+userinfo["s_ps_zy"]);
                //给图片赋值
                $.getJSON("<%=basePath%>photo/getPhotoById", {userId: id, _: new Date()}, function (data) {
                    if (data !== null) {
                        $('#photo').attr("src",data);
                    }
                });
            });
        });
        function loadInfo(info){
            $("table [name^='userInfo.']").each(function () {
                $(this).text(info[$(this).attr("id")]);
            });
        }
    </script>
</body>
</html>