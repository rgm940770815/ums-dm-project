<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../../common/layui/css/layui.css">
    <title>统计</title>
    <script src="<%=basePath%>js/jquery/jquery.js" type="text/javascript"></script>
    <script src="../../common/layui/layui.js" type="text/javascript"></script>
    <style type="text/css">
        .header {
            background: #F5F5F5;
            z-index: 100;
            opacity: 0.8;
            padding: 12px 15px;
        }

        .base-info {
            min-height: 18px;
            padding: 10px;
            border: 1px solid #bcbcbc;
            margin: 10px 15px 5px 15px;
            box-shadow: 0 0 1px #BCBCBC;
        }

        .site-tree {
            border-right: 1px solid #eee;
            width: 100%;
            padding: 5px 0 20px;
            display: inline-block;
            vertical-align: top;
            font-size: 14px;
        }

        .site-tree .layui-tree {
            line-height: 32px;
        }

        .site-tree .layui-tree .label-c {
            /*cursor: pointer;*/
        }

        .site-tree .layui-tree .label-c h2 {
            line-height: 36px;
            border-left: 5px solid #5fb878;
            margin: 0px 0 5px;
            padding: 0 10px;
            background-color: #f2f2f2;
            font-size: 14px;
            font-weight: 600;
        }

        /*.site-tree .layui-tree .label-c.layui-this h2, .site-tree .layui-tree .label-c:hover h2, .site-tree .layui-tree .label-c.site-tree-noicon:hover a {*/
        /*    border-left: 5px solid #FFB800;*/
        /*}*/

        .site-tree .layui-tree .site-tree-noicon a cite {
            padding-left: 15px;
        }

        .site-tree .layui-tree .label-c a {
            border-left: 5px solid #fff;
        }

        .site-tree .layui-tree .label-c a cite {
            padding: 0 8px;
        }

        .site-tree .layui-tree .label-c a em {
            font-size: 12px;
            color: #bbb;
            padding-right: 5px;
            font-style: normal;
        }

        .site-tree .layui-tree .layui-this a {
            color: #01AAED;
        }

        .content-item-info {
            font-size: 15px;
            min-height: 18px;
            padding: 10px;
            border: 1px solid #bcbcbc;
            margin: 5px;
            box-shadow: 0 0 1px #BCBCBC;
        }

        .c-item-show {
            display: inline-block;
            margin-right: 25px;
            padding: 0 10px;
            border: 1px solid #fff;
            cursor: pointer;
        }

        .c-item-show .item-label {
            display: inline-block;
            width: 10px;
            height: 10px;
            border-radius: 50%;
            background-color: #5470c6
        }

        .c-item-show.item-checked {
            border: 1px solid #bcbcbc;
            box-shadow: 0 0 1px #BCBCBC;
        }

        .c-item-show.item-checked .item-label {
            background-color: #ee6666
        }

        .sp2 {
            color: #5fb878;
            margin: 0 4px;
            cursor: pointer;
        }

        .sp3 {
            margin: 0 4px;
        }

        .sp4 {
            color: #0e90d2;
            cursor: pointer;
        }

        .sp5{
            margin: 0 10px;
            font-size: 14px;
            font-weight: 400;
        }

        /*.layui-border-blue {*/
        /*    border-color: #1E9FFF!important;*/
        /*    color: #1E9FFF!important;*/
        /*}*/
    </style>
</head>
<body>

<div class="header">
    <h2 class="court_txt">专项统计</h2>
</div>
<div class="base-info">
    <div class="site-tree">
        <div class="layui-tree ">
            <div class="each-content">
                <%--                <div class=" label-c"><h2>法官等级</h2></div>--%>
                <%--                <div class="content-item-info">--%>
                <%--                    <div class="c-item-show">--%>
                <%--                        <div style='display:inline-block;width: 10px;height: 10px;border-radius: 50%;background-color: #5470c6'></div>--%>
                <%--                        男性<span class="sp2" >7428</span>人，占比<span class="sp3" >7428</span>%--%>
                <%--                    </div>--%>
                <%--                    <div class="c-item-show">--%>
                <%--                        <div style='display:inline-block;width: 10px;height: 10px;border-radius: 50%;background-color: #5470c6'></div>--%>
                <%--                        女性<span class="sp2" >7428</span>人，占比<span class="sp3" >7428</span>%--%>
                <%--                    </div>--%>

                <%--                </div>--%>
                <%--            </div>--%>
                <%--            <div chart="educationBackground" class="label-c"><h2>学历</h2></div>--%>
                <%--            <div chart="age" class="label-c"><h2>年龄</h2></div>--%>
                <%--            <div chart="department" class="label-c"><h2>部门人员数</h2></div>--%>
            </div>
        </div>

        <div>
            <button class="layui-btn layui-btn-primary layui-border-blue" id="clear-item-checked">清除选中</button>
            <button class="layui-btn layui-btn-primary layui-border-blue" id="excel-download">excel下载</button>
        </div>

        <div class="show-for-layui">
            <%--右侧列表--%>
            <div class="main-table-show">
                <table id="show-for-layui" class="" lay-filter="layuiftest"></table>
            </div>

        </div>


    </div>
</div>

<script type="text/javascript">


    $(function () {
        loadCheckItem();
        //取消选中
        $("#clear-item-checked").off("click").on("click", function () {
            $(".item-checked").removeClass("item-checked")
        })
        //excel 下载
        $("#excel-download").off("click").on("click", function () {
            downLoaExcel();
        })

    });

    var fydm = "";

    //加载配置统计项  展示统计信息
    function loadCheckItem() {
        $.ajax({
            url: '<%=basePath%>countStatistics/getStatistics',
            type: "post",
            data: {},
            dataType: "json",
            success: function (resD) {
                if (resD) {

                    var res = resD.data;
                    fydm = resD.fydm;
                    $(".court_txt").html(resD.fymc + "   专项统计");
                    var $container = $(".layui-tree");
                    $container.empty();
                    //总人数
                    var $templatexx = $('  <div class="each-content">\n' +
                        '                <div class=" label-c"><h2>总人数</h2></div>\n' +
                        '                <div class="content-item-info">\n' +
                        '                </div>\n' +
                        '            </div> ');
                    $container.append($templatexx);
                    var $item = $('<div class="c-item-show all-count-item"  onclick="chooseALLItem()" >\n' +
                        '<div class="item-label"  ></div>  ' + resD.fymc + "   " + '<span class="sp5" >' + resD.total + '</span>人 ' +
                        '</div> ');
                    $templatexx.find(".content-item-info").append($item);

                    $.each(res, function (index, val) {
                        var checkData = val.checkData;

                        var title = val.checkName;
                        if(title == '年龄'){
                            title =  val.checkName + "    <span  class=\"sp5\"> 平均年龄 "+ resD.ageAvg +" 岁</span>"
                        }

                        var $template = $('  <div class="each-content">\n' +
                            '                <div class=" label-c"><h2>' + title + '</h2></div>\n' +
                            '                <div class="content-item-info">\n' +
                            '                </div>\n' +
                            '            </div> ');
                        $container.append($template);
                        $.each(checkData, function (nIndex, nVal) {
                            var $item = $('<div class="c-item-show"  onclick="chooseItem(this)" d-configId="' + val.checkId + '"  d-keyValue="' + nVal.key + '" >\n' +
                                '<div class="item-label"  ></div>\n' +
                                nVal.name + '<span class="sp2" >' + nVal.value + '</span>人，占比<span class="sp3" >' + nVal.percent + '</span>%\n' +
                                '</div> ');
                            $template.find(".content-item-info").append($item);
                        })
                    })
                }
            }
        });

    }

    var tableRender;
    //最后一次加载的条件
    var lastCondition ;

    //反查信息
    function queryPerson() {

        var configId = [];
        var keyValue = [];
        $(".item-checked:not('.all-count-item')").each(function () {
            var $this = $(this);
            configId.push($this.attr("d-configid"));
            keyValue.push($this.attr("d-keyValue"));
        });

        var url = "<%=path%>/countStatistics/fcStatistics";
        var condition = {
            fydm: fydm,
            configId: JSON.stringify(configId),
            keyValue: JSON.stringify(keyValue)
        };
        lastCondition = condition;

        if (tableRender) {
            tableRender.reload({
                where: condition
                , page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        } else {
            layui.use('table', function () {
                var table = layui.table;
                tableRender = table.render({
                    elem: '#show-for-layui'
                    // , height: 'full-50'
                    , url: url //数据接口
                    , where: condition
                    , title: '用户表'
                    , page: true //开启分页
                    , defaultToolbar: ['filter']  //默认的工具
                    , cols: [[ //表头

                        {field: 'index', title: '序号', width: 100, type: "numbers"}
                        , {
                            field: 'fullname', title: '姓名', width: 120,
                            templet: function (d) {
                                return '<span class="sp4" onclick="toDetail(\'' + d.id + '\')">' + d.fullname + '</span>'
                            }
                        }
                        , {field: 'court_no_text', title: '法院', width: 200}
                        , {field: 'department_text', title: '部门', width: 200}
                        , {field: 'gender_text', title: '性别', width: 75}
                        , {field: 'birthday', title: '出生年月', width: 120}
                        , {field: 'age', title: '年龄', width: 75}
                        , {field: 'nation_report_text', title: '民族', width: 75}
                        , {field: 'hometown', title: '籍贯', width: 150}
                        , {field: 'personnel_classification_text', title: '人员分类', width: 150}
                        , {
                            field: 'law_position_report_text', title: '法律职务', width: 250, templet: function (d) {
                                return getFieldText(d, 'law_position_date') + "  " + getFieldText(d, "law_position_report_text");
                            }
                        }
                        , {
                            field: 'administration_position_report_text',
                            title: '行政职务',
                            width: 250,
                            templet: function (d) {
                                return getFieldText(d, 'administration_position_date') + "  " + getFieldText(d, "administration_position_report_text");
                            }
                        }
                        , {field: 'education_background_report_text', title: '学历', width: 150}
                        , {field: 'degree_text', title: '学位', width: 150}
                        , {field: 'work_date', title: '工作日期', width: 120}
                        , {field: 'enter_date', title: '进院日期', width: 120}
                        , {field: 'khqk', title: '近五年年度考核情况', width: 420}
                    ]]
                });


                var store = {};

                //监听事件 行点击
                table.on('row(layuiftest)', function (obj) {
                    var trA = obj.tr;
                    console.log("in=====");
                    // if(trA.length > 1){
                    var t = trA[trA.length - 1];
                    var isclick = store[obj.data.id];
                    if (isclick) {
                        store[obj.data.id] = false;
                    } else {
                        store[obj.data.id] = true;
                        $(t).find('.layui-form-radio').click();
                    }

                    // }


                    //obj.del(); //删除当前行
                    //obj.update(fields) //修改当前行数据
                });

            });
        }


    }

    function chooseItem(obj) {
        var $obj = $(obj);
        //移除全部选中
        $(".all-count-item.item-checked").removeClass("item-checked");

        //先看这个选中没得 选中了移除选中
        if ($obj.hasClass("item-checked")) {
            $obj.removeClass("item-checked");
        } else {
            $obj.closest(".content-item-info").find(".c-item-show").removeClass("item-checked");
            $obj.addClass("item-checked");
        }
        //触发计算
        queryPerson();
    }

    //查询全部数据
    function chooseALLItem(){
        $(".item-checked").removeClass("item-checked");
        $(".all-count-item").addClass("item-checked");
        //触发计算
        queryPerson();
    }

    function toDetail(id) {
        open("<%=basePath%>view/detail_new?id=" + id + "#userinfo/base");
    }

    function getFieldText(d, f) {
        return d[f] ? d[f] : "";
    }


    function downLoaExcel() {

        if(!lastCondition){
            return;
        }
        var url = '<%=basePath%>/countStatistics/downloadExcel';
        var turnForm = document.getElementById("downloadForm");
        if (turnForm) {
            turnForm.innerHTML = "";
            turnForm.action = url;
        } else {
            turnForm = document.createElement("form");
            //一定要加入到body中！！
            document.body.appendChild(turnForm);
            turnForm.method = 'post';
            turnForm.action = url;
            // turnForm.target = 'targetIfr';
            turnForm.target = '_blank';
            turnForm.id = "downloadForm";
        }
        turnForm.setAttribute("style", "display:none");
        $.each(lastCondition, function (key, val) {

            var input_ = document.createElement("input");
            input_.name = key;
            input_.value = val;
            turnForm.appendChild(input_);
        });
        turnForm.submit();

    }

</script>
</body>
</html>
