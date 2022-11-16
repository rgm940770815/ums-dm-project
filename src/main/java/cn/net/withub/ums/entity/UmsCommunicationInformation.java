package cn.net.withub.ums.entity;

import java.util.Date;

public class UmsCommunicationInformation {
    private String id;

    private String institutionId;

    private String courtCode;

    private Integer courtNo;

    private Integer courtStdNo;

    private Integer deptNo;

    private String address;

    private String phoneNumber;

    private String faxNumber;

    private String postalCode;

    private String emailAddress;

    private String officeContactName;

    private String officeContactPhoneNumber;

    private String personnelDepartmentContactName;

    private String personnelDepartmentContactPhoneNumber;

    private String personnelDepartmentContactEmailAddress;

    private String internetAddress;

    private Date insertTime;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getOfficeContactName() {
        return officeContactName;
    }

    public void setOfficeContactName(String officeContactName) {
        this.officeContactName = officeContactName;
    }

    public String getOfficeContactPhoneNumber() {
        return officeContactPhoneNumber;
    }

    public void setOfficeContactPhoneNumber(String officeContactPhoneNumber) {
        this.officeContactPhoneNumber = officeContactPhoneNumber;
    }

    public String getPersonnelDepartmentContactName() {
        return personnelDepartmentContactName;
    }

    public void setPersonnelDepartmentContactName(String personnelDepartmentContactName) {
        this.personnelDepartmentContactName = personnelDepartmentContactName;
    }

    public String getPersonnelDepartmentContactPhoneNumber() {
        return personnelDepartmentContactPhoneNumber;
    }

    public void setPersonnelDepartmentContactPhoneNumber(String personnelDepartmentContactPhoneNumber) {
        this.personnelDepartmentContactPhoneNumber = personnelDepartmentContactPhoneNumber;
    }

    public String getPersonnelDepartmentContactEmailAddress() {
        return personnelDepartmentContactEmailAddress;
    }

    public void setPersonnelDepartmentContactEmailAddress(String personnelDepartmentContactEmailAddress) {
        this.personnelDepartmentContactEmailAddress = personnelDepartmentContactEmailAddress;
    }

    public String getInternetAddress() {
        return internetAddress;
    }

    public void setInternetAddress(String internetAddress) {
        this.internetAddress = internetAddress;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
}