var vm = {};
var courtInfo = {};
$(function () {

    loadCourtTree();

    //给保存绑定事件
    $(".layui-btn").off("click").on("click", function () {
        var $this = $(this);
        var type = $this.attr("btn-c");
        switch (type) {
            case "1":

                var rek = {
                    'zzzx': false,
                    'yefg': false
                };
                var rev = {
                    'zzzx': false,
                    'yefg': false
                };
                var c = (function (rek, rev) {

                    return function (re, scope) {

                        rek[scope] = true;
                        if (re) {
                            rev[scope] = true;
                        }
                        var flag = false;
                        $.each(rek, function (index, value) {
                            flag = value;
                            if (!flag) {
                                return false;
                            }
                        });
                        if (flag) {
                            flag = false;
                            $.each(rev, function (index, value) {
                                flag = value;
                                if (!flag) {
                                    return false;
                                }
                            });
                            if (flag) {
                                alert("更新成功");
                            } else {
                                alert("操作失败");
                            }

                            readConfig('zzzx', courtInfo['courtNo']);
                            readConfig('yefg', courtInfo['courtNo']);
                        }

                    }
                })(rek, rev);
                saveCourtConfig('zzzx', c);
                saveCourtConfig('yefg', c);
                break;
            case "2":
                saveCourtConfig('level');
                break;
        }

    });

});

//保存信息
function saveCourtConfig(scope, callback) {

    var formC = "";
    switch (scope) {
        case "zzzx":
            formC = "zzzx_config";
            break;
        case "yefg":
            formC = "yefg_config";
            break;
        case "level":
            formC = "level_config";
            break;
    }

    var condition = [];
    $("#" + formC + " li").each(function () {
        var $t = $(this);
        var c = {};
        $t.find(":input").each(function () {
            var $i = $(this);
            c[$i.attr("name")] = $i.val();
        });
        condition.push(c);
    });
    var json = JSON.stringify(condition);

    $.ajax({
        type: "post",
        url: urlPath + "/courtVerifyAction/saveConfig",
        data: {
            jsonStr: json,
            scope: scope,
            courtNo: courtInfo['courtNo']
        },
        dataType: "json",
        success: function (re) {
            if (typeof callback === 'function') {
                callback(re, scope);
                return;
            }

            if (re) {
                alert("更新成功");
            } else {
                alert("操作失败");
            }
            readConfig(scope, courtInfo['courtNo']);
        }
    })


}

//生成法院树结构
function loadCourtTree() {

    var url = urlPath + "/code/tree/children3";

    $.ajax({
        type: "post",
        url: url,
        data: {
            type: 1
        },
        dataType: "json",
        success: function (re) {

            if (!re) {
                return;
            }

            handleTreeData(re);

            var callbackHandler = {
                dataFilter: function (treeId, parentNode, responseData) {
                    //处理数据
                    if (responseData) {
                        handleTreeData(responseData);
                        return responseData;
                    } else {
                        return [];
                    }

                },
                onClick: function (event, treeId, treeNode) {
                    //处理点击事件
                    handleClickCourtTree(treeNode);
                }
            };

            //这个是配置
            var setting = {
                //回掉
                callback: {
                    onClick: callbackHandler.onClick,
                },
                async: {
                    enable: true,
                    dataFilter: callbackHandler.dataFilter,
                    url: url,
                    autoParam: ["id=id"],
                    otherParam: {
                        'type': 1,
                    }
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                }
            };

            $.fn.zTree.init($("#ztree-div"), setting, re);

            //触发第一个节点点击事件
            var zTreeObj = $.fn.zTree.getZTreeObj('ztree-div');
            var nodeById = zTreeObj.getNodeByTId("ztree-div_1");
            zTreeObj.selectNode(nodeById);
            zTreeObj.setting.callback.onClick(null, 'ztree-div', nodeById);//触发函数

        }
    })


}

//处理点击法院事件 同时生成右侧的数据
function handleClickCourtTree(treeNode) {
    console.log(treeNode);

    courtInfo['courtNo'] = treeNode['courtNo'];
    //读取政治专项
    readConfig("zzzx", treeNode['courtNo']);
    //读取法官员额
    readConfig("yefg", treeNode['courtNo']);
    //读取
    readConfig("level", treeNode['courtNo']);
}

//读取配置信息
function readConfig(scope, courtNo) {


    if (!scope) {
        return;
    }

    var url = urlPath + "/courtVerifyAction/getConfig";
    $.ajax({
        url: url,
        type: "post",
        data: {
            "scope": scope,
            "courtNo": courtNo,
        },
        dataType: "json",
        success: function (re) {

            if (re && re.length > 0) {

                if (typeof vm[scope] !== 'undefined') {
                    var vx = vm[scope];
                    vx.dataInfo = re;

                } else {
                    generateDataInfo(re, scope);
                }

            }
        }
    });

}

//获取到数据
function generateDataInfo(datas, scope) {

    var el = "";
    switch (scope) {
        case "zzzx":
            el = "#zzzx_config";
            break;
        case "yefg":
            el = "#yefg_config";
            break;
        case "level":
            el = "#level_config";
            break;

    }
    vm[scope] = new Vue({
        el: el,
        data: {
            dataInfo: datas
        },
        methods: {
            //处理删除事件
            handleDelete: function (ev) {

            }
        }

    });
    $(el).closest(".base_container").find(".hide_div").show();

}


//处理返回数据
function handleTreeData(datas) {
    $.each(datas, function (index, value) {
        value['name'] = value['text'];
        value['isParent'] = true;
    })
}

//限制正整数的输入
function onkeyupN(t) {
    t.value = t.value.replace(/\D/g, '')
}

function onafterpasteN(t) {
    t.value = t.value.replace(/\D/g, '')
}