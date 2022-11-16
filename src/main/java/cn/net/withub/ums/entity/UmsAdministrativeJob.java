package cn.net.withub.ums.entity;

import java.util.Date;

public class UmsAdministrativeJob {
    /**
     * 
     */
    private String id;

    /**
     * 
     */
    private String userId;

    /**
     * 
     */
    private Integer sortNo;

    /**
     * 
     */
    private Long oldId;

    /**
     * 
     */
    private Integer userNo;

    /**
     * 
     */
    private Integer courtNo;

    /**
     * 
     */
    private Integer nAssignType;

    /**
     * 
     */
    private String cUnit;

    /**
     * 
     */
    private String cDepartment;

    /**
     * 
     */
    private String nJob;

    /**
     * 
     */
    private Date dAssignDate;

    /**
     * 
     */
    private Date dApprovalDate;

    /**
     * 
     */
    private String cApprovalDocNo;

    /**
     * 
     */
    private String cApprovalUnit;

    /**
     * 
     */
    private Integer nAssignReason;

    /**
     * 
     */
    private Integer nPresentInfo;

    /**
     * 
     */
    private String recordId;

    /**
     * 
     */
    private String courtCode;

    /**
     * 
     */
    private String nJobReport;

    /**
     * 
     */
    private String nExtraJob;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Long getOldId() {
        return oldId;
    }

    public void setOldId(Long oldId) {
        this.oldId = oldId;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public Integer getCourtNo() {
        return courtNo;
    }

    public void setCourtNo(Integer courtNo) {
        this.courtNo = courtNo;
    }

    public Integer getnAssignType() {
        return nAssignType;
    }

    public void setnAssignType(Integer nAssignType) {
        this.nAssignType = nAssignType;
    }

    public String getcUnit() {
        return cUnit;
    }

    public void setcUnit(String cUnit) {
        this.cUnit = cUnit == null ? null : cUnit.trim();
    }

    public String getcDepartment() {
        return cDepartment;
    }

    public void setcDepartment(String cDepartment) {
        this.cDepartment = cDepartment == null ? null : cDepartment.trim();
    }

    public String getnJob() {
        return nJob;
    }

    public void setnJob(String nJob) {
        this.nJob = nJob == null ? null : nJob.trim();
    }

    public Date getdAssignDate() {
        return dAssignDate;
    }

    public void setdAssignDate(Date dAssignDate) {
        this.dAssignDate = dAssignDate;
    }

    public Date getdApprovalDate() {
        return dApprovalDate;
    }

    public void setdApprovalDate(Date dApprovalDate) {
        this.dApprovalDate = dApprovalDate;
    }

    public String getcApprovalDocNo() {
        return cApprovalDocNo;
    }

    public void setcApprovalDocNo(String cApprovalDocNo) {
        this.cApprovalDocNo = cApprovalDocNo == null ? null : cApprovalDocNo.trim();
    }

    public String getcApprovalUnit() {
        return cApprovalUnit;
    }

    public void setcApprovalUnit(String cApprovalUnit) {
        this.cApprovalUnit = cApprovalUnit == null ? null : cApprovalUnit.trim();
    }

    public Integer getnAssignReason() {
        return nAssignReason;
    }

    public void setnAssignReason(Integer nAssignReason) {
        this.nAssignReason = nAssignReason;
    }

    public Integer getnPresentInfo() {
        return nPresentInfo;
    }

    public void setnPresentInfo(Integer nPresentInfo) {
        this.nPresentInfo = nPresentInfo;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId == null ? null : recordId.trim();
    }

    public String getCourtCode() {
        return courtCode;
    }

    public void setCourtCode(String courtCode) {
        this.courtCode = courtCode == null ? null : courtCode.trim();
    }

    public String getnJobReport() {
        return nJobReport;
    }

    public void setnJobReport(String nJobReport) {
        this.nJobReport = nJobReport == null ? null : nJobReport.trim();
    }

    public String getnExtraJob() {
        return nExtraJob;
    }

    public void setnExtraJob(String nExtraJob) {
        this.nExtraJob = nExtraJob == null ? null : nExtraJob.trim();
    }
}