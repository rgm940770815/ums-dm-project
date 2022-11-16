package cn.net.withub.ums.entity.sbrmpsy;

public class TRmpsyPersonInfoRecord {
    private String id;

    private String recordTableId;

    private String recordLogId;

    private String caseNumber;

    private String assessorNumber;

    private String conditionNumber;

    private Integer type;

    private Integer replyStatus;

    private Integer openCourt;

    private String msgId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRecordTableId() {
        return recordTableId;
    }

    public void setRecordTableId(String recordTableId) {
        this.recordTableId = recordTableId == null ? null : recordTableId.trim();
    }

    public String getRecordLogId() {
        return recordLogId;
    }

    public void setRecordLogId(String recordLogId) {
        this.recordLogId = recordLogId == null ? null : recordLogId.trim();
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber == null ? null : caseNumber.trim();
    }

    public String getAssessorNumber() {
        return assessorNumber;
    }

    public void setAssessorNumber(String assessorNumber) {
        this.assessorNumber = assessorNumber == null ? null : assessorNumber.trim();
    }

    public String getConditionNumber() {
        return conditionNumber;
    }

    public void setConditionNumber(String conditionNumber) {
        this.conditionNumber = conditionNumber == null ? null : conditionNumber.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(Integer replyStatus) {
        this.replyStatus = replyStatus;
    }

    public Integer getOpenCourt() {
        return openCourt;
    }

    public void setOpenCourt(Integer openCourt) {
        this.openCourt = openCourt;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId == null ? null : msgId.trim();
    }
}