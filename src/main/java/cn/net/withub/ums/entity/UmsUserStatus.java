package cn.net.withub.ums.entity;

import java.util.Objects;

/**
 * 人员身份实体
 */
public class UmsUserStatus {

    private String addIp;	 //添加ip地址
    private String addUser;	 //添加人
    private String createTime;	 //添加时间
    private Integer relationId;	 //关联id
    private String statusCode;	 //身份编码
    private Integer typeId;	 //编码表类型id
    private String userId;	 //用户id


    public String getAddIp() {
        return addIp;
    }

    public void setAddIp(String addIp) {
        this.addIp = addIp;
    }

    public String getAddUser() {
        return addUser;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UmsUserStatus that = (UmsUserStatus) o;
        return Objects.equals(addIp, that.addIp) &&
                Objects.equals(addUser, that.addUser) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(relationId, that.relationId) &&
                Objects.equals(statusCode, that.statusCode) &&
                Objects.equals(typeId, that.typeId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addIp, addUser, createTime, relationId, statusCode, typeId, userId);
    }
}
