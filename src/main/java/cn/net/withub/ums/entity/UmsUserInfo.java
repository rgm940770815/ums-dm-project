package cn.net.withub.ums.entity;

import java.util.Date;

public class UmsUserInfo {
    private String id;

    private Integer courtNo;

    private Integer userNo;

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

    private String phoneNumber;

    private String machineNumber;

    private String localAddress;

    private Integer province;

    private Integer city;

    private Integer area;

    private String postalAddress;

    private Integer cPsXlxw;

    private String sPsByyxjzy;

    private String sPsDwdz;

    private String sPsZw;

    private String sPsZj;

    private String sPsZyjszw;

    private Integer cPsLx;

    private Integer cPsZylb;

    private String sPsRmdw;

    private Date sPsRmrq;

    private String sPsPsybh;

    private Date sPsMzrq;

    private Integer cPsMzyy;

    private String ukbm;

    private String zytc;

    private String sPsZy;

    private Date beginTime;

    private Date endTime;

    private Integer isInfoComplete;

    private String salt;

    private Integer dataType;

    private String userId;

    private String officePhone;

    private String phone;

    private Integer yefg;

    private String extOfficePhone;

    private String extTax;

    private String extAddress;

    private String extZipCode;

    private String bzxx;

    private String job;

    private String personnelClassification;

    private String nationReport;

    private String educationBackgroundReport;

    private String administrationPositionReport;

    private String lawPositionReport;

    private String rankReport;

    private String politicalReport;

    private String partyOfficeReport;

    private Integer bzcy;

    private Integer c_ps_xlxw;
    private String s_ps_byyxjzy;
    private String s_ps_dwdz;
    private String s_ps_zw;
    private String s_ps_zj;
    private String s_ps_zyjszw;
    private Integer c_ps_lx;
    private Integer c_ps_zylb;
    private String s_ps_rmdw;
    private Date s_ps_rmrq;
    private String s_ps_psybh;
    private Date s_ps_mzrq;
    private Integer c_ps_mzyy;
    private String s_ps_zy;        //个人简历
    private Date begin_time; // 陪审员 任命开始时间
    private Date end_time; // 陪审员 任命结束时间
    private Integer is_info_complete;

    private Integer assessType;//考核类型

    public Date getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(Date begin_time) {
        this.begin_time = begin_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Integer getIs_info_complete() {
        return is_info_complete;
    }

    public void setIs_info_complete(Integer is_info_complete) {
        this.is_info_complete = is_info_complete;
    }

    public Integer getC_ps_xlxw() {
        return c_ps_xlxw;
    }

    public void setC_ps_xlxw(Integer c_ps_xlxw) {
        this.c_ps_xlxw = c_ps_xlxw;
    }

    public String getS_ps_byyxjzy() {
        return s_ps_byyxjzy;
    }

    public void setS_ps_byyxjzy(String s_ps_byyxjzy) {
        this.s_ps_byyxjzy = s_ps_byyxjzy == null ? null : s_ps_byyxjzy.trim();
    }

    public String getS_ps_dwdz() {
        return s_ps_dwdz;
    }

    public void setS_ps_dwdz(String s_ps_dwdz) {
        this.s_ps_dwdz = s_ps_dwdz == null ? null : s_ps_dwdz.trim();
    }

    public String getS_ps_zw() {
        return s_ps_zw;
    }

    public void setS_ps_zw(String s_ps_zw) {
        this.s_ps_zw = s_ps_zw == null ? null : s_ps_zw.trim();
    }

    public String getS_ps_zj() {
        return s_ps_zj;
    }

    public void setS_ps_zj(String s_ps_zj) {
        this.s_ps_zj = s_ps_zj == null ? null : s_ps_zj.trim();
    }

    public String getS_ps_zyjszw() {
        return s_ps_zyjszw;
    }

    public void setS_ps_zyjszw(String s_ps_zyjszw) {
        this.s_ps_zyjszw = s_ps_zyjszw == null ? null : s_ps_zyjszw.trim();
    }

    public Integer getC_ps_lx() {
        return c_ps_lx;
    }

    public void setC_ps_lx(Integer c_ps_lx) {
        this.c_ps_lx = c_ps_lx;
    }

    public Integer getC_ps_zylb() {
        return c_ps_zylb;
    }

    public void setC_ps_zylb(Integer c_ps_zylb) {
        this.c_ps_zylb = c_ps_zylb;
    }

    public String getS_ps_rmdw() {
        return s_ps_rmdw;
    }

    public void setS_ps_rmdw(String s_ps_rmdw) {
        this.s_ps_rmdw = s_ps_rmdw == null ? null : s_ps_rmdw.trim();
    }

    public Date getS_ps_rmrq() {
        return s_ps_rmrq;
    }

    public void setS_ps_rmrq(Date s_ps_rmrq) {
        this.s_ps_rmrq = s_ps_rmrq;
    }

    public String getS_ps_psybh() {
        return s_ps_psybh;
    }

    public void setS_ps_psybh(String s_ps_psybh) {
        this.s_ps_psybh = s_ps_psybh == null ? null : s_ps_psybh.trim();
    }

    public Date getS_ps_mzrq() {
        return s_ps_mzrq;
    }

    public void setS_ps_mzrq(Date s_ps_mzrq) {
        this.s_ps_mzrq = s_ps_mzrq;
    }

    public Integer getC_ps_mzyy() {
        return c_ps_mzyy;
    }

    public void setC_ps_mzyy(Integer c_ps_mzyy) {
        this.c_ps_mzyy = c_ps_mzyy;
    }

    public String getS_ps_zy() {
        return s_ps_zy;
    }

    public void setS_ps_zy(String s_ps_zy) {
        this.s_ps_zy = s_ps_zy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
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
        this.cCodeJg1 = cCodeJg1;
    }

    public String getcCodeJg2() {
        return cCodeJg2;
    }

    public void setcCodeJg2(String cCodeJg2) {
        this.cCodeJg2 = cCodeJg2;
    }

    public String getcCodeJg3() {
        return cCodeJg3;
    }

    public void setcCodeJg3(String cCodeJg3) {
        this.cCodeJg3 = cCodeJg3;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFormerName() {
        return formerName;
    }

    public void setFormerName(String formerName) {
        this.formerName = formerName;
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
        this.hometown = hometown;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
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
        this.idcard = idcard;
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
        this.formerUnit = formerUnit;
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
        this.courtCode = courtCode;
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
        this.ukNo = ukNo;
    }

    public String getWorkNo() {
        return workNo;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    public String getFankaNo() {
        return fankaNo;
    }

    public void setFankaNo(String fankaNo) {
        this.fankaNo = fankaNo;
    }

    public String getOfficialNo() {
        return officialNo;
    }

    public void setOfficialNo(String officialNo) {
        this.officialNo = officialNo;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
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
        this.deptOrgCode = deptOrgCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public Integer getCPsXlxw() {
        return cPsXlxw;
    }

    public void setCPsXlxw(Integer cPsXlxw) {
        this.cPsXlxw = cPsXlxw;
    }

    public String getSPsByyxjzy() {
        return sPsByyxjzy;
    }

    public void setSPsByyxjzy(String sPsByyxjzy) {
        this.sPsByyxjzy = sPsByyxjzy;
    }

    public String getSPsDwdz() {
        return sPsDwdz;
    }

    public void setSPsDwdz(String sPsDwdz) {
        this.sPsDwdz = sPsDwdz;
    }

    public String getSPsZw() {
        return sPsZw;
    }

    public void setSPsZw(String sPsZw) {
        this.sPsZw = sPsZw;
    }

    public String getSPsZj() {
        return sPsZj;
    }

    public void setSPsZj(String sPsZj) {
        this.sPsZj = sPsZj;
    }

    public String getSPsZyjszw() {
        return sPsZyjszw;
    }

    public void setSPsZyjszw(String sPsZyjszw) {
        this.sPsZyjszw = sPsZyjszw;
    }

    public Integer getCPsLx() {
        return cPsLx;
    }

    public void setCPsLx(Integer cPsLx) {
        this.cPsLx = cPsLx;
    }

    public Integer getCPsZylb() {
        return cPsZylb;
    }

    public void setCPsZylb(Integer cPsZylb) {
        this.cPsZylb = cPsZylb;
    }

    public String getSPsRmdw() {
        return sPsRmdw;
    }

    public void setSPsRmdw(String sPsRmdw) {
        this.sPsRmdw = sPsRmdw;
    }

    public Date getSPsRmrq() {
        return sPsRmrq;
    }

    public void setSPsRmrq(Date sPsRmrq) {
        this.sPsRmrq = sPsRmrq;
    }

    public String getSPsPsybh() {
        return sPsPsybh;
    }

    public void setSPsPsybh(String sPsPsybh) {
        this.sPsPsybh = sPsPsybh;
    }

    public Date getSPsMzrq() {
        return sPsMzrq;
    }

    public void setSPsMzrq(Date sPsMzrq) {
        this.sPsMzrq = sPsMzrq;
    }

    public Integer getCPsMzyy() {
        return cPsMzyy;
    }

    public void setCPsMzyy(Integer cPsMzyy) {
        this.cPsMzyy = cPsMzyy;
    }

    public String getUkbm() {
        return ukbm;
    }

    public void setUkbm(String ukbm) {
        this.ukbm = ukbm;
    }

    public String getZytc() {
        return zytc;
    }

    public void setZytc(String zytc) {
        this.zytc = zytc;
    }

    public String getSPsZy() {
        return sPsZy;
    }

    public void setSPsZy(String sPsZy) {
        this.sPsZy = sPsZy;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
        this.salt = salt;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getYefg() {
        return yefg;
    }

    public void setYefg(Integer yefg) {
        this.yefg = yefg;
    }

    public String getExtOfficePhone() {
        return extOfficePhone;
    }

    public void setExtOfficePhone(String extOfficePhone) {
        this.extOfficePhone = extOfficePhone;
    }

    public String getExtTax() {
        return extTax;
    }

    public void setExtTax(String extTax) {
        this.extTax = extTax;
    }

    public String getExtAddress() {
        return extAddress;
    }

    public void setExtAddress(String extAddress) {
        this.extAddress = extAddress;
    }

    public String getExtZipCode() {
        return extZipCode;
    }

    public void setExtZipCode(String extZipCode) {
        this.extZipCode = extZipCode;
    }

    public String getBzxx() {
        return bzxx;
    }

    public void setBzxx(String bzxx) {
        this.bzxx = bzxx;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPersonnelClassification() {
        return personnelClassification;
    }

    public void setPersonnelClassification(String personnelClassification) {
        this.personnelClassification = personnelClassification;
    }

    public String getNationReport() {
        return nationReport;
    }

    public void setNationReport(String nationReport) {
        this.nationReport = nationReport;
    }

    public String getEducationBackgroundReport() {
        return educationBackgroundReport;
    }

    public void setEducationBackgroundReport(String educationBackgroundReport) {
        this.educationBackgroundReport = educationBackgroundReport;
    }

    public String getAdministrationPositionReport() {
        return administrationPositionReport;
    }

    public void setAdministrationPositionReport(String administrationPositionReport) {
        this.administrationPositionReport = administrationPositionReport;
    }

    public String getLawPositionReport() {
        return lawPositionReport;
    }

    public void setLawPositionReport(String lawPositionReport) {
        this.lawPositionReport = lawPositionReport;
    }

    public String getRankReport() {
        return rankReport;
    }

    public void setRankReport(String rankReport) {
        this.rankReport = rankReport;
    }

    public String getPoliticalReport() {
        return politicalReport;
    }

    public void setPoliticalReport(String politicalReport) {
        this.politicalReport = politicalReport;
    }

    public String getPartyOfficeReport() {
        return partyOfficeReport;
    }

    public Integer getcPsXlxw() {
        return cPsXlxw;
    }

    public void setcPsXlxw(Integer cPsXlxw) {
        this.cPsXlxw = cPsXlxw;
    }

    public String getsPsByyxjzy() {
        return sPsByyxjzy;
    }

    public void setsPsByyxjzy(String sPsByyxjzy) {
        this.sPsByyxjzy = sPsByyxjzy;
    }

    public String getsPsDwdz() {
        return sPsDwdz;
    }

    public void setPartyOfficeReport(String partyOfficeReport) {
        this.partyOfficeReport = partyOfficeReport;
    }

    public Integer getBzcy() {
        return bzcy;
    }

    public void setBzcy(Integer bzcy) {
        this.bzcy = bzcy;
    }

    public void setsPsDwdz(String sPsDwdz) {
        this.sPsDwdz = sPsDwdz;
    }

    public String getsPsZw() {
        return sPsZw;
    }

    public void setsPsZw(String sPsZw) {
        this.sPsZw = sPsZw;
    }

    public String getsPsZj() {
        return sPsZj;
    }

    public void setsPsZj(String sPsZj) {
        this.sPsZj = sPsZj;
    }

    public String getsPsZyjszw() {
        return sPsZyjszw;
    }

    public void setsPsZyjszw(String sPsZyjszw) {
        this.sPsZyjszw = sPsZyjszw;
    }

    public Integer getcPsLx() {
        return cPsLx;
    }

    public void setcPsLx(Integer cPsLx) {
        this.cPsLx = cPsLx;
    }

    public Integer getcPsZylb() {
        return cPsZylb;
    }

    public void setcPsZylb(Integer cPsZylb) {
        this.cPsZylb = cPsZylb;
        }

    public String getsPsRmdw() {
        return sPsRmdw;
        }

    public void setsPsRmdw(String sPsRmdw) {
        this.sPsRmdw = sPsRmdw;
        }

    public Date getsPsRmrq() {
        return sPsRmrq;
    }

    public void setsPsRmrq(Date sPsRmrq) {
        this.sPsRmrq = sPsRmrq;
    }

    public String getsPsPsybh() {
        return sPsPsybh;
    }

    public void setsPsPsybh(String sPsPsybh) {
        this.sPsPsybh = sPsPsybh;
    }

    public Date getsPsMzrq() {
        return sPsMzrq;
    }

    public void setsPsMzrq(Date sPsMzrq) {
        this.sPsMzrq = sPsMzrq;
    }

    public Integer getcPsMzyy() {
        return cPsMzyy;
    }

    public void setcPsMzyy(Integer cPsMzyy) {
        this.cPsMzyy = cPsMzyy;
    }

    public String getsPsZy() {
        return sPsZy;
    }

    public void setsPsZy(String sPsZy) {
        this.sPsZy = sPsZy;
    }

    public Integer getAssessType() {
        return assessType;
    }

    public void setAssessType(Integer assessType) {
        this.assessType = assessType;
    }
}