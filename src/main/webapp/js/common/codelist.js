/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @param cbo           dom对象
 * @param typeId
 * @param listType      展示类型，默认为select列表，可以设置为tree(树的所有节点都可以选择)，或者是tree2(只能选择无子节点的节点)
 * @param firstLine     select展示类型下的第一行
 * @param callback      回调方法
 * @param copySelector  需要执行相同操作的jquery对象
 */
var tree_dialog_id;
function loadCodeListbyType(cbo, typeId, listType, firstLine, callback , copySelector) {
    $.getJSON("/ums/code/codeListByType",
            {
                typeId: typeId,
                getParent: 1
            },
    function (data) {
        if (firstLine) {
            $(cbo).html(firstLine);
            if(copySelector){
                copySelector.html(firstLine);
            }
        } else {
            $(cbo).empty();
            if(copySelector){
                copySelector.empty();
            }
        }
        if(data) {
            for (var i = 0; i < data.length; i++) {
                $(cbo).append($("<option>").attr({"value": data[i].id}).text(data[i].codeName));
                //把同样的东西赋予另外一个jQuery对象
                if (copySelector) {
                    copySelector.append($("<option>").attr({"value": data[i].id}).text(data[i].codeName));
                }
            }
            if (listType) {
                if (listType.indexOf('tree') != -1) {
                    //组装成tree需要的对象格式
                    var getJsonTree = function (data, parentid, pid) {
                        var itemArr = [];
                        for (var i = 0; i < data.length; i++) {
                            var node = data[i];
                            if (node.parentId == parentid) {
                                var newNode = {};
                                newNode.id = node.id;
                                newNode.typeId = typeId;
                                newNode.text = node.codeName;
                                newNode.sortNo = node.sortNo;
                                newNode.pid = pid + ',' + node.id;
                                newNode.children = getJsonTree(data, node.id, pid + "," + node.id);
                                itemArr.push(newNode);
                            }
                        }
                        return itemArr;
                    };
                    var treeData = getJsonTree(data, null, '-1');
                    var cboClickfunction = function (ev) {
                        var cbo = $(ev.target);
                        tree_dialog_id = cbo.attr('tree_dialog');
                        BUI.getControl('tree_dialog_' + typeId + '_' + listType).show();
                    };
                    var cboChangefunction = function (ev) {
                        var cbo = $(ev.target);
                        var txt = cbo.find('option:selected').text();
                        var span = cbo.next().children().find('.select2-selection__rendered');
                        span.text(txt).attr('title', txt);
                    };
                    var cbowidth = $(cbo).width() + 24;
                    var html = "<span class=\"select2 select2-container select2-container--default\" dir=\"ltr\" style=\"width: 166px;\"><span class=\"selection\"><span class=\"select2-selection select2-selection--single\" role=\"combobox\" aria-autocomplete=\"list\" aria-haspopup=\"true\" aria-expanded=\"false\" tabindex=\"0\" aria-labelledby=\"select2-n_reward_type-container\"><span class=\"select2-selection__rendered\" title=\"请选择\">请选择</span><span class=\"select2-selection__arrow\" role=\"presentation\"><b role=\"presentation\"></b></span></span></span><span class=\"dropdown-wrapper\" aria-hidden=\"true\"></span></span>";
                    $(cbo).on('change', cboChangefunction).after(html);
                    $(cbo).next().width(cbowidth).children().find('.select2-selection__rendered').attr('tree_dialog', BUI.guid('tree_dialog')).on('click', cboClickfunction);
                    $(cbo).hide();
                    if (copySelector) {
                        copySelector.each(function (e) {
                            var cbowidth = $(this).width() + 24;
                            $(this).on('change', cboChangefunction).after(html);
                            $(this).next().width(cbowidth).children().find('.select2-selection__rendered').attr('tree_dialog', BUI.guid('tree_dialog')).on('click', cboClickfunction);
                            $(this).hide();
                        });
                    }

                    var dialogId = 'tree_dialog_' + typeId + '_' + listType;

                    //只能选择子节点
                    if (listType.indexOf('tree') != -1) {
                        //赋值，并增加点击事件
                        BUI.use(['bui/tree', 'bui/overlay'], function (Tree, Overlay) {
                            var tree = new Tree.TreeList({
                                id: dialogId + '_tree_id',
                                root: {                  //由于要显示根节点，所以需要配置根节点
                                    id: '-1',
                                    text: '全部',
                                    typeId: typeId,
                                    expanded: true,
                                    children: treeData
                                },
                                showLine: true, //显示连接线
                                showRoot: true
                            });
                            tree.on('itemclick', function (ev) {
                                if ($(ev.domTarget).parent().hasClass('x-tree-icon-wraper')) return true;
                                var item = ev.item;
                                if (item.children && item.children.length > 0 && listType == 'tree2') {
                                    tree.toggleExpand(item);
                                    return false;
                                } else {
                                    $('[tree_dialog=' + tree_dialog_id + ']').text(item.text).attr('title', item.text)
                                        .closest('.select2-container').prev().val(item.id).change();
                                    BUI.getControl(dialogId).close();
                                }
                            });
                            var dialog = new Overlay.Dialog({
                                id: dialogId,
                                width: 350,
                                height: 400,
                                zIndex: 1080,
                                buttons: [{
                                    text: '清除',
                                    elCls: 'button button-primary',
                                    handler: function () {
                                        var span = $('[tree_dialog=' + tree_dialog_id + ']');
                                        var cbo = span.closest('.select2-container').prev();
                                        var firstline = cbo.find("option:eq(0)");
                                        span.text(firstline.text()).attr('title', firstline.text());
                                        cbo.val(firstline.val());
                                        this.close();
                                    }
                                }],
                                elCls: 'custom-dialog',
                                children: [tree]
                            });
                            dialog.on('show', function (ev) {
                                $('.custom-dialog').find('.bui-stdmod-body').css('overflow', 'auto')
                                    .find('.bui-tree-list').css('border', 'none');

                                var tree = this.getChild(this.get('id') + '_tree_id');
                                var span = $('[tree_dialog=' + tree_dialog_id + ']');
                                var cbo = span.closest('.select2-container').prev();
                                cbo.parent().find('.valid-text').remove();

                                var id = cbo.find('option:selected').val();
                                var text = cbo.find('option:selected').text();
                                span.text(text).attr('title', text);
                                if (id) {
                                    var itemCurrent = tree.findNode(id);
                                    tree.expandPath(itemCurrent.pid);
                                    tree.setSelected(itemCurrent);
                                }
                            });
                        });
                    }
                } else if (listType == 'select2') {
                    var firstOptionValue = $(cbo).find("option:eq(0)").val();
                    $(cbo).select2().select2("val", firstOptionValue);
                    $(cbo).change();
                    if (copySelector) {
                        firstOptionValue = copySelector.find("option:eq(0)").val();
                        copySelector.select2().select2("val", firstOptionValue);
                        copySelector.change();
                    }
                }
            }
        }

        if (callback) {
            callback();
        }

    });
}

function codeListLabel(label) {
    if ($(label).text())
        return;

    var cbo = $("#" + $(label).attr("for"));
    var typeId = cbo.attr("typeId");

    $.getJSON("/ums/code/codeTypeName", {typeId: typeId}, function (data) {
        $(label).text(data);
        //$(cbo).before($(label).attr({"for": $(cbo).attr("id")}).text(data));
    });
}

function loadCodeList(cbo, firstLine, callback , copySelector) {
    loadCodeListbyType(cbo, $(cbo).attr("typeId"), $(cbo).attr("listType"), firstLine, callback ,copySelector);
}

function loadDeptList(cbo, courtNo, firstLine, callback) {
    $.getJSON("/ums/code/deptByCourtNo",
            {
                courtNo: courtNo
            },
    function (data) {
        if (firstLine) {
            $(cbo).html(firstLine);
        } else {
            $(cbo).empty();
        }
        if (data) {
            for (var i = 0; i < data.length; i++) {
                $(cbo).append($("<option>").attr({"value": data[i].deptNo}).text(data[i].deptName));
            }
        }

        if (callback) {
            callback();
        }
    });
}

function loadCourts(cbo, firstLine, callback) {
    $.getJSON("/ums/code/courtList", {}, function (data) {
        if (firstLine) {
            $(cbo).html(firstLine);
        } else {
            $(cbo).empty();
        }
        if (data) {
            for (var i = 0; i < data.length; i++) {
                $(cbo).append($("<option>").attr({"value": data[i].courtNo}).text(data[i].courtStdName));
            }
        }

        if (callback) {
            callback();
        }
    });
}

function loadCourts2(cbo, firstLine, callback) {
    $.getJSON("/ums/code/courtList2", {}, function (data) {
        if (firstLine) {
            $(cbo).html(firstLine);
        } else {
            $(cbo).empty();
        }
        if (data) {
            for (var i = 0; i < data.length; i++) {
                $(cbo).append($("<option>").attr({"value": data[i].courtStdNo}).text(data[i].courtStdName));
            }
        }
        if (callback) {
            callback();
        }
    });
}

function loadArea(cbo, firstLine, callback) {
    $.getJSON("/ums/code/areaList", {}, function (data) {
        if (firstLine) {
            $(cbo).html(firstLine);
        } else {
            $(cbo).empty();
        }
        if (data) {
            for (var i = 0; i < data.length; i++) {
                $(cbo).append($("<option>").attr({"value": data[i].courtStdNo}).text(data[i].courtStdName));
            }
        }
        if (callback) {
            callback();
        }
    });
}

function loadRole(cbo, firstLine, callback) {
    $.getJSON("/ums/auth/role/listAll", {}, function (data) {
        if (firstLine) {
            $(cbo).html(firstLine);
        } else {
            $(cbo).empty();
        }
        if (data) {
            for (var i = 0; i < data.length; i++) {
                $(cbo).append($("<option>").attr({"value": data[i].id}).text(data[i].roleName));
            }
        }

        if (callback) {
            callback();
        }
    });
}