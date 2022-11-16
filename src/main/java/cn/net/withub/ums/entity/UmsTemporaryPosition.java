package cn.net.withub.ums.entity;

import java.util.Date;

public class UmsTemporaryPosition extends UmsTemporaryPositionKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_temporary_position.UUID
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    private String uuid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_temporary_position.CREATE_DATE
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_temporary_position.CREATOR
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_temporary_position.SORT_NO
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    private Integer sortNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_temporary_position.IS_PART_TIME_JOB
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    private Integer isPartTimeJob;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_temporary_position.ADMINISTRATION_POSITION
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    private Integer administrationPosition;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_temporary_position.LAW_POSITION
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    private Integer lawPosition;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_temporary_position.UUID
     *
     * @return the value of ums_temporary_position.UUID
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_temporary_position.UUID
     *
     * @param uuid the value for ums_temporary_position.UUID
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_temporary_position.CREATE_DATE
     *
     * @return the value of ums_temporary_position.CREATE_DATE
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_temporary_position.CREATE_DATE
     *
     * @param createDate the value for ums_temporary_position.CREATE_DATE
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_temporary_position.CREATOR
     *
     * @return the value of ums_temporary_position.CREATOR
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_temporary_position.CREATOR
     *
     * @param creator the value for ums_temporary_position.CREATOR
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_temporary_position.SORT_NO
     *
     * @return the value of ums_temporary_position.SORT_NO
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    public Integer getSortNo() {
        return sortNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_temporary_position.SORT_NO
     *
     * @param sortNo the value for ums_temporary_position.SORT_NO
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_temporary_position.IS_PART_TIME_JOB
     *
     * @return the value of ums_temporary_position.IS_PART_TIME_JOB
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    public Integer getIsPartTimeJob() {
        return isPartTimeJob;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_temporary_position.IS_PART_TIME_JOB
     *
     * @param isPartTimeJob the value for ums_temporary_position.IS_PART_TIME_JOB
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    public void setIsPartTimeJob(Integer isPartTimeJob) {
        this.isPartTimeJob = isPartTimeJob;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_temporary_position.ADMINISTRATION_POSITION
     *
     * @return the value of ums_temporary_position.ADMINISTRATION_POSITION
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    public Integer getAdministrationPosition() {
        return administrationPosition;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_temporary_position.ADMINISTRATION_POSITION
     *
     * @param administrationPosition the value for ums_temporary_position.ADMINISTRATION_POSITION
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    public void setAdministrationPosition(Integer administrationPosition) {
        this.administrationPosition = administrationPosition;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_temporary_position.LAW_POSITION
     *
     * @return the value of ums_temporary_position.LAW_POSITION
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    public Integer getLawPosition() {
        return lawPosition;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_temporary_position.LAW_POSITION
     *
     * @param lawPosition the value for ums_temporary_position.LAW_POSITION
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    public void setLawPosition(Integer lawPosition) {
        this.lawPosition = lawPosition;
    }
}