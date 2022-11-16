package cn.net.withub.ums.entity;

import java.util.Date;

public class UmsCountDataConfig {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String checkName;

    /**
     * 
     */
    private Integer checkType;

    /**
     * 
     */
    private String queryCondition;

    /**
     * 
     */
    private String reflectCondition;

    /**
     * 
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName == null ? null : checkName.trim();
    }

    public Integer getCheckType() {
        return checkType;
    }

    public void setCheckType(Integer checkType) {
        this.checkType = checkType;
    }

    public String getQueryCondition() {
        return queryCondition;
    }

    public void setQueryCondition(String queryCondition) {
        this.queryCondition = queryCondition == null ? null : queryCondition.trim();
    }

    public String getReflectCondition() {
        return reflectCondition;
    }

    public void setReflectCondition(String reflectCondition) {
        this.reflectCondition = reflectCondition == null ? null : reflectCondition.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}