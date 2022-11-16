package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsLeadership;
import cn.net.withub.ums.entity.UmsLeadershipCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsLeadershipMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_leadership
     *
     * @mbggenerated
     */
    int countByExample(UmsLeadershipCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_leadership
     *
     * @mbggenerated
     */
    int deleteByExample(UmsLeadershipCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_leadership
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_leadership
     *
     * @mbggenerated
     */
    int insert(UmsLeadership record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_leadership
     *
     * @mbggenerated
     */
    int insertSelective(UmsLeadership record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_leadership
     *
     * @mbggenerated
     */
    List<UmsLeadership> selectByExample(UmsLeadershipCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_leadership
     *
     * @mbggenerated
     */
    UmsLeadership selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_leadership
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") UmsLeadership record, @Param("example") UmsLeadershipCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_leadership
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") UmsLeadership record, @Param("example") UmsLeadershipCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_leadership
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UmsLeadership record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_leadership
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(UmsLeadership record);
}