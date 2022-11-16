package cn.net.withub.ums.entity;

import java.util.Date;

public class UmsDepartment extends UmsDepartmentKey {
    private String courtCode;

    private Integer courtStdNo;

    private String orgCode;

    private Integer unicode;

    private Integer level;

    private String deptName;

    private String deptStName;

    private String levelCode;

    private Integer officerPosition;

    private Integer numOfStaff;

    private Integer isValid;

    private Integer isVisible;

    private Integer sortNo;

    private Integer dataType;

    private Integer orgType;

    private String courtShortName;

    private String id;

    private String institutionCode;

    private String courtLevel;

    private String deptType;

    private String isPeples;

    private String isLeaders;

    private String deptProperty;

    private Date createTime;

    private Date updateTime;

    private String parentId;

    private String header;

    private String description;

    private Integer assessSerial;

    public String getCourtCode() {
        return courtCode;
    }

    public void setCourtCode(String courtCode) {
        this.courtCode = courtCode == null ? null : courtCode.trim();
    }

    public Integer getCourtStdNo() {
        return courtStdNo;
    }

    public void setCourtStdNo(Integer courtStdNo) {
        this.courtStdNo = courtStdNo;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public Integer getUnicode() {
        return unicode;
    }

    public void setUnicode(Integer unicode) {
        this.unicode = unicode;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public String getDeptStName() {
        return deptStName;
    }

    public void setDeptStName(String deptStName) {
        this.deptStName = deptStName == null ? null : deptStName.trim();
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode == null ? null : levelCode.trim();
    }

    public Integer getOfficerPosition() {
        return officerPosition;
    }

    public void setOfficerPosition(Integer officerPosition) {
        this.officerPosition = officerPosition;
    }

    public Integer getNumOfStaff() {
        return numOfStaff;
    }

    public void setNumOfStaff(Integer numOfStaff) {
        this.numOfStaff = numOfStaff;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Integer isVisible) {
        this.isVisible = isVisible;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    public String getCourtShortName() {
        return courtShortName;
    }

    public void setCourtShortName(String courtShortName) {
        this.courtShortName = courtShortName == null ? null : courtShortName.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode == null ? null : institutionCode.trim();
    }

    public String getCourtLevel() {
        return courtLevel;
    }

    public void setCourtLevel(String courtLevel) {
        this.courtLevel = courtLevel == null ? null : courtLevel.trim();
    }

    public String getDeptType() {
        return deptType;
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType == null ? null : deptType.trim();
    }

    public String getIsPeples() {
        return isPeples;
    }

    public void setIsPeples(String isPeples) {
        this.isPeples = isPeples == null ? null : isPeples.trim();
    }

    public String getIsLeaders() {
        return isLeaders;
    }

    public void setIsLeaders(String isLeaders) {
        this.isLeaders = isLeaders == null ? null : isLeaders.trim();
    }

    public String getDeptProperty() {
        return deptProperty;
    }

    public void setDeptProperty(String deptProperty) {
        this.deptProperty = deptProperty == null ? null : deptProperty.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAssessSerial() {
        return assessSerial;
    }

    public void setAssessSerial(Integer assessSerial) {
        this.assessSerial = assessSerial;
    }
}