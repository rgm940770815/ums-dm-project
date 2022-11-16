package cn.net.withub.ums.entity;

import java.util.Date;

public class UmsLogView {
    private String id;

    private Integer sortNo;

    private String opUserId;

    private String opUser;

    private Integer opCourtNo;

    private String userId;

    private Integer courtNo;

    private Date opTime;

    private Integer opType;

    private String opContent;

    private String opIp;

    private String entityId;

    private String opTypeText;

    private String courtNoText;

    private String opCourtNoText;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId == null ? null : opUserId.trim();
    }

    public String getOpUser() {
        return opUser;
    }

    public void setOpUser(String opUser) {
        this.opUser = opUser == null ? null : opUser.trim();
    }

    public Integer getOpCourtNo() {
        return opCourtNo;
    }

    public void setOpCourtNo(Integer opCourtNo) {
        this.opCourtNo = opCourtNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getCourtNo() {
        return courtNo;
    }

    public void setCourtNo(Integer courtNo) {
        this.courtNo = courtNo;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public Integer getOpType() {
        return opType;
    }

    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    public String getOpContent() {
        return opContent;
    }

    public void setOpContent(String opContent) {
        this.opContent = opContent == null ? null : opContent.trim();
    }

    public String getOpIp() {
        return opIp;
    }

    public void setOpIp(String opIp) {
        this.opIp = opIp == null ? null : opIp.trim();
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getOpTypeText() {
        return opTypeText;
    }

    public void setOpTypeText(String opTypeText) {
        this.opTypeText = opTypeText == null ? null : opTypeText.trim();
    }

    public String getCourtNoText() {
        return courtNoText;
    }

    public void setCourtNoText(String courtNoText) {
        this.courtNoText = courtNoText;
    }

    public String getOpCourtNoText() {
        return opCourtNoText;
    }

    public void setOpCourtNoText(String opCourtNoText) {
        this.opCourtNoText = opCourtNoText;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UmsLogView other = (UmsLogView) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSortNo() == null ? other.getSortNo() == null : this.getSortNo().equals(other.getSortNo()))
            && (this.getOpUserId() == null ? other.getOpUserId() == null : this.getOpUserId().equals(other.getOpUserId()))
            && (this.getOpUser() == null ? other.getOpUser() == null : this.getOpUser().equals(other.getOpUser()))
            && (this.getOpCourtNo() == null ? other.getOpCourtNo() == null : this.getOpCourtNo().equals(other.getOpCourtNo()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCourtNo() == null ? other.getCourtNo() == null : this.getCourtNo().equals(other.getCourtNo()))
            && (this.getOpTime() == null ? other.getOpTime() == null : this.getOpTime().equals(other.getOpTime()))
            && (this.getOpType() == null ? other.getOpType() == null : this.getOpType().equals(other.getOpType()))
            && (this.getOpContent() == null ? other.getOpContent() == null : this.getOpContent().equals(other.getOpContent()))
            && (this.getOpIp() == null ? other.getOpIp() == null : this.getOpIp().equals(other.getOpIp()))
            && (this.getEntityId() == null ? other.getEntityId() == null : this.getEntityId().equals(other.getEntityId()))
            && (this.getOpTypeText() == null ? other.getOpTypeText() == null : this.getOpTypeText().equals(other.getOpTypeText()))
            && (this.getCourtNoText() == null ? other.getCourtNoText() == null : this.getCourtNoText().equals(other.getCourtNoText()))
            && (this.getOpCourtNoText() == null ? other.getOpCourtNoText() == null : this.getOpCourtNoText().equals(other.getOpCourtNoText()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSortNo() == null) ? 0 : getSortNo().hashCode());
        result = prime * result + ((getOpUserId() == null) ? 0 : getOpUserId().hashCode());
        result = prime * result + ((getOpUser() == null) ? 0 : getOpUser().hashCode());
        result = prime * result + ((getOpCourtNo() == null) ? 0 : getOpCourtNo().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCourtNo() == null) ? 0 : getCourtNo().hashCode());
        result = prime * result + ((getOpTime() == null) ? 0 : getOpTime().hashCode());
        result = prime * result + ((getOpType() == null) ? 0 : getOpType().hashCode());
        result = prime * result + ((getOpContent() == null) ? 0 : getOpContent().hashCode());
        result = prime * result + ((getOpIp() == null) ? 0 : getOpIp().hashCode());
        result = prime * result + ((getEntityId() == null) ? 0 : getEntityId().hashCode());
        result = prime * result + ((getOpTypeText() == null) ? 0 : getOpTypeText().hashCode());
        result = prime * result + ((getCourtNoText() == null) ? 0 : getCourtNoText().hashCode());
        result = prime * result + ((getOpCourtNoText() == null) ? 0 : getOpCourtNoText().hashCode());
        return result;
    }
}