/**
 * Created by liangtao on 2016/3/25.
 */
var clickTime = 0;
var commonJs = {
    rootPath: function () {
        var curPath = window.document.location.href;
        var pathName = window.document.location.pathname;
        var pos = curPath.indexOf(pathName);
        var localhostPaht = curPath.substring(0, pos);
        var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
        return (localhostPaht + projectName);
    },
    //success message
    smsg: function (msg) {
        layer.msg(msg, {icon: 1, offset: (window.innerHeight / 2 + 'px'), time: 1500})
    },
    //error message
    emsg: function (msg) {
        layer.msg(msg, {icon: 2, offset: (window.innerHeight / 2 + 'px'), time: 1500})
    },
    //warn message
    wmsg: function (msg) {
        layer.msg(msg, {icon: 0, offset: (window.innerHeight / 2 + 'px'), time: 1500})
    },
    //ajax 
    ajaxload: function () {
        var index = layer.load(2, {offset: (window.innerHeight / 2 + 'px'), shade: [0.8, '#393D49']})
        return index
    },
    ajaxstop: function (index) {
        layer.close(index)
    },
    close: function (index) {
        layer.close(index)
    },
    confirm: function (msg, yesCall) {
        layer.msg(msg, {
            time: 0 //不自动关闭
            , shade: [0.3, '#000']
            , shadeClose: false
            , offset: (window.innerHeight / 2 + 'px')
            , btn: ['确定', '取消']
            , yes: yesCall
        });
    },
    removeEndLabel: function (str) {
        if (str.length > 0) str = str.substring(0, str.length - 1);
        return str
    },
    dateFormat: function (date, patten) {
        var dat = date;
        if (typeof(date) == "string") {
            dat = new Date(date.replace(/-/g, "/"));
        }
        return dat.pattern(patten);
    },
    getCkboxCourt: function (role, court, click) {
        var url = commonJs.rootPath() + "/resources/common/json/court.json"
        var html = ''
        $.ajaxSettings.async = false;
        $.getJSON(url, function (data) {
            var json = data.cqcourt
            var count = 201
            if (role == 'analyis_qs') {//全市
                for (var key in json) {
                    var child = json[key]
                    for (var ckey in child) {
                        html += '<dd>'
                        var eChild = child[ckey]
                        for (var ekey in eChild) {
                            console.log(eChild)
                            var shortName = eChild[ekey]['shortName']
                            html += '<input name="ck_fydm" key="' + shortName + '" value="' + ekey + '" onclick="' + click + '" type="checkbox" id="checkbox-7-' + count + '" /><label for="checkbox-7-' + count + '"><span>' + shortName + '</span></label>'
                            count++
                        }
                        html += '</dd>'
                    }
                }
            } else if (role == 'analyis_xq') {//辖区
                html += '<dd>'
                var keys = String(court).indexOf("00") > -1 ? 'gy' : 'zy'
                var fy = String(court).substr(0, 2) + '0'
                if (String(court).indexOf("00") > -1 || String(court).indexOf("0") == -1) {
                    var shortName = json[key][fy][court]['shortName']
                    html += '<dd><input name="ck_fydm" key="' + shortName + '" value="' + court + '" onclick="report.setHiddenValue()" type="checkbox" id="checkbox-7-' + count + '" /><label for="checkbox-7-' + count + '"><span>' + shortName + '</span></label></dd>'
                } else {
                    var zJson = json[keys][court]
                    for (var key in zJson) {
                        var shortName = zJson[key]['shortName']
                        html += '<input name="ck_fydm" key="' + shortName + '" value="' + key + '" onclick="report.setHiddenValue()" type="checkbox" id="checkbox-7-' + count + '" /><label for="checkbox-7-' + count + '"><span>' + shortName + '</span></label>'
                        count++
                    }
                }
                html += '<dd>'
            } else {//本院
                var key = String(court).indexOf("00") > -1 ? 'gy' : 'zy'
                var fy = String(court).substr(0, 2) + '0'
                var shortName = json[key][fy][court]['shortName']
                html += '<dd><input name="ck_fydm" key="' + shortName + '" value="' + court + '" onclick="report.setHiddenValue()" type="checkbox" id="checkbox-7-' + count + '" /><label for="checkbox-7-' + count + '"><span>' + shortName + '</span></label></dd>'
            }
        })
        $.ajaxSettings.async = true;
        return html
    },
    gotoAjxx: function (fydm, ajbs, type) {
        var url = path + "/map/gotoAjxx?fydm=" + fydm + "&ajbs=" + ajbs + "&type=" + type;
        window.open(url);
    },
    getImageNum: function (num) {
        var float = num.toString().split(".")[1];
        if (float <= 0)num = num.toString().split(".")[0];
        var nums = num.toString().split("");
        var html = ""
        for (var i in nums) {
            var imgName = nums[i] == "." ? "dian" : nums[i]
            html += "<img src='" + commonJs.rootPath() + "/resources/common/skin_white/images/sz_" + imgName + ".png' />"
        }
        return html
    },
    getBaseOptions: function (type) {
        var jsonName = 'Histogram'
        switch (type) {
            case "Line":
                jsonName = 'LineGraph'
                break;
            case "Map":
                jsonName = 'CQMap'
                break;
            case "Pie":
                jsonName = 'Pie'
                break;
            case "Bar":
                jsonName = 'Bar'
                break;
        }
        var option
        commonJs.ajaxAsync(false)
        var url = commonJs.rootPath() + "/resources/common/json/" + jsonName
        $.getJSON(url, {}, function (json) {
            option = json
        })
        commonJs.ajaxAsync(true)
        return option
    },
    getOptions: function (type, condition) {
        var option = commonJs.getBaseOptions(type)
        //for(var key in condition){
        //    option[key] = condition[key]
        //}
        var resultOption = $.extend(true, option, condition);
        return resultOption
    },
    initCharts: function (loadId) {
        var chartNode = echarts.init(document.getElementById(loadId));
        return chartNode;
    },
    loadCharts: function (type, condition, chartNode) {
        chartNode.showLoading({text: '数据分析中...'});
        var option = commonJs.getOptions(type, condition);
        //alert(JSON.stringify(option));
        chartNode.clear();
        chartNode.setOption(option);
        chartNode.hideLoading();
    },
    chartsFunction: {
        getTitle: function (title, subtext) {
            var jsonTitle = {
                text: title, subtext: subtext, x: "center"
            }
            return jsonTitle
        }
    },
    ajaxAsync: function (bool) {
        $.ajaxSettings.async = bool
    },
    zxglChoiseDialog: function (func) {
        if (clickTime == 0) {
            var url = commonJs.rootPath() + "/zxgl/choise";
            var params = "";
            params = commonJs.condJoin(params, "funName=" + func);
            $('#condition_choise').load(url, params, function () {
                //回调函数
                initAySelect();
            });
            clickTime = clickTime + 1;
        } else {
            $('#condition_choise').show();
        }
    },
    bmLbEditDialog: function (lbbm, id) {
        var url = commonJs.rootPath() + "/dataReport/editBmLb";
        var params = "";
        if (id == undefined) {
            params = commonJs.condJoin(params, "id=");
        } else {
            params = commonJs.condJoin(params, "id=" + id);
        }
        params = commonJs.condJoin(params, "lbbm=" + lbbm);
        $('#editBmLb').load(url, params, function () {
            //回调函数
            //initAySelect();
        });
    },
    zxglShowChoiseDialog: function (func) {
        var url = commonJs.rootPath() + "/zxgl/choise"
        params = commonJs.condJoin(params, "currentStartDate=" + currentStartDate)
        params = commonJs.condJoin(params, "currentEndDate=" + currentEndDate)
        params = commonJs.condJoin(params, "currentFyjbm=" + currentFyjbm)
        params = commonJs.condJoin(params, "currentFymc=" + currentFymc)
        params = commonJs.condJoin(params, "currentAy=" + currentAy)
        params = commonJs.condJoin(params, "currentAymc=" + currentAymc)
        params = commonJs.condJoin(params, "currentStartBd=" + currentStartBd)
        params = commonJs.condJoin(params, "currentEndBd=" + func)
        params = commonJs.condJoin(params, "funName=" + func)
        $('#zxgl_choise').load(url, params, function () {
            init()
            componentRenders()
        })
    },

    condJoin: function (params, join) {
        if (params != "")params += "&";
        params += join
        return params
    }
}

/**
 * 对Date的扩展，将 Date 转化为指定格式的String
 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
 * eg:
 * (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
 * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04
 * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04
 * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04
 * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
 */
Date.prototype.pattern = function (fmt) {
    if (fmt == null || typeof(fmt) == 'undefined' || fmt == '') {
        fmt = 'yyyy年MM月dd日';
    }
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    var week = {
        "0": "/u65e5",
        "1": "/u4e00",
        "2": "/u4e8c",
        "3": "/u4e09",
        "4": "/u56db",
        "5": "/u4e94",
        "6": "/u516d"
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    if (/(E+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f" : "/u5468") : "") + week[this.getDay() + ""]);
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
}

/**
 * 获取指标数据
 * @param func 回调方法
 * @param zdyParam 自定义参数对象
 * @param kpiKey 指标关键字
 * @param startDate 开始日期
 * @param endDate 结束日期
 * @param fyjbm 法院级别码
 * @param ay 案由编码
 * @param startBd 开始标的
 * @param endBd 结束标的
 */
function getKpiData(func, kpiKey, zdyParam, startDate, endDate, fyjbm, ay, startBd, endBd) {
    var url = appBasePath + "/zxgl/getKpiData";
    $.post(url, {
        kpiKey: kpiKey,
        zdyParam: zdyParam,
        startDate: startDate,
        endDate: endDate,
        fyjbm: fyjbm,
        ay: ay,
        startBd: startBd,
        endBd: endBd
    }, function (retObj) {
        func(retObj);
    }, "json");
}

/**
 * 跳转数据反查列表
 * @param title 标题
 * @param filterQuery 过滤语句
 */
function toDataList(title, filterQuery, schema, type) {
    var url = appBasePath + "/map/list?period=" + currentPeriod + "&&title=" + encodeURI(encodeURI(title)) + "&schema=" + schema + "&filterQuery=" + filterQuery + "&type=" + type;
    window.open(url);
}

/**
 * 跳转数据反查表格
 * @param title 标题
 * @param filterQuery 过滤语句
 */
function toDataTable(title, filterQuery, schema, type) {
    var url = appBasePath + "/map/table?from=solr&period=" + currentPeriod + "&&title=" + encodeURI(encodeURI(title)) + "&schema=" + schema + "&filterQuery=" + filterQuery + "&type=" + type;
    window.open(url);
}


/**
 * 将弹出层中选择的案由回填到下拉菜单
 * */

function ht(name) {
    var obj = $("#ay").find('option');
    obj.each(function () {
        if ($(this).text() == name) {
            $(this).attr('selected', true);
            $("#ay").val($(this).val()).change();
            $("#ay").select2().select2('val', $(this).val());
            //$(".select2-hidden-accessible").text($(this).text());
            $("#aydm").val($(this).val());
            $("#aydmmc").val($(this).text());
            $("#choseaylx").hide();
            return false;
        }
    });

}


function closeck() {

    $("#choseaylx").hide();

}

//返回首页
function backIndex() {
    location.href = appBasePath + "/baseindex/index";
}

//返回封面
function backWelcome() {
    location.href = appBasePath + "/baseindex/welcome";
}

function showdata(param, datas, name, dw, fcType) {
    var values = datas.rankData;
    var names = datas.rankName;
    var html = "";
    html += "<dt>" + name + "-" + param.litellName + "<dt>";
    html += "<dd>" + formatTime(currentStartDate) + "至" + formatTime(currentEndDate) + "," + currentFymc + "," + param.litellName + ":<br>";
    for (var i = 0; i < names.length; i++) {
        var name = names[i];
        var value = values[i];
        if (i == names.length - 1) {
            html += "&nbsp &nbsp <span>" + name + "</span>:<span style=\"cursor:pointer\" onclick=\"toDataList('" + name + "','" + datas['filterQuery_' + name] + "','" + datas['schema'] + "','" + fcType + "')\">" + value + dw + "</span>。</dd>";
        } else {
            html += "&nbsp &nbsp <span>" + name + "</span>:<span style=\"cursor:pointer\" onclick=\"toDataList('" + name + "','" + datas['filterQuery_' + name] + "','" + datas['schema'] + "','" + fcType + "')\">" + value + dw + "</span>,<br>";

        }

    }
    $("#showDiv").html(html);

}

function formatTime(time) {
    var times = time.split("-");
    var year = times[0];
    var month = times[1];
    var day = times[2];
    var newTime = year + "年" + month + "月" + day + "日";
    return newTime;
}


var options = {
    zzOption: function (xData, data2, title) {
        var barOption = {
            grid: {
                x: 80,
                y: 50,
                x2: 60,
                y2: 60
            },
            color: ['#F15E5E', '#F15E5E'],
            tooltip: {
                trigger: 'axis',
                textStyle: {
                    fontSize: '18'

                },
                formatter: function (params, ticket, callback) {
                    //var res = params.name + ":" + params.seriesName +params.value+"%";
                    var res;
                    if (params.length > 1) {
                        res = params[0].name + "<br/>" + params[0]['seriesName'] + ':' + params[0]['value'] + '<br/>'
                            + params[1]['seriesName'] + ':' + params[1]['value'] + ''
                    } else {
                        res = params[0].name + "<br/>" + params[0]['seriesName'] + ':' + params[0]['value'];
                    }
                    return res;
                }
            },
            toolbox: {
                show: true,
                backgroundColor: 'rgba(0,0,0,0)',
                feature: {
                    myline: {
                        show: true,
                        title: '切换为折线图',
                        // icon: 'image://../../images/line.png',
                        icon: 'image://../../images/line.png',
                        onclick: function (ev1, ev2) {
                            getBar(eval($(ev2.getDom()).attr("id")), data2, 'line', null, title);
                        }
                    },
                    mybar: {
                        show: true,
                        title: '切换为柱图',
                        icon: 'image://../../images/bar.png',
                        onclick: function (ev1, ev2) {
                            getBar(eval($(ev2.getDom()).attr("id")), data2, 'bar', null, title);
                        }
                    },
                    //自定义工具只能以my开头
                    myPie: {
                        show: true,
                        title: '切换为饼图',
                        icon: 'image://../../images/pie.png',
                        onclick: function (ev1, ev2) {
                            getBar(eval($(ev2.getDom()).attr("id")), data2, 'pie', null, title);
                        }
                    }
                },
                itemSize: 25
            },
            calculable: true,
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: true,
                    name: "",
                    nameTextStyle: "",
                    axisLabel: {
                        interval: 0,
                        textStyle: {
                            color: "#000",
                            fontSize: '18'
                        },
                        rotate: -20
                    },
                    data: xData
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: "",
                    nameTextStyle: "",
                    axisLabel: {
                        textStyle: {
                            formatter: '{value} ',
                            color: "#000",
                            fontSize: '18'
                        },
                        formatter: function (value) {
                            return value;
                        }
                    }
                }
            ],

            series: []
        }
        return barOption;
    },
    zzOption2: function (xData) {
        var barOption = {
            grid: {
                x: 110,
                y: 50,
                x2: 30,
                y2: 60
            },
            color: ['#F15E5E', '#F15E5E'],
            tooltip: {
                trigger: 'axis',
                textStyle: {
                    fontSize: '18'

                },
                formatter: function (params, ticket, callback) {
                    //var res = params.name + ":" + params.seriesName +params.value+"%";
                    var res;
                    if (params.length > 1) {
                        res = params[0].name + "<br/>" + params[0]['seriesName'] + ':' + params[0]['value'] + '<br/>'
                            + params[1]['seriesName'] + ':' + params[1]['value'] + ''
                    } else {
                        res = params[0].name + "<br/>" + params[0]['seriesName'] + ':' + params[0]['value'];
                    }
                    return res;
                }
            },
            toolbox: {
                show: true,
                backgroundColor: 'rgba(0,0,0,0)',
                feature: {
                    magicType: {show: true, type: ['line', 'bar']},
                }
            },
            calculable: true,
            yAxis: [
                {
                    type: 'category',
                    boundaryGap: true,
                    axisLabel: {
                        interval: 0,
                        textStyle: {
                            color: "#000",
                            fontSize: '18'
                        },
                        rotate: 20
                    },
                    data: xData
                }
            ],
            xAxis: [
                {
                    type: 'value',
                    axisLabel: {
                        textStyle: {
                            formatter: '{value} ',
                            color: "#000",
                            fontSize: '18'
                        },
                        formatter: function (value) {
                            return value;
                        }
                    }
                }
            ],

            series: []
        }
        return barOption;
    }, zzNoyOption: function (xData) {
        var barOption = {
            grid: {
                x: 60,
                y: 50,
                x2: 30,
                y2: 60
            },
            color: ['#F15E5E', '#F15E5E'],
            tooltip: {
                trigger: 'axis',
                textStyle: {
                    fontSize: '18'

                },
                formatter: function (params, ticket, callback) {
                    //var res = params.name + ":" + params.seriesName +params.value+"%";
                    var res;
                    if (params.length > 1) {
                        res = params[0].name + "<br/>" + params[0]['seriesName'] + ':' + params[0]['value'] + '<br/>'
                            + params[1]['seriesName'] + ':' + params[1]['value'] + ''
                    } else {
                        res = params[0].name + "<br/>" + params[0]['seriesName'] + ':' + params[0]['value'];
                    }
                    return res;
                }
            },
            toolbox: {
                show: true,
                backgroundColor: 'rgba(0,0,0,0)',
                feature: {
                    magicType: {show: true, type: ['line', 'bar']},
                }
            },
            calculable: true,
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: true,
                    axisLabel: {
                        interval: 0,
                        textStyle: {
                            color: "#000",
                            fontSize: '18'
                        }
                    },
                    data: xData
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    show: false
                }
            ],

            series: []
        }
        return barOption;
    },
    btOption: function (pieName, title, datas, data2) {
        var pieOptions = {
            legend: {
                orient: 'vertical',
                x: 'left',
                data: pieName,
                textStyle: {
                    fontSize: '18',
                    color: '#aaa'
                }
            },
            toolbox: {
                show: true,
                feature: {
                    myline: {
                        show: true,
                        title: '切换为折线图',
                        // icon: 'image://../../images/line.png',
                        icon: 'image://../../images/line.png',
                        onclick: function (ev1, ev2) {
                            getBar(eval($(ev2.getDom()).attr("id")), data2, 'line', null, title);
                        }
                    },
                    mybar: {
                        show: true,
                        title: '切换为柱图',
                        icon: 'image://../../images/bar.png',
                        onclick: function (ev1, ev2) {
                            getBar(eval($(ev2.getDom()).attr("id")), data2, 'bar', null, title);
                        }
                    },
                    //自定义工具只能以my开头
                    myPie: {
                        show: true,
                        title: '切换为饼图',
                        icon: 'image://../../images/pie.png',
                        onclick: function (ev1, ev2) {
                            getBar(eval($(ev2.getDom()).attr("id")), data2, 'pie', null, title);
                        }
                    }
                },
                itemSize: 25
            },
            tooltip: {
                trigger: 'item',
                textStyle: {
                    fontSize: '18'

                },
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            series: [{
                name: title,
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: datas,
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }]
        }
        return pieOptions;
    },
    zxOption: function (xData, data2, title) {
        var lineOption = {
            grid: {
                x: 60,
                y: 50,
                x2: 30,
                y2: 60
            },
            color: ['#F15E5E', '#3baeda', '#2f4554'],
            tooltip: {
                trigger: 'axis',
                formatter: function (params, ticket, callback) {
                    //var res = params.name + ":" + params.seriesName +params.value+"%";
                    var res;
                    if (params.length > 1) {
                        res = params[0].name + "<br/>" + params[0]['seriesName'] + ':' + params[0]['value'] + '<br/>'
                            + params[1]['seriesName'] + ':' + params[1]['value'] + '<br/>'
                            + params[2]['seriesName'] + ':' + params[2]['value'] + ''
                    } else {
                        res = params[0].name + "<br/>" + params[0]['seriesName'] + ':' + params[0]['value'];
                    }
                    return res;
                },
                textStyle: {
                    fontSize: '18'
                },
            },
            toolbox: {
                show: true,
                backgroundColor: 'rgba(0,0,0,0)',
                feature: {
                    myline: {
                        show: true,
                        title: '切换为折线图',
                        // icon: 'image://../../images/line.png',
                        icon: 'image://../../images/line.png',
                        onclick: function (ev1, ev2) {
                            getBar(eval($(ev2.getDom()).attr("id")), data2, 'line', null, title);
                        }
                    },
                    mybar: {
                        show: true,
                        title: '切换为柱图',
                        icon: 'image://../../images/bar.png',
                        onclick: function (ev1, ev2) {
                            getBar(eval($(ev2.getDom()).attr("id")), data2, 'bar', null, title);
                        }
                    },
                    //自定义工具只能以my开头
                    myPie: {
                        show: true,
                        title: '切换为饼图',
                        icon: 'image://../../images/pie.png',
                        onclick: function (ev1, ev2) {
                            getBar(eval($(ev2.getDom()).attr("id")), data2, 'pie', null, title);
                        }
                    }
                },
                itemSize: 25
            },
            calculable: true,
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: true,
                    axisLabel: {
                        interval: 0,
                        rotate: -20,
                        textStyle: {
                            color: "#000",
                            fontSize: '18'
                        },
                    },
                    data: xData
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    axisLabel: {
                        textStyle: {
                            formatter: '{value} ',
                            color: "#000",
                            fontSize: '18'
                        },
                        formatter: function (value) {
                            return value;
                        }
                    }
                }
            ],

            series: []
        }
        return lineOption;
    },
}

function getBar(chart, data, type, v, name, legend, xName, yName) {
    var rankData = data.value;
    var ayzzlName = data.name;
    var key = data.key;
    var keyValue = data.keyValue;

    var Option;
    if (type == "bar") {
        var yData = [];
        for (var a in rankData) {
            var obj1 = {
                value: rankData[a]
            };

            if(key &&  keyValue && keyValue.length == rankData.length){
                obj1['key'] = key;
                obj1['keyValue'] = keyValue[a];
            }

            yData.push(obj1);
        }

        var Series = {
            name: name, type: type, data: yData, textStyle: {
                color: '#000',
                fontSize: '18'
            }, barWidth: 40,
            label: {
                normal: {
                    show: true,
                    position: 'top'
                }
            }

        };

        if (v) {
            Option = options.zzOption2(ayzzlName, name);
        } else {
            Option = options.zzOption(ayzzlName, data, name);
        }
        Option.legend = {
            data: legend, color: '#F15E5E', x: 'left', textStyle: {
                color: '#000',
                fontSize: '18'
            }
        };
        Option.series.push(Series);
    }else if (type == "barWithScroll") {
        //x轴带滚动条
        var yData = [];
        for (var a in rankData) {
            var obj1 = {
                value: rankData[a]
            };

            if(key &&  keyValue && keyValue.length == rankData.length){
                obj1['key'] = key;
                obj1['keyValue'] = keyValue[a];
            }

            yData.push(obj1);
        }

        var Series = {
            name: name, type: 'bar', data: yData, textStyle: {
                color: '#000',
                fontSize: '18'
            }, barWidth: 40,
            label: {
                normal: {
                    show: true,
                    position: 'top'
                }
            }

        };

        if (v) {
            Option = options.zzOption2(ayzzlName, name);
        } else {
            Option = options.zzOption(ayzzlName, data, name);
        }
        //加入滚动条
        var startV = data.start ? data.start : 0;
        var endV = data.end ? data.end : 100;
        Option.dataZoom = [{
            type: 'slider',
            show: true,
            xAxisIndex: [0],
            left: '9%',
            bottom: -5,
            start: startV,
            end: endV //初始化滚动条
        }];
        Option.legend = {
            data: legend, color: '#F15E5E', x: 'left', textStyle: {
                color: '#000',
                fontSize: '18'
            }
        };
        Option.series.push(Series);
    } else if (type == "barWithNoY") {
        var yData = [];
        for (var a in rankData) {
            yData.push({
                value: rankData[a]
            })
        }
        var Series = {
            name: name, type: 'bar', data: yData, textStyle: {
                color: '#000',
                fontSize: '18'
            }, barWidth: 40,
            itemStyle: {
                normal: {
                    color: function (params) {
                        var colorList = [
                            '#f2a030', '#74be1d'
                        ];
                        return colorList[params.dataIndex]
                    },
                    label: {
                        show: true,
                        position: 'top',
                        formatter: '{b}\n{c}',
                        textStyle: {
                            fontSize: 18
                        }
                    }
                }
            }
        };

        Option = options.zzNoyOption(ayzzlName, name);

        Option.legend = {
            data: legend, color: '#F15E5E', x: 'left', textStyle: {
                color: '#000',
                fontSize: '18'
            }
        };
        Option.series.push(Series);
    } else if (type == 'pie') {
        var datas = []
        for (var i = 0; i < rankData.length; i++) {
            var sj = {
                value: rankData[i],
                name: ayzzlName[i]
            };

            if(key &&  keyValue && keyValue.length == rankData.length){
                sj['key'] = key;
                sj['keyValue'] = keyValue[i];
            }

            sj.itemStyle = {
                normal: {
                    label: {show: true, textStyle: {color: '#5a5a5a', fontSize: 18}},
                    labelLine: {show: true, lineStyle: {color: '#5a5a5a'}}
                }
            };
            sj.label = {
                normal: {
                    formatter: '{b} ({c}人)'
                }
            }
            datas.push(sj)
        }

        Option = options.btOption(ayzzlName, name, datas.sort(function (a, b) {
            return b.value - a.value
        }), data);
    } else if (type == 'line') {
        var yData = [];
        for (var a in rankData) {

            var obj2 = {
                value: rankData[a]
            };

            if(key &&  keyValue && keyValue.length == rankData.length){
                obj2['key'] = key;
                obj2['keyValue'] = keyValue[a];
            }

            yData.push(obj2);

        }
        var Series = {
            name: name, type: type, data: yData, textStyle: {
                color: '#000',
                fontSize: '18'
            }, barWidth: 40
        };
        Option = options.zxOption(ayzzlName, data, name);
        Option.xAxis[0].axisLabel.rotate = -20;

        Option.legend = {
            data: legend, color: '#F15E5E', x: 'left', textStyle: {
                color: '#000',
                fontSize: '18'
            }
        };
        Option.series.push(Series);

    }
    Option.title = {
        text: name, padding: 12, x: 'center', y: '-10', textStyle: {
            color: '#000',
            fontSize: '30'
        }, subtextStyle: {
            color: '#000',
            fontSize: '18'
        }
    };
    /*
     统一x,y轴名字处理
     */
    if (xName != "" && xName != null && typeof(xName) != "undefined") {
        Option.xAxis[0].name = xName;
        Option.xAxis[0].nameTextStyle = {color: '#5a5a5a', fontSize: 20}
    }
    if (yName != "" && yName != null && typeof(yName) != "undefined") {
        Option.yAxis[0].name = yName;
        Option.yAxis[0].nameTextStyle = {color: '#5a5a5a', fontSize: 20}
    }
    ///**
    // * 标题统一处理
    // *
    // */
    Option.title = {
        text: name, subtext: '', padding: 10, x: 'center', y: '-10', textStyle: {
            color: '#5a5a5a',
            fontSize: '25'
        }, subtextStyle: {
            color: '#aaa',
            fontSize: '18'
        }
    };


    //设置颜色
    if (typeof(colorArray) != 'undefined') {
        Option.color = colorArray;
    }

    chart.clear();
    chart.setOption(Option);
    chart.hideLoading();

}
function getHxt(chart, data) {

    var labelTop = {
        normal: {
            label: {
                show: true,
                position: 'center',
                formatter: '{b}',
                textStyle: {
                    baseline: 'bottom'
                }
            },
            labelLine: {
                show: false
            }
        }
    };
    var labelBottom = {
        normal: {
            color: '#ccc',
            label: {
                show: true,
                position: 'center'
            },
            labelLine: {
                show: false
            }
        },
        emphasis: {
            color: 'rgba(0,0,0,0)'
        }
    };
    var datas = [
        {name: 'other', value: (((data.all - data.value) / data.all) * 100).toFixed(2), itemStyle: labelBottom},
        {name: data.name, value: ((data.value / data.all) * 100).toFixed(2), itemStyle: labelTop}
    ];

    var Option = options.hxtOption2(datas);
    Option.title = {
        text: name, padding: 12, x: 'center', y: '-10', textStyle: {
            color: '#000',
            fontSize: '30'
        }, subtextStyle: {
            color: '#000',
            fontSize: '18'
        }
    };

    chart.clear();
    chart.setOption(Option);
    chart.hideLoading();

}












