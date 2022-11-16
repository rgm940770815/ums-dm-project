package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsInstitutionUploadFile;
import cn.net.withub.ums.entity.UmsInstitutionUploadFileCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsInstitutionUploadFileMapper {
    int countByExample(UmsInstitutionUploadFileCriteria example);

    int deleteByExample(UmsInstitutionUploadFileCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(UmsInstitutionUploadFile record);

    int insertSelective(UmsInstitutionUploadFile record);

    List<UmsInstitutionUploadFile> selectByExample(UmsInstitutionUploadFileCriteria example);

    UmsInstitutionUploadFile selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UmsInstitutionUploadFile record, @Param("example") UmsInstitutionUploadFileCriteria example);

    int updateByExample(@Param("record") UmsInstitutionUploadFile record, @Param("example") UmsInstitutionUploadFileCriteria example);

    int updateByPrimaryKeySelective(UmsInstitutionUploadFile record);

    int updateByPrimaryKey(UmsInstitutionUploadFile record);
}