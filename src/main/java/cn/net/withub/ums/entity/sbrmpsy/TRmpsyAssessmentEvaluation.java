package cn.net.withub.ums.entity.sbrmpsy;

import java.util.Date;

public class TRmpsyAssessmentEvaluation {
    private String id;

    private String recordTableId;

    private String assessorId;

    private String assessorName;

    private Integer professionalNumber;

    private String caseNumber;

    private String caseName;

    private Integer trialNumber;

    private Integer type;

    private Integer ethics;

    private Integer businessLevel;

    private Integer attitude;

    private Integer trialResults;

    private Integer discipline;

    private Integer score;

    private Date updateTime;

    private String updateUser;

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

    public String getAssessorId() {
        return assessorId;
    }

    public void setAssessorId(String assessorId) {
        this.assessorId = assessorId == null ? null : assessorId.trim();
    }

    public String getAssessorName() {
        return assessorName;
    }

    public void setAssessorName(String assessorName) {
        this.assessorName = assessorName == null ? null : assessorName.trim();
    }

    public Integer getProfessionalNumber() {
        return professionalNumber;
    }

    public void setProfessionalNumber(Integer professionalNumber) {
        this.professionalNumber = professionalNumber;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber == null ? null : caseNumber.trim();
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName == null ? null : caseName.trim();
    }

    public Integer getTrialNumber() {
        return trialNumber;
    }

    public void setTrialNumber(Integer trialNumber) {
        this.trialNumber = trialNumber;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getEthics() {
        return ethics;
    }

    public void setEthics(Integer ethics) {
        this.ethics = ethics;
    }

    public Integer getBusinessLevel() {
        return businessLevel;
    }

    public void setBusinessLevel(Integer businessLevel) {
        this.businessLevel = businessLevel;
    }

    public Integer getAttitude() {
        return attitude;
    }

    public void setAttitude(Integer attitude) {
        this.attitude = attitude;
    }

    public Integer getTrialResults() {
        return trialResults;
    }

    public void setTrialResults(Integer trialResults) {
        this.trialResults = trialResults;
    }

    public Integer getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Integer discipline) {
        this.discipline = discipline;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }
}