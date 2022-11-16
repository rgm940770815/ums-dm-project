package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsJurorInfo;
import cn.net.withub.ums.entity.UmsJurorInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UmsJurorInfoMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table ums_juror_info
     *
     * @mbggenerated
     */
    int countByExample(UmsJurorInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table ums_juror_info
     *
     * @mbggenerated
     */
    int deleteByExample(UmsJurorInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table ums_juror_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String userId);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table ums_juror_info
     *
     * @mbggenerated
     */
    int insert(UmsJurorInfo record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table ums_juror_info
     *
     * @mbggenerated
     */
    int insertSelective(UmsJurorInfo record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table ums_juror_info
     *
     * @mbggenerated
     */
    List<UmsJurorInfo> selectByExample(UmsJurorInfoCriteria example);

    List<UmsJurorInfo> selectByExample(UmsJurorInfoCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table ums_juror_info
     *
     * @mbggenerated
     */
    UmsJurorInfo selectByPrimaryKey(String userId);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table ums_juror_info
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") UmsJurorInfo record, @Param("example") UmsJurorInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table ums_juror_info
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") UmsJurorInfo record, @Param("example") UmsJurorInfoCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table ums_juror_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UmsJurorInfo record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table ums_juror_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(UmsJurorInfo record);
}