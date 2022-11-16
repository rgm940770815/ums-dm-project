package cn.net.withub.ums.entity.sbrmpsy;

import java.util.Date;

public class TRmpsyStaggerRecord {
    private String id;

    private String assessorId;

    private Integer type;

    private Date staggerStartTime;

    private Date staggerEndTime;

    private String remark;

    private String detail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAssessorId() {
        return assessorId;
    }

    public void setAssessorId(String assessorId) {
        this.assessorId = assessorId == null ? null : assessorId.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getStaggerStartTime() {
        return staggerStartTime;
    }

    public void setStaggerStartTime(Date staggerStartTime) {
        this.staggerStartTime = staggerStartTime;
    }

    public Date getStaggerEndTime() {
        return staggerEndTime;
    }

    public void setStaggerEndTime(Date staggerEndTime) {
        this.staggerEndTime = staggerEndTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}