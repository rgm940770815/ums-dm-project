package cn.net.withub.ums.entity.sbrmpsy;

public class TRmpsyCourtRelationship {
    private String workAreaCode;

    private String updateTime;

    private String courtCode;

    private String tribunalCode;

    public String getWorkAreaCode() {
        return workAreaCode;
    }

    public void setWorkAreaCode(String workAreaCode) {
        this.workAreaCode = workAreaCode == null ? null : workAreaCode.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getCourtCode() {
        return courtCode;
    }

    public void setCourtCode(String courtCode) {
        this.courtCode = courtCode == null ? null : courtCode.trim();
    }

    public String getTribunalCode() {
        return tribunalCode;
    }

    public void setTribunalCode(String tribunalCode) {
        this.tribunalCode = tribunalCode == null ? null : tribunalCode.trim();
    }
}