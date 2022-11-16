package cn.net.withub.ums.entity;

public class UmsUserInfoExternalCriteria extends UmsUserInfoViewCriteria {

    private Integer entrySrc;
    private Integer companyId;
    private String jurorWork;
    private String memberState;

    private Integer start;
    private Integer limit;
    public Integer getEntrySrc() {
        return entrySrc;
    }

    public void setEntrySrc(Integer entrySrc) {
        this.entrySrc = entrySrc;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getJurorWork() {
        return jurorWork;
    }

    public void setJurorWork(String jurorWork) {
        this.jurorWork = jurorWork;
    }

    public String getMemberState() {
        return memberState;
    }

    public void setMemberState(String memberState) {
        this.memberState = memberState;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
