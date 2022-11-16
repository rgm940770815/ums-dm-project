package cn.net.withub.ums.entity;

public class UmsCodeKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_code.id
     *
     * @mbggenerated Sat Apr 28 16:20:53 CST 2018
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_code.type_id
     *
     * @mbggenerated Sat Apr 28 16:20:53 CST 2018
     */
    private Integer typeId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_code.id
     *
     * @return the value of ums_code.id
     *
     * @mbggenerated Sat Apr 28 16:20:53 CST 2018
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_code.id
     *
     * @param id the value for ums_code.id
     *
     * @mbggenerated Sat Apr 28 16:20:53 CST 2018
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_code.type_id
     *
     * @return the value of ums_code.type_id
     *
     * @mbggenerated Sat Apr 28 16:20:53 CST 2018
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_code.type_id
     *
     * @param typeId the value for ums_code.type_id
     *
     * @mbggenerated Sat Apr 28 16:20:53 CST 2018
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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
        UmsCodeKey other = (UmsCodeKey) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTypeId() == null ? other.getTypeId() == null : this.getTypeId().equals(other.getTypeId()));
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
        return result;
    }
}