<%--
  Created by IntelliJ IDEA.
  User: huise
  Date: 2018/6/4
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>人事数据下行json生成页</title>
    <jsp:include page="basic_import.jsp"></jsp:include>
    <style href="<%=basePath%>/js/select2/css/select2.css"></style>
    <script src="<%=basePath%>/js/select2/js/select2.js"></script>
    <style>
        .key {
            color: red;
        }

        .string {
            color: green;
        }

        .number {
            color: orange;
        }

        .bui-select-list {
            overflow: auto;
            overflow-x: hidden;
            max-height: 220px;
        }

        input {
            height: 30px !important;
            line-height: 30px !important;
        }

        input.calendar {
            width: 120px;
        }
    </style>
</head>
<body>

<div style="width: 70%;display: inline-block;margin-top: 20px;">
    <form class="form-horizontal" onsubmit="return false;">
        <div class="row">
            <div class="control-group span12">
                <label class="control-label">信息集：</label>
                <div class="controls">
                    <select id="xxj" class="field span8">
                        <option></option>
                    </select>
                </div>
            </div>
            <button type="button" class="button button-small" id="search"><i class="icon-search"></i>表格搜索</button>
            <button type="button" class="button button-small" id="add"><i class="icon-plus"></i>增加该数据集所有</button>
            <button type="button" class="button button-small" id="clear"><i class="icon-remove"></i>清除该数据集所有</button>
        </div>
        <hr/>
        <div class="row">
            <div class="control-group span11">
                <label class="control-label">法院：</label>
                <div class="controls">
                    <div id="s1">
                        <input type="hidden" id="hide" value="b" name="hide">
                    </div>
                </div>
                <button style="margin-left: 10px;" type="button" class="button button-small" id="addfy"><i class="icon-plus"></i>增加所有法院</button>
            </div>
            <div class="control-group span8">
                <label class="control-label">起始日期：</label>
                <div class="controls">
                    <input type="text" class="field calendar">
                </div>
            </div>
            <div class="control-group span8">
                <label class="control-label">是否含下级法院：</label>
                <div class="controls">
                    <select id="xjfy" class="field">
                        <option value="1">否</option>
                        <option value="2">是</option>
                    </select>
                </div>
            </div>
        </div>
    </form>
    <div style="margin-left: 10px;">
        <div id="grid"></div>
    </div>
</div>
<pre style="width: 29%;display: inline-block;height: 500px;border: 1px solid #7abef9;" id="config"></pre>

<div style="font-size: 20px;margin: 20px;">
    <a href="<%=basePath%>downward/trans" target="_blank">开始下行导入(请先复制json到项目指定位置)</a>
</div>
</body>
</html>
<script>
    var allData = [];
    var datasize = {};
    var allConfig = {};
    var allCourts = [];
    $(function () {
        $.post('<%=basePath%>downward/all', function (data) {
            allData = data.data;
            allConfig = data.config;
            for (var i = 0; i < data.courts.length; i++) {
                allCourts.push({
                    text: data.courts[i],
                    value: data.courts[i]
                });
            }
            for (var i = 0; i < allData.length; i++) {
                var table = tablelist[allData[i]['sourceTable']]['table'];
                var field = allData[i]['targetField'];
                allData[i]['targetTable'] = table;
                for (var j = 0; j < allConfig['configList'].length; j++) {
                    var obj = allConfig['configList'][j];
                    if (obj['table'] == table) {
                        if (obj['field'][0] == 'ALL' || obj['field'].indexOf(field) != -1) {
                            allData[i]['selected'] = true;
                        }
                        break;
                    }
                }

                if (datasize[table] == undefined) datasize[table] = [];
                datasize[table].push(field);
            }

            renderBui();
            updateView();
        });
        for (var table in tablelist) {
            $('#xxj').append('<option val="' + table + '" field-table="' + tablelist[table]['table'] + '">' + tablelist[table]['text'] + ' -- ' + table + ' -- ' + tablelist[table]['table'] + '</option>');
        }
        $('#xxj').select2();
    });

    function renderBui() {
        //初始化BUI相关组件
        BUI.use(['common/search', 'bui/grid', 'bui/data', 'bui/select'], function (Search, Grid, Data, Select) {
            datepickerFix($, BUI.Calendar);
            var Store = Data.Store;
            var store = new Store({
                data: allData,	// 前端分页 数据一定是静态数据
                autoLoad: true,
                remoteSort: true,
                pageSize: 10		// 需要在store中 配置pageSize
            });
            var grid = new Grid.Grid({
                render: '#grid',
                columns: columns,
                itemStatusFields: { //设置数据跟状态的对应关系
                    selected: 'selected'
                },
                // 底部工具栏
                bbar: {
                    pagingBar: true
                },
                store: store,
                plugins: [BUI.Grid.Plugins.CheckSelection] // 插件形式引入多选表格
            });

            grid.render();

            grid.on('selectedchange', function (e) {
                var item = e.item;
                var field = item.targetField;
                var table = item.targetTable;
                var flag = false;
                for (var j = 0; j < allConfig['configList'].length; j++) {
                    var obj = allConfig['configList'][j];
                    if (obj['table'] == table) {
                        if (item.selected) {
                            if (obj['field'].indexOf(field) == -1) {
                                if (obj['field'].length == datasize[table].length - 1) {
                                    obj['field'] = ['ALL'];
                                } else {
                                    obj['field'].push(field);
                                }
                            }
                        } else {
                            if (obj['field'][0] == 'ALL') {
                                obj['field'] = $.extend([], datasize[table]);
                                obj['field'].splice(obj['field'].indexOf(field), 1);
                            } else if (obj['field'].indexOf(field) != -1) {
                                if (obj['field'].length == 1) {
                                    allConfig['configList'].splice(allConfig['configList'].indexOf(obj), 1);
                                } else {
                                    obj['field'].splice(obj['field'].indexOf(field), 1);
                                }
                            }
                        }
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    allConfig['configList'].push({
                        "field": [field],
                        "table": table
                    });
                }
                updateView();
            });

            $('#search').on('click', function () {
                var table = $('#xxj').find('option:selected').attr('val');
                var data = [];
                if (table == undefined) {
                    data = allData;
                } else {
                    for (var i = 0; i < allData.length; i++) {
                        if (table == allData[i]['sourceTable']) {
                            data.push(allData[i]);
                        }
                    }
                }
                store.setResult(data);
            });
            $('#clear').on('click', function () {
                var table = $('#xxj').find('option:selected').attr('field-table');
                if (table == undefined) {
                    var r=confirm("确定清空所有数据集？！");
                    if (r) {
                        allConfig.configList = [];
                        for (var i = 0; i < allData.length; i++) {
                            allData[i]['selected'] = false;
                        }
                        updateView();
                        store.setResult(allData);
                    }
                } else {
                    for (var i = 0; i < allConfig.configList.length; i++) {
                        if (table == allConfig.configList[i]['table']) {
                            allConfig.configList.splice(i,1);
                            break;
                        }
                    }
                    for (var i = 0; i < allData.length; i++) {
                        var table1 = tablelist[allData[i]['sourceTable']]['table'];
                        if (table == table1) {
                            allData[i]['selected'] = false;
                        }
                    }
                    updateView();
                    store.setResult(allData);
                }
            });
            $('#add').on('click', function () {
                var table = $('#xxj').find('option:selected').attr('field-table');
                if (table == undefined) {
                    alert('...');
                } else {
                    var currentXXJIndex = -1;
                    for (var i = 0; i < allConfig.configList.length; i++) {
                        if (table == allConfig.configList[i]['table']) {
                            allConfig.configList[i]['field'] = ['ALL'];
                            currentXXJIndex = i;
                            break;
                        }
                    }
                    for (var i = 0; i < allData.length; i++) {
                        var table1 = tablelist[allData[i]['sourceTable']]['table'];
                        if (table == table1) {
                            allData[i]['selected'] = true;
                            if (currentXXJIndex === -1) {
                                allConfig.configList.push({
                                    'field': ['ALL'],
                                    'table': table
                                });
                                currentXXJIndex = 0;
                            }
                        }
                    }
                    updateView();
                    store.setResult(allData);
                }
            });
            var select = new Select.Select({
                render: '#s1',
                valueField: '#hide',
                multipleSelect: true,
                items: allCourts
            });
            select.render();
            select.on('change', function (ev) {
                allConfig['fymc'] = ev.text.split(',');
                updateView();
            });

            $('#addfy').on('click', function (e) {
                allConfig['fymc'] = [];
                var s = '';
                for (var i in allCourts) {
                    s += allCourts[i]['value'] + ",";
                }
                select.setSelectedValue(s.substring(0, s.length - 1));
            })
        });
        $('.calendar').on('change', function (e) {
            var c = $('.calendar').val();
            if (c == '') {
                allConfig['time'] = 'ALL';
            } else {
                allConfig['time'] = c;
            }
            updateView();
        });
        $('#xjfy').on('change', function (e) {
            var c = $(this).find('option:selected').val();
            allConfig['type'] = Number(c);
            updateView();
        });
    }

    function highLight(json) {
        if (typeof json == "object") json = JSON.stringify(json, undefined, 4);
        json = json.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
        return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function (match) {
            var cls = 'number';
            if (/^"/.test(match)) {
                if (/:$/.test(match)) {
                    cls = 'key';
                } else {
                    cls = 'string';
                }
            } else if (/true|false/.test(match)) {
                cls = 'boolean';
            } else if (/null/.test(match)) {
                cls = 'null';
            }
            return '<span class="' + cls + '">' + match + '</span>';
        });
    }

    function updateView() {
        $('#config').html(highLight(allConfig));
    }

    var columns = [{
        title: '信息集',
        dataIndex: 'sourceTable',
        width: 150, renderer: function (val) {
            return tablelist[val]['text'];
        }
    }, {
        title: '地方表',
        dataIndex: 'sourceTable',
        width: 130, renderer: function (val) {
            return tablelist[val]['table'];
        }
    }, {
        title: '本地表',
        dataIndex: 'sourceTable',
        width: 200
    }, {
        title: '字段描述',
        dataIndex: 'fieldDescribe',
        width: 180
    }, {
        title: '地方字段',
        dataIndex: 'targetField',
        width: 130
    }, {
        title: '地方字段类型',
        dataIndex: 'targetFieldType',
        width: 100
    }, {
        title: '本地字段',
        dataIndex: 'sourceField',
        width: 150
    }, {
        title: '本地字段类型',
        dataIndex: 'fieldType',
        width: 140
    }];
    var tablelist = {
        'ums_user_info': {
            'text': '基本信息',
            'table': 'A01'
        },
        'ums_political_info': {
            'text': '政治面貌',
            'table': 'T_RYSX_ZZMM'
        },
        'ums_administrative_job': {
            'text': '行政职务',
            'table': 'A02'
        },
        'ums_legal_job': {
            'text': '法律职务',
            'table': 'T_RYSX_FLZW'
        },
        'ums_rank_info': {
            'text': '职级信息',
            'table': 'A05'
        },
        'ums_parttime_position': {
            'text': '兼职职务',
            'table': 'T_RYSX_JZZW'
        },
        'ums_level_info_fg': {
            'text': '等级信息',
            'table': 'T_RYSX_DJXX'
        },
        'ums_level_info_fj': {
            'text': '法警职级等级信息',
            'table': 'T_RYSX_FJDJ'
        },
        'ums_level_info_fgzl': {
            'text': '法官助理等级信息',
            'table': 'T_RYSX_FGZLDJ'
        },
        'ums_level_info_sjy': {
            'text': '书记员等级信息',
            'table': 'T_RYSX_SJYDJ'
        },
        'ums_education_info': {
            'text': '学历信息',
            'table': 'A08'
        },
        'ums_degree_info': {
            'text': '学位信息',
            'table': 'A09'
        },
        'ums_study_info': {
            'text': '在读信息',
            'table': 'T_RYSX_ZDXX'
        },
        'ums_training_info': {
            'text': '培训信息',
            'table': 'A11'
        },
        'ums_resume_info': {
            'text': '简历信息',
            'table': 'A17'
        },
        'ums_family_info': {
            'text': '家庭成员信息',
            'table': 'A36'
        },
        'ums_assess_info': {
            'text': '年度考核信息',
            'table': 'A15'
        },
        'ums_reward_info': {
            'text': '奖励信息',
            'table': 'A14Z2'
        },
        'ums_punish_info': {
            'text': '惩罚信息',
            'table': 'A14Z3'
        },
        'ums_abroad_info': {
            'text': '出国信息',
            'table': 'A12'
        },
        'ums_foreign_lang': {
            'text': '外语信息',
            'table': 'A10'
        },
        'ums_exchange_info': {
            'text': '交流信息',
            'table': 'A49'
        },
        'ums_judicial_exam': {
            'text': '司法考试信息',
            'table': 'T_RYSX_SFKS'
        },
        'ums_pro_technical_position': {
            'text': '专业技术职务信息',
            'table': 'A06'
        },
        'ums_casualty_info': {
            'text': '伤亡信息',
            'table': 'T_RYSX_SWXX'
        },
        'ums_remark': {
            'text': '备注信息',
            'table': 'T_RYSX_BZXX'
        },
        'ums_audio_video': {
            'text': '声音与影响',
            'table': 'A57'
        },
        'ums_wage_info': {
            'text': '工资信息',
            'table': 'A32'
        },
        'ums_reserve_cadre': {
            'text': '后备干部',
            'table': 'A23'
        },
        'ums_contact': {
            'text': '通讯信息',
            'table': 'A37'
        },
        'ums_leadership': {
            'text': '领导班子',
            'table': 'T_RYSX_LDBZ'
        },
        'ums_civil_servant_level': {
            'text': '公务员',
            'table': 'T_RYSX_GWY'
        },
        'ums_change_info': {
            'text': '变动信息',
            'table': 'T_RYSX_RYBD'
        }
    }

</script>
