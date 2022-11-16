package cn.net.withub.ums.entity.sbrmpsy;

public class TRmpsySelectConditionSetting {
    private String id;

    private String institutionNumber;

    private String selectMode;

    private Integer alternativeNumber;

    private Integer national;

    private Integer caseType;

    private Integer isNearest;

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

    public String getSelectMode() {
        return selectMode;
    }

    public void setSelectMode(String selectMode) {
        this.selectMode = selectMode == null ? null : selectMode.trim();
    }

    public Integer getAlternativeNumber() {
        return alternativeNumber;
    }

    public void setAlternativeNumber(Integer alternativeNumber) {
        this.alternativeNumber = alternativeNumber;
    }

    public Integer getNational() {
        return national;
    }

    public void setNational(Integer national) {
        this.national = national;
    }

    public Integer getCaseType() {
        return caseType;
    }

    public void setCaseType(Integer caseType) {
        this.caseType = caseType;
    }

    public Integer getIsNearest() {
        return isNearest;
    }

    public void setIsNearest(Integer isNearest) {
        this.isNearest = isNearest;
    }
}