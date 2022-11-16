package cn.net.withub.ums.entity.sbrmpsy;

public class TRmpsyLocalCourtSetting {
    private String id;

    private String institutionNumber;

    private Integer maxCaseNumber;

    private String replyTimeLimit;

    private String sendTime;

    private String courtCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getInstitutionNumber() {
        return institutionNumber;
    }

    public void setInstitutionNumber(String institutionNumber) {
        this.institutionNumber = institutionNumber == null ? null : institutionNumber.trim();
    }

    public Integer getMaxCaseNumber() {
        return maxCaseNumber;
    }

    public void setMaxCaseNumber(Integer maxCaseNumber) {
        this.maxCaseNumber = maxCaseNumber;
    }

    public String getReplyTimeLimit() {
        return replyTimeLimit;
    }

    public void setReplyTimeLimit(String replyTimeLimit) {
        this.replyTimeLimit = replyTimeLimit == null ? null : replyTimeLimit.trim();
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime == null ? null : sendTime.trim();
    }

    public String getCourtCode() {
        return courtCode;
    }

    public void setCourtCode(String courtCode) {
        this.courtCode = courtCode == null ? null : courtCode.trim();
    }
}