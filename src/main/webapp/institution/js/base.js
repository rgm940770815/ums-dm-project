//缓存
var cache = {
    code_108: {},
    code_106: {},
    code_107: {},
    code_92: {}
};
//存放配置
var configObj = {
    //加载数据的类型 1:单位(部门)基本情况 2:单位(部门)受奖惩情况 3:单位(部门)通信信息 4:机构编制
    loadType: 1

};
//存放单位部门
var nodeInfo = {
    courtNo: "",
    deptNo: "",
    //具体信息的id
    tipId: ""
};

var fileParamName = "";

$(function () {

    //先加载必要的编码 101 单位级别  100 单位性质类别    102部门性质类别
    loadCode(100);
    loadCode(101);
    loadCode(90);
    loadCode(92, cache['code_92']);
    loadCode(91);
    loadCode(108, cache['code_108']);
    loadCode(106, cache['code_106']);
    loadCode(107, cache['code_107']);

    initBUI();

    //给按钮绑定事件
    $("#button-save-1").off("click").on("click", function (e) {
        saveInstitution();
    });

    $("#button-insert-2").off("click").on("click", function (e) {

        $("div[fileParam] ul").empty();
        if (initF) {
            initF.resetUploadFile();
        }
        clearForm($(".middle_div_3 .initClass"));
        nodeInfo['tipId'] = "";

    });

    //给按钮绑定事件
    $("#button-save-2").off("click").on("click", function (e) {
        saveRewardPunish();
    });

    $("#button-delete-2").off("click").on("click", function (e) {

        baseDelete(2);
    });

    //给按钮绑定事件
    $("#button-save-3").off("click").on("click", function (e) {
        saveCommunication();
    });

    $("#button-insert-3").off("click").on("click", function () {

        clearForm($(".middle_div_4 .initClass"));
        nodeInfo['tipId'] = "";

    });

    $("#button-delete-3").off("click").on("click", function () {
        baseDelete();
    });

    //给按钮绑定事件
    $("#button-save-4").off("click").on("click", function (e) {
        savePersonRecord();
    });


    //给title 绑定切换时间
    $(".title_2 ul li").off("click").on("click", function () {
        $(".title_2 ul li span").removeClass("checked_span");
        var $span = $(this).find("span");
        $span.addClass("checked_span");
        configObj['loadType'] = Number($span.attr("typeCode"));
        loadInstitution();
    });


    //给按钮绑定事件
    $("#button-save-5").off("click").on("click", function (e) {
        savePartyOrganization();
    });

    //给按钮绑定事件
    $("#button-delete-5").off("click").on("click", function (e) {
        baseDelete();
    });

    $("#button-insert-5").off("click").on("click", function () {

        clearForm($(".middle_div_6 .initClass"));
        nodeInfo['tipId'] = "";

    });

    $(".paramInfo.isNumber").off("change").on("change", function () {

        var $this = $(this);
        if ($this.val()) {
            //是数字
            if ($this.hasClass("isNumber")) {
                var regexp = /^[0-9]*$/;
                if (!regexp.test($this.val())) {
                    $this.val("");
                }
            }

        }


    });

    $(".paramInfo.required").off("change").on("change", function () {

        var $this = $(this);
        if ($this.val()) {

            if ($this.is("input") || $this.is("textarea")) {
                $this.removeClass("not_fill");
            }

            if ($this.is("select")) {
                try {
                    if ($this.hasClass("select2-hidden-accessible")) {
                        $this.closest("td").find("span.select2-selection").removeClass("not_fill");
                    } else {
                        $this.removeClass("not_fill");
                    }
                } catch (e) {
                }
            }

            //是数字
            if ($this.hasClass("isNumber")) {
                var regexp = /^[0-9]*$/;
                if (!regexp.test($this.val())) {
                    $this.val("");
                }
            }

        }


    });


    $(".add_file").click(function (e) {
        fileParamName = $(this).closest("div[fileParam]").attr("fileParam");
        $(".extend-file-add").click();
    });


    //删除待上传的附件
    $(document).off("click", ".x-icon-delete").on("click", ".x-icon-delete", function () {

        var spanId = $(this).attr("spanId");
        if (spanId) {
            if (initF) {
                initF.funDeleteFile(spanId, true);
            }

        }

    });

    //删除已经存在的附件
    $(document).off("click", ".x-icon-delete-have").on("click", ".x-icon-delete-have", function () {

        var spanId = $(this).attr("spanId");
        if (!spanId) {
            return;
        }
        if (window.confirm("确定要删除附件吗?")) {

            $.ajax({
                url: basePath + "/rewardPunish/fjDelete",
                type: "post",
                data: {"uploadFile.id": spanId},
                dataType: "json",
                success: function (data) {
                    if (data && data.success) {
                        alert("删除成功");
                        var li = $("[spanId='" + spanId + "']").closest("li");
                        if (li) {
                            li.remove();
                        }
                    } else {
                        alert("删除失败");
                    }
                }
            })
        }


    });

    //下载附件
    $(document).off("click",".span-fj-download").on("click",".span-fj-download" ,function(){
        var $this = $(this);
        var spanId = $this.next().attr("spanId");
        if(spanId){
            downLoadFj(spanId)
        }
    })
});


function initBUI() {

    BUI.use(['bui/tree', 'bui/data', 'bui/calendar'], function (Tree, Data, Calendar) {

        //-------------树结构Start-----------------
        var treestore = new Data.TreeStore({
            root: {
                text: "全部",
                courtNo: "",
                deptNo: ""
            },
            url: basePath + 'code/tree/children5'
        });
        var tree = new Tree.TreeList({
            render: '.tree',
            store: treestore,
            elStyle: {border: "none"},
            //checkType: 'all',
            multipleCheck: false,
            showRoot: true
        });
        tree.render();
        treestore.load();//加载根节点，也可以让用户点击加载
        tree.on('itemclick', function (ev) {
            var item = ev.item;

            var id = item.id;
            try {
                var split = id.split(",");
            } catch (e) {
            }
            if (!split) {
                return;
            }
            var courtNo = split[0];
            var deptNo = split[1];
            nodeInfo['courtNo'] = courtNo;
            nodeInfo['deptNo'] = deptNo;
            nodeInfo['tipId'] = "";
            loadInstitution();
        });

        //========================树结构end==========================

        var datepicker = new Calendar.DatePicker({
            trigger: '.calendar',
            autoRender: true
        });
    })

}

function loadInstitution() {

    //先清空数据
    nodeInfo['tipId'] = "";
    clearForm();
    var courtNo = nodeInfo['courtNo'];
    var deptNo = nodeInfo['deptNo'];


    // Array.
    if ($.inArray(configObj['loadType'], [2, 3, 5]) === -1) {
        $(".context_bottom_div").addClass("hide");
        if (grid) {
            grid.destroy();
        }
    } else {
        $(".context_bottom_div").removeClass("hide");
    }

    //部门编码为空说明点击的是法院
    if (configObj['loadType'] === 1) {

        if (courtNo && 'null' == deptNo) {
            loadCourtInfo(courtNo);
        } else if (courtNo && deptNo) {
            //内设机构先不管
            if (deptNo == 101093324) {

            } else {
                loadDeptInfo(courtNo, deptNo);
            }
        }

    }

    if (configObj['loadType'] === 2) {
        loadContainer(courtNo, deptNo, 3);
    }
    if (configObj['loadType'] === 3) {
        loadContainer(courtNo, deptNo, 4);
    }
    if (configObj['loadType'] === 4) {
        loadPersonRecord(courtNo, deptNo);
    }
    if (configObj['loadType'] === 5) {
        loadContainer(courtNo, deptNo, 6);
    }

}

function loadCourtInfo(courtNo) {

    changeDiv(1);

    if (!nodeInfo['courtNo'] && !nodeInfo['deptNo']) {
        return;
    }

    if (nodeInfo['deptNo'] == 101093324) {
        return;
    }

    //加载数据的类型
    if (configObj['loadType'] === 1) {


        $.ajax({
            url: basePath + "code/getCourtByNo",
            data: {"paramInfo.courtNo": courtNo},
            dataType: "json",
            type: "post",
            success: function (data) {
                if (data) {

                    if (data.info) {
                        $(".middle_div_1 .paramInfo").each(function (ev) {

                            var $ev = $(this);
                            showParamInfo($ev, data.info);

                        })
                    }
                    if (data.parentName) {
                        $(".middle_div_1 input[name='parentInstitution']").val(data.parentName).prop("readOnly", "readOnly");
                    }


                }
            }
        })
    }
}

function loadDeptInfo(courtNo, deptNo) {

    changeDiv(2);

    if (!nodeInfo['courtNo'] && !nodeInfo['deptNo']) {
        return;
    }

    if (nodeInfo['deptNo'] == 101093324) {
        return;
    }

    //加载数据的类型
    if (configObj['loadType'] === 1) {

        $.ajax({
            url: basePath + "deptAction/getDeptByNo",
            data: {"department.courtNo": courtNo, "department.deptNo": deptNo},
            dataType: "json",
            type: "post",
            success: function (data) {
                if (data) {

                    if (data.info) {
                        $(".middle_div_2 .paramInfo").each(function (ev) {

                            var $ev = $(this);
                            showParamInfo($ev, data.info);

                        })
                    }
                    if (data.parentName) {
                        $(".middle_div_2 input[name='parentInstitution']").val(data.parentName).prop("readOnly", "readOnly");
                    }


                }
            }
        })
    }
}

// function load
function loadPersonRecord(courtNo, deptNo) {
    changeDiv(5);
    if (!nodeInfo['courtNo'] && !nodeInfo['deptNo']) {
        return;
    }

    if (nodeInfo['deptNo'] == 101093324) {
        return;
    }

    $.ajax({
        url: basePath + "personRecord/query",
        data: {"information.courtNo": courtNo, "information.deptNo": deptNo},
        dataType: "json",
        type: "post",
        success: function (data) {
            if (data) {

                nodeInfo['tipId'] = data.id;
                $(".middle_div_5 .paramInfo").each(function (ev) {
                    var $ev = $(this);
                    showParamInfo($ev, data);

                });


            }
        }
    })
}

function loadContainer(courtNo, deptNo, num) {

    changeDiv(num);


    if (!nodeInfo['courtNo'] && !nodeInfo['deptNo']) {
        return;
    }

    if (nodeInfo['deptNo'] == 101093324) {
        return;
    }

    generateBUI(courtNo, deptNo);

    if (num === 3) {
        //把显示的文件清空
        $("div[fileParam] ul").empty();
        if (initF) {
            initF.resetUploadFile();
        }

        initUpload();
    }

}

//保存基本信息
function saveInstitution() {
    var courtNo = nodeInfo['courtNo'];
    var deptNo = nodeInfo['deptNo'];

    if (deptNo == 101093324) {
        return;
    }
    //部门编码为空说明点击的是法院
    if (courtNo && 'null' == deptNo) {
        saveCourtInfo(courtNo);
    } else if (courtNo && deptNo) {
        //内设机构先不管
        if (deptNo == 101093324) {

        } else {
            saveDeptInfo(courtNo, deptNo);

        }

    }

}

//保存法院基本信息
function saveCourtInfo(courtNo) {


    //加载数据的类型
    if (configObj['loadType'] === 1) {


        var updateData = {"paramInfo.courtNo": courtNo};
        $(".middle_div_1 .paramInfo:not(.readOnly)").each(function () {
            var $this = $(this);
            updateData["paramInfo." + $this.attr("param")] = $this.val();
        });

        $.ajax({
            url: basePath + "code/saveCourtInfo",
            data: updateData,
            dataType: "json",
            type: "post",
            success: function (data) {
                if (data && data.success) {
                    alert("修改成功");
                } else {
                    alert("修改失败");
                }

            }
        })

    }

}

//保存部门基本信息
function saveDeptInfo(courtNo, deptNo) {

    //加载数据的类型
    if (configObj['loadType'] === 1) {

        var updateData = {"department.courtNo": courtNo, "department.deptNo": deptNo};
        $(".middle_div_2 .paramInfo:not(.readOnly)").each(function () {
            var $this = $(this);
            updateData["department." + $this.attr("param")] = $this.val();
        });

        $.ajax({
            url: basePath + "deptAction/saveDeptInfo",
            data: updateData,
            dataType: "json",
            type: "post",
            success: function (data) {
                if (data && data.success) {
                    alert("修改成功");
                } else {
                    alert("修改失败");

                }
            }
        })

    }

}

//保存奖惩信息
function saveRewardPunish() {

    var courtNo = nodeInfo['courtNo'];
    var deptNo = nodeInfo['deptNo'];

    if (deptNo == 101093324) {
        return;
    }
    var updateData = {};
    if (courtNo) {
        updateData["information.courtNo"] = courtNo;
    }
    if (deptNo && 'null' != deptNo) {
        updateData["information.deptNo"] = deptNo;
    }

    //提交前的验证
    var flag = validateRequired($(".middle_div_3"));

    if (!flag) {
        alert("有必填项未填写!");
        return;
    }

    $(".middle_div_3 .paramInfo:not(.readOnly)").each(function () {
        var $this = $(this);
        updateData["information." + $this.attr("param")] = $this.val();
    });


    var url = "";
    if (nodeInfo['tipId']) {
        updateData["information.id"] = nodeInfo['tipId'];
        url = basePath + "rewardPunish/update";
    } else {
        url = basePath + "rewardPunish/insert";
        //为了使附件保存有id 人为的生成一个id
        nodeInfo['tipId'] = guid();
        updateData["information.id"] = nodeInfo['tipId'];

    }


    var flag_ = true;
    if(initF.fileFilter.length > 0){
        flag_ = false;
    }

    $.ajax({
        url: url,
        data: updateData,
        dataType: "json",
        type: "post",
        success: function (data) {
            if (data && data.success) {
                alert("修改成功");
                //不在这里读取 在附件上传完后读取
                if(flag_){
                    store.load();
                }

            } else {
                alert("修改失败");

            }
        }
    });

    //保存附件
    $(".extend-file-save").click();
}

//保存通信信息
function saveCommunication() {

    var courtNo = nodeInfo['courtNo'];
    var deptNo = nodeInfo['deptNo'];

    if (deptNo == 101093324) {
        return;
    }
    var updateData = {};
    if (courtNo) {
        updateData["information.courtNo"] = courtNo;
    }
    if (deptNo && 'null' != deptNo) {
        updateData["information.deptNo"] = deptNo;
    }

    var flag = false;
    $(".middle_div_4 .paramInfo:not(.readOnly)").each(function () {
        var $this = $(this);
        updateData["information." + $this.attr("param")] = $this.val();
        if ($this.val()) {
            flag = true;
        }
    });

    if (!flag) {
        alert("不能保存空白的表单!");
        return;
    }

    var url = "";
    if (nodeInfo['tipId']) {
        updateData["information.id"] = nodeInfo['tipId'];
        url = basePath + "communication/update";
    } else {
        url = basePath + "communication/insert";
    }


    $.ajax({
        url: url,
        data: updateData,
        dataType: "json",
        type: "post",
        success: function (data) {
            if (data && data.success) {
                alert("修改成功");
                store.load();
            } else {
                alert("修改失败");

            }
        }
    })
}


//保存机构编制
function savePersonRecord() {

    var courtNo = nodeInfo['courtNo'];
    var deptNo = nodeInfo['deptNo'];

    if (deptNo == 101093324) {
        return;
    }
    var updateData = {};
    if (courtNo) {
        updateData["information.courtNo"] = courtNo;
    }
    if (deptNo && 'null' != deptNo) {
        updateData["information.deptNo"] = deptNo;
    }

    //提交前的验证
    var flag = validateRequired($(".middle_div_5"));

    if (!flag) {
        alert("有必填项未填写!");
        return;
    }

    $(".middle_div_5 .paramInfo:not(.readOnly)").each(function () {
        var $this = $(this);
        updateData["information." + $this.attr("param")] = $this.val();
    });

    //验证是否选择了左侧tree中的机构
    var treeVal = $("#spywjgs").val();
    console.log('tree: ',treeVal, typeof treeVal);
    if(treeVal == ''){
        alert("请先选择法院机构或者部门！")
        return;
    }

    var url = "";
    if (nodeInfo['tipId']) {
        updateData["information.id"] = nodeInfo['tipId'];
        url = basePath + "personRecord/update";
    } else {
        url = basePath + "personRecord/insert";
    }
    console.log(updateData);

    $.ajax({
        url: url,
        data: updateData,
        dataType: "json",
        type: "post",
        success: function (data) {
            console.log(data);
            if (data && data.success) {
                alert("修改成功");
                store.load();
            } else {
                alert("修改失败");

            }
        }
    })
}

// 是否建立党组织 选择否时,没有必填项;选择是时,不变
$(".middle_div_6 select[name='isBuild']").parent().change(function () {
    if ($(".middle_div_6 select[name='isBuild'] option:selected").text() == "否") {
        $(".middle_div_6 .isHide").css("visibility", "hidden");
        $(".middle_div_6 select[name='orgType']").select2("val", "");
        $(".middle_div_6 select[name='orgSituation']").select2("val", "");
        $(".middle_div_6 input").val("");
        $(".middle_div_6 select[name='orgType']").attr("disabled", "disabled ");
        $(".middle_div_6 select[name='orgSituation']").attr("disabled", "disabled ");
        $(".middle_div_6 input").attr("readonly", "readonly ");
    } else {
        $(".middle_div_6 .isHide").css("visibility", "visible");
        $(".middle_div_6 select[name='orgType']").removeAttr("disabled");
        $(".middle_div_6 select[name='orgSituation']").removeAttr("disabled");
        $(".middle_div_6 input").removeAttr("readonly");
    }
});

//保存通信信息
function savePartyOrganization() {

    var courtNo = nodeInfo['courtNo'];
    var deptNo = nodeInfo['deptNo'];

    if (deptNo == 101093324) {
        return;
    }
    var updateData = {};
    if (courtNo) {
        updateData["information.courtNo"] = courtNo;
    }
    if (deptNo && 'null' != deptNo) {
        updateData["information.deptNo"] = deptNo;
    }

    //提交前的验证
    var flag = validateRequired($(".middle_div_6"));

    if (!flag) {
        alert("有必填项未填写!");
        return;
    }

    $(".middle_div_6 .paramInfo:not(.readOnly)").each(function () {
        var $this = $(this);
        updateData["information." + $this.attr("param")] = $this.val();
    });

    var url = "";
    if (nodeInfo['tipId']) {
        updateData["information.id"] = nodeInfo['tipId'];
        url = basePath + "party/update";
    } else {
        url = basePath + "party/insert";
    }


    $.ajax({
        url: url,
        data: updateData,
        dataType: "json",
        type: "post",
        success: function (data) {
            if (data && data.success) {
                alert("修改成功");
                store.load();
            } else {
                alert("修改失败");

            }
        }
    })

}

//清除数据
function clearForm($ev) {

    if (!$ev) {
        $ev = $(".context_div .initClass");
    }

    $ev.each(function () {
        var $ev = $(this);
        if ($ev.is("input") || $ev.is("textarea")) {
            $ev.val("");
        }
        if ($ev.is("select")) {
            try {
                if ($ev.hasClass("select2-hidden-accessible")) {
                    $ev.select2("val", "")
                }
            } catch (e) {
            }
            $ev.val("");
        }

        if ($ev.hasClass("required")) {

            if ($ev.is("input") || $ev.is("textarea")) {
                $ev.removeClass("not_fill");
            }

            if ($ev.is("select")) {
                try {
                    if ($ev.hasClass("select2-hidden-accessible")) {
                        $ev.closest("td").find("span.select2-selection").removeClass("not_fill");
                    } else {
                        $ev.removeClass("not_fill");
                    }
                } catch (e) {
                }
            }

        }

    });

}

//根据页面显示取得数据的信息
function showParamInfo($ev, data) {

    var attr = $ev.attr("param");
    if (!attr) {
        return;
    }
    //可能有多个匹配项
    var val = "";
    var strings = attr.trim().split(/\s+/);
    for (var j in strings) {
        val = data[strings[j]];
        if (val || 0 === val || false === val) {
            if (false === val) {
                val = 0;
            }
            if ($ev.is("input") || $ev.is("textarea")) {
                $ev.val(val);
            }
            if ($ev.is("select")) {
                $ev.select2("val", val)
            }

            break;
        }
    }
    // readonly
    if ($ev.hasClass("readOnly")) {
        if ($ev.is("input")) {
            $ev.prop("readOnly", "readOnly");
        }
        if ($ev.is("select")) {
            $ev.select2("enable", false);
        }

    }

}

//type 1:middle_div_1 法院基本信息   2:middle_div_2 部门基本信息  3:
function changeDiv(type) {

    $(".context_div .middle_div").addClass("hide");
    $(".title_3 ul").addClass("hide");

    var $container = null;
    var $container_r = null;
    switch (type) {
        case 1:
            $container = $(".context_div .middle_div_1");
            $container_r = $(".title_3 .button-group-1");
            break;
        case 2:
            $container = $(".context_div .middle_div_2");
            $container_r = $(".title_3 .button-group-1");
            break;
        case 3:
            $container = $(".context_div .middle_div_3");
            $container_r = $(".title_3 .button-group-2");
            break;
        case 4 :
            $container = $(".context_div .middle_div_4");
            $container_r = $(".title_3 .button-group-3");
            break;
        case 5 :
            $container = $(".context_div .middle_div_5");
            $container_r = $(".title_3 .button-group-4");
            break;
        case 6 :
            $container = $(".context_div .middle_div_6");
            $container_r = $(".title_3 .button-group-5");
            break;
    }
    if ($container_r) {
        $container_r.removeClass("hide");
    }
    if ($container) {
        $container.removeClass("hide");
    }


}


var grid;
var store;
var startRow;

function generateBUI(courtNo, deptNo) {

    if (grid) {
        grid.destroy();
    }
    // Array.
    if ($.inArray(configObj['loadType'], [2, 3, 5]) === -1) {
        $(".context_bottom_div").addClass("hide");
        return
    } else {
        $(".context_bottom_div").removeClass("hide");
    }

    if (!courtNo && !deptNo) {
        return;
    }


    var queryData = {};
    var storeUrl = "";
    var columns = [];
    var $container;

    if (courtNo) {
        queryData["information.courtNo"] = courtNo;
    }
    if (deptNo && 'null' != deptNo) {
        queryData["information.deptNo"] = deptNo;
    }

    switch (configObj['loadType']) {
        case 2:
            storeUrl = basePath + "rewardPunish/query";
            $container = $(".middle_div_3");
            columns = [
                {
                    title: '序号', sortable: false, width: "60", align: 'left', renderer: function (value, obj) {
                        return startRow++;
                    }
                },
                {
                    title: '奖励名称', dataIndex: 'rewardTypeCode', width: "280", align: 'left',
                    renderer: BUI.Grid.Format.enumRenderer(cache['code_92'])
                },
                {title: '其他奖励名称', dataIndex: 'rewardNameAppend', width: "200", align: 'left'},
                {title: '惩处名称', dataIndex: 'punishName', width: "200", align: 'left'},
                {title: '奖惩日期', dataIndex: 'recordDate', width: "200", align: 'left'},
                {title: '奖惩原因', dataIndex: 'reason', width: "200", align: 'left'},

            ];
            break;

        case 3:
            storeUrl = basePath + "communication/query";
            $container = $(".middle_div_4");
            columns = [
                {
                    title: '序号', sortable: false, width: "60", align: 'left', renderer: function (value, obj) {
                        return startRow++;
                    }
                },
                {title: '单位地址', dataIndex: 'address', width: "280", align: 'left'},
                {title: '单位电话号码', dataIndex: 'phoneNumber', width: "200", align: 'left'},
                {title: '单位传真号码', dataIndex: 'faxNumber', width: "200", align: 'left'},
                {title: '单位邮政编码', dataIndex: 'postalCode', width: "200", align: 'left'},
                {title: '电子邮箱 ', dataIndex: 'emailAddress', width: "200", align: 'left'}

            ];
            break;
        case 5:
            storeUrl = basePath + "party/query";
            $container = $(".middle_div_6");
            columns = [
                {
                    title: '序号', sortable: false, width: "60", align: 'left', renderer: function (value, obj) {
                        return startRow++;
                    }
                },
                {
                    title: '是否建立党组织', dataIndex: 'isBuild', width: "140", align: 'left',
                    renderer: BUI.Grid.Format.enumRenderer(cache['code_108'])
                },
                {
                    title: '党组织类型', dataIndex: 'orgType', width: "140", align: 'left',
                    renderer: BUI.Grid.Format.enumRenderer(cache['code_106'])
                },
                {title: '党组织名称', dataIndex: 'orgName', width: "320", align: 'left'},
                {title: '所属党员数', dataIndex: 'orgPersonCount', width: "140", align: 'left'},
                {title: '专职党务干部数', dataIndex: 'orgCadrePersonCount', width: "140", align: 'left'},
                {
                    title: '党组织书记任职情况', dataIndex: 'orgSituation', width: "200", align: 'left',
                    renderer: BUI.Grid.Format.enumRenderer(cache['code_107'])
                }

            ];
            break;
    }

    if (!storeUrl) {
        return;
    }


    BUI.use(['common/search', 'bui/tree', 'bui/data', 'bui/calendar', 'bui/overlay', 'bui/form', 'bui/uploader', 'bui/mask', 'bui/grid'],
        function (Search, Tree, Data, Calendar, Overlay, Form, Uploader, Mask, Grid) {


            store = Search.createStore(storeUrl, {
                sortField: 'sortNo',
                sortDirection: 'ASC',
                remoteSort: true,
                pageSize: 4,
                params: queryData
            });


            //定义表格样式
            var gridCfg = Search.createGridCfg(columns, {
                //width: 600,
                height: 200,
                //forceFit: true,
                emptyDataTpl: '<div class="centered"><h2>查询的数据不存在</h2></div>',
                plugins: [BUI.Grid.Plugins.RadioSelection, BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格
            });

            store.on("load", function () {
                startRow = store.get("start") + 1;

            });

            var search = new Search({
                store: store,
                gridCfg: gridCfg
            });
            grid = search.get('grid');

            grid.on("aftershow", function (ev) {
                var records = store.getResult();

                clearForm($container.find(".initClass"));

                if (configObj['loadType'] == 2) {
                    $("div[fileParam] ul").empty();
                    if (initF) {
                        initF.resetUploadFile();
                    }
                }


                if (records instanceof Array && records.length > 0) {
                    nodeInfo['tipId'] = records[0].id;


                    var item = grid.getItemAt(0);
                    grid.setSelected(item);

                    $container.find(".paramInfo").each(function (ev) {
                        var $ev = $(this);
                        showParamInfo($ev, records[0]);

                    });

                    if (configObj['loadType'] == 2) {
                        //更新参数
                        if (initF) {
                            initF.setParam("information.relationId", nodeInfo['tipId']);
                        }
                        loadFj();
                    }
                }


            });

            grid.on('itemclick', function (ev) {
                var item = ev.item;

                nodeInfo['tipId'] = item.id;
                clearForm($container.find(".initClass"));

                if (configObj['loadType'] == 2) {
                    //更新参数
                    if (initF) {
                        initF.setParam("information.relationId", nodeInfo['tipId']);
                    }

                    loadFj();
                }

                $container.find(".paramInfo").each(function (ev) {


                    var $ev = $(this);
                    showParamInfo($ev, item);

                })

            });
        });


}

function baseDelete(num) {

    if (!nodeInfo['tipId']) {
        return;
    }

    var deleteUrl = "";
    var deleteData = {};
    switch (configObj['loadType']) {
        case 2:
            deleteUrl = basePath + "rewardPunish/delete";
            deleteData = {"information.id": nodeInfo['tipId']};
            break;
        case 3:
            deleteUrl = basePath + "communication/delete";
            deleteData = {"information.id": nodeInfo['tipId']};
            break;
        case 5:
            deleteUrl = basePath + "party/delete";
            deleteData = {"information.id": nodeInfo['tipId']};
            break;
    }

    if (!deleteUrl) {
        return;
    }

    if (window.confirm('你确定要删除吗？')) {


        nodeInfo['tipId'] = "";
        $.ajax({
            url: deleteUrl,
            type: "post",
            data: deleteData,
            dataType: "json",
            success: function (data) {

                if(num == 2){

                    $("div[fileParam] ul").empty();
                    if (initF) {
                        initF.resetUploadFile();
                    }
                }

                if (data && data.success) {
                    alert("删除成功");
                    store.load();
                }

            }
        });

    }
}

function loadCode(typeId, $con) {

    var $container;


    switch (typeId) {
        case 100:
            $container = $("select[name='courtType']");
            break;
        case 101:
            $container = $("select[name='courtGrade']");
            break;
        case 90:
            $container = $("select[name='grantUnitCode']");
            break;
        case 91:
            $container = $("select[name='recognitionField']");
            break;
        case 92:
            $container = $("select[name='rewardTypeCode']");
            break;
        case 108:
            $container = $("select[name='isBuild']");
            break;
        case 106:
            $container = $("select[name='orgType']");
            break;
        case 107:
            $container = $("select[name='orgSituation']");
            break;
    }

    if (!$container) {
        return;
    }


    $.ajax({
        url: basePath + "code/codeListByType",
        type: "post",
        data: {typeId: typeId},
        dataType: "json",
        // async: false,
        success: function (data) {
            cache['code_101'] = data;

            var text = "<option value=''></option>";
            for (var i in data) {
                if (1 != data[i]['isValid']) {
                    continue;
                }
                text += "<option value='" + data[i].id + "'>" + data[i].codeName + "</option>";
                if ($con) {
                    $con[data[i].id] = data[i].codeName;
                }
            }
            $container.html(text);
            $container.select2({width: "100%"}).select2("val", "");

        }
    });

}

function validateRequired($container) {
    var flag = true;
    $container.find(".paramInfo.required").each(function () {
        var $this = $(this);
        // 额外添加一个例外项
        if ($(".middle_div_6 select[name='isBuild'] option:selected").text() == "否") {
            if ($this.is("input") || $this.is("textarea")) {
                $this.removeClass("not_fill");
            }

            if ($this.is("select")) {
                try {
                    if ($this.hasClass("select2-hidden-accessible")) {
                        $this.closest("td").find("span.select2-selection").removeClass("not_fill");
                    } else {
                        $this.removeClass("not_fill");
                    }
                } catch (e) {
                }
            }
        } else if (!$this.val()) {

            if ($this.is("input") || $this.is("textarea")) {
                $this.addClass("not_fill");
            }

            if ($this.is("select")) {
                try {
                    if ($this.hasClass("select2-hidden-accessible")) {
                        $this.closest("td").find("span.select2-selection").addClass("not_fill");
                    } else {
                        $this.addClass("not_fill");
                    }
                } catch (e) {
                }
            }

            flag = false;
        } else {

            if ($this.is("input") || $this.is("textarea")) {
                $this.removeClass("not_fill");
            }

            if ($this.is("select")) {
                try {
                    if ($this.hasClass("select2-hidden-accessible")) {
                        $this.closest("td").find("span.select2-selection").removeClass("not_fill");
                    } else {
                        $this.removeClass("not_fill");
                    }
                } catch (e) {
                }
            }

        }

    });

    return flag;
}


//初始化上传
var initF;

function initUpload() {
    var config = {
        fileInput: $(".extend-file-add")[0],
        url: basePath + "upload/uploadFile",
        upButton: $(".extend-file-save").get(0),
        parameters: {},
        filter: function (files) {
            var arrFiles = [];
            for (var i = 0, file; file = files[i]; i++) {
                arrFiles.push(file);
            }
            return arrFiles;
        },
        beforeUpload: function () {
            //加遮罩层
            if(initF.fileFilter.length == 0){
                return false;
            }
            initF.setParam("information.relationId", nodeInfo['tipId']);
            $(".mask-info").show();
            return true;
        },
        onSelect: function (files, e) {

            var template = "<li>\n" +
                "<span>[{filename}]</span>\n" +
                "<span class=\"x-icon x-icon-small x-icon-delete\" spanId=\"[{index}]\">×</span>\n" +
                "</li>";
            var container = $("div[fileParam='" + fileParamName + "'] ul");
            var regexp = /\[\{(.*?)\}\]/g;
            // container.empty();
            for (var i = 0; i < files.length; i++) {
                var file = files[i];
                if (file) {
                    var innerHtml = template;
                    var contentMatch = innerHtml.match(regexp);
                    var fileParams = {
                        filename: file.name,
                        index: file.index
                    };
                    for (var d in contentMatch) {
                        regexp.lastIndex = 0;
                        var d_index_ = regexp.exec(contentMatch[d]);
                        var d_content_ = fileParams[d_index_[1]];
                        innerHtml = innerHtml.replace(contentMatch[d], d_content_);
                    }
                    container.append(innerHtml);
                }
            }
        },
        onDelete: function (index, flag) {
            var li = $("[spanId='" + index + "']").closest("li");
            if (li) {
                li.remove();
            }
        },
        onComplete: function (flag) {
            $(".mask-info").hide();
            //加载数据
            store.load();

        }

    };

    if (!initF) {
        initF = new uploadFileTemp(config);
    }


}


function loadFj() {

    $("div[fileParam] ul").empty();
    if (initF) {
        initF.resetUploadFile();
    }

    var tipId = nodeInfo['tipId'];

    if (!tipId) {
        return;
    }

    loadFjd(tipId, 1);
    loadFjd(tipId, 2);
    loadFjd(tipId, 3);
    loadFjd(tipId, 4);
}

function loadFjd(tipId, type) {

    $.ajax({
        url: basePath + "rewardPunish/fjQuery",
        data: {"uploadFile.relationId": tipId, "uploadFile.relationType": type},
        dataType: "json",
        type: "post",
        success: function (data) {
            if (data && data.length > 0) {

                for (var i in data) {
                    doFile(data[i]);
                }

            }
        }
    })

}

function doFile(file) {

    var template = "<li>\n" +
        "<span class='span-fj-download'>[{filename}]</span>\n" +
        "<span class=\"x-icon x-icon-small x-icon-delete-have\" spanId=\"[{index}]\">×</span>\n" +
        "</li>";
    var container = $("div[fileParam='" + file.relationType + "'] ul");
    var regexp = /\[\{(.*?)\}\]/g;
    if (file) {
        var innerHtml = template;
        var contentMatch = innerHtml.match(regexp);
        var fileParams = {
            filename: file.fileName,
            index: file.id
        };
        for (var d in contentMatch) {
            regexp.lastIndex = 0;
            var d_index_ = regexp.exec(contentMatch[d]);
            var d_content_ = fileParams[d_index_[1]];
            innerHtml = innerHtml.replace(contentMatch[d], d_content_);
        }
        container.append(innerHtml);
    }
}

function guid() {
    /**
     * @return {string}
     */
    function S4() {
        return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
    }

    return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4());
}


function downLoadFj(spanId) {
    if(!spanId){
        return;
    }

    var url = basePath + '/rewardPunish/downLoadFj';
    var turnForm = document.getElementById("downloadForm");
    if (turnForm) {
        turnForm.innerHTML = "";
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
    var input_ = document.createElement("input");
    input_.name = "uploadFile.id";
    input_.value = spanId;
    turnForm.appendChild(input_);
    turnForm.submit();


}