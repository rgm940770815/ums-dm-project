package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsContact;
import cn.net.withub.ums.entity.UmsContactCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsContactMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_contact
     *
     * @mbggenerated
     */
    int countByExample(UmsContactCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_contact
     *
     * @mbggenerated
     */
    int deleteByExample(UmsContactCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_contact
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_contact
     *
     * @mbggenerated
     */
    int insert(UmsContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_contact
     *
     * @mbggenerated
     */
    int insertSelective(UmsContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_contact
     *
     * @mbggenerated
     */
    List<UmsContact> selectByExample(UmsContactCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_contact
     *
     * @mbggenerated
     */
    UmsContact selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_contact
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") UmsContact record, @Param("example") UmsContactCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_contact
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") UmsContact record, @Param("example") UmsContactCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_contact
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UmsContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_contact
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(UmsContact record);
}