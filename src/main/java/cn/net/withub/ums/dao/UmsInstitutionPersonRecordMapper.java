package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsInstitutionPersonRecord;
import cn.net.withub.ums.entity.UmsInstitutionPersonRecordCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsInstitutionPersonRecordMapper {
    int countByExample(UmsInstitutionPersonRecordCriteria example);

    int deleteByExample(UmsInstitutionPersonRecordCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(UmsInstitutionPersonRecord record);

    int insertSelective(UmsInstitutionPersonRecord record);

    List<UmsInstitutionPersonRecord> selectByExample(UmsInstitutionPersonRecordCriteria example);

    UmsInstitutionPersonRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UmsInstitutionPersonRecord record, @Param("example") UmsInstitutionPersonRecordCriteria example);

    int updateByExample(@Param("record") UmsInstitutionPersonRecord record, @Param("example") UmsInstitutionPersonRecordCriteria example);

    int updateByPrimaryKeySelective(UmsInstitutionPersonRecord record);

    int updateByPrimaryKey(UmsInstitutionPersonRecord record);
}