package cn.net.withub.ums.entity;

import java.util.Date;

public class UmsWageInfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_wage_info.id
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_wage_info.user_id
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_wage_info.old_id
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    private Long oldId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_wage_info.user_no
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    private Integer userNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_wage_info.court_no
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    private Integer courtNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_wage_info.c_pos_wage_grade
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    private String cPosWageGrade;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_wage_info.m_pos_wage
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    private Long mPosWage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_wage_info.d_pos_wage_grade_time
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    private Date dPosWageGradeTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_wage_info.c_current_grade
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    private String cCurrentGrade;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_wage_info.m_level_wage
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    private Long mLevelWage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_wage_info.d_current_grade_time
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    private Date dCurrentGradeTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_wage_info.m_base_wage
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    private Long mBaseWage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_wage_info.m_time_wage
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    private Long mTimeWage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_wage_info.m_posi_allowance
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    private Long mPosiAllowance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_wage_info.m_allowance
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    private Long mAllowance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_wage_info.m_other_allowance
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    private Long mOtherAllowance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_wage_info.sort_no
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    private Integer sortNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_wage_info.court_code
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    private String courtCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_wage_info.c_pos_wage_level
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    private String cPosWageLevel;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_wage_info.id
     *
     * @return the value of ums_wage_info.id
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_wage_info.id
     *
     * @param id the value for ums_wage_info.id
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_wage_info.user_id
     *
     * @return the value of ums_wage_info.user_id
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_wage_info.user_id
     *
     * @param userId the value for ums_wage_info.user_id
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_wage_info.old_id
     *
     * @return the value of ums_wage_info.old_id
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public Long getOldId() {
        return oldId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_wage_info.old_id
     *
     * @param oldId the value for ums_wage_info.old_id
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public void setOldId(Long oldId) {
        this.oldId = oldId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_wage_info.user_no
     *
     * @return the value of ums_wage_info.user_no
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public Integer getUserNo() {
        return userNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_wage_info.user_no
     *
     * @param userNo the value for ums_wage_info.user_no
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_wage_info.court_no
     *
     * @return the value of ums_wage_info.court_no
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public Integer getCourtNo() {
        return courtNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_wage_info.court_no
     *
     * @param courtNo the value for ums_wage_info.court_no
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public void setCourtNo(Integer courtNo) {
        this.courtNo = courtNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_wage_info.c_pos_wage_grade
     *
     * @return the value of ums_wage_info.c_pos_wage_grade
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public String getcPosWageGrade() {
        return cPosWageGrade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_wage_info.c_pos_wage_grade
     *
     * @param cPosWageGrade the value for ums_wage_info.c_pos_wage_grade
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public void setcPosWageGrade(String cPosWageGrade) {
        this.cPosWageGrade = cPosWageGrade == null ? null : cPosWageGrade.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_wage_info.m_pos_wage
     *
     * @return the value of ums_wage_info.m_pos_wage
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public Long getmPosWage() {
        return mPosWage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_wage_info.m_pos_wage
     *
     * @param mPosWage the value for ums_wage_info.m_pos_wage
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public void setmPosWage(Long mPosWage) {
        this.mPosWage = mPosWage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_wage_info.d_pos_wage_grade_time
     *
     * @return the value of ums_wage_info.d_pos_wage_grade_time
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public Date getdPosWageGradeTime() {
        return dPosWageGradeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_wage_info.d_pos_wage_grade_time
     *
     * @param dPosWageGradeTime the value for ums_wage_info.d_pos_wage_grade_time
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public void setdPosWageGradeTime(Date dPosWageGradeTime) {
        this.dPosWageGradeTime = dPosWageGradeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_wage_info.c_current_grade
     *
     * @return the value of ums_wage_info.c_current_grade
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public String getcCurrentGrade() {
        return cCurrentGrade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_wage_info.c_current_grade
     *
     * @param cCurrentGrade the value for ums_wage_info.c_current_grade
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public void setcCurrentGrade(String cCurrentGrade) {
        this.cCurrentGrade = cCurrentGrade == null ? null : cCurrentGrade.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_wage_info.m_level_wage
     *
     * @return the value of ums_wage_info.m_level_wage
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public Long getmLevelWage() {
        return mLevelWage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_wage_info.m_level_wage
     *
     * @param mLevelWage the value for ums_wage_info.m_level_wage
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public void setmLevelWage(Long mLevelWage) {
        this.mLevelWage = mLevelWage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_wage_info.d_current_grade_time
     *
     * @return the value of ums_wage_info.d_current_grade_time
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public Date getdCurrentGradeTime() {
        return dCurrentGradeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_wage_info.d_current_grade_time
     *
     * @param dCurrentGradeTime the value for ums_wage_info.d_current_grade_time
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public void setdCurrentGradeTime(Date dCurrentGradeTime) {
        this.dCurrentGradeTime = dCurrentGradeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_wage_info.m_base_wage
     *
     * @return the value of ums_wage_info.m_base_wage
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public Long getmBaseWage() {
        return mBaseWage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_wage_info.m_base_wage
     *
     * @param mBaseWage the value for ums_wage_info.m_base_wage
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public void setmBaseWage(Long mBaseWage) {
        this.mBaseWage = mBaseWage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_wage_info.m_time_wage
     *
     * @return the value of ums_wage_info.m_time_wage
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public Long getmTimeWage() {
        return mTimeWage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_wage_info.m_time_wage
     *
     * @param mTimeWage the value for ums_wage_info.m_time_wage
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public void setmTimeWage(Long mTimeWage) {
        this.mTimeWage = mTimeWage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_wage_info.m_posi_allowance
     *
     * @return the value of ums_wage_info.m_posi_allowance
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public Long getmPosiAllowance() {
        return mPosiAllowance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_wage_info.m_posi_allowance
     *
     * @param mPosiAllowance the value for ums_wage_info.m_posi_allowance
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public void setmPosiAllowance(Long mPosiAllowance) {
        this.mPosiAllowance = mPosiAllowance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_wage_info.m_allowance
     *
     * @return the value of ums_wage_info.m_allowance
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public Long getmAllowance() {
        return mAllowance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_wage_info.m_allowance
     *
     * @param mAllowance the value for ums_wage_info.m_allowance
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public void setmAllowance(Long mAllowance) {
        this.mAllowance = mAllowance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_wage_info.m_other_allowance
     *
     * @return the value of ums_wage_info.m_other_allowance
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public Long getmOtherAllowance() {
        return mOtherAllowance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_wage_info.m_other_allowance
     *
     * @param mOtherAllowance the value for ums_wage_info.m_other_allowance
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public void setmOtherAllowance(Long mOtherAllowance) {
        this.mOtherAllowance = mOtherAllowance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_wage_info.sort_no
     *
     * @return the value of ums_wage_info.sort_no
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public Integer getSortNo() {
        return sortNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_wage_info.sort_no
     *
     * @param sortNo the value for ums_wage_info.sort_no
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_wage_info.court_code
     *
     * @return the value of ums_wage_info.court_code
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public String getCourtCode() {
        return courtCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_wage_info.court_code
     *
     * @param courtCode the value for ums_wage_info.court_code
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public void setCourtCode(String courtCode) {
        this.courtCode = courtCode == null ? null : courtCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_wage_info.c_pos_wage_level
     *
     * @return the value of ums_wage_info.c_pos_wage_level
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public String getcPosWageLevel() {
        return cPosWageLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_wage_info.c_pos_wage_level
     *
     * @param cPosWageLevel the value for ums_wage_info.c_pos_wage_level
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    public void setcPosWageLevel(String cPosWageLevel) {
        this.cPosWageLevel = cPosWageLevel == null ? null : cPosWageLevel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_wage_info
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
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
        UmsWageInfo other = (UmsWageInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getOldId() == null ? other.getOldId() == null : this.getOldId().equals(other.getOldId()))
            && (this.getUserNo() == null ? other.getUserNo() == null : this.getUserNo().equals(other.getUserNo()))
            && (this.getCourtNo() == null ? other.getCourtNo() == null : this.getCourtNo().equals(other.getCourtNo()))
            && (this.getcPosWageGrade() == null ? other.getcPosWageGrade() == null : this.getcPosWageGrade().equals(other.getcPosWageGrade()))
            && (this.getmPosWage() == null ? other.getmPosWage() == null : this.getmPosWage().equals(other.getmPosWage()))
            && (this.getdPosWageGradeTime() == null ? other.getdPosWageGradeTime() == null : this.getdPosWageGradeTime().equals(other.getdPosWageGradeTime()))
            && (this.getcCurrentGrade() == null ? other.getcCurrentGrade() == null : this.getcCurrentGrade().equals(other.getcCurrentGrade()))
            && (this.getmLevelWage() == null ? other.getmLevelWage() == null : this.getmLevelWage().equals(other.getmLevelWage()))
            && (this.getdCurrentGradeTime() == null ? other.getdCurrentGradeTime() == null : this.getdCurrentGradeTime().equals(other.getdCurrentGradeTime()))
            && (this.getmBaseWage() == null ? other.getmBaseWage() == null : this.getmBaseWage().equals(other.getmBaseWage()))
            && (this.getmTimeWage() == null ? other.getmTimeWage() == null : this.getmTimeWage().equals(other.getmTimeWage()))
            && (this.getmPosiAllowance() == null ? other.getmPosiAllowance() == null : this.getmPosiAllowance().equals(other.getmPosiAllowance()))
            && (this.getmAllowance() == null ? other.getmAllowance() == null : this.getmAllowance().equals(other.getmAllowance()))
            && (this.getmOtherAllowance() == null ? other.getmOtherAllowance() == null : this.getmOtherAllowance().equals(other.getmOtherAllowance()))
            && (this.getSortNo() == null ? other.getSortNo() == null : this.getSortNo().equals(other.getSortNo()))
            && (this.getCourtCode() == null ? other.getCourtCode() == null : this.getCourtCode().equals(other.getCourtCode()))
            && (this.getcPosWageLevel() == null ? other.getcPosWageLevel() == null : this.getcPosWageLevel().equals(other.getcPosWageLevel()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_wage_info
     *
     * @mbggenerated Tue Jun 05 15:21:55 CST 2018
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getOldId() == null) ? 0 : getOldId().hashCode());
        result = prime * result + ((getUserNo() == null) ? 0 : getUserNo().hashCode());
        result = prime * result + ((getCourtNo() == null) ? 0 : getCourtNo().hashCode());
        result = prime * result + ((getcPosWageGrade() == null) ? 0 : getcPosWageGrade().hashCode());
        result = prime * result + ((getmPosWage() == null) ? 0 : getmPosWage().hashCode());
        result = prime * result + ((getdPosWageGradeTime() == null) ? 0 : getdPosWageGradeTime().hashCode());
        result = prime * result + ((getcCurrentGrade() == null) ? 0 : getcCurrentGrade().hashCode());
        result = prime * result + ((getmLevelWage() == null) ? 0 : getmLevelWage().hashCode());
        result = prime * result + ((getdCurrentGradeTime() == null) ? 0 : getdCurrentGradeTime().hashCode());
        result = prime * result + ((getmBaseWage() == null) ? 0 : getmBaseWage().hashCode());
        result = prime * result + ((getmTimeWage() == null) ? 0 : getmTimeWage().hashCode());
        result = prime * result + ((getmPosiAllowance() == null) ? 0 : getmPosiAllowance().hashCode());
        result = prime * result + ((getmAllowance() == null) ? 0 : getmAllowance().hashCode());
        result = prime * result + ((getmOtherAllowance() == null) ? 0 : getmOtherAllowance().hashCode());
        result = prime * result + ((getSortNo() == null) ? 0 : getSortNo().hashCode());
        result = prime * result + ((getCourtCode() == null) ? 0 : getCourtCode().hashCode());
        result = prime * result + ((getcPosWageLevel() == null) ? 0 : getcPosWageLevel().hashCode());
        return result;
    }
}