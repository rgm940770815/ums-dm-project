package cn.net.withub.ums.entity.sbrmpsy;

public class TRmpsyAssessorJoinRecord {
    private String isJoinLogo;

    private String assessorId;

    private String caseNumber;

    private String isJoin;

    private String reason;

    private String otherReason;

    private String isAvoid;

    public String getIsJoinLogo() {
        return isJoinLogo;
    }

    public void setIsJoinLogo(String isJoinLogo) {
        this.isJoinLogo = isJoinLogo == null ? null : isJoinLogo.trim();
    }

    public String getAssessorId() {
        return assessorId;
    }

    public void setAssessorId(String assessorId) {
        this.assessorId = assessorId == null ? null : assessorId.trim();
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber == null ? null : caseNumber.trim();
    }

    public String getIsJoin() {
        return isJoin;
    }

    public void setIsJoin(String isJoin) {
        this.isJoin = isJoin == null ? null : isJoin.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getOtherReason() {
        return otherReason;
    }

    public void setOtherReason(String otherReason) {
        this.otherReason = otherReason == null ? null : otherReason.trim();
    }

    public String getIsAvoid() {
        return isAvoid;
    }

    public void setIsAvoid(String isAvoid) {
        this.isAvoid = isAvoid == null ? null : isAvoid.trim();
    }
}