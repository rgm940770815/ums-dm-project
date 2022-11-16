package cn.net.withub.ums.entity.sbrmpsy;

import java.util.Date;

public class TRmpsyAchievementEvaluation {
    private String id;

    private String caseId;

    private String assessorId;

    private String assessorName;

    private String judgeId;

    private String judgeName;

    private Date time;

    private String year;

    private String status;

    private String moralScore;

    private String businessScore;

    private String attitudeScore;

    private String trialScore;

    private String discipline;

    private String totalScore;

    private String result;

    private String updateResultPeopleId;

    private Date updateResultTime;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String deleteUser;

    private String deleteFlag;

    private Integer dataLevel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId == null ? null : caseId.trim();
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

    public String getJudgeId() {
        return judgeId;
    }

    public void setJudgeId(String judgeId) {
        this.judgeId = judgeId == null ? null : judgeId.trim();
    }

    public String getJudgeName() {
        return judgeName;
    }

    public void setJudgeName(String judgeName) {
        this.judgeName = judgeName == null ? null : judgeName.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getMoralScore() {
        return moralScore;
    }

    public void setMoralScore(String moralScore) {
        this.moralScore = moralScore == null ? null : moralScore.trim();
    }

    public String getBusinessScore() {
        return businessScore;
    }

    public void setBusinessScore(String businessScore) {
        this.businessScore = businessScore == null ? null : businessScore.trim();
    }

    public String getAttitudeScore() {
        return attitudeScore;
    }

    public void setAttitudeScore(String attitudeScore) {
        this.attitudeScore = attitudeScore == null ? null : attitudeScore.trim();
    }

    public String getTrialScore() {
        return trialScore;
    }

    public void setTrialScore(String trialScore) {
        this.trialScore = trialScore == null ? null : trialScore.trim();
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline == null ? null : discipline.trim();
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore == null ? null : totalScore.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public String getUpdateResultPeopleId() {
        return updateResultPeopleId;
    }

    public void setUpdateResultPeopleId(String updateResultPeopleId) {
        this.updateResultPeopleId = updateResultPeopleId == null ? null : updateResultPeopleId.trim();
    }

    public Date getUpdateResultTime() {
        return updateResultTime;
    }

    public void setUpdateResultTime(Date updateResultTime) {
        this.updateResultTime = updateResultTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(String deleteUser) {
        this.deleteUser = deleteUser == null ? null : deleteUser.trim();
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag == null ? null : deleteFlag.trim();
    }

    public Integer getDataLevel() {
        return dataLevel;
    }

    public void setDataLevel(Integer dataLevel) {
        this.dataLevel = dataLevel;
    }
}