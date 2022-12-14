package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsChangeInfo;
import cn.net.withub.ums.entity.UmsChangeInfoCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsChangeInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_change_info
     *
     * @mbggenerated Tue May 15 10:32:03 CST 2018
     */
    int countByExample(UmsChangeInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_change_info
     *
     * @mbggenerated Tue May 15 10:32:03 CST 2018
     */
    int deleteByExample(UmsChangeInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_change_info
     *
     * @mbggenerated Tue May 15 10:32:03 CST 2018
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_change_info
     *
     * @mbggenerated Tue May 15 10:32:03 CST 2018
     */
    int insert(UmsChangeInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_change_info
     *
     * @mbggenerated Tue May 15 10:32:03 CST 2018
     */
    int insertSelective(UmsChangeInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_change_info
     *
     * @mbggenerated Tue May 15 10:32:03 CST 2018
     */
    List<UmsChangeInfo> selectByExample(UmsChangeInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_change_info
     *
     * @mbggenerated Tue May 15 10:32:03 CST 2018
     */
    UmsChangeInfo selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_change_info
     *
     * @mbggenerated Tue May 15 10:32:03 CST 2018
     */
    int updateByExampleSelective(@Param("record") UmsChangeInfo record, @Param("example") UmsChangeInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_change_info
     *
     * @mbggenerated Tue May 15 10:32:03 CST 2018
     */
    int updateByExample(@Param("record") UmsChangeInfo record, @Param("example") UmsChangeInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_change_info
     *
     * @mbggenerated Tue May 15 10:32:03 CST 2018
     */
    int updateByPrimaryKeySelective(UmsChangeInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_change_info
     *
     * @mbggenerated Tue May 15 10:32:03 CST 2018
     */
    int updateByPrimaryKey(UmsChangeInfo record);
}