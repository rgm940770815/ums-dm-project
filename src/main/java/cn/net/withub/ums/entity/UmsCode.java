package cn.net.withub.ums.entity;

public class UmsCode extends UmsCodeKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_code.code_name
     *
     * @mbggenerated Sat Apr 28 16:20:53 CST 2018
     */
    private String codeName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_code.sort_no
     *
     * @mbggenerated Sat Apr 28 16:20:53 CST 2018
     */
    private Integer sortNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_code.is_valid
     *
     * @mbggenerated Sat Apr 28 16:20:53 CST 2018
     */
    private Integer isValid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_code.court_code
     *
     * @mbggenerated Sat Apr 28 16:20:53 CST 2018
     */
    private String courtCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_code.parent_id
     *
     * @mbggenerated Sat Apr 28 16:20:53 CST 2018
     */
    private String parentId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_code.code_name
     *
     * @return the value of ums_code.code_name
     *
     * @mbggenerated Sat Apr 28 16:20:53 CST 2018
     */
    public String getCodeName() {
        return codeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_code.code_name
     *
     * @param codeName the value for ums_code.code_name
     *
     * @mbggenerated Sat Apr 28 16:20:53 CST 2018
     */
    public void setCodeName(String codeName) {
        this.codeName = codeName == null ? null : codeName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_code.sort_no
     *
     * @return the value of ums_code.sort_no
     *
     * @mbggenerated Sat Apr 28 16:20:53 CST 2018
     */
    public Integer getSortNo() {
        return sortNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_code.sort_no
     *
     * @param sortNo the value for ums_code.sort_no
     *
     * @mbggenerated Sat Apr 28 16:20:53 CST 2018
     */
    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_code.is_valid
     *
     * @return the value of ums_code.is_valid
     *
     * @mbggenerated Sat Apr 28 16:20:53 CST 2018
     */
    public Integer getIsValid() {
        return isValid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_code.is_valid
     *
     * @param isValid the value for ums_code.is_valid
     *
     * @mbggenerated Sat Apr 28 16:20:53 CST 2018
     */
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_code.court_code
     *
     * @return the value of ums_code.court_code
     *
     * @mbggenerated Sat Apr 28 16:20:53 CST 2018
     */
    public String getCourtCode() {
        return courtCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_code.court_code
     *
     * @param courtCode the value for ums_code.court_code
     *
     * @mbggenerated Sat Apr 28 16:20:53 CST 2018
     */
    public void setCourtCode(String courtCode) {
        this.courtCode = courtCode == null ? null : courtCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_code.parent_id
     *
     * @return the value of ums_code.parent_id
     *
     * @mbggenerated Sat Apr 28 16:20:53 CST 2018
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_code.parent_id
     *
     * @param parentId the value for ums_code.parent_id
     *
     * @mbggenerated Sat Apr 28 16:20:53 CST 2018
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_code
     *
     * @mbggenerated Sat Apr 28 16:20:53 CST 2018
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
        UmsCode other = (UmsCode) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTypeId() == null ? other.getTypeId() == null : this.getTypeId().equals(other.getTypeId()))
            && (this.getCodeName() == null ? other.getCodeName() == null : this.getCodeName().equals(other.getCodeName()))
            && (this.getSortNo() == null ? other.getSortNo() == null : this.getSortNo().equals(other.getSortNo()))
            && (this.getIsValid() == null ? other.getIsValid() == null : this.getIsValid().equals(other.getIsValid()))
            && (this.getCourtCode() == null ? other.getCourtCode() == null : this.getCourtCode().equals(other.getCourtCode()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_code
     *
     * @mbggenerated Sat Apr 28 16:20:53 CST 2018
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTypeId() == null) ? 0 : getTypeId().hashCode());
        result = prime * result + ((getCodeName() == null) ? 0 : getCodeName().hashCode());
        result = prime * result + ((getSortNo() == null) ? 0 : getSortNo().hashCode());
        result = prime * result + ((getIsValid() == null) ? 0 : getIsValid().hashCode());
        result = prime * result + ((getCourtCode() == null) ? 0 : getCourtCode().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        return result;
    }
}