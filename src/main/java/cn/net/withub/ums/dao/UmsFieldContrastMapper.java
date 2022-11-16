package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsFieldContrast;
import cn.net.withub.ums.entity.UmsFieldContrastExample;
import cn.net.withub.ums.entity.UmsFieldContrastKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsFieldContrastMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_field_contrast
     *
     * @mbggenerated Mon Jan 18 11:15:50 CST 2016
     */
    int countByExample(UmsFieldContrastExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_field_contrast
     *
     * @mbggenerated Mon Jan 18 11:15:50 CST 2016
     */
    int deleteByExample(UmsFieldContrastExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_field_contrast
     *
     * @mbggenerated Mon Jan 18 11:15:50 CST 2016
     */
    int deleteByPrimaryKey(UmsFieldContrastKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_field_contrast
     *
     * @mbggenerated Mon Jan 18 11:15:50 CST 2016
     */
    int insert(UmsFieldContrast record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_field_contrast
     *
     * @mbggenerated Mon Jan 18 11:15:50 CST 2016
     */
    int insertSelective(UmsFieldContrast record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_field_contrast
     *
     * @mbggenerated Mon Jan 18 11:15:50 CST 2016
     */
    List<UmsFieldContrast> selectByExample(UmsFieldContrastExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_field_contrast
     *
     * @mbggenerated Mon Jan 18 11:15:50 CST 2016
     */
    UmsFieldContrast selectByPrimaryKey(UmsFieldContrastKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_field_contrast
     *
     * @mbggenerated Mon Jan 18 11:15:50 CST 2016
     */
    int updateByExampleSelective(@Param("record") UmsFieldContrast record, @Param("example") UmsFieldContrastExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_field_contrast
     *
     * @mbggenerated Mon Jan 18 11:15:50 CST 2016
     */
    int updateByExample(@Param("record") UmsFieldContrast record, @Param("example") UmsFieldContrastExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_field_contrast
     *
     * @mbggenerated Mon Jan 18 11:15:50 CST 2016
     */
    int updateByPrimaryKeySelective(UmsFieldContrast record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_field_contrast
     *
     * @mbggenerated Mon Jan 18 11:15:50 CST 2016
     */
    int updateByPrimaryKey(UmsFieldContrast record);

    List<String> selectTableName();
}