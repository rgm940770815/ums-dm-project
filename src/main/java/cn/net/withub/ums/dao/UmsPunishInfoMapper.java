package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsPunishInfo;
import cn.net.withub.ums.entity.UmsPunishInfoCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsPunishInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_punish_info
     *
     * @mbggenerated Wed May 16 17:21:54 CST 2018
     */
    int countByExample(UmsPunishInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_punish_info
     *
     * @mbggenerated Wed May 16 17:21:54 CST 2018
     */
    int deleteByExample(UmsPunishInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_punish_info
     *
     * @mbggenerated Wed May 16 17:21:54 CST 2018
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_punish_info
     *
     * @mbggenerated Wed May 16 17:21:54 CST 2018
     */
    int insert(UmsPunishInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_punish_info
     *
     * @mbggenerated Wed May 16 17:21:54 CST 2018
     */
    int insertSelective(UmsPunishInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_punish_info
     *
     * @mbggenerated Wed May 16 17:21:54 CST 2018
     */
    List<UmsPunishInfo> selectByExample(UmsPunishInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_punish_info
     *
     * @mbggenerated Wed May 16 17:21:54 CST 2018
     */
    UmsPunishInfo selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_punish_info
     *
     * @mbggenerated Wed May 16 17:21:54 CST 2018
     */
    int updateByExampleSelective(@Param("record") UmsPunishInfo record, @Param("example") UmsPunishInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_punish_info
     *
     * @mbggenerated Wed May 16 17:21:54 CST 2018
     */
    int updateByExample(@Param("record") UmsPunishInfo record, @Param("example") UmsPunishInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_punish_info
     *
     * @mbggenerated Wed May 16 17:21:54 CST 2018
     */
    int updateByPrimaryKeySelective(UmsPunishInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_punish_info
     *
     * @mbggenerated Wed May 16 17:21:54 CST 2018
     */
    int updateByPrimaryKey(UmsPunishInfo record);
}