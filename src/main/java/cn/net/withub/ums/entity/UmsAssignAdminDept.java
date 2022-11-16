package cn.net.withub.ums.entity;

import java.util.Date;

public class UmsAssignAdminDept {
    /**
     * 
     */
    private String id;

    /**
     * 人员id
     */
    private Integer userId;

    /**
     * 人员uuid
     */
    private String userUuid;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 分管法院编码
     */
    private String adminCourtCode;

    /**
     * 分管法院名称
     */
    private String adminCourtName;

    /**
     * 分管部门id
     */
    private String adminDeptId;

    /**
     * 分管部门名称
     */
    private String adminDeptName;

    /**
     * 创建人id
     */
    private String createUserUuid;

    /**
     * 创建人名称
     */
    private String createUserName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否删除  0 未删除  1 删除
     */
    private Integer isDelete;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人uuid
     */
    private String updateUserId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid == null ? null : userUuid.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getAdminCourtCode() {
        return adminCourtCode;
    }

    public void setAdminCourtCode(String adminCourtCode) {
        this.adminCourtCode = adminCourtCode == null ? null : adminCourtCode.trim();
    }

    public String getAdminCourtName() {
        return adminCourtName;
    }

    public void setAdminCourtName(String adminCourtName) {
        this.adminCourtName = adminCourtName == null ? null : adminCourtName.trim();
    }

    public String getAdminDeptId() {
        return adminDeptId;
    }

    public void setAdminDeptId(String adminDeptId) {
        this.adminDeptId = adminDeptId == null ? null : adminDeptId.trim();
    }

    public String getAdminDeptName() {
        return adminDeptName;
    }

    public void setAdminDeptName(String adminDeptName) {
        this.adminDeptName = adminDeptName == null ? null : adminDeptName.trim();
    }

    public String getCreateUserUuid() {
        return createUserUuid;
    }

    public void setCreateUserUuid(String createUserUuid) {
        this.createUserUuid = createUserUuid == null ? null : createUserUuid.trim();
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }
}