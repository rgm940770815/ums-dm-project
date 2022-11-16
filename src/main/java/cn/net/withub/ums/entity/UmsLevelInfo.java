package cn.net.withub.ums.entity;

import cn.net.withub.ums.subitemAudit.CodeType;
import cn.net.withub.ums.subitemAudit.FieldDescribe;
import cn.net.withub.ums.subitemAudit.FieldSort;

import java.util.Date;

public class UmsLevelInfo {
    private String id;

    private String userId;

    @FieldDescribe("序号")
    @FieldSort(14)
    private Integer sortNo;

    private Long oldId;

    private Integer userNo;

    private Integer courtNo;

    private String courtCode;

    private Integer nLevelType;

    @FieldDescribe("法官等级")
    @CodeType(117)
    @FieldSort(1)
    private String judgeLevel;

    private String marshalLevel;

    private String helperLevel;

    private String clerkLevel;

    @FieldDescribe("起算日期")
    @FieldSort(2)
    private Date dStartDate;

    @FieldDescribe("证书编号")
    @FieldSort(11)
    private String cCertNo;

    @FieldDescribe("是否为当前信息")
    @CodeType(68)
    @FieldSort(6)
    private Integer nPresentInfo;

    @FieldDescribe("批准文号")
    @FieldSort(13)
    private String cApprovalDocNo;

    @FieldDescribe("变动类别")
    @CodeType(21)
    @FieldSort(3)
    private Integer nAltType;

    @FieldDescribe("变动原因")
    @CodeType(79)
    @FieldSort(4)
    private Integer nAltReason;

    @FieldDescribe("批准单位")
    @FieldSort(12)
    private String cApprovalUnit;

    @FieldDescribe("变动依据")
    @FieldSort(5)
    private String cAltBasis;

    private String updateTime;

    @FieldDescribe("是否入额")
    @CodeType(68)
    @FieldSort(7)
    private String isYefg;

    @FieldDescribe("入额时间 ")
    @FieldSort(8)
    private Date yefgStartTime;

    @FieldDescribe("退出员额时间")
    @FieldSort(9)
    private Date yefgEndTime;

    @FieldDescribe("退出员额原因")
    @CodeType(104)
    @FieldSort(10)
    private String yefgEndReason;

    private Integer enterJobSequence;

    private Integer bailiffRank;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Long getOldId() {
        return oldId;
    }

    public void setOldId(Long oldId) {
        this.oldId = oldId;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public Integer getCourtNo() {
        return courtNo;
    }

    public void setCourtNo(Integer courtNo) {
        this.courtNo = courtNo;
    }

    public String getCourtCode() {
        return courtCode;
    }

    public void setCourtCode(String courtCode) {
        this.courtCode = courtCode == null ? null : courtCode.trim();
    }

    public Integer getnLevelType() {
        return nLevelType;
    }

    public void setnLevelType(Integer nLevelType) {
        this.nLevelType = nLevelType;
    }

    public String getJudgeLevel() {
        return judgeLevel;
    }

    public void setJudgeLevel(String judgeLevel) {
        this.judgeLevel = judgeLevel;
    }

    public String getMarshalLevel() {
        return marshalLevel;
    }

    public void setMarshalLevel(String marshalLevel) {
        this.marshalLevel = marshalLevel;
    }

    public String getHelperLevel() {
        return helperLevel;
    }

    public void setHelperLevel(String helperLevel) {
        this.helperLevel = helperLevel;
    }

    public String getClerkLevel() {
        return clerkLevel;
    }

    public void setClerkLevel(String clerkLevel) {
        this.clerkLevel = clerkLevel;
    }

    public Date getdStartDate() {
        return dStartDate;
    }

    public void setdStartDate(Date dStartDate) {
        this.dStartDate = dStartDate;
    }

    public String getcCertNo() {
        return cCertNo;
    }

    public void setcCertNo(String cCertNo) {
        this.cCertNo = cCertNo == null ? null : cCertNo.trim();
    }

    public Integer getnPresentInfo() {
        return nPresentInfo;
    }

    public void setnPresentInfo(Integer nPresentInfo) {
        this.nPresentInfo = nPresentInfo;
    }

    public String getcApprovalDocNo() {
        return cApprovalDocNo;
    }

    public void setcApprovalDocNo(String cApprovalDocNo) {
        this.cApprovalDocNo = cApprovalDocNo == null ? null : cApprovalDocNo.trim();
    }

    public Integer getnAltType() {
        return nAltType;
    }

    public void setnAltType(Integer nAltType) {
        this.nAltType = nAltType;
    }

    public Integer getnAltReason() {
        return nAltReason;
    }

    public void setnAltReason(Integer nAltReason) {
        this.nAltReason = nAltReason;
    }

    public String getcApprovalUnit() {
        return cApprovalUnit;
    }

    public void setcApprovalUnit(String cApprovalUnit) {
        this.cApprovalUnit = cApprovalUnit == null ? null : cApprovalUnit.trim();
    }

    public String getcAltBasis() {
        return cAltBasis;
    }

    public void setcAltBasis(String cAltBasis) {
        this.cAltBasis = cAltBasis == null ? null : cAltBasis.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getIsYefg() {
        return isYefg;
    }

    public void setIsYefg(String isYefg) {
        this.isYefg = isYefg == null ? null : isYefg.trim();
    }

    public Date getYefgStartTime() {
        return yefgStartTime;
    }

    public void setYefgStartTime(Date yefgStartTime) {
        this.yefgStartTime = yefgStartTime;
    }

    public Date getYefgEndTime() {
        return yefgEndTime;
    }

    public void setYefgEndTime(Date yefgEndTime) {
        this.yefgEndTime = yefgEndTime;
    }

    public String getYefgEndReason() {
        return yefgEndReason;
    }

    public void setYefgEndReason(String yefgEndReason) {
        this.yefgEndReason = yefgEndReason;
    }

    public Integer getEnterJobSequence() {
        return enterJobSequence;
    }

    public void setEnterJobSequence(Integer enterJobSequence) {
        this.enterJobSequence = enterJobSequence;
    }

    public Integer getBailiffRank() {
        return bailiffRank;
    }

    public void setBailiffRank(Integer bailiffRank) {
        this.bailiffRank = bailiffRank;
    }
}