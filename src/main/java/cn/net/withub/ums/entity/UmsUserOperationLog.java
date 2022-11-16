package cn.net.withub.ums.entity;

import org.apache.struts2.json.annotations.JSON;

import java.util.Date;

public class UmsUserOperationLog {

    private String id;

    private String operationUsername;

    private String modifiedUserid;

    private Integer operationTypecode;

    private String operationTypedetail;

    private Date operationTime;

    private String operationIp;

    private String operationContent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperationUsername() {
        return operationUsername;
    }

    public void setOperationUsername(String operationUsername) {
        this.operationUsername = operationUsername;
    }

    public String getModifiedUserid() {
        return modifiedUserid;
    }

    public void setModifiedUserid(String modifiedUserid) {
        this.modifiedUserid = modifiedUserid;
    }

    public Integer getOperationTypecode() {
        return operationTypecode;
    }

    public void setOperationTypecode(Integer operationTypecode) {
        this.operationTypecode = operationTypecode;
    }

    public String getOperationTypedetail() {
        return operationTypedetail;
    }

    public void setOperationTypedetail(String operationTypedetail) {
        this.operationTypedetail = operationTypedetail;
    }

    @JSON(format="yyyy-MM-dd HH:mm:ss")
    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getOperationIp() {
        return operationIp;
    }

    public void setOperationIp(String operationIp) {
        this.operationIp = operationIp;
    }

    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
    }
}