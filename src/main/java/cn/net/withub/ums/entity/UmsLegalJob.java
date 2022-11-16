package cn.net.withub.ums.entity;

import java.util.Date;

public class UmsLegalJob {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_legal_job.id
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_legal_job.user_id
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_legal_job.old_id
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    private Long oldId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_legal_job.court_no
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    private Integer courtNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_legal_job.user_no
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    private Integer userNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_legal_job.sort_no
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    private Integer sortNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_legal_job.n_assign_type
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    private Integer nAssignType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_legal_job.c_unit
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    private String cUnit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_legal_job.c_department
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    private String cDepartment;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_legal_job.n_assign_reason
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    private Integer nAssignReason;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_legal_job.n_job
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    private String nJob;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_legal_job.d_assign_date
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    private Date dAssignDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_legal_job.c_approval_doc_no
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    private String cApprovalDocNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_legal_job.c_approval_unit
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    private String cApprovalUnit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_legal_job.d_approval_date
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    private Date dApprovalDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_legal_job.n_is_first_appoint_judge
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    private Integer nIsFirstAppointJudge;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_legal_job.n_first_judge_year
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    private Integer nFirstJudgeYear;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_legal_job.n_job_situation
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    private Integer nJobSituation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_legal_job.n_is_pro
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    private Integer nIsPro;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_legal_job.n_pro_situation
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    private Integer nProSituation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_legal_job.record_id
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    private String recordId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_legal_job.n_present_info
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    private Integer nPresentInfo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_legal_job.court_code
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    private String courtCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_legal_job.update_time
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    private String updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_legal_job.n_job_report
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    private String nJobReport;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_legal_job.id
     *
     * @return the value of ums_legal_job.id
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_legal_job.id
     *
     * @param id the value for ums_legal_job.id
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_legal_job.user_id
     *
     * @return the value of ums_legal_job.user_id
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_legal_job.user_id
     *
     * @param userId the value for ums_legal_job.user_id
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_legal_job.old_id
     *
     * @return the value of ums_legal_job.old_id
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public Long getOldId() {
        return oldId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_legal_job.old_id
     *
     * @param oldId the value for ums_legal_job.old_id
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public void setOldId(Long oldId) {
        this.oldId = oldId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_legal_job.court_no
     *
     * @return the value of ums_legal_job.court_no
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public Integer getCourtNo() {
        return courtNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_legal_job.court_no
     *
     * @param courtNo the value for ums_legal_job.court_no
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public void setCourtNo(Integer courtNo) {
        this.courtNo = courtNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_legal_job.user_no
     *
     * @return the value of ums_legal_job.user_no
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public Integer getUserNo() {
        return userNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_legal_job.user_no
     *
     * @param userNo the value for ums_legal_job.user_no
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_legal_job.sort_no
     *
     * @return the value of ums_legal_job.sort_no
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public Integer getSortNo() {
        return sortNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_legal_job.sort_no
     *
     * @param sortNo the value for ums_legal_job.sort_no
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_legal_job.n_assign_type
     *
     * @return the value of ums_legal_job.n_assign_type
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public Integer getnAssignType() {
        return nAssignType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_legal_job.n_assign_type
     *
     * @param nAssignType the value for ums_legal_job.n_assign_type
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public void setnAssignType(Integer nAssignType) {
        this.nAssignType = nAssignType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_legal_job.c_unit
     *
     * @return the value of ums_legal_job.c_unit
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public String getcUnit() {
        return cUnit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_legal_job.c_unit
     *
     * @param cUnit the value for ums_legal_job.c_unit
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public void setcUnit(String cUnit) {
        this.cUnit = cUnit == null ? null : cUnit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_legal_job.c_department
     *
     * @return the value of ums_legal_job.c_department
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public String getcDepartment() {
        return cDepartment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_legal_job.c_department
     *
     * @param cDepartment the value for ums_legal_job.c_department
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public void setcDepartment(String cDepartment) {
        this.cDepartment = cDepartment == null ? null : cDepartment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_legal_job.n_assign_reason
     *
     * @return the value of ums_legal_job.n_assign_reason
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public Integer getnAssignReason() {
        return nAssignReason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_legal_job.n_assign_reason
     *
     * @param nAssignReason the value for ums_legal_job.n_assign_reason
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public void setnAssignReason(Integer nAssignReason) {
        this.nAssignReason = nAssignReason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_legal_job.n_job
     *
     * @return the value of ums_legal_job.n_job
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public String getnJob() {
        return nJob;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_legal_job.n_job
     *
     * @param nJob the value for ums_legal_job.n_job
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public void setnJob(String nJob) {
        this.nJob = nJob == null ? null : nJob.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_legal_job.d_assign_date
     *
     * @return the value of ums_legal_job.d_assign_date
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public Date getdAssignDate() {
        return dAssignDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_legal_job.d_assign_date
     *
     * @param dAssignDate the value for ums_legal_job.d_assign_date
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public void setdAssignDate(Date dAssignDate) {
        this.dAssignDate = dAssignDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_legal_job.c_approval_doc_no
     *
     * @return the value of ums_legal_job.c_approval_doc_no
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public String getcApprovalDocNo() {
        return cApprovalDocNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_legal_job.c_approval_doc_no
     *
     * @param cApprovalDocNo the value for ums_legal_job.c_approval_doc_no
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public void setcApprovalDocNo(String cApprovalDocNo) {
        this.cApprovalDocNo = cApprovalDocNo == null ? null : cApprovalDocNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_legal_job.c_approval_unit
     *
     * @return the value of ums_legal_job.c_approval_unit
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public String getcApprovalUnit() {
        return cApprovalUnit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_legal_job.c_approval_unit
     *
     * @param cApprovalUnit the value for ums_legal_job.c_approval_unit
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public void setcApprovalUnit(String cApprovalUnit) {
        this.cApprovalUnit = cApprovalUnit == null ? null : cApprovalUnit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_legal_job.d_approval_date
     *
     * @return the value of ums_legal_job.d_approval_date
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public Date getdApprovalDate() {
        return dApprovalDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_legal_job.d_approval_date
     *
     * @param dApprovalDate the value for ums_legal_job.d_approval_date
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public void setdApprovalDate(Date dApprovalDate) {
        this.dApprovalDate = dApprovalDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_legal_job.n_is_first_appoint_judge
     *
     * @return the value of ums_legal_job.n_is_first_appoint_judge
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public Integer getnIsFirstAppointJudge() {
        return nIsFirstAppointJudge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_legal_job.n_is_first_appoint_judge
     *
     * @param nIsFirstAppointJudge the value for ums_legal_job.n_is_first_appoint_judge
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public void setnIsFirstAppointJudge(Integer nIsFirstAppointJudge) {
        this.nIsFirstAppointJudge = nIsFirstAppointJudge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_legal_job.n_first_judge_year
     *
     * @return the value of ums_legal_job.n_first_judge_year
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public Integer getnFirstJudgeYear() {
        return nFirstJudgeYear;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_legal_job.n_first_judge_year
     *
     * @param nFirstJudgeYear the value for ums_legal_job.n_first_judge_year
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public void setnFirstJudgeYear(Integer nFirstJudgeYear) {
        this.nFirstJudgeYear = nFirstJudgeYear;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_legal_job.n_job_situation
     *
     * @return the value of ums_legal_job.n_job_situation
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public Integer getnJobSituation() {
        return nJobSituation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_legal_job.n_job_situation
     *
     * @param nJobSituation the value for ums_legal_job.n_job_situation
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public void setnJobSituation(Integer nJobSituation) {
        this.nJobSituation = nJobSituation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_legal_job.n_is_pro
     *
     * @return the value of ums_legal_job.n_is_pro
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public Integer getnIsPro() {
        return nIsPro;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_legal_job.n_is_pro
     *
     * @param nIsPro the value for ums_legal_job.n_is_pro
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public void setnIsPro(Integer nIsPro) {
        this.nIsPro = nIsPro;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_legal_job.n_pro_situation
     *
     * @return the value of ums_legal_job.n_pro_situation
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public Integer getnProSituation() {
        return nProSituation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_legal_job.n_pro_situation
     *
     * @param nProSituation the value for ums_legal_job.n_pro_situation
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public void setnProSituation(Integer nProSituation) {
        this.nProSituation = nProSituation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_legal_job.record_id
     *
     * @return the value of ums_legal_job.record_id
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public String getRecordId() {
        return recordId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_legal_job.record_id
     *
     * @param recordId the value for ums_legal_job.record_id
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public void setRecordId(String recordId) {
        this.recordId = recordId == null ? null : recordId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_legal_job.n_present_info
     *
     * @return the value of ums_legal_job.n_present_info
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public Integer getnPresentInfo() {
        return nPresentInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_legal_job.n_present_info
     *
     * @param nPresentInfo the value for ums_legal_job.n_present_info
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public void setnPresentInfo(Integer nPresentInfo) {
        this.nPresentInfo = nPresentInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_legal_job.court_code
     *
     * @return the value of ums_legal_job.court_code
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public String getCourtCode() {
        return courtCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_legal_job.court_code
     *
     * @param courtCode the value for ums_legal_job.court_code
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public void setCourtCode(String courtCode) {
        this.courtCode = courtCode == null ? null : courtCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_legal_job.update_time
     *
     * @return the value of ums_legal_job.update_time
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_legal_job.update_time
     *
     * @param updateTime the value for ums_legal_job.update_time
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_legal_job.n_job_report
     *
     * @return the value of ums_legal_job.n_job_report
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public String getnJobReport() {
        return nJobReport;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_legal_job.n_job_report
     *
     * @param nJobReport the value for ums_legal_job.n_job_report
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    public void setnJobReport(String nJobReport) {
        this.nJobReport = nJobReport == null ? null : nJobReport.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_legal_job
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UmsLegalJob other = (UmsLegalJob) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getOldId() == null ? other.getOldId() == null : this.getOldId().equals(other.getOldId()))
            && (this.getCourtNo() == null ? other.getCourtNo() == null : this.getCourtNo().equals(other.getCourtNo()))
            && (this.getUserNo() == null ? other.getUserNo() == null : this.getUserNo().equals(other.getUserNo()))
            && (this.getSortNo() == null ? other.getSortNo() == null : this.getSortNo().equals(other.getSortNo()))
            && (this.getnAssignType() == null ? other.getnAssignType() == null : this.getnAssignType().equals(other.getnAssignType()))
            && (this.getcUnit() == null ? other.getcUnit() == null : this.getcUnit().equals(other.getcUnit()))
            && (this.getcDepartment() == null ? other.getcDepartment() == null : this.getcDepartment().equals(other.getcDepartment()))
            && (this.getnAssignReason() == null ? other.getnAssignReason() == null : this.getnAssignReason().equals(other.getnAssignReason()))
            && (this.getnJob() == null ? other.getnJob() == null : this.getnJob().equals(other.getnJob()))
            && (this.getdAssignDate() == null ? other.getdAssignDate() == null : this.getdAssignDate().equals(other.getdAssignDate()))
            && (this.getcApprovalDocNo() == null ? other.getcApprovalDocNo() == null : this.getcApprovalDocNo().equals(other.getcApprovalDocNo()))
            && (this.getcApprovalUnit() == null ? other.getcApprovalUnit() == null : this.getcApprovalUnit().equals(other.getcApprovalUnit()))
            && (this.getdApprovalDate() == null ? other.getdApprovalDate() == null : this.getdApprovalDate().equals(other.getdApprovalDate()))
            && (this.getnIsFirstAppointJudge() == null ? other.getnIsFirstAppointJudge() == null : this.getnIsFirstAppointJudge().equals(other.getnIsFirstAppointJudge()))
            && (this.getnFirstJudgeYear() == null ? other.getnFirstJudgeYear() == null : this.getnFirstJudgeYear().equals(other.getnFirstJudgeYear()))
            && (this.getnJobSituation() == null ? other.getnJobSituation() == null : this.getnJobSituation().equals(other.getnJobSituation()))
            && (this.getnIsPro() == null ? other.getnIsPro() == null : this.getnIsPro().equals(other.getnIsPro()))
            && (this.getnProSituation() == null ? other.getnProSituation() == null : this.getnProSituation().equals(other.getnProSituation()))
            && (this.getRecordId() == null ? other.getRecordId() == null : this.getRecordId().equals(other.getRecordId()))
            && (this.getnPresentInfo() == null ? other.getnPresentInfo() == null : this.getnPresentInfo().equals(other.getnPresentInfo()))
            && (this.getCourtCode() == null ? other.getCourtCode() == null : this.getCourtCode().equals(other.getCourtCode()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getnJobReport() == null ? other.getnJobReport() == null : this.getnJobReport().equals(other.getnJobReport()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_legal_job
     *
     * @mbggenerated Fri Jun 08 16:34:01 CST 2018
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getOldId() == null) ? 0 : getOldId().hashCode());
        result = prime * result + ((getCourtNo() == null) ? 0 : getCourtNo().hashCode());
        result = prime * result + ((getUserNo() == null) ? 0 : getUserNo().hashCode());
        result = prime * result + ((getSortNo() == null) ? 0 : getSortNo().hashCode());
        result = prime * result + ((getnAssignType() == null) ? 0 : getnAssignType().hashCode());
        result = prime * result + ((getcUnit() == null) ? 0 : getcUnit().hashCode());
        result = prime * result + ((getcDepartment() == null) ? 0 : getcDepartment().hashCode());
        result = prime * result + ((getnAssignReason() == null) ? 0 : getnAssignReason().hashCode());
        result = prime * result + ((getnJob() == null) ? 0 : getnJob().hashCode());
        result = prime * result + ((getdAssignDate() == null) ? 0 : getdAssignDate().hashCode());
        result = prime * result + ((getcApprovalDocNo() == null) ? 0 : getcApprovalDocNo().hashCode());
        result = prime * result + ((getcApprovalUnit() == null) ? 0 : getcApprovalUnit().hashCode());
        result = prime * result + ((getdApprovalDate() == null) ? 0 : getdApprovalDate().hashCode());
        result = prime * result + ((getnIsFirstAppointJudge() == null) ? 0 : getnIsFirstAppointJudge().hashCode());
        result = prime * result + ((getnFirstJudgeYear() == null) ? 0 : getnFirstJudgeYear().hashCode());
        result = prime * result + ((getnJobSituation() == null) ? 0 : getnJobSituation().hashCode());
        result = prime * result + ((getnIsPro() == null) ? 0 : getnIsPro().hashCode());
        result = prime * result + ((getnProSituation() == null) ? 0 : getnProSituation().hashCode());
        result = prime * result + ((getRecordId() == null) ? 0 : getRecordId().hashCode());
        result = prime * result + ((getnPresentInfo() == null) ? 0 : getnPresentInfo().hashCode());
        result = prime * result + ((getCourtCode() == null) ? 0 : getCourtCode().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getnJobReport() == null) ? 0 : getnJobReport().hashCode());
        return result;
    }
}