package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsFamilyInfo;
import cn.net.withub.ums.entity.UmsFamilyInfoCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsFamilyInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_family_info
     *
     * @mbggenerated Thu Jun 14 09:41:36 CST 2018
     */
    int countByExample(UmsFamilyInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_family_info
     *
     * @mbggenerated Thu Jun 14 09:41:36 CST 2018
     */
    int deleteByExample(UmsFamilyInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_family_info
     *
     * @mbggenerated Thu Jun 14 09:41:36 CST 2018
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_family_info
     *
     * @mbggenerated Thu Jun 14 09:41:36 CST 2018
     */
    int insert(UmsFamilyInfo record);

    int insert1(UmsFamilyInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_family_info
     *
     * @mbggenerated Thu Jun 14 09:41:36 CST 2018
     */
    int insertSelective(UmsFamilyInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_family_info
     *
     * @mbggenerated Thu Jun 14 09:41:36 CST 2018
     */
    List<UmsFamilyInfo> selectByExample(UmsFamilyInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_family_info
     *
     * @mbggenerated Thu Jun 14 09:41:36 CST 2018
     */
    UmsFamilyInfo selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_family_info
     *
     * @mbggenerated Thu Jun 14 09:41:36 CST 2018
     */
    int updateByExampleSelective(@Param("record") UmsFamilyInfo record, @Param("example") UmsFamilyInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_family_info
     *
     * @mbggenerated Thu Jun 14 09:41:36 CST 2018
     */
    int updateByExample(@Param("record") UmsFamilyInfo record, @Param("example") UmsFamilyInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_family_info
     *
     * @mbggenerated Thu Jun 14 09:41:36 CST 2018
     */
    int updateByPrimaryKeySelective(UmsFamilyInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_family_info
     *
     * @mbggenerated Thu Jun 14 09:41:36 CST 2018
     */
    int updateByPrimaryKey(UmsFamilyInfo record);
}