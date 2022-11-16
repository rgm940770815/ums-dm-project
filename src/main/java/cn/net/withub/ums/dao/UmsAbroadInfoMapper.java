package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsAbroadInfo;
import cn.net.withub.ums.entity.UmsAbroadInfoCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsAbroadInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_abroad_info
     *
     * @mbggenerated Wed May 16 17:21:54 CST 2018
     */
    int countByExample(UmsAbroadInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_abroad_info
     *
     * @mbggenerated Wed May 16 17:21:54 CST 2018
     */
    int deleteByExample(UmsAbroadInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_abroad_info
     *
     * @mbggenerated Wed May 16 17:21:54 CST 2018
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_abroad_info
     *
     * @mbggenerated Wed May 16 17:21:54 CST 2018
     */
    int insert(UmsAbroadInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_abroad_info
     *
     * @mbggenerated Wed May 16 17:21:54 CST 2018
     */
    int insertSelective(UmsAbroadInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_abroad_info
     *
     * @mbggenerated Wed May 16 17:21:54 CST 2018
     */
    List<UmsAbroadInfo> selectByExample(UmsAbroadInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_abroad_info
     *
     * @mbggenerated Wed May 16 17:21:54 CST 2018
     */
    UmsAbroadInfo selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_abroad_info
     *
     * @mbggenerated Wed May 16 17:21:54 CST 2018
     */
    int updateByExampleSelective(@Param("record") UmsAbroadInfo record, @Param("example") UmsAbroadInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_abroad_info
     *
     * @mbggenerated Wed May 16 17:21:54 CST 2018
     */
    int updateByExample(@Param("record") UmsAbroadInfo record, @Param("example") UmsAbroadInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_abroad_info
     *
     * @mbggenerated Wed May 16 17:21:54 CST 2018
     */
    int updateByPrimaryKeySelective(UmsAbroadInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_abroad_info
     *
     * @mbggenerated Wed May 16 17:21:54 CST 2018
     */
    int updateByPrimaryKey(UmsAbroadInfo record);
}