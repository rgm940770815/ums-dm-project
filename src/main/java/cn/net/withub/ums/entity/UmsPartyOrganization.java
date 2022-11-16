package cn.net.withub.ums.entity;

import java.util.Date;

public class UmsPartyOrganization {
    private String id;

    private String institutionId;

    private String courtCode;

    private Integer courtNo;

    private Integer courtStdNo;

    private Integer deptNo;

    private String isBuild;

    private String orgType;

    private String orgName;

    private Integer orgPersonCount;

    private Integer orgCadrePersonCount;

    private String orgSituation;

    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getCourtCode() {
        return courtCode;
    }

    public void setCourtCode(String courtCode) {
        this.courtCode = courtCode;
    }

    public Integer getCourtNo() {
        return courtNo;
    }

    public void setCourtNo(Integer courtNo) {
        this.courtNo = courtNo;
    }

    public Integer getCourtStdNo() {
        return courtStdNo;
    }

    public void setCourtStdNo(Integer courtStdNo) {
        this.courtStdNo = courtStdNo;
    }

    public Integer getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    public String getIsBuild() {
        return isBuild;
    }

    public void setIsBuild(String isBuild) {
        this.isBuild = isBuild;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getOrgPersonCount() {
        return orgPersonCount;
    }

    public void setOrgPersonCount(Integer orgPersonCount) {
        this.orgPersonCount = orgPersonCount;
    }

    public Integer getOrgCadrePersonCount() {
        return orgCadrePersonCount;
    }

    public void setOrgCadrePersonCount(Integer orgCadrePersonCount) {
        this.orgCadrePersonCount = orgCadrePersonCount;
    }

    public String getOrgSituation() {
        return orgSituation;
    }

    public void setOrgSituation(String orgSituation) {
        this.orgSituation = orgSituation;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}