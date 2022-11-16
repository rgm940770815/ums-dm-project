package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsInstitutionPersonRecord;
import cn.net.withub.ums.entity.UmsInstitutionPersonRecordCriteria;

import java.util.List;

public interface UmsInstitutionPersonRecordService {

    int countByExample(UmsInstitutionPersonRecordCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(UmsInstitutionPersonRecord record);

    int insertSelective(UmsInstitutionPersonRecord record);

    List<UmsInstitutionPersonRecord> selectByExample(UmsInstitutionPersonRecordCriteria example);

    UmsInstitutionPersonRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UmsInstitutionPersonRecord record);


}
