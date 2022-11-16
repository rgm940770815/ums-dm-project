package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsCasualtyInfo;
import cn.net.withub.ums.entity.UmsCasualtyInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsCasualtyInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_casualty_info
     *
     * @mbggenerated
     */
    int countByExample(UmsCasualtyInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_casualty_info
     *
     * @mbggenerated
     */
    int deleteByExample(UmsCasualtyInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_casualty_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_casualty_info
     *
     * @mbggenerated
     */
    int insert(UmsCasualtyInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_casualty_info
     *
     * @mbggenerated
     */
    int insertSelective(UmsCasualtyInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_casualty_info
     *
     * @mbggenerated
     */
    List<UmsCasualtyInfo> selectByExample(UmsCasualtyInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_casualty_info
     *
     * @mbggenerated
     */
    UmsCasualtyInfo selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_casualty_info
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") UmsCasualtyInfo record, @Param("example") UmsCasualtyInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_casualty_info
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") UmsCasualtyInfo record, @Param("example") UmsCasualtyInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_casualty_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UmsCasualtyInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_casualty_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(UmsCasualtyInfo record);
}