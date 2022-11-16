package cn.net.withub.ums.entity.sbrmpsy;

import java.util.Date;

public class TRmpsySelectRecord {
    private String id;

    private String caseNumber;

    private String caseName;

    private String courtName;

    private String courtCode;

    private String contractorId;

    private String contractorName;

    private Date trialTime;

    private Integer selectMode;

    private String trialPlaceName;

    private String trialPlaceCode;

    private String trialCourtName;

    private String trialCourtCode;

    private Integer selectNumber;

    private Integer alternativeNumber;

    private String specialty;

    private String national;

    private String caseType;

    private Integer isNearest;

    private Date selectTime;

    private String caseTypeName;

    private Date updateTime;

    private String updateUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName == null ? null : courtName.trim();
    }

    public String getCourtCode() {
        return courtCode;
    }

    public void setCourtCode(String courtCode) {
        this.courtCode = courtCode == null ? null : courtCode.trim();
    }

    public String getContractorId() {
        return contractorId;
    }

    public void setContractorId(String contractorId) {
        this.contractorId = contractorId == null ? null : contractorId.trim();
    }

    public String getContractorName() {
        return contractorName;
    }

    public void setContractorName(String contractorName) {
        this.contractorName = contractorName == null ? null : contractorName.trim();
    }

    public Date getTrialTime() {
        return trialTime;
    }

    public void setTrialTime(Date trialTime) {
        this.trialTime = trialTime;
    }

    public Integer getSelectMode() {
        return selectMode;
    }

    public void setSelectMode(Integer selectMode) {
        this.selectMode = selectMode;
    }

    public String getTrialPlaceName() {
        return trialPlaceName;
    }

    public void setTrialPlaceName(String trialPlaceName) {
        this.trialPlaceName = trialPlaceName == null ? null : trialPlaceName.trim();
    }

    public String getTrialPlaceCode() {
        return trialPlaceCode;
    }

    public void setTrialPlaceCode(String trialPlaceCode) {
        this.trialPlaceCode = trialPlaceCode == null ? null : trialPlaceCode.trim();
    }

    public String getTrialCourtName() {
        return trialCourtName;
    }

    public void setTrialCourtName(String trialCourtName) {
        this.trialCourtName = trialCourtName == null ? null : trialCourtName.trim();
    }

    public String getTrialCourtCode() {
        return trialCourtCode;
    }

    public void setTrialCourtCode(String trialCourtCode) {
        this.trialCourtCode = trialCourtCode == null ? null : trialCourtCode.trim();
    }

    public Integer getSelectNumber() {
        return selectNumber;
    }

    public void setSelectNumber(Integer selectNumber) {
        this.selectNumber = selectNumber;
    }

    public Integer getAlternativeNumber() {
        return alternativeNumber;
    }

    public void setAlternativeNumber(Integer alternativeNumber) {
        this.alternativeNumber = alternativeNumber;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty == null ? null : specialty.trim();
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national == null ? null : national.trim();
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType == null ? null : caseType.trim();
    }

    public Integer getIsNearest() {
        return isNearest;
    }

    public void setIsNearest(Integer isNearest) {
        this.isNearest = isNearest;
    }

    public Date getSelectTime() {
        return selectTime;
    }

    public void setSelectTime(Date selectTime) {
        this.selectTime = selectTime;
    }

    public String getCaseTypeName() {
        return caseTypeName;
    }

    public void setCaseTypeName(String caseTypeName) {
        this.caseTypeName = caseTypeName == null ? null : caseTypeName.trim();
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