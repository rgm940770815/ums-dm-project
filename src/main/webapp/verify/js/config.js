

var vm ={};
$(function () {

    //读取配置信息
    //退休时间

    readConfig("1");
    readConfig("2");
    readConfig("3");


    //给保存绑定事件
    $(".layui-btn").off("click").on("click", function () {
        var $this = $(this);
        var type = $this.attr("btn-c");
        var formC = "";
        var scope = "";
        switch (type) {
            case "1":
                formC = "save_form_1";
                scope = "level";
                break;
            case "2":
                formC = "save_form_2";
                scope = "retire";
                break;
            case "3":
                formC = "save_form_3";
                scope = "threshold";
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
            url: urlPath + "/verifyConfigAction/saveConfig",
            data: {
                jsonStr: json,
                scope: scope
            },
            dataType: "json",
            success: function (re) {
                if(re){
                    alert("更新成功");

                }else{
                    alert("操作失败");
                }
                readConfig(type);
            }
        })
    });

});


//读取配置信息
function readConfig(type,oScope) {

    var scope = "";
    if(oScope){
        scope = oScope;
    }
    switch (type) {
        case "1":
            scope = "level";
            break;
        case "2":
            scope = "retire";
            break;
        case "3":
            scope = "threshold";
            break;
    }


    var url = urlPath + "/verifyConfigAction/getConfig";
    $.ajax({
        url: url,
        type: "post",
        data: {
            scope: scope
        },
        dataType: "json",
        success: function (re) {
            console.log(re);
            if (re && re.length > 0) {

                if(typeof vm[scope] !== 'undefined'){
                    var vx = vm[scope];
                    vx.dataInfo = re;

                }else{
                    generateDataInfo(re, scope);
                }
            }
        }
    });

}


//生成法官等级信息
function generateDataInfo(datas, scope) {

    var el = "";
    switch (scope) {
        case "level":
            el = "#level_promote_config";
            break;
        case "retire":
            el = "#retire_config";
            break;
        case "threshold":
            el = "#idle_threshold_config";
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

                var target = ev.target || ev.srcElement;
                var $form = $(target).closest(".layui-form-item-my");
                var scope = $form.find(":input[name='cScope']").val();
                var key = $form.find(":input[name='cKey']").val();

                if(scope && key){
                    $.ajax({
                        type : "post",
                        url : urlPath + "/verifyConfigAction/deleteConfig",
                        data : {
                            scope : scope,
                            key : key
                        },
                        dataType : "json",
                        success :function (re) {
                            if(re ){
                                alert("删除成功");
                            }else{
                                alert("操作失败");
                            }
                            readConfig(null,scope);
                        }
                    })
                }

            }
        }

    });
    $(el).closest(".base_container").find(".hide_div").show();

}

//限制正整数的输入
function onkeyupN(t) {
    t.value = t.value.replace(/\D/g, '')
}

function onafterpasteN(t) {
    t.value = t.value.replace(/\D/g, '')
}