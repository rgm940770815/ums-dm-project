package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsTrainingInfoView;
import cn.net.withub.ums.entity.UmsTrainingInfoViewCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsTrainingInfoViewMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_training_info_view
     *
     * @mbggenerated Wed Jun 06 18:24:21 CST 2018
     */
    int countByExample(UmsTrainingInfoViewCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_training_info_view
     *
     * @mbggenerated Wed Jun 06 18:24:21 CST 2018
     */
    int deleteByExample(UmsTrainingInfoViewCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_training_info_view
     *
     * @mbggenerated Wed Jun 06 18:24:21 CST 2018
     */
    int insert(UmsTrainingInfoView record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_training_info_view
     *
     * @mbggenerated Wed Jun 06 18:24:21 CST 2018
     */
    int insertSelective(UmsTrainingInfoView record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_training_info_view
     *
     * @mbggenerated Wed Jun 06 18:24:21 CST 2018
     */
    List<UmsTrainingInfoView> selectByExample(UmsTrainingInfoViewCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_training_info_view
     *
     * @mbggenerated Wed Jun 06 18:24:21 CST 2018
     */
    int updateByExampleSelective(@Param("record") UmsTrainingInfoView record, @Param("example") UmsTrainingInfoViewCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_training_info_view
     *
     * @mbggenerated Wed Jun 06 18:24:21 CST 2018
     */
    int updateByExample(@Param("record") UmsTrainingInfoView record, @Param("example") UmsTrainingInfoViewCriteria example);
}