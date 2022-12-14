package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsRankInfoView;
import cn.net.withub.ums.entity.UmsRankInfoViewCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsRankInfoViewMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_rank_info_view
     *
     * @mbggenerated Fri Jun 08 16:32:57 CST 2018
     */
    int countByExample(UmsRankInfoViewCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_rank_info_view
     *
     * @mbggenerated Fri Jun 08 16:32:57 CST 2018
     */
    int deleteByExample(UmsRankInfoViewCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_rank_info_view
     *
     * @mbggenerated Fri Jun 08 16:32:57 CST 2018
     */
    int insert(UmsRankInfoView record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_rank_info_view
     *
     * @mbggenerated Fri Jun 08 16:32:57 CST 2018
     */
    int insertSelective(UmsRankInfoView record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_rank_info_view
     *
     * @mbggenerated Fri Jun 08 16:32:57 CST 2018
     */
    List<UmsRankInfoView> selectByExample(UmsRankInfoViewCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_rank_info_view
     *
     * @mbggenerated Fri Jun 08 16:32:57 CST 2018
     */
    int updateByExampleSelective(@Param("record") UmsRankInfoView record, @Param("example") UmsRankInfoViewCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_rank_info_view
     *
     * @mbggenerated Fri Jun 08 16:32:57 CST 2018
     */
    int updateByExample(@Param("record") UmsRankInfoView record, @Param("example") UmsRankInfoViewCriteria example);
}