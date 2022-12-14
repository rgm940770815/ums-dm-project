package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsUploadCodeContrast;
import cn.net.withub.ums.entity.UmsUploadCodeContrastExample;
import cn.net.withub.ums.entity.UmsUploadCodeContrastKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsUploadCodeContrastMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_upload_code_contrast
     *
     * @mbggenerated Tue Jan 26 17:26:34 CST 2016
     */
    int countByExample(UmsUploadCodeContrastExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_upload_code_contrast
     *
     * @mbggenerated Tue Jan 26 17:26:34 CST 2016
     */
    int deleteByExample(UmsUploadCodeContrastExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_upload_code_contrast
     *
     * @mbggenerated Tue Jan 26 17:26:34 CST 2016
     */
    int deleteByPrimaryKey(UmsUploadCodeContrastKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_upload_code_contrast
     *
     * @mbggenerated Tue Jan 26 17:26:34 CST 2016
     */
    int insert(UmsUploadCodeContrast record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_upload_code_contrast
     *
     * @mbggenerated Tue Jan 26 17:26:34 CST 2016
     */
    int insertSelective(UmsUploadCodeContrast record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_upload_code_contrast
     *
     * @mbggenerated Tue Jan 26 17:26:34 CST 2016
     */
    List<UmsUploadCodeContrast> selectByExample(UmsUploadCodeContrastExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_upload_code_contrast
     *
     * @mbggenerated Tue Jan 26 17:26:34 CST 2016
     */
    UmsUploadCodeContrast selectByPrimaryKey(UmsUploadCodeContrastKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_upload_code_contrast
     *
     * @mbggenerated Tue Jan 26 17:26:34 CST 2016
     */
    int updateByExampleSelective(@Param("record") UmsUploadCodeContrast record, @Param("example") UmsUploadCodeContrastExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_upload_code_contrast
     *
     * @mbggenerated Tue Jan 26 17:26:34 CST 2016
     */
    int updateByExample(@Param("record") UmsUploadCodeContrast record, @Param("example") UmsUploadCodeContrastExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_upload_code_contrast
     *
     * @mbggenerated Tue Jan 26 17:26:34 CST 2016
     */
    int updateByPrimaryKeySelective(UmsUploadCodeContrast record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_upload_code_contrast
     *
     * @mbggenerated Tue Jan 26 17:26:34 CST 2016
     */
    int updateByPrimaryKey(UmsUploadCodeContrast record);

    List<String> selectCodeType();
}
