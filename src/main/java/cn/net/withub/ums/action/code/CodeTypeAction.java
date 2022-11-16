/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.action.code;

import cn.net.withub.ums.service.UmsCodeTypeService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 编码类型
 *
 * @author Diluka
 */
@Controller
@ParentPackage("codeBase")
@Scope("prototype")
@Namespace("/code")
@Results({
    @Result(name = "json", type = "json", params = {"root", "data"})
})
public class CodeTypeAction {

    @Autowired
    private UmsCodeTypeService codeTypeService;

    private Integer typeId;
    private Object data;

    /**
     * 某个编码类型的名称
     *
     * @return
     */
    @Action("codeTypeName")
    public String codeTypeName() {
        data = codeTypeService.codeTypeName(typeId);
        return "json";
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
