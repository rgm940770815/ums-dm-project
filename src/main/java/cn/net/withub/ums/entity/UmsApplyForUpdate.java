package cn.net.withub.ums.entity;

import java.util.Date;

public class UmsApplyForUpdate {
    private String id;

    private Integer type;

    private String userId;

    private String userName;

    private Integer userCourtNo;

    private String applyUserId;

    private String applyUserName;

    private Integer oldValidCode;

    private Integer newValidCode;

    private Date createTime;

    private Date updateTime;

    private String updateUserId;

    private String updateUserName;

    private Integer handle;

    private String remark;

    private String contactNumber;

    private Integer leaveReason;

    private Date leaveDate;

    private Integer leaveDestination;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserCourtNo() {
        return userCourtNo;
    }

    public void setUserCourtNo(Integer userCourtNo) {
        this.userCourtNo = userCourtNo;
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public Integer getOldValidCode() {
        return oldValidCode;
    }

    public void setOldValidCode(Integer oldValidCode) {
        this.oldValidCode = oldValidCode;
    }

    public Integer getNewValidCode() {
        return newValidCode;
    }

    public void setNewValidCode(Integer newValidCode) {
        this.newValidCode = newValidCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Integer getHandle() {
        return handle;
    }

    public void setHandle(Integer handle) {
        this.handle = handle;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Integer getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(Integer leaveReason) {
        this.leaveReason = leaveReason;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public Integer getLeaveDestination() {
        return leaveDestination;
    }

    public void setLeaveDestination(Integer leaveDestination) {
        this.leaveDestination = leaveDestination;
    }
}