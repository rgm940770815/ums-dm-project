package cn.net.withub.ums.entity.sbrmpsy;

public class TRmpsyWorkArea {
    private String institutionNumber;

    private String workAreaCode;

    public String getInstitutionNumber() {
        return institutionNumber;
    }

    public void setInstitutionNumber(String institutionNumber) {
        this.institutionNumber = institutionNumber == null ? null : institutionNumber.trim();
    }

    public String getWorkAreaCode() {
        return workAreaCode;
    }

    public void setWorkAreaCode(String workAreaCode) {
        this.workAreaCode = workAreaCode == null ? null : workAreaCode.trim();
    }
}