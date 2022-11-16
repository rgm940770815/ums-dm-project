package cn.net.withub.ums.entity;

public class UmsGbbzlxInfo {
    private String changeuuid;

    private String userId;

    private String userName;

    private Integer courtNo;

    private String courtText;

    private Integer depNo;

    private String depText;

    private String sqContent;

    private String sqTime;

    private String clrUserId;

    private String clrUserName;

    private String clrTime;

    private String clrReason;

    private Integer clrCourtNo;

    private String clrCourtText;

    private String state;

    private String sq_reason;

    public String getSq_reason() {
        return sq_reason;
    }

    public void setSq_reason(String sq_reason) {
        this.sq_reason = sq_reason;
    }

    public String getChangeuuid() {
        return changeuuid;
    }

    public void setChangeuuid(String changeuuid) {
        this.changeuuid = changeuuid == null ? null : changeuuid.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getCourtNo() {
        return courtNo;
    }

    public void setCourtNo(Integer courtNo) {
        this.courtNo = courtNo;
    }

    public String getCourtText() {
        return courtText;
    }

    public void setCourtText(String courtText) {
        this.courtText = courtText == null ? null : courtText.trim();
    }

    public Integer getDepNo() {
        return depNo;
    }

    public void setDepNo(Integer depNo) {
        this.depNo = depNo;
    }

    public String getDepText() {
        return depText;
    }

    public void setDepText(String depText) {
        this.depText = depText == null ? null : depText.trim();
    }

    public String getSqContent() {
        return sqContent;
    }

    public void setSqContent(String sqContent) {
        this.sqContent = sqContent == null ? null : sqContent.trim();
    }

    public String getSqTime() {
        return sqTime;
    }

    public void setSqTime(String sqTime) {
        this.sqTime = sqTime == null ? null : sqTime.trim();
    }

    public String getClrUserId() {
        return clrUserId;
    }

    public void setClrUserId(String clrUserId) {
        this.clrUserId = clrUserId == null ? null : clrUserId.trim();
    }

    public String getClrUserName() {
        return clrUserName;
    }

    public void setClrUserName(String clrUserName) {
        this.clrUserName = clrUserName == null ? null : clrUserName.trim();
    }

    public String getClrTime() {
        return clrTime;
    }

    public void setClrTime(String clrTime) {
        this.clrTime = clrTime == null ? null : clrTime.trim();
    }

    public String getClrReason() {
        return clrReason;
    }

    public void setClrReason(String clrReason) {
        this.clrReason = clrReason == null ? null : clrReason.trim();
    }

    public Integer getClrCourtNo() {
        return clrCourtNo;
    }

    public void setClrCourtNo(Integer clrCourtNo) {
        this.clrCourtNo = clrCourtNo;
    }

    public String getClrCourtText() {
        return clrCourtText;
    }

    public void setClrCourtText(String clrCourtText) {
        this.clrCourtText = clrCourtText == null ? null : clrCourtText.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}