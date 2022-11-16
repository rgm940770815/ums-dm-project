/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.action.external;

import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.entity.UmsExternalLabourContract;
import cn.net.withub.ums.entity.UmsExternalLabourContractCriteria;
import cn.net.withub.ums.entity.UmsUserInfoView;
import cn.net.withub.ums.service.UmsExternalLabourContractService;
import cn.net.withub.ums.util.StringTools;
import com.opensymphony.xwork2.ActionContext;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

/**
 *
 * @author Diluka
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/external/contract")
@Results({
    @Result(name = "json", type = "json", params = {"root", "data"})
})
public class LabourContractAction {

    @Autowired
    private UmsExternalLabourContractService labourContractService;

    private Object data;

    private UmsExternalLabourContract contract;
    private String id;

    private String userId;
    private Integer start = 0;
    private Integer pageIndex = 1;
    private Integer limit = 20;
    private String field;
    private String direction;

    @Action("save")
    public String save() {
        Map<String, Object> map = new HashMap<>();

        if (contract == null
                || contract.getUserId() == null
                || contract.getContractStart() == null
                || contract.getContractEnd() == null
                || contract.getContractNature() == null
                || contract.getContractCompany() == null) {
            map.put("success", false);
            return "json";
        }

        UmsUserInfoView user = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
        contract.setUpdateUser(user.getFullname() + "@" + user.getCourtNoText());
        contract.setUpdateTime(new Date());

        if (!StringUtils.isEmpty(contract.getId())) { //新增
            System.out.println("新增 \n\n\n\n\n");
            //contract.setId(id);

            labourContractService.insert(contract);
            map.put("success", true);
        } else { //修改
            System.out.println("修改 \n\n\n\n\n");
            labourContractService.update(contract);
            map.put("success", true);
        }

        data = map;
        return "json";
    }

    @Action("delete")
    public String delete() {
        Map<String, Object> map = new HashMap<>();
        data = map;
        int result = 0;

        result = labourContractService.deleteById(id);

        map.put("success", result > 0);
        map.put("result", result);
        return "json";
    }

    @Action("search")
    public String search() {


        Map<String, Object> map = new HashMap<>();
        data = map;

        RowBounds rowBounds;
        if (start != null && limit != null) {
            rowBounds = new RowBounds(start, limit);
        } else {
            rowBounds = RowBounds.DEFAULT;
        }

        if (!StringUtils.isEmpty(userId)) {
            UmsExternalLabourContractCriteria criteria = new UmsExternalLabourContractCriteria();
            if (!StringUtils.isEmpty(field)) {
                criteria.setOrderByClause(StringTools.camelOrPascalToUnderline(field) + " " + direction);
            }

            criteria.createCriteria().andUserIdEqualTo(userId);

            List<UmsExternalLabourContract> list = labourContractService.search(criteria, rowBounds);
            int total = labourContractService.count(criteria);
            map.put("success", list != null);
            map.put("rows", list);
            map.put("results", total);
        } else {
            map.put("success", false);
        }

        return "json";
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public UmsExternalLabourContract getContract() {
        return contract;
    }

    public void setContract(UmsExternalLabourContract contract) {
        this.contract = contract;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
    //</editor-fold>
}
