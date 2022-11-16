package cn.net.withub.ums.webService.interior;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "personInfo")
public class PersonInfo implements Serializable {


    private static final long serialVersionUID = 1675789279977168747L;
    private String id; //id
    private String xtptId; //系统平台id
    private String fullname; //姓名
    private Integer courtNo; //法院编码(如:2950)
    private String fydm; //法院代码(如:M00)
    private String courtNoText;//法院名称
    private String orgCode;//部门代码(三位数)
    private Integer department; //部门编码
    private String departmentText; //部门名称
    private String politicalDate; //入职时间
    private Integer lawPosition;// 法律职务编码
    private String lawPositionReportText;//法律职务名称
    private Integer gender; //性别
    private Integer rank;//行政职级编码
    private String rankReportText;//行政职级名称
    private Integer administrationPosition;//行政职务编码
    private String administrationPositionReportText; //行政职务名称
    private Integer political;//政治面貌编码
    private String politicalReportText;//政治面貌名称
    private Integer level;// 法官等级编码
    private String levelText;//法官等级名称
    private Integer yefg; //员额法官(1:是)
    private String personnelClassificationText;//人员分类名称
    private Integer personnelClassification;//人员分类编码


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXtptId() {
        return xtptId;
    }

    public void setXtptId(String xtptId) {
        this.xtptId = xtptId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getCourtNo() {
        return courtNo;
    }

    public void setCourtNo(Integer courtNo) {
        this.courtNo = courtNo;
    }

    public String getFydm() {
        return fydm;
    }

    public void setFydm(String fydm) {
        this.fydm = fydm;
    }

    public String getCourtNoText() {
        return courtNoText;
    }

    public void setCourtNoText(String courtNoText) {
        this.courtNoText = courtNoText;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public String getDepartmentText() {
        return departmentText;
    }

    public void setDepartmentText(String departmentText) {
        this.departmentText = departmentText;
    }

    public String getPoliticalDate() {
        return politicalDate;
    }

    public void setPoliticalDate(String politicalDate) {
        this.politicalDate = politicalDate;
    }

    public Integer getLawPosition() {
        return lawPosition;
    }

    public void setLawPosition(Integer lawPosition) {
        this.lawPosition = lawPosition;
    }

    public String getLawPositionReportText() {
        return lawPositionReportText;
    }

    public void setLawPositionReportText(String lawPositionReportText) {
        this.lawPositionReportText = lawPositionReportText;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getRankReportText() {
        return rankReportText;
    }

    public void setRankReportText(String rankReportText) {
        this.rankReportText = rankReportText;
    }

    public Integer getAdministrationPosition() {
        return administrationPosition;
    }

    public void setAdministrationPosition(Integer administrationPosition) {
        this.administrationPosition = administrationPosition;
    }

    public String getAdministrationPositionReportText() {
        return administrationPositionReportText;
    }

    public void setAdministrationPositionReportText(String administrationPositionReportText) {
        this.administrationPositionReportText = administrationPositionReportText;
    }

    public Integer getPolitical() {
        return political;
    }

    public void setPolitical(Integer political) {
        this.political = political;
    }

    public String getPoliticalReportText() {
        return politicalReportText;
    }

    public void setPoliticalReportText(String politicalReportText) {
        this.politicalReportText = politicalReportText;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getLevelText() {
        return levelText;
    }

    public void setLevelText(String levelText) {
        this.levelText = levelText;
    }

    public Integer getYefg() {
        return yefg;
    }

    public void setYefg(Integer yefg) {
        this.yefg = yefg;
    }

    public String getPersonnelClassificationText() {
        return personnelClassificationText;
    }

    public void setPersonnelClassificationText(String personnelClassificationText) {
        this.personnelClassificationText = personnelClassificationText;
    }

    public Integer getPersonnelClassification() {
        return personnelClassification;
    }

    public void setPersonnelClassification(Integer personnelClassification) {
        this.personnelClassification = personnelClassification;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonInfo that = (PersonInfo) o;
        return Objects.equals(id, that.id) && Objects.equals(xtptId, that.xtptId) && Objects.equals(fullname, that.fullname) && Objects.equals(courtNo, that.courtNo) && Objects.equals(fydm, that.fydm) && Objects.equals(courtNoText, that.courtNoText) && Objects.equals(orgCode, that.orgCode) && Objects.equals(department, that.department) && Objects.equals(departmentText, that.departmentText) && Objects.equals(politicalDate, that.politicalDate) && Objects.equals(lawPosition, that.lawPosition) && Objects.equals(lawPositionReportText, that.lawPositionReportText) && Objects.equals(gender, that.gender) && Objects.equals(rank, that.rank) && Objects.equals(rankReportText, that.rankReportText) && Objects.equals(administrationPosition, that.administrationPosition) && Objects.equals(administrationPositionReportText, that.administrationPositionReportText) && Objects.equals(political, that.political) && Objects.equals(politicalReportText, that.politicalReportText) && Objects.equals(level, that.level) && Objects.equals(levelText, that.levelText) && Objects.equals(yefg, that.yefg) && Objects.equals(personnelClassificationText, that.personnelClassificationText) && Objects.equals(personnelClassification, that.personnelClassification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, xtptId, fullname, courtNo, fydm, courtNoText, orgCode, department, departmentText, politicalDate, lawPosition, lawPositionReportText, gender, rank, rankReportText, administrationPosition, administrationPositionReportText, political, politicalReportText, level, levelText, yefg, personnelClassificationText, personnelClassification);
    }

    @Override
    public String toString() {
        return "PersonInfo{" +
                "id='" + id + '\'' +
                ", xtptId='" + xtptId + '\'' +
                ", fullname='" + fullname + '\'' +
                ", courtNo=" + courtNo +
                ", fydm='" + fydm + '\'' +
                ", courtNoText='" + courtNoText + '\'' +
                ", org_code='" + orgCode + '\'' +
                ", department=" + department +
                ", departmentText='" + departmentText + '\'' +
                ", politicalDate='" + politicalDate + '\'' +
                ", lawPosition=" + lawPosition +
                ", lawPositionReportText='" + lawPositionReportText + '\'' +
                ", gender=" + gender +
                ", rank=" + rank +
                ", rankReportText='" + rankReportText + '\'' +
                ", administrationPosition=" + administrationPosition +
                ", administrationPositionReportText='" + administrationPositionReportText + '\'' +
                ", political=" + political +
                ", politicalReportText='" + politicalReportText + '\'' +
                ", level=" + level +
                ", levelText='" + levelText + '\'' +
                ", yefg=" + yefg +
                ", personnelClassificationText='" + personnelClassificationText + '\'' +
                ", personnelClassification=" + personnelClassification +
                '}';
    }
}
