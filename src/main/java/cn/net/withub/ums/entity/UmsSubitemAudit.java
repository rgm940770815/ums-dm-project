package cn.net.withub.ums.entity;

import java.util.Date;

public class UmsSubitemAudit {
    private String id;

    private String contentType;

    private String contentTypeText;

    private String examineType;

    private String examineTypeText;

    private String operatedUserId;

    private String operatedUserName;

    private Integer operatedUserCourtNo;

    private String operatedUserCourtText;

    private Date createTime;

    private Date createTimeStart;

    private Date createTimeEnd;

    private String creatorId;

    private String creatorName;

    private String auditorId;

    private String auditorName;

    private Date auditTime;

    private Integer auditStatus;

    private String auditOpinions;

    private Integer importStatus;

    private String shlx;

    public String getShlx() {
        return shlx;
    }

    public void setShlx(String shlx) {
        this.shlx = shlx;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType == null ? null : contentType.trim();
    }

    public String getContentTypeText() {
        return contentTypeText;
    }

    public void setContentTypeText(String contentTypeText) {
        this.contentTypeText = contentTypeText == null ? null : contentTypeText.trim();
    }

    public String getExamineType() {
        return examineType;
    }

    public void setExamineType(String examineType) {
        this.examineType = examineType == null ? null : examineType.trim();
    }

    public String getExamineTypeText() {
        return examineTypeText;
    }

    public void setExamineTypeText(String examineTypeText) {
        this.examineTypeText = examineTypeText == null ? null : examineTypeText.trim();
    }

    public String getOperatedUserId() {
        return operatedUserId;
    }

    public void setOperatedUserId(String operatedUserId) {
        this.operatedUserId = operatedUserId == null ? null : operatedUserId.trim();
    }

    public String getOperatedUserName() {
        return operatedUserName;
    }

    public void setOperatedUserName(String operatedUserName) {
        this.operatedUserName = operatedUserName == null ? null : operatedUserName.trim();
    }

    public Integer getOperatedUserCourtNo() {
        return operatedUserCourtNo;
    }

    public void setOperatedUserCourtNo(Integer operatedUserCourtNo) {
        this.operatedUserCourtNo = operatedUserCourtNo;
    }

    public String getOperatedUserCourtText() {
        return operatedUserCourtText;
    }

    public void setOperatedUserCourtText(String operatedUserCourtText) {
        this.operatedUserCourtText = operatedUserCourtText == null ? null : operatedUserCourtText.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Date createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
    }

    public String getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(String auditorId) {
        this.auditorId = auditorId == null ? null : auditorId.trim();
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName == null ? null : auditorName.trim();
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditOpinions() {
        return auditOpinions;
    }

    public void setAuditOpinions(String auditOpinions) {
        this.auditOpinions = auditOpinions == null ? null : auditOpinions.trim();
    }

    public Integer getImportStatus() {
        return importStatus;
    }

    public void setImportStatus(Integer importStatus) {
        this.importStatus = importStatus;
    }
}