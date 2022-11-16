package cn.net.withub.ums.entity.sbrmpsy;

import java.math.BigDecimal;
import java.util.Date;

public class TRmpsyTrainingClassRelevanceAssessor {
    private String id;

    private String trainingPrimaryKey;

    private String participantPrimaryKey;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String deleteUser;

    private String deleteFlag;

    private BigDecimal dataLevel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTrainingPrimaryKey() {
        return trainingPrimaryKey;
    }

    public void setTrainingPrimaryKey(String trainingPrimaryKey) {
        this.trainingPrimaryKey = trainingPrimaryKey == null ? null : trainingPrimaryKey.trim();
    }

    public String getParticipantPrimaryKey() {
        return participantPrimaryKey;
    }

    public void setParticipantPrimaryKey(String participantPrimaryKey) {
        this.participantPrimaryKey = participantPrimaryKey == null ? null : participantPrimaryKey.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(String deleteUser) {
        this.deleteUser = deleteUser == null ? null : deleteUser.trim();
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag == null ? null : deleteFlag.trim();
    }

    public BigDecimal getDataLevel() {
        return dataLevel;
    }

    public void setDataLevel(BigDecimal dataLevel) {
        this.dataLevel = dataLevel;
    }
}