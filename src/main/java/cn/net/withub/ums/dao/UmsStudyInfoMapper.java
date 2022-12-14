package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsStudyInfo;
import cn.net.withub.ums.entity.UmsStudyInfoCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsStudyInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_study_info
     *
     * @mbggenerated Fri Jun 08 16:32:56 CST 2018
     */
    int countByExample(UmsStudyInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_study_info
     *
     * @mbggenerated Fri Jun 08 16:32:56 CST 2018
     */
    int deleteByExample(UmsStudyInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_study_info
     *
     * @mbggenerated Fri Jun 08 16:32:56 CST 2018
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_study_info
     *
     * @mbggenerated Fri Jun 08 16:32:56 CST 2018
     */
    int insert(UmsStudyInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_study_info
     *
     * @mbggenerated Fri Jun 08 16:32:56 CST 2018
     */
    int insertSelective(UmsStudyInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_study_info
     *
     * @mbggenerated Fri Jun 08 16:32:56 CST 2018
     */
    List<UmsStudyInfo> selectByExample(UmsStudyInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_study_info
     *
     * @mbggenerated Fri Jun 08 16:32:56 CST 2018
     */
    UmsStudyInfo selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_study_info
     *
     * @mbggenerated Fri Jun 08 16:32:56 CST 2018
     */
    int updateByExampleSelective(@Param("record") UmsStudyInfo record, @Param("example") UmsStudyInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_study_info
     *
     * @mbggenerated Fri Jun 08 16:32:56 CST 2018
     */
    int updateByExample(@Param("record") UmsStudyInfo record, @Param("example") UmsStudyInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_study_info
     *
     * @mbggenerated Fri Jun 08 16:32:56 CST 2018
     */
    int updateByPrimaryKeySelective(UmsStudyInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_study_info
     *
     * @mbggenerated Fri Jun 08 16:32:56 CST 2018
     */
    int updateByPrimaryKey(UmsStudyInfo record);
}