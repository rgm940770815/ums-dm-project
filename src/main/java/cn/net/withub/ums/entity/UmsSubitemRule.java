package cn.net.withub.ums.entity;

import java.util.Date;

public class UmsSubitemRule {
    private String tableKey;

    private String tableText;

    private String filterRule;

    private String insertRule;

    private String updateRule;

    private Boolean enable;

    private String remark;

    private Date modifyTime;

    public String getTableKey() {
        return tableKey;
    }

    public void setTableKey(String tableKey) {
        this.tableKey = tableKey == null ? null : tableKey.trim();
    }

    public String getTableText() {
        return tableText;
    }

    public void setTableText(String tableText) {
        this.tableText = tableText == null ? null : tableText.trim();
    }

    public String getFilterRule() {
        return filterRule;
    }

    public void setFilterRule(String filterRule) {
        this.filterRule = filterRule == null ? null : filterRule.trim();
    }

    public String getInsertRule() {
        return insertRule;
    }

    public void setInsertRule(String insertRule) {
        this.insertRule = insertRule == null ? null : insertRule.trim();
    }

    public String getUpdateRule() {
        return updateRule;
    }

    public void setUpdateRule(String updateRule) {
        this.updateRule = updateRule == null ? null : updateRule.trim();
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}