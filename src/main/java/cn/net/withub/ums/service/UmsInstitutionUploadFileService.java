package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsInstitutionUploadFile;
import cn.net.withub.ums.entity.UmsInstitutionUploadFileCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsInstitutionUploadFileService {

    int countByExample(UmsInstitutionUploadFileCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(UmsInstitutionUploadFile record);

    List<UmsInstitutionUploadFile> selectByExample(UmsInstitutionUploadFileCriteria example);

    UmsInstitutionUploadFile selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UmsInstitutionUploadFile record);

    int updateByPrimaryKey(UmsInstitutionUploadFile record);

    int updateByExampleSelective(@Param("record") UmsInstitutionUploadFile record, @Param("example") UmsInstitutionUploadFileCriteria example);
}
