package cn.net.withub.ums.entity;

public class UmsTemporaryPositionKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_temporary_position.ID
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_temporary_position.COURT_CODE
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    private String courtCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_temporary_position.DEPARTMENT
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    private Integer department;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_temporary_position.ID
     *
     * @return the value of ums_temporary_position.ID
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_temporary_position.ID
     *
     * @param id the value for ums_temporary_position.ID
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_temporary_position.COURT_CODE
     *
     * @return the value of ums_temporary_position.COURT_CODE
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    public String getCourtCode() {
        return courtCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_temporary_position.COURT_CODE
     *
     * @param courtCode the value for ums_temporary_position.COURT_CODE
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    public void setCourtCode(String courtCode) {
        this.courtCode = courtCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_temporary_position.DEPARTMENT
     *
     * @return the value of ums_temporary_position.DEPARTMENT
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    public Integer getDepartment() {
        return department;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_temporary_position.DEPARTMENT
     *
     * @param department the value for ums_temporary_position.DEPARTMENT
     *
     * @mbggenerated Thu Mar 30 16:39:45 CST 2017
     */
    public void setDepartment(Integer department) {
        this.department = department;
    }
}