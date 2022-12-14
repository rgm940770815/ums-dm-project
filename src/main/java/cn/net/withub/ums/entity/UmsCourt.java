package cn.net.withub.ums.entity;

public class UmsCourt {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_court.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_court.old_id
     *
     * @mbggenerated
     */
    private Integer oldId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_court.court_no
     *
     * @mbggenerated
     */
    private Integer courtNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_court.court_std_no
     *
     * @mbggenerated
     */
    private Integer courtStdNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_court.court_code
     *
     * @mbggenerated
     */
    private String courtCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_court.court_std_name
     *
     * @mbggenerated
     */
    private String courtStdName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_court.id
     *
     * @return the value of ums_court.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_court.id
     *
     * @param id the value for ums_court.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_court.old_id
     *
     * @return the value of ums_court.old_id
     *
     * @mbggenerated
     */
    public Integer getOldId() {
        return oldId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_court.old_id
     *
     * @param oldId the value for ums_court.old_id
     *
     * @mbggenerated
     */
    public void setOldId(Integer oldId) {
        this.oldId = oldId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_court.court_no
     *
     * @return the value of ums_court.court_no
     *
     * @mbggenerated
     */
    public Integer getCourtNo() {
        return courtNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_court.court_no
     *
     * @param courtNo the value for ums_court.court_no
     *
     * @mbggenerated
     */
    public void setCourtNo(Integer courtNo) {
        this.courtNo = courtNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_court.court_std_no
     *
     * @return the value of ums_court.court_std_no
     *
     * @mbggenerated
     */
    public Integer getCourtStdNo() {
        return courtStdNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_court.court_std_no
     *
     * @param courtStdNo the value for ums_court.court_std_no
     *
     * @mbggenerated
     */
    public void setCourtStdNo(Integer courtStdNo) {
        this.courtStdNo = courtStdNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_court.court_code
     *
     * @return the value of ums_court.court_code
     *
     * @mbggenerated
     */
    public String getCourtCode() {
        return courtCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_court.court_code
     *
     * @param courtCode the value for ums_court.court_code
     *
     * @mbggenerated
     */
    public void setCourtCode(String courtCode) {
        this.courtCode = courtCode == null ? null : courtCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_court.court_std_name
     *
     * @return the value of ums_court.court_std_name
     *
     * @mbggenerated
     */
    public String getCourtStdName() {
        return courtStdName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_court.court_std_name
     *
     * @param courtStdName the value for ums_court.court_std_name
     *
     * @mbggenerated
     */
    public void setCourtStdName(String courtStdName) {
        this.courtStdName = courtStdName == null ? null : courtStdName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_court
     *
     * @mbggenerated
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
        UmsCourt other = (UmsCourt) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOldId() == null ? other.getOldId() == null : this.getOldId().equals(other.getOldId()))
            && (this.getCourtNo() == null ? other.getCourtNo() == null : this.getCourtNo().equals(other.getCourtNo()))
            && (this.getCourtStdNo() == null ? other.getCourtStdNo() == null : this.getCourtStdNo().equals(other.getCourtStdNo()))
            && (this.getCourtCode() == null ? other.getCourtCode() == null : this.getCourtCode().equals(other.getCourtCode()))
            && (this.getCourtStdName() == null ? other.getCourtStdName() == null : this.getCourtStdName().equals(other.getCourtStdName()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_court
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOldId() == null) ? 0 : getOldId().hashCode());
        result = prime * result + ((getCourtNo() == null) ? 0 : getCourtNo().hashCode());
        result = prime * result + ((getCourtStdNo() == null) ? 0 : getCourtStdNo().hashCode());
        result = prime * result + ((getCourtCode() == null) ? 0 : getCourtCode().hashCode());
        result = prime * result + ((getCourtStdName() == null) ? 0 : getCourtStdName().hashCode());
        return result;
    }
}