package cn.net.withub.ums.entity;

import cn.net.withub.ums.auth.AuthType;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class UmsUserInfoView {
    private String id;

    private Integer xtptId;

    private Integer courtNo;

    private Integer userNo;

    private Integer isInfoComplete;

    private String salt;

    private Integer cPsXlxw;

    private String phoneNumber;

    private String username;

    private String password;

    private String userCode;

    private Integer userType;

    private String cCodeJg1;

    private String cCodeJg2;

    private String cCodeJg3;

    private String fullname;

    private String formerName;

    private Integer gender;

    private Integer department;

    private Integer unicode;

    private Integer positionNature;

    private String hometown;

    private String birthplace;

    private Date birthday;

    private Integer physicalCondition;

    private Integer maritalStatus;

    private Integer nation;

    private String idcard;

    private Integer preparation;

    private Integer positionType;

    private Date positionTypeDate;

    private Integer assign;

    private Integer educationBackground;

    private Integer major;

    private Integer degree;

    private Date degreeDate;

    private Date workDate;

    private Date enterDate;

    private Integer proCert;

    private Date proCertDate;

    private Integer political;

    private Date politicalDate;

    private Date politicLawWorkDate;

    private Integer administrationPosition;

    private Date administrationPositionDate;

    private Integer lawPosition;

    private Date lawPositionDate;

    private Integer isParttimePresidingJudge;

    private Integer partyOffice;

    private Date partyOfficeDate;

    private Date lawyerDate;

    private Integer extraSeniority;

    private Integer deductionSeniority;

    private Integer beforeCourtWorkYear;

    private Integer rank;

    private Date rankDate;

    private Integer level;

    private Date levelDate;

    private Integer enterWay;

    private Integer enterSource;

    private Integer formerPost;

    private Integer formerRank;

    private String formerUnit;

    private Date verifyDate;

    private Integer leaveReason;

    private Date leaveDate;

    private Integer leaveDestination;

    private Integer sortNo;

    private Integer isValid;

    private Integer additionalDuration;

    private Date lawyerCertDate;

    private Integer servantLevel;

    private Date servantLevelDate;

    private Integer lawyerCert;

    private String courtCode;

    private Integer orderNo;

    private Integer courtStdNo;

    private String ukNo;

    private String workNo;

    private String fankaNo;

    private String officialNo;

    private String updateUser;

    private Date updateTime;

    private String deptOrgCode;

    private Integer yefg;

    private String userTypeText;

    private String courtNoText;

    private String genderText;

    private String departmentText;

    private String positionNatureText;

    private String physicalConditionText;

    private String maritalStatusText;

    private String nationText;

    private String preparationText;

    private String positionTypeText;

    private String assignText;

    private String educationBackgroundText;

    private String majorText;

    private String degreeText;

    private String proCertText;

    private String politicalText;

    private String administrationPositionText;

    private String lawPositionText;

    private String isParttimePresidingJudgeText;

    private String partyOfficeText;

    private String rankText;

    private String levelText;

    private String enterWayText;

    private String enterSourceText;

    private String formerPostText;

    private String formerRankText;

    private String leaveReasonText;

    private String leaveDestinationText;

    private String isValidText;

    private String servantLevelText;

    private String lawyerCertText;

    private String userId;

    private Date beginTime;

    private String localAddress;

    private String postalAddress;

    private String job;

    private String jurorEduText;

    private Long deptSortno;

    private Long deptLevel;

    private String personnelClassification;

    private String nationReport;

    private String educationBackgroundReport;

    private String administrationPositionReport;

    private String lawPositionReport;

    private String rankReport;

    private String politicalReport;

    private String partyOfficeReport;

    private String nationReportText;

    private String educationBackgroundReportText;

    private String administrationPositionReportText;

    private String lawPositionReportText;

    private String rankReportText;

    private String politicalReportText;

    private String partyOfficeReportText;

    private Integer bzcy;

    private String personnelClassificationText;

    private String whereFrom;

    private String sq_content;

    private String sq_time;

    private String changeUUID;

    private Integer assessType;//考核类型

    // 权限类型
    private AuthType authType;

    public AuthType getAuthType() {
        return authType;
    }

    public void setAuthType(AuthType authType) {
        this.authType = authType;
    }

    public String getChangeUUID() {
        return changeUUID;
    }

    public void setChangeUUID(String changeUUID) {
        this.changeUUID = changeUUID;
    }

    public String getWhereFrom() {
        return whereFrom;
    }

    public void setWhereFrom(String whereFrom) {
        this.whereFrom = whereFrom;
    }

    public String getSq_content() {
        return sq_content;
    }

    public void setSq_content(String sq_content) {
        this.sq_content = sq_content;
    }

    public String getSq_time() {
        return sq_time;
    }

    public void setSq_time(String sq_time) {
        this.sq_time = sq_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getXtptId() {
        return xtptId;
    }

    public void setXtptId(Integer xtptId) {
        this.xtptId = xtptId;
    }

    public Integer getCourtNo() {
        return courtNo;
    }

    public void setCourtNo(Integer courtNo) {
        this.courtNo = courtNo;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public Integer getIsInfoComplete() {
        return isInfoComplete;
    }

    public void setIsInfoComplete(Integer isInfoComplete) {
        this.isInfoComplete = isInfoComplete;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Integer getcPsXlxw() {
        return cPsXlxw;
    }

    public void setcPsXlxw(Integer cPsXlxw) {
        this.cPsXlxw = cPsXlxw;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getcCodeJg1() {
        return cCodeJg1;
    }

    public void setcCodeJg1(String cCodeJg1) {
        this.cCodeJg1 = cCodeJg1 == null ? null : cCodeJg1.trim();
    }

    public String getcCodeJg2() {
        return cCodeJg2;
    }

    public void setcCodeJg2(String cCodeJg2) {
        this.cCodeJg2 = cCodeJg2 == null ? null : cCodeJg2.trim();
    }

    public String getcCodeJg3() {
        return cCodeJg3;
    }

    public void setcCodeJg3(String cCodeJg3) {
        this.cCodeJg3 = cCodeJg3 == null ? null : cCodeJg3.trim();
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname == null ? null : fullname.trim();
    }

    public String getFormerName() {
        return formerName;
    }

    public void setFormerName(String formerName) {
        this.formerName = formerName == null ? null : formerName.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public Integer getUnicode() {
        return unicode;
    }

    public void setUnicode(Integer unicode) {
        this.unicode = unicode;
    }

    public Integer getPositionNature() {
        return positionNature;
    }

    public void setPositionNature(Integer positionNature) {
        this.positionNature = positionNature;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown == null ? null : hometown.trim();
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace == null ? null : birthplace.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getPhysicalCondition() {
        return physicalCondition;
    }

    public void setPhysicalCondition(Integer physicalCondition) {
        this.physicalCondition = physicalCondition;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getNation() {
        return nation;
    }

    public void setNation(Integer nation) {
        this.nation = nation;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public Integer getPreparation() {
        return preparation;
    }

    public void setPreparation(Integer preparation) {
        this.preparation = preparation;
    }

    public Integer getPositionType() {
        return positionType;
    }

    public void setPositionType(Integer positionType) {
        this.positionType = positionType;
    }

    public Date getPositionTypeDate() {
        return positionTypeDate;
    }

    public void setPositionTypeDate(Date positionTypeDate) {
        this.positionTypeDate = positionTypeDate;
    }

    public Integer getAssign() {
        return assign;
    }

    public void setAssign(Integer assign) {
        this.assign = assign;
    }

    public Integer getEducationBackground() {
        return educationBackground;
    }

    public void setEducationBackground(Integer educationBackground) {
        this.educationBackground = educationBackground;
    }

    public Integer getMajor() {
        return major;
    }

    public void setMajor(Integer major) {
        this.major = major;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public Date getDegreeDate() {
        return degreeDate;
    }

    public void setDegreeDate(Date degreeDate) {
        this.degreeDate = degreeDate;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public Date getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
    }

    public Integer getProCert() {
        return proCert;
    }

    public void setProCert(Integer proCert) {
        this.proCert = proCert;
    }

    public Date getProCertDate() {
        return proCertDate;
    }

    public void setProCertDate(Date proCertDate) {
        this.proCertDate = proCertDate;
    }

    public Integer getPolitical() {
        return political;
    }

    public void setPolitical(Integer political) {
        this.political = political;
    }

    public Date getPoliticalDate() {
        return politicalDate;
    }

    public void setPoliticalDate(Date politicalDate) {
        this.politicalDate = politicalDate;
    }

    public Date getPoliticLawWorkDate() {
        return politicLawWorkDate;
    }

    public void setPoliticLawWorkDate(Date politicLawWorkDate) {
        this.politicLawWorkDate = politicLawWorkDate;
    }

    public Integer getAdministrationPosition() {
        return administrationPosition;
    }

    public void setAdministrationPosition(Integer administrationPosition) {
        this.administrationPosition = administrationPosition;
    }

    public Date getAdministrationPositionDate() {
        return administrationPositionDate;
    }

    public void setAdministrationPositionDate(Date administrationPositionDate) {
        this.administrationPositionDate = administrationPositionDate;
    }

    public Integer getLawPosition() {
        return lawPosition;
    }

    public void setLawPosition(Integer lawPosition) {
        this.lawPosition = lawPosition;
    }

    public Date getLawPositionDate() {
        return lawPositionDate;
    }

    public void setLawPositionDate(Date lawPositionDate) {
        this.lawPositionDate = lawPositionDate;
    }

    public Integer getIsParttimePresidingJudge() {
        return isParttimePresidingJudge;
    }

    public void setIsParttimePresidingJudge(Integer isParttimePresidingJudge) {
        this.isParttimePresidingJudge = isParttimePresidingJudge;
    }

    public Integer getPartyOffice() {
        return partyOffice;
    }

    public void setPartyOffice(Integer partyOffice) {
        this.partyOffice = partyOffice;
    }

    public Date getPartyOfficeDate() {
        return partyOfficeDate;
    }

    public void setPartyOfficeDate(Date partyOfficeDate) {
        this.partyOfficeDate = partyOfficeDate;
    }

    public Date getLawyerDate() {
        return lawyerDate;
    }

    public void setLawyerDate(Date lawyerDate) {
        this.lawyerDate = lawyerDate;
    }

    public Integer getExtraSeniority() {
        return extraSeniority;
    }

    public void setExtraSeniority(Integer extraSeniority) {
        this.extraSeniority = extraSeniority;
    }

    public Integer getDeductionSeniority() {
        return deductionSeniority;
    }

    public void setDeductionSeniority(Integer deductionSeniority) {
        this.deductionSeniority = deductionSeniority;
    }

    public Integer getBeforeCourtWorkYear() {
        return beforeCourtWorkYear;
    }

    public void setBeforeCourtWorkYear(Integer beforeCourtWorkYear) {
        this.beforeCourtWorkYear = beforeCourtWorkYear;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Date getRankDate() {
        return rankDate;
    }

    public void setRankDate(Date rankDate) {
        this.rankDate = rankDate;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getLevelDate() {
        return levelDate;
    }

    public void setLevelDate(Date levelDate) {
        this.levelDate = levelDate;
    }

    public Integer getEnterWay() {
        return enterWay;
    }

    public void setEnterWay(Integer enterWay) {
        this.enterWay = enterWay;
    }

    public Integer getEnterSource() {
        return enterSource;
    }

    public void setEnterSource(Integer enterSource) {
        this.enterSource = enterSource;
    }

    public Integer getFormerPost() {
        return formerPost;
    }

    public void setFormerPost(Integer formerPost) {
        this.formerPost = formerPost;
    }

    public Integer getFormerRank() {
        return formerRank;
    }

    public void setFormerRank(Integer formerRank) {
        this.formerRank = formerRank;
    }

    public String getFormerUnit() {
        return formerUnit;
    }

    public void setFormerUnit(String formerUnit) {
        this.formerUnit = formerUnit == null ? null : formerUnit.trim();
    }

    public Date getVerifyDate() {
        return verifyDate;
    }

    public void setVerifyDate(Date verifyDate) {
        this.verifyDate = verifyDate;
    }

    public Integer getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(Integer leaveReason) {
        this.leaveReason = leaveReason;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public Integer getLeaveDestination() {
        return leaveDestination;
    }

    public void setLeaveDestination(Integer leaveDestination) {
        this.leaveDestination = leaveDestination;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getAdditionalDuration() {
        return additionalDuration;
    }

    public void setAdditionalDuration(Integer additionalDuration) {
        this.additionalDuration = additionalDuration;
    }

    public Date getLawyerCertDate() {
        return lawyerCertDate;
    }

    public void setLawyerCertDate(Date lawyerCertDate) {
        this.lawyerCertDate = lawyerCertDate;
    }

    public Integer getServantLevel() {
        return servantLevel;
    }

    public void setServantLevel(Integer servantLevel) {
        this.servantLevel = servantLevel;
    }

    public Date getServantLevelDate() {
        return servantLevelDate;
    }

    public void setServantLevelDate(Date servantLevelDate) {
        this.servantLevelDate = servantLevelDate;
    }

    public Integer getLawyerCert() {
        return lawyerCert;
    }

    public void setLawyerCert(Integer lawyerCert) {
        this.lawyerCert = lawyerCert;
    }

    public String getCourtCode() {
        return courtCode;
    }

    public void setCourtCode(String courtCode) {
        this.courtCode = courtCode == null ? null : courtCode.trim();
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getCourtStdNo() {
        return courtStdNo;
    }

    public void setCourtStdNo(Integer courtStdNo) {
        this.courtStdNo = courtStdNo;
    }

    public String getUkNo() {
        return ukNo;
    }

    public void setUkNo(String ukNo) {
        this.ukNo = ukNo == null ? null : ukNo.trim();
    }

    public String getWorkNo() {
        return workNo;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo == null ? null : workNo.trim();
    }

    public String getFankaNo() {
        return fankaNo;
    }

    public void setFankaNo(String fankaNo) {
        this.fankaNo = fankaNo == null ? null : fankaNo.trim();
    }

    public String getOfficialNo() {
        return officialNo;
    }

    public void setOfficialNo(String officialNo) {
        this.officialNo = officialNo == null ? null : officialNo.trim();
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

    public String getDeptOrgCode() {
        return deptOrgCode;
    }

    public void setDeptOrgCode(String deptOrgCode) {
        this.deptOrgCode = deptOrgCode == null ? null : deptOrgCode.trim();
    }

    public Integer getYefg() {
        return yefg;
    }

    public void setYefg(Integer yefg) {
        this.yefg = yefg;
    }

    public String getUserTypeText() {
        return userTypeText;
    }

    public void setUserTypeText(String userTypeText) {
        this.userTypeText = userTypeText == null ? null : userTypeText.trim();
    }

    public String getCourtNoText() {
        return courtNoText;
    }

    public void setCourtNoText(String courtNoText) {
        this.courtNoText = courtNoText == null ? null : courtNoText.trim();
    }

    public String getGenderText() {
        return genderText;
    }

    public void setGenderText(String genderText) {
        this.genderText = genderText == null ? null : genderText.trim();
    }

    public String getDepartmentText() {
        return departmentText;
    }

    public void setDepartmentText(String departmentText) {
        this.departmentText = departmentText == null ? null : departmentText.trim();
    }

    public String getPositionNatureText() {
        return positionNatureText;
    }

    public void setPositionNatureText(String positionNatureText) {
        this.positionNatureText = positionNatureText == null ? null : positionNatureText.trim();
    }

    public String getPhysicalConditionText() {
        return physicalConditionText;
    }

    public void setPhysicalConditionText(String physicalConditionText) {
        this.physicalConditionText = physicalConditionText == null ? null : physicalConditionText.trim();
    }

    public String getMaritalStatusText() {
        return maritalStatusText;
    }

    public void setMaritalStatusText(String maritalStatusText) {
        this.maritalStatusText = maritalStatusText == null ? null : maritalStatusText.trim();
    }

    public String getNationText() {
        return nationText;
    }

    public void setNationText(String nationText) {
        this.nationText = nationText == null ? null : nationText.trim();
    }

    public String getPreparationText() {
        return preparationText;
    }

    public void setPreparationText(String preparationText) {
        this.preparationText = preparationText == null ? null : preparationText.trim();
    }

    public String getPositionTypeText() {
        return positionTypeText;
    }

    public void setPositionTypeText(String positionTypeText) {
        this.positionTypeText = positionTypeText == null ? null : positionTypeText.trim();
    }

    public String getAssignText() {
        return assignText;
    }

    public void setAssignText(String assignText) {
        this.assignText = assignText == null ? null : assignText.trim();
    }

    public String getEducationBackgroundText() {
        return educationBackgroundText;
    }

    public void setEducationBackgroundText(String educationBackgroundText) {
        this.educationBackgroundText = educationBackgroundText == null ? null : educationBackgroundText.trim();
    }

    public String getMajorText() {
        return majorText;
    }

    public void setMajorText(String majorText) {
        this.majorText = majorText == null ? null : majorText.trim();
    }

    public String getDegreeText() {
        return degreeText;
    }

    public void setDegreeText(String degreeText) {
        this.degreeText = degreeText == null ? null : degreeText.trim();
    }

    public String getProCertText() {
        return proCertText;
    }

    public void setProCertText(String proCertText) {
        this.proCertText = proCertText == null ? null : proCertText.trim();
    }

    public String getPoliticalText() {
        return politicalText;
    }

    public void setPoliticalText(String politicalText) {
        this.politicalText = politicalText == null ? null : politicalText.trim();
    }

    public String getAdministrationPositionText() {
        return administrationPositionText;
    }

    public void setAdministrationPositionText(String administrationPositionText) {
        this.administrationPositionText = administrationPositionText == null ? null : administrationPositionText.trim();
    }

    public String getLawPositionText() {
        return lawPositionText;
    }

    public void setLawPositionText(String lawPositionText) {
        this.lawPositionText = lawPositionText == null ? null : lawPositionText.trim();
    }

    public String getIsParttimePresidingJudgeText() {
        return isParttimePresidingJudgeText;
    }

    public void setIsParttimePresidingJudgeText(String isParttimePresidingJudgeText) {
        this.isParttimePresidingJudgeText = isParttimePresidingJudgeText == null ? null : isParttimePresidingJudgeText.trim();
    }

    public String getPartyOfficeText() {
        return partyOfficeText;
    }

    public void setPartyOfficeText(String partyOfficeText) {
        this.partyOfficeText = partyOfficeText == null ? null : partyOfficeText.trim();
    }

    public String getRankText() {
        return rankText;
    }

    public void setRankText(String rankText) {
        this.rankText = rankText == null ? null : rankText.trim();
    }

    public String getLevelText() {
        return levelText;
    }

    public void setLevelText(String levelText) {
        this.levelText = levelText == null ? null : levelText.trim();
    }

    public String getEnterWayText() {
        return enterWayText;
    }

    public void setEnterWayText(String enterWayText) {
        this.enterWayText = enterWayText == null ? null : enterWayText.trim();
    }

    public String getEnterSourceText() {
        return enterSourceText;
    }

    public void setEnterSourceText(String enterSourceText) {
        this.enterSourceText = enterSourceText == null ? null : enterSourceText.trim();
    }

    public String getFormerPostText() {
        return formerPostText;
    }

    public void setFormerPostText(String formerPostText) {
        this.formerPostText = formerPostText == null ? null : formerPostText.trim();
    }

    public String getFormerRankText() {
        return formerRankText;
    }

    public void setFormerRankText(String formerRankText) {
        this.formerRankText = formerRankText == null ? null : formerRankText.trim();
    }

    public String getLeaveReasonText() {
        return leaveReasonText;
    }

    public void setLeaveReasonText(String leaveReasonText) {
        this.leaveReasonText = leaveReasonText == null ? null : leaveReasonText.trim();
    }

    public String getLeaveDestinationText() {
        return leaveDestinationText;
    }

    public void setLeaveDestinationText(String leaveDestinationText) {
        this.leaveDestinationText = leaveDestinationText == null ? null : leaveDestinationText.trim();
    }

    public String getIsValidText() {
        return isValidText;
    }

    public void setIsValidText(String isValidText) {
        this.isValidText = isValidText == null ? null : isValidText.trim();
    }

    public String getServantLevelText() {
        return servantLevelText;
    }

    public void setServantLevelText(String servantLevelText) {
        this.servantLevelText = servantLevelText == null ? null : servantLevelText.trim();
    }

    public String getLawyerCertText() {
        return lawyerCertText;
    }

    public void setLawyerCertText(String lawyerCertText) {
        this.lawyerCertText = lawyerCertText == null ? null : lawyerCertText.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress == null ? null : localAddress.trim();
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress == null ? null : postalAddress.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getJurorEduText() {
        return jurorEduText;
    }

    public void setJurorEduText(String jurorEduText) {
        this.jurorEduText = jurorEduText == null ? null : jurorEduText.trim();
    }

    public Long getDeptSortno() {
        return deptSortno;
    }

    public void setDeptSortno(Long deptSortno) {
        this.deptSortno = deptSortno;
    }

    public Long getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(Long deptLevel) {
        this.deptLevel = deptLevel;
    }

    public String getPersonnelClassification() {
        return personnelClassification;
    }

    public void setPersonnelClassification(String personnelClassification) {
        this.personnelClassification = personnelClassification == null ? null : personnelClassification.trim();
    }

    public String getNationReport() {
        return nationReport;
    }

    public void setNationReport(String nationReport) {
        this.nationReport = nationReport == null ? null : nationReport.trim();
    }

    public String getEducationBackgroundReport() {
        return educationBackgroundReport;
    }

    public void setEducationBackgroundReport(String educationBackgroundReport) {
        this.educationBackgroundReport = educationBackgroundReport == null ? null : educationBackgroundReport.trim();
    }

    public String getAdministrationPositionReport() {
        return administrationPositionReport;
    }

    public void setAdministrationPositionReport(String administrationPositionReport) {
        this.administrationPositionReport = administrationPositionReport == null ? null : administrationPositionReport.trim();
    }

    public String getLawPositionReport() {
        return lawPositionReport;
    }

    public void setLawPositionReport(String lawPositionReport) {
        this.lawPositionReport = lawPositionReport == null ? null : lawPositionReport.trim();
    }

    public String getRankReport() {
        return rankReport;
    }

    public void setRankReport(String rankReport) {
        this.rankReport = rankReport == null ? null : rankReport.trim();
    }

    public String getPoliticalReport() {
        return politicalReport;
    }

    public void setPoliticalReport(String politicalReport) {
        this.politicalReport = politicalReport == null ? null : politicalReport.trim();
    }

    public String getPartyOfficeReport() {
        return partyOfficeReport;
    }

    public void setPartyOfficeReport(String partyOfficeReport) {
        this.partyOfficeReport = partyOfficeReport == null ? null : partyOfficeReport.trim();
    }

    public String getNationReportText() {
        return nationReportText;
    }

    public void setNationReportText(String nationReportText) {
        this.nationReportText = nationReportText == null ? null : nationReportText.trim();
    }

    public String getEducationBackgroundReportText() {
        return educationBackgroundReportText;
    }

    public void setEducationBackgroundReportText(String educationBackgroundReportText) {
        this.educationBackgroundReportText = educationBackgroundReportText == null ? null : educationBackgroundReportText.trim();
    }

    public String getAdministrationPositionReportText() {
        return administrationPositionReportText;
    }

    public void setAdministrationPositionReportText(String administrationPositionReportText) {
        this.administrationPositionReportText = administrationPositionReportText == null ? null : administrationPositionReportText.trim();
    }

    public String getLawPositionReportText() {
        return lawPositionReportText;
    }

    public void setLawPositionReportText(String lawPositionReportText) {
        this.lawPositionReportText = lawPositionReportText == null ? null : lawPositionReportText.trim();
    }

    public String getRankReportText() {
        return rankReportText;
    }

    public void setRankReportText(String rankReportText) {
        this.rankReportText = rankReportText == null ? null : rankReportText.trim();
    }

    public String getPoliticalReportText() {
        return politicalReportText;
    }

    public void setPoliticalReportText(String politicalReportText) {
        this.politicalReportText = politicalReportText == null ? null : politicalReportText.trim();
    }

    public String getPartyOfficeReportText() {
        return partyOfficeReportText;
    }

    public void setPartyOfficeReportText(String partyOfficeReportText) {
        this.partyOfficeReportText = partyOfficeReportText == null ? null : partyOfficeReportText.trim();
    }

    public Integer getBzcy() {
        return bzcy;
    }

    public void setBzcy(Integer bzcy) {
        this.bzcy = bzcy;
    }

    public String getPersonnelClassificationText() {
        return personnelClassificationText;
    }

    public void setPersonnelClassificationText(String personnelClassificationText) {
        this.personnelClassificationText = personnelClassificationText;
    }

    public Integer getAssessType() {
        return assessType;
    }

    public void setAssessType(Integer assessType) {
        this.assessType = assessType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UmsUserInfoView that = (UmsUserInfoView) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(xtptId, that.xtptId) &&
                Objects.equals(courtNo, that.courtNo) &&
                Objects.equals(userNo, that.userNo) &&
                Objects.equals(isInfoComplete, that.isInfoComplete) &&
                Objects.equals(salt, that.salt) &&
                Objects.equals(cPsXlxw, that.cPsXlxw) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(userCode, that.userCode) &&
                Objects.equals(userType, that.userType) &&
                Objects.equals(cCodeJg1, that.cCodeJg1) &&
                Objects.equals(cCodeJg2, that.cCodeJg2) &&
                Objects.equals(cCodeJg3, that.cCodeJg3) &&
                Objects.equals(fullname, that.fullname) &&
                Objects.equals(formerName, that.formerName) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(department, that.department) &&
                Objects.equals(unicode, that.unicode) &&
                Objects.equals(positionNature, that.positionNature) &&
                Objects.equals(hometown, that.hometown) &&
                Objects.equals(birthplace, that.birthplace) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(physicalCondition, that.physicalCondition) &&
                Objects.equals(maritalStatus, that.maritalStatus) &&
                Objects.equals(nation, that.nation) &&
                Objects.equals(idcard, that.idcard) &&
                Objects.equals(preparation, that.preparation) &&
                Objects.equals(positionType, that.positionType) &&
                Objects.equals(positionTypeDate, that.positionTypeDate) &&
                Objects.equals(assign, that.assign) &&
                Objects.equals(educationBackground, that.educationBackground) &&
                Objects.equals(major, that.major) &&
                Objects.equals(degree, that.degree) &&
                Objects.equals(degreeDate, that.degreeDate) &&
                Objects.equals(workDate, that.workDate) &&
                Objects.equals(enterDate, that.enterDate) &&
                Objects.equals(proCert, that.proCert) &&
                Objects.equals(proCertDate, that.proCertDate) &&
                Objects.equals(political, that.political) &&
                Objects.equals(politicalDate, that.politicalDate) &&
                Objects.equals(politicLawWorkDate, that.politicLawWorkDate) &&
                Objects.equals(administrationPosition, that.administrationPosition) &&
                Objects.equals(administrationPositionDate, that.administrationPositionDate) &&
                Objects.equals(lawPosition, that.lawPosition) &&
                Objects.equals(lawPositionDate, that.lawPositionDate) &&
                Objects.equals(isParttimePresidingJudge, that.isParttimePresidingJudge) &&
                Objects.equals(partyOffice, that.partyOffice) &&
                Objects.equals(partyOfficeDate, that.partyOfficeDate) &&
                Objects.equals(lawyerDate, that.lawyerDate) &&
                Objects.equals(extraSeniority, that.extraSeniority) &&
                Objects.equals(deductionSeniority, that.deductionSeniority) &&
                Objects.equals(beforeCourtWorkYear, that.beforeCourtWorkYear) &&
                Objects.equals(rank, that.rank) &&
                Objects.equals(rankDate, that.rankDate) &&
                Objects.equals(level, that.level) &&
                Objects.equals(levelDate, that.levelDate) &&
                Objects.equals(enterWay, that.enterWay) &&
                Objects.equals(enterSource, that.enterSource) &&
                Objects.equals(formerPost, that.formerPost) &&
                Objects.equals(formerRank, that.formerRank) &&
                Objects.equals(formerUnit, that.formerUnit) &&
                Objects.equals(verifyDate, that.verifyDate) &&
                Objects.equals(leaveReason, that.leaveReason) &&
                Objects.equals(leaveDate, that.leaveDate) &&
                Objects.equals(leaveDestination, that.leaveDestination) &&
                Objects.equals(sortNo, that.sortNo) &&
                Objects.equals(isValid, that.isValid) &&
                Objects.equals(additionalDuration, that.additionalDuration) &&
                Objects.equals(lawyerCertDate, that.lawyerCertDate) &&
                Objects.equals(servantLevel, that.servantLevel) &&
                Objects.equals(servantLevelDate, that.servantLevelDate) &&
                Objects.equals(lawyerCert, that.lawyerCert) &&
                Objects.equals(courtCode, that.courtCode) &&
                Objects.equals(orderNo, that.orderNo) &&
                Objects.equals(courtStdNo, that.courtStdNo) &&
                Objects.equals(ukNo, that.ukNo) &&
                Objects.equals(workNo, that.workNo) &&
                Objects.equals(fankaNo, that.fankaNo) &&
                Objects.equals(officialNo, that.officialNo) &&
                Objects.equals(updateUser, that.updateUser) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(deptOrgCode, that.deptOrgCode) &&
                Objects.equals(yefg, that.yefg) &&
                Objects.equals(userTypeText, that.userTypeText) &&
                Objects.equals(courtNoText, that.courtNoText) &&
                Objects.equals(genderText, that.genderText) &&
                Objects.equals(departmentText, that.departmentText) &&
                Objects.equals(positionNatureText, that.positionNatureText) &&
                Objects.equals(physicalConditionText, that.physicalConditionText) &&
                Objects.equals(maritalStatusText, that.maritalStatusText) &&
                Objects.equals(nationText, that.nationText) &&
                Objects.equals(preparationText, that.preparationText) &&
                Objects.equals(positionTypeText, that.positionTypeText) &&
                Objects.equals(assignText, that.assignText) &&
                Objects.equals(educationBackgroundText, that.educationBackgroundText) &&
                Objects.equals(majorText, that.majorText) &&
                Objects.equals(degreeText, that.degreeText) &&
                Objects.equals(proCertText, that.proCertText) &&
                Objects.equals(politicalText, that.politicalText) &&
                Objects.equals(administrationPositionText, that.administrationPositionText) &&
                Objects.equals(lawPositionText, that.lawPositionText) &&
                Objects.equals(isParttimePresidingJudgeText, that.isParttimePresidingJudgeText) &&
                Objects.equals(partyOfficeText, that.partyOfficeText) &&
                Objects.equals(rankText, that.rankText) &&
                Objects.equals(levelText, that.levelText) &&
                Objects.equals(enterWayText, that.enterWayText) &&
                Objects.equals(enterSourceText, that.enterSourceText) &&
                Objects.equals(formerPostText, that.formerPostText) &&
                Objects.equals(formerRankText, that.formerRankText) &&
                Objects.equals(leaveReasonText, that.leaveReasonText) &&
                Objects.equals(leaveDestinationText, that.leaveDestinationText) &&
                Objects.equals(isValidText, that.isValidText) &&
                Objects.equals(servantLevelText, that.servantLevelText) &&
                Objects.equals(lawyerCertText, that.lawyerCertText) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(beginTime, that.beginTime) &&
                Objects.equals(localAddress, that.localAddress) &&
                Objects.equals(postalAddress, that.postalAddress) &&
                Objects.equals(job, that.job) &&
                Objects.equals(jurorEduText, that.jurorEduText) &&
                Objects.equals(deptSortno, that.deptSortno) &&
                Objects.equals(deptLevel, that.deptLevel) &&
                Objects.equals(personnelClassification, that.personnelClassification) &&
                Objects.equals(nationReport, that.nationReport) &&
                Objects.equals(educationBackgroundReport, that.educationBackgroundReport) &&
                Objects.equals(administrationPositionReport, that.administrationPositionReport) &&
                Objects.equals(lawPositionReport, that.lawPositionReport) &&
                Objects.equals(rankReport, that.rankReport) &&
                Objects.equals(politicalReport, that.politicalReport) &&
                Objects.equals(partyOfficeReport, that.partyOfficeReport) &&
                Objects.equals(nationReportText, that.nationReportText) &&
                Objects.equals(educationBackgroundReportText, that.educationBackgroundReportText) &&
                Objects.equals(administrationPositionReportText, that.administrationPositionReportText) &&
                Objects.equals(lawPositionReportText, that.lawPositionReportText) &&
                Objects.equals(rankReportText, that.rankReportText) &&
                Objects.equals(politicalReportText, that.politicalReportText) &&
                Objects.equals(partyOfficeReportText, that.partyOfficeReportText) &&
                Objects.equals(bzcy, that.bzcy) &&
                Objects.equals(personnelClassificationText, that.personnelClassificationText) &&
                Objects.equals(whereFrom, that.whereFrom) &&
                Objects.equals(sq_content, that.sq_content) &&
                Objects.equals(sq_time, that.sq_time) &&
                Objects.equals(changeUUID, that.changeUUID) &&
                Objects.equals(assessType, that.assessType) &&
                authType == that.authType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, xtptId, courtNo, userNo, isInfoComplete, salt, cPsXlxw, phoneNumber, username, password, userCode, userType, cCodeJg1, cCodeJg2, cCodeJg3, fullname, formerName, gender, department, unicode, positionNature, hometown, birthplace, birthday, physicalCondition, maritalStatus, nation, idcard, preparation, positionType, positionTypeDate, assign, educationBackground, major, degree, degreeDate, workDate, enterDate, proCert, proCertDate, political, politicalDate, politicLawWorkDate, administrationPosition, administrationPositionDate, lawPosition, lawPositionDate, isParttimePresidingJudge, partyOffice, partyOfficeDate, lawyerDate, extraSeniority, deductionSeniority, beforeCourtWorkYear, rank, rankDate, level, levelDate, enterWay, enterSource, formerPost, formerRank, formerUnit, verifyDate, leaveReason, leaveDate, leaveDestination, sortNo, isValid, additionalDuration, lawyerCertDate, servantLevel, servantLevelDate, lawyerCert, courtCode, orderNo, courtStdNo, ukNo, workNo, fankaNo, officialNo, updateUser, updateTime, deptOrgCode, yefg, userTypeText, courtNoText, genderText, departmentText, positionNatureText, physicalConditionText, maritalStatusText, nationText, preparationText, positionTypeText, assignText, educationBackgroundText, majorText, degreeText, proCertText, politicalText, administrationPositionText, lawPositionText, isParttimePresidingJudgeText, partyOfficeText, rankText, levelText, enterWayText, enterSourceText, formerPostText, formerRankText, leaveReasonText, leaveDestinationText, isValidText, servantLevelText, lawyerCertText, userId, beginTime, localAddress, postalAddress, job, jurorEduText, deptSortno, deptLevel, personnelClassification, nationReport, educationBackgroundReport, administrationPositionReport, lawPositionReport, rankReport, politicalReport, partyOfficeReport, nationReportText, educationBackgroundReportText, administrationPositionReportText, lawPositionReportText, rankReportText, politicalReportText, partyOfficeReportText, bzcy, personnelClassificationText, whereFrom, sq_content, sq_time, changeUUID, assessType, authType);
    }
}