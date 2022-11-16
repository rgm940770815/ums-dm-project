package cn.net.withub.ums.entity;

import java.util.Date;

public class UmsInstitutionRewardPunish {
    private String id;

    private String institutionId;

    private String courtCode;

    private Integer courtNo;

    private Integer courtStdNo;

    private Integer deptNo;

    private String grantUnitCode;

    private String grantUnitNameAppend;

    private String rewardTypeCode;

    private String rewardNameAppend;

    private String punishName;

    private String recognitionField;

    private Date recordDate;

    private String approvalAuthorityLevel;

    private String reason;

    private Date insertTime;

    private Integer sortNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getCourtCode() {
        return courtCode;
    }

    public void setCourtCode(String courtCode) {
        this.courtCode = courtCode;
    }

    public Integer getCourtNo() {
        return courtNo;
    }

    public void setCourtNo(Integer courtNo) {
        this.courtNo = courtNo;
    }

    public Integer getCourtStdNo() {
        return courtStdNo;
    }

    public void setCourtStdNo(Integer courtStdNo) {
        this.courtStdNo = courtStdNo;
    }

    public Integer getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    public String getGrantUnitCode() {
        return grantUnitCode;
    }

    public void setGrantUnitCode(String grantUnitCode) {
        this.grantUnitCode = grantUnitCode;
    }

    public String getGrantUnitNameAppend() {
        return grantUnitNameAppend;
    }

    public void setGrantUnitNameAppend(String grantUnitNameAppend) {
        this.grantUnitNameAppend = grantUnitNameAppend;
    }

    public String getRewardTypeCode() {
        return rewardTypeCode;
    }

    public void setRewardTypeCode(String rewardTypeCode) {
        this.rewardTypeCode = rewardTypeCode;
    }

    public String getRewardNameAppend() {
        return rewardNameAppend;
    }

    public void setRewardNameAppend(String rewardNameAppend) {
        this.rewardNameAppend = rewardNameAppend;
    }

    public String getPunishName() {
        return punishName;
    }

    public void setPunishName(String punishName) {
        this.punishName = punishName;
    }

    public String getRecognitionField() {
        return recognitionField;
    }

    public void setRecognitionField(String recognitionField) {
        this.recognitionField = recognitionField;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getApprovalAuthorityLevel() {
        return approvalAuthorityLevel;
    }

    public void setApprovalAuthorityLevel(String approvalAuthorityLevel) {
        this.approvalAuthorityLevel = approvalAuthorityLevel;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }
}