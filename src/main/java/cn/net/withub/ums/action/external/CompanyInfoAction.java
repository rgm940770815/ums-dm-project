/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.action.external;

import cn.net.withub.ums.entity.UmsExternalCompanyInfo;
import cn.net.withub.ums.service.UmsExternalCompanyInfoService;
import java.util.ArrayList;
import java.util.List;
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
@Namespace("/external/company")
@Results({
    @Result(name = "json", type = "json", params = {"root", "data"})
})
public class CompanyInfoAction {

    @Autowired
    private UmsExternalCompanyInfoService externalCompanyInfoService;

    private List<UmsExternalCompanyInfo> companies;

    private Object data;

    private String emptyItem;

    @Action("findAll")
    public String findAll() {

        companies = externalCompanyInfoService.findAll();

        if (StringUtils.hasText(emptyItem)) {
            companies.add(0, new UmsExternalCompanyInfo() {
                {
                    setId(0);
                    setCompanyName(emptyItem);
                }
            });
        }

        data = ItemWrapper.fromList(companies);

        return "json";
    }

    public static class ItemWrapper {

        private UmsExternalCompanyInfo item;
        private String value;
        private String text;

        public ItemWrapper(UmsExternalCompanyInfo item) {
            this.item = item;
            this.value = item.getId().toString();
            this.text = item.getCompanyName();
        }

        public static List<ItemWrapper> fromList(List<UmsExternalCompanyInfo> list) {
            List<ItemWrapper> items = new ArrayList<>();

            for (UmsExternalCompanyInfo c : list) {
                items.add(new ItemWrapper(c));
            }

            return items;
        }

        public UmsExternalCompanyInfo getItem() {
            return item;
        }

        public void setItem(UmsExternalCompanyInfo item) {
            this.item = item;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

    }

    public List<UmsExternalCompanyInfo> getCompanies() {
        return companies;
    }

    public void setCompanies(List<UmsExternalCompanyInfo> companies) {
        this.companies = companies;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getEmptyItem() {
        return emptyItem;
    }

    public void setEmptyItem(String emptyItem) {
        this.emptyItem = emptyItem;
    }

}
